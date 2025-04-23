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

public class Flip1CoinMK20 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable obs1 = observeInt("obs1", location(11, 28, 11, 35));
        IntVariable obs2 = observeInt("obs2", location(11, 38, 11, 45));

        DoubleVariable bias = beta(intVariable(1, location(13, 24, 13, 24)), intVariable(1, location(13, 26, 13, 26)), location(13, 19, 13, 27)).sample(location(13, 29, 13, 36));
        bias.setAlias("bias");
        bias.setLocation(location(13, 12, 13, 15));

        Binomial binomial = binomial(bias, intVariable(100, location(14, 40, 14, 42)), location(14, 25, 14, 43));
        binomial.setAlias("binomial");
        binomial.setLocation(location(14, 14, 14, 21));

        IntVariable count1 = binomial.sample(location(15, 27, 15, 34));
        count1.setAlias("count1");
        count1.setLocation(location(15, 9, 15, 14));

        IntVariable count2 = binomial.sample(location(16, 27, 16, 34));
        count2.setAlias("count2");
        count2.setLocation(location(16, 9, 16, 14));

        IntVariable total = count1.add(count2, location(17, 24, 17, 24));
        total.setAlias("total");
        total.setLocation(location(17, 9, 17, 13));

        count1.observe(obs1, location(19, 12, 19, 24));
        count2.observe(obs2, location(20, 12, 20, 24));

        Variable<?>[] $variableNames = {obs1, obs2, bias, binomial, count1, count2, total};
        String[] $constructorArgs = {"obs1", "obs2"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK20", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
             + " * \n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model Flip1CoinMK20(int obs1, int obs2) {\n"
             + "    \n"
             + "    double bias = beta(1,1).sample();\n"
             + "    Binomial binomial = binomial(bias, 100);\n"
             + "    int count1 = binomial.sample();\n"
             + "    int count2 = binomial.sample();\n"
             + "    int total = count1 + count2;\n"
             + "    \n"
             + "    count1.observe(obs1);\n"
             + "    count2.observe(obs2);\n"
             + "}\n"
             + "";
    }
}