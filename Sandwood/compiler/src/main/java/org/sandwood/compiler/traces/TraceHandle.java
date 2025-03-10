/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.traces;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.exceptions.CompilerException;

/**
 * Handle class to ensure that traces are always copied when they are accessed. *
 */
public class TraceHandle implements Comparable<TraceHandle>, Iterable<DataflowTaskArgDesc> {
    private final Trace t;

    private TraceHandle(Trace t, boolean reverse) {
        this.t = t.copy();
        if(reverse)
            Collections.reverse(this.t);
    }

    public TraceHandle() {
        t = new Trace();
    }

    public Trace getTrace() {
        return t.copy();
    }

    public DataflowTaskArgDesc get(int i) {
        return t.get(i);
    }

    public DataflowTaskArgDesc peek() {
        return t.peek();
    }

    public int size() {
        return t.size();
    }

    @Override
    public String toString() {
        return t.toString();
    }

    public boolean isEmpty() {
        return t.isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + t.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        TraceHandle other = (TraceHandle) obj;
        return t.equals(other.t);
    }

    /*
     * The order of this is important for the DistributionsAdditionalScopes class to ensure that indexes are calculated
     * before the resultant values are.
     */
    @Override
    public int compareTo(TraceHandle o) {
        int size = size();
        int oSize = o.size();

        while(size != 0 && oSize != 0) {
            size--;
            oSize--;
            DataflowTaskArgDesc d = t.get(size);
            DataflowTaskArgDesc od = o.t.get(oSize);

            int taskID = d.task.id();
            int oTaskID = od.task.id();
            if(taskID != oTaskID)
                return taskID - oTaskID;

            if(d.argPos != od.argPos) {
                switch(d.task.getType()) {
                    case GET:
                        return -Integer.compare(d.argPos, od.argPos); // As the index is after the array value, but
                        // needs computing first we reverse the order for gets.
                    case IF_ASSIGNMENT:
                        // Code is required here to ensure that indexes are generated ahead of other
                        // code.
                        throw new CompilerException(
                                "The code for comparing traces including conditionals is not yet implemented.");
                    default:
                        return Integer.compare(d.argPos, od.argPos);
                }
            }
        }

        return size - oSize;
    }

    @Override
    public Iterator<DataflowTaskArgDesc> iterator() {
        return t.iterator();
    }

    public Set<Variable<?>> getIntermediates() {
        return t.getIntermediates();
    }

    public Map<ProducingDataflowTask<?>, String> inversionErrors() {
        return t.inversionErrors();
    }

    public static TraceHandle getTraceHandle(Trace t) {
        return new TraceHandle(t, false);
    }

    public static TraceHandle getReversedTraceHandle(Trace t) {
        return new TraceHandle(t, true);
    }

    /**
     * A method to remove the prefix of a trace. This is useful if traces between two points hold a common prefix and
     * only the suffixes are required.
     * 
     * @param prefix The prefix to remove.
     * @return A TraceHandle with the prefix removed.
     */
    public TraceHandle subTrace(TraceHandle prefix) {
        int traceSize = size();
        int prefixSize = prefix.size();
        if(traceSize < prefixSize)
            throw new CompilerException("Prefixed trace is not a subtrace of this trace.");
        for(int i = 0; i < prefixSize; i++) {
            if(!t.get(i).equals(prefix.get(i)))
                throw new CompilerException("Prefixed trace is not a subtrace of this trace.");
        }

        Trace subTrace = new Trace();
        for(int i = prefixSize - 1; i < traceSize; i++)
            subTrace.add(t.get(i));

        return getTraceHandle(subTrace);
    }

    /**
     * A method for removing all elements from a trace before the value of i.
     * 
     * @param i The point in the trace to start from.
     * @return The resultant sub trace.
     */
    public TraceHandle subTrace(int i) {
        Trace nt = new Trace();
        int size = t.size();
        while(i < size)
            nt.add(t.get(i++));
        return TraceHandle.getTraceHandle(nt);
    }

    public boolean invertable() {
        boolean b = true;
        for(int i = 1; i < t.size(); i++) {
            DataflowTaskArgDesc d = t.get(i);
            b = b && (d.task.checkInversionError(d.argPos) == null);
        }

        return b;
    }

    /**
     * As traces inside a trace handle are immutable a singleton instance is created for the empty trace.
     */
    private static final TraceHandle emptyTrace = new TraceHandle(new Trace(), false);

    public static TraceHandle emptyTrace() {
        return emptyTrace;
    }
}
