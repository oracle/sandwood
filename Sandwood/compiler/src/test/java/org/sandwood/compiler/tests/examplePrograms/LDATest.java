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
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class LDATest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        /*
         * Construct inputs. This could be called in a separate function, but isn't in this test for brevity.
         */
        IntVariable noTopics = Variable.intVariable(5);
        IntVariable vocabSize = Variable.intVariable(10);
        ArrayVariable<ArrayVariable<IntVariable>> documents = Variable
                .arrayVariable(VariableType.arrayType(VariableType.IntVariable), 2);
        ArrayVariable<IntVariable> document = Variable.arrayVariable(10, 1);
        documents.put(0, document);
        document = Variable.arrayVariable(8, 4);
        documents.put(1, document);

        ArrayVariable<DoubleVariable> alpha = Variable.arrayVariable(0.1, noTopics);
        ArrayVariable<DoubleVariable> beta = Variable.arrayVariable(0.1, vocabSize);
        ArrayVariable<ArrayVariable<DoubleVariable>> phi = Variable.dirichlet(beta).sample(noTopics);
        ArrayVariable<ArrayVariable<DoubleVariable>> theta = Variable.dirichlet(alpha).sample(documents.length());
        ArrayVariable<ArrayVariable<IntVariable>> w = Variable
                .arrayVariable(VariableType.arrayType(VariableType.IntVariable), documents.length());

        Sandwood.parFor(Variable.intVariable(0), documents.length(), Variable.intVariable(1), true, (i) -> {
            ArrayVariable<IntVariable> t = Variable.arrayVariable(VariableType.IntVariable, documents.get(i).length());
            Sandwood.parFor(Variable.intVariable(0), documents.get(i).length(), Variable.intVariable(1), true, (j) -> {
                IntVariable z = Variable.categorical(theta.get(i)).sample();
                t.put(j, Variable.categorical(phi.get(z)).sample());
            });
            w.put(i, t);
        });

        w.observe(documents);

        ArrayList<Variable<?>> output = new ArrayList<>();
        output.add(w);
        return output;
    }
}
