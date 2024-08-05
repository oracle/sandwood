/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ReductionReturnTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public final Variable<A> var;

    public ReductionReturnTask(A var, Location location) {
        super(DFType.REDUCTION_RETURN, var.getType(), location, var);
        this.var = var;
        inlineableTask = false;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "return " + this.getInput(0).getExpression(compressSandwoodCode) + ";";
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return getSandwoodExpression(compressSandwoodCode);
    }

    @Override
    public IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        ReductionScope<A> reductionScope = getReductionScope();
        compilationCtx.enterScope(reductionScope);
        VariableDescription<A> varDesc = reductionScope.getVariableDesc(compilationCtx);
        IRTreeReturn<A> t = var.getForwardIR(compilationCtx);
        compilationCtx.addTreeToScope(reductionScope, IRTree.store(varDesc, t,
                "Copy the result of the reduction into the variable returned by the reduction."));
        compilationCtx.leaveScope(reductionScope);
        return IRTree.load(varDesc);
    }

    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "It is not possible to invert a reduction as "
                + "information is lost in the reduction step, so the array cannot be recreated.";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ReductionReturnTask<?> t = (ReductionReturnTask<?>) other;
        return var.equivalent(t.var);
    }

    public ReductionScope<A> getReductionScope() {
        return (ReductionScope<A>) var.scope();
    }
}
