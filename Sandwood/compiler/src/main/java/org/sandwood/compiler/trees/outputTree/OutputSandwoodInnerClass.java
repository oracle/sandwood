/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
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

public class OutputSandwoodInnerClass extends OutputSandwoodClass {

    // Variable field name |-> Field Descriptor
    private final OutputTree fieldsTree;
    private final List<OutputFunction> functions;

    public OutputSandwoodInnerClass(ClassName name, ClassName extended, List<ClassName> interfaces, List<OutputSandwoodInnerClass> innerClasses,
            OutputTree fieldsTree, List<OutputFunction> functions) {
        super(PackageName.emptyName, name, Map.of(extended, Collections.emptyList()), interfaces, innerClasses);
        // Normalize newline characters
        this.fieldsTree = fieldsTree;
        this.functions = functions;
    }
    
    public OutputSandwoodInnerClass(ClassName name, List<ClassName> interfaces, List<OutputSandwoodInnerClass> innerClasses,
            OutputTree fieldsTree, List<OutputFunction> functions) {
        super(PackageName.emptyName, name, Collections.emptyMap(), interfaces, innerClasses);
        // Normalize newline characters
        this.fieldsTree = fieldsTree;
        this.functions = functions;
    }

    public List<OutputFunction> getGettersAndSetters() {
        return functions;
    }

    @Override
    protected void addDeclaration(StringBuilder sb) {
        sb.append("final class ");
    }

    @Override
    protected void toJavaBody(StringBuilder sb, int indent, Set<String> requiredImports) {
        fieldsTree.toJava(sb, indent, requiredImports);

        for(OutputFunction f:functions)
            f.toJava(sb, indent, MethodLocation.CLASS, requiredImports);
    }

    @Override
    public String getJavaDocComment() {
        return Tree.NoComment;
    }
}
