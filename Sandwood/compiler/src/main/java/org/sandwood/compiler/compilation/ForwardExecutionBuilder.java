/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import static org.sandwood.compiler.trees.irTree.IRTree.addDD;
import static org.sandwood.compiler.trees.irTree.IRTree.and;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayPut;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.divideDD;
import static org.sandwood.compiler.trees.irTree.IRTree.eq;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.DistributableRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.ScopeConstructor.Guards;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;
import org.sandwood.compiler.util.Pair;

public class ForwardExecutionBuilder {
    /**
     * An enum to represent which conditional guards should be added to the generated code.
     */
    public enum GuardStatus {
        /**
         * No guards are required.
         */
        NONE,
        /**
         * Only execute if the inputs fixed.
         */
        FIXED,
        /**
         * Only execute if at least one of the inputs is free.
         */
        FREE;
    }

    public static void constructForwardMethod(Variable<?> intermediate, GuardStatus guardStatus,
            boolean useDistributions, CompilationContext compilationCtx) {
        Set<TraceHandle> obsTraces = Collections.emptySet();
        constructForwardMethod(intermediate, obsTraces, guardStatus, useDistributions, compilationCtx);
    }

    // TODO make sure this is only called on intermediates or samples. Once this is done calls
    // to isIntermediate can be removed as they will always be true.
    public static void constructForwardMethod(Variable<?> intermediate, Set<TraceHandle> obsTraces,
            GuardStatus guardStatus, boolean useDistributions, CompilationContext compilationCtx) {
        if(intermediate instanceof RandomVariable) {
            RandomVariable<?, ?> rv = (RandomVariable<?, ?>) intermediate;

            if(rv.distributionSampled() && useDistributions) {
                // If we are using restrictions in this code construct a guard for the code
                // generating this intermediate.
                IRTreeReturn<BooleanVariable> guard = null;
                // We want to generate new values from the distribution unless all the consumers
                // won't use it.
                Set<SampleTask<?, ?>> sTasks = new HashSet<>();
                for(DataflowTask<?> task:rv.getConsumers()) {
                    SampleTask<?, ?> sTask = (SampleTask<?, ?>) task;
                    sTasks.add(sTask);
                }
                guard = constructGuard(sTasks, guardStatus);
                compilationCtx.setCodeGuard(guard);

                constructForwardRVDistribution((DistributableRandomVariable<?, ?>) rv, compilationCtx);

                compilationCtx.clearCodeGuard();
            }
        } else {
            if(!(intermediate.isDistribution() && useDistributions)) {
                // A special case for if assignments.
                if(intermediate.getParent().getType() == DFType.IF_ASSIGNMENT)
                    forwardIfAssignment(intermediate, obsTraces, guardStatus, compilationCtx);
                else
                    constructVariableForward(intermediate, obsTraces, guardStatus, compilationCtx);
            }
        }
    }

    private static <A extends Variable<A>> void forwardIfAssignment(Variable<A> intermediate,
            Set<TraceHandle> obsTraces, GuardStatus guardStatus, CompilationContext compilationCtx) {
        /*
         * This indirection is done to allow for the case where the intermediate variable is allocated inside a for
         * loop. When this happens an array is created to allow each instantiation of the intermediate variable to be
         * saved, and this array needs dereferencing. As this is constructed by the compiler there is no problem with
         * complex indices, or different orders to the indices.
         */
        List<Pair<Variable<A>, Scope>> sources = new ArrayList<>();
        sources.add(new Pair<>(intermediate, intermediate.scope()));
        while(!sources.isEmpty()) {
            Pair<Variable<A>, Scope> p = sources.remove(sources.size() - 1);
            Variable<A> v = p.a;
            Scope s = p.b;
            DataflowTask<A> parent = v.getParent();
            if(parent.getType() == DFType.IF_ASSIGNMENT) {
                IfElseAssignmentTask<A> ifElseParent = (IfElseAssignmentTask<A>) parent;
                sources.add(new Pair<>(ifElseParent.ifValue, ifElseParent.ifScope));
                sources.add(new Pair<>(ifElseParent.elseValue, ifElseParent.ifScope.elseScope));
            } else {
                // If we are using restrictions in this code construct a guard for the code
                // generating this intermediate.
                IRTreeReturn<BooleanVariable> guard = constructGuard(guardStatus, compilationCtx, v,
                        s.getScopeCondition());

                if(obsTraces.isEmpty()) {
                    compilationCtx.setCodeGuard(guard);
                    constructIfAssignmentVariable(intermediate, v, s, compilationCtx);
                    // If a guard was generated clear it.
                    compilationCtx.clearCodeGuard();
                } else {
                    observationGuard(v, obsTraces, guard, () -> {
                        constructIfAssignmentVariable(intermediate, v, s, compilationCtx);
                    }, compilationCtx);
                }
            }
        }
    }

