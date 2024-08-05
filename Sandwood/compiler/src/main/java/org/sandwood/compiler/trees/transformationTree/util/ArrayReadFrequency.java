/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransArrayGet;
import org.sandwood.compiler.trees.transformationTree.TransArrayPut;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.visitors.TreeVisitor;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

/**
 * A class to determine if a variable set in an array is not read before it is written over. Currently it only checks
 * this for arrays with the same indexes, but this should be extended. TODO detect if an earlier or later write of the
 * same value is always a subset of the writes appearing elsewhere.
 */
public class ArrayReadFrequency {
    private class ArrayReadFrequencyVisitor implements TreeVisitor {
        private record CandidateDescription(TransArrayPut<?> putTask, Stack<TransTreeReturn<IntVariable>> indexes,
                List<TransFor> indexLoops, TransFor outerLoop, IfElseDesc ifElse) {}

        private record ArraySetDescription(VariableDescription<?> arrayName,
                Stack<TransTreeReturn<IntVariable>> indexes, IfElseDesc ifElse) {}

        private record IfElseDesc(TreeID ifElseID, Boolean inIf, IfElseDesc parent) {
            public boolean contains(IfElseDesc target) {
                IfElseDesc d = this;
                do {
                    if(d == target)
                        return true;
                    d = d.parent;
                } while(d != null);
                return false;
            }
        }

        // Variable tracking
        private final VariableTracking vars;
        // A stack holding all the for loops that the tree is embedded in
        private final Stack<TransFor> forLoops = new Stack<>();
        // A set containing all the current for loop indexes
        private final Map<VariableDescription<?>, TransFor> loopIndexes = new HashMap<>();
        // A Stack holding Stacks of array indexes. These will be build up while exploring put and get operations.
        private final Stack<Stack<TransTreeReturn<IntVariable>>> arrayIndexes = new Stack<>();
        // An array to hold the names of explored arrays
        private final Stack<VariableDescription<?>> arrayName = new Stack<>();
        // A flag to record if we are currently calculating a put to an array.
        private boolean inPut = false;
        // A map to record candidates for being removed.
        private final Map<VariableDescription<?>, Map<Stack<TransTreeReturn<IntVariable>>, CandidateDescription>> candidates = new HashMap<>();
        // A map to record the values that should be removed if the values are modified.
        private final Map<VariableDescription<?>, Set<ArraySetDescription>> writeDependencies = new HashMap<>();
        // A map to record the values that should be removed if the values are read.
        private final Map<VariableDescription<?>, Set<ArraySetDescription>> readDependencies = new HashMap<>();
        // A map to record the values that should be removed if the values are modified.
        private final Map<TransFor, Set<ArraySetDescription>> forLoopDependencies = new HashMap<>();
        // A Stack tracking which if else branches the tree is in. If the tree is not in an if branch the fields will be
        // null.
        private final Stack<IfElseDesc> ifElseDesces = new Stack<>();
        // A Map for tracking candidates constructed in each branch of an if else statement.
        private final Map<IfElseDesc, Map<VariableDescription<?>, Set<ArraySetDescription>>> ifElseBranchCandidate = new HashMap<>();
        // A list of all the declared variables so that they can be removed from candidates as they move out of scope.
        private final List<VariableDescription<?>> declaredVariables = new ArrayList<>();

        public ArrayReadFrequencyVisitor(VariableTracking vars) {
            this.vars = vars;
            ifElseDesces.push(new IfElseDesc(null, null, null));
        }

