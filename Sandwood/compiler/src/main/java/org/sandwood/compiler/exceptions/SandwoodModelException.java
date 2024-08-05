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

    public SandwoodModelException(String message, Variable<?> v) {
        super(message);
        loc = v.getLocation();
    }

    public SandwoodModelException(String message, DataflowTask<?> t) {
        super(message);
        loc = t.getLocation();
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
