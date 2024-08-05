/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Set;

import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.outputTree.OutputTree.OutputTreeType;

public class OutputConstructorFunction extends OutputFunction {

    private final OutputTree body;

    OutputConstructorFunction(Visibility visibility, FunctionName constructorName, ArgDesc<?>[] args, OutputTree body,
            String comment) {
        super(visibility, constructorName, args, false, comment);
        this.body = body;
        body.setScopeStart();
    }

    @Override
    public void toJava(StringBuilder sb, int indent, MethodLocation loc, Set<String> requiredImports) {
        if(loc == MethodLocation.CLASS) {
            if(OutputTree.includeComments) {
                sb.append("\n");
                generateComment(sb, indent, comment);
            } else
                sb.append("\n");

            addIndent(sb, indent);
            if(visibility == Visibility.DEFAULT)
                sb.append(name + "(");
            else
                sb.append(visibility + " " + name + "(");
            boolean first = true;
            for(ArgDesc<?> arg:args) {
                if(first)
                    first = false;
                else
                    sb.append(", ");
                sb.append(arg.varDesc.type.getJavaType(requiredImports) + " " + arg.varDesc.name);
            }
            sb.append(") {");

            if(body.type != OutputTreeType.NOP) {
                sb.append("\n");
                addIndent(sb, indent + 1);
                body.toJava(sb, indent + 1, requiredImports);
                if(!body.terminal)
                    sb.append(";\n");
                addIndent(sb, indent);
            }

            sb.append("}\n");
        }
    }
}
