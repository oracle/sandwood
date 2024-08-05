/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleArraySampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.MultinomialTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Multinomial extends ArrayRandomVariable<IntVariable, Multinomial> {

    public final ArrayVariable<DoubleVariable> p;
    public final IntVariable n;

    private Multinomial(ArrayVariable<DoubleVariable> p, IntVariable n,
            RandomVariableConstructorTask<ArrayVariable<IntVariable>, Multinomial> parent) {
        super(parent, VariableType.arrayType(VariableType.IntVariable));
        this.p = p.getCurrentInstance();
        this.n = n;
    }

    @Override
    public ArrayVariable<IntVariable> sample() {
        return sample((Location) null);
    }

    @Override
    public ArrayVariable<IntVariable> sample(Location location) {
        return ArrayVariable.getArrayVariable(new SingleArraySampleTask<>(
                VariableType.arrayType(VariableType.IntVariable), this, p.getPossibleLengths(), location));
    }

    @Override
    public Multinomial getCurrentInstance() {
        return this;
    }

    @Override
    protected void getSampleTree(IRTreeReturn<ArrayVariable<IntVariable>> sample, Scope scope,
            CompilationContext compilationCtx) {
        // String name = sample.getName();

        // TODO Move the allocator into an intermediate state of the tree, so that we
        // don't need to keep track of them in separate sets.
        // TODO merge visited and add for allocators?
        // TODO Add handling of different length arrays if we are in a for loop
        // constructing lots of arrays

        compilationCtx.addTreeToScope(scope, IRTree.functionCall(FunctionType.SAMPLE, getType(), Tree.NoComment,
                p.getForwardIR(compilationCtx), n.getForwardIR(compilationCtx), sample));
    }

    public static Multinomial multinomial(ArrayVariable<DoubleVariable> p, IntVariable n) {
        return new Multinomial(p, n, new MultinomialTask(p, n, null));
    }

    public static Multinomial multinomial(ArrayVariable<DoubleVariable> p, IntVariable n, Location location) {
        return new Multinomial(p, n, new MultinomialTask(p, n, location));
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return p.getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return p.getMinLength(compilationCtx);
    }
}
