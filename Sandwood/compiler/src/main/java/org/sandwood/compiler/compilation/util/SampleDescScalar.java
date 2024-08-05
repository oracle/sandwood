/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

/**
 * A class for describing the sample task that generates a scalar result.
 *
 * @param <A> The type of the output of the sample task.
 * @param <B> The type of the random variable that the sample is drawn from.
 */
public class SampleDescScalar<A extends ScalarVariable<A>, B extends RandomVariable<A, B>> extends SampleDesc<A, B> {
    public SampleDescScalar(SampleTask<A, B> sample, Traces traces) {
        super(sample, traces);
    }

    /**
     * Method to update the value of the sample output.
     * 
     * @param sampleValue    Tree generating the new value for the output.
     * @param compilationCtx The compilation context.
     */
    public void updateSample(IRTreeReturn<A> sampleValue, CompilationContext compilationCtx) {
        IRTreeVoid f;
        // If the output value is saved as part of the model update this value.
        if(output.isSample())
            f = TreeUtils.putIndirectValue(output.getUniqueVarDesc(),
                    TreeUtils.toArgTrees(variableIndexes, compilationCtx), sampleValue,
                    "Write out the new value of the sample.");
        // Otherwise create a variable to hold the value.
        else
            f = IRTree.initializeVariable(output.getUniqueVarDesc(), sampleValue,
                    "Write out the value of the sample to a temporary variable prior to updating the intermediate variables.");
        // Add the tree to the scope.
        compilationCtx.addTreeToScope(GlobalScope.scope, f);
        // and update any other intermediate variables.
        setIntermediateValues(compilationCtx);
    }
}
