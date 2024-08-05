/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;

/**
 * Class to hold trees whose value is known when the associated function is called.
 */
public abstract class KnownValuesIR implements Iterable<KnownValuesIR.KnownValue> {
    public static class KnownValue {
        public final IRTreeReturn<BooleanVariable> expr;
        public final boolean value;

        public KnownValue(IRTreeReturn<BooleanVariable> expr, boolean value) {
            this.expr = expr;
            this.value = value;
        }

        @Override
        public String toString() {
            return expr.toString() + " : " + value + "\n";
        }
    }

    private static KnownValuesIR noKnownValues = new KnownValuesIR() {
        @Override
        public KnownValuesTrans toTransformationTree() {
            return KnownValuesTrans.constructKnownValues();
        }
    };

    private final List<KnownValue> values;

    protected KnownValuesIR() {
        values = Collections.emptyList();
    }

    protected KnownValuesIR(List<KnownValue> values) {
        this.values = values;
    }

    @Override
    public Iterator<KnownValue> iterator() {
        return values.iterator();
    }

    public abstract KnownValuesTrans toTransformationTree();

    @Override
    public String toString() {
        return values.toString();
    }

    public static KnownValuesIR constructKnownValues() {
        return noKnownValues;
    }

    public static KnownValuesIR constructKnownValues(List<KnownValue> values) {
        if(values.isEmpty())
            return noKnownValues;
        else {
            return new KnownValuesIR(values) {
                @Override
                public KnownValuesTrans toTransformationTree() {
                    List<KnownValuesTrans.KnownValue> transKnownValues = new ArrayList<>();
                    for(KnownValue v:values) {
                        TransTreeReturn<BooleanVariable> expr = v.expr.toTransformationTree();
                        transKnownValues.add(new KnownValuesTrans.KnownValue(expr, v.value));
                    }
                    return KnownValuesTrans.constructKnownValues(transKnownValues);
                }
            };
        }
    }
}
