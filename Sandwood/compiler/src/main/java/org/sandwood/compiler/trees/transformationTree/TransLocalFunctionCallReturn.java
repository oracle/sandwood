/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.trees.outputTree.OutputFunctionCallReturn;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransLocalFunctionCallReturn<X extends Variable<X>> extends TransTreeReturn<X> {

    private final String name;
    private final TransTreeReturn<?>[] args;
    private final Type<X> outputType;

    TransLocalFunctionCallReturn(Type<X> outputType, String name, TransTreeReturn<?>... args) {
        super(TransTreeType.LOCAL_FUNCTION_CALL_RETURN, size(args));
        this.name = name;
        this.args = args;
        this.outputType = outputType;
    }

    private static int size(TransTreeReturn<?>... args) {
        int size = 1;
        for(TransTreeReturn<?> t:args)
            size += t.size();
        return size;
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
    public OutputFunctionCallReturn<X> toOutputTreeReturnInternal() {
        OutputTreeReturn<?>[] outputArgs = new OutputTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toOutputTreeReturnInternal();
        return OutputTree.functionCallReturn(name, outputArgs);
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
        TransLocalFunctionCallReturn<?> other = (TransLocalFunctionCallReturn<?>) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i], substitutions))
                return false;
        return name.equals(other.name);
    }

    /*
     * This should be applyTransformation, but the alternative method and the cast are required to get it to compile
     * with the Oracle compiler.
     */
    @Override
    public TransLocalFunctionCallReturn<X> applyTransformationInternal(Transformer t) {
        int size = args.length;
        TransTreeReturn<?>[] a = new TransTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = (TransTreeReturn<?>) t.transform(args[i]);
        return new TransLocalFunctionCallReturn<>(outputType, name, a);
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

    @Override
    public TransTreeReturn<X> maxValue(Bounds bounds) {
        return this;
    }

    @Override
    public TransTreeReturn<X> minValue(Bounds bounds) {
        return this;
    }
}
