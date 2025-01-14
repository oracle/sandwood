/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask.TraceCallback;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

public class TraceConstructionDesc {
    // The sink is required to be carried in the constructor as the skipping over of
    // some tasks such as puts, means that the sink is not always the output of the
    // first task in the trace.
    public final Variable<?> sink;
    public final Trace trace;
    public final TraceCallback c;
    public DataflowTask<?> arrayEntryPoint = null;
    public boolean firstPut = true;
    public final Set<Variable<?>> seenVar;
    private final Map<ArrayVariable<?>, Map<Integer, Set<IntVariable>>> restrictedIndexes;

    public TraceConstructionDesc(Variable<?> sink, TraceCallback c) {
        this.sink = sink;
        trace = new Trace();
        this.c = c;
        restrictedIndexes = new HashMap<>();
        seenVar = new HashSet<>();
    }

    public void addRestrictedIndexes(PutTask<?> task) {
        // Get the set of indexes to augment, constructing it if need be.
        ArrayVariable<?> array = (ArrayVariable<?>) task.array.getSource().getOutput();
        int depth = array.getType().getDepth() - task.array.getType().getDepth();

        Map<Integer, Set<IntVariable>> m = restrictedIndexes.computeIfAbsent(array, k -> new HashMap<>());

        Set<IntVariable> indexes = m.computeIfAbsent(depth, k -> new HashSet<>());

        // Add all the indexes from the for scopes to the set.
        Scope scope = task.scope();
        Scope endScope = array.scope();
        while(scope != endScope) {
            switch(scope.getScopeType()) {
                case FOR:
                    ForTask ft = (ForTask) scope;
                    indexes.add(ft.getIndex());
                    break;
                case BLOCK:
                case COMMENT:
                case ELSE:
                case GLOBAL:
                case IF:
                case REDUCE:
                default:
                    break;
            }
            scope = scope.getEnclosingScope();
        }
    }

    public void removeRestrictedIndexes(PutTask<?> task) {
        // Get the set of indexes to remove indexes from.
        ArrayVariable<?> array = (ArrayVariable<?>) task.array.getSource().getOutput();
        int depth = array.getType().getDepth() - task.array.getType().getDepth();

        Map<Integer, Set<IntVariable>> m = restrictedIndexes.computeIfAbsent(array, k -> new HashMap<>());

        Set<IntVariable> indexes = m.computeIfAbsent(depth, k -> new HashSet<>());

        // Remove from the set all the indexes from the for scopes.
        Scope scope = task.scope();
        Scope endScope = array.scope();
        while(scope != endScope) {
            switch(scope.getScopeType()) {
                case FOR:
                    ForTask ft = (ForTask) scope;
                    indexes.remove(ft.getIndex());
                    break;
                case BLOCK:
                case COMMENT:
                case ELSE:
                case GLOBAL:
                case IF:
                case REDUCE:
                default:
                    break;
            }
            scope = scope.getEnclosingScope();
        }
    }

    public Set<IntVariable> checkRestrictedIndexes(GetTask<?> task) {
        // Get the set of indexes to check against.
        ArrayVariable<?> array = (ArrayVariable<?>) task.array.getSource().getOutput();
        int depth = array.getType().getDepth() - task.array.getType().getDepth();

        Map<Integer, Set<IntVariable>> m = restrictedIndexes.get(array);
        // If the set is not present there are no restrictions, so
        // the task passes by default.
        if(m == null)
            return Collections.emptySet();

        Set<IntVariable> indexes = m.get(depth);
        // If the set is not present there are no restrictions, so
        // the task passes by default.
        if(indexes == null)
            return Collections.emptySet();

        Set<IntVariable> toReturn = new HashSet<>();

        IntVariable index = task.index;
        Stack<Variable<?>> toProcess = new Stack<>();
        toProcess.push(index);

        while(!toProcess.isEmpty()) {
            Variable<?> v = toProcess.pop();
            DataflowTask<?> t = v.getParent();

            // If we come across a for index check if it is restricted.
            DFType type = t.getType();
            if((type == DFType.FOR || type == DFType.PAR_FOR) && indexes.contains(v))
                toReturn.add((IntVariable) v);
            // Otherwise continue to explore the graph if we have not reached a sample where
            // the trace will stop.
            else if(!(type == DFType.SAMPLE))
                for(Variable<?> in:t.getInputs())
                    toProcess.push(in);
        }

        return toReturn;
    }
}
