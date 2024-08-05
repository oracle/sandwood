/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.util.KnownValuesIR;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransVoidFunction;

public class IRVoidFunction extends IRFunction<IRTreeVoid> {
    IRVoidFunction(Visibility visibility, FunctionName name, ArgDesc[] args, IRTreeVoid body, boolean override,
            String comment, KnownValuesIR knownValues) {
        super(visibility, name, args, body, override, comment, knownValues);
    }

    @Override
    public TransVoidFunction toTransformationTree() {
        return TransTree.voidFunction(visibility, name, args, body.toTransformationTree(), override, comment,
                knownValues.toTransformationTree());
    }
}
