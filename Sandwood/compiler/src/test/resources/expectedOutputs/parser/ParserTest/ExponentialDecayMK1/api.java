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

public class ExponentialDecayMK1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> decayDetected = observeArray("decayDetected", VariableType.arrayType(VariableType.DoubleVariable), location(11, 34, 11, 55));
        DoubleVariable a = observeDouble("a", location(11, 58, 11, 65));
        DoubleVariable b = observeDouble("b", location(11, 68, 11, 75));

        IntVariable samples = decayDetected.length(location(13, 37, 13, 42));
        samples.setAlias("samples");
        samples.setLocation(location(13, 13, 13, 19));

        DoubleVariable rate = gamma(a, b, location(14, 23, 14, 33)).sample(location(14, 35, 14, 42));
        rate.setAlias("rate");
        rate.setLocation(location(14, 16, 14, 19));

        Exponential exponential = exponential(rate, location(16, 35, 16, 51));
        exponential.setAlias("exponential");
        exponential.setLocation(location(16, 21, 16, 31));

        ArrayVariable<DoubleVariable> decay = exponential.sample(samples, location(17, 38, 17, 52));
        decay.setAlias("decay");
        decay.setLocation(location(17, 18, 17, 22));

        decay.observe(decayDetected, location(18, 15, 18, 36));

        Variable<?>[] $variableNames = {decayDetected, a, b, samples, rate, exponential, decay};
        String[] $constructorArgs = {"decayDetected", "a", "b"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ExponentialDecayMK1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ExponentialDecayMK1(double[] decayDetected, double a, double b) {\n    \n        int samples = decayDetected.length;\n        double rate = gamma(a, b).sample();\n        \n        Exponential exponential = exponential(rate);\n        double[] decay = exponential.sample(samples);\n        decay.observe(decayDetected);\n}";
    }
}