/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.IsNaN;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class Number {
    
    /**
     * Private constructor to prevent this object being instantiated.
     */
    private Number() {}
    
    public static BooleanVariable isNaN(DoubleVariable v) {
        return isNaN(v, null);
    }

    public static BooleanVariable isNaN(DoubleVariable v, Location location) {
        return IsNaN.isNaN(v, location);
    }
}
