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

import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;

public class OutputSandwoodInterfaceGenerated extends OutputSandwoodClass {

    private final PackageName packageName;
    private final ClassName name;
    private final ClassName[] interfaces;
    // Variable field name |-> Field Descriptor
    private final List<OutputFunction> functions;
    private final List<OutputFunction> gettersAndSetters;

    public OutputSandwoodInterfaceGenerated(OutputSandwoodClassGenerated source, ClassName name,
            PackageName packageName, ClassName... interfaces) {
        this.name = name;
        this.packageName = packageName;
        this.interfaces = interfaces;
        functions = source.getFunctions();
        gettersAndSetters = source.getGettersAndSetters();
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public PackageName getPackage() {
        return packageName;
    }

    @Override
    public void toJava(StringBuilder sb) {
        if(!packageName.isEmpty())
            sb.append("package " + packageName + ";\n\n");

        sb.append("interface " + name);

        if(interfaces.length != 0) {
            sb.append(" extends " + interfaces[0]);
            for(int i = 1; i < interfaces.length; i++)
                sb.append(", " + interfaces[i]);
        }

        sb.append(" {\n");

        for(OutputFunction f:gettersAndSetters) {
            f.toJava(sb, 1, MethodLocation.INTERFACE, Collections.emptySet());
        }

        for(OutputFunction f:functions) {
            f.toJava(sb, 1, MethodLocation.INTERFACE, Collections.emptySet());
        }

        sb.append("}");
    }
}
