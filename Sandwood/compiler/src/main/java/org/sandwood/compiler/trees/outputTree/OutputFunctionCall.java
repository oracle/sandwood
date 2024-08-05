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
import java.util.Objects;
import java.util.Set;

import org.sandwood.compiler.compilation.FunctionType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.FunctionName;

public class OutputFunctionCall extends OutputTree {

    protected static class FunctionDesc {
        final String name;
        final String javaClass;
        final String javaMethodName;
        final boolean random;

        FunctionDesc(String name, String javaClass, String javaMethodName, boolean random) {
            this.name = name;
            this.javaClass = javaClass;
            this.javaMethodName = javaMethodName;
            this.random = random;
        }

        FunctionDesc(FunctionName javaMethodName) {
            this.name = javaMethodName.getName();
            this.javaClass = null;
            this.javaMethodName = javaMethodName.getName();
            this.random = false;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Objects.hash(javaClass, javaMethodName, name);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if((obj == null) || (getClass() != obj.getClass()))
                return false;
            FunctionDesc other = (FunctionDesc) obj;
            return Objects.equals(javaClass, other.javaClass) && Objects.equals(javaMethodName, other.javaMethodName)
                    && Objects.equals(name, other.name);
        }
    }

    private final FunctionDesc f;
    private final OutputTreeReturn<?>[] args;

    private OutputFunctionCall(FunctionDesc f, OutputTreeReturn<?>[] args, String comment) {
        super(OutputTreeType.FUNCTION_CALL, false, comment);
        this.f = f;
        this.args = args;

        for(OutputTreeReturn<?> a:args)
            if(a.type == OutputTreeType.CAST_TO_DOUBLE)
                ((OutputCastToDouble) a).setImplicit();
    }

    // Methods and data structures for constructing functions to go with random
    // variables.
    private static final Map<RandomVariableType<?, ?>, Map<RandomVariableType<?, ?>, FunctionDesc>> conjugateFunctionDescriptions;
    private static final Map<FunctionType, Map<RandomVariableType<?, ?>, FunctionDesc>> RVFunctionDescriptions;

    static {
        conjugateFunctionDescriptions = new HashMap<>();
        addConjugate(VariableType.Dirichlet, VariableType.Categorical);

        RVFunctionDescriptions = new HashMap<>();

        // Add sample functions
        Map<RandomVariableType<?, ?>, FunctionDesc> sample = new HashMap<>();
        RVFunctionDescriptions.put(FunctionType.SAMPLE, sample);
        addSampleFunc(VariableType.Dirichlet);
        addSampleFunc(VariableType.Multinomial);

        // Add distribution add functions
        Map<RandomVariableType<?, ?>, FunctionDesc> addDistribution = new HashMap<>();
        RVFunctionDescriptions.put(FunctionType.ADD_DISTRIBUTION, addDistribution);
        addAddDistributionFunc(VariableType.Bernoulli);
        addAddDistributionFunc(VariableType.Binomial);
        addAddDistributionFunc(VariableType.Categorical);
    }

    private static void addSampleFunc(RandomVariableType<?, ?> rv) {
        Map<RandomVariableType<?, ?>, FunctionDesc> samples = RVFunctionDescriptions.get(FunctionType.SAMPLE);
        String name = rv.getAPIType();
        FunctionDesc f = new FunctionDesc(name + " Sample",
                "org.sandwood.runtime.internal.numericTools.DistributionSampling", "sample" + name, true);
        samples.put(rv, f);
    }

    private static void addAddDistributionFunc(RandomVariableType<?, ?> rv) {
        Map<RandomVariableType<?, ?>, FunctionDesc> addDistribution = RVFunctionDescriptions
                .get(FunctionType.ADD_DISTRIBUTION);
        String name = rv.getAPIType();
        FunctionDesc f = new FunctionDesc("Add probability distribution " + name,
                "org.sandwood.runtime.internal.numericTools.DistributionSampling", "addProbabilityDistribution" + name,
                false);
        addDistribution.put(rv, f);
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

    public static OutputFunctionCall getFunctionCall(FunctionType t, RandomVariableType<?, ?> source,
            RandomVariableType<?, ?> sink, OutputTreeReturn<?>[] args, String comment) {
        FunctionDesc f = null;
        if(t == FunctionType.CONJUGATE_SAMPLE)
            f = conjugateFunctionDescriptions.get(source).get(sink);
        else
            f = RVFunctionDescriptions.get(t).get(source);
        if(f == null)
            throw new CompilerException("Function is unknown. Type: " + t);
        return new OutputFunctionCall(f, args, comment);
    }

    public static OutputFunctionCall getFunctionCall(FunctionName name, OutputTreeReturn<?>[] args, String comment) {
        return new OutputFunctionCall(new FunctionDesc(name), args, comment);
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
    protected OutputFunctionCall copy(Map<OutputTree, OutputTree> results) {
        int size = args.length;
        OutputTreeReturn<?>[] a = new OutputTreeReturn[size];
        for(int i = 0; i < size; i++)
            a[i] = args[i].copy(results);
        OutputFunctionCall f = new OutputFunctionCall(this.f, a, comment);
        results.put(this, f);
        return f;
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
        OutputFunctionCall other = (OutputFunctionCall) obj;
        if(!Arrays.equals(args, other.args))
            return false;
        return f.equals(other.f);
    }
}
