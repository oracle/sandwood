/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;

public abstract class SampleTask<A extends Variable<A>, B extends RandomVariable<A, B>>
        extends ProducingDataflowTaskImplementation<A> {

    public final B randomVariable;

    public SampleTask(B randomVariable, Location location) {
        super(DFType.SAMPLE, randomVariable.getSampleType(), false, location, randomVariable);
        this.randomVariable = randomVariable;
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        desc.trace.add(new DataflowTaskArgDesc(this, 0));
        desc.c.callback(desc.trace, desc.sink);
        desc.trace.pop();
    }

    public abstract Type<A> getBaseType();

    public VariableDescription<A> getUniqueVarDesc() {
        return new VariableDescription<>("sample" + id(), getBaseType(), false);
    }
}
