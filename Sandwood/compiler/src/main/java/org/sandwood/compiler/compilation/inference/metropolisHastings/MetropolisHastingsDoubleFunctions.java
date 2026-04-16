/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.metropolisHastings;

import static org.sandwood.compiler.trees.irTree.IRTree.conditionalAssignment;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThan;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.negate;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.LocalVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class to perform Metropolis-Hastings to generate a sample value. To prevent probabilities going to zero the
 * probabilities are generated and stored in log space. New proposed values are constructed by adding an offset to the
 * current value.
 * 
 * When constructing the offset if the current value is 0 the offset is drawn from a Gaussian with mean 0 and variance
 * of 3. If the offset is not 0 the sample is drawn from a Gaussian with mean 0 and variance 0.001 times the current
 * sample value. The special case for 0 is required to stop the offset being deterministically 0.
 * 
 * The parameters for the gaussian for non-zero values are picked to give average offsets that are %5 of the size of the
 * current value.
 */

public class MetropolisHastingsDoubleFunctions<B extends RandomVariable<DoubleVariable, B>>
        extends MetropolisHastingsScalarFunctions<DoubleVariable, B> {

    @Override
    protected void getProposedValue(MetropolisHastingsData<DoubleVariable, B> funcData) {
        LocalVariableDescription<DoubleVariable> varName = VariableNames.localCalcVarName("var", VariableType.DoubleVariable,
                true);

        // Calculate the variance based on the current value.
        double fractionOfCurrentValue = 40;
        IRTreeReturn<DoubleVariable> proposedVar = multiplyDD(
                conditionalAssignment(lessThan(load(funcData.originalValueName), constant(0)),
                        negate(load(funcData.originalValueName)), load(funcData.originalValueName)),
                constant(fractionOfCurrentValue));
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(varName, proposedVar, "Calculate a proposed variance."));

        // Ensure it is not too small
        double minVariance = 0.01;
        IRTreeReturn<BooleanVariable> guard = lessThan(load(varName), constant(minVariance));
        IRTreeVoid ifStmt = store(varName, constant(minVariance), Tree.NoComment);
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope,
                IRTree.ifElse(guard, ifStmt, "Ensure the variance is at least " + minVariance));

        // Sample a Gaussian distribution based on it to generate the new proposed value.
        funcData.compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(funcData.proposedValueName,
                        functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, VariableType.Gaussian,
                                load(funcData.originalValueName), load(varName)),
                        "The proposed new value for the sample"));
    }

    /**
     * Test if the trace can be accepted.
     */
    @Override
    public boolean canAcceptTraces(SampleTask<DoubleVariable, B> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        return true;
    }
}
