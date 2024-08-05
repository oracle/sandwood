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

public class Flip1CoinMK1b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 28, 11, 50));
        DoubleVariable a = observeDouble("a", location(11, 53, 11, 60));
        DoubleVariable b = observeDouble("b", location(11, 63, 11, 70));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        DoubleVariable bias = beta(a, b, location(13, 19, 13, 28)).sample(location(13, 30, 13, 37));
        bias.setAlias("bias");
        bias.setLocation(location(13, 12, 13, 15));

        Bernoulli bernoulli = bernoulli(bias, location(15, 27, 15, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(15, 15, 15, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(16, 33, 16, 47));
        flips.setAlias("flips");
        flips.setLocation(location(16, 15, 16, 19));

        flips.observe(flipsMeasured, location(17, 11, 17, 32));

        Variable<?>[] $variableNames = {flipsMeasured, a, b, samples, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "a", "b"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK1b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK1b(boolean[] flipsMeasured, double a, double b) {\n    int samples = flipsMeasured.length;\n    double bias = beta(a, b).sample();\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n    flips.observe(flipsMeasured);\n}\n";
    }
}