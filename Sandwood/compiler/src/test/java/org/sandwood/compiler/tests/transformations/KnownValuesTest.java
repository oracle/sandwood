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
import static org.sandwood.compiler.trees.transformationTree.TransTree.arrayGet;
import static org.sandwood.compiler.trees.transformationTree.TransTree.arrayPut;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThan;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThanEqual;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;
import org.sandwood.compiler.util.StringUtil;

class KnownValuesTest {

    private final static int noTests;
    private final static TransTreeVoid[] trees;
    private final static String[] before;
    private final static String[] after;

    private final static VariableDescription<IntVariable> indexName = new VariableDescription<>("index",
            VariableType.IntVariable, true);
    private final static TransTreeReturn<IntVariable> index = load(indexName);
    private final static TransTreeReturn<IntVariable> start = load(
            new VariableDescription<>("start", VariableType.IntVariable, true));
    private final static TransTreeReturn<IntVariable> end = load(
            new VariableDescription<>("end", VariableType.IntVariable, true));
    private final static TransTreeReturn<ArrayVariable<IntVariable>> a = load(
            new VariableDescription<>("a", VariableType.arrayType(VariableType.IntVariable), true));
    private final static TransTreeReturn<ArrayVariable<IntVariable>> b = load(
            new VariableDescription<>("b", VariableType.arrayType(VariableType.IntVariable), true));
    private final static TransTreeReturn<ArrayVariable<IntVariable>> c = load(
            new VariableDescription<>("c", VariableType.arrayType(VariableType.IntVariable), true));

    private static final Set<Tag> tags = Collections.emptySet();

    static {
        List<TransTreeVoid> treeList = new ArrayList<>();
        List<String> beforeList = new ArrayList<>();
        List<String> afterList = new ArrayList<>();

        // start <= index
        treeList.add(forStmt(ifElse(lessThanEqual(start, index),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 1) {\n"
                + "\tif((start <= index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index <= end; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index <= end
        treeList.add(forStmt(ifElse(lessThanEqual(index, end),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 1) {\n"
                + "\tif((index <= end))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index <= end; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index < end
        treeList.add(forStmt(ifElse(lessThan(index, end),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 1) {\n"
                + "\tif((index < end))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index < end; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // 0 <= index
        treeList.add(forStmt(ifElse(lessThanEqual(constant(0), index),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), constant(0), end, constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = 0; index <= end; index += 1) {\n"
                + "\tif((0 <= index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = 0; index <= end; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index < 10
        treeList.add(forStmt(
                ifElse(lessThan(index, constant(10)),
                        arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                        Tree.NoComment, tags),
                start, constant(9), constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index < 10; index += 1) {\n"
                + "\tif((index < 10))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index < 10; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // start <= end
        treeList.add(forStmt(ifElse(lessThanEqual(start, end),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 1) {\n"
                + "\tif((start <= end))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index <= end; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // 0 <= index
        treeList.add(forStmt(ifElse(lessThanEqual(constant(0), end),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), constant(0), end, constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = 0; index <= end; index += 1) {\n"
                + "\tif((0 <= end))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = 0; index <= end; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index < 10
        treeList.add(forStmt(
                ifElse(lessThan(start, constant(10)),
                        arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                        Tree.NoComment, tags),
                start, constant(9), constant(1), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index < 10; index += 1) {\n"
                + "\tif((start < 10))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index < 10; index += 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // end <= index
        treeList.add(forStmt(ifElse(lessThanEqual(end, index),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 1) {\n"
                + "\tif((end <= index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index >= end; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index < start
        treeList.add(forStmt(ifElse(lessThanEqual(index, start),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 1) {\n"
                + "\tif((index <= start))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index >= end; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // 0 <= index
        treeList.add(forStmt(
                ifElse(lessThanEqual(constant(0), index),
                        arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                        Tree.NoComment, tags),
                start, constant(0), constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= 0; index -= 1) {\n"
                + "\tif((0 <= index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index >= 0; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index < 10
        treeList.add(forStmt(
                ifElse(lessThanEqual(index, constant(10)),
                        arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                        Tree.NoComment, tags),
                constant(10), end, constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = 10; index >= end; index -= 1) {\n"
                + "\tif((index <= 10))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = 10; index >= end; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // end <= start
        treeList.add(forStmt(ifElse(lessThanEqual(end, start),
                arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment), Tree.NoComment,
                tags), start, end, constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 1) {\n"
                + "\tif((end <= start))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index >= end; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // 0 <= index
        treeList.add(forStmt(
                ifElse(lessThanEqual(constant(0), start),
                        arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                        Tree.NoComment, tags),
                start, constant(0), constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= 0; index -= 1) {\n"
                + "\tif((0 <= start))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = start; index >= 0; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        // index <= 10
        treeList.add(forStmt(
                ifElse(lessThanEqual(end, constant(10)),
                        arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                        Tree.NoComment, tags),
                constant(10), end, constant(1), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = 10; index >= end; index -= 1) {\n"
                + "\tif((end <= 10))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n"
                + "}\n");
        afterList.add("for(int index = 10; index >= end; index -= 1)\n"
                + "\tc[index] = (a[index] + b[index]);\n");

        noTests = treeList.size();
        trees = treeList.toArray(new TransTreeVoid[noTests]);
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
    @DisplayName("Test propagation of known values.")
    void test(int i) {
        assertEquals(before[i], StringUtil.normalizeNewLines(trees[i].toString()));
        assertEquals(after[i],
                StringUtil.normalizeNewLines(trees[i].copy()
                        .applyOptimisations(new ArgDesc[0], new HashMap<>(), KnownValuesTrans.constructKnownValues())
                        .toString()));
    }
}
