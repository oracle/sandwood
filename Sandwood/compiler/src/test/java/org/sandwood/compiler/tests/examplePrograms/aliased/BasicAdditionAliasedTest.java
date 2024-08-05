/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms.aliased;

import java.util.ArrayList;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class BasicAdditionAliasedTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        IntVariable a = Variable.intVariable(5);
        a.setAlias("a");
        IntVariable b = Variable.intVariable(10);
        b.setAlias("b");
        IntVariable c = a.add(b);
        c.setAlias("c");
        IntVariable d = b.add(c);
        d.setAlias("d");
        IntVariable e = b.add(a);
        e.setAlias("e");

        ArrayList<Variable<?>> output = new ArrayList<>();
        output.add(e);
        output.add(d);
        return output;
    }
}
