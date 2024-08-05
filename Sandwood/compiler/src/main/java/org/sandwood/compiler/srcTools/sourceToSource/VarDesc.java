/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

public class VarDesc {
    final String type;
    final Token[] typeLocation;
    final Token nameLocation;

    VarDesc(String type, Token[] typeLocation, Token nameLocation) {
        this.type = type;
        this.typeLocation = typeLocation;
        this.nameLocation = nameLocation;
    }
}
