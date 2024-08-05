/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable.OuterArrayDesc;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ArrayConstructTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {
    public final IntVariable length;

    public ArrayConstructTask(VariableType.Type<A> baseType, IntVariable length, Location location) {
        super(DFType.ARRAY_CONSTRUCTOR, VariableType.arrayType(baseType), location, length);
        this.length = length;
        // TODO add in a test here for non deterministic lengths. This will need to
        // follow the
        // length all the way back to it's source like max does. A flag to hold this
        // information
        // can be added to array, something like "deterministicLength".
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        StringBuilder sb = new StringBuilder();

        if(getInputsCount() != 1) {
            sb.append("Variable.createArray(");
            boolean first = true;
            for(Variable<?> v:getInputs()) {
                if(first)
                    first = false;
                else
                    sb.append(", ");
                sb.append(v.getExpression(compressSandwoodCode));
            }
            sb.append(")");
        } else {
            // TODO replace calls to getOutput with task specific fields.
            ArrayVariable<A> var = getOutput();
            sb.append("new ");
            ArrayType<A> type = var.getType();
            sb.append(type.getBaseJavaType());
            sb.append("[" + length.getExpression(compressSandwoodCode) + "]");
            for(int depth = type.getDepth() - 1; depth != 0; depth--)
                sb.append("[]");
        }

        return sb.toString();
    }

    @Override
    protected IRTreeReturn<ArrayVariable<A>> getForwardIRinternal(
            CompilationContext compilationCtx) {
        if(output.isSubArray()) {
            OuterArrayDesc<A> outerDesc = output.getOuterArrayDesc();
            return IRTree.arrayGet(outerDesc.getArray().getForwardIR(compilationCtx), 
                    outerDesc.getIndex().getForwardIR(compilationCtx));
        } else 
            return TreeUtils.getIndirectValue(output, compilationCtx);
    }

    @Override
    protected IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<ArrayVariable<A>> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        backTraceInfo.clearGets();
        return IRTree.getIntField(taskOutput, "length");
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ArrayConstructTask<?> t = (ArrayConstructTask<?>) other;
        return length.equivalent(t.length) && getOutputType().equals(t.getOutputType());
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        Set<VariableWrapper<IntVariable>> lengthSet = new HashSet<>();
        lengthSet.add(new VariableWrapper<>(length));
        return lengthSet;
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return length.getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return length.getMin(compilationCtx);
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }
}
