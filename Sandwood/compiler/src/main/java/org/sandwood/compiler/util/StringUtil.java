/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.util;

public class StringUtil {
    public static String escapeSpecialCharacters(String input) {
        input = input.replace("\\", "\\\\");
        input = input.replace("\b", "\\b");
        input = input.replace("\"", "\\\"");
        input = input.replace("\n", "\\n");
        input = input.replace("\t", "\\t");
        input = input.replace("\f", "\\f");
        input = input.replace("\r", "\\r");
        return input;
    }

    public static String escapeSpecialCharacters(char input) {
        switch(input) {
            case '\\':
                return "\\\\";
            case '\b':
                return "\\b";
            case '\"':
                return "\\\"";
            case '\n':
                return "\\n";
            case '\t':
                return "\\t";
            case '\f':
                return "\\f";
            case '\r':
                return "\\r";
            case ' ':
                return "[space]";
            default:
                return String.valueOf(input);
        }
    }

    public static String normalizeNewLines(String s) {
        return s.replaceAll("\r\n", "\n");
    }
}
