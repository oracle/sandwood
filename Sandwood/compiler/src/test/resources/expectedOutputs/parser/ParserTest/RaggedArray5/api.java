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

public class RaggedArray5 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable y = observeInt("y", location(11, 27, 11, 31));
        ArrayVariable<BooleanVariable> obs_measured = observeArray("obs_measured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 34, 11, 55));

        ArrayVariable<ArrayVariable<DoubleVariable>> a = Variable.arrayVariable(location(12, 20, 12, 48), VariableType.arrayType(VariableType.DoubleVariable), 2);
        {
            ArrayVariable<DoubleVariable> a$0 = Variable.arrayVariable(location(12, 21, 12, 30), VariableType.DoubleVariable, 2);
            {
                a$0.put(intVariable(0), doubleVariable(0.4, location(12, 22, 12, 24)), location(12, 21, 12, 24));
                a$0.put(intVariable(1, location(12, 21, 12, 21)), doubleVariable(0.6, location(12, 27, 12, 29)), location(12, 21, 12, 29));
            }
            a.put(intVariable(0, location(12, 20, 12, 21)), a$0);

            ArrayVariable<DoubleVariable> a$1 = Variable.arrayVariable(location(12, 33, 12, 47), VariableType.DoubleVariable, 3);
            {
                a$1.put(intVariable(0), doubleVariable(0.2, location(12, 34, 12, 36)), location(12, 33, 12, 36));
                a$1.put(intVariable(1, location(12, 33, 12, 33)), doubleVariable(0.3, location(12, 39, 12, 41)), location(12, 33, 12, 41));
                a$1.put(intVariable(2, location(12, 33, 12, 33)), doubleVariable(0.5, location(12, 44, 12, 46)), location(12, 33, 12, 46));
            }
            a.put(intVariable(1), a$1);
        }

        a.setAlias("a");
        a.setLocation(location(12, 16, 12, 16));

        ArrayVariable<DoubleVariable> d = dirichlet(a.get(y, location(14, 29, 14, 31)), location(14, 18, 14, 32)).sample(location(14, 34, 14, 41));
        d.setAlias("d");
        d.setLocation(location(14, 14, 14, 14));

        ArrayVariable<BooleanVariable> obs = bernoulli(d.get(y, location(15, 32, 15, 34)), location(15, 21, 15, 35)).sample(obs_measured.length(location(15, 57, 15, 62)), location(15, 37, 15, 63));
        obs.setAlias("obs");
        obs.setLocation(location(15, 15, 15, 17));

        obs.observe(obs_measured, location(16, 9, 16, 29));

        Variable<?>[] $variableNames = {y, obs_measured, a, d, obs};
        String[] $constructorArgs = {"y", "obs_measured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "RaggedArray5", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray5(int y, boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    \n    double[] d = dirichlet(a[y]).sample();\n    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
    }
}