/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.sandwood.compiler.trees.transformationTree.TransTree.arrayGet;
import static org.sandwood.compiler.trees.transformationTree.TransTree.arrayPut;
import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;
import static org.sandwood.compiler.trees.transformationTree.TransTree.forStmt;
import static org.sandwood.compiler.trees.transformationTree.TransTree.ifElse;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeUnsetVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.initializeVariable;
import static org.sandwood.compiler.trees.transformationTree.TransTree.load;
import static org.sandwood.compiler.trees.transformationTree.TransTree.newArray;
import static org.sandwood.compiler.trees.transformationTree.TransTree.nop;
import static org.sandwood.compiler.trees.transformationTree.TransTree.sequential;
import static org.sandwood.compiler.trees.transformationTree.TransTree.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

public class ReadWrite {
    private static final VariableDescription<IntVariable> a = new VariableDescription<>("a", VariableType.IntVariable,
            true);
    private static final VariableDescription<IntVariable> b = new VariableDescription<>("b", VariableType.IntVariable,
            true);
    private static final VariableDescription<IntVariable> c = new VariableDescription<>("c", VariableType.IntVariable,
            true);
    private static final VariableDescription<IntVariable> d = new VariableDescription<>("d", VariableType.IntVariable,
            true);
    private static final VariableDescription<IntVariable> e = new VariableDescription<>("e", VariableType.IntVariable,
            true);

    private static final VariableDescription<ArrayVariable<IntVariable>> f = new VariableDescription<>("f",
            VariableType.arrayType(VariableType.IntVariable), true);
    private static final VariableDescription<ArrayVariable<IntVariable>> g = new VariableDescription<>("g",
            VariableType.arrayType(VariableType.IntVariable), true);
    private static final VariableDescription<ArrayVariable<IntVariable>> h = new VariableDescription<>("h",
            VariableType.arrayType(VariableType.IntVariable), true);

