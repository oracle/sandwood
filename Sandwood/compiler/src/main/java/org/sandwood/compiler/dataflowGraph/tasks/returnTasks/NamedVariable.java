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
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

/**
 * Used to construct named variables in the auto generated code. This should never be used to construct the original
 * DAGs.
 *
 * @param <A>
 */
public class NamedVariable<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public final VariableDescription<A> varDesc;

    public NamedVariable(VariableDescription<A> varDesc) {
        super(DFType.NAMED_VARIABLE, varDesc.type, false, null);
        this.varDesc = varDesc;
        inlineableTask = false;
    }

    @Override
    public void setOutput(A output) {
        output.setAlias(varDesc);
        output.setUniqueVarDesc(varDesc);
        super.setOutput(output);
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return varDesc.toString();
    }

    @Override
    public IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return load(varDesc);
    }

    @Override
    public IRTreeReturn<A> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "There is nowhere to go back from a named variable. The trace that lead here should not exist";
    }

    // TODO ensure that when a variable is constrained by this the getSandwoodMethod
    // is
    // called.
    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "Load field " + varDesc;
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
        NamedVariable<?> dft = (NamedVariable<?>) other;
        if(!varDesc.equals(dft.varDesc))
            return false;
        return this.getOutputType().equals(dft.getOutputType());
    }

}
