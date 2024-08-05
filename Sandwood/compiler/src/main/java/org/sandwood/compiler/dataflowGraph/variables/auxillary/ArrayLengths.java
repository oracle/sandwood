/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.auxillary;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

//A class to store array lengths as a linked list.
public class ArrayLengths {
    private final Set<Set<VariableWrapper<IntVariable>>> lengths = new HashSet<>();
    private ArrayLengths childLengths = null;

    public Set<VariableWrapper<IntVariable>> getLengths() {
        return new MergeSet<>(lengths);
    }

    // Method called when performing a get and a new array is constructed.
    public ArrayLengths getChildLengths() {
        return childLengths;
    }

    // Method called to consume array lengths when a put is called.
    public void addChildLengths(ArrayLengths childLengthsToAdd) {
        if(childLengths == null)
            childLengths = new ArrayLengths();
        childLengths.lengths.add(childLengthsToAdd.getLengths());

        if(childLengthsToAdd.childLengths != null)
            childLengths.addChildLengths(childLengthsToAdd.childLengths);
    }

    // Method to call when the array is created from an integer.
    public void addLength(IntVariable length) {
        Set<VariableWrapper<IntVariable>> s = new HashSet<>();
        s.add(new VariableWrapper<>(length));
        lengths.add(s);
    }

    public static class MergeSet<A> implements Set<A> {

        private final Set<Set<A>> sets;
        private final Set<A> localValues;

        public MergeSet(Set<Set<A>> lengths) {
            localValues = new HashSet<>();
            sets = new HashSet<>();
            sets.addAll(lengths);
        }

        private Set<A> constructSuperSet() {
            Set<A> superSet = new HashSet<>(localValues);
            for(Set<A> s:sets)
                superSet.addAll(s);
            return superSet;
        }

        @Override
        public int size() {
            return constructSuperSet().size();
        }

        @Override
        public boolean isEmpty() {
            return constructSuperSet().isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return constructSuperSet().contains(o);
        }

        @Override
        public Iterator<A> iterator() {
            return constructSuperSet().iterator();
        }

        @Override
        public Object[] toArray() {
            return constructSuperSet().toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return constructSuperSet().toArray(a);
        }

        @Override
        public boolean add(A e) {
            if(contains(e))
                return false;
            else {
                localValues.add(e);
                return true;
            }
        }

        @Override
        public boolean remove(Object o) {
            boolean contains = localValues.remove(o);
            for(Set<A> s:sets)
                contains = contains || s.remove(o);
            return contains;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return constructSuperSet().containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends A> c) {
            return localValues.addAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            boolean changed = localValues.retainAll(c);
            for(Set<A> s:sets)
                changed = changed || s.retainAll(c);
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            boolean contains = localValues.removeAll(c);
            for(Set<A> s:sets)
                contains = contains || s.removeAll(c);
            return contains;
        }

        @Override
        public void clear() {
            sets.clear();
            localValues.clear();
        }
    }
}
