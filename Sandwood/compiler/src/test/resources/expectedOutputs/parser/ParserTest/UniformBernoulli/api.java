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

public class UniformBernoulli extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.BooleanVariable), location(11, 31, 11, 48));

        DoubleVariable a = doubleVariable(0.0, location(12, 16, 12, 18));
        a.setAlias("a");
        a.setLocation(location(12, 12, 12, 12));

        DoubleVariable b = doubleVariable(1.0, location(13, 16, 13, 18));
        b.setAlias("b");
        b.setLocation(location(13, 12, 13, 12));

        DoubleVariable prior = uniform(a, b, location(14, 20, 14, 32)).sample(location(14, 34, 14, 41));
        prior.setAlias("prior");
        prior.setLocation(location(14, 12, 14, 16));

        Bernoulli bernoulli = bernoulli(prior, location(15, 27, 15, 42));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(15, 15, 15, 23));

        ArrayVariable<BooleanVariable> output = bernoulli.sample(observed.length(location(16, 50, 16, 55)), location(16, 34, 16, 56));
        output.setAlias("output");
        output.setLocation(location(16, 15, 16, 20));

        output.observe(observed, location(17, 12, 17, 28));

        Variable<?>[] $variableNames = {observed, a, b, prior, bernoulli, output};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "UniformBernoulli", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model UniformBernoulli(boolean[] observed) {\n    double a = 0.0;\n    double b = 1.0;\n    double prior = uniform(a, b).sample();\n    Bernoulli bernoulli = bernoulli(prior);\n    boolean[] output = bernoulli.sample(observed.length);\n    output.observe(observed);\n}\n";
    }
}