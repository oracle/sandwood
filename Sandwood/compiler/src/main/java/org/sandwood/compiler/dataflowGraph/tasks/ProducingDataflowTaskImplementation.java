/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class ProducingDataflowTaskImplementation<A extends Variable<A>> extends DataflowTaskImplementation<A>
        implements ProducingDataflowTask<A> {

    /**
     * Flag to say if the value produced here will be the same in every iteration of the code.
     */
    private boolean deterministic;

    private final VariableType.Type<A> type;

    public ProducingDataflowTaskImplementation(DFType dfType, VariableType.Type<A> type, boolean deterministic,
            Location location, Variable<?>... vars) {
        super(dfType, location, vars);
        this.type = type;
        this.deterministic = deterministic;
    }

    public ProducingDataflowTaskImplementation(DFType dfType, VariableType.Type<A> type, Location location,
            Variable<?>... vars) {
        super(dfType, location, vars);
        this.type = type;
        boolean d = true;
        for(Variable<?> v:vars)
            d = d && v.isDeterministic();
        deterministic = d;
    }

    @Override
    public VariableType.Type<A> getOutputType() {
        return type;
    }

    @Override
    public void setNonDeterministic() {
        if(deterministic) {
            deterministic = false;
            output.setNonDeterministic();
        }
    }

    @Override
    public boolean deterministic() {
        return deterministic;
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        int i = 0;
        for(Variable<?> in:inputs) {
            desc.trace.push(new DataflowTaskArgDesc(this, i));
            in.constructTrace(desc);
            desc.trace.pop();
            i++;
        }
    }

    protected abstract IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx);

    @Override
    public IRTreeReturn<?> getInverseIR(int argPos, IRTreeReturn<?> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        @SuppressWarnings("unchecked")
        IRTreeReturn<?> v = getInverseIRInternal(argPos, (IRTreeReturn<A>) taskOutput, backTraceInfo, compilationCtx);
        if(v == null)
            throw new CompilerException("Generated null tree.");
        return v;
    }
}
