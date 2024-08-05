/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.sandwood.compiler.dataflowGraph.Id;
import org.sandwood.compiler.dataflowGraph.variables.VariableImplementation;
import org.sandwood.compiler.traces.guards.ScopeConstructor;

public class CompilerState {
    public static void reset() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        reset(Id.class);
        reset(VariableImplementation.class);
        ScopeConstructor.clearId();
    }

    private static void reset(Class<?> c) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Method m = c.getDeclaredMethod("reset");
        m.setAccessible(true);
        m.invoke(c);
    }
}
