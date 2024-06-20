/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.autoDifferentiation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.StructureVerifier;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.BlockScope;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseNumberAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;
import org.sandwood.compiler.util.StringUtil;

public class TestDifferentiationArrays {
	
    private final String sourceDir = "src" + File.separator + "test" + File.separator +
			 "resources" + File.separator + "expectedOutputs";

	private final boolean constructingResults = false;
	private final TestStringGenerator tsg = new TestStringGenerator();
	
	private final String firstDifferential1DArrayA = "firstDifferential1DArrayA";
	private final String firstDifferential1DArrayB = "firstDifferential1DArrayB";
	
	private final String firstDifferential2DArrayA = "firstDifferential2DArrayA";
	private final String firstDifferential2DArrayB = "firstDifferential2DArrayB";
	
	private final String firstDifferential3DArrayOriginal = "firstDifferential3DArrayOriginal";
	private final String firstDifferential3DArray = "firstDifferential3DArray";
	private final String firstDifferential3DArrayAlt = "firstDifferential3DArrayAlt";
	
	private final String  secondDifferential2DArrayOriginal = "secondDifferential2DArrayOriginal";
	private final String  secondDifferential2DArray = "secondDifferential2DArray";
	
	private final String  complex3DArrayDifferentialOriginal = "complex3DArrayDifferentialOriginal";
	private final String  complex3DArrayDifferential = "complex3DArrayDifferential";
	private final String  complex3DArrayDifferentialAlt = "complex3DArrayDifferentialAlt";
	
	private final String forLoops1DOriginal = "forLoops1DOriginal";
	private final String forLoops1DDifferential = "forLoops1DDifferential";
	
	private final String forLoops2DOriginal = "forLoops2DOriginal";
	private final String forLoops2DDifferential = "forLoops2DDifferential";
	
	private final String forLoops3DOriginal = "forLoops3DOriginal";
	private final String forLoops3DDifferential = "forLoops3DDifferential";
	
	private final String forLoops4DOriginal = "forLoops4DOriginal";
	private final String forLoops4DDifferential = "forLoops4DDifferential";
	
	private final CompilationOptions opts = new CompilationOptions();
	private final Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
	private final CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
	
