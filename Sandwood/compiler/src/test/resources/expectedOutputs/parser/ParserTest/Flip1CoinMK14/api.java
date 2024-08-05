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

public class Flip1CoinMK14 extends GeneratedAPIBuilder {
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
            ArrayVariable<DoubleVariable> c = Variable.arrayVariable(location(19, 30, 19, 32), VariableType.DoubleVariable, intVariable(1, location(19, 31, 19, 31)));
            c.setAlias("c");
            c.setLocation(location(19, 16, 19, 16));

            c.put(intVariable(0, location(20, 9, 20, 9)), b.divide(intVariable(2, location(20, 16, 20, 16)), location(20, 15, 20, 15)), location(20, 8, 20, 15));
            $else1.bias = c.get(intVariable(0, location(21, 16, 21, 16)), location(21, 15, 21, 17));

        });
        bias = ifElseAssignment(guard$1, $if1.bias, $else1.bias, location(16, 5, 16, 14));
        bias.setAlias("bias");
        bias.setLocation(location(15, 12, 15, 15));

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


        Variable<?>[] $variableNames = {flipsMeasured, guard1, samples, b, bias, bernoulli, flips};
        String[] $constructorArgs = {"flipsMeasured", "guard1"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        return compileAPI(opts, $variableNames, "Flip1CoinMK14", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[1];\n      c[0] = b/2;\n      bias = c[0];\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
    }
}