/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

public class IdentiferDesc {
    public final String name;
    public final Token source;

    public IdentiferDesc(String name, Token source) {
        this.name = name;
        this.source = source;
    }
}
