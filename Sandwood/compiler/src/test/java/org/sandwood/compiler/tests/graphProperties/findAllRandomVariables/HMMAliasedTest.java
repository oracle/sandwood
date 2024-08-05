/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.graphProperties.findAllRandomVariables;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Beta;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Categorical;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class HMMAliasedTest extends ModelTestSkeleton {

    @Override
    protected void buildGraph() {
        IntVariable states = Variable.intVariable(2);
        states.setAlias("states");
        addVariable(states);

        ArrayVariable<BooleanVariable> flipsMeasured = Variable.arrayVariable(false, 100); // A very biased coin.
        flipsMeasured.setAlias("flipsMeasured");
        addVariable(flipsMeasured);

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(0.1, states);
        v.setAlias("v");
        addVariable(v);

        Dirichlet dirichlet = Variable.dirichlet(v);
        dirichlet.setAlias("dirichlet");
        addVariable(dirichlet);
        addRandomVariable(dirichlet);

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet.sample(states);
        m.setAlias("m");
        addVariable(m);

        Beta beta = Variable.beta(1.0, 1.0);
        beta.setAlias("beta");
        addVariable(beta);
        addRandomVariable(beta);

        ArrayVariable<DoubleVariable> bias = beta.sample(states);
        bias.setAlias("bias");
        addVariable(bias);

        ArrayVariable<IntVariable> st = Variable.arrayVariable(0, flipsMeasured.length());
        st.setAlias("st");
        addVariable(st);

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(VariableType.BooleanVariable,
                flipsMeasured.length());
        flips.setAlias("flips");
        addVariable(flips);

        // We will probably add in a max function so that optimisations can trivially
        // fuse these loops.
        Categorical categorical = Variable.categorical(m.get(st.get(0)));
        categorical.setAlias("categorical");
        addVariable(categorical);
        addRandomVariable(categorical);

        st.put(0, categorical.sample());
        addVariable(st.getCurrentInstance());
        Sandwood.parFor(Variable.intVariable(1), flipsMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            addVariable(i);

            Categorical c2 = Variable.categorical(m.get(st.get(i.subtract(1))));
            c2.setAlias("c2");
            addVariable(c2);
            addRandomVariable(c2);

            st.put(i, c2.sample());
            addVariable(st.getCurrentInstance());
        });

        Sandwood.parFor(Variable.intVariable(0), flipsMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            addVariable(i);

            Bernoulli bernoulli = Variable.bernoulli(bias.get(st.get(i)));
            bernoulli.setAlias("bernoulli");
            addVariable(bernoulli);
            addRandomVariable(bernoulli);

            flips.put(i, bernoulli.sample());
            addVariable(flips.getCurrentInstance());
        });

        flips.observe(flipsMeasured);
    }
}
