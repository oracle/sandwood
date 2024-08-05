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

public class Flip1CoinMK6 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured1 = observeArray("flipsMeasured1", VariableType.arrayType(VariableType.BooleanVariable), location(11, 27, 11, 50));
        ArrayVariable<BooleanVariable> flipsMeasured2 = observeArray("flipsMeasured2", VariableType.arrayType(VariableType.BooleanVariable), location(11, 53, 11, 76));

        IntVariable samples1 = flipsMeasured1.length(location(12, 35, 12, 40));
        samples1.setAlias("samples1");
        samples1.setLocation(location(12, 9, 12, 16));

        IntVariable samples2 = flipsMeasured2.length(location(13, 35, 13, 40));
        samples2.setAlias("samples2");
        samples2.setLocation(location(13, 9, 13, 16));

        DoubleVariable bias = beta(doubleVariable(1.0, location(15, 24, 15, 26)), intVariable(1, location(15, 29, 15, 29)), location(15, 19, 15, 30)).sample(location(15, 32, 15, 39));
        bias.setAlias("bias");
        bias.setLocation(location(15, 12, 15, 15));

        Bernoulli bernoulli = bernoulli(bias, location(17, 27, 17, 41));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(17, 15, 17, 23));

        ArrayVariable<BooleanVariable> flips1 = bernoulli.sample(samples1, location(18, 34, 18, 49));
        flips1.setAlias("flips1");
        flips1.setLocation(location(18, 15, 18, 20));

        ArrayVariable<BooleanVariable> flips2 = bernoulli.sample(samples2, location(19, 34, 19, 49));
        flips2.setAlias("flips2");
        flips2.setLocation(location(19, 15, 19, 20));

        parFor(intVariable(0, location(21, 16, 21, 16)), samples1, intVariable(1, location(21, 15, 21, 18)), true, location(21, 5, 21, 28), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(21, 13, 21, 13));
            flips1.get(i, location(22, 15, 22, 17)).observe(flipsMeasured1.get(i, location(22, 41, 22, 43)), location(22, 19, 22, 44));
        });

        parFor(intVariable(0, location(24, 16, 24, 16)), samples2, intVariable(1, location(24, 15, 24, 18)), true, location(24, 5, 24, 28), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(24, 13, 24, 13));
            flips2.get(i, location(25, 15, 25, 17)).observe(flipsMeasured2.get(i, location(25, 41, 25, 43)), location(25, 19, 25, 44));
        });


        Variable<?>[] $variableNames = {flipsMeasured1, flipsMeasured2, samples1, samples2, bias, bernoulli, flips1, flips2};
        String[] $constructorArgs = {"flipsMeasured1", "flipsMeasured2"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK6", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK6(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n    int samples1 = flipsMeasured1.length;\n    int samples2 = flipsMeasured2.length;\n        \n    double bias = beta(1.0, 1).sample();\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips1 = bernoulli.sample(samples1);\n    boolean[] flips2 = bernoulli.sample(samples2);\n\n    for(int i:[0..samples1))\n        flips1[i].observe(flipsMeasured1[i]);\n\n    for(int i:[0..samples2))\n        flips2[i].observe(flipsMeasured2[i]);\n}\n";
    }
}