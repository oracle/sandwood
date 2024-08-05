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

public class LDATest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        IntVariable noTopics = observeInt("noTopics", location(11, 15, 11, 26));
        IntVariable vocabSize = observeInt("vocabSize", location(11, 29, 11, 41));
        ArrayVariable<ArrayVariable<IntVariable>> documents = observeArray("documents", VariableType.arrayType(VariableType.arrayType(VariableType.IntVariable)), location(11, 44, 11, 60));

        ArrayVariable<DoubleVariable> alpha = Variable.arrayVariable(location(13, 32, 13, 41), VariableType.DoubleVariable, noTopics);
        alpha.setAlias("alpha");
        alpha.setLocation(location(13, 14, 13, 18));

        parFor(intVariable(0, location(14, 16, 14, 16)), noTopics, intVariable(1, location(14, 15, 14, 18)), true, location(14, 5, 14, 28), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(14, 13, 14, 13));
            alpha.put(i, doubleVariable(0.1, location(15, 20, 15, 22)), location(15, 14, 15, 22));
        });

        ArrayVariable<DoubleVariable> beta = Variable.arrayVariable(location(17, 31, 17, 41), VariableType.DoubleVariable, vocabSize);
        beta.setAlias("beta");
        beta.setLocation(location(17, 14, 17, 17));

        parFor(intVariable(0, location(18, 16, 18, 16)), vocabSize, intVariable(1, location(18, 15, 18, 18)), true, location(18, 5, 18, 29), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 13, 18, 13));
            beta.put(i, doubleVariable(0.1, location(19, 19, 19, 21)), location(19, 13, 19, 21));
        });

        ArrayVariable<ArrayVariable<DoubleVariable>> phi = dirichlet(beta, location(21, 22, 21, 36)).sample(noTopics, location(21, 38, 21, 53));
        phi.setAlias("phi");
        phi.setLocation(location(21, 16, 21, 18));

        ArrayVariable<ArrayVariable<DoubleVariable>> theta = dirichlet(alpha, location(22, 24, 22, 39)).sample(documents.length(location(22, 58, 22, 63)), location(22, 41, 22, 64));
        theta.setAlias("theta");
        theta.setLocation(location(22, 16, 22, 20));

        ArrayVariable<ArrayVariable<IntVariable>> w = Variable.arrayVariable(location(23, 24, 23, 43), VariableType.arrayType(VariableType.IntVariable), documents.length(location(23, 35, 23, 40)));
        w.setAlias("w");
        w.setLocation(location(23, 13, 23, 13));

        parFor(intVariable(0, location(25, 16, 25, 16)), documents.length(location(25, 29, 25, 34)), intVariable(1, location(25, 15, 25, 18)), true, location(25, 5, 25, 36), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(25, 13, 25, 13));
            ArrayVariable<IntVariable> t = Variable.arrayVariable(location(26, 26, 26, 46), VariableType.IntVariable, documents.get(i, location(26, 36, 26, 38)).length(location(26, 40, 26, 45)));
            t.setAlias("t");
            t.setLocation(location(26, 15, 26, 15));

            parFor(intVariable(0, location(27, 20, 27, 20)), documents.get(i, location(27, 32, 27, 34)).length(location(27, 36, 27, 41)), intVariable(1, location(27, 19, 27, 22)), true, location(27, 9, 27, 43), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(27, 17, 27, 17));
                IntVariable z = categorical(theta.get(i, location(28, 38, 28, 40)), location(28, 21, 28, 41)).sample(location(28, 43, 28, 50));
                z.setAlias("z");
                z.setLocation(location(28, 17, 28, 17));

                t.put(j, categorical(phi.get(z, location(29, 35, 29, 37)), location(29, 20, 29, 38)).sample(location(29, 40, 29, 47)), location(29, 14, 29, 47));

            });

            w.put(i, t, location(31, 10, 31, 16));

        });

        w.observe(documents, location(34, 7, 34, 24));

        Variable<?>[] $variableNames = {noTopics, vocabSize, documents, alpha, beta, phi, theta, w};
        String[] $constructorArgs = {"noTopics", "vocabSize", "documents"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "LDATest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LDATest(int noTopics, int vocabSize, int[][] documents) {\n\n    double[] alpha = new double[noTopics];\n    for(int i:[0..noTopics))\n        alpha[i] = 0.1;\n\n    double[] beta = new double[vocabSize];\n    for(int i:[0..vocabSize))\n        beta[i] = 0.1;\n\n    double[][] phi = dirichlet(beta).sample(noTopics);\n    double[][] theta = dirichlet(alpha).sample(documents.length);\n    int[][] w = new int[documents.length][];\n\n    for(int i:[0..documents.length)) {\n        int[] t = new int[documents[i].length];\n        for(int j:[0..documents[i].length)) {\n            int z = categorical(theta[i]).sample();\n            t[j] = categorical(phi[z]).sample();\n        }\n        w[i] = t;\n    }\n\n    w.observe(documents);\n}\n";
    }
}