/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees;

public interface TreeInterface extends Comparable<TreeInterface> {

    int getId();

    int scopeId(int scopeId);

    String getDescription();

    TreeInterface[] getChildren();
}
