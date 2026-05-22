/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph;

import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Cos;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Exp;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Log;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Max;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Min;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Pow;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sin;
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

    public static <A extends NumberVariable<A>> A max(A a, A b) {
        return max(a, b, null);
    }

    public static <A extends NumberVariable<A>> A max(A a, A b, Location location) {
        return Max.max(a, b, location);
    }

    public static <A extends NumberVariable<A>> A min(A a, A b) {
        return min(a, b, null);
    }

    public static <A extends NumberVariable<A>> A min(A a, A b, Location location) {
        return Min.min(a, b, location);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sqrt(A a) {
        return sqrt(a, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sqrt(A a, Location location) {
        return Sqrt.sqrt(a, location);
    }

    public static DoubleVariable pow(DoubleVariable a, DoubleVariable b) {
        return pow(a, b, null);
    }

    public static DoubleVariable pow(DoubleVariable a, DoubleVariable b, Location location) {
        return Pow.pow(a, b, location);
    }

    public static DoubleVariable pow(DoubleVariable a, IntVariable b) {
        return pow(a, b, null);
    }

    public static DoubleVariable pow(DoubleVariable a, IntVariable b, Location location) {
        return Pow.pow(a, b, location);
    }

    public static DoubleVariable pow(IntVariable a, DoubleVariable b) {
        return pow(a, b, null);
    }

    public static DoubleVariable pow(IntVariable a, DoubleVariable b, Location location) {
        return Pow.pow(a, b, location);
    }

    public static IntVariable pow(IntVariable a, IntVariable b) {
        return pow(a, b, null);
    }

    public static IntVariable pow(IntVariable a, IntVariable b, Location location) {
        return Pow.pow(a, b, location);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sin(A a) {
        return sin(a, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable sin(A a, Location location) {
        return Sin.sin(a, location);
    }

    public static <A extends NumberVariable<A>> DoubleVariable cos(A a) {
        return cos(a, null);
    }

    public static <A extends NumberVariable<A>> DoubleVariable cos(A a, Location location) {
        return Cos.cos(a, location);
    }
}
