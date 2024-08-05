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

public class Flip2CoinsMK5 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 28, 11, 52));
        ArrayVariable<IntVariable> shape = observeArray("shape", VariableType.arrayType(VariableType.IntVariable), location(11, 55, 11, 65));

        IntVariable coins = shape.length(location(13, 23, 13, 28));
        coins.setAlias("coins");
        coins.setLocation(location(13, 9, 13, 13));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(14, 26, 14, 28)), doubleVariable(1.0, location(14, 31, 14, 33)), location(14, 21, 14, 34)).sample(coins, location(14, 36, 14, 48));
        bias.setAlias("bias");
        bias.setLocation(location(14, 14, 14, 17));

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(16, 36, 16, 44), VariableType.arrayType(VariableType.BooleanVariable), coins);
        flips.setAlias("flips");
        flips.setLocation(location(16, 17, 16, 21));

        parFor(intVariable(0, location(18, 16, 18, 16)), coins, intVariable(1, location(18, 15, 18, 18)), true, location(18, 5, 18, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(18, 13, 18, 13));
            IntVariable samples = shape.get(j, location(19, 28, 19, 30));
            samples.setAlias("samples");
            samples.setLocation(location(19, 13, 19, 19));

            Bernoulli bernoulli = bernoulli(bias.get(j, location(20, 45, 20, 47)), location(20, 31, 20, 48));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(20, 19, 20, 27));

            flips.put(j, bernoulli.sample(samples, location(21, 30, 21, 44)), location(21, 14, 21, 44));

        });

        flips.observe(flipsMeasured, location(24, 11, 24, 32));

        Variable<?>[] $variableNames = {flipsMeasured, shape, coins, bias, flips};
        String[] $constructorArgs = {"flipsMeasured", "shape"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK5", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK5(boolean[][] flipsMeasured, int[] shape) {\n \n    int coins = shape.length;\n    double[] bias = beta(1.0, 1.0).sample(coins);\n        \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}\n\n";
    }
}