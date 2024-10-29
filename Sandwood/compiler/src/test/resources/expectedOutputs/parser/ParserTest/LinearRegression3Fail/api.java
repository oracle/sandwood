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

//User defined imports.
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;

public class LinearRegressionWrongNameFail extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> x = observeArray("x", VariableType.arrayType(VariableType.DoubleVariable), location(13, 44, 13, 53));
        ArrayVariable<DoubleVariable> yMeasured = observeArray("yMeasured", VariableType.arrayType(VariableType.DoubleVariable), location(13, 56, 13, 73));

        IntVariable noSamples = x.length(location(15, 27, 15, 32));
        noSamples.setAlias("noSamples");
        noSamples.setLocation(location(15, 13, 15, 21));

        DoubleVariable b0 = gaussian(doubleVariable(0.0, location(16, 30, 16, 32)), doubleVariable(2.0, location(16, 35, 16, 37)), location(16, 21, 16, 38)).sample(location(16, 40, 16, 47));
        b0.setAlias("b0");
        b0.setLocation(location(16, 16, 16, 17));

        DoubleVariable b1 = gaussian(doubleVariable(1.0, location(17, 30, 17, 32)), doubleVariable(5.0, location(17, 35, 17, 37)), location(17, 21, 17, 38)).sample(location(17, 40, 17, 47));
        b1.setAlias("b1");
        b1.setLocation(location(17, 16, 17, 17));

        DoubleVariable variance = inverseGamma(doubleVariable(1.0, location(18, 40, 18, 42)), doubleVariable(1.0, location(18, 45, 18, 47)), location(18, 27, 18, 48)).sample(location(18, 50, 18, 57));
        variance.setAlias("variance");
        variance.setLocation(location(18, 16, 18, 23));

        ArrayVariable<DoubleVariable> y = Variable.arrayVariable(location(19, 32, 19, 42), VariableType.DoubleVariable, noSamples);
        y.setAlias("y");
        y.setLocation(location(19, 18, 19, 18));

        parFor(intVariable(0, location(20, 20, 20, 20)), noSamples, intVariable(1, location(20, 19, 20, 22)), true, location(20, 9, 20, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(20, 17, 20, 17));
            y.put(i, gaussian(b0.add(b1.times(x.get(i, location(21, 39, 21, 41)), location(21, 36, 21, 36)), location(21, 31, 21, 31)), variance, location(21, 19, 21, 52)).sample(location(21, 54, 21, 61)), location(21, 13, 21, 61));

        });

        y.observe(yMeasured, location(23, 11, 23, 28));

        Variable<?>[] $variableNames = {x, yMeasured, noSamples, b0, b1, variance, y};
        String[] $constructorArgs = {"x", "yMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LinearRegressionWrongNameFail", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nimport org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;\n\npublic model LinearRegressionWrongNameFail(double[] x, double[] yMeasured) {\n    \n        int noSamples = x.length;\n        double b0 = gaussian(0.0, 2.0).sample();\n        double b1 = gaussian(1.0, 5.0).sample();\n        double variance = inverseGamma(1.0, 1.0).sample();\n        double[] y = new double[noSamples];\n        for(int i:[0..noSamples)) {\n           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n        }\n        y.observe(yMeasured);\n}\n";
    }
}