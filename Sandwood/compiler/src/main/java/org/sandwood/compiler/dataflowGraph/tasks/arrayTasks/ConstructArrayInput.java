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
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.ConstructInput;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.CopyNumberTask;
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
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class ConstructArrayInput<A extends Variable<A>> extends ConstructInput<ArrayVariable<A>>
        implements ArrayProducingDataflowTask<A> {

    public final Variable<?> lengthVar;
    private final boolean compilerConstructedVar;

    public ConstructArrayInput(Type<ArrayVariable<A>> type, String name, Location location) {
        super(type, name, location);
        compilerConstructedVar = VariableNames.isLengthName(name);

        if(compilerConstructedVar)
            lengthVar = null;
        else {
            // Calculate the dimension of the input array
            int arrayDim = type.getDepth();

            if(arrayDim == 1) // TODO tidy up the creation and removal of VariableName's here.
                lengthVar = Variable.observeInt(
                        VariableNames.lengthName(new VariableDescription<>(name, VariableType.IntVariable, false)).name
                                .getName(),
                        location);
            else {
                ArrayType<?> lengthType = (ArrayType<?>) VariableType.getType(VariableType.IntVariable, arrayDim - 1);
                lengthVar = Variable.observeArray(
                        VariableNames.lengthName(new VariableDescription<>(name, lengthType, false)).name.getName(),
                        lengthType, location);
            }
        }
    }

    @Override
    public Set<VariableWrapper<IntVariable>> getPossibleLengths() {
        Set<VariableWrapper<IntVariable>> lengthSet = new HashSet<>();
        IntVariable length;
        if(compilerConstructedVar) { // If this observed variable is a constructed variable holding the shape
            length = IntVariable.intVariable(new GetArrayLengthTask(getOutput(), getLocation()));
        } else {
            // Calculate the dimension of the input array
            int arrayDim = getOutputType().getDepth();
            // Construct the length for this array.
            if(arrayDim == 1)
                length = (IntVariable) lengthVar;
            else
                length = IntVariable.intVariable(new GetArrayLengthTask((ArrayVariable<?>) lengthVar, getLocation()));
            length = IntVariable.intVariable(
                    new GetArrayLengthTask(getOutput(), new CopyNumberTask<>(length, getLocation()), getLocation()));
        }
        lengthSet.add(new VariableWrapper<>(length));
        return lengthSet;
    }

    @Override
    public IRTreeReturn<IntVariable> getMaxLength(CompilationContext compilationCtx) {
        IntVariable length = IntVariable.intVariable(new GetArrayLengthTask(getOutput()));
        return length.getForwardIR(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMinLength(CompilationContext compilationCtx) {
        IntVariable length = IntVariable.intVariable(new GetArrayLengthTask(getOutput()));
        return length.getForwardIR(compilationCtx);
    }
}
