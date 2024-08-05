/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables;

import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;

/**
 * A class to hold variable names. This was created to allow better typing in rest of the compiler, but has been
 * extended with the parameter generated to say if the name was auto generated, or is based on a name in the model. this
 * is used to improve comments, and could be used to control substitutions if we decided that some substitutions should
 * be restricted.
 * <p>
 * If we ever move to Java 17 this should be replaced with a record.
 * 
 * @author djgoodma
 *
 */
public class VariableDescription<A extends Variable<A>> implements Comparable<VariableDescription<?>> {
    /**
     * The name of the variable.
     */
    public final VariableName name;
    /**
     * What is the type of this variable, This can be null if the type is not a model type. For example if it is a Map
     * in the wrapper class.
     */
    public final Type<A> type;

    public VariableDescription(String name, Type<A> type, boolean comment) {
        this(new VariableName(name, comment), type);
    }

    public VariableDescription(VariableName name, Type<A> type) {
        this.name = name;
        if(type == null)
            throw new NullPointerException();
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = name.hashCode();
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        VariableDescription<?> other = (VariableDescription<?>) obj;
        if(type == null)
            if(other.type != null)
                return false;
        return name.equals(other.name) && type.equals(other.type);
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public int compareTo(VariableDescription<?> o) {
        return name.compareTo(o.name);
    }
}
