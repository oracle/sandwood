/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.transformations;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;

public class DAGTransformations {
    public static void apply(Variable<?>[] vs) {
        LengthTransformation.applyTransformation(vs);
    }

    /**
     * Method to collect all the tasks in the DAG that containing the variables in vs.
     * 
     * @param vs An array of variables.
     * @return A set containing all the tasks in the DAG that are connected to the provided variables.
     */
    protected static Set<DataflowTask<?>> getTasks(Variable<?>[] vs) {
        Set<DataflowTask<?>> allTasks = new HashSet<>();

        Queue<DataflowTask<?>> toProcess = new LinkedList<>();
        for(Variable<?> v:vs)
            toProcess.add(v.getParent());

        while(!toProcess.isEmpty()) {
            DataflowTask<?> t = toProcess.remove();
            if(!allTasks.contains(t)) {
                allTasks.add(t);

                // Add all the tasks that produce the inputs.
                for(Variable<?> v:t.getInputs())
                    toProcess.add(v.getParent());

                // Add all the tasks that consume the output
                if(t.outputSet())
                    toProcess.addAll(t.getOutput().getConsumers());
            }
        }
        return allTasks;
    }
}
