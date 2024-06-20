/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.ArrayConstructTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.exceptions.CompilerException;


public class StructureVerifier {

    public static boolean checkStructureEquality(ArrayVariable<?> array1, ArrayVariable<?> array2) {

    	ArrayVariable<?> outerMostArray1 = array1;
    	ArrayVariable<?> outerMostArray2 = array2;
    	
    	while (outerMostArray1.isSubArray()) {
    		outerMostArray1 = outerMostArray1.getOuterArrayDesc().getArray();
    	}
    	
    	while (outerMostArray2.isSubArray()) {
    		outerMostArray2 = outerMostArray2.getOuterArrayDesc().getArray();
    	} 

    	if (!outerMostArray1.getType().equals(outerMostArray2.getType()) ||
    		outerMostArray1.scope() != outerMostArray2.scope()) {
    		return false;
    	}

    	Set<PutTask<?>> checkedPuts = new HashSet<PutTask<?>>();

    	return checkSubstructureEquality(outerMostArray1, outerMostArray2, checkedPuts);
    }
    
    public static boolean checkSubstructureEquality(ArrayVariable<?> array1, ArrayVariable<?> array2, Set<PutTask<?>> checkedPuts) {

    	Set<?> array1Puts = array1.getPuts(array1.scope(), array1.getParent().id());
    	PriorityQueue<?> array1PutsQueue = new PriorityQueue<>(array1Puts);
    	
    	Set<?> array2Puts = array2.getPuts(array2.scope(), array2.getParent().id());
    	PriorityQueue<?> array2PutsQueue = new PriorityQueue<>(array2Puts);
    	
    	while (!array1PutsQueue.isEmpty() && !array2PutsQueue.isEmpty()) {

    		PutTask<?> arr1Put = (PutTask<?>) array1PutsQueue.poll();
    		PutTask<?> arr2Put = (PutTask<?>) array2PutsQueue.poll();
    		
    		// If puts already checked, skip check.
    		if (checkedPuts.contains(arr1Put)) {
    			continue;
    		}

    		checkedPuts.add(arr1Put);
    		
    		ArrayProducingDataflowTask<?> arr1Task = arr1Put.array.getParent();
    		ArrayProducingDataflowTask<?> arr2Task = arr2Put.array.getParent();

    		if(arr1Task.scope() != arr2Task.scope()) {
    			return false;
    		
    		// If subsequent puts are on the same scope, check recursively.
    		} else if (arr1Put.value.getType().isArray() && arr2Put.value.getType().isArray() && arr1Put.value.scope() == arr2Put.value.scope()) {
				// Check structure recursively.
    			if (arr1Put.value.getParent().getType() == DFType.PUT) {
    				PutTask<?> valuePutParent = (PutTask<?>) arr1Put.value.getParent();
    				// Check for get(...).put(...) case.
    				if (valuePutParent.array.getParent().getType() == DFType.GET) {
    					GetTask<?> valueArrayGetParent = (GetTask<?>) valuePutParent.array.getParent();
    					if (valueArrayGetParent.array == arr1Put.array) {
    						break;
    					}
    				}
    			}
    			if(!checkSubstructureEquality((ArrayVariable<?>) arr1Put.value, (ArrayVariable<?>) arr2Put.value, checkedPuts)) {
    				return false;
				}
			}

    		// Use only the type from first array to switch,
    		// since both tasks from arrays are expected to have the same type.
    		DFType arr1Type = arr1Task.getType();
    		DFType arr2Type = arr1Task.getType();
    		
    		if (arr1Type != arr2Type) {
    			return false;
    		}
    		
    		// Switch type for array 1.
    		// It is essential that nodes of the same type are checked.
    		switch (arr1Type) {
	    		case ARRAY_CONSTRUCTOR: {
	    			ArrayConstructTask<?> arr1ConstructTask = (ArrayConstructTask<?>) arr1Task;
	    			ArrayConstructTask<?> arr2ConstructTask = (ArrayConstructTask<?>) arr2Task;
	    			
	    			// Check lengths of arrays.
	    			if (!arr1ConstructTask.length.equivalent(arr2ConstructTask.length)) {
	    				return false;
	    			}
	    			
	    			break;
	    		}
    			case PUT: {
    				PutTask<?> arr1PutTask = (PutTask<?>) arr1Task;
    				PutTask<?> arr2PutTask = (PutTask<?>) arr2Task;
    				
    				// Check equivalence in type and scope.
    				if(!(arr1PutTask.index.equivalent(arr2PutTask.index) && 
    					arr1PutTask.value.getType().equals(arr2PutTask.value.getType()) &&
    					arr1PutTask.value.scope() == arr2PutTask.value.scope())) {
    					return false;
    				}
    				// Checking only for the type of value for one array, since we checked
    				// that they have the same type above.
    				if (arr1PutTask.value.getType().isArray()) {
    					ArrayVariable<?> array1Value = (ArrayVariable<?>) arr1PutTask.value;
    					ArrayVariable<?> array2Value = (ArrayVariable<?>) arr2PutTask.value;
    					
    					// Check structure recursively.
    					if (!checkSubstructureEquality((ArrayVariable<?>) arr1PutTask.value, (ArrayVariable<?>) arr2PutTask.value, checkedPuts)) {
    						return false;
	    				}
    				}
    				break;
    				
    			}
    			case GET: {
    				GetTask<?> arr1GetTask = (GetTask<?>) arr1Task;
    				GetTask<?> arr2GetTask = (GetTask<?>) arr2Task;
    				
    				// Check equivalence in get type and scope
    				if (!arr1GetTask.array.getType().equals(arr2GetTask.array.getType()) ||
    					arr1GetTask.array.scope() != arr2GetTask.array.scope()) {
    					return false;
    				}

    				// As they are both of the same type, checking one is enough.
    				if (arr1GetTask.getOutput().getType().isArray()) {
        				ArrayVariable<?> arrayGet1 = (ArrayVariable<?>) arr1GetTask.array.get(arr1GetTask.index);
        				ArrayVariable<?> arrayGet2 = (ArrayVariable<?>) arr1GetTask.array.get(arr1GetTask.index);
        				
    					// Check structure recursively.
	    				if (!checkSubstructureEquality((ArrayVariable<?>) arrayGet1, (ArrayVariable<?>) arrayGet2, checkedPuts)) {
	    					return false;
	    				}
	    				
    				}
    				
    				break;
    				
    			}

				default:
					throw new CompilerException("Unsupported case. Array structure access should consist only of Get, Put, Array Construct and IF/Else tasks.");

    		}

    	}

    	return true;
    }

