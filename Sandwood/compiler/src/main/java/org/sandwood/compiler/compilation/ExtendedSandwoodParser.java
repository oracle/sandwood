/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import java.io.IOException;

import org.sandwood.compiler.exceptions.SandwoodParseException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.srcTools.sourceToSource.ParseException;
import org.sandwood.compiler.srcTools.sourceToSource.SandwoodParser;
import org.sandwood.compiler.srcTools.sourceToSource.Token;

/**
 * This class extends the generated Sandwood parser to provide better code generation
 * 
 * @author djgoodma
 *
 */
public class ExtendedSandwoodParser extends SandwoodParser {
    public ExtendedSandwoodParser(String filename) throws IOException {
        super(filename);
    }

    /** Generate ParseException. */
    @Override
    public ParseException generateParseException() {
        Token errorToken = token.next;

        // Find the start
        int beginLine, beginCol;
        if(token.beginLine == 0) {
            // There is a comment before the error token.
            if(errorToken.specialToken != null) {
                Token specialToken = errorToken.specialToken;
                beginLine = specialToken.beginLine;
                beginCol = specialToken.beginColumn;
            }
            // The error was the first token in the file.
            else {
                beginLine = errorToken.beginLine;
                beginCol = errorToken.beginColumn;
            }
        }
        // There is a regular token before this token.
        else {
            beginLine = token.beginLine;
            beginCol = token.beginColumn;
        }

        // Find the end
        int endLine = errorToken.endLine;
        int endCol = errorToken.endColumn;

        Location location = new Location(beginLine, beginCol, endLine, endCol);
        String metToken = (errorToken.kind == 0) ? tokenImage[0] : errorToken.image;
        String message = "Parse error: Unexpected token \"" + metToken + "\" encountered on line "
                + errorToken.beginLine + " column " + errorToken.beginColumn;

        return new SandwoodParseException(location, message);
    }
}
