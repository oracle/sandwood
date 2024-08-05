/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransRVFunctionCall;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

public class IRRVFunctionCall extends IRTreeVoid {

    private final FunctionType funcType;
    private final RandomVariableType<?, ?> source, sink;
    private final IRTreeReturn<?>[] args;

    IRRVFunctionCall(FunctionType t, RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink,
            IRTreeReturn<?>[] args, String comment) {
        super(IRTreeType.FUNCTION_CALL_RETURN, comment);
        this.funcType = t;
        this.source = source;
        this.sink = sink;
        this.args = args;

        assert t != null;
        assert source != null;
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

        sb.append(funcType + " " + source + ((sink != null) ? " " + sink : ""));
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
    public TransRVFunctionCall toTransformationTree() {
        TransTreeReturn<?>[] outputArgs = new TransTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toTransformationTree();
        return TransTree.functionCall(funcType, source, sink, outputArgs, comment);
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        for(IRTreeReturn<?> arg:args)
            result = prime * result + arg.equivalentHashCode();
        result = prime * result + source.hashCode();
        result = prime * result + ((sink != null) ? sink.hashCode() : 0);
        result = prime * result + funcType.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRRVFunctionCall other = (IRRVFunctionCall) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i]))
                return false;
        if(!source.equals(other.source))
            return false;
        if(sink == null)
            return other.sink == null;
        else if(!sink.equals(other.sink))
            return false;
        return funcType == other.funcType;
    }

    @Override
    public IRRVFunctionCall applyTransformation(TreeTransformation t) {
        int size = args.length;
        IRTreeReturn<?>[] a = new IRTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = t.transformReturn(args[i]);
        return new IRRVFunctionCall(funcType, source, sink, a, comment);
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        int size = args.length;
        for(int i = 0; i < size; i++)
            v.visit(args[i]);
    }
}
