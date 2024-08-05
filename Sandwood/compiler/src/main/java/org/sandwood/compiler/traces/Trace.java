/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;

public class Trace extends Stack<DataflowTaskArgDesc> {
    private static final long serialVersionUID = 1L;

    public Trace copy() {
        return (Trace) this.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size(); i++)
            sb.append(i + ": " + get(i).toString());
        return sb.toString();
    }

    public Set<Variable<?>> getIntermediates() {
        Set<Variable<?>> intermediates = new HashSet<>();

        // find the intermediate variables
        for(DataflowTaskArgDesc d:this) {
            Variable<?> v = d.task.getOutput();
            if(v.isIntermediate())
                intermediates.add(v);

            // Set all outer arrays to intermediates.
            DataflowTask<?> t = d.task;
            switch(t.getType()) {
                case PUT:
                    if(d.argPos == 2) {
                        v = ((PutTask<?>) t).findOuterArray();
                        assert (v.isIntermediate());
                        intermediates.add(v);
                    }
                    break;
                default:
                    break;
            }
        }
        return intermediates;
    }

    public Map<ProducingDataflowTask<?>, String> inversionErrors() {
        Map<ProducingDataflowTask<?>, String> errors = new HashMap<>();
        for(DataflowTaskArgDesc d:this) {
            String error = d.task.checkInversionError(d.argPos);
            if(error != null)
                errors.put(d.task, error);
        }
        return errors;
    }
}
