/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms.var;

import java.util.ArrayList;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class HMMTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        var states = Variable.intVariable(2);
        var flipsMeasured = Variable.arrayVariable(false, 100); // A very biased coin.

        var v = Variable.arrayVariable(0.1, states);
        var m = Variable.dirichlet(v).sample(states);
        var bias = Variable.beta(1.0, 1.0).sample(states);

        var st = Variable.arrayVariable(0, flipsMeasured.length());
        var flips = Variable.<BooleanVariable>arrayVariable(VariableType.BooleanVariable, flipsMeasured.length());

        st.put(0, Variable.categorical(m.get(st.get(0))).sample());
        Sandwood.parFor(Variable.intVariable(1), flipsMeasured.length(), Variable.intVariable(1), true,
                (i) -> st.put(i, Variable.categorical(m.get(st.get(i.subtract(1)))).sample()));

        Sandwood.parFor(Variable.intVariable(0), flipsMeasured.length(), Variable.intVariable(1), true,
                (i) -> flips.put(i, Variable.bernoulli(bias.get(st.get(i))).sample()));

        flips.observe(flipsMeasured);

        var output = new ArrayList<Variable<?>>();
        output.add(flips);
        return output;
    }
}