    // Functionality added for debugging purposes.
    // TODO: Improve presentation/indentation.
	public static void printStructure(ArrayVariable<?> array) {

    	ArrayVariable<?> outerMostArray = array;
    	
    	while (outerMostArray.isSubArray()) {
    		outerMostArray = outerMostArray.getOuterArrayDesc().getArray();
    	}
    	
    	outerMostArray = outerMostArray.getCurrentInstance();
    	String output = printSubstructure(outerMostArray, "");
    	System.out.println(output);
    }
    
    public static String printSubstructure(ArrayVariable<?> array, String prefix) {
    	
    	String output = "";
    	
    	Set<?> array1Puts = array.getPuts(array.scope(), array.getId());
    	PriorityQueue<?> arrayPutsQueue = new PriorityQueue<>(array1Puts);
    	

    	while (!arrayPutsQueue.isEmpty()) {
    		
    		PutTask<?> put = (PutTask<?>) arrayPutsQueue.poll();
    		
    		ArrayProducingDataflowTask<?> parentTask = put.array.getParent();
    		
    		output += prefix + "Put(Array ID:" + put.array.getId() +
    				", Index ID: " + put.index.getId() + ", Value ID: " + put.value.getId() + ", ";

    		String taskInfo = "Task(" + parentTask.id() + ", " + parentTask.getType() + "), ";
    		
    		output += taskInfo;
    		
    		String innerPrefix = prefix + "\s\s";
    		switch (parentTask.getType()) {
	    		case ARRAY_CONSTRUCTOR: {
	    			ArrayConstructTask<?> arrConstructTask = (ArrayConstructTask<?>) parentTask;
	    			output += "\n" + innerPrefix + "Array(Length ID: " + arrConstructTask.length.getId() + ")";
	    			break;
	    		}
    			case PUT: {
    				PutTask<?> arrPutTask = (PutTask<?>) parentTask;
    				
    				output += "\n" + innerPrefix + "Put(Array ID:" +  arrPutTask.array.getId() +
    				", Index ID: " + arrPutTask.index.getId() + ", Value ID: " + arrPutTask.value.getId();
    				
    				if (arrPutTask.value.getType().isArray()) {
    					String innerPutStr = printSubstructure((ArrayVariable<?>) arrPutTask.value, prefix + "\s\s");
    					output += !innerPutStr.isBlank() ? ", \n" + prefix + innerPutStr : ""; 
    				}
    				
    				output += ")";
    				break;
    			}
    			case GET: {
    				GetTask<?> arrGetTask = (GetTask<?>) parentTask;
    				output += "\n" +  innerPrefix + "PUT(Array ID:" +  arrGetTask.array.getId() +
    				", Index ID: " + arrGetTask.index.getId() + ",";
    				
    				output += ", \n" + innerPrefix +  printSubstructure(arrGetTask.array, prefix + "\s\s") + ")";

    				break;
    				
    			}
    			case IF_ASSIGNMENT: {
    				IfElseAssignmentTask<?> arrIfElseAssignmentTask = (IfElseAssignmentTask<?>) parentTask;

    				if (parentTask.getOutputType().isArray()) {
	    				output += "\n" + innerPrefix + "IfElse(Test: " + arrIfElseAssignmentTask.guard + "," +
	    						printSubstructure((ArrayVariable<?>)arrIfElseAssignmentTask.ifValue, prefix + "\s\s") + 
	    						printSubstructure((ArrayVariable<?>)arrIfElseAssignmentTask.elseValue, prefix + "\s\s");
    				
    				} else {
    					output += "\n" + prefix + "IfElse(Test: " + arrIfElseAssignmentTask.guard + "," +
	    						arrIfElseAssignmentTask.ifValue.toString() + "\n" + prefix + 
								arrIfElseAssignmentTask.elseValue.toString() + ")";
    				}
    				break;
    				
    			}
				default:
					break;
    		}
    		output += "\n" + prefix + ")\n";
    	}
    	return output;
    }
    
    public static boolean checkPutsScopeEquality(ArrayVariable<?> array1, ArrayVariable<?> array2) {

    	Set<?> putsSet1 = array1.getPuts(ScopeStack.getCurrentScope(), array1.getParent().id());
    	Set<?> putsSet2 = array1.getPuts(ScopeStack.getCurrentScope(), array1.getParent().id());
    	if (putsSet1.size() != putsSet2.size()) {
    		return false;
    	}
		Queue<?> putTaskQueue1 = new PriorityQueue<>(putsSet1);
		Queue<?> putTaskQueue2 = new PriorityQueue<>(putsSet2);
    	
		while (!putTaskQueue1.isEmpty()) {
			PutTask<?> put1 = (PutTask<?>) putTaskQueue1.poll();
			PutTask<?> put2 = (PutTask<?>) putTaskQueue2.poll();
			if (put1.scope() != put2.scope() || put1.array.scope() != put2.array.scope() ||
				put1.index.scope() != put2.index.scope() || put1.value.scope() != put2.value.scope()) {
				return false;
			}
		}
		return true;
    }

}
