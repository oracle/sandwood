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

public class HMM_Mk2Dist extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<IntVariable>> eventsMeasured = observeArray("eventsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(11, 19, 11, 40));
        IntVariable noStates = observeInt("noStates", location(11, 43, 11, 54));
        IntVariable noEvents = observeInt("noEvents", location(11, 57, 11, 68));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(14, 32, 14, 41), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(14, 46, 14, 48)); }, noStates);
        v.setAlias("v");
        v.setLocation(location(14, 18, 14, 18));

        ArrayVariable<DoubleVariable> v2 = Variable.arrayVariable(location(15, 33, 15, 42), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(15, 47, 15, 49)); }, noEvents);
        v2.setAlias("v2");
        v2.setLocation(location(15, 18, 15, 19));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(16, 24, 16, 35)).sample(noStates, location(16, 37, 16, 52));
        m.setAlias("m");
        m.setLocation(location(16, 20, 16, 20));

        ArrayVariable<ArrayVariable<DoubleVariable>> bias = dirichlet(v2, location(19, 27, 19, 39)).sample(noStates, location(19, 41, 19, 56));
        bias.setAlias("bias");
        bias.setLocation(location(19, 20, 19, 23));

        IntVariable samples = eventsMeasured.length(location(22, 38, 22, 43));
        samples.setAlias("samples");
        samples.setLocation(location(22, 13, 22, 19));

        ArrayVariable<ArrayVariable<IntVariable>> st = Variable.arrayVariable(location(25, 29, 25, 39), VariableType.arrayType(VariableType.IntVariable), samples);
        st.setAlias("st");
        st.setLocation(location(25, 17, 25, 18));

        parFor(intVariable(0, location(26, 20, 26, 20)), samples, intVariable(1, location(26, 19, 26, 23)), true, location(26, 9, 26, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(26, 17, 26, 17));
            IntVariable streamLength = eventsMeasured.get(i, location(27, 46, 27, 48)).length(location(27, 50, 27, 55));
            streamLength.setAlias("streamLength");
            streamLength.setLocation(location(27, 17, 27, 28));

            st.put(i, Variable.arrayVariable(location(28, 28, 28, 41), VariableType.IntVariable, streamLength), location(28, 15, 28, 41));

        });

        ArrayVariable<DoubleVariable> weights = dirichlet(v, location(32, 28, 32, 39)).sample(location(32, 41, 32, 48));
        weights.setAlias("weights");
        weights.setLocation(location(32, 18, 32, 24));

        IntVariable initialState = categorical(weights, location(33, 28, 33, 47)).sample(location(33, 49, 33, 56));
        initialState.setAlias("initialState");
        initialState.setLocation(location(33, 13, 33, 24));

        parFor(intVariable(0, location(34, 20, 34, 20)), samples, intVariable(1, location(34, 19, 34, 22)), true, location(34, 9, 34, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(34, 17, 34, 17));
            st.get(i, location(35, 15, 35, 17)).put(intVariable(0, location(35, 19, 35, 19)), categorical(m.get(initialState, location(35, 37, 35, 50)), location(35, 24, 35, 51)).sampleDistribution(location(35, 53, 35, 72)), location(35, 18, 35, 72));

        });

        parFor(intVariable(0, location(39, 20, 39, 20)), samples, intVariable(1, location(39, 19, 39, 23)), true, location(39, 9, 39, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(39, 17, 39, 17));
            IntVariable streamLength = eventsMeasured.get(i, location(40, 46, 40, 48)).length(location(40, 50, 40, 55));
            streamLength.setAlias("streamLength");
            streamLength.setLocation(location(40, 17, 40, 28));

            parFor(intVariable(1, location(41, 24, 41, 24)), streamLength, intVariable(1, location(41, 23, 41, 26)), true, location(41, 13, 41, 40), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(41, 21, 41, 21));
                st.get(i, location(42, 19, 42, 21)).put(j, categorical(m.get(st.get(i, location(42, 44, 42, 46)).get(j.subtract(intVariable(1, location(42, 50, 42, 50)), location(42, 49, 42, 49)), location(42, 47, 42, 51)), location(42, 41, 42, 52)), location(42, 28, 42, 53)).sampleDistribution(location(42, 55, 42, 74)), location(42, 22, 42, 74));

            });


        });

        ArrayVariable<ArrayVariable<IntVariable>> events = Variable.arrayVariable(location(47, 33, 47, 43), VariableType.arrayType(VariableType.IntVariable), samples);
        events.setAlias("events");
        events.setLocation(location(47, 17, 47, 22));

        parFor(intVariable(0, location(48, 20, 48, 20)), samples, intVariable(1, location(48, 19, 48, 23)), true, location(48, 9, 48, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(48, 17, 48, 17));
            IntVariable streamLength = eventsMeasured.get(i, location(49, 46, 49, 48)).length(location(49, 50, 49, 55));
            streamLength.setAlias("streamLength");
            streamLength.setLocation(location(49, 17, 49, 28));

            events.put(i, Variable.arrayVariable(location(50, 32, 50, 45), VariableType.IntVariable, streamLength), location(50, 19, 50, 45));
            parFor(intVariable(1, location(51, 24, 51, 24)), streamLength, intVariable(1, location(51, 23, 51, 26)), true, location(51, 13, 51, 40), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(51, 21, 51, 21));
                events.get(i, location(52, 23, 52, 25)).put(j, categorical(bias.get(st.get(i, location(52, 51, 52, 53)).get(j, location(52, 54, 52, 56)), location(52, 48, 52, 57)), location(52, 32, 52, 58)).sample(location(52, 60, 52, 67)).add(intVariable(1, location(52, 71, 52, 71)), location(52, 69, 52, 69)), location(52, 26, 52, 69));

            });


        });

        events.observe(eventsMeasured, location(57, 16, 57, 38));

        Variable<?>[] $variableNames = {eventsMeasured, noStates, noEvents, v, v2, m, bias, samples, st, weights, initialState, events};
        String[] $constructorArgs = {"eventsMeasured", "noStates", "noEvents"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMM_Mk2Dist", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sampleDistribution();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
    }
}