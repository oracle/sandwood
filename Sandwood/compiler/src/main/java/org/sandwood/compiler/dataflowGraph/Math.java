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
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Max;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Min;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sqrt;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
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

    public static DoubleVariable max(DoubleVariable a, DoubleVariable b, Location location) {
        return Max.max(a, b, location);
    }

    public static IntVariable max(IntVariable a, IntVariable b, Location location) {
        return Max.max(a, b, location);
    }

    public static DoubleVariable min(DoubleVariable a, DoubleVariable b, Location location) {
        return Min.min(a, b, location);
    }

    public static IntVariable min(IntVariable a, IntVariable b, Location location) {
        return Min.min(a, b, location);
    }

    public static DoubleVariable sqrt(DoubleVariable d, Location location) {
        return Sqrt.sqrt(d, location);
    }

    public static DoubleVariable sqrt(IntVariable d, Location location) {
        return Sqrt.sqrt(d, location);
    }
}
