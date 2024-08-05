/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.trees.Tree;

public class OutputNOP extends OutputTree {

    static final OutputNOP nop = new OutputNOP();

    private OutputNOP() {
        super(OutputTreeType.NOP, true, Tree.NoComment);
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {}

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[0];
    }

    @Override
    public String getDescription() {
        return "NOP";
    }

    @Override
    protected OutputTree copy(Map<OutputTree, OutputTree> results) {
        results.put(this, this);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }
}
