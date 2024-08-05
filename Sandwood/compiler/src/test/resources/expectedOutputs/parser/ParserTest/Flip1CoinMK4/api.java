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

public class Flip1CoinMK4 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 27, 11, 49));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        DoubleVariable bias = beta(doubleVariable(1.0, location(13, 24, 13, 26)), doubleVariable(1.0, location(13, 29, 13, 31)), location(13, 19, 13, 32)).sample(location(13, 34, 13, 41));
        bias.setAlias("bias");
        bias.setLocation(location(13, 12, 13, 15));

        Bernoulli bernoulli = bernoulli(bias, location(14, 35, 14, 49));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 23, 14, 31));
        bernoulli.setPrivate();

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(15, 33, 15, 47));
        flips.setAlias("flips");
        flips.setLocation(location(15, 15, 15, 19));

        parFor(samples.subtract(intVariable(1, location(17, 23, 17, 23)), location(17, 22, 17, 22)), intVariable(0, location(17, 29, 17, 29)).subtract(intVariable(1, location(17, 27, 17, 30)), location(17, 27, 17, 30)), intVariable(1, location(17, 32, 17, 34)), false, location(17, 5, 17, 35), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(17, 13, 17, 13));
            flips.get(i, location(18, 14, 18, 16)).observe(flipsMeasured.get(i, location(18, 39, 18, 41)), location(18, 18, 18, 42));
        });


        Variable<?>[] $variableNames = {flipsMeasured, samples, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK4", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK4(boolean[] flipsMeasured) {\n    int samples = flipsMeasured.length;\n    double bias = beta(1.0, 1.0).sample();\n    private Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n        \n    for(int i=samples-1; i>=0; i--)\n        flips[i].observe(flipsMeasured[i]);\n}\n";
    }
}