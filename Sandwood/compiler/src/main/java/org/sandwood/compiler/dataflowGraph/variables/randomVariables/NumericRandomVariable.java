/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.dataflowGraph.NumberProperties;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;

public interface NumericRandomVariable<A extends NumberVariable<A>, B extends NumericRandomVariable<A, B>>
        extends RandomVariable<A, B>, NumberProperties<A> {

}
