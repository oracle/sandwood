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
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Categorical;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Dirichlet;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.InverseGamma;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class GaussianMixtureAliasedTest extends ModelTestSkeleton {

    @Override
    protected void buildGraph() {

        IntVariable k = Variable.intVariable(5);
        k.setAlias("k");
        addVariable(k);

        ArrayVariable<DoubleVariable> xMeasured = Variable.arrayVariable(1.0, 10);
        xMeasured.setAlias("xMeasured");
        addVariable(xMeasured);

        ArrayVariable<DoubleVariable> alpha = Variable.arrayVariable(1.0, k);
        alpha.setAlias("alpha");
        addVariable(alpha);

        Dirichlet dirichlet = Variable.dirichlet(alpha);
        dirichlet.setAlias("dirichlet");
        addVariable(dirichlet);
        addRandomVariable(dirichlet);

        ArrayVariable<DoubleVariable> phi = dirichlet.sample();
        phi.setAlias("phi");
        addVariable(phi);

        Gaussian gaussian = Variable.gaussian(0, 20);
        gaussian.setAlias("gaussian");
        addVariable(gaussian);
        addRandomVariable(gaussian);

        ArrayVariable<DoubleVariable> mu = gaussian.sample(k);
        mu.setAlias("mu");
        addVariable(mu);

        InverseGamma inverseGamma = Variable.inverseGamma(1, 1);
        inverseGamma.setAlias("inverseGamma");
        addVariable(inverseGamma);
        addRandomVariable(inverseGamma);

        ArrayVariable<DoubleVariable> sigma = inverseGamma.sample(k);
        sigma.setAlias("sigma");
        addVariable(sigma);

        ArrayVariable<DoubleVariable> x = Variable.arrayVariable(VariableType.DoubleVariable, xMeasured.length());
        x.setAlias("x");
        addVariable(x);

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            addVariable(i);

            Categorical categorical = Variable.categorical(phi);
            categorical.setAlias("categorical");
            addVariable(categorical);
            addRandomVariable(categorical);

            IntVariable z = categorical.sample();
            z.setAlias("z");
            addVariable(z);

            Gaussian g2 = Variable.gaussian(mu.get(z), sigma.get(z));
            g2.setAlias("g2");
            addVariable(g2);
            addRandomVariable(g2);

            x.put(i, g2.sample());
            addVariable(x.getCurrentInstance());
        });

        x.observe(xMeasured);
    }
}
