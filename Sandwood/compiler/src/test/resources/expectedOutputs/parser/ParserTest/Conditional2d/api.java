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

public class Conditional2d extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 {
        DoubleVariable value;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> observedValue = observeArray("observedValue", VariableType.arrayType(VariableType.DoubleVariable), location(11, 28, 11, 49));

        Bernoulli bernoulli = bernoulli(doubleVariable(0.5, location(14, 37, 14, 39)), location(14, 27, 14, 40));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 15, 14, 23));

        BooleanVariable guard = bernoulli.sample(location(17, 31, 17, 38));
        guard.setAlias("guard");
        guard.setLocation(location(17, 13, 17, 17));

        DoubleVariable value;
        DoubleVariable u;
        u = uniform(doubleVariable(0.0, location(22, 17, 22, 19)), doubleVariable(1.0, location(22, 22, 22, 24)), location(22, 9, 22, 25)).sample(location(22, 27, 22, 34));
        u.setAlias("u");
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = guard;
        IfScope ifScope$1 = ifElse(guard$1, () -> {
            $if1.value = doubleVariable(1.0, location(25, 17, 25, 19));
        }, () -> {
            $else1.value = u;

        });
        value = ifElseAssignment(guard$1, $if1.value, $else1.value, ifScope$1, location(24, 5, 24, 13));
        value.setAlias("value");
        value.setLocation(location(19, 12, 19, 16));

        ArrayVariable<DoubleVariable> value2 = Variable.arrayVariable(location(30, 33, 30, 35), VariableType.DoubleVariable, intVariable(1, location(30, 34, 30, 34)));
        value2.setAlias("value2");
        value2.setLocation(location(30, 14, 30, 19));

        value2.put(intVariable(0, location(32, 12, 32, 12)), value, location(32, 11, 32, 21));
        value2.observe(observedValue, location(35, 12, 35, 33));

        Variable<?>[] $variableNames = {observedValue, bernoulli, guard, value, u, value2};
        String[] $constructorArgs = {"observedValue"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        return compileAPI(opts, $variableNames, "Conditional2d", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "public model Conditional2d(double[] observedValue)  {\n"
             + "        \n"
             + "    //Construct a bernoulli\n"
             + "    Bernoulli bernoulli = bernoulli(0.5);\n"
             + "                \n"
             + "    //Sample from it\n"
             + "    boolean guard = bernoulli.sample();\n"
             + "    \n"
             + "    double value;\n"
             + "    \n"
             + "    double u;\n"
             + "    u = uniform(0.0, 1.0).sample();\n"
             + "    \n"
             + "    if(guard)\n"
             + "        value = 1.0;\n"
             + "    else {\n"
             + "        value = u;\n"
             + "    }\n"
             + "    \n"
             + "    double[] value2 = new double[1];\n"
             + "    \n"
             + "    value2[0] = value;\n"
             + "    \n"
             + "    //Link the sampled value to the observed value\n"
             + "    value2.observe(observedValue);\n"
             + "}";
    }
}