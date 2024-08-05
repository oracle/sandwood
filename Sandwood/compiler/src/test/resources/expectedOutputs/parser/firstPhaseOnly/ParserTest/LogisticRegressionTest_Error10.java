package org.sandwood.compiler.tests.parser.firstPhaseOnly;

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

public class LogisticRegressionTest_Error10 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<DoubleVariable>> x = observeArray("x", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(13, 38, 13, 49));
        ArrayVariable<BooleanVariable> yMeasured = observeArray("yMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(13, 52, 13, 70));

        IntVariable n = x.length(location(15, 15, 15, 20));
        n.setAlias("n");
        n.setLocation(location(15, 9, 15, 9));

        IntVariable k = x.get(intVariable(0, location(16, 15, 16, 15)), location(16, 14, 16, 16)).length(location(16, 18, 16, 23));
        k.setAlias("k");
        k.setLocation(location(16, 9, 16, 9));

        ArrayVariable<BooleanVariable> y = Variable.arrayVariable(location(17, 30, 17, 32), VariableType.BooleanVariable, n);
        y.setAlias("y");
        y.setLocation(location(17, 15, 17, 15));

        ArrayVariable<DoubleVariable> weights = gaussian(intVariable(0, location(19, 33, 19, 33)), intVariable(10, location(19, 35, 19, 36)), location(19, 24, 19, 37)).sample(k, location(19, 39, 19, 47));
        weights.setAlias("weights");
        weights.setLocation(location(19, 14, 19, 20));

        DoubleVariable bias = gaussian(intVariable(0, location(20, 28, 20, 28)), intVariable(10, location(20, 30, 20, 31)), location(20, 19, 20, 32)).sample(location(20, 34, 20, 41));
        bias.setAlias("bias");
        bias.setLocation(location(20, 12, 20, 15));

        parFor(intVariable(0, location(22, 16, 22, 16)), n, intVariable(1, location(22, 15, 22, 18)), true, location(22, 5, 22, 21), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(22, 13, 22, 13));
            ArrayVariable<DoubleVariable> indicatorValues = Variable.arrayVariable(location(23, 46, 23, 48), VariableType.DoubleVariable, k);
            indicatorValues.setAlias("indicatorValues");
            indicatorValues.setLocation(location(23, 18, 23, 32));

            parFor(intVariable(0, location(24, 20, 24, 20)), k, intVariable(1, location(24, 19, 24, 22)), true, location(24, 9, 24, 25), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(24, 17, 24, 17));
                indicatorValues.put(j, weights.get(j, location(25, 41, 25, 43)).times(x.get(i, location(25, 48, 25, 50)).get(j, location(25, 51, 25, 53)), location(25, 45, 25, 45)), location(25, 28, 25, 45));
            });

            DoubleVariable success = Sigmoid.sigmoid(sum(indicatorValues, location(26, 42, 26, 61)).add(bias, location(26, 63, 26, 63)), location(26, 34, 26, 69));
            success.setAlias("success");
            success.setLocation(location(26, 16, 26, 22));

            y.put(i, bernulli(success, location(27, 16, 27, 32)).sample(location(27, 34, 27, 41)), location(27, 10, 27, 41));

        });

        y.observe(yMeasured, location(30, 7, 30, 24));

        Variable<?>[] $variableNames = {x, yMeasured, n, k, y, weights, bias};
        String[] $constructorArgs = {"x", "yMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LogisticRegressionTest_Error10", $helperClasses, "org.sandwood.compiler.tests.parser.firstPhaseOnly", $constructorArgs, getOriginalModel(), null);
    }

    private static DoubleVariable sum(ArrayVariable<DoubleVariable> a, Location $location) { 
        return reduce(a, intVariable(0, location(34, 26, 34, 26)), location(34, 16, 36, 10), (i, j) ->  { 
            i.setAlias("i");
            i.setLocation(location(34, 30, 34, 30));
            j.setAlias("j");
            j.setLocation(location(34, 32, 34, 32));
            return i.add(j, location(35, 22, 35, 22));
        });
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser.firstPhaseOnly;\n\nimport org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;\n\nmodel LogisticRegressionTest_Error10(double[][] x, boolean[] yMeasured) {\n\n    int n = x.length;\n    int k = x[0].length;\n    boolean[] y = new boolean[n];\n\n    double[] weights = gaussian(0,10).sample(k);\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0..n)) {\n        double[] indicatorValues = new double[k];\n        for(int j:[0..k))\n            indicatorValues[j] = weights[j] * x[i][j];\n        double success = Sigmoid.sigmoid(sum(indicatorValues) + bias);\n        y[i] = bernulli(success).sample();\n    }\n\n    y.observe(yMeasured);\n\n\n    private double sum(double[] a) {\n        return reduce(a, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n}\n";
    }
}