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

public class Vulcano2012basicDG extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<ArrayVariable<IntVariable>> ObsSales = observeArray("ObsSales", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(15, 26, 15, 41));
        ArrayVariable<ArrayVariable<BooleanVariable>> avail = observeArray("avail", VariableType.arrayType(VariableType.arrayType(VariableType.BooleanVariable)), location(15, 44, 15, 60));

        DoubleVariable r = doubleVariable(0.3, location(18, 15, 18, 17));
        r.setAlias("r");
        r.setLocation(location(18, 11, 18, 11));

        IntVariable numTimeSteps = avail.length(location(20, 29, 20, 34));
        numTimeSteps.setAlias("numTimeSteps");
        numTimeSteps.setLocation(location(20, 8, 20, 19));

        BooleanVariable guard$1 = numTimeSteps.greaterThan(intVariable(0, location(21, 22, 21, 22)), location(21, 20, 21, 20));
        ifElse(guard$1, () -> {
            IntVariable numProducts = avail.get(intVariable(0, location(22, 31, 22, 31)), location(22, 30, 22, 32)).length(location(22, 34, 22, 39));
            numProducts.setAlias("numProducts");
            numProducts.setLocation(location(22, 11, 22, 21));

            ArrayVariable<DoubleVariable> ut = gaussian(intVariable(0, location(25, 30, 25, 30)), intVariable(1, location(25, 33, 25, 33)), location(25, 21, 25, 34)).sample(numProducts, location(25, 36, 25, 54));
            ut.setAlias("ut");
            ut.setLocation(location(25, 16, 25, 17));

            ArrayVariable<DoubleVariable> exped = Variable.arrayVariable(location(28, 34, 28, 46), VariableType.DoubleVariable, numProducts);
            exped.setAlias("exped");
            exped.setLocation(location(28, 16, 28, 20));

            parFor(intVariable(0, location(29, 20, 29, 20)), numProducts, intVariable(1, location(29, 19, 29, 22)), true, location(29, 7, 29, 35), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(29, 15, 29, 15));
                exped.put(j, exp(ut.get(j, location(30, 27, 30, 29)), location(30, 21, 30, 30)), location(30, 15, 30, 30));
            });

            IntVariable numChoices = numProducts.add(intVariable(1, location(33, 38, 33, 38)), location(33, 36, 33, 36));
            numChoices.setAlias("numChoices");
            numChoices.setLocation(location(33, 11, 33, 20));

            ArrayVariable<DoubleVariable> expedNorm = Variable.arrayVariable(location(36, 38, 36, 50), VariableType.DoubleVariable, numProducts);
            expedNorm.setAlias("expedNorm");
            expedNorm.setLocation(location(36, 16, 36, 24));

            DoubleVariable sum = reduce(exped, intVariable(0, location(37, 34, 37, 34)), location(37, 20, 37, 64), (k, l) ->  { 
                k.setAlias("k");
                k.setLocation(location(37, 38, 37, 38));
                l.setAlias("l");
                l.setLocation(location(37, 41, 37, 41));
                return k.add(l, location(37, 58, 37, 58));
            });
            sum.setAlias("sum");
            sum.setLocation(location(37, 14, 37, 16));

            parFor(intVariable(0, location(38, 20, 38, 20)), numProducts, intVariable(1, location(38, 19, 38, 22)), true, location(38, 7, 38, 35), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(38, 15, 38, 15));
                expedNorm.put(j, exped.get(j, location(39, 30, 39, 32)).divide((r.times(sum, location(39, 36, 39, 36))), location(39, 33, 39, 33)), location(39, 19, 39, 33));
            });

            ArrayVariable<ArrayVariable<IntVariable>> sales = Variable.arrayVariable(location(41, 30, 41, 56), VariableType.arrayType(VariableType.IntVariable), numTimeSteps, numProducts);
            sales.setAlias("sales");
            sales.setLocation(location(41, 15, 41, 19));

            parFor(intVariable(0, location(43, 19, 43, 19)), numTimeSteps, intVariable(1, location(43, 18, 43, 21)), true, location(43, 7, 43, 35), (t) -> { 
                t.setAlias("t");
                t.setLocation(location(43, 16, 43, 16));
                IntVariable numSales = reduce(ObsSales.get(t, location(45, 40, 45, 42)), intVariable(0, location(45, 45, 45, 45)), location(45, 25, 45, 75), (k, l) ->  { 
                    k.setAlias("k");
                    k.setLocation(location(45, 49, 45, 49));
                    l.setAlias("l");
                    l.setLocation(location(45, 52, 45, 52));
                    return k.add(l, location(45, 69, 45, 69));
                });
                numSales.setAlias("numSales");
                numSales.setLocation(location(45, 14, 45, 21));

                DoubleVariable lambda = gamma(intVariable(10, location(49, 39, 49, 40)), intVariable(10, location(49, 42, 49, 43)), location(49, 33, 49, 44)).sample(location(49, 46, 49, 53));
                lambda.setAlias("lambda");
                lambda.setLocation(location(49, 24, 49, 29));
                lambda.setPublic();

                IntVariable arrivals = numSales.add(poisson(lambda, location(50, 43, 50, 57)).sample(location(50, 59, 50, 66)), location(50, 41, 50, 41));
                arrivals.setAlias("arrivals");
                arrivals.setLocation(location(50, 21, 50, 28));
                arrivals.setPublic();

                ArrayVariable<DoubleVariable> weekly_rates = Variable.arrayVariable(location(53, 44, 53, 55), VariableType.DoubleVariable, numChoices);
                weekly_rates.setAlias("weekly_rates");
                weekly_rates.setLocation(location(53, 19, 53, 30));

                ArrayVariable<DoubleVariable> weekly_ut = Variable.arrayVariable(location(54, 41, 54, 52), VariableType.DoubleVariable, numChoices);
                weekly_ut.setAlias("weekly_ut");
                weekly_ut.setLocation(location(54, 19, 54, 27));

                parFor(intVariable(0, location(56, 23, 56, 23)), numProducts, intVariable(1, location(56, 22, 56, 25)), true, location(56, 10, 56, 38), (j) -> { 
                    j.setAlias("j");
                    j.setLocation(location(56, 18, 56, 18));
                    BooleanVariable guard$2 = avail.get(t, location(57, 21, 57, 23)).get(j, location(57, 24, 57, 26));
                    ifElse(guard$2, () -> {
                        weekly_ut.put(j, expedNorm.get(j, location(58, 40, 58, 42)), location(58, 25, 58, 42));
                    }, () -> {
                        weekly_ut.put(j, doubleVariable(0.0, location(60, 31, 60, 33)), location(60, 25, 60, 33));
                    });

                });

                weekly_ut.put(numProducts, doubleVariable(1.0, location(65, 35, 65, 37)), location(65, 19, 65, 37));
                DoubleVariable denom = reduce(weekly_ut, intVariable(0, location(67, 43, 67, 43)), location(67, 25, 67, 73), (k, l) ->  { 
                    k.setAlias("k");
                    k.setLocation(location(67, 47, 67, 47));
                    l.setAlias("l");
                    l.setLocation(location(67, 50, 67, 50));
                    return k.add(l, location(67, 67, 67, 67));
                });
                denom.setAlias("denom");
                denom.setLocation(location(67, 17, 67, 21));

                parFor(intVariable(0, location(69, 23, 69, 23)), numProducts.add(intVariable(1, location(69, 24, 69, 37)), location(69, 24, 69, 37)), intVariable(1, location(69, 22, 69, 25)), true, location(69, 10, 69, 38), (j) -> { 
                    j.setAlias("j");
                    j.setLocation(location(69, 18, 69, 18));
                    weekly_rates.put(j, weekly_ut.get(j, location(70, 40, 70, 42)).divide(denom, location(70, 43, 70, 43)), location(70, 25, 70, 43));
                });

                ArrayVariable<IntVariable> weekly_sales = multinomial(weekly_rates, arrivals, location(72, 31, 72, 65)).sample(location(72, 67, 72, 74));
                weekly_sales.setAlias("weekly_sales");
                weekly_sales.setLocation(location(72, 16, 72, 27));

                ArrayVariable<IntVariable> observed_weekly_sales = sales.get(t, location(75, 45, 75, 47));
                observed_weekly_sales.setAlias("observed_weekly_sales");
                observed_weekly_sales.setLocation(location(75, 16, 75, 36));

                parFor(intVariable(0, location(76, 24, 76, 24)), numProducts, intVariable(1, location(76, 23, 76, 26)), true, location(76, 10, 76, 39), (j) -> { 
                    j.setAlias("j");
                    j.setLocation(location(76, 19, 76, 19));
                    observed_weekly_sales.put(j, weekly_sales.get(j, location(77, 52, 77, 54)), location(77, 34, 77, 54));
                });


            });

            sales.observe(ObsSales, location(80, 13, 80, 29));

        }, () -> {
        });

        Variable<?>[] $variableNames = {ObsSales, avail, r, numTimeSteps};
        String[] $constructorArgs = {"ObsSales", "avail"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Vulcano2012basicDG", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012basicDG(int[][] ObsSales, boolean[][] avail) {\n   // avail is the availability matrix, numTimeSteps-by-numProducts\n   // r is the normalization constant here, number between 0 and 1. \"Relative appeal of the outside option\"\n   double r = 0.3;\n    \n   int numTimeSteps = avail.length;\n   if(numTimeSteps > 0) {\n      int numProducts = avail[0].length;\n\n      // draw utilities\n      double[] ut = gaussian(0, 1).sample(numProducts);\n\n      //exponentiate right here (in the non-basic models move to the for loop)\n      double[] exped = new double[numProducts];\n      for(int j : [0..numProducts))\n         exped[j] = exp(ut[j]);\n\n      //Choices includes the choice to not buy anything.\n      int numChoices = numProducts + 1;\n\n      //now normalize\n      double[] expedNorm = new double[numProducts];\n      double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n      for(int j : [0..numProducts))\n         expedNorm[j] = exped[j]/(r*sum);\n\n      int[][] sales = new int[numTimeSteps][numProducts];\n\n      for (int t:[0..numTimeSteps)){\n         // Calculate the number of purchases made.\n         int numSales = reduce(ObsSales[t], 0, (k, l) -> { return k + l; });\n\n         // prior for the distribution of lambda for arrivals. These can be \n         // supplied as a vector if RGBU has some estimates, or just use some priors.\n         public double lambda = gamma(10,10).sample();\n         public int arrivals = numSales + poisson(lambda).sample();\n\n         // for each period t calculate choice probabilities and sales\n         double[] weekly_rates = new double[numChoices];\n         double[] weekly_ut = new double[numChoices];\n\n         for(int j : [0..numProducts)) {\n            if(avail[t][j])\n               weekly_ut[j] = expedNorm[j];\n            else\n               weekly_ut[j] = 0.0;\n         }\n         // Moved v_0 to the end of the array to keep indexing consistent everywhere else in \n         // the model and delayed the assignment of the value 1 to here. None of this is a \n         // sandwood requirement, I just thought it made the model eaiser to follow.\n         weekly_ut[numProducts] = 1.0;\n\n         double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n         for(int j : [0..numProducts]) \n            weekly_rates[j] = weekly_ut[j]/denom ;\n\n         int[] weekly_sales = multinomial(weekly_rates, arrivals).sample();\n\n         //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n         int[] observed_weekly_sales = sales[t];\n         for (int j : [0..numProducts))\n            observed_weekly_sales[j] = weekly_sales[j] ;\n      }\n      // assert that generated sales match observed sales\n      sales.observe(ObsSales);\n   }\n}";
    }
}