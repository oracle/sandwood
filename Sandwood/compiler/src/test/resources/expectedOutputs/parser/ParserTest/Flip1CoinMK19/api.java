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

public class Flip1CoinMK19 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable samples = observeInt("samples", location(11, 28, 11, 38));
        IntVariable a = observeInt("a", location(11, 41, 11, 45));
        IntVariable b = observeInt("b", location(11, 48, 11, 52));
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 55, 11, 77));

        DoubleVariable q = beta(intVariable(1, location(13, 21, 13, 21)), intVariable(1, location(13, 23, 13, 23)), location(13, 16, 13, 24)).sample(location(13, 26, 13, 33));
        q.setAlias("q");
        q.setLocation(location(13, 12, 13, 12));

        DoubleVariable t = beta(intVariable(1, location(14, 21, 14, 21)), intVariable(1, location(14, 23, 14, 23)), location(14, 16, 14, 24)).sample(location(14, 26, 14, 33));
        t.setAlias("t");
        t.setLocation(location(14, 12, 14, 12));

        ArrayVariable<ArrayVariable<DoubleVariable>> bias = Variable.arrayVariable(location(16, 33, 16, 37), VariableType.arrayType(VariableType.DoubleVariable), intVariable(1, location(16, 34, 16, 34)));
        bias.setAlias("bias");
        bias.setLocation(location(16, 16, 16, 19));

        ArrayVariable<DoubleVariable> inner = Variable.arrayVariable(location(17, 40, 17, 42), VariableType.DoubleVariable, intVariable(2, location(17, 41, 17, 41)));
        inner.setAlias("inner");
        inner.setLocation(location(17, 22, 17, 26));
        inner.setPrivate();

        inner.put(intVariable(0, location(18, 11, 18, 11)), q, location(18, 10, 18, 16));
        bias.put(intVariable(0, location(19, 10, 19, 10)), inner, location(19, 9, 19, 19));
        bias.get(intVariable(0, location(20, 10, 20, 10)), location(20, 9, 20, 11)).put(intVariable(1, location(20, 13, 20, 13)), t, location(20, 12, 20, 18));
        Bernoulli bernoulli = bernoulli(inner.get(b, location(22, 42, 22, 44)), location(22, 27, 22, 45));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(22, 15, 22, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(23, 33, 23, 47));
        flips.setAlias("flips");
        flips.setLocation(location(23, 15, 23, 19));

        flips.observe(flipsMeasured, location(25, 11, 25, 32));

        Variable<?>[] $variableNames = {samples, a, b, flipsMeasured, q, t, bias, inner, bernoulli, flips};
        String[] $constructorArgs = {"samples", "a", "b", "flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK19", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK19(int samples, int a, int b, boolean[] flipsMeasured) {\n    \n    double q = beta(1,1).sample();\n    double t = beta(1,1).sample();\n    \n    double[][] bias = new double[1][];\n    private double[] inner = new double[2];\n    inner[0] = q;\n    bias[0] = inner;\n    bias[0][1] = t;\n    \n    Bernoulli bernoulli = bernoulli(inner[b]);\n    boolean[] flips = bernoulli.sample(samples);\n    \n    flips.observe(flipsMeasured);\n}\n";
    }
}