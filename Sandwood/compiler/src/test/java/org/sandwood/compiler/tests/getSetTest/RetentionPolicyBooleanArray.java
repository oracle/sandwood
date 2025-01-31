/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.getSetTest;

import static org.sandwood.compiler.dataflowGraph.Sandwood.compileAPI;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.arrayVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.beta;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.doubleVariable;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;

public class RetentionPolicyBooleanArray extends ModelTestSkeleton {

    @Override
    protected List<String> getVariables() {
        List<String> l = new ArrayList<>();
        l.add("bias");
        return l;
    }

    @Override
    protected CompilationDesc buildClass(CompilationOptions opts) {
        DoubleVariable a = doubleVariable(1.0);
        a.setAlias("a");
        DoubleVariable b = doubleVariable(1.0);
        b.setAlias("b");
        ArrayVariable<BooleanVariable> bias = arrayVariable(VariableType.BooleanVariable, 1);
        bias.setAlias("bias");
        bias.put(0, beta(a, b).sample().eq(doubleVariable(0.5)));
        String[] constructorArgs = {};
        return compileAPI(opts, bias, "ClassName", "", constructorArgs, "", null);
    }
}
