/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.inference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.inference.conjugatePrior.BetaToBernoulliBinomial;
import org.sandwood.compiler.compilation.inference.conjugatePrior.DirichletToCategoricalMultinomial;
import org.sandwood.compiler.compilation.inference.conjugatePrior.GammaToExponential;
import org.sandwood.compiler.compilation.inference.conjugatePrior.GammaToGaussian;
import org.sandwood.compiler.compilation.inference.conjugatePrior.GammaToPoisson;
import org.sandwood.compiler.compilation.inference.conjugatePrior.GaussianToGaussian;
import org.sandwood.compiler.compilation.inference.conjugatePrior.InverseGammaToGaussian;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Beta;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.InverseGamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.util.Pair;

public class InferenceGeneratorLookup {
    private final static Map<RandomVariableType<?, ?>, List<Pair<Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>>, InferenceGenerator<?, ?>>>> generatorOptions = new HashMap<>();

    private static class BooleanArrayWrapper {
        public final boolean[] a;

        BooleanArrayWrapper(boolean[] a) {
            this.a = a;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(a);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if((obj == null) || (getClass() != obj.getClass()))
                return false;
            BooleanArrayWrapper other = (BooleanArrayWrapper) obj;
            if(a.length != other.a.length)
                return false;
            for(int i = 0; i < a.length; i++)
                if(a[i] != other.a[i])
                    return false;
            return true;
        }

        @Override
        public String toString() {
            return Arrays.toString(a);
        }
    }

    static {
        addBernoulliBinomialToBeta();
        addDirichletToCategoricalMultinomial();
        addGaussianToGaussian();
        addGammaToExponential();
        addGammaToGaussian();
        addGammaToPoisson();
        addInverseGammaToGaussian();
    }

    private static <A extends Variable<A>, B extends RandomVariable<A, B>> void addConjugateGenerator(
            RandomVariableType<A, B> sourceType,
            Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> acceptedArguments,
            InferenceGenerator<A, B> generator) {
        // See if we already have options for this type of random variable, if we don't,
        // create a set to hold them
        List<Pair<Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>>, InferenceGenerator<?, ?>>> availableOptions = generatorOptions
                .computeIfAbsent(sourceType, k -> new ArrayList<>());

        availableOptions.add(new Pair<>(acceptedArguments, generator));
    }

    public static <A extends ScalarVariable<A>, B extends RandomVariable<A, B>> InferenceGenerator<A, B> lookupConjugate(
            SampleTask<A, B> sample, Map<RandomVariable<?, ?>, boolean[]> consumingVariables, List<String> suggestions,
            CompilationContext compilationCtx) {

        RandomVariableType<A, B> sourceType = sample.randomVariable.getType();

        // Condense input to type and sets of positions.
        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> consumingSignatures = new HashMap<>();
        for(RandomVariable<?, ?> rv:consumingVariables.keySet()) {
            RandomVariableType<?, ?> rvType = rv.getType();
            Set<BooleanArrayWrapper> positions = consumingSignatures.computeIfAbsent(rvType, k -> new HashSet<>());

            positions.add(new BooleanArrayWrapper(consumingVariables.get(rv)));
        }

        // find all instances of options for the sourceType
        List<Pair<Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>>, InferenceGenerator<?, ?>>> possibleGenerators = generatorOptions
                .get(sourceType);
        if(possibleGenerators != null)
            // Run through the list in order to find the first option.
            for(Pair<Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>>, InferenceGenerator<?, ?>> p:possibleGenerators) {
                if(compatibleSignatures(consumingSignatures, p.a)) {
                    @SuppressWarnings("unchecked")
                    InferenceGenerator<A, B> generator = (InferenceGenerator<A, B>) p.b;
                    if(generator.canAcceptTraces(sample, suggestions, compilationCtx))
                        return generator;
                }
            }

        return null;
    }

