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
import static org.sandwood.compiler.trees.transformationTree.TransTree.eq;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThan;
import static org.sandwood.compiler.trees.transformationTree.TransTree.lessThanEqual;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;

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
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.Tag;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.util.StringUtil;

class ApplyConstraintsTest {

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
    private final static TransTreeReturn<IntVariable> x = load(
            new VariableDescription<>("x", VariableType.IntVariable, true));
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

        treeList.add(forStmt(buildBody(lessThan(index, x)), start, end, constant(2), indexName, false, true,
                Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 2) {\n" + "\tif((index < x))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("for(int index = start; index <= Math.min(end, (x - 1)); index += 2) {\n" + "\tif(true)\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThan(index, x)), start, end, constant(2), indexName, false, false,
                Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 2) {\n" + "\tif((index < x))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add(
                "for(int index = Math.min(start, ((x - 1) - ((2 - ((start - (x - 1)) % 2)) % 2))); index >= end; index -= 2) {\n"
                        + "\tif(true)\n" + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThanEqual(index, x)), start, end, constant(2), indexName, false, true,
                Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 2) {\n" + "\tif((index <= x))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("for(int index = start; index <= Math.min(end, x); index += 2) {\n" + "\tif(true)\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThanEqual(index, x)), start, end, constant(2), indexName, false, false,
                Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 2) {\n" + "\tif((index <= x))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("for(int index = Math.min(start, (x - ((2 - ((start - x) % 2)) % 2))); index >= end; index -= 2) {\n"
                + "\tif(true)\n" + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThan(x, index)), start, end, constant(2), indexName, false, true,
                Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 2) {\n" + "\tif((x < index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add(
                "for(int index = Math.max(start, ((x + 1) + ((2 - (((x + 1) - start) % 2)) % 2))); index <= end; index += 2) {\n"
                        + "\tif(true)\n" + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThan(x, index)), start, end, constant(2), indexName, false, false,
                Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 2) {\n" + "\tif((x < index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("for(int index = start; index >= Math.max(end, (x + 1)); index -= 2) {\n" + "\tif(true)\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThanEqual(x, index)), start, end, constant(2), indexName, false, true,
                Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 2) {\n" + "\tif((x <= index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("for(int index = Math.max(start, (x + ((2 - ((x - start) % 2)) % 2))); index <= end; index += 2) {\n"
                + "\tif(true)\n" + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(lessThanEqual(x, index)), start, end, constant(2), indexName, false, false,
                Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 2) {\n" + "\tif((x <= index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("for(int index = start; index >= Math.max(end, x); index -= 2) {\n" + "\tif(true)\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(eq(index, x)), start, end, constant(2), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 2) {\n" + "\tif((index == x))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("{\n" + "\tint index = x;\n"
                + "\tif((((0 == ((index - start) % 2)) && (start <= index)) && (index <= end)))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(
                forStmt(buildBody(eq(index, x)), start, end, constant(2), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 2) {\n" + "\tif((index == x))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("{\n" + "\tint index = x;\n"
                + "\tif((((0 == ((index - start) % 2)) && (index <= start)) && (end <= index)))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(forStmt(buildBody(eq(x, index)), start, end, constant(2), indexName, false, true, Tree.NoComment));
        beforeList.add("for(int index = start; index <= end; index += 2) {\n" + "\tif((x == index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("{\n" + "\tint index = x;\n"
                + "\tif((((0 == ((index - start) % 2)) && (start <= index)) && (index <= end)))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        treeList.add(
                forStmt(buildBody(eq(x, index)), start, end, constant(2), indexName, false, false, Tree.NoComment));
        beforeList.add("for(int index = start; index >= end; index -= 2) {\n" + "\tif((x == index))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");
        afterList.add("{\n" + "\tint index = x;\n"
                + "\tif((((0 == ((index - start) % 2)) && (index <= start)) && (end <= index)))\n"
                + "\t\tc[index] = (a[index] + b[index]);\n" + "}\n");

        noTests = treeList.size();
        trees = treeList.toArray(new TransTreeVoid[noTests]);
        before = beforeList.toArray(new String[noTests]);
        after = afterList.toArray(new String[noTests]);
    }

    private static TransTreeVoid buildBody(TransTreeReturn<BooleanVariable> guard) {
        return ifElse(guard, arrayPut(c, index, addII(arrayGet(a, index), arrayGet(b, index)), Tree.NoComment),
                Tree.NoComment, tags);
    }

    static Stream<Integer> getIndex() {
        Integer[] out = new Integer[noTests];
        for(int i = 0; i < noTests; i++)
            out[i] = i;
        return Stream.of(out);
    }

    @ParameterizedTest
    @MethodSource("getIndex")
    @DisplayName("Test the merging of constraints into arrays.")
    void test(int i) {
        assertEquals(before[i], StringUtil.normalizeNewLines(trees[i].toString()));
        assertEquals(after[i],
                StringUtil.normalizeNewLines(trees[i].copy().applyConstraints(new HashSet<>()).toString()));
    }
}
