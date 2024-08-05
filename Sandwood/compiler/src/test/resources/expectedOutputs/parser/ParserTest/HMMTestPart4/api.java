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

public class HMMTestPart4 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<ArrayVariable<BooleanVariable>>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable))), location(11, 20, 11, 46));

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

        ArrayVariable<ArrayVariable<ArrayVariable<IntVariable>>> st = Variable.arrayVariable(location(24, 31, 24, 43), VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), samples);
        st.setAlias("st");
        st.setLocation(location(24, 19, 24, 20));

        parFor(intVariable(0, location(25, 20, 25, 20)), samples, intVariable(1, location(25, 19, 25, 22)), true, location(25, 9, 25, 31), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(25, 17, 25, 17));
            st.put(i, Variable.arrayVariable(location(26, 28, 26, 38), VariableType.arrayType(VariableType.IntVariable), samples), location(26, 15, 26, 38));
            parFor(intVariable(0, location(27, 24, 27, 24)), samples, intVariable(1, location(27, 23, 27, 26)), true, location(27, 13, 27, 35), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(27, 21, 27, 21));
                st.get(i, location(28, 19, 28, 21)).put(j, Variable.arrayVariable(location(28, 35, 28, 43), VariableType.IntVariable, samples), location(28, 22, 28, 43));
            });


        });

        st.get(intVariable(0, location(31, 12, 31, 12)), location(31, 11, 31, 13)).get(intVariable(0, location(31, 15, 31, 15)), location(31, 14, 31, 16)).put(intVariable(0, location(31, 18, 31, 18)), categorical(m.get(intVariable(0, location(31, 37, 31, 37)), location(31, 36, 31, 38)), location(31, 23, 31, 39)).sample(location(31, 41, 31, 48)), location(31, 17, 31, 48));
        parFor(intVariable(1, location(33, 21, 33, 21)), samples, intVariable(1, location(33, 20, 33, 23)), true, location(33, 9, 33, 32), (i1) -> { 
            i1.setAlias("i1");
            i1.setLocation(location(33, 17, 33, 18));
parFor(intVariable(0, location(34, 25, 34, 25)), samples, intVariable(1, location(34, 24, 34, 27)), true, location(34, 13, 34, 36), (j1) -> { 
                j1.setAlias("j1");
                j1.setLocation(location(34, 21, 34, 22));
parFor(intVariable(0, location(35, 29, 35, 29)), samples, intVariable(1, location(35, 28, 35, 31)), true, location(35, 17, 35, 40), (k1) -> { 
                    k1.setAlias("k1");
                    k1.setLocation(location(35, 25, 35, 26));
                    st.get(i1, location(36, 23, 36, 26)).get(j1, location(36, 27, 36, 30)).put(k1, categorical(m.get(intVariable(0, location(36, 52, 36, 52)), location(36, 51, 36, 53)), location(36, 38, 36, 54)).sample(location(36, 56, 36, 63)), location(36, 31, 36, 63));
                });

            });

        });

        ArrayVariable<ArrayVariable<ArrayVariable<BooleanVariable>>> flips = Variable.arrayVariable(location(38, 42, 38, 54), VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), samples);
        flips.setAlias("flips");
        flips.setLocation(location(38, 23, 38, 27));

        parFor(intVariable(0, location(39, 21, 39, 21)), samples, intVariable(1, location(39, 20, 39, 23)), true, location(39, 9, 39, 32), (i2) -> { 
            i2.setAlias("i2");
            i2.setLocation(location(39, 17, 39, 18));
            flips.put(i2, Variable.arrayVariable(location(40, 36, 40, 46), VariableType.arrayType(VariableType.BooleanVariable), samples), location(40, 18, 40, 46));
            parFor(intVariable(0, location(41, 25, 41, 25)), samples, intVariable(1, location(41, 24, 41, 27)), true, location(41, 13, 41, 36), (j2) -> { 
                j2.setAlias("j2");
                j2.setLocation(location(41, 21, 41, 22));
                flips.get(i2, location(42, 22, 42, 25)).put(j2, Variable.arrayVariable(location(42, 44, 42, 52), VariableType.BooleanVariable, samples), location(42, 26, 42, 52));
            });


        });

        parFor(intVariable(0, location(45, 20, 45, 20)), samples, intVariable(1, location(45, 19, 45, 22)), true, location(45, 9, 45, 31), (l) -> { 
            l.setAlias("l");
            l.setLocation(location(45, 17, 45, 17));
parFor(intVariable(0, location(46, 24, 46, 24)), samples, intVariable(1, location(46, 23, 46, 26)), true, location(46, 13, 46, 35), (p) -> { 
                p.setAlias("p");
                p.setLocation(location(46, 21, 46, 21));
parFor(intVariable(0, location(47, 28, 47, 28)), samples, intVariable(1, location(47, 27, 47, 30)), true, location(47, 17, 47, 39), (n) -> { 
                    n.setAlias("n");
                    n.setLocation(location(47, 25, 47, 25));
                    flips.get(l, location(48, 26, 48, 28)).get(n, location(48, 29, 48, 31)).put(p, bernoulli(bias.get(st.get(p, location(48, 55, 48, 57)).get(l, location(48, 58, 48, 60)).get(n, location(48, 61, 48, 63)), location(48, 52, 48, 64)), location(48, 38, 48, 65)).sample(location(48, 67, 48, 74)), location(48, 32, 48, 74));
                });

            });

        });

        flips.observe(flipsMeasured, location(50, 15, 50, 36));

        Variable<?>[] $variableNames = {flipsMeasured, states, v, m, bias, samples, st, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTestPart4", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart4(boolean[][][] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        \n        \n        int[][][] st = new int[samples][][];\n        for(int i:[0..samples)) {\n            st[i] = new int[samples][];\n            for(int j:[0..samples))\n                st[i][j] = new int[samples];\n        }\n\n        st[0][0][0] = categorical(m[0]).sample();\n\n        for(int i1:[1..samples))\n            for(int j1:[0..samples))\n                for(int k1:[0..samples))\n                    st[i1][j1][k1] = categorical(m[0]).sample();\n            \n        boolean[][][] flips = new boolean[samples][][];\n        for(int i2:[0..samples)) {\n            flips[i2] = new boolean[samples][];\n            for(int j2:[0..samples))\n                flips[i2][j2] = new boolean[samples];\n        }\n            \n        for(int l:[0..samples))\n            for(int p:[0..samples))\n                for(int n:[0..samples))\n                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
    }
}