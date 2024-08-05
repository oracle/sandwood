/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.auxillary;

import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;

public class DataflowTaskArgDesc {
    public final ProducingDataflowTask<?> task;
    public final int argPos;

    public DataflowTaskArgDesc(ProducingDataflowTask<?> task, int argPos) {
        assert (task != null);
        this.task = task;
        this.argPos = argPos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + argPos;
        result = prime * result + task.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        DataflowTaskArgDesc other = (DataflowTaskArgDesc) obj;
        if(argPos != other.argPos)
            return false;
        return task.equals(other.task);
    }

    @Override
    public String toString() {
        return "Task " + task.id() + " " + task.getType() + " arg position: " + argPos + "\n";
    }
}
