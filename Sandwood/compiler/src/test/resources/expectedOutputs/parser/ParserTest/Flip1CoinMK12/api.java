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

public class Flip1CoinMK12 extends GeneratedAPIBuilder {
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
        IntVariable guard2 = observeInt("guard2", location(11, 69, 11, 78));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        DoubleVariable bias;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = guard1;
        ifElse(guard$1, () -> {
            $if1.bias = beta(doubleVariable(1.0, location(16, 19, 16, 21)), intVariable(1, location(16, 24, 16, 24)), location(16, 14, 16, 25)).sample(location(16, 27, 16, 34));
        }, () -> {
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = guard2.lessThanEqual(intVariable(2, location(18, 22, 18, 22)), location(18, 19, 18, 20));
            ifElse(guard$2, () -> {
                $if2.bias = beta(doubleVariable(1.0, location(19, 25, 19, 27)), intVariable(1, location(19, 30, 19, 30)), location(19, 20, 19, 31)).sample(location(19, 33, 19, 40)).divide(intVariable(2, location(19, 42, 19, 42)), location(19, 41, 19, 41));

            }, () -> {
                $else2.bias = beta(doubleVariable(1.0, location(21, 25, 21, 27)), intVariable(1, location(21, 30, 21, 30)), location(21, 20, 21, 31)).sample(location(21, 33, 21, 40)).divide(intVariable(3, location(21, 42, 21, 42)), location(21, 41, 21, 41));
            });
            $else1.bias = ifElseAssignment(guard$2, $if2.bias, $else2.bias, location(18, 9, 18, 23));


        });
        bias = ifElseAssignment(guard$1, $if1.bias, $else1.bias, location(15, 5, 15, 14));
        bias.setAlias("bias");
        bias.setLocation(location(14, 12, 14, 15));

        Bernoulli bernoulli = bernoulli(bias, location(24, 27, 24, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(24, 15, 24, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(25, 33, 25, 47));
        flips.setAlias("flips");
        flips.setLocation(location(25, 15, 25, 19));

        parFor(intVariable(0, location(27, 16, 27, 16)), samples, intVariable(1, location(27, 15, 27, 18)), true, location(27, 5, 27, 27), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(27, 13, 27, 13));
            flips.get(i, location(28, 14, 28, 16)).observe(flipsMeasured.get(i, location(28, 39, 28, 41)), location(28, 18, 28, 42));
        });


        Variable<?>[] $variableNames = {flipsMeasured, guard1, guard2, samples, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "guard1", "guard2"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        return compileAPI(opts, $variableNames, "Flip1CoinMK12", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n    int samples = flipsMeasured.length;\n        \n    double bias;\n    if(guard1)\n      bias = beta(1.0, 1).sample();\n    else { \n        if(guard2 <= 2) {\n            bias = beta(1.0, 1).sample()/2;\n        } else\n            bias = beta(1.0, 1).sample()/3;\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
    }
}