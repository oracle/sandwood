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

public class DistributionsTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> x = observeArray("x", VariableType.arrayType(VariableType.DoubleVariable), location(11, 32, 11, 41));
        ArrayVariable<DoubleVariable> yMeasured = observeArray("yMeasured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 44, 11, 61));

        IntVariable noSamples = x.length(location(12, 23, 12, 28));
        noSamples.setAlias("noSamples");
        noSamples.setLocation(location(12, 9, 12, 17));

        DoubleVariable b0 = cauchy(doubleVariable(0.0, location(13, 24, 13, 26)), doubleVariable(2.0, location(13, 29, 13, 31)), location(13, 17, 13, 32)).sample(location(13, 34, 13, 41));
        b0.setAlias("b0");
        b0.setLocation(location(13, 12, 13, 13));

        DoubleVariable b1 = halfCauchy(doubleVariable(1.0, location(14, 28, 14, 30)), doubleVariable(5.0, location(14, 33, 14, 35)), location(14, 17, 14, 36)).sample(location(14, 38, 14, 45));
        b1.setAlias("b1");
        b1.setLocation(location(14, 12, 14, 13));

        ArrayVariable<DoubleVariable> y = Variable.arrayVariable(location(15, 28, 15, 38), VariableType.DoubleVariable, noSamples);
        y.setAlias("y");
        y.setLocation(location(15, 14, 15, 14));

        parFor(intVariable(0, location(16, 16, 16, 16)), noSamples, intVariable(1, location(16, 15, 16, 18)), true, location(16, 5, 16, 29), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(16, 13, 16, 13));
            y.put(i, studentT(b0.add(b1.times(x.get(i, location(17, 35, 17, 37)), location(17, 32, 17, 32)), location(17, 27, 17, 27)), location(17, 15, 17, 38)).sample(location(17, 40, 17, 47)), location(17, 9, 17, 47));

        });

        y.observe(yMeasured, location(19, 7, 19, 24));

        Variable<?>[] $variableNames = {x, yMeasured, noSamples, b0, b1, y};
        String[] $constructorArgs = {"x", "yMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DistributionsTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DistributionsTest(double[] x, double[] yMeasured) {\n    int noSamples = x.length;\n    double b0 = cauchy(0.0, 2.0).sample();\n    double b1 = halfCauchy(1.0, 5.0).sample();\n    double[] y = new double[noSamples];\n    for(int i:[0..noSamples)) {\n       y[i] = studentT(b0 + b1 * x[i]).sample();\n    }\n    y.observe(yMeasured);\n}\n";
    }
}