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
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Categorical;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.InverseGamma;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class GaussianMixtureAliasedTest extends ModelTestSkeleton {

    /*
     * object GaussianMixture { class sig(var z: Array[Int], var x: Array[Double], var mu: Array[Double], var sigma:
     * Array[Double], var phi: Array[Double])
     *
     * val model = bayes { (N: Int, K: Int) => { val alpha = array(K,1)
     *
     * val phi = Dirichlet(K,alpha).sample() val mu = Gaussian(0,20).sample(K) val sigma = InverseGamma(1,1).sample(K)
     *
     * val x = for(i <- 1 to N) { val z = Categorical(K, phi).sample() Gaussian(mu(z), sigma(z)).sample() }
     *
     * observe(x) } } }
     */

    @Override
    protected Map<RandomVariable<?, ?>, Set<Variable<?>>> buildGraph() {
        Map<RandomVariable<?, ?>, Set<Variable<?>>> result = new HashMap<>();

        IntVariable k = Variable.intVariable(5);
        k.setAlias("k");

        ArrayVariable<DoubleVariable> xMeasured = Variable.arrayVariable(1.0, 10);
        xMeasured.setAlias("xMeasured");

        ArrayVariable<DoubleVariable> alpha = Variable.arrayVariable(1.0, k);
        alpha.setAlias("alpha");

        Dirichlet dirichlet = Variable.dirichlet(alpha);
        dirichlet.setAlias("dirichlet");

        ArrayVariable<DoubleVariable> phi = dirichlet.sample();
        phi.setAlias("phi");

        Gaussian gaussian = Variable.gaussian(0, 20);
        gaussian.setAlias("gaussian");

        ArrayVariable<DoubleVariable> mu = gaussian.sample(k);
        mu.setAlias("mu");

        InverseGamma inverseGamma = Variable.inverseGamma(1, 1);
        inverseGamma.setAlias("inverseGamma");
        ArrayVariable<DoubleVariable> sigma = inverseGamma.sample(k);
        sigma.setAlias("sigma");

        ArrayVariable<DoubleVariable> x = Variable.arrayVariable(VariableType.DoubleVariable, xMeasured.length());
        x.setAlias("x");
        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");

            Categorical categorical = Variable.categorical(phi);
            categorical.setAlias("categorical");

            IntVariable z = categorical.sample();
            z.setAlias("z");

            Gaussian g2 = Variable.gaussian(mu.get(z), sigma.get(z));
            g2.setAlias("g2");

            x.put(i, g2.sample());

            // i
            Set<Variable<?>> observedLoop = new HashSet<>();
            observedLoop.add(x.getCurrentInstance());

            // Categorical
            result.put(categorical, new HashSet<>());

            // z
            observedLoop = new HashSet<>();

            // k
            observedLoop = new HashSet<>();

            // Categorical
            observedLoop = new HashSet<>();
            result.put(categorical, observedLoop);

            // mu
            observedLoop = new HashSet<>();

            // g2
            result.put(g2, new HashSet<>());

            // sigma
            observedLoop = new HashSet<>();

            // dirichlet
            observedLoop = new HashSet<>();
            result.put(dirichlet, observedLoop);

            // phi
            observedLoop = new HashSet<>();

            // Gaussian
            observedLoop = new HashSet<>();
            observedLoop.add(x.getCurrentInstance());
            result.put(g2, observedLoop);
        });

        x.observe(xMeasured);

        return result;
    }
}