	@BeforeEach
	public void beforeEach() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ctx.initialize();
		CompilerState.reset();
		ScopeStack.reset();
	}
	
	@Test
    public void test1DArrayDifferential() {
 
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
        
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<DoubleVariable> array =  
				Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(2));
        array.setAlias("arr");
        array.put(0, powTwoVarA);
        array.put(1, powThreeVarB);
        
        array = array.getCurrentInstance();
        
        assertTrue(array.isDifferentiable(doubleVarA));
        
        ctx.initialize();
        DoubleVariable diff0VarA = array.get(1).getDifferential(doubleVarA, ctx).getCurrentInstance();
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diff0VarA.getParent()).array.getCurrentInstance()));
        
        diff0VarA.getForwardIR(ctx);
        
        String diff0VarAString = ctx.getOutermostScopeTree().toString();
        String fileName1DArrayOriginal = tsg.processString(this, sourceDir, firstDifferential1DArrayA, 
        		diff0VarAString, constructingResults, "txt");
        // Check index.
        ctx.initialize();
        assertEquals(((GetTask<?>) diff0VarA.getParent()).index.getForwardIR(ctx).toString(), "1");
        
        ctx.initialize();
        DoubleVariable diff0VarB = array.get(1).getDifferential(doubleVarB, ctx);
        diff0VarB.getForwardIR(ctx);
        String diff0VarBString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
        String fileName1DArrayDiff = tsg.processString(this, sourceDir, firstDifferential1DArrayB, 
        		diff0VarBString, constructingResults, "txt");

        // Check index, expected to be diff[1].
        assertEquals(((GetTask<?>) diff0VarB.getParent()).index.getForwardIR(ctx).toString(), "1");
        
        // Differentiating on independent variable,
        // so it will return Constant Double 0.0.
        ctx.initialize();
        DoubleVariable diff1Ind = array.get(0).getDifferential(doubleVarC, ctx);
        assertEquals(diff1Ind.getForwardIR(ctx).toString(), "0.0");
        
        // Check output equality.
        String fileToOpen = fileName1DArrayOriginal;
        try {
        	String firstDiffA = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName1DArrayDiff;
        	String firstDiffB = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	firstDiffA = StringUtil.normalizeNewLines(firstDiffA);
        	firstDiffB = StringUtil.normalizeNewLines(firstDiffB);
        	
            // Non-differentiated IR code for 2D array.
            assertEquals(firstDiffA, diff0VarAString);
            
            // Differentiated IR code for 2D array.
            assertEquals(firstDiffB, diff0VarBString);

        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
    }
    
	@Test
    public void test1DArrayDifferentialGet() {
 
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        
        ArrayVariable<DoubleVariable> array =  
				Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(2));
        array.setAlias("arr");
        array.put(0, doubleVarA);
        array.put(1, doubleVarA);
        
        assertTrue(array.isDifferentiable(doubleVarA));
        
        ctx.initialize();
        // This should lead to no exception, since the array is 1D.
        DoubleVariable diff0VarA = array.get(1).getDifferential(doubleVarA, ctx);
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diff0VarA.getParent()).array));
    }
	
	@Test
    public void test1DArrayDifferentialInt() {
 
        IntVariable intVarA = Variable.intVariable(1);
        
        ArrayVariable<IntVariable> array =  
				Variable.arrayVariable(null, VariableType.IntVariable, IntVariable.intVariable(2));
        array.setAlias("arr");
        array.put(0, intVarA);
        array.put(1, intVarA);
        
        assertFalse(array.isDifferentiable(intVarA));
        
        ctx.initialize();
        
        // This should lead to assertion error.
        AssertionError error = Assertions.assertThrows(AssertionError.class, () -> {
        	array.getDifferential(intVarA, ctx);
        });
        Assertions.assertEquals(null, error.getMessage());
    }
	
	/**
	 * Verify that differential structure expands correctly in analogy 
	 * to the original array.
	 */
	@Test
    public void test1DDifferentialStructureExpansion() {
 
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<DoubleVariable> array =  
				Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(3));
        array.setAlias("arr");
        array.put(0, powTwoVarA);
        
        assertTrue(array.isDifferentiable(doubleVarA));

        ctx.initialize();
        DoubleVariable diff0VarA = array.get(0).getDifferential(doubleVarA, ctx);
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diff0VarA.getParent()).array));
        
        diff0VarA.getForwardIR(ctx);
        ctx.initialize();
        
        array.put(1, powThreeVarB);
        
        array = array.getCurrentInstance();
        ArrayVariable<?> diffarray = array.getDifferential(doubleVarA, ctx).getCurrentInstance();
        DoubleVariable diff1VarA = (DoubleVariable) diffarray.get(1);
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diff1VarA.getParent()).array.getCurrentInstance()));
        assertTrue(StructureVerifier.checkStructureEquality(array, diffarray));

        diff1VarA.getForwardIR(ctx);
        
        ctx.initialize();
        array.put(2, doubleVarA);
        array = array.getCurrentInstance();
        
        ArrayVariable<?> diffarray2 = array.getDifferential(doubleVarA, ctx).getCurrentInstance();
        DoubleVariable diff2VarA = (DoubleVariable) diffarray2.get(2);

        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diff2VarA.getParent()).array.getCurrentInstance()));
        assertTrue(StructureVerifier.checkStructureEquality(array, diffarray2));
         
        diff2VarA.getForwardIR(ctx);
    }
	
    @Test
    public void test2DDifferentialStructureExpansion() {

        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<ArrayVariable<DoubleVariable>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(2));
        array.setAlias("arr");
        
        ArrayVariable<DoubleVariable> inner0 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner0.setAlias("inner0");
        
        ArrayVariable<DoubleVariable> inner1 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner1.setAlias("inner1");
        
        // Build 2D Array Structure.
        inner0.put(0, powTwoVarA);
        array.put(0, inner0);

        array = array.getCurrentInstance();

        assertTrue(array.isDifferentiable(doubleVarA));

        DoubleVariable arrGet0Get0 = array.get(0).get(0);
        arrGet0Get0.getForwardIR(ctx);

        ctx.initialize();
        DoubleVariable diffGet0Get0 = arrGet0Get0.getDifferential(doubleVarA, ctx).getCurrentInstance();
        diffGet0Get0.getForwardIR(ctx);
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diffGet0Get0.getParent()).array.getCurrentInstance()));
        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx).getCurrentInstance()));
        
        // Extend the array, then check new differential.
        inner1.put(0, powThreeVarB);
        array.put(1, inner1);
        
        array = array.getCurrentInstance();
        
        ctx.initialize();
        
        DoubleVariable arrGet1Get0 = array.get(1).get(0);
        arrGet1Get0.getForwardIR(ctx);
        
        ctx.initialize();
        
        DoubleVariable diffGet1Get0 = array.getDifferential(doubleVarA, ctx).getCurrentInstance().get(0).get(0);
        diffGet1Get0.getForwardIR(ctx);
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diffGet1Get0.getParent()).array.getCurrentInstance()));
        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx).getCurrentInstance()));
    }

    @Test
    public void test2DArrayDifferential() {

        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
        
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<ArrayVariable<DoubleVariable>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(2));
        array.setAlias("arr");
        
        ArrayVariable<DoubleVariable> inner0 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner0.setAlias("inner0");
        
        ArrayVariable<DoubleVariable> inner1 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner1.setAlias("inner1");
        
        // Build 2D Array Structure.
        inner0.put(0, powTwoVarA);
        inner1.put(0, powThreeVarB);
        array.put(0, inner0);
        array.put(1, inner1);
        
        array = array.getCurrentInstance();
        
        assertTrue(array.isDifferentiable(doubleVarA));
        
        array.get(1).get(0).getForwardIR(ctx);
        
        String arrGetGetString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        String fileName2DArrayOriginal = tsg.processString(this, sourceDir, firstDifferential2DArrayA, 
        		arrGetGetString, constructingResults, "txt");
        
        ctx.initialize();
        
        DoubleVariable diffGetGet = array.get(1).get(0).getDifferential(doubleVarA, ctx);
        array.getDifferential(doubleVarA, ctx).getCurrentInstance().getForwardIR(ctx);
       
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diffGetGet.getParent()).array.getCurrentInstance()));
        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx)));
        
        ctx.initialize();
        diffGetGet.getForwardIR(ctx);
        
        String diffGetGetString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
        
        String fileName2DArrayDiff = tsg.processString(this, sourceDir, firstDifferential2DArrayB, 
        		diffGetGetString, constructingResults, "txt");

        // Indexes check: Expected to be diff[1][0], checking from inner to outer.
        ctx.initialize();
        
        GetTask<?> innerIndexTask = ((GetTask<?>) diffGetGet.getParent());
        assertEquals(innerIndexTask.index.getForwardIR(ctx).toString(), "0");
        
        ctx.initialize();
        assertEquals(innerIndexTask.array.getOuterArrayDesc().getIndex().getForwardIR(ctx).toString(), "1");
        
        ctx.initialize();
        DoubleVariable diff1Ind = array.get(0).get(0).getDifferential(doubleVarC, ctx);
        assertEquals(diff1Ind.getForwardIR(ctx).toString(), "0.0");
        
        // Check output equality.
        String fileToOpen = fileName2DArrayOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName2DArrayDiff;
        	String firstDiff = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	firstDiff = StringUtil.normalizeNewLines(firstDiff);
        	
            // Non-differentiated IR code for 2D array.
            assertEquals(original, arrGetGetString);
            
            // Differentiated IR code for 2D array.
            assertEquals(firstDiff, diffGetGetString);


        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
    }
      
    @Test
    public void test3DArrayDifferential() {

        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), IntVariable.intVariable(2));
        array.setAlias("arr");
        
        ArrayVariable<ArrayVariable<DoubleVariable>> inner0 = Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(1));
        inner0.setAlias("inner0");
        
        ArrayVariable<ArrayVariable<DoubleVariable>> inner1 = Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(1));
        inner1.setAlias("inner1");
        
        ArrayVariable<DoubleVariable> inner2 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner2.setAlias("inner2");
        
        ArrayVariable<DoubleVariable> inner3 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner3.setAlias("inner3");
        
        // Prepare a 3D array.
        array.put(0, inner0);
        array.put(1, inner1);
        inner0.put(0, inner2);
        inner1.put(0, inner3);
        inner2.put(0, powTwoVarA);
        inner3.put(0, powThreeVarB);
        
        array = array.getCurrentInstance();
        
        assertTrue(array.isDifferentiable(doubleVarA));
        
        ArrayVariable<?> initialArray = array;

        ctx.initialize();
        DoubleVariable arrGetGetGet = array.get(1).get(0).get(0);
        
        arrGetGetGet.getForwardIR(ctx);
        String firstDiffBaseOriginalString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(initialArray, ((GetTask<?>) arrGetGetGet.getParent()).array));
        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx).getCurrentInstance()));
        
        String fileName3DArrayOriginal = tsg.processString(this, sourceDir, complex3DArrayDifferentialOriginal, 
        		firstDiffBaseOriginalString, constructingResults, "txt");
        
        ctx.initialize();
        
        DoubleVariable diffGetGetGet = arrGetGetGet.getDifferential(doubleVarA, ctx).getCurrentInstance();
        diffGetGetGet.getForwardIR(ctx);
        
        String firstDiffBaseString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        String fileName3DArrayDiff = tsg.processString(this, sourceDir, complex3DArrayDifferential, 
        		firstDiffBaseString, constructingResults, "txt");
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(initialArray, ((GetTask<?>) diffGetGetGet.getParent()).array));

        ctx.initialize();
        
        DoubleVariable diffGetGetGetAlt = array.getDifferential(doubleVarA, ctx).get(1).get(0).get(0);
        diffGetGetGetAlt.getForwardIR(ctx);
        
        String firstDiffBaseAltString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        String fileName3DArrayAltDiff = tsg.processString(this, sourceDir, complex3DArrayDifferentialAlt, 
        		firstDiffBaseAltString, constructingResults, "txt");

        // Indexes check: Expected to be diff[1][0][0], checking from inner to outer.
        ctx.initialize();
        GetTask<?> innerInnerIndexTask = ((GetTask<?>) diffGetGetGet.getParent());
        assertEquals(innerInnerIndexTask.index.getForwardIR(ctx).toString(), "0");
        
        ctx.initialize();
        assertEquals(innerInnerIndexTask.array.getOuterArrayDesc().getIndex().getForwardIR(ctx).toString(), "0");
        
        ctx.initialize();
        assertEquals(innerInnerIndexTask.array.getOuterArrayDesc().getArray().getOuterArrayDesc().
        		getIndex().getForwardIR(ctx).toString(), "1");

        if (constructingResults) {
        	System.err.println("Tests have been updated to files.");
        	assertFalse(true);
        }
        
        // Check output equality.
        String fileToOpen = fileName3DArrayOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName3DArrayDiff;
        	String firstDiff = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName3DArrayAltDiff;
        	String firstDiffAlt = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	firstDiff = StringUtil.normalizeNewLines(firstDiff);
        	firstDiffAlt = StringUtil.normalizeNewLines(firstDiffAlt);
        	
            // Non-differentiated IR code for 3D array.
            assertEquals(original, firstDiffBaseOriginalString);
            
            // Differentiated IR code for 3D array.
            assertEquals(firstDiff, firstDiffBaseString);
            
            // Differentiated IR code for 3D array (alternative).
            assertEquals(firstDiffAlt, firstDiffBaseAltString);
        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
    }
    
    @Test
    public void test3DArrayDifferentialComplex() {

        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), IntVariable.intVariable(2));
        array.setAlias("arr");
        
        ArrayVariable<ArrayVariable<DoubleVariable>> inner0 = Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(1));
        inner0.setAlias("inner0");
        
        ArrayVariable<ArrayVariable<DoubleVariable>> inner1 = Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(1));
        inner1.setAlias("inner1");
        
        ArrayVariable<DoubleVariable> inner2 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(5));
        inner2.setAlias("inner2");
        
        ArrayVariable<DoubleVariable> inner3 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(5));
        inner3.setAlias("inner3");
        
		BooleanVariable guardVar = BooleanVariable.booleanVariable(true);
		
		IfScope ifScope = new IfScope(guardVar);
		
		// If Block preparation.
		ScopeStack.pushScope(ifScope);
		DoubleVariable doubleVarIf = Variable.doubleVariable(10.0);
		doubleVarIf.setAlias("a");
		ScopeStack.popScope(ifScope);
		
		// Else Block preparation.
		ScopeStack.pushScope(ifScope.elseScope);
		DoubleVariable doubleVarElse = Variable.doubleVariable(20.0);
		DoubleVariable powThreeVarElse = doubleVarElse.times(doubleVarElse.times(doubleVarElse));
		powThreeVarElse.setAlias("b");
		
		ScopeStack.popScope(ifScope.elseScope);
		
		IfElseNumberAssignmentTask<DoubleVariable> ifElseTask = new IfElseNumberAssignmentTask<DoubleVariable>(
				guardVar, doubleVarIf, doubleVarElse, null);
		
		DoubleVariable outIfElse = DoubleVariable.doubleVariable(ifElseTask);
		
		// Prepare 3D Array.
        inner2.put(0, powTwoVarA);
        inner2.put(1, powTwoVarA.add(powTwoVarA));
        inner2.put(2, powTwoVarA.subtract(doubleVarB));
        inner2.put(3, doubleVarA.times(doubleVarB));
        inner2.put(4, IntVariable.intVariable(10));
       
        inner3.put(0, outIfElse.times(powThreeVarB));
        inner3.put(1, outIfElse.divide(outIfElse));
        inner3.put(2, DoubleVariable.doubleVariable(10.0));
        inner3.put(3, IntVariable.intVariable(10));
        inner3.put(4, Sigmoid.sigmoid(doubleVarA));
        
		inner0.put(0, inner2);
		inner1.put(0, inner3);
		
		array.put(0, inner0);
		array.put(1, inner1);
		
		array = array.getCurrentInstance();
		
		assertTrue(array.isDifferentiable(doubleVarA));

        ctx.initialize();
        DoubleVariable arrGetGetGet = array.get(1).get(0).get(4);
        arrGetGetGet.getCurrentInstance().getForwardIR(ctx);

        String firstBaseOriginalString = ctx.getOutermostScopeTree().toString();

        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) arrGetGetGet.getParent()).array));
        array.getCurrentInstance().getForwardIR(ctx);

        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx).getCurrentInstance()));
        
        String fileName3DArrayOriginal = tsg.processString(this, sourceDir, firstDifferential3DArrayOriginal, 
        		firstBaseOriginalString, constructingResults, "txt");

        ctx.initialize();
        
        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> arrDiff = array.getDifferential(doubleVarA, ctx);
        
        DoubleVariable diffGetGetGet = array.get(1).get(0).get(4).getDifferential(doubleVarA, ctx);
        diffGetGetGet.getForwardIR(ctx);
        
        String firstDiffBaseString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        String fileName3DArrayDiff = tsg.processString(this, sourceDir, firstDifferential3DArray, 
        		firstDiffBaseString, constructingResults, "txt");
        
        // Check structure equality.
        assertTrue(StructureVerifier.checkStructureEquality(array, arrDiff.getCurrentInstance()));

        ctx.initialize();
        
        DoubleVariable diffGetGetGetAlt = array.getDifferential(doubleVarA, ctx).get(1).get(0).get(4);
        assertTrue(StructureVerifier.checkStructureEquality(array, ((GetTask<?>) diffGetGetGetAlt.getParent()).array));
        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx).getCurrentInstance()));
        
        diffGetGetGetAlt.getForwardIR(ctx);
        
        String firstDiffBaseAltString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        String fileName3DArrayDiffAlt = tsg.processString(this, sourceDir, firstDifferential3DArrayAlt, 
        		firstDiffBaseAltString, constructingResults, "txt");

        // Indexes check: Expected to be diff[1][0][0], checking from inner to outer.
        ctx.initialize();
        GetTask<?> innerInnerIndexTask = ((GetTask<?>) diffGetGetGet.getParent());
        assertEquals(innerInnerIndexTask.index.getForwardIR(ctx).toString(), "4");
        
        ctx.initialize();
        assertEquals(innerInnerIndexTask.array.getOuterArrayDesc().getIndex().getForwardIR(ctx).toString(), "0");
        
        ctx.initialize();
        assertEquals(innerInnerIndexTask.array.getOuterArrayDesc().getArray().getOuterArrayDesc().
        		getIndex().getForwardIR(ctx).toString(), "1");
        
        if (constructingResults) {
        	System.err.println("Tests have been updated to files.");
        	assertFalse(true);
        }
        
        // Check output equality.
        String fileToOpen = fileName3DArrayOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName3DArrayDiff;
        	String firstDiff = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName3DArrayDiffAlt;
        	String firstDiffAlt = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	firstDiff = StringUtil.normalizeNewLines(firstDiff);
        	
        	firstDiffAlt = StringUtil.normalizeNewLines(firstDiffAlt);
        	
            // Non-differentiated IR code for 3D array.
            assertEquals(original, firstBaseOriginalString);

            // Differentiated IR code for 3D array.
            assertEquals(firstDiff, firstDiffBaseString);
            
            
            
            // Differentiated IR code for 3D array (alternative).
            assertEquals(firstDiffAlt, firstDiffBaseAltString);
        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
    }


    @Test
    public void test2DSecondDifferentialComplex() {

        ArrayVariable<ArrayVariable<DoubleVariable>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(2));
        array.setAlias("arr");
        
        ArrayVariable<DoubleVariable> inner0 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner0.setAlias("inner0");
        
        ArrayVariable<DoubleVariable> inner1 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner1.setAlias("inner1");
        
		BooleanVariable guardVar = BooleanVariable.booleanVariable(true);
		
		IfScope ifScope = new IfScope(guardVar);
		
		// If Block preparation.
		ScopeStack.pushScope(ifScope);
		DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
		DoubleVariable ifVar = doubleVarA;
		ifVar.setAlias("a");
		ScopeStack.popScope(ifScope);
		
		// Else Block preparation.
		ScopeStack.pushScope(ifScope.elseScope);
		DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
		DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
		DoubleVariable elseVar = powThreeVarB;
		elseVar.setAlias("b");
		
		ScopeStack.popScope(ifScope.elseScope);
		
		DoubleVariable doubleVarN = Variable.doubleVariable(10.0);
        
        DoubleVariable powTwoVarN = doubleVarN.times(doubleVarN);

		IfElseNumberAssignmentTask<DoubleVariable> ifElseTask = new IfElseNumberAssignmentTask<DoubleVariable>(
				guardVar, ifVar, elseVar, null);
		
		DoubleVariable outIfElse = DoubleVariable.doubleVariable(ifElseTask);
		
		IntVariable x = IntVariable.intVariable(6);
		
		ArrayVariable<IntVariable> indexes = Variable.arrayVariable(null, VariableType.IntVariable, IntVariable.intVariable(1));
		
		indexes.put(0, IntVariable.intVariable(0));

		// Prepare Large 2D Array.
		inner0.put(0, powTwoVarN);
		inner0.put(1, powTwoVarN.subtract(doubleVarN));
		inner0.put(2, powTwoVarN.add(powTwoVarN));
		inner0.put(3, powTwoVarN.add(10));
		inner0.put(4, powTwoVarN.add(10));
		
        inner1.put(0, powThreeVarB);
        inner1.put(1, powThreeVarB.times(powThreeVarB));
        inner1.put(2, powThreeVarB.times(powThreeVarB.times(powThreeVarB)));
        inner1.put(3, powThreeVarB.divide(10));
        inner1.put(4, powThreeVarB.divide(doubleVarA));
        inner1.put(5, powThreeVarB.divide(doubleVarA.times(20)));
        inner1.put(x, outIfElse);
        
		array.put(0, inner0);
		array.put(indexes.get(0), inner1);
        
        array = array.getCurrentInstance();
        
        assertTrue(array.isDifferentiable(doubleVarA));
 
        array.get(0).get(0).getForwardIR(ctx);

        String secondDifferentialBaseStringOriginal = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
        String fileName2DArrayOriginal = tsg.processString(this, sourceDir, secondDifferential2DArrayOriginal, 
        		secondDifferentialBaseStringOriginal, constructingResults, "txt");
        ctx.initialize();

        // Expose the latest version of each array between differentials, as no get operation is applied.
        DoubleVariable arrayDiff = array.get(0).get(0).getDifferential(doubleVarA, ctx).getDifferential(doubleVarA, ctx);
        
        arrayDiff.getCurrentInstance().getForwardIR(ctx);

        String secondDifferentialBaseString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());

        String fileName2DArray = tsg.processString(this, sourceDir, secondDifferential2DArray, 
        		secondDifferentialBaseString, constructingResults, "txt");      

        ctx.initialize();
        
        array.getDifferential(doubleVarA, ctx).getCurrentInstance().getDifferential(doubleVarA, ctx).getCurrentInstance().getForwardIR(ctx);
        
        // Verify structure.
        assertTrue(StructureVerifier.checkStructureEquality(array, array.getDifferential(doubleVarA, ctx).
        		getCurrentInstance().getDifferential(doubleVarA, ctx).getCurrentInstance()));

        if (constructingResults) {
        	System.err.println("Tests have been updated to files.");
        	assertFalse(true);
        }
        
        // Check output equality.
        String fileToOpen = fileName2DArrayOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileName2DArray;
        	String secondDifferential = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	secondDifferential = StringUtil.normalizeNewLines(secondDifferential);
            // Non-differentiated IR code for 2D array.
            assertEquals(secondDifferentialBaseStringOriginal, original);
            // Differentiated IR code for 2D array.
            assertEquals(secondDifferentialBaseString, secondDifferential);

        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
    }
    
    @Test
    public void test2DArrayScope() {

        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
        DoubleVariable powTwoVarA = doubleVarA.times(doubleVarA);
        DoubleVariable powThreeVarB = doubleVarB.times(doubleVarB.times(doubleVarB));
        
        ArrayVariable<ArrayVariable<DoubleVariable>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(3));
        array.setAlias("arr");


        ArrayVariable<DoubleVariable> inner0 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner0.setAlias("inner0");

        ArrayVariable<DoubleVariable> inner1 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner1.setAlias("inner1");

        // Prepare 2D array.
        array.put(0, inner0);
        array.put(1, inner1);
        inner0.put(0, powTwoVarA);
        inner1.put(0, powThreeVarB);
        
		array = array.getCurrentInstance();
		
		assertTrue(array.isDifferentiable(doubleVarA));

        ArrayVariable<ArrayVariable<DoubleVariable>> arrDiffVar1 = array.getDifferential(doubleVarA, ctx).getCurrentInstance();

        assertTrue(StructureVerifier.checkPutsScopeEquality(array.getCurrentInstance(), arrDiffVar1));
        
        BlockScope blockScope2 = new BlockScope("BlockScope2");
        
        ScopeStack.pushScope(blockScope2);

        // Extend array in different-than-global scope.
        ArrayVariable<DoubleVariable> inner2 = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(1));
        inner2.setAlias("inner2");
        
        array.put(2, inner2);
        inner2.put(0, doubleVarC);

        ctx.initialize();
        
        ArrayVariable<ArrayVariable<DoubleVariable>> arrDiffVar2 = 
        		array.getDifferential(doubleVarA, ctx).getCurrentInstance();
        
        arrDiffVar2.getForwardIR(ctx);

        
        assertTrue(StructureVerifier.checkPutsScopeEquality(array.getCurrentInstance(), arrDiffVar2));
        
        ScopeStack.popScope(blockScope2);
        
        // Check again initial array.
        assertTrue(StructureVerifier.checkPutsScopeEquality(array.getCurrentInstance(), arrDiffVar1));
    }

	@Test
	public void testLoops1D() {

		IntVariable start = IntVariable.intVariable(1);
		IntVariable end = IntVariable.intVariable(5);
		IntVariable step = IntVariable.intVariable(1);
		
    	DoubleVariable doubleVarA = Variable.doubleVariable(50.0);
        
        ArrayVariable<DoubleVariable> array = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(5));
        array.setAlias("arr");
        
        DoubleVariable x = DoubleVariable.doubleVariable(10);
        
		assertTrue(array.isDifferentiable(x));
        
        // Prepare 1D array.
        array.put(0, x.times(x));

        // Prepare loop.Ø
		Sandwood.forLoop(start, end, step, true, (i) -> {
			array.put(i, i.times(x).times(x));
		});
		
		ctx.initialize();
		
		array.getCurrentInstance().getForwardIR(ctx);

		String originalString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		String fileNameOriginal = tsg.processString(this, sourceDir, forLoops1DOriginal, 
				originalString, constructingResults, "txt");   

		ctx.initialize();
		
		ArrayVariable<?> diffVariable = array.getDifferential(doubleVarA, ctx).getCurrentInstance();
		
		diffVariable.getForwardIR(ctx);
		
		String differentialString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		String fileNameDifferential = tsg.processString(this, sourceDir, forLoops1DDifferential, 
				differentialString, constructingResults, "txt");   
		
		assertTrue(StructureVerifier.checkStructureEquality(array.getCurrentInstance(), diffVariable));

		// This should NOT be included in the generated code.
		array.getCurrentInstance().put(1, x.times(x).times(x));

        // Check output equality.
        String fileToOpen = fileNameOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameDifferential;
        	String differential = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	differential = StringUtil.normalizeNewLines(differential);
            // Non-differentiated IR code for 2D array.
            assertEquals(original, originalString);

            // Differentiated IR code for 2D array.
            assertEquals(differential, differentialString);

        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
	}
	
	@Test
	public void testForLoops2DArray() {

		IntVariable start = IntVariable.intVariable(0);
		IntVariable end = IntVariable.intVariable(9);
		IntVariable step = IntVariable.intVariable(1);

        ArrayVariable<ArrayVariable<DoubleVariable>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(10));
        array.setAlias("arr");
 
        DoubleVariable x = DoubleVariable.doubleVariable(10);
        
		assertTrue(array.isDifferentiable(x));
		
        ScopeStack.pushScope(GlobalScope.scope);
        
        // Prepare double loop.
		Sandwood.forLoop(start, end, step, true, (i) -> {
			ArrayVariable<DoubleVariable> inner = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(10));
	        inner.setAlias("inner");
	        array.put(i, inner);
	        
			Sandwood.forLoop(start, end, step, true, (j) -> {
		        array.get(i).put(j, x.times(i));
			});
		});	

		ScopeStack.popScope(GlobalScope.scope);

		ctx.initialize();
		
		array.getCurrentInstance().getForwardIR(ctx);
		
		String originalString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		String fileNameOriginal = tsg.processString(this, sourceDir, forLoops2DOriginal, 
				originalString, constructingResults, "txt"); 

		ctx.initialize();
		
		ArrayVariable<?> diffVariable = array.getDifferential(x, ctx).getCurrentInstance();
		diffVariable.getForwardIR(ctx);
		
		String differentialString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		assertTrue(StructureVerifier.checkStructureEquality(array.getCurrentInstance(), diffVariable.getCurrentInstance()));
		
		String fileNameDifferential = tsg.processString(this, sourceDir, forLoops2DDifferential, 
				differentialString, constructingResults, "txt"); 

		String fileToOpen = fileNameOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameDifferential;
        	String differential = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	differential = StringUtil.normalizeNewLines(differential);
            // Non-differentiated IR code for 2D array.
            assertEquals(original, originalString);
            
            // Differentiated IR code for 2D array.
            assertEquals(differential, differentialString);

        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
	}
	 
	@Test
	public void testForLoops3DArray() {
		IntVariable start = IntVariable.intVariable(0);
		IntVariable end = IntVariable.intVariable(9);
		IntVariable step = IntVariable.intVariable(1);
		
    	ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), IntVariable.intVariable(10));
        
    	array.setAlias("arr");
        

        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> arrIn = array.getCurrentInstance();
 
        DoubleVariable x = DoubleVariable.doubleVariable(10);
        
		assertTrue(array.isDifferentiable(x));
		
        // Prepare triple loop.
		Sandwood.forLoop(start, end, step, true, (i) -> {
			ArrayVariable<ArrayVariable<DoubleVariable>> inner = Variable.arrayVariable(null, 
					VariableType.arrayType(VariableType.DoubleVariable), IntVariable.intVariable(10));
	        arrIn.put(i, inner);
	        
			Sandwood.forLoop(start, end, step, true, (j) -> {
				// Setup inner array to use in inner loop.
				ArrayVariable<DoubleVariable> innerInner = Variable.arrayVariable(null, 
						VariableType.DoubleVariable, IntVariable.intVariable(10));
		        arrIn.get(i).put(j, innerInner);
		        
		        Sandwood.forLoop(start, end, step, true, (k) -> {
		        	arrIn.get(i).get(j).put(k, x.times(i.times(j)));
		        });
			});
		});

		ctx.initialize();
		arrIn.getCurrentInstance().getForwardIR(ctx);
		String originalString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		String fileNameOriginal = tsg.processString(this, sourceDir, forLoops3DOriginal, 
				originalString, constructingResults, "txt"); 
		
		ctx.initialize();
		ArrayVariable<?> diffVariable = arrIn.getDifferential(x, ctx).getCurrentInstance();
		diffVariable.getForwardIR(ctx);
	
		String differentialString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		String fileNameDifferential = tsg.processString(this, sourceDir, forLoops3DDifferential, 
				differentialString, constructingResults, "txt");
		
