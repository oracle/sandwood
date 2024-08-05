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

public class ParallelMK3 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<DoubleVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.DoubleVariable), location(11, 26, 11, 42));

        ArrayVariable<DoubleVariable> generated = Variable.arrayVariable(location(12, 36, 12, 52), VariableType.DoubleVariable, observed.length(location(12, 46, 12, 51)));
        generated.setAlias("generated");
        generated.setLocation(location(12, 14, 12, 22));

        ArrayVariable<DoubleVariable> indirection = Variable.arrayVariable(location(13, 38, 13, 54), VariableType.DoubleVariable, observed.length(location(13, 48, 13, 53)));
        indirection.setAlias("indirection");
        indirection.setLocation(location(13, 14, 13, 24));

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(location(14, 28, 14, 31), VariableType.DoubleVariable, () -> { return doubleVariable(0.1, location(14, 36, 14, 38)); }, intVariable(10, location(14, 29, 14, 30)));
        v.setAlias("v");
        v.setLocation(location(14, 14, 14, 14));

        ArrayVariable<DoubleVariable> sample = dirichlet(v, location(17, 23, 17, 34)).sample(location(17, 36, 17, 43));
        sample.setAlias("sample");
        sample.setLocation(location(17, 14, 17, 19));

        parFor(intVariable(0, location(18, 15, 18, 15)), observed.length(location(18, 29, 18, 34)), intVariable(1, location(18, 37, 18, 39)), true, location(18, 5, 18, 40), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(18, 13, 18, 13));
            indirection.put(i, sample.get(i, location(19, 32, 19, 34)), location(19, 20, 19, 34));
            generated.put(i, gaussian(sample.get(i, location(20, 39, 20, 41)), indirection.get(i, location(20, 55, 20, 57)), location(20, 24, 20, 58)).sample(location(20, 60, 20, 67)), location(20, 18, 20, 67));

        });

        generated.observe(observed, location(23, 15, 23, 31));

        Variable<?>[] $variableNames = {observed, generated, indirection, v, sample};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ParallelMK3", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK3(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length];\n    double[] v = new double[10] <~ 0.1;\n\n\n    double[] sample = dirichlet(v).sample();\n    for(int i=0; i<observed.length; i++) {\n        indirection[i] = sample[i];\n        generated[i] = gaussian(sample[i], indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
    }
}