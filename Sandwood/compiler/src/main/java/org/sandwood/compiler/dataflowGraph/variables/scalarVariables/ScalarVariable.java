/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.scalarVariables;

import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.VariableImplementation;
import org.sandwood.compiler.exceptions.ConstraintAlreadySetException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;

//Implement proxy in scalar only for now as I am unsure about interesting sides effects with arrays etc.
public abstract class ScalarVariable<A extends ScalarVariable<A>> extends VariableImplementation<A> {

    protected ScalarVariable(ProducingDataflowTask<A> parent) {
        super(parent);
    }

    @Override
    public abstract A getCurrentInstance();

    public void observe(A source) throws ConstraintAlreadySetException {
        observe(source, null);
    }

    public abstract void observe(A source, Location location) throws ConstraintAlreadySetException;
}
