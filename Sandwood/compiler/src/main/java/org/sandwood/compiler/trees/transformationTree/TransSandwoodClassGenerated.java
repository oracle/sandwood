/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.compilation.APICompile;
import org.sandwood.compiler.compilation.CompilationContext.AuxFunctionType;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.trees.outputTree.OutputFunction;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClassGenerated;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransSandwoodClassGenerated {

    private final ExecutionType target;
    private final ModelClassName name;
    private final PackageName packageName;
    private final String modelCode;
    // Variable field name |-> Field Descriptor
    private final Map<FunctionName, TransFunction<?>> functions;
    private final Map<VariableName, TransTreeVoid> classFieldTrees, scratchFieldTrees;
    private final List<TransFunction<?>> gettersAndSetters;

    public TransSandwoodClassGenerated(ExecutionType target, ModelClassName name, PackageName packageName,
            String modelCode, Map<FunctionName, TransFunction<?>> functions,
            Map<VariableName, TransTreeVoid> classFieldsTrees, Map<VariableName, TransTreeVoid> scratchFieldTrees,
            List<TransFunction<?>> gettersAndSetters) {
        this.target = target;
        this.name = name;
        this.packageName = packageName;
        this.modelCode = modelCode;
        this.functions = functions;
        this.classFieldTrees = classFieldsTrees;
        this.scratchFieldTrees = scratchFieldTrees;
        this.gettersAndSetters = gettersAndSetters;
    }

    private class FunctionOptTask extends RecursiveTask<TransFunction<?>> {
        private static final long serialVersionUID = -1113745508808057185L;
        private final TransFunction<?> f;
        private final Map<VariableDescription<?>, TransTreeReturn<?>> constants;

        public FunctionOptTask(TransFunction<?> f, Map<VariableDescription<?>, TransTreeReturn<?>> constants) {
            this.f = f;
            this.constants = constants;
            if(APICompile.parallel)
                fork();
        }

        @Override
        protected TransFunction<?> compute() {
            return f.applyOptimisations(constants);
        }

    }

    public TransSandwoodClassGenerated applyOptimisations() {
        Map<FunctionName, TransFunction<?>> optimisedFunctions = new HashMap<>();

        // Get the values that are set to constants.
        TransFunction<?> initialize = functions.get(AuxFunctionType.INITIALIZE.functionName);
        initialize = initialize.applyOptimisations(Collections.emptyMap());
        Map<VariableDescription<?>, TransTreeReturn<?>> constants = getConstants(initialize);
        initialize = initialize.applyConstants(constants);
        optimisedFunctions.put(AuxFunctionType.INITIALIZE.functionName, initialize);

        // Construct tasks to perform optimisations
        List<FunctionOptTask> functionTasks = new ArrayList<>();
        for(FunctionName name:functions.keySet()) {
            if(name != AuxFunctionType.INITIALIZE.functionName)
                functionTasks.add(new FunctionOptTask(functions.get(name), constants));
        }

        List<TransFunction<?>> optimisedGettersAndSetters = new ArrayList<>();
        for(TransFunction<?> f:gettersAndSetters)
            optimisedGettersAndSetters.add(f.applyOptimisations(constants));

        // Gather the results.
        for(FunctionOptTask t:functionTasks)
            if(APICompile.parallel) {
                TransFunction<?> f = t.join();
                optimisedFunctions.put(f.name, f);
            } else {
                TransFunction<?> f = t.compute();
                optimisedFunctions.put(f.name, f);
            }

        // Remove unused fields
        Map<VariableName, TransTreeVoid> fieldTrees = new HashMap<>(this.classFieldTrees);
        for(VariableDescription<?> desc:constants.keySet())
            fieldTrees.remove(desc.name);

        return new TransSandwoodClassGenerated(target, name, packageName, modelCode, optimisedFunctions,
                classFieldTrees, scratchFieldTrees, optimisedGettersAndSetters);
    }

    private Map<VariableDescription<?>, TransTreeReturn<?>> getConstants(TransFunction<?> initialize) {
        Map<VariableDescription<?>, TransTreeReturn<?>> constants = new HashMap<>();
        initialize.body.traverseTree(new TreeVisitor() {
            @Override
            public void visit(TransTree<?> tree) {
                switch(tree.type) {
                    case STORE: {
                        TransStore<?> s = (TransStore<?>) tree;
                        TransTreeReturn<?> value = s.value;
                        boolean found = false;
                        while(!found) {
                            switch(value.type) {
                                case CAST_DOUBLE:
                                    value = ((TransCastToDouble) value).input;
                                    break;
                                case CAST_INT:
                                    value = ((TransCastToInteger) value).input;
                                    break;
                                case CONST_BOOLEAN:
                                case CONST_DOUBLE:
                                case CONST_INT:
                                    constants.put(s.varDesc, s.value);
                                    found = true;
                                    break;
                                default:
                                    tree.traverseTree(this);
                                    found = true;
                                    break;
                            }
                        }
                        break;
                    }
                    default:
                        tree.traverseTree(this);
                        break;
                }
            }
        });
        return constants;
    }

    public OutputSandwoodClassGenerated toOutputTree() {
        // Turn the individual field declarations into a single tree.
        PriorityQueue<VariableName> fieldNames = new PriorityQueue<>(classFieldTrees.keySet());
        List<TransTreeVoid> declarations = new ArrayList<>();
        while(!fieldNames.isEmpty())
            declarations.add(classFieldTrees.get(fieldNames.poll()));
        
        fieldNames.addAll(scratchFieldTrees.keySet());
        while(!fieldNames.isEmpty())
            declarations.add(scratchFieldTrees.get(fieldNames.poll()));
        
        OutputTree fieldsTree = TransTree.sequential(declarations, "Declare the variables for the model.")
                .toOutputTree(target);

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

        List<OutputFunction> functionList = new ArrayList<>();
        while(!samples.isEmpty()) {
            TransFunction<?> f = functions.get(samples.poll());
            functionList.add(f.toOutputTree(target));
        }

        while(!aux.isEmpty()) {
            TransFunction<?> f = functions.get(aux.poll());
            functionList.add(f.toOutputTree(target));
        }

        List<OutputFunction> gettersAndSetters = new ArrayList<>();
        for(TransFunction<?> f:this.gettersAndSetters)
            gettersAndSetters.add(f.toOutputTree(target));

        return OutputSandwoodClassGenerated.getClass(target, name, packageName, functionList, fieldsTree,
                modelCode, gettersAndSetters);
    }
}
