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

public class LinearRegressionBasic2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> x = observeArray("x", VariableType.arrayType(VariableType.DoubleVariable), location(11, 37, 11, 46));
        ArrayVariable<DoubleVariable> yMeasured = observeArray("yMeasured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 49, 11, 66));

        IntVariable noSamples = x.length(location(13, 27, 13, 32));
        noSamples.setAlias("noSamples");
        noSamples.setLocation(location(13, 13, 13, 21));

        DoubleVariable b0 = gaussian(doubleVariable(0.0, location(14, 30, 14, 32)), doubleVariable(2.0, location(14, 35, 14, 37)), location(14, 21, 14, 38)).sample(location(14, 40, 14, 47));
        b0.setAlias("b0");
        b0.setLocation(location(14, 16, 14, 17));

        DoubleVariable b1 = gaussian(doubleVariable(1.0, location(15, 30, 15, 32)), doubleVariable(5.0, location(15, 35, 15, 37)), location(15, 21, 15, 38)).sample(location(15, 40, 15, 47));
        b1.setAlias("b1");
        b1.setLocation(location(15, 16, 15, 17));

        DoubleVariable variance = intVariable(1, location(16, 27, 16, 27)).divide(gamma(doubleVariable(1.0, location(16, 35, 16, 37)), doubleVariable(1.0, location(16, 40, 16, 42)), location(16, 29, 16, 43)).sample(location(16, 45, 16, 52)), location(16, 28, 16, 28));
        variance.setAlias("variance");
        variance.setLocation(location(16, 16, 16, 23));

        ArrayVariable<DoubleVariable> y = Variable.arrayVariable(location(17, 32, 17, 42), VariableType.DoubleVariable, noSamples);
        y.setAlias("y");
        y.setLocation(location(17, 18, 17, 18));

        parFor(intVariable(0, location(18, 20, 18, 20)), noSamples, intVariable(1, location(18, 19, 18, 22)), true, location(18, 9, 18, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 17, 18, 17));
            y.put(i, gaussian(b0.add(b1.times(x.get(i, location(19, 39, 19, 41)), location(19, 36, 19, 36)), location(19, 31, 19, 31)), variance, location(19, 19, 19, 52)).sample(location(19, 54, 19, 61)), location(19, 13, 19, 61));

        });

        y.observe(yMeasured, location(21, 11, 21, 28));

        Variable<?>[] $variableNames = {x, yMeasured, noSamples, b0, b1, variance, y};
        String[] $constructorArgs = {"x", "yMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LinearRegressionBasic2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model LinearRegressionBasic2(double[] x, double[] yMeasured) {\n    \n        int noSamples = x.length;\n        double b0 = gaussian(0.0, 2.0).sample();\n        double b1 = gaussian(1.0, 5.0).sample();\n        double variance = 1/gamma(1.0, 1.0).sample();\n        double[] y = new double[noSamples];\n        for(int i:[0..noSamples)) {\n           y[i] = gaussian(b0 + b1 * x[i], variance).sample();\n        }\n        y.observe(yMeasured);\n}\n";
    }
}