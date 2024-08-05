/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

/**
 * Class to hold trees whose value is known when the associated function is called.
 */
public class KnownValuesTrans implements Iterable<KnownValuesTrans.KnownValue> {
    public static class KnownValue {
        public TransTreeReturn<BooleanVariable> expr;
        public final boolean value;

        public KnownValue(TransTreeReturn<BooleanVariable> expr, boolean value) {
            this.expr = expr;
            this.value = value;
        }

        @Override
        public String toString() {
            return expr.toString() + " : " + value + "\n";
        }
    }

    private static KnownValuesTrans noKnownValues = new KnownValuesTrans() {};

    private final List<KnownValue> values;

    private KnownValuesTrans() {
        values = Collections.emptyList();
    }

    private KnownValuesTrans(List<KnownValue> knownValues) {
        this.values = knownValues;
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public Iterator<KnownValue> iterator() {
        return values.iterator();
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public static KnownValuesTrans constructKnownValues() {
        return noKnownValues;
    }

    public static KnownValuesTrans constructKnownValues(List<KnownValue> knownValues) {
        return new KnownValuesTrans(knownValues);
    }

    public KnownValuesTrans applyOptimisations(ArgDesc<?>[] args,
            Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
        List<KnownValue> newKnownValues = new ArrayList<>();
        for(KnownValue v:values) {
            TransTreeReturn<BooleanVariable> expr = v.expr.applyOptimisations(args, constants, noKnownValues);
            newKnownValues.add(new KnownValue(expr, v.value));
        }
        return new KnownValuesTrans(newKnownValues);
    }
}
