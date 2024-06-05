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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Add;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Exp;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Log;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Max;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Subtract;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant.ConstantDoubleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseNumberAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.util.TestStringGenerator;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;
import org.sandwood.compiler.util.StringUtil;

public class TestDifferentiationScalar {
	
    private final String sourceDir = "src" + File.separator + "test" + File.separator + "resources" + File.separator
            + "expectedOutputs";
    
    private final boolean constructingResults = false;
    private final TestStringGenerator tsg = new TestStringGenerator();
    
    private final String firstDifferentialATag = "firstDifferentialA";
    private final String secondDifferentialATag = "secondDifferentialA";
    
    private final String firstDifferentialBTag = "firstDifferentialB";
    private final String secondDifferentialBTag = "secondDifferentialB";
    
    private final String ifElseDifferentialATag = "ifElseDifferentialA";
    private final String ifElseDifferentialBTag = "ifElseDifferentialB";
    private final String ifElseDifferentialETag = "ifElseDifferentialE";
    
    private final ArrayList<Variable<?>> outputForCtx = new ArrayList<>();
    
	private final CompilationOptions opts = new CompilationOptions();
	private final Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
	private final CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);

	@BeforeEach
	public void prepareEach() {
		ctx.initialize();
	}
	
    @Test
    public void testDoubleConstant() {
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable differentialA = (DoubleVariable) doubleVarA.getDifferential(doubleVarA, ctx);
        ProducingDataflowTask<?> taskA = differentialA.getParent();
        switch (taskA.getType()) {
            case CONSTANT_DOUBLE:
                ConstantDoubleTask doubleTaskA = (ConstantDoubleTask) taskA;
                assertEquals(doubleTaskA.v, 1.0);
                break;
            default:
                assertFalse(true);
        }
    }
 
    @Test
    public void testDoubleConstantAgainstAnotherVar() {
        DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
        DoubleVariable differentialB = (DoubleVariable) doubleVarA.getDifferential(doubleVarB, ctx);
        ProducingDataflowTask<?> taskB = differentialB.getParent();
 
        switch (taskB.getType()) {
	        case CONSTANT_DOUBLE:
	            ConstantDoubleTask doubleTaskB = (ConstantDoubleTask) taskB;
	            assertEquals(doubleTaskB.v, 0.0);
	            break;
	        default:
	            assertFalse(true);
 
        }
    }
 
    @Test
    public void testDoubleAddDifferentials() {
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
        DoubleVariable doubleAdd = doubleVarA.add(doubleVarB);
        DoubleVariable differentialA = (DoubleVariable) doubleAdd.getDifferential(doubleVarA, ctx);
        DoubleVariable differentialB = (DoubleVariable) doubleAdd.getDifferential(doubleVarB, ctx);
        DoubleVariable differentialC = (DoubleVariable) doubleAdd.getDifferential(doubleVarC, ctx);
        Add<?, ?, ?> differentialTaskA = (Add<?, ?, ?>) differentialA.getParent();
        Add<?, ?, ?> differentialTaskB = (Add<?, ?, ?>) differentialB.getParent();
        Add<?, ?, ?> differentialTaskC = (Add<?, ?, ?>) differentialC.getParent();

        ConstantDoubleTask leftA = (ConstantDoubleTask) differentialTaskA.left.getParent();
        ConstantDoubleTask rightA = (ConstantDoubleTask) differentialTaskA.right.getParent();
        assertEquals(leftA.v, 1.0);
        assertEquals(rightA.v, 0.0);
        
        ConstantDoubleTask leftB = (ConstantDoubleTask) differentialTaskB.left.getParent();
        ConstantDoubleTask rightB = (ConstantDoubleTask) differentialTaskB.right.getParent();
        assertEquals(leftB.v, 0.0);
        assertEquals(rightB.v, 1.0);
        
        ConstantDoubleTask leftC = (ConstantDoubleTask) differentialTaskC.left.getParent();
        ConstantDoubleTask rightC = (ConstantDoubleTask) differentialTaskC.right.getParent();
        assertEquals(leftC.v, 0.0);
        assertEquals(rightC.v, 0.0);
    }
    
    @Test
    public void testDoubleIntAddDifferentials() {
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        IntVariable intVarB = Variable.intVariable(20);
        IntVariable intVarC = Variable.intVariable(50);
        DoubleVariable doubleIntAdd = doubleVarA.add(intVarB);
        DoubleVariable differentialA = doubleIntAdd.getDifferential(doubleVarA, ctx);
        DoubleVariable differentialC = doubleIntAdd.getDifferential(intVarC, ctx);
        Add<?, ?, ?> taskA = (Add<?, ?, ?>) differentialA.getParent();
        Add<?, ?, ?> taskC = (Add<?, ?, ?>) differentialC.getParent();

        // Check result with double.
        ConstantDoubleTask doubleLeftA = (ConstantDoubleTask) taskA.left.getParent();
        ConstantDoubleTask doubleRightA = (ConstantDoubleTask) taskA.right.getParent();
        assertEquals(doubleLeftA.v, 1.0);
        assertEquals(doubleRightA.v, 0.0);

        // differentiating based on the integer should not be possible.
        assertFalse(doubleIntAdd.isDifferentiable(intVarB));
        
        // Differentiating with an independent variable should return 0.
        ConstantDoubleTask doubleLeftC = (ConstantDoubleTask) taskC.left.getParent();
        ConstantDoubleTask doubleRightC = (ConstantDoubleTask) taskC.right.getParent();
        assertEquals(doubleLeftC.v, 0.0);
        assertEquals(doubleRightC.v, 0.0);
    }
    
    @Test
    public void testDoubleSubtractDifferentials() {
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
        DoubleVariable doubleSubtract = doubleVarA.subtract(doubleVarB);
        DoubleVariable differentialA = (DoubleVariable) doubleSubtract.getDifferential(doubleVarA, ctx);
        DoubleVariable differentialB = (DoubleVariable) doubleSubtract.getDifferential(doubleVarB, ctx);
        DoubleVariable differentialC = (DoubleVariable) doubleSubtract.getDifferential(doubleVarC, ctx);
        Subtract<?, ?, ?> differentialTaskA = (Subtract<?, ?, ?>) differentialA.getParent();
        Subtract<?, ?, ?> differentialTaskB = (Subtract<?, ?, ?>) differentialB.getParent();
        Subtract<?, ?, ?> differentialTaskC = (Subtract<?, ?, ?>) differentialC.getParent();

        ConstantDoubleTask leftA = (ConstantDoubleTask) differentialTaskA.left.getParent();
        ConstantDoubleTask rightA = (ConstantDoubleTask) differentialTaskA.right.getParent();
        assertEquals(leftA.v, 1.0);
        assertEquals(rightA.v, 0.0);
        
        ConstantDoubleTask leftB = (ConstantDoubleTask) differentialTaskB.left.getParent();
        ConstantDoubleTask rightB = (ConstantDoubleTask) differentialTaskB.right.getParent();
        assertEquals(leftB.v, 0.0);
        assertEquals(rightB.v, 1.0);
        
        ConstantDoubleTask leftC = (ConstantDoubleTask) differentialTaskC.left.getParent();
        ConstantDoubleTask rightC = (ConstantDoubleTask) differentialTaskC.right.getParent();
        assertEquals(leftC.v, 0.0);
        assertEquals(rightC.v, 0.0);
    }
    
    @Test
    public void testDoubleIntSubtractDifferentials() {
        DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
        IntVariable intVarB = Variable.intVariable(20);
        IntVariable intVarC = Variable.intVariable(50);
        DoubleVariable doubleIntSubtract = doubleVarA.subtract(intVarB);
        DoubleVariable differentialA = doubleIntSubtract.getDifferential(doubleVarA, ctx);
        DoubleVariable differentialC = doubleIntSubtract.getDifferential(intVarC, ctx);
        Subtract<?, ?, ?> taskA = (Subtract<?, ?, ?>) differentialA.getParent();
        Subtract<?, ?, ?> taskC = (Subtract<?, ?, ?>) differentialC.getParent();

        // Check result with double.
        ConstantDoubleTask doubleLeftA = (ConstantDoubleTask) taskA.left.getParent();
        ConstantDoubleTask doubleRightA = (ConstantDoubleTask) taskA.right.getParent();
        assertEquals(doubleLeftA.v, 1.0);
        assertEquals(doubleRightA.v, 0.0);
 

        // differentiating based on the integer should not be possible.
        assertFalse(doubleIntSubtract.isDifferentiable(intVarB));
        
        // Differentiating with an independent variable should return 0.
        ConstantDoubleTask doubleLeftC = (ConstantDoubleTask) taskC.left.getParent();
        ConstantDoubleTask doubleRightC = (ConstantDoubleTask) taskC.right.getParent();
        assertEquals(doubleLeftC.v, 0.0);
        assertEquals(doubleRightC.v, 0.0);
           
    }
    
    @Test
    public void testDoubleMulDifferentials() {
    	DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
    	DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
    	DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
    	
    	DoubleVariable mul = doubleVarA.times(doubleVarB);
    	
    	CompilationOptions opts = new CompilationOptions();
    	DoubleVariable varAMulDifferential = mul.getDifferential(doubleVarA, ctx);
    	DoubleVariable varBMulDifferential = mul.getDifferential(doubleVarB, ctx);
    	DoubleVariable varCMulDifferential = mul.getDifferential(doubleVarC, ctx);
    	
        Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String varAMulDifferentialString = varAMulDifferential.getForwardIR(ctx).toString();
        String varBMulDifferentialString = varBMulDifferential.getForwardIR(ctx).toString();
        String varCMulDifferentialString = varCMulDifferential.getForwardIR(ctx).toString();

        // Differentiate for doubleVarA, should give doubleVarB
        assertEquals(varAMulDifferentialString, "((10.0 * 0.0) + (20.0 * 1.0))");
        // Differentiate for doubleVarB, should give doubleVarA
        assertEquals(varBMulDifferentialString, "((10.0 * 1.0) + (20.0 * 0.0))");
        // Differentiate for doubleVarC, should give 0.
        assertEquals(varCMulDifferentialString, "((10.0 * 0.0) + (20.0 * 0.0))");
    }
    
    @Test
    public void testDoubleDivideDifferentials() {
    	DoubleVariable doubleVarA = Variable.doubleVariable(10.0);
    	DoubleVariable doubleVarB = Variable.doubleVariable(20.0);
    	DoubleVariable doubleVarC = Variable.doubleVariable(50.0);
    	
    	DoubleVariable div = doubleVarA.divide(doubleVarB);
    	
    	
    	DoubleVariable varADivDifferential = div.getDifferential(doubleVarA, ctx);
    	DoubleVariable varBDivDifferential = div.getDifferential(doubleVarB, ctx);
    	DoubleVariable varCDivDifferential = div.getDifferential(doubleVarC, ctx);
        
        String varAMulDifferentialString = varADivDifferential.getForwardIR(ctx).toString();
        String varBMulDifferentialString = varBDivDifferential.getForwardIR(ctx).toString();
        String varCMulDifferentialString = varCDivDifferential.getForwardIR(ctx).toString();

        // Differentiate for doubleVarA, should give 1/doubleVarB.
        assertEquals(varAMulDifferentialString, "(((20.0 * 1.0) - (10.0 * 0.0)) / (20.0 * 20.0))");
        // Differentiate for doubleVarB, should give -doubleVarA/(doubleVarB^2)
        assertEquals(varBMulDifferentialString, "(((20.0 * 0.0) - (10.0 * 1.0)) / (20.0 * 20.0))");
        // Differentiate for doubleVarC, should give 0.
        assertEquals(varCMulDifferentialString, "(((20.0 * 0.0) - (10.0 * 0.0)) / (20.0 * 20.0))");
    }  

    @Test
    public void testComplexDifferentials() {
    	//f(x, y) = (x^4 - 5*x)/(14*y)
        DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
        DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
        
        // x^4
        DoubleVariable xPowFour = doubleVarA.times(doubleVarA.times(doubleVarA.times(doubleVarA)));

        // 5*x
        DoubleVariable fiveTimesX = Variable.doubleVariable(5.0).times(doubleVarA);
        
        // x^4 + 5*x
        DoubleVariable addNumerator = xPowFour.add(fiveTimesX);
        
        // 7*y
        DoubleVariable sevenTimesY = Variable.doubleVariable(7.0).times(doubleVarB);
        
        // numerator/(7y + 7y)
        DoubleVariable calc = addNumerator.divide(sevenTimesY.add(sevenTimesY));
        
        // Obtain first differentials.
        DoubleVariable firstDifferentialA = calc.getDifferential(doubleVarA, ctx);
        DoubleVariable firstDifferentialB = calc.getDifferential(doubleVarB, ctx);
        
        // Obtain second differentials.
        DoubleVariable secondDifferentialA = firstDifferentialA.getDifferential(doubleVarA, ctx);
        DoubleVariable secondDifferentialB = firstDifferentialB.getDifferential(doubleVarB, ctx);

        CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(outputForCtx.toArray(new Variable<?>[0]));
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String firstDifferentialStringA = firstDifferentialA.getForwardIR(ctx).toString();
        String secondDifferentialStringA = secondDifferentialA.getForwardIR(ctx).toString();
        
        String fileNameFirstDiffA = tsg.processString(this, sourceDir, firstDifferentialATag, 
        		firstDifferentialStringA, constructingResults, "txt");
        String fileNameSecondDiffA = tsg.processString(this, sourceDir, secondDifferentialATag, 
        		secondDifferentialStringA, constructingResults, "txt");
        
        String firstDifferentialStringB = firstDifferentialB.getForwardIR(ctx).toString();
        String secondDifferentialStringB = secondDifferentialB.getForwardIR(ctx).toString();
        
        String fileNameFirstDiffB = tsg.processString(this, sourceDir, firstDifferentialBTag, 
        		firstDifferentialStringB, constructingResults, "txt");
        String fileNameSecondDiffB = tsg.processString(this, sourceDir, secondDifferentialBTag, 
        		secondDifferentialStringB, constructingResults, "txt");
        
        if (constructingResults) {
        	System.err.println("Tests have been updated to files.");
        	assertFalse(true);
        }
        
        // Use this variable to print the proper file in case of an exception
        String fileToOpen = fileNameFirstDiffA;
        try {
        	String firstBaseStringA = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameSecondDiffA;
        	String secondBaseStringA = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameFirstDiffB;
        	String firstBaseStringB = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameSecondDiffB;
        	String secondBaseStringB = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	firstBaseStringA = StringUtil.normalizeNewLines(firstBaseStringA);
        	secondBaseStringA = StringUtil.normalizeNewLines(secondBaseStringA);
        	
        	firstBaseStringB = StringUtil.normalizeNewLines(firstBaseStringB);
        	secondBaseStringB = StringUtil.normalizeNewLines(secondBaseStringB);
        	
        	assertEquals(firstBaseStringA, firstDifferentialStringA);
        	assertEquals(secondBaseStringA, secondDifferentialStringA);
        	
        	assertEquals(firstBaseStringB, firstDifferentialStringB);
        	assertEquals(secondBaseStringB, secondDifferentialStringB);
        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
    }

    @Test
    public void testExpDifferentials() {
    	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
    	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
    	DoubleVariable exponent = Exp.exp(doubleVarA);
    	DoubleVariable exponentDifferentialA = exponent.getDifferential(doubleVarA, ctx);
    	DoubleVariable exponentDifferentialB = exponent.getDifferential(doubleVarB, ctx);
    	
    	CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(outputForCtx.toArray(new Variable<?>[0]));
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String exponentDifferentialStringA = exponentDifferentialA.getForwardIR(ctx).toString();
        String exponentDifferentialStringB = exponentDifferentialB.getForwardIR(ctx).toString();

        assertEquals(exponentDifferentialStringA, "(Math.exp(20.0) * 1.0)");
        assertEquals(exponentDifferentialStringB, "(Math.exp(20.0) * 0.0)");
    }
    
    @Test
    public void testExpFunctionDifferentials() {
    	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
    	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
    	DoubleVariable powThirdVarA = doubleVarA.times(doubleVarA.times(doubleVarA));
    	DoubleVariable exponent = Exp.exp(powThirdVarA);
    	DoubleVariable exponentDifferentialA = exponent.getDifferential(doubleVarA, ctx);
    	DoubleVariable exponentDifferentialB = exponent.getDifferential(doubleVarB, ctx);

        String exponentDifferentialStringA = exponentDifferentialA.getForwardIR(ctx).toString();
        String exponentDifferentialStringB = exponentDifferentialB.getForwardIR(ctx).toString();
        
        // e^(x^3)*3(x^2)
        assertEquals(exponentDifferentialStringA, 
        		"(Math.exp((20.0 * (20.0 * 20.0))) * ((20.0 * ((20.0 * 1.0) + "
        		+ "(20.0 * 1.0))) + ((20.0 * 20.0) * 1.0)))");
        
        assertEquals(exponentDifferentialStringB, "(Math.exp((20.0 * (20.0 * 20.0))) * "
        		+ "((20.0 * ((20.0 * 0.0) + (20.0 * 0.0))) + ((20.0 * 20.0) * 0.0)))");
    }
    
    @Test
    public void testDoubleToIntDifferentials() {
    	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
    	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
    	IntVariable doubleToInt = doubleVarA.castToInteger();
    	DoubleVariable differentialB = doubleToInt.getDifferential(doubleVarB, ctx);
    	ConstantDoubleTask doubleTaskB = (ConstantDoubleTask) differentialB.getParent();
        assertEquals(doubleTaskB.v, 0.0);
        
        // Should not be differentiable for dependent variable.
        assertFalse(doubleToInt.isDifferentiable(doubleVarA));
    }
    
    @Test
    public void testIntToDoubleDifferentials() {
    	IntVariable intVarA = Variable.intVariable(20);
    	IntVariable intVarB = Variable.intVariable(50);
    	DoubleVariable intToDouble = intVarA.castToDouble();
    	DoubleVariable differentialB = intToDouble.getDifferential(intVarB, ctx);
    	ConstantDoubleTask doubleTaskB = (ConstantDoubleTask) differentialB.getParent();
    	
        assertEquals(doubleTaskB.v, 0.0);
        // Should not be differentiable for dependent integer variable.
        assertFalse(intToDouble.isDifferentiable(intVarA));
    }
    
    @Test
    public void testRemainderDifferentials() {
	  	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
	  	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
	  	DoubleVariable doubleVarC = Variable.doubleVariable(70.0);
	  	
	  	DoubleVariable remainderA = doubleVarA.remainder(doubleVarB);
	  	assertFalse(remainderA.isDifferentiable(doubleVarA));
	  	assertFalse(remainderA.isDifferentiable(doubleVarB));
	  	
	  	ConstantDoubleTask task = (ConstantDoubleTask) remainderA.getDifferential(doubleVarC, ctx).getParent();
	  	assertEquals(task.v, 0.0);
    }
  
    @Test
    public void testMaxDifferentials() {
	  	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
	  	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
	  	DoubleVariable doubleVarC = Variable.doubleVariable(70.0);
	  	
	  	DoubleVariable maxA = Max.max(doubleVarA, doubleVarB);
	  	assertFalse(maxA.isDifferentiable(doubleVarA));
	  	assertFalse(maxA.isDifferentiable(doubleVarB));
	  	
	  	ConstantDoubleTask task = (ConstantDoubleTask) maxA.getDifferential(doubleVarC, ctx).getParent();
	  	assertEquals(task.v, 0.0);
    }

    @Test
    public void testLogDoubleDifferential() {
	  	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
	  	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
	  	
	  	DoubleVariable logA = Log.log(doubleVarA);
	  	
	  	DoubleVariable logADifferential = logA.getDifferential(doubleVarA, ctx);
	  	DoubleVariable logBDifferential = logA.getDifferential(doubleVarB, ctx);
	  	
	  	ConstantDoubleTask doubleTaskB = (ConstantDoubleTask) logBDifferential.getParent();
	  	assertEquals(doubleTaskB.v, 0.0);
	  	
	  	CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);

        String logADifferentialString = logADifferential.getForwardIR(ctx).toString();
        assertEquals(logADifferentialString, "(1.0 / 20.0)");
    }
    
    @Test
    public void testLogDoubleFunctionDifferential() {
    	// y(x) = log(f(x)), where f(x) = x^3.
	  	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
	  	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
	  	DoubleVariable powThirdDoubleVarA = doubleVarA.times(doubleVarA.times(doubleVarA));
	  	
	  	DoubleVariable logA = Log.log(powThirdDoubleVarA);
	  	
	  	DoubleVariable logADifferential = logA.getDifferential(doubleVarA, ctx);
	  	DoubleVariable logBDifferential = logA.getDifferential(doubleVarB, ctx);
	  	
	  	ConstantDoubleTask doubleTaskB = (ConstantDoubleTask) logBDifferential.getParent();
	  	assertEquals(doubleTaskB.v, 0.0);
	  	
	  	CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String logADifferentialString = logADifferential.getForwardIR(ctx).toString();
        // y'(x) = (log(f(x)))' = f'(x)/f(x) = 3/x.
        assertEquals(logADifferentialString, "(((20.0 * ((20.0 * 1.0) + (20.0 * 1.0))) + "
        		+ "((20.0 * 20.0) * 1.0)) / (20.0 * (20.0 * 20.0)))");
    }
  
    @Test
    public void testLogIntDifferential() {
	  	IntVariable intVarA = Variable.intVariable(20);
	  	IntVariable intVarB = Variable.intVariable(50);
	  	DoubleVariable logA = Log.log(intVarA);
	  	
	  	DoubleVariable logADifferential = logA.getDifferential(intVarA, ctx);
	  	DoubleVariable logBDifferential = logA.getDifferential(intVarB, ctx);
	  	
	  	ConstantDoubleTask doubleTaskB = (ConstantDoubleTask) logBDifferential.getParent();
	  	
	  	assertEquals(doubleTaskB.v, 0.0);
	  	
	  	CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String logADifferentialString = logADifferential.getForwardIR(ctx).toString();

        assertEquals(logADifferentialString, "(1.0 / 20)");
    }
      
  	@Test
	public void testSigmoidDifferentials() {
	  	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
	  	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
	  	
	  	DoubleVariable sigmoidA = Sigmoid.sigmoid(doubleVarA);
	  	
	  	DoubleVariable sigmoidADifferential = sigmoidA.getDifferential(doubleVarA, ctx);
	  	DoubleVariable sigmoidBDifferential = sigmoidA.getDifferential(doubleVarB, ctx);

	  	CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String sigmoidADifferentialString = sigmoidADifferential.getForwardIR(ctx).toString();
        String sigmoidBDifferentialString = sigmoidBDifferential.getForwardIR(ctx).toString();
        
        // Expanded form of (f'(x)*sigmoid(f(x))*(1 - sigmoid(f(x)))).
        assertEquals(sigmoidADifferentialString,
        			 "((1.0 * (1.0 / (1 + Math.exp((-20.0))))) * (1.0 - (1.0 / (1 + Math.exp((-20.0))))))");
        // Evaluates to 0.
        assertEquals(sigmoidBDifferentialString,
        		"((0.0 * (1.0 / (1 + Math.exp((-20.0))))) * (1.0 - (1.0 / (1 + Math.exp((-20.0))))))");
	}
  	
  	@Test
	public void testSigmoidFunctionDifferentials() {
	  	DoubleVariable doubleVarA = Variable.doubleVariable(20.0);
	  	DoubleVariable doubleVarB = Variable.doubleVariable(50.0);
	  	
	  	DoubleVariable powThreeDoubleVarA = doubleVarA.times(doubleVarA).times(doubleVarA);
	  	
	  	DoubleVariable sigmoidA = Sigmoid.sigmoid(powThreeDoubleVarA);
	  	
	  	DoubleVariable sigmoidADifferential = sigmoidA.getDifferential(doubleVarA, ctx);
	  	DoubleVariable sigmoidBDifferential = sigmoidA.getDifferential(doubleVarB, ctx);
	  	
	  	CompilationOptions opts = new CompilationOptions();
        Traces traces = TracesImplementation.getTraces(new Variable<?>[0]);
        CompilationContext ctx = new CompilationContext(opts, traces, ExecutionType.SingleThreadCPU);
        
        String sigmoidADifferentialString = sigmoidADifferential.getForwardIR(ctx).toString();
        String sigmoidBDifferentialString = sigmoidBDifferential.getForwardIR(ctx).toString();
        
        assertEquals(sigmoidADifferentialString, 
        			 "(((((20.0 * 20.0) * 1.0) + (20.0 * ((20.0 * 1.0) + (20.0 * 1.0)))) * "
        			 + "(1.0 / (1 + Math.exp((-((20.0 * 20.0) * 20.0)))))) * (1.0 - (1.0 / "
        			 + "(1 + Math.exp((-((20.0 * 20.0) * 20.0)))))))");
        
        // Evaluates to 0.
        assertEquals(sigmoidBDifferentialString, 
   			 "(((((20.0 * 20.0) * 0.0) + (20.0 * ((20.0 * 0.0) + (20.0 * 0.0)))) * "
   			 + "(1.0 / (1 + Math.exp((-((20.0 * 20.0) * 20.0)))))) * (1.0 - (1.0 / "
   			 + "(1 + Math.exp((-((20.0 * 20.0) * 20.0)))))))");
	}
  	
	@Test
	public void testConditionalAssignmentAdd() {
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
		
		// Test case: Guard contains variable.
		assertFalse(ifElseTask.isDifferentiable(intVarA));
		assertFalse(ifElseTask.isDifferentiable(intVarB));
		
		// Test case: Guard does not contain variable, and both conditions are differentiable.
		ctx.initialize();
		DoubleVariable differentialA = ifElseTask.getDifferential(doubleVarA, ctx);
    	differentialA.setAlias("diffA");
    	differentialA.getForwardIR(ctx);
    	String differentialAIRString = ctx.getOutermostScopeTree().toString();
        String fileNameIfElseA = tsg.processString(this, sourceDir, ifElseDifferentialATag, 
        		differentialAIRString, constructingResults, "txt");
        
        ctx.initialize();
    	DoubleVariable differentialB = ifElseTask.getDifferential(doubleVarB, ctx);
    	differentialB.setAlias("diffB");
    	differentialB.getForwardIR(ctx);
    	String differentialBIRString = ctx.getOutermostScopeTree().toString();
        String fileNameIfElseB = tsg.processString(this, sourceDir, ifElseDifferentialBTag, 
        		differentialBIRString, constructingResults, "txt");
        
        ctx.initialize();
    	DoubleVariable differentialE = ifElseTask.getDifferential(doubleVarE, ctx);
    	differentialE.setAlias("diffE");
    	differentialE.getForwardIR(ctx);
    	String differentialCIRString = ctx.getOutermostScopeTree().toString();
    	String fileNameIfElseE = tsg.processString(this, sourceDir, ifElseDifferentialETag, 
    			differentialCIRString, constructingResults, "txt");
    	
        String fileToOpen = fileNameIfElseA;
        try {
        	String ifElseDifferentialAString = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameIfElseB;
        	String ifElseDifferentialBString = new String(Files.readAllBytes(Paths.get(fileToOpen)));
        	fileToOpen = fileNameIfElseE;
        	String ifElseDifferentialEString = new String(Files.readAllBytes(Paths.get(fileToOpen)));
            
        	// Normalize newline characters This second use is required because GitHub
            // rewrites the data files.
        	ifElseDifferentialAString = StringUtil.normalizeNewLines(ifElseDifferentialAString);
        	ifElseDifferentialBString = StringUtil.normalizeNewLines(ifElseDifferentialBString);
        	ifElseDifferentialEString = StringUtil.normalizeNewLines(ifElseDifferentialEString);
        	
        	// doubleVarA is contained in both expressions.
        	assertEquals(differentialAIRString, ifElseDifferentialAString);
        	
        	// doubleVarB is not contained in any expressions.
        	assertEquals(differentialBIRString, ifElseDifferentialBString);
        	
        	// doubleVarE is contained in second expression only.
        	assertEquals(differentialCIRString, ifElseDifferentialEString);
        	
        } catch(IOException e) {
            System.err.println("Failed to read file \"" + fileToOpen + "\"");
            assertFalse(true);
        }
	}
	
	@Test
	public void testForTask() {
		IntVariable intVar1 = IntVariable.intVariable(10);
		IntVariable intVar2 = IntVariable.intVariable(20);
		IntVariable intVar3 = IntVariable.intVariable(30);
		IntVariable intVar4 = IntVariable.intVariable(40);
		IntVariable calc = intVar1.add(intVar2.subtract(intVar3.times(intVar4.divide(10))));
		
		IntVariable intVar5 = IntVariable.intVariable(50);
		ForTask task = new ForTask(intVar1, intVar2, calc, true);
		
		// For loop task contains variable.
		assertFalse(task.isDifferentiable(intVar1));
		assertFalse(task.isDifferentiable(intVar2));
		assertFalse(task.isDifferentiable(intVar3));
		assertFalse(task.isDifferentiable(intVar4));
		
		// Variable not contained.
		DoubleVariable taskDifferentialVar5 = task.getDifferential(intVar5, ctx);
		ConstantDoubleTask doubleVar5DiffTask = (ConstantDoubleTask) taskDifferentialVar5.getParent();
		assertEquals(doubleVar5DiffTask.v, 0.0);
	}
}
