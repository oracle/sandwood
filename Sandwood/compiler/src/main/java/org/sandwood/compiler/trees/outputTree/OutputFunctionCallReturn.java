/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.compilation.ExternalFunction;
import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.exceptions.CompilerException;

public class OutputFunctionCallReturn<X extends Variable<X>> extends OutputTreeReturn<X> {

    protected static class FunctionDesc {
        final String name;
        final String javaClass;
        final String javaMethodName;
        final boolean random;
        // Static arguments are no longer used, and probably should be removed.
        final String[] staticArgs;

        FunctionDesc(String name, String javaClass, String javaMethodName, boolean random, String[] staticArgs) {
            this.name = name;
            this.javaClass = javaClass;
            this.javaMethodName = javaMethodName;
            this.random = random;
            this.staticArgs = staticArgs;
        }

        FunctionDesc(String name, String javaClass, String javaMethodName, boolean random) {
            this.name = name;
            this.javaClass = javaClass;
            this.javaMethodName = javaMethodName;
            this.random = random;
            this.staticArgs = new String[0];
        }

        FunctionDesc(String javaMethodName) {
            this.name = javaMethodName;
            this.javaClass = null;
            this.javaMethodName = javaMethodName;
            this.random = false;
            this.staticArgs = new String[0];
        }
    }

    protected final OutputTreeReturn<?>[] args;
    protected final FunctionDesc f;

    private OutputFunctionCallReturn(FunctionDesc f, OutputTreeReturn<?>[] args) {
        super(OutputTreeType.FUNCTION_CALL_RETURN);
        this.f = f;
        this.args = args;
        for(OutputTreeReturn<?> a:args)
            if(a.type == OutputTreeType.CAST_TO_DOUBLE)
                ((OutputCastToDouble) a).setImplicit();
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        if(f.javaClass != null) {
            String[] parts = f.javaClass.split("\\.");
            sb.append(parts[parts.length - 1] + ".");
            if(parts.length != 1)
                requiredImports.add(f.javaClass);
        }
        sb.append(f.javaMethodName + "(");
        boolean first = true;

        for(String arg:f.staticArgs) {
            if(first)
                first = false;
            else
                sb.append(", ");
            sb.append(arg);
        }

        for(OutputTreeReturn<?> t:args) {
            if(first)
                first = false;
            else
                sb.append(", ");
            t.toJava(sb, indent, requiredImports);
        }
        sb.append(")");
    }

