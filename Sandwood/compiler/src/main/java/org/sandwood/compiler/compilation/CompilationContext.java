/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import static org.sandwood.compiler.compilation.util.TreeUtils.allocate;
import static org.sandwood.compiler.trees.irTree.IRTree.voidFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.ForwardExecutionBuilder.GuardStatus;
import org.sandwood.compiler.compilation.inferenceRange.InferenceRange;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.compilation.util.TreeUtils.ArrayDesc;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.Variable.Observed;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.FunctionName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.guards.DistSampleDesc;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.WrappedTree;
import org.sandwood.compiler.trees.irTree.IRFunction;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;
import org.sandwood.compiler.trees.irTree.IRVoidFunction;

public class CompilationContext {

    // TODO Ensure that the names and function names match here and are appropriate.
    public enum AuxFunctionType {
        VAR_ALLOCATOR("allocator"),
        SCRATCH_ALLOCATOR("allocateScratch"),
        INITIALIZE("initializeConstants"),
        INITIALIZE_LOG_PROBABILITY_FIELDS("initializeLogProbabilityFields"),
        SET_INTERMEDIATES("setIntermediates"),
        GIBBS_ROUND("gibbsRound"),
        FORWARD_GENERATION("forwardGeneration"),
        FORWARD_GENERATION_VALUES_NO_OUTPUTS("forwardGenerationValuesNoOutputs"),
        FORWARD_GENERATION_DISTRIBUTIONS_NO_OUTPUTS("forwardGenerationDistributionsNoOutputs"),
        LOG_EVIDENCE_GENERATION("logEvidenceGeneration"),
        LOG_EVIDENCE_PROBABILITIES("logEvidenceProbabilities"),
        ALL_LOG_PROBABILITIES("logProbabilityGeneration"),
        ALL_LOG_PROBABILITIES_CALCULATION_VAL("logModelProbabilitiesVal"),
        ALL_LOG_PROBABILITIES_CALCULATION_DIST("logModelProbabilitiesDist"),
        OBSERVATION_PROPAGATION("propagateObservedValues");

        public final FunctionName functionName;

        AuxFunctionType(String functionName) {
            this.functionName = FunctionName.createFunctionName(functionName);
        }
    }

    public enum SampleFunctionClass {
        INFERENCE,
        LOG_PROBABILITY_VALUE,
        LOG_PROBABILITY_DISTRIBUTIONS,
        FIXED_SAMPLE_PROBABILITY
    }

    public enum CompilationPhase {
        MAIN_METHODS,
        INITIALIZATION_OF_CONSTANTS,
        ALLOCATION
    }

    /**
     * Structure to hold all the generated functions.
     */
    private static class Functions {
        final Map<SampleFunctionClass, Map<SampleTask<?, ?>, IRFunction<?>>> functions;
        final Map<AuxFunctionType, IRFunction<?>> auxiliary;
        boolean optimise = true;

        public Functions() {
            functions = new HashMap<>();
            for(SampleFunctionClass c:SampleFunctionClass.values())
                functions.put(c, new HashMap<>());

            auxiliary = new LinkedHashMap<>();
        }

        // function to get all the generated functions
        public Map<FunctionName, IRFunction<?>> getFunctions() {
            Map<FunctionName, IRFunction<?>> toReturn = new HashMap<>();

            for(SampleFunctionClass c:SampleFunctionClass.values()) {
                Map<SampleTask<?, ?>, IRFunction<?>> classFunctions = functions.get(c);
                for(SampleTask<?, ?> s:classFunctions.keySet()) {
                    IRFunction<?> f = classFunctions.get(s);
                    toReturn.put(f.name, f);
                }
            }

            for(IRFunction<?> f:auxiliary.values())
                toReturn.put(f.name, f);

            return toReturn;
        }

        public void add(AuxFunctionType auxType, Visibility visibility, ArgDesc<?>[] args, IRTreeVoid tree,
                boolean override, String comment) {
            IRVoidFunction f = voidFunction(visibility, auxType.functionName, args, tree, override, comment);
            auxiliary.put(auxType, f);
            ScopeConstructor.clearId();
        }

        public void add(SampleFunctionClass c, SampleTask<?, ?> v, IRVoidFunction f) {
            functions.get(c).put(v, f);
            // Reset the id so changes to code do not propagate between functions in the for
            // of different ids.
            ScopeConstructor.clearId();
        }

        public Map<SampleTask<?, ?>, IRFunction<?>> getMap(SampleFunctionClass c) {
            return functions.get(c);
        }
    }

