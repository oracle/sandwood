package org.sandwood.compiler.tests.autoDifferentiation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseNumberAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;
import org.sandwood.compiler.util.StringUtil;

public class TestDifferentiationTraces {
	
	private final CompilationOptions opts = new CompilationOptions();
	private final Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
	private final CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
	
	@BeforeEach
	public void beforeEach() {
		ctx.initialize();
	}
	
	@Test
	public void testTraceScalarSimple() {
		
		// Generate differentials for 2 variables.
		DoubleVariable doubleVariableA = DoubleVariable.doubleVariable(10.0);
		DoubleVariable doubleVariableB = DoubleVariable.doubleVariable(70.0);
		
		doubleVariableA.getDifferential(doubleVariableA, ctx);
		doubleVariableA.getDifferential(doubleVariableB, ctx);

		ctx.traces.constructDifferentialTraces();
		
		assertEquals(ctx.traces.getDifferentialVariables().size(), 2);
		assertEquals(ctx.traces.getIntermediateDifferentialVariables().size(), 0);
	}
	
	@Test
	public void testTraceScalarComplex() {
		
		IntVariable intVarA = IntVariable.intVariable(1000);
		intVarA.setAlias("intA");
		IntVariable intVarB = IntVariable.intVariable(2000);
		intVarB.setAlias("intB");
		DoubleVariable doubleVarB = DoubleVariable.doubleVariable(30.0);
		doubleVarB.setAlias("b");
		DoubleVariable doubleVarC = DoubleVariable.doubleVariable(40.0);
		doubleVarC.setAlias("c");
		DoubleVariable doubleVarD = DoubleVariable.doubleVariable(50.0);
		doubleVarD.setAlias("d");
		DoubleVariable doubleVarE = DoubleVariable.doubleVariable(100.0);
		doubleVarE.setAlias("e");

		BooleanVariable guard = intVarA.lessThanEqual(intVarB);
		
		// If Block preparation.
		IfScope ifScope = new IfScope(guard);
		ScopeStack.pushScope(ifScope);
		DoubleVariable doubleVarA = DoubleVariable.doubleVariable(20.0);
		doubleVarA.setAlias("a");
		ScopeStack.popScope(ifScope);
		
		// Else Block preparation.
		ScopeStack.pushScope(ifScope.elseScope);
		// calc = a + (a - b * (c / d));
		DoubleVariable calc = doubleVarA.add(doubleVarA.subtract(doubleVarB.times(doubleVarC.divide(doubleVarD))));
		calc.setAlias("calc");
		ScopeStack.popScope(ifScope.elseScope);

		IfElseNumberAssignmentTask<DoubleVariable> ifElseTask = new IfElseNumberAssignmentTask<DoubleVariable>(
				guard, doubleVarA, calc, null);

		ifElseTask.getDifferential(doubleVarA, ctx);
		
		ctx.traces.constructDifferentialTraces();
		
		assertEquals(ctx.traces.getDifferentialVariables().size(), 8);
		assertEquals(ctx.traces.getIntermediateDifferentialVariables().size(), 0);
	}
	
	@Test
	public void test4DArray() {
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

        ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>>> arrIn = array;
 
        DoubleVariable x = DoubleVariable.doubleVariable(10);
        
		assertTrue(array.isDifferentiable(x));
		
        // Prepare quadruple loop.
		Sandwood.forLoop(start, end, step, true, (i) -> {
			Sandwood.forLoop(start, end, step, true, (j) -> {
				Sandwood.forLoop(start, end, step, true, (k) -> {
					// Setup inner array to use in inner loop.
			        ArrayVariable<DoubleVariable> inner = Variable.arrayVariable(null, 
			        		VariableType.DoubleVariable, IntVariable.intVariable(10));
			        inner.setAlias("inner");
			        arrIn.get(0).get(j).put(k, inner);
					Sandwood.forLoop(start, end, step, true, (l) -> {
				        arrIn.get(0).get(j).get(k).put(l, x.times(i).times(j).times(k).times(l));
					});
				});
			});
		});	

		array.getDifferential(x, ctx).getDifferential(x, ctx);

		ctx.traces.constructDifferentialTraces();
		
		assertEquals(ctx.traces.getDifferentialVariables().size(), 34);
		
		// The non-subarrays generated must have been registered.
		assertEquals(ctx.traces.getIntermediateDifferentialVariables().size(), 8);
	}

}
