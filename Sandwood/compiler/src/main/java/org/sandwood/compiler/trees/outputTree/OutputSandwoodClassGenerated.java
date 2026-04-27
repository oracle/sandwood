/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.compilation.CompilationContext.AuxFunctionType;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.util.StringUtil;

public class OutputSandwoodClassGenerated extends OutputSandwoodOuterClass {

    private static final class ScratchClass extends OutputSandwoodInnerClass {
        public ScratchClass(OutputFunction allocator, OutputTree fieldsTree) {
            super(ClassName.scratchClass, List.of(ClassName.scratchInterface), Collections.emptyList(), fieldsTree,
                    List.of(allocator));
        }
    }

    public static OutputSandwoodClassGenerated getClass(ExecutionType target, ModelClassName name, PackageName packageName,
            Map<FunctionName, OutputFunction> functions, OutputTree classFieldsTree,
            OutputTree scratchFieldsTree, String modelCode, List<OutputFunction> gettersAndSetters) {

        ClassName scratchName = ClassName.QualifiedName(packageName, name.backendName(target), ClassName.scratchClass);
        ClassName stateName = ClassName.QualifiedName(packageName, name, ClassName.stateClass);
        Map<ClassName, List<ClassName>> extended = Map.of(ClassName.coreBase(target), List.of(stateName, scratchName));
        ScratchClass scratch = new ScratchClass(functions.get(AuxFunctionType.SCRATCH_ALLOCATOR.functionName), scratchFieldsTree);

        return new OutputSandwoodClassGenerated(name.backendName(target), packageName, extended, functions, modelCode,
                gettersAndSetters, classFieldsTree, stateName, scratch);
    }

    private final ClassName stateName;
    private final String modelCode;
    // Variable field name |-> Field Descriptor
    private final Map<FunctionName, OutputFunction> functions;
    private final OutputTree classFieldsTree;
    private final List<OutputFunction> gettersAndSetters;

    private OutputSandwoodClassGenerated(ClassName name, PackageName packageName,
            Map<ClassName, List<ClassName>> extendedClass, Map<FunctionName, OutputFunction> functions,
            String modelCode, List<OutputFunction> gettersAndSetters, OutputTree classFieldTree, ClassName stateName, ScratchClass scratch) {
        super(packageName, name, extendedClass, Collections.emptyList(), List.of(scratch));
        this.stateName = stateName;
        this.classFieldsTree = classFieldTree;
        this.functions = functions;
        // Normalize newline characters
        this.modelCode = StringUtil.normalizeNewLines(modelCode);
        this.gettersAndSetters = gettersAndSetters;

    }

    public Map<FunctionName, OutputFunction> getFunctions() {
        return functions;
    }

    public List<OutputFunction> getGettersAndSetters() {
        return gettersAndSetters;
    }
    
    public OutputTree getClassFields() {
        return classFieldsTree;
    }

    @Override
    protected void addDeclaration(StringBuilder sb) {
        sb.append("final class ");
    }

    @Override
    public void toJavaBody(StringBuilder sb, int indent, Set<String> requiredImports) {
        addConstructor(sb, indent, requiredImports);

        // Convert the functions.
        PriorityQueue<FunctionName> samples = new PriorityQueue<>();
        PriorityQueue<FunctionName> aux = new PriorityQueue<>();

        Set<FunctionName> auxNames = new HashSet<>();
        for(AuxFunctionType t:AuxFunctionType.values())
            auxNames.add(t.functionName);

        for(FunctionName name:functions.keySet()) {
            if(auxNames.contains(name))
                aux.add(name);
            else
                samples.add(name);
        }

        while(!samples.isEmpty()) {
            FunctionName name = samples.poll();
            OutputFunction f = functions.get(name);
            f.toJava(sb, 1, MethodLocation.CLASS, requiredImports);
        }

        while(!aux.isEmpty()) {
            FunctionName name = aux.poll();
            if(name != AuxFunctionType.SCRATCH_ALLOCATOR.functionName
                    && name != AuxFunctionType.VAR_ALLOCATOR.functionName) {
                OutputFunction f = functions.get(name);
                f.toJava(sb, 1, MethodLocation.CLASS, requiredImports);
            }
        }

        sb.append("\n");

        addModelCode(sb, indent);
    }

    private void addConstructor(StringBuilder sb, int indent, Set<String> requiredImports) {
        requiredImports.add("org.sandwood.runtime.model.ExecutionTarget");
        sb.append("\n");
        addIndent(sb, indent);
        sb.append("public " + getName() + "(" + processImport(stateName, requiredImports) + " state, ExecutionTarget target) {\n");
        addIndent(sb, indent + 1);
        sb.append("super(state, target);\n");
        addIndent(sb, indent + 1);
        sb.append("scratch = new " + ClassName.scratchClass + "();\n");
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
