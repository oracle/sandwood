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

public class Flip1CoinMK1c extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 28, 11, 50));
        DoubleVariable a = observeDouble("a", location(11, 53, 11, 60));
        DoubleVariable b = observeDouble("b", location(11, 63, 11, 70));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        Bernoulli bernoulli = bernoulli(beta(a, b, location(14, 37, 14, 46)).sample(location(14, 48, 14, 55)), location(14, 27, 14, 56));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 15, 14, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(15, 33, 15, 47));
        flips.setAlias("flips");
        flips.setLocation(location(15, 15, 15, 19));

        flips.observe(flipsMeasured, location(16, 11, 16, 32));

        Variable<?>[] $variableNames = {flipsMeasured, a, b, samples, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "a", "b"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK1c", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK1c(boolean[] flipsMeasured, double a, double b) {\n    int samples = flipsMeasured.length;\n        \n    Bernoulli bernoulli = bernoulli(beta(a, b).sample());\n    boolean[] flips = bernoulli.sample(samples);\n    flips.observe(flipsMeasured);\n}\n";
    }
}