/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

import java.util.ArrayList;
import java.util.List;

public class TokenMapping {
    private static class LocationDescriptor {
        public int startLine = Integer.MAX_VALUE;
        public int startCol = Integer.MAX_VALUE;
        public int endLine = 0;
        public int endCol = 0;
        public final int apiEndPos;
        public final boolean includesEstimates;

        public LocationDescriptor(int pos, Token[] tokens) {
            apiEndPos = pos;
            if(tokens.length == 0)
                includesEstimates = true;
            else {
                includesEstimates = false;
                for(Token t:tokens) {
                    if(t.beginLine < startLine || (t.beginLine == startLine && t.beginColumn < startCol)) {
                        startLine = t.beginLine;
                        startCol = t.beginColumn;
                    }
                    if(t.endLine > endLine || (t.endLine == endLine && t.endColumn > endCol)) {
                        endLine = t.endLine;
                        endCol = t.endColumn;
                    }
                }
            }
        }

        public void checkRange() {
            // Do we need to swap?
            if(endLine < startLine || (endLine == startLine && endCol < startCol)) {
                int t = endLine;
                endLine = startLine;
                startLine = t;

                t = endCol;
                endCol = startCol;
                startCol = t;
            }
        }
    }

    private final LocationDescriptor[] locs;
    private final String[] originalParts;

    public TokenMapping(List<TokenMap> tokens, String original) {
        originalParts = split(original, "\\\\n");

        List<LocationDescriptor> l = new ArrayList<>();
        int col = 1;

        for(TokenMap t:tokens) {
            // count through all the columns for this token
            String value = t.toString();
            if(!value.equals("")) {
                col += value.length();
                l.add(new LocationDescriptor(col, t.getTokens()));
            }
        }

        locs = l.toArray(new LocationDescriptor[l.size()]);

        // Forward pass to add in inferred descriptor starts
        {
            int startLine = 1, startCol = 1;
            for(LocationDescriptor desc:locs) {
                if(desc.includesEstimates) {
                    desc.startCol = startCol;
                    desc.startLine = startLine;
                } else {
                    startCol = desc.endCol + 1;
                    startLine = desc.endLine;
                    // Ensure we have not run off the end of the line
                    while(startLine < originalParts.length && startCol >= originalParts[startLine - 1].length()) {
                        startCol = 1;
                        startLine++;
                    }
                }
            }
        }

        // Backward pass to add in inferred starts.
        {
            int endLine = originalParts.length, endCol = originalParts[endLine - 1].length();

            for(int i = locs.length - 1; i >= 0; i--) {
                LocationDescriptor desc = locs[i];
                if(desc.includesEstimates) {
                    desc.endCol = endCol;
                    desc.endLine = endLine;
                } else {
                    endCol = desc.startCol - 1;
                    endLine = desc.endLine;
                    // Ensure we have not run off the end of the line
                    while(endLine != 1 && endCol == 0) {
                        endCol = originalParts[endLine].length();
                        endLine--;
                    }
                }

                // Method to ensure that syntax placed between tokens always has
                // the correct orientation of the range.
                desc.checkRange();
            }
        }
    }

    // Split method as String split method trims out empty strings;
    private String[] split(String string, String pattern) {
        List<String> parts = new ArrayList<>();
        while(string.startsWith(pattern)) {
            parts.add("");
            string = string.substring(1);
        }

        int length = string.length();

        String[] split = string.split(pattern);
        for(String s:split) {
            length -= s.length();
            parts.add(s);
        }

        length /= pattern.length();
        for(int i = 0; i < length; i++)
            parts.add("");

        return parts.toArray(new String[parts.size()]);
    }

    public Location getLocation(long startPos, long endPos) {
        // Find the first segment
        int i = 0;
        while(i < locs.length && locs[i].apiEndPos <= startPos)
            i++;
        int start = i;

        // Find the last segment
        int end = i;
        while(end < locs.length && locs[end].apiEndPos < endPos)
            end++;

        // Gather all the locations
        List<LocationDescriptor> descs = new ArrayList<>();

        while(start <= end)
            descs.add(locs[start++]);

        // Construct the location
        int startLine = Integer.MAX_VALUE, startCol = Integer.MAX_VALUE, endLine = 0, endCol = 0;

        for(LocationDescriptor d:descs) {
            if(d.startLine < startLine || (d.startLine == startLine && d.startCol < startCol)) {
                startLine = d.startLine;
                startCol = d.startCol;
            }
            if(d.endLine > endLine || (d.endLine == endLine && d.endCol > endCol)) {
                endLine = d.endLine;
                endCol = d.endCol;
            }
        }

        return new Location(startLine, startCol, endLine, endCol);
    }

    // SubStrings removed due to inaccurate error location reporting.
    public String getSource(Location loc) {
        if(loc.startLine == loc.endLine)
            return originalParts[loc.startLine - 1];// .substring(loc.startCol - 1, loc.endCol);

        StringBuilder sb = new StringBuilder();
        int i = loc.startLine;
        sb.append(originalParts[i - 1]);// .substring(loc.startCol - 1));
        i++;
        while(i < loc.endLine) {
            sb.append("\n" + originalParts[i - 1]);
            i++;
        }

        sb.append("\n" + originalParts[i - 1]);// .substring(0,loc.endCol));
        return sb.toString();
    }
}
