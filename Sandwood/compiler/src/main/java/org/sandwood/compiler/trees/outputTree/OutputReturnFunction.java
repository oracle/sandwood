/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;

public class OutputReturnFunction<A extends Variable<A>> extends OutputFunction {
    private final Type<A> returnType;
    private final OutputTreeReturn<A> body;

    OutputReturnFunction(Visibility visibility, Type<A> returnType, FunctionName name, ArgDesc<?>[] args,
            OutputTreeReturn<A> body, boolean override, String comment) {
        super(visibility, name, args, override, comment);
        this.returnType = returnType;
        this.body = body;
        body.setScopeStart();
    }

    @Override
    public void toJava(StringBuilder sb, int indent, MethodLocation loc, Set<String> requiredImports) {
        if(loc == MethodLocation.CLASS || (visibility != Visibility.PRIVATE && !override)) {
            if(OutputTree.includeComments) {
                sb.append("\n");
                generateComment(sb, indent, comment);
            } else if(loc == MethodLocation.CLASS)
                sb.append("\n");

            if(override || (visibility != Visibility.PRIVATE && loc == MethodLocation.CLASS)) {
                addIndent(sb, indent);
                sb.append("@Override\n");
            }

            addIndent(sb, indent);
            assert (visibility != Visibility.DEFAULT);
            sb.append(visibility + " ");

            if(loc == MethodLocation.CLASS)
                sb.append("final ");

            sb.append(returnType.getJavaType(requiredImports) + " " + name + "(");

            boolean first = true;
            for(ArgDesc<?> arg:args) {
                if(first)
                    first = false;
                else
                    sb.append(", ");
                sb.append(arg.varDesc.type.getJavaType() + " " + arg.varDesc.name);
            }

            sb.append(")");
            if(loc == MethodLocation.INTERFACE)
                sb.append(";");
            else {
                sb.append(" {\n");

                // Single statement to return; //TODO make this work with trees that have more
                // than one statement.
                addIndent(sb, indent + 1);
                sb.append("return ");
                body.toJava(sb, indent + 1, requiredImports);
                sb.append(";\n");

                addIndent(sb, indent);
                sb.append("}");
            }
            sb.append("\n");
        }
    }
}
