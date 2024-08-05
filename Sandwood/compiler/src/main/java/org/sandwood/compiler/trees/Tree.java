/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.tools.visuliser.GraphVisualizer;

public abstract class Tree<T extends Tree<T>> implements Comparable<T> {
    public static final String NoComment = null;

    public final TreeID id;
    // A set of tags that can be used to identify properties of the tree.
    protected Set<Tag> tags;

    protected Tree(Set<Tag> tags) {
        id = new TreeID();
        this.tags = tags;
    }

    protected Tree() {
        this(Collections.emptySet());
    }

    public int scopeId(int scopeId) {
        return scopeId;
    }

    public abstract T[] getChildren();

    public abstract String getDescription();

    public void visualize() {
        GraphVisualizer v = GraphVisualizer.visualize(this);
        v.setVisible(true);
    }

    /**
     * Method that is like equals, but doesn't test if this is the same instance of the class. equals cannot be used for
     * this as separation between instances that are the same is required for proxy fields when constructing traces. For
     * example two proxies that are yet to be set can be referenced by different trees, but will appear identical and
     * will be merged.
     *
     * @param tree
     * @return
     */
    public abstract boolean equivalent(T tree);

    public abstract int equivalentHashCode();

    @Override
    public int compareTo(T t) {
        return id.tag - t.id.tag;
    }

    public boolean containsTag(Tag tag) {
        return tags.contains(tag);
    }

    public Set<Tag> tags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags = new HashSet<>(tags);
        tags.add(tag);
    }

    public void addTags(Set<Tag> newTags) {
        if(!newTags.isEmpty()) {
            tags = new HashSet<>(tags);
            tags.addAll(newTags);
        }
    }
}
