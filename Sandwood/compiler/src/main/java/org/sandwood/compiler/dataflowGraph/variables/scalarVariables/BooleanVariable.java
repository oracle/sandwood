/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.scalarVariables;

import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.And;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.ConstructInput;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.CopyBooleanTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Eq;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NamedVariable;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NegateBoolean;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Or;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant.ConstantBooleanTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.BooleanType;
import org.sandwood.compiler.exceptions.ConstraintAlreadySetException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class BooleanVariable extends ScalarVariable<BooleanVariable> {

    private BooleanVariable(ProducingDataflowTask<BooleanVariable> parent) {
        super(parent);
    }

    @Override
    public void observe(BooleanVariable source, Location location) throws ConstraintAlreadySetException {
        ObserveVariableTask<BooleanVariable> observation = new ObserveVariableTask<>(this, source, location);
        addConstraint(observation);
    }

    @Override
    public BooleanVariable getCurrentInstance() {
        return this;
    }

    public BooleanVariable and(boolean b) {
        return And.and(this, b);
    }

    public BooleanVariable and(boolean b, Location location) {
        return And.and(this, b, location);
    }

    public BooleanVariable and(BooleanVariable b) {
        return And.and(this, b);
    }

    public BooleanVariable and(BooleanVariable b, Location location) {
        return And.and(this, b, location);
    }

    public BooleanVariable or(boolean b) {
        return Or.or(this, b);
    }

    public BooleanVariable or(boolean b, Location location) {
        return Or.or(this, b, location);
    }

    public BooleanVariable or(BooleanVariable b) {
        return Or.or(this, b);
    }

    public BooleanVariable or(BooleanVariable b, Location location) {
        return Or.or(this, b, location);
    }

    public BooleanVariable negate() {
        return NegateBoolean.negate(this);
    }

    public BooleanVariable negate(Location location) {
        return NegateBoolean.negate(this, location);
    }

    public BooleanVariable eq(BooleanVariable v, Location location) {
        return Eq.eq(this, v, location);
    }

    public BooleanVariable eq(BooleanVariable v) {
        return Eq.eq(this, v);
    }

    public BooleanVariable notEq(BooleanVariable v, Location location) {
        return Eq.eq(this, v, location).negate(location);
    }

    public BooleanVariable notEq(BooleanVariable v) {
        return Eq.eq(this, v).negate();
    }

    @Override
    public BooleanVariable copy() {
        return booleanVariable(new CopyBooleanTask(this));
    }

    @Override
    public BooleanVariable copy(Location location) {
        return booleanVariable(new CopyBooleanTask(this, location));
    }

    /* Factory methods for construction */
    public static BooleanVariable booleanVariable(boolean b) {
        return booleanVariable(new ConstantBooleanTask(b, null));
    }

    public static BooleanVariable booleanVariable(boolean b, Location location) {
        return booleanVariable(new ConstantBooleanTask(b, location));
    }

    public static BooleanVariable booleanVariable(boolean b, Scope scope) {
        ScopeStack.pushScope(scope);
        BooleanVariable bv = booleanVariable(new ConstantBooleanTask(b, null));
        ScopeStack.popScope(scope);
        return bv;
    }

    public static BooleanVariable booleanVariable(ProducingDataflowTask<BooleanVariable> parent) {
        return new BooleanVariable(parent);
    }

    public static BooleanVariable booleanVariable(String fieldname) {
        Location location = null;
        return booleanVariable(fieldname, location);
    }

    public static BooleanVariable booleanVariable(String fieldname, Location location) {
        ConstructInput<BooleanVariable> o = new ConstructInput<>(VariableType.BooleanVariable, fieldname, location);
        return new BooleanVariable(o);
    }

    public static BooleanVariable booleanVariable(String fieldname, String comment) {
        return booleanVariable(fieldname, comment, null);
    }

    public static BooleanVariable booleanVariable(String fieldname, String comment, Location location) {
        ConstructInput<BooleanVariable> o = new ConstructInput<>(VariableType.BooleanVariable, fieldname, location);
        BooleanVariable b = new BooleanVariable(o);
        b.setComment(comment);
        return b;
    }

    public static BooleanVariable namedBoolean(VariableDescription<BooleanVariable> varDesc, Scope scope) {
        ScopeStack.pushScope(scope);
        NamedVariable<BooleanVariable> nv = new NamedVariable<>(varDesc);
        BooleanVariable b = new BooleanVariable(nv);
        ScopeStack.popScope(scope);
        return b;
    }

    @Override
    public BooleanType getType() {
        return VariableType.BooleanVariable;
    }
}
