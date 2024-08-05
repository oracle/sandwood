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

public class ParallelMK4 extends GeneratedAPIBuilder {
    @Override
    public CompilationDesc buildClass(CompilationOptions opts) {
        //Allocating initial observed parameters
        ArrayVariable<IntVariable> observed = observeArray("observed", VariableType.arrayType(VariableType.IntVariable), location(11, 26, 11, 39));

        ArrayVariable<IntVariable> generated = Variable.arrayVariable(location(12, 30, 12, 46), VariableType.IntVariable, observed.length(location(12, 40, 12, 45)));
        generated.setAlias("generated");
        generated.setLocation(location(12, 11, 12, 19));

        ArrayVariable<ArrayVariable<DoubleVariable>> indirection1 = Variable.arrayVariable(location(13, 41, 13, 61), VariableType.arrayType(VariableType.DoubleVariable), observed.length(location(13, 51, 13, 56)), intVariable(10, location(13, 59, 13, 60)));
        indirection1.setAlias("indirection1");
        indirection1.setLocation(location(13, 16, 13, 27));

        ArrayVariable<ArrayVariable<DoubleVariable>> indirection2 = Variable.arrayVariable(location(14, 41, 14, 61), VariableType.arrayType(VariableType.DoubleVariable), observed.length(location(14, 51, 14, 56)), intVariable(10, location(14, 59, 14, 60)));
        indirection2.setAlias("indirection2");
        indirection2.setLocation(location(14, 16, 14, 27));

        parFor(intVariable(0, location(16, 15, 16, 15)), observed.length(location(16, 29, 16, 34)), intVariable(1, location(16, 37, 16, 39)), true, location(16, 5, 16, 40), (i) -> { 
            i.setAlias("i");
            i.setLocation(location(16, 13, 16, 13));
            parFor(intVariable(0, location(17, 19, 17, 19)), intVariable(10, location(17, 24, 17, 25)), intVariable(1, location(17, 28, 17, 30)), true, location(17, 9, 17, 31), (j) -> { 
                j.setAlias("j");
                j.setLocation(location(17, 17, 17, 17));
                indirection1.get(i, location(18, 25, 18, 27)).put(j, uniform(doubleVariable(0.0, location(18, 42, 18, 44)), doubleVariable(1.0, location(18, 47, 18, 49)), location(18, 34, 18, 50)).sample(location(18, 52, 18, 59)), location(18, 28, 18, 59));

            });


        });

        parFor(intVariable(0, location(22, 15, 22, 15)), observed.length(location(22, 29, 22, 34)), intVariable(1, location(22, 37, 22, 39)), true, location(22, 5, 22, 40), (k) -> { 
            k.setAlias("k");
            k.setLocation(location(22, 13, 22, 13));
            parFor(intVariable(0, location(23, 19, 23, 19)), intVariable(10, location(23, 24, 23, 25)), intVariable(1, location(23, 28, 23, 30)), true, location(23, 9, 23, 31), (l) -> { 
                l.setAlias("l");
                l.setLocation(location(23, 17, 23, 17));
                indirection2.get(k, location(24, 25, 24, 27)).put(l, indirection1.get(k, location(24, 46, 24, 48)).get(l, location(24, 49, 24, 51)), location(24, 28, 24, 51));

            });


        });

        parFor(intVariable(0, location(28, 15, 28, 15)), observed.length(location(28, 29, 28, 34)), intVariable(1, location(28, 37, 28, 39)), true, location(28, 5, 28, 40), (m) -> { 
            m.setAlias("m");
            m.setLocation(location(28, 13, 28, 13));
            generated.put(m, categorical(indirection2.get(m, location(29, 48, 29, 50)), location(29, 24, 29, 51)).sample(location(29, 53, 29, 60)), location(29, 18, 29, 60));

        });

        generated.observe(observed, location(32, 15, 32, 31));

        Variable<?>[] $variableNames = {observed, generated, indirection1, indirection2};
        String[] $constructorArgs = {"observed"};
        Set<String> $helperClasses = new HashSet<>();
        return compileAPI(opts, $variableNames, "ParallelMK4", $helperClasses, "org.sandwood.compiler.tests.parser", $constructorArgs, getOriginalModel(), null);
    }

    private static String getOriginalModel() { 
        return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK4(int[] observed) {\n    int[] generated = new int[observed.length];\n    double[][] indirection1 = new double[observed.length][10];\n    double[][] indirection2 = new double[observed.length][10];\n\n    for(int i=0; i<observed.length; i++) {\n        for(int j=0; j<10; j++) {\n            indirection1[i][j] = uniform(0.0, 1.0).sample();\n        }\n    }\n    \n    for(int k=0; k<observed.length; k++) {\n        for(int l=0; l<10; l++) {\n            indirection2[k][l] = indirection1[k][l];\n        }\n    }\n    \n    for(int m=0; m<observed.length; m++) {\n        generated[m] = categorical(indirection2[m]).sample();\n    }\n\n    generated.observe(observed);\n}";
    }
}