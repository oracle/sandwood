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

public class Conditional4 extends GeneratedAPIBuilder {
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

        ArrayVariable<DoubleVariable> bias = Variable.arrayVariable(location(19, 31, 19, 33), VariableType.DoubleVariable, intVariable(1, location(19, 32, 19, 32)));
        bias.setAlias("bias");
        bias.setLocation(location(19, 14, 19, 17));

        BooleanVariable guard$1 = guard;
        IfScope ifScope$1 = ifElse(guard$1, () -> {
            bias.put(intVariable(0, location(22, 14, 22, 14)), doubleVariable(0.5, location(22, 19, 22, 21)), location(22, 13, 22, 21));
        }, () -> {
            bias.put(intVariable(0, location(24, 14, 24, 14)), uniform(doubleVariable(0.0, location(24, 27, 24, 29)), doubleVariable(0.5, location(24, 32, 24, 34)), location(24, 19, 24, 35)).sample(location(24, 37, 24, 44)), location(24, 13, 24, 44));
        });
        DoubleVariable value = beta(bias.get(intVariable(0, location(26, 30, 26, 30)), location(26, 29, 26, 31)), doubleVariable(1.0, location(26, 33, 26, 35)), location(26, 20, 26, 36)).sample(location(26, 38, 26, 45));
        value.setAlias("value");
        value.setLocation(location(26, 12, 26, 16));

        value.observe(observedValue, location(29, 11, 29, 32));

        Variable<?>[] $variableNames = {observedValue, bernoulli, guard, bias, value};
        String[] $constructorArgs = {"observedValue"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Conditional4", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "public model Conditional4(double observedValue)  {\n"
             + "        \n"
             + "    //Construct a bernoulli\n"
             + "    Bernoulli bernoulli = bernoulli(0.5);\n"
             + "                \n"
             + "    //Sample from it\n"
             + "    boolean guard = bernoulli.sample();\n"
             + "    \n"
             + "    double[] bias = new double[1];\n"
             + "        \n"
             + "    if(guard)\n"
             + "        bias[0] = 0.5;\n"
             + "    else\n"
             + "        bias[0] = uniform(0.0, 0.5).sample();\n"
             + "    \n"
             + "    double value = beta(bias[0],1.0).sample();\n"
             + "    \n"
             + "    //Link the sampled value to the observed value\n"
             + "    value.observe(observedValue);\n"
             + "}";
    }
}