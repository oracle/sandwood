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

public class Flip2CoinsMK1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 28, 11, 52));

        IntVariable coins = flipsMeasured.length(location(12, 31, 12, 36));
        coins.setAlias("coins");
        coins.setLocation(location(12, 9, 12, 13));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(13, 26, 13, 28)), doubleVariable(1.0, location(13, 31, 13, 33)), location(13, 21, 13, 34)).sample(coins, location(13, 36, 13, 48));
        bias.setAlias("bias");
        bias.setLocation(location(13, 14, 13, 17));

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(15, 36, 15, 44), VariableType.arrayType(VariableType.BooleanVariable), coins);
        flips.setAlias("flips");
        flips.setLocation(location(15, 17, 15, 21));

        parFor(intVariable(0, location(17, 16, 17, 16)), coins, intVariable(1, location(17, 15, 17, 18)), true, location(17, 5, 17, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(17, 13, 17, 13));
            IntVariable samples = flipsMeasured.get(j, location(18, 36, 18, 38)).length(location(18, 40, 18, 45));
            samples.setAlias("samples");
            samples.setLocation(location(18, 13, 18, 19));

            Bernoulli bernoulli = bernoulli(bias.get(j, location(19, 45, 19, 47)), location(19, 31, 19, 48));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(19, 19, 19, 27));

            flips.put(j, bernoulli.sample(samples, location(20, 30, 20, 44)), location(20, 14, 20, 44));

        });

        flips.observe(flipsMeasured, location(23, 11, 23, 32));

        Variable<?>[] $variableNames = {flipsMeasured, coins, bias, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK1(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n    double[] bias = beta(1.0, 1.0).sample(coins);\n        \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}\n";
    }
}