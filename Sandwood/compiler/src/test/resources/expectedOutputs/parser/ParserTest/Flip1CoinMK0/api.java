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

public class Flip1CoinMK0 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        BooleanVariable flipMeasured = observeBoolean("flipMeasured", location(11, 27, 11, 46));

        DoubleVariable bias = beta(doubleVariable(1.0, location(12, 24, 12, 26)), doubleVariable(1.0, location(12, 29, 12, 31)), location(12, 19, 12, 32)).sample(location(12, 34, 12, 41));
        bias.setAlias("bias");
        bias.setLocation(location(12, 12, 12, 15));

        Bernoulli bernoulli = bernoulli(bias, location(13, 27, 13, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(13, 15, 13, 23));

        BooleanVariable flip = bernoulli.sample(location(14, 30, 14, 37));
        flip.setAlias("flip");
        flip.setLocation(location(14, 13, 14, 16));

        flip.observe(flipMeasured, location(15, 10, 15, 30));

        Variable<?>[] $variableNames = {flipMeasured, bias, bernoulli, flip};
        String[] $constructorArgs = {"flipMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK0", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK0(boolean flipMeasured) {\n    double bias = beta(1.0, 1.0).sample();\n    Bernoulli bernoulli = bernoulli(bias);\n    boolean flip = bernoulli.sample();\n    flip.observe(flipMeasured);\n}\n";
    }
}