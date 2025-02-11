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

public class RaggedArray4 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<IntVariable> obs_measured = observeArray("obs_measured", VariableType.arrayType(VariableType.IntVariable), location(11, 27, 11, 44));

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

        ArrayVariable<DoubleVariable> b = Variable.arrayVariable(location(13, 18, 13, 31), VariableType.DoubleVariable, 2);
        {
            b.put(intVariable(0), doubleVariable(0.35, location(13, 20, 13, 23)), location(13, 18, 13, 23));
            b.put(intVariable(1, location(13, 18, 13, 18)), doubleVariable(0.65, location(13, 26, 13, 29)), location(13, 18, 13, 29));
        }

        b.setAlias("b");
        b.setLocation(location(13, 14, 13, 14));

        IntVariable y = categorical(b, location(14, 13, 14, 26)).sampleDistribution(location(14, 28, 14, 47));
        y.setAlias("y");
        y.setLocation(location(14, 9, 14, 9));

        ArrayVariable<DoubleVariable> d = dirichlet(a.get(y, location(15, 29, 15, 31)), location(15, 18, 15, 32)).sample(location(15, 34, 15, 41));
        d.setAlias("d");
        d.setLocation(location(15, 14, 15, 14));

        ArrayVariable<IntVariable> obs = categorical(d, location(16, 17, 16, 30)).sample(obs_measured.length(location(16, 52, 16, 57)), location(16, 32, 16, 58));
        obs.setAlias("obs");
        obs.setLocation(location(16, 11, 16, 13));

        obs.observe(obs_measured, location(17, 9, 17, 29));

        Variable<?>[] $variableNames = {obs_measured, a, b, y, d, obs};
        String[] $constructorArgs = {"obs_measured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "RaggedArray4", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray4(int[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[] b = { 0.35, 0.65 };\n    int y = categorical(b).sampleDistribution();\n    double[] d = dirichlet(a[y]).sample();\n    int[] obs = categorical(d).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
    }
}