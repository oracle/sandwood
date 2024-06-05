/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.autoDifferentiation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Log;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Max;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseNumberAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class TestIfDifferentiableScalar {
	
	private final DoubleVariable zero = Variable.doubleVariable(0.0);
	private final DoubleVariable doubleA = Variable.doubleVariable(5.0);
    private final DoubleVariable doubleB = Variable.doubleVariable(25.0);
    private final IntVariable intA = Variable.intVariable(30);
    private final IntVariable intB = Variable.intVariable(70);
    private final BooleanVariable bool = Variable.booleanVariable(false);

	@Test
	public void testDoubleVariable() {
		// Check differentiation on same variable.
		assertTrue(doubleA.isDifferentiable(doubleA));
		
		// Check differentiation on different variable.
		assertTrue(doubleA.isDifferentiable(doubleB));
		
		// Check differentiation on different var (non-differentiable).
		assertTrue(doubleA.isDifferentiable(intA));	
	}
	
	@Test
	public void testDoubleToIntCast() {
		// Check differentiation on same variable.
		IntVariable castDoubleA = doubleA.castToInteger();
		assertFalse(castDoubleA.isDifferentiable(doubleA));
		
		// Check differentiation on independent vars.
		assertTrue(castDoubleA.isDifferentiable(intA));
		assertTrue(castDoubleA.isDifferentiable(intB));
	}
	
	@Test
	public void testIntVariable() {
		// Check differentiation on non-differentiable variable.
		assertTrue(intA.isDifferentiable(intB));
		
		// Check differentiation on differentiable variable.
		assertTrue(intA.isDifferentiable(doubleB));
		
		// Check differentiation on same variable.
		assertFalse(intA.isDifferentiable(intA));
	}
	
	@Test
	public void testIntToDoubleCast() {
		// Check on same variable.
		DoubleVariable castIntA = intA.castToDouble();
		
		assertFalse(castIntA.isDifferentiable(intA));
		
		// Check on differentiable independent variable.
		assertTrue(castIntA.isDifferentiable(doubleA));
		
		// Check on non-differentiable independent variable.
		assertTrue(castIntA.isDifferentiable(intB));
	}
	
	@Test
	public void testDoubleDoubleSum() {
		DoubleVariable doubleDoubleSum = doubleA.add(doubleA.add(doubleB));
		assertTrue(doubleDoubleSum.isDifferentiable(doubleA));
		assertTrue(doubleDoubleSum.isDifferentiable(doubleB));
		
		// Check for non-differentiable types.
		assertTrue(doubleDoubleSum.isDifferentiable(doubleB));
		assertTrue(doubleDoubleSum.isDifferentiable(bool));
	}
	
	@Test
	public void testDoubleIntSum() {
		DoubleVariable doubleIntAdd = doubleA.add(intA);
		assertTrue(doubleIntAdd.isDifferentiable(doubleA));
		assertTrue(doubleIntAdd.isDifferentiable(doubleB));
		
		// Check for non-differentiable, non-utilized variable.
		assertTrue(doubleIntAdd.isDifferentiable(intB));
		
		// Check for non-differentiable types.
		assertFalse(doubleIntAdd.isDifferentiable(intA));
	}
	
	@Test
	public void testIntIntSum() {
		IntVariable intIntSum = (IntVariable) intA.add(intB);
		assertTrue(intIntSum.isDifferentiable(doubleA));
		// Check for non-differentiable types.
		assertFalse(intIntSum.isDifferentiable(intA));
		assertFalse(intIntSum.isDifferentiable(intB));
	}
	
	@Test
	public void testDoubleIntConsequtiveSum() {
		DoubleVariable doubleIntAdd = doubleA.add(intA.add(intB));
		assertTrue(doubleIntAdd.isDifferentiable(doubleA));
		assertTrue(doubleIntAdd.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertFalse(doubleIntAdd.isDifferentiable(intB));
		assertFalse(doubleIntAdd.isDifferentiable(intA));
	}
	
	@Test
	public void testdoubleDoubleSubtraction() {
		DoubleVariable doubleDoubleSub = (DoubleVariable) doubleA.subtract(doubleB);
		assertTrue(doubleDoubleSub.isDifferentiable(doubleA));
		assertTrue(doubleDoubleSub.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleDoubleSub.isDifferentiable(intA));
		assertTrue(doubleDoubleSub.isDifferentiable(bool));
	}

	@Test
	public void testDoubleIntSubtraction() {
		DoubleVariable doubleIntSub = (DoubleVariable) doubleA.subtract(intA);
		assertTrue(doubleIntSub.isDifferentiable(doubleA));
		assertTrue(doubleIntSub.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleIntSub.isDifferentiable(intB));
		assertFalse(doubleIntSub.isDifferentiable(intA));
	}
	
	@Test
	public void testIntIntSubtraction() {
		IntVariable intIntSub = (IntVariable) intA.subtract(intB);
		assertTrue(intIntSub.isDifferentiable(doubleA));
		assertTrue(intIntSub.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertFalse(intIntSub.isDifferentiable(intA));
		assertFalse(intIntSub.isDifferentiable(intB));
	}
	
	@Test
	public void testDoubleDoubleMultiplication() {
		DoubleVariable doubleDoubleMul = (DoubleVariable) doubleA.times(doubleB);
		assertTrue(doubleDoubleMul.isDifferentiable(doubleA));
		assertTrue(doubleDoubleMul.isDifferentiable(doubleB));
		
		// Check for non-differentiable types.
		assertTrue(doubleDoubleMul.isDifferentiable(intA));
		assertTrue(doubleDoubleMul.isDifferentiable(bool));
	}
	
	@Test
	public void testDoubleIntMultiplication() {
		DoubleVariable doubleIntMul = (DoubleVariable) doubleA.times(intA);
		assertTrue(doubleIntMul.isDifferentiable(doubleA));
		assertTrue(doubleIntMul.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleIntMul.isDifferentiable(intB));
		assertFalse(doubleIntMul.isDifferentiable(intA));
	}
	
	@Test
	public void testIntIntMultiplication() {
		IntVariable intIntMul = (IntVariable) intA.times(intB);
		assertTrue(intIntMul.isDifferentiable(doubleA));
		assertTrue(intIntMul.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertFalse(intIntMul.isDifferentiable(intA));
		assertFalse(intIntMul.isDifferentiable(intB));
	}
	
	@Test
	public void testDoubleDoubleDivision() {
		DoubleVariable doubleDoubleDiv = (DoubleVariable) doubleA.divide(doubleB);
		assertTrue(doubleDoubleDiv.isDifferentiable(doubleA));
		assertTrue(doubleDoubleDiv.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleDoubleDiv.isDifferentiable(intA));
		assertTrue(doubleDoubleDiv.isDifferentiable(bool));
	}
	
	@Test
	public void testDoubleIntDivision() {
		DoubleVariable doubleIntDiv = (DoubleVariable) doubleA.divide(intA);
		assertTrue(doubleIntDiv.isDifferentiable(doubleA));
		assertTrue(doubleIntDiv.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleIntDiv.isDifferentiable(intB));
		assertFalse(doubleIntDiv.isDifferentiable(intA));
	}
	
	@Test
	public void testIntIntDivision() {
		IntVariable intIntDiv = (IntVariable) intA.divide(intB);
		assertTrue(intIntDiv.isDifferentiable(doubleA));
		assertTrue(intIntDiv.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertFalse(intIntDiv.isDifferentiable(intA));
		assertFalse(intIntDiv.isDifferentiable(intB));
	}

	@Test
	public void testNumberNegate() {
		// Double Negate check
		DoubleVariable doubleANegate = doubleA.negate();
		assertTrue(doubleANegate.isDifferentiable(doubleANegate));
		assertTrue(doubleANegate.isDifferentiable(doubleA));
		assertTrue(doubleANegate.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleANegate.isDifferentiable(intA));
		
		// Integer Negate check.
		IntVariable intANegate = intA.negate();
		assertTrue(intANegate.isDifferentiable(doubleA));
		// Check for non-differentiable types.
		assertFalse(intANegate.isDifferentiable(intA));
		assertTrue(intANegate.isDifferentiable(intB));
	}
	
	@Test
	public void testNumberRemainder() {
		DoubleVariable doubleRemainder = doubleA.remainder(doubleB);

		// Double mod Double case.
		assertFalse(doubleRemainder.isDifferentiable(doubleA));
		assertFalse(doubleRemainder.isDifferentiable(doubleB));
		// The intA variable is not contained,
		// therefore remainder is differentiable.
		assertTrue(doubleRemainder.isDifferentiable(intA));
		
		// Double mod Integer case.
		DoubleVariable doubleIntRemainder = doubleA.remainder(intA);
		assertFalse(doubleIntRemainder.isDifferentiable(doubleA));
		assertFalse(doubleIntRemainder.isDifferentiable(intA));
		
		// The intB variable is not contained,
		// therefore remainder is differentiable.
		assertTrue(doubleIntRemainder.isDifferentiable(intB));
		
		// Int mod Integer case.
		IntVariable intIntRemainder = intA.remainder(intB);
		assertFalse(intIntRemainder.isDifferentiable(intA));
		assertFalse(intIntRemainder.isDifferentiable(intB));
		// The doubleA variable is not contained,
		// therefore remainder is differentiable.
		assertTrue(intIntRemainder.isDifferentiable(doubleA));
	}
	
	@Test
	public void testNumberLog() {
		DoubleVariable doubleLog = Log.log(doubleA);

		// Double mod Double case.
		assertTrue(doubleLog.isDifferentiable(doubleA));
		assertTrue(doubleLog.isDifferentiable(doubleB));
		assertTrue(doubleLog.isDifferentiable(intA));
		
		// Double mod Integer case.
		DoubleVariable intLog = Log.log(intA);
		assertTrue(intLog.isDifferentiable(intB));
		assertFalse(intLog.isDifferentiable(intA));
		
    	// NOTE: We are aware that this will return true for the case
    	// that the input is a 0, but the differential will essentially
    	// fail upon execution, due to divide-by-zero error.
		// So the code generation is possible, but the code will fail.
		DoubleVariable zeroLog = Log.log(zero);
		assertTrue(zeroLog.isDifferentiable(zero));
	}
	
	@Test
	public void testNumberSigmoid() {
		DoubleVariable doubleASigmoid = Sigmoid.sigmoid(doubleA);
		assertTrue(doubleASigmoid.isDifferentiable(doubleA));
		assertTrue(doubleASigmoid.isDifferentiable(doubleB));
		// Check for non-differentiable types.
		assertTrue(doubleASigmoid.isDifferentiable(intA));
	}

	
	@Test
	public void testDoubleDoubleMax() {
		DoubleVariable doubleDoubleMax = Max.max(doubleA, doubleB);
		assertFalse(doubleDoubleMax.isDifferentiable(doubleA));
		assertFalse(doubleDoubleMax.isDifferentiable(doubleB));
		
		// Check for non-differentiable types.
		assertTrue(doubleDoubleMax.isDifferentiable(intA));
		assertTrue(doubleDoubleMax.isDifferentiable(bool));
	}
	
	@Test
	public void intIntMax() {

		IntVariable intIntMax = Max.max(intA, intB);
		assertTrue(intIntMax.isDifferentiable(doubleA));
		// Check for non-differentiable types.
		assertFalse(intIntMax.isDifferentiable(intA));
		assertFalse(intIntMax.isDifferentiable(intB));
		assertTrue(intIntMax.isDifferentiable(bool));
	}
	
	@Test
	public void testIntIntMax() {
		IntVariable intIntSum = (IntVariable) intA.add(intB);
		
		assertTrue(intIntSum.isDifferentiable(doubleA));
		// Check for non-differentiable types.
		assertFalse(intIntSum.isDifferentiable(intA));
		assertFalse(intIntSum.isDifferentiable(intB));
	}
	
	@Test
	public void testIfElseAssignmentSimpleInt() {
		
		IntVariable testVar1 = IntVariable.intVariable(10);
		IntVariable testVar2 = IntVariable.intVariable(20);
		IntVariable testVar3 = IntVariable.intVariable(30);
		IfElseNumberAssignmentTask<IntVariable> task = new IfElseNumberAssignmentTask<IntVariable>(
				testVar1.lessThan(testVar2), testVar1, testVar2, null);
		
		assertFalse(task.isDifferentiable(testVar1));
		assertFalse(task.isDifferentiable(testVar2));
		// Variable not contained anywhere, therefore both sides are differentiable.
		assertTrue(task.isDifferentiable(testVar3));

	}
	
	@Test
	public void testIfElseAssignmentAdd() {
		
		IntVariable intVar1 = IntVariable.intVariable(10);
		DoubleVariable doubleVar2 = DoubleVariable.doubleVariable(20.0);
		DoubleVariable doubleVar3 = DoubleVariable.doubleVariable(30.0);
		DoubleVariable doubleVar4 = DoubleVariable.doubleVariable(40.0);
		DoubleVariable doubleVar5 = DoubleVariable.doubleVariable(50.0);
		DoubleVariable calc = doubleVar5.add(doubleVar2.subtract(doubleVar3.times(doubleVar4.divide(10))));
		IfElseNumberAssignmentTask<DoubleVariable> task = new IfElseNumberAssignmentTask<DoubleVariable>(
				intVar1.lessThanEqual(DoubleVariable.doubleVariable(10)), doubleVar2, calc, null);
		
		// Guard contains variable.
		assertFalse(task.isDifferentiable(intVar1));
		// Guard does not contain variable, and both conditions are differentiable.
		assertTrue(task.isDifferentiable(doubleVar2));
		assertTrue(task.isDifferentiable(doubleVar3));
	}
	
	@Test
	public void testForTask() {
		IntVariable intVar1 = IntVariable.intVariable(10);
		IntVariable intVar2 = IntVariable.intVariable(20);
		IntVariable intVar3 = IntVariable.intVariable(30);
		IntVariable intVar4 = IntVariable.intVariable(40);
		IntVariable calc = intVar1.add(intVar2.subtract(intVar3.times(intVar4.divide(10))));
		IntVariable intVar5 = IntVariable.intVariable(40);
		ForTask task = new ForTask(intVar1, intVar2, calc, true);
		
		assertFalse(task.isDifferentiable(intVar1));
		assertFalse(task.isDifferentiable(intVar2));
		assertFalse(task.isDifferentiable(intVar3));
		assertFalse(task.isDifferentiable(intVar4));
		assertTrue(task.isDifferentiable(intVar5));
	}

}