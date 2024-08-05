/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.metropolisHastings;

import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.castToInteger;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.divideDI;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.getIntField;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeUnsetVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThan;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDI;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDD;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractDI;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractII;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class to perform Metropolis-Harstings on Dirichlet samples to generate a new sample value. To prevent probabilities
 * going to zero the probabilities are generated and stored in log space.
 */
public class MetropolisHastingsDirichletFunctions extends MetropolisHastingsArrayFunctions<DoubleVariable, Dirichlet> {
    private static final VariableDescription<DoubleVariable> proposedDifference = VariableNames
            .calcVarName("proposedDifference", VariableType.DoubleVariable, true);
    private static final VariableDescription<DoubleVariable> rebalanceValue = VariableNames
            .calcVarName("rebalanceValue", VariableType.DoubleVariable, true);
    private static final VariableDescription<IntVariable> indexToChange = VariableNames.calcVarName("indexToChange",
            VariableType.IntVariable, true);
    private static final VariableDescription<DoubleVariable> movementRatio = VariableNames.calcVarName("movementRatio",
            VariableType.DoubleVariable, true);
    private static final VariableDescription<IntVariable> loopIndex = VariableNames.calcVarName("loopIndex",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> arrayLength = VariableNames.calcVarName("arrayLength",
            VariableType.IntVariable, true);
    private static final VariableDescription<DoubleVariable> tempValue = VariableNames.calcVarName("temp",
            VariableType.DoubleVariable, true);

    /**
     * Test if the trace can be accepted.
     */
    @Override
    public boolean canAcceptTraces(SampleTask<ArrayVariable<DoubleVariable>, Dirichlet> sample,
            List<String> suggestions, CompilationContext compilationCtx) {
        return true;
    }

    /**
     * Allocate space to store the current value and the proposed sample value.
     * <p>
     * TODO Make the parameters of the normal distribution generating the proposals user configurable.
     */

    @Override
    protected void constructFunctionVariablesProb(CompilationContext compilationCtx,
            MetropolisHastingsArrayData<DoubleVariable, Dirichlet> funcData) {
        super.constructFunctionVariablesProb(compilationCtx, funcData);

        // Array Length
        IRTreeReturn<IntVariable> arrayLengthTree = getIntField(funcData.getTarget(), "length");
        IRTreeVoid arrayLengthTreeInit = initializeVariable(arrayLength, arrayLengthTree, Tree.NoComment);
        compilationCtx.addTreeToScope(GlobalScope.scope, arrayLengthTreeInit);

        // Index to Change
        // TODO confirm this cannot return arrayLength.
        IRTreeReturn<IntVariable> indexToChangeTree = castToInteger(functionCallReturn(FunctionType.SAMPLE,
                VariableType.DoubleVariable, VariableType.Uniform, constant(0.0), load(arrayLength)));
        IRTreeVoid indexToChangeTreeInit = initializeVariable(indexToChange, indexToChangeTree,
                "Pick a value in the array to adjust.");
        compilationCtx.addTreeToScope(GlobalScope.scope, indexToChangeTreeInit);

        // Fraction to move selected index by.
        IRTreeReturn<DoubleVariable> movementRatioTree = subtractDI(multiplyDD(functionCallReturn(FunctionType.SAMPLE,
                VariableType.DoubleVariable, VariableType.Beta, constant(5), constant(5)), constant(1.9999)),
                constant(1));
        IRTreeVoid movementRatioTreeInit = initializeVariable(movementRatio, movementRatioTree,
                "Pick how much the value "
                        + "should be moved by. Initially this value is proposed as a ratio of the current magnitude of the value, we will check to make sure "
                        + "the adjustment will not make this value too large or other values too small and adjust if required before it is applied.");
        compilationCtx.addTreeToScope(GlobalScope.scope, movementRatioTreeInit);

        // Calculate the proposed difference
        IRTreeVoid proposedDifferenceTreeInit = initializeUnsetVariable(proposedDifference,
                "Allocate space for the proposed change to be stored as an absolute value");

        IRTreeVoid max = sequential("Calculate the maximum magnitude of the proposed index change.", store(
                proposedDifference, subtractDD(constant(1.0), arrayGet(funcData.getTarget(), load(indexToChange))),
                "Initially set the maximum to the amount that the value we are changing could increase without exceeding 1"),

                forStmt(sequential(Tree.NoComment,
                        // Calculate the new possible lower bound on movement.
                        initializeVariable(tempValue,
                                multiplyDI(arrayGet(funcData.getTarget(), load(loopIndex)),
                                        subtractII(load(arrayLength), constant(1))),
                                "Calculate the maximum change value that the value at array index " + loopIndex
                                        + " could support. Based " + "on moving all other values by an equal amount."),
                        // Test if it is a lower bound
                        ifElse(lessThan(load(tempValue), load(proposedDifference)),
                                // And update the value if it is lower
                                store(proposedDifference, load(tempValue), Tree.NoComment),
                                "If the maximum move is less than the proposed move update the move size.")),
                        constant(0), load(indexToChange), constant(1), loopIndex, true,
                        "For the array values up to the index we are going to change calculate the maximum move possible."),

                forStmt(sequential(Tree.NoComment,
                        // Calculate the new possible lower bound on movement.
                        initializeVariable(tempValue,
                                multiplyDI(arrayGet(funcData.getTarget(), load(loopIndex)),
                                        subtractII(load(arrayLength), constant(1))),
                                "Calculate the maximum change value that the value at array index " + loopIndex
                                        + " could support. Based " + "on moving all other values by an equal amount."),
                        // Test if it is a lower bound
                        ifElse(lessThan(load(tempValue), load(proposedDifference)),
                                // And update the value if it is lower
                                store(proposedDifference, load(tempValue), Tree.NoComment),
                                "If this is less than the proposed increase, change the proposed increase to this value.")),
                        addII(load(indexToChange), constant(1)), load(arrayLength), constant(1), loopIndex, true,
                        "For the array values after the index we are going to change calculate the maximum move possible."));

        IRTreeVoid min = store(proposedDifference, arrayGet(funcData.getTarget(), load(indexToChange)),
                "The maximum reduction of the array at the index without going below 0 is the value of the array at that index.");

        proposedDifferenceTreeInit = sequential(
                "Calculate how much we are going to move the array index " + indexToChange + " the by.",
                proposedDifferenceTreeInit,
                ifElse(lessThan(load(movementRatio), constant(0)), min,
                        "Test if we are increasing or decreasing the value at the index. For each case calculate the maximum valid adjustment.",
                        max, Tree.NoComment),
                store(proposedDifference, multiplyDD(load(movementRatio), load(proposedDifference)),
                        "Multiply the maximum adjustment by the adjustment ratio to get the actual adjustment we are going to make."));
        compilationCtx.addTreeToScope(GlobalScope.scope, proposedDifferenceTreeInit);

        // Finally set the amount that each of the other values should be moved by to
        // rebalance the array.
        IRTreeReturn<DoubleVariable> rebalanceTree = divideDI(load(proposedDifference),
                subtractII(load(arrayLength), constant(1)));
        IRTreeVoid rebalanceTreeInit = initializeVariable(rebalanceValue, rebalanceTree,
                "Calculate how much each of the other indexes needs to be adjusted by in order to maintain that the sum of the indexes is 1.");
        compilationCtx.addTreeToScope(GlobalScope.scope, rebalanceTreeInit);

    }

    @Override
    protected IRTreeVoid updateSampleValue(MetropolisHastingsArrayData<DoubleVariable, Dirichlet> funcData,
            boolean resetting) {
        IRTreeReturn<ArrayVariable<DoubleVariable>> target = funcData.getTarget();
        List<IRTreeVoid> stmts = new ArrayList<>();

        IRTreeReturn<DoubleVariable> v = arrayGet(target, load(loopIndex));
        if(resetting)
            v = addDD(v, load(rebalanceValue));
        else
            v = subtractDD(v, load(rebalanceValue));

        IRTreeVoid rebalance = arrayPut(target, load(loopIndex), v, Tree.NoComment);

        // Rebalance values before the index we are adjusting
        stmts.add(forStmt(rebalance, constant(0), load(indexToChange), constant(1), loopIndex, true,
                "Update all the indexes up to the index selected."));

        // Adjust index
        v = arrayGet(target, load(indexToChange));

        if(resetting)
            v = subtractDD(v, load(proposedDifference));
        else
            v = addDD(v, load(proposedDifference));

        stmts.add(arrayPut(target, load(indexToChange), v, "Update the selected index."));

        // Rebalance values after the index we are adjusting
        stmts.add(forStmt(rebalance, addII(load(indexToChange), constant(1)), load(arrayLength), constant(1), loopIndex,
                true, "Update all the indexes after the index we selected."));

        return sequential(stmts, "Update the sample value");
    }
}
