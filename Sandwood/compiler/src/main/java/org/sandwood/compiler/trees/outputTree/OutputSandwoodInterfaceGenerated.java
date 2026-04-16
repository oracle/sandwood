/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.trees.Tree;

public class OutputSandwoodInterfaceGenerated extends OutputSandwoodOuterClass {

    public static OutputSandwoodInterfaceGenerated getInterface(OutputSandwoodClassGenerated source,
            PackageName packageName, ClassName name) {
        Map<ClassName, List<ClassName>> extended = Map.of(ClassName.coreBase, Collections.emptyList());



        return new OutputSandwoodInterfaceGenerated(source, packageName, name, extended);
    }
    
    // Variable field name |-> Field Descriptor
    private final List<OutputFunction> functions;
    private final List<OutputFunction> gettersAndSetters;

    public OutputSandwoodInterfaceGenerated(OutputSandwoodClassGenerated source, PackageName packageName, ClassName name,
            Map<ClassName, List<ClassName>> extended) {
        super(packageName, name, extended, Collections.emptyList(), Collections.emptyList());
        functions = source.getFunctions();
        gettersAndSetters = source.getGettersAndSetters();
    }

    @Override
    protected void addDeclaration(StringBuilder sb) {
        sb.append("interface ");
    }

    @Override
    protected void toJavaBody(StringBuilder sb, int indent, Set<String> requiredImports) {
        for(OutputFunction f:gettersAndSetters)
            f.toJava(sb, indent, MethodLocation.INTERFACE, Collections.emptySet());

        for(OutputFunction f:functions)
            f.toJava(sb, indent, MethodLocation.INTERFACE, Collections.emptySet());
    }

    @Override
    public String getJavaDocComment() {
        return Tree.NoComment;
    }
}
