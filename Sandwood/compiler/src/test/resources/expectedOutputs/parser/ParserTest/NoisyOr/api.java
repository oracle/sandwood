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

public class NoisyOr extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        DoubleVariable prior1 = doubleVariable(0.01, location(15, 21, 15, 24));
        prior1.setAlias("prior1");
        prior1.setLocation(location(15, 12, 15, 17));

        BooleanVariable flag1 = bernoulli(prior1, location(16, 21, 16, 37)).sample(location(16, 39, 16, 46));
        flag1.setAlias("flag1");
        flag1.setLocation(location(16, 13, 16, 17));

        DoubleVariable prior2 = doubleVariable(0.01, location(19, 21, 19, 24));
        prior2.setAlias("prior2");
        prior2.setLocation(location(19, 12, 19, 17));

        BooleanVariable flag2 = bernoulli(prior2, location(20, 21, 20, 37)).sample(location(20, 39, 20, 46));
        flag2.setAlias("flag2");
        flag2.setLocation(location(20, 13, 20, 17));

        DoubleVariable prior3 = doubleVariable(0.01, location(23, 21, 23, 24));
        prior3.setAlias("prior3");
        prior3.setLocation(location(23, 12, 23, 17));

        BooleanVariable flag3 = bernoulli(prior3, location(24, 21, 24, 37)).sample(location(24, 39, 24, 46));
        flag3.setAlias("flag3");
        flag3.setLocation(location(24, 13, 24, 17));

        DoubleVariable prior4 = doubleVariable(0.01, location(27, 21, 27, 24));
        prior4.setAlias("prior4");
        prior4.setLocation(location(27, 12, 27, 17));

        BooleanVariable flag4 = bernoulli(prior4, location(28, 21, 28, 37)).sample(location(28, 39, 28, 46));
        flag4.setAlias("flag4");
        flag4.setLocation(location(28, 13, 28, 17));

        DoubleVariable prior5 = doubleVariable(0.01, location(31, 21, 31, 24));
        prior5.setAlias("prior5");
        prior5.setLocation(location(31, 12, 31, 17));

        BooleanVariable flag5 = bernoulli(prior5, location(32, 21, 32, 37)).sample(location(32, 39, 32, 46));
        flag5.setAlias("flag5");
        flag5.setLocation(location(32, 13, 32, 17));

        DoubleVariable prior6 = doubleVariable(0.01, location(35, 21, 35, 24));
        prior6.setAlias("prior6");
        prior6.setLocation(location(35, 12, 35, 17));

        BooleanVariable flag6 = bernoulli(prior6, location(36, 21, 36, 37)).sample(location(36, 39, 36, 46));
        flag6.setAlias("flag6");
        flag6.setLocation(location(36, 13, 36, 17));

        ArrayVariable<ArrayVariable<DoubleVariable>> p = Variable.arrayVariable(location(39, 30, 39, 34), VariableType.arrayType(VariableType.DoubleVariable), intVariable(6, location(39, 31, 39, 31)));
        p.setAlias("p");
        p.setLocation(location(39, 16, 39, 16));

        p.put(intVariable(0, location(40, 7, 40, 7)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(40, 25, 40, 35), VariableType.DoubleVariable, 5);
                tempArray$.put(intVariable(0, location(40, 25, 40, 25)), intVariable(0, location(40, 26, 40, 26)), location(40, 25, 40, 26));
                tempArray$.put(intVariable(1, location(40, 25, 40, 25)), intVariable(1, location(40, 28, 40, 28)), location(40, 25, 40, 28));
                tempArray$.put(intVariable(2, location(40, 25, 40, 25)), intVariable(0, location(40, 30, 40, 30)), location(40, 25, 40, 30));
                tempArray$.put(intVariable(3, location(40, 25, 40, 25)), intVariable(0, location(40, 32, 40, 32)), location(40, 25, 40, 32));
                tempArray$.put(intVariable(4, location(40, 25, 40, 25)), intVariable(0, location(40, 34, 40, 34)), location(40, 25, 40, 34));
                return tempArray$;
            }), location(40, 6, 40, 25));
        p.put(intVariable(1, location(41, 7, 41, 7)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(41, 25, 41, 39), VariableType.DoubleVariable, 5);
                tempArray$.put(intVariable(0, location(41, 25, 41, 25)), doubleVariable(0.5, location(41, 26, 41, 28)), location(41, 25, 41, 28));
                tempArray$.put(intVariable(1, location(41, 25, 41, 25)), doubleVariable(0.5, location(41, 30, 41, 32)), location(41, 25, 41, 32));
                tempArray$.put(intVariable(2, location(41, 25, 41, 25)), intVariable(0, location(41, 34, 41, 34)), location(41, 25, 41, 34));
                tempArray$.put(intVariable(3, location(41, 25, 41, 25)), intVariable(0, location(41, 36, 41, 36)), location(41, 25, 41, 36));
                tempArray$.put(intVariable(4, location(41, 25, 41, 25)), intVariable(0, location(41, 38, 41, 38)), location(41, 25, 41, 38));
                return tempArray$;
            }), location(41, 6, 41, 25));
        p.put(intVariable(2, location(42, 7, 42, 7)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(42, 25, 42, 35), VariableType.DoubleVariable, 5);
                tempArray$.put(intVariable(0, location(42, 25, 42, 25)), intVariable(0, location(42, 26, 42, 26)), location(42, 25, 42, 26));
                tempArray$.put(intVariable(1, location(42, 25, 42, 25)), intVariable(0, location(42, 28, 42, 28)), location(42, 25, 42, 28));
                tempArray$.put(intVariable(2, location(42, 25, 42, 25)), intVariable(0, location(42, 30, 42, 30)), location(42, 25, 42, 30));
                tempArray$.put(intVariable(3, location(42, 25, 42, 25)), intVariable(1, location(42, 32, 42, 32)), location(42, 25, 42, 32));
                tempArray$.put(intVariable(4, location(42, 25, 42, 25)), intVariable(0, location(42, 34, 42, 34)), location(42, 25, 42, 34));
                return tempArray$;
            }), location(42, 6, 42, 25));
        p.put(intVariable(3, location(43, 7, 43, 7)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(43, 25, 43, 35), VariableType.DoubleVariable, 5);
                tempArray$.put(intVariable(0, location(43, 25, 43, 25)), intVariable(0, location(43, 26, 43, 26)), location(43, 25, 43, 26));
                tempArray$.put(intVariable(1, location(43, 25, 43, 25)), intVariable(0, location(43, 28, 43, 28)), location(43, 25, 43, 28));
                tempArray$.put(intVariable(2, location(43, 25, 43, 25)), intVariable(0, location(43, 30, 43, 30)), location(43, 25, 43, 30));
                tempArray$.put(intVariable(3, location(43, 25, 43, 25)), intVariable(1, location(43, 32, 43, 32)), location(43, 25, 43, 32));
                tempArray$.put(intVariable(4, location(43, 25, 43, 25)), intVariable(0, location(43, 34, 43, 34)), location(43, 25, 43, 34));
                return tempArray$;
            }), location(43, 6, 43, 25));
        p.put(intVariable(4, location(44, 7, 44, 7)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(44, 25, 44, 35), VariableType.DoubleVariable, 5);
                tempArray$.put(intVariable(0, location(44, 25, 44, 25)), intVariable(0, location(44, 26, 44, 26)), location(44, 25, 44, 26));
                tempArray$.put(intVariable(1, location(44, 25, 44, 25)), intVariable(0, location(44, 28, 44, 28)), location(44, 25, 44, 28));
                tempArray$.put(intVariable(2, location(44, 25, 44, 25)), intVariable(1, location(44, 30, 44, 30)), location(44, 25, 44, 30));
                tempArray$.put(intVariable(3, location(44, 25, 44, 25)), intVariable(0, location(44, 32, 44, 32)), location(44, 25, 44, 32));
                tempArray$.put(intVariable(4, location(44, 25, 44, 25)), intVariable(0, location(44, 34, 44, 34)), location(44, 25, 44, 34));
                return tempArray$;
            }), location(44, 6, 44, 25));
        p.put(intVariable(5, location(45, 7, 45, 7)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(45, 25, 45, 35), VariableType.DoubleVariable, 5);
                tempArray$.put(intVariable(0, location(45, 25, 45, 25)), intVariable(0, location(45, 26, 45, 26)), location(45, 25, 45, 26));
                tempArray$.put(intVariable(1, location(45, 25, 45, 25)), intVariable(0, location(45, 28, 45, 28)), location(45, 25, 45, 28));
                tempArray$.put(intVariable(2, location(45, 25, 45, 25)), intVariable(1, location(45, 30, 45, 30)), location(45, 25, 45, 30));
                tempArray$.put(intVariable(3, location(45, 25, 45, 25)), intVariable(0, location(45, 32, 45, 32)), location(45, 25, 45, 32));
                tempArray$.put(intVariable(4, location(45, 25, 45, 25)), intVariable(0, location(45, 34, 45, 34)), location(45, 25, 45, 34));
                return tempArray$;
            }), location(45, 6, 45, 25));
        ArrayVariable<BooleanVariable> noisyOr = Variable.arrayVariable(location(47, 36, 47, 38), VariableType.BooleanVariable, intVariable(5, location(47, 37, 47, 37)));
        noisyOr.setAlias("noisyOr");
        noisyOr.setLocation(location(47, 15, 47, 21));

        parFor(intVariable(0, location(49, 15, 49, 15)), intVariable(5, location(49, 20, 49, 20)), intVariable(1, location(49, 23, 49, 25)), true, location(49, 5, 49, 26), (i) -> {
            i.setAlias("i");
            i.setLocation(location(49, 13, 49, 13));
            ArrayVariable<BooleanVariable> issues = Variable.arrayVariable(location(50, 39, 50, 41), VariableType.BooleanVariable, intVariable(6, location(50, 40, 50, 40)));
            issues.setAlias("issues");
            issues.setLocation(location(50, 19, 50, 24));

            issues.put(intVariable(0, location(51, 16, 51, 16)), bernoulli(ifElseLambdaAssignment(flag1, () -> { return p.get(intVariable(0, location(51, 39, 51, 39)), location(51, 38, 51, 40)).get(i, location(51, 41, 51, 43)); }, () -> { return intVariable(0, location(51, 45, 51, 45)); }, location(51, 31, 51, 45)), location(51, 21, 51, 46)).sample(location(51, 48, 51, 55)), location(51, 15, 51, 55));
            issues.put(intVariable(1, location(52, 16, 52, 16)), bernoulli(ifElseLambdaAssignment(flag2, () -> { return p.get(intVariable(1, location(52, 39, 52, 39)), location(52, 38, 52, 40)).get(i, location(52, 41, 52, 43)); }, () -> { return intVariable(0, location(52, 45, 52, 45)); }, location(52, 31, 52, 45)), location(52, 21, 52, 46)).sample(location(52, 48, 52, 55)), location(52, 15, 52, 55));
            issues.put(intVariable(2, location(53, 16, 53, 16)), bernoulli(ifElseLambdaAssignment(flag3, () -> { return p.get(intVariable(2, location(53, 39, 53, 39)), location(53, 38, 53, 40)).get(i, location(53, 41, 53, 43)); }, () -> { return intVariable(0, location(53, 45, 53, 45)); }, location(53, 31, 53, 45)), location(53, 21, 53, 46)).sample(location(53, 48, 53, 55)), location(53, 15, 53, 55));
            issues.put(intVariable(3, location(54, 16, 54, 16)), bernoulli(ifElseLambdaAssignment(flag4, () -> { return p.get(intVariable(3, location(54, 39, 54, 39)), location(54, 38, 54, 40)).get(i, location(54, 41, 54, 43)); }, () -> { return intVariable(0, location(54, 45, 54, 45)); }, location(54, 31, 54, 45)), location(54, 21, 54, 46)).sample(location(54, 48, 54, 55)), location(54, 15, 54, 55));
            issues.put(intVariable(4, location(55, 16, 55, 16)), bernoulli(ifElseLambdaAssignment(flag5, () -> { return p.get(intVariable(4, location(55, 39, 55, 39)), location(55, 38, 55, 40)).get(i, location(55, 41, 55, 43)); }, () -> { return intVariable(0, location(55, 45, 55, 45)); }, location(55, 31, 55, 45)), location(55, 21, 55, 46)).sample(location(55, 48, 55, 55)), location(55, 15, 55, 55));
            issues.put(intVariable(5, location(56, 16, 56, 16)), bernoulli(ifElseLambdaAssignment(flag6, () -> { return p.get(intVariable(5, location(56, 39, 56, 39)), location(56, 38, 56, 40)).get(i, location(56, 41, 56, 43)); }, () -> { return intVariable(0, location(56, 45, 56, 45)); }, location(56, 31, 56, 45)), location(56, 21, 56, 46)).sample(location(56, 48, 56, 55)), location(56, 15, 56, 55));
            noisyOr.put(i, reduce(issues, booleanVariable(false, location(58, 37, 58, 41)), location(58, 22, 60, 10), (x, y) -> {
                x.setAlias("x");
                x.setLocation(location(58, 45, 58, 45));
                y.setAlias("y");
                y.setLocation(location(58, 48, 58, 48));
                return x.or(y, location(59, 22, 59, 23));
            }), location(58, 16, 60, 10));

        });

        ArrayVariable<ArrayVariable<DoubleVariable>> p13 = Variable.arrayVariable(location(64, 32, 64, 36), VariableType.arrayType(VariableType.DoubleVariable), intVariable(5, location(64, 33, 64, 33)));
        p13.setAlias("p13");
        p13.setLocation(location(64, 16, 64, 18));

        p13.put(intVariable(0, location(65, 9, 65, 9)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(65, 27, 65, 36), VariableType.DoubleVariable, 2);
                tempArray$.put(intVariable(0, location(65, 27, 65, 27)), doubleVariable(0.1, location(65, 28, 65, 30)), location(65, 27, 65, 30));
                tempArray$.put(intVariable(1, location(65, 27, 65, 27)), doubleVariable(0.9, location(65, 33, 65, 35)), location(65, 27, 65, 35));
                return tempArray$;
            }), location(65, 8, 65, 27));
        p13.put(intVariable(1, location(66, 9, 66, 9)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(66, 27, 66, 36), VariableType.DoubleVariable, 2);
                tempArray$.put(intVariable(0, location(66, 27, 66, 27)), doubleVariable(1.0, location(66, 28, 66, 30)), location(66, 27, 66, 30));
                tempArray$.put(intVariable(1, location(66, 27, 66, 27)), doubleVariable(0.0, location(66, 33, 66, 35)), location(66, 27, 66, 35));
                return tempArray$;
            }), location(66, 8, 66, 27));
        p13.put(intVariable(2, location(67, 9, 67, 9)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(67, 27, 67, 36), VariableType.DoubleVariable, 2);
                tempArray$.put(intVariable(0, location(67, 27, 67, 27)), doubleVariable(0.5, location(67, 28, 67, 30)), location(67, 27, 67, 30));
                tempArray$.put(intVariable(1, location(67, 27, 67, 27)), doubleVariable(0.5, location(67, 33, 67, 35)), location(67, 27, 67, 35));
                return tempArray$;
            }), location(67, 8, 67, 27));
        p13.put(intVariable(3, location(68, 9, 68, 9)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(68, 27, 68, 36), VariableType.DoubleVariable, 2);
                tempArray$.put(intVariable(0, location(68, 27, 68, 27)), doubleVariable(0.5, location(68, 28, 68, 30)), location(68, 27, 68, 30));
                tempArray$.put(intVariable(1, location(68, 27, 68, 27)), doubleVariable(0.5, location(68, 33, 68, 35)), location(68, 27, 68, 35));
                return tempArray$;
            }), location(68, 8, 68, 27));
        p13.put(intVariable(4, location(69, 9, 69, 9)), ArrayVariable.getArrayVariable(() -> {
                ArrayVariable<DoubleVariable> tempArray$ = Variable.arrayVariable(location(69, 27, 69, 36), VariableType.DoubleVariable, 2);
                tempArray$.put(intVariable(0, location(69, 27, 69, 27)), doubleVariable(0.0, location(69, 28, 69, 30)), location(69, 27, 69, 30));
                tempArray$.put(intVariable(1, location(69, 27, 69, 27)), doubleVariable(1.0, location(69, 33, 69, 35)), location(69, 27, 69, 35));
                return tempArray$;
            }), location(69, 8, 69, 27));
        ArrayVariable<BooleanVariable> n13State = Variable.arrayVariable(location(71, 37, 71, 39), VariableType.BooleanVariable, intVariable(2, location(71, 38, 71, 38)));
        n13State.setAlias("n13State");
        n13State.setLocation(location(71, 15, 71, 22));

        parFor(intVariable(0, location(73, 15, 73, 15)), intVariable(2, location(73, 20, 73, 20)), intVariable(1, location(73, 23, 73, 25)), true, location(73, 5, 73, 26), (i) -> {
            i.setAlias("i");
            i.setLocation(location(73, 13, 73, 13));
            ArrayVariable<BooleanVariable> issues = Variable.arrayVariable(location(74, 39, 74, 41), VariableType.BooleanVariable, intVariable(5, location(74, 40, 74, 40)));
            issues.setAlias("issues");
            issues.setLocation(location(74, 19, 74, 24));

            parFor(intVariable(0, location(75, 19, 75, 19)), intVariable(5, location(75, 24, 75, 24)), intVariable(1, location(75, 27, 75, 29)), true, location(75, 9, 75, 30), (j) -> {
                j.setAlias("j");
                j.setLocation(location(75, 17, 75, 17));
                issues.put(j, bernoulli(ifElseLambdaAssignment(noisyOr.get(j, location(76, 42, 76, 44)), () -> { return p13.get(j, location(76, 49, 76, 51)).get(i, location(76, 52, 76, 54)); }, () -> { return intVariable(0, location(76, 56, 76, 56)); }, location(76, 35, 76, 56)), location(76, 25, 76, 57)).sample(location(76, 59, 76, 66)), location(76, 19, 76, 66));
            });

            n13State.put(i, reduce(issues, booleanVariable(false, location(78, 38, 78, 42)), location(78, 23, 80, 10), (x, y) -> {
                x.setAlias("x");
                x.setLocation(location(78, 46, 78, 46));
                y.setAlias("y");
                y.setLocation(location(78, 49, 78, 49));
                return x.or(y, location(79, 22, 79, 23));
            }), location(78, 17, 80, 10));

        });


        Variable<?>[] $variableNames = {prior1, flag1, prior2, flag2, prior3, flag3, prior4, flag4, prior5, flag5, prior6, flag6, p, noisyOr, p13, n13State};
        String[] $constructorArgs = {};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "NoisyOr", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() {
        return "/*\n"
             + " * Sandwood\n"
             + " *\n"
             + " * Copyright (c) 2019-2026, Oracle and/or its affiliates\n"
             + " *\n"
             + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
             + " */\n"
             + " \n"
             + "package org.sandwood.compiler.tests.parser;\n"
             + "\n"
             + "public model NoisyOr() {\n"
             + "\n"
             + "\n"
             + "    // 1)\n"
             + "    double prior1 = 0.01;\n"
             + "    boolean flag1 = bernoulli(prior1).sample();\n"
             + "    \n"
             + "    // 2)\n"
             + "    double prior2 = 0.01;\n"
             + "    boolean flag2 = bernoulli(prior2).sample();\n"
             + "    \n"
             + "    // 3)\n"
             + "    double prior3 = 0.01;\n"
             + "    boolean flag3 = bernoulli(prior3).sample();\n"
             + "    \n"
             + "    // 4)\n"
             + "    double prior4 = 0.01;\n"
             + "    boolean flag4 = bernoulli(prior4).sample();\n"
             + "    \n"
             + "    // 5)\n"
             + "    double prior5 = 0.01;\n"
             + "    boolean flag5 = bernoulli(prior5).sample();\n"
             + "    \n"
             + "    // 6)\n"
             + "    double prior6 = 0.01;\n"
             + "    boolean flag6 = bernoulli(prior6).sample();\n"
             + "    \n"
             + "    // Start n12\n"
             + "    double[][] p = new double[6][];\n"
             + "    p[0] = new double[] {0,1,0,0,0};\n"
             + "    p[1] = new double[] {0.5,0.5,0,0,0};\n"
             + "    p[2] = new double[] {0,0,0,1,0};\n"
             + "    p[3] = new double[] {0,0,0,1,0};\n"
             + "    p[4] = new double[] {0,0,1,0,0};\n"
             + "    p[5] = new double[] {0,0,1,0,0};\n"
             + "    \n"
             + "    boolean[] noisyOr = new boolean[5];\n"
             + "    \n"
             + "    for(int i=0; i<5; i++) {\n"
             + "        boolean[] issues = new boolean[6];\n"
             + "        issues[0] = bernoulli(flag1?p[0][i]:0).sample();\n"
             + "        issues[1] = bernoulli(flag2?p[1][i]:0).sample();\n"
             + "        issues[2] = bernoulli(flag3?p[2][i]:0).sample();\n"
             + "        issues[3] = bernoulli(flag4?p[3][i]:0).sample();\n"
             + "        issues[4] = bernoulli(flag5?p[4][i]:0).sample();\n"
             + "        issues[5] = bernoulli(flag6?p[5][i]:0).sample();\n"
             + "        \n"
             + "        noisyOr[i] = reduce(issues, false, (x, y) -> {\n"
             + "            return x || y;\n"
             + "        });\n"
             + "    }\n"
             + "    \n"
             + "    // Starting n13\n"
             + "    double[][] p13 = new double[5][];\n"
             + "    p13[0] = new double[] {0.1, 0.9};\n"
             + "    p13[1] = new double[] {1.0, 0.0};\n"
             + "    p13[2] = new double[] {0.5, 0.5};\n"
             + "    p13[3] = new double[] {0.5, 0.5};\n"
             + "    p13[4] = new double[] {0.0, 1.0};\n"
             + "    \n"
             + "    boolean[] n13State = new boolean[2];\n"
             + "    \n"
             + "    for(int i=0; i<2; i++) {\n"
             + "        boolean[] issues = new boolean[5];\n"
             + "        for(int j=0; j<5; j++)\n"
             + "            issues[j] = bernoulli(noisyOr[j]?p13[j][i]:0).sample();\n"
             + "        \n"
             + "        n13State[i] = reduce(issues, false, (x, y) -> {\n"
             + "            return x || y;\n"
             + "        });\n"
             + "    }\n"
             + "}";
    }
}