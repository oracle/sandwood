/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.guards;

/**
 * An interface used to provide a lambda for building the custom logic at the inner point of the additional scopes.
 */
public interface TreeBuilder {
    /**
     * Method that takes a probability as its argument and uses it to construct tree elements that are embedded inside
     * the additional scopes.
     *
     * @param info The current state of the scope that the tree is being built in including the probability of reaching
     *             the tree with the model in its current state. This value will change as the different distribution
     *             values are explored.
     */
    void buildTree(TreeBuilderInfo info);
}