package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

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

public class HMMMetrics2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<DoubleVariable>> metric = observeArray("metric", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(12, 16, 12, 32));
        ArrayVariable<ArrayVariable<BooleanVariable>> metric_valid = observeArray("metric_valid", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(13, 16, 13, 39));
        IntVariable noStates = observeInt("noStates", location(14, 16, 14, 27));

        IntVariable noSamples = metric.length(location(16, 28, 16, 33));
        noSamples.setAlias("noSamples");
        noSamples.setLocation(location(16, 9, 16, 17));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(19, 28, 19, 37), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(19, 42, 19, 44)); }, noStates);
        v.setAlias("v");
        v.setLocation(location(19, 14, 19, 14));

        ArrayVariable<DoubleVariable> initialStateDistribution = dirichlet(v, location(20, 41, 20, 52)).sample(location(20, 54, 20, 61));
        initialStateDistribution.setAlias("initialStateDistribution");
        initialStateDistribution.setLocation(location(20, 14, 20, 37));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(21, 20, 21, 31)).sample(noStates, location(21, 33, 21, 48));
        m.setAlias("m");
        m.setLocation(location(21, 16, 21, 16));

        ArrayVariable<ArrayVariable<IntVariable>> st = Variable.arrayVariable(location(24, 25, 24, 37), VariableType.arrayType(VariableType.IntVariable), noSamples);
        st.setAlias("st");
        st.setLocation(location(24, 13, 24, 14));

        ArrayVariable<ArrayVariable<DoubleVariable>> metric_g = Variable.arrayVariable(location(27, 37, 27, 49), VariableType.arrayType(VariableType.DoubleVariable), noSamples);
        metric_g.setAlias("metric_g");
        metric_g.setLocation(location(27, 16, 27, 23));

        ArrayVariable<ArrayVariable<BooleanVariable>> metric_valid_g = Variable.arrayVariable(location(28, 45, 28, 57), VariableType.arrayType(VariableType.BooleanVariable), noSamples);
        metric_valid_g.setAlias("metric_valid_g");
        metric_valid_g.setLocation(location(28, 17, 28, 30));

        ArrayVariable<DoubleVariable> metric_mean = uniform(doubleVariable(0.0, location(31, 36, 31, 38)), doubleVariable(100.0, location(31, 41, 31, 45)), location(31, 28, 31, 46)).sample(noStates, location(31, 48, 31, 63));
        metric_mean.setAlias("metric_mean");
        metric_mean.setLocation(location(31, 14, 31, 24));

        ArrayVariable<DoubleVariable> metric_var = inverseGamma(doubleVariable(1.0, location(32, 40, 32, 42)), doubleVariable(1.0, location(32, 45, 32, 47)), location(32, 27, 32, 48)).sample(noStates, location(32, 50, 32, 65));
        metric_var.setAlias("metric_var");
        metric_var.setLocation(location(32, 14, 32, 23));

        ArrayVariable<DoubleVariable> metric_valid_bias = beta(doubleVariable(1.0, location(33, 39, 33, 41)), doubleVariable(1.0, location(33, 44, 33, 46)), location(33, 34, 33, 47)).sample(noStates, location(33, 49, 33, 64));
        metric_valid_bias.setAlias("metric_valid_bias");
        metric_valid_bias.setLocation(location(33, 14, 33, 30));

        parFor(intVariable(0, location(36, 22, 36, 22)), noSamples, intVariable(1, location(36, 45, 36, 52)), true, location(36, 5, 36, 53), (sample) -> { 
            sample.setAlias("sample");
            sample.setLocation(location(36, 13, 36, 18));
            IntVariable streamLength = metric.get(sample, location(38, 34, 38, 41)).length(location(38, 43, 38, 48));
            streamLength.setAlias("streamLength");
            streamLength.setLocation(location(38, 13, 38, 24));

            st.put(sample, Variable.arrayVariable(location(41, 29, 41, 42), VariableType.IntVariable, streamLength), location(41, 11, 41, 42));
            st.get(sample, location(44, 11, 44, 18)).put(intVariable(0, location(44, 20, 44, 20)), categorical(initialStateDistribution, location(44, 25, 44, 61)).sampleDistribution(location(44, 63, 44, 82)), location(44, 19, 44, 82));
            parFor(intVariable(1, location(47, 28, 47, 28)), streamLength, intVariable(1, location(47, 56, 47, 65)), true, location(47, 9, 47, 66), (timeStep) -> { 
                timeStep.setAlias("timeStep");
                timeStep.setLocation(location(47, 17, 47, 24));
                st.get(sample, location(48, 15, 48, 22)).put(timeStep, categorical(m.get(st.get(sample, location(48, 52, 48, 59)).get(timeStep.subtract(intVariable(1, location(48, 70, 48, 70)), location(48, 69, 48, 69)), location(48, 60, 48, 71)), location(48, 49, 48, 72)), location(48, 36, 48, 73)).sampleDistribution(location(48, 75, 48, 94)), location(48, 23, 48, 94));
            });

            ArrayVariable<DoubleVariable> metric_1d = Variable.arrayVariable(location(51, 40, 51, 53), VariableType.DoubleVariable, streamLength);
            metric_1d.setAlias("metric_1d");
            metric_1d.setLocation(location(51, 18, 51, 26));

            metric_g.put(sample, metric_1d, location(52, 17, 52, 36));
            ArrayVariable<BooleanVariable> metric_valid_1d = Variable.arrayVariable(location(54, 48, 54, 61), VariableType.BooleanVariable, streamLength);
            metric_valid_1d.setAlias("metric_valid_1d");
            metric_valid_1d.setLocation(location(54, 19, 54, 33));

            metric_valid_g.put(sample, metric_valid_1d, location(55, 23, 55, 48));
            parFor(intVariable(0, location(58, 28, 58, 28)), streamLength, intVariable(1, location(58, 56, 58, 65)), true, location(58, 9, 58, 66), (timeStep) -> { 
                timeStep.setAlias("timeStep");
                timeStep.setLocation(location(58, 17, 58, 24));
                IntVariable currentState = st.get(sample, location(59, 34, 59, 41)).get(timeStep, location(59, 42, 59, 51));
                currentState.setAlias("currentState");
                currentState.setLocation(location(59, 17, 59, 28));

                metric_valid_1d.put(timeStep, bernoulli(metric_valid_bias.get(currentState, location(61, 68, 61, 81)), location(61, 41, 61, 82)).sample(location(61, 84, 61, 91)), location(61, 28, 61, 91));
                BooleanVariable guard$1 = metric_valid_1d.get(timeStep, location(62, 31, 62, 40));
                ifElse(guard$1, () -> {
                    metric_1d.put(timeStep, gaussian(metric_mean.get(currentState, location(63, 59, 63, 72)), metric_var.get(currentState, location(63, 85, 63, 98)), location(63, 39, 63, 99)).sample(location(63, 101, 63, 108)), location(63, 26, 63, 108));
                }, () -> {
                });
                metric_1d.get(timeStep, location(65, 22, 65, 31)).observe(metric.get(sample, location(65, 47, 65, 54)).get(timeStep, location(65, 55, 65, 64)), location(65, 33, 65, 65));

            });


        });

        metric_valid_g.observe(metric_valid, location(70, 20, 70, 40));

        Variable<?>[] $variableNames = {metric, metric_valid, noStates, noSamples, v, initialStateDistribution, m, st, metric_g, metric_valid_g, metric_mean, metric_var, metric_valid_bias};
        String[] $constructorArgs = {"metric", "metric_valid", "noStates"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMMetrics2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics2(\n               double[][] metric,\n               boolean[][] metric_valid, \n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n\n    //Allocate space for states\n    int[][] st = new int[noSamples][];\n\n    //Allocate space for generated metrics \n    double[][] metric_g = new double[noSamples][];\n    boolean[][] metric_valid_g = new boolean[noSamples][];\n    \n    //Calculate priors for the metric\n    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n    \n    // Compute the values of each metric value\n    for(int sample = 0; sample < noSamples; sample++) {\n        //Calculate all the state transitions\n        int streamLength = metric[sample].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n        \n        //Calculate metric values\n        double[] metric_1d = new double[streamLength];\n        metric_g[sample] = metric_1d;\n\n        boolean[] metric_valid_1d = new boolean[streamLength];\n        metric_valid_g[sample] = metric_valid_1d;\n\n        //Generate values.\n        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n            int currentState = st[sample][timeStep];\n            \n            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n            if(metric_valid_1d[timeStep])\n                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n            metric_1d[timeStep].observe(metric[sample][timeStep]);\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n}";
    }
}