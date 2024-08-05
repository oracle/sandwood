/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.graphProperties.findAllRandomVariables;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.tests.util.CompilerState;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;

public abstract class ModelTestSkeleton {

    private final Set<RandomVariable<?, ?>> randoms = new HashSet<>();
    private final Set<Variable<?>> variables = new HashSet<>();

    protected abstract void buildGraph();

    @BeforeEach
    void resetStaticValues() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        CompilerState.reset();
    }

    protected void addRandomVariable(RandomVariable<?, ?> v) {
        randoms.add(v);
    }

    protected void addVariable(Variable<?> v) {
        variables.add(v);
    }

    /**
     * A test method to check that the code generated from a simple has not changed.
     */
    @Test
    void testFindAllRandomVariables() {
        try {
            buildGraph();
            for(Variable<?> v:variables) {
                Variable<?>[] vs = { v };
                Traces t = TracesImplementation.getTraces(vs);
                List<RandomVariable<?, ?>> results = t.getAllRandomVariables();
                boolean sameSets = randoms.containsAll(results) && results.size() == randoms.size();
                if(sameSets)
                    assertTrue(true);
                else {
                    System.out.println("Error with " + v.getAlias());
                    System.out.println("\nExpected contains:");
                    for(Variable<?> e:randoms)
                        System.out.println(e.getAlias() + ":" + e.getType());

                    System.out.println("\nResults contains:");
                    for(Variable<?> r:results)
                        System.out.println(r.getAlias() + ":" + r.getType());
                    assertTrue(false);
                }
            }
        } catch(SandwoodException e) {
            fail("This code should not throw exceptions:" + e.getMessage());
            e.printStackTrace();
        }
    }
}