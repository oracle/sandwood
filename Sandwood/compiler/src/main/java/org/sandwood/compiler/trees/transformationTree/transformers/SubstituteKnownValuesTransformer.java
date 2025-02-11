/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.transformationTree.transformers;

import static org.sandwood.compiler.trees.transformationTree.TransTree.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.TreeID;
import org.sandwood.compiler.trees.transformationTree.TransArrayGet;
import org.sandwood.compiler.trees.transformationTree.TransArrayPut;
import org.sandwood.compiler.trees.transformationTree.TransCastToDouble;
import org.sandwood.compiler.trees.transformationTree.TransCastToInteger;
import org.sandwood.compiler.trees.transformationTree.TransConditionalAssignment;
import org.sandwood.compiler.trees.transformationTree.TransFor;
import org.sandwood.compiler.trees.transformationTree.TransIfElse;
import org.sandwood.compiler.trees.transformationTree.TransInitialize;
import org.sandwood.compiler.trees.transformationTree.TransInitializeUnset;
import org.sandwood.compiler.trees.transformationTree.TransLoad;
import org.sandwood.compiler.trees.transformationTree.TransNegateBoolean;
import org.sandwood.compiler.trees.transformationTree.TransSequential;
import org.sandwood.compiler.trees.transformationTree.TransStore;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTree.TransTreeType;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.TransTreeScope;
import org.sandwood.compiler.trees.transformationTree.TransTreeVoid;
import org.sandwood.compiler.trees.transformationTree.binop.TransAnd;
import org.sandwood.compiler.trees.transformationTree.binop.TransEq;
import org.sandwood.compiler.trees.transformationTree.binop.TransOr;
import org.sandwood.compiler.trees.transformationTree.util.ArrayReadFrequency;
import org.sandwood.compiler.trees.transformationTree.util.IntGCD;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans;
import org.sandwood.compiler.trees.transformationTree.util.ScalarReadFrequency;
import org.sandwood.compiler.trees.transformationTree.util.ScalarReadFrequency.Frequency;
import org.sandwood.compiler.trees.transformationTree.util.RearrangeTree;
import org.sandwood.compiler.trees.transformationTree.util.TaggedVariableName;
import org.sandwood.compiler.trees.transformationTree.util.WrappedTransReturn;
import org.sandwood.compiler.trees.transformationTree.util.KnownValuesTrans.KnownValue;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.AssignmentDesc;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.ScopedVarSet;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VarDef;
import org.sandwood.compiler.trees.transformationTree.visitors.variableTracking.VariableTracking;

/**
 * A class holding the framework for removing unrequired assignments.
 * 
 * @author djgoodma
 *
 */
public class SubstituteKnownValuesTransformer extends Transformer {

    /**
     * A class to hold all the substitutions that are valid at any given moment.
     */
    private static class Substitutes {
        private static class SubDesc {
            public final TransTreeReturn<?> substitute;
            public final TreeID sourceID;
            public final ScopedVarSet dependencies;
            public final boolean restricted;
            public final int scopeDepth;

            public SubDesc(TransTreeReturn<?> substitute, TreeID sourceID, ScopedVarSet dependencies,
                    boolean restricted, int scopeDepth) {
                this.substitute = substitute;
                this.sourceID = sourceID;
                this.dependencies = dependencies;
                this.restricted = restricted;
                this.scopeDepth = scopeDepth;
            }
        }

        private static class SubExprDesc {
            public final TransTreeReturn<BooleanVariable> substitute;
            public final ScopedVarSet dependencies;

            public SubExprDesc(TransTreeReturn<BooleanVariable> substitute, ScopedVarSet dependencies) {
                this.substitute = substitute;
                this.dependencies = dependencies;
            }
        }

        /**
         * Map of variable names, to instances of the variable, to the trees that constructed that instance. If a tree
         * is in here it can be used as a substitute to the specific variable instance.
         */
        private final Map<VariableName, Map<TreeID, SubDesc>> substitutes = new HashMap<>();

        /**
         * A Map for recording the variables that other variables are dependent on.
         */
        private final Map<VariableDescription<?>, Set<TaggedVariableName>> dependencyTracking = new HashMap<>();

        /**
         * A map for holding comments to go with trees so that the information can be preserved. The map is indexed by
         * tree ID instead of by tree for quicker hash and equality checks.
         */
        private final Map<TreeID, String> comments = new HashMap<>();

        /**
         * A map from trees to names to ignore while processing the trees. The map is indexed by tree ID instead of by
         * tree for quicker hash and equality checks.
         */
        private final Map<TreeID, VariableName> substitutionSources = new HashMap<>();

        /**
         * A set of variable names that substitution values for should be ignored at this time.
         */
        private final Set<VariableName> ignoreNames = new HashSet<>();

        /**
         * A Stack containing all the names that have been removed from different scopes.
         */
        private final Stack<Set<TaggedVariableName>> removed = new Stack<>();

        /**
         * Map of wrapped trees, the value they evaluate to.
         */
        private final Map<TreeID, SubExprDesc> substituteExprs = new HashMap<>();

        /**
         * A map that allows the substitution a tree maps to be changed. This allows for different
         */
        private final Stack<Map<WrappedTransReturn<BooleanVariable>, TreeID>> activeExprSubstitutions = new Stack<>();

        /**
         * A Map for recording the variables that other variables are dependent on.
         */
        private final Map<VariableDescription<?>, Set<TransTreeReturn<BooleanVariable>>> dependencyTrackingExpr = new HashMap<>();

        /**
         * Variable tracking for the tree.
         */
        private final VariableTracking vars;

        /**
         * A variable to flag if we should use restricted substitutions.
         */
        private boolean useRestricted = false;

        public void useRestricted() {
            useRestricted = true;
        }

        public void ignoreRestricted() {
            useRestricted = false;
        }

        public Substitutes(VariableTracking vars) {
            removed.push(new HashSet<>());
            activeExprSubstitutions.push(new HashMap<>());
            this.vars = vars;
        }

        /**
         * Method to stop a tree being substituted into itself
         * 
         * @param tree
         */
        public void startTree(TransTreeReturn<?> tree) {
            VariableName name = substitutionSources.get(tree.id);
            if(name != null)
                ignoreNames.add(name);
        }

        /**
         * Method to mark that a given tree has finished being updated.
         * 
         * @param tree
         */
        public void endTree(TransTreeReturn<?> tree) {
            VariableName name = substitutionSources.get(tree.id);
            if(name != null)
                ignoreNames.remove(name);
        }

