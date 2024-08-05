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

public class Flip1CoinMK13 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 { 
        DoubleVariable bias;
    }

    private static class $IfElseMods2 { 
        DoubleVariable bias;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 28, 11, 50));
        BooleanVariable guard1 = observeBoolean("guard1", location(11, 53, 11, 66));
        BooleanVariable guard2 = observeBoolean("guard2", location(11, 69, 11, 82));

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
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = guard2;
            ifElse(guard$2, () -> {
                $if2.bias = b.divide(intVariable(2, location(20, 22, 20, 22)), location(20, 21, 20, 21));

            }, () -> {
                $else2.bias = b.divide(intVariable(3, location(22, 22, 22, 22)), location(22, 21, 22, 21));
            });
            $else1.bias = ifElseAssignment(guard$2, $if2.bias, $else2.bias, location(19, 9, 19, 18));


        });
        bias = ifElseAssignment(guard$1, $if1.bias, $else1.bias, location(16, 5, 16, 14));
        bias.setAlias("bias");
        bias.setLocation(location(15, 12, 15, 15));

        Bernoulli bernoulli = bernoulli(bias, location(25, 27, 25, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(25, 15, 25, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(26, 33, 26, 47));
        flips.setAlias("flips");
        flips.setLocation(location(26, 15, 26, 19));

        parFor(intVariable(0, location(28, 16, 28, 16)), samples, intVariable(1, location(28, 15, 28, 18)), true, location(28, 5, 28, 27), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(28, 13, 28, 13));
            flips.get(i, location(29, 14, 29, 16)).observe(flipsMeasured.get(i, location(29, 39, 29, 41)), location(29, 18, 29, 42));
        });


        Variable<?>[] $variableNames = {flipsMeasured, guard1, guard2, samples, b, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "guard1", "guard2"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        return compileAPI(opts, $variableNames, "Flip1CoinMK13", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK13(boolean[] flipsMeasured, boolean guard1, boolean guard2) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else { \n        if(guard2) {\n            bias = b/2;\n        } else\n            bias = b/3;\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
    }
}