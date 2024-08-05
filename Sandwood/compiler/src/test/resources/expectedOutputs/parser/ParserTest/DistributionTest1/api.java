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

public class DistributionTest1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> weightings = observeArray("weightings", VariableType.arrayType(VariableType.DoubleVariable), location(11, 25, 11, 43));
        BooleanVariable value = observeBoolean("value", location(11, 46, 11, 58));

        IntVariable v1 = categorical(weightings, location(12, 14, 12, 36)).sampleDistribution(location(12, 38, 12, 57));
        v1.setAlias("v1");
        v1.setLocation(location(12, 9, 12, 10));

        IntVariable v2 = categorical(weightings, location(13, 14, 13, 36)).sampleDistribution(location(13, 38, 13, 57));
        v2.setAlias("v2");
        v2.setLocation(location(13, 9, 13, 10));

        BooleanVariable v = bernoulli((doubleVariable(1.0, location(14, 28, 14, 30)).times(v1, location(14, 31, 14, 31))).divide(v2, location(14, 35, 14, 35)), location(14, 17, 14, 38)).sample(location(14, 40, 14, 47));
        v.setAlias("v");
        v.setLocation(location(14, 13, 14, 13));

        v.observe(value, location(15, 7, 15, 20));

        Variable<?>[] $variableNames = {weightings, value, v1, v2, v};
        String[] $constructorArgs = {"weightings", "value"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DistributionTest1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel DistributionTest1(double[] weightings, boolean value) {\n    int v1 = categorical(weightings).sampleDistribution();\n    int v2 = categorical(weightings).sampleDistribution();\n    boolean v = bernoulli((1.0*v1)/v2).sample();\n    v.observe(value);\n}\n";
    }
}