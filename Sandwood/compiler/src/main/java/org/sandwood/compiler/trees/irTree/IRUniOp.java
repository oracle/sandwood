/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;

public abstract class IRUniOp<A extends Variable<A>, B extends Variable<B>> extends IRTreeReturn<B> {

    protected final IRTreeReturn<A> input;
    private final Type<B> outputType;
    private final String description;

    public IRUniOp(IRTreeType type, IRTreeReturn<A> input, Type<B> outputType, String description) {
        super(type);
        this.input = input;
        this.outputType = outputType;
        this.description = description;
    }

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { input };
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = type.hashCode();
        result = prime * result + input.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRUniOp<?, ?> other = (IRUniOp<?, ?>) tree;
        return input.equivalent(other.input);
    }

    @Override
    public Type<B> getOutputType() {
        return outputType;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(input);
    }

}