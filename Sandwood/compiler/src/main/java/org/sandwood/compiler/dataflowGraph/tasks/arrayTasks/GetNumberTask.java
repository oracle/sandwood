/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.MissingFeatureException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class GetNumberTask<A extends NumberVariable<A>> extends GetTask<A> implements NumberProducingDataflowTask<A> {

    public GetNumberTask(ArrayVariable<A> array, IntVariable index, Location location) {
        super(array, index, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        VariableDescription<A> max = VariableNames.calcVarName(getOutput(), "max", getOutputType());
        Set<PutTask<A>> puts = array.getPuts(scope(), id());

        // If there are no puts this is an input value. TODO replace this with a
        // complete scan of the inputs to calculate the size.
        if(puts.isEmpty())
            throw new MissingFeatureException("Unable to determine the maximum size of model inputs.");
        else {
            // For each put value, find it's maximum value and calculate the maximum of all
            // these values.
            PriorityQueue<PutTask<A>> p = new PriorityQueue<>(puts);
            PutTask<A> pt = p.poll();
            A v = (A) pt.value;
            IRTreeVoid t = IRTree.initializeVariable(max, v.getMax(compilationCtx),
                    "Calculate the maximum size of the inputs to array " + array.getVarDesc() + ".");
            compilationCtx.addTreeToScope(GlobalScope.scope, t);
            while(!p.isEmpty()) {
                pt = p.poll();
                v = (A) pt.value;
                t = IRTree.store(max, IRTree.max(IRTree.load(max), v.getMax(compilationCtx)), Tree.NoComment);
                compilationCtx.addTreeToScope(GlobalScope.scope, t);
            }
            return IRTree.load(max);
        }
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        VariableDescription<A> min = VariableNames.calcVarName(getOutput(), "min", getOutputType());
        Set<PutTask<A>> puts = array.getPuts(scope(), id());

        // If there are no puts this is an input value. TODO replace this with a
        // complete scan of the inputs to calculate the size.
        if(puts.isEmpty())
            throw new MissingFeatureException("Unable to determine the minimum size of model inputs.");
        else {
            // For each put value, find it's maximum value and calculate the maximum of all
            // these values.
            PriorityQueue<PutTask<A>> p = new PriorityQueue<>(puts);
            PutTask<A> pt = p.poll();
            A v = (A) pt.value;
            IRTreeVoid t = IRTree.initializeVariable(min, v.getMin(compilationCtx),
                    "Calculate the minimum size of the inputs to array " + array.getVarDesc() + ".");
            compilationCtx.addTreeToScope(GlobalScope.scope, t);
            while(!p.isEmpty()) {
                pt = p.poll();
                v = (A) pt.value;
                t = IRTree.store(min, IRTree.min(IRTree.load(min), v.getMin(compilationCtx)), Tree.NoComment);
                compilationCtx.addTreeToScope(GlobalScope.scope, t);
            }
            return IRTree.load(min);
        }
    }
}
