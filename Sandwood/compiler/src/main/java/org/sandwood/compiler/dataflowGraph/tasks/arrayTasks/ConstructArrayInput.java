/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.ConstructInput;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.VariableWrapper;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ConstructArrayInput<A extends Variable<A>> extends ConstructInput<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {

    private Variable<?> shapeVar = null;

    public ConstructArrayInput(Type<ArrayVariable<A>> type, String name, Location location) {
        super(type, name, location);
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        Set<VariableWrapper<IntVariable>> lengthSet = new HashSet<>();
        IntVariable length;
        if(shapeVar == null) { // If this observed variable is a constructed variable holding the shape
            length = IntVariable.intVariable(new GetArrayLengthTask(getOutput(), getLocation()));
        } else {
            // Calculate the dimension of the input array
            int arrayDim = getOutputType().getDepth();
            // Construct the length for this array.
            if(arrayDim == 1)
                length = (IntVariable) shapeVar;
            else
                length = IntVariable.intVariable(new GetArrayLengthTask((ArrayVariable<?>) shapeVar, getLocation()));
        }
        lengthSet.add(new VariableWrapper<>(length));
        return lengthSet;
    }

    public Variable<?> shapeVar() {
        return shapeVar;
    }

    @Override
    public IRTreeReturn<IntVariable> getLength(CompilationContext compilationCtx) {
        if(shapeVar == null)
            return IRTree.getIntField(output.getForwardIR(compilationCtx), "length");
        else {
            if(shapeVar.getType().isArray())
                return IRTree.getIntField(shapeVar.getForwardIR(compilationCtx), "length");
            else
                return ((IntVariable) shapeVar).getForwardIR(compilationCtx);
        }
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        return IRTree.getIntField(output.getForwardIR(compilationCtx), "length");
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        return IRTree.getIntField(output.getForwardIR(compilationCtx), "length");
    }

    public void constuctShapeVaraible() {
        // Calculate the dimension of the input array
        int arrayDim = getOutputType().getDepth();

        if(arrayDim == 1) // TODO tidy up the creation and removal of VariableName's here.
            shapeVar = Variable.observeInt(
                    VariableNames.lengthName(new VariableDescription<>(name, VariableType.IntVariable, false)).name
                            .getName(),
                    getLocation());
        else {
            ArrayType<?> lengthType = (ArrayType<?>) VariableType.getType(VariableType.IntVariable, arrayDim - 1);
            shapeVar = Variable.observeArray(
                    VariableNames.lengthName(new VariableDescription<>(name, lengthType, false)).name.getName(),
                    lengthType, getLocation());
        }
    }
}