        /**
         * Method to add a substitution to the set of available substitutions.
         * 
         * @param name       The name of the variable.
         * @param value      The value that should be substituted instead of the named variable.
         * @param sourceID   The ID of the tree that goes with this substitution.
         * @param comment    The comment that goes with this value.
         * @param restricted Is this substitution restricted to only being used in some circumstances?
         */
        private void add(TaggedVariableName name, TransTreeReturn<?> value, TreeID sourceID, String comment,
                boolean restricted) {
            add(name, value, vars.readVars(value), sourceID, comment, restricted);
        }

        /**
         * Method to add a substitution to the set of available substitutions.
         * 
         * @param taggedName   The name of the variable.
         * @param value        The value that should be substituted instead of the named variable.
         * @param dependencies A map listing the dependencies of the substitution.
         * @param sourceID     The ID of the tree that goes with this substitution.
         * @param comment      The comment that goes with this value.
         * @param restricted   Is this substitution restricted to only being used in some circumstances?
         */
        private void add(TaggedVariableName taggedName, TransTreeReturn<?> value, ScopedVarSet dependencies,
                TreeID sourceID, String comment, boolean restricted) {
            // Get the substitutes for this variable, creating the map if required.
            Map<TreeID, SubDesc> m = substitutes.computeIfAbsent(taggedName.name, k -> new HashMap<>());

            // Add the substitution for this tag, and the corresponding comment.
            m.put(taggedName.tag, new SubDesc(value, sourceID, dependencies, restricted, removed.size()));
            comments.put(value.id, comment);

            // Record the dependencies of this substitution.
            addDependencies(taggedName, dependencies);

            substitutionSources.put(sourceID, taggedName.name);
        }

        /**
         * Method to add a substitution to the set of available substitutions.
         * 
         * @param tree         The tree to substitute.
         * @param value        The value that should be substituted instead of the named variable.
         * @param dependencies A map listing the dependencies of the substitution.
         */
        private void add(TransTreeReturn<BooleanVariable> tree, TransTreeReturn<BooleanVariable> value,
                ScopedVarSet dependencies) {
            WrappedTransReturn<BooleanVariable> w = new WrappedTransReturn<>(tree);
            Map<WrappedTransReturn<BooleanVariable>, TreeID> activeExprMap = activeExprSubstitutions.peek();
            TreeID id = activeExprMap.get(w);
            if(id == null) {
                id = tree.id;
                activeExprMap.put(w, id);
                substituteExprs.put(id, new SubExprDesc(value, dependencies));
                addDependencies(tree);
            }
        }

        /**
         * Method to add dependencies for substitutions.
         * 
         * @param name         The variable that the dependencies are being stored for.
         * @param dependencies A map of the dependencies of the expression replacing the variable name.
         */
        private void addDependencies(TaggedVariableName name, ScopedVarSet dependencies) {
            for(VariableDescription<?> n:dependencies.getVars()) {
                Set<TaggedVariableName> d = dependencyTracking.computeIfAbsent(n, k -> new HashSet<>());
                d.add(name);
            }
        }

        /**
         * Method to add dependencies for substitutions.
         * 
         */
        private void addDependencies(TransTreeReturn<BooleanVariable> tree) {
            ScopedVarSet dependencies = vars.readVars(tree);
            for(VariableDescription<?> n:dependencies.getVars()) {
                Set<TransTreeReturn<BooleanVariable>> d = dependencyTrackingExpr.computeIfAbsent(n,
                        k -> new HashSet<>());
                d.add(tree);
            }
        }

        private void remove(TaggedVariableName taggedName, boolean updatingArray) {
            Map<TreeID, SubDesc> m = substitutes.get(taggedName.name);
            if(m != null) {
                SubDesc s = m.get(taggedName.tag);
                if(s != null) { // If it has not already been removed when a variable was written to.
                    /*
                     * If this is invalidated because of an array modification, but the substitute is just a load of the
                     * array reference, not an access of the array then the substitution does not need to be removed.
                     */
                    if(!updatingArray || s.substitute.type != TransTreeType.LOAD) {
                        if(s.scopeDepth > removed.size())
                            removed.peek().add(taggedName);
                        else {
                            m.remove(taggedName.tag);
                            for(VariableDescription<?> n:s.dependencies.getVars()) {
                                Set<TaggedVariableName> d = dependencyTracking.get(n);
                                d.remove(taggedName);
                                if(d.isEmpty())
                                    dependencyTracking.remove(n);
                            }
                            substitutionSources.remove(s.sourceID);
                            comments.remove(s.sourceID);
                        }
                    }
                }
            }
        }

        private void remove(TransTreeReturn<BooleanVariable> tree) {
            int size = activeExprSubstitutions.size();
            WrappedTransReturn<BooleanVariable> w = new WrappedTransReturn<>(tree);
            TreeID id = activeExprSubstitutions.peek().remove(w);
            // If this scope is the scope the value is defined in remove it entirely.
            if(size == 1 || activeExprSubstitutions.get(size - 2).get(w) != tree.id) {
                // If the tree is substituted, the tree will not be if an equivalent tree was
                // earlier
                // in the transformation.
                if(id != null) {
                    SubExprDesc s = substituteExprs.remove(id);
                    if(s != null) { // If it has not already been removed when a variable was written to.
                        for(VariableDescription<?> n:s.dependencies.getVars()) {
                            Set<TransTreeReturn<BooleanVariable>> d = dependencyTrackingExpr.get(n);
                            d.remove(tree);
                            if(d.isEmpty())
                                dependencyTrackingExpr.remove(n);
                        }
                    }
                }
            }
        }

        /**
         * Method to remove all substitutes that have values dependent on a specific variable name. This method should
         * be called after a variable is updated.
         * 
         * @param name The variable name.
         */
        private void removeDependantSubstitutions(VariableDescription<?> name, boolean updatingArray) {
            Set<TaggedVariableName> dependencies = dependencyTracking.get(name);
            // If the array is being updated, the reference will still be the same so does
            // not need removing, but the expressions might have changed. for example
            // a[0] == 0 might not be known anymore.
            if(dependencies != null) {
                Set<TaggedVariableName> toRemove = new HashSet<>(dependencies);
                for(TaggedVariableName n:toRemove)
                    remove(n, updatingArray);
            }

            Set<TransTreeReturn<BooleanVariable>> dependencyExprs = dependencyTrackingExpr.get(name);
            if(dependencyExprs != null) {
                Set<TransTreeReturn<BooleanVariable>> toRemove = new HashSet<>(dependencyExprs);
                for(TransTreeReturn<BooleanVariable> tree:toRemove)
                    remove(tree);
            }
        }