    private static <A extends Variable<A>> void constructIfAssignmentVariable(Variable<A> intermediate, Variable<A> v,
            Scope s, CompilationContext compilationCtx) {
        IRTreeReturn<A> tree;
        if(v.isIntermediate() || v.isSample())
            tree = IRTree.load(v);
        else
            tree = v.getForwardIR(compilationCtx);

        compilationCtx.addTreeToScope(s, IRTree.store(intermediate.getUniqueVarDesc(), tree, Tree.NoComment));
    }

    private static <A extends Variable<A>> void constructVariableForward(Variable<A> intermediate,
            Set<TraceHandle> obsTraces, GuardStatus guardStatus, CompilationContext compilationCtx) {
        // Otherwise just generate regular code.
        // NOTE Get parent is required to avoid tests that stop code generation at
        // intermediate
        // values.
        if(!intermediate.isDeterministic() || compilationCtx.phase == CompilationPhase.INITIALIZATION_OF_CONSTANTS) {
            // If a sequence of put tasks find the value being assigned so scope constraints are not included.
            Variable<?> v = intermediate;
            ProducingDataflowTask<?> p = v.getParent();
            while(p.getType() == DFType.PUT) {
                PutTask<?> pt = (PutTask<?>) p;
                v = pt.value;
                p = v.getParent();
            }

            // If we are using restrictions in this code construct a guard for the code
            // generating this intermediate.
            IRTreeReturn<BooleanVariable> guard = constructGuard(guardStatus, compilationCtx, v);

            if(obsTraces.isEmpty()) {
                compilationCtx.setCodeGuard(guard);
                forwardVariableBody(intermediate, compilationCtx);
                compilationCtx.clearCodeGuard();
            } else {
                observationGuard(intermediate, obsTraces, guard, () -> {
                    forwardVariableBody(intermediate, compilationCtx);
                }, compilationCtx);
            }
        }
    }

    private interface TreeBuilder {
        void build();
    }

    private static <A extends Variable<A>> void observationGuard(Variable<A> v, Set<TraceHandle> obsTraces,
            IRTreeReturn<BooleanVariable> guard, TreeBuilder t, CompilationContext compilationCtx) {

        ScopeConstructor sc = ScopeConstructor.construct(v.getParent(), Tree.NoComment, compilationCtx);

        if(guard != null)
            sc = sc.addCondition(guard).ifScopeConstructor;

        VariableDescription<BooleanVariable> guardName = VariableNames.observedGuard(v);
        sc.addTree((TreeBuilderInfo info) -> {
            compilationCtx.addTreeToScope(GlobalScope.scope, IRTree.initializeVariable(guardName,
                    IRTree.constant(false), "Track if it is possible to reach an observed variable"));
        });

        ScopeConstructor obs = sc.addConstraints(obsTraces, Guards.NO_GUARDS);
        obs.addTree((TreeBuilderInfo info) -> {
            compilationCtx.addTreeToScope(GlobalScope.scope,
                    IRTree.store(guardName, IRTree.constant(true), "Observation reached"));
        });

        sc = sc.addCondition(IRTree.negateBoolean(IRTree.load(guardName))).ifScopeConstructor;
        sc.addTree((TreeBuilderInfo info) -> {
            t.build();
        });
    }

    private static <A extends Variable<A>> void forwardVariableBody(Variable<A> v, CompilationContext compilationCtx) {
        IRTreeReturn<A> returnBody = v.getParent().getForwardIR(compilationCtx);

        // Typically this will be if the value is from a sample task and is written to an intermediate array.
        // In this case the result will have already been written out to the outer array.
        if(!v.getType().isArray() || !((ArrayVariable<?>) v).isSubArray()) {
            /*
             * This indirection is done to allow for the case where the intermediate variable is allocated inside a for
             * loop. When this happens an array is created to allow each instantiation of the intermediate variable to
             * be saved, and this array needs dereferencing. As this is constructed by the compiler there is no problem
             * with complex indices, or different orders to the indices.
             */
            IRTreeVoid storedResult = TreeUtils.putIndirectValue(v, returnBody, Tree.NoComment, compilationCtx);
            compilationCtx.addTreeToScope(v.getParent().scope(), storedResult);
        }
    }

