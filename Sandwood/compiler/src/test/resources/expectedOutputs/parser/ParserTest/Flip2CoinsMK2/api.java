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

public class Flip2CoinsMK2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable a = observeDouble("a", location(11, 28, 11, 35));
        DoubleVariable b = observeDouble("b", location(11, 38, 11, 45));
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 48, 11, 72));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        IntVariable coins = flipsMeasured.get(intVariable(0, location(13, 31, 13, 31)), location(13, 30, 13, 32)).length(location(13, 34, 13, 39));
        coins.setAlias("coins");
        coins.setLocation(location(13, 9, 13, 13));

        ArrayVariable<DoubleVariable> bias = beta(a, b, location(14, 21, 14, 30)).sample(coins, location(14, 32, 14, 44));
        bias.setAlias("bias");
        bias.setLocation(location(14, 14, 14, 17));

        ArrayVariable<ArrayVariable<BooleanVariable>> flips = Variable.arrayVariable(location(15, 36, 15, 46), VariableType.arrayType(VariableType.BooleanVariable), samples);
        flips.setAlias("flips");
        flips.setLocation(location(15, 17, 15, 21));

        parFor(intVariable(0, location(16, 16, 16, 16)), samples, intVariable(1, location(16, 15, 16, 18)), true, location(16, 5, 16, 27), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(16, 13, 16, 13));
            ArrayVariable<BooleanVariable> sample = Variable.arrayVariable(location(17, 39, 17, 45), VariableType.BooleanVariable, coins);
            sample.setAlias("sample");
            sample.setLocation(location(17, 19, 17, 24));

            parFor(intVariable(0, location(18, 20, 18, 20)), coins, intVariable(1, location(18, 19, 18, 22)), true, location(18, 9, 18, 29), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(18, 17, 18, 17));
                Bernoulli bernoulli = bernoulli(bias.get(j, location(19, 49, 19, 51)), location(19, 35, 19, 52));
                bernoulli.setAlias("bernoulli");
                bernoulli.setLocation(location(19, 23, 19, 31));

                sample.put(j, bernoulli.sample(location(20, 35, 20, 42)), location(20, 19, 20, 42));

            });

            flips.put(i, sample, location(22, 14, 22, 25));

        });

        flips.observe(flipsMeasured, location(25, 11, 25, 32));

        Variable<?>[] $variableNames = {a, b, flipsMeasured, samples, coins, bias, flips};
        String[] $constructorArgs = {"a", "b", "flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK2(double a, double b, boolean[][] flipsMeasured) {\n    int samples = flipsMeasured.length;\n    int coins = flipsMeasured[0].length;\n    double[] bias = beta(a, b).sample(coins);\n    boolean[][] flips = new boolean[samples][];\n    for(int i:[0..samples)) {\n        boolean[] sample = new boolean[coins];\n        for(int j:[0..coins)) {\n            Bernoulli bernoulli = bernoulli(bias[j]);\n            sample[j] = bernoulli.sample();\n        }\n        flips[i] = sample;\n    }\n\n    flips.observe(flipsMeasured);\n}\n";
    }
}