    private static final VariableDescription<ArrayVariable<ArrayVariable<IntVariable>>> i = new VariableDescription<>(
            "i", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), true);
    private static final VariableDescription<ArrayVariable<ArrayVariable<IntVariable>>> j = new VariableDescription<>(
            "j", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), true);
    private static final VariableDescription<ArrayVariable<ArrayVariable<IntVariable>>> k = new VariableDescription<>(
            "k", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), true);

    private static final VariableDescription<BooleanVariable> guard = new VariableDescription<>("guard",
            VariableType.BooleanVariable, true);

    @Test
    void nopTest() {
        TransTreeVoid t = nop();
        VariableTracking vt = t.getVariableTracking();
        assertTrue(vt.inScopeVars(t).getVars().isEmpty());
        assertTrue(vt.readVars(t).getVars().isEmpty());
        assertTrue(vt.written(t).isEmpty());
        assertTrue(vt.arraysModified(t).isEmpty());
    }

    @Test
    void seqTest() {
        TransTreeVoid t = sequential();
        VariableTracking vt = t.getVariableTracking();
        assertTrue(vt.inScopeVars(t).getVars().isEmpty());
        assertTrue(vt.readVars(t).getVars().isEmpty());
        assertTrue(vt.written(t).isEmpty());
        assertTrue(vt.arraysModified(t).isEmpty());
    }

    @Test
    void basicTest() {
        // Build tree
        TransTreeVoid t1 = initializeVariable(a, constant(5), Tree.NoComment);
        TransTreeVoid t2 = initializeUnsetVariable(b, Tree.NoComment);
        TransTreeVoid t3 = store(b, load(c), Tree.NoComment);
        TransTreeVoid t4 = store(b, load(d), Tree.NoComment);
        TransTreeVoid t5 = store(b, load(a), Tree.NoComment);
        TransTreeVoid t = sequential(t1, t2, t3, t4, t5);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test T1
        ScopedVarSet m = vt.inScopeVars(t1);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);

        assertTrue(vt.readVars(t1).getVars().isEmpty());

        Set<VariableDescription<?>> written = vt.written(t1);
        assertEquals(1, written.size());
        assertTrue(written.contains(a));

        assertTrue(vt.arraysModified(t1).isEmpty());

        // Test t2
        m = vt.inScopeVars(t2);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, a, t1.id, t1.id);

        assertTrue(vt.readVars(t2).getVars().isEmpty());

        written = vt.written(t2);
        assertEquals(0, written.size());

        assertTrue(vt.arraysModified(t2).isEmpty());

        // Test T3
        m = vt.inScopeVars(t3);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id);

        m = vt.readVars(t3);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);

        written = vt.written(t3);
        assertEquals(1, written.size());
        assertTrue(written.contains(b));

        assertTrue(vt.arraysModified(t3).isEmpty());

        // Test T4
        m = vt.inScopeVars(t4);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t3.id);

        m = vt.readVars(t4);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, d, TreeID.global, TreeID.global);

        written = vt.written(t4);
        assertEquals(1, written.size());
        assertTrue(written.contains(b));

        assertTrue(vt.arraysModified(t4).isEmpty());

        // Test T5
        m = vt.inScopeVars(t5);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t4.id);

        m = vt.readVars(t5);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, a, t1.id, t1.id);

        written = vt.written(t5);
        assertEquals(1, written.size());
        assertTrue(written.contains(b));

        assertTrue(vt.arraysModified(t5).isEmpty());

        // Test T
        m = vt.inScopeVars(t);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);

        m = vt.readVars(t);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);

        written = vt.written(t);
        assertEquals(0, written.size());

        assertTrue(vt.arraysModified(t).isEmpty());
    }

    @Test
    void basicArrayTest() {
        // Build tree
        TransTreeVoid t1 = arrayPut(load(f), load(a), load(b), Tree.NoComment);
        TransTreeVoid t2 = initializeVariable(g, load(f), Tree.NoComment);
        TransTreeVoid t3 = initializeVariable(c, arrayGet(load(g), load(a)), Tree.NoComment);
        TransTreeVoid t4 = arrayPut(load(f), load(c), load(b), Tree.NoComment);
        TransTreeVoid t = sequential(t1, t2, t3, t4);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test T1
        ScopedVarSet m = vt.inScopeVars(t1);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        m = vt.readVars(t1);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        Set<VariableDescription<?>> written = vt.written(t1);
        assertEquals(0, written.size());

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(t1);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(f));

        // Test t2
        m = vt.inScopeVars(t2);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        m = vt.readVars(t2);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(t2);
        assertEquals(1, written.size());
        assertTrue(written.contains(g));

        arraysModified = vt.arraysModified(t2);
        assertEquals(0, arraysModified.size());

        // Test T3
        m = vt.inScopeVars(t3);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, t2.id, t2.id);

        m = vt.readVars(t3);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, g, t2.id, t2.id);

        written = vt.written(t3);
        assertEquals(1, written.size());
        assertTrue(written.contains(c));

        assertTrue(vt.arraysModified(t3).isEmpty());

        // Test T4
        m = vt.inScopeVars(t4);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, t2.id, t2.id);

        m = vt.readVars(t4);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(t4);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t4);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));

        // Test T
        m = vt.inScopeVars(t);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        m = vt.readVars(t);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(t);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(f));
    }

    @Test
    void basicArrayTest2() {
        // Build tree
        List<TransTreeReturn<IntVariable>> dims = new ArrayList<>();
        dims.add(load(a));
        TransTreeVoid t1 = initializeVariable(f, newArray(dims, VariableType.arrayType(VariableType.IntVariable)),
                Tree.NoComment);
        TransTreeVoid t2 = initializeVariable(g, newArray(dims, VariableType.arrayType(VariableType.IntVariable)),
                Tree.NoComment);
        TransTreeVoid t3 = initializeVariable(h, load(f), Tree.NoComment);
        TransTreeVoid t4 = store(f, load(g), Tree.NoComment);
        TransTreeVoid t5 = arrayPut(load(f), load(c), load(b), Tree.NoComment);
        TransTreeVoid t6 = arrayPut(load(g), load(c), load(b), Tree.NoComment);
        TransTreeVoid t7 = arrayPut(load(h), load(c), load(b), Tree.NoComment);
        TransTreeVoid t = sequential(t1, t2, t3, t4, t5, t6, t7);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test T1
        ScopedVarSet m = vt.inScopeVars(t1);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);

        m = vt.readVars(t1);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);

        Set<VariableDescription<?>> written = vt.written(t1);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(t1);
        assertEquals(0, arraysModified.size());

        // Test t2
        m = vt.inScopeVars(t2);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t1.id);

        m = vt.readVars(t2);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);

        written = vt.written(t2);
        assertEquals(1, written.size());
        assertTrue(written.contains(g));

        arraysModified = vt.arraysModified(t2);
        assertEquals(0, arraysModified.size());

        // Test T3
        m = vt.inScopeVars(t3);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t1.id);
        testVarDef(m, g, t2.id, t2.id);

        m = vt.readVars(t3);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, f, t1.id, t1.id);

        written = vt.written(t3);
        assertEquals(1, written.size());
        assertTrue(written.contains(h));

        assertTrue(vt.arraysModified(t3).isEmpty());

        // Test T4
        m = vt.inScopeVars(t4);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t1.id);
        testVarDef(m, g, t2.id, t2.id);
        testVarDef(m, h, t3.id, t3.id);

        m = vt.readVars(t4);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, g, t2.id, t2.id);

        written = vt.written(t4);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(t4);
        assertEquals(0, arraysModified.size());

        // Test T5
        m = vt.inScopeVars(t5);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t4.id);
        testVarDef(m, g, t2.id, t2.id);
        testVarDef(m, h, t3.id, t3.id);

        m = vt.readVars(t5);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t4.id);

        written = vt.written(t5);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t5);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));

        // Test T6
        m = vt.inScopeVars(t6);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t4.id);
        testVarDef(m, g, t2.id, t2.id);
        testVarDef(m, h, t3.id, t3.id);

        m = vt.readVars(t6);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, g, t2.id, t2.id);

        written = vt.written(t6);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t6);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));

        // Test T7
        m = vt.inScopeVars(t7);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, t1.id, t4.id);
        testVarDef(m, g, t2.id, t2.id);
        testVarDef(m, h, t3.id, t3.id);

        m = vt.readVars(t7);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, h, t3.id, t3.id);

        written = vt.written(t7);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t7);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(h));

        // Test T
        m = vt.inScopeVars(t);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);

        m = vt.readVars(t);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);

        written = vt.written(t);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t);
        assertEquals(0, arraysModified.size());
    }

    @Test
    void complexArrayTest() {
        // Build tree
        TransTreeVoid t1 = arrayPut(load(f), load(b), load(c), Tree.NoComment);
        TransTreeVoid t2 = arrayPut(load(i), load(b), load(f), Tree.NoComment);
        TransTreeVoid t3 = arrayPut(load(f), load(b), load(c), Tree.NoComment);
        TransTreeVoid t4 = initializeVariable(g, arrayGet(load(i), load(a)), Tree.NoComment);
        TransTreeVoid t5 = arrayPut(load(f), load(b), load(c), Tree.NoComment);
        List<TransTreeReturn<IntVariable>> dims = new ArrayList<>();
        dims.add(load(a));
        TransTreeVoid t6 = store(f, TransTree.newArray(dims, VariableType.arrayType(VariableType.IntVariable)),
                Tree.NoComment);
        TransTreeVoid t7 = arrayPut(load(f), load(b), load(c), Tree.NoComment);

        TransTreeVoid t = sequential(t1, t2, t3, t4, t5, t6, t7);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test T1
        ScopedVarSet m = vt.inScopeVars(t1);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        m = vt.readVars(t1);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        Set<VariableDescription<?>> written = vt.written(t1);
        assertEquals(0, written.size());

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(t1);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(f));

        // Test t2
        m = vt.inScopeVars(t2);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        m = vt.readVars(t2);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(t2);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t2);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(i));

        // Test T3
        m = vt.inScopeVars(t3);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        m = vt.readVars(t3);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(t3);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t3);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(i));

        // Test T4
        m = vt.inScopeVars(t4);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        m = vt.readVars(t4);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(t4);
        assertEquals(1, written.size());
        assertTrue(written.contains(g));

        arraysModified = vt.arraysModified(t4);
        assertEquals(0, arraysModified.size());

        // Test T5
        m = vt.inScopeVars(t5);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, g, t4.id, t4.id);

        m = vt.readVars(t5);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(t5);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t5);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(i));

        // Test T6
        m = vt.inScopeVars(t6);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, g, t4.id, t4.id);

        m = vt.readVars(t6);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);

        written = vt.written(t6);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(t6);
        assertEquals(0, arraysModified.size());

        // Test T7
        m = vt.inScopeVars(t7);
        keys = m.getVars();
        assertEquals(6, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, t6.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, g, t4.id, t4.id);

        m = vt.readVars(t7);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, t6.id);

        written = vt.written(t7);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t7);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(f));

        // Test T
        m = vt.inScopeVars(t);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        m = vt.readVars(t);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(t);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(t);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(i));
    }

    @Test
    void basicIfTest() {
        // Build tree
        TransTreeVoid t1 = initializeVariable(a, constant(5), Tree.NoComment);
        TransTreeVoid t2 = initializeVariable(b, constant(5), Tree.NoComment);
        TransTreeVoid t3 = initializeVariable(c, constant(5), Tree.NoComment);
        TransTreeVoid t4 = initializeVariable(d, constant(5), Tree.NoComment);
        TransTreeVoid ifBody1 = store(a, load(c), Tree.NoComment);
        TransTreeVoid ifBody2 = store(c, load(a), Tree.NoComment);
        TransTreeVoid ifBody = sequential(ifBody1, ifBody2);
        TransTreeVoid elseBody1 = store(b, load(d), Tree.NoComment);
        TransTreeVoid elseBody2 = store(c, load(b), Tree.NoComment);
        TransTreeVoid elseBody = sequential(elseBody1, elseBody2);
        TransTreeVoid ifTree = ifElse(load(guard), ifBody, Tree.NoComment, elseBody, Tree.NoComment,
                Collections.emptySet());
        TransTreeVoid t5 = store(d, load(a), Tree.NoComment);
        TransTreeVoid t = sequential(t1, t2, t3, t4, ifTree, t5);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test T1
        ScopedVarSet m = vt.inScopeVars(t1);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(t1);
        keys = m.getVars();
        assertEquals(0, keys.size());

        Set<VariableDescription<?>> written = vt.written(t1);
        assertEquals(1, written.size());
        assertTrue(written.contains(a));

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(t1);
        assertEquals(0, arraysModified.size());

        // Test t2
        m = vt.inScopeVars(t2);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, guard, TreeID.global, TreeID.global);
        testVarDef(m, a, t1.id, t1.id);

        m = vt.readVars(t2);
        keys = m.getVars();
        assertEquals(0, keys.size());

        written = vt.written(t2);
        assertEquals(1, written.size());
        assertTrue(written.contains(b));

        arraysModified = vt.arraysModified(t2);
        assertEquals(0, arraysModified.size());

        // Test T3
        m = vt.inScopeVars(t3);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(t3);
        keys = m.getVars();
        assertEquals(0, keys.size());

        written = vt.written(t3);
        assertEquals(1, written.size());
        assertTrue(written.contains(c));

        assertTrue(vt.arraysModified(t3).isEmpty());

        // Test T4
        m = vt.inScopeVars(t4);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(t4);
        keys = m.getVars();
        assertEquals(0, keys.size());

        written = vt.written(t4);
        assertEquals(1, written.size());
        assertTrue(written.contains(d));

        arraysModified = vt.arraysModified(t4);
        assertEquals(0, arraysModified.size());

        // Test ifBody1
        m = vt.inScopeVars(ifBody1);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody1);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, c, t3.id, t3.id);

        written = vt.written(ifBody1);
        assertEquals(1, written.size());
        assertTrue(written.contains(a));

        arraysModified = vt.arraysModified(ifBody1);
        assertEquals(0, arraysModified.size());

        // Test ifBody2
        m = vt.inScopeVars(ifBody2);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, ifBody1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody2);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, a, t1.id, ifBody1.id);

        written = vt.written(ifBody2);
        assertEquals(1, written.size());
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(ifBody2);
        assertEquals(0, arraysModified.size());

        // Test ifBody
        m = vt.inScopeVars(ifBody);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, c, t3.id, t3.id);

        written = vt.written(ifBody);
        assertEquals(2, written.size());
        assertTrue(written.contains(a));
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(ifBody);
        assertEquals(0, arraysModified.size());

        // Test elseBody1
        m = vt.inScopeVars(elseBody1);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody1);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, d, t4.id, t4.id);

        written = vt.written(elseBody1);
        assertEquals(1, written.size());
        assertTrue(written.contains(b));

        arraysModified = vt.arraysModified(elseBody1);
        assertEquals(0, arraysModified.size());

        // Test elseBody2
        m = vt.inScopeVars(elseBody2);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, elseBody1.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody2);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, b, t2.id, elseBody1.id);

        written = vt.written(elseBody2);
        assertEquals(1, written.size());
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(elseBody2);
        assertEquals(0, arraysModified.size());

        // Test elseBody
        m = vt.inScopeVars(elseBody);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, d, t4.id, t4.id);

        written = vt.written(elseBody);
        assertEquals(2, written.size());
        assertTrue(written.contains(b));
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(elseBody);
        assertEquals(0, arraysModified.size());

        // Test ifTree
        m = vt.inScopeVars(ifTree);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id);
        testVarDef(m, b, t2.id, t2.id);
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifTree);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, t3.id, t3.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        written = vt.written(ifTree);
        assertEquals(3, written.size());
        assertTrue(written.contains(a));
        assertTrue(written.contains(b));
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(ifTree);
        assertEquals(0, arraysModified.size());

        // Test t5
        m = vt.inScopeVars(t5);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, t1.id, t1.id, ifBody1.id);
        testVarDef(m, b, t2.id, t2.id, elseBody1.id);
        testVarDef(m, c, t3.id, ifBody2.id, elseBody2.id);
        testVarDef(m, d, t4.id, t4.id);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(t5);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, a, t1.id, t1.id, ifBody1.id);

        written = vt.written(t5);
        assertEquals(1, written.size());
        assertTrue(written.contains(d));

        arraysModified = vt.arraysModified(t5);
        assertEquals(0, arraysModified.size());

        // Test T
        m = vt.inScopeVars(t);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(t);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, guard, TreeID.global, TreeID.global);

        written = vt.written(t);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(t);
        assertEquals(0, arraysModified.size());
    }

    @Test
    void arrayIfTest() {
        // Build tree
        TransTreeVoid ifBody1 = arrayPut(load(i), load(a), load(f), Tree.NoComment);
        TransTreeVoid ifBody2 = arrayPut(load(f), load(a), load(b), Tree.NoComment);
        TransTreeVoid ifBody3 = arrayPut(load(i), load(a), load(g), Tree.NoComment);
        TransTreeVoid ifBody4 = arrayPut(load(g), load(a), load(b), Tree.NoComment);
        TransTreeVoid ifBody = sequential(ifBody1, ifBody2, ifBody3, ifBody4);
        TransTreeVoid elseBody1 = store(g, arrayGet(load(i), load(c)), Tree.NoComment);
        TransTreeVoid elseBody2 = arrayPut(load(g), load(c), load(d), Tree.NoComment);
        TransTreeVoid elseBody3 = store(h, arrayGet(load(i), load(c)), Tree.NoComment);
        TransTreeVoid elseBody4 = arrayPut(load(h), load(c), load(d), Tree.NoComment);
        TransTreeVoid elseBody = sequential(elseBody1, elseBody2, elseBody3, elseBody4);
        TransTreeVoid ifTree = ifElse(load(guard), ifBody, Tree.NoComment, elseBody, Tree.NoComment,
                Collections.emptySet());
        TransTreeVoid tafter1 = arrayPut(load(g), load(c), load(d), Tree.NoComment);
        TransTreeVoid tafter2 = arrayPut(load(h), load(c), load(d), Tree.NoComment);
        TransTreeVoid t = sequential(ifTree, tafter1, tafter2);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test ifBody1
        ScopedVarSet m = vt.inScopeVars(ifBody1);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody1);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        Set<VariableDescription<?>> written = vt.written(ifBody1);
        assertEquals(0, written.size());

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(ifBody1);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(i));

        // Test ifBody2
        m = vt.inScopeVars(ifBody2);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody2);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);

        written = vt.written(ifBody2);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(ifBody2);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(i));

        // Test ifBody3
        m = vt.inScopeVars(ifBody3);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody3);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(ifBody3);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(ifBody3);
        assertEquals(1, arraysModified.size());
        assertTrue(arraysModified.contains(i));

        // Test ifBody4
        m = vt.inScopeVars(ifBody4);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody4);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);

        written = vt.written(ifBody4);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(ifBody4);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(i));

        // Test ifBody
        m = vt.inScopeVars(ifBody);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifBody);
        keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(ifBody);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(ifBody);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(i));

        // Test elseBody1
        m = vt.inScopeVars(elseBody1);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody1);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(elseBody1);
        assertEquals(1, written.size());
        assertTrue(written.contains(g));

        arraysModified = vt.arraysModified(elseBody1);
        assertEquals(0, arraysModified.size());

        // Test elseBody2
        m = vt.inScopeVars(elseBody2);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, elseBody1.id);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody2);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, elseBody1.id);

        written = vt.written(elseBody2);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(elseBody2);
        assertEquals(2, arraysModified.size());
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(i));

        // Test elseBody3
        m = vt.inScopeVars(elseBody3);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, elseBody1.id);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody3);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(elseBody3);
        assertEquals(1, written.size());
        assertTrue(written.contains(h));

        arraysModified = vt.arraysModified(elseBody3);
        assertEquals(0, arraysModified.size());

        // Test elseBody4
        m = vt.inScopeVars(elseBody4);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, elseBody1.id);
        testVarDef(m, h, TreeID.global, elseBody3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody4);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, elseBody3.id);

        written = vt.written(elseBody4);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(elseBody4);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));

        // Test elseBody
        m = vt.inScopeVars(elseBody);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(elseBody);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);

        written = vt.written(elseBody);
        assertEquals(2, written.size());
        assertTrue(written.contains(g));
        assertTrue(written.contains(h));

        arraysModified = vt.arraysModified(elseBody);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));

        // Test ifTree
        m = vt.inScopeVars(ifTree);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(ifTree);
        keys = m.getVars();
        assertEquals(8, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        written = vt.written(ifTree);
        assertEquals(2, written.size());
        assertTrue(written.contains(g));
        assertTrue(written.contains(h));

        arraysModified = vt.arraysModified(ifTree);
        assertEquals(4, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));

        // Test tAfter1
        m = vt.inScopeVars(tafter1);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global, elseBody1.id);
        testVarDef(m, h, TreeID.global, TreeID.global, elseBody3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(tafter1);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global, elseBody1.id);

        written = vt.written(tafter1);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(tafter1);
        assertEquals(4, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));

        // Test tAfter2
        m = vt.inScopeVars(tafter2);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global, elseBody1.id);
        testVarDef(m, h, TreeID.global, TreeID.global, elseBody3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(tafter2);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global, elseBody3.id);

        written = vt.written(tafter2);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(tafter2);
        assertEquals(4, arraysModified.size());
        // TODO It can be determined that this variable cannot be reached if
        // f can be reached. Consider a more complex structure to enforce this.
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));

        // Test T
        m = vt.inScopeVars(t);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        m = vt.readVars(t);
        keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);
        testVarDef(m, d, TreeID.global, TreeID.global);
        testVarDef(m, f, TreeID.global, TreeID.global);
        testVarDef(m, g, TreeID.global, TreeID.global);
        testVarDef(m, h, TreeID.global, TreeID.global);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, guard, TreeID.global, TreeID.global);

        written = vt.written(t);
        assertEquals(2, written.size());
        assertTrue(written.contains(g));
        assertTrue(written.contains(h));

        arraysModified = vt.arraysModified(t);
        assertEquals(4, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));
    }

    @Test
    void basicForTest() {
        // Build tree
        TransTreeVoid outer1 = store(a, constant(1), Tree.NoComment);
        TransTreeVoid outer2 = store(b, constant(1), Tree.NoComment);
        TransTreeVoid outer3 = store(c, constant(1), Tree.NoComment);

        TransTreeVoid inner1 = store(b, load(c), Tree.NoComment);
        TransTreeVoid inner2 = store(c, load(b), Tree.NoComment);

        TransTreeVoid innerBody = sequential(inner1, inner2);
        TransTreeVoid innerFor = forStmt(innerBody, constant(0), constant(10), constant(1), d, false, true,
                Tree.NoComment);

        TransTreeVoid outer4 = store(c, load(b), Tree.NoComment);
        TransTreeVoid outerBody = sequential(outer1, outer2, outer3, innerFor, outer4);
        TransTreeVoid outerFor = forStmt(outerBody, constant(0), constant(10), constant(1), e, false, true,
                Tree.NoComment);
        TransTreeVoid tFinal = store(a, load(b), Tree.NoComment);
        TransTreeVoid t = sequential(outerFor, tFinal);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test innerBody
        ScopedVarSet m = vt.inScopeVars(innerBody);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(5, keys.size());
        testVarDef(m, a, TreeID.global, outer1.id);
        testVarDef(m, b, TreeID.global, outer2.id, inner1.id);
        testVarDef(m, c, TreeID.global, outer3.id, inner2.id);
        testVarDef(m, d, innerFor.id, innerFor.id);
        testVarDef(m, e, outerFor.id, outerFor.id);

        m = vt.readVars(innerBody);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, c, TreeID.global, outer3.id, inner2.id);

        Set<VariableDescription<?>> written = vt.written(innerBody);
        assertEquals(2, written.size());
        assertTrue(written.contains(b));
        assertTrue(written.contains(c));

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(innerBody);
        assertEquals(0, arraysModified.size());

        // Test innerFor
        m = vt.inScopeVars(innerFor);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, a, TreeID.global, outer1.id);
        testVarDef(m, b, TreeID.global, outer2.id);
        testVarDef(m, c, TreeID.global, outer3.id);
        testVarDef(m, e, outerFor.id, outerFor.id);

        m = vt.readVars(innerFor);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, c, TreeID.global, outer3.id);

        written = vt.written(innerFor);
        assertEquals(2, written.size());
        assertTrue(written.contains(b));
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(innerFor);
        assertEquals(0, arraysModified.size());

        // Test outer4
        m = vt.inScopeVars(outer4);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, a, TreeID.global, outer1.id);
        testVarDef(m, b, TreeID.global, outer2.id, inner1.id);
        testVarDef(m, c, TreeID.global, outer3.id, inner2.id);
        testVarDef(m, e, outerFor.id, outerFor.id);

        m = vt.readVars(outer4);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, b, TreeID.global, outer2.id, inner1.id);

        written = vt.written(outer4);
        assertEquals(1, written.size());
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(outer4);
        assertEquals(0, arraysModified.size());

        // Test outerBody
        m = vt.inScopeVars(outerBody);
        keys = m.getVars();
        assertEquals(4, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global, outer1.id);
        testVarDef(m, b, TreeID.global, TreeID.global, outer2.id, inner1.id);
        testVarDef(m, c, TreeID.global, TreeID.global, outer4.id);
        testVarDef(m, e, outerFor.id, outerFor.id);

        m = vt.readVars(outerBody);
        keys = m.getVars();
        assertEquals(0, keys.size());

        written = vt.written(outerBody);
        assertEquals(3, written.size());
        assertTrue(written.contains(a));
        assertTrue(written.contains(b));
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(outerBody);
        assertEquals(0, arraysModified.size());

        // Test outerFor
        m = vt.inScopeVars(outerFor);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, b, TreeID.global, TreeID.global);
        testVarDef(m, c, TreeID.global, TreeID.global);

        m = vt.readVars(outerFor);
        keys = m.getVars();
        assertEquals(0, keys.size());

        written = vt.written(outerFor);
        assertEquals(3, written.size());
        assertTrue(written.contains(a));
        assertTrue(written.contains(b));
        assertTrue(written.contains(c));

        arraysModified = vt.arraysModified(outerFor);
        assertEquals(0, arraysModified.size());

        // Test tFinal
        m = vt.inScopeVars(tFinal);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global, outer1.id);
        testVarDef(m, b, TreeID.global, TreeID.global, outer2.id, inner1.id);
        testVarDef(m, c, TreeID.global, TreeID.global, outer4.id);

        m = vt.readVars(tFinal);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, b, TreeID.global, TreeID.global, outer2.id, inner1.id);

        written = vt.written(tFinal);
        assertEquals(1, written.size());
        assertTrue(written.contains(a));

        arraysModified = vt.arraysModified(tFinal);
        assertEquals(0, arraysModified.size());
    }

    @Test
    void arrayForTest() {
        // Build tree
        TransTreeVoid init1 = initializeVariable(f, arrayGet(load(i), load(a)), Tree.NoComment);
        TransTreeVoid init2 = initializeVariable(g, arrayGet(load(j), load(a)), Tree.NoComment);
        TransTreeVoid init3 = initializeVariable(h, arrayGet(load(k), load(a)), Tree.NoComment);

        TransTreeVoid outer1 = arrayPut(load(f), load(e), constant(1), Tree.NoComment);
        TransTreeVoid outer2 = store(g, load(f), Tree.NoComment);

        TransTreeVoid inner1 = arrayPut(load(h), load(d), constant(2), Tree.NoComment);
        TransTreeVoid inner2 = store(f, load(h), Tree.NoComment);

        TransTreeVoid innerBody = sequential(inner1, inner2);
        TransTreeVoid innerFor = forStmt(innerBody, constant(0), constant(10), constant(1), d, false, true,
                Tree.NoComment);

        TransTreeVoid outer3 = arrayPut(load(f), load(e), constant(3), Tree.NoComment);
        TransTreeVoid outer4 = store(f, load(g), Tree.NoComment);

        TransTreeVoid outerBody = sequential(outer1, outer2, innerFor, outer3, outer4);
        TransTreeVoid outerFor = forStmt(outerBody, constant(0), constant(10), constant(1), e, false, true,
                Tree.NoComment);
        TransTreeVoid tFinal = arrayPut(load(f), load(a), constant(4), Tree.NoComment);
        TransTreeVoid t = sequential(init1, init2, init3, outerFor, tFinal);

        // Construct Tracking
        VariableTracking vt = t.getVariableTracking();

        // Test innerBody
        ScopedVarSet m = vt.inScopeVars(innerBody);
        Set<VariableDescription<?>> keys = m.getVars();
        assertEquals(9, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, d, innerFor.id, innerFor.id);
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, inner2.id, outer4.id);
        testVarDef(m, g, init2.id, outer2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(innerBody);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, d, innerFor.id, innerFor.id);
        testVarDef(m, h, init3.id, init3.id);

        Set<VariableDescription<?>> written = vt.written(innerBody);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        Set<VariableDescription<?>> arraysModified = vt.arraysModified(innerBody);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(k));

        // Test innerFor
        m = vt.inScopeVars(innerFor);
        keys = m.getVars();
        assertEquals(8, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, outer4.id);
        testVarDef(m, g, init2.id, outer2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(innerFor);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, h, init3.id, init3.id);

        written = vt.written(innerFor);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(innerFor);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(k));

        // Test outer3
        m = vt.inScopeVars(outer3);
        keys = m.getVars();
        assertEquals(8, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, inner2.id, outer4.id);
        testVarDef(m, g, init2.id, outer2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(outer3);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, inner2.id, outer4.id);

        written = vt.written(outer3);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(outer3);
        assertEquals(5, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));
        assertTrue(arraysModified.contains(k));

        // Test outer4
        m = vt.inScopeVars(outer4);
        keys = m.getVars();
        assertEquals(8, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, inner2.id, outer4.id);
        testVarDef(m, g, init2.id, outer2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(outer4);
        keys = m.getVars();
        assertEquals(1, keys.size());
        testVarDef(m, g, init2.id, outer2.id);

        written = vt.written(outer4);
        assertEquals(1, written.size());
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(outer4);
        assertEquals(0, arraysModified.size());

        // Test outerBody
        m = vt.inScopeVars(outerBody);
        keys = m.getVars();
        assertEquals(8, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, outer4.id);
        testVarDef(m, g, init2.id, init2.id, outer2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(outerBody);
        keys = m.getVars();
        assertEquals(3, keys.size());
        testVarDef(m, e, outerFor.id, outerFor.id);
        testVarDef(m, f, init1.id, init1.id, outer4.id);
        testVarDef(m, h, init3.id, init3.id);

        written = vt.written(outerBody);
        assertEquals(2, written.size());
        assertTrue(written.contains(g));
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(outerBody);
        assertEquals(5, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));
        assertTrue(arraysModified.contains(k));

        // Test outerFor
        m = vt.inScopeVars(outerFor);
        keys = m.getVars();
        assertEquals(7, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, f, init1.id, init1.id);
        testVarDef(m, g, init2.id, init2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(outerFor);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, f, init1.id, init1.id);
        testVarDef(m, h, init3.id, init3.id);

        written = vt.written(outerFor);
        assertEquals(2, written.size());
        assertTrue(written.contains(g));
        assertTrue(written.contains(f));

        arraysModified = vt.arraysModified(outerFor);
        assertEquals(5, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(h));
        assertTrue(arraysModified.contains(i));
        assertTrue(arraysModified.contains(k));

        // Test tFinal
        m = vt.inScopeVars(tFinal);
        keys = m.getVars();
        assertEquals(7, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, f, init1.id, init1.id, outer4.id);
        testVarDef(m, g, init2.id, init2.id, outer2.id);
        testVarDef(m, h, init3.id, init3.id);
        testVarDef(m, i, TreeID.global, TreeID.global);
        testVarDef(m, j, TreeID.global, TreeID.global);
        testVarDef(m, k, TreeID.global, TreeID.global);

        m = vt.readVars(tFinal);
        keys = m.getVars();
        assertEquals(2, keys.size());
        testVarDef(m, a, TreeID.global, TreeID.global);
        testVarDef(m, f, init1.id, init1.id, outer4.id);

        written = vt.written(tFinal);
        assertEquals(0, written.size());

        arraysModified = vt.arraysModified(tFinal);
        assertEquals(3, arraysModified.size());
        assertTrue(arraysModified.contains(f));
        assertTrue(arraysModified.contains(g));
        assertTrue(arraysModified.contains(i));
    }

    public void testVarDef(ScopedVarSet m, VariableDescription<?> desc, TreeID declarationID,
            TreeID... expectedWriteLocations) {
        assertTrue(m.containsVar(desc));
        VarDef v = m.getVarDef(desc);
        assertEquals(TreeID.global == declarationID, v.isGlobal());
        assertEquals(declarationID, v.declarationId);
        Set<TreeID> writeLocations = v.writeLocations();
        assertEquals(expectedWriteLocations.length, writeLocations.size());
        for(TreeID id:expectedWriteLocations)
            assertTrue(writeLocations.contains(id));
    }
}