    public enum FieldType {
        INTERNAL(Getter.NONE, Setter.NONE, false, Visibility.PRIVATE, Observed.FREE),
        PRIVATE_PROBABILITY(Getter.NONE, Setter.NONE, false, Visibility.PRIVATE, Observed.FREE),
        PUBLIC_PROBABILITY(Getter.REQUIRED, Setter.NONE, false, Visibility.PUBLIC, Observed.FREE),
        USER_FLAG(Getter.REQUIRED, Setter.REQUIRED, false, Visibility.PUBLIC, Observed.FREE),

        INPUT(Getter.REQUIRED, Setter.REQUIRED, false, Visibility.PUBLIC, Observed.FREE),

        PRIVATE_FREE_INTERMEDIATE(Getter.NONE, Setter.NONE, false, Visibility.PRIVATE, Observed.FREE),
        PRIVATE_FIXED_INTERMEDIATE(Getter.NONE, Setter.NONE, false, Visibility.PRIVATE, Observed.FIXED),
        PRIVATE_OBSERVED_INTERMEDIATE(Getter.NONE, Setter.NONE, false, Visibility.PRIVATE, Observed.OBSERVED),

        PUBLIC_FREE_INTERMEDIATE(Getter.REQUIRED, Setter.NONE, false, Visibility.PUBLIC, Observed.FREE),
        PUBLIC_FIXED_INTERMEDIATE(Getter.REQUIRED, Setter.NONE, false, Visibility.PUBLIC, Observed.FIXED),
        PUBLIC_OBSERVED_INTERMEDIATE(Getter.REQUIRED, Setter.NONE, false, Visibility.PUBLIC, Observed.OBSERVED),

        PRIVATE_FREE_SAMPLE(Getter.REQUIRED, Setter.REQUIRED, true, Visibility.PRIVATE, Observed.FREE),
        PRIVATE_FIXED_SAMPLE(Getter.NONE, Setter.NONE, true, Visibility.PRIVATE, Observed.FIXED),
        PRIVATE_OBSERVED_SAMPLE(Getter.NONE, Setter.NONE, true, Visibility.PRIVATE, Observed.OBSERVED),

        PUBLIC_FREE_SAMPLE(Getter.REQUIRED, Setter.REQUIRED, true, Visibility.PUBLIC, Observed.FREE),
        PUBLIC_FIXED_SAMPLE(Getter.REQUIRED, Setter.NONE, true, Visibility.PUBLIC, Observed.FIXED),
        PUBLIC_OBSERVED_SAMPLE(Getter.REQUIRED, Setter.NONE, true, Visibility.PUBLIC, Observed.OBSERVED);

        private enum Getter {
            NONE,
            REQUIRED
        }

        private enum Setter {
            NONE,
            REQUIRED
        }

        private enum Visibility {
            PUBLIC,
            PRIVATE
        }

        public final boolean isSample;
        public final boolean getter;
        public final boolean setter;
        public final boolean isPrivate;
        public final Observed observed;

        private FieldType(Getter getter, Setter setter, boolean isSample, Visibility isPrivate, Observed observed) {
            this.isSample = isSample;
            this.getter = getter == Getter.REQUIRED;
            this.setter = setter == Setter.REQUIRED;
            this.isPrivate = isPrivate == Visibility.PRIVATE;
            this.observed = observed;
        }

        public static FieldType getFieldType(Variable<?> v) {
            v = v.getCurrentInstance();
            switch(v.getObservationStatus()) {
                case FREE: {
                    if(v.getParent().getType() == DFType.NAMED_VARIABLE)
                        return INPUT;
                    if(v.isPrivate()) {
                        if(setBySampleValue(v))
                            return PRIVATE_FREE_SAMPLE;
                        if(v.isIntermediate())
                            return PRIVATE_FREE_INTERMEDIATE;
                        throw new CompilerException("Unknown field category for variable " + v);
                    } else {
                        if(setBySampleValue(v))
                            return PUBLIC_FREE_SAMPLE;
                        if(v.isIntermediate())
                            return PUBLIC_FREE_INTERMEDIATE;
                        throw new CompilerException("Unknown field category for variable " + v);
                    }
                }
                case FIXED: {
                    if(v.isPrivate()) {
                        if(v.isSample())
                            return PRIVATE_FIXED_SAMPLE;
                        if(v.isIntermediate())
                            return PRIVATE_FIXED_INTERMEDIATE;
                        throw new CompilerException("Unknown field category for variable " + v);
                    } else {
                        if(setBySampleValue(v))
                            return PUBLIC_FIXED_SAMPLE;
                        if(v.isIntermediate())
                            return PUBLIC_FIXED_INTERMEDIATE;
                        throw new CompilerException("Unknown field category for variable " + v);
                    }
                }
                case OBSERVED:
                    if(v.isPrivate()) {
                        if(v.isSample())
                            return PRIVATE_OBSERVED_SAMPLE;
                        if(v.isIntermediate())
                            return PRIVATE_OBSERVED_INTERMEDIATE;
                        throw new CompilerException("Unknown field category for variable " + v);
                    } else {
                        if(setBySampleValue(v))
                            return PUBLIC_OBSERVED_SAMPLE;
                        if(v.isIntermediate())
                            return PUBLIC_OBSERVED_INTERMEDIATE;
                        throw new CompilerException("Unknown field category for variable " + v);
                    }
                default:
                    throw new CompilerException("Unknown field category for variable " + v);
            }
        }

