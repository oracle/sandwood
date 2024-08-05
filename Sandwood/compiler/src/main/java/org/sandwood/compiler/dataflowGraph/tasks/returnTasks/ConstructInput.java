/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import static org.sandwood.compiler.trees.irTree.IRTree.load;

import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

/**
 * TODO Make a second version of this that takes Java base types and use this to set all constants in out programs. TODO
 * Making int for example will then be shorthand for creating an unset int and observing its values.
 *
 * @param <A>
 */
public class ConstructInput<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public final String name;
    public Variable<A> target = null;

    public ConstructInput(VariableType.Type<A> type, String name, Location location) {
        super(DFType.CONSTRUCT_INPUT, type, true, location);
        this.name = name;
        inlineableTask = false;
    }

    @Override
    public void setOutput(A output) {
        if(target == null) {
            output.setAlias(name);
            target = output;
        } else
            throw new CompilerException("The output of this construct input task is already set.");
        super.setOutput(output);
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return target.getType().getJavaType() + " " + target.getExpression(false) + " = observe(" + name + ");";
    }

    @Override
    public IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        VariableDescription<A> varName = getOutput().getUniqueVarDesc();
        return load(varName);
    }

    @Override
    public IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "There is nowhere to go back from a constructor, the trace that lead here should not be listed for back tracing";
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        desc.trace.add(new DataflowTaskArgDesc(this, 0));
        desc.c.callback(desc.trace, desc.sink);
        desc.trace.pop();
    }

    // TODO ensure that when a variable is constrained by this when the
    // getSandwoodMethod is called.
    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "Load field " + name;
    }

    @Override
    public int getSandwoodCode(StringBuilder sb, int indent, Map<Variable<?>, Boolean> inlineableVariables,
            boolean compressSandwoodCode) {
        addIndent(sb, indent);
        sb.append(getSandwoodString(compressSandwoodCode) + "\n");
        return indent;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        ConstructInput<?> input = (ConstructInput<?>) other;
        if(!name.equals(input.name))
            return false;
        return getType().equals(input.getType());
    }
}
