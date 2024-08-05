/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.transformations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.sandwood.compiler.trees.transformationTree.TransTree.addDD;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;
import static org.sandwood.compiler.trees.transformationTree.TransTree.treeScope;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.util.StringUtil;

class RemoveScopesTest {

    private final static int noTests;
    private final static TransTreeVoid[] trees;
    private final static TransTreeVoid[] result;

    static {
        List<TransTreeVoid> treeList = new ArrayList<>();
        List<TransTreeVoid> resultList = new ArrayList<>();

        treeList.add(body("a", "b", "c"));
        resultList.add(body("a", "b", "c"));

        treeList.add(sequential(body("a", "b", "c"), body("d", "e", "f")));
        resultList.add(sequential(body("a", "b", "c"), body("d", "e", "f")));

        treeList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment), body("d", "e", "f")));
        resultList.add(sequential(body("a", "b", "c"), body("d", "e", "f")));

        treeList.add(sequential(body("a", "b", "c"), treeScope(body("d", "e", "f"), Tree.NoComment)));
        resultList.add(sequential(body("a", "b", "c"), body("d", "e", "f")));

        treeList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment),
                treeScope(body("d", "e", "f"), Tree.NoComment)));
        resultList.add(sequential(body("a", "b", "c"), body("d", "e", "f")));

        treeList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment), body("a", "b", "c")));
        resultList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment), body("a", "b", "c")));

        treeList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment),
                treeScope(body("a", "b", "c"), Tree.NoComment)));
        resultList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment), body("a", "b", "c")));

        treeList.add(sequential(treeScope(treeScope(body("a", "b", "c"), Tree.NoComment), Tree.NoComment),
                treeScope(body("a", "b", "c"), Tree.NoComment)));
        resultList.add(sequential(treeScope(body("a", "b", "c"), Tree.NoComment), body("a", "b", "c")));

        treeList.add(treeScope(body("a", "b", "c"), Tree.NoComment));
        resultList.add(body("a", "b", "c"));

        treeList.add(forStmt(treeScope(body("a", "b", "c"), Tree.NoComment), constant(0), constant(100), constant(1),
                VariableNames.variableName("index", 0, VariableType.IntVariable, false), false, true, Tree.NoComment));

        resultList.add(forStmt(body("a", "b", "c"), constant(0), constant(100), constant(1),
                VariableNames.variableName("index", 0, VariableType.IntVariable, false), false, true, Tree.NoComment));

        treeList.add(ifElse(constant(true), treeScope(body("a", "b", "c"), Tree.NoComment), Tree.NoComment,
                Collections.emptySet()));

        resultList.add(ifElse(constant(true), body("a", "b", "c"), Tree.NoComment, Collections.emptySet()));

        treeList.add(ifElse(constant(true), treeScope(body("a", "b", "c"), Tree.NoComment), Tree.NoComment,
                treeScope(body("a", "b", "c"), Tree.NoComment), Tree.NoComment, Collections.emptySet()));

        resultList.add(ifElse(constant(true), body("a", "b", "c"), Tree.NoComment, body("a", "b", "c"), Tree.NoComment,
                Collections.emptySet()));

        noTests = treeList.size();

        trees = treeList.toArray(new TransTreeVoid[noTests]);
        result = resultList.toArray(new TransTreeVoid[noTests]);
    }

    static Stream<Integer> getIndex() {
        Integer[] out = new Integer[noTests];
        for(int i = 0; i < noTests; i++)
            out[i] = i;
        return Stream.of(out);
    }

    private static TransTreeVoid body(String name1, String name2, String name3) {
        VariableDescription<DoubleVariable> vName1 = VariableNames.variableName(name1, 0, VariableType.DoubleVariable,
                false);
        VariableDescription<DoubleVariable> vName2 = VariableNames.variableName(name2, 0, VariableType.DoubleVariable,
                false);
        VariableDescription<DoubleVariable> vName3 = VariableNames.variableName(name3, 0, VariableType.DoubleVariable,
                false);
        return sequential(initializeVariable(vName1, constant(1.0), Tree.NoComment),
                initializeVariable(vName2, constant(1.5), Tree.NoComment),
                store(vName3, addDD(load(vName1), load(vName2)), Tree.NoComment));
    }

    @ParameterizedTest
    @MethodSource("getIndex")
    @DisplayName("Test partial evaluation of constants transformation.")
    void test(int i) {
        TransTreeVoid t = trees[i];
        try {
            Method method = TransTree.class.getDeclaredMethod("removeUnusedScopes", Set.class);
            method.setAccessible(true);
            t = (TransTreeVoid) method.invoke(t, new HashSet<TransTree<?>>());
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
            assertTrue(false);
        } catch(SecurityException e) {
            e.printStackTrace();
            assertTrue(false);
        } catch(IllegalAccessException e) {
            e.printStackTrace();
            assertTrue(false);
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            assertTrue(false);
        } catch(InvocationTargetException e) {
            e.printStackTrace();
            assertTrue(false);
        }
        assertEquals(StringUtil.normalizeNewLines(result[i].toString()), StringUtil.normalizeNewLines(t.toString()));
    }

}