        /**
         * Method to test if this intermediate value has already been set when the sample values were set.
         * 
         * @param v The intermediate variable to test.
         * @return Returns true if the value will have been set by the sample value.
         */
        private static boolean setBySampleValue(Variable<?> v) {
            if(v.isSample())
                return true;

            if(v.getParent().getType() == DFType.PUT) {
                PutTask<?> pt = (PutTask<?>) v.getParent();
                Variable<?> value = pt.value;
                if(value.isSample())
                    return true;
                ArrayVariable<?> array = pt.array;
                while(value.getParent().getType() == DFType.PUT) {
                    pt = (PutTask<?>) value.getParent();
                    if(pt.value.isSample() && !pt.value.isIntermediate())
                        return true;
                    value = pt.value;
                }
                return setBySampleValue(array);
            } else
                return false;
        }
    }

    public static class FieldDesc<A extends Variable<A>> {

        public final VariableDescription<A> varDesc;
        public final String comment;
        public final IRTreeReturn<A> initialValue;
        private final static Map<VariableDescription<?>, Set<WrappedTree<IRTree, IRTreeVoid>>> setSideEffects = new HashMap<>();
        public final FieldType fieldType;

        public FieldDesc(VariableDescription<A> varDesc, FieldType fieldType, IRTreeReturn<A> initialValue,
                String comment) {
            this.varDesc = varDesc;
            this.comment = comment;
            this.fieldType = fieldType;
            this.initialValue = initialValue;
        }

        public Set<WrappedTree<IRTree, IRTreeVoid>> getSetSideEffects() {
            Set<WrappedTree<IRTree, IRTreeVoid>> s = setSideEffects.get(varDesc);
            if(s == null)
                return Collections.emptySet();
            else
                return s;
        }

        public static void addSetSideEffects(VariableDescription<?> varDesc, IRTreeVoid sideEffect) {
            Set<WrappedTree<IRTree, IRTreeVoid>> s = setSideEffects.computeIfAbsent(varDesc,
                    k -> new LinkedHashSet<>());

            s.add(new WrappedTree<>(sideEffect));
        }
    }

    private static class Allocators {
        private final CompilationContext compilationCtx;

        Allocators(CompilationContext compilationCtx) {
            this.compilationCtx = compilationCtx;
        }

        private class VarConstructor implements Comparable<VarConstructor> {
            private final Variable<?> v;
            public final IRTreeVoid tree;

            public VarConstructor(Variable<?> v, IRTreeVoid tree) {
                this.v = v.instanceHandle();
                this.tree = tree;
            }

            @Override
            public int compareTo(VarConstructor o) {
                return v.getId() - o.v.getId();
            }
        }

        private final List<IRTreeVoid> unorderedConstructors = new ArrayList<>();
        private final List<IRTreeVoid> scratchConstructors = new ArrayList<>();
        private final List<VarConstructor> orderedConstructors = new ArrayList<>();

        public void addConstructor(VariableDescription<?> fieldName, Variable<?> v, IRTreeVoid constructor) {
            if(v == null) {
                if(VariableNames.isCalcVar(fieldName) || VariableNames.isGuardVar(fieldName))
                    scratchConstructors.add(IRTree.treeScope(constructor, "Constructor for " + fieldName));
                else
                    unorderedConstructors.add(IRTree.treeScope(constructor, "Constructor for " + fieldName));
            } else
                orderedConstructors
                        .add(new VarConstructor(v, IRTree.treeScope(constructor, "Constructor for " + fieldName)));
        }

