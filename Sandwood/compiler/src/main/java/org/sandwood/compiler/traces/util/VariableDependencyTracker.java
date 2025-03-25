/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.traces.TraceHandle;

public class VariableDependencyTracker implements Iterable<Variable<?>> {

    private static class VarDesc implements Comparable<VarDesc> {
        final Variable<?> v;
        final Set<Variable<?>> dependencies;

        VarDesc(Variable<?> v) {
            this.v = v;
            dependencies = new HashSet<>();
        }

        @Override
        public int compareTo(VarDesc o) {
            if(o.dependencies.contains(v))
                return -1;
            else if(dependencies.contains(o.v))
                return 1;
            else
                return v.getId() - o.v.getId();
        }
    }

    private static class VariableIterator implements Iterator<Variable<?>> {

        private final Queue<VarDesc> q;

        public VariableIterator(Collection<VarDesc> variables) {
            q = new PriorityQueue<>(variables);
        }

        @Override
        public boolean hasNext() {
            return !q.isEmpty();
        }

        @Override
        public Variable<?> next() {
            return q.poll().v;
        }
    }

    private final Collection<VarDesc> variables;

    public VariableDependencyTracker(Map<Variable<?>, Set<TraceHandle>> vs) {
        Map<Variable<?>, VarDesc> requiredIntermediates = new HashMap<>();

        for(Variable<?> v:vs.keySet()) {
            requiredIntermediates.put(v, new VarDesc(v));
            for(TraceHandle h:vs.get(v)) {
                DataflowTaskArgDesc d = h.get(0);
                if(d.task.getInputsCount() > 0) {
                    Variable<?> sourceVar = d.task.getInput(d.argPos);
                    if(sourceVar.isFixed())
                        requiredIntermediates.get(v).dependencies.add(sourceVar);
                }
                for(DataflowTaskArgDesc a:h) {
                    Variable<?> output = a.task.getOutput();
                    if(output.isIntermediate() && output != v) {
                        requiredIntermediates.get(v).dependencies.add(output);
                    }
                }
            }
        }
        variables = requiredIntermediates.values();
    }

    @Override
    public Iterator<Variable<?>> iterator() {
        return new VariableIterator(variables);
    }
}
