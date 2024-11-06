/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.visitors.variableTracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransArrayGet;
import org.sandwood.compiler.trees.transformationTree.TransArrayPut;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransLocalFunctionCall;
import org.sandwood.compiler.trees.transformationTree.TransExternalFunctionCallReturn;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransRVFunctionCall;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeScope;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;

/**
 * A visitor to explore the tree recording where variables are read and where variables are written.
 * 
 * @author djgoodma
 *
 */
public class VariableTrackingVisitor implements TreeVisitor {

    /**
     * A helper class to hold all the data that needs to persist after the visitor has visited each node in the tree.
     */
    private static class TreeData extends VariableTracking {}

    /**
     * A class to track which arrays are modified when the value of a given array is changed. This class has imitations,
     * among them are: * Multiple global references to arrays must not point to the same array as there is no way to
     * detect this in the local code. * There must not be memory leaks, so references to memory allocations must be
     * written out to other arrays before the references can be overwritten or go out of scope.
     * 
     * @author djgoodma
     *
     */
    private class ArrayTracking {
        /**
         * A map from reference names to the references for the outermost possible arrays.
         */
        private final Map<VariableDescription<?>, Set<VariableDescription<?>>> sourceArrays = new HashMap<>();
        /**
         * A Map from outermost arrays to all the other references that possibly point to the same array. For each of
         * these the dimension of the array being referred to is recorded.
         */
        private final Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> subarrays = new HashMap<>();
        private final List<ArrayTracking> dependents = new ArrayList<>();
        private Set<VariableDescription<?>> dependentVars;
        private Map<VariableDescription<?>, Map<ArrayModifications, Integer>> listeners = null;

        public ArrayTracking() {
            dependentVars = Collections.emptySet();
        }

        private ArrayTracking(ArrayTracking a, boolean allVars) {
            sourceArrays.putAll(a.sourceArrays);
            subarrays.putAll(a.subarrays);
            a.dependents.add(this);
            if(allVars)
                dependentVars = sourceArrays.keySet();
            else
                dependentVars = a.dependentVars;
        }

        private ArrayTracking(ArrayTracking a, ArrayTracking b, boolean bDependent) {
            mergeSources(a.sourceArrays, b.sourceArrays, sourceArrays);
            mergeChildren(a.subarrays, b.subarrays, subarrays);
            dependentVars = new HashSet<>(a.dependentVars);
            a.dependents.add(this);

            if(bDependent) {
                dependentVars.addAll(b.dependentVars);
                b.dependents.add(this);
            }
        }

        public ArrayTracking copy(boolean allVars) {
            return new ArrayTracking(this, allVars);
        }

        public ArrayTracking merge(ArrayTracking b, boolean bDependent) {
            return new ArrayTracking(this, b, bDependent);
        }

        private void addInternal(VariableDescription<?> source, VariableDescription<?> child, int dimension) {
            // Find the sources of the provided source.
            Set<VariableDescription<?>> sources = sourceArrays.get(source);

            // Find out if the child was previously thought to be a source.
            Map<VariableDescription<?>, Integer> oldChildArrays = subarrays.remove(child);

            if(oldChildArrays == null) {
                // Add child for each of the discovered sources.
                sourceArrays.put(child, sources);
                for(VariableDescription<?> v:sources) {
                    Map<VariableDescription<?>, Integer> s = new HashMap<>(subarrays.get(v));
                    s.put(child, dimension); // minus 1 as this is the dimension of the array being added.
                    subarrays.put(v, s);
                }
            } else {
                // If the child array already existed as a source merge it into the
                // arrays with the new source as the source
                for(VariableDescription<?> v:oldChildArrays.keySet()) {
                    Set<VariableDescription<?>> s = sourceArrays.get(v);
                    s.remove(child);
                    s.addAll(sources);
                }

                for(VariableDescription<?> v:sources) {
                    Map<VariableDescription<?>, Integer> s = new HashMap<>(subarrays.get(v));
                    s.putAll(oldChildArrays);
                    subarrays.put(v, s);
                }
            }
        }

        private void removeInternal(VariableDescription<?> name) {
            Set<VariableDescription<?>> sources = sourceArrays.remove(name);
            if(sources != null) {
                // Remove the named value from the children
                for(VariableDescription<?> v:sources) {
                    Map<VariableDescription<?>, Integer> newChildren = new HashMap<>(subarrays.get(v));
                    newChildren.remove(name);
                    subarrays.put(v, newChildren);
                }

                // Remove the name from the sources.
                if(sources.contains(name)) {
                    Map<VariableDescription<?>, Integer> m = subarrays.remove(name);
                    if(sources.size() == 1 && !m.isEmpty())
                        throw new CompilerException("Trying to remove the only reference to an array source \"" + name
                                + "\" "
                                + "when this source has child arrays. This is not legal code as arrays should have global sources.");

                    Set<VariableDescription<?>> newSources = new HashSet<>(sources);
                    newSources.remove(name);

                    for(VariableDescription<?> sourceName:m.keySet())
                        sourceArrays.put(sourceName, newSources);
                }

                // Name is no longer dependent, so remove it.
                if(dependentVars.contains(name)) {
                    dependentVars = new HashSet<>(dependentVars);
                    dependentVars.remove(name);
                }
            }
        }

        private void mergeSources(Map<VariableDescription<?>, Set<VariableDescription<?>>> a,
                Map<VariableDescription<?>, Set<VariableDescription<?>>> b,
                Map<VariableDescription<?>, Set<VariableDescription<?>>> target) {
            Set<VariableDescription<?>> keys = new HashSet<>(a.keySet());
            keys.addAll(b.keySet());
            for(VariableDescription<?> v:keys) {
                Set<VariableDescription<?>> as = a.get(v);
                Set<VariableDescription<?>> bs = b.get(v);
                // Arrays only change on modification so if they are the same the references
                // will be too.
                if(as != bs) {
                    if(as == null)
                        target.put(v, bs);
                    else if(bs == null)
                        target.put(v, as);
                    else {
                        as = new HashSet<>(as);
                        as.addAll(bs);
                        target.put(v, as);
                    }
                } else
                    target.put(v, as);
            }
        }

