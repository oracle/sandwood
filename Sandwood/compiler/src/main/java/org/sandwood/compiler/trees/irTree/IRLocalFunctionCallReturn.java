/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransLocalFunctionCallReturn;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRLocalFunctionCallReturn<X extends Variable<X>> extends IRTreeReturn<X> {

    private final String name;
    private final IRTreeReturn<?>[] args;
    private final Type<X> outputType;

    IRLocalFunctionCallReturn(Type<X> outputType, String name, IRTreeReturn<?>... args) {
        super(IRTreeType.FUNCTION_CALL_RETURN);
        this.name = name;
        this.args = args;
        this.outputType = outputType;
    }

    @Override
    public IRTree[] getChildren() {
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
    public TransLocalFunctionCallReturn<X> toTransformationTree() {
        TransTreeReturn<?>[] outputArgs = new TransTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toTransformationTree();
        return TransTree.functionCallReturn(getOutputType(), name, outputArgs);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        for(IRTreeReturn<?> arg:args)
            result = prime * result + arg.equivalentHashCode();
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRLocalFunctionCallReturn<?> other = (IRLocalFunctionCallReturn<?>) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i]))
                return false;
        return !name.equals(other.name);
    }

    @Override
    public IRLocalFunctionCallReturn<X> applyTransformation(TreeTransformation t) {
        int size = args.length;
        IRTreeReturn<?>[] a = new IRTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = t.transformReturn(args[i]);
        return new IRLocalFunctionCallReturn<>(outputType, name, a);
    }

    @Override
    public Type<X> getOutputType() {
        return outputType;
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        int size = args.length;
        for(int i = 0; i < size; i++)
            v.visit(args[i]);
    }
}
