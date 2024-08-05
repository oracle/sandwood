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

public class HMM_Paper extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> measured = observeArray("measured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 17, 11, 34));
        IntVariable nCoins = observeInt("nCoins", location(11, 37, 11, 46));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(13, 26, 13, 33), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(13, 38, 13, 40)); }, nCoins);
        v.setAlias("v");
        v.setLocation(location(13, 12, 13, 12));

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet(v, location(14, 18, 14, 29)).sample(nCoins, location(14, 31, 14, 44));
        m.setAlias("m");
        m.setLocation(location(14, 14, 14, 14));

        ArrayVariable<DoubleVariable> initialCoin = dirichlet(v, location(17, 26, 17, 37)).sample(location(17, 39, 17, 44));
        initialCoin.setAlias("initialCoin");
        initialCoin.setLocation(location(17, 12, 17, 22));

        ArrayVariable<DoubleVariable> bias = beta(doubleVariable(1.0, location(20, 24, 20, 26)), doubleVariable(1.0, location(20, 29, 20, 31)), location(20, 19, 20, 32)).sample(nCoins, location(20, 34, 20, 47));
        bias.setAlias("bias");
        bias.setLocation(location(20, 12, 20, 15));

        IntVariable nFlips = measured.length(location(23, 25, 23, 30));
        nFlips.setAlias("nFlips");
        nFlips.setLocation(location(23, 7, 23, 12));

        ArrayVariable<IntVariable> st = Variable.arrayVariable(location(24, 21, 24, 28), VariableType.IntVariable, nFlips);
        st.setAlias("st");
        st.setLocation(location(24, 9, 24, 10));

        st.put(intVariable(0, location(27, 6, 27, 6)), categorical(initialCoin, location(27, 11, 27, 34)).sample(location(27, 36, 27, 43)), location(27, 5, 27, 43));
        parFor(intVariable(1, location(28, 16, 28, 16)), nFlips, intVariable(1, location(28, 15, 28, 18)), true, location(28, 3, 28, 27), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(28, 12, 28, 12));
            st.put(i, categorical(m.get(st.get(i.subtract(intVariable(1, location(29, 34, 29, 34)), location(29, 32, 29, 32)), location(29, 29, 29, 35)), location(29, 26, 29, 36)), location(29, 13, 29, 37)).sample(location(29, 39, 29, 46)), location(29, 7, 29, 46));
        });

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(location(32, 32, 32, 39), VariableType.BooleanVariable, nFlips);
        flips.setAlias("flips");
        flips.setLocation(location(32, 13, 32, 17));

        parFor(intVariable(0, location(33, 16, 33, 16)), nFlips, intVariable(1, location(33, 15, 33, 18)), true, location(33, 3, 33, 27), (j) -> { 
            j.setAlias("j");
            j.setLocation(location(33, 12, 33, 12));
            flips.put(j, bernoulli(bias.get(st.get(j, location(34, 33, 34, 35)), location(34, 30, 34, 36)), location(34, 16, 34, 37)).sample(location(34, 39, 34, 46)), location(34, 10, 34, 46));
        });

        flips.observe(measured, location(37, 9, 37, 25));

        Variable<?>[] $variableNames = {measured, nCoins, v, m, initialCoin, bias, nFlips, st, flips};
        String[] $constructorArgs = {"measured", "nCoins"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "HMM_Paper", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Paper(boolean[] measured, int nCoins) {\n  //Construct a transistion matrix m.\n  double[] v = new double[nCoins] <~ 0.1;\n  double[][] m = dirichlet(v).sample(nCoins);\n  \n  //Construct weighting for which coin to start at.\n  double[] initialCoin = dirichlet(v).sample;\n    \n  //Construct biases for each coin    \n  double[] bias = beta(1.0, 1.0).sample(nCoins);\n\n  //Allocate space to record which coin is flipped\n  int nFlips = measured.length;\n  int[] st = new int[nFlips];\n\n  //Calculate the movements between coins        \n  st[0] = categorical(initialCoin).sample();\n  for (int i: [1..nFlips) )\n    st[i] = categorical(m[st[i - 1]]).sample();\n\n  //Flip the coins\n  boolean[] flips = new boolean[nFlips];\n  for (int j: [0..nFlips) )\n    flips[j] = bernoulli(bias[st[j]]).sample();\n    \n  //Assert that the flips match the measured data.\n  flips.observe(measured);\n}\n";
    }
}