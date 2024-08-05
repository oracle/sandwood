/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.scalarVariables;

import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Add;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.ConstructNumericInput;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Divide;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Eq;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.IntToDouble;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.LessThan;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.LessThanEqual;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Multiply;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.NamedNumericVariable;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Negate;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Remainder;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Subtract;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.constant.ConstantIntTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.exceptions.ConstraintAlreadySetException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

public class IntVariable extends NumberVariable<IntVariable> {

    private IntVariable(NumberProducingDataflowTask<IntVariable> parent) {
        super(parent);
    }

    @Override
    public void observe(IntVariable source, Location location) throws ConstraintAlreadySetException {
        ObserveVariableTask<IntVariable> observation = new ObserveVariableTask<>(this, source, location);
        addConstraint(observation);
    }

    @Override
    public IntVariable getCurrentInstance() {
        return this;
    }

    public IntVariable add(int a) {
        return Add.add(this, a);
    }

    public IntVariable add(int a, Location location) {
        return Add.add(this, a, location);
    }

    public DoubleVariable add(double a) {
        return Add.add(this, a);
    }

    public DoubleVariable add(double a, Location location) {
        return Add.add(this, a, location);
    }

    public IntVariable add(IntVariable a) {
        return Add.add(this, a);
    }

    public IntVariable add(IntVariable a, Location location) {
        return Add.add(this, a, location);
    }

    public DoubleVariable add(DoubleVariable a) {
        return Add.add(this, a);
    }

    public DoubleVariable add(DoubleVariable a, Location location) {
        return Add.add(this, a, location);
    }

    public IntVariable subtract(int a) {
        return Subtract.subtract(this, a);
    }

    public IntVariable subtract(int a, Location location) {
        return Subtract.subtract(this, a, location);
    }

    public DoubleVariable subtract(double a) {
        return Subtract.subtract(this, a);
    }

    public DoubleVariable subtract(double a, Location location) {
        return Subtract.subtract(this, a, location);
    }

    public IntVariable subtract(IntVariable a) {
        return Subtract.subtract(this, a);
    }

    public IntVariable subtract(IntVariable a, Location location) {
        return Subtract.subtract(this, a, location);
    }

    public DoubleVariable subtract(DoubleVariable a) {
        return Subtract.subtract(this, a);
    }

    public DoubleVariable subtract(DoubleVariable a, Location location) {
        return Subtract.subtract(this, a, location);
    }

    public IntVariable times(int a) {
        return Multiply.multiply(this, a);
    }

    public IntVariable times(int a, Location location) {
        return Multiply.multiply(this, a, location);
    }

    public DoubleVariable times(double a) {
        return Multiply.multiply(this, a);
    }

    public DoubleVariable times(double a, Location location) {
        return Multiply.multiply(this, a, location);
    }

    public IntVariable times(IntVariable a) {
        return Multiply.multiply(this, a);
    }

    public IntVariable times(IntVariable a, Location location) {
        return Multiply.multiply(this, a, location);
    }

    public DoubleVariable times(DoubleVariable a) {
        return Multiply.multiply(this, a);
    }

    public DoubleVariable times(DoubleVariable a, Location location) {
        return Multiply.multiply(this, a, location);
    }

    public IntVariable divide(int a) {
        return Divide.divide(this, a);
    }

    public IntVariable divide(int a, Location location) {
        return Divide.divide(this, a, location);
    }

    public DoubleVariable divide(double a) {
        return Divide.divide(this, a);
    }

    public DoubleVariable divide(double a, Location location) {
        return Divide.divide(this, a, location);
    }

    public IntVariable divide(IntVariable a) {
        return Divide.divide(this, a);
    }

    public IntVariable divide(IntVariable a, Location location) {
        return Divide.divide(this, a, location);
    }

    public DoubleVariable divide(DoubleVariable a) {
        return Divide.divide(this, a);
    }

    public DoubleVariable divide(DoubleVariable a, Location location) {
        return Divide.divide(this, a, location);
    }

    public IntVariable remainder(IntVariable i) {
        return Remainder.remainder(this, i);
    }

    public IntVariable remainder(IntVariable i, Location location) {
        return Remainder.remainder(this, i, location);
    }

    public DoubleVariable remainder(DoubleVariable d) {
        return Remainder.remainder(this, d);
    }

    public DoubleVariable remainder(DoubleVariable d, Location location) {
        return Remainder.remainder(this, d, location);
    }

    public IntVariable remainder(int i) {
        return Remainder.remainder(this, i);
    }

    public IntVariable remainder(int i, Location location) {
        return Remainder.remainder(this, i, location);
    }

    public DoubleVariable remainder(double d) {
        return Remainder.remainder(this, d);
    }

    public DoubleVariable remainder(double d, Location location) {
        return Remainder.remainder(this, d, location);
    }

    public IntVariable negate() {
        return Negate.negate(this);
    }

    public IntVariable negate(Location location) {
        return Negate.negate(this, location);
    }

    public DoubleVariable castToDouble() {
        return IntToDouble.intToDouble(this);
    }

    public DoubleVariable castToDouble(Location location) {
        return IntToDouble.intToDouble(this, location);
    }

    public BooleanVariable lessThan(double d) {
        return LessThan.lessThan(this, d);
    }

