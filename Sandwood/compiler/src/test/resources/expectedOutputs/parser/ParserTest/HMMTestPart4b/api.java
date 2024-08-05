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

public class HMMTestPart4b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<ArrayVariable<BooleanVariable>>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable))), location(11, 21, 11, 47));

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

        ArrayVariable<ArrayVariable<ArrayVariable<IntVariable>>> st = Variable.arrayVariable(location(23, 31, 23, 57), VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), samples, samples, samples);
        st.setAlias("st");
        st.setLocation(location(23, 19, 23, 20));

        st.get(intVariable(0, location(25, 12, 25, 12)), location(25, 11, 25, 13)).get(intVariable(0, location(25, 15, 25, 15)), location(25, 14, 25, 16)).put(intVariable(0, location(25, 18, 25, 18)), categorical(m.get(intVariable(0, location(25, 37, 25, 37)), location(25, 36, 25, 38)), location(25, 23, 25, 39)).sample(location(25, 41, 25, 48)), location(25, 17, 25, 48));
        parFor(intVariable(1, location(27, 20, 27, 20)), samples, intVariable(1, location(27, 19, 27, 22)), true, location(27, 9, 27, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(27, 17, 27, 17));
parFor(intVariable(0, location(28, 24, 28, 24)), samples, intVariable(1, location(28, 23, 28, 26)), true, location(28, 13, 28, 35), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(28, 21, 28, 21));
parFor(intVariable(0, location(29, 28, 29, 28)), samples, intVariable(1, location(29, 27, 29, 30)), true, location(29, 17, 29, 39), (k) -> { 
                    k.setAlias("k");
                    k.setLocation(location(29, 25, 29, 25));
                    st.get(i, location(30, 23, 30, 25)).get(j, location(30, 26, 30, 28)).put(k, categorical(m.get(intVariable(0, location(30, 49, 30, 49)), location(30, 48, 30, 50)), location(30, 35, 30, 51)).sample(location(30, 53, 30, 60)), location(30, 29, 30, 60));
                });

            });

        });

        ArrayVariable<ArrayVariable<ArrayVariable<BooleanVariable>>> flips = Variable.arrayVariable(location(32, 42, 32, 54), VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), samples);
        flips.setAlias("flips");
        flips.setLocation(location(32, 23, 32, 27));

        parFor(intVariable(0, location(33, 20, 33, 20)), samples, intVariable(1, location(33, 19, 33, 22)), true, location(33, 9, 33, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(33, 17, 33, 17));
            flips.put(i, Variable.arrayVariable(location(34, 35, 34, 45), VariableType.arrayType(VariableType.BooleanVariable), samples), location(34, 18, 34, 45));
            parFor(intVariable(0, location(35, 24, 35, 24)), samples, intVariable(1, location(35, 23, 35, 26)), true, location(35, 13, 35, 35), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(35, 21, 35, 21));
                flips.get(i, location(36, 22, 36, 24)).put(j, Variable.arrayVariable(location(36, 42, 36, 50), VariableType.BooleanVariable, samples), location(36, 25, 36, 50));
            });


        });

        parFor(intVariable(0, location(39, 20, 39, 20)), samples, intVariable(1, location(39, 19, 39, 22)), true, location(39, 9, 39, 31), (l) -> { 
            l.setAlias("l");
            l.setLocation(location(39, 17, 39, 17));
parFor(intVariable(0, location(40, 24, 40, 24)), samples, intVariable(1, location(40, 23, 40, 26)), true, location(40, 13, 40, 35), (p) -> { 
                p.setAlias("p");
                p.setLocation(location(40, 21, 40, 21));
parFor(intVariable(0, location(41, 28, 41, 28)), samples, intVariable(1, location(41, 27, 41, 30)), true, location(41, 17, 41, 39), (n) -> { 
                    n.setAlias("n");
                    n.setLocation(location(41, 25, 41, 25));
                    flips.get(l, location(42, 26, 42, 28)).get(n, location(42, 29, 42, 31)).put(p, bernoulli(bias.get(st.get(p, location(42, 55, 42, 57)).get(l, location(42, 58, 42, 60)).get(n, location(42, 61, 42, 63)), location(42, 52, 42, 64)), location(42, 38, 42, 65)).sample(location(42, 67, 42, 74)), location(42, 32, 42, 74));
                });

            });

        });

        flips.observe(flipsMeasured, location(44, 15, 44, 36));

        Variable<?>[] $variableNames = {flipsMeasured, states, v, m, bias, samples, st, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTestPart4b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart4b(boolean[][][] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        \n        int[][][] st = new int[samples][samples][samples];\n\n        st[0][0][0] = categorical(m[0]).sample();\n\n        for(int i:[1..samples))\n            for(int j:[0..samples))\n                for(int k:[0..samples))\n                    st[i][j][k] = categorical(m[0]).sample();\n            \n        boolean[][][] flips = new boolean[samples][][];\n        for(int i:[0..samples)) {\n            flips[i] = new boolean[samples][];\n            for(int j:[0..samples))\n                flips[i][j] = new boolean[samples];\n        }\n            \n        for(int l:[0..samples))\n            for(int p:[0..samples))\n                for(int n:[0..samples))\n                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
    }
}