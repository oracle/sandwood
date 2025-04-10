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

public class Vulcano2012basic extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noProducts = observeInt("noProducts", location(17, 24, 17, 37));
        IntVariable T = observeInt("T", location(17, 40, 17, 44));
        ArrayVariable<ArrayVariable<IntVariable>> ObsSales = observeArray("ObsSales", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(17, 47, 17, 62));
        ArrayVariable<ArrayVariable<IntVariable>> Avail = observeArray("Avail", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(17, 65, 17, 77));
        DoubleVariable r = observeDouble("r", location(17, 80, 17, 87));

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
            ArrayVariable<IntVariable> week_sales = ObsSales.get(t, location(41, 36, 41, 38));
            week_sales.setAlias("week_sales");
            week_sales.setLocation(location(41, 15, 41, 24));

            sales_sum.put(t, reduce(week_sales, intVariable(0, location(42, 43, 42, 43)), location(42, 24, 42, 73), (k, l) -> {
                k.setAlias("k");
                k.setLocation(location(42, 47, 42, 47));
                l.setAlias("l");
                l.setLocation(location(42, 50, 42, 50));
                return k.add(l, location(42, 67, 42, 67));
            }), location(42, 18, 42, 73));

        });

        ArrayVariable<ArrayVariable<IntVariable>> Sales = Variable.arrayVariable(location(45, 28, 45, 42), VariableType.arrayType(VariableType.IntVariable), T, noProducts);
        Sales.setAlias("Sales");
        Sales.setLocation(location(45, 13, 45, 17));

        parFor(intVariable(0, location(47, 17, 47, 17)), T, intVariable(1, location(47, 16, 47, 19)), true, location(47, 5, 47, 22), (t) -> {
            t.setAlias("t");
            t.setLocation(location(47, 14, 47, 14));
            ArrayVariable<DoubleVariable> weekly_rates = Variable.arrayVariable(location(50, 43, 50, 54), VariableType.DoubleVariable, noProducts);
            weekly_rates.setAlias("weekly_rates");
            weekly_rates.setLocation(location(50, 18, 50, 29));

            ArrayVariable<DoubleVariable> weekly_ut = Variable.arrayVariable(location(51, 40, 51, 51), VariableType.DoubleVariable, noProducts);
            weekly_ut.setAlias("weekly_ut");
            weekly_ut.setLocation(location(51, 18, 51, 26));

            parFor(intVariable(0, location(53, 23, 53, 23)), noProducts, intVariable(1, location(53, 22, 53, 25)), true, location(53, 9, 53, 37), (j) -> {
                j.setAlias("j");
                j.setLocation(location(53, 18, 53, 18));
                weekly_ut.put(j, expedNorm.get(j, location(54, 37, 54, 39)).times(Avail.get(t, location(54, 46, 54, 48)).get(j, location(54, 49, 54, 51)), location(54, 40, 54, 40)), location(54, 22, 54, 40));

            });

            DoubleVariable denom = reduce(weekly_ut, intVariable(0, location(56, 42, 56, 42)), location(56, 24, 56, 72), (k, l) -> {
                k.setAlias("k");
                k.setLocation(location(56, 46, 56, 46));
                l.setAlias("l");
                l.setLocation(location(56, 49, 56, 49));
                return k.add(l, location(56, 66, 56, 66));
            });
            denom.setAlias("denom");
            denom.setLocation(location(56, 16, 56, 20));

            parFor(intVariable(0, location(58, 23, 58, 23)), noProducts, intVariable(1, location(58, 22, 58, 25)), true, location(58, 9, 58, 37), (j) -> {
                j.setAlias("j");
                j.setLocation(location(58, 18, 58, 18));
                weekly_rates.put(j, weekly_ut.get(j, location(59, 40, 59, 42)).divide(denom, location(59, 43, 59, 43)), location(59, 25, 59, 43));

            });

            ArrayVariable<IntVariable> weekly_sales = multinomial(weekly_rates, sales_sum.get(t, location(62, 65, 62, 67)), location(62, 30, 62, 68)).sample(location(62, 70, 62, 77));
            weekly_sales.setAlias("weekly_sales");
            weekly_sales.setLocation(location(62, 15, 62, 26));

            Sales.put(t, weekly_sales, location(65, 14, 65, 31));

        });

        Sales.observe(ObsSales, location(69, 11, 69, 27));

        Variable<?>[] $variableNames = {noProducts, T, ObsSales, Avail, r, ut, exped, sum, expedNorm, sales_sum, Sales};
        String[] $constructorArgs = {"noProducts", "T", "ObsSales", "Avail", "r"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Vulcano2012basic", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "model Vulcano2012basic(int noProducts, int T, int[][] ObsSales, int[][] Avail, double r) {\n"
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
             + "        int[] week_sales = ObsSales[t];\n"
             + "        sales_sum[t] = reduce(week_sales, 0, (k, l) -> { return k + l; });\n"
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
             + "\n"
             + "}";
    }
}