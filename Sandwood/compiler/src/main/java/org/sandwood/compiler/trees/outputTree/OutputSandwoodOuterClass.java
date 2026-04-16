/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.PackageName;

public abstract class OutputSandwoodOuterClass extends OutputSandwoodClass {

    public OutputSandwoodOuterClass(PackageName packageName, ClassName name, ClassName extended, List<ClassName> generics,
            List<ClassName> interfaces, List<OutputSandwoodInnerClass> innerClasses) {
        super(packageName, name, Map.of(extended, generics), interfaces, innerClasses);
    }
    
    public OutputSandwoodOuterClass(PackageName packageName, ClassName name, Map<ClassName, List<ClassName>> extendeds,
            List<ClassName> interfaces, List<OutputSandwoodInnerClass> innerClasses) {
        super(packageName, name, extendeds, interfaces, innerClasses);
    }

    public OutputSandwoodOuterClass(PackageName packageName, ClassName name, List<ClassName> interfaces,
            List<OutputSandwoodInnerClass> innerClasses) {
        super(packageName, name, Collections.emptyMap(), interfaces, innerClasses);
    }
    
    public String toFile(String targetDir) throws IOException {
        PackageName packageName = getPackage();
        String name = getName();

        StringBuilder sb = new StringBuilder();
        toJava(sb);

        if(packageName != null)
            targetDir = targetDir + File.separator + packageName.getName().replace('.', File.separatorChar);

        File dir = new File(targetDir);
        dir.mkdirs();

        File file = new File(targetDir + File.separator + name + ".java");

        if(!file.exists())
            file.createNewFile();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(sb.toString());
        }

        return file.getAbsolutePath();
    }
}