    private static void constructForwardRVDistribution(DistributableRandomVariable<?, ?> rv,
            CompilationContext compilationCtx) {
        Scope rvScope = rv.scope();

        List<ArrayVariable<DoubleVariable>> probabilitiesArrays = new ArrayList<>();
        for(DataflowTask<?> task:rv.getConsumers()) {
            SampleTask<?, ?> sTask = (SampleTask<?, ?>) task;
            if(sTask.isDistribution())
                probabilitiesArrays.add(((DistributionSampleTask<?, ?>) sTask).getProbabilitiesArray());
        }

        List<VariableDescription<ArrayVariable<DoubleVariable>>> localNames = new ArrayList<>();
        boolean first = true;
        for(ArrayVariable<DoubleVariable> probabilities:probabilitiesArrays) {
            VariableDescription<ArrayVariable<DoubleVariable>> probName = probabilities.getUniqueVarDesc();
            // Get local instance of the variable.
            VariableDescription<ArrayVariable<DoubleVariable>> localName = VariableNames.calcVarName(probName,
                    probName.type);
            List<IRTreeReturn<IntVariable>> indexes = TreeUtils.toArgTrees(TreeUtils.getScopeArgs(probabilities),
                    compilationCtx);
            IRTreeReturn<ArrayVariable<DoubleVariable>> probArray = TreeUtils.getIndirectValue(probName, indexes);
            IRTreeVoid saveLocalProb = initializeVariable(localName, probArray,
                    first ? "Create local copy of variable probabilities." : Tree.NoComment);
            compilationCtx.addTreeToScope(rvScope, saveLocalProb);
            localNames.add(localName);
            first = false;
        }

        if(rv.isDistribution()) {

            // zero values in first local array.
            {
                // Get an index name
                VariableDescription<IntVariable> indexName = VariableNames.indexName(rv.getVarDesc());

                // Get the number of states that this variable could be generating.
                IntVariable noStates = rv.getNumStates();

                ScopeStack.pushScope(rvScope);
                ForTask newScope = Sandwood.forLoop(Variable.intVariable(0), noStates, Variable.intVariable(1), true,
                        (index) -> {
                            // Set alias for better readability, this has no effect on the generated code.
                            index.setAlias(indexName);
                            index.setUniqueVarDesc(indexName);
                        });
                ScopeStack.popScope(rvScope);

                IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(localNames.get(0));
                IRTreeReturn<IntVariable> index = newScope.getIndex().getForwardIR(compilationCtx);
                IRTreeReturn<DoubleVariable> value = constant(0.0);
                IRTreeVoid storeValue = arrayPut(array, index, value, "Zero the probability of each value");
                compilationCtx.addTreeToScope(newScope, storeValue);
            }

            // Calculate the probabilities for the first array.
            Set<Set<TraceHandle>> distributedTraces = Traces.findDistributionTraces(rv, compilationCtx);
            ProducingDataflowTask<?> parent = rv.getParent();

            ScopeConstructor a = ScopeConstructor.construct(parent, distributedTraces,
                    "Iterate through possible values for " + rv.getVarDesc() + "'s arguments.", Tree.NoComment,
                    compilationCtx);
            a = a.applyAllDistributedArguments();
            a = a.addIsolation();
            a.addTree((TreeBuilderInfo info) -> {
                // Get an index name
                VariableDescription<IntVariable> indexName = VariableNames.indexName(rv.getVarDesc());

                // Iterate through each value adding it to the available values
                // Get the number of states that this variable could be generating.
                IntVariable noStates = rv.getNumStates();

                ScopeStack.pushScope(rvScope);
                ForTask newScope = Sandwood.forLoop(Variable.intVariable(0), noStates, Variable.intVariable(1), true,
                        (index) -> {
                            // Set alias for better readability, this has no effect on the generated code.
                            index.setAlias(indexName);
                            index.setUniqueVarDesc(indexName);
                        });
                ScopeStack.popScope(rvScope);

                IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(localNames.get(0));
                IRTreeReturn<IntVariable> index = newScope.getIndex().getForwardIR(compilationCtx);
                IRTreeReturn<DoubleVariable> value = arrayGet(array, index);
                IRTreeReturn<DoubleVariable> prob = multiplyDD(info.probability,
                        getProbability(rv, newScope, compilationCtx));
                value = addDD(value, prob);
                IRTreeVoid storeValue = arrayPut(array, index, value, "Save the probability of each value");
                compilationCtx.addTreeToScope(newScope, storeValue);
            });

            // sum and normalise values
            {
                // Get an index name
                VariableDescription<IntVariable> indexName = VariableNames.indexName(rv.getVarDesc());

                // Init sum value
                // Sum all the values in the array.
                VariableDescription<DoubleVariable> sum = VariableNames.calcVarName(rv.getVarDesc(), "sum",
                        VariableType.DoubleVariable);
                compilationCtx.addTreeToScope(rvScope,
                        initializeVariable(sum, constant(0.0), "Sum the values in the array"));

                // Get the number of states that this variable could be generating.
                IntVariable noStates = rv.getNumStates();
                {
                    ScopeStack.pushScope(rvScope);
                    ForTask newScope = Sandwood.forLoop(Variable.intVariable(0), noStates, Variable.intVariable(1),
                            true, (index) -> {
                                // Set alias for better readability, this has no effect on the generated code.
                                index.setAlias(indexName);
                                index.setUniqueVarDesc(indexName);
                            });
                    ScopeStack.popScope(rvScope);

                    IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(localNames.get(0));
                    IRTreeReturn<IntVariable> index = newScope.getIndex().getForwardIR(compilationCtx);
                    IRTreeReturn<DoubleVariable> value = arrayGet(array, index);
                    IRTreeVoid storeValue = store(sum, addDD(load(sum), value), "sum the probability of each value");
                    compilationCtx.addTreeToScope(newScope, storeValue);
                }

                // Normalise the array and copy the result into any other local arrays.
                {
                    ScopeStack.pushScope(rvScope);
                    ForTask newScope = Sandwood.forLoop(Variable.intVariable(0), noStates, Variable.intVariable(1),
                            true, (index) -> {
                                // Set alias for better readability, this has no effect on the generated code.
                                index.setAlias(indexName);
                                index.setUniqueVarDesc(indexName);
                            });
                    ScopeStack.popScope(rvScope);

                    IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(localNames.get(0));
                    IRTreeReturn<IntVariable> index = newScope.getIndex().getForwardIR(compilationCtx);
                    IRTreeReturn<DoubleVariable> value = divideDD(arrayGet(array, index), load(sum));
                    IRTreeVoid storeValue = arrayPut(array, index, value, "Normalise the probability of each value");
                    compilationCtx.addTreeToScope(newScope, storeValue);

                    first = true;
                    for(int i = 1; i < localNames.size(); i++) {
                        value = arrayGet(array, index);
                        storeValue = arrayPut(load(localNames.get(i)), index, value,
                                first ? "Copy the value into the other sample task arrays" : Tree.NoComment);
                        compilationCtx.addTreeToScope(newScope, storeValue);
                        first = false;
                    }
                }
            }
        } else {
            // Each value will only be set once, so there is no need to zero the values
            // and then add weighted probabilities, instead we will just store the values.

            // Get an index name
            VariableDescription<IntVariable> indexName = VariableNames.indexName(rv.getVarDesc());

            // Get the number of states that this variable could be generating.
            IntVariable noStates = rv.getNumStates();

            ScopeStack.pushScope(rvScope);
            ForTask newScope = Sandwood.forLoop(Variable.intVariable(0), noStates, Variable.intVariable(1), true,
                    (index) -> {
                        // Set alias for better readability, this has no effect on the generated code.
                        index.setAlias(indexName);
                        index.setUniqueVarDesc(indexName);
                    });
            ScopeStack.popScope(rvScope);

            VariableDescription<DoubleVariable> valueName = VariableNames.calcVarName("value",
                    VariableType.DoubleVariable, true);
            IRTreeReturn<DoubleVariable> value = getProbability(rv, newScope, compilationCtx);
            IRTreeVoid valueInit = IRTree.initializeVariable(valueName, value, "Probability for this value");
            compilationCtx.addTreeToScope(newScope, valueInit);

            first = true;
            for(VariableDescription<ArrayVariable<DoubleVariable>> localName:localNames) {
                IRTreeReturn<IntVariable> index = newScope.getIndex().getForwardIR(compilationCtx);
                IRTreeReturn<ArrayVariable<DoubleVariable>> array = load(localName);
                IRTreeVoid storeValue = arrayPut(array, index, load(valueName),
                        first ? "Save the probability of each value" : Tree.NoComment);
                compilationCtx.addTreeToScope(newScope, storeValue);
                first = false;
            }
        }
    }

