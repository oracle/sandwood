/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputFunctionCallReturn;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransExternalFunctionCallReturn<X extends Variable<X>> extends TransTreeReturn<X> {

    public final ExternalFunction func;
    public final TransTreeReturn<?>[] args;
    public final Type<X> outputType;

    TransExternalFunctionCallReturn(ExternalFunction func, Type<X> outputType, TransTreeReturn<?>... args) {
        super(TransTreeType.EXTERNAL_FUNCTION_CALL_RETURN, treeSize(args));
        this.func = func;
        this.outputType = outputType;
        this.args = args;

        assert func != null;
        assert args != null;
        for(TransTreeReturn<?> arg:args)
            assert arg != null;
    }

    private static int treeSize(TransTreeReturn<?>... args) {
        int treeSize = 1;
        for(TransTreeReturn<?> t:args)
            treeSize += t.treeSize();
        return treeSize;
    }

    @Override
    public TransTree<?>[] getChildren() {
        return args;
    }

    @Override
    public boolean deterministic() {
        return super.deterministic();
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
    public OutputFunctionCallReturn<X> toOutputTreeReturnInternal() {
        OutputTreeReturn<?>[] outputArgs = new OutputTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toOutputTreeReturnInternal();
        return OutputTree.functionCallReturn(func, outputArgs);
    }

    @Override
    public int equivalentHashCodeInternal() {
        final int prime = 31;
        int result = 1;
        result = prime * result + equivalentHashCode(args);
        result = prime * result + func.hashCode();
        return result;
    }

    @Override
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransExternalFunctionCallReturn<?> other = (TransExternalFunctionCallReturn<?>) tree;
        if(args.length != other.args.length)
            return false;
        for(int i = 0; i < args.length; i++)
            if(!args[i].equivalent(other.args[i], substitutions))
                return false;
        return func == other.func;
    }

    /*
     * This should just be applyTransformation, but the cast and the method using a raw type are required to get it to
     * work with the Oracle compiler.
     */
    @Override
    public TransExternalFunctionCallReturn<X> applyTransformationInternal(Transformer t) {
        int size = args.length;
        TransTreeReturn<?>[] a = new TransTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = (TransTreeReturn<?>) t.transform(args[i]);
        return new TransExternalFunctionCallReturn<>(func, outputType, a);
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
        if(func.monotonic) {
            if(func.increasing) {
                TransTreeReturn<?>[] maxArgs = new TransTreeReturn<?>[args.length];
                for(int i=0; i<args.length; i++) {
                    maxArgs[i] = args[i].maxValue(bounds);
                }
                return new TransExternalFunctionCallReturn<>(func, outputType, maxArgs);
            } else {
                TransTreeReturn<?>[] minArgs = new TransTreeReturn<?>[args.length];
                for(int i=0; i<args.length; i++) {
                    minArgs[i] = args[i].minValue(bounds);
                }
                return new TransExternalFunctionCallReturn<>(func, outputType, minArgs);
            }
        } else {
            throw new CompilerException("Function " + func + " not supported.");
        }
    }

    @Override
    public TransTreeReturn<X> minValue(Bounds bounds) {
        if(func.monotonic) {
            if(func.increasing) {
                TransTreeReturn<?>[] minArgs = new TransTreeReturn<?>[args.length];
                for(int i=0; i<args.length; i++) {
                    minArgs[i] = args[i].minValue(bounds);
                }
                return new TransExternalFunctionCallReturn<>(func, outputType, minArgs);
            } else {
                TransTreeReturn<?>[] maxArgs = new TransTreeReturn<?>[args.length];
                for(int i=0; i<args.length; i++) {
                    maxArgs[i] = args[i].maxValue(bounds);
                }
                return new TransExternalFunctionCallReturn<>(func, outputType, maxArgs);
            }
        } else {
            throw new CompilerException("Function " + func + " not supported.");
        }
    }

    public TransTreeReturn<?>[] args() {
        return args;
    }
}
