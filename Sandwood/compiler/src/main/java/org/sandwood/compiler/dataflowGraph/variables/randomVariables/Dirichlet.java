/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SingleArraySampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.DirichletTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class Dirichlet extends ArrayRandomVariable<DoubleVariable, Dirichlet> {

    public final ArrayVariable<DoubleVariable> beta;

    private Dirichlet(ArrayVariable<DoubleVariable> beta,
            RandomVariableConstructorTask<ArrayVariable<DoubleVariable>, Dirichlet> parent) {
        super(parent, VariableType.arrayType(VariableType.DoubleVariable));
        this.beta = beta.getCurrentInstance();
    }

    @Override
    public ArrayVariable<DoubleVariable> sample() {
        return sample((Location) null);
    }

    @Override
    public ArrayVariable<DoubleVariable> sample(Location location) {
        return ArrayVariable.getArrayVariable(
                new SingleArraySampleTask<>(VariableType.arrayType(VariableType.DoubleVariable), this, location));
    }

    @Override
    public Dirichlet getCurrentInstance() {
        return this;
    }

    @Override
    public void getSampleTree(IRTreeReturn<ArrayVariable<DoubleVariable>> sample, Scope scope,
            CompilationContext compilationCtx) {

        compilationCtx.addTreeToScope(scope, IRTree.functionCall(FunctionType.SAMPLE, getType(), Tree.NoComment,
                beta.getForwardIR(compilationCtx), beta.getLength(compilationCtx), sample));
    }

    public static Dirichlet dirichlet(ArrayVariable<DoubleVariable> beta) {
        return new Dirichlet(beta, new DirichletTask(beta, null));
    }

    public static Dirichlet dirichlet(ArrayVariable<DoubleVariable> beta, Location location) {
        return new Dirichlet(beta, new DirichletTask(beta, location));
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        return beta.getPossibleLengths();
    }

    @Override
    public IRTreeReturn<IntVariable> getLength(CompilationContext compilationCtx) {
        return beta.getLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return beta.getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return beta.getMinLength(compilationCtx);
    }
}
