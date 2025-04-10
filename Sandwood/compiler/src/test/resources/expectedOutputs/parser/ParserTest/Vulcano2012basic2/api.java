package org.sandwood.compiler.tests.parser;

import static org.sandwood.compiler.dataflowGraph.Sandwood.*;
import static org.sandwood.compiler.dataflowGraph.Math.*;
import static org.sandwood.compiler.dataflowGraph.Number.*;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.*;

import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
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

public class Vulcano2012basic2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noProducts = observeInt("noProducts", location(17, 25, 17, 38));
        IntVariable T = observeInt("T", location(17, 41, 17, 45));
        ArrayVariable<ArrayVariable<IntVariable>> ObsSales = observeArray("ObsSales", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(17, 48, 17, 63));
        ArrayVariable<ArrayVariable<IntVariable>> Avail = observeArray("Avail", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(17, 66, 17, 78));
        DoubleVariable r = observeDouble("r", location(17, 81, 17, 88));

        ArrayVariable<DoubleVariable> ut = Variable.arrayVariable(location(19, 29, 19, 40), VariableType.DoubleVariable, noProducts);
        ut.setAlias("ut");
        ut.setLocation(location(19, 14, 19, 15));

        ut.put(intVariable(0, location(20, 8, 20, 8)), doubleVariable(0.0, location(20, 13, 20, 15)), location(20, 7, 20, 15));
        parFor(intVariable(1, location(21, 18, 21, 18)), noProducts, intVariable(1, location(21, 17, 21, 20)), true, location(21, 5, 21, 32), (j) -> {
            j.setAlias("j");
            j.setLocation(location(21, 13, 21, 13));
            ut.put(j, gaussian(intVariable(0, location(22, 26, 22, 26)), intVariable(2, location(22, 29, 22, 29)), location(22, 17, 22, 30)).sample(location(22, 32, 22, 39)), location(22, 11, 22, 39));

        });

        ArrayVariable<DoubleVariable> exped = Variable.arrayVariable(location(26, 32, 26, 43), VariableType.DoubleVariable, noProducts);
        exped.setAlias("exped");
        exped.setLocation(location(26, 14, 26, 18));

        parFor(intVariable(0, location(27, 18, 27, 18)), noProducts, intVariable(1, location(27, 17, 27, 20)), true, location(27, 5, 27, 32), (j) -> {
            j.setAlias("j");
            j.setLocation(location(27, 13, 27, 13));
            exped.put(j, exp(ut.get(j, location(28, 26, 28, 28)), location(28, 20, 28, 29)), location(28, 14, 28, 29));

        });

        DoubleVariable sum = reduce(exped, intVariable(0, location(31, 32, 31, 32)), location(31, 18, 31, 62), (k, l) -> {
            k.setAlias("k");
            k.setLocation(location(31, 36, 31, 36));
            l.setAlias("l");
            l.setLocation(location(31, 39, 31, 39));
            return k.add(l, location(31, 56, 31, 56));
        });
        sum.setAlias("sum");
        sum.setLocation(location(31, 12, 31, 14));

        ArrayVariable<DoubleVariable> expedNorm = Variable.arrayVariable(location(34, 36, 34, 47), VariableType.DoubleVariable, noProducts);
        expedNorm.setAlias("expedNorm");
        expedNorm.setLocation(location(34, 14, 34, 22));

        parFor(intVariable(0, location(35, 18, 35, 18)), noProducts, intVariable(1, location(35, 17, 35, 20)), true, location(35, 5, 35, 32), (j) -> {
            j.setAlias("j");
            j.setLocation(location(35, 13, 35, 13));
            expedNorm.put(j, exped.get(j, location(36, 29, 36, 31)).divide((r.times(sum, location(36, 35, 36, 35))), location(36, 32, 36, 32)), location(36, 18, 36, 32));

        });

        ArrayVariable<IntVariable> sales_sum = Variable.arrayVariable(location(39, 30, 39, 32), VariableType.IntVariable, T);
        sales_sum.setAlias("sales_sum");
        sales_sum.setLocation(location(39, 11, 39, 19));

        parFor(intVariable(0, location(40, 19, 40, 19)), T, intVariable(1, location(40, 18, 40, 21)), true, location(40, 5, 40, 24), (t) -> {
            t.setAlias("t");
            t.setLocation(location(40, 14, 40, 14));
            sales_sum.put(t, poisson(doubleVariable(0.5, location(41, 32, 41, 34)), location(41, 24, 41, 35)).sample(location(41, 37, 41, 44)), location(41, 18, 41, 44));

        });

        ArrayVariable<ArrayVariable<IntVariable>> Sales = Variable.arrayVariable(location(44, 28, 44, 42), VariableType.arrayType(VariableType.IntVariable), T, noProducts);
        Sales.setAlias("Sales");
        Sales.setLocation(location(44, 13, 44, 17));

        parFor(intVariable(0, location(46, 17, 46, 17)), T, intVariable(1, location(46, 16, 46, 19)), true, location(46, 5, 46, 22), (t) -> {
            t.setAlias("t");
            t.setLocation(location(46, 14, 46, 14));
            ArrayVariable<DoubleVariable> weekly_rates = Variable.arrayVariable(location(49, 43, 49, 54), VariableType.DoubleVariable, noProducts);
            weekly_rates.setAlias("weekly_rates");
            weekly_rates.setLocation(location(49, 18, 49, 29));

            ArrayVariable<DoubleVariable> weekly_ut = Variable.arrayVariable(location(50, 40, 50, 51), VariableType.DoubleVariable, noProducts);
            weekly_ut.setAlias("weekly_ut");
            weekly_ut.setLocation(location(50, 18, 50, 26));

            parFor(intVariable(0, location(52, 23, 52, 23)), noProducts, intVariable(1, location(52, 22, 52, 25)), true, location(52, 9, 52, 37), (j) -> {
                j.setAlias("j");
                j.setLocation(location(52, 18, 52, 18));
                weekly_ut.put(j, expedNorm.get(j, location(53, 37, 53, 39)).times(Avail.get(t, location(53, 46, 53, 48)).get(j, location(53, 49, 53, 51)), location(53, 40, 53, 40)), location(53, 22, 53, 40));

            });

            DoubleVariable denom = reduce(weekly_ut, intVariable(0, location(55, 42, 55, 42)), location(55, 24, 55, 72), (k, l) -> {
                k.setAlias("k");
                k.setLocation(location(55, 46, 55, 46));
                l.setAlias("l");
                l.setLocation(location(55, 49, 55, 49));
                return k.add(l, location(55, 66, 55, 66));
            });
            denom.setAlias("denom");
            denom.setLocation(location(55, 16, 55, 20));

            parFor(intVariable(0, location(57, 23, 57, 23)), noProducts, intVariable(1, location(57, 22, 57, 25)), true, location(57, 9, 57, 37), (j) -> {
                j.setAlias("j");
                j.setLocation(location(57, 18, 57, 18));
                weekly_rates.put(j, weekly_ut.get(j, location(58, 40, 58, 42)).divide(denom, location(58, 43, 58, 43)), location(58, 25, 58, 43));

            });

            ArrayVariable<IntVariable> weekly_sales = multinomial(weekly_rates, sales_sum.get(t, location(61, 65, 61, 67)), location(61, 30, 61, 68)).sample(location(61, 70, 61, 77));
            weekly_sales.setAlias("weekly_sales");
            weekly_sales.setLocation(location(61, 15, 61, 26));

            Sales.put(t, weekly_sales, location(64, 14, 64, 31));

        });

        Sales.observe(ObsSales, location(68, 11, 68, 27));

        Variable<?>[] $variableNames = {noProducts, T, ObsSales, Avail, r, ut, exped, sum, expedNorm, sales_sum, Sales};
        String[] $constructorArgs = {"noProducts", "T", "ObsSales", "Avail", "r"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Vulcano2012basic2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
             + " * \n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + " \n"
             + "/*\n"
             + " * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n"
             + " * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n"
             + " * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n"
             + " */\n"
             + "\n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "model Vulcano2012basic2(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
             + "    // draw utilities\n"
             + "    double[] ut = new double[noProducts];\n"
             + "    ut[0] = 0.0;  //the first one is fixed so that we can normalize the sum to be equal 1/r\n"
             + "    for(int j : [1..noProducts)) {\n"
             + "        ut[j] = gaussian(0, 2).sample();\n"
             + "    }\n"
             + "\n"
             + "    //exponentiate right here (in the non-basic models move to the for loop)\n"
             + "    double[] exped = new double[noProducts];\n"
             + "    for(int j : [0..noProducts)) {\n"
             + "        exped[j] = exp(ut[j]);\n"
             + "    }\n"
             + "\n"
             + "    double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
             + "\n"
             + "    //now normalize\n"
             + "    double[] expedNorm = new double[noProducts];\n"
             + "    for(int j : [0..noProducts)) {\n"
             + "        expedNorm[j] = exped[j]/(r*sum);\n"
             + "    }\n"
             + "\n"
             + "    int[] sales_sum = new int[T];\n"
             + "    for (int t : [0..T)){\n"
             + "        sales_sum[t] = poisson(0.5).sample();\n"
             + "    }\n"
             + "\n"
             + "    int[][] Sales = new int[T][noProducts];\n"
             + "\n"
             + "    for (int t:[0..T)){\n"
             + "        // for each period t calculate choice probabilities and sales\n"
             + "\n"
             + "        double[] weekly_rates = new double[noProducts];\n"
             + "        double[] weekly_ut = new double[noProducts];\n"
             + "\n"
             + "        for (int j : [0..noProducts)) {\n"
             + "            weekly_ut[j] = expedNorm[j]*Avail[t][j] ;\n"
             + "        }\n"
             + "        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n"
             + "\n"
             + "        for (int j : [0..noProducts)) {\n"
             + "            weekly_rates[j] = weekly_ut[j]/denom ;\n"
             + "        }\n"
             + "\n"
             + "        int[] weekly_sales = multinomial(weekly_rates, sales_sum[t]).sample();\n"
             + "\n"
             + "        // record sales for period t\n"
             + "        Sales[t] = weekly_sales;\n"
             + "\n"
             + "                                }\n"
             + "    // assert that generated sales match observed sales\n"
             + "    Sales.observe(ObsSales);\n"
             + "}";
    }
}