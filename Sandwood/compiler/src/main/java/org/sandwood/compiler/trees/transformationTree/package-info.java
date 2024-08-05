/*
 * Sandwood
 *
 * Copyright (c) 2018-2023, Oracle and/or its affiliates. All rights reserved.
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
/**
 * A package holding the classes for the transformation trees. These classes are immutable. Trees are transformed by a
 * transformation object that takes a tree and uses the visitor pattern to visit each node. In doing so it constructs
 * and returns a new optimized tree.
 */
package org.sandwood.compiler.trees.transformationTree;