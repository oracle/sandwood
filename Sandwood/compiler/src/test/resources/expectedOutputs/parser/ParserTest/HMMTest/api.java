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

public class HMMTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 15, 11, 37));

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

        st.put(intVariable(0, location(23, 12, 23, 12)), intVariable(0, location(23, 17, 23, 17)), location(23, 11, 23, 17));
        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(25, 38, 25, 46), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(25, 19, 25, 23));

        parFor(intVariable(1, location(27, 20, 27, 20)), samples, intVariable(1, location(27, 19, 27, 22)), true, location(27, 9, 27, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(27, 17, 27, 17));
            st.put(i, categorical(m.get(st.get(i.subtract(intVariable(1, location(28, 42, 28, 42)), location(28, 40, 28, 40)), location(28, 37, 28, 43)), location(28, 34, 28, 44)), location(28, 21, 28, 45)).sample(location(28, 47, 28, 54)), location(28, 15, 28, 54));
        });

        parFor(intVariable(0, location(31, 20, 31, 20)), samples, intVariable(1, location(31, 19, 31, 22)), true, location(31, 9, 31, 31), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(31, 17, 31, 17));
            flips.put(j, bernoulli(bias.get(st.get(j, location(32, 41, 32, 43)), location(32, 38, 32, 44)), location(32, 24, 32, 45)).sample(location(32, 47, 32, 54)), location(32, 18, 32, 54));
        });

        flips.observe(flipsMeasured, location(34, 15, 34, 36));

        Variable<?>[] $variableNames = {flipsMeasured, states, v, m, bias, samples, st, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTest(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n        st[0] = 0;\n                \n        boolean[] flips = new boolean[samples];\n\n        for(int i:[1..samples))\n            st[i] = categorical(m[st[i - 1]]).sample();\n\n\n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
    }
}