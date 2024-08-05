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

public class AlternativeModelMK2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable observedSampleCount = observeInt("observedSampleCount", "/** The number of observed samples */", location(14, 71, 14, 93));
        IntVariable observedPositiveCount = observeInt("observedPositiveCount", location(14, 96, 14, 120));

        DoubleVariable bias = beta(doubleVariable(1.0, location(16, 24, 16, 26)), doubleVariable(1.0, location(16, 29, 16, 31)), location(16, 19, 16, 32)).sample(location(16, 34, 16, 41));
        bias.setAlias("bias");
        bias.setLocation(location(16, 12, 16, 15));
        bias.setComment("/** a bias to see how like values are to be collected. */");

        Binomial binomial = binomial(bias, observedSampleCount, location(20, 25, 20, 59));
        binomial.setAlias("binomial");
        binomial.setLocation(location(20, 14, 20, 21));
        binomial.setComment("/** A binomial distribution for the tests. */");

        IntVariable positiveCount = binomial.sample(location(23, 34, 23, 41));
        positiveCount.setAlias("positiveCount");
        positiveCount.setLocation(location(23, 9, 23, 21));

        positiveCount.observe(observedPositiveCount, location(26, 19, 26, 48));

        Variable<?>[] $variableNames = {observedSampleCount, observedPositiveCount, bias, binomial, positiveCount};
        String[] $constructorArgs = {"observedSampleCount", "observedPositiveCount"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "AlternativeModelMK2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), "/**\n * A model for the fairness work.\n */");
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model AlternativeModelMK2(/** The number of observed samples */int observedSampleCount, int observedPositiveCount)  {\n    /** a bias to see how like values are to be collected. */    \n    double bias = beta(1.0, 1.0).sample();\n        \n    //Construct a binomial\n    /** A binomial distribution for the tests. */\n    Binomial binomial = binomial(bias, observedSampleCount);\n                \n    //Sample from it\n    int positiveCount = binomial.sample();\n        \n    //Link the sampled values to the observed values\n    positiveCount.observe(observedPositiveCount);\n}";
    }
}