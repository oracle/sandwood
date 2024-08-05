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

import org.sandwood.compiler.names.PackageName;

public abstract class OutputSandwoodClass {
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

    public abstract String getName();

    public abstract PackageName getPackage();

    public abstract void toJava(StringBuilder sb);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.toJava(sb);
        return sb.toString();
    }
}
