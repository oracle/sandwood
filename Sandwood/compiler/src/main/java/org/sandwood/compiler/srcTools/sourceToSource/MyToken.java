/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

public class MyToken extends Token {
    /**
     *
     */
    private static final long serialVersionUID = 2611883316634674616L;

    /**
     * Constructs a new token for the specified Image and Kind.
     */
    public MyToken(int kind, String image) {
        this.kind = kind;
        this.image = image;
    }

    int realKind = SandwoodParserConstants.GT;

    /**
     * Returns a new Token object.
     */

    public static final Token newToken(int ofKind, String tokenImage) {
        return new MyToken(ofKind, tokenImage);
    }
}
