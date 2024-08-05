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

public class Flip2CoinsMK10 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 29, 11, 53));
        ArrayVariable<IntVariable> shape = observeArray("shape", VariableType.arrayType(VariableType.IntVariable), location(11, 56, 11, 66));

        IntVariable coins = shape.length(location(12, 23, 12, 28));
        coins.setAlias("coins");
        coins.setLocation(location(12, 9, 12, 13));

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(14, 36, 14, 44), VariableType.arrayType(VariableType.BooleanVariable), coins);
        flips.setAlias("flips");
        flips.setLocation(location(14, 17, 14, 21));

        ArrayVariable<DoubleVariable> bias = Variable.arrayVariable(location(15, 32, 15, 38), VariableType.DoubleVariable, coins);
        bias.setAlias("bias");
        bias.setLocation(location(15, 15, 15, 18));

        Beta beta = beta(doubleVariable(1.0, location(17, 22, 17, 24)), doubleVariable(1.0, location(17, 27, 17, 29)), location(17, 17, 17, 30));
        beta.setAlias("beta");
        beta.setLocation(location(17, 10, 17, 13));

        bias.put(intVariable(0, location(19, 10, 19, 10)), beta.sample(location(19, 20, 19, 27)), location(19, 9, 19, 27));
        parFor(intVariable(1, location(21, 16, 21, 16)), coins, intVariable(1, location(21, 15, 21, 18)), true, location(21, 5, 21, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(21, 13, 21, 13));
            bias.put(i, beta.sample(location(22, 24, 22, 31)), location(22, 13, 22, 31));
        });

        parFor(intVariable(0, location(24, 16, 24, 16)), coins, intVariable(1, location(24, 15, 24, 18)), true, location(24, 5, 24, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(24, 13, 24, 13));
            IntVariable samples = shape.get(j, location(25, 28, 25, 30));
            samples.setAlias("samples");
            samples.setLocation(location(25, 13, 25, 19));

            Bernoulli bernoulli = bernoulli(bias.get(j, location(26, 45, 26, 47)), location(26, 31, 26, 48));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(26, 19, 26, 27));

            flips.put(j, bernoulli.sample(samples, location(27, 30, 27, 44)), location(27, 14, 27, 44));

        });

        flips.observe(flipsMeasured, location(30, 11, 30, 32));

        Variable<?>[] $variableNames = {flipsMeasured, shape, coins, flips, bias, beta};
        String[] $constructorArgs = {"flipsMeasured", "shape"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK10", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK10(boolean[][] flipsMeasured, int[] shape) {\n    int coins = shape.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n        \n    for(int j:[0..coins)) {\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}";
    }
}