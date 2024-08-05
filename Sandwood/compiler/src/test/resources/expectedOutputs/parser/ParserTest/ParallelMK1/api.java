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

public class ParallelMK1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.DoubleVariable), location(11, 26, 11, 42));

        ArrayVariable<DoubleVariable> generated = Variable.arrayVariable(location(12, 36, 12, 52), VariableType.DoubleVariable, observed.length(location(12, 46, 12, 51)));
        generated.setAlias("generated");
        generated.setLocation(location(12, 14, 12, 22));

        ArrayVariable<DoubleVariable> indirection = Variable.arrayVariable(location(13, 38, 13, 54), VariableType.DoubleVariable, observed.length(location(13, 48, 13, 53)));
        indirection.setAlias("indirection");
        indirection.setLocation(location(13, 14, 13, 24));

        parFor(intVariable(0, location(15, 15, 15, 15)), observed.length(location(15, 29, 15, 34)), intVariable(1, location(15, 37, 15, 39)), true, location(15, 5, 15, 40), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(15, 13, 15, 13));
            DoubleVariable sample = uniform(doubleVariable(0.0, location(16, 33, 16, 35)), doubleVariable(1.0, location(16, 38, 16, 40)), location(16, 25, 16, 41)).sample(location(16, 43, 16, 50));
            sample.setAlias("sample");
            sample.setLocation(location(16, 16, 16, 21));

            indirection.put(i, sample, location(17, 20, 17, 31));
            generated.put(i, gaussian(sample, indirection.get(i, location(18, 52, 18, 54)), location(18, 24, 18, 55)).sample(location(18, 57, 18, 64)), location(18, 18, 18, 64));

        });

        generated.observe(observed, location(21, 15, 21, 31));

        Variable<?>[] $variableNames = {observed, generated, indirection};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ParallelMK1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK1(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length];\n\n    for(int i=0; i<observed.length; i++) {\n        double sample = uniform(0.0, 1.0).sample();\n        indirection[i] = sample;\n        generated[i] = gaussian(sample, indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
    }
}