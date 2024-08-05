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
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputVoidFunction;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;

public class TransVoidFunction extends TransFunction<TransTreeVoid> {

    TransVoidFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args, TransTreeVoid body, boolean override,
            String comment, KnownValuesTrans knownValues) {
        super(visibility, name, args, body, override, comment, knownValues);
    }

    @Override
    public OutputVoidFunction toOutputTree(ExecutionType target) {
        return OutputTree.voidFunction(visibility, name, args, body.toOutputTree(target), override, comment);
    }

    @Override
    public TransVoidFunction applyOptimisations(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        KnownValuesTrans newKnownValues = knownValues.applyOptimisations(args, constants);
        TransTreeVoid newBody = body.applyOptimisations(args, constants, newKnownValues);
        return new TransVoidFunction(visibility, name, args, newBody, override, comment, newKnownValues);
    }

    @Override
    protected TransFunction<?> applyConstants(Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        KnownValuesTrans newKnownValues = knownValues.applyOptimisations(args, constants);
        TransTreeVoid newBody = body.applyOptimisations(args, constants, newKnownValues);
        return new TransVoidFunction(visibility, name, args, newBody.applyConstants(constants), override, comment,
                newKnownValues);
    }
}
