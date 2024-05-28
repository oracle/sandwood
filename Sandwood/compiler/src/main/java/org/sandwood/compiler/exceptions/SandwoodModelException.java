/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.srcTools.sourceToSource.Token;

public class SandwoodModelException extends SandwoodException {

    private static final long serialVersionUID = 8306336037367882879L;
    public final Location loc;

    public SandwoodModelException(String message) {
        super(message);
        loc = null;
    }

    public SandwoodModelException(String message, Variable<?>... vs) {
        super(message);
        if(vs.length==1)
            loc = vs[0].getLocation();
        else {
            int startCol = Integer.MAX_VALUE;
            int startLine = Integer.MAX_VALUE;
            int endCol = -1;
            int endLine = -1;
            for(Variable<?> v:vs) {
                Location l = v.getLocation();
                if(l.startLine < startLine) {
                    startLine = l.startLine;
                    startCol = l.startCol;
                } else if(l.startLine == startLine && l.startCol < startCol)
                    startCol = l.startCol;

                if(l.endLine > endLine) {
                    endLine = l.endLine;
                    endCol = l.endCol;
                } else if(l.endLine == endLine && l.endCol > endCol)
                    endCol = l.endCol;
            }
            loc = new Location(startLine, startCol, endLine, endCol);
        }
    }

    public SandwoodModelException(String message, DataflowTask<?>... ts) {
        super(message);
        if(ts.length == 1)
            loc = ts[0].getLocation();
        else {
            int startCol = Integer.MAX_VALUE;
            int startLine = Integer.MAX_VALUE;
            int endCol = -1;
            int endLine = -1;
            for(DataflowTask<?> t:ts) {
                Location l = t.getLocation();
                if(l.startLine < startLine) {
                    startLine = l.startLine;
                    startCol = l.startCol;
                } else if(l.startLine == startLine && l.startCol < startCol)
                    startCol = l.startCol;

                if(l.endLine > endLine) {
                    endLine = l.endLine;
                    endCol = l.endCol;
                } else if(l.endLine == endLine && l.endCol > endCol)
                    endCol = l.endCol;
            }
            loc = new Location(startLine, startCol, endLine, endCol);
        }
    }

    public SandwoodModelException(String message, Token t) {
        super(message);
        loc = new Location(t.beginLine, t.beginColumn, t.endLine, t.endColumn);
    }

    public SandwoodModelException(String message, Location location) {
        super(message);
        loc = location;
    }

    @Override
    public String toString() {
        return "Message: " + getMessage() + "\nLocation: " + loc;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loc == null) ? 0 : loc.hashCode());
        String message = this.getMessage();
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        SandwoodModelException other = (SandwoodModelException) obj;
        if(loc == null) {
            if(other.loc != null)
                return false;
        } else if(!loc.equals(other.loc))
            return false;
        return getMessage().equals(other.getMessage());
    }
}
