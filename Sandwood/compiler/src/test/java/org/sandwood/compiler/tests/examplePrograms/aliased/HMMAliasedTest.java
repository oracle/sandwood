/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms.aliased;

import java.util.ArrayList;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class HMMAliasedTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        IntVariable states = Variable.intVariable(2);
        states.setAlias("states");
        ArrayVariable<BooleanVariable> flipsMeasured = Variable.arrayVariable(false, 100); // A very biased coin.
        flipsMeasured.setAlias("flipsMeasured");

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(0.1, states);
        v.setAlias("v");
        ArrayVariable<ArrayVariable<DoubleVariable>> m = Variable.dirichlet(v).sample(states);
        m.setAlias("m");
        ArrayVariable<DoubleVariable> bias = Variable.beta(1.0, 1.0).sample(states);
        bias.setAlias("bias");

        ArrayVariable<IntVariable> st = Variable.arrayVariable(0, flipsMeasured.length());
        st.setAlias("st");
        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(VariableType.BooleanVariable,
                flipsMeasured.length());
        flips.setAlias("flips");

        // We will probably add in a max function so that optimisations can trivially
        // fuse these loops.
        st.put(0, Variable.categorical(m.get(st.get(0))).sample());
        Sandwood.parFor(Variable.intVariable(1), flipsMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            st.put(i, Variable.categorical(m.get(st.get(i.subtract(1)))).sample());
        });

        Sandwood.parFor(Variable.intVariable(0), flipsMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            flips.put(i, Variable.bernoulli(bias.get(st.get(i))).sample());
        });

        flips.observe(flipsMeasured);

        ArrayList<Variable<?>> output = new ArrayList<>();
        output.add(flips);
        return output;
    }
}
