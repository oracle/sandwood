/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.graphProperties.findAllRandomVariables;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

class BasicAdditionAliasedTest extends ModelTestSkeleton {

    @Override
    protected void buildGraph() {
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

        addVariable(a);
        addVariable(b);
        addVariable(c);
        addVariable(d);
        addVariable(e);
    }
}
