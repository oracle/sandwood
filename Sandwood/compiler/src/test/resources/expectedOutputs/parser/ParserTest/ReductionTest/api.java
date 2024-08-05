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

public class ReductionTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 21, 11, 43));
        IntVariable noCats = observeInt("noCats", location(11, 46, 11, 55));

        IntVariable noFlips = flipsMeasured.length(location(12, 33, 12, 38));
        noFlips.setAlias("noFlips");
        noFlips.setLocation(location(12, 9, 12, 15));

        IntVariable noStates = noFlips.divide(noCats, location(13, 27, 13, 27));
        noStates.setAlias("noStates");
        noStates.setLocation(location(13, 9, 13, 16));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(15, 28, 15, 37), VariableType.DoubleVariable, noStates);
        v.setAlias("v");
        v.setLocation(location(15, 14, 15, 14));

        parFor(intVariable(0, location(16, 16, 16, 16)), noStates, intVariable(1, location(16, 15, 16, 18)), true, location(16, 5, 16, 28), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(16, 13, 16, 13));
            v.put(i, doubleVariable(0.1, location(17, 16, 17, 18)), location(17, 10, 17, 18));
        });

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(19, 20, 19, 31)).sample(noCats, location(19, 33, 19, 46));
        m.setAlias("m");
        m.setLocation(location(19, 16, 19, 16));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(21, 26, 21, 28)), doubleVariable(1.0, location(21, 31, 21, 33)), location(21, 21, 21, 34)).sample(noFlips, location(21, 36, 21, 50));
        bias.setAlias("bias");
        bias.setLocation(location(21, 14, 21, 17));

        ArrayVariable<IntVariable> st = Variable.arrayVariable(location(23, 23, 23, 30), VariableType.IntVariable, noCats);
        st.setAlias("st");
        st.setLocation(location(23, 11, 23, 12));

        parFor(intVariable(0, location(26, 16, 26, 16)), noCats, intVariable(1, location(26, 15, 26, 18)), true, location(26, 5, 26, 26), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(26, 13, 26, 13));
            st.put(i, categorical(m.get(i, location(27, 30, 27, 32)), location(27, 17, 27, 33)).sample(location(27, 35, 27, 42)), location(27, 11, 27, 42));
        });

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(29, 34, 29, 42), VariableType.BooleanVariable, noFlips);
        flips.setAlias("flips");
        flips.setLocation(location(29, 15, 29, 19));

        parFor(intVariable(0, location(31, 16, 31, 16)), noFlips, intVariable(1, location(31, 15, 31, 18)), true, location(31, 5, 31, 27), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(31, 13, 31, 13));
            flips.put(j, bernoulli(bias.get(sum(st, location(32, 35, 32, 41)), location(32, 34, 32, 42)), location(32, 20, 32, 43)).sample(location(32, 45, 32, 52)), location(32, 14, 32, 52));
        });

        flips.observe(flipsMeasured, location(34, 11, 34, 32));

        Variable<?>[] $variableNames = {flipsMeasured, noCats, noFlips, noStates, v, m, bias, st, flips};
        String[] $constructorArgs = {"flipsMeasured", "noCats"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ReductionTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static IntVariable sum(ArrayVariable<IntVariable> a, Location $location) { 
        return reduce(a, intVariable(0, location(37, 26, 37, 26)), location(37, 16, 39, 10), (i, j) ->  { 
            i.setAlias("i");
            i.setLocation(location(37, 30, 37, 30));
            j.setAlias("j");
            j.setLocation(location(37, 32, 37, 32));
            return i.add(j, location(38, 22, 38, 22));
        });
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel ReductionTest(boolean[] flipsMeasured, int noCats) {\n    int noFlips = flipsMeasured.length;\n    int noStates = noFlips/noCats;\n    \n    double[] v = new double[noStates];\n    for(int i:[0..noStates))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(noCats);\n    \n    double[] bias = beta(1.0, 1.0).sample(noFlips);\n    \n    int[] st = new int[noCats];\n\n\n    for(int i:[0..noCats))\n        st[i] = categorical(m[i]).sample();\n            \n    boolean[] flips = new boolean[noFlips];\n            \n    for(int j:[0..noFlips))\n        flips[j] = bernoulli(bias[sum(st)]).sample();\n\n    flips.observe(flipsMeasured);\n    \n    private int sum(int[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}";
    }
}