        public IRTreeVoid getVarTree(Map<VariableName, FieldDesc<?>> fieldDescs) {
            List<IRTreeVoid> trees = new ArrayList<>();

            PriorityQueue<VarConstructor> p = new PriorityQueue<>();
            p.addAll(orderedConstructors);
            int size = p.size();
            for(int i = 0; i < size; i++) {
                VarConstructor c = p.poll();
                VariableName name = c.v.getUniqueVarDesc().name;
                FieldDesc<?> f = fieldDescs.get(name);
                IRTreeVoid t = c.tree;
                if(f.fieldType.setter) {
                    Variable<?> v = c.v;

                    IRTreeReturn<BooleanVariable> guard = null;

                    if(v.getType().isArray()) {
                        while(v != null) {
                            if(v.isSample()) {
                                // Get the requirements for the array
                                if(guard == null)
                                    guard = ForwardExecutionBuilder.constructGuard(GuardStatus.FREE, compilationCtx, v);
                                else
                                    guard = IRTree.or(guard, ForwardExecutionBuilder.constructGuard(GuardStatus.FREE,
                                            compilationCtx, v));
                            }
                            v = ((ArrayVariable<?>) v).getChildInstance();
                        }
                    } else if(v.isSample())
                        guard = ForwardExecutionBuilder.constructGuard(GuardStatus.FREE, compilationCtx, v);

                    if(guard != null)
                        t = IRTree.ifElse(guard, t, "If " + name + " has not been set already allocate space.");
                }
                trees.add(t);
            }

            trees.addAll(unorderedConstructors);

            if(!scratchConstructors.isEmpty())
                trees.add(
                        IRTree.functionCall(AuxFunctionType.SCRATCH_ALLOCATOR.functionName, "Allocate scratch space"));

            return IRTree.sequential(trees, Tree.NoComment);
        }

        public IRTreeVoid getScratchTree() {
            return IRTree.sequential(scratchConstructors, "Allocate scratch space.");
        }

        public void clear() {
            unorderedConstructors.clear();
            orderedConstructors.clear();
            scratchConstructors.clear();
        }
    }

    private static class Substitutions {
        /**
         * Map to Store variables that should be substituted for other variables.
         */
        private final Map<Variable<?>, Stack<Variable<?>>> substitutes = new HashMap<>();
        /**
         * Map to store required scope substitutions. This is necessary as the newly substituted variables may require a
         * different scope.
         */
        private final Map<Scope, Stack<Scope>> scopeSubstitute = new HashMap<>();

        /**
         * Set of variables we are currently replacing, so should ignore if we see them as we would otherwise be in an
         * infinite recursion.
         */
        private final Set<Variable<?>> variablesToIgnore = new HashSet<>();

        public Substitutions() {}

        /**
         * Method to add a variable to substitute.
         * 
         * @param v The variable that will be replaced with a different variable.
         * @param r The variable that replace the variable v.
         */
        public <A extends Variable<A>> void addSubstitute(Variable<A> v, Variable<A> r) {
            v = v.instanceHandle();
            Stack<Variable<?>> rs = substitutes.computeIfAbsent(v, k -> new Stack<>());
            rs.push(r);
        }

        public void addScopeSubstitute(Scope scope, Scope replacementScope) {
            // Add the scope substitution.
            Stack<Scope> rs = scopeSubstitute.computeIfAbsent(scope, k -> new Stack<>());
            rs.push(replacementScope);
        }

        public void addIgnoreSubstitute(Variable<?> v) {
            variablesToIgnore.add(v.instanceHandle());
        }

        public void removeIgnoreSubstitute(Variable<?> v) {
            variablesToIgnore.remove(v.instanceHandle());
        }

        @SuppressWarnings("unchecked")
        public <A extends Variable<A>> Variable<A> getSubstitute(Variable<A> v) {
            Stack<Variable<?>> s = substitutes.get(v);
            if(s == null || s.isEmpty() || variablesToIgnore.contains(v.instanceHandle()))
                return v;
            else
                return (Variable<A>) s.peek();
        }

        /**
         * Method to check for substitute scopes, and if there is a substitute return it, otherwise return the original
         * scope.
         * 
         * @param scope
         * @return
         */
        public Scope substituteScope(Scope scope) {
            while(scopeSubstitute.containsKey(scope)) {
                Scope newScope = scopeSubstitute.get(scope).peek();
                // Test if the scope is substituted with itself. This allows substitutions to be
                // effectively unset without having to pop and reconstruct the whole stack.
                if(newScope == scope)
                    return scope;
                else
                    scope = newScope;
            }
            return scope;
        }

        /**
         * Method to remove an individual substitution.
         * 
         * @param v
         */
        public void removeSubstitute(Variable<?> v) {
            v = v.instanceHandle();
            Stack<Variable<?>> s = substitutes.get(v);
            if(s == null)
                throw new CompilerException("Removed variable substitution that was not set.");
            s.pop();
            if(s.isEmpty())
                substitutes.remove(v);
        }

        /**
         * Method to remove a single scope substitution.
         * 
         * @param scope
         */
        public void removeScopeSubstitute(Scope scope) {
            Stack<Scope> s = scopeSubstitute.get(scope);
            if(s == null)
                throw new CompilerException("Removed scope substitution that was not set.");
            s.pop();
            if(s.isEmpty())
                scopeSubstitute.remove(scope);
        }

        /**
         * Method to remove all substitutions.
         */
        public void removeAllSubstitutes() {
            if(!substitutes.isEmpty())
                throw new CompilerException("Warning clearing non-empty set of variable substitutions.");
            if(!scopeSubstitute.isEmpty())
                throw new CompilerException("Warning clearing non-empty set of scopes substitutions.");
        }
    }