        /**
         * Method to remove all substitutes that have values dependent on a set of variable names. This method should be
         * called after a set of variables is updated.
         * 
         * @param names The variable names.
         */
        public void removeDependantSubstitutions(Set<VariableDescription<?>> descs, boolean updatingArray) {
            for(VariableDescription<?> desc:descs)
                removeDependantSubstitutions(desc, updatingArray);
        }

        /**
         * Recover the comment for the tree.
         * 
         * @param tree The tree to get the comment for.
         * @return The comment.
         */
        public String getComment(TransTreeReturn<?> tree) {
            return comments.get(tree.id);
        }

        /**
         * Method to look for substitute values for the value being loaded in a load node.
         * 
         * @param l The load node that could be substituted.
         * @return The substitute value if available, otherwise null.
         */
        public TransTree<?> get(TransLoad<?> l) {
            if(ignoreNames.contains(l.varDesc.name))
                return null;
            // Get the set of writes that this load could potentially read from.
            ScopedVarSet readVars = vars.readVars(l);

            if(readVars == null) // This is a tree that has been generated during this run, so does not have
                // tracking data.
                return null;

            int size = readVars.size();

            assert (size <= 1);
            // Is this an internally declared variable?
            if(size == 0)
                return null;

            VarDef varDef = readVars.getVarDef(l.varDesc);

            VariableName name = l.varDesc.name;
            Map<TreeID, SubDesc> m = substitutes.get(name);
            // Are there substitutes for this variable.
            if(m == null)
                return null;
            else {// if the are substitutes return the substitute for this instance if it is
                  // available.
                Iterator<TreeID> writeIDs = varDef.writeLocations().iterator();

                TreeID writeTag = writeIDs.next();
                if(removed.peek().contains(new TaggedVariableName(name, writeTag)))
                    return null;
                SubDesc subDesc = m.get(writeTag);
                if((subDesc == null) || (!useRestricted && subDesc.restricted))
                    return null;

                // TODO work out how to handle substitutions from further ahead in the tree. This will
                // probably require multiple passes and it is not clear at this point that this is
                // worth while.
                while(writeIDs.hasNext()) {
                    writeTag = writeIDs.next();
                    if(removed.peek().contains(new TaggedVariableName(name, writeTag)))
                        return null;
                    SubDesc subDescAlt = m.get(writeTag);
                    if(subDescAlt == null)
                        return null;
                    else {
                        if(!useRestricted && subDescAlt.restricted)
                            return null;
                        if(!subDescAlt.substitute.equivalent(subDesc.substitute))
                            return null;
                    }
                }
                return subDesc.substitute;
            }
        }

        public TransTreeReturn<BooleanVariable> get(TransTreeReturn<BooleanVariable> tree) {
            WrappedTransReturn<BooleanVariable> w = new WrappedTransReturn<>(tree);
            TreeID id = activeExprSubstitutions.peek().get(w);
            if(id != null && id != tree.id) {
                SubExprDesc s = substituteExprs.get(id);
                return s.substitute.copy();
            } else
                return null;
        }

        public void enterIfElseScope() {
            removed.push(new HashSet<>(removed.peek()));
            activeExprSubstitutions.push(new HashMap<>(activeExprSubstitutions.peek()));
        }

        public void leaveIfElseScope() {
            removed.pop();
            activeExprSubstitutions.pop();
        }
    }

    /**
     * An object holding information on where variables are allocated, written to and read from
     */
    private final VariableTracking vars;
    /**
     * A class for holding information about current substitutions.
     */
    private final Substitutes substitutes;
    /**
     * Stack for recording the current sequential block we are located in. As sequential statements merge all other
     * sequential statements into them when they are constructed each of these blocks will hold all the statements that
     * appear in order without additional scoping.
     */
    private final Stack<TransSequential> parents = new Stack<>();
    /**
     * A map of trees that need to become initialisations to the comments that need to go with these initialisations.
     */
    private final Map<TransTreeVoid, String> initializations = new HashMap<>();
    /**
     * A field used for constructing the comment that will go with a statement as the statement is built and bits of it
     * are substituted.
     */
    private String statementComment = Tree.NoComment;

    /**
     * A stack of lists holding the variable names that are currently in scope. each list in the stack represents the
     * variables declared in each of the current scopes, with the top most list being the variables in the innermost
     * scope.
     */
    private final Stack<List<VariableDescription<?>>> declaredVariables = new Stack<>();

    /**
     * A field to track the last array set call. This is done so that additional calls that just repeat the assignment
     * can be dropped.
     */
    private final Stack<TransArrayPut<?>> lastArraySet = new Stack<>();

    /**
     * The frequency that different writes are read.
     */
    private final ScalarReadFrequency scalarReadFrequency;

    /**
     * Tracking assignments to arrays that are never read because they are over written before they are read.
     */
    private final ArrayReadFrequency arrayReadFrequency;

    /**
     * Class to calculate the greatest common denominator of integer values.
     */
    private final IntGCD gcds;

    /**
     * A Stack of stacks to record array indexes.
     */
    private final Stack<Stack<IntVariable>> arrayIndexes;

    /**
     * A mapping of for loop ids to the corresponding trees.
     */
    private final Map<TreeID, TransFor> forLoopLookup = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param knownValues The values that are known from the calling class.
     * @param t           The tree that the unrequired assignments are to be removed from.
     */
    public SubstituteKnownValuesTransformer(KnownValuesTrans knownValues, TransTree<?> t) {
        // Get the variable tracking information.
        Set<TransTree<?>> knownTrees = new HashSet<>();
        for(KnownValue v:knownValues)
            knownTrees.add(v.expr);
        vars = t.getVariableTracking(knownTrees);
        gcds = new IntGCD(t, vars);
        substitutes = new Substitutes(vars);
        arrayIndexes = new Stack<>();
        arrayIndexes.push(new Stack<>());

        // Initialise everything else
        scalarReadFrequency = new ScalarReadFrequency(vars);
        arrayReadFrequency = new ArrayReadFrequency(vars, t);
        declaredVariables.push(new ArrayList<>());
        lastArraySet.push(null);

        if(!knownValues.isEmpty()) {
            // Create a visited nodes set for the transformations performed on the known values
            visitedNodes = new HashSet<>();
            // Insert the known values.
            for(KnownValue v:knownValues) {
                GuardSubstitutions gs = new GuardSubstitutions(v.expr);
                /*
                 * Apply the optimisation to the know values in case this changes the value. This is deliberately done
                 * after the guard substitutions is created to prevent the read vars being effected, but will be used on
                 * the next iteration
                 */
                v.expr = transform(v.expr);

                if(v.value)
                    gs.applyIfSubs();
                else
                    gs.applyElseSubs();
            }
        }
    }

