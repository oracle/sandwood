/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.ReductionScope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.MissingFeatureException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ReductionInput<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public final ArrayVariable<A> array;
    public final Variable<A> emptyValue;
    public final IntVariable start;
    public final IntVariable end;
    public final boolean first;

    public ReductionInput(IntVariable start, IntVariable end, Variable<A> emptyValue, ArrayVariable<A> a, boolean first,
            Location location) {
        super(DFType.REDUCE_INPUT, a.getElementType(), location, start, end, emptyValue, a.getCurrentInstance());
        if(a.isDistribution())
            throw new MissingFeatureException(
                    "Sandwood is currently unable to reduce arrays of distributions as the number of concurrent distributed values cannot be calculated at compile time.");
        this.start = start;
        this.end = end;
        this.emptyValue = emptyValue.getCurrentInstance();
        this.array = a.getCurrentInstance();
        inlineableTask = false;
        this.first = first;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "";
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return getSandwoodExpression(compressSandwoodCode);
    }

    @Override
    public int getSandwoodCode(StringBuilder sb, int indent, Map<Variable<?>, Boolean> inlineableVariables,
            boolean compressSandwoodCode) {
        return indent;
    }

    @Override
    public IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.load(getOutput());
    }

    @Override
    public IRTreeReturn<ArrayVariable<A>> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "It is not possible to invert a reduction as information is lost in the reduction step, so the array cannot be recreated.";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ReductionInput<?> t = (ReductionInput<?>) other;
        return start.equivalent(t.start) && end.equivalent(t.end) && array.equivalent(t.array);
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        if(first)
            super.constructTrace(desc);
    }

    @Override
    public ReductionScope<A> scope() {
        return (ReductionScope<A>) super.scope();
    }

    /**
     * Method used by Variable to set itself as an output of the task.
     *
     * @param output The variable to become and output of this task.
     */
    @Override
    public void setOutput(A output) {
        super.setOutput(output);
        output.setPrivate();
    }
}
