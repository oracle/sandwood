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

public class GaussianMixtureTest extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> xMeasured = observeArray("xMeasured", VariableType.arrayType(VariableType.DoubleVariable), location(11, 27, 11, 44));

        IntVariable k = intVariable(5, location(13, 17, 13, 17));
        k.setAlias("k");
        k.setLocation(location(13, 13, 13, 13));

        ArrayVariable<DoubleVariable> alpha = Variable.arrayVariable(location(15, 36, 15, 38), VariableType.DoubleVariable, k);
        alpha.setAlias("alpha");
        alpha.setLocation(location(15, 18, 15, 22));

        parFor(intVariable(0, location(16, 20, 16, 20)), k, intVariable(1, location(16, 19, 16, 22)), true, location(16, 9, 16, 25), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(16, 17, 16, 17));
            alpha.put(i, doubleVariable(1.0, location(17, 24, 17, 26)), location(17, 18, 17, 26));
        });

        ArrayVariable<DoubleVariable> phi = dirichlet(alpha, location(19, 24, 19, 39)).sample(location(19, 41, 19, 48));
        phi.setAlias("phi");
        phi.setLocation(location(19, 18, 19, 20));

        ArrayVariable<DoubleVariable> mu = gaussian(intVariable(0, location(20, 32, 20, 32)), intVariable(20, location(20, 35, 20, 36)), location(20, 23, 20, 37)).sample(k, location(20, 39, 20, 47));
        mu.setAlias("mu");
        mu.setLocation(location(20, 18, 20, 19));

        ArrayVariable<DoubleVariable> sigma = inverseGamma(intVariable(1, location(21, 39, 21, 39)), intVariable(1, location(21, 42, 21, 42)), location(21, 26, 21, 43)).sample(k, location(21, 45, 21, 53));
        sigma.setAlias("sigma");
        sigma.setLocation(location(21, 18, 21, 22));

        ArrayVariable<DoubleVariable> x = Variable.arrayVariable(location(23, 32, 23, 49), VariableType.DoubleVariable, xMeasured.length(location(23, 43, 23, 48)));
        x.setAlias("x");
        x.setLocation(location(23, 18, 23, 18));

        parFor(intVariable(0, location(24, 20, 24, 20)), xMeasured.length(location(24, 33, 24, 38)), intVariable(1, location(24, 19, 24, 22)), true, location(24, 9, 24, 40), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(24, 17, 24, 17));
            IntVariable z = categorical(phi, location(25, 21, 25, 36)).sample(location(25, 38, 25, 45));
            z.setAlias("z");
            z.setLocation(location(25, 17, 25, 17));

            x.put(i, gaussian(mu.get(z, location(26, 31, 26, 33)), sigma.get(z, location(26, 41, 26, 43)), location(26, 20, 26, 44)).sample(location(26, 46, 26, 53)), location(26, 14, 26, 53));

        });

        x.observe(xMeasured, location(29, 11, 29, 28));

        Variable<?>[] $variableNames = {xMeasured, k, alpha, phi, mu, sigma, x};
        String[] $constructorArgs = {"xMeasured"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "GaussianMixtureTest", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel GaussianMixtureTest(double[] xMeasured) {\n\n        int k = 5;\n\n        double[] alpha = new double[k];\n        for(int i:[0..k)) \n            alpha[i] = 1.0;\n        \n        double[] phi = dirichlet(alpha).sample();\n        double[] mu = gaussian(0, 20).sample(k);\n        double[] sigma = inverseGamma(1, 1).sample(k);\n        \n        double[] x = new double[xMeasured.length];\n        for(int i:[0..xMeasured.length)) {\n            int z = categorical(phi).sample();\n            x[i] = gaussian(mu[z], sigma[z]).sample();\n        }\n        \n        x.observe(xMeasured);\n}\n";
    }
}