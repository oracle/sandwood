/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class ArrayRandomVariable<A extends Variable<A>, B extends ArrayRandomVariable<A, B>>
        extends RandomVariableImplementation<ArrayVariable<A>, B> {

    protected ArrayRandomVariable(
            RandomVariableConstructorTask<ArrayVariable<A>, B> parent, Type<ArrayVariable<A>> outputType) {
        super(parent, outputType);
    }

    @Override
    public final IRTreeReturn<ArrayVariable<A>> getSampleTree(ArrayVariable<A> sample,
            CompilationContext compilationCtx) {
        IRTreeReturn<ArrayVariable<A>> outputValue;
        if(sample.isIntermediate()) {
            outputValue = TreeUtils.getIndirectValue(sample, compilationCtx);
        } else {
            outputValue = IRTree.load(sample);
        }
        getSampleTree(outputValue, sample.scope(), compilationCtx);
        return outputValue;
    }

    protected abstract void getSampleTree(IRTreeReturn<ArrayVariable<A>> sample, Scope scope,
            CompilationContext compilationCtx);

    public abstract IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx);

    public abstract IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx);
}
