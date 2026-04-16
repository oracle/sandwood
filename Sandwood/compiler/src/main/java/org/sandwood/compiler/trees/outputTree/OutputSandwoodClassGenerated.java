/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.util.StringUtil;

public class OutputSandwoodClassGenerated extends OutputSandwoodOuterClass {

    public static OutputSandwoodClassGenerated getClass(ExecutionType target, ModelClassName name,
            PackageName packageName, List<OutputFunction> functions, OutputTree fieldsTree, String modelCode,
            List<OutputFunction> gettersAndSetters) {

        Map<ClassName, List<ClassName>> extended = Map.of(ClassName.coreBase(target), List.of());
        List<ClassName> interfaces = List.of(name.interfaceName());

        return new OutputSandwoodClassGenerated(name.backendName(target), packageName, extended, interfaces, functions,
                fieldsTree, modelCode, gettersAndSetters);
    }

    private final String modelCode;
    // Variable field name |-> Field Descriptor
    private final List<OutputFunction> functions;
    private final OutputTree fieldsTree;
    private final List<OutputFunction> gettersAndSetters;

    private OutputSandwoodClassGenerated(ClassName name, PackageName packageName,
            Map<ClassName, List<ClassName>> extended, List<ClassName> interfaces,
            List<OutputFunction> functions, OutputTree fieldsTree, String modelCode,
            List<OutputFunction> gettersAndSetters) {
        super(packageName, name, extended, interfaces, List.of());
        this.functions = functions;
        // Normalize newline characters
        this.modelCode = StringUtil.normalizeNewLines(modelCode);
        this.fieldsTree = fieldsTree;
        this.gettersAndSetters = gettersAndSetters;

    }

    public List<OutputFunction> getFunctions() {
        return functions;
    }

    public List<OutputFunction> getGettersAndSetters() {
        return gettersAndSetters;
    }

    @Override
    protected void addDeclaration(StringBuilder sb) {
        sb.append("final class ");
    }

    @Override
    public void toJavaBody(StringBuilder sb, int indent, Set<String> requiredImports) {

        fieldsTree.toJava(sb, indent, requiredImports);

        addConstructor(sb, indent, requiredImports);

        for(OutputFunction f:gettersAndSetters)
            f.toJava(sb, 1, MethodLocation.CLASS, requiredImports);

        for(OutputFunction f:functions)
            f.toJava(sb, 1, MethodLocation.CLASS, requiredImports);

        sb.append("\n");

        addModelCode(sb, indent);
    }

    private void addConstructor(StringBuilder sb, int indent, Set<String> requiredImports) {
        requiredImports.add("org.sandwood.runtime.model.ExecutionTarget");
        sb.append("\n");
        addIndent(sb, indent);
        sb.append("public " + getName() + "(ExecutionTarget target) {\n");
        addIndent(sb, indent + 1);
        sb.append("super(target);\n");
        addIndent(sb, indent);
        sb.append("}\n");
    }

    /**
     * Method to add in the required boilerplate code for random. We may want to make this an IR later, but for now I
     * cannot think of a good reason not to just keep it as a string.
     */
    private void addModelCode(StringBuilder sb, int indent) {
        addIndent(sb, indent);
        sb.append("@Override\n");
        addIndent(sb, indent);
        sb.append("public String modelCode() {\n");
        addIndent(sb, indent + 1);
        if(modelCode.equals(""))
            sb.append("return null;");
        else {
            sb.append("return ");
            processInputString(sb, StringUtil.escapeSpecialCharacters(modelCode), indent + 1);
            sb.append(";\n");
        }
        addIndent(sb, indent);
        sb.append("}\n");
    }

    private void processInputString(StringBuilder sb, String input, int indent) {
        String[] parts = input.split("\\\\n", -1);
        sb.append("\"" + parts[0]);
        for(int i = 1; i < parts.length; i++) {
            sb.append("\\n\"\n");
            addIndent(sb, indent);
            sb.append("     + \"" + parts[i]);
        }
        sb.append("\"");
    }

    @Override
    public String getJavaDocComment() {
        return Tree.NoComment;
    }
}
