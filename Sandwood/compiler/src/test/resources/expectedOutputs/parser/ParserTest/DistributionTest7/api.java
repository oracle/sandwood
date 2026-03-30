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

public class DistributionTest7 extends GeneratedAPIBuilder {
    //Helper classes for if else statements.
    private static class $IfElseMods1 {
        IntVariable result;
    }

    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        DoubleVariable observedData = observeDouble("observedData", location(11, 32, 11, 50));

        ArrayVariable<DoubleVariable> bias = ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> bias$ = Variable.arrayVariable(location(13, 21, 13, 35), VariableType.DoubleVariable, 3);
                bias$.put(intVariable(0, location(13, 21, 13, 21)), doubleVariable(0.2, location(13, 22, 13, 24)), location(13, 21, 13, 24));
                bias$.put(intVariable(1, location(13, 21, 13, 21)), doubleVariable(0.3, location(13, 27, 13, 29)), location(13, 21, 13, 29));
                bias$.put(intVariable(2, location(13, 21, 13, 21)), doubleVariable(0.5, location(13, 32, 13, 34)), location(13, 21, 13, 34));
                return bias$;
            });
        bias.setAlias("bias");
        bias.setLocation(location(13, 14, 13, 17));

        ArrayVariable<DoubleVariable> prob = ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> prob$ = Variable.arrayVariable(location(14, 21, 14, 35), VariableType.DoubleVariable, 3);
                prob$.put(intVariable(0, location(14, 21, 14, 21)), doubleVariable(0.2, location(14, 22, 14, 24)), location(14, 21, 14, 24));
                prob$.put(intVariable(1, location(14, 21, 14, 21)), doubleVariable(0.4, location(14, 27, 14, 29)), location(14, 21, 14, 29));
                prob$.put(intVariable(2, location(14, 21, 14, 21)), doubleVariable(0.4, location(14, 32, 14, 34)), location(14, 21, 14, 34));
                return prob$;
            });
        prob.setAlias("prob");
        prob.setLocation(location(14, 14, 14, 17));

        IntVariable cat = categorical(prob, location(16, 15, 16, 31)).sampleDistribution(location(16, 33, 16, 52));
        cat.setAlias("cat");
        cat.setLocation(location(16, 9, 16, 11));

        IntVariable result;
        $IfElseMods1 $if1 = new $IfElseMods1();
        $IfElseMods1 $else1 = new $IfElseMods1();
        BooleanVariable guard$1 = cat.notEq(intVariable(1, location(18, 15, 18, 15)), location(18, 12, 18, 13));
        IfScope ifScope$1 = ifElse(guard$1, () -> {
            $if1.result = binomial(bias.get(cat, location(19, 31, 19, 35)), intVariable(10, location(19, 38, 19, 39)), location(19, 18, 19, 40)).sample(location(19, 42, 19, 49));

        }, () -> {
            $else1.result = intVariable(5, location(21, 18, 21, 18));

        });
        result = ifElseAssignment(guard$1, $if1.result, $else1.result, ifScope$1, location(18, 5, 18, 16));
        result.setAlias("result");
        result.setLocation(location(17, 9, 17, 14));

        DoubleVariable data = gaussian(result, cat.castToDouble(location(25, 36, 25, 43)), location(25, 19, 25, 48)).sample(location(25, 50, 25, 57));
        data.setAlias("data");
        data.setLocation(location(25, 12, 25, 15));

        data.observe(observedData, location(27, 10, 27, 30));

        Variable<?>[] $variableNames = {observedData, bias, prob, cat, result, data};
        String[] $constructorArgs = {"observedData"};
        Set<String> $helperClasses = new HashSet<>();
        $helperClasses.add("$IfElseMods1");
        return compileAPI(opts, $variableNames, "DistributionTest7", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
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
             + "public model DistributionTest7(double observedData ) {\n"
             + "\n"
             + "    double[] bias = {0.2, 0.3, 0.5};\n"
             + "    double[] prob = {0.2, 0.4, 0.4};\n"
             + "\n"
             + "    int cat = categorical(prob).sampleDistribution();\n"
             + "    int result;\n"
             + "    if(cat != 1) {\n"
             + "        result = binomial(bias[cat], 10).sample();\n"
             + "    } else {\n"
             + "        result = 5;\n"
             + "    }\n"
             + "    \n"
             + "\n"
             + "    double data = gaussian(result, (double) cat).sample();\n"
             + "\n"
             + "    data.observe(observedData);\n"
             + "\n"
             + "    }";
    }
}