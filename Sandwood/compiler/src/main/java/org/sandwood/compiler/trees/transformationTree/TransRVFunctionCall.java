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

import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.trees.outputTree.OutputFunctionCall;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransRVFunctionCall extends TransTreeVoid {

    public final FunctionType funcType;
    public final RandomVariableType<?, ?> source, sink;
    public final TransTreeReturn<?>[] args;

    private TransRVFunctionCall(FunctionType t, RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink,
            TransTreeReturn<?>[] args, int size, String comment) {
        super(TransTreeType.RV_FUNCTION_CALL, size, comment);
        this.funcType = t;
        this.source = source;
        this.sink = sink;
        this.args = args;

        assert t != null;
        assert source != null;
        assert args != null;
        for(TransTreeReturn<?> arg:args)
            assert arg != null;
    }

    public static TransRVFunctionCall getTransRVFunctionCall(FunctionType t, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, TransTreeReturn<?>[] args, String comment) {
        int size = 1;
        for(TransTreeReturn<?> a:args)
            size += a.size();
        return new TransRVFunctionCall(t, source, sink, args, size, comment);
    }

    @Override
    public TransTree<?>[] getChildren() {
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
    public OutputFunctionCall toOutputTreeInternal() {
        OutputTreeReturn<?>[] outputArgs = new OutputTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toOutputTreeReturnInternal();
        return OutputTree.functionCall(funcType, source, sink, outputArgs, comment);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + equivalentHashCode(args);
        result = prime * result + source.hashCode();
        result = prime * result + ((sink != null) ? sink.hashCode() : 0);
        result = prime * result + funcType.hashCode();
        return result;
    }

    @Override
    public boolean equivalentInternal(TransTree<?> tree,
            Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransRVFunctionCall other = (TransRVFunctionCall) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i], substitutions))
                return false;
        if(!source.equals(other.source))
            return false;
        if(sink == null) {
            if(other.sink != null)
                return false;
        } else if(!sink.equals(other.sink))
            return false;
        return funcType == other.funcType;
    }

    /*
     * Cast required to make the Oracle compiler accept the type is correct.
     */
    @Override
    public TransRVFunctionCall applyTransformationInternal(Transformer t) {
        int size = args.length;
        TransTreeReturn<?>[] a = new TransTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = (TransTreeReturn<?>) t.transform(args[i]);
        return getTransRVFunctionCall(funcType, source, sink, a, comment);
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
}
