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

public class DiscreteChoice extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noProducts = observeInt("noProducts", location(11, 29, 11, 42));
        IntVariable noObs = observeInt("noObs", location(11, 45, 11, 53));
        ArrayVariable<IntVariable> ObsChoices = observeArray("ObsChoices", VariableType.arrayType(VariableType.IntVariable), location(11, 56, 11, 71));

        ArrayVariable<DoubleVariable> ut = Variable.arrayVariable(location(15, 29, 15, 40), VariableType.DoubleVariable, noProducts);
        ut.setAlias("ut");
        ut.setLocation(location(15, 14, 15, 15));

        ut.put(intVariable(0, location(16, 8, 16, 8)), doubleVariable(0.0, location(16, 13, 16, 15)), location(16, 7, 16, 15));
        parFor(intVariable(1, location(17, 15, 17, 15)), noProducts, intVariable(1, location(17, 32, 17, 34)), true, location(17, 5, 17, 35), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(17, 13, 17, 13));
            ut.put(i, gaussian(intVariable(0, location(18, 25, 18, 25)), intVariable(10, location(18, 28, 18, 29)), location(18, 16, 18, 30)).sample(location(18, 32, 18, 39)), location(18, 11, 18, 39));

        });

        ArrayVariable<DoubleVariable> exped = Variable.arrayVariable(location(22, 32, 22, 43), VariableType.DoubleVariable, noProducts);
        exped.setAlias("exped");
        exped.setLocation(location(22, 14, 22, 18));

        parFor(intVariable(0, location(23, 18, 23, 18)), noProducts, intVariable(1, location(23, 17, 23, 20)), true, location(23, 5, 23, 32), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(23, 13, 23, 13));
            exped.put(i, exp(ut.get(i, location(24, 26, 24, 28)), location(24, 20, 24, 29)), location(24, 14, 24, 29));

        });

        DoubleVariable sum = reduce(exped, intVariable(0, location(26, 32, 26, 32)), location(26, 18, 26, 62), (i, j) ->  { 
            i.setAlias("i");
            i.setLocation(location(26, 36, 26, 36));
            j.setAlias("j");
            j.setLocation(location(26, 39, 26, 39));
            return i.add(j, location(26, 56, 26, 56));
        });
        sum.setAlias("sum");
        sum.setLocation(location(26, 12, 26, 14));

        ArrayVariable<DoubleVariable> prob = Variable.arrayVariable(location(27, 31, 27, 42), VariableType.DoubleVariable, noProducts);
        prob.setAlias("prob");
        prob.setLocation(location(27, 14, 27, 17));

        parFor(intVariable(0, location(28, 19, 28, 19)), noProducts, intVariable(1, location(28, 18, 28, 21)), true, location(28, 5, 28, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(28, 14, 28, 14));
            prob.put(i, exped.get(i, location(29, 24, 29, 26)).divide(sum, location(29, 28, 29, 28)), location(29, 13, 29, 28));

        });

        ArrayVariable<IntVariable> choices = categorical(prob, location(32, 21, 32, 37)).sample(noObs, location(32, 39, 32, 51));
        choices.setAlias("choices");
        choices.setLocation(location(32, 11, 32, 17));

        choices.observe(ObsChoices, location(35, 13, 35, 31));

        Variable<?>[] $variableNames = {noProducts, noObs, ObsChoices, ut, exped, sum, prob, choices};
        String[] $constructorArgs = {"noProducts", "noObs", "ObsChoices"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DiscreteChoice", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = new double[noProducts];\n    ut[0] = 0.0;\n    for(int i=1; i<noProducts; i++) {\n        ut[i]= gaussian(0, 10).sample();\n    }\n\n    // calculate choice probabilities\n    double[] exped = new double[noProducts];\n    for(int i : [0..noProducts)) {\n        exped[i] = exp(ut[i]);\n    }\n    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n    double[] prob = new double[noProducts];\n    for (int i : [0..noProducts)) {\n        prob[i] = exped[i] / sum;\n    }\n    // draw consumer choices according to the calculated probabilities\n    int[] choices = categorical(prob).sample(noObs);\n\n    // assert generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
    }
}