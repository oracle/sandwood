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
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class GaussianMixtureTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        var k = Variable.intVariable(5);
        // ArrayVariable<DoubleVariable>
        var xMeasured = Variable.arrayVariable(1.0, 10);

        var alpha = Variable.arrayVariable(1.0, k);
        var phi = Variable.dirichlet(alpha).sample();
        var mu = Variable.gaussian(0, 20).sample(k);
        var sigma = Variable.inverseGamma(1, 1).sample(k);

        ArrayVariable<DoubleVariable> x = Variable.arrayVariable(VariableType.DoubleVariable, xMeasured.length());
        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            IntVariable z = Variable.categorical(phi).sample();
            x.put(i, Variable.gaussian(mu.get(z), sigma.get(z)).sample());
        });

        x.observe(xMeasured);

        var output = new ArrayList<Variable<?>>();
        output.add(x);
        return output;
    }
}
