/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.graphProperties.findObservedChildren;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Beta;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Categorical;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class HMMAliasedTest extends ModelTestSkeleton {

    /*
     * object HMM { class sig(var flips: Array[Int], var bias: Array[Double], var m: Array[Double], var st: Array[Int])
     *
     * val model = bayes { (N: Int, states: Int) => { val v = array(states,0.1) val m =
     * Dirichlet(states,v).sample(states) val bias = Beta(1.0,1.0).sample(states)
     *
     * val st: IndexedSeq[Int] = for (i <- 1 to N) yield Categorical(states,m(st(max(0, i-1)))).sample()
     *
     * val flips = for (i <- 1 to N) Bernoulli(bias(st(i))).sample()
     *
     * observe(flips) } } }
     */

    @Override
    protected Map<RandomVariable<?, ?>, Set<Variable<?>>> buildGraph() {
        Map<RandomVariable<?, ?>, Set<Variable<?>>> result = new HashMap<>();

        IntVariable states = Variable.intVariable(2);
        states.setAlias("states");

        ArrayVariable<BooleanVariable> flipsMeasured = Variable.arrayVariable(false, 100); // A very biased coin.
        flipsMeasured.setAlias("flipsMeasured");

        ArrayVariable<DoubleVariable> v = Variable.arrayVariable(0.1, states);
        v.setAlias("v");

        Dirichlet dirichlet = Variable.dirichlet(v);
        dirichlet.setAlias("dirichlet");

        ArrayVariable<ArrayVariable<DoubleVariable>> m = dirichlet.sample(states);
        m.setAlias("m");

        Beta beta = Variable.beta(1.0, 1.0);
        beta.setAlias("beta");

        ArrayVariable<DoubleVariable> bias = beta.sample(states);
        bias.setAlias("bias");

        ArrayVariable<IntVariable> st = Variable.arrayVariable(flipsMeasured.length(), 0);
        st.setAlias("st");

        ArrayVariable<BooleanVariable> flips = Variable.arrayVariable(VariableType.BooleanVariable,
                flipsMeasured.length());
        flips.setAlias("flips");

        // We will probably add in a max function so that optimisations can trivially
        // fuse these loops.
        Categorical categorical = Variable.categorical(m.get(st.get(0)));
        categorical.setAlias("categorical");

        st.put(0, categorical.sample());

        Sandwood.parFor(Variable.intVariable(1), flipsMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            Categorical c2 = Variable.categorical(m.get(st.get(i.subtract(1))));
            c2.setAlias("c2");

            st.put(i, c2.sample());

            // dirichlet and m
            Set<Variable<?>> observableLoop = new HashSet<>();
            result.put(dirichlet, observableLoop);
        });

        Sandwood.parFor(Variable.intVariable(0), flipsMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            Bernoulli bernoulli = Variable.bernoulli(bias.get(st.get(i)));
            bernoulli.setAlias("bernoulli");

            flips.put(i, bernoulli.sample());

            // bernoulli
            Set<Variable<?>> observableLoop = new HashSet<>();
            observableLoop.add(flips.getCurrentInstance());
            result.put(bernoulli, observableLoop);

            // beta, bias, and st
            observableLoop = new HashSet<>();
            result.put(beta, observableLoop);

            // Categorical
            result.put(categorical, observableLoop);
        });

        // flipsMeasured
        Set<Variable<?>> observable = new HashSet<>();
        observable.add(flips.getCurrentInstance());

        flips.observe(flipsMeasured);

        return result;
    }
}
