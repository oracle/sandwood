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

public class HMMMetrics4 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> metric = observeArray("metric", VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable))), location(12, 16, 12, 34));
        ArrayVariable<ArrayVariable<ArrayVariable<BooleanVariable>>> metric_valid = observeArray("metric_valid", VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable))), location(13, 16, 13, 41));
        IntVariable max_metric = observeInt("max_metric", location(14, 16, 14, 29));
        IntVariable noStates = observeInt("noStates", location(15, 16, 15, 27));

        IntVariable noSamples = metric.length(location(17, 28, 17, 33));
        noSamples.setAlias("noSamples");
        noSamples.setLocation(location(17, 9, 17, 17));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(20, 28, 20, 37), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(20, 42, 20, 44)); }, noStates);
        v.setAlias("v");
        v.setLocation(location(20, 14, 20, 14));

        ArrayVariable<DoubleVariable> initialStateDistribution = dirichlet(v, location(21, 41, 21, 52)).sample(location(21, 54, 21, 61));
        initialStateDistribution.setAlias("initialStateDistribution");
        initialStateDistribution.setLocation(location(21, 14, 21, 37));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(22, 20, 22, 31)).sample(noStates, location(22, 33, 22, 48));
        m.setAlias("m");
        m.setLocation(location(22, 16, 22, 16));

        ArrayVariable<ArrayVariable<IntVariable>> st = Variable.arrayVariable(location(25, 25, 25, 37), VariableType.arrayType(VariableType.IntVariable), noSamples);
        st.setAlias("st");
        st.setLocation(location(25, 13, 25, 14));

        parFor(intVariable(0, location(26, 22, 26, 22)), noSamples, intVariable(1, location(26, 45, 26, 52)), true, location(26, 5, 26, 53), (sample) -> { 
            sample.setAlias("sample");
            sample.setLocation(location(26, 13, 26, 18));
            IntVariable streamLength = metric.get(sample, location(27, 34, 27, 41)).get(intVariable(0, location(27, 43, 27, 43)), location(27, 42, 27, 44)).length(location(27, 46, 27, 51));
            streamLength.setAlias("streamLength");
            streamLength.setLocation(location(27, 13, 27, 24));

            st.put(sample, Variable.arrayVariable(location(30, 29, 30, 42), VariableType.IntVariable, streamLength), location(30, 11, 30, 42));
            st.get(sample, location(33, 11, 33, 18)).put(intVariable(0, location(33, 20, 33, 20)), categorical(initialStateDistribution, location(33, 25, 33, 61)).sampleDistribution(location(33, 63, 33, 82)), location(33, 19, 33, 82));
            parFor(intVariable(1, location(36, 28, 36, 28)), streamLength, intVariable(1, location(36, 56, 36, 65)), true, location(36, 9, 36, 66), (timeStep) -> { 
                timeStep.setAlias("timeStep");
                timeStep.setLocation(location(36, 17, 36, 24));
                st.get(sample, location(37, 15, 37, 22)).put(timeStep, categorical(m.get(st.get(sample, location(37, 52, 37, 59)).get(timeStep.subtract(intVariable(1, location(37, 70, 37, 70)), location(37, 69, 37, 69)), location(37, 60, 37, 71)), location(37, 49, 37, 72)), location(37, 36, 37, 73)).sampleDistribution(location(37, 75, 37, 94)), location(37, 23, 37, 94));
            });


        });

        IntVariable noServers = metric.get(intVariable(0, location(41, 28, 41, 28)), location(41, 27, 41, 29)).length(location(41, 31, 41, 36));
        noServers.setAlias("noServers");
        noServers.setLocation(location(41, 9, 41, 17));

        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> metric_g = Variable.arrayVariable(location(44, 39, 44, 62), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), noSamples, noServers);
        metric_g.setAlias("metric_g");
        metric_g.setLocation(location(44, 18, 44, 25));

        ArrayVariable<ArrayVariable<ArrayVariable<BooleanVariable>>> metric_valid_g = Variable.arrayVariable(location(45, 47, 45, 70), VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), noSamples, noServers);
        metric_valid_g.setAlias("metric_valid_g");
        metric_valid_g.setLocation(location(45, 19, 45, 32));

        ArrayVariable<ArrayVariable<DoubleVariable>> current_metric_mean = uniform(doubleVariable(0.0, location(48, 46, 48, 48)), max_metric.castToDouble(location(48, 51, 48, 58)), location(48, 38, 48, 70)).sample(noServers, noStates, location(48, 72, 48, 98));
        current_metric_mean.setAlias("current_metric_mean");
        current_metric_mean.setLocation(location(48, 16, 48, 34));

        ArrayVariable<ArrayVariable<DoubleVariable>> current_metric_var = inverseGamma(doubleVariable(1.0, location(49, 50, 49, 52)), doubleVariable(1.0, location(49, 55, 49, 57)), location(49, 37, 49, 58)).sample(noServers, noStates, location(49, 60, 49, 86));
        current_metric_var.setAlias("current_metric_var");
        current_metric_var.setLocation(location(49, 16, 49, 33));

        ArrayVariable<ArrayVariable<DoubleVariable>> current_metric_valid_bias = beta(doubleVariable(1.0, location(50, 49, 50, 51)), doubleVariable(1.0, location(50, 54, 50, 56)), location(50, 44, 50, 57)).sample(noServers, noStates, location(50, 59, 50, 85));
        current_metric_valid_bias.setAlias("current_metric_valid_bias");
        current_metric_valid_bias.setLocation(location(50, 16, 50, 40));

        parFor(intVariable(0, location(53, 22, 53, 22)), noSamples, intVariable(1, location(53, 45, 53, 52)), true, location(53, 5, 53, 53), (sample) -> { 
            sample.setAlias("sample");
            sample.setLocation(location(53, 13, 53, 18));
            IntVariable streamLength = metric.get(sample, location(54, 34, 54, 41)).get(intVariable(0, location(54, 43, 54, 43)), location(54, 42, 54, 44)).length(location(54, 46, 54, 51));
            streamLength.setAlias("streamLength");
            streamLength.setLocation(location(54, 13, 54, 24));

            parFor(intVariable(0, location(55, 26, 55, 26)), noServers, intVariable(1, location(55, 49, 55, 56)), true, location(55, 9, 55, 57), (server) -> { 
                server.setAlias("server");
                server.setLocation(location(55, 17, 55, 22));
                ArrayVariable<DoubleVariable> metric_inner = Variable.arrayVariable(location(57, 47, 57, 60), VariableType.DoubleVariable, streamLength);
                metric_inner.setAlias("metric_inner");
                metric_inner.setLocation(location(57, 22, 57, 33));

                metric_g.get(sample, location(58, 21, 58, 28)).put(server, metric_inner, location(58, 29, 58, 51));
                ArrayVariable<BooleanVariable> metric_valid_inner = Variable.arrayVariable(location(60, 55, 60, 68), VariableType.BooleanVariable, streamLength);
                metric_valid_inner.setAlias("metric_valid_inner");
                metric_valid_inner.setLocation(location(60, 23, 60, 40));

                metric_valid_g.get(sample, location(61, 27, 61, 34)).put(server, metric_valid_inner, location(61, 35, 61, 63));
                parFor(intVariable(0, location(64, 32, 64, 32)), streamLength, intVariable(1, location(64, 60, 64, 69)), true, location(64, 13, 64, 70), (timeStep) -> { 
                    timeStep.setAlias("timeStep");
                    timeStep.setLocation(location(64, 21, 64, 28));
                    IntVariable currentState = st.get(sample, location(65, 38, 65, 45)).get(timeStep, location(65, 46, 65, 55));
                    currentState.setAlias("currentState");
                    currentState.setLocation(location(65, 21, 65, 32));

                    metric_valid_inner.put(timeStep, bernoulli(current_metric_valid_bias.get(server, location(67, 83, 67, 90)).get(currentState, location(67, 91, 67, 104)), location(67, 48, 67, 105)).sample(location(67, 107, 67, 114)), location(67, 35, 67, 114));
                    BooleanVariable guard$1 = metric_valid_inner.get(timeStep, location(68, 38, 68, 47));
                    ifElse(guard$1, () -> {
                        metric_inner.put(timeStep, gaussian(current_metric_mean.get(server, location(69, 74, 69, 81)).get(currentState, location(69, 82, 69, 95)), current_metric_var.get(server, location(69, 116, 69, 123)).get(currentState, location(69, 124, 69, 137)), location(69, 46, 69, 138)).sample(location(69, 140, 69, 147)), location(69, 33, 69, 147));
                    }, () -> {
                    });

                });


            });


        });

        metric_valid_g.observe(metric_valid, location(75, 20, 75, 40));
        metric_g.observe(metric, location(76, 14, 76, 28));

        Variable<?>[] $variableNames = {metric, metric_valid, max_metric, noStates, noSamples, v, initialStateDistribution, m, st, noServers, metric_g, metric_valid_g, current_metric_mean, current_metric_var, current_metric_valid_bias};
        String[] $constructorArgs = {"metric", "metric_valid", "max_metric", "noStates"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMMetrics4", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics4(\n               double[][][] metric,\n               boolean[][][] metric_valid, \n               int max_metric,\n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    //Calculate all the state transitions\n    int[][] st = new int[noSamples][];\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n    }\n    \n    // Calculate the number of servers\n    int noServers = metric[0].length;    \n    \n    // Allocate space for each generated metric.    \n    double[][][] metric_g = new double[noSamples][noServers][];\n    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n\n    // Calculate metric parameters\n    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n    \n    // Compute the values of each metric\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        for(int server = 0; server < noServers; server++) {\n            //Allocate space for the time series\n            double[] metric_inner = new double[streamLength];\n            metric_g[sample][server] = metric_inner;\n            \n            boolean[] metric_valid_inner = new boolean[streamLength];\n            metric_valid_g[sample][server] = metric_valid_inner;\n            \n            //Generate values.\n            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n                int currentState = st[sample][timeStep];\n                \n                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n                if(metric_valid_inner[timeStep])\n                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n            }\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n    metric_g.observe(metric);\n}";
    }
}