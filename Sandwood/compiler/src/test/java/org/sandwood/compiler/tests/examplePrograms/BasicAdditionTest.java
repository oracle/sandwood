/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms;

import java.util.ArrayList;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class BasicAdditionTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        IntVariable a = Variable.intVariable(5);
        IntVariable b = Variable.intVariable(10);
        IntVariable c = a.add(b);
        IntVariable d = b.add(c);
        IntVariable e = b.add(a);

        ArrayList<Variable<?>> output = new ArrayList<>();
        output.add(e);
        output.add(d);
        return output;
    }
}