//		System.out.println(originalString);
//		System.out.println(differentialString);
		
		assertTrue(StructureVerifier.checkStructureEquality(arrIn.getCurrentInstance(), diffVariable));
		
		String fileToOpen = fileNameOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameDifferential;
        	String differential = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	original = StringUtil.normalizeNewLines(original);
        	differential = StringUtil.normalizeNewLines(differential);
            // Non-differentiated IR code for 2D array.
            assertEquals(original, originalString);
            
            // Differentiated IR code for 2D array.
            assertEquals(differential, differentialString);

        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
	}

	/**
	 * This test is a case where a 4D array is created,
	 * Then filled using a quadruple loop.
	 * Following this, 4 chain differentials are applied. 
	 */
	@Test
	public void testForLoops4DArrayVeryComplex() {
		IntVariable start = IntVariable.intVariable(0);
		IntVariable end = IntVariable.intVariable(9);
		IntVariable step = IntVariable.intVariable(1);
		
    	ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> array =  
				Variable.arrayVariable(null, VariableType.arrayType(VariableType.arrayType(
						VariableType.arrayType(VariableType.DoubleVariable))), 
						IntVariable.intVariable(1));
        
        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> inner0 = 
        		Variable.arrayVariable(null, VariableType.arrayType(
        				VariableType.arrayType(VariableType.DoubleVariable)), 
        				IntVariable.intVariable(10));

        ArrayVariable<ArrayVariable<DoubleVariable>> inner1 = 
        		Variable.arrayVariable(null, 
        				VariableType.arrayType(VariableType.DoubleVariable), 
        				IntVariable.intVariable(10));
        
        ArrayVariable<DoubleVariable> inner2 = 
        		Variable.arrayVariable(null, 
        				VariableType.DoubleVariable, 
        				IntVariable.intVariable(10));
        
        // Prepare 4D array.
        array.put(0, inner0);
        inner0.put(0, inner1);
        inner1.put(0, inner2);
        
		array = array.getCurrentInstance();

        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> arrIn = array;
 
        DoubleVariable x = DoubleVariable.doubleVariable(10);
        
		assertTrue(array.isDifferentiable(x));
		
        // Prepare quadruple loop.
		Sandwood.forLoop(start, end, step, true, (i) -> {
			Sandwood.forLoop(start, end, step, true, (j) -> {
				Sandwood.forLoop(start, end, step, true, (k) -> {
					// Setup inner array to use in inner loop.
			        ArrayVariable<DoubleVariable> inner = Variable.arrayVariable(null, VariableType.DoubleVariable, IntVariable.intVariable(10));
			        inner.setAlias("inner");
			        arrIn.get(0).get(j).put(k, inner);
					Sandwood.forLoop(start, end, step, true, (l) -> {
				        arrIn.get(0).get(j).get(k).put(l, x.times(i).times(j).times(k).times(l));
					});
				});
			});
		});	

		array = array.getCurrentInstance();

		ctx.initialize();
		array.getForwardIR(ctx);

		String originalString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		String fileNameOriginal = tsg.processString(this, sourceDir, forLoops4DOriginal, 
				originalString, constructingResults, "txt"); 

		ctx.initialize();
		
		// Generate quadruple (!) differential,
		// resulting in huge expressions that compute to 0.
		ArrayVariable<?> arrFour = array.getDifferential(x, ctx).
			  getDifferential(x, ctx).
			  getDifferential(x, ctx).
			  getDifferential(x, ctx).
			  getCurrentInstance();
		
		arrFour.getForwardIR(ctx);

		ctx.traces.constructDifferentialTraces();

		assertTrue(StructureVerifier.checkStructureEquality(array, arrFour));
		
		String differentialString = StringUtil.normalizeNewLines(ctx.getOutermostScopeTree().toString());
		
		String fileNameDifferential = tsg.processString(this, sourceDir, forLoops4DDifferential, 
				differentialString, constructingResults, "txt"); 

		String fileToOpen = fileNameOriginal;
        try {
        	String original = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameDifferential;
        	String differential = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	original = StringUtil.normalizeNewLines(original);
        	differential = StringUtil.normalizeNewLines(differential);
            // Non-differentiated IR code for 4D array.
            assertEquals(original, originalString);
            
            // Differentiated IR code for 4D array.
            assertEquals(differential, differentialString);

        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
	}
}
