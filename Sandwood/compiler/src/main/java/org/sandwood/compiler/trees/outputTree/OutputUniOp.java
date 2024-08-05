/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;

public abstract class OutputUniOp<A extends Variable<A>, B extends Variable<B>> extends OutputTreeReturn<B> {

    protected final OutputTreeReturn<A> input;
    private final String description;

    public OutputUniOp(OutputTreeType type, OutputTreeReturn<A> input, String description) {
        super(type);
        this.input = input;
        this.description = description;
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { input };
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * type.hashCode();
        result = prime * result + input.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputUniOp<?, ?> other = (OutputUniOp<?, ?>) obj;
        return input.equals(other.input);
    }

}