    /**
     * Method for transforming return trees.
     * 
     * @param tree The tree to transform.
     * @return The transformed tree.
     */
    @Override
    public <X extends Variable<X>> TransTreeReturn<X> transformReturn(TransTreeReturn<X> tree) {
        substitutes.startTree(tree);
        TransTreeReturn<X> toReturn;
        switch(tree.type) {
            case LOAD:
                visitedNodes.add(tree);
                toReturn = transformTree((TransLoad<X>) tree);
                break;

            case ARRAY_GET:
                visitedNodes.add(tree);
                toReturn = transformTree((TransArrayGet<X>) tree);
                break;

            case EQUALITY:
                visitedNodes.add(tree);
                toReturn = (TransTreeReturn<X>) transformTree((TransEq<?, ?>) tree);
                break;

            case CONDITIONAL_ASSIGNMENT:
                visitedNodes.add(tree);
                toReturn = transformTree((TransConditionalAssignment<X>) tree);
                break;

            case ALLOCATE_ARRAY:
            case AND:
            case SUBTRACT:
            case MULTIPLY:
            case REMAINDER:
            case DIVIDE:
            case ADD:
            case CAST_DOUBLE:
            case CAST_INT:
            case CONST_BOOLEAN:
            case CONST_DOUBLE:
            case CONST_INT:
            case LOCAL_FUNCTION_CALL_RETURN:
            case EXTERNAL_FUNCTION_CALL_RETURN:
            case RV_FUNCTION_CALL_RETURN:
            case GET_FIELD:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
            case MAX:
            case MIN:
            case NEGATE:
            case NEGATE_BOOLEAN:
            case OR:
                toReturn = tree.applyTransformation(this);
                break;

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }
        substitutes.endTree(tree);

        // If this is a boolean and the tree has not been changed see if
        // we can just substitute the whole expression.
        if(tree.getOutputType() == VariableType.BooleanVariable && tree.equivalent(toReturn)) {
            @SuppressWarnings("unchecked")
            TransTreeReturn<BooleanVariable> t = substitutes.get((TransTreeReturn<BooleanVariable>) tree);
            if(t != null) {
                toReturn = (TransTreeReturn<X>) t;
            }
        }

        return toReturn;
    }

    private <X extends ScalarVariable<X>, Y extends ScalarVariable<Y>> TransTreeReturn<BooleanVariable> transformTree(
            TransEq<X, Y> tree) {
        substitutes.useRestricted();
        TransTreeReturn<X> left = transform(tree.left);
        TransTreeReturn<Y> right = transform(tree.right);
        substitutes.ignoreRestricted();
        if(left.equivalent(right))
            return constant(true);
        return TransTree.eq(transform(tree.left), transform(tree.right));
    }

    /**
     * Method for transforming a load node.
     * 
     * @param <X>  The type of the variable being loaded
     * @param tree The node doing the load.
     * @return The transformed tree.
     */
    private <X extends Variable<X>> TransTreeReturn<X> transformTree(TransLoad<X> tree) {
        // Look for a substitute value.
        @SuppressWarnings("unchecked")
        TransTreeReturn<X> s = (TransTreeReturn<X>) substitutes.get(tree);
        // If there isn't one continue as normal.
        if(s == null) {
            TransTreeReturn<X> toReturn = tree.applyTransformation(this);
            if(tree.getOutputType() == VariableType.IntVariable)
                gcds.addMapping((TransLoad<IntVariable>) tree, (TransLoad<IntVariable>) toReturn);
            return toReturn;
        } else {
            // Otherwise construct a comment to go with the substituted statement
            if(tree.varDesc.name.comment) {
                String subComment = substitutes.getComment(s);
                if(subComment != Tree.NoComment) {
                    if(statementComment == Tree.NoComment)
                        statementComment = subComment;
                    else
                        statementComment = "\n\n" + subComment;
                }
            } else {
                String comment = "Substituted \"" + tree.varDesc.name + "\" with its value \"" + s + "\".";
                String subComment = substitutes.getComment(s);
                if(subComment != Tree.NoComment)
                    comment = "\n" + tree.varDesc + "'s comment\n" + subComment;
                if(statementComment == Tree.NoComment)
                    statementComment = comment;
                else
                    statementComment = "\n\n" + comment;
            }
            // and transform the resultant tree.
            return transformReturn(s);
        }
    }

    /**
     * Method to apply substitutions to an array get.
     * 
     * @param <X>  The type returned by the get operation.
     * @param tree The tree to transform.
     * @return The transformed tree.
     */
    private <X extends Variable<X>> TransTreeReturn<X> transformTree(TransArrayGet<X> tree) {
        TransTreeReturn<IntVariable> index = transformReturn(tree.index);
        TransTreeReturn<ArrayVariable<X>> array = transformReturn(tree.array);
        // Construct a new tree node using the resultant values.
        return TransTree.arrayGet(array, index);
    }

    /**
     * Method for transforming tree that don't return values.
     * 
     * @param tree The tree to transform.
     * @return The transformed tree.
     */
    @Override
    public TransTreeVoid transformVoid(TransTreeVoid tree) {
        TransTreeVoid toReturn;
        switch(tree.type) {
            case STORE:
                visitedNodes.add(tree);
                toReturn = transformTree((TransStore<?>) tree);
                break;
            case INITIALIZE:
                visitedNodes.add(tree);
                toReturn = transformTree((TransInitialize<?>) tree);
                break;
            case INITIALIZE_UNSET:
                visitedNodes.add(tree);
                toReturn = transformTree((TransInitializeUnset<?>) tree);
                break;

            case SEQUENTIAL:
                visitedNodes.add(tree);
                toReturn = transformTree((TransSequential) tree);
                break;

            case ARRAY_PUT:
                visitedNodes.add(tree);
                toReturn = transformTree((TransArrayPut<?>) tree);
                break;

            case FOR:
                toReturn = transformTree((TransFor) tree);
                break;

            case SCOPE:
                toReturn = transformTree((TransTreeScope) tree);
                break;

            case IF:
                toReturn = transformTree((TransIfElse) tree);
                break;

            case LOCAL_FUNCTION_CALL:
            case RV_FUNCTION_CALL:
            case NOP:
                toReturn = tree.applyTransformation(this);
                break;

            default:
                throw new CompilerException("Unknown tree type " + tree.type + " encountered");
        }

        // After constructing the tree postfix and generated comment to it.
        toReturn.postfixComment(statementComment);
        statementComment = Tree.NoComment;
        return toReturn;
    }

    private TransTreeVoid transformTree(TransTreeScope tree) {
        pushScope();
        visitedNodes.add(tree);
        TransTreeVoid toReturn = tree.applyTransformation(this);
        popScope();
        return toReturn;
    }

