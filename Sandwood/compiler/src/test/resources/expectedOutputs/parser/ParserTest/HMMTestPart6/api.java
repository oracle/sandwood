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

public class HMMTestPart6 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 20, 11, 42));

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

        st.put(intVariable(0, location(24, 12, 24, 12)), categorical(m.get(intVariable(0, location(24, 31, 24, 31)), location(24, 30, 24, 32)), location(24, 17, 24, 33)).sample(location(24, 35, 24, 42)), location(24, 11, 24, 42));
        parFor(intVariable(4, location(26, 20, 26, 20)), samples.add(intVariable(3, location(26, 33, 26, 33)), location(26, 31, 26, 31)), intVariable(1, location(26, 19, 26, 22)), true, location(26, 9, 26, 35), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(26, 17, 26, 17));
            st.put(i.subtract(intVariable(3, location(27, 18, 27, 18)), location(27, 17, 27, 17)), categorical(m.get((states.subtract(intVariable(1, location(27, 47, 27, 47)), location(27, 45, 27, 45))).subtract(st.get(i.subtract(intVariable(4, location(27, 57, 27, 57)), location(27, 56, 27, 56)), location(27, 54, 27, 58)), location(27, 50, 27, 50)), location(27, 36, 27, 59)), location(27, 23, 27, 60)).sample(location(27, 62, 27, 69)), location(27, 15, 27, 69));
        });

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(29, 38, 29, 46), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(29, 19, 29, 23));

        parFor(intVariable(5, location(31, 20, 31, 20)), samples.add(intVariable(5, location(31, 31, 31, 31)), location(31, 30, 31, 30)), intVariable(1, location(31, 19, 31, 22)), true, location(31, 9, 31, 33), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(31, 17, 31, 17));
            flips.put(j.subtract(intVariable(5, location(32, 21, 32, 21)), location(32, 20, 32, 20)), bernoulli(bias.get((states.subtract(intVariable(1, location(32, 51, 32, 51)), location(32, 49, 32, 49))).subtract(st.get(j.subtract(intVariable(5, location(32, 61, 32, 61)), location(32, 60, 32, 60)), location(32, 58, 32, 62)), location(32, 54, 32, 54)), location(32, 40, 32, 63)), location(32, 26, 32, 64)).sample(location(32, 66, 32, 73)), location(32, 18, 32, 73));
        });

        flips.observe(flipsMeasured, location(34, 15, 34, 36));

        Variable<?>[] $variableNames = {flipsMeasured, states, v, m, bias, samples, st, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTestPart6", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart6(boolean[] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sample();\n\n        for(int i:[4..samples + 3))\n            st[i-3] = categorical(m[(states - 1) - st[i-4]]).sample();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[5..samples+5))\n            flips[j-5] = bernoulli(bias[(states - 1) - st[j-5]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
    }
}