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

public class HMM extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> measured = observeArray("measured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 11, 11, 28));
        IntVariable states = observeInt("states", location(11, 31, 11, 40));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(13, 26, 13, 33), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(13, 38, 13, 40)); }, states);
        v.setAlias("v");
        v.setLocation(location(13, 12, 13, 12));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(14, 18, 14, 29)).sample(states, location(14, 31, 14, 44));
        m.setAlias("m");
        m.setLocation(location(14, 14, 14, 14));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(16, 24, 16, 26)), doubleVariable(1.0, location(16, 29, 16, 31)), location(16, 19, 16, 32)).sample(states, location(16, 34, 16, 47));
        bias.setAlias("bias");
        bias.setLocation(location(16, 12, 16, 15));

        IntVariable samples = measured.length(location(18, 26, 18, 31));
        samples.setAlias("samples");
        samples.setLocation(location(18, 7, 18, 13));

        ArrayVariable<IntVariable> st = Variable.arrayVariable(location(19, 21, 19, 29), VariableType.IntVariable, samples);
        st.setAlias("st");
        st.setLocation(location(19, 9, 19, 10));

        st.put(intVariable(0, location(21, 6, 21, 6)), categorical(m.get(intVariable(0, location(21, 25, 21, 25)), location(21, 24, 21, 26)), location(21, 11, 21, 27)).sample(location(21, 29, 21, 36)), location(21, 5, 21, 36));
        parFor(intVariable(1, location(23, 14, 23, 14)), samples, intVariable(1, location(23, 13, 23, 16)), true, location(23, 3, 23, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(23, 11, 23, 11));
            st.put(i, categorical(m.get(st.get(i.subtract(intVariable(1, location(24, 34, 24, 34)), location(24, 32, 24, 32)), location(24, 29, 24, 35)), location(24, 26, 24, 36)), location(24, 13, 24, 37)).sample(location(24, 39, 24, 46)), location(24, 7, 24, 46));
        });

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(26, 32, 26, 40), VariableType.BooleanVariable, samples);
        flips.setAlias("flips");
        flips.setLocation(location(26, 13, 26, 17));

        parFor(intVariable(0, location(27, 14, 27, 14)), samples, intVariable(1, location(27, 13, 27, 16)), true, location(27, 3, 27, 25), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(27, 11, 27, 11));
            flips.put(j, bernoulli(bias.get(st.get(j, location(28, 33, 28, 35)), location(28, 30, 28, 36)), location(28, 16, 28, 37)).sample(location(28, 39, 28, 46)), location(28, 10, 28, 46));
        });

        flips.observe(measured, location(30, 9, 30, 25));

        Variable<?>[] $variableNames = {measured, states, v, m, bias, samples, st, flips};
        String[] $constructorArgs = {"measured", "states"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMM", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM(boolean[] measured, int states) {\n\n  double[] v = new double[states] <~ 0.1;\n  double[][] m = dirichlet(v).sample(states);\n    \n  double[] bias = beta(1.0, 1.0).sample(states);\n\n  int samples = measured.length;\n  int[] st = new int[samples];\n        \n  st[0] = categorical(m[0]).sample();\n \n  for(int i:[1..samples))\n    st[i] = categorical(m[st[i - 1]]).sample();\n\n  boolean[] flips = new boolean[samples];\n  for(int j:[0..samples))\n    flips[j] = bernoulli(bias[st[j]]).sample();\n\n  flips.observe(measured);\n}";
    }
}