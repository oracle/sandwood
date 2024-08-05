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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.DirichletTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.RandomVariableConstructorTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
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
        return ArrayVariable.getArrayVariable(new SingleArraySampleTask<>(
                VariableType.arrayType(VariableType.DoubleVariable), this, beta.getPossibleLengths(), location));
    }

    @Override
    public Dirichlet getCurrentInstance() {
        return this;
    }

    @Override
    public void getSampleTree(IRTreeReturn<ArrayVariable<DoubleVariable>> sample, Scope scope,
            CompilationContext compilationCtx) {
        // String name = sample.getName();

        // TODO Move the allocator into an intermediate state of the tree, so that we
        // don't need to keep track of them in separate sets.
        // TODO merge visited and add for allocators?
        // TODO Add handling of different length arrays if we are in a for loop
        // constructing lots of arrays
        /*
         * if (!compilationCtx.allocatorVisited(sample)) { compilationCtx.addAllocator(sample); // Needing to know the
         * value of length in the allocator may cause us problems in // the future. //ArrayType<DoubleVariable>
         * arrayType = VariableType.arrayType(VariableType.DoubleVariable); //Create a new scope to perform the
         * construction in compilationCtx.pushScope(); CompilationPhase currentPhase = compilationCtx.phase;
         * compilationCtx.phase = CompilationPhase.Allocation;
         * 
         * //Construct the allocator compilationCtx.addConstructedClassField(sample, sample.isIntermediate(), false,
         * compilationCtx);
         */
        /*
         * compilationCtx.addTreeToScope(getEnclosingScope(), store(name, newArray(beta.length().getForwardIR(
         * compilationCtx), arrayType))); compilationCtx.addConstructedClassField(name, arrayType,
         * compilationCtx.getOutermostScopeTree(), sample, true, false);
         */

        // Restore the old scope
        /*
         * compilationCtx.phase = currentPhase; compilationCtx.popScope(); }
         */

        compilationCtx.addTreeToScope(scope, IRTree.functionCall(FunctionType.SAMPLE, getType(), Tree.NoComment,
                beta.getForwardIR(compilationCtx), sample));
    }

    public static Dirichlet dirichlet(ArrayVariable<DoubleVariable> beta) {
        return new Dirichlet(beta, new DirichletTask(beta, null));
    }

    public static Dirichlet dirichlet(ArrayVariable<DoubleVariable> beta, Location location) {
        return new Dirichlet(beta, new DirichletTask(beta, location));
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
