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

public class LogitRegressionTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<DoubleVariable>> x = observeArray("x", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(11, 27, 11, 38));
        ArrayVariable<ArrayVariable<BooleanVariable>> yMeasured = observeArray("yMeasured", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(11, 41, 11, 61));

        IntVariable k = intVariable(3, location(12, 13, 12, 13));
        k.setAlias("k");
        k.setLocation(location(12, 9, 12, 9));

        IntVariable n = x.length(location(14, 15, 14, 20));
        n.setAlias("n");
        n.setLocation(location(14, 9, 14, 9));

        ArrayVariable<ArrayVariable<BooleanVariable>> y = Variable.arrayVariable(location(15, 32, 15, 37), VariableType.arrayType(VariableType.BooleanVariable), n, k);
        y.setAlias("y");
        y.setLocation(location(15, 17, 15, 17));

        ArrayVariable<DoubleVariable> weights = gaussian(intVariable(0, location(17, 33, 17, 33)), intVariable(10, location(17, 35, 17, 36)), location(17, 24, 17, 37)).sample(k, location(17, 39, 17, 47));
        weights.setAlias("weights");
        weights.setLocation(location(17, 14, 17, 20));

        DoubleVariable bias = gaussian(intVariable(0, location(19, 28, 19, 28)), intVariable(10, location(19, 30, 19, 31)), location(19, 19, 19, 32)).sample(location(19, 34, 19, 41));
        bias.setAlias("bias");
        bias.setLocation(location(19, 12, 19, 15));

        parFor(intVariable(0, location(21, 16, 21, 16)), n, intVariable(1, location(21, 15, 21, 19)), true, location(21, 5, 21, 23), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(21, 13, 21, 13));
            ArrayVariable<DoubleVariable> indicator = Variable.arrayVariable(location(22, 40, 22, 42), VariableType.DoubleVariable, k);
            indicator.setAlias("indicator");
            indicator.setLocation(location(22, 18, 22, 26));

            parFor(intVariable(0, location(23, 20, 23, 20)), k, intVariable(1, location(23, 19, 23, 23)), true, location(23, 9, 23, 27), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(23, 17, 23, 17));
                indicator.put(j, exp(weights.get(j, location(24, 39, 24, 41)).times(x.get(i, location(24, 46, 24, 48)).get(j, location(24, 49, 24, 51)), location(24, 43, 24, 43)), location(24, 28, 24, 52)), location(24, 22, 24, 52));

            });

            DoubleVariable sum = indicator.get(intVariable(0, location(28, 32, 28, 32)), location(28, 31, 28, 33)).add(indicator.get(intVariable(1, location(28, 47, 28, 47)), location(28, 46, 28, 48)), location(28, 35, 28, 35)).add(indicator.get(intVariable(2, location(28, 62, 28, 62)), location(28, 61, 28, 63)), location(28, 50, 28, 50));
            sum.setAlias("sum");
            sum.setLocation(location(28, 16, 28, 18));

            ArrayVariable<DoubleVariable> p = Variable.arrayVariable(location(29, 32, 29, 34), VariableType.DoubleVariable, k);
            p.setAlias("p");
            p.setLocation(location(29, 18, 29, 18));

            parFor(intVariable(0, location(31, 20, 31, 20)), k, intVariable(1, location(31, 19, 31, 23)), true, location(31, 9, 31, 27), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(31, 17, 31, 17));
                p.put(j, indicator.get(j, location(32, 29, 32, 31)).divide(sum, location(32, 32, 32, 32)), location(32, 14, 32, 32));
                y.get(i, location(35, 14, 35, 16)).put(j, bernoulli(p.get(j, location(35, 34, 35, 36)).add(bias, location(35, 38, 35, 38)), location(35, 23, 35, 44)).sample(location(35, 46, 35, 53)), location(35, 17, 35, 53));

            });


        });

        y.observe(yMeasured, location(39, 7, 39, 24));

        Variable<?>[] $variableNames = {x, yMeasured, k, n, y, weights, bias};
        String[] $constructorArgs = {"x", "yMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LogitRegressionTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n    int k = 3;\n\n    int n = x.length;\n    boolean[][] y = new boolean[n][k];\n\n    double[] weights = gaussian(0,10).sample(k);\n    //TODO, change this to a beta distribution.\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0 .. n)) {\n        double[] indicator = new double[k];\n        for(int j:[0 .. k)) {\n            indicator[j] = exp(weights[j] * x[i][j]);\n        }\n        \n        //Single assignment semantics means a for loop cannot be used here.\n        double sum = indicator[0] + indicator[1] + indicator[2];\n        double[] p = new double[k];\n\n        for(int j:[0 .. k)) {\n            p[j] = indicator[j]/sum;\n            //This really wants to be a Categorical, but for now y will have\n            //to be arrays with just a single value set.\n            y[i][j] = bernoulli(p[j] + bias).sample();\n        }    \n    }\n\n    y.observe(yMeasured);\n}\n";
    }
}