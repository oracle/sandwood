/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransRVFunctionCallReturn;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;

//TODO split these into random and non random.
public class IRRVFunctionCallReturn<X extends Variable<X>> extends IRTreeReturn<X> {

    private final FunctionType funcType;
    private final RandomVariableType<?, ?> source, sink;
    private final IRTreeReturn<?>[] args;
    private final Type<X> outputType;

    private IRRVFunctionCallReturn(FunctionType t, Type<X> outputType, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, IRTreeReturn<?>... args) {
        super(IRTreeType.FUNCTION_CALL_RETURN);
        this.funcType = t;
        this.outputType = outputType;
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
    public TransRVFunctionCallReturn<X> toTransformationTree() {
        TransTreeReturn<?>[] outputArgs = new TransTreeReturn<?>[args.length];
        for(int i = 0; i < args.length; i++)
            outputArgs[i] = args[i].toTransformationTree();
        return TransTree.functionCallReturn(funcType, getOutputType(), source, sink, outputArgs);
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
        IRRVFunctionCallReturn<?> other = (IRRVFunctionCallReturn<?>) tree;
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
    public IRRVFunctionCallReturn<X> applyTransformation(TreeTransformation t) {
        int size = args.length;
        IRTreeReturn<?>[] a = new IRTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = t.transformReturn(args[i]);
        return new IRRVFunctionCallReturn<>(funcType, outputType, source, sink, a);
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

    public static <X extends Variable<X>> IRTreeReturn<X> construct(FunctionType t, Type<X> outputType,
            RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink, IRTreeReturn<?>[] args) {
        args = processArgs(t, source, args);
        return constructInternal(t, outputType, source, sink, args);
    }

    private static <X extends Variable<X>> IRTreeReturn<X> constructInternal(FunctionType t, Type<X> outputType,
            RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink, IRTreeReturn<?>[] args) {
        // TODO go through Distribution Sampling and decide if there are any other methods to promote into the model
        // code. For example Beta, Gamma and Inverse Gamma probabilities and samples.

        // Split out expensive constants so that future model transformations can cache the values when possible.

        if(source == VariableType.TruncatedGaussian) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> mean = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> variance = (IRTreeReturn<DoubleVariable>) args[2];
                    IRTreeReturn<DoubleVariable> lower = (IRTreeReturn<DoubleVariable>) args[3];
                    IRTreeReturn<DoubleVariable> upper = (IRTreeReturn<DoubleVariable>) args[4];

                    List<IRTreeReturn<BooleanVariable>> conditions = new ArrayList<>();
                    conditions.add(lessThanEqual(lower, value));
                    conditions.add(lessThanEqual(value, upper));
                    conditions.add(lessThan(lower, upper));
                    IRTreeReturn<BooleanVariable> guard = and(conditions);

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[3];
                    newArgs[0] = value;
                    newArgs[1] = mean;
                    newArgs[2] = variance;
                    // Get the probability for a regular Gaussian and scale it.
                    IRTreeReturn<DoubleVariable> p = construct(t, VariableType.DoubleVariable, VariableType.Gaussian,
                            sink, newArgs);

                    IRTreeReturn<DoubleVariable> lowerCD = IRTree.functionCallReturn(ExternalFunction.GAUSSIAN_CDF,
                            VariableType.DoubleVariable, normalizeGaussian(lower, mean, variance));
                    IRTreeReturn<DoubleVariable> upperCD = IRTree.functionCallReturn(ExternalFunction.GAUSSIAN_CDF,
                            VariableType.DoubleVariable, normalizeGaussian(upper, mean, variance));
                    // It is possible to change the multiplication with the square root of the variance if the
                    // optimisations start caching results.
                    IRTreeReturn<X> validRange = (IRTreeReturn<X>) subtractDD(p, log(subtractDD(upperCD, lowerCD)));
                    IRTreeReturn<X> invalidRange = (IRTreeReturn<X>) constant(Double.NEGATIVE_INFINITY);
                    return conditionalAssignment(guard, validRange, invalidRange);
                }
                case PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> mean = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> variance = (IRTreeReturn<DoubleVariable>) args[2];
                    IRTreeReturn<DoubleVariable> lower = (IRTreeReturn<DoubleVariable>) args[3];
                    IRTreeReturn<DoubleVariable> upper = (IRTreeReturn<DoubleVariable>) args[4];

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[3];
                    newArgs[0] = value;
                    newArgs[1] = mean;
                    newArgs[2] = variance;
                    // Get the probability for a regular Gaussian and scale it.
                    IRTreeReturn<DoubleVariable> p = construct(t, VariableType.DoubleVariable, VariableType.Gaussian,
                            sink, newArgs);

                    List<IRTreeReturn<BooleanVariable>> conditions = new ArrayList<>();
                    conditions.add(lessThanEqual(lower, value));
                    conditions.add(lessThanEqual(value, upper));
                    conditions.add(lessThan(lower, upper));
                    IRTreeReturn<BooleanVariable> guard = and(conditions);

                    IRTreeReturn<DoubleVariable> lowerCD = IRTree.functionCallReturn(ExternalFunction.GAUSSIAN_CDF,
                            VariableType.DoubleVariable, normalizeGaussian(lower, mean, variance));
                    IRTreeReturn<DoubleVariable> upperCD = IRTree.functionCallReturn(ExternalFunction.GAUSSIAN_CDF,
                            VariableType.DoubleVariable, normalizeGaussian(upper, mean, variance));
                    IRTreeReturn<X> validRange = (IRTreeReturn<X>) divideDD(p, subtractDD(upperCD, lowerCD));
                    IRTreeReturn<X> invalidRange = (IRTreeReturn<X>) constant(0.0);
                    return conditionalAssignment(guard, validRange, invalidRange);
                }
                case SAMPLE: {
                    IRTreeReturn<DoubleVariable> mean = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> variance = (IRTreeReturn<DoubleVariable>) args[2];
                    IRTreeReturn<DoubleVariable> lower = (IRTreeReturn<DoubleVariable>) args[3];
                    IRTreeReturn<DoubleVariable> upper = (IRTreeReturn<DoubleVariable>) args[4];

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[5];
                    newArgs[0] = args[0];
                    newArgs[1] = normalizeGaussian(lower, mean, variance);
                    newArgs[2] = IRTree.functionCallReturn(ExternalFunction.GAUSSIAN_CDF, VariableType.DoubleVariable,
                            normalizeGaussian(lower, mean, variance));
                    newArgs[3] = normalizeGaussian(upper, mean, variance);
                    newArgs[4] = IRTree.functionCallReturn(ExternalFunction.GAUSSIAN_CDF, VariableType.DoubleVariable,
                            normalizeGaussian(upper, mean, variance));

                    IRTreeReturn<DoubleVariable> sample = new IRRVFunctionCallReturn<DoubleVariable>(t,
                            VariableType.DoubleVariable, source, sink, newArgs);

                    return (IRTreeReturn<X>) addDD(multiplyDD(sqrt(variance), sample), mean);
                }
            }
        } else if(source == VariableType.Gaussian) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> mean = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> variance = (IRTreeReturn<DoubleVariable>) args[2];

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[1];
                    newArgs[0] = normalizeGaussian(value, mean, variance);
                    IRTreeReturn<DoubleVariable> p = new IRRVFunctionCallReturn<DoubleVariable>(t,
                            VariableType.DoubleVariable, source, sink, newArgs);
                    // It is possible to change the multiplication with the square root of the variance if the
                    // optimisations start caching results.
                    return (IRTreeReturn<X>) subtractDD(p, multiplyDD(constant(0.5), log(variance)));
                }
                case PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> mean = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> variance = (IRTreeReturn<DoubleVariable>) args[2];

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[1];
                    newArgs[0] = normalizeGaussian(value, mean, variance);
                    IRTreeReturn<DoubleVariable> p = new IRRVFunctionCallReturn<DoubleVariable>(t,
                            VariableType.DoubleVariable, source, sink, newArgs);
                    return (IRTreeReturn<X>) divideDD(p, sqrt(variance));
                }
                case SAMPLE: {
                    IRTreeReturn<DoubleVariable> mean = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> variance = (IRTreeReturn<DoubleVariable>) args[2];

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[1];
                    newArgs[0] = args[0];
                    IRTreeReturn<DoubleVariable> sample = new IRRVFunctionCallReturn<DoubleVariable>(t,
                            VariableType.DoubleVariable, source, sink, newArgs);

                    return (IRTreeReturn<X>) addDD(multiplyDD(sqrt(variance), sample), mean);
                }
            }
        } else if(source == VariableType.Categorical) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                case SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<IntVariable> value = (IRTreeReturn<IntVariable>) args[0];
                    IRTreeReturn<ArrayVariable<DoubleVariable>> elementProbs = (IRTreeReturn<ArrayVariable<DoubleVariable>>) args[1];

                    List<IRTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                    constraints.add(greaterThanEqual(value, constant(0.0)));
                    constraints.add(lessThan(value, IRTree.getIntField(elementProbs, "length")));
                    IRTreeReturn<BooleanVariable> guard = and(constraints);

                    return (IRTreeReturn<X>) conditionalAssignment(guard, log(arrayGet(elementProbs, value)),
                            constant(Double.NEGATIVE_INFINITY));
                }
                case PROBABILITY: {
                    IRTreeReturn<IntVariable> value = (IRTreeReturn<IntVariable>) args[0];
                    IRTreeReturn<ArrayVariable<DoubleVariable>> elementProbs = (IRTreeReturn<ArrayVariable<DoubleVariable>>) args[1];

                    List<IRTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                    constraints.add(greaterThanEqual(value, constant(0.0)));
                    constraints.add(lessThan(value, IRTree.getIntField(elementProbs, "length")));
                    IRTreeReturn<BooleanVariable> guard = and(constraints);

                    return (IRTreeReturn<X>) conditionalAssignment(guard, arrayGet(elementProbs, value), constant(0.0));
                }
            }
        } else if(source == VariableType.Exponential) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> lambda = (IRTreeReturn<DoubleVariable>) args[1];

                    List<IRTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                    constraints.add(greaterThanEqual(value, constant(0.0)));
                    constraints.add(negateBoolean(eq(value, constant(Double.POSITIVE_INFINITY))));
                    IRTreeReturn<BooleanVariable> guard = and(constraints);

                    return (IRTreeReturn<X>) conditionalAssignment(guard,
                            subtractDD(log(lambda), multiplyDD(lambda, value)), constant(Double.NEGATIVE_INFINITY));
                }
                case PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> lambda = (IRTreeReturn<DoubleVariable>) args[1];

                    List<IRTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                    constraints.add(greaterThanEqual(value, constant(0.0)));
                    constraints.add(negateBoolean(eq(value, constant(Double.POSITIVE_INFINITY))));
                    IRTreeReturn<BooleanVariable> guard = and(constraints);

                    return (IRTreeReturn<X>) conditionalAssignment(guard,
                            multiplyDD(lambda, exp(negate(multiplyDD(lambda, value)))), constant(0.0));
                }
                case SAMPLE: {
                    IRTreeReturn<DoubleVariable> lambda = (IRTreeReturn<DoubleVariable>) args[1];

                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[1];
                    newArgs[0] = args[0];
                    IRTreeReturn<DoubleVariable> sample = new IRRVFunctionCallReturn<DoubleVariable>(t,
                            VariableType.DoubleVariable, source, sink, newArgs);
                    return (IRTreeReturn<X>) divideDD(sample, lambda);
                }
            }
        } else if(source == VariableType.Geometric) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<IntVariable> value = (IRTreeReturn<IntVariable>) args[0];
                    IRTreeReturn<BooleanVariable> guard = greaterThanEqual(value, constant(0));
                    IRTreeReturn<DoubleVariable> function = (IRTreeReturn<DoubleVariable>) new IRRVFunctionCallReturn<>(
                            t, outputType, source, sink, args);
                    return (IRTreeReturn<X>) conditionalAssignment(guard, function, constant(Double.NEGATIVE_INFINITY));
                }
                case PROBABILITY: {
                    IRTreeReturn<IntVariable> value = (IRTreeReturn<IntVariable>) args[0];
                    IRTreeReturn<BooleanVariable> guard = greaterThanEqual(value, constant(0));
                    IRTreeReturn<DoubleVariable> function = (IRTreeReturn<DoubleVariable>) new IRRVFunctionCallReturn<>(
                            t, outputType, source, sink, args);
                    return (IRTreeReturn<X>) conditionalAssignment(guard, function, constant(0.0));
                }
                case SAMPLE:
                    break;
            }
        } else if(source == VariableType.NegativeBinomial) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<IntVariable> value = (IRTreeReturn<IntVariable>) args[0];
                    IRTreeReturn<BooleanVariable> guard = greaterThanEqual(value, constant(0));
                    IRTreeReturn<DoubleVariable> function = (IRTreeReturn<DoubleVariable>) new IRRVFunctionCallReturn<>(
                            t, outputType, source, sink, args);
                    return (IRTreeReturn<X>) conditionalAssignment(guard, function, constant(Double.NEGATIVE_INFINITY));
                }
                case PROBABILITY: {
                    IRTreeReturn<IntVariable> value = (IRTreeReturn<IntVariable>) args[0];
                    IRTreeReturn<BooleanVariable> guard = greaterThanEqual(value, constant(0));
                    IRTreeReturn<DoubleVariable> function = (IRTreeReturn<DoubleVariable>) new IRRVFunctionCallReturn<>(
                            t, outputType, source, sink, args);
                    return (IRTreeReturn<X>) conditionalAssignment(guard, function, constant(0.0));
                }
                case SAMPLE: {
                    // Samples are drawn from a Poisson distribution with lambda drawn from the Gamma distribution with
                    // parameters r and p/(1-p)
                    IRTreeReturn<DoubleVariable> p = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<IntVariable> r = (IRTreeReturn<IntVariable>) args[2];

                    IRTreeReturn<?>[] gammaArgs = new IRTreeReturn<?>[3];
                    gammaArgs[0] = args[0]; // RNG
                    gammaArgs[1] = r;
                    gammaArgs[2] = divideDD(p, subtractDD(constant(1.0), p));

                    IRTreeReturn<?>[] poissonArgs = new IRTreeReturn<?>[2];
                    poissonArgs[0] = args[0]; // RNG
                    poissonArgs[1] = constructInternal(t, VariableType.DoubleVariable, VariableType.Gamma, sink,
                            gammaArgs);

                    return (IRTreeReturn<X>) constructInternal(t, VariableType.IntVariable, VariableType.Poisson, sink,
                            poissonArgs);
                }
            }
        } else if(source == VariableType.Uniform) {
            switch(t) {
                case ADD_DISTRIBUTION:
                case CONJUGATE_SAMPLE:
                    break;
                case LOG_PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> start = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> end = (IRTreeReturn<DoubleVariable>) args[2];

                    List<IRTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                    constraints.add(greaterThanEqual(value, start));
                    constraints.add(lessThanEqual(value, end));
                    IRTreeReturn<BooleanVariable> guard = and(constraints);
                    return (IRTreeReturn<X>) conditionalAssignment(guard, negate(log(subtractDD(end, start))),
                            constant(Double.NEGATIVE_INFINITY));
                }
                case PROBABILITY: {
                    IRTreeReturn<DoubleVariable> value = (IRTreeReturn<DoubleVariable>) args[0];
                    IRTreeReturn<DoubleVariable> start = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> end = (IRTreeReturn<DoubleVariable>) args[2];

                    List<IRTreeReturn<BooleanVariable>> constraints = new ArrayList<>();
                    constraints.add(greaterThanEqual(value, start));
                    constraints.add(lessThanEqual(value, end));
                    IRTreeReturn<BooleanVariable> guard = and(constraints);
                    return (IRTreeReturn<X>) conditionalAssignment(guard,
                            divideDD(constant(1.0), subtractDD(end, start)), constant(0.0));
                }
                case SAMPLE: {
                    IRTreeReturn<DoubleVariable> start = (IRTreeReturn<DoubleVariable>) args[1];
                    IRTreeReturn<DoubleVariable> end = (IRTreeReturn<DoubleVariable>) args[2];

                    // Sample between 0 and 1
                    IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[1];
                    newArgs[0] = args[0];
                    IRTreeReturn<DoubleVariable> sample = new IRRVFunctionCallReturn<DoubleVariable>(t,
                            VariableType.DoubleVariable, source, sink, newArgs);

                    // Scale and shift
                    return (IRTreeReturn<X>) addDD(start, multiplyDD(subtractDD(end, start), sample));
                }
            }
        }
        return new IRRVFunctionCallReturn<>(t, outputType, source, sink, args);
    }

    private static IRTreeReturn<?>[] processArgs(FunctionType t, RandomVariableType<?, ?> source,
            IRTreeReturn<?>[] args) {
        // Add the required RNG.
        if(t == FunctionType.CONJUGATE_SAMPLE || t == FunctionType.SAMPLE) {
            IRTreeReturn<?>[] newArgs = new IRTreeReturn<?>[args.length + 1];
            newArgs[0] = IRTree.load(VariableNames.rngName(0));
            System.arraycopy(args, 0, newArgs, 1, args.length);
            args = newArgs;
        }
        return args;
    }

    private static IRTreeReturn<DoubleVariable> normalizeGaussian(IRTreeReturn<DoubleVariable> x,
            IRTreeReturn<DoubleVariable> mean, IRTreeReturn<DoubleVariable> variance) {
        return divideDD(subtractDD(x, mean), sqrt(variance));
    }
}
