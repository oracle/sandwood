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

public class Flip1CoinMK16 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        BooleanVariable flipMeasured = observeBoolean("flipMeasured", location(11, 28, 11, 47));
        DoubleVariable guard = observeDouble("guard", location(11, 50, 11, 61));

        BooleanVariable guard$1 = isNaN(guard, location(12, 8, 12, 19));
        ifElse(guard$1, () -> {
            DoubleVariable bias = beta(doubleVariable(1.0, location(13, 28, 13, 30)), doubleVariable(1.0, location(13, 33, 13, 35)), location(13, 23, 13, 36)).sample(location(13, 38, 13, 45));
            bias.setAlias("bias");
            bias.setLocation(location(13, 16, 13, 19));

            Bernoulli bernoulli = bernoulli(bias, location(14, 31, 14, 45));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(14, 19, 14, 27));

            BooleanVariable flip = bernoulli.sample(location(15, 34, 15, 41));
            flip.setAlias("flip");
            flip.setLocation(location(15, 17, 15, 20));

            flip.observe(flipMeasured, location(16, 14, 16, 34));

        }, () -> {
        });

        Variable<?>[] $variableNames = {flipMeasured, guard};
        String[] $constructorArgs = {"flipMeasured", "guard"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK16", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK16(boolean flipMeasured, double guard) {\n    if(isNaN(guard)) {\n        double bias = beta(1.0, 1.0).sample();\n        Bernoulli bernoulli = bernoulli(bias);\n        boolean flip = bernoulli.sample();\n        flip.observe(flipMeasured);\n    }\n}\n";
    }
}