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

public class HMMTestFromStan extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 23, 11, 45));

        IntVariable states = intVariable(2, location(12, 22, 12, 22));
        states.setAlias("states");
        states.setLocation(location(12, 13, 12, 18));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(14, 32, 14, 39), VariableType.DoubleVariable, states);
        v.setAlias("v");
        v.setLocation(location(14, 18, 14, 18));

        parFor(intVariable(0, location(15, 20, 15, 20)), states, intVariable(1, location(15, 19, 15, 22)), true, location(15, 9, 15, 30), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(15, 17, 15, 17));
            v.put(i, doubleVariable(0.1, location(16, 20, 16, 22)), location(16, 14, 16, 22));
        });

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(18, 24, 18, 35)).sample(states, location(18, 37, 18, 50));
        m.setAlias("m");
        m.setLocation(location(18, 20, 18, 20));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(19, 30, 19, 32)), doubleVariable(1.0, location(19, 35, 19, 37)), location(19, 25, 19, 38)).sample(states, location(19, 40, 19, 53));
        bias.setAlias("bias");
        bias.setLocation(location(19, 18, 19, 21));

        IntVariable samples = flipsMeasured.length(location(21, 37, 21, 42));
        samples.setAlias("samples");
        samples.setLocation(location(21, 13, 21, 19));

        ArrayVariable<IntVariable> st = Variable.arrayVariable(location(22, 27, 22, 35), VariableType.IntVariable, samples);
        st.setAlias("st");
        st.setLocation(location(22, 15, 22, 16));

        st.put(intVariable(0, location(29, 12, 29, 12)), categorical(m.get(intVariable(0, location(29, 31, 29, 31)), location(29, 30, 29, 32)), location(29, 17, 29, 33)).sample(location(29, 35, 29, 42)), location(29, 11, 29, 42));
        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(35, 38, 35, 46), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(35, 19, 35, 23));

        parFor(intVariable(1, location(38, 20, 38, 20)), samples, intVariable(1, location(38, 19, 38, 22)), true, location(38, 9, 38, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(38, 17, 38, 17));
            st.put(i, categorical(m.get(st.get(i.subtract(intVariable(1, location(39, 42, 39, 42)), location(39, 40, 39, 40)), location(39, 37, 39, 43)), location(39, 34, 39, 44)), location(39, 21, 39, 45)).sample(location(39, 47, 39, 54)), location(39, 15, 39, 54));
        });

        parFor(intVariable(0, location(42, 20, 42, 20)), samples, intVariable(1, location(42, 19, 42, 22)), true, location(42, 9, 42, 31), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(42, 17, 42, 17));
            flips.put(j, bernoulli(bias.get(st.get(j, location(43, 41, 43, 43)), location(43, 38, 43, 44)), location(43, 24, 43, 45)).sample(location(43, 47, 43, 54)), location(43, 18, 43, 54));
        });

        flips.observe(flipsMeasured, location(45, 15, 45, 36));

        Variable<?>[] $variableNames = {flipsMeasured, states, v, m, bias, samples, st, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTestFromStan", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestFromStan(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        //Commented lines are the lines missing in the Stan example. \n        //This seems to make sense, as there is no transition from state 0 to state 0,\n        //Just a possible state being picked based on the observed data.\n        //\n        //Forward execution is more complex now as st[0] is used but never set.\n        //we could add:\n        st[0] = categorical(m[0]).sample();  \n        \n        //Original code commented out below.\n        \n        //st[0] = 0;\n                \n        boolean[] flips = new boolean[samples];\n\n        //st[0] = categorical(m[st[0]]).sample(); \n        for(int i:[1..samples))\n            st[i] = categorical(m[st[i - 1]]).sample();\n\n\n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
    }
}