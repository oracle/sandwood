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

public class DistributionTest2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> weightings = observeArray("weightings", VariableType.arrayType(VariableType.DoubleVariable), location(11, 25, 11, 43));
        ArrayVariable<BooleanVariable> value = observeArray("value", VariableType.arrayType(VariableType.BooleanVariable), location(11, 46, 11, 60));

        IntVariable size = value.length(location(12, 22, 12, 27));
        size.setAlias("size");
        size.setLocation(location(12, 9, 12, 12));

        IntVariable v1 = categorical(weightings, location(14, 14, 14, 36)).sampleDistribution(location(14, 38, 14, 57));
        v1.setAlias("v1");
        v1.setLocation(location(14, 9, 14, 10));

        ArrayVariable<IntVariable> v2 = Variable.arrayVariable(location(16, 23, 16, 28), VariableType.IntVariable, size);
        v2.setAlias("v2");
        v2.setLocation(location(16, 11, 16, 12));

        v2.put(intVariable(0, location(17, 8, 17, 8)), categorical(weightings, location(17, 13, 17, 35)).sampleDistribution(location(17, 37, 17, 56)), location(17, 7, 17, 56));
        parFor(intVariable(1, location(18, 16, 18, 16)), size, intVariable(1, location(18, 15, 18, 18)), true, location(18, 5, 18, 24), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 13, 18, 13));
            v2.put(i, categorical(weightings, location(19, 17, 19, 39)).sampleDistribution(location(19, 41, 19, 60)), location(19, 11, 19, 60));
        });

        ArrayVariable<BooleanVariable> v = Variable.arrayVariable(location(21, 30, 21, 35), VariableType.BooleanVariable, size);
        v.setAlias("v");
        v.setLocation(location(21, 15, 21, 15));

        parFor(intVariable(0, location(22, 16, 22, 16)), size, intVariable(1, location(22, 15, 22, 18)), true, location(22, 5, 22, 24), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(22, 13, 22, 13));
            v.put(j, bernoulli((doubleVariable(1.0, location(23, 27, 23, 29)).times(v1, location(23, 30, 23, 30))).divide(v2.get(j, location(23, 37, 23, 39)), location(23, 34, 23, 34)), location(23, 16, 23, 40)).sample(location(23, 42, 23, 49)), location(23, 10, 23, 49));
        });

        v.observe(value, location(25, 7, 25, 20));

        Variable<?>[] $variableNames = {weightings, value, size, v1, v2, v};
        String[] $constructorArgs = {"weightings", "value"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DistributionTest2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest2(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[1..size))\n        v2[i] = categorical(weightings).sampleDistribution();\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli((1.0*v1)/v2[j]).sample();\n        \n    v.observe(value);\n}\n";
    }
}