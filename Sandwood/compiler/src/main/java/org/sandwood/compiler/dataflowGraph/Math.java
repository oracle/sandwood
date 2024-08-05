/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Exp;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Log;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class Math {
    
    /**
     * Private constructor to prevent this object being instantiated.
     */
    private Math() {}
    
    public static <A extends NumberVariable<A>> DoubleVariable exp(A v) {
        return exp(v, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable exp(A v, Location location) {
        return Exp.exp(v, location);
    }

    public static <A extends NumberVariable<A>> DoubleVariable log(A v) {
        return log(v, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable log(A v, Location location) {
        return Log.log(v, location);
    }
}
