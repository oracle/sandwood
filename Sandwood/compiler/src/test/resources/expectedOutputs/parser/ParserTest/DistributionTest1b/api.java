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

public class DistributionTest1b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> weightings = observeArray("weightings", VariableType.arrayType(VariableType.DoubleVariable), location(11, 26, 11, 44));
        BooleanVariable value = observeBoolean("value", location(11, 47, 11, 59));

        IntVariable v1 = categorical(weightings, location(12, 14, 12, 36)).sampleDistribution(location(12, 38, 12, 57));
        v1.setAlias("v1");
        v1.setLocation(location(12, 9, 12, 10));

        Categorical c = categorical(weightings, location(13, 21, 13, 47));
        c.setAlias("c");
        c.setLocation(location(13, 17, 13, 17));

        IntVariable v2 = c.sampleDistribution(location(14, 16, 14, 35));
        v2.setAlias("v2");
        v2.setLocation(location(14, 9, 14, 10));

        IntVariable v3 = c.sample(location(15, 16, 15, 23));
        v3.setAlias("v3");
        v3.setLocation(location(15, 9, 15, 10));

        BooleanVariable v = bernoulli((doubleVariable(1.0, location(16, 28, 16, 30)).times(v1, location(16, 31, 16, 31))).divide((v2.add(v3, location(16, 40, 16, 40))), location(16, 35, 16, 35)), location(16, 17, 16, 45)).sample(location(16, 47, 16, 54));
        v.setAlias("v");
        v.setLocation(location(16, 13, 16, 13));

        v.observe(value, location(17, 7, 17, 20));

        Variable<?>[] $variableNames = {weightings, value, v1, c, v2, v3, v};
        String[] $constructorArgs = {"weightings", "value"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DistributionTest1b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest1b(double[] weightings, boolean value) {\n    int v1 = categorical(weightings).sampleDistribution();\n    Categorical c = new Categorical(weightings);\n    int v2 = c.sampleDistribution();\n    int v3 = c.sample();\n    boolean v = bernoulli((1.0*v1)/(v2 + v3)).sample();\n    v.observe(value);\n}\n";
    }
}