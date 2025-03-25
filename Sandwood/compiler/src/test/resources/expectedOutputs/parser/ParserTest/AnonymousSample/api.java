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

public class AnonymousSample extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> obsAmounts1 = observeArray("obsAmounts1", VariableType.arrayType(VariableType.DoubleVariable), location(11, 30, 11, 49));
        ArrayVariable<DoubleVariable> obsAmounts2 = observeArray("obsAmounts2", VariableType.arrayType(VariableType.DoubleVariable), location(11, 52, 11, 71));

        IntVariable n = obsAmounts1.length(location(12, 25, 12, 30));
        n.setAlias("n");
        n.setLocation(location(12, 9, 12, 9));

        DoubleVariable priorSigma2 = gaussian(intVariable(10000, location(14, 35, 14, 39)), intVariable(900, location(14, 42, 14, 44)), location(14, 26, 14, 45)).sample(location(14, 47, 14, 54));
        priorSigma2.setAlias("priorSigma2");
        priorSigma2.setLocation(location(14, 12, 14, 22));

        DoubleVariable mean1 = gaussian(intVariable(2000, location(16, 29, 16, 32)), intVariable(10000, location(16, 35, 16, 39)), location(16, 20, 16, 40)).sample(location(16, 42, 16, 49));
        mean1.setAlias("mean1");
        mean1.setLocation(location(16, 12, 16, 16));

        DoubleVariable mean2 = gaussian(intVariable(2000, location(17, 29, 17, 32)), intVariable(10000, location(17, 35, 17, 39)), location(17, 20, 17, 40)).sample(location(17, 42, 17, 49));
        mean2.setAlias("mean2");
        mean2.setLocation(location(17, 12, 17, 16));

        ArrayVariable<DoubleVariable> amounts1 = Variable.arrayVariable(location(20, 35, 20, 37), VariableType.DoubleVariable, n);
        amounts1.setAlias("amounts1");
        amounts1.setLocation(location(20, 14, 20, 21));

        ArrayVariable<DoubleVariable> amounts2 = Variable.arrayVariable(location(21, 35, 21, 37), VariableType.DoubleVariable, n);
        amounts2.setAlias("amounts2");
        amounts2.setLocation(location(21, 14, 21, 21));

        parFor(intVariable(0, location(22, 18, 22, 18)), n, intVariable(1, location(22, 17, 22, 20)), true, location(22, 5, 22, 23), (i) -> {
            i.setAlias("i");
            i.setLocation(location(22, 13, 22, 13));
            amounts1.put(i, gaussian(mean1, priorSigma2, location(23, 23, 23, 50)).sample(location(23, 52, 23, 59)), location(23, 17, 23, 59));
            amounts2.put(i, amounts1.get(i, location(24, 31, 24, 33)).add(gaussian(mean2, priorSigma2, location(24, 37, 24, 64)).sample(location(24, 66, 24, 73)), location(24, 35, 24, 35)), location(24, 17, 24, 35));

        });

        amounts1.observe(obsAmounts1, location(27, 14, 27, 33));
        amounts2.observe(obsAmounts2, location(28, 14, 28, 33));

        Variable<?>[] $variableNames = {obsAmounts1, obsAmounts2, n, priorSigma2, mean1, mean2, amounts1, amounts2};
        String[] $constructorArgs = {"obsAmounts1", "obsAmounts2"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "AnonymousSample", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
             + " *\n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + " \n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model AnonymousSample(double[] obsAmounts1, double[] obsAmounts2) {\n"
             + "    int n = obsAmounts1.length;\n"
             + "\n"
             + "    double priorSigma2 = gaussian(10000, 900).sample();   // can always use inverseGamma(1.5, 100)\n"
             + "\n"
             + "    double mean1 = gaussian(2000, 10000).sample();\n"
             + "    double mean2 = gaussian(2000, 10000).sample();\n"
             + "\n"
             + "\n"
             + "    double[] amounts1 = new double[n];\n"
             + "    double[] amounts2 = new double[n];\n"
             + "    for(int i : [0..n)) {\n"
             + "        amounts1[i] = gaussian(mean1, priorSigma2).sample();\n"
             + "        amounts2[i] = amounts1[i] + gaussian(mean2, priorSigma2).sample();\n"
             + "    }\n"
             + "\n"
             + "    amounts1.observe(obsAmounts1);\n"
             + "    amounts2.observe(obsAmounts2);\n"
             + "}";
    }
}