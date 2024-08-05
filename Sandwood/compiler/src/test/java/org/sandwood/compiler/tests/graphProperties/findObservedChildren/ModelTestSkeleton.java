/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.graphProperties.findObservedChildren;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;

public abstract class ModelTestSkeleton {

    protected abstract Map<RandomVariable<?, ?>, Set<Variable<?>>> buildGraph();

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    /**
     * A test method to check that the code generated from a simple has not changed.
     */
    @Test
    void testGetObservedChildren() {
        try {
            boolean allTestsPassed = true;
            Map<RandomVariable<?, ?>, Set<Variable<?>>> output = buildGraph();
            for(RandomVariable<?, ?> v:output.keySet()) {
                Variable<?>[] vs = { v };
                Traces t = TracesImplementation.getTraces(vs);
                Set<Variable<?>> expected = output.get(v);

                Set<Variable<?>> results = new HashSet<>();
                for(TraceHandle h:t.getObservedTraces(v))
                    results.add(h.peek().task.getOutput());

                boolean sameSets = expected.containsAll(results) && results.size() == expected.size();
                if(!sameSets) {
                    allTestsPassed = false;
                    System.out.println("Error with " + v.getAlias() + ":" + v.getType());
                    System.out.println("\nExpected contains:");
                    for(Variable<?> e:expected)
                        System.out.println(e.getAlias() + ":" + e.getType());

                    System.out.println("\nResults contains:");
                    for(Variable<?> r:results)
                        System.out.println(r.getAlias() + ":" + r.getType());
                    System.out.println("\n=================\n");
                }
            }
            assertTrue(allTestsPassed);
        } catch(SandwoodException e) {
            fail("This code should not throw exceptions:" + e.getMessage());
            e.printStackTrace();
        }
    }
}