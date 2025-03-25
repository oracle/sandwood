/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.exceptions.SandwoodModelException;

public class DAGInfo {
    public static class TraceSinkPair {
        public final TraceHandle handle;
        public final Variable<?> sink;

        public TraceSinkPair(TraceHandle handle, Variable<?> sink) {
            this.handle = handle;
            /*
             * If the last task in the trace is a put the output could be different to the output from the task as all
             * possible puts are considered.
             */
            this.sink = sink;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((handle == null) ? 0 : handle.hashCode());
            result = prime * result + ((sink == null) ? 0 : sink.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            TraceSinkPair other = (TraceSinkPair) obj;
            if(handle == null && other.handle != null)
                return false;
            if(!handle.equals(other.handle))
                return false;
            if(sink == null && other.sink != null)
                return false;
            return sink.equals(other.sink);
        }

        @Override
        public String toString() {
            return "Sink: " + sink + " Trace: " + handle;
        }
    }

    private final List<TraceSinkPair> observedVarTraces = new ArrayList<>();
    private final List<TraceSinkPair> observedSourceTraces = new ArrayList<>();
    private final List<TraceSinkPair> randomVarTraces = new ArrayList<>();
    private final List<TraceSinkPair> terminalVarTraces = new ArrayList<>();
    private final List<TraceSinkPair> allTraces = new ArrayList<>();

    private final Set<RandomVariable<?, ?>> randomVariables = new HashSet<>();

    /**
     * Add an observed variable as an end point, with a random variable, constant, or input as the source, and a trace
     * from the sink to the source such that the sample task from the source is on the top of the stack. This is
     * opposite to the output from the Traces interface as it is reversed internally.
     *
     * @param trace A trace between a variable fixed by an observation, and the source of that variable.
     * @param sink  The variable that this trace ends at once it is reversed.
     */
    public void addObservedChild(Trace trace, Variable<?> sink) {
        TraceHandle h = TraceHandle.getReversedTraceHandle(trace);
        TraceSinkPair p = new TraceSinkPair(h, sink);
        observedVarTraces.add(p);
        allTraces.add(p);
    }

    /**
     * Add an observed variable as an end point, with a model input as the source, and a trace from the sink to the
     * source such that the input is on the top of the stack. This is opposite to the output from the Traces interface
     * as it is reversed internally.
     *
     * @param trace A trace between a variable used to fix another variable with an observation and the source for that
     *              variable.
     * @param sink  The variable that this trace ends at once it is reversed.
     */
    public void addObservedSource(Trace trace, Variable<?> sink) {
        // Check that internal model variables are not being observed.
        if(trace.peek().task.getType() == DFType.SAMPLE) {
            for(DataflowTask<?> d:sink.getConsumers()) {
                if(d.getType() == DFType.OBSERVE_VARIABLE)
                    throw new SandwoodModelException(
                            "Tried to observe a value that was not a model input. This is currently not a supported feature.",
                            d);
            }
        }

        TraceHandle h = TraceHandle.getReversedTraceHandle(trace);
        TraceSinkPair p = new TraceSinkPair(h, sink);
        observedSourceTraces.add(p);
        allTraces.add(p);
    }

    /**
     * Add a consuming random variable as an end point, with a random variable, constant, or input as the source, and a
     * trace from the sink to the source such that the sample task from the random variable is on the top of the stack.
     * This is opposite to the output from the Traces interface as it is reversed internally.
     *
     * @param trace The trace between the two random variables.
     * @param sink  The variable that this trace ends at once it is reversed.
     */
    public void addRandomChild(Trace trace, Variable<?> sink) {
        TraceHandle h = TraceHandle.getReversedTraceHandle(trace);
        TraceSinkPair p = new TraceSinkPair(h, sink);
        randomVarTraces.add(p);
        allTraces.add(p);
    }

    /**
     * Adds a variable that is not consumed by anything else. This is added as a way of getting data out of the graph if
     * it is not returned by any other process. For example if we just sample a random variable at the end, but never
     * observe values for it.
     *
     * @param trace The trace from a terminal variable to its source.
     * @param sink  The variable that this trace ends at once it is reversed.
     */
    public void addTerminalChild(Trace trace, Variable<?> sink) {
        TraceHandle h = TraceHandle.getReversedTraceHandle(trace);
        TraceSinkPair p = new TraceSinkPair(h, sink);
        terminalVarTraces.add(p);
        allTraces.add(p);
    }

    public List<TraceSinkPair> observedVarTraces() {
        return observedVarTraces;
    }

    public List<TraceSinkPair> observedSourceTraces() {
        return observedSourceTraces;
    }

    public List<TraceSinkPair> randomVarTraces() {
        return randomVarTraces;
    }

    public List<TraceSinkPair> terminalVarTraces() {
        return terminalVarTraces;
    }

    public List<TraceSinkPair> allTraces() {
        return allTraces;
    }

    /**
     * Add an encountered random variable.
     *
     * @param v The random variable to be recorded.
     */
    public void addRandomVariable(RandomVariable<?, ?> v) {
        randomVariables.add(v);
    }

    public Set<RandomVariable<?, ?>> getRandomVariables() {
        return randomVariables;
    }

    public void filterTraces() {
        filterTraces(observedVarTraces);
        filterTraces(observedSourceTraces);
        filterTraces(randomVarTraces);
        filterTraces(terminalVarTraces);
    }

    private void filterTraces(List<TraceSinkPair> traces) {
        Set<ObserveVariableTask<?>> s = new LinkedHashSet<>();
        Set<TraceSinkPair> toAdd = new LinkedHashSet<>();
        Set<TraceSinkPair> toRemove = new LinkedHashSet<>();

        for(TraceSinkPair p:traces) {
            Set<ObserveVariableTask<?>> os = p.sink.getFixingObservations();
            TraceHandle h = p.handle;
            int i = h.size() - 1;
            boolean fixed = false;
            while(i >= 0 && !fixed) {
                Variable<?> v = h.get(i--).task.getOutput();
                if(v.isFixed()) {
                    fixed = true;
                    // Test if there is overlap between the initial constraining set and the constraining set for this
                    // variable
                    for(ObserveVariableTask<?> o:v.getFixingObservations()) {
                        if(os.contains(o)) {
                            fixed = false;
                            break;
                        }
                    }
                }
            }

            if(fixed) {
                TraceHandle subTrace = h.subTrace(i + 2);
                toRemove.add(p);
                if(!subTrace.isEmpty())
                    toAdd.add(new TraceSinkPair(subTrace, p.sink));
            }
        }

        if(!toRemove.isEmpty()) {
            traces.removeAll(toRemove);
            traces.addAll(toAdd);

            allTraces.removeAll(toRemove);
            allTraces.addAll(toAdd);
        }
    }
}
