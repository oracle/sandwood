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

public class AlternativeModelMK3 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable observedSampleCount = observeInt("observedSampleCount", location(11, 34, 11, 56));
        IntVariable observedPositiveCount = observeInt("observedPositiveCount", location(11, 59, 11, 83));

        DoubleVariable bias = beta(doubleVariable(1.0, location(12, 28, 12, 30)), doubleVariable(1.0, location(12, 33, 12, 35)), location(12, 19, 12, 36)).sample(location(12, 38, 12, 45));
        bias.setAlias("bias");
        bias.setLocation(location(12, 12, 12, 15));

        Binomial binomial = binomial(bias, observedSampleCount, location(15, 25, 15, 63));
        binomial.setAlias("binomial");
        binomial.setLocation(location(15, 14, 15, 21));

        IntVariable positiveCount = binomial.sample(location(18, 34, 18, 41));
        positiveCount.setAlias("positiveCount");
        positiveCount.setLocation(location(18, 9, 18, 21));

        positiveCount.observe(observedPositiveCount, location(21, 19, 21, 48));

        Variable<?>[] $variableNames = {observedSampleCount, observedPositiveCount, bias, binomial, positiveCount};
        String[] $constructorArgs = {"observedSampleCount", "observedPositiveCount"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "AlternativeModelMK3", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model AlternativeModelMK3(int observedSampleCount, int observedPositiveCount)  {    \n    double bias = new Beta(1.0, 1.0).sample();\n        \n    //Construct a binomial\n    Binomial binomial = new Binomial(bias, observedSampleCount);\n                \n    //Sample from it\n    int positiveCount = binomial.sample();\n        \n    //Link the sampled values to the observed values\n    positiveCount.observe(observedPositiveCount);\n}";
    }
}