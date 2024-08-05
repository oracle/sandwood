/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.getIntField;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.max;
import static org.sandwood.compiler.trees.irTree.IRTree.min;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class GetArrayTask<A extends Variable<A>> extends GetTask<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {

    public GetArrayTask(ArrayVariable<ArrayVariable<A>> array, IntVariable index, Location location) {
        super(array, index, location);
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return array.getPossibleChildLengths();
    }

    private IRTreeReturn<IntVariable> getMinMaxLength(boolean findMax, CompilationContext compilationCtx) {
        Set<PutTask<ArrayVariable<A>>> puts = array.getPuts(scope(), id());

        // This is a sequence of gets that ends in the array being constructed in a
        // fully populated form. Currently, this can only be an input, but if we had
        // multidimensional arrays being sampled it could be one of those.
        if(puts.isEmpty()) {
            // TODO convert this into a tail recursion
            GetTask gt = this;
            // Get a list of all the gets.
            Stack<GetTask<?>> gets = new Stack<>();
            gets.push(gt);
            ProducingDataflowTask arrayParent = gt.array.getParent();
            while(arrayParent.getType() == DFType.GET) {
                GetTask<?> producingGt = (GetTask<?>) arrayParent;
                gets.push(producingGt);
                arrayParent = producingGt.array.getParent();
            }
            VariableDescription<IntVariable> returnLength = VariableNames.calcVarName(gt.array,
                    findMax ? "max" : "min" + "Length", VariableType.IntVariable);

            List<IRTreeVoid> stmts = new ArrayList<>();
            stmts.add(initializeVariable(returnLength, IRTree.constant(0),
                    "initialize ready to iterate over all possible arrays to find the " + (findMax ? "max" : "min")
                            + "imum length"));
            stmts.add(getLength(gets, returnLength, findMax));

            compilationCtx.addTreeToScope(GlobalScope.scope, sequential(stmts,
                    "calculate the " + (findMax ? "max" : "min") + "imum lengths of array " + getName()));
            return load(returnLength);

        } else {
            VariableDescription<IntVariable> returnName = VariableNames.calcVarName(array, findMax ? "max" : "min",
                    VariableType.IntVariable);

            PriorityQueue<PutTask<ArrayVariable<A>>> p = new PriorityQueue<>(puts);
            PutTask<ArrayVariable<A>> pt = p.poll();
            ArrayVariable<A> value = ((ArrayVariable<A>) pt.value);
            IRTreeReturn<IntVariable> rValue = findMax ? value.getMaxLength(compilationCtx)
                    : value.getMinLength(compilationCtx);
            compilationCtx.addTreeToScope(GlobalScope.scope,
                    initializeVariable(returnName, rValue,
                            "Variable to record the " + (findMax ? "max" : "min") + "imum value of " + getName()
                                    + ". Initially set to the value of putTask " + pt.id() + "."));

            while(!p.isEmpty()) {
                pt = p.poll();
                value = ((ArrayVariable<A>) pt.value);
                rValue = findMax ? value.getMaxLength(compilationCtx) : value.getMinLength(compilationCtx);
                compilationCtx.addTreeToScope(GlobalScope.scope,
                        store(returnName, findMax ? max(load(returnName), rValue) : min(load(returnName), rValue),
                                "Test if the input to putTask " + pt.id() + " is " + (findMax ? "larger" : "smaller")
                                        + " than the current values."));
            }
            return load(returnName);
        }
    }

    private IRTreeVoid getLength(Stack<GetTask<?>> gets, VariableDescription<IntVariable> returnLength,
            boolean findMax) {
        List<IRTreeVoid> stmts = new ArrayList<>();
        GetTask<A> gt = (GetTask<A>) gets.pop();
        ArrayVariable<A> v = gt.array;
        VariableDescription<ArrayVariable<A>> parentArrayDesc = v.getUniqueVarDesc();

        VariableDescription<IntVariable> length = VariableNames.calcVarName(v, "length", VariableType.IntVariable);
        stmts.add(initializeVariable(length, getIntField(load(parentArrayDesc), "length"), Tree.NoComment));

        VariableDescription<IntVariable> index = VariableNames.indexName(parentArrayDesc);

        List<IRTreeVoid> bodyStmts = new ArrayList<>();
        VariableDescription<A> outputName = gt.getOutput().getVarDesc();
        bodyStmts.add(initializeVariable(outputName, arrayGet(load(parentArrayDesc), load(index)), Tree.NoComment));
        if(gets.isEmpty()) {
            IRTreeReturn<IntVariable> localLength = getIntField(load(outputName), "length");
            bodyStmts.add(store(returnLength,
                    findMax ? max(localLength, load(returnLength)) : min(localLength, load(returnLength)),
                    Tree.NoComment));
        } else {
            bodyStmts.add(getLength(gets, returnLength, findMax));
        }

        IRTreeVoid body = sequential(bodyStmts, Tree.NoComment);
        stmts.add(forStmt(body, IRTree.constant(0), load(length), IRTree.constant(1), index, true, Tree.NoComment));
        return sequential(stmts,
                "Iterate through all arrays to find out the " + (findMax ? "max" : "min") + "imum size");
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return getMinMaxLength(true, compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return getMinMaxLength(false, compilationCtx);
    }
}