    private static boolean compatibleSignatures(
            Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> consumingSignatures,
            Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> acceptedSignatures) {
        // For each variable type test that signatures for this type with these
        // positions exist.
        for(RandomVariableType<?, ?> rvType:consumingSignatures.keySet()) {
            Set<BooleanArrayWrapper> availablePositions = acceptedSignatures.get(rvType);
            if(availablePositions == null)
                return false;
            for(BooleanArrayWrapper positions:consumingSignatures.get(rvType)) {
                if(!availablePositions.contains(positions))
                    return false;
            }
        }
        return true;
    }

    private static void addBernoulliBinomialToBeta() {

        boolean[] positions = new boolean[1];
        positions[0] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));

        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();
        availableConfigurations.put(VariableType.Bernoulli, allPositions);

        positions = new boolean[2];
        positions[0] = true;
        allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));
        availableConfigurations.put(VariableType.Binomial, allPositions);

        InferenceGenerator<DoubleVariable, Beta> generator = BetaToBernoulliBinomial.getGenerator();

        addConjugateGenerator(VariableType.Beta, availableConfigurations, generator);
    }

    private static void addDirichletToCategoricalMultinomial() {
        // Map to hold the available positions for each type of RV to receive a Dirichlet sample as an argument.
        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();

        // Add the ability to handle categorical distributions
        boolean[] positions = new boolean[1];
        positions[0] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));
        availableConfigurations.put(VariableType.Categorical, allPositions);

        // Add the ability to handle multinomial distributions
        positions = new boolean[2];
        positions[0] = true;
        allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));
        availableConfigurations.put(VariableType.Multinomial, allPositions);

        InferenceGenerator<ArrayVariable<DoubleVariable>, Dirichlet> generator = DirichletToCategoricalMultinomial
                .getGenerator();

        addConjugateGenerator(VariableType.Dirichlet, availableConfigurations, generator);
    }

    private static void addGaussianToGaussian() {

        boolean[] positions = new boolean[2];
        positions[0] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));

        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();
        availableConfigurations.put(VariableType.Gaussian, allPositions);

        InferenceGenerator<DoubleVariable, Gaussian> generator = GaussianToGaussian.getGenerator();

        addConjugateGenerator(VariableType.Gaussian, availableConfigurations, generator);
    }

    private static void addInverseGammaToGaussian() {

        boolean[] positions = new boolean[2];
        positions[1] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));

        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();
        availableConfigurations.put(VariableType.Gaussian, allPositions);

        InferenceGenerator<DoubleVariable, InverseGamma> generator = InverseGammaToGaussian.getGenerator();

        addConjugateGenerator(VariableType.InverseGamma, availableConfigurations, generator);
    }

    private static void addGammaToExponential() {

        boolean[] positions = new boolean[1];
        positions[0] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));

        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();
        availableConfigurations.put(VariableType.Exponential, allPositions);

        InferenceGenerator<DoubleVariable, Gamma> generator = GammaToExponential.getGenerator();

        addConjugateGenerator(VariableType.Gamma, availableConfigurations, generator);
    }

    private static void addGammaToGaussian() {

        boolean[] positions = new boolean[2];
        positions[1] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));

        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();
        availableConfigurations.put(VariableType.Gaussian, allPositions);

        InferenceGenerator<DoubleVariable, Gamma> generator = GammaToGaussian.getGenerator();

        addConjugateGenerator(VariableType.Gamma, availableConfigurations, generator);
    }

    private static void addGammaToPoisson() {

        boolean[] positions = new boolean[1];
        positions[0] = true;
        Set<BooleanArrayWrapper> allPositions = new HashSet<>();
        allPositions.add(new BooleanArrayWrapper(positions));

        Map<RandomVariableType<?, ?>, Set<BooleanArrayWrapper>> availableConfigurations = new HashMap<>();
        availableConfigurations.put(VariableType.Poisson, allPositions);

        InferenceGenerator<DoubleVariable, Gamma> generator = GammaToPoisson.getGenerator();

        addConjugateGenerator(VariableType.Gamma, availableConfigurations, generator);
    }
}
