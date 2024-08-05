/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees;

public enum Visibility {
    PUBLIC("public"),
    PROTECTED("protected"),
    PRIVATE("private"),
    DEFAULT("");

    private final String name;

    Visibility(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String toJava() {
        return name;
    }
}
