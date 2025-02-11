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

public class RaggedArray2 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> obs_measured = observeArray("obs_measured", VariableType.arrayType(VariableType.BooleanVariable), location(11, 27, 11, 48));

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

        ArrayVariable<ArrayVariable<DoubleVariable>> b = Variable.arrayVariable(location(13, 20, 13, 48), VariableType.arrayType(VariableType.DoubleVariable), 2);
        {
            ArrayVariable<DoubleVariable> b$0 = Variable.arrayVariable(location(13, 21, 13, 30), VariableType.DoubleVariable, 2);
            {
                b$0.put(intVariable(0), doubleVariable(0.2, location(13, 22, 13, 24)), location(13, 21, 13, 24));
                b$0.put(intVariable(1, location(13, 21, 13, 21)), doubleVariable(0.8, location(13, 27, 13, 29)), location(13, 21, 13, 29));
            }
            b.put(intVariable(0, location(13, 20, 13, 21)), b$0);

            ArrayVariable<DoubleVariable> b$1 = Variable.arrayVariable(location(13, 33, 13, 47), VariableType.DoubleVariable, 3);
            {
                b$1.put(intVariable(0), doubleVariable(0.4, location(13, 34, 13, 36)), location(13, 33, 13, 36));
                b$1.put(intVariable(1, location(13, 33, 13, 33)), doubleVariable(0.2, location(13, 39, 13, 41)), location(13, 33, 13, 41));
                b$1.put(intVariable(2, location(13, 33, 13, 33)), doubleVariable(0.6, location(13, 44, 13, 46)), location(13, 33, 13, 46));
            }
            b.put(intVariable(1), b$1);
        }

        b.setAlias("b");
        b.setLocation(location(13, 16, 13, 16));

        ArrayVariable<DoubleVariable> c = Variable.arrayVariable(location(14, 18, 14, 31), VariableType.DoubleVariable, 2);
        {
            c.put(intVariable(0), doubleVariable(0.35, location(14, 20, 14, 23)), location(14, 18, 14, 23));
            c.put(intVariable(1, location(14, 18, 14, 18)), doubleVariable(0.65, location(14, 26, 14, 29)), location(14, 18, 14, 29));
        }

        c.setAlias("c");
        c.setLocation(location(14, 14, 14, 14));

        IntVariable y = categorical(c, location(15, 13, 15, 26)).sampleDistribution(location(15, 28, 15, 47));
        y.setAlias("y");
        y.setLocation(location(15, 9, 15, 9));

        IntVariable i = categorical(a.get(y, location(16, 26, 16, 28)), location(16, 13, 16, 29)).sample(location(16, 31, 16, 38));
        i.setAlias("i");
        i.setLocation(location(16, 9, 16, 9));

        DoubleVariable p = b.get(y, location(17, 17, 17, 19)).get(i, location(17, 20, 17, 22));
        p.setAlias("p");
        p.setLocation(location(17, 12, 17, 12));

        ArrayVariable<BooleanVariable> obs = bernoulli(p, location(18, 21, 18, 32)).sample(obs_measured.length(location(18, 54, 18, 59)), location(18, 34, 18, 60));
        obs.setAlias("obs");
        obs.setLocation(location(18, 15, 18, 17));

        obs.observe(obs_measured, location(19, 9, 19, 29));

        Variable<?>[] $variableNames = {obs_measured, a, b, c, y, i, p, obs};
        String[] $constructorArgs = {"obs_measured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "RaggedArray2", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray2(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n    double[] c = { 0.35, 0.65 };\n    int y = categorical(c).sampleDistribution();\n    int i = categorical(a[y]).sample();\n    double p = b[y][i];\n    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
    }
}