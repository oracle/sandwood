/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.transformations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sandwood.compiler.trees.transformationTree.TransTree.addII;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;
import org.sandwood.compiler.util.StringUtil;

class EmptyForLoopTest {

    private static final int noTests = 3;
    private static final TransTreeVoid[] trees = new TransTreeVoid[noTests];
    private static final String[] before = new String[noTests];
    private static final String[] after = new String[noTests];

    static {
        VariableDescription<IntVariable> i = new VariableDescription<>("i", VariableType.IntVariable, false);
        VariableDescription<IntVariable> j = new VariableDescription<>("j", VariableType.IntVariable, false);
        VariableDescription<IntVariable> k = new VariableDescription<>("k", VariableType.IntVariable, false);
        VariableDescription<IntVariable> l = new VariableDescription<>("l", VariableType.IntVariable, false);
        VariableDescription<IntVariable> m = new VariableDescription<>("m", VariableType.IntVariable, false);
        VariableDescription<IntVariable> x = new VariableDescription<>("x", VariableType.IntVariable, false);

        int test = 0;
        trees[test] = forStmt(forStmt(forStmt(
                forStmt(store(x, addII(load(i), load(k)), Tree.NoComment), constant(0), constant(9), constant(1), l,
                        false, true, Tree.NoComment),
                constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0), constant(9),
                constant(1), j, false, true, Tree.NoComment), constant(0), constant(9), constant(1), i, false, true,
                Tree.NoComment);
        before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                + "\tfor(int j = 0; j < 10; j += 1) {\n"
                + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                + "\t\t\tfor(int l = 0; l < 10; l += 1)\n"
                + "\t\t\t\tx = (i + k);\n"
                + "\t\t}\n"
                + "\t}\n"
                + "}\n";
        after[test++] = "for(int i = 0; i < 10; i += 1) {\n"
                + "\tfor(int k = 0; k < 10; k += 1)\n"
                + "\t\tx = (i + k);\n"
                + "}\n";

        List<TransTreeVoid> ts = new ArrayList<>();
        ts.add(initializeVariable(m, constant(5), Tree.NoComment));
        ts.add(store(x, addII(load(m), load(k)), Tree.NoComment));
        TransTreeVoid body = sequential(ts, Tree.NoComment);

        trees[test] = forStmt(
                forStmt(forStmt(forStmt(body, constant(0), constant(9), constant(1), l, false, true, Tree.NoComment),
                        constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0),
                        constant(9), constant(1), j, false, true, Tree.NoComment),
                constant(0), constant(9), constant(1), i, false, true, Tree.NoComment);
        before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                + "\tfor(int j = 0; j < 10; j += 1) {\n"
                + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                + "\t\t\tfor(int l = 0; l < 10; l += 1) {\n"
                + "\t\t\t\tint m = 5;\n"
                + "\t\t\t\tx = (m + k);\n"
                + "\t\t\t}\n"
                + "\t\t}\n"
                + "\t}\n"
                + "}\n";
        after[test++] = "for(int k = 0; k < 10; k += 1)\n"
                + "\t// Substituted \"m\" with its value \"5\".\n"
                + "\tx = (k + 5);\n";

        ts = new ArrayList<>();
        ts.add(store(x, addII(load(i), load(k)), Tree.NoComment));
        ts.add(initializeVariable(m, constant(5), Tree.NoComment));
        body = sequential(ts, Tree.NoComment);
        trees[test] = forStmt(
                forStmt(forStmt(forStmt(body, constant(0), constant(9), constant(1), l, false, true, Tree.NoComment),
                        constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0),
                        constant(9), constant(1), j, false, true, Tree.NoComment),
                constant(0), constant(9), constant(1), i, false, true, Tree.NoComment);
        before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                + "\tfor(int j = 0; j < 10; j += 1) {\n"
                + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                + "\t\t\tfor(int l = 0; l < 10; l += 1) {\n"
                + "\t\t\t\tx = (i + k);\n"
                + "\t\t\t\tint m = 5;\n"
                + "\t\t\t}\n"
                + "\t\t}\n"
                + "\t}\n"
                + "}\n";
        after[test++] = "for(int i = 0; i < 10; i += 1) {\n"
                + "\tfor(int k = 0; k < 10; k += 1)\n"
                + "\t\tx = (i + k);\n" + "}\n";

        assertEquals(test, noTests, "Not all tests are initialized");
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
        assertEquals(before[i], StringUtil.normalizeNewLines(trees[i].toString()));
        assertEquals(after[i],
                StringUtil.normalizeNewLines(trees[i]
                        .applyOptimisations(new ArgDesc[0], new HashMap<>(), KnownValuesTrans.constructKnownValues())
                        .toString()));
    }

}
