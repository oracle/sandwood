/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree;

import java.util.Map;

import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.outputTree.OutputFunctionCallReturn;
import org.sandwood.compiler.trees.outputTree.OutputTree;
import org.sandwood.compiler.trees.outputTree.OutputTreeReturn;
import org.sandwood.compiler.trees.transformationTree.transformers.Transformer;
import org.sandwood.compiler.trees.transformationTree.util.Bounds;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

public class TransRVFunctionCallReturn<X extends Variable<X>> extends TransTreeReturn<X> {

    public final FunctionType funcType;
    public final RandomVariableType<?, ?> source;
    public final RandomVariableType<?, ?> sink;
    public final TransTreeReturn<?>[] args;
    public final Type<X> outputType;

    TransRVFunctionCallReturn(FunctionType t, Type<X> outputType, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, TransTreeReturn<?>... args) {
        super(TransTreeType.RV_FUNCTION_CALL_RETURN, treeSize(args));
        this.funcType = t;
        this.outputType = outputType;
        this.source = source;
        this.sink = sink;
        this.args = args;

        assert t != null;
        assert source != null;
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
        if(funcType == FunctionType.CONJUGATE_SAMPLE || funcType == FunctionType.SAMPLE)
            return false;
        return super.deterministic();
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(funcType + " " + source + ((sink != null) ? " to " + sink : ""));
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
        return OutputTree.functionCallReturn(funcType, source, sink, outputArgs);
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
    public boolean equivalent(TransTree<?> tree, Map<VariableDescription<?>, VariableDescription<?>> substitutions) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        TransRVFunctionCallReturn<?> other = (TransRVFunctionCallReturn<?>) tree;
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
     * This should just be applyTransformation, but the cast and the method using a raw type are required to get it to
     * work with the Oracle compiler.
     */
    @Override
    public TransRVFunctionCallReturn<X> applyTransformationInternal(Transformer t) {
        int size = args.length;
        TransTreeReturn<?>[] a = new TransTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = (TransTreeReturn<?>) t.transform(args[i]);
        return new TransRVFunctionCallReturn<>(funcType, outputType, source, sink, a);
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
        TransTreeReturn<X> tree;
        switch(funcType) {
            case CONJUGATE_SAMPLE: {
                if(source == VariableType.Bernoulli) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Beta) {
                    tree = (TransTreeReturn<X>) constant(1.0);
                } else if(source == VariableType.Binomial) {
                    tree = (TransTreeReturn<X>) args[2];
                } else if(source == VariableType.Categorical) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Cauchy) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Dirichlet) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Exponential) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Gamma) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Gaussian) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.HalfCauchy) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.InverseGamma) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Poisson) {
                    tree = (TransTreeReturn<X>) constant(Integer.MAX_VALUE);
                } else if(source == VariableType.StudentT) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.TruncatedGaussian) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Uniform) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else
                    throw new CompilerException("Method not implemented for type " + source);
                break;
            }
            case SAMPLE: {
                if(source == VariableType.Bernoulli) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Beta) {
                    tree = (TransTreeReturn<X>) constant(1.0);
                } else if(source == VariableType.Binomial) {
                    tree = (TransTreeReturn<X>) args[2];
                } else if(source == VariableType.Categorical) {
                    tree = (TransTreeReturn<X>) getIntField(args[1], "length");
                } else if(source == VariableType.Cauchy) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Dirichlet) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Exponential) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Gamma) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Gaussian) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.HalfCauchy) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.InverseGamma) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.Poisson) {
                    tree = (TransTreeReturn<X>) constant(Integer.MAX_VALUE);
                } else if(source == VariableType.StudentT) {
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                } else if(source == VariableType.TruncatedGaussian) {
                    tree = (TransTreeReturn<X>) args[5];
                } else if(source == VariableType.Uniform) {
                    tree = (TransTreeReturn<X>) constant(1.0);
                } else
                    throw new CompilerException("Method not implemented for type " + source);
                break;
            }
            case LOG_PROBABILITY: {
                if(source.isContinuous())
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                else
                    tree = (TransTreeReturn<X>) constant(0.0);
                break;
            }
            case PROBABILITY: {
                if(source.isContinuous())
                    tree = (TransTreeReturn<X>) constant(Double.POSITIVE_INFINITY);
                else
                    tree = (TransTreeReturn<X>) constant(1.0);
                break;
            }
            default:
                throw new CompilerException("Operation type " + funcType + " not supported.");
        }
        bounds.addTransformedTree(this, tree);
        return tree;
    }

    @Override
    public TransTreeReturn<X> minValue(Bounds bounds) {
        TransTreeReturn<X> tree;
        switch(funcType) {
            case CONJUGATE_SAMPLE: {
                if(source == VariableType.Bernoulli) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Beta) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Binomial) {
                    tree = (TransTreeReturn<X>) constant(0);
                } else if(source == VariableType.Categorical) {
                    tree = (TransTreeReturn<X>) constant(0);
                } else if(source == VariableType.Cauchy) {
                    tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                } else if(source == VariableType.Dirichlet) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Exponential) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Gamma) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Gaussian) {
                    tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                } else if(source == VariableType.HalfCauchy) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.InverseGamma) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Poisson) {
                    tree = (TransTreeReturn<X>) constant(0);
                } else if(source == VariableType.StudentT) {
                    tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                } else if(source == VariableType.TruncatedGaussian) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Uniform) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else
                    throw new CompilerException("Method not implemented for type " + source);
                break;
            }
            case SAMPLE: {
                if(source == VariableType.Bernoulli) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Beta) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Binomial) {
                    tree = (TransTreeReturn<X>) constant(0);
                } else if(source == VariableType.Categorical) {
                    tree = (TransTreeReturn<X>) constant(0);
                } else if(source == VariableType.Cauchy) {
                    tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                } else if(source == VariableType.Dirichlet) {
                    throw new CompilerException("Method not implemented for type " + source);
                } else if(source == VariableType.Exponential) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Gamma) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Gaussian) {
                    tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                } else if(source == VariableType.HalfCauchy) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.InverseGamma) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else if(source == VariableType.Poisson) {
                    tree = (TransTreeReturn<X>) constant(0);
                } else if(source == VariableType.StudentT) {
                    tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                } else if(source == VariableType.TruncatedGaussian) {
                    tree = (TransTreeReturn<X>) args[3];
                } else if(source == VariableType.Uniform) {
                    tree = (TransTreeReturn<X>) constant(0.0);
                } else
                    throw new CompilerException("Method not implemented for type " + source);
                break;
            }
            case LOG_PROBABILITY:
                tree = (TransTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                break;
            case PROBABILITY:
                tree = (TransTreeReturn<X>) constant(0.0);
                break;
            default:
                throw new CompilerException("Operation type " + funcType + " not supported.");
        }
        bounds.addTransformedTree(this, tree);
        return tree;
    }
}
