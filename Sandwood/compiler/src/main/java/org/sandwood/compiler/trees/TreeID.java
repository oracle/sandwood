/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A tag to record a specific instance of a variable in an output tree. The main purpose of this class to reduce errors
 * by improving type checking.
 * 
 * @author djgoodma
 *
 */
public class TreeID implements Comparable<TreeID> {
    private final static AtomicInteger globalId = new AtomicInteger();
    public final int tag;
    public final static TreeID global = new TreeID(-1);

    TreeID() {
        this(globalId.getAndIncrement());
    }

    TreeID(int id) {
        tag = id;
    }

    /**
     * Method for getting the current value, this is useful for determining which trees were created after a point in a
     * programs execution as they will have a larger ID.
     * 
     * @return
     */
    public static int getCurrentValue() {
        return globalId.get();
    }

    @Override
    public int hashCode() {
        return tag;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if((obj == null) || (getClass() != obj.getClass()))
            return false;
        TreeID other = (TreeID) obj;
        return tag == other.tag;
    }

    @Override
    public String toString() {
        return isGlobal() ? "Global" : Integer.toString(tag);
    }

    public boolean isGlobal() {
        return this == TreeID.global;
    }

    @Override
    public int compareTo(TreeID o) {
        return tag - o.tag;
    }
}
