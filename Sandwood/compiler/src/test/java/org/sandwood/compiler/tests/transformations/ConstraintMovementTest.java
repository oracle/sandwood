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
import static org.sandwood.compiler.trees.transformationTree.TransTree.and;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.eq;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransBinOp;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;
import org.sandwood.compiler.util.StringUtil;

class ConstraintMovementTest {

    private static final int noTests = 4;
    private static final TransTreeVoid[] trees = new TransTreeVoid[noTests];
    private static final String[] before = new String[noTests];
    private static final String[] after = new String[noTests];

    static {
        int test = 0;

        VariableDescription<IntVariable> const1 = new VariableDescription<>("constant1", VariableType.IntVariable,
                false);
        VariableDescription<IntVariable> const2 = new VariableDescription<>("constant2", VariableType.IntVariable,
                false);
        VariableDescription<IntVariable> const3 = new VariableDescription<>("constant3", VariableType.IntVariable,
                false);
        VariableDescription<IntVariable> i = new VariableDescription<>("i", VariableType.IntVariable, false);
        VariableDescription<IntVariable> j = new VariableDescription<>("j", VariableType.IntVariable, false);
        VariableDescription<IntVariable> k = new VariableDescription<>("k", VariableType.IntVariable, false);
        VariableDescription<IntVariable> l = new VariableDescription<>("l", VariableType.IntVariable, false);
        VariableDescription<IntVariable> m = new VariableDescription<>("m", VariableType.IntVariable, false);
        VariableDescription<IntVariable> x = new VariableDescription<>("x", VariableType.IntVariable, false);

        {
            List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();

            TransBinOp<IntVariable, IntVariable, BooleanVariable> c = eq(load(const1), load(j));
            constraints.add(c);

            c = eq(load(const2), addII(load(i), load(k)));
            constraints.add(c);

            trees[test] = forStmt(
                    forStmt(forStmt(
                            forStmt(forStmt(
                                    ifElse(and(constraints), store(x, addII(load(i), load(k)), Tree.NoComment),
                                            Tree.NoComment, Collections.emptySet()),
                                    constant(0), constant(9), constant(1), m, false, true, Tree.NoComment), constant(0),
                                    constant(9), constant(1), l, false, true, Tree.NoComment),
                            constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0),
                            constant(9), constant(1), j, false, true, Tree.NoComment),
                    constant(0), constant(9), constant(1), i, false, true, Tree.NoComment);
            before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                    + "\tfor(int j = 0; j < 10; j += 1) {\n"
                    + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                    + "\t\t\tfor(int l = 0; l < 10; l += 1) {\n"
                    + "\t\t\t\tfor(int m = 0; m < 10; m += 1) {\n"
                    + "\t\t\t\t\tif(((constant1 == j) && (constant2 == (i + k))))\n"
                    + "\t\t\t\t\t\tx = (i + k);\n"
                    + "\t\t\t\t}\n"
                    + "\t\t\t}\n"
                    + "\t\t}\n"
                    + "\t}\n"
                    + "}\n";
            after[test++] = "\n" + "// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "if(((0 <= constant1) && (constant1 < 10))) {\n"
                    + "\tfor(int i = 0; i < 10; i += 1) {\n"
                    + "\t\tint k = (constant2 - i);\n"
                    + "\t\t\n"
                    + "\t\t// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "\t\tif(((0 <= k) && (k < 10)))\n"
                    + "\t\t\tx = (i + k);\n"
                    + "\t}\n"
                    + "}\n";
        }

        {
            List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();

            TransBinOp<IntVariable, IntVariable, BooleanVariable> c = eq(load(const1), load(j));
            constraints.add(c);

            c = eq(load(const2), load(j));
            constraints.add(c);

            c = eq(load(const3), addII(load(i), load(k)));
            constraints.add(c);

            trees[test] = forStmt(
                    forStmt(forStmt(
                            forStmt(forStmt(
                                    ifElse(and(constraints), store(x, addII(load(i), load(k)), Tree.NoComment),
                                            Tree.NoComment, Collections.emptySet()),
                                    constant(0), constant(9), constant(1), m, false, true, Tree.NoComment), constant(0),
                                    constant(9), constant(1), l, false, true, Tree.NoComment),
                            constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0),
                            constant(9), constant(1), j, false, true, Tree.NoComment),
                    constant(0), constant(9), constant(1), i, false, true, Tree.NoComment);
            before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                    + "\tfor(int j = 0; j < 10; j += 1) {\n"
                    + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                    + "\t\t\tfor(int l = 0; l < 10; l += 1) {\n"
                    + "\t\t\t\tfor(int m = 0; m < 10; m += 1) {\n"
                    + "\t\t\t\t\tif((((constant1 == j) && (constant2 == j)) && (constant3 == (i + k))))\n"
                    + "\t\t\t\t\t\tx = (i + k);\n"
                    + "\t\t\t\t}\n"
                    + "\t\t\t}\n"
                    + "\t\t}\n"
                    + "\t}\n"
                    + "}\n";
            after[test++] = "\n"
                    + "// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "if((((constant1 == constant2) && (0 <= constant2)) && (constant2 < 10))) {\n"
                    + "\tfor(int i = 0; i < 10; i += 1) {\n"
                    + "\t\tint k = (constant3 - i);\n"
                    + "\t\t\n"
                    + "\t\t// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "\t\tif(((0 <= k) && (k < 10)))\n"
                    + "\t\t\tx = (i + k);\n"
                    + "\t}\n"
                    + "}\n";
        }

        {
            List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();

            TransBinOp<IntVariable, IntVariable, BooleanVariable> c = eq(load(const1), load(j));
            constraints.add(c);

            c = eq(load(const2), load(j));
            constraints.add(c);

            c = eq(load(const3), addII(load(j), load(k)));
            constraints.add(c);

            trees[test] = forStmt(
                    forStmt(forStmt(
                            forStmt(forStmt(
                                    ifElse(and(constraints), store(x, addII(load(i), load(k)), Tree.NoComment),
                                            Tree.NoComment, Collections.emptySet()),
                                    constant(0), constant(9), constant(1), m, false, true, Tree.NoComment), constant(0),
                                    constant(9), constant(1), l, false, true, Tree.NoComment),
                            constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0),
                            constant(9), constant(1), j, false, true, Tree.NoComment),
                    constant(0), constant(9), constant(1), i, false, true, Tree.NoComment);
            before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                    + "\tfor(int j = 0; j < 10; j += 1) {\n"
                    + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                    + "\t\t\tfor(int l = 0; l < 10; l += 1) {\n"
                    + "\t\t\t\tfor(int m = 0; m < 10; m += 1) {\n"
                    + "\t\t\t\t\tif((((constant1 == j) && (constant2 == j)) && (constant3 == (j + k))))\n"
                    + "\t\t\t\t\t\tx = (i + k);\n"
                    + "\t\t\t\t}\n"
                    + "\t\t\t}\n"
                    + "\t\t}\n"
                    + "\t}\n" 
                    + "}\n";
            after[test++] = "\n" + "// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "if((((constant1 == constant2) && (0 <= constant2)) && (constant2 < 10))) {\n"
                    + "\tfor(int i = 0; i < 10; i += 1) {\n"
                    + "\t\tint k = (constant3 - constant2);\n"
                    + "\t\t\n"
                    + "\t\t// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "\t\tif(((0 <= k) && (k < 10)))\n"
                    + "\t\t\tx = (i + k);\n"
                    + "\t}\n"
                    + "}\n";
        }

        {
            List<TransTreeReturn<BooleanVariable>> constraints = new ArrayList<>();

            TransBinOp<IntVariable, IntVariable, BooleanVariable> c = eq(load(const1), load(j));
            constraints.add(c);

            c = eq(load(const2), load(j));
            constraints.add(c);

            c = eq(load(const3), load(k));
            constraints.add(c);

            trees[test] = forStmt(
                    forStmt(forStmt(
                            forStmt(forStmt(
                                    ifElse(and(constraints), store(x, addII(load(i), load(k)), Tree.NoComment),
                                            Tree.NoComment, Collections.emptySet()),
                                    constant(0), constant(9), constant(1), m, false, true, Tree.NoComment), constant(0),
                                    constant(9), constant(1), l, false, true, Tree.NoComment),
                            constant(0), constant(9), constant(1), k, false, true, Tree.NoComment), constant(0),
                            constant(9), constant(1), j, false, true, Tree.NoComment),
                    constant(0), constant(9), constant(1), i, false, true, Tree.NoComment);
            before[test] = "for(int i = 0; i < 10; i += 1) {\n"
                    + "\tfor(int j = 0; j < 10; j += 1) {\n"
                    + "\t\tfor(int k = 0; k < 10; k += 1) {\n"
                    + "\t\t\tfor(int l = 0; l < 10; l += 1) {\n"
                    + "\t\t\t\tfor(int m = 0; m < 10; m += 1) {\n"
                    + "\t\t\t\t\tif((((constant1 == j) && (constant2 == j)) && (constant3 == k)))\n"
                    + "\t\t\t\t\t\tx = (i + k);\n"
                    + "\t\t\t\t}\n"
                    + "\t\t\t}\n"
                    + "\t\t}\n"
                    + "\t}\n"
                    + "}\n";
            after[test++] = "\n"
                    + "// Constraints moved from conditionals in inner loops/scopes/etc.\n"
                    + "if((((((constant1 == constant2) && (0 <= constant2)) && (constant2 < 10)) && (0 <= constant3)) && (constant3 < 10))) {\n"
                    + "\tfor(int i = 0; i < 10; i += 1)\n"
                    + "\t\t// Substituted \"k\" with its value \"constant3\".\n"
                    + "\t\tx = (i + constant3);\n"
                    + "}\n";
        }

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
