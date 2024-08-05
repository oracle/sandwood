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
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Uniform;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class LinearRegressionAliasedTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        /*
         * Construct inputs. This could be called in a separate function, but isn't in this test for brevity.
         */
        DoubleVariable l = Variable.doubleVariable(0);
        l.setAlias("l");
        DoubleVariable u = Variable.doubleVariable(10);
        u.setAlias("u");

        IntVariable n = Variable.intVariable(2);
        n.setAlias("n");
        IntVariable kInit = Variable.intVariable(7);
        kInit.setAlias("kInit");

        ArrayVariable<ArrayVariable<DoubleVariable>> xMeasured = Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), n);
        xMeasured.setAlias("xMeasured");
        ArrayVariable<DoubleVariable> t = Variable.arrayVariable(1.0, kInit);
        t.setAlias("t");
        xMeasured.put(0, t);
        t = Variable.arrayVariable(4.0, kInit);
        t.setAlias("t");
        xMeasured.put(1, t);
        ArrayVariable<DoubleVariable> yMeasured = Variable.arrayVariable(3.5, n);
        yMeasured.setAlias("yMeasured");

        IntVariable k = xMeasured.get(0).length();
        k.setAlias("k");

        ArrayVariable<DoubleVariable> y = Variable.arrayVariable(VariableType.DoubleVariable, xMeasured.length());
        y.setAlias("y");
        ArrayVariable<ArrayVariable<DoubleVariable>> x = Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), xMeasured.length());
        x.setAlias("x");

        ArrayVariable<DoubleVariable> weights = Variable.gaussian(0, 10).sample(k);
        weights.setAlias("weights");
        DoubleVariable bias = Variable.gaussian(0, 10).sample();
        bias.setAlias("bias");
        DoubleVariable tau = Variable.inverseGamma(3.0, 1.0).sample();
        tau.setAlias("tau");

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            Uniform uniform = Variable.uniform(l, u);
            uniform.setAlias("uniform");
            x.put(i, uniform.sample(k));
        });

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            ArrayVariable<DoubleVariable> phi = Variable.arrayVariable(VariableType.DoubleVariable, k);
            phi.setAlias("phi");
            Sandwood.parFor(Variable.intVariable(0), k, Variable.intVariable(1), true, (j) -> {
                j.setAlias("j");
                phi.put(j, weights.get(j).times(x.get(i).get(j)));
            });
            y.put(i, Variable.gaussian(sum(phi).add(bias), tau).sample());
        });

        x.observe(xMeasured);
        y.observe(yMeasured);

        ArrayList<Variable<?>> output = new ArrayList<>();
        output.add(x);
        output.add(y);
        return output;
    }

    private DoubleVariable sum(ArrayVariable<DoubleVariable> a) {
        return Sandwood.reduce(a, 0, DoubleVariable::add);
    }
}
