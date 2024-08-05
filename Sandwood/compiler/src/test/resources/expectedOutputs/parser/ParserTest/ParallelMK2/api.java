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

public class ParallelMK2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.DoubleVariable), location(11, 26, 11, 42));

        ArrayVariable<DoubleVariable> generated = Variable.arrayVariable(location(12, 36, 12, 52), VariableType.DoubleVariable, observed.length(location(12, 46, 12, 51)));
        generated.setAlias("generated");
        generated.setLocation(location(12, 14, 12, 22));

        ArrayVariable<DoubleVariable> indirection = Variable.arrayVariable(location(13, 38, 13, 58), VariableType.DoubleVariable, observed.length(location(13, 48, 13, 53)).add(intVariable(1, location(13, 57, 13, 57)), location(13, 55, 13, 55)));
        indirection.setAlias("indirection");
        indirection.setLocation(location(13, 14, 13, 24));

        indirection.put(intVariable(0, location(14, 17, 14, 17)), doubleVariable(1.0, location(14, 22, 14, 24)), location(14, 16, 14, 24));
        parFor(intVariable(0, location(16, 15, 16, 15)), observed.length(location(16, 29, 16, 34)), intVariable(1, location(16, 37, 16, 39)), true, location(16, 5, 16, 40), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(16, 13, 16, 13));
            DoubleVariable sample = uniform(doubleVariable(0.0, location(17, 33, 17, 35)), doubleVariable(1.0, location(17, 38, 17, 40)), location(17, 25, 17, 41)).sample(location(17, 43, 17, 50));
            sample.setAlias("sample");
            sample.setLocation(location(17, 16, 17, 21));

            indirection.put(i.add(intVariable(1, location(18, 25, 18, 25)), location(18, 23, 18, 23)), sample, location(18, 20, 18, 35));
            generated.put(i, gaussian(sample, indirection.get(i, location(19, 52, 19, 54)), location(19, 24, 19, 55)).sample(location(19, 57, 19, 64)), location(19, 18, 19, 64));

        });

        generated.observe(observed, location(22, 15, 22, 31));

        Variable<?>[] $variableNames = {observed, generated, indirection};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ParallelMK2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK2(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length + 1];\n    indirection[0] = 1.0;\n\n    for(int i=0; i<observed.length; i++) {\n        double sample = uniform(0.0, 1.0).sample();\n        indirection[i + 1] = sample;\n        generated[i] = gaussian(sample, indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
    }
}