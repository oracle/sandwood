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

public class PoissonDecayMK1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<IntVariable> decayDetected = observeArray("decayDetected", VariableType.arrayType(VariableType.IntVariable), location(11, 30, 11, 48));
        DoubleVariable a = observeDouble("a", location(11, 51, 11, 58));
        DoubleVariable b = observeDouble("b", location(11, 61, 11, 68));

        IntVariable samples = decayDetected.length(location(13, 37, 13, 42));
        samples.setAlias("samples");
        samples.setLocation(location(13, 13, 13, 19));

        DoubleVariable rate = gamma(a, b, location(14, 23, 14, 33)).sample(location(14, 35, 14, 42));
        rate.setAlias("rate");
        rate.setLocation(location(14, 16, 14, 19));

        Poisson poisson = poisson(rate, location(16, 27, 16, 39));
        poisson.setAlias("poisson");
        poisson.setLocation(location(16, 17, 16, 23));

        ArrayVariable<IntVariable> decay = poisson.sample(samples, location(17, 31, 17, 45));
        decay.setAlias("decay");
        decay.setLocation(location(17, 15, 17, 19));

        decay.observe(decayDetected, location(18, 15, 18, 36));

        Variable<?>[] $variableNames = {decayDetected, a, b, samples, rate, poisson, decay};
        String[] $constructorArgs = {"decayDetected", "a", "b"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "PoissonDecayMK1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model PoissonDecayMK1(int[] decayDetected, double a, double b) {\n    \n        int samples = decayDetected.length;\n        double rate = gamma(a, b).sample();\n        \n        Poisson poisson = poisson(rate);\n        int[] decay = poisson.sample(samples);\n        decay.observe(decayDetected);\n}";
    }
}