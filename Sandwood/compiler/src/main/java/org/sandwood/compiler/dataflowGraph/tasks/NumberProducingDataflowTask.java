/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks;

import org.sandwood.compiler.dataflowGraph.NumberProperties;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;

public interface NumberProducingDataflowTask<A extends NumberVariable<A>>
        extends ProducingDataflowTask<A>, NumberProperties<A> {

}