        @Override
        public void visit(TransTree<?> tree) {
            switch(tree.type) {
                case ARRAY_GET: {
                    TransArrayGet<?> get = (TransArrayGet<?>) tree;
                    visit(get.array);
                    if(inPut) {
                        arrayIndexes.peek().push(get.index);
                        inPut = false;
                        visit(get.index);
                        inPut = true;
                    } else {
                        visit(get.index);
                    }
                    break;
                }
                case ARRAY_PUT: {
                    TransArrayPut<?> put = (TransArrayPut<?>) tree;
                    visit(put.value);
                    visit(put.index);

                    // Visit the source of the array
                    Stack<TransTreeReturn<IntVariable>> indexes = new Stack<>();
                    arrayIndexes.push(indexes);
                    inPut = true;
                    visit(put.array);
                    indexes.push(put.index);
                    inPut = false;

                    updatePutState(put, indexes);

                    /*
                     * Remove any candidates that were read in the creation of this candidate. This would include this
                     * put for cases like a[a[i]] = f(i);. This is correct as if this value were met again it would
                     * depend on the value that was set by the candidate.
                     */
                    for(VariableDescription<?> desc:vars.arraysModified(put)) {
                        Set<ArraySetDescription> arrayDesces = writeDependencies.get(desc);
                        removeCandidate(arrayDesces);
                    }
                    break;
                }
                case FOR: {
                    int numDeclared = declaredVariables.size();
                    TransFor tf = (TransFor) tree;
                    visit(tf.start);
                    visit(tf.end);
                    visit(tf.step);
                    forLoops.push(tf);
                    loopIndexes.put(tf.indexDesc, tf);
                    declaredVariables.add(tf.indexDesc);
                    visit(tf.body);
                    forLoops.pop();
                    loopIndexes.remove(tf.indexDesc);

                    // Remove any no longer valid candidates
                    Set<ArraySetDescription> arrayDesces = forLoopDependencies.get(tf);
                    removeCandidate(arrayDesces);

                    // Remove candidates that depend on variables that are moving out of scope.
                    for(int i = declaredVariables.size() - 1; i >= numDeclared; i--) {
                        arrayDesces = writeDependencies.get(declaredVariables.remove(i));
                        removeCandidate(arrayDesces);
                    }

                    break;
                }
                case IF: {
                    int numDeclared = declaredVariables.size();
                    TransIfElse ifElse = (TransIfElse) tree;
                    visit(ifElse.condition);

                    IfElseDesc d = new IfElseDesc(ifElse.id, true, ifElseDesces.peek());
                    ifElseDesces.push(d);
                    visit(ifElse.ifBody);
                    ifElseDesces.pop();
                    Map<VariableDescription<?>, Set<ArraySetDescription>> ifArrays = ifElseBranchCandidate.remove(d);

                    // Remove candidates that depend on variables that are moving out of scope.
                    for(int i = declaredVariables.size() - 1; i >= numDeclared; i--) {
                        Set<ArraySetDescription> arrayDesces = writeDependencies.get(declaredVariables.remove(i));
                        removeCandidate(arrayDesces);
                    }

                    d = new IfElseDesc(ifElse.id, false, ifElseDesces.peek());
                    ifElseDesces.push(d);
                    visit(ifElse.elseBody);
                    ifElseDesces.pop();
                    Map<VariableDescription<?>, Set<ArraySetDescription>> elseArrays = ifElseBranchCandidate.remove(d);

                    // Remove candidates that depend on variables that are moving out of scope.
                    for(int i = declaredVariables.size() - 1; i >= numDeclared; i--) {
                        Set<ArraySetDescription> arrayDesces = writeDependencies.get(declaredVariables.remove(i));
                        removeCandidate(arrayDesces);
                    }

                    // Look for arrays that can be merged into a single candidate over riding the preceding ones.
                    if(ifArrays != null && elseArrays != null) {
                        Set<VariableDescription<?>> intersect = new HashSet<>(ifArrays.keySet());
                        intersect.retainAll(elseArrays.keySet());
                        for(VariableDescription<?> varDesc:intersect) {
                            Set<ArraySetDescription> ifDescriptions = ifArrays.get(varDesc);
                            Set<ArraySetDescription> elseDescriptions = elseArrays.get(varDesc);
                            for(ArraySetDescription ifDesc:ifDescriptions) {
                                CandidateDescription ifCandidate = candidates.get(varDesc).get(ifDesc.indexes);
                                if(ifCandidate != null) {
                                    for(ArraySetDescription elseDesc:elseDescriptions) {
                                        CandidateDescription elseCandidate = candidates.get(varDesc)
                                                .get(elseDesc.indexes);
                                        if(elseCandidate != null) {
                                            if(equivalentCandidates(ifCandidate, elseCandidate)) {
                                                overrideCandidates(varDesc, ifCandidate, elseCandidate);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case LOAD: {
                    TransLoad<?> l = (TransLoad<?>) tree;
                    if(inPut) {
                        arrayName.push(l.varDesc);
                    } else {
                        Set<ArraySetDescription> arrayDesces = readDependencies.get(l.varDesc);
                        removeCandidate(arrayDesces);
                    }
                    break;
                }
                case STORE: {
                    TransStore<?> s = (TransStore<?>) tree;
                    Set<ArraySetDescription> arrayDesces = writeDependencies.get(s.varDesc);
                    removeCandidate(arrayDesces);
                    break;
                }

                case SCOPE: {
                    int numDeclared = declaredVariables.size();

                    tree.traverseTree(this);

                    // Remove candidates that depend on variables that are moving out of scope.
                    for(int i = declaredVariables.size() - 1; i >= numDeclared; i--) {
                        Set<ArraySetDescription> arrayDesces = writeDependencies.get(declaredVariables.remove(i));
                        removeCandidate(arrayDesces);
                    }

                    break;
                }

                case INITIALIZE: {
                    TransInitialize<?> ti = (TransInitialize<?>) tree;
                    declaredVariables.add(ti.varDesc);

                    tree.traverseTree(this);
                    break;
                }

                case INITIALIZE_UNSET: {
                    TransInitializeUnset<?> ti = (TransInitializeUnset<?>) tree;
                    declaredVariables.add(ti.varDesc);

                    tree.traverseTree(this);
                    break;
                }

                default:
                    tree.traverseTree(this);
                    break;
            }
        }

        private void removeCandidate(Set<ArraySetDescription> arrayDesces) {
            if(arrayDesces != null) {
                for(ArraySetDescription ad:arrayDesces) {
                    // Check this array set description refers to arrays visible from this if branch.
                    if(ifElseDesces.peek().contains(ad.ifElse) || ad.ifElse.contains(ifElseDesces.peek())) {
                        Map<Stack<TransTreeReturn<IntVariable>>, CandidateDescription> m = candidates.get(ad.arrayName);
                        if(m != null)
                            m.remove(ad.indexes);
                    }
                }
            }
        }

        private void updatePutState(TransArrayPut<?> put, Stack<TransTreeReturn<IntVariable>> indexes) {
            /*
             * The condition that only one for index is allowed per array index and every loop index is required is
             * added to ensure that each element in the array is accessed by only one configuration of the loops. This
             * way there is no is no way the loops can iterate round and make a read before this code is reached such
             * that it is set and read in the same place. TODO reduce this restriction. This will involve getting the
             * reads from the array for every loop that the array is embedded in.
             */
            Set<VariableDescription<?>> usedLoopIndexes = new HashSet<>();
            // Set of variables that changes to will result in this write being potentially to a different location.
            Set<VariableDescription<?>> otherReadVariables = new HashSet<>();
            // Check all indexes use at most 1 loop index and gather a complete set of
            // used loop indexes.
            List<TransFor> indexLoops = new ArrayList<>();
            for(TransTreeReturn<IntVariable> index:indexes) {
                ScopedVarSet read = vars.readVars(index);
                int indexCount = 0;
                for(VariableDescription<?> d:read.getVars()) {
                    TransFor t = loopIndexes.get(d);
                    if(t != null) {
                        indexLoops.add(t);
                        usedLoopIndexes.add(d);
                        indexCount++;
                    } else {
                        otherReadVariables.add(d);
                    }
                }
                // Check each index is dependent on at most 1 loop index.
                if(indexCount > 1)
                    return;
                if(indexCount == 0)
                    indexLoops.add(null);
            }

            // Check that for n used indexes these come from the first n for loops
            int numUsedIndexes = usedLoopIndexes.size();
            int forLoopDepth = forLoops.size();
            for(int i = forLoopDepth - numUsedIndexes; i < forLoopDepth; i++) {
                TransFor loop = forLoops.get(i);
                if(!usedLoopIndexes.contains(loop.indexDesc))
                    return;
            }

            // Get the outer loop that must be in the stack for the value doing the overwriting. This restriction can be
            // removed when we track the read locations based on for loops. TODO add this feature
            TransFor outerForLoop = (numUsedIndexes == forLoopDepth) ? null
                    : forLoops.get(forLoopDepth - (numUsedIndexes + 1));

            // If this is true then the put value can be saved as one that could potentially be detected as being over
            // written before it is used.
            VariableDescription<?> name = arrayName.pop();

            CandidateDescription current = new CandidateDescription(put, indexes, indexLoops, outerForLoop,
                    ifElseDesces.peek());

            // Check if any of the candidates are over written by this put.
            // As this tests equivalence only candidates can overwrite candidates.
            overrideCandidates(name, current);

            // Record this candidate for future overrides
            recordCandidate(name, current, otherReadVariables);
        }

        private void overrideCandidates(VariableDescription<?> varDesc, CandidateDescription current) {
            // Look to see if any candidates for removal from the tree can be confirmed.
            Map<Stack<TransTreeReturn<IntVariable>>, CandidateDescription> m = candidates.get(varDesc);
            if(m != null) {
                Collection<CandidateDescription> cs = new HashSet<>(m.values());
                for(CandidateDescription candidate:cs) {
                    if(equivalentCandidates(current, candidate)) {
                        notRead.add(candidate.putTask);
                        m.remove(candidate.indexes);
                    }
                }
            }
        }

        private void overrideCandidates(VariableDescription<?> name, CandidateDescription ifCandidate,
                CandidateDescription elseCandidate) {
            // Look to see if any candidates for removal from the tree can be confirmed.
            Map<Stack<TransTreeReturn<IntVariable>>, CandidateDescription> m = candidates.get(name);
            Collection<CandidateDescription> cs = m.values();
            for(CandidateDescription candidate:cs) {
                if(candidate != ifCandidate && candidate != elseCandidate) {
                    if(equivalentCandidates(ifCandidate, candidate)) {
                        notRead.add(candidate.putTask);
                        m.remove(candidate.indexes);
                    }
                }
            }
        }

        private boolean equivalentCandidates(CandidateDescription current, CandidateDescription candidate) {
            // Check that these writes are occurring in the same outer loop
            // TODO weaken this.
            if(candidate.outerLoop != current.outerLoop)
                return false;
            if(!candidate.ifElse.contains(ifElseDesces.peek()))
                return false;

            // Test the indexes are equivalent
            Map<VariableDescription<?>, VariableDescription<?>> substitutions = new HashMap<>();
            for(int i = 0; i < current.indexes.size(); i++) {
                TransFor tfl = candidate.indexLoops.get(i);
                TransFor tfr = current.indexLoops.get(i);

                if(tfl == null) {
                    if(tfr != null)
                        return false;
                    else {
                        if(!current.indexes.get(i).equivalent(candidate.indexes.get(i)))
                            return false;
                    }
                } else {
                    if(tfr == null)
                        return false;
                    else {
                        if(!tfl.start.equivalent(tfr.start))
                            return false;
                        if(!tfl.end.equivalent(tfr.end))
                            return false;
                        if(!tfl.step.equivalent(tfr.step))
                            return false;

                        substitutions.put(tfl.indexDesc, tfr.indexDesc);
                        if(!current.indexes.get(i).equivalent(candidate.indexes.get(i), substitutions))
                            return false;
                    }
                }

                substitutions.clear();
            }

            return true;
        }

        private void recordCandidate(VariableDescription<?> name, CandidateDescription candidate,
                Set<VariableDescription<?>> otherReadVariables) {
            // Record varname -> indexes -> set puts as possible, outerloop that must be the same.
            candidates.computeIfAbsent(name, (key) -> new HashMap<>()).put(candidate.indexes, candidate);

            // Record the set variables that if modified or written to this candidate for removal is not valid.
            // TODO extend this so that modifications to arrays are only relevant if the indexes cannot be statically
            // shown to be separate.
            ArraySetDescription arraySetDesc = new ArraySetDescription(name, candidate.indexes, ifElseDesces.peek());
            for(VariableDescription<?> d:otherReadVariables)
                writeDependencies.computeIfAbsent(d, (key) -> new HashSet<>()).add(arraySetDesc);

            // Record the set of variables that if read this candidate for removal is not valid.
            // TODO weaken this so it is only relevant if the indexes cannot be statically confirmed to be for different
            // parts of the array.
            for(VariableDescription<?> d:vars.arraysModified(candidate.putTask))
                readDependencies.computeIfAbsent(d, (key) -> new HashSet<>()).add(arraySetDesc);

            // Record the for loop that this array assignment is located in.
            // TODO weaken this so that if the array is being iterated round on reads that would happen on a later
            // iteration are also taken into account
            forLoopDependencies.computeIfAbsent(candidate.outerLoop, (key) -> new HashSet<>()).add(arraySetDesc);

            // Record the candidates created in if else branches they can be merged and override earlier candidates if
            // they appear in both the if and the else branch.
            ifElseBranchCandidate.computeIfAbsent(candidate.ifElse, (key) -> new HashMap<>())
                    .computeIfAbsent(name, (key) -> new HashSet<>()).add(arraySetDesc);
        }
    }

    private final Set<TransArrayPut<?>> notRead = new HashSet<>();

    public ArrayReadFrequency(VariableTracking vars, TransTree<?> tree) {
        new ArrayReadFrequencyVisitor(vars).visit(tree);
    }

    public boolean modificationNotRead(TransArrayPut<?> pt) {
        return notRead.contains(pt);
    }
}
