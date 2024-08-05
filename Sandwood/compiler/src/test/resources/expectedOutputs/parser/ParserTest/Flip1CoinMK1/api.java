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

public class Flip1CoinMK1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable samples = observeInt("samples", location(11, 27, 11, 37));
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 40, 11, 62));

        DoubleVariable a = doubleVariable(1.0, location(16, 16, 16, 18));
        a.setAlias("a");
        a.setLocation(location(16, 12, 16, 12));

        DoubleVariable b = doubleVariable(1.0, location(17, 16, 17, 18));
        b.setAlias("b");
        b.setLocation(location(17, 12, 17, 12));

        DoubleVariable bias = beta(a, b, location(18, 19, 18, 28)).sample(location(18, 30, 18, 37));
        bias.setAlias("bias");
        bias.setLocation(location(18, 12, 18, 15));

        Bernoulli bernoulli = bernoulli(bias, location(19, 27, 19, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(19, 15, 19, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(20, 33, 20, 47));
        flips.setAlias("flips");
        flips.setLocation(location(20, 15, 20, 19));

        flips.observe(flipsMeasured, location(21, 11, 21, 32));

        Variable<?>[] $variableNames = {samples, flipsMeasured, a, b, bias, bernoulli, flips};
        String[] $constructorArgs = {"samples", "flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK1(int samples, boolean[] flipsMeasured) {\n    /*\n     * This is a bad example as there is a separation between the size of \n     * flips measured, and the size of noSamples.\n     */\n    double a = 1.0;\n    double b = 1.0;\n    double bias = beta(a, b).sample();\n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n    flips.observe(flipsMeasured);\n}\n";
    }
}