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

public class DirichletBernoulli extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<BooleanVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.BooleanVariable), location(11, 33, 11, 50));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(12, 28, 12, 30), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(12, 35, 12, 37)); }, intVariable(2, location(12, 29, 12, 29)));
        v.setAlias("v");
        v.setLocation(location(12, 14, 12, 14));

        ArrayVariable<DoubleVariable> prior = dirichlet(v, location(13, 22, 13, 33)).sample(location(13, 35, 13, 42));
        prior.setAlias("prior");
        prior.setLocation(location(13, 14, 13, 18));

        Bernoulli b1 = bernoulli(prior.get(intVariable(0, location(14, 40, 14, 40)), location(14, 39, 14, 41)), location(14, 20, 14, 42));
        b1.setAlias("b1");
        b1.setLocation(location(14, 15, 14, 16));

        Bernoulli b2 = bernoulli(prior.get(intVariable(1, location(15, 40, 15, 40)), location(15, 39, 15, 41)), location(15, 20, 15, 42));
        b2.setAlias("b2");
        b2.setLocation(location(15, 15, 15, 16));

        IntVariable length = observed.length(location(16, 27, 16, 32));
        length.setAlias("length");
        length.setLocation(location(16, 9, 16, 14));

        ArrayVariable<BooleanVariable> output = Variable.arrayVariable(location(17, 35, 17, 42), VariableType.BooleanVariable, length);
        output.setAlias("output");
        output.setLocation(location(17, 15, 17, 20));

        parFor(intVariable(0, location(18, 15, 18, 15)), length.divide(intVariable(2, location(18, 27, 18, 27)), location(18, 26, 18, 26)), intVariable(1, location(18, 30, 18, 32)), true, location(18, 5, 18, 33), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 13, 18, 13));
            output.put(i, b1.sample(location(19, 24, 19, 31)), location(19, 15, 19, 31));
        });

        parFor(length.divide(intVariable(2, location(20, 22, 20, 22)), location(20, 21, 20, 21)), length, intVariable(1, location(20, 35, 20, 37)), true, location(20, 5, 20, 38), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(20, 13, 20, 13));
            output.put(i, b2.sample(location(21, 24, 21, 31)), location(21, 15, 21, 31));
        });

        output.observe(observed, location(22, 12, 22, 28));

        Variable<?>[] $variableNames = {observed, v, prior, b1, b2, length, output};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "DirichletBernoulli", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DirichletBernoulli(boolean[] observed) {\n    double[] v = new double[2] <~ 0.1;\n    double[] prior = dirichlet(v).sample();\n    Bernoulli b1 = new Bernoulli(prior[0]);\n    Bernoulli b2 = new Bernoulli(prior[1]);\n    int length = observed.length;\n    boolean[] output = new boolean[length];\n    for(int i=0; i<length/2; i++)\n        output[i] = b1.sample();\n    for(int i=length/2; i<length; i++)\n        output[i] = b2.sample();\n    output.observe(observed);\n}\n";
    }
}