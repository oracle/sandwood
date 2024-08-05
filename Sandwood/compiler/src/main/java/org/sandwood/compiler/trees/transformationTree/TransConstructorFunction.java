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
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.outputTree.OutputFunction;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;

public class TransConstructorFunction extends TransFunction<TransTreeVoid> {

    private final TransTreeVoid body;

    TransConstructorFunction(Visibility visibility, FunctionName constructorName, ArgDesc<?>[] args, TransTreeVoid body,
            String comment) {
        super(visibility, constructorName, args, body, false, comment, KnownValuesTrans.constructKnownValues());
        this.body = body;
    }

    @Override
    public OutputFunction toOutputTree(ExecutionType target) {
        return OutputTree.constructorFunction(visibility, name, args, body.toOutputTree(target), comment);
    }

    @Override
    public TransConstructorFunction applyOptimisations(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        return new TransConstructorFunction(visibility, name, args,
                body.applyOptimisations(args, constants, knownValues), comment);
    }

    @Override
    protected TransFunction<?> applyConstants(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        return new TransConstructorFunction(visibility, name, args, body.applyConstants(constants), comment);
    }
}
