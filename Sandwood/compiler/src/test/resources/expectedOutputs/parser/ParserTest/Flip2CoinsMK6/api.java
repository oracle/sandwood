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

public class Flip2CoinsMK6 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 28, 11, 52));
        ArrayVariable<IntVariable> shape = observeArray("shape", VariableType.arrayType(VariableType.IntVariable), location(11, 55, 11, 65));

        IntVariable coins = shape.length(location(13, 23, 13, 28));
        coins.setAlias("coins");
        coins.setLocation(location(13, 9, 13, 13));

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(15, 36, 15, 44), VariableType.arrayType(VariableType.BooleanVariable), coins);
        flips.setAlias("flips");
        flips.setLocation(location(15, 17, 15, 21));

        parFor(intVariable(0, location(17, 16, 17, 16)), coins, intVariable(1, location(17, 15, 17, 18)), true, location(17, 5, 17, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(17, 13, 17, 13));
            Beta beta = beta(doubleVariable(1.0, location(18, 26, 18, 28)), doubleVariable(1.0, location(18, 31, 18, 33)), location(18, 21, 18, 34));
            beta.setAlias("beta");
            beta.setLocation(location(18, 14, 18, 17));

            DoubleVariable bias = beta.sample(location(19, 35, 19, 42));
            bias.setAlias("bias");
            bias.setLocation(location(19, 23, 19, 26));
            bias.setPublic();

            IntVariable samples = shape.get(j, location(20, 28, 20, 30));
            samples.setAlias("samples");
            samples.setLocation(location(20, 13, 20, 19));

            Bernoulli bernoulli = bernoulli(bias, location(21, 31, 21, 45));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(21, 19, 21, 27));

            flips.put(j, bernoulli.sample(samples, location(22, 30, 22, 44)), location(22, 14, 22, 44));

        });

        flips.observe(flipsMeasured, location(25, 11, 25, 32));

        Variable<?>[] $variableNames = {flipsMeasured, shape, coins, flips};
        String[] $constructorArgs = {"flipsMeasured", "shape"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK6", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK6(boolean[][] flipsMeasured, int[] shape) {\n\n    int coins = shape.length;\n                 \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        Beta beta = beta(1.0, 1.0);\n        public double bias = beta.sample();\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}";
    }
}