    @Override
    public OutputTree[] getChildren() {
        return args;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(f.name);
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
    protected OutputFunctionCallReturn<X> copy(Map<OutputTree, OutputTree> results) {
        int size = args.length;
        OutputTreeReturn<?>[] a = new OutputTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = args[i].copy(results);
        OutputFunctionCallReturn<X> func = new OutputFunctionCallReturn<>(f, a);
        results.put(this, func);
        return func;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(args);
        result = prime * result + f.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputFunctionCallReturn<?> other = (OutputFunctionCallReturn<?>) obj;
        if(!Arrays.equals(args, other.args))
            return false;
        return f.equals(other.f);
    }

    // Methods and data structures for constructing functions to go with random
    // variables.
    private static final Map<FunctionType, Map<RandomVariableType<?, ?>, FunctionDesc>> RVFunctionDescriptions;
    private static final Map<RandomVariableType<?, ?>, Map<RandomVariableType<?, ?>, FunctionDesc>> conjugateFunctionDescriptions;
    private static final Map<ExternalFunction, FunctionDesc> externalDescriptions;

    private static void addSampleFunc(RandomVariableType<?, ?> rv) {
        Map<RandomVariableType<?, ?>, FunctionDesc> samples = RVFunctionDescriptions.get(FunctionType.SAMPLE);
        String name = rv.getAPIType();
        FunctionDesc f = new FunctionDesc(name + " Sample",
                "org.sandwood.runtime.internal.numericTools.DistributionSampling", "sample" + name, true);
        samples.put(rv, f);
    }

    private static void addConjugate(RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink) {
        Map<RandomVariableType<?, ?>, FunctionDesc> m = conjugateFunctionDescriptions.computeIfAbsent(source,
                k -> new HashMap<>());

        String sourceName = source.getAPIType();
        String sinkName = sink.getAPIType();
        FunctionDesc desc = new FunctionDesc(sourceName + " " + sinkName + " Conjugate",
                "org.sandwood.runtime.internal.numericTools.Conjugates", "sampleConjugate" + sourceName + sinkName,
                true);
        m.put(sink, desc);
    }

    private static void addProbabilityFunc(RandomVariableType<?, ?> rv) {
        Map<RandomVariableType<?, ?>, FunctionDesc> probabilities = RVFunctionDescriptions
                .get(FunctionType.PROBABILITY);
        String name = rv.getAPIType();
        FunctionDesc f = new FunctionDesc(name + " Probability",
                "org.sandwood.runtime.internal.numericTools.DistributionSampling", "probability" + name, false);
        probabilities.put(rv, f);
    }

    private static void addLogProbabilityFunc(RandomVariableType<?, ?> rv) {
        Map<RandomVariableType<?, ?>, FunctionDesc> logProbabilities = RVFunctionDescriptions
                .get(FunctionType.LOG_PROBABILITY);
        String name = rv.getAPIType();
        FunctionDesc f = new FunctionDesc(name + " Log Probability",
                "org.sandwood.runtime.internal.numericTools.DistributionSampling", "logProbability" + name, false);
        logProbabilities.put(rv, f);
    }

    static {
        RVFunctionDescriptions = new HashMap<>();
        conjugateFunctionDescriptions = new HashMap<>();
        externalDescriptions = new HashMap<>();

        {
            Map<RandomVariableType<?, ?>, FunctionDesc> sample = new HashMap<>();
            RVFunctionDescriptions.put(FunctionType.SAMPLE, sample);
            addSampleFunc(VariableType.Bernoulli);
            addSampleFunc(VariableType.Beta);
            addSampleFunc(VariableType.Binomial);
            addSampleFunc(VariableType.Categorical);
            addSampleFunc(VariableType.Cauchy);
            addSampleFunc(VariableType.Exponential);
            addSampleFunc(VariableType.Gamma);
            addSampleFunc(VariableType.Gaussian);
            addSampleFunc(VariableType.HalfCauchy);
            addSampleFunc(VariableType.InverseGamma);
            addSampleFunc(VariableType.Poisson);
            addSampleFunc(VariableType.StudentT);
            addSampleFunc(VariableType.Uniform);
        }
        {
            addConjugate(VariableType.Beta, VariableType.Binomial);
            addConjugate(VariableType.Gaussian, VariableType.Gaussian);
            addConjugate(VariableType.Gamma, VariableType.Exponential);
            addConjugate(VariableType.Gamma, VariableType.Gaussian);
            addConjugate(VariableType.Gamma, VariableType.Poisson);
            addConjugate(VariableType.InverseGamma, VariableType.Gaussian);
        }
        {
            Map<RandomVariableType<?, ?>, FunctionDesc> probabilities = new HashMap<>();
            RVFunctionDescriptions.put(FunctionType.PROBABILITY, probabilities);
            addProbabilityFunc(VariableType.Bernoulli);
            addProbabilityFunc(VariableType.Beta);
            addProbabilityFunc(VariableType.Binomial);
            addProbabilityFunc(VariableType.Categorical);
            addProbabilityFunc(VariableType.Cauchy);
            addProbabilityFunc(VariableType.Dirichlet);
            addProbabilityFunc(VariableType.Exponential);
            addProbabilityFunc(VariableType.Gamma);
            addProbabilityFunc(VariableType.Gaussian);
            addProbabilityFunc(VariableType.HalfCauchy);
            addProbabilityFunc(VariableType.InverseGamma);
            addProbabilityFunc(VariableType.Multinomial);
            addProbabilityFunc(VariableType.Poisson);
            addProbabilityFunc(VariableType.StudentT);
            addProbabilityFunc(VariableType.Uniform);
        }
        {
            Map<RandomVariableType<?, ?>, FunctionDesc> logProbabilities = new HashMap<>();
            RVFunctionDescriptions.put(FunctionType.LOG_PROBABILITY, logProbabilities);
            addLogProbabilityFunc(VariableType.Bernoulli);
            addLogProbabilityFunc(VariableType.Beta);
            addLogProbabilityFunc(VariableType.Binomial);
            addLogProbabilityFunc(VariableType.Categorical);
            addLogProbabilityFunc(VariableType.Cauchy);
            addLogProbabilityFunc(VariableType.Dirichlet);
            addLogProbabilityFunc(VariableType.Exponential);
            addLogProbabilityFunc(VariableType.Gamma);
            addLogProbabilityFunc(VariableType.Gaussian);
            addLogProbabilityFunc(VariableType.HalfCauchy);
            addLogProbabilityFunc(VariableType.InverseGamma);
            addLogProbabilityFunc(VariableType.Multinomial);
            addLogProbabilityFunc(VariableType.Poisson);
            addLogProbabilityFunc(VariableType.StudentT);
            addLogProbabilityFunc(VariableType.Uniform);
        }
        {
            externalDescriptions.put(ExternalFunction.IS_NAN, new FunctionDesc("IsNaN", "Double", "isNaN", false));
        }
    }

    public static <X extends Variable<X>> OutputFunctionCallReturn<X> getFunctionCallReturn(FunctionType t,
            RandomVariableType<?, ?> source, RandomVariableType<?, ?> sink, OutputTreeReturn<?>[] args) {
        FunctionDesc f;
        if(t == FunctionType.CONJUGATE_SAMPLE)
            f = conjugateFunctionDescriptions.get(source).get(sink);
        else
            f = RVFunctionDescriptions.get(t).get(source);
        if(f == null)
            throw new CompilerException("Function is unknown. Type: " + t + " RV type: " + source);
        return new OutputFunctionCallReturn<>(f, args);
    }

    public static <X extends Variable<X>> OutputFunctionCallReturn<X> getFunctionCallReturn(ExternalFunction func,
            OutputTreeReturn<?>[] args) {
        FunctionDesc f = externalDescriptions.get(func);
        if(f == null)
            throw new CompilerException("Function is unknown. External Function: " + func);
        return new OutputFunctionCallReturn<>(f, args);
    }

    public static <X extends Variable<X>> OutputFunctionCallReturn<X> getFunctionCallReturn(String name,
            OutputTreeReturn<?>[] args) {
        return new OutputFunctionCallReturn<>(new FunctionDesc(name), args);
    }

}