/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.exceptions;

import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.srcTools.sourceToSource.ParseException;

public class SandwoodParseException extends ParseException {
    private static final long serialVersionUID = -1233596028194316061L;

    public final Location loc;

    public SandwoodParseException(Location loc, String msg) {
        super(msg);
        this.loc = loc;
    }
}
