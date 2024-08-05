/*
 * Sandwood
 *
 * Copyright (c) 2018-2023, Oracle and/or its affiliates. All rights reserved.
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */
/**
 * A root package for classes describing trees code produced by the compiler and transformations on that code. THe trees
 * are split into 3 classes:
 * <p>
 * IR-trees These are Intermediate Representation trees, and are the trees that the compiler first constructs. They
 * include proxy sequential nodes that allow additional subtrees to be added to an existing tree. This is important in
 * the incremental construction of functions, but reduces the ability to reason about the tree when performing
 * optimisation work.
 * <p>
 * Trans-trees These are immutable trees that optimizations can be applied to to construct new versions of the tree that
 * are semantically the same, but more efficient. These transformations are performed by an implementation of the
 * visitor pattern.
 * <p>
 * Output-trees These are trees that generate code to be output. Currently they only generate Java code, but it is
 * envisaged that they will produce other code types in the future.
 * <p>
 * As the compilation progresses each function will move through each tree type in turn.
 */
package org.sandwood.compiler.trees;