    private class InferenceRangesMap {
        // A flag to track if inference methods are currently being generated.
        private boolean inInference = false;

        // Map from serial tasks with a flag that is true if they are only
        // serial for inference, and false if they are serial in all cases.
        private final Map<ForTask, Boolean> serialScopes = new HashMap<>();

        public void populate(CompilationContext compilationCtx) {
            for(SampleTask<?, ?> sampleTask:traces.getAllIntermediateSamples())
                InferenceRange.getRanges(sampleTask, compilationCtx);
        }

        public boolean serialScope(ForTask s) {
            Boolean inferenceOnly = serialScopes.get(s);
            // This scope is never serial
            if(inferenceOnly == null)
                return false;
            return inInference || !inferenceOnly;
        }

        public void addSerialScopes(Set<ForTask> scopes, boolean inferenceOnly) {
            for(ForTask f:scopes)
                addSerialScope(f, inferenceOnly);
        }

        public void addSerialScope(ForTask scope, boolean inferenceOnly) {
            if(serialScopes.containsKey(scope)) {
                if(!inferenceOnly)
                    serialScopes.put(scope, inferenceOnly);
            } else
                serialScopes.put(scope, inferenceOnly);
        }

        public void setInInference(boolean inInference) {
            this.inInference = inInference;
        }
    }

    public CompilationPhase phase = CompilationPhase.MAIN_METHODS;

    private ScopeTracking scopes = new ScopeTracking(this);
    private final Stack<ScopeTracking> scopeStack = new Stack<>();

    // Java class field name, class field type.
    private final Map<VariableName, FieldDesc<?>> classFields = new HashMap<>();

    // Store the functions constructed by the compilation process.
    private final Functions functions = new Functions();

    private final Allocators allocators = new Allocators(this);

    private Substitutions substitutions = new Substitutions();
    private final Stack<Substitutions> substitutionsStack = new Stack<>();

    private final InferenceRangesMap inferenceRanges;

    private final Stack<Set<Variable<?>>> initialized = new Stack<>();

    private boolean fullInferenceRequired = true;

    private final Stack<IRTreeReturn<BooleanVariable>> codeGuard = new Stack<>();

    /** Should this piece of code execute in serial */
    private final Stack<Boolean> isSerial = new Stack<>();

    public final ExecutionType target;

    public final Traces traces;

    /** A set to record which sub arrays are required in locations where only some sub arrays are required. */
    private Set<ArrayVariable<?>> requiredArrays = null;

    /**
     * A set to track which array lengths are being calculated so that infinite loops can be trapped without waiting for
     * a stack overflow exception.
     */
    private final Set<ArrayVariable<?>> arrayLengths = new HashSet<>();

    /**
     * A stack to track the distribution samples that have already been explored by outer scope constructors.
     */
    private final Stack<Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>>> exploredDistSamples = new Stack<>();

    public CompilationContext(CompilationOptions options, Traces traces, ExecutionType target) {
        this.traces = traces;
        this.target = target;
        configureTarget(target);
        inferenceRanges = new InferenceRangesMap();
        inferenceRanges.populate(this);
        addDistributionSerialScopes();
        options.apply(this);
        codeGuard.push(null);
        clearAll();
    }

    /**
     * Method to add in the scopes that need to be serial when constructing code to handle distributions. This is
     * required because the calculation for a distribution can span many samples.
     */
    private void addDistributionSerialScopes() {
        for(SampleTask<?, ?> s:traces.getAllSampleTasks()) {
            if(s.randomVariable.distributionSampled()) {
                Scope sampleScope = s.scope();
                Scope rvScope = s.randomVariable.scope();
                while(sampleScope != rvScope) {
                    if(sampleScope.getScopeType() == ScopeType.FOR)
                        addSerialScope((ForTask) sampleScope, false);
                    sampleScope = sampleScope.getEnclosingScope();
                }
            }
        }
    }

    private void configureTarget(ExecutionType target) {
        switch(target) {
            case SingleThreadCPU:
                this.pushIsSerial(true);
                break;
            case MultiThreadCPU:
                this.pushIsSerial(false);
                break;
            case GPU:
                break;
            default:
                throw new CompilerException("Unknown target type " + target);
        }
    }

    /**
     * Method to set if functions should be optimised.
     * 
     * @param optimise
     */
    public void setOptimisation(boolean optimise) {
        functions.optimise = optimise;
    }

    /**
     * Method to get if functions should be optimised.
     */
    public boolean getOptimisation() {
        return functions.optimise;
    }

    private boolean calculateIndividualProbabilities;

