/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.transformations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sandwood.compiler.trees.transformationTree.TransTree.addDD;
import static org.sandwood.compiler.trees.transformationTree.TransTree.addDI;
import static org.sandwood.compiler.trees.transformationTree.TransTree.addII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.and;
import static org.sandwood.compiler.trees.transformationTree.TransTree.castToDouble;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.divideDD;
import static org.sandwood.compiler.trees.transformationTree.TransTree.divideID;
import static org.sandwood.compiler.trees.transformationTree.TransTree.eq;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThan;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThanEqual;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.max;
import static org.sandwood.compiler.trees.transformationTree.TransTree.min;
import static org.sandwood.compiler.trees.transformationTree.TransTree.multiplyDD;
import static org.sandwood.compiler.trees.transformationTree.TransTree.multiplyDI;
import static org.sandwood.compiler.trees.transformationTree.TransTree.remainderII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.subtractDD;
import static org.sandwood.compiler.trees.transformationTree.TransTree.subtractDI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;

class ConstantsTest {

    private static final int noTests;
    private static final TransTreeReturn<?>[] trees;
    private static final String[] before;
    private static final String[] after;

    static {
        List<TransTreeReturn<?>> treeList = new ArrayList<>();
        List<String> beforeList = new ArrayList<>();
        List<String> afterList = new ArrayList<>();

        treeList.add(addDD(constant(0.0), constant(1.5)));
        beforeList.add("(0.0 + 1.5)");
        afterList.add("1.5");

        treeList.add(addDD(constant(0.0), load(new VariableDescription<>("i", VariableType.DoubleVariable, false))));
        beforeList.add("(0.0 + i)");
        afterList.add("i");

        treeList.add(addDD(load(new VariableDescription<>("i", VariableType.DoubleVariable, false)), constant(0.0)));
        beforeList.add("(i + 0.0)");
        afterList.add("i");

        treeList.add(addDD(constant(0.0), castToDouble(constant(1))));
        beforeList.add("(0.0 + 1.0)");
        afterList.add("1.0");

        treeList.add(addDD(castToDouble(constant(1)), constant(1.5)));
        beforeList.add("(1.0 + 1.5)");
        afterList.add("2.5");

        treeList.add(addDD(constant(1.0), addDI(constant(0.0), constant(1))));
        beforeList.add("(1.0 + (0.0 + 1))");
        afterList.add("2.0");

        treeList.add(addDI(constant(0.0), constant(1)));
        beforeList.add("(0.0 + 1)");
        afterList.add("1.0");

        treeList.add(multiplyDD(constant(1.0), constant(1.5)));
        beforeList.add("(1.0 * 1.5)");
        afterList.add("1.5");

        treeList.add(
                multiplyDD(constant(1.0), load(new VariableDescription<>("i", VariableType.DoubleVariable, false))));
        beforeList.add("(1.0 * i)");
        afterList.add("i");

        treeList.add(
                multiplyDD(load(new VariableDescription<>("i", VariableType.DoubleVariable, false)), constant(1.0)));
        beforeList.add("(i * 1.0)");
        afterList.add("i");

        treeList.add(multiplyDD(constant(1.0), castToDouble(constant(2))));
        beforeList.add("(1.0 * 2.0)");
        afterList.add("2.0");

        treeList.add(multiplyDD(castToDouble(constant(2)), constant(1.5)));
        beforeList.add("(2.0 * 1.5)");
        afterList.add("3.0");

        treeList.add(multiplyDD(constant(2.0), multiplyDI(constant(1.0), constant(2))));
        beforeList.add("(2.0 * (1.0 * 2))");
        afterList.add("4.0");

        treeList.add(multiplyDI(constant(1.0), constant(2)));
        beforeList.add("(1.0 * 2)");
        afterList.add("2.0");

        treeList.add(subtractDD(constant(0.0), constant(1.5)));
        beforeList.add("(0.0 - 1.5)");
        afterList.add("-1.5");

        treeList.add(
                subtractDD(constant(0.0), load(new VariableDescription<>("i", VariableType.DoubleVariable, false))));
        beforeList.add("(0.0 - i)");
        afterList.add("(-i)");

        treeList.add(
                subtractDD(load(new VariableDescription<>("i", VariableType.DoubleVariable, false)), constant(0.0)));
        beforeList.add("(i - 0.0)");
        afterList.add("i");

        treeList.add(subtractDD(constant(0.0), castToDouble(constant(1))));
        beforeList.add("(0.0 - 1.0)");
        afterList.add("-1.0");

        treeList.add(subtractDD(castToDouble(constant(1)), constant(1.5)));
        beforeList.add("(1.0 - 1.5)");
        afterList.add("-0.5");

        treeList.add(subtractDD(constant(1.0), addDI(constant(0.0), constant(1))));
        beforeList.add("(1.0 - (0.0 + 1))");
        afterList.add("0.0");

        treeList.add(subtractDI(constant(0.0), constant(1)));
        beforeList.add("(0.0 - 1)");
        afterList.add("-1.0");

        treeList.add(divideDD(constant(1.0), constant(2.0)));
        beforeList.add("(1.0 / 2.0)");
        afterList.add("0.5");

        treeList.add(divideDD(constant(1.0), load(new VariableDescription<>("i", VariableType.DoubleVariable, false))));
        beforeList.add("(1.0 / i)");
        afterList.add("(1.0 / i)");

        treeList.add(divideDD(load(new VariableDescription<>("i", VariableType.DoubleVariable, false)), constant(1.0)));
        beforeList.add("(i / 1.0)");
        afterList.add("i");

        treeList.add(divideDD(constant(1.0), castToDouble(constant(2))));
        beforeList.add("(1.0 / 2.0)");
        afterList.add("0.5");

        treeList.add(divideDD(castToDouble(constant(2)), constant(1.0)));
        beforeList.add("(2.0 / 1.0)");
        afterList.add("2.0");

        treeList.add(divideDD(constant(2.0), multiplyDI(constant(1.0), constant(2))));
        beforeList.add("(2.0 / (1.0 * 2))");
        afterList.add("1.0");

        treeList.add(divideID(constant(2), constant(1.0)));
        beforeList.add("(2 / 1.0)");
        afterList.add("2.0");

        treeList.add(and(constant(true), constant(true)));
        beforeList.add("true");
        afterList.add("true");

        treeList.add(and(constant(true), constant(false)));
        beforeList.add("false");
        afterList.add("false");

        treeList.add(and(constant(false), constant(true)));
        beforeList.add("false");
        afterList.add("false");

        treeList.add(and(constant(false), constant(false)));
        beforeList.add("false");
        afterList.add("false");

        treeList.add(and(constant(true), load(new VariableDescription<>("i", VariableType.BooleanVariable, false))));
        beforeList.add("i");
        afterList.add("i");

        treeList.add(and(load(new VariableDescription<>("i", VariableType.BooleanVariable, false)), constant(true)));
        beforeList.add("i");
        afterList.add("i");

        treeList.add(lessThan(load(new VariableDescription<>("i", VariableType.IntVariable, false)), constant(2)));
        beforeList.add("(i < 2)");
        afterList.add("(i < 2)");

        treeList.add(lessThan(constant(2), load(new VariableDescription<>("i", VariableType.IntVariable, false))));
        beforeList.add("(2 < i)");
        afterList.add("(2 < i)");

        treeList.add(lessThan(constant(1), constant(2)));
        beforeList.add("(1 < 2)");
        afterList.add("true");

        treeList.add(lessThan(constant(2), constant(1)));
        beforeList.add("(2 < 1)");
        afterList.add("false");

        treeList.add(lessThanEqual(load(new VariableDescription<>("i", VariableType.IntVariable, false)), constant(2)));
        beforeList.add("(i <= 2)");
        afterList.add("(i <= 2)");

        treeList.add(lessThanEqual(constant(2), load(new VariableDescription<>("i", VariableType.IntVariable, false))));
        beforeList.add("(2 <= i)");
        afterList.add("(2 <= i)");

        treeList.add(lessThanEqual(constant(1), constant(2)));
        beforeList.add("(1 <= 2)");
        afterList.add("true");

        treeList.add(lessThanEqual(constant(2), constant(1)));
        beforeList.add("(2 <= 1)");
        afterList.add("false");

        treeList.add(eq(load(new VariableDescription<>("i", VariableType.IntVariable, false)), constant(2)));
        beforeList.add("(i == 2)");
        afterList.add("(i == 2)");

        treeList.add(eq(constant(2), load(new VariableDescription<>("i", VariableType.IntVariable, false))));
        beforeList.add("(2 == i)");
        afterList.add("(2 == i)");

        treeList.add(eq(constant(1), constant(1)));
        beforeList.add("(1 == 1)");
        afterList.add("true");

        treeList.add(eq(constant(2), constant(1)));
        beforeList.add("(2 == 1)");
        afterList.add("false");

        treeList.add(remainderII(load(new VariableDescription<>("i", VariableType.IntVariable, false)), constant(2)));
        beforeList.add("(i % 2)");
        afterList.add("(i % 2)");

        treeList.add(remainderII(load(new VariableDescription<>("i", VariableType.IntVariable, false)), constant(1)));
        beforeList.add("(i % 1)");
        afterList.add("0");

        treeList.add(remainderII(constant(1), load(new VariableDescription<>("i", VariableType.IntVariable, false))));
        beforeList.add("(1 % i)");
        afterList.add("(1 % i)");

        treeList.add(remainderII(constant(2), constant(1)));
        beforeList.add("(2 % 1)");
        afterList.add("0");

        treeList.add(remainderII(constant(1), constant(4)));
        beforeList.add("(1 % 4)");
        afterList.add("1");

        treeList.add(max(constant(1), constant(4)));
        beforeList.add("Math.max(1, 4)");
        afterList.add("4");

        treeList.add(max(constant(4), constant(1)));
        beforeList.add("Math.max(4, 1)");
        afterList.add("4");

        treeList.add(max(
                addII(load(new VariableDescription<>("i", VariableType.IntVariable, false)),
                        load(new VariableDescription<>("j", VariableType.IntVariable, false))),
                addII(load(new VariableDescription<>("j", VariableType.IntVariable, false)),
                        load(new VariableDescription<>("i", VariableType.IntVariable, false)))));
        beforeList.add("Math.max((i + j), (j + i))");
        afterList.add("(i + j)");

        treeList.add(min(constant(1), constant(4)));
        beforeList.add("Math.min(1, 4)");
        afterList.add("1");

        treeList.add(min(constant(4), constant(1)));
        beforeList.add("Math.min(4, 1)");
        afterList.add("1");

        treeList.add(min(
                addII(load(new VariableDescription<>("i", VariableType.IntVariable, false)),
                        load(new VariableDescription<>("j", VariableType.IntVariable, false))),
                addII(load(new VariableDescription<>("j", VariableType.IntVariable, false)),
                        load(new VariableDescription<>("i", VariableType.IntVariable, false)))));
        beforeList.add("Math.min((i + j), (j + i))");
        afterList.add("(i + j)");

        noTests = treeList.size();

        trees = treeList.toArray(new TransTreeReturn<?>[noTests]);
        before = beforeList.toArray(new String[noTests]);
        after = afterList.toArray(new String[noTests]);
    }

    static Stream<Integer> getIndex() {
        Integer[] out = new Integer[noTests];
        for(int i = 0; i < noTests; i++)
            out[i] = i;
        return Stream.of(out);
    }

    @ParameterizedTest
    @MethodSource("getIndex")
    @DisplayName("Test partial evaluation of constants transformation.")
    void test(int i) {
        assertEquals(before[i], trees[i].toString());
        assertEquals(after[i],
                trees[i].applyOptimisations(new ArgDesc[0], new HashMap<>(), KnownValuesTrans.constructKnownValues())
                        .toString());
    }

}
