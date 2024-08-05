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

public class DistributionTest5 extends GeneratedAPIBuilder {
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

        ArrayVariable<IntVariable> v2 = Variable.arrayVariable(location(16, 23, 16, 32), VariableType.IntVariable, size.add(intVariable(1, location(16, 31, 16, 31)), location(16, 29, 16, 29)));
        v2.setAlias("v2");
        v2.setLocation(location(16, 11, 16, 12));

        v2.put(intVariable(0, location(17, 8, 17, 8)), categorical(weightings, location(17, 13, 17, 35)).sampleDistribution(location(17, 37, 17, 56)), location(17, 7, 17, 56));
        parFor(intVariable(0, location(18, 16, 18, 16)), size, intVariable(1, location(18, 15, 18, 18)), true, location(18, 5, 18, 24), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 13, 18, 13));
            v2.put(i.add(intVariable(1, location(19, 16, 19, 16)), location(19, 14, 19, 14)), categorical(weightings, location(19, 21, 19, 43)).sampleDistribution(location(19, 45, 19, 64)), location(19, 11, 19, 64));
        });

        ArrayVariable<IntVariable> v3 = Variable.arrayVariable(location(22, 23, 22, 32), VariableType.IntVariable, size.add(intVariable(1, location(22, 31, 22, 31)), location(22, 29, 22, 29)));
        v3.setAlias("v3");
        v3.setLocation(location(22, 11, 22, 12));

        parFor(intVariable(0, location(23, 16, 23, 16)), size.add(intVariable(1, location(23, 17, 23, 23)), location(23, 17, 23, 23)), intVariable(1, location(23, 15, 23, 18)), true, location(23, 5, 23, 24), (k) -> { 
            k.setAlias("k");
            k.setLocation(location(23, 13, 23, 13));
            v3.put(k, v2.get(k, location(24, 19, 24, 21)), location(24, 11, 24, 21));
        });

        ArrayVariable<BooleanVariable> v = Variable.arrayVariable(location(26, 30, 26, 35), VariableType.BooleanVariable, size);
        v.setAlias("v");
        v.setLocation(location(26, 15, 26, 15));

        parFor(intVariable(0, location(27, 16, 27, 16)), size, intVariable(1, location(27, 15, 27, 18)), true, location(27, 5, 27, 24), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(27, 13, 27, 13));
            v.put(j, bernoulli(((doubleVariable(1.0, location(28, 28, 28, 30)).times(v1, location(28, 31, 28, 31))).add(v2.get(j, location(28, 40, 28, 42)), location(28, 36, 28, 36)).add(v3.get(j.add(intVariable(1, location(28, 51, 28, 51)), location(28, 50, 28, 50)), location(28, 48, 28, 52)), location(28, 44, 28, 44))).divide(v2.get(j.add(intVariable(1, location(28, 60, 28, 60)), location(28, 59, 28, 59)), location(28, 57, 28, 61)), location(28, 54, 28, 54)), location(28, 16, 28, 62)).sample(location(28, 64, 28, 71)), location(28, 10, 28, 71));
        });

        v.observe(value, location(30, 7, 30, 20));

        Variable<?>[] $variableNames = {weightings, value, size, v1, v2, v3, v};
        String[] $constructorArgs = {"weightings", "value"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DistributionTest5", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest5(double[] weightings, boolean[] value) {\n    int size = value.length;\n    \n    int v1 = categorical(weightings).sampleDistribution();\n    \n    int[] v2 = new int[size + 1];\n    v2[0] = categorical(weightings).sampleDistribution();\n    for(int i:[0..size))\n        v2[i + 1] = categorical(weightings).sampleDistribution();\n        \n        \n    int[] v3 = new int[size + 1];\n    for(int k:[0..size]) \n        v3[k] = v2[k];\n        \n    boolean[] v = new boolean[size];\n    for(int j:[0..size))\n        v[j] = bernoulli(((1.0*v1) + v2[j] + v3[j+1])/v2[j+1]).sample();\n        \n    v.observe(value);\n}\n";
    }
}