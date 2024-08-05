/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
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
    }

    private final List<TraceSinkPair> observedVarTraces = new ArrayList<>();
    private final List<TraceSinkPair> observedSourceTraces = new ArrayList<>();
    private final List<TraceSinkPair> randomVarTraces = new ArrayList<>();
    private final List<TraceSinkPair> terminalVarTraces = new ArrayList<>();
    private final List<TraceSinkPair> allTraces = new ArrayList<>();

    private final Set<RandomVariable<?, ?>> randomVariables = new HashSet<>();
    private final Set<Variable<?>> observedVariables = new HashSet<>();

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
     * Adds an encountered observed variable to be kept track of.
     *
     * @param v The observed variable to be recorded.
     */
    public void addObservedVariable(Variable<?> v) {
        v = v.getCurrentInstance();
        observedVariables.add(v);
    }

    public Set<Variable<?>> getObservedVariables() {
        return observedVariables;
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
}
