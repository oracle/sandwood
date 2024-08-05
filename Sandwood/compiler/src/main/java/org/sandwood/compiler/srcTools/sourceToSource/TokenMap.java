/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

import java.util.List;

/**
 * A class mapping javaCC tokens to the string output that they generate. This will be used to track the line locations
 * so errors can be reported correctly.
 *
 */
public class TokenMap {
    private final String output;
    private final Token[] tokens;

    private TokenMap(String output, Token... tokens) {
        this.output = output;
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return output;
    }

    public static TokenMap tokenMap(String output, Token... tokens) {
        return new TokenMap(output, tokens);
    }

    public static TokenMap tokenMap(IdentiferDesc desc) {
        return new TokenMap(desc.name, desc.source);
    }

    public static String tokensToString(List<TokenMap> tokens) {
        StringBuilder sb = new StringBuilder();
        for(TokenMap t:tokens)
            sb.append(t.toString());
        return sb.toString();
    }

    private String padding(int i) {
        StringBuilder sb = new StringBuilder();
        while(i > 0) {
            sb.append(" ");
            i--;
        }
        return sb.toString();
    }

    public static void printMapping(List<TokenMap> tokens) {
        int padding = 75;
        for(TokenMap t:tokens) {
            t.printMapping(padding);
        }
    }

    private void printMapping(int padding) {
        String toPrint = output.replace("\n", "\\n");
        System.out.print(toPrint);
        System.out.print(padding((padding >= toPrint.length()) ? padding - toPrint.length() : 0));
        System.out.print("|   ");
        for(Token t:tokens)
            System.out.print(" " + t.image);
        System.out.print("\n");
    }

    public Token[] getTokens() {
        return tokens;
    }
}
