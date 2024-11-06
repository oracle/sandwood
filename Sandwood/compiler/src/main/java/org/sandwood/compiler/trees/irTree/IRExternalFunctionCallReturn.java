/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransExternalFunctionCallReturn;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRExternalFunctionCallReturn<X extends Variable<X>> extends IRTreeReturn<X> {

    private final IRTreeReturn<?>[] args;
    private final Type<X> outputType;
    private final ExternalFunction func;

    IRExternalFunctionCallReturn(ExternalFunction func, Type<X> outputType, IRTreeReturn<?>... args) {
        super(IRTreeType.FUNCTION_CALL_RETURN);
        this.func = func;
        this.outputType = outputType;
        this.args = args;

        assert args != null;
        for(IRTreeReturn<?> arg:args)
            assert arg != null;
    }

    @Override
    public IRTree[] getChildren() {
        return args;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(func + "(");
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
    public TransExternalFunctionCallReturn<X> toTransformationTree() {
        TransTreeReturn<?>[] outputArgs = new TransTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toTransformationTree();
        return TransTree.functionCallReturn(func, getOutputType(), outputArgs);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        for(IRTreeReturn<?> arg:args)
            result = prime * result + arg.equivalentHashCode();
        result = prime * result + func.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRExternalFunctionCallReturn<?> other = (IRExternalFunctionCallReturn<?>) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i]))
                return false;
        return func == other.func;
    }

    @Override
    public IRExternalFunctionCallReturn<X> applyTransformation(TreeTransformation t) {
        int size = args.length;
        IRTreeReturn<?>[] a = new IRTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = t.transformReturn(args[i]);
        return new IRExternalFunctionCallReturn<>(func, getOutputType(), a);
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