    /**
     * Method to test if we should construct probabilities for individual iterations of for loops.
     * 
     * @return
     */
    public boolean calculateIndividualProbabilities() {
        return calculateIndividualProbabilities;
    }

    /**
     * Method to set if we should construct probabilities for individual iterations of for loops.
     * 
     * @param calculateIndividualProbabilities
     */
    public void calculateIndividualProbabilities(boolean calculateIndividualProbabilities) {
        this.calculateIndividualProbabilities = calculateIndividualProbabilities;
    }

    public void initialize() {
        scopes.clear();
        scopeStack.clear();
        substitutions.removeAllSubstitutes();
        initialized.clear();
        initialized.push(new HashSet<>());
        exploredDistSamples.clear();
        exploredDistSamples.push(new HashMap<>());
    }

    /**
     * A method to reset everything.
     */
    private void clearAll() {
        initialize();
        classFields.clear();
        FieldDesc.setSideEffects.clear();
        allocators.clear();
    }

    public <A extends Variable<A>> void addClassInputField(VariableDescription<A> varDesc, String comment) {
        if(!classFields.containsKey(varDesc.name)) {
            classFields.put(varDesc.name, new FieldDesc<>(varDesc, FieldType.INPUT, null, comment));

        } else {
            @SuppressWarnings("unchecked")
            FieldDesc<A> f = (FieldDesc<A>) classFields.get(varDesc.name);
            assert (f.varDesc.type.equals(varDesc.type));
            f = new FieldDesc<>(varDesc, f.fieldType, f.initialValue, f.comment);
            classFields.put(varDesc.name, f);
        }
    }