    private TransTreeVoid transformTree(TransFor tree) {
        pushScope();
        declaredVariables.peek().add(tree.indexDesc);
        visitedNodes.add(tree);

        // Calculate the start value before removing all substitutions that
        // may be invalid after the first iteration.
        TransTreeReturn<IntVariable> start = transform(tree.start);

        substitutes.removeDependantSubstitutions(vars.written(tree), false);
        substitutes.removeDependantSubstitutions(vars.arraysModified(tree), true);

        TransTreeReturn<IntVariable> end = transform(tree.end);
        TransTreeReturn<IntVariable> step = transform(tree.step);
        String subComment = statementComment;
        statementComment = Tree.NoComment;

        TransTreeVoid toReturn = TransTree.forStmt(transform(tree.body), start, end, step, tree.indexDesc,
                tree.parallel, tree.incrementing, tree.getComment());

        statementComment = subComment;
        popScope();
        return toReturn;
    }

    private class GuardSubstitutions {
        private class SubstituteDescription<V extends Variable<V>> {
            final ScopedVarSet dependencies;
            final TransTreeReturn<V> value;
            final TransTreeReturn<?> source;

            SubstituteDescription(ScopedVarSet dependencies, TransTreeReturn<V> value, TransTreeReturn<?> source) {
                this.dependencies = dependencies;
                this.value = value;
                this.source = source;
            }
        }

        private final Map<VariableDescription<?>, SubstituteDescription<?>> ifNameSubs = new HashMap<>();
        private final Map<VariableDescription<?>, SubstituteDescription<?>> elseNameSubs = new HashMap<>();
        private final Map<TransTreeReturn<BooleanVariable>, SubstituteDescription<BooleanVariable>> ifExprSubs = new HashMap<>();
        private final Map<TransTreeReturn<BooleanVariable>, SubstituteDescription<BooleanVariable>> elseExprSubs = new HashMap<>();

        public GuardSubstitutions(TransTreeReturn<BooleanVariable> condition) {
            ScopedVarSet readVars = vars.readVars(condition);

            ifExprSubs.put(condition, new SubstituteDescription<>(readVars, constant(true), condition));
            elseExprSubs.put(condition, new SubstituteDescription<>(readVars, constant(false), condition));

            switch(condition.type) {
                case AND: {
                    TransAnd and = (TransAnd) condition;
                    for(TransTreeReturn<BooleanVariable> t:and.getConstraints()) {
                        GuardSubstitutions gs = new GuardSubstitutions(t);
                        ifNameSubs.putAll(gs.ifNameSubs);
                        ifExprSubs.putAll(gs.ifExprSubs);
                    }
                    break;
                }
                case EQUALITY: {
                    generateSubstitutionsEq((TransEq<?, ?>) condition);
                    break;
                }
                case LOAD: {
                    TransLoad<BooleanVariable> l = (TransLoad<BooleanVariable>) condition;
                    ifNameSubs.put(l.varDesc, new SubstituteDescription<>(readVars, constant(true), l));
                    elseNameSubs.put(l.varDesc, new SubstituteDescription<>(readVars, constant(false), l));
                    break;
                }
                case NEGATE_BOOLEAN: {
                    TransNegateBoolean nb = (TransNegateBoolean) condition;
                    GuardSubstitutions gs = new GuardSubstitutions(nb.input);
                    ifNameSubs.putAll(gs.elseNameSubs);
                    ifExprSubs.putAll(gs.elseExprSubs);
                    elseNameSubs.putAll(gs.ifNameSubs);
                    elseExprSubs.putAll(gs.ifExprSubs);
                    break;
                }
                case OR: {
                    TransOr or = (TransOr) condition;
                    for(TransTreeReturn<BooleanVariable> t:or.getConstraints()) {
                        GuardSubstitutions gs = new GuardSubstitutions(t);
                        elseNameSubs.putAll(gs.elseNameSubs);
                        elseExprSubs.putAll(gs.elseExprSubs);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        private void generateSubstitutionsEq(final TransEq<?, ?> eq) {
            ScopedVarSet readVars = vars.readVars(eq);
            Set<VariableDescription<?>> readNames = readVars.getVars();

            // Find the variable the constraint should target.
            List<VariableDescription<?>> declaredNames = declaredVariables.peek();
            int length = declaredNames.size();
            for(int i = length - 1; i >= 0; i--) {
                VariableDescription<?> v = declaredNames.get(i);
                if(readNames.contains(v)) {
                    if(generateSubstitution(eq, readVars, v))
                        return;
                }
            }

            // If we cannot find a declared variable try references to global variables and
            // function parameters.
            PriorityQueue<VariableDescription<?>> p = new PriorityQueue<>(readNames);
            p.removeAll(declaredNames);
            while(!p.isEmpty()) {
                if(generateSubstitution(eq, readVars, p.poll()))
                    return;
            }
        }

        private <A extends ScalarVariable<A>> boolean generateSubstitution(final TransEq<?, ?> eq,
                ScopedVarSet readVars, VariableDescription<?> variableDescription) {
            TransTreeReturn<A> replacement = RearrangeTree.rearrangeTree(eq,
                    (VariableDescription<A>) variableDescription, gcds);
            if(replacement != null) {
                replacement = replacement.collapseConstants();
                switch(replacement.type) {
                    case CONST_BOOLEAN:
                    case CONST_DOUBLE:
                    case CONST_INT:
                    case LOAD:
                        ifNameSubs.put(variableDescription, new SubstituteDescription<>(readVars, replacement, eq));
                        return true;
                    default:
                        break;
                }
            }
            return false;
        }

        private void applyIfSubs() {
            // Apply the named variables
            for(VariableDescription<?> desc:ifNameSubs.keySet()) {
                SubstituteDescription<?> d = ifNameSubs.get(desc);
                VarDef varDef = d.dependencies.getVarDef(desc);
                for(TreeID tag:varDef.writeLocations()) {
                    TaggedVariableName taggedName = new TaggedVariableName(desc.name, tag);
                    substitutes.add(taggedName, d.value, d.dependencies, d.source.id, Tree.NoComment, false);
                }
            }

            for(TransTreeReturn<BooleanVariable> tree:ifExprSubs.keySet()) {
                SubstituteDescription<BooleanVariable> d = ifExprSubs.get(tree);
                substitutes.add(tree, d.value, d.dependencies);
            }
        }

        private void removeIfSubs() {
            for(VariableDescription<?> desc:ifNameSubs.keySet()) {
                SubstituteDescription<?> d = ifNameSubs.get(desc);
                VarDef varDef = d.dependencies.getVarDef(desc);
                for(TreeID tag:varDef.writeLocations()) {
                    TaggedVariableName taggedName = new TaggedVariableName(desc.name, tag);
                    substitutes.remove(taggedName, false);
                }
            }

            for(TransTreeReturn<BooleanVariable> tree:ifExprSubs.keySet())
                substitutes.remove(tree);
        }

        private void applyElseSubs() {
            for(VariableDescription<?> desc:elseNameSubs.keySet()) {
                SubstituteDescription<?> d = elseNameSubs.get(desc);
                VarDef varDef = d.dependencies.getVarDef(desc);
                for(TreeID tag:varDef.writeLocations()) {
                    TaggedVariableName taggedName = new TaggedVariableName(desc.name, tag);
                    substitutes.add(taggedName, d.value, d.dependencies, d.source.id, Tree.NoComment, false);
                }
            }

            for(TransTreeReturn<BooleanVariable> tree:elseExprSubs.keySet()) {
                SubstituteDescription<BooleanVariable> d = elseExprSubs.get(tree);
                substitutes.add(tree, d.value, d.dependencies);
            }
        }

        private void removeElseSubs() {
            for(VariableDescription<?> desc:elseNameSubs.keySet()) {
                SubstituteDescription<?> d = elseNameSubs.get(desc);
                VarDef varDef = d.dependencies.getVarDef(desc);
                for(TreeID tag:varDef.writeLocations()) {
                    TaggedVariableName taggedName = new TaggedVariableName(desc.name, tag);
                    substitutes.remove(taggedName, false);
                }
            }

            for(TransTreeReturn<BooleanVariable> tree:elseExprSubs.keySet())
                substitutes.remove(tree);
        }
    }

    private TransTreeVoid transformTree(TransIfElse ifElse) {
        visitedNodes.add(ifElse);

        TransTreeReturn<BooleanVariable> condition = transform(ifElse.condition);

        TransTreeVoid ifBody;
        TransTreeVoid elseBody;

        // If the condition has changed our tags are out of date, so just construct the
        // tree.
        if(!condition.equivalent(ifElse.condition)) {
            String conditionComment = statementComment;
            statementComment = Tree.NoComment;

            pushScope();
            substitutes.enterIfElseScope();
            ifBody = transform(ifElse.ifBody);
            substitutes.leaveIfElseScope();
            popScope();

            pushScope();
            substitutes.enterIfElseScope();
            elseBody = transform(ifElse.elseBody);
            substitutes.leaveIfElseScope();
            popScope();

            statementComment = conditionComment;
        } else {
            // Go back to using the ifElse condition so that the trees appear in
            // the variable tracking data. The newly created trees would not be present.
            condition = ifElse.condition;

            // Otherwise construct and apply the substitutions.
            GuardSubstitutions gs = new GuardSubstitutions(condition);

            gs.applyIfSubs();

            condition = transform(condition);

            String conditionComment = statementComment;
            statementComment = Tree.NoComment;

            pushScope();
            substitutes.enterIfElseScope();
            ifBody = transform(ifElse.ifBody);
            substitutes.leaveIfElseScope();
            popScope();
            gs.removeIfSubs();

            gs.applyElseSubs();
            pushScope();
            substitutes.enterIfElseScope();
            elseBody = transform(ifElse.elseBody);
            substitutes.leaveIfElseScope();
            popScope();
            gs.removeElseSubs();

            statementComment = conditionComment;
        }

        // Remove any variables that may have changed inside the conditional
        substitutes.removeDependantSubstitutions(vars.written(ifElse), false);
        substitutes.removeDependantSubstitutions(vars.arraysModified(ifElse), true);

        return TransTree.ifElse(condition, ifBody, ifElse.getComment(), elseBody, ifElse.getElseComment(),
                ifElse.tags());
    }

    private <X extends Variable<X>> TransTreeReturn<X> transformTree(TransConditionalAssignment<X> t) {
        visitedNodes.add(t);

        TransTreeReturn<BooleanVariable> condition = transform(t.condition);

        TransTreeReturn<X> ifValue;
        TransTreeReturn<X> elseValue;

        // If the condition has changed our tags are out of date, so just construct the
        // tree.
        if(!condition.equivalent(t.condition)) {
            ifValue = transform(t.ifValue);
            elseValue = transform(t.elseValue);
        } else {
            // Go back to using the ifElse condition so that the trees appear in
            // the variable tracking data. The newly created trees would not be present.
            condition = t.condition;

            // Otherwise construct and apply the substitutions.
            GuardSubstitutions gs = new GuardSubstitutions(condition);

            gs.applyIfSubs();
            condition = transform(condition);
            ifValue = transform(t.ifValue);
            gs.removeIfSubs();

            gs.applyElseSubs();
            elseValue = transform(t.elseValue);
            gs.removeElseSubs();
        }

        return TransTree.conditionalAssignment(condition, ifValue, elseValue, t.tags());
    }

    /**
     * Transform a sequential block.
     * 
     * @param tree The tree to transform
     * @return The transformed tree.
     */
    private TransTreeVoid transformTree(TransSequential tree) {
        TransTreeVoid toReturn;
        // Push the block onto the stack of parents before starting so initialisation
        // statements can access it.
        parents.push(tree);
        toReturn = tree.applyTransformation(this);
        // Pop the block back off once the transformation is done.
        parents.pop();
        return toReturn;
    }

    /**
     * Transform the initialisation of an unset variable.
     * 
     * @param tree The tree to transform.
     * @return The transformed tree.
     */
    private TransTreeVoid transformTree(TransInitializeUnset<?> tree) {
        declaredVariables.peek().add(tree.varDesc);
        tree.addNodes(visitedNodes);
        // Determine the next tree to write to this variable.
        TransTreeVoid nextWrittenTo = nextWrittenTo(tree, tree.varDesc);
        // If there is no other trees writing to this variable, the variable is
        // not required, so return a NOP.
        if(nextWrittenTo == null)
            return TransTree.nop();
        else
        // If the next tree to write to this value is a store, flag the store
        // to be converted into an initialisation, and record the comment to go
        // from the initialisation to go with it.
        if(nextWrittenTo.type == TransTreeType.STORE) {
            initializations.put(nextWrittenTo, tree.getComment());
            return TransTree.nop();
        } else {
            // Otherwise the first store to this variable is embedded in another
            // scope such as an if or a for loop. If this is the case we still need
            // the initialisation, so just return the tree.
            return tree;
        }
    }

    /**
     * Method for finding the next tree to potentially write to a given variable.
     * 
     * @param tree The tree that currently initializes the variable.
     * @param desc The description of the variable.
     * @return The first tree after the initialisation that writes to this variable. Null if there are no more trees
     *         writing to this variable.
     */
    private TransTreeVoid nextWrittenTo(TransTreeVoid tree, VariableDescription<?> desc) {
        // Get the parent sequential trees. Because sequential nodes merge into each
        // other so that they are never directly embedded in each other all we don't
        // need to look any deeper to find all the statements that can execute with
        // the named variable in scope.
        TransSequential ts = parents.peek();
        List<TransTreeVoid> trees = ts.getTrees();
        // A flag to say if we have passed the point that the variable is currently
        // initialized at.
        boolean initializationPassed = false;
        // For each tree.
        for(TransTreeVoid t:trees) {
            // If the variable has been initialized, and this tree writes to it return this
            // tree.
            if(initializationPassed && vars.written(t).contains(desc))
                return t;
            // If this tree equals the initialising tree set the flag to mark the variable
            // as initialized.
            if(tree == t)
                initializationPassed = true;
        }
        // If no writing tree is found return null to signal this.
        return null;
    }

    /**
     * Method to transform an array set.
     * 
     * @param <X>  The type of value being placed into the array.
     * @param tree The tree to be transformed.
     * @return The transformed tree.
     */
    private <X extends Variable<X>> TransTreeVoid transformTree(TransArrayPut<X> tree) {
        if(arrayReadFrequency.modificationNotRead(tree)) {
            tree.addNodes(visitedNodes);
            return TransTree.nop();
        }

        TransArrayPut<?> lastTree = lastArraySet.peek();
        if(lastTree != null && tree.equivalent(lastTree)) {
            ScopedVarSet inScope = vars.inScopeVars(tree);

            // Check the value reads the same values
            ScopedVarSet readVars = vars.readVars(lastTree.value);
            for(VariableDescription<?> desc:readVars.getVars())
                if(!readVars.getVarDef(desc).equals(inScope.getVarDef(desc)))
                    return transformBasicArraySet(tree);

            // Check the index reads the same values
            readVars = vars.readVars(lastTree.index);
            for(VariableDescription<?> desc:readVars.getVars())
                if(!readVars.getVarDef(desc).equals(inScope.getVarDef(desc)))
                    return transformBasicArraySet(tree);

            /*
             * Check the array is set by the last instance this check means we do not need to clear last array set as
             * this check will fail if the set was inside a conditional or a loop.
             */
            TransTreeReturn array = tree.array;
            TransTreeReturn lastArray = lastTree.array;
            while(array.type == TransTreeType.ARRAY_GET) {
                TransArrayGet<?> ag = (TransArrayGet<?>) array;
                TransArrayGet<?> lag = (TransArrayGet<?>) lastArray;
                array = ag.array;
                lastArray = lag.array;

                // Check the index
                readVars = vars.readVars(lag.index);
                for(VariableDescription<?> desc:readVars.getVars())
                    if(!readVars.getVarDef(desc).equals(inScope.getVarDef(desc)))
                        return transformBasicArraySet(tree);
            }

            TransLoad<?> tl = (TransLoad<?>) array;
            readVars = vars.readVars(tl);
            assert (readVars.size() == 1);
            VarDef vCurrentArray = readVars.getVarDef(tl.varDesc);
            VarDef vLastArray = vars.inScopeVars(lastTree).getVarDef(tl.varDesc);
            if(!vLastArray.declarationId.equals(vCurrentArray.declarationId))
                return transformBasicArraySet(tree);
            else {
                Set<AssignmentDesc> currentAssignments = vCurrentArray.currentAssignments();
                Set<AssignmentDesc> lastAssignments = vLastArray.currentAssignments();
                if(currentAssignments.size() != lastAssignments.size() + 1)
                    return transformBasicArraySet(tree);
                for(AssignmentDesc a:currentAssignments) {
                    if(!lastAssignments.contains(a)) {
                        if(a.pathLength() != 0)
                            return transformBasicArraySet(tree);
                        else {
                            if(!a.assignmentLocation().equals(lastTree.id))
                                return transformBasicArraySet(tree);
                        }
                    }
                }
            }

            // If all the variables are correct drop this assignment.
            lastArraySet.pop();
            lastArraySet.push(tree);
            tree.addNodes(visitedNodes);
            return TransTree.nop();
        }

        return transformBasicArraySet(tree);
    }

    private <X extends Variable<X>> TransTreeVoid transformBasicArraySet(TransArrayPut<X> tree) {
        // Transform the index and the value
        TransTreeReturn<IntVariable> index = transformReturn(tree.index);
        TransTreeReturn<X> value = transformReturn(tree.value);
        TransTreeReturn<ArrayVariable<X>> array = transformReturn(tree.array);

        // Remove any substitutions that depend on the array being modified.
        substitutes.removeDependantSubstitutions(vars.arraysModified(tree), true);

        // Construct a new node and return it.
        // If the array is setting itself to the value currently in the array do
        // nothing.
        if(value.type == TransTreeType.ARRAY_GET && ((TransArrayGet<X>) value).index.equivalent(index)
                && ((TransArrayGet<X>) value).array.equivalent(array))
            return TransTree.nop();

        lastArraySet.pop();
        lastArraySet.push(tree);

        return TransTree.arrayPut(array, index, value, tree.getComment());
    }

    /**
     * Method to transform an initialisation tree.
     * 
     * @param tree The tree to transform.
     * @return The transformed tree.
     */
    private <X extends Variable<X>> TransTreeVoid transformTree(TransInitialize<X> tree) {
        declaredVariables.peek().add(tree.varDesc);
        // Store the value to transform.
        TransTreeReturn<X> value = tree.value;
        // Transform the value.
        TransTreeReturn<X> newValue = transformReturn(value);
        // Test if the new value and the old value are equivalent. If they are not it is
        // not
        // safe to apply transformations here as we don't have up-to-date information,
        // so
        // just return the tree, and try again on the next iteration of the optimisation
        // phase.
        if(!newValue.equivalent(value))
            return TransTree.initializeVariable(tree.varDesc, newValue, tree.getComment());
        else { // If they are the same.
            TaggedVariableName idName = new TaggedVariableName(tree.varDesc.name, tree.id);

            // Depending on the frequency of the reads update the tree.
            switch(scalarReadFrequency.frequency(idName)) {
                // If the value is read multiple times it can only be substituted if it is
                // a load or a constant. If it is, add it to the substitutions and record the
                // dependencies.
                case MULTIPLE:
                    if(value.type == TransTreeType.LOAD || value.type == TransTreeType.CONST_BOOLEAN
                            || value.type == TransTreeType.CONST_DOUBLE || value.type == TransTreeType.CONST_INT)
                        substitutes.add(idName, value, tree.id, tree.getComment(), false);
                    else if(value.type == TransTreeType.CAST_DOUBLE) {
                        TransCastToDouble v = (TransCastToDouble) value;
                        substitutes.add(idName, value, tree.id, tree.getComment(), v.input.type != TransTreeType.LOAD);
                    } else if(value.type == TransTreeType.CAST_INT) {
                        TransCastToInteger v = (TransCastToInteger) value;
                        substitutes.add(idName, value, tree.id, tree.getComment(), v.input.type != TransTreeType.LOAD);
                    } else
                        substitutes.add(idName, value, tree.id, tree.getComment(), true);

                    return TransTree.initializeVariable(tree.varDesc, value, tree.getComment());

                // If the value is never read downgrade it.
                case NEVER:
                    // Determine the next tree to write to this value
                    TransTreeVoid nextWrittenTo = nextWrittenTo(tree, tree.varDesc);
                    // If there is no further tree that writes to it replace the declaration with a
                    // NOP.
                    if(nextWrittenTo == null)
                        return TransTree.nop();
                    else {
                        // If the next write is a store in this scope flag that to be an initialisation
                        // storing the comment, and return a NOP for this tree.
                        if(nextWrittenTo.type == TransTreeType.STORE) {
                            initializations.put(nextWrittenTo, tree.getComment());
                            return TransTree.nop();
                        } else { // If the value is written to in a different scope, make this an unset
                            // declaration.
                            TransTreeVoid toReturn = TransTree.initializeUnsetVariable(tree.varDesc,
                                    "This value is not used before it is set again, so removing the value declaration.");
                            toReturn.postfixComment(tree.getComment());
                            return toReturn;
                        }
                    }
                    // If the value is only read once add it to the set of possible substitutions.
                    // If this substitution is
                    // used the initialisation will be removed on the next pass.
                case ONCE: {
                    substitutes.add(idName, value, tree.id, tree.getComment(), false);
                    return TransTree.initializeVariable(tree.varDesc, value, tree.getComment());
                }
                default:
                    throw new CompilerException("This should not be reachable.");
            }
        }
    }

    /**
     * A method to transform a store tree.
     * 
     * @param tree The tree to transform.
     * @return The transformed tree.
     */
    private <X extends Variable<X>> TransTreeVoid transformTree(TransStore<X> tree) {
        TransTreeReturn<X> value = tree.value;
        TransTreeVoid toReturn;
        TransTreeReturn<X> newValue = transformReturn(value);

        // Catch store that store themselves. This case is missed by read frequency if
        // the value is also read elsewhere.
        if(newValue.type == TransTreeType.LOAD && ((TransLoad<X>) newValue).varDesc.equals(tree.varDesc))
            return TransTree.nop();

        // If a declared variable and the value has not been changed to invalidate the
        // written information.
        if(!newValue.equivalent(value) || !value.deterministic())
            toReturn = TransTree.store(tree.varDesc, newValue, tree.getComment());
        else {
            TaggedVariableName idName = new TaggedVariableName(tree.varDesc.name, tree.id);

            Frequency rf = scalarReadFrequency.frequency(idName);
            // If the value is global include that it will be read at some point out of the global state.
            if(vars.isGlobal(tree)) {
                switch(rf) {
                    case NEVER:
                        rf = Frequency.ONCE;
                        break;
                    case ONCE:
                        rf = Frequency.MULTIPLE;
                        break;
                    default:
                        break;

                }
            }

            switch(rf) {
                // If the value is read multiple times it can only be substituted if it is
                // a load or a constant. If it is, add it to the substitutions and record the
                // dependencies.
                case MULTIPLE:
                    if(value.type == TransTreeType.LOAD || value.type == TransTreeType.CONST_BOOLEAN
                            || value.type == TransTreeType.CONST_DOUBLE || value.type == TransTreeType.CONST_INT) {
                        // The old value is required in order to access information from vars
                        substitutes.add(idName, value, tree.id, tree.getComment(), false);
                    } else if(value.type == TransTreeType.CAST_DOUBLE) {
                        TransCastToDouble v = (TransCastToDouble) value;
                        substitutes.add(idName, value, tree.id, tree.getComment(), v.input.type != TransTreeType.LOAD);
                    } else if(value.type == TransTreeType.CAST_INT) {
                        TransCastToInteger v = (TransCastToInteger) value;
                        substitutes.add(idName, value, tree.id, tree.getComment(), v.input.type != TransTreeType.LOAD);
                    } else
                        substitutes.add(idName, value, tree.id, tree.getComment(), true);

                    toReturn = TransTree.store(tree.varDesc, value, tree.getComment());
                    break;
                // If the value is never used, if it is global return the tree, otherwise remove
                // the assignment.
                case NEVER:
                    toReturn = TransTree.nop();
                    break;
                // If the value is only read once add it to the set of possible substitutions.
                case ONCE:
                    // The old value is required in order to access information from vars
                    substitutes.add(idName, value, tree.id, tree.getComment(), false);
                    toReturn = TransTree.store(tree.varDesc, value, tree.getComment());
                    break;
                default:
                    throw new CompilerException("This should not be reachable.");
            }
        }

        // Check if this update invalidates any of our set substitutions.
        substitutes.removeDependantSubstitutions(tree.varDesc, false);

        // Modify output to an initialisation if this tree has been flagged for
        // conversion.
        if(initializations.containsKey(tree)) {
            if(toReturn.type == TransTreeType.NOP)
                toReturn = TransTree.initializeUnsetVariable(tree.varDesc, tree.getComment());
            else {
                TransStore<X> store = (TransStore<X>) toReturn;
                toReturn = TransTree.initializeVariable(store.varDesc, store.value, store.getComment());
            }
            String initComment = initializations.get(tree);
            toReturn.prefixComment("Variable declaration of " + tree.varDesc + " moved."
                    + (initComment == null ? "" : "\nDeclaration comment was:\n" + initComment));
        }

        return toReturn;
    }

    private void pushScope() {
        declaredVariables.push(new ArrayList<>(declaredVariables.peek()));
        lastArraySet.push(lastArraySet.peek());
    }

    private void popScope() {
        // Pop the scope and remove any substitutions that depend on variables set in
        // this scope.
        List<VariableDescription<?>> scopeVars = declaredVariables.pop();
        int noScopeVars = scopeVars.size();
        int noCurrentVars = declaredVariables.peek().size();
        for(int i = noCurrentVars; i < noScopeVars; i++)
            substitutes.removeDependantSubstitutions(scopeVars.get(i), false);
        lastArraySet.pop();
    }
}