        private void mergeChildren(Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> a,
                Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> b,
                Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> target) {
            Set<VariableDescription<?>> keys = new HashSet<>(a.keySet());
            keys.addAll(b.keySet());
            for(VariableDescription<?> v:keys) {
                Map<VariableDescription<?>, Integer> as = a.get(v);
                Map<VariableDescription<?>, Integer> bs = b.get(v);
                // Arrays only change on modification so if they are the same the references
                // will be too.
                if(as != bs) {
                    if(as == null)
                        target.put(v, bs);
                    else if(bs == null)
                        target.put(v, as);
                    else {
                        as = new HashMap<>(as);
                        for(VariableDescription<?> name:bs.keySet()) {
                            Integer aDim = as.get(name);
                            Integer bDim = bs.get(name);
                            if(aDim == null || aDim < bDim)
                                as.put(name, bDim);
                        }
                        target.put(v, as);
                    }
                } else
                    target.put(v, as);
            }
        }

        public ArrayTracking addArray(VariableDescription<?> source, VariableDescription<?> subarray, int dimension) {
            ArrayTracking at = copy(false);
            at.addInternal(source, subarray, dimension);
            return at;
        }

        /**
         * Method to remove out of scope arrays tracking information.
         * 
         * @param inScope The variables that are in scope.
         */
        public ArrayTracking removeOutOfScopeTracking(ScopedVarSet inScope) {
            ArrayTracking toReturn = this;
            for(VariableDescription<?> v:sourceArrays.keySet()) {
                if(!inScope.containsVar(v)) {
                    if(toReturn == this)
                        toReturn = copy(false);
                    toReturn.removeInternal(v);
                }
            }

            return toReturn;
        }

        public ArrayTracking addInnerArrayReference(VariableDescription<?> source, VariableDescription<?> subarray,
                int dimension) {
            ArrayTracking at = copy(false);
            // Remove the child as this is an assignment and so the child does not reference
            // its previous value.
            at.removeInternal(subarray);
            // Add the new references for the child.
            at.addInternal(source, subarray, dimension);
            return at;
        }

        public void addArray(VariableDescription<?> name, int dimension) {
            Set<VariableDescription<?>> s = new HashSet<>();
            s.add(name);
            sourceArrays.put(name, s);

            Map<VariableDescription<?>, Integer> m = new HashMap<>();
            m.put(name, dimension);
            subarrays.put(name, m);
        }

        public void exportArrayReferences(VariableDescription<?> name, int dimension, ArrayModifications am) {
            addListener(am, name, dimension);
            Set<VariableDescription<?>> sources = sourceArrays.get(name);
            Set<VariableDescription<?>> targets = new HashSet<>();
            if(sources != null) {
                for(VariableDescription<?> v:sources) {
                    Map<VariableDescription<?>, Integer> subarray = subarrays.get(v);
                    for(VariableDescription<?> subarrayName:subarray.keySet())
                        if(subarray.get(subarrayName) > dimension)
                            targets.add(subarrayName);
                }
            }
            am.addModifications(targets);
        }

        public void updateDependents(ArrayTracking at) {
            if(!dependents.isEmpty()) {
                // Construct map of extra arrays to sources;
                Map<VariableDescription<?>, Set<VariableDescription<?>>> additionalSources = new HashMap<>();
                for(VariableDescription<?> name:at.sourceArrays.keySet()) {
                    Set<VariableDescription<?>> localSources = sourceArrays.get(name);
                    Set<VariableDescription<?>> altSources = at.sourceArrays.get(name);

                    assert (localSources != null); // If the value is being added to it must have been in scope.
                    if(!localSources.containsAll(altSources)) {// If there are values missing that need to be added.
                        Set<VariableDescription<?>> s = new HashSet<>();
                        for(VariableDescription<?> v:altSources)
                            if(!localSources.contains(v))
                                s.add(v);
                        additionalSources.put(name, s);
                    }
                }

                // Construct map of extra sources to children
                Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> additionalChildren = new HashMap<>();
                for(VariableDescription<?> name:at.subarrays.keySet()) {
                    Map<VariableDescription<?>, Integer> localChildren = subarrays.get(name);
                    Map<VariableDescription<?>, Integer> altChildren = at.subarrays.get(name);
                    assert (localChildren != null); // If the value is being added to it must have been in scope.
                    if(!localChildren.keySet().containsAll(altChildren.keySet())) {// If there are values missing that
                        // need to be added.
                        Map<VariableDescription<?>, Integer> s = new HashMap<>();
                        for(VariableDescription<?> v:altChildren.keySet())
                            if(!localChildren.containsKey(v))
                                s.put(v, altChildren.get(v));
                        additionalChildren.put(name, s);
                    }
                }

                if(!additionalSources.isEmpty() || !additionalChildren.isEmpty()) {
                    for(ArrayTracking d:dependents)
                        d.updateDependentsInternal(additionalSources, additionalChildren);
                }
            }
        }