    public <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> fieldname,
            IRTreeReturn<A> initialValue) {
        addConstructedClassField(fieldname, null, null, FieldType.INTERNAL, initialValue, null);
    }

    public <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> fieldname,
            IRTreeVoid constructor) {
        addConstructedClassField(fieldname, constructor, null, FieldType.INTERNAL, null, null);
    }

    public <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> fieldname,
            IRTreeVoid constructor, IRTreeReturn<A> initialValue) {
        addConstructedClassField(fieldname, constructor, null, FieldType.INTERNAL, initialValue, null);
    }

    public <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> fieldname,
            IRTreeVoid constructor, FieldType fieldType, String comment) {
        addConstructedClassField(fieldname, constructor, null, fieldType, null, comment);
    }

    public <A extends Variable<A>> void addFlagClassField(VariableDescription<A> fieldname,
            IRTreeReturn<A> initialValue) {
        addConstructedClassField(fieldname, null, null, FieldType.USER_FLAG, initialValue, null);
    }

    public <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> fieldname,
            FieldType fieldType) {
        addConstructedClassField(fieldname, null, null, fieldType, null, null);
    }

    public <A extends Variable<A>> void addConstructedClassField(Variable<A> v, CompilationContext compilationCtx) {
        VariableDescription<A> fieldDesc = v.getUniqueVarDesc();
        if(classFields.containsKey(fieldDesc.name)) {
            // If the field has already been declared just pass this through to update date the getter and setter flags
            // if required. They are a collective OR of all the passed flags.
            addConstructedClassField(fieldDesc, null, v);
        } else {
            ArrayDesc<?> arrayDesc = TreeUtils.getArrayDescription(v);
            if(arrayDesc == null) {
                addConstructedClassField(fieldDesc, null, v);
            } else {
                addConstructedClassFieldArray(v, fieldDesc, arrayDesc, compilationCtx);
            }
        }
    }

    private <A extends Variable<A>, B extends Variable<B>> void addConstructedClassFieldArray(Variable<A> v,
            VariableDescription<A> fieldDesc, ArrayDesc<B> arrayDesc, CompilationContext compilationCtx) {
        VariableDescription<ArrayVariable<B>> arrayName = VariableNames.altTypeName(fieldDesc, arrayDesc.type);
        pushIsSerial(true);
        IRTreeVoid allocator = allocate(arrayName, arrayDesc, compilationCtx);
        popIsSerial();
        addConstructedClassField(arrayName, allocator, v);
    }

    private <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> fieldname,
            IRTreeVoid constructor, Variable<?> v) {
        addConstructedClassField(fieldname, constructor, v, FieldType.getFieldType(v), null, v.getComment());
    }

    public <A extends Variable<A>> void addConstructedClassField(VariableDescription<A> varDesc,
            IRTreeVoid constructorTree, Variable<?> v, FieldType fieldType, IRTreeReturn<A> initialValue,
            String comment) {
        // Ensure variables that did not appear in the model, and variables that have
        // been marked private don't have getters and setters constructed.
        if(!classFields.containsKey(varDesc.name)) {
            classFields.put(varDesc.name, new FieldDesc<>(varDesc, fieldType, initialValue, comment));
            if(constructorTree != null)
                allocators.addConstructor(varDesc, v, constructorTree);
        } else {
            @SuppressWarnings("unchecked")
            FieldDesc<A> f = (FieldDesc<A>) classFields.get(varDesc.name);
            if(initialValue == null)
                initialValue = f.initialValue;
            else
                assert f.initialValue == null || (f.initialValue.equivalent(initialValue));
            // It is important that f.varDesc is used here as only the first variable description is checked to see if
            // it need to be converted to an array type.
            assert fieldType == f.fieldType;
            f = new FieldDesc<>(f.varDesc, fieldType, initialValue, f.comment);
            classFields.put(varDesc.name, f);
        }
    }

    public void addSetSideEffect(VariableDescription<?> fieldName, IRTreeVoid sideEffect) {
        FieldDesc.addSetSideEffects(fieldName, sideEffect);
    }

    public Map<VariableName, FieldDesc<?>> getClassFields() {
        return classFields;
    }

    /**
     * Methods for checking which variables have had a local value for them declared.
     *
     * @param v The array to test.
     * @return
     */
    public boolean initialized(Variable<?> v) {
        return initialized.peek().contains(v.instanceHandle());
    }

    public void addInitialized(Variable<?> v) {
        initialized.peek().add(v.instanceHandle());
    }

    public void pushInitializedArrays() {
        initialized.push(new HashSet<>());
    }

    public void popInitializedArrays() {
        initialized.pop();
    }

    /**
     * Method for adding trees to the scope described by the dataflow task.
     *
     * @param scope
     * @param tree
     */
    public void addTreeToScope(Scope scope, IRTreeVoid tree) {
        if(codeGuard.peek() != null)
            tree = restructure(tree);
        scopes.addTreeToScope(scope, tree);
    }

    /**
     * Method for adding a comment to a scope
     * 
     * @param scope
     * @param comment
     */
    public void addCommentToScope(Scope scope, String comment) {
        scopes.addCommentToScope(scope, comment);
    }

    /**
     * Method for adding trees to the scope described by the dataflow task.
     *
     * @param task
     * @param tree
     */

    public void addTreeToScope(Scope scope, IRTreeVoid tree, DataflowTask<?> task) {
        if(codeGuard.peek() != null)
            tree = restructure(tree);
        scopes.addTreeToScope(scope, tree, task);
    }

    private IRTreeVoid restructure(IRTreeVoid tree) {
        switch(tree.type) {
            case INITIALIZE:
                /*
                 * Assertion removed because methods don't have side effects, so it is not needed. IRInitialize<?> i =
                 * (IRInitialize<?>) tree; assert(i.value.type == IRTreeType.CONST_BOOLEAN || i.value.type ==
                 * IRTreeType.CONST_DOUBLE || i.value.type == IRTreeType.CONST_INT || i.value.type ==
                 * IRTreeType.ARRAY_GET);
                 */
                return tree;
            case INITIALIZE_UNSET:
                return tree;
            default:
                return IRTree.ifElse(codeGuard.peek(), tree, Tree.NoComment);

        }
    }

    public IRTreeVoid getOutermostScopeTree() {
        return scopes.getOutermostScopeTree();
    }

    public void enterScope(Scope s) {
        scopes.enterScope(s);
    }

    public void leaveScope(Scope s) {
        scopes.leaveScope(substituteScope(s));
    }

    public void setreverseScopes(boolean reverseScopes) {
        scopes.setreverseScopes(reverseScopes);
    }

    /**
     * A method to touch a scope to ensure it is built in the tracking. This is used for scopes that would not be
     * touched because all the uses of their variable are through synthetic named variables, or to ensure they are
     * placed before any substitutions are installed.
     * 
     * @param s The scope to touch.
     */
    public void touchScope(Scope s) {
        scopes.touchScope(s);
    }

    public void pushScope() {
        scopeStack.push(scopes);
        scopes = new ScopeTracking(this);
        codeGuard.push(null);
    }

    public void popScope() {
        scopes = scopeStack.pop();
        codeGuard.pop();
    }

    public IRTreeVoid getVarAllocator() {
        return allocators.getVarTree(classFields);
    }

    public IRTreeVoid getScratchAllocator() {
        return allocators.getScratchTree();
    }

    public Map<FunctionName, IRFunction<?>> getFunctions() {
        return functions.getFunctions();
    }

    public void addFunction(AuxFunctionType auxType, Visibility visibility, ArgDesc<?>[] args, IRTreeVoid tree,
            boolean override, String comment) {
        functions.add(auxType, visibility, args, tree, override, comment);
    }

    public void addFunction(SampleFunctionClass c, SampleTask<?, ?> s, IRVoidFunction f) {
        functions.add(c, s, f);
    }

    public Map<SampleTask<?, ?>, IRFunction<?>> getFunctionMap(SampleFunctionClass c) {
        return functions.getMap(c);
    }

    /*
     * Start of substitution methods.
     */

    public <A extends Variable<A>> void addSubstitute(Variable<A> v, Variable<A> r) {
        substitutions.addSubstitute(v, r);
    }

    public void addIgnoreSubstitute(Variable<?> v) {
        substitutions.addIgnoreSubstitute(v);
    }

    public void removeIgnoreSubstitute(Variable<?> v) {
        substitutions.removeIgnoreSubstitute(v);
    }

    public <A extends Variable<A>> Variable<A> getSubstitute(Variable<A> v) {
        return substitutions.getSubstitute(v);
    }

    public void addScopeSubstitute(Scope source, Scope replacement) {
        substitutions.addScopeSubstitute(source, replacement);
    }

    public void removeScopeSubstitute(Scope source) {
        substitutions.removeScopeSubstitute(source);
    }

    public Scope substituteScope(Scope scope) {
        return substitutions.substituteScope(scope);
    }

    public void removeSubstitute(Variable<?> v) {
        substitutions.removeSubstitute(v);
    }

    public void pushSubstitutions() {
        substitutionsStack.push(substitutions);
        substitutions = new Substitutions();
    }

    public void popSubstitutions() {
        substitutions = substitutionsStack.pop();
    }

    /*
     * Start of inference ranges methods
     */

    public boolean serialScope(ForTask s) {
        return inferenceRanges.serialScope(s);
    }

    public void addSerialScopes(Set<ForTask> scopes, boolean inferenceOnly) {
        inferenceRanges.addSerialScopes(scopes, inferenceOnly);
    }

    public void addSerialScope(ForTask scope, boolean inferenceOnly) {
        inferenceRanges.addSerialScope(scope, inferenceOnly);
    }

    public void setInInference(boolean inInference) {
        inferenceRanges.setInInference(inInference);
    }

    public boolean inInference() {
        return inferenceRanges.inInference;
    }

    public boolean fullInferenceRequired() {
        return fullInferenceRequired;
    }

    public void fullInferenceRequired(boolean fullInferenceRequired) {
        this.fullInferenceRequired = fullInferenceRequired;
    }

    public void setCodeGuard(IRTreeReturn<BooleanVariable> guard) {
        IRTreeReturn<BooleanVariable> current = codeGuard.peek();
        if(current != null)
            guard = IRTree.and(guard, current);
        codeGuard.push(guard);
    }

    public void clearCodeGuard() {
        codeGuard.pop();
    }

    public boolean codeGuardSet() {
        return codeGuard.peek() != null;
    }

    /**
     * Check if this section of code should execute in serial.
     * 
     * @return Should this section of code execute in serial.
     */
    public boolean isSerial() {
        return isSerial.peek();
    }

    /**
     * Set if this section of code should execute in serial
     * 
     * @param isSerial Should this piece of code be serial.
     */
    public void pushIsSerial(boolean isSerial) {
        this.isSerial.push(isSerial);
    }

    /**
     * Undo last setting of should this section of code should execute in serial
     */
    public void popIsSerial() {
        isSerial.pop();
    }

    /**
     * Method to set the required arrays when updating intermediates
     * 
     * @param requiredArrays The set of required arrays.
     */
    public void setRequiredArrays(Set<ArrayVariable<?>> requiredArrays) {
        this.requiredArrays = requiredArrays;
    }

    /**
     * Method to clear the set of required arrays. Once this set is cleared all arrays will be required.
     */
    public void clearRequiredArrays() {
        requiredArrays = null;
    }

    /**
     * Method to check if this array is currently required to be constructed.
     * 
     * @param array The array to check.
     * @return Is the array required to be constructed.
     */
    public boolean requiredArray(ArrayVariable<?> array) {
        if(requiredArrays == null)
            return true;
        else
            return requiredArrays.contains(array);
    }

    public void addLengthArray(ArrayVariable<?> a) {
        if(arrayLengths.contains(a))
            throw new CompilerException("Entered an infinite loop calculating the length of array "
                    + (a.aliasSet() ? a.getAlias() : a.getUniqueVarDesc()) + " at " + a.getLocation()
                    + ". This is either an error in the model that should have been detected earlier, or an error in the compiler.");
        arrayLengths.add(a);
    }

    public void removeLengthArray(ArrayVariable<?> a) {
        arrayLengths.remove(a);
    }

    public void pushExploredDistSamples(Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> m) {
        exploredDistSamples.push(m);
    }

    public Map<DistributionSampleTask<?, ?>, List<DistSampleDesc<?>>> peekExploredDistSamples() {
        return exploredDistSamples.peek();
    }

    public void popExploredDistSamples() {
        exploredDistSamples.pop();
    }
}
