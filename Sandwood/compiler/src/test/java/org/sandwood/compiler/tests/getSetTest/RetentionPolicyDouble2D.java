/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.getSetTest;

import static org.sandwood.compiler.dataflowGraph.Sandwood.compileAPI;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.bernoulli;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.beta;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.doubleVariable;
import static org.sandwood.compiler.dataflowGraph.variables.Variable.observeBoolean;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;

public class RetentionPolicyDouble2D extends ModelTestSkeleton {

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
        ArrayVariable<ArrayVariable<DoubleVariable>> bias =  Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), 1);
        bias.setAlias("bias");
        bias.put(0, beta(a, b).sample(1));
        Bernoulli bernoulli = bernoulli(bias.get(0).get(0));
        bernoulli.setAlias("bernoulli");
        BooleanVariable flip = bernoulli.sample();
        flip.setAlias("flip");
        BooleanVariable flipMeasured = observeBoolean("flipMeasured");
        flip.observe(flipMeasured);
        String[] constructorArgs = { "flipMeasured" };
        return compileAPI(opts, flip, "ClassName", "", constructorArgs, "", null);
    }
}
