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

public class Conditional3 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable observedValue = observeDouble("observedValue", location(11, 27, 11, 46));

        Bernoulli bernoulli = bernoulli(doubleVariable(0.5, location(14, 37, 14, 39)), location(14, 27, 14, 40));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 15, 14, 23));

        BooleanVariable guard = bernoulli.sample(location(17, 31, 17, 38));
        guard.setAlias("guard");
        guard.setLocation(location(17, 13, 17, 17));

        DoubleVariable bias = ifElseLambdaAssignment(guard, () -> { return doubleVariable(0.5, location(19, 25, 19, 27)); }, () -> { return uniform(doubleVariable(0.0, location(19, 37, 19, 39)), doubleVariable(0.5, location(19, 42, 19, 44)), location(19, 29, 19, 45)).sample(location(19, 47, 19, 54)); }, location(19, 19, 19, 54));
        bias.setAlias("bias");
        bias.setLocation(location(19, 12, 19, 15));

        DoubleVariable value = beta(bias, doubleVariable(1.0, location(21, 31, 21, 33)), location(21, 20, 21, 34)).sample(location(21, 36, 21, 43));
        value.setAlias("value");
        value.setLocation(location(21, 12, 21, 16));

        value.observe(observedValue, location(24, 11, 24, 32));

        Variable<?>[] $variableNames = {observedValue, bernoulli, guard, bias, value};
        String[] $constructorArgs = {"observedValue"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Conditional3", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "public model Conditional3(double observedValue)  {\n"
             + "        \n"
             + "    //Construct a bernoulli\n"
             + "    Bernoulli bernoulli = bernoulli(0.5);\n"
             + "                \n"
             + "    //Sample from it\n"
             + "    boolean guard = bernoulli.sample();\n"
             + "        \n"
             + "    double bias = guard?0.5:uniform(0.0, 0.5).sample();\n"
             + "    \n"
             + "    double value = beta(bias, 1.0).sample();\n"
             + "    \n"
             + "    //Link the sampled value to the observed value\n"
             + "    value.observe(observedValue);\n"
             + "}";
    }
}