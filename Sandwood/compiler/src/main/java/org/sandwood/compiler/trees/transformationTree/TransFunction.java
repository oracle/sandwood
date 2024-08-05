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
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;

public abstract class TransFunction<T extends TransTree<T>> {

    public final FunctionName name;
    public final ArgDesc<?>[] args;
    public final T body;
    public final Visibility visibility;
    public final boolean override;
    public final String comment;
    public final KnownValuesTrans knownValues;

    protected TransFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args, T body, boolean override,
            String comment, KnownValuesTrans knownValues) {
        this.visibility = visibility;
        this.name = name;
        this.args = args;
        this.body = body;
        this.override = override;
        this.comment = comment;
        this.knownValues = knownValues;
    }

    public abstract OutputFunction toOutputTree(ExecutionType target);

    public abstract TransFunction<T> applyOptimisations(Map<VariableDescription<?>, TransTreeReturn<?>> constants);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toOutputTree(ExecutionType.SingleThreadCPU).toJava(sb, 0);
        return sb.toString();
    }

    protected abstract TransFunction<?> applyConstants(Map<VariableDescription<?>, TransTreeReturn<?>> constants);
}