    public BooleanVariable lessThan(double d, Location location) {
        return LessThan.lessThan(this, d, location);
    }

    public BooleanVariable lessThan(int i) {
        return LessThan.lessThan(this, i);
    }

    public BooleanVariable lessThan(int i, Location location) {
        return LessThan.lessThan(this, i, location);
    }

    public <V extends NumberVariable<V>> BooleanVariable lessThan(NumberVariable<V> v) {
        return LessThan.lessThan(this, v);
    }

    public <V extends NumberVariable<V>> BooleanVariable lessThan(NumberVariable<V> v, Location location) {
        return LessThan.lessThan(this, v, location);
    }

    public BooleanVariable lessThanEqual(double d) {
        return LessThanEqual.lessThanEqual(this, d);
    }

    public BooleanVariable lessThanEqual(double d, Location location) {
        return LessThanEqual.lessThanEqual(this, d, location);
    }

    public BooleanVariable lessThanEqual(int i) {
        return LessThanEqual.lessThanEqual(this, i);
    }

    public BooleanVariable lessThanEqual(int i, Location location) {
        return LessThanEqual.lessThanEqual(this, i, location);
    }

    public <V extends NumberVariable<V>> BooleanVariable lessThanEqual(NumberVariable<V> v) {
        return LessThanEqual.lessThanEqual(this, v);
    }

    public <V extends NumberVariable<V>> BooleanVariable lessThanEqual(NumberVariable<V> v, Location location) {
        return LessThanEqual.lessThanEqual(this, v, location);
    }

    public BooleanVariable greaterThan(double d) {
        return LessThan.lessThan(d, this);
    }

    public BooleanVariable greaterThan(double d, Location location) {
        return LessThan.lessThan(d, this, location);
    }

    public BooleanVariable greaterThan(int i) {
        return LessThan.lessThan(i, this);
    }

    public BooleanVariable greaterThan(int i, Location location) {
        return LessThan.lessThan(i, this, location);
    }

    public <V extends NumberVariable<V>> BooleanVariable greaterThan(NumberVariable<V> v) {
        return LessThan.lessThan(v, this);
    }

    public <V extends NumberVariable<V>> BooleanVariable greaterThan(NumberVariable<V> v, Location location) {
        return LessThan.lessThan(v, this, location);
    }

    public BooleanVariable greaterThanEqual(double d) {
        return LessThanEqual.lessThanEqual(d, this);
    }

    public BooleanVariable greaterThanEqual(double d, Location location) {
        return LessThanEqual.lessThanEqual(d, this, location);
    }

    public BooleanVariable greaterThanEqual(int i) {
        return LessThanEqual.lessThanEqual(i, this);
    }

    public BooleanVariable greaterThanEqual(int i, Location location) {
        return LessThanEqual.lessThanEqual(i, this, location);
    }

    public <V extends NumberVariable<V>> BooleanVariable greaterThanEqual(NumberVariable<V> v) {
        return LessThanEqual.lessThanEqual(this, v);
    }

    public <V extends NumberVariable<V>> BooleanVariable greaterThanEqual(NumberVariable<V> v, Location location) {
        return LessThanEqual.lessThanEqual(this, v, location);
    }

    public BooleanVariable eq(IntVariable v, Location location) {
        return Eq.eq(this, v, location);
    }

    public BooleanVariable eq(DoubleVariable v, Location location) {
        return Eq.eq(this, v, location);
    }

    public BooleanVariable eq(IntVariable v) {
        return Eq.eq(this, v);
    }

    public BooleanVariable eq(DoubleVariable v) {
        return Eq.eq(this, v);
    }

    public BooleanVariable notEq(IntVariable v, Location location) {
        return Eq.eq(this, v, location).negate(location);
    }

    public BooleanVariable notEq(DoubleVariable v, Location location) {
        return Eq.eq(this, v, location).negate(location);
    }

    /* Factory methods for construction */
    public static IntVariable intVariable(int v) {
        return intVariable(v, null);
    }

    public static IntVariable intVariable(int v, Location location) {
        return intVariable(new ConstantIntTask(v, location));
    }

    public static IntVariable intVariable(NumberProducingDataflowTask<IntVariable> parent) {
        return new IntVariable(parent);
    }

    public static IntVariable intVariable(String fieldname) {
        Location location = null;
        return intVariable(fieldname, location);
    }

    public static IntVariable intVariable(String fieldname, Location location) {
        ConstructNumericInput<IntVariable> o = new ConstructNumericInput<>(VariableType.IntVariable, fieldname,
                location);
        return new IntVariable(o);
    }

    public static IntVariable intVariable(String fieldname, String comment) {
        return intVariable(fieldname, comment, null);
    }

    public static IntVariable intVariable(String fieldname, String comment, Location location) {
        ConstructNumericInput<IntVariable> o = new ConstructNumericInput<>(VariableType.IntVariable, fieldname,
                location);
        IntVariable i = new IntVariable(o);
        i.setComment(comment);
        return i;
    }

    public static IntVariable namedInt(VariableDescription<IntVariable> varDesc, Scope scope) {
        ScopeStack.pushScope(scope);
        NamedNumericVariable<IntVariable> nv = new NamedNumericVariable<>(varDesc);
        IntVariable i = new IntVariable(nv);
        ScopeStack.popScope(scope);
        return i;
    }
}
