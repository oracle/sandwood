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

public class LinearRegressionTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<DoubleVariable>> x = observeArray("x", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(11, 28, 11, 39));
        ArrayVariable<DoubleVariable> yMeasured = observeArray("yMeasured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 42, 11, 59));

        IntVariable n = x.length(location(13, 19, 13, 24));
        n.setAlias("n");
        n.setLocation(location(13, 13, 13, 13));

        IntVariable k = x.get(intVariable(0, location(14, 19, 14, 19)), location(14, 18, 14, 20)).length(location(14, 22, 14, 27));
        k.setAlias("k");
        k.setLocation(location(14, 13, 14, 13));

        ArrayVariable<DoubleVariable> y = Variable.arrayVariable(location(16, 32, 16, 34), VariableType.DoubleVariable, n);
        y.setAlias("y");
        y.setLocation(location(16, 18, 16, 18));

        ArrayVariable<DoubleVariable> weights = gaussian(intVariable(0, location(18, 37, 18, 37)), intVariable(10, location(18, 39, 18, 40)), location(18, 28, 18, 41)).sample(k, location(18, 43, 18, 51));
        weights.setAlias("weights");
        weights.setLocation(location(18, 18, 18, 24));

        DoubleVariable bias = gaussian(intVariable(0, location(19, 32, 19, 32)), intVariable(10, location(19, 34, 19, 35)), location(19, 23, 19, 36)).sample(location(19, 38, 19, 45));
        bias.setAlias("bias");
        bias.setLocation(location(19, 16, 19, 19));

        DoubleVariable tau = inverseGamma(doubleVariable(3.0, location(20, 35, 20, 37)), doubleVariable(1.0, location(20, 39, 20, 41)), location(20, 22, 20, 42)).sample(location(20, 44, 20, 51));
        tau.setAlias("tau");
        tau.setLocation(location(20, 16, 20, 18));

        parFor(intVariable(0, location(22, 20, 22, 20)), n, intVariable(1, location(22, 19, 22, 22)), true, location(22, 9, 22, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(22, 17, 22, 17));
            ArrayVariable<DoubleVariable> phi = Variable.arrayVariable(location(23, 38, 23, 40), VariableType.DoubleVariable, k);
            phi.setAlias("phi");
            phi.setLocation(location(23, 22, 23, 24));

            parFor(intVariable(0, location(24, 24, 24, 24)), k, intVariable(1, location(24, 29, 24, 29)), true, location(24, 13, 24, 31), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(24, 21, 24, 21));
                phi.put(j, weights.get(j, location(25, 33, 25, 35)).times(x.get(i, location(25, 40, 25, 42)).get(j, location(25, 43, 25, 45)), location(25, 37, 25, 37)), location(25, 20, 25, 37));
            });

            y.put(i, gaussian(sum(phi, location(27, 29, 27, 36)).add(bias, location(27, 38, 27, 38)), tau, location(27, 20, 27, 49)).sample(location(27, 51, 27, 58)), location(27, 14, 27, 58));

        });

        y.observe(yMeasured, location(30, 11, 30, 28));

        Variable<?>[] $variableNames = {x, yMeasured, n, k, y, weights, bias, tau};
        String[] $constructorArgs = {"x", "yMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LinearRegressionTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static DoubleVariable sum(ArrayVariable<DoubleVariable> a, Location $location) { 
        return reduce(a, intVariable(0, location(33, 26, 33, 26)), location(33, 16, 35, 10), (i, j) ->  { 
            i.setAlias("i");
            i.setLocation(location(33, 30, 33, 30));
            j.setAlias("j");
            j.setLocation(location(33, 32, 33, 32));
            return i.add(j, location(34, 22, 34, 22));
        });
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LinearRegressionTest(double[][] x, double[] yMeasured) {\n\n        int n = x.length;\n        int k = x[0].length;\n\n        double[] y = new double[n];\n\n        double[] weights = gaussian(0,10).sample(k);\n        double bias = gaussian(0,10).sample();\n        double tau = inverseGamma(3.0,1.0).sample();\n\n        for(int i:[0..n)) {\n            double[] phi = new double[k];\n            for(int j:[0..k,1))\n                phi[j] = weights[j] * x[i][j];\n            \n            y[i] = gaussian(sum(phi) + bias, tau).sample();\n        }\n        \n        y.observe(yMeasured);\n\n    private double sum(double[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}\n";
    }
}