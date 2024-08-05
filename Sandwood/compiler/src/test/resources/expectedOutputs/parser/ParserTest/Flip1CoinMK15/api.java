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

public class Flip1CoinMK15 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 { 
        DoubleVariable bias;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 28, 11, 50));
        BooleanVariable guard1 = observeBoolean("guard1", location(11, 53, 11, 66));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        DoubleVariable b = beta(doubleVariable(1.0, location(14, 21, 14, 23)), intVariable(1, location(14, 26, 14, 26)), location(14, 16, 14, 27)).sample(location(14, 29, 14, 36));
        b.setAlias("b");
        b.setLocation(location(14, 12, 14, 12));

        DoubleVariable bias;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = guard1;
        ifElse(guard$1, () -> {
            $if1.bias = b;
        }, () -> {
            ArrayVariable<DoubleVariable> c = Variable.arrayVariable(location(19, 30, 19, 32), VariableType.DoubleVariable, intVariable(2, location(19, 31, 19, 31)));
            c.setAlias("c");
            c.setLocation(location(19, 16, 19, 16));

            c.put(intVariable(0, location(20, 9, 20, 9)), b.divide(intVariable(2, location(20, 16, 20, 16)), location(20, 15, 20, 15)), location(20, 8, 20, 15));
            c.put(intVariable(1, location(21, 9, 21, 9)), b.divide(intVariable(2, location(21, 16, 21, 16)), location(21, 15, 21, 15)), location(21, 8, 21, 15));
            $else1.bias = reduce(c, intVariable(0, location(22, 24, 22, 24)), location(22, 14, 24, 10), (i, j) ->  { 
                i.setAlias("i");
                i.setLocation(location(22, 28, 22, 28));
                j.setAlias("j");
                j.setLocation(location(22, 30, 22, 30));
                return i.add(j, location(23, 22, 23, 22));
            });

        });
        bias = ifElseAssignment(guard$1, $if1.bias, $else1.bias, location(16, 5, 16, 14));
        bias.setAlias("bias");
        bias.setLocation(location(15, 12, 15, 15));

        Bernoulli bernoulli = bernoulli(bias, location(27, 27, 27, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(27, 15, 27, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(28, 33, 28, 47));
        flips.setAlias("flips");
        flips.setLocation(location(28, 15, 28, 19));

        parFor(intVariable(0, location(30, 16, 30, 16)), samples, intVariable(1, location(30, 15, 30, 18)), true, location(30, 5, 30, 27), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(30, 13, 30, 13));
            flips.get(i, location(31, 14, 31, 16)).observe(flipsMeasured.get(i, location(31, 39, 31, 41)), location(31, 18, 31, 42));
        });


        Variable<?>[] $variableNames = {flipsMeasured, guard1, samples, b, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "guard1"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        return compileAPI(opts, $variableNames, "Flip1CoinMK15", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[2];\n      c[0] = b/2;\n      c[1] = b/2;\n      bias = reduce(c, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
    }
}