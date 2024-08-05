/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.graphProperties.findAllRandomVariables;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Bernoulli;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Gaussian;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Uniform;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class LogisticRegressionAliasedTest extends ModelTestSkeleton {

    @Override
    protected void buildGraph() {
        /*
         * Construct inputs. This could be called in a separate function, but isn't in this test for brevity.
         */

        DoubleVariable xLower = Variable.doubleVariable(0);
        xLower.setAlias("xLower");
        addVariable(xLower);

        DoubleVariable xUpper = Variable.doubleVariable(10);
        xUpper.setAlias("xUpper");
        addVariable(xUpper);

        IntVariable nInit = Variable.intVariable(2);
        nInit.setAlias("nInit");
        addVariable(nInit);

        IntVariable kInit = Variable.intVariable(7);
        kInit.setAlias("kInit");
        addVariable(kInit);

        ArrayVariable<ArrayVariable<DoubleVariable>> xMeasured = Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), nInit);
        xMeasured.setAlias("xMeasured");
        addVariable(xMeasured);

        ArrayVariable<DoubleVariable> t = Variable.arrayVariable(1.0, kInit);
        t.setAlias("t");
        addVariable(t);

        xMeasured.put(0, t);
        addVariable(xMeasured.getCurrentInstance());

        t = Variable.arrayVariable(4.0, kInit);
        t.setAlias("t2");
        addVariable(t);

        xMeasured.put(1, t);
        addVariable(xMeasured.getCurrentInstance());

        ArrayVariable<BooleanVariable> yMeasured = Variable.arrayVariable(true, nInit);
        yMeasured.setAlias("yMeasured");
        addVariable(yMeasured);

        IntVariable k = xMeasured.get(0).length();
        k.setAlias("k");
        addVariable(k);

        ArrayVariable<BooleanVariable> y = Variable.arrayVariable(VariableType.BooleanVariable, xMeasured.length());
        y.setAlias("y");
        addVariable(y);

        ArrayVariable<ArrayVariable<DoubleVariable>> x = Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), xMeasured.length());
        x.setAlias("x");
        addVariable(x);

        Gaussian gaussian = Variable.gaussian(0, 10);
        gaussian.setAlias("gaussian");
        addVariable(gaussian);
        addRandomVariable(gaussian);

        ArrayVariable<DoubleVariable> weights = gaussian.sample(k);
        weights.setAlias("weights");
        addVariable(weights);

        Gaussian g2 = Variable.gaussian(0, 10);
        g2.setAlias("g2");
        addVariable(g2);
        addRandomVariable(g2);

        DoubleVariable bias = g2.sample();
        bias.setAlias("bias");
        addVariable(bias);

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            addVariable(i);

            Uniform uniform = Variable.uniform(xLower, xUpper);
            uniform.setAlias("uniform");
            addVariable(uniform);
            addRandomVariable(uniform);

            x.put(i, uniform.sample(k));
            addVariable(x.getCurrentInstance());
        });

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            i.setAlias("i");
            addVariable(i);

            ArrayVariable<DoubleVariable> indicatorValues = Variable.arrayVariable(VariableType.DoubleVariable, k);
            indicatorValues.setAlias("indicatorValues");
            addVariable(indicatorValues);

            Sandwood.parFor(Variable.intVariable(0), k, Variable.intVariable(1), true, (j) -> {
                j.setAlias("j");
                addVariable(j);

                indicatorValues.put(j, weights.get(j).times(x.get(i).get(j)));
                addVariable(indicatorValues.getCurrentInstance());
            });

            DoubleVariable success = Sigmoid.sigmoid(sum(indicatorValues).add(bias));
            success.setAlias("success");
            addVariable(success);

            Bernoulli bernoulli = Variable.bernoulli(success);
            bernoulli.setAlias("bernoulli");
            addVariable(bernoulli);
            addRandomVariable(bernoulli);

            y.put(i, bernoulli.sample());
            addVariable(y.getCurrentInstance());
        });

        x.observe(xMeasured);
        y.observe(yMeasured);
    }

    private DoubleVariable sum(ArrayVariable<DoubleVariable> a) {
        return Sandwood.reduce(a, 0, DoubleVariable::add);
    }
}
