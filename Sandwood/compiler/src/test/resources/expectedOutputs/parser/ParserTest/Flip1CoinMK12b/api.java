package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
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

public class Flip1CoinMK12b extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 {
        DoubleVariable bias;
    }

    private static class $IfElseMods2 {
        DoubleVariable bias2;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 29, 11, 51));
        BooleanVariable guard1 = observeBoolean("guard1", location(11, 54, 11, 67));
        IntVariable guard2 = observeInt("guard2", location(11, 70, 11, 79));

        IntVariable samples = flipsMeasured.length(location(12, 33, 12, 38));
        samples.setAlias("samples");
        samples.setLocation(location(12, 9, 12, 15));

        DoubleVariable bias;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = guard1;
        IfScope ifScope$1 = ifElse(guard$1, () -> {
            $if1.bias = beta(doubleVariable(1.0, location(16, 19, 16, 21)), intVariable(1, location(16, 24, 16, 24)), location(16, 14, 16, 25)).sample(location(16, 27, 16, 34));
        }, () -> {
            DoubleVariable bias2;
            $IfElseMods2 $if2 = new $IfElseMods2();
            $IfElseMods2 $else2 = new $IfElseMods2();
            BooleanVariable guard$2 = guard2.lessThanEqual(intVariable(2, location(19, 22, 19, 22)), location(19, 19, 19, 20));
            IfScope ifScope$2 = ifElse(guard$2, () -> {
                $if2.bias2 = beta(doubleVariable(1.0, location(20, 26, 20, 28)), intVariable(1, location(20, 31, 20, 31)), location(20, 21, 20, 32)).sample(location(20, 34, 20, 41)).divide(intVariable(2, location(20, 43, 20, 43)), location(20, 42, 20, 42));

            }, () -> {
                $else2.bias2 = beta(doubleVariable(1.0, location(22, 26, 22, 28)), intVariable(1, location(22, 31, 22, 31)), location(22, 21, 22, 32)).sample(location(22, 34, 22, 41)).divide(intVariable(3, location(22, 43, 22, 43)), location(22, 42, 22, 42));
            });
            bias2 = ifElseAssignment(guard$2, $if2.bias2, $else2.bias2, ifScope$2, location(19, 9, 19, 23));
            bias2.setAlias("bias2");
            bias2.setLocation(location(18, 16, 18, 20));

            $else1.bias = bias2;

        });
        bias = ifElseAssignment(guard$1, $if1.bias, $else1.bias, ifScope$1, location(15, 5, 15, 14));
        bias.setAlias("bias");
        bias.setLocation(location(14, 12, 14, 15));

        Bernoulli bernoulli = bernoulli(bias, location(26, 27, 26, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(26, 15, 26, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(27, 33, 27, 47));
        flips.setAlias("flips");
        flips.setLocation(location(27, 15, 27, 19));

        parFor(intVariable(0, location(29, 16, 29, 16)), samples, intVariable(1, location(29, 15, 29, 18)), true, location(29, 5, 29, 27), (i) -> {
            i.setAlias("i");
            i.setLocation(location(29, 13, 29, 13));
            flips.get(i, location(30, 14, 30, 16)).observe(flipsMeasured.get(i, location(30, 39, 30, 41)), location(30, 18, 30, 42));
        });


        Variable<?>[] $variableNames = {flipsMeasured, guard1, guard2, samples, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "guard1", "guard2"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        $helperClasses.add("$IfElseMods2");
        return compileAPI(opts, $variableNames, "Flip1CoinMK12b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
             + " * \n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model Flip1CoinMK12b(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
             + "    int samples = flipsMeasured.length;\n"
             + "        \n"
             + "    double bias;\n"
             + "    if(guard1)\n"
             + "      bias = beta(1.0, 1).sample();\n"
             + "    else {\n"
             + "        double bias2;\n"
             + "        if(guard2 <= 2) {\n"
             + "            bias2 = beta(1.0, 1).sample()/2;\n"
             + "        } else\n"
             + "            bias2 = beta(1.0, 1).sample()/3;\n"
             + "        bias = bias2;\n"
             + "    }\n"
             + "        \n"
             + "    Bernoulli bernoulli = bernoulli(bias);\n"
             + "    boolean[] flips = bernoulli.sample(samples);\n"
             + "\n"
             + "    for(int i:[0..samples))\n"
             + "        flips[i].observe(flipsMeasured[i]);\n"
             + "}\n"
             + "";
    }
}