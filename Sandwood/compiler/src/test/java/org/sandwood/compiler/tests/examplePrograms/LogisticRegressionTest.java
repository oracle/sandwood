/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms;

import java.util.ArrayList;

import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.Sigmoid;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Uniform;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class LogisticRegressionTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        /*
         * Construct inputs. This could be called in a separate function, but isn't in this test for brevity.
         */

        DoubleVariable xLower = Variable.doubleVariable(0);
        DoubleVariable xUpper = Variable.doubleVariable(10);

        IntVariable nInit = Variable.intVariable(2);
        IntVariable kInit = Variable.intVariable(7);

        ArrayVariable<ArrayVariable<DoubleVariable>> xMeasured = Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), nInit);
        ArrayVariable<DoubleVariable> t = Variable.arrayVariable(1.0, kInit);
        xMeasured.put(0, t);
        t = Variable.arrayVariable(4.0, kInit);
        xMeasured.put(1, t);

        ArrayVariable<BooleanVariable> yMeasured = Variable.arrayVariable(true, nInit);

        IntVariable k = xMeasured.get(0).length();

        ArrayVariable<BooleanVariable> y = Variable.arrayVariable(VariableType.BooleanVariable, xMeasured.length());
        ArrayVariable<ArrayVariable<DoubleVariable>> x = Variable
                .arrayVariable(VariableType.arrayType(VariableType.DoubleVariable), xMeasured.length());

        ArrayVariable<DoubleVariable> weights = Variable.gaussian(0, 10).sample(k);
        DoubleVariable bias = Variable.gaussian(0, 10).sample();

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            Uniform uniform = Variable.uniform(xLower, xUpper);
            x.put(i, uniform.sample(k));
        });

        Sandwood.parFor(Variable.intVariable(0), xMeasured.length(), Variable.intVariable(1), true, (i) -> {
            ArrayVariable<DoubleVariable> indicatorValues = Variable.arrayVariable(VariableType.DoubleVariable, k);
            Sandwood.parFor(Variable.intVariable(0), k, Variable.intVariable(1), true,
                    (j) -> indicatorValues.put(j, weights.get(j).times(x.get(i).get(j))));
            DoubleVariable success = Sigmoid.sigmoid(sum(indicatorValues).add(bias));
            y.put(i, Variable.bernoulli(success).sample());
        });

        x.observe(xMeasured);
        y.observe(yMeasured);

        var output = new ArrayList<Variable<?>>();
        output.add(x);
        output.add(y);
        return output;
    }

    private DoubleVariable sum(ArrayVariable<DoubleVariable> a) {
        return Sandwood.reduce(a, 0, DoubleVariable::add);
    }
}
