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

public class ReductionTest1 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable T = observeInt("T", location(11, 29, 11, 33));
        IntVariable n_ac = observeInt("n_ac", location(11, 36, 11, 43));
        ArrayVariable<ArrayVariable<IntVariable>> ObsArr = observeArray("ObsArr", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(11, 46, 11, 59));
        ArrayVariable<ArrayVariable<DoubleVariable>> TimeFeat = observeArray("TimeFeat", VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), location(11, 62, 11, 80));

        IntVariable time_dim = TimeFeat.get(intVariable(0, location(12, 29, 12, 29)), location(12, 28, 12, 30)).length(location(12, 32, 12, 37));
        time_dim.setAlias("time_dim");
        time_dim.setLocation(location(12, 9, 12, 16));

        ArrayVariable<ArrayVariable<DoubleVariable>> time_coeff = Variable.arrayVariable(location(15, 39, 15, 54), VariableType.arrayType(VariableType.DoubleVariable), n_ac, time_dim);
        time_coeff.setAlias("time_coeff");
        time_coeff.setLocation(location(15, 16, 15, 25));

        ArrayVariable<ArrayVariable<DoubleVariable>> sum_t = Variable.arrayVariable(location(16, 34, 16, 42), VariableType.arrayType(VariableType.DoubleVariable), T, n_ac);
        sum_t.setAlias("sum_t");
        sum_t.setLocation(location(16, 16, 16, 20));

        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> time_impact = Variable.arrayVariable(location(17, 42, 17, 60), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), T, n_ac, time_dim);
        time_impact.setAlias("time_impact");
        time_impact.setLocation(location(17, 18, 17, 28));

        ArrayVariable<ArrayVariable<IntVariable>> arr = Variable.arrayVariable(location(18, 26, 18, 34), VariableType.arrayType(VariableType.IntVariable), T, n_ac);
        arr.setAlias("arr");
        arr.setLocation(location(18, 13, 18, 15));

        parFor(intVariable(0, location(20, 19, 20, 19)), n_ac, intVariable(1, location(20, 18, 20, 21)), true, location(20, 5, 20, 27), (i) -> {
            i.setAlias("i");
            i.setLocation(location(20, 14, 20, 14));
            time_coeff.put(i, gaussian(intVariable(0, location(21, 34, 21, 34)), intVariable(1, location(21, 36, 21, 36)), location(21, 25, 21, 37)).sample(time_dim, location(21, 39, 21, 54)), location(21, 19, 21, 54));
        });

        parFor(intVariable(0, location(23, 19, 23, 19)).add(intVariable(1, location(23, 18, 23, 21)), location(23, 18, 23, 21)), T, intVariable(1, location(23, 18, 23, 21)), true, location(23, 5, 23, 24), (t) -> {
            t.setAlias("t");
            t.setLocation(location(23, 14, 23, 14));
            parFor(intVariable(0, location(24, 23, 24, 23)), n_ac, intVariable(1, location(24, 22, 24, 25)), true, location(24, 9, 24, 31), (i) -> {
                i.setAlias("i");
                i.setLocation(location(24, 18, 24, 18));
                parFor(intVariable(0, location(25, 27, 25, 27)), time_dim, intVariable(1, location(25, 26, 25, 29)), true, location(25, 13, 25, 39), (j) -> {
                    j.setAlias("j");
                    j.setLocation(location(25, 22, 25, 22));
                    time_impact.get(t, location(26, 28, 26, 30)).get(i, location(26, 31, 26, 33)).put(j, TimeFeat.get(t, location(26, 48, 26, 50)).get(j, location(26, 51, 26, 53)).times(time_coeff.get(i, location(26, 65, 26, 67)).get(j, location(26, 68, 26, 70)), location(26, 54, 26, 54)), location(26, 34, 26, 54));
                });

                sum_t.get(t, location(28, 18, 28, 20)).put(i, reduce(time_impact.get(t, location(28, 45, 28, 47)).get(i, location(28, 48, 28, 50)), intVariable(0, location(28, 53, 28, 53)), location(28, 27, 28, 83), (x, y) -> {
                    x.setAlias("x");
                    x.setLocation(location(28, 57, 28, 57));
                    y.setAlias("y");
                    y.setLocation(location(28, 60, 28, 60));
                    return x.add(y, location(28, 77, 28, 77));
                }), location(28, 21, 28, 83));
                arr.get(t, location(29, 16, 29, 18)).put(i, poisson(sum_t.get(t, location(29, 38, 29, 40)).get(i, location(29, 41, 29, 43)), location(29, 25, 29, 44)).sample(location(29, 46, 29, 53)), location(29, 19, 29, 53));

            });


        });

        arr.observe(ObsArr, location(32, 9, 32, 23));

        Variable<?>[] $variableNames = {T, n_ac, ObsArr, TimeFeat, time_dim, time_coeff, sum_t, time_impact, arr};
        String[] $constructorArgs = {"T", "n_ac", "ObsArr", "TimeFeat"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ReductionTest1", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model ReductionTest1(int T, int n_ac, int[][] ObsArr, double[][] TimeFeat) {\n"
             + "    int time_dim = TimeFeat[0].length; //length of the feature vector\n"
             + "\n"
             + "\n"
             + "    double[][] time_coeff = new double[n_ac][time_dim];\n"
             + "    double[][] sum_t = new double[T][n_ac];\n"
             + "    double[][][] time_impact = new double[T][n_ac][time_dim];\n"
             + "    int[][] arr = new int[T][n_ac];\n"
             + "    \n"
             + "    for (int i : [0..n_ac))\n"
             + "        time_coeff[i] = gaussian(0,1).sample(time_dim);\n"
             + "\n"
             + "    for (int t : (0..T)) {\n"
             + "        for (int i : [0..n_ac)){\n"
             + "            for (int j : [0..time_dim))\n"
             + "                time_impact[t][i][j] = TimeFeat[t][j]*time_coeff[i][j];\n"
             + "            //calculate sum\n"
             + "            sum_t[t][i] = reduce(time_impact[t][i], 0, (x, y) -> { return x + y; });\n"
             + "            arr[t][i] = poisson(sum_t[t][i]).sample();\n"
             + "        }\n"
             + "    }\n"
             + "    arr.observe(ObsArr);\n"
             + "}";
    }
}