/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.util.KnownValuesIR;
import org.sandwood.compiler.trees.transformationTree.TransConstructorFunction;
import org.sandwood.compiler.trees.transformationTree.TransTree;

public class IRConstructorFunction extends IRFunction<IRTreeVoid> {

    IRConstructorFunction(Visibility visibility, ClassName className, ArgDesc[] args, IRTreeVoid body, String comment) {
        super(visibility, FunctionName.getConstructorName(className), args, body, false, comment,
                KnownValuesIR.constructKnownValues());
    }

    @Override
    public TransConstructorFunction toTransformationTree() {
        return TransTree.constructorFunction(visibility, name, args, body.toTransformationTree(), comment);
    }
}
