/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.util.StringUtil;

public class OutputSandwoodClassGenerated extends OutputSandwoodClass {

    private final ClassName name;
    private final ClassName extendedClass;
    private final ClassName[] interfaces;
    private final PackageName packageName;
    private final String modelCode;
    // Variable field name |-> Field Descriptor
    private final List<OutputFunction> functions;
    private final OutputTree fieldsTree;
    private final List<OutputFunction> gettersAndSetters;

    public OutputSandwoodClassGenerated(ClassName name, PackageName packageName, ClassName extendedClass,
            ClassName[] interfaces, List<OutputFunction> functions, OutputTree fieldsTree, String modelCode,
            List<OutputFunction> gettersAndSetters) {
        this.name = name;
        this.packageName = packageName;
        this.extendedClass = extendedClass;
        this.interfaces = interfaces;
        this.functions = functions;
        // Normalize newline characters
        this.modelCode = StringUtil.normalizeNewLines(modelCode);
        this.fieldsTree = fieldsTree;
        this.gettersAndSetters = gettersAndSetters;

    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public PackageName getPackage() {
        return packageName;
    }

    public List<OutputFunction> getFunctions() {
        return functions;
    }

    public List<OutputFunction> getGettersAndSetters() {
        return gettersAndSetters;
    }

    @Override
    public void toJava(StringBuilder sb) {
        Set<String> requiredImports = new HashSet<>();

        if(packageName != null)
            sb.append("package " + packageName + ";\n\n");

        StringBuilder bodySB = new StringBuilder();

        bodySB.append("class " + name);

        if(extendedClass != null)
            bodySB.append(" extends " + extendedClass);

        if(interfaces.length != 0) {
            bodySB.append(" implements " + interfaces[0]);
            for(int i = 1; i < interfaces.length; i++)
                bodySB.append(", " + interfaces[i]);
        }

        bodySB.append(" {\n");

        bodySB.append("\t");
        fieldsTree.toJava(bodySB, 1, requiredImports);

        addConstructor(bodySB);

        for(OutputFunction f:gettersAndSetters)
            f.toJava(bodySB, 1, MethodLocation.CLASS, requiredImports);

        for(OutputFunction f:functions)
            f.toJava(bodySB, 1, MethodLocation.CLASS, requiredImports);

        bodySB.append("\n");

        addModelCode(bodySB);

        bodySB.append("}");

        addImports(sb, requiredImports);
        sb.append(bodySB);
    }

    private void addImports(StringBuilder sb, Set<String> requiredImports) {
        requiredImports.add("org.sandwood.runtime.model.ExecutionTarget");
        PriorityQueue<String> p = new PriorityQueue<>(requiredImports);
        while(!p.isEmpty())
            sb.append("import " + p.poll() + ";\n");
        sb.append("\n");
    }

    private void addConstructor(StringBuilder sb) {
        sb.append("\n\tpublic " + name + "(ExecutionTarget target) {\n\t\tsuper(target);\n\t}\n");
    }

    /**
     * Method to add in the required boilerplate code for random. We may want to make this an IR later, but for now I
     * cannot think of a good reason not to just keep it as a string.
     */
    private void addModelCode(StringBuilder sb) {
        sb.append("\t@Override\n\tpublic String modelCode() {\n\t\treturn "
                + ((modelCode.equals("")) ? "null" : "\"" + StringUtil.escapeSpecialCharacters(modelCode) + "\"")
                + ";\n\t}\n");
    }
}
