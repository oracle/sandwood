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

public class Conditional2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> observedValue = observeArray("observedValue", VariableType.arrayType(VariableType.DoubleVariable), location(11, 27, 11, 48));

        Bernoulli bernoulli = bernoulli(doubleVariable(0.5, location(14, 37, 14, 39)), location(14, 27, 14, 40));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 15, 14, 23));

        BooleanVariable guard = bernoulli.sample(location(17, 31, 17, 38));
        guard.setAlias("guard");
        guard.setLocation(location(17, 13, 17, 17));

        ArrayVariable<DoubleVariable> value = Variable.arrayVariable(location(19, 32, 19, 34), VariableType.DoubleVariable, intVariable(1, location(19, 33, 19, 33)));
        value.setAlias("value");
        value.setLocation(location(19, 14, 19, 18));

        BooleanVariable guard$1 = guard;
        IfScope ifScope$1 = ifElse(guard$1, () -> {
            value.put(intVariable(0, location(22, 15, 22, 15)), doubleVariable(1.0, location(22, 20, 22, 22)), location(22, 14, 22, 22));
        }, () -> {
            value.put(intVariable(0, location(24, 15, 24, 15)), uniform(doubleVariable(0.0, location(24, 28, 24, 30)), doubleVariable(1.0, location(24, 33, 24, 35)), location(24, 20, 24, 36)).sample(location(24, 38, 24, 45)), location(24, 14, 24, 45));
        });
        ArrayVariable<DoubleVariable> value2 = Variable.arrayVariable(location(26, 34, 26, 36), VariableType.DoubleVariable, intVariable(1, location(26, 35, 26, 35)));
        value2.setAlias("value2");
        value2.setLocation(location(26, 15, 26, 20));

        value2.put(intVariable(0, location(28, 12, 28, 12)), value.get(intVariable(0, location(28, 23, 28, 23)), location(28, 22, 28, 24)), location(28, 11, 28, 24));
        value2.observe(observedValue, location(31, 12, 31, 33));

        Variable<?>[] $variableNames = {observedValue, bernoulli, guard, value, value2};
        String[] $constructorArgs = {"observedValue"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Conditional2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Conditional2(double[] observedValue)  {\n        \n    //Construct a bernoulli\n    Bernoulli bernoulli = bernoulli(0.5);\n                \n    //Sample from it\n    boolean guard = bernoulli.sample();\n    \n    double[] value = new double[1];\n        \n    if(guard)\n        value[0] = 1.0;\n    else\n        value[0] = uniform(0.0, 1.0).sample();\n    \n    double [] value2 = new double[1];\n    \n    value2[0] = value[0];\n    \n    //Link the sampled value to the observed value\n    value2.observe(observedValue);\n}";
    }
}