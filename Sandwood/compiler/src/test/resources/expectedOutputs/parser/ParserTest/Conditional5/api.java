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

public class Conditional5 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable observedValue = observeDouble("observedValue", location(11, 27, 11, 46));
        BooleanVariable observedGuard = observeBoolean("observedGuard", location(11, 49, 11, 69));

        Bernoulli bernoulli = bernoulli(doubleVariable(0.5, location(14, 37, 14, 39)), location(14, 27, 14, 40));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 15, 14, 23));

        BooleanVariable guard = bernoulli.sample(location(17, 31, 17, 38));
        guard.setAlias("guard");
        guard.setLocation(location(17, 13, 17, 17));

        DoubleVariable a = uniform(doubleVariable(0.0, location(18, 24, 18, 26)), doubleVariable(1.0, location(18, 29, 18, 31)), location(18, 16, 18, 32)).sample(location(18, 34, 18, 41));
        a.setAlias("a");
        a.setLocation(location(18, 12, 18, 12));

        DoubleVariable b = uniform(doubleVariable(0.0, location(19, 24, 19, 26)), doubleVariable(1.0, location(19, 29, 19, 31)), location(19, 16, 19, 32)).sample(location(19, 34, 19, 41));
        b.setAlias("b");
        b.setLocation(location(19, 12, 19, 12));

        DoubleVariable value = ifElseLambdaAssignment(guard, () -> { return a; }, () -> { return b; }, location(21, 20, 21, 28));
        value.setAlias("value");
        value.setLocation(location(21, 12, 21, 16));

        guard.observe(observedGuard, location(24, 11, 24, 32));
        value.observe(observedValue, location(26, 11, 26, 32));

        Variable<?>[] $variableNames = {observedValue, observedGuard, bernoulli, guard, a, b, value};
        String[] $constructorArgs = {"observedValue", "observedGuard"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Conditional5", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "public model Conditional5(double observedValue, boolean observedGuard)  {\n"
             + "        \n"
             + "    //Construct a bernoulli\n"
             + "    Bernoulli bernoulli = bernoulli(0.5);\n"
             + "                \n"
             + "    //Sample from it\n"
             + "    boolean guard = bernoulli.sample();\n"
             + "    double a = uniform(0.0, 1.0).sample();\n"
             + "    double b = uniform(0.0, 1.0).sample();\n"
             + "        \n"
             + "    double value = guard?a:b;\n"
             + "    \n"
             + "    //\n"
             + "    guard.observe(observedGuard);\n"
             + "    //Link the sampled value to the observed value\n"
             + "    value.observe(observedValue);\n"
             + "}";
    }
}