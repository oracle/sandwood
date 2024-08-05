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

public class Flip1CoinMK2b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 28, 11, 50));

        DoubleVariable a = doubleVariable(1.0, location(12, 16, 12, 18));
        a.setAlias("a");
        a.setLocation(location(12, 12, 12, 12));

        DoubleVariable b = doubleVariable(1.0, location(13, 16, 13, 18));
        b.setAlias("b");
        b.setLocation(location(13, 12, 13, 12));

        DoubleVariable bias = beta(a, b, location(14, 19, 14, 28)).sample(location(14, 30, 14, 37));
        bias.setAlias("bias");
        bias.setLocation(location(14, 12, 14, 15));

        IntVariable samples = flipsMeasured.length(location(16, 33, 16, 38));
        samples.setAlias("samples");
        samples.setLocation(location(16, 9, 16, 15));

        Bernoulli bernoulli = bernoulli(bias, location(18, 27, 18, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(18, 15, 18, 23));

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(19, 34, 19, 42), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(19, 15, 19, 19));

        parFor(intVariable(0, location(20, 15, 20, 15)), samples, intVariable(1, location(20, 27, 20, 29)), true, location(20, 5, 20, 30), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(20, 13, 20, 13));
            flips.put(i, bernoulli.sample(location(21, 30, 21, 37)), location(21, 14, 21, 37));
        });

        flips.observe(flipsMeasured, location(22, 11, 22, 32));

        Variable<?>[] $variableNames = {flipsMeasured, a, b, bias, samples, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK2b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK2b(boolean[] flipsMeasured) {\n    double a = 1.0;\n    double b = 1.0;\n    double bias = beta(a, b).sample();\n        \n    int samples = flipsMeasured.length;\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = new boolean[samples];\n    for(int i=0;i<samples;i++)\n        flips[i] = bernoulli.sample();\n    flips.observe(flipsMeasured);\n}";
    }
}