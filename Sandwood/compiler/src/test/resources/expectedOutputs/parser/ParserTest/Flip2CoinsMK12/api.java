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

public class Flip2CoinsMK12 extends GeneratedAPIBuilder {
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

        ArrayVariable<ArrayVariable<BooleanVariable>> intermediateFlips = Variable.arrayVariable(location(15, 48, 15, 56), VariableType.arrayType(VariableType.BooleanVariable), coins);
        intermediateFlips.setAlias("intermediateFlips");
        intermediateFlips.setLocation(location(15, 17, 15, 33));

        ArrayVariable<DoubleVariable> bias = Variable.arrayVariable(location(16, 32, 16, 38), VariableType.DoubleVariable, coins);
        bias.setAlias("bias");
        bias.setLocation(location(16, 15, 16, 18));

        Beta beta = beta(doubleVariable(1.0, location(18, 22, 18, 24)), doubleVariable(1.0, location(18, 27, 18, 29)), location(18, 17, 18, 30));
        beta.setAlias("beta");
        beta.setLocation(location(18, 10, 18, 13));

        bias.put(intVariable(0, location(20, 10, 20, 10)), beta.sample(location(20, 20, 20, 27)), location(20, 9, 20, 27));
        parFor(intVariable(1, location(22, 16, 22, 16)), coins, intVariable(1, location(22, 15, 22, 18)), true, location(22, 5, 22, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(22, 13, 22, 13));
            bias.put(i, beta.sample(location(23, 24, 23, 31)), location(23, 13, 23, 31));
        });

        parFor(intVariable(0, location(25, 16, 25, 16)), intVariable(1, location(25, 19, 25, 19)), intVariable(1, location(25, 15, 25, 18)), true, location(25, 5, 25, 21), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(25, 13, 25, 13));
            IntVariable samples = flipsMeasured.get(j, location(26, 36, 26, 38)).length(location(26, 40, 26, 45));
            samples.setAlias("samples");
            samples.setLocation(location(26, 13, 26, 19));

            Bernoulli bernoulli1 = bernoulli(bias.get(j, location(27, 46, 27, 48)), location(27, 32, 27, 49));
            bernoulli1.setAlias("bernoulli1");
            bernoulli1.setLocation(location(27, 19, 27, 28));

            flips.put(j, bernoulli1.sample(samples, location(28, 31, 28, 45)), location(28, 14, 28, 45));

        });

        parFor(intVariable(1, location(31, 16, 31, 16)), coins, intVariable(1, location(31, 15, 31, 18)), true, location(31, 5, 31, 25), (k) -> { 
            k.setAlias("k");
            k.setLocation(location(31, 13, 31, 13));
            IntVariable samples = flipsMeasured.get(k, location(32, 36, 32, 38)).length(location(32, 40, 32, 45));
            samples.setAlias("samples");
            samples.setLocation(location(32, 13, 32, 19));

            Bernoulli bernoulli2 = bernoulli(bias.get(k, location(33, 46, 33, 48)), location(33, 32, 33, 49));
            bernoulli2.setAlias("bernoulli2");
            bernoulli2.setLocation(location(33, 19, 33, 28));

            flips.put(k, bernoulli2.sample(samples, location(34, 31, 34, 45)), location(34, 14, 34, 45));

        });

        parFor(intVariable(0, location(37, 16, 37, 16)), coins, intVariable(1, location(37, 15, 37, 18)), true, location(37, 5, 37, 25), (l) -> { 
            l.setAlias("l");
            l.setLocation(location(37, 13, 37, 13));
            ArrayVariable<BooleanVariable> source = flipsMeasured.get(l, location(38, 41, 38, 43));
            source.setAlias("source");
            source.setLocation(location(38, 19, 38, 24));

            IntVariable noFlips = source.length(location(39, 30, 39, 35));
            noFlips.setAlias("noFlips");
            noFlips.setLocation(location(39, 13, 39, 19));

            ArrayVariable<BooleanVariable> target = Variable.arrayVariable(location(40, 39, 40, 47), VariableType.BooleanVariable, noFlips);
            target.setAlias("target");
            target.setLocation(location(40, 19, 40, 24));

            intermediateFlips.put(l, target, location(41, 26, 41, 37));
            parFor(intVariable(0, location(43, 20, 43, 20)), noFlips, intVariable(1, location(43, 19, 43, 22)), true, location(43, 9, 43, 31), (m) -> { 
                m.setAlias("m");
                m.setLocation(location(43, 17, 43, 17));
                target.put(m, source.get(m, location(44, 31, 44, 33)), location(44, 19, 44, 33));
            });


        });

        parFor(intVariable(0, location(47, 16, 47, 16)), coins, intVariable(1, location(47, 15, 47, 18)), true, location(47, 5, 47, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(47, 13, 47, 13));
            ArrayVariable<BooleanVariable> f = flips.get(i, location(48, 28, 48, 30));
            f.setAlias("f");
            f.setLocation(location(48, 19, 48, 19));

            ArrayVariable<BooleanVariable> m = intermediateFlips.get(coins.subtract((i.add(intVariable(1, location(49, 52, 49, 52)), location(49, 51, 49, 51))), location(49, 47, 49, 47)), location(49, 40, 49, 54));
            m.setAlias("m");
            m.setLocation(location(49, 19, 49, 19));

            f.observe(m, location(50, 11, 50, 20));

        });


        Variable<?>[] $variableNames = {flipsMeasured, coins, flips, intermediateFlips, bias, beta};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip2CoinsMK12", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    boolean[][] intermediateFlips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n    \n    for(int j:[0..1)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli1 = bernoulli(bias[j]);\n        flips[j] = bernoulli1.sample(samples);\n    }\n                \n    for(int k:[1..coins)) {\n        int samples = flipsMeasured[k].length;\n        Bernoulli bernoulli2 = bernoulli(bias[k]);\n        flips[k] = bernoulli2.sample(samples);\n    }\n        \n    for(int l:[0..coins)) {\n        boolean[] source = flipsMeasured[l];\n        int noFlips = source.length;\n        boolean[] target = new boolean[noFlips];\n        intermediateFlips[l] = target;\n        \n        for(int m:[0..noFlips))\n            target[m] = source[m];\n    }\n        \n    for(int i:[0..coins)) {\n        boolean[] f = flips[i];\n        boolean[] m = intermediateFlips[coins - (i+1)];\n        f.observe(m);\n    }\n}";
    }
}