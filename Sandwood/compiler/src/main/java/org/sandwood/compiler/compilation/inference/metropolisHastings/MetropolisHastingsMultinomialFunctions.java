/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference.metropolisHastings;

import static org.sandwood.compiler.trees.irTree.IRTree.addID;
import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.castToInteger;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.getIntField;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
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
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Multinomial;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class to perform Metropolis-Harstings on Multinomial samples to generate a new sample value. To prevent
 * probabilities going to zero the probabilities are generated and stored in log space.
 */
public class MetropolisHastingsMultinomialFunctions extends MetropolisHastingsArrayFunctions<IntVariable, Multinomial> {
    private static final VariableDescription<IntVariable> nonZeroCount = VariableNames.calcVarName("nonZeroCount",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> source = VariableNames.calcVarName("sourceIndex",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> destination = VariableNames.calcVarName("destinationIndex",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> change = VariableNames.calcVarName("changeValue",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> loopIndex = VariableNames.calcVarName("loopIndex",
            VariableType.IntVariable, true);
    private static final VariableDescription<IntVariable> arrayLength = VariableNames.calcVarName("arrayLength",
            VariableType.IntVariable, true);

    /**
     * Test if the trace can be accepted.
     */
    @Override
    public boolean canAcceptTraces(SampleTask<ArrayVariable<IntVariable>, Multinomial> sample, List<String> suggestions,
            CompilationContext compilationCtx) {
        return true;
    }

    /**
     * Allocate space to store the current value and the proposed sample value.
     * <p>
     * TODO Make the parameters of the normal distribution generating the proposals user configurable.
     */

    @Override
    protected void constructFunctionVariablesProb(CompilationContext compilationCtx,
            MetropolisHastingsArrayData<IntVariable, Multinomial> funcData) {
        super.constructFunctionVariablesProb(compilationCtx, funcData);

        IRTreeReturn<ArrayVariable<IntVariable>> target = funcData.getTarget();

        // Array Length
        IRTreeReturn<IntVariable> arrayLengthTree = getIntField(funcData.getTarget(), "length");
        IRTreeVoid arrayLengthTreeInit = initializeVariable(arrayLength, arrayLengthTree, Tree.NoComment);
        compilationCtx.addTreeToScope(GlobalScope.scope, arrayLengthTreeInit);

        // Count the number of non zero values
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(nonZeroCount, constant(0), "Count how many non zero entries there are."));
        IRTreeReturn<BooleanVariable> guard = negateBoolean(eq(arrayGet(target, load(loopIndex)), constant(0)));
        IRTreeVoid increment = store(nonZeroCount, addII(load(nonZeroCount), constant(1)), Tree.NoComment);
        IRTreeVoid count = IRTree.ifElse(guard, increment, Tree.NoComment);
        compilationCtx.addTreeToScope(GlobalScope.scope,
                forStmt(count, constant(0), arrayLengthTree, constant(1), loopIndex, true, Tree.NoComment));

        // Index to Change
        IRTreeReturn<IntVariable> indexToChangeTree = castToInteger(functionCallReturn(FunctionType.SAMPLE,
                VariableType.DoubleVariable, VariableType.Uniform, constant(0.0), load(nonZeroCount)));
        IRTreeVoid indexToChangeTreeInit = initializeVariable(source, indexToChangeTree,
                "Pick a value in the array to adjust.");
        compilationCtx.addTreeToScope(GlobalScope.scope, indexToChangeTreeInit);
        guard = eq(arrayGet(target, load(loopIndex)), constant(0));
        increment = store(source, addII(load(source), constant(1)), Tree.NoComment);
        count = IRTree.ifElse(guard, increment, Tree.NoComment);
        compilationCtx.addTreeToScope(GlobalScope.scope, forStmt(count, constant(0), addII(load(source), constant(1)),
                constant(1), loopIndex, true, Tree.NoComment));

        // Fraction to move selected index by.
        IRTreeReturn<IntVariable> changeValue = castToInteger(
                functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, VariableType.Uniform,
                        constant(1.0), addID(arrayGet(target, load(source)), constant(1.0))));
        compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(change, changeValue,
                "Select the number of trials to remove from the selected category."));

        // Calculate the destination of the change
        IRTreeReturn<IntVariable> destinationToChange = castToInteger(
                functionCallReturn(FunctionType.SAMPLE, VariableType.DoubleVariable, VariableType.Uniform,
                        constant(0.0), subtractII(load(arrayLength), constant(1))));
        compilationCtx.addTreeToScope(GlobalScope.scope,
                initializeVariable(destination, destinationToChange, "Select the destination of the moved trials."));
        compilationCtx.addTreeToScope(GlobalScope.scope,
                ifElse(IRTree.lessThanEqual(load(source), load(destination)),
                        store(destination, addII(load(destination), constant(1)), Tree.NoComment),
                        "Ensure the source and target are not equal"));
    }

    @Override
    protected IRTreeVoid updateSampleValue(MetropolisHastingsArrayData<IntVariable, Multinomial> funcData,
            boolean resetting) {
        List<IRTreeVoid> stmts = new ArrayList<>();
        IRTreeReturn<ArrayVariable<IntVariable>> target = funcData.getTarget();
        if(resetting) {
            stmts.add(arrayPut(target, load(source), addII(arrayGet(target, load(source)), load(change)),
                    Tree.NoComment));
            stmts.add(arrayPut(target, load(destination), subtractII(arrayGet(target, load(destination)), load(change)),
                    Tree.NoComment));
        } else {
            stmts.add(arrayPut(target, load(source), subtractII(arrayGet(target, load(source)), load(change)),
                    Tree.NoComment));
            stmts.add(arrayPut(target, load(destination), addII(arrayGet(target, load(destination)), load(change)),
                    Tree.NoComment));
        }
        return sequential(stmts, "Update the sample values");
    }
}
