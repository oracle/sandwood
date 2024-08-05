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

public class HMMTestPart3d extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 21, 11, 43));

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

        ArrayVariable<IntVariable> st2 = Variable.arrayVariable(location(23, 28, 23, 36), VariableType.IntVariable, samples);
        st2.setAlias("st2");
        st2.setLocation(location(23, 15, 23, 17));

        st.put(intVariable(0, location(25, 12, 25, 12)), categorical(m.get(intVariable(0, location(25, 31, 25, 31)), location(25, 30, 25, 32)), location(25, 17, 25, 33)).sample(location(25, 35, 25, 42)), location(25, 11, 25, 42));
        st2.put(intVariable(0, location(26, 13, 26, 13)), samples.subtract(st.get(intVariable(0, location(26, 31, 26, 31)), location(26, 30, 26, 32)), location(26, 26, 26, 26)), location(26, 12, 26, 26));
        parFor(intVariable(1, location(28, 20, 28, 20)), samples, intVariable(1, location(28, 19, 28, 22)), true, location(28, 9, 28, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(28, 17, 28, 17));
            st.put(i, categorical(m.get(samples.subtract(st2.get(i.subtract(intVariable(1, location(29, 51, 29, 51)), location(29, 50, 29, 50)), location(29, 48, 29, 52)), location(29, 43, 29, 43)), location(29, 34, 29, 53)), location(29, 21, 29, 54)).sample(location(29, 56, 29, 63)), location(29, 15, 29, 63));
            ArrayVariable<IntVariable> indirection = Variable.arrayVariable(location(31, 40, 31, 44), VariableType.IntVariable, i.add(intVariable(1, location(31, 43, 31, 43)), location(31, 42, 31, 42)));
            indirection.setAlias("indirection");
            indirection.setLocation(location(31, 19, 31, 29));

            parFor(intVariable(0, location(32, 24, 32, 24)), i.add(intVariable(1, location(32, 25, 32, 28)), location(32, 25, 32, 28)), intVariable(1, location(32, 23, 32, 26)), true, location(32, 13, 32, 29), (k) -> { 
                k.setAlias("k");
                k.setLocation(location(32, 21, 32, 21));
                indirection.put(k, k.times(i, location(33, 35, 33, 35)), location(33, 28, 33, 35));
            });

            st2.put(indirection.get(i, location(35, 28, 35, 30)).divide(i, location(35, 31, 35, 31)), samples.subtract(st.get(indirection.get(i, location(35, 61, 35, 63)).divide(i, location(35, 64, 35, 64)), location(35, 49, 35, 66)), location(35, 45, 35, 45)), location(35, 16, 35, 45));

        });

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(38, 38, 38, 46), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(38, 19, 38, 23));

        parFor(intVariable(0, location(40, 20, 40, 20)), samples, intVariable(1, location(40, 19, 40, 22)), true, location(40, 9, 40, 31), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(40, 17, 40, 17));
            flips.put(j, bernoulli(bias.get(samples.subtract(st2.get(j, location(41, 52, 41, 54)), location(41, 47, 41, 47)), location(41, 38, 41, 55)), location(41, 24, 41, 56)).sample(location(41, 58, 41, 65)), location(41, 18, 41, 65));
        });

        flips.observe(flipsMeasured, location(43, 15, 43, 36));

        Variable<?>[] $variableNames = {flipsMeasured, states, v, m, bias, samples, st, st2, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTestPart3d", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart3d(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n\n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        int[] st2 = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n        st2[0] = samples - st[0];\n\n        for(int i:[1..samples)) {\n            st[i] = categorical(m[samples - st2[i-1]]).sample();\n            \n            int[] indirection = new int[i+1];\n            for(int k:[0..i])\n                indirection[k] = k*i; \n                \n            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n        }\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
    }
}