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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class LDATest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        /*
         * Construct inputs. This could be called in a separate function, but isn't in this test for brevity.
         */
        var noTopics = Variable.intVariable(5);
        var vocabSize = Variable.intVariable(10);
        var documents = Variable
                .<ArrayVariable<IntVariable>>arrayVariable(VariableType.arrayType(VariableType.IntVariable), 2);
        var document = Variable.arrayVariable(1, 10);
        documents.put(0, document);
        document = Variable.arrayVariable(8, 4);
        documents.put(1, document);

        var alpha = Variable.arrayVariable(0.1, noTopics);
        var beta = Variable.arrayVariable(0.1, vocabSize);
        var phi = Variable.dirichlet(beta).sample(noTopics);
        var theta = Variable.dirichlet(alpha).sample(documents.length());
        var w = Variable.arrayVariable(VariableType.arrayType(VariableType.IntVariable), documents.length());

        Sandwood.parFor(Variable.intVariable(0), documents.length(), Variable.intVariable(1), true, (i) -> {
            ArrayVariable<IntVariable> t = Variable.arrayVariable(VariableType.IntVariable, documents.get(i).length());
            Sandwood.parFor(Variable.intVariable(0), documents.get(i).length(), Variable.intVariable(1), true, (j) -> {
                IntVariable z = Variable.categorical(theta.get(i)).sample();
                t.put(j, Variable.categorical(phi.get(z)).sample());
            });
            w.put(i, t);
        });

        w.observe(documents);

        var output = new ArrayList<Variable<?>>();
        output.add(w);
        return output;
    }
}
