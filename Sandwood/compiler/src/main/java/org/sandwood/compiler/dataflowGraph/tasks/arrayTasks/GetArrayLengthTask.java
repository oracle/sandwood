/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class GetArrayLengthTask extends NumberProducingDataflowTaskImplementation<IntVariable> {

    /**
     * An alternative tree for constructing the length that can be used when accessing variables that just have a shape
     * constructed for them.
     */
    private final ProducingDataflowTask<IntVariable> alternative;

    public final ArrayVariable<?> array;

    public GetArrayLengthTask(ArrayVariable<?> array) {
        this(array, null, null);
    }

    public GetArrayLengthTask(ArrayVariable<?> array, Location location) {
        this(array, null, location);
    }

    public GetArrayLengthTask(ArrayVariable<?> array, ProducingDataflowTask<IntVariable> alternative) {
        this(array, alternative, null);
    }

    public GetArrayLengthTask(ArrayVariable<?> array, ProducingDataflowTask<IntVariable> alternative,
            Location location) {
        super(DFType.GET_LENGTH, VariableType.IntVariable, location, array);
        this.array = array.instanceHandle();
        this.alternative = alternative;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return getInput(0).getExpression(compressSandwoodCode) + ".length";
    }

    @Override
    public IRTreeReturn<IntVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        Set<VariableWrapper<IntVariable>> possibleLengths = array.getPossibleLengths();
        if(possibleLengths.size() == 1) {
            IntVariable length = possibleLengths.iterator().next().value;
            boolean inScope = inScope(length.scope());
            if(!length.equivalent(getOutput()) && inScope)
                return length.getForwardIR(compilationCtx);
        }
        return IRTree.getIntField(array.getForwardIR(compilationCtx), "length");
    }

    private boolean inScope(Scope variableScope) {
        Scope enclosingScope = scope();
        while(enclosingScope != GlobalScope.scope) {
            if(variableScope == enclosingScope)
                return true;
            enclosingScope = enclosingScope.getEnclosingScope();
        }
        return variableScope == enclosingScope;
    }

    @Override
    public IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "It is not possible to construct an inverse function via an array length as "
                + "the array cannot be reconstructed from the length.";
    }

    public void useAlternative() {
        getOutput().changeParent(alternative);
        array.removeConsumer(this);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        GetArrayLengthTask t = (GetArrayLengthTask) other;
        return array.instanceHandle() == t.array.instanceHandle();
    }

    /**
     * We don't have dynamic allocation of memory, so all allocation sizes must come from static inputs. As a result the
     * output of an RV can never go through a length as the only way it could set a length if it was the size input to a
     * constructor which is not allowed.
     */
    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        desc.trace.push(new DataflowTaskArgDesc(this, 0));
        array.instanceHandle().constructTrace(desc);
        desc.trace.pop();
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        return array.getMaxLength(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        return array.getMinLength(compilationCtx);
    }
}
