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

public class HMMTestPart1b extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        BooleanVariable flipMeasured = observeBoolean("flipMeasured", location(11, 21, 11, 40));

        IntVariable states = intVariable(2, location(12, 22, 12, 22));
        states.setAlias("states");
        states.setLocation(location(12, 13, 12, 18));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(14, 32, 14, 39), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(14, 44, 14, 46)); }, states);
        v.setAlias("v");
        v.setLocation(location(14, 18, 14, 18));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(16, 24, 16, 35)).sample(states, location(16, 37, 16, 50));
        m.setAlias("m");
        m.setLocation(location(16, 20, 16, 20));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(17, 30, 17, 32)), doubleVariable(1.0, location(17, 35, 17, 37)), location(17, 25, 17, 38)).sample(states, location(17, 40, 17, 53));
        bias.setAlias("bias");
        bias.setLocation(location(17, 18, 17, 21));

        IntVariable st = categorical(m.get(intVariable(0, location(19, 32, 19, 32)), location(19, 31, 19, 33)), location(19, 18, 19, 34)).sample(location(19, 36, 19, 43));
        st.setAlias("st");
        st.setLocation(location(19, 13, 19, 14));

        BooleanVariable flip = bernoulli(bias.get(st, location(20, 38, 20, 41)), location(20, 24, 20, 42)).sample(location(20, 44, 20, 51));
        flip.setAlias("flip");
        flip.setLocation(location(20, 17, 20, 20));

        flip.observe(flipMeasured, location(22, 14, 22, 34));

        Variable<?>[] $variableNames = {flipMeasured, states, v, m, bias, st, flip};
        String[] $constructorArgs = {"flipMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMMTestPart1b", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart1b(boolean flipMeasured) {\n        int states = 2;\n\n        double[] v = new double[states] <~ 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int st = categorical(m[0]).sample();\n        boolean flip = bernoulli(bias[st]).sample();\n\n        flip.observe(flipMeasured);\n}\n";
    }
}