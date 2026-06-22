package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.*;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.*;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.*;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.GeneratedAPIBuilder;
import org.sandwood.compiler.compilation.util.CompilationDesc;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class LowDimMix extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> yObserved = observeArray("yObserved", VariableType.arrayType(VariableType.DoubleVariable), location(11, 24, 11, 41));

        IntVariable N = yObserved.length(location(12, 23, 12, 28));
        N.setAlias("N");
        N.setLocation(location(12, 9, 12, 9));

        ArrayVariable<DoubleVariable> rawMu = gaussian(doubleVariable(0.0, location(17, 31, 17, 33)), doubleVariable(2.0, location(17, 36, 17, 38)).times(doubleVariable(2.0, location(17, 42, 17, 44)), location(17, 40, 17, 40)), location(17, 22, 17, 45)).sample(intVariable(2, location(17, 54, 17, 54)), location(17, 47, 17, 55));
        rawMu.setAlias("rawMu");
        rawMu.setLocation(location(17, 14, 17, 18));

        ArrayVariable<DoubleVariable> mu = Variable.arrayVariable(location(18, 29, 18, 31), VariableType.DoubleVariable, intVariable(2, location(18, 30, 18, 30)));
        mu.setAlias("mu");
        mu.setLocation(location(18, 14, 18, 15));

        mu.put(intVariable(0, location(19, 8, 19, 8)), ifElseLambdaAssignment(rawMu.get(intVariable(0, location(19, 19, 19, 19)), location(19, 18, 19, 20)).lessThan(rawMu.get(intVariable(1, location(19, 30, 19, 30)), location(19, 29, 19, 31)), location(19, 22, 19, 22)), () -> { return rawMu.get(intVariable(0, location(19, 41, 19, 41)), location(19, 40, 19, 42)); }, () -> { return rawMu.get(intVariable(1, location(19, 52, 19, 52)), location(19, 51, 19, 53)); }, location(19, 13, 19, 53)), location(19, 7, 19, 53));
        mu.put(intVariable(1, location(20, 8, 20, 8)), ifElseLambdaAssignment(rawMu.get(intVariable(0, location(20, 19, 20, 19)), location(20, 18, 20, 20)).lessThan(rawMu.get(intVariable(1, location(20, 30, 20, 30)), location(20, 29, 20, 31)), location(20, 22, 20, 22)), () -> { return rawMu.get(intVariable(1, location(20, 41, 20, 41)), location(20, 40, 20, 42)); }, () -> { return rawMu.get(intVariable(0, location(20, 52, 20, 52)), location(20, 51, 20, 53)); }, location(20, 13, 20, 53)), location(20, 7, 20, 53));
        ArrayVariable<DoubleVariable> sigma = truncatedGaussian(doubleVariable(0.0, location(23, 40, 23, 42)), doubleVariable(2.0, location(23, 45, 23, 47)).times(doubleVariable(2.0, location(23, 51, 23, 53)), location(23, 49, 23, 49)), doubleVariable(0.0, location(23, 56, 23, 58)), doubleVariable(1.0e100, location(23, 61, 23, 67)), location(23, 22, 23, 68)).sample(intVariable(2, location(23, 77, 23, 77)), location(23, 70, 23, 78));
        sigma.setAlias("sigma");
        sigma.setLocation(location(23, 14, 23, 18));

        DoubleVariable theta = beta(doubleVariable(5.0, location(26, 25, 26, 27)), doubleVariable(5.0, location(26, 30, 26, 32)), location(26, 20, 26, 33)).sample(location(26, 35, 26, 42));
        theta.setAlias("theta");
        theta.setLocation(location(26, 12, 26, 16));

        Bernoulli componentDistribution = bernoulli(theta, location(32, 39, 32, 54));
        componentDistribution.setAlias("componentDistribution");
        componentDistribution.setLocation(location(32, 15, 32, 35));

        ArrayVariable<BooleanVariable> component = componentDistribution.sample(N, location(33, 49, 33, 57));
        component.setAlias("component");
        component.setLocation(location(33, 15, 33, 23));

        ArrayVariable<DoubleVariable> y = Variable.arrayVariable(location(34, 28, 34, 30), VariableType.DoubleVariable, N);
        y.setAlias("y");
        y.setLocation(location(34, 14, 34, 14));

        parFor(intVariable(0, location(36, 17, 36, 17)), N, intVariable(1, location(36, 27, 36, 29)), true, location(36, 5, 36, 30), (n) -> {
            n.setAlias("n");
            n.setLocation(location(36, 13, 36, 13));
            DoubleVariable componentMu = ifElseLambdaAssignment(component.get(n, location(37, 39, 37, 41)), () -> { return mu.get(intVariable(0, location(37, 48, 37, 48)), location(37, 47, 37, 49)); }, () -> { return mu.get(intVariable(1, location(37, 56, 37, 56)), location(37, 55, 37, 57)); }, location(37, 30, 37, 57));
            componentMu.setAlias("componentMu");
            componentMu.setLocation(location(37, 16, 37, 26));

            DoubleVariable componentSigma = ifElseLambdaAssignment(component.get(n, location(38, 42, 38, 44)), () -> { return sigma.get(intVariable(0, location(38, 54, 38, 54)), location(38, 53, 38, 55)); }, () -> { return sigma.get(intVariable(1, location(38, 65, 38, 65)), location(38, 64, 38, 66)); }, location(38, 33, 38, 66));
            componentSigma.setAlias("componentSigma");
            componentSigma.setLocation(location(38, 16, 38, 29));

            y.put(n, gaussian(componentMu, componentSigma.times(componentSigma, location(39, 53, 39, 53)), location(39, 16, 39, 69)).sample(location(39, 71, 39, 78)), location(39, 10, 39, 78));

        });

        y.observe(yObserved, location(42, 7, 42, 24));

        Variable<?>[] $variableNames = {yObserved, N, rawMu, mu, sigma, theta, componentDistribution, component, y};
        String[] $constructorArgs = {"yObserved"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LowDimMix", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
             + " * \n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model LowDimMix(double[] yObserved) {\n"
             + "    int N = yObserved.length;\n"
             + "\n"
             + "    // Stan parameter: ordered[2] mu; prior: mu ~ normal(0, 2)\n"
             + "    // Sampling two unconstrained normal values and sorting them gives the same ordered support up to\n"
             + "    // the constant normalisation factor for the ordered constraint.\n"
             + "    double[] rawMu = gaussian(0.0, 2.0 * 2.0).sample(2);\n"
             + "    double[] mu = new double[2];\n"
             + "    mu[0] = rawMu[0] < rawMu[1] ? rawMu[0] : rawMu[1];\n"
             + "    mu[1] = rawMu[0] < rawMu[1] ? rawMu[1] : rawMu[0];\n"
             + "\n"
             + "    // Stan parameter: array[2] real<lower=0> sigma; prior: sigma ~ normal(0, 2)\n"
             + "    double[] sigma = truncatedGaussian(0.0, 2.0 * 2.0, 0.0, 1.0e100).sample(2);\n"
             + "\n"
             + "    // Stan parameter: real<lower=0, upper=1> theta; prior: theta ~ beta(5, 5)\n"
             + "    double theta = beta(5.0, 5.0).sample();\n"
             + "\n"
             + "    // Stan likelihood:\n"
             + "    // target += log_mix(theta, normal_lpdf(y[n] | mu[1], sigma[1]),\n"
             + "    //                   normal_lpdf(y[n] | mu[2], sigma[2]));\n"
             + "    // In Sandwood, represent the same two-component mixture with explicit latent component indicators.\n"
             + "    Bernoulli componentDistribution = bernoulli(theta);\n"
             + "    boolean[] component = componentDistribution.sample(N);\n"
             + "    double[] y = new double[N];\n"
             + "\n"
             + "    for(int n = 0; n < N; n++) {\n"
             + "        double componentMu = component[n] ? mu[0] : mu[1];\n"
             + "        double componentSigma = component[n] ? sigma[0] : sigma[1];\n"
             + "        y[n] = gaussian(componentMu, componentSigma * componentSigma).sample();\n"
             + "    }\n"
             + "\n"
             + "    y.observe(yObserved);\n"
             + "}\n"
             + "";
    }
}