        private void updateDependentsInternal(
                Map<VariableDescription<?>, Set<VariableDescription<?>>> additionalSources,
                Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> additionalChildren) {
            Map<VariableDescription<?>, Set<VariableDescription<?>>> additionalSourcesToPass = additionalSources;
            // Add sources and filter to prevent non-dependent values being passed on.
            for(VariableDescription<?> v:additionalSources.keySet()) {
                if(dependentVars.contains(v)) { // If this array tracking object is dependent add the extra details
                    Set<VariableDescription<?>> s = sourceArrays.get(v);
                    s = new HashSet<>(s);
                    s.addAll(additionalSources.get(v));
                    sourceArrays.put(v, s);
                } else { // If this array tracking object is not dependent on this value, remove the
                    // value
                    // so that it does not get propagated.
                    if(!dependents.isEmpty()) {
                        // If a new map has not been constructed, construct it now ready to update it.
                        if(additionalSourcesToPass == additionalSources)
                            additionalSourcesToPass = new HashMap<>(additionalSources);
                        additionalSourcesToPass.remove(v);
                    }
                }
            }

            Map<VariableDescription<?>, Map<VariableDescription<?>, Integer>> additionalChildrenToPass = additionalChildren;
            // Add children and filter to prevent non dependent values being passed on.
            for(VariableDescription<?> v:additionalChildren.keySet()) {
                Map<VariableDescription<?>, Integer> newChildren = additionalChildren.get(v);
                Map<VariableDescription<?>, Integer> currentChildren = subarrays.get(v);
                // TODO tidy up this bug fix where a new set is created to prevent a concurrent
                // modification error.
                for(VariableDescription<?> c:new HashSet<>(newChildren.keySet())) {
                    if(dependentVars.contains(c)) { // If this array tracking object is dependent add the extra details
                        currentChildren = new HashMap<>(currentChildren);
                        currentChildren.put(c, newChildren.get(c));
                        subarrays.put(v, currentChildren);
                    } else { // If this array tracking object is not dependent on this value remove the value
                        // so that it does not get propagated.
                        if(!dependents.isEmpty()) {
                            // If a new map has not been constructed, construct it now ready to update it.
                            if(additionalChildrenToPass == additionalChildren)
                                additionalChildrenToPass = new HashMap<>(additionalChildren);

                            Map<VariableDescription<?>, Integer> s = additionalChildrenToPass.get(v);
                            // If a new set has not been created, create it now.
                            if(s == currentChildren) {
                                s = new HashMap<>(currentChildren);
                                additionalChildrenToPass.put(v, s);
                            }

                            s.remove(c);

                            // If the set is now empty remove it.
                            if(s.isEmpty())
                                additionalChildrenToPass.remove(v);
                        }
                    }
                }
            }

            // Update ArrayModification Objects.
            if(listeners != null) {
                for(VariableDescription<?> name:listeners.keySet()) {
                    // Get the modifications to update
                    Map<ArrayModifications, Integer> modifications = listeners.get(name);
                    for(ArrayModifications am:modifications.keySet()) {
                        Integer modificationDimension = modifications.get(am);

                        // Add values from news sources to the set
                        Set<VariableDescription<?>> sources = additionalSources.get(name);
                        if(sources != null) {
                            for(VariableDescription<?> sourceName:sources) {
                                Map<VariableDescription<?>, Integer> children = subarrays.get(sourceName);
                                for(VariableDescription<?> childName:children.keySet()) {
                                    if(children.get(childName) > modificationDimension) {
                                        am.addModification(childName);
                                    }
                                }
                            }
                        }

                        // Add in new child values
                        for(VariableDescription<?> sourceName:sourceArrays.get(name)) {
                            Map<VariableDescription<?>, Integer> children = additionalChildren.get(sourceName);
                            if(children != null) {
                                for(VariableDescription<?> childName:children.keySet()) {
                                    if(children.get(childName) > modificationDimension) {
                                        am.addModification(childName);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public ArrayTracking addEqualArrayReference(VariableDescription<?> source, VariableDescription<?> target,
                int dimension) {
            ArrayTracking at = copy(false);
            // Remove the child as this is an assignment and so the child does not reference
            // its previous value.
            at.removeInternal(target);

            // Find out if the source reference is an array source or not.
            Set<VariableDescription<?>> s = sourceArrays.get(source);
            if(s.contains(source)) { // Source is an outer array meaning the child will now be a source too.
                at.addExtraSource(source, target, dimension);
            } else { // The source is an inner array and the child can be added.
                // Add the new references for the child.
                at.addInternal(source, target, dimension);
            }
            return at;
        }

        private void addExtraSource(VariableDescription<?> existingSource, VariableDescription<?> additionalSource,
                int dimension) {
            Set<VariableDescription<?>> sources = sourceArrays.get(existingSource);

            Map<VariableDescription<?>, Integer> subarrayMap = subarrays.get(existingSource);

            sources = new HashSet<>(sources);
            sources.add(additionalSource);
            for(VariableDescription<?> name:subarrayMap.keySet())
                sourceArrays.put(name, sources);
            sourceArrays.put(additionalSource, sources);

            subarrayMap.put(additionalSource, dimension);
            for(VariableDescription<?> name:sources) {
                subarrays.put(name, subarrayMap);
            }
        }

        /*
         * private void test() { { assert !sourceArrays.containsKey(null); Set<VariableDescription<?>> allSources = new
         * HashSet<>(); for(VariableDescription<?> v:sourceArrays.keySet()) { Set<VariableDescription<?>> sources =
         * sourceArrays.get(v); assert !sources.contains(null); for(VariableDescription<?> source:sources) { assert
         * childArrays.containsKey(source); assert childArrays.get(source).containsKey(v); allSources.addAll(sources); }
         * } assert allSources.size() == childArrays.size(); } { assert !childArrays.containsKey(null);
         * Set<VariableDescription<?>> allChildren = new HashSet<>(); for(VariableDescription<?> v:childArrays.keySet())
         * { assert ! childArrays.get(v).containsKey(null); Set<VariableDescription<?>> children =
         * childArrays.get(v).keySet(); for(VariableDescription<?> child:children) { assert
         * sourceArrays.containsKey(child); assert sourceArrays.get(child).contains(v); allChildren.addAll(children); }
         * } assert allChildren.size() == sourceArrays.size(); } assert
         * sourceArrays.keySet().containsAll(dependentVars); }
         */

        private void addListener(ArrayModifications arrayModifications, VariableDescription<?> name,
                Integer dimension) {
            if(listeners == null)
                listeners = new HashMap<>();
            Map<ArrayModifications, Integer> m = listeners.computeIfAbsent(name, k -> new HashMap<>());
            m.put(arrayModifications, dimension);
        }

        @Override
        public String toString() {
            return "Source Arrays: " + sourceArrays + "\nChild Arrays: " + subarrays + "\nDependent Variables: "
                    + dependentVars + "\nNumber of Dependents: " + dependents.size();
        }
    }

    private class InScopeVariableTracking {
        private class UpdateableScopedVarSet extends ScopedVarSet {
            private boolean seen = false;

            public UpdateableScopedVarSet() {
                scopedVarStore.addMap(this);
            }

            private UpdateableScopedVarSet(ScopedVarSet inScopeVars) {
                super(inScopeVars);
                scopedVarStore.addMap(this);
            }

            public UpdateableScopedVarSet(TransFor forTree) {
                scopedVarStore.addMap(forTree.id, this);
            }

            public UpdateableScopedVarSet assignVar(VariableDescription<?> desc, TransTree<?> tree) {
                // Create a new hash table as all assignments have child trees, so this
                // map will have been seen by them.
                UpdateableScopedVarSet u = new UpdateableScopedVarSet(this);
                u.assignVarInternal(desc, tree);
                return u;
            }

            private void assignVarInternal(VariableDescription<?> desc, TransTree<?> tree) {
                // Recover the current value of the variable to assign.
                VarDef v = scopeVarSet.get(desc);
                // If the value is not set it must be a global, so create the value and set
                // it for all existing inScope objects.
                if(v == null)
                    v = scopedVarStore.addGlobal(desc);

                // Create a new VarDef holding the assigned variable.
                scopeVarSet.put(desc, new VarDef(v.declarationId, tree.id));
            }

            public UpdateableScopedVarSet declareVar(VariableDescription<?> name, TransTree<?> tree, boolean isSet) {
                if(scopeVarSet.containsKey(name))
                    throw new CompilerException("Problem with variable " + name + ". Variables cannot be overloaded.");
                UpdateableScopedVarSet u = new UpdateableScopedVarSet(this);
                u.declareVarInternal(name, tree, isSet);
                return u;
            }

            private void declareVarInternal(VariableDescription<?> name, TransTree<?> tree, boolean isSet) {
                VarDef v = isSet ? new VarDef(tree.id, tree.id) : new VarDef(tree.id);
                scopeVarSet.put(name, v);
            }

            /**
             * A method that is allowed to modify the map, but can only be called before the state of the object is
             * queried.
             * 
             * @param name
             * @param varDef
             */
            public void setVarDef(VariableDescription<?> desc, VarDef varDef) {
                if(seen && varDef.declarationId != TreeID.global)
                    throw new CompilerException(
                            "Trying to update a scoped variable set after the set has been observed.");
                scopeVarSet.put(desc, varDef);
            }

            @Override
            public VarDef getVarDef(VariableDescription<?> desc) {
                seen = true;
                return super.getVarDef(desc);
            }

            @Override
            public Set<VariableDescription<?>> getVars() {
                seen = true;
                return super.getVars();
            }

            @Override
            public int size() {
                seen = true;
                return super.size();
            }
        }

        private class ScopedVarSetStore {
            private class VarDefDesc {
                public final TreeID forID;
                public final VarDef v;

                VarDefDesc(TreeID forID, VarDef v) {
                    this.forID = forID;
                    this.v = v;
                }
            }

            private class ScopedVarDesc {
                public final TreeID forID;
                public final UpdateableScopedVarSet vars;

                ScopedVarDesc(TreeID forID, UpdateableScopedVarSet vars) {
                    this.forID = forID;
                    this.vars = vars;
                }
            }

            // A list of all the scopes created for the tree. This allows maps to be
            // updated.
            private final List<ScopedVarDesc> scopes = new ArrayList<>();

            private TreeID currentID = null;

            public VarDef addGlobal(VariableDescription<?> desc) {
                /*
                 * This method creates a chain of VarDef objects for the variable with a new object being created for
                 * each for loop reached. This simplistic approach works because we know that the global variable has
                 * not been referenced until this point. If the for loops have been left all the way to the outermost
                 * part of the scope then the dependencies are unimportant because the values cannot change.
                 * Alternatively if the VarDef can still be reached because we are still in the loop we know there have
                 * been no assignments to this variable otherwise it would have been seen and added at that point, so
                 * all the VarDefs need to be updated with any assignments later in the loop.
                 */
                // Define the variable definition for the global.
                VarDefDesc currentVarDef = new VarDefDesc(currentID, new VarDef(TreeID.global, TreeID.global));

                for(ScopedVarDesc s:scopes) {
                    if(currentVarDef.forID != s.forID) // If the code enters a for loop create a new id.
                        currentVarDef = new VarDefDesc(s.forID, currentVarDef.v.getDependent());
                    s.vars.setVarDef(desc, currentVarDef.v);
                }
                return currentVarDef.v;
            }

            public void addMap(UpdateableScopedVarSet vars) {
                scopes.add(new ScopedVarDesc(currentID, vars));
            }

            public void addMap(TreeID forID, UpdateableScopedVarSet vars) {
                currentID = forID;
                addMap(vars);
            }
        }

        private final Stack<UpdateableScopedVarSet> scopes = new Stack<>();
        private final Stack<ScopedVarSet> ifBodyScopes = new Stack<>();
        private final Stack<ArrayTracking> arrayTracking = new Stack<>();
        private final Stack<ArrayTracking> ifBodyArrayTracking = new Stack<>();
        private final ScopedVarSetStore scopedVarStore = new ScopedVarSetStore();

        public InScopeVariableTracking() {
            scopes.push(new UpdateableScopedVarSet());
            arrayTracking.push(new ArrayTracking());
        }

        public ScopedVarSet inScopeVars() {
            return scopes.peek();
        }

        public void enterForScope(TransFor forTree) {
            ScopedVarSet currentState = scopes.peek();
            UpdateableScopedVarSet scopeDesc = new UpdateableScopedVarSet(forTree);
            for(VariableDescription<?> v:currentState.getVars())
                scopeDesc.setVarDef(v, currentState.getVarDef(v).getDependent());
            scopes.push(scopeDesc);

            arrayTracking.push(arrayTracking.peek().copy(true));
        }

        public void enterBlockScope() {
            enterScope();
        }

        public void enterIfScope() {
            enterScope();
            arrayTracking.push(arrayTracking.peek().copy(false));
        }

        public void enterElseScope() {
            enterScope();
            arrayTracking.push(arrayTracking.peek().copy(false));
        }

        private void enterScope() {
            ScopedVarSet currentState = scopes.peek();
            scopes.push(new UpdateableScopedVarSet(currentState));
        }

        /**
         * Method to leave a subscope this will merge any variable assignments into the parent scope as required.
         *
         * @param tree The tree that is being left.
         */
        public void leaveForScope(TransFor tree) {
            ScopedVarSet innerScope = scopes.pop();
            ScopedVarSet outerScope = scopes.pop();

            ArrayTracking atBody = arrayTracking.pop();
            atBody = atBody.removeOutOfScopeTracking(outerScope);

            UpdateableScopedVarSet newScopeDesc = new UpdateableScopedVarSet();
            scopes.push(newScopeDesc);

            for(VariableDescription<?> desc:outerScope.getVars()) {
                VarDef outerDef = outerScope.getVarDef(desc);
                VarDef innerDef = innerScope.getVarDef(desc);

                // Test if the VarDef has not been modified. If it has not been
                // modified the inner def will be directly dependent on the outer def.
                // If it has been modified an additional definition will sit between the two.
                if(outerDef.isDependent(innerDef)) {
                    newScopeDesc.setVarDef(desc, outerDef.copy());
                } else { // If it has been modified update it and merge it.
                    outerDef.updateDependents(innerDef, tree.id);
                    newScopeDesc.setVarDef(desc, outerDef.merge(innerDef, false));
                }
            }

            ArrayTracking atOuter = arrayTracking.pop();
            arrayTracking.push(atOuter.merge(atBody, false));
            atOuter.updateDependents(atBody);
        }

        /**
         * Method to leave a subscope this will merge any variable assignments into the parent scope as required.
         */
        public void leaveBlockScope() {
            ScopedVarSet innerScope = scopes.pop();
            ScopedVarSet outerScope = scopes.pop();

            arrayTracking.push(arrayTracking.pop().removeOutOfScopeTracking(outerScope));

            // Drop names that are not declared in the outer scope,
            // but for variables that are declared use the state
            // from the inner scope.
            UpdateableScopedVarSet newScopeDesc = new UpdateableScopedVarSet();
            for(VariableDescription<?> desc:outerScope.getVars())
                newScopeDesc.setVarDef(desc, innerScope.getVarDef(desc));
            scopes.push(newScopeDesc);
        }

        /**
         * Method to leave a subscope this will merge any variable assignments into the parent scope as required.
         */
        public void leaveIfScope() {
            // Save the value from the if scope to use once the else scope is generated.
            ifBodyScopes.push(scopes.pop());
            ArrayTracking at = arrayTracking.pop();
            at = at.removeOutOfScopeTracking(scopes.peek());
            ifBodyArrayTracking.push(at);
        }

        /**
         * Method to leave a subscope this will merge any variable assignments into the parent scope as required.
         */
        public void leaveElseScope() {
            ScopedVarSet ifScope = ifBodyScopes.pop();
            ScopedVarSet elseScope = scopes.pop();
            ScopedVarSet outerScope = scopes.pop();

            ArrayTracking atIf = ifBodyArrayTracking.pop();
            ArrayTracking atElse = arrayTracking.pop();
            atElse = atElse.removeOutOfScopeTracking(outerScope);

            UpdateableScopedVarSet newScopeDesc = new UpdateableScopedVarSet();
            scopes.push(newScopeDesc);

            for(VariableDescription<?> desc:outerScope.getVars()) {
                VarDef ifDef = ifScope.getVarDef(desc);
                VarDef elseDef = elseScope.getVarDef(desc);
                if(ifDef == elseDef)
                    newScopeDesc.setVarDef(desc, ifDef);
                else
                    newScopeDesc.setVarDef(desc, ifDef.merge(elseDef, true));
            }

            // This comes after the updateReadsAndWrites so reference to the old Array
            // tracking is not required.
            arrayTracking.pop();
            arrayTracking.push(atIf.merge(atElse, true));
        }

        public void assignVar(VariableDescription<?> desc, TransTree<?> tree) {
            UpdateableScopedVarSet vars = scopes.pop();
            scopes.push(vars.assignVar(desc, tree));
        }

        public void declareVar(VariableDescription<?> name, TransTree<?> tree, boolean isSet) {
            UpdateableScopedVarSet vars = scopes.pop();
            scopes.push(vars.declareVar(name, tree, isSet));
        }

        public void addArray(VariableDescription<?> source, VariableDescription<?> subarray, int dimension) {
            arrayTracking.push(arrayTracking.pop().addArray(source, subarray, dimension));
        }

        public void addInnerArrayReference(VariableDescription<?> source, VariableDescription<?> subarray,
                int dimension) {
            arrayTracking.push(arrayTracking.pop().addInnerArrayReference(source, subarray, dimension));
        }

        public void addEqualArrayReference(VariableDescription<?> source, VariableDescription<?> subarray,
                int dimension) {
            arrayTracking.push(arrayTracking.pop().addEqualArrayReference(source, subarray, dimension));
        }

        public void addArray(VariableDescription<?> name, int dimension) {
            ArrayTracking at = arrayTracking.pop().copy(false);
            at.addArray(name, dimension);
            arrayTracking.push(at);
        }

        public boolean varDeclared(VariableDescription<?> desc) {
            return scopes.peek().containsVar(desc);
        }

        public void addGlobal(VariableDescription<?> desc) {
            scopedVarStore.addGlobal(desc);
            if(desc.type.isArray())
                // This works because all arrays are global, if they were not
                // We would need the same code for array allocation.
                for(ArrayTracking at:arrayTracking)
                    at.addArray(desc, desc.type.getDepth());
        }

        public void exportArrayReferences(VariableDescription<?> name, int dimension, ArrayModifications am) {
            arrayTracking.peek().exportArrayReferences(name, dimension, am);
        }
    }

    /**
     * Helper class to enforce that read and write sets are never modified after construction.
     */
    private static class WrittenVariables {
        private final Set<VariableDescription<?>> writtenVariables = new HashSet<>();
        private final ScopedVarSet inScopeVars;

        public WrittenVariables(ScopedVarSet inScopeVars) {
            this.inScopeVars = inScopeVars;
        }

        public void add(VariableDescription<?> desc) {
            writtenVariables.add(desc);
        }

        public void addAllInScope(WrittenVariables toAdd) {
            for(VariableDescription<?> desc:toAdd.writtenVariables)
                if(inScopeVars.containsVar(desc))
                    writtenVariables.addAll(toAdd.writtenVariables);
        }

        public Set<VariableDescription<?>> get() {
            return writtenVariables;
        }
    }

    private static class ReadVariables {
        // Map from variable names to a list of VarDefs, one for each read from a
        // different value of the
        // variable.
        private final Map<VariableDescription<?>, VarDef> readVariables = new HashMap<>();
        private final ScopedVarSet inScopeVars;

        public ReadVariables(ScopedVarSet inScopeVars) {
            this.inScopeVars = inScopeVars;
        }

        public void add(VariableDescription<?> name) {
            readVariables.put(name, inScopeVars.getVarDef(name));
        }

        public void addAllInScope(ReadVariables toAdd) {
            for(VariableDescription<?> name:toAdd.readVariables.keySet()) {
                VarDef inScopeDef = inScopeVars.getVarDef(name);
                VarDef readDef = toAdd.readVariables.get(name);
                if(// If the variable is in scope.
                inScopeDef != null &&
                // And check the assignments are a superset of the in scope assignments.
                // This will contain all the values or none of the values as it is either:
                // 1) The same VarDef
                // 2) A new VarDef made from a merge between the original VarDef and a newly
                // constructed VarDef.
                // 3) A new VarDef made of a separate assignment.
                //
                // This also has the property of preventing overloaded variables being added as
                // they would have different
                // write locations.
                        readDef.currentAssignments().containsAll(inScopeDef.currentAssignments())) {
                    readVariables.put(name, inScopeDef);
                }
            }
        }

        public ScopedVarSet get() {
            return new ScopedVarSet(readVariables);
        }
    }

    private static class ArrayModifications {
        private final Set<VariableDescription<?>> arrayModifications = new HashSet<>();
        private final ScopedVarSet inScopeVars;
        private ArrayModifications parent = null;

        public ArrayModifications(ScopedVarSet inScopeVars) {
            this.inScopeVars = inScopeVars;
        }

        public Set<VariableDescription<?>> get() {
            return arrayModifications;
        }

        public void addModification(VariableDescription<?> additionalModification) {
            if(inScopeVars.containsVar(additionalModification)) {
                arrayModifications.add(additionalModification);
                if(parent != null)
                    parent.addModification(additionalModification);
            }
        }

        public void addModifications(Set<VariableDescription<?>> modifications) {
            boolean addition = false;
            for(VariableDescription<?> desc:modifications) {
                if(inScopeVars.containsVar(desc)) {
                    arrayModifications.add(desc);
                    addition = true;
                }
            }
            if(parent != null && addition)
                parent.addModifications(modifications);
        }

        public void addAllInScope(ArrayModifications a) {
            for(VariableDescription<?> desc:a.arrayModifications) {
                if(inScopeVars.containsVar(desc))
                    arrayModifications.add(desc);
            }
            a.setParent(this);
        }

        private void setParent(ArrayModifications parent) {
            assert this.parent == null;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "ArrayModifications: " + arrayModifications;
        }
    }

    /** Class for holding the data that will eventually be returned. */
    private final TreeData variableTracking = new TreeData();

    /** Object holding all the in scope Variables */
    private final InScopeVariableTracking scopedVars = new InScopeVariableTracking();

    /**
     * A stack of objects recoding the variables read by the tree traversed so far.
     */
    private final Stack<ReadVariables> readVariables = new Stack<>();

    /**
     * A stack containing object for tracking the variables written to so far in the traversal of this tree.
     */
    private final Stack<WrittenVariables> writtenVariables = new Stack<>();

    /**
     * A stack containing objects for recording the array modifications that have happened so far in the tree.
     */
    private final Stack<ArrayModifications> arrayModifications = new Stack<>();

    /**
     * A map to record the effectively final variables.
     */
    private Map<VariableDescription<?>, Set<TreeID>> effectivelyFinal = new HashMap<>();

    /**
     * A reference to the first tree visited so that the completion of the traversal can be detected.
     */
    private TransTree<?> outermostTree = null;

    /**
     * A flag to say if the source of the array being explored should be saved
     */
    private boolean getArrayName = false;

    /**
     * A field to store the source of an explored tree providing an array.
     */
    private VariableDescription<?> arrayName;

    public VariableTrackingVisitor() {
        this(Collections.emptySet());
    }

    public VariableTrackingVisitor(Set<VariableDescription<?>> knownGlobals) {
        for(VariableDescription<?> desc:knownGlobals)
            scopedVars.addGlobal(desc);
        writtenVariables.push(new WrittenVariables(new ScopedVarSet()));
        readVariables.push(new ReadVariables(new ScopedVarSet()));
        arrayModifications.push(new ArrayModifications(new ScopedVarSet()));
    }

    /**
     * The method for visiting trees when tracking variables.
     */
    @Override
    public void visit(TransTree<?> tree) {
        if(outermostTree == null)
            outermostTree = tree;

        // Get the set of variables in scope. This needs to happen now as more variables
        // may be added
        // in the traversal.
        ScopedVarSet inScope = scopedVars.inScopeVars();

        // Create new data structures to store the reads and writes of this tree.
        readVariables.push(new ReadVariables(inScope));
        writtenVariables.push(new WrittenVariables(inScope));
        arrayModifications.push(new ArrayModifications(inScope));

        // Check the tree type and call the appropriate method to analyse it.
        switch(tree.type) {
            case FOR:
                getVariableTracking((TransFor) tree);
                break;

            case INITIALIZE:
                getVariableTracking((TransInitialize<?>) tree);
                break;

            case INITIALIZE_UNSET:
                getVariableTracking((TransInitializeUnset<?>) tree);
                break;

            case LOAD:
                getVariableTracking((TransLoad<?>) tree);
                break;

            case SCOPE:
                getVariableTracking((TransTreeScope) tree);
                break;

            case STORE:
                getVariableTracking((TransStore<?>) tree);
                break;

            case IF:
                getVariableTracking((TransIfElse) tree);
                break;

            case ARRAY_GET:
                getVariableTracking((TransArrayGet<?>) tree);
                break;

            case ARRAY_PUT:
                getVariableTracking((TransArrayPut<?>) tree);
                break;

            case RV_FUNCTION_CALL:
                getVariableTracking((TransRVFunctionCall) tree);
                break;

            case LOCAL_FUNCTION_CALL:
                getVariableTracking((TransLocalFunctionCall) tree);
                break;

            case EXTERNAL_FUNCTION_CALL_RETURN:
                getVariableTracking((TransExternalFunctionCallReturn<?>) tree);
                break;

            case ALLOCATE_ARRAY:
            case AND:
            case CAST_DOUBLE:
            case CAST_INT:
            case CONDITIONAL_ASSIGNMENT:
            case CONST_BOOLEAN:
            case CONST_DOUBLE:
            case CONST_INT:
            case EQUALITY:
            case LOCAL_FUNCTION_CALL_RETURN:
            case RV_FUNCTION_CALL_RETURN:
            case GET_FIELD:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
            case MAX:
            case MIN:
            case NEGATE:
            case NEGATE_BOOLEAN:
            case NOP:
            case OR:
            case SEQUENTIAL:
            case SUBTRACT:
            case MULTIPLY:
            case REMAINDER:
            case DIVIDE:
            case ADD:
                tree.traverseTree(this);
                break;

            default:
                throw new CompilerException("Unsupported Type " + tree.type);
        }

        ReadVariables r = readVariables.pop();
        WrittenVariables w = writtenVariables.pop();
        ArrayModifications a = arrayModifications.pop();

        // Record the variables that are currently in scope, and the variables used in
        // the traversal of the child trees.
        variableTracking.addTree(tree, inScope, r.get(), w.get(), a.get());

        // Add all the read and written variables to the sets of variables already used
        // by the parent elsewhere.
        // Any variables declared in the tree will have already been removed by this
        // point if they are now out of scope.
        readVariables.peek().addAllInScope(r);
        writtenVariables.peek().addAllInScope(w);
        arrayModifications.peek().addAllInScope(a);

        if(outermostTree == tree)
            variableTracking.effectivelyFinal.putAll(effectivelyFinal);
    }

    private void getVariableTracking(TransLocalFunctionCall tree) {
        for(TransTreeReturn<?> arg:tree.args()) {
            if(arg.getOutputType().isArray())
                throw new CompilerException(
                        "Variable tracking needs extending for named variables that take arrays as arguments");
            visit(arg);
        }
    }

    private void getVariableTracking(TransExternalFunctionCallReturn<?> tree) {
        for(TransTreeReturn<?> arg:tree.args()) {
            if(arg.getOutputType().isArray())
                throw new CompilerException(
                        "Variable tracking needs extending for named variables that take arrays as arguments");
            visit(arg);
        }
    }

    // TODO make all the arguments consistent in there position. They should all be
    // the last argument.
    private void getVariableTracking(TransRVFunctionCall tree) {
        int noArgs = tree.args.length;
        for(int i = 0; i < noArgs; i++) {
            TransTreeReturn<?> arg = tree.args[i];
            Type<?> type = arg.getOutputType();
            if(type.isArray()) {
                int dimension = type.getDepth();
                getArrayName = true;
                visit(arg);
                getArrayName = false;
                switch(tree.funcType) {
                    case ADD_DISTRIBUTION:
                        if(i == 0)
                            scopedVars.exportArrayReferences(arrayName, dimension, arrayModifications.peek());
                        break;
                    case SAMPLE:
                    case CONJUGATE_SAMPLE:
                        if(i == noArgs - 1)
                            scopedVars.exportArrayReferences(arrayName, dimension, arrayModifications.peek());
                        break;
                    case LOG_PROBABILITY:
                    case PROBABILITY:
                        break;
                    default:
                        throw new CompilerException("Unknown function type");
                }
            } else
                visit(arg);
        }
    }

    /**
     * Method to traverse an array set.
     * 
     * @param tree Tree to traverse.
     */
    private void getVariableTracking(TransArrayPut<?> tree) {
        visit(tree.index);
        visit(tree.value);
        // Set array put so that when the traversal of the array happens
        // the visitor knows this array will be written to.
        getArrayName = true;
        visit(tree.array);
        getArrayName = false;
        // Code output from Sandwood does not assign arrays into other arrays outside
        // the allocator methods. Therefore, if we are assigning an array this is inside
        // the allocator and the value is only assigned to this array, and it is only
        // assigned once. These are checks that we could enforce here, but it is
        // unnecessary to slow the compiler down like that.
        int dimension = tree.value.getOutputType().getDepth();
        if(tree.value.getOutputType().isArray()) {
            switch(tree.value.type) {
                case ALLOCATE_ARRAY: // This is overzealous as only arrays of higher dimension are
                    // actually updated, but as this is only used in the allocator this is not a
                    // problem.
                    scopedVars.exportArrayReferences(arrayName, dimension, arrayModifications.peek());
                    break;
                case ARRAY_GET:
                    throw new CompilerException("Attempting to assign the same sub-array to multiple"
                            + " parent arrays. This is not allowed in Sandwood code.\n\n" + tree.toString() + "\n");
                case LOAD:
                    scopedVars.exportArrayReferences(arrayName, dimension, arrayModifications.peek());
                    scopedVars.addArray(arrayName, ((TransLoad<?>) tree.value).varDesc, dimension);
                    break;
                default:
                    throw new CompilerException("Unhandled tree type encountered " + tree.value.type);
            }
        } else {
            scopedVars.exportArrayReferences(arrayName, dimension, arrayModifications.peek());
        }
    }

    /**
     * Method to traverse a get.
     * 
     * @param tree
     */
    private void getVariableTracking(TransArrayGet<?> tree) {
        // Record the status of getSource and then set it to false for the
        // traversal of the index before restoring its value.
        boolean getSource = this.getArrayName;
        this.getArrayName = false;
        visit(tree.index);
        this.getArrayName = getSource;

        visit(tree.array);
    }

    /**
     * Method for traversing an if else
     * 
     * @param tree
     */
    private void getVariableTracking(TransIfElse tree) {
        // Store the currently final values and create a new map for
        // for the if side of the conditional.
        Map<VariableDescription<?>, Set<TreeID>> efVars = effectivelyFinal;
        effectivelyFinal = new HashMap<>();

        // Visit the condition
        visit(tree.condition);
        // Enter a new scope and visit the body.
        scopedVars.enterIfScope();
        visit(tree.ifBody);
        scopedVars.leaveIfScope();

        // Store the values from the if side and create a new map for
        // the else side.
        Map<VariableDescription<?>, Set<TreeID>> efVarsIf = effectivelyFinal;
        effectivelyFinal = new HashMap<>();

        // Traverse the else body in the normal way.
        scopedVars.enterElseScope();
        visit(tree.elseBody);
        scopedVars.leaveElseScope();

        // Combine the resulting maps and save the results.
        Map<VariableDescription<?>, Set<TreeID>> efVarsElse = effectivelyFinal;
        effectivelyFinal = efVars;

        // Set any variables written in the ifElse to not be effectively final
        // if it is currently in the mapping as this means it is written to again.
        for(VariableDescription<?> varDesc:writtenVariables.peek().get()) {
            VarDef varDef = scopedVars.inScopeVars().getVarDef(varDesc);
            // VarDef can be null if the variable was declared inside the if else.
            if(varDef != null)
                removeEffectivelyFinal(varDesc, varDef.declarationId);
        }

        // Set any effectively final values from the if else statements starting
        // with the if tree.
        for(VariableDescription<?> desc:efVarsIf.keySet()) {
            Set<TreeID> ids = efVarsIf.get(desc);
            if(scopedVars.inScopeVars().containsVar(desc)) {
                Set<TreeID> elseIds = efVarsElse.get(desc);
                if(elseIds != null) {
                    for(TreeID id:ids) {
                        // If the value is set in both branches add it.
                        if(elseIds.contains(id))
                            addEffectivelyFinal(desc, id);
                    }
                }
            } else {
                // Add any values declared inside the if branch.
                addEffectivelyFinal(desc, ids);
            }
        }

        // Add any remaining values declared inside the else branch.
        for(VariableDescription<?> desc:efVarsElse.keySet()) {
            Set<TreeID> ids = efVarsElse.get(desc);
            if(!scopedVars.inScopeVars().containsVar(desc))
                addEffectivelyFinal(desc, ids);
        }
    }

    /**
     * Method for traversing a for statement.
     * 
     * @param tree The for statement.
     */
    private void getVariableTracking(TransFor tree) {
        // Visit the start of the loop before we do anything with the scopes.
        visit(tree.start);

        // Construct a new nested scope.
        scopedVars.enterForScope(tree);
        // Add the index to this scope
        scopedVars.declareVar(tree.indexDesc, tree, true);
        // Visit the remaining elements of the for loop.
        visit(tree.end);
        visit(tree.step);
        visit(tree.body);

        // Restore original scope, this returns a set of all the new states for
        // variables
        // that were present at the end of the scope execution.
        scopedVars.leaveForScope(tree);

        // Set any variables written in the loop to not be effectively final
        for(VariableDescription<?> varDesc:writtenVariables.peek().get()) {
            VarDef varDef = scopedVars.inScopeVars().getVarDef(varDesc);
            // Written variables has not been has variables declared in the
            // body removed yet.
            if(varDef != null)
                removeEffectivelyFinal(varDesc, varDef.declarationId);
        }
    }

    /**
     * Method to visit an initialisation tree, adding this value to the scope and marking the value as written too.
     * 
     * @param tree The initialisation tree
     */
    private void getVariableTracking(TransInitialize<?> tree) {

        VariableDescription<?> desc = tree.varDesc;
        TransTreeReturn<?> value = tree.value;
        VariableType.Type<?> vt = value.getOutputType();
        if(vt.isArray()) {
            switch(value.type) {
                case ALLOCATE_ARRAY:
                    tree.traverseTree(this);
                    scopedVars.addArray(desc, vt.getDepth());
                    break;
                case ARRAY_GET:
                    getArrayName = true;
                    tree.traverseTree(this);
                    getArrayName = false;
                    scopedVars.addInnerArrayReference(arrayName, desc, vt.getDepth());
                    break;
                case LOAD:
                    getArrayName = true;
                    tree.traverseTree(this);
                    getArrayName = false;
                    scopedVars.addEqualArrayReference(arrayName, desc, vt.getDepth());
                    break;
                default:
                    throw new CompilerException("Unexpected assignment type: " + value.type);

            }
        } else
            tree.traverseTree(this);
        scopedVars.declareVar(desc, tree, true);
        writtenVariables.peek().add(desc);
        addEffectivelyFinal(tree.varDesc, tree.id);
    }

    /**
     * Method to visit a variable declaration tree.
     * 
     * @param tree The variable declaration.
     */
    private void getVariableTracking(TransInitializeUnset<?> tree) {
        VariableDescription<?> desc = tree.varDesc;
        scopedVars.declareVar(desc, tree, false);
    }

    /**
     * Method to record the reading of a variable.
     * 
     * @param tree The tree reading the variable
     */
    private void getVariableTracking(TransLoad<?> tree) {
        VariableDescription<?> desc = tree.varDesc;
        if(!scopedVars.varDeclared(desc))
            scopedVars.addGlobal(desc);
        readVariables.peek().add(desc);
        // Record the source of a tree providing an array.
        if(getArrayName)
            arrayName = desc;
    }

    /**
     * Method to traverse a store recording that the named variable was assigned to at this location.
     * 
     * @param tree
     */
    private void getVariableTracking(TransStore<?> tree) {
        VariableDescription<?> desc = tree.varDesc;
        TransTreeReturn<?> value = tree.value;
        VariableType.Type<?> vt = value.getOutputType();
        if(vt.isArray()) {
            switch(value.type) {
                case ALLOCATE_ARRAY:
                    tree.traverseTree(this);
                    scopedVars.addArray(desc, vt.getDepth());
                    break;
                case ARRAY_GET:
                    getArrayName = true;
                    tree.traverseTree(this);
                    getArrayName = false;
                    scopedVars.addInnerArrayReference(arrayName, desc, vt.getDepth());
                    break;
                case LOAD:
                    getArrayName = true;
                    tree.traverseTree(this);
                    getArrayName = false;
                    scopedVars.addEqualArrayReference(arrayName, desc, vt.getDepth());
                    break;
                default:
                    throw new CompilerException("Unexpected assignment type: " + value.type);

            }
        } else
            tree.traverseTree(this);

        VarDef vd = scopedVars.inScopeVars().getVarDef(desc);
        // Null values happen if the value is a global value being met for the first time.
        if(vd != null && !vd.declarationId.isGlobal()) {
            if(vd.currentAssignments().isEmpty())
                addEffectivelyFinal(desc, vd.declarationId);
            else
                removeEffectivelyFinal(desc, vd.declarationId);
        }
        // Add to the variable used to store the result to the used variables
        recordAssignment(desc, tree);
    }

    /**
     * Method for traversing a scope block. This will apply the required steps to ensure declared variables do not
     * escape the block.
     * 
     * @param tree The scope tree.
     */
    private void getVariableTracking(TransTreeScope tree) {
        // Construct a new nested scope.
        scopedVars.enterBlockScope();
        tree.traverseTree(this);
        // Restore original scope
        scopedVars.leaveBlockScope();
    }

    /**
     * Method to record the assignment. There may be same scope for compacting the set of methods here, but for now we
     * will leave them be.
     * 
     * @param name The variable name.
     * @param tree The tree performing the assignment.
     */
    private void recordAssignment(VariableDescription<?> name, TransTree<?> tree) {
        writtenVariables.peek().add(name); // We don't want the tag as we don't care which version is overwritten.
        scopedVars.assignVar(name, tree);
    }

    /**
     * A helper method to record a currently effectively final value.
     * 
     * @param desc The description of the variable.
     * @param id   The id of the initialization.
     */
    private void addEffectivelyFinal(VariableDescription<?> desc, TreeID id) {
        Set<TreeID> ids = effectivelyFinal.computeIfAbsent(desc, k -> new HashSet<>());
        ids.add(id);
    }

    /**
     * A helper method to record a currently effectively final value.
     * 
     * @param desc The description of the variable.
     * @param id   The set of ids of the initializations. Multiple initializations can occur if the same variable is
     *             initialized in different sub-branches of the trees.
     */
    private void addEffectivelyFinal(VariableDescription<?> desc, Set<TreeID> id) {
        Set<TreeID> ids = effectivelyFinal.computeIfAbsent(desc, k -> new HashSet<>());
        ids.addAll(id);
    }

    /**
     * A helper method to remove a currently effectively final value.
     * 
     * @param desc The description of the variable.
     * @param id   The id of the initialization.
     */
    private void removeEffectivelyFinal(VariableDescription<?> desc, TreeID id) {
        Set<TreeID> ids = effectivelyFinal.get(desc);
        if(ids != null) {
            ids.remove(id);
            if(ids.isEmpty())
                effectivelyFinal.remove(desc);
        }
    }

    /**
     * A method to call after the traversal has completed to return the data structure that will be used to access the
     * information gathered.
     * 
     * @return A VariableTracking object holding the gathered data.
     */
    public VariableTracking getVariableTrackingData() {
        return variableTracking;
    }

    @Override
    public String toString() { // TODO complete this.
        StringBuilder sb = new StringBuilder();
        sb.append("writtenVariables " + writtenVariables + "\n");
        sb.append("\ntreeData:\n" + variableTracking + "\n");
        sb.append("scopedVars:\n" + scopedVars + "\n");
        return sb.toString();
    }
}
