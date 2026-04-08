/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext.FieldDesc;
import org.sandwood.compiler.dataflowGraph.variables.ScratchVariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.WrappedTree;
import org.sandwood.compiler.trees.transformationTree.TransFunction;
import org.sandwood.compiler.trees.transformationTree.TransSandwoodClassGenerated;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;

public class IRSandwoodClassGenerated {

    private final ClassName name;
    private final ClassName extendedClass;
    private final ClassName[] interfaces;
    private final PackageName packageName;
    private final String modelCode;
    // Variable field name |-> Field Descriptor
    private final Map<VariableName, FieldDesc<?>> classFields;
    private final Set<ScratchVariableDescription<?>> scratchFields;
    private final Map<FunctionName, IRFunction<?>> functions;
    private final List<IRFunction<?>> gettersAndSetters = new ArrayList<>();

    private static final VariableDescription<DoubleVariable> modelProbabilityName = VariableNames
            .logProbabilityName(VariableNames.internalName("model"));
    private static final VariableDescription<DoubleVariable> evidenceProbabilityName = VariableNames
            .logProbabilityName(VariableNames.internalName("evidence"));

    public IRSandwoodClassGenerated(ClassName name, PackageName packageName, ClassName extendedClass,
            ClassName[] interfaces, Map<VariableName, FieldDesc<?>> classFields, Set<ScratchVariableDescription<?>> scratchFields,
            Map<FunctionName, IRFunction<?>> functions, String modelCode) {
        this.name = name;
        this.packageName = packageName;
        this.extendedClass = extendedClass;
        this.interfaces = interfaces;
        this.modelCode = modelCode;
        this.classFields = classFields;
        this.scratchFields = scratchFields;
        this.functions = functions;

        // Add the getter and setter methods
        addGettersAndSetters();
    }

    private <A extends Variable<A>> IRTreeVoid declareField(FieldDesc<A> f) {
        if(f.initialValue == null)
            return IRTree.initializeUnsetField(Visibility.PRIVATE, f.varDesc, Tree.NoComment);
        else
            return IRTree.initializeField(Visibility.PRIVATE, f.varDesc, f.initialValue, Tree.NoComment);
    }

    private void addGettersAndSetters() {
        PriorityQueue<VariableName> names = new PriorityQueue<>(classFields.keySet());
        while(!names.isEmpty()) {
            VariableName name = names.poll();
            FieldDesc<?> f = classFields.get(name);
            addGetterAndSetter(f);
        }
    }

    private <A extends Variable<A>> void addGetterAndSetter(FieldDesc<A> f) {
        if(f.fieldType.getter) {
            IRTreeReturn<A> body = IRTree.load(f.varDesc);
            IRFunction<?> get;
            if(f.varDesc.equals(modelProbabilityName))
                get = IRTree.returnFunction(Visibility.PUBLIC, f.varDesc.type,
                        FunctionName.createFunctionName("getCurrentLogProbability"), new ArgDesc[0], body, true,
                        "Getter for the probability of " + f.varDesc + ".");
            else
                get = IRTree.returnFunction(Visibility.PUBLIC, f.varDesc.type, FunctionName.getterName(f.varDesc.name),
                        new ArgDesc[0], body, overridesModel(f.varDesc), "Getter for " + f.varDesc + ".");
            gettersAndSetters.add(get);
        }

        if(f.fieldType.setter) {
            ArgDesc<?>[] args = new ArgDesc[2];
            VariableDescription<A> valueName = VariableNames.localCalcVarName("value", f.varDesc.type, false);
            args[0] = new ArgDesc<>(valueName);
            VariableDescription<BooleanVariable> allocatedName = VariableNames.allocatedFlag();
            args[1] = new ArgDesc<>(allocatedName);

            IRTreeReturn<A> argLoad = IRTree.load(valueName);
            IRTreeVoid body = IRTree.store(f.varDesc, argLoad, IRTree.NoComment);

            // Add in any side effects.
            Set<WrappedTree<IRTree, IRTreeVoid>> setSideEffects = f.getSetSideEffects();
            if(!setSideEffects.isEmpty()) {
                List<IRTreeVoid> l = new ArrayList<>();
                l.add(body);

                PriorityQueue<WrappedTree<IRTree, IRTreeVoid>> p = new PriorityQueue<>(setSideEffects);
                while(!p.isEmpty()) {
                    WrappedTree<IRTree, IRTreeVoid> w = p.poll();
                    l.add(w.tree);
                }

                body = IRTree.sequential(l, "Set flags for all the side effects of " + f.varDesc
                        + " including if probabilities need to be updated.");
            }

            // Add the resulting body to a function.
            IRVoidFunction set = IRTree.voidFunction(Visibility.PUBLIC, FunctionName.setterName(f.varDesc.name), args,
                    body, "Setter for " + f.varDesc + ".");
            gettersAndSetters.add(set);
        }
    }

    private boolean overridesModel(VariableDescription<?> varDesc) {
        return varDesc.equals(modelProbabilityName) || varDesc.equals(evidenceProbabilityName);
    }

    public TransSandwoodClassGenerated toTransformationTree() {

        Map<FunctionName, TransFunction<?>> transFunctions = new HashMap<>();
        for(FunctionName name:functions.keySet())
            transFunctions.put(name, functions.get(name).toTransformationTree());

        List<TransFunction<?>> transGettersAndSetters = new ArrayList<>();
        for(IRFunction<?> f:gettersAndSetters)
            transGettersAndSetters.add(f.toTransformationTree());

        // Construct fields Map.
        Map<VariableName, TransTreeVoid> fieldTrees = new HashMap<>();
        for(VariableName fieldName:classFields.keySet()) {
            FieldDesc<?> f = classFields.get(fieldName);
            fieldTrees.put(fieldName, declareField(f).toTransformationTree());
        }

        for(ScratchVariableDescription<?> varDesc:scratchFields)
            fieldTrees.put(varDesc.name, IRTree.initializeUnsetField(Visibility.PRIVATE, varDesc, null).toTransformationTree());

        return new TransSandwoodClassGenerated(name, packageName, extendedClass, interfaces, modelCode, transFunctions,
                fieldTrees, transGettersAndSetters);
    }
}
