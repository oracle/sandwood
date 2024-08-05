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

public class Flip1CoinMK5 extends GeneratedAPIBuilder {
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

        Bernoulli bernoulli1 = bernoulli(bias, location(17, 28, 17, 42));
        bernoulli1.setAlias("bernoulli1");
        bernoulli1.setLocation(location(17, 15, 17, 24));

        ArrayVariable<BooleanVariable> flips1 = bernoulli1.sample(samples1, location(18, 35, 18, 50));
        flips1.setAlias("flips1");
        flips1.setLocation(location(18, 15, 18, 20));

        Bernoulli bernoulli2 = bernoulli(bias, location(20, 28, 20, 42));
        bernoulli2.setAlias("bernoulli2");
        bernoulli2.setLocation(location(20, 15, 20, 24));

        ArrayVariable<BooleanVariable> flips2 = bernoulli2.sample(samples2, location(21, 35, 21, 50));
        flips2.setAlias("flips2");
        flips2.setLocation(location(21, 15, 21, 20));

        parFor(samples1.subtract(intVariable(1, location(23, 24, 23, 24)), location(23, 23, 23, 23)), intVariable(1, location(23, 30, 23, 30)).negate(location(23, 29, 23, 29)), intVariable(1, location(23, 33, 23, 35)), false, location(23, 5, 23, 36), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(23, 13, 23, 13));
            flips1.get(i, location(24, 15, 24, 17)).observe(flipsMeasured1.get(i, location(24, 41, 24, 43)), location(24, 19, 24, 44));
        });

        parFor(intVariable(2, location(26, 15, 26, 15)).times(samples2, location(26, 16, 26, 16)).subtract(intVariable(2, location(26, 26, 26, 26)), location(26, 25, 26, 25)), intVariable(0, location(26, 32, 26, 32)).subtract(intVariable(1, location(26, 30, 26, 33)), location(26, 30, 26, 33)), intVariable(2, location(26, 41, 26, 41)), false, location(26, 5, 26, 42), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(26, 13, 26, 13));
            flips2.get(i.divide(intVariable(2, location(27, 18, 27, 18)), location(27, 17, 27, 17)), location(27, 15, 27, 19)).observe(flipsMeasured2.get(i.divide(intVariable(2, location(27, 46, 27, 46)), location(27, 45, 27, 45)), location(27, 43, 27, 47)), location(27, 21, 27, 48));
        });


        Variable<?>[] $variableNames = {flipsMeasured1, flipsMeasured2, samples1, samples2, bias, bernoulli1, flips1, bernoulli2, flips2};
        String[] $constructorArgs = {"flipsMeasured1", "flipsMeasured2"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK5", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK5(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n    int samples1 = flipsMeasured1.length;\n    int samples2 = flipsMeasured2.length;\n        \n    double bias = beta(1.0, 1).sample();\n        \n    Bernoulli bernoulli1 = bernoulli(bias);\n    boolean[] flips1 = bernoulli1.sample(samples1);\n        \n    Bernoulli bernoulli2 = bernoulli(bias);\n    boolean[] flips2 = bernoulli2.sample(samples2);\n\n    for(int i=samples1-1; i>-1; --i)\n        flips1[i].observe(flipsMeasured1[i]);\n\n    for(int i=2*samples2-2; i>=0; i = i-2)\n        flips2[i/2].observe(flipsMeasured2[i/2]);\n}\n";
    }
}