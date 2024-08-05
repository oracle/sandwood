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
import org.sandwood.compiler.trees.transformationTree.TransFunction;

public abstract class IRFunction<T extends IRTree> {

    public final FunctionName name;
    public final ArgDesc[] args;
    public final T body;
    public final Visibility visibility;
    public final boolean override;
    public final String comment;
    public final KnownValuesIR knownValues;

    protected IRFunction(Visibility visibility, FunctionName name, ArgDesc[] args, T body, boolean override,
            String comment, KnownValuesIR knownValues) {
        this.visibility = visibility;
        this.name = name;
        this.args = args;
        this.body = body;
        this.override = override;
        this.comment = comment;
        this.knownValues = knownValues;
    }

    public abstract TransFunction<?> toTransformationTree();

    @Override
    public String toString() {
        return toTransformationTree().toString();
    }
}
