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

public class Deterministic2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable n = observeInt("n", location(14, 29, 14, 33));
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(14, 36, 14, 58));

        IntVariable states = intVariable(5, location(15, 18, 15, 18));
        states.setAlias("states");
        states.setLocation(location(15, 9, 15, 14));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(17, 28, 17, 35), VariableType.DoubleVariable, states);
        v.setAlias("v");
        v.setLocation(location(17, 14, 17, 14));

        parFor(intVariable(0, location(18, 16, 18, 16)), states, intVariable(1, location(18, 15, 18, 18)), true, location(18, 5, 18, 26), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 13, 18, 13));
            v.put(i, doubleVariable(0.1, location(19, 16, 19, 18)), location(19, 10, 19, 18));
        });

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(21, 20, 21, 31)).sample(states, location(21, 33, 21, 46));
        m.setAlias("m");
        m.setLocation(location(21, 16, 21, 16));

        ArrayVariable<IntVariable> a = Variable.arrayVariable(location(23, 22, 23, 24), VariableType.IntVariable, n);
        a.setAlias("a");
        a.setLocation(location(23, 11, 23, 11));

        ArrayVariable<IntVariable> b = Variable.arrayVariable(location(24, 22, 24, 24), VariableType.IntVariable, n);
        b.setAlias("b");
        b.setLocation(location(24, 11, 24, 11));

        a.put(intVariable(0, location(25, 7, 25, 7)), intVariable(0, location(25, 12, 25, 12)), location(25, 6, 25, 12));
        parFor(intVariable(1, location(26, 15, 26, 15)), n, intVariable(1, location(26, 23, 26, 25)), true, location(26, 5, 26, 26), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(26, 13, 26, 13));
            b.put(i, a.get(i.subtract(intVariable(1, location(27, 20, 27, 20)), location(27, 19, 27, 19)), location(27, 17, 27, 21)), location(27, 10, 27, 21));
            a.put(i, categorical(m.get(b.get(i, location(28, 31, 28, 33)), location(28, 29, 28, 34)), location(28, 16, 28, 35)).sampleDistribution(location(28, 37, 28, 56)), location(28, 10, 28, 56));

        });

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(31, 34, 31, 36), VariableType.BooleanVariable, n);
        flips.setAlias("flips");
        flips.setLocation(location(31, 15, 31, 19));

        parFor(intVariable(0, location(33, 16, 33, 16)), n, intVariable(1, location(33, 15, 33, 18)), true, location(33, 5, 33, 21), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(33, 13, 33, 13));
            flips.put(j, bernoulli(intVariable(1, location(34, 30, 34, 30)).divide(a.get(j.add(intVariable(1, location(34, 36, 34, 36)), location(34, 35, 34, 35)), location(34, 33, 34, 37)), location(34, 31, 34, 31)), location(34, 20, 34, 38)).sample(location(34, 40, 34, 47)), location(34, 14, 34, 47));
        });

        flips.observe(flipsMeasured, location(35, 15, 35, 36));

        Variable<?>[] $variableNames = {n, flipsMeasured, states, v, m, a, b, flips};
        String[] $constructorArgs = {"n", "flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Deterministic2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), "/**\n * A model for the fairness work.\n */");
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic2(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sampleDistribution();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
    }
}