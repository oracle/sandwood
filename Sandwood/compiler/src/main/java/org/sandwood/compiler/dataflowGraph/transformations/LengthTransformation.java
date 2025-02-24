/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.transformations;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.ConstructArrayInput;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetArrayLengthTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.CategoricalTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.DirichletTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor.MultinomialTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;

/**
 * A class to direct length queries to an alternative array populated with the dimensions of the input array. Currently,
 * this is only used for lengths of observed arrays as we will always need the shape of these arrays, but it is not
 * always desirable to have the whole array.
 * <p>
 * In the future we may choose to extend this to all arrays to simplify GPU programming.
 */

public class LengthTransformation extends DAGTransformations {

    static void applyTransformation(Variable<?>[] vs) {

        Set<Variable<?>> terminals = new HashSet<>();

        // Find all terminal variables.
        for(DataflowTask<?> t:getTasks(vs)) {
            if(t.getType() == DFType.OBSERVE_VARIABLE)
                terminals.add(((ObserveVariableTask<?>) t).target);

            if(t.outputSet()) {
                Variable<?> v = t.getOutput();

                // Get any terminal variables.
                if(v.getConsumers().isEmpty())
                    terminals.add(v);
            }
        }

        // Gather the inputs that are read by the model and all length tasks that occur in the model.
        Set<ConstructArrayInput<?>> arrayInputs = new HashSet<>();
        Set<ArrayVariable<?>> lengthArrays = new HashSet<>();
        {
            Set<DataflowTask<?>> seen = new HashSet<>();
            Stack<Variable<?>> toProcess = new Stack<>();
            toProcess.addAll(terminals);

            while(!toProcess.isEmpty()) {
                Variable<?> v = toProcess.pop();
                DataflowTask<?> t = v.getParent();
                if(!seen.contains(t)) {
                    seen.add(t);
                    switch(t.getType()) {
                        case CONSTRUCT_INPUT: {
                            if(v.getType().isArray())
                                arrayInputs.add((ConstructArrayInput<?>) t);
                            break;
                        }
                        case GET_LENGTH: {
                            GetArrayLengthTask lt = (GetArrayLengthTask) t;
                            lengthArrays.add(lt.array);
                            break;
                        }
                        case CATEGORICAL: {
                            CategoricalTask c = (CategoricalTask) t;
                            lengthArrays.add(c.elements);
                            toProcess.addAll(t.getInputs());
                            break;
                        }
                        case DIRICHLET: {
                            DirichletTask d = (DirichletTask) t;
                            lengthArrays.add(d.beta);
                            toProcess.addAll(t.getInputs());
                            break;
                        }
                        case MULTINOMIAL: {
                            MultinomialTask m = (MultinomialTask) t;
                            lengthArrays.add(m.p);
                            toProcess.addAll(t.getInputs());
                            break;
                        }
                        default:
                            toProcess.addAll(t.getInputs());
                            break;
                    }
                }
            }
        }

        // Get all the inputs that are used in model lengths.
        Set<ConstructArrayInput<?>> lengthInputs = new HashSet<>();
        {
            Stack<ArrayVariable<?>> toProcess = new Stack<>();
            toProcess.addAll(lengthArrays);
            while(!toProcess.isEmpty()) {
                ArrayVariable<?> v = toProcess.pop();
                DataflowTask<?> t = v.getParent();
                while(t.getType() == DFType.GET) {
                    v = ((GetTask<?>) t).array;
                    t = v.getParent();
                }

                if(t.getType() == DFType.CONSTRUCT_INPUT)
                    lengthInputs.add((ConstructArrayInput<?>) t);
            }
        }

        // Construct any required shape variables.
        for(ConstructArrayInput<?> c:lengthInputs) {
            if(!arrayInputs.contains(c))
                c.constuctShapeVaraible();
        }
    }
}
