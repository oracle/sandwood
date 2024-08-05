/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.tests.examplePrograms.var;

import java.util.ArrayList;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.tests.examplePrograms.ModelTestSkeleton;

class BasicAdditionTest extends ModelTestSkeleton {

    @Override
    protected ArrayList<Variable<?>> buildGraph() {
        var a = Variable.intVariable(5);
        var b = Variable.intVariable(10);
        var c = a.add(b);
        var d = b.add(c);
        var e = b.add(a);

        var output = new ArrayList<Variable<?>>();
        output.add(e);
        output.add(d);
        return output;
    }
}
