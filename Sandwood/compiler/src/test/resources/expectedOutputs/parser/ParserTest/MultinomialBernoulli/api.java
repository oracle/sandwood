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

public class MultinomialBernoulli extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.BooleanVariable), location(11, 35, 11, 52));

        ArrayVariable<DoubleVariable> beta = Variable.arrayVariable(location(12, 21, 12, 35), VariableType.DoubleVariable, 3);
        {
            beta.put(intVariable(0), doubleVariable(0.1, location(12, 22, 12, 24)), location(12, 21, 12, 24));
            beta.put(intVariable(1, location(12, 21, 12, 21)), doubleVariable(0.1, location(12, 27, 12, 29)), location(12, 21, 12, 29));
            beta.put(intVariable(2, location(12, 21, 12, 21)), doubleVariable(0.1, location(12, 32, 12, 34)), location(12, 21, 12, 34));
        }

        beta.setAlias("beta");
        beta.setLocation(location(12, 14, 12, 17));

        ArrayVariable<DoubleVariable> p = dirichlet(beta, location(13, 18, 13, 32)).sample(location(13, 34, 13, 41));
        p.setAlias("p");
        p.setLocation(location(13, 14, 13, 14));

        IntVariable n = intVariable(10, location(14, 13, 14, 14));
        n.setAlias("n");
        n.setLocation(location(14, 9, 14, 9));

        ArrayVariable<IntVariable> prior = multinomial(p, n, location(15, 19, 15, 35)).sample(location(15, 37, 15, 44));
        prior.setAlias("prior");
        prior.setLocation(location(15, 11, 15, 15));

        Bernoulli b1 = bernoulli(prior.get(intVariable(0, location(16, 40, 16, 40)), location(16, 39, 16, 41)).divide(n, location(16, 42, 16, 42)), location(16, 20, 16, 44));
        b1.setAlias("b1");
        b1.setLocation(location(16, 15, 16, 16));

        Bernoulli b2 = bernoulli(prior.get(intVariable(1, location(17, 40, 17, 40)), location(17, 39, 17, 41)).divide(n, location(17, 42, 17, 42)), location(17, 20, 17, 44));
        b2.setAlias("b2");
        b2.setLocation(location(17, 15, 17, 16));

        Bernoulli b3 = bernoulli(prior.get(intVariable(2, location(18, 40, 18, 40)), location(18, 39, 18, 41)).divide(n, location(18, 42, 18, 42)), location(18, 20, 18, 44));
        b3.setAlias("b3");
        b3.setLocation(location(18, 15, 18, 16));

        IntVariable length = observed.length(location(19, 27, 19, 32));
        length.setAlias("length");
        length.setLocation(location(19, 9, 19, 14));

        ArrayVariable<BooleanVariable> output = Variable.arrayVariable(location(20, 35, 20, 42), VariableType.BooleanVariable, length);
        output.setAlias("output");
        output.setLocation(location(20, 15, 20, 20));

        parFor(intVariable(0, location(21, 15, 21, 15)), length, intVariable(3, location(21, 31, 21, 31)), true, location(21, 5, 21, 32), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(21, 13, 21, 13));
            output.put(i, b1.sample(location(22, 24, 22, 31)), location(22, 15, 22, 31));
        });

        parFor(intVariable(1, location(23, 15, 23, 15)), length, intVariable(3, location(23, 31, 23, 31)), true, location(23, 5, 23, 32), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(23, 13, 23, 13));
            output.put(i, b2.sample(location(24, 24, 24, 31)), location(24, 15, 24, 31));
        });

        parFor(intVariable(2, location(25, 15, 25, 15)), length, intVariable(3, location(25, 31, 25, 31)), true, location(25, 5, 25, 32), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(25, 13, 25, 13));
            output.put(i, b3.sample(location(26, 24, 26, 31)), location(26, 15, 26, 31));
        });

        output.observe(observed, location(27, 12, 27, 28));

        Variable<?>[] $variableNames = {observed, beta, p, n, prior, b1, b2, b3, length, output};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "MultinomialBernoulli", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model MultinomialBernoulli(boolean[] observed) {\n    double[] beta = {0.1, 0.1, 0.1};\n    double[] p = dirichlet(beta).sample();\n    int n = 10;\n    int[] prior = multinomial(p, n).sample();\n    Bernoulli b1 = new Bernoulli(prior[0]/n);\n    Bernoulli b2 = new Bernoulli(prior[1]/n);\n    Bernoulli b3 = new Bernoulli(prior[2]/n);\n    int length = observed.length;\n    boolean[] output = new boolean[length];\n    for(int i=0; i<length; i+=3)\n        output[i] = b1.sample();\n    for(int i=1; i<length; i+=3)\n        output[i] = b2.sample();\n    for(int i=2; i<length; i+=3)\n        output[i] = b3.sample();\n    output.observe(observed);\n}\n";
    }
}