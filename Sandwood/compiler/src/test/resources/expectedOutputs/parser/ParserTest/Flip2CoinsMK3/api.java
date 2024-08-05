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

public class Flip2CoinsMK3 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable a = observeDouble("a", location(11, 28, 11, 35));
        DoubleVariable b = observeDouble("b", location(11, 38, 11, 45));
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 48, 11, 72));

        IntVariable coins = flipsMeasured.length(location(12, 32, 12, 37));
        coins.setAlias("coins");
        coins.setLocation(location(12, 10, 12, 14));

        ArrayVariable<DoubleVariable> bias = beta(a, b, location(13, 21, 13, 30)).sample(coins, location(13, 32, 13, 44));
        bias.setAlias("bias");
        bias.setLocation(location(13, 14, 13, 17));

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(14, 36, 14, 44), VariableType.arrayType(VariableType.BooleanVariable), coins);
        flips.setAlias("flips");
        flips.setLocation(location(14, 17, 14, 21));

        parFor(intVariable(0, location(16, 16, 16, 16)), coins, intVariable(1, location(16, 15, 16, 18)), true, location(16, 5, 16, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(16, 13, 16, 13));
            IntVariable samples = flipsMeasured.get(j, location(17, 36, 17, 38)).length(location(17, 40, 17, 45));
            samples.setAlias("samples");
            samples.setLocation(location(17, 13, 17, 19));

            Bernoulli bernoulli = bernoulli(bias.get(j, location(18, 45, 18, 47)), location(18, 31, 18, 48));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(18, 19, 18, 27));

            flips.put(j, bernoulli.sample(samples, location(19, 30, 19, 44)), location(19, 14, 19, 44));

        });

        flips.observe(flipsMeasured, location(22, 11, 22, 32));

        Variable<?>[] $variableNames = {a, b, flipsMeasured, coins, bias, flips};
        String[] $constructorArgs = {"a", "b", "flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK3", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK3(double a, double b, boolean[][] flipsMeasured) {                 \n     int coins = flipsMeasured.length;\n    double[] bias = beta(a, b).sample(coins);\n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n\n    flips.observe(flipsMeasured);\n}\n";
    }
}