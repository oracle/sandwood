/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.util.KnownValuesIR;
import org.sandwood.compiler.trees.transformationTree.TransReturnFunction;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRReturnFunction<A extends Variable<A>> extends IRFunction<IRTreeReturn<A>> {
    private final Type<A> returnType;

    IRReturnFunction(Visibility visibility, Type<A> returnType, FunctionName name, ArgDesc[] args, IRTreeReturn<A> body,
            boolean override, String comment, KnownValuesIR knownValues) {
        super(visibility, name, args, body, override, comment, knownValues);
        this.returnType = returnType;
    }

    @Override
    public TransReturnFunction<A> toTransformationTree() {
        return TransTree.returnFunction(visibility, returnType, name, args, body.toTransformationTree(), override,
                comment, knownValues.toTransformationTree());
    }
}
