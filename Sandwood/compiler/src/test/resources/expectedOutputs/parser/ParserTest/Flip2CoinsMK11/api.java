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

public class Flip2CoinsMK11 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<BooleanVariable>> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 29, 11, 53));

        IntVariable coins = flipsMeasured.length(location(12, 31, 12, 36));
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

        parFor(intVariable(0, location(24, 16, 24, 16)), intVariable(1, location(24, 19, 24, 19)), intVariable(1, location(24, 15, 24, 18)), true, location(24, 5, 24, 21), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(24, 13, 24, 13));
            IntVariable samples = flipsMeasured.get(j, location(25, 36, 25, 38)).length(location(25, 40, 25, 45));
            samples.setAlias("samples");
            samples.setLocation(location(25, 13, 25, 19));

            Bernoulli bernoulli1 = bernoulli(bias.get(j, location(26, 46, 26, 48)), location(26, 32, 26, 49));
            bernoulli1.setAlias("bernoulli1");
            bernoulli1.setLocation(location(26, 19, 26, 28));

            flips.put(j, bernoulli1.sample(samples, location(27, 31, 27, 45)), location(27, 14, 27, 45));

        });

        parFor(intVariable(1, location(30, 16, 30, 16)), coins, intVariable(1, location(30, 15, 30, 18)), true, location(30, 5, 30, 25), (k) -> { 
            k.setAlias("k");
            k.setLocation(location(30, 13, 30, 13));
            IntVariable samples = flipsMeasured.get(k, location(31, 36, 31, 38)).length(location(31, 40, 31, 45));
            samples.setAlias("samples");
            samples.setLocation(location(31, 13, 31, 19));

            Bernoulli bernoulli2 = bernoulli(bias.get(k, location(32, 46, 32, 48)), location(32, 32, 32, 49));
            bernoulli2.setAlias("bernoulli2");
            bernoulli2.setLocation(location(32, 19, 32, 28));

            flips.put(k, bernoulli2.sample(samples, location(33, 31, 33, 45)), location(33, 14, 33, 45));

        });

        parFor(intVariable(0, location(36, 16, 36, 16)), coins, intVariable(1, location(36, 15, 36, 18)), true, location(36, 5, 36, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(36, 13, 36, 13));
            ArrayVariable<BooleanVariable> f = flips.get(i, location(37, 28, 37, 30));
            f.setAlias("f");
            f.setLocation(location(37, 19, 37, 19));

            ArrayVariable<BooleanVariable> m = flipsMeasured.get(coins.subtract((i.add(intVariable(1, location(38, 48, 38, 48)), location(38, 47, 38, 47))), location(38, 43, 38, 43)), location(38, 36, 38, 50));
            m.setAlias("m");
            m.setLocation(location(38, 19, 38, 19));

            f.observe(m, location(39, 11, 39, 20));

        });


        Variable<?>[] $variableNames = {flipsMeasured, coins, flips, bias, beta};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK11", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n        \n    for(int j:[0..1)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli1 = bernoulli(bias[j]);\n        flips[j] = bernoulli1.sample(samples);\n    }\n                \n    for(int k:[1..coins)) {\n        int samples = flipsMeasured[k].length;\n        Bernoulli bernoulli2 = bernoulli(bias[k]);\n        flips[k] = bernoulli2.sample(samples);\n    }\n        \n    for(int i:[0..coins)) {\n        boolean[] f = flips[i];\n        boolean[] m = flipsMeasured[coins - (i+1)];\n        f.observe(m);\n    }\n}";
    }
}