    /**
     * Method to get all the sample tasks that a variable is dependent on when performing forward execution.
     * 
     * @param v              The variable to get the dependent sample tasks for.
     * @param compilationCtx The compilation context.
     * @return A set containing all the dependent sample tasks.
     */
    private static Set<SampleTask<?, ?>> getSourceSampleTasks(Variable<?> v, CompilationContext compilationCtx) {
        return getSourceSampleTasks(v, new HashSet<>(), compilationCtx.traces);
    }

    /**
     * Method to get all the sample tasks that a variable is dependent on when performing forward execution.
     * 
     * @param v      The variable to get the dependent sample tasks for.
     * @param seen   A set containing all the variables already seen. This will be updated with the variables seen
     *               during the execution of this method.
     * @param traces The traces object to query.
     * @return A set containing all the sample tasks.
     */
    private static Set<SampleTask<?, ?>> getSourceSampleTasks(Variable<?> v, Set<Variable<?>> seen, Traces traces) {
        Set<SampleTask<?, ?>> samples = traces.getSourceSampleTasks(v);
        seen.add(v);
        for(Variable<?> o:traces.getSourceObservedVariables(v)) {
            if(!seen.contains(o))
                samples.addAll(getSourceSampleTasks(o, seen, traces));
        }
        return samples;
    }

