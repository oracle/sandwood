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

public class Flip1CoinMK3 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(12, 27, 12, 49));

        IntVariable samples = flipsMeasured.length(location(13, 33, 13, 38));
        samples.setAlias("samples");
        samples.setLocation(location(13, 9, 13, 15));

        DoubleVariable bias = beta(doubleVariable(1.0, location(14, 24, 14, 26)), doubleVariable(1.0, location(14, 29, 14, 31)), location(14, 19, 14, 32)).sample(location(14, 34, 14, 41));
        bias.setAlias("bias");
        bias.setLocation(location(14, 12, 14, 15));

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(15, 34, 15, 42), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(15, 15, 15, 19));

        parFor(intVariable(0, location(17, 15, 17, 15)), samples.subtract(intVariable(1, location(17, 28, 17, 28)), location(17, 27, 17, 27)).add(intVariable(1, location(17, 18, 17, 29)), location(17, 18, 17, 29)), intVariable(1, location(17, 30, 17, 32)), true, location(17, 5, 17, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(17, 13, 17, 13));
            Bernoulli bernoulli = bernoulli(bias, location(18, 31, 18, 45));
            bernoulli.setAlias("bernoulli");
            bernoulli.setLocation(location(18, 19, 18, 27));

            flips.put(i, bernoulli.sample(location(19, 30, 19, 37)), location(19, 14, 19, 37));

        });

        parFor(intVariable(0, location(22, 15, 22, 15)), intVariable(2, location(22, 19, 22, 19)).times(samples, location(22, 20, 22, 20)), intVariable(2, location(22, 33, 22, 33)), true, location(22, 5, 22, 34), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(22, 13, 22, 13));
            flips.get(i.divide(intVariable(2, location(23, 17, 23, 17)), location(23, 16, 23, 16)), location(23, 14, 23, 18)).observe(flipsMeasured.get(i.divide(intVariable(2, location(23, 44, 23, 44)), location(23, 43, 23, 43)), location(23, 41, 23, 45)), location(23, 20, 23, 46));

        });


        Variable<?>[] $variableNames = {flipsMeasured, samples, bias, flips};
        String[] $constructorArgs = {"flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK3", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\npublic model Flip1CoinMK3(boolean[] flipsMeasured) {\n    int samples = flipsMeasured.length;\n    double bias = beta(1.0, 1.0).sample();\n    boolean[] flips = new boolean[samples];\n        \n    for(int i=0;i<=samples-1;++i) {\n        Bernoulli bernoulli = bernoulli(bias);\n        flips[i] = bernoulli.sample();\n    }\n\n    for(int i=0;i<2*samples;i=i+2) {\n        flips[i/2].observe(flipsMeasured[i/2]);\n    }\n}\n";
    }
}