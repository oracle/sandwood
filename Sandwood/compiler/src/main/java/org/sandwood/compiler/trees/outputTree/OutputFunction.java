/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;

public abstract class OutputFunction {

    public final FunctionName name;
    public final ArgDesc<?>[] args;
    public final Visibility visibility;
    public final boolean override;
    public final String comment;

    protected OutputFunction(Visibility visibility, FunctionName name, ArgDesc<?>[] args, boolean override,
            String comment) {
        this.visibility = visibility;
        this.name = name;
        this.args = args;
        this.override = override;
        this.comment = comment;
    }

    protected void addIndent(StringBuilder sb, int indent) {
        OutputTree.addIndent(sb, indent);
    }

    public void generateComment(StringBuilder sb, int indent, String comment) {
        if(comment != Tree.NoComment) {
            String[] parts = comment.split("\n");

            // Split out bits that already have a new line.
            for(String part:parts) {
                addIndent(sb, indent);
                sb.append("//");
                int counter = 2;

                // For each of these split them into 80 character lengths.
                String[] words = part.split(" ");
                for(String word:words) { // If the comment is more than 80 characters long start a new line.
                    if(counter > 80) {
                        sb.append("\n");
                        addIndent(sb, indent);
                        sb.append("//");
                        counter = 2;
                    }

                    sb.append(" " + word);
                    counter += word.length() + 1;
                }
                sb.append("\n");
            }
        }
    }

    public void toJava(StringBuilder sb, int indent) {
        toJava(sb, indent, MethodLocation.CLASS, new HashSet<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toJava(sb, 0, MethodLocation.CLASS, new HashSet<>());
        return sb.toString();
    }

    public abstract void toJava(StringBuilder sb, int indent, MethodLocation loc, Set<String> requiredImports);
}
