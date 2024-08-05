/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.names;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.exceptions.CompilerException;

public class FunctionName extends Name {

    private FunctionName(String name) {
        super(name);
        if(name == null)
            throw new CompilerException("Null function name set");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        FunctionName other = (FunctionName) obj;
        return name.equals(other.name);
    }

    public static FunctionName setterName(VariableName name) {
        return new FunctionName("set" + prefix + name);
    }

    public static FunctionName getterName(VariableName name) {
        return new FunctionName("get" + prefix + name);
    }

    public static FunctionName createFunctionName(String name) {
        return new FunctionName(name);
    }

    public static FunctionName createFunctionName(VariableName name) {
        return new FunctionName(name.getName());
    }

    public static FunctionName createFunctionName(VariableDescription<?> desc) {
        return new FunctionName(desc.name.getName());
    }

    public static FunctionName getConstructorName(ClassName className) {
        return new FunctionName(className.getName());
    }
}