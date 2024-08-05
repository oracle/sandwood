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

public class Flip1CoinMK10 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 28, 11, 50));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        DoubleVariable bias = beta(doubleVariable(1.0, location(14, 24, 14, 26)), intVariable(1, location(14, 29, 14, 29)), location(14, 19, 14, 30)).sample(location(14, 32, 14, 39));
        bias.setAlias("bias");
        bias.setLocation(location(14, 12, 14, 15));

        Bernoulli bernoulli = bernoulli(bias, location(16, 27, 16, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(16, 15, 16, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(17, 33, 17, 47));
        flips.setAlias("flips");
        flips.setLocation(location(17, 15, 17, 19));

        parFor(intVariable(0, location(19, 16, 19, 16)), samples, intVariable(1, location(19, 15, 19, 18)), true, location(19, 5, 19, 27), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(19, 13, 19, 13));
            (booleanVariable(false, location(20, 10, 20, 14)).notEq(flips.get(i, location(20, 24, 20, 26)), location(20, 16, 20, 17))).observe(flipsMeasured.get(i, location(20, 50, 20, 52)), location(20, 29, 20, 53));
        });


        Variable<?>[] $variableNames = {flipsMeasured, samples, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK10", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK10(boolean[] flipsMeasured) {\n    int samples = flipsMeasured.length;\n        \n    double bias = beta(1.0, 1).sample();\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        (false != flips[i]).observe(flipsMeasured[i]);\n}\n";
    }
}