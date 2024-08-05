/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees;

public class WrappedTree<B extends Tree<B>, T extends B> implements Comparable<WrappedTree<B, T>> {
    @Override
    public int hashCode() {
        return tree.equivalentHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        WrappedTree<?, ?> other = (WrappedTree<?, ?>) obj;
        if(tree.getClass() != other.tree.getClass())
            return false;
        @SuppressWarnings("unchecked")
        B otherTree = (B) other.tree;
        return tree.equivalent(otherTree);
    }

    public final T tree;

    public WrappedTree(T tree) {
        this.tree = tree;
    }

    @Override
    public int compareTo(WrappedTree<B, T> o) {
        return tree.compareTo(o.tree);
    }

    @Override
    public String toString() {
        return tree.toString();
    }
}