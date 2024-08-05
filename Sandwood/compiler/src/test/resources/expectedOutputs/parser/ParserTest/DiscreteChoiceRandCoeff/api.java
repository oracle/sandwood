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

public class DiscreteChoiceRandCoeff extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noProducts = observeInt("noProducts", location(12, 31, 12, 44));
        IntVariable noObs = observeInt("noObs", location(12, 47, 12, 55));
        ArrayVariable<IntVariable> ObsChoices = observeArray("ObsChoices", VariableType.arrayType(VariableType.IntVariable), location(12, 58, 12, 73));
        ArrayVariable<ArrayVariable<IntVariable>> Prices = observeArray("Prices", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(12, 76, 12, 89));

        ArrayVariable<DoubleVariable> ut = gaussian(intVariable(0, location(16, 28, 16, 28)), intVariable(10, location(16, 31, 16, 32)), location(16, 19, 16, 33)).sample(noProducts, location(16, 35, 16, 52));
        ut.setAlias("ut");
        ut.setLocation(location(16, 14, 16, 15));

        DoubleVariable b = gaussian(intVariable(0, location(20, 25, 20, 25)), intVariable(10, location(20, 27, 20, 28)), location(20, 16, 20, 29)).sample(location(20, 31, 20, 38));
        b.setAlias("b");
        b.setLocation(location(20, 12, 20, 12));

        DoubleVariable sigma = inverseGamma(intVariable(2, location(21, 34, 21, 34)), intVariable(2, location(21, 36, 21, 36)), location(21, 21, 21, 37)).sample(location(21, 39, 21, 46));
        sigma.setAlias("sigma");
        sigma.setLocation(location(21, 12, 21, 16));

        ArrayVariable<DoubleVariable> beta = gaussian(b, sigma, location(23, 21, 23, 38)).sample(noObs, location(23, 40, 23, 52));
        beta.setAlias("beta");
        beta.setLocation(location(23, 14, 23, 17));

        ArrayVariable<IntVariable> choices = Variable.arrayVariable(location(25, 28, 25, 34), VariableType.IntVariable, noObs);
        choices.setAlias("choices");
        choices.setLocation(location(25, 11, 25, 17));

        parFor(intVariable(0, location(27, 17, 27, 17)), noObs, intVariable(1, location(27, 16, 27, 19)), true, location(27, 5, 27, 26), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(27, 14, 27, 14));
            ArrayVariable<DoubleVariable> exped = Variable.arrayVariable(location(30, 36, 30, 47), VariableType.DoubleVariable, noProducts);
            exped.setAlias("exped");
            exped.setLocation(location(30, 18, 30, 22));

            parFor(intVariable(0, location(31, 22, 31, 22)), noProducts, intVariable(1, location(31, 21, 31, 24)), true, location(31, 9, 31, 36), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(31, 17, 31, 17));
                exped.put(j, exp(ut.get(j, location(32, 30, 32, 32)).subtract(beta.get(i, location(32, 39, 32, 41)).times(Prices.get(i, location(32, 49, 32, 51)).get(j, location(32, 52, 32, 54)), location(32, 42, 32, 42)), location(32, 33, 32, 33)), location(32, 24, 32, 55)), location(32, 18, 32, 55));

            });

            DoubleVariable sum = reduce(exped, intVariable(0, location(34, 36, 34, 36)), location(34, 22, 34, 66), (k, l) ->  { 
                k.setAlias("k");
                k.setLocation(location(34, 40, 34, 40));
                l.setAlias("l");
                l.setLocation(location(34, 43, 34, 43));
                return k.add(l, location(34, 60, 34, 60));
            });
            sum.setAlias("sum");
            sum.setLocation(location(34, 16, 34, 18));

            ArrayVariable<DoubleVariable> prob = Variable.arrayVariable(location(35, 42, 35, 53), VariableType.DoubleVariable, noProducts);
            prob.setAlias("prob");
            prob.setLocation(location(35, 25, 35, 28));
            prob.setPublic();

            parFor(intVariable(0, location(36, 23, 36, 23)), noProducts, intVariable(1, location(36, 22, 36, 25)), true, location(36, 9, 36, 37), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(36, 18, 36, 18));
                prob.put(j, exped.get(j, location(37, 28, 37, 30)).divide(sum, location(37, 32, 37, 32)), location(37, 17, 37, 32));

            });

            choices.put(i, categorical(prob, location(40, 22, 40, 38)).sample(location(40, 40, 40, 47)), location(40, 16, 40, 47));

        });

        choices.observe(ObsChoices, location(44, 13, 44, 31));

        Variable<?>[] $variableNames = {noProducts, noObs, ObsChoices, Prices, ut, b, sigma, beta, choices};
        String[] $constructorArgs = {"noProducts", "noObs", "ObsChoices", "Prices"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DiscreteChoiceRandCoeff", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\nmodel DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n    //can we set the first element to 0? like ut[0] <~ 0\n\n    //priors for distribution of beta\n    double b = gaussian(0,10).sample();\n    double sigma =  inverseGamma(2,2).sample();\n\n    double[] beta = gaussian(b, sigma).sample(noObs);\n\n    int[] choices = new int[noObs];\n\n    for (int i:[0..noObs)){\n        // calculate choice probabilities for consumer i\n\n        double[] exped = new double[noProducts];\n        for(int j : [0..noProducts)) {\n            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n            }\n        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n        public double[] prob = new double[noProducts];\n        for (int j : [0..noProducts)) {\n            prob[j] = exped[j] / sum;\n        }\n        // emit choices of consumer i\n        choices[i] = categorical(prob).sample();\n                                }\n\n    // assert that generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
    }
}