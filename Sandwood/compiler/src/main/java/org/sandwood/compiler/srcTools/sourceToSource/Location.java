/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.srcTools.sourceToSource;

public class Location implements Comparable<Location> {
    public final int startLine, startCol, endLine, endCol;

    public Location(int startLine, int startCol, int endLine, int endCol) {
        this.startLine = startLine;
        this.startCol = startCol;
        this.endLine = endLine;
        this.endCol = endCol;
    }

    @Override
    public String toString() {
        if(startLine == endLine)
            return "Line " + startLine + " columns " + startCol + " to " + endCol;
        else
            return "Line " + startLine + " column " + startCol + " to line " + endLine + " column " + endCol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + endCol;
        result = prime * result + endLine;
        result = prime * result + startCol;
        result = prime * result + startLine;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        Location other = (Location) obj;
        return endCol != other.endCol && endLine != other.endLine && startCol != other.startCol
                && startLine != other.startLine;
    }

    @Override
    public int compareTo(Location o) {
        int i = startLine - o.startLine;
        if(i != 0)
            return i;
        i = startCol - o.startCol;
        if(i != 0)
            return i;
        i = endLine - o.endLine;
        if(i != 0)
            return i;
        return endCol - o.endCol;
    }
}
