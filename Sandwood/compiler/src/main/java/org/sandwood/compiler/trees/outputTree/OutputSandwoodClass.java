/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.trees.Tree;

public abstract class OutputSandwoodClass implements Comparable<OutputSandwoodClass> {

    private final PackageName packageName;
    private final ClassName name;
    private final Map<ClassName, List<ClassName>> extended;
    private final List<ClassName> interfaces;
    private final List<OutputSandwoodInnerClass> innerClasses;

    public OutputSandwoodClass(PackageName packageName, ClassName name, Map<ClassName, List<ClassName>> extended,
            List<ClassName> interfaces, List<OutputSandwoodInnerClass> innerClasses) {
        this.packageName = packageName;
        this.name = name;
        this.extended = extended;
        this.interfaces = interfaces;
        this.innerClasses = innerClasses;
    }

    public String getName() {
        return name.getName();
    }

    public PackageName getPackage() {
        return packageName;
    }

    public abstract String getJavaDocComment();

    public final void toJava(StringBuilder sb) {
        Set<String> requiredImports = new HashSet<>();

        setPackage(sb);

        StringBuilder bodySB = new StringBuilder();

        toJava(bodySB, 0, requiredImports);

        addImports(sb, requiredImports);
        sb.append(bodySB);
    }

    final void toJava(StringBuilder sb, int indent, Set<String> requiredImports) {
        addJavaDocComment(sb, indent, getJavaDocComment());
        addIndent(sb, indent);
        addDeclaration(sb);
        sb.append(getName());
        extendsDeclaration(sb, requiredImports);
        interfaceDeclaration(sb, requiredImports);
        sb.append(" {\n");

        for(OutputSandwoodInnerClass innerClass:innerClasses) {
            innerClass.toJava(sb, indent + 1, requiredImports);
            sb.append("\n\n");
        }

        toJavaBody(sb, indent + 1, requiredImports);

        addIndent(sb, indent);
        sb.append("}");
    }

    protected abstract void addDeclaration(StringBuilder sb);

    protected abstract void toJavaBody(StringBuilder sb, int indent, Set<String> requiredImports);

    protected void setPackage(StringBuilder sb) {
        if(!packageName.isEmpty())
            sb.append("package " + packageName + ";\n\n");
    }

    protected void extendsDeclaration(StringBuilder sb, Set<String> requiredImports) {
        if(extended.size() != 0) {
            boolean first = true;
            for(ClassName e:extended.keySet()) {
                if(first) {
                    sb.append(" extends ");
                    ClassName extendsName = processImport(e, requiredImports);
                    sb.append(extendsName);
                    genericsDeclaration(sb, extended.get(e), requiredImports);
                    first = false;
                } else {
                    ClassName extendsName = processImport(e, requiredImports);
                    sb.append(", " + extendsName);
                    genericsDeclaration(sb, extended.get(e), requiredImports);
                }
            }
        }
    }

    private void genericsDeclaration(StringBuilder sb, List<ClassName> generics, Set<String> requiredImports) {
        if(!generics.isEmpty()) {
            sb.append("<");

            boolean first = true;
            for(ClassName c:generics) {
                if(first)
                    first = false;
                else
                    sb.append(", ");
                ClassName extendsName = processImport(c, requiredImports);
                sb.append(extendsName);
            }

            sb.append(">");
        }
    }

    protected void interfaceDeclaration(StringBuilder sb, Set<String> requiredImports) {
        if(interfaces.size() != 0) {
            sb.append(" implements ");
            ClassName interfaceName = processImport(interfaces.get(0), requiredImports);
            sb.append(interfaceName);
            for(int j = 1; j < interfaces.size(); j++) {
                interfaceName = processImport(interfaces.get(j), requiredImports);
                sb.append(", " + j);
            }
        }
    }

    protected ClassName processImport(ClassName interfaceName, Set<String> requiredImports) {
        String name = interfaceName.getName();
        String[] parts = name.split("\\.");

        // Check if this is an internal class and the package is empty
        if(!interfaceName.useInImport)
            return interfaceName;

        // Special case for the internal classes when the package is empty.
        if(parts.length > 1) {
            requiredImports.add(name);
            return new ClassName(parts[parts.length - 1], false);
        } else
            return interfaceName;
    }

    private void addImports(StringBuilder sb, Set<String> requiredImports) {
        PriorityQueue<String> p = new PriorityQueue<>(requiredImports);

        // Check if this name is for the same package.
        while(!p.isEmpty()) {
            String requiredImport = p.poll();
            if(requiredImport.startsWith(packageName.getName())) {
                String remainder = requiredImport.substring(packageName.getName().length());
                String[] parts = remainder.split("\\.");
                // If this is a class in the same package the remainder will have been .name, and the array will be {"", "name" }
                if(!(parts.length == 2) || !parts[0].equals(""))
                    sb.append("import " + requiredImport + ";\n");
            }
            else 
                sb.append("import " + requiredImport + ";\n");
        }

        sb.append("\n");
    }

    protected void addJavaDocComment(StringBuilder sb, int indent, String comment) {
        if(comment != Tree.NoComment) {
            addIndent(sb, indent);
            OutputTree.addFormattedComment(sb, indent, comment, "/**", " *", " */");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toJava(sb);
        return sb.toString();
    }

    /**
     * Method to add an indent to a string buffer
     *
     * @param sb     StringBuffer to add the indent to.
     * @param indent Depth of indent to add.
     */
    protected static void addIndent(StringBuilder sb, int indent) {
        for(int i = 0; i < indent; i++)
            sb.append("\t");
    }

    @Override
    public int compareTo(OutputSandwoodClass o) {
        return name.compareTo(o.name);
    }
}