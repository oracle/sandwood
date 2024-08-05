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

public class Vulcano2012basic extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noProducts = observeInt("noProducts", location(16, 24, 16, 37));
        IntVariable T = observeInt("T", location(16, 40, 16, 44));
        IntVariable s = observeInt("s", location(16, 47, 16, 51));
        ArrayVariable<ArrayVariable<DoubleVariable>> ObsSales = observeArray("ObsSales", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(16, 54, 16, 72));
        ArrayVariable<ArrayVariable<IntVariable>> Avail = observeArray("Avail", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(16, 75, 16, 87));

        ArrayVariable<DoubleVariable> ut = gaussian(intVariable(0, location(21, 28, 21, 28)), intVariable(10, location(21, 31, 21, 32)), location(21, 19, 21, 33)).sample(noProducts, location(21, 35, 21, 52));
        ut.setAlias("ut");
        ut.setLocation(location(21, 14, 21, 15));

        ArrayVariable<DoubleVariable> exped = Variable.arrayVariable(location(24, 32, 24, 43), VariableType.DoubleVariable, noProducts);
        exped.setAlias("exped");
        exped.setLocation(location(24, 14, 24, 18));

        parFor(intVariable(0, location(25, 18, 25, 18)), noProducts, intVariable(1, location(25, 17, 25, 20)), true, location(25, 5, 25, 32), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(25, 13, 25, 13));
            exped.put(j, exp(ut.get(j, location(26, 22, 26, 24)), location(26, 16, 26, 25)), location(26, 10, 26, 25));

        });

        DoubleVariable sum = reduce(exped, intVariable(0, location(28, 32, 28, 32)), location(28, 18, 28, 62), (k, l) ->  { 
            k.setAlias("k");
            k.setLocation(location(28, 36, 28, 36));
            l.setAlias("l");
            l.setLocation(location(28, 39, 28, 39));
            return k.add(l, location(28, 56, 28, 56));
        });
        sum.setAlias("sum");
        sum.setLocation(location(28, 12, 28, 14));

        DoubleVariable denom = sum.divide(s, location(29, 23, 29, 23));
        denom.setAlias("denom");
        denom.setLocation(location(29, 12, 29, 16));

        ArrayVariable<DoubleVariable> lambda = gamma(intVariable(10, location(32, 30, 32, 31)), intVariable(10, location(32, 33, 32, 34)), location(32, 24, 32, 35)).sample(T, location(32, 37, 32, 45));
        lambda.setAlias("lambda");
        lambda.setLocation(location(32, 15, 32, 20));

        ArrayVariable<IntVariable> arrivals = Variable.arrayVariable(location(35, 29, 35, 31), VariableType.IntVariable, T);
        arrivals.setAlias("arrivals");
        arrivals.setLocation(location(35, 11, 35, 18));

        parFor(intVariable(0, location(36, 19, 36, 19)), T, intVariable(1, location(36, 18, 36, 21)), true, location(36, 5, 36, 24), (t) -> { 
            t.setAlias("t");
            t.setLocation(location(36, 14, 36, 14));
            arrivals.put(t, poisson(lambda.get(t, location(37, 32, 37, 34)), location(37, 18, 37, 35)).sample(location(37, 37, 37, 44)), location(37, 13, 37, 44));

        });

        ArrayVariable<ArrayVariable<DoubleVariable>> Sales = Variable.arrayVariable(location(40, 34, 40, 48), VariableType.arrayType(VariableType.DoubleVariable), T, noProducts);
        Sales.setAlias("Sales");
        Sales.setLocation(location(40, 16, 40, 20));

        parFor(intVariable(0, location(42, 17, 42, 17)), T, intVariable(1, location(42, 16, 42, 19)), true, location(42, 5, 42, 22), (t) -> { 
            t.setAlias("t");
            t.setLocation(location(42, 14, 42, 14));
            ArrayVariable<DoubleVariable> weekly_sales = Variable.arrayVariable(location(47, 43, 47, 54), VariableType.DoubleVariable, noProducts);
            weekly_sales.setAlias("weekly_sales");
            weekly_sales.setLocation(location(47, 18, 47, 29));

            parFor(intVariable(0, location(48, 23, 48, 23)), noProducts, intVariable(1, location(48, 22, 48, 25)), true, location(48, 9, 48, 37), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(48, 18, 48, 18));
                weekly_sales.put(j, gaussian(exped.get(j, location(49, 45, 49, 47)).times(Avail.get(t, location(49, 54, 49, 56)).get(j, location(49, 57, 49, 59)), location(49, 48, 49, 48)).divide(denom, location(49, 61, 49, 61)).times(arrivals.get(t, location(49, 77, 49, 79)), location(49, 68, 49, 68)), doubleVariable(0.2, location(49, 82, 49, 84)), location(49, 31, 49, 85)).sample(location(49, 87, 49, 94)), location(49, 25, 49, 94));

            });

            Sales.put(t, weekly_sales, location(52, 14, 52, 31));

        });

        Sales.observe(ObsSales, location(55, 11, 55, 27));

        Variable<?>[] $variableNames = {noProducts, T, s, ObsSales, Avail, ut, exped, sum, denom, lambda, arrivals, Sales};
        String[] $constructorArgs = {"noProducts", "T", "s", "ObsSales", "Avail"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Vulcano2012basic", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel Vulcano2012basic(int noProducts, int T, int s, double[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n    // s is the normalization constant (market share), number between 0 and 1\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n    double denom = sum/s;   // this is the sum of utilities plus normalized by s outside options\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector, or just use some priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    double[][] Sales = new double[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities\n        // (does it matter if choice probabilities or individual choices?)\n        // let's try aggregate first\n\n        double[] weekly_sales = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            weekly_sales[j] = gaussian(exped[j]*Avail[t][j] /denom *arrivals[t], 0.2).sample();\n        }\n        // record sales for period t\n        Sales[t] = weekly_sales;\n                                }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
    }
}