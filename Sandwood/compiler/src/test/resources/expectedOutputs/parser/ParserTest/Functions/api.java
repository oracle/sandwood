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

public class Functions extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {

        return new CompilationDesc();
    }

    private static  void sample(ArrayVariable<ArrayVariable<BooleanVariable>> flips, IntVariable samples, IntVariable i, ArrayVariable<DoubleVariable> bias, Location $location) { 
        Bernoulli bernoulli = bernoulli(bias.get(i, location(14, 49, 14, 51)), location(14, 35, 14, 52));
        bernoulli.setAlias("bernoulli");
        bernoulli.setLocation(location(14, 23, 14, 31));

        flips.put(i, bernoulli.sample(samples, location(15, 34, 15, 48)), location(15, 18, 15, 48));
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Functions {        \n         \n    private void sample(boolean[][] flips, int samples, int i, double[] bias) {\n            Bernoulli bernoulli = bernoulli(bias[i]);\n            flips[i] = bernoulli.sample(samples);\n    }\n}\n";
    }
}