    private static IRTreeReturn<DoubleVariable> getProbability(DistributableRandomVariable<?, ?> rv, ForTask newScope,
            CompilationContext compilationCtx) {
        compilationCtx.enterScope(newScope);
        RandomVariableType<?, ?> type = rv.getType();
        IRTreeReturn<IntVariable> indexValue = newScope.getIndex().getForwardIR(compilationCtx);
        IRTreeReturn<DoubleVariable> toReturn;
        if(type == VariableType.Bernoulli) {
            IRTreeReturn<BooleanVariable> value = eq(indexValue, constant(1));
            toReturn = ProbabilityFunction.getSampleProbability(rv, value, false, compilationCtx);
        } else if(type == VariableType.Binomial) {
            toReturn = ProbabilityFunction.getSampleProbability(rv, indexValue, false, compilationCtx);
        } else if(type == VariableType.Categorical) {
            toReturn = ProbabilityFunction.getSampleProbability(rv, indexValue, false, compilationCtx);
        } else
            throw new CompilerException("Unsupported type of random variable.");
        compilationCtx.leaveScope(newScope);
        return toReturn;
    }

    static IRTreeReturn<BooleanVariable> constructGuard(GuardStatus guardStatus, CompilationContext compilationCtx,
            Variable<?>... vs) {
        Set<SampleTask<?, ?>> sampleTasks = new HashSet<>();
        for(Variable<?> v:vs)
            sampleTasks.addAll(getSourceSampleTasks(v, compilationCtx));
        return constructGuard(sampleTasks, guardStatus);
    }

    private static IRTreeReturn<BooleanVariable> constructGuard(Set<SampleTask<?, ?>> sTasks, GuardStatus guardStatus) {
        return switch(guardStatus) {
            case FIXED, FREE -> {
                IRTreeReturn<BooleanVariable> guard = null;

                PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>();
                for(SampleTask<?, ?> s:sTasks) {
                    if(!s.getOutput().isFixed())
                        p.add(s);
                }

                if(!p.isEmpty()) {
                    VariableDescription<BooleanVariable> flagName = VariableNames.fixedFlagName(p.poll());
                    guard = load(flagName);

                    while(!p.isEmpty()) {
                        flagName = VariableNames.fixedFlagName(p.poll());
                        guard = and(guard, load(flagName));
                    }

                    if(guardStatus == GuardStatus.FREE)
                        guard = negateBoolean(guard);
                }
                yield guard;
            }
            case NONE -> null;
        };
    }
}
