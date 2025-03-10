package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
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

public class AlternativeModelMK4Fail extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable observedSampleCount = observeInt("observedSampleCount", location(11, 38, 11, 60));
        IntVariable observedPositiveCount = observeInt("observedPositiveCount", location(11, 63, 11, 87));

        DoubleVariable bias1 = beta(doubleVariable(1.0, location(12, 29, 12, 31)), doubleVariable(1.0, location(12, 34, 12, 36)), location(12, 20, 12, 37)).sample(location(12, 39, 12, 46));
        bias1.setAlias("bias1");
        bias1.setLocation(location(12, 12, 12, 16));

        DoubleVariable bias2 = beta(doubleVariable(1.0, location(13, 29, 13, 31)), doubleVariable(1.0, location(13, 34, 13, 36)), location(13, 20, 13, 37)).sample(location(13, 39, 13, 46));
        bias2.setAlias("bias2");
        bias2.setLocation(location(13, 12, 13, 16));

        Binomial binomial1 = binomial(bias1, observedSampleCount, location(16, 26, 16, 65));
        binomial1.setAlias("binomial1");
        binomial1.setLocation(location(16, 14, 16, 22));

        Binomial binomial2 = binomial(bias2, observedSampleCount, location(17, 26, 17, 65));
        binomial2.setAlias("binomial2");
        binomial2.setLocation(location(17, 14, 17, 22));

        IntVariable positiveCount = binomial1.sample(location(20, 35, 20, 42)).add(binomial2.sample(location(20, 56, 20, 63)), location(20, 44, 20, 44));
        positiveCount.setAlias("positiveCount");
        positiveCount.setLocation(location(20, 9, 20, 21));

        positiveCount.observe(observedPositiveCount, location(23, 19, 23, 48));

        Variable<?>[] $variableNames = {observedSampleCount, observedPositiveCount, bias1, bias2, binomial1, binomial2, positiveCount};
        String[] $constructorArgs = {"observedSampleCount", "observedPositiveCount"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "AlternativeModelMK4Fail", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
             + " * \n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model AlternativeModelMK4Fail(int observedSampleCount, int observedPositiveCount)  {    \n"
             + "    double bias1 = new Beta(1.0, 1.0).sample();    \n"
             + "    double bias2 = new Beta(1.0, 1.0).sample();\n"
             + "        \n"
             + "    //Construct a binomial\n"
             + "    Binomial binomial1 = new Binomial(bias1, observedSampleCount);\n"
             + "    Binomial binomial2 = new Binomial(bias2, observedSampleCount);\n"
             + "                \n"
             + "    //Sample from it\n"
             + "    int positiveCount = binomial1.sample() + binomial2.sample();\n"
             + "        \n"
             + "    //Link the sampled values to the observed values\n"
             + "    positiveCount.observe(observedPositiveCount);\n"
             + "}";
    }
}