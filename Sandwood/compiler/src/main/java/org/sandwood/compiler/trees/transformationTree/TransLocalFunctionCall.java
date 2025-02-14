/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.trees.outputTree.OutputFunctionCall;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransLocalFunctionCall extends TransTreeVoid {

    private final FunctionName name;
    private final TransTreeReturn<?>[] args;

    private TransLocalFunctionCall(FunctionName name, TransTreeReturn<?>[] args, int size, String comment) {
        super(TransTreeType.LOCAL_FUNCTION_CALL, size, comment);
        this.name = name;
        this.args = args;
    }

    static TransLocalFunctionCall getTransLocalFunctionCall(FunctionName name, TransTreeReturn<?>[] args,
            String comment) {
        int size = 1;
        for(TransTreeReturn<?> a:args)
            size += a.size();
        return new TransLocalFunctionCall(name, args, size, comment);
    }

    @Override
    public TransTree<?>[] getChildren() {
        return args;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append("(");

        boolean first = true;
        for(int i = 0; i < args.length; i++) {
            if(first)
                first = false;
            else
                sb.append(", ");
            sb.append("arg" + (i + 1));
        }

        sb.append(");");
        return sb.toString();
    }

    @Override
    public OutputFunctionCall toOutputTreeInternal() {
        OutputTreeReturn<?>[] outputArgs = new OutputTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toOutputTreeReturnInternal();
        return OutputTree.functionCall(name, outputArgs, comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + equivalentHashCode(args);
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransLocalFunctionCall other = (TransLocalFunctionCall) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i], substitutions))
                return false;
        return name.equals(other.name);
    }

    /*
     * Cast required to get the Oracle compiler to accept the types are correct.
     */
    @Override
    public TransLocalFunctionCall applyTransformationInternal(Transformer t) {
        int size = args.length;
        TransTreeReturn<?>[] a = new TransTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = (TransTreeReturn<?>) t.transform(args[i]);
        return getTransLocalFunctionCall(name, a, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        int size = args.length;
        for(int i = 0; i < size; i++)
            v.visit(args[i]);
    }

    @Override
    public Set<String> declaredAllVariablesInternal() {
        return Collections.emptySet();
    }

    public TransTreeReturn<?>[] args() {
        return args;
    }
}
