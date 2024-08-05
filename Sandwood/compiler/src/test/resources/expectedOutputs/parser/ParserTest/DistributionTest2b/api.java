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

public class DistributionTest2b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> weightings = observeArray("weightings", VariableType.arrayType(VariableType.DoubleVariable), location(11, 26, 11, 44));
        ArrayVariable<BooleanVariable> value = observeArray("value", VariableType.arrayType(VariableType.BooleanVariable), location(11, 47, 11, 61));

        IntVariable size = value.length(location(12, 22, 12, 27));
        size.setAlias("size");
        size.setLocation(location(12, 9, 12, 12));

        Categorical c = categorical(weightings, location(14, 21, 14, 47));
        c.setAlias("c");
        c.setLocation(location(14, 17, 14, 17));

        IntVariable v1 = c.sampleDistribution(location(15, 16, 15, 35));
        v1.setAlias("v1");
        v1.setLocation(location(15, 9, 15, 10));

        ArrayVariable<IntVariable> v2 = Variable.arrayVariable(location(17, 23, 17, 28), VariableType.IntVariable, size);
        v2.setAlias("v2");
        v2.setLocation(location(17, 11, 17, 12));

        v2.put(intVariable(0, location(18, 8, 18, 8)), categorical(weightings, location(18, 13, 18, 35)).sampleDistribution(location(18, 37, 18, 56)), location(18, 7, 18, 56));
        parFor(intVariable(1, location(19, 16, 19, 16)), size, intVariable(1, location(19, 15, 19, 18)), true, location(19, 5, 19, 24), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(19, 13, 19, 13));
            v2.put(i, categorical(weightings, location(20, 17, 20, 39)).sampleDistribution(location(20, 41, 20, 60)), location(20, 11, 20, 60));
        });

        ArrayVariable<BooleanVariable> v = Variable.arrayVariable(location(22, 30, 22, 35), VariableType.BooleanVariable, size);
        v.setAlias("v");
        v.setLocation(location(22, 15, 22, 15));

        parFor(intVariable(0, location(23, 16, 23, 16)), size, intVariable(1, location(23, 15, 23, 18)), true, location(23, 5, 23, 24), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(23, 13, 23, 13));
            IntVariable v3 = c.sample(location(24, 20, 24, 27));
            v3.setAlias("v3");
            v3.setLocation(location(24, 13, 24, 14));

            v.put(j, bernoulli((doubleVariable(1.0, location(25, 27, 25, 29)).times(v1, location(25, 30, 25, 30))).divide((v2.get(j, location(25, 38, 25, 40)).add(v3, location(25, 42, 25, 42))), location(25, 34, 25, 34)), location(25, 16, 25, 47)).sample(location(25, 49, 25, 56)), location(25, 10, 25, 56));

        });

        v.observe(value, location(28, 7, 28, 20));

        Variable<?>[] $variableNames = {weightings, value, size, c, v1, v2, v};
        String[] $constructorArgs = {"weightings", "value"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DistributionTest2b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest2b(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    Categorical c = new Categorical(weightings);\n    int v1 = c.sampleDistribution();\n    \n    int[] v2 = new int[size];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[1..size))\n        v2[i] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size)) {\n        int v3 = c.sample();\n        v[j] = bernoulli((1.0*v1)/(v2[j] + v3)).sample();\n    }\n        \n    v.observe(value);\n}\n";
    }
}