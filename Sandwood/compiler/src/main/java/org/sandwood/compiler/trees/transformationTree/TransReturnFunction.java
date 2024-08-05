/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.outputTree.OutputFunction;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;

public class TransReturnFunction<A extends Variable<A>> extends TransFunction<TransTreeReturn<A>> {
    private final Type<A> returnType;

    TransReturnFunction(Visibility visibility, Type<A> returnType, FunctionName name, ArgDesc<?>[] args,
            TransTreeReturn<A> body, boolean override, String comment, KnownValuesTrans knownValues) {
        super(visibility, name, args, body, override, comment, knownValues);
        this.returnType = returnType;
    }

    @Override
    public OutputFunction toOutputTree(ExecutionType target) {
        return OutputTree.returnFunction(visibility, returnType, name, args, body.toOutputTreeReturn(target), override,
                comment);
    }

    @Override
    public TransReturnFunction<A> applyOptimisations(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        KnownValuesTrans newKnownValues = knownValues.applyOptimisations(args, constants);
        TransTreeReturn<A> newBody = body.applyOptimisations(args, constants, newKnownValues);
        return TransTree.returnFunction(visibility, returnType, name, args, newBody, override, comment, newKnownValues);
    }

    @Override
    protected TransFunction<?> applyConstants(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        KnownValuesTrans newKnownValues = knownValues.applyOptimisations(args, constants);
        TransTreeReturn<A> newBody = body.applyOptimisations(args, constants, newKnownValues);
        return TransTree.returnFunction(visibility, returnType, name, args, newBody, override, comment, newKnownValues);
    }
}
