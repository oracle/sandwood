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

public class Vulcano2012notNormalized extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noProducts = observeInt("noProducts", location(15, 32, 15, 45));
        IntVariable T = observeInt("T", location(15, 48, 15, 52));
        IntVariable s = observeInt("s", location(15, 55, 15, 59));
        ArrayVariable<ArrayVariable<IntVariable>> ObsSales = observeArray("ObsSales", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(15, 62, 15, 77));
        ArrayVariable<ArrayVariable<IntVariable>> Avail = observeArray("Avail", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(15, 80, 15, 92));

        ArrayVariable<DoubleVariable> ut = gaussian(intVariable(0, location(19, 28, 19, 28)), intVariable(10, location(19, 31, 19, 32)), location(19, 19, 19, 33)).sample(noProducts, location(19, 35, 19, 52));
        ut.setAlias("ut");
        ut.setLocation(location(19, 14, 19, 15));

        ArrayVariable<DoubleVariable> exped = Variable.arrayVariable(location(22, 32, 22, 43), VariableType.DoubleVariable, noProducts);
        exped.setAlias("exped");
        exped.setLocation(location(22, 14, 22, 18));

        parFor(intVariable(0, location(23, 18, 23, 18)), noProducts, intVariable(1, location(23, 17, 23, 20)), true, location(23, 5, 23, 32), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(23, 13, 23, 13));
            exped.put(j, exp(ut.get(j, location(24, 22, 24, 24)), location(24, 16, 24, 25)), location(24, 10, 24, 25));

        });

        ArrayVariable<DoubleVariable> lambda = gamma(intVariable(10, location(28, 30, 28, 31)), intVariable(10, location(28, 33, 28, 34)), location(28, 24, 28, 35)).sample(T, location(28, 37, 28, 45));
        lambda.setAlias("lambda");
        lambda.setLocation(location(28, 15, 28, 20));

        ArrayVariable<IntVariable> arrivals = Variable.arrayVariable(location(31, 29, 31, 31), VariableType.IntVariable, T);
        arrivals.setAlias("arrivals");
        arrivals.setLocation(location(31, 11, 31, 18));

        parFor(intVariable(0, location(32, 19, 32, 19)), T, intVariable(1, location(32, 18, 32, 21)), true, location(32, 5, 32, 24), (t) -> { 
            t.setAlias("t");
            t.setLocation(location(32, 14, 32, 14));
            arrivals.put(t, poisson(lambda.get(t, location(33, 32, 33, 34)), location(33, 18, 33, 35)).sample(location(33, 37, 33, 44)), location(33, 13, 33, 44));

        });

        ArrayVariable<ArrayVariable<IntVariable>> Sales = Variable.arrayVariable(location(36, 28, 36, 42), VariableType.arrayType(VariableType.IntVariable), T, noProducts);
        Sales.setAlias("Sales");
        Sales.setLocation(location(36, 13, 36, 17));

        parFor(intVariable(0, location(38, 17, 38, 17)), T, intVariable(1, location(38, 16, 38, 19)), true, location(38, 5, 38, 22), (t) -> { 
            t.setAlias("t");
            t.setLocation(location(38, 14, 38, 14));
            ArrayVariable<DoubleVariable> weekly_rates = Variable.arrayVariable(location(41, 43, 41, 56), VariableType.DoubleVariable, noProducts.add(intVariable(1, location(41, 55, 41, 55)), location(41, 54, 41, 54)));
            weekly_rates.setAlias("weekly_rates");
            weekly_rates.setLocation(location(41, 18, 41, 29));

            ArrayVariable<DoubleVariable> weekly_ut = Variable.arrayVariable(location(42, 40, 42, 53), VariableType.DoubleVariable, noProducts.add(intVariable(1, location(42, 52, 42, 52)), location(42, 51, 42, 51)));
            weekly_ut.setAlias("weekly_ut");
            weekly_ut.setLocation(location(42, 18, 42, 26));

            parFor(intVariable(0, location(44, 23, 44, 23)), noProducts, intVariable(1, location(44, 22, 44, 25)), true, location(44, 9, 44, 37), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(44, 18, 44, 18));
                weekly_ut.put(j, exped.get(j, location(45, 33, 45, 35)).times(Avail.get(t, location(45, 42, 45, 44)).get(j, location(45, 45, 45, 47)), location(45, 36, 45, 36)), location(45, 22, 45, 36));

            });

            weekly_ut.put(noProducts, doubleVariable(1.0, location(48, 33, 48, 35)), location(48, 18, 48, 35));
            DoubleVariable denom = reduce(weekly_ut, intVariable(0, location(49, 42, 49, 42)), location(49, 24, 49, 72), (k, l) ->  { 
                k.setAlias("k");
                k.setLocation(location(49, 46, 49, 46));
                l.setAlias("l");
                l.setLocation(location(49, 49, 49, 49));
                return k.add(l, location(49, 66, 49, 66));
            });
            denom.setAlias("denom");
            denom.setLocation(location(49, 16, 49, 20));

            parFor(intVariable(0, location(51, 23, 51, 23)), noProducts.add(intVariable(1, location(51, 24, 51, 36)), location(51, 24, 51, 36)), intVariable(1, location(51, 22, 51, 25)), true, location(51, 9, 51, 37), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(51, 18, 51, 18));
                weekly_rates.put(j, weekly_ut.get(j, location(52, 40, 52, 42)).divide(denom, location(52, 43, 52, 43)), location(52, 25, 52, 43));

            });

            ArrayVariable<IntVariable> weekly_sales = multinomial(weekly_rates, arrivals.get(t, location(55, 64, 55, 66)), location(55, 30, 55, 67)).sample(location(55, 69, 55, 76));
            weekly_sales.setAlias("weekly_sales");
            weekly_sales.setLocation(location(55, 15, 55, 26));

            ArrayVariable<IntVariable> observed_weekly_sales = Variable.arrayVariable(location(58, 46, 58, 57), VariableType.IntVariable, noProducts);
            observed_weekly_sales.setAlias("observed_weekly_sales");
            observed_weekly_sales.setLocation(location(58, 15, 58, 35));

            parFor(intVariable(0, location(59, 23, 59, 23)), noProducts, intVariable(1, location(59, 22, 59, 25)), true, location(59, 9, 59, 37), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(59, 18, 59, 18));
                observed_weekly_sales.put(j, weekly_sales.get(j, location(60, 52, 60, 54)), location(60, 34, 60, 54));

            });

            Sales.put(t, observed_weekly_sales, location(64, 14, 64, 40));

        });

        Sales.observe(ObsSales, location(68, 11, 68, 27));

        Variable<?>[] $variableNames = {noProducts, T, s, ObsSales, Avail, ut, exped, lambda, arrivals, Sales};
        String[] $constructorArgs = {"noProducts", "T", "s", "ObsSales", "Avail"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Vulcano2012notNormalized", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][noProducts];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
    }
}