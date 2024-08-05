/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.util;

import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCallReturn;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.newArray;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;
import static org.sandwood.compiler.trees.irTree.IRTree.treeScope;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ParForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.ArrayType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class FunctionUtils {
    public static <V extends Variable<V>> boolean globalFieldAllocation(VariableDescription<V> fieldName,
            IRTreeReturn<V> localAllocation, boolean isSerial, Scope innerScope, CompilationContext compilationCtx) {
        isSerial = checkIsSerial(isSerial, innerScope, compilationCtx);
        switch(compilationCtx.target) {
            case SingleThreadCPU:
            case MultiThreadCPU: {
                if(isSerial) {
                    IRTreeVoid storeArray = store(fieldName, localAllocation,
                            "Allocation of " + fieldName + " for single threaded execution");
                    compilationCtx.addTreeToScope(GlobalScope.scope, storeArray);
                    break;
                } else {
                    List<IRTreeVoid> subtrees = new ArrayList<>();

                    VariableDescription<IntVariable> threadCount = VariableNames.calcVarName("threadCount",
                            VariableType.IntVariable, true);
                    VariableDescription<ArrayVariable<V>> arrayFieldName = VariableNames.altTypeName(fieldName,
                            VariableType.arrayType(fieldName.type));
                    ArrayType<V> arrayType = VariableType.arrayType(localAllocation.getOutputType());
                    subtrees.add(initializeVariable(threadCount,
                            functionCallReturn(VariableType.IntVariable, "threadCount"), "Get the thread count."));
                    subtrees.add(store(arrayFieldName, newArray(load(threadCount), arrayType),
                            "Allocate an array to hold a copy per thread"));
                    VariableDescription<IntVariable> index = VariableNames.calcVarName("index",
                            VariableType.IntVariable, true);
                    IRTreeVoid body = arrayPut(load(arrayFieldName), load(index), localAllocation, Tree.NoComment);
                    subtrees.add(forStmt(body, constant(0), load(threadCount), constant(1), index, true,
                            "Populate the array with a copy per thread"));

                    compilationCtx.addTreeToScope(GlobalScope.scope, treeScope(sequential(subtrees, Tree.NoComment),
                            "Allocation of " + fieldName + " for multithreaded execution"));
                }
                break;
            }
            case GPU:
            default:
                throw new CompilerException("Unknown compilation type " + compilationCtx.target);
        }
        return isSerial;
    }

    public static <V extends Variable<V>> boolean createGlobalField(VariableDescription<V> fieldName,
            IRTreeVoid allocator, boolean isSerial, Scope innerScope, CompilationContext compilationCtx) {
        isSerial = checkIsSerial(isSerial, innerScope, compilationCtx);
        switch(compilationCtx.target) {
            case SingleThreadCPU:
            case MultiThreadCPU: {
                if(isSerial) {
                    compilationCtx.addConstructedClassField(fieldName, allocator, null);
                } else {
                    VariableDescription<ArrayVariable<V>> arrayVariable = VariableNames.altTypeName(fieldName,
                            VariableType.arrayType(fieldName.type));
                    compilationCtx.addConstructedClassField(arrayVariable, allocator, null);
                }
                break;
            }
            case GPU:
            default:
                throw new CompilerException("Unknown compilation type " + compilationCtx.target);
        }
        return isSerial;
    }

    public static <V extends Variable<V>> IRTreeReturn<V> loadGlobalField(VariableDescription<V> varDesc,
            boolean isSerial, Scope innerScope, CompilationContext compilationCtx) {
        isSerial = checkIsSerial(isSerial, innerScope, compilationCtx);
        switch(compilationCtx.target) {
            case SingleThreadCPU:
                return load(varDesc);
            case MultiThreadCPU: {
                if(isSerial) {
                    return load(varDesc);
                } else {
                    VariableDescription<ArrayVariable<V>> arrayName = VariableNames.altTypeName(varDesc,
                            VariableType.arrayType(varDesc.type));
                    IRTreeReturn<ArrayVariable<V>> array = load(arrayName);
                    Scope s = innerScope;
                    while(s.isSerial(compilationCtx))
                        s = s.getEnclosingScope();
                    // First we have to construct the index name used in the new target, and then we
                    // can construct the thread id name.
                    VariableDescription<IntVariable> threadID = VariableNames.threadIdName(
                            VariableNames.calcVarName(((ParForTask) s).getIndex().getUniqueVarDesc().name));
                    IRTreeReturn<IntVariable> index = load(threadID);
                    return arrayGet(array, index);
                }
            }
            case GPU:
            default:
                throw new CompilerException("Unknown compilation type " + compilationCtx.target);
        }
    }

    private static boolean checkIsSerial(boolean isSerial, Scope innerScope, CompilationContext compilationCtx) {
        boolean serialScope = true;
        while(innerScope != null) {
            if(innerScope.getScopeType() == ScopeType.FOR)
                serialScope = serialScope
                        && (compilationCtx.serialScope((ForTask) innerScope) || !(innerScope instanceof ParForTask));
            innerScope = innerScope.getEnclosingScope();
        }

        return isSerial || serialScope;
    }
}
