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

public class NullModelMK2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable eta = observeDouble("eta", location(11, 27, 11, 36));
        IntVariable observedSampleCount = observeInt("observedSampleCount", location(11, 39, 11, 61));
        IntVariable observedPositiveCount = observeInt("observedPositiveCount", location(11, 64, 11, 88));

        DoubleVariable min = eta.times(doubleVariable(4.0, location(12, 28, 12, 30)), location(12, 26, 12, 26)).divide(doubleVariable(5.0, location(12, 32, 12, 34)), location(12, 31, 12, 31));
        min.setAlias("min");
        min.setLocation(location(12, 16, 12, 18));

        DoubleVariable bias = uniform(min, doubleVariable(1.0, location(13, 36, 13, 38)), location(13, 23, 13, 39)).sample(location(13, 41, 13, 48));
        bias.setAlias("bias");
        bias.setLocation(location(13, 16, 13, 19));

        Binomial binomial = binomial(bias, observedSampleCount, location(16, 29, 16, 63));
        binomial.setAlias("binomial");
        binomial.setLocation(location(16, 18, 16, 25));

        IntVariable positiveCount = binomial.sample(location(19, 38, 19, 45));
        positiveCount.setAlias("positiveCount");
        positiveCount.setLocation(location(19, 13, 19, 25));

        positiveCount.observe(observedPositiveCount, location(22, 23, 22, 52));

        Variable<?>[] $variableNames = {eta, observedSampleCount, observedPositiveCount, min, bias, binomial, positiveCount};
        String[] $constructorArgs = {"eta", "observedSampleCount", "observedPositiveCount"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "NullModelMK2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model NullModelMK2(double eta, int observedSampleCount, int observedPositiveCount) {\n        double min = eta * 4.0/5.0;    \n        double bias = uniform(min, 1.0).sample();\n        \n        //Construct a binomial\n        Binomial binomial = binomial(bias, observedSampleCount);\n                \n        //Sample from it\n        int positiveCount = binomial.sample();\n        \n        //Link the sampled values to the observed values\n        positiveCount.observe(observedPositiveCount);\n}";
    }
}