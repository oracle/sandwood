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

public class Flip1CoinMK18 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable samples = observeInt("samples", location(11, 28, 11, 38));
        IntVariable a = observeInt("a", location(11, 41, 11, 45));
        IntVariable b = observeInt("b", location(11, 48, 11, 52));
        IntVariable c = observeInt("c", location(11, 55, 11, 59));
        ArrayVariable<BooleanVariable> flipsMeasured = observeArray("flipsMeasured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 62, 11, 84));

        DoubleVariable q = beta(intVariable(1, location(13, 21, 13, 21)), intVariable(1, location(13, 23, 13, 23)), location(13, 16, 13, 24)).sample(location(13, 26, 13, 33));
        q.setAlias("q");
        q.setLocation(location(13, 12, 13, 12));

        DoubleVariable t = beta(intVariable(1, location(14, 21, 14, 21)), intVariable(1, location(14, 23, 14, 23)), location(14, 16, 14, 24)).sample(location(14, 26, 14, 33));
        t.setAlias("t");
        t.setLocation(location(14, 12, 14, 12));

        ArrayVariable<ArrayVariable<ArrayVariable<DoubleVariable>>> bias = Variable.arrayVariable(location(15, 25, 15, 65), VariableType.arrayType(VariableType.arrayType(VariableType.DoubleVariable)), 2);
        {
            ArrayVariable<ArrayVariable<DoubleVariable>> bias$0 = Variable.arrayVariable(location(15, 26, 15, 44), VariableType.arrayType(VariableType.DoubleVariable), 2);
            {
                ArrayVariable<DoubleVariable> bias$0$0 = Variable.arrayVariable(location(15, 27, 15, 34), VariableType.DoubleVariable, 2);
                {
                    bias$0$0.put(intVariable(0), t, location(15, 27, 15, 28));
                    bias$0$0.put(intVariable(1, location(15, 27, 15, 27)), intVariable(1, location(15, 31, 15, 31)).subtract(t, location(15, 32, 15, 32)), location(15, 27, 15, 32));
                }
                bias$0.put(intVariable(0, location(15, 26, 15, 27)), bias$0$0);

                ArrayVariable<DoubleVariable> bias$0$1 = Variable.arrayVariable(location(15, 36, 15, 43), VariableType.DoubleVariable, 2);
                {
                    bias$0$1.put(intVariable(0), intVariable(1, location(15, 37, 15, 37)).subtract(q, location(15, 38, 15, 38)), location(15, 36, 15, 38));
                    bias$0$1.put(intVariable(1, location(15, 36, 15, 36)), t, location(15, 36, 15, 42));
                }
                bias$0.put(intVariable(1), bias$0$1);
            }
            bias.put(intVariable(0, location(15, 25, 15, 26)), bias$0);

            ArrayVariable<ArrayVariable<DoubleVariable>> bias$1 = Variable.arrayVariable(location(15, 46, 15, 64), VariableType.arrayType(VariableType.DoubleVariable), 2);
            {
                ArrayVariable<DoubleVariable> bias$1$0 = Variable.arrayVariable(location(15, 47, 15, 54), VariableType.DoubleVariable, 2);
                {
                    bias$1$0.put(intVariable(0), t, location(15, 47, 15, 48));
                    bias$1$0.put(intVariable(1, location(15, 47, 15, 47)), intVariable(1, location(15, 51, 15, 51)).subtract(q, location(15, 52, 15, 52)), location(15, 47, 15, 52));
                }
                bias$1.put(intVariable(0, location(15, 46, 15, 47)), bias$1$0);

                ArrayVariable<DoubleVariable> bias$1$1 = Variable.arrayVariable(location(15, 56, 15, 63), VariableType.DoubleVariable, 2);
                {
                    bias$1$1.put(intVariable(0), intVariable(1, location(15, 57, 15, 57)).subtract(q, location(15, 58, 15, 58)), location(15, 56, 15, 58));
                    bias$1$1.put(intVariable(1, location(15, 56, 15, 56)), q, location(15, 56, 15, 62));
                }
                bias$1.put(intVariable(1), bias$1$1);
            }
            bias.put(intVariable(1), bias$1);
        }

        bias.setAlias("bias");
        bias.setLocation(location(15, 18, 15, 21));

        Bernoulli bernoulli = bernoulli(bias.get(a, location(17, 41, 17, 43)).get(b, location(17, 44, 17, 46)).get(c, location(17, 47, 17, 49)), location(17, 27, 17, 50));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(17, 15, 17, 23));

        ArrayVariable<BooleanVariable> flips = bernoulli.sample(samples, location(18, 33, 18, 47));
        flips.setAlias("flips");
        flips.setLocation(location(18, 15, 18, 19));

        flips.observe(flipsMeasured, location(20, 11, 20, 32));

        Variable<?>[] $variableNames = {samples, a, b, c, flipsMeasured, q, t, bias, bernoulli, flips};
        String[] $constructorArgs = {"samples", "a", "b", "c", "flipsMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "Flip1CoinMK18", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK18(int samples, int a, int b, int c, boolean[] flipsMeasured) {\n    \n    double q = beta(1,1).sample();\n    double t = beta(1,1).sample();\n    double[][][] bias = {{{t, 1-t},{1-q, t}},{{t, 1-q},{1-q, q}}};\n    \n    Bernoulli bernoulli = bernoulli(bias[a][b][c]);\n    boolean[] flips = bernoulli.sample(samples);\n    \n    flips.observe(flipsMeasured);\n}\n";
    }
}