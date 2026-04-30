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

public class InjectionAttackTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable observedSampleCount = observeInt("observedSampleCount", "/** The number of observed samples \" + new java.util.Random().nextDouble() + \"*/", location(16, 114, 16, 136));
        IntVariable observedPositiveCount = observeInt("observedPositiveCount", location(16, 139, 16, 163));

        DoubleVariable bias = beta(doubleVariable(1.0, location(19, 24, 19, 26)), doubleVariable(1.0, location(19, 29, 19, 31)), location(19, 19, 19, 32)).sample(location(19, 34, 19, 41));
        bias.setAlias("bias");
        bias.setLocation(location(19, 12, 19, 15));
        bias.setComment("/** a bias to see how like values are to be collected. \" + new java.util.Random().nextDouble() + \"*/");

        Binomial binomial = binomial(bias, observedSampleCount, location(23, 25, 23, 59));
        binomial.setAlias("binomial");
        binomial.setLocation(location(23, 14, 23, 21));
        binomial.setComment("/** A binomial distribution for the tests. \" + new java.util.Random().nextDouble() + \" */");

        IntVariable positiveCount = binomial.sample(location(26, 34, 26, 41));
        positiveCount.setAlias("positiveCount");
        positiveCount.setLocation(location(26, 9, 26, 21));

        positiveCount.observe(observedPositiveCount, location(29, 19, 29, 48));

        Variable<?>[] $variableNames = {observedSampleCount, observedPositiveCount, bias, binomial, positiveCount};
        String[] $constructorArgs = {"observedSampleCount", "observedPositiveCount"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "InjectionAttackTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), "/**\n * A model for the fairness work. \" + new java.util.Random().nextDouble() + \"\n */");
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
             + " * \n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + " \n"
             + " /** \" + new java.util.Random().nextDouble() + \" */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "/**\n"
             + " * A model for the fairness work. \" + new java.util.Random().nextDouble() + \"\n"
             + " */\n"
             + "public model InjectionAttackTest(/** The number of observed samples \" + new java.util.Random().nextDouble() + \"*/int observedSampleCount, int observedPositiveCount)  {\n"
             + "    // \" + new java.util.Random().nextDouble() + \"\n"
             + "    /** a bias to see how like values are to be collected. \" + new java.util.Random().nextDouble() + \"*/    \n"
             + "    double bias = beta(1.0, 1.0).sample();\n"
             + "        \n"
             + "    //Construct a binomial\n"
             + "    /** A binomial distribution for the tests. \" + new java.util.Random().nextDouble() + \" */\n"
             + "    Binomial binomial = binomial(bias, observedSampleCount);\n"
             + "                \n"
             + "    //Sample from it\n"
             + "    int positiveCount = binomial.sample();\n"
             + "        \n"
             + "    //Link the sampled values to the observed values\n"
             + "    positiveCount.observe(observedPositiveCount);\n"
             + "}";
    }
}