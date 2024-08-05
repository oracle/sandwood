/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.transformations;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetArrayLengthTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.names.VariableNames;

/**
 * A class to direct length queries to an alternative array populated with the dimensions of the input array. Currently,
 * this is only used for lengths of observed arrays as we will always need the shape of these arrays, but it is not
 * always desirable to have the whole array.
 * <p>
 * In the future we may choose to extend this to all arrays simplify GPU programming.
 */

public class LengthTransformation extends DAGTransformations {

    static void applyTransformation(Variable<?>[] vs) {

        Set<Variable<?>> inputs = new HashSet<>();
        Set<Variable<?>> terminals = new HashSet<>();
        Set<GetArrayLengthTask> lengthTasks = new HashSet<>();

        for(DataflowTask<?> t:getTasks(vs)) {
            switch(t.getType()) {
                case CONSTRUCT_INPUT: {
                    inputs.add(t.getOutput());
                    break;
                }
                case GET_LENGTH: {
                    GetArrayLengthTask lt = (GetArrayLengthTask) t;
                    // If the task is an synthetically created alternative it will not have an output yet, that is only
                    // set at the point the task is swapped with the original.
                    if(lt.outputSet())
                        lengthTasks.add(lt);
                    break;
                }
                case OBSERVE_VARIABLE: {
                    terminals.add(((ObserveVariableTask<?>) t).target);
                    break;
                }
                default:
                    break;
            }

            if(t.outputSet()) {
                // Get any terminal variables.
                Variable<?> v = t.getOutput();
                if(v.getConsumers().isEmpty())
                    terminals.add(v);
            }
        }

        // Remove any inputs that are used in the model, instead of just in observations
        Stack<Variable<?>> toProcess = new Stack<>();
        toProcess.addAll(terminals);
        Set<DataflowTask<?>> seen = new HashSet<>(lengthTasks);

        while(!toProcess.isEmpty()) {
            Variable<?> v = toProcess.pop();
            DataflowTask<?> t = v.getParent();
            if(!seen.contains(t)) {
                seen.add(t);
                if(t.getType() == DFType.CONSTRUCT_INPUT)
                    inputs.remove(v);
                else
                    toProcess.addAll(t.getInputs());
            }
        }

        // Switch to alternative lengths for any getLengths that query inputs that are
        // only observed.
        for(GetArrayLengthTask t:lengthTasks) {
            // Test that this get length already references a length parameter and that the
            // variable is not used elsewhere in the model.
            if(!VariableNames.isLengthName(t.array.getUniqueVarDesc().name)
                    && inputs.contains(getOuterVariable(t.array)))
                t.useAlternative();
        }
    }

    private static Variable<?> getOuterVariable(Variable<?> v) {
        ProducingDataflowTask<?> p = v.instanceHandle().getParent();

        // Run through all the parents to the ultimate source.
        while(p.getType() == DFType.GET) {
            GetTask<?> get = (GetTask<?>) p;
            v = get.array;
            p = v.instanceHandle().getParent();
        }
        return v;
    }
}
