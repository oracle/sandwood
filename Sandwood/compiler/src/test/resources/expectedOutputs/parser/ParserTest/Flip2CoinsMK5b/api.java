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

public class Flip2CoinsMK5b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 29, 11, 53));
        ArrayVariable<IntVariable> shape = observeArray("shape", VariableType.arrayType(VariableType.IntVariable), location(11, 56, 11, 66));

        IntVariable coins = shape.length(location(13, 23, 13, 28));
        coins.setAlias("coins");
        coins.setLocation(location(13, 9, 13, 13));

        ArrayVariable<DoubleVariable> bias = Variable.arrayVariable(location(14, 31, 14, 37), VariableType.DoubleVariable, coins);
        bias.setAlias("bias");
        bias.setLocation(location(14, 14, 14, 17));

        parFor(intVariable(0, location(15, 16, 15, 16)), coins, intVariable(1, location(15, 15, 15, 18)), true, location(15, 5, 15, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(15, 13, 15, 13));
            bias.put(i, beta(doubleVariable(1.0, location(16, 22, 16, 24)), doubleVariable(1.0, location(16, 27, 16, 29)), location(16, 17, 16, 30)).sample(location(16, 32, 16, 39)), location(16, 11, 16, 39));
        });

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(18, 36, 18, 44), VariableType.arrayType(VariableType.BooleanVariable), coins);
        flips.setAlias("flips");
        flips.setLocation(location(18, 17, 18, 21));

        parFor(intVariable(0, location(20, 16, 20, 16)), coins, intVariable(1, location(20, 15, 20, 18)), true, location(20, 5, 20, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(20, 13, 20, 13));
            IntVariable samples = shape.get(j, location(21, 28, 21, 30));
            samples.setAlias("samples");
            samples.setLocation(location(21, 13, 21, 19));

            Bernoulli bernoulli = bernoulli(bias.get(coins.subtract((j.add(intVariable(1, location(22, 55, 22, 55)), location(22, 54, 22, 54))), location(22, 51, 22, 51)), location(22, 45, 22, 57)), location(22, 31, 22, 58));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(22, 19, 22, 27));

            flips.put(j, bernoulli.sample(samples, location(23, 30, 23, 44)), location(23, 14, 23, 44));

        });

        flips.observe(flipsMeasured, location(26, 11, 26, 32));

        Variable<?>[] $variableNames = {flipsMeasured, shape, coins, bias, flips};
        String[] $constructorArgs = {"flipsMeasured", "shape"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK5b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK5b(boolean[][] flipsMeasured, int[] shape) {\n     \n    int coins = shape.length;\n    double[] bias = new double[coins];\n    for(int i:[0..coins))\n      bias[i] = beta(1.0, 1.0).sample();\n        \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias[coins-(j+1)]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}\n\n";
    }
}