/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation;

import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.functionCall;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.sandwood.common.exceptions.SandwoodException;
import org.sandwood.common.execution.ExecutionType;
import org.sandwood.compiler.CompilationOptions;
import org.sandwood.compiler.compilation.CompilationContext.AuxFunctionType;
import org.sandwood.compiler.compilation.CompilationContext.CompilationPhase;
import org.sandwood.compiler.compilation.CompilationContext.SampleFunctionClass;
import org.sandwood.compiler.compilation.ForwardExecutionBuilder.GuardStatus;
import org.sandwood.compiler.compilation.inference.InferenceGenerator;
import org.sandwood.compiler.compilation.inference.InferenceGeneratorLookup;
import org.sandwood.compiler.compilation.inference.marginalization.MarginalizationFunctions;
import org.sandwood.compiler.compilation.inference.metropolisHastings.MetropolisHastingsDirichletFunctions;
import org.sandwood.compiler.compilation.inference.metropolisHastings.MetropolisHastingsDoubleFunctions;
import org.sandwood.compiler.compilation.inference.metropolisHastings.MetropolisHastingsIntFunctions;
import org.sandwood.compiler.compilation.inference.metropolisHastings.MetropolisHastingsMultinomialFunctions;
import org.sandwood.compiler.compilation.util.CompilationDesc;
import org.sandwood.compiler.compilation.util.TreeUtils;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.IRFunction;
import org.sandwood.compiler.trees.irTree.IRFunctionCall;
import org.sandwood.compiler.trees.irTree.IRSandwoodClassGenerated;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;
import org.sandwood.compiler.trees.irTree.IRVoidFunction;
import org.sandwood.compiler.trees.irTree.transformations.ReverseTreeTransformation;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClassGenerated;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodClassWrapper;
import org.sandwood.compiler.trees.outputTree.OutputSandwoodInterfaceGenerated;
import org.sandwood.compiler.trees.transformationTree.TransSandwoodClassGenerated;

public class APICompile {
    // A flag used to make the compiler run in serial mode. THis is used to make it easier to debug errors in the
    // compiler and should always be true in production systems.
    public static final boolean parallel = true;

    /**
     * Method to compile a DAG into the corresponding Java files.
     *
     * @param vs The variables in the DAG we want to compile.
     */

    public static CompilationDesc compile(CompilationOptions compilationOptions, Variable<?>[] vs, String modelName,
            String packageName, VariableName[] constructorArgs, String modelCode, String comment) {
        return compile(compilationOptions, vs, modelName, Collections.emptySet(), packageName, constructorArgs,
                modelCode, comment);
    }

    public static CompilationDesc compile(CompilationOptions compilationOptions, Variable<?>[] vs, String modelName,
            Set<String> helperClasses, String packageName, VariableName[] constructorArgs, String modelCode,
            String comment) {

        // Collect the classes constructing the other simpler classes in the main
        // thread.
        CompilationDesc compDesc = new CompilationDesc();

        if(vs.length == 0)
            throw new SandwoodException("This model contains no variables.");

        try {
            PackageName targetPackageName = new PackageName(packageName);
            ModelClassName className = ModelClassName.modelName(modelName, helperClasses);
            ClassName modelInterface = className.interfaceName();

            // Start the compilation of the model.
            Traces traces = TracesImplementation.getTraces(compDesc, vs);

            // Test the graph for errors.
            for(DataflowTask<?> d:traces.getAllTasks())
                d.testTask(compDesc.errors);

            // Test the required inversions for errors.
            inversionCheck(traces, compDesc);

            // Test for observed values that are dependent on more than one sample task
            sharedObservationCheck(traces, compDesc);

            // Test if any observed variables are also read by random variables.
            readObservedCheck(traces, compDesc);

            if(!compDesc.errors.isEmpty()) {
                return compDesc;
            }
            // If error free continue with the compilation
            CompilationContext compilationCtx = null; // TODO move this into the for loop and protect against cases with
            // no execution targets.
            ClassName[] interfaces = { modelInterface };

            Map<ExecutionType, IRSandwoodClassGenerated> irClasses = new LinkedHashMap<>();

            for(ExecutionType target:ExecutionType.supportedTypes) {
                compilationCtx = new CompilationContext(compilationOptions, traces, target);

                declareClassFields(compilationCtx);

                inferenceFunctions(compDesc, compilationCtx);
                if(!compDesc.successful())
                    return compDesc;
                ProbabilityFunction.probabilityFunctions(compilationCtx);

                Set<Variable<?>> forwardVariables = forwardVariables(compilationCtx);
                forwardPasses(forwardVariables, compilationCtx);
                gibbsRound(compilationCtx);
                evidenceProbabilities(compilationCtx);
                evidencePass(compilationCtx);
                allProbabilities(forwardVariables, compilationCtx);

                constructInitialisation(compilationCtx);
                constructAllocators(compilationCtx);

                constructSetIntermediates(forwardVariables, compilationCtx);
                ObservedValuePropagationBuilder.constructPropagateObservedValues(compilationCtx);

                IRSandwoodClassGenerated irCls = new IRSandwoodClassGenerated(className.backendName(target),
                        targetPackageName, ClassName.coreBase(target), interfaces, compilationCtx.getClassFields(),
                        compilationCtx.getFunctions(), modelCode);
                irClasses.put(target, irCls);
            }

            // Convert constructed classes to output classes in parallel.
            ForkJoinPool commonPool = ForkJoinPool.commonPool();
            List<RecursiveTask<OutputSandwoodClassGenerated>> tasks = new ArrayList<>();
            boolean optimise = compilationCtx.getOptimisation();
            for(ExecutionType e:irClasses.keySet()) {
                RecursiveTask<OutputSandwoodClassGenerated> task = new RecursiveTask<>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected OutputSandwoodClassGenerated compute() {
                        TransSandwoodClassGenerated transCls = irClasses.get(e).toTransformationTree();
                        if(optimise)
                            transCls = transCls.applyOptimisations();

                        return transCls.toOutputTree(e);
                    }
                };
                tasks.add(task);
                if(parallel)
                    commonPool.execute(task);
                else
                    task.invoke();
            }

            compDesc.classes.add(new OutputSandwoodClassWrapper(className, targetPackageName, constructorArgs,
                    compilationCtx.getClassFields(), traces, compDesc, comment, ExecutionType.supportedTypes));

            OutputSandwoodClassGenerated outputCls;
            Iterator<RecursiveTask<OutputSandwoodClassGenerated>> i = tasks.iterator();
            RecursiveTask<OutputSandwoodClassGenerated> task = i.next();
            // commonPool.execute(task);
            outputCls = task.join();

            compDesc.classes.add(outputCls);
            compDesc.classes.add(new OutputSandwoodInterfaceGenerated(outputCls, modelInterface, targetPackageName,
                    ClassName.coreBase()));

            while(i.hasNext()) {
                task = i.next();
                // commonPool.execute(task);
                compDesc.classes.add(task.join());
            }

        } catch(SandwoodModelException e) {
            // Remove the thread local variables from this compilation.
            ScopeStack.remove();
            ScopeConstructor.clearId();

            compDesc.errors.add(e);
            compDesc.classes.clear();
        }

        // Remove the thread local variables from this compilation.
        ScopeStack.remove();

        return compDesc;
    }

    private static void readObservedCheck(Traces traces, CompilationDesc compDesc) {
        for(Variable<?> v:traces.getReadObservedVariables()) {
            String message = "Variable " + v.getAlias() + " is fixed by an observation and used to construct "
                    + "arguments to random variables. Because of the observation, the value of this variable "
                    + "will not be inferred, so all read locations must be set via the observation when performing "
                    + "inference.";
            compDesc.warnings.add(new SandwoodModelException(message, v));
        }
    }

    // TODO make sure only currentInstances are used when allocating fields, otherwise the same fields will be allocated
    // again and again.
    private static void declareClassFields(CompilationContext compilationCtx) {
        // Construct the distribution fields required for sampled distributions probabilities.
        for(DistributionSampleTask<?, ?> rv:compilationCtx.traces.getDistributionSampleTasks()) {
            ScopeStack.pushScope(rv.scope());

            // This is ok as the number of states must be a constant for a distributed sample.
            IntVariable noStates = rv.randomVariable.getNumStates();
            ArrayVariable<DoubleVariable> v = Variable.arrayVariable(VariableType.DoubleVariable, noStates);

            VariableDescription<ArrayVariable<DoubleVariable>> distributionName = VariableNames.distribution(rv);
            v.setUniqueVarDesc(distributionName);
            v.setSample();
            v.setPrivate();

            ScopeStack.popScope(rv.scope());

            rv.addProbabilityDistribution(v);
            compilationCtx.addConstructedClassField(v, compilationCtx);
        }

        // Intermediates and sample variables
        for(Variable<?> v:compilationCtx.traces.getAllVariables()) {
            boolean isSubArray = v.getType().isArray() && ((ArrayVariable<?>) v).isSubArray();
            boolean isModelInput = v.getParent().getType() == DFType.CONSTRUCT_INPUT;
            if((v.isIntermediate() || (v.isSample() && !isSubArray)) && !isModelInput) {
                compilationCtx.addConstructedClassField(v, compilationCtx);
            }
        }

        // Fixed sample flags
        for(SampleTask<?, ?> s:compilationCtx.traces.getAllSampleTasks()) {
            if(!s.getOutput().isFixed()) {
                VariableDescription<BooleanVariable> flagName = VariableNames.fixedFlagName(s);
                compilationCtx.addFlagClassField(flagName, constant(false));
            }
        }

        // Inputs
        for(Variable<?> v:compilationCtx.traces.modelInputs())
            compilationCtx.addClassInputField(v.getUniqueVarDesc(), v.getComment());

        for(Variable<?> v:compilationCtx.traces.observedOnlyInputs())
            compilationCtx.addClassInputField(v.getUniqueVarDesc(), v.getComment());

        for(Variable<?> v:compilationCtx.traces.observedShapeableValues()) {
            compilationCtx.addClassInputField(v.getUniqueVarDesc(), v.getComment());
            Variable<?> shape = compilationCtx.traces.observedShapeVariable(v);
            compilationCtx.addClassInputField(shape.getUniqueVarDesc(), shape.getComment());
        }
    }

    private static void inversionCheck(Traces traces, CompilationDesc compDesc) {
        Set<SandwoodModelException> errors = new HashSet<>();
        for(SampleTask<?, ?> s:traces.getAllIntermediateSamples()) {
            for(RandomVariable<?, ?> consumingRV:traces.getTracesRVToSampleTask(s).keySet()) {
                // Confirm that all traces from observed variables to the generating samples are invertable.
                for(TraceHandle t:traces.getObservedTraces(consumingRV)) {
                    Map<ProducingDataflowTask<?>, String> tErrors = t.inversionErrors();
                    // Remove the sample task as we know it is present and cannot be inverted.
                    tErrors.remove(t.get(0).task);
                    for(ProducingDataflowTask<?> task:tErrors.keySet())
                        errors.add(new SandwoodModelException(tErrors.get(task), task));
                }

                // Check that the traces from sample values to sample tasks are invertible for all samples whose random
                // variables consume other sampled values
                for(TraceHandle t:traces.getSampleVariableTraces(consumingRV)) {
                    Map<ProducingDataflowTask<?>, String> tErrors = t.inversionErrors();
                    // Remove the sample task as we know it is present and cannot be inverted.
                    tErrors.remove(t.get(0).task);
                    for(ProducingDataflowTask<?> task:tErrors.keySet())
                        errors.add(new SandwoodModelException(tErrors.get(task), task));
                }
            }
        }
        compDesc.errors.addAll(errors);
    }

    private static void sharedObservationCheck(Traces traces, CompilationDesc compDesc) {
        Set<SandwoodModelException> errors = new HashSet<>();

        PriorityQueue<SampleTask<?, ?>> p1 = new PriorityQueue<>(traces.getObservedSampleTasks());
        PriorityQueue<SampleTask<?, ?>> p2 = new PriorityQueue<>();
        while(!p1.isEmpty()) {
            SampleTask<?, ?> s1 = p1.poll();
            Map<Variable<?>, Set<TraceHandle>> m1 = traces.getAllObservedTraces(s1);
            // Examine all possible later sample tasks
            p2.addAll(p1);
            while(!p2.isEmpty()) {
                SampleTask<?, ?> s2 = p2.poll();
                Map<Variable<?>, Set<TraceHandle>> m2 = traces.getAllObservedTraces(s2);
                for(Variable<?> v:m1.keySet()) {
                    // If 2 sets of traces from different sample tasks lead to the same observed variable see if they
                    // are dependent
                    if(m2.containsKey(v)) {
                        // Get traces that do not go via a conditional
                        Set<TraceHandle> h1 = new HashSet<>(m1.get(v));
                        Set<TraceHandle> h2 = new HashSet<>(m2.get(v));
                        boolean linkFound = false;
                        for(TraceHandle t1:h1) {
                            if(!linkFound && t1.invertable()) {
                                for(TraceHandle t2:h2) {
                                    if(t2.invertable() && Traces.observedDependentTraces(t1, t2)) {
                                        // There is an error
                                        String name;
                                        // Calculate the named variable
                                        if(v.aliasSet())
                                            name = v.getAlias();
                                        else {
                                            int i = t2.size() - 1;
                                            DataflowTaskArgDesc d = t2.get(i);
                                            while(d.task.getType() == DFType.GET && (d.argPos == 0 || d.argPos == 1))
                                                d = t2.get(--i);
                                            name = d.task.getOutput().getAlias();
                                        }

                                        // Construct the error
                                        errors.add(new SandwoodModelException("Sample task " + s1.id()
                                                + " and sample task " + s2.id()
                                                + " may be combining to generate a single value of observed variable "
                                                + name, s1.getLocation()));
                                        linkFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        compDesc.errors.addAll(errors);
    }

    private static void constructSetIntermediates(Set<Variable<?>> vars, CompilationContext compilationCtx) {
        compilationCtx.phase = CompilationPhase.MAIN_METHODS;
        compilationCtx.initialize();

        Map<Variable<?>, Set<TraceHandle>> forwardVariables = getObservedVariableForwardableInputs(vars,
                compilationCtx);
        Set<Variable<?>> toRemove = new HashSet<>();
        for(Variable<?> v:forwardVariables.keySet()) {
            if(v.isSample())
                toRemove.add(v);
        }
        for(Variable<?> v:toRemove)
            forwardVariables.remove(v);

        IRTreeVoid forwardBody = forwardBody(forwardVariables, GuardStatus.FIXED, false, compilationCtx);
        compilationCtx.addFunction(AuxFunctionType.SET_INTERMEDIATES, Visibility.PUBLIC, new ArgDesc[0], forwardBody,
                true,
                "A method to set array values that depend on the output of a sample task, but are not directly set by "
                        + "the sample task. This method is called to propagate set values through the model. Any non-fixed sample "
                        + "values may be sampled to random variables as part of this process.");
    }

    private static void constructAllocators(CompilationContext compilationCtx) {
        compilationCtx.addFunction(AuxFunctionType.VAR_ALLOCATOR, Visibility.PUBLIC, new ArgDesc[0],
                compilationCtx.getVarAllocator(), true, "Method to allocate space for model inputs and outputs.");
        compilationCtx.addFunction(AuxFunctionType.SCRATCH_ALLOCATOR, Visibility.PUBLIC, new ArgDesc[0],
                compilationCtx.getScratchAllocator(), true,
                "Method to allocate space temporary variables used by "
                        + "the inference methods. Allocating here prevents repeated allocation and deallocation, and makes the code "
                        + "more amenable to GPU execution.");
    }

    private static void inferenceFunctions(CompilationDesc compDesc, CompilationContext compilationCtx) {
        compilationCtx.phase = CompilationContext.CompilationPhase.MAIN_METHODS;

        // Inference functions
        Map<SampleTask<?, ?>, Map<RandomVariable<?, ?>, boolean[]>> sampleSignatures = constructSampleSignatures(
                compilationCtx);

        // This will also provide a space to encode that this cannot be done with
        // conjugates.
        PriorityQueue<SampleTask<?, ?>> sampleQueue = new PriorityQueue<>(
                compilationCtx.traces.getAllIntermediateSamples());
        while(!sampleQueue.isEmpty()) {
            SampleTask<?, ?> s = sampleQueue.poll();
            processSample(s, sampleSignatures.get(s), compDesc, compilationCtx);
        }
    }

    private static Map<SampleTask<?, ?>, Map<RandomVariable<?, ?>, boolean[]>> constructSampleSignatures(
            CompilationContext compilationCtx) {
        // Mapping of a sampling task to the type of the variable that consumed its
        // output and
        // the position of the arguments in the consumers constructor.
        Map<SampleTask<?, ?>, Map<RandomVariable<?, ?>, boolean[]>> sampleSignatures = new HashMap<>();

        // For each possible consumer
        PriorityQueue<RandomVariable<?, ?>> randomQueue = new PriorityQueue<>(
                compilationCtx.traces.getAllRandomVariables());
        while(!randomQueue.isEmpty())
            recordRandomVariable(randomQueue.poll(), sampleSignatures, compilationCtx);
        return sampleSignatures;
    }

    private static <A extends ScalarVariable<A>, B extends RandomVariable<A, B>> void processSample(SampleTask<?, ?> s,
            Map<RandomVariable<?, ?>, boolean[]> signature, CompilationDesc compDesc,
            CompilationContext compilationCtx) {
        @SuppressWarnings("unchecked")
        SampleTask<A, B> sample = (SampleTask<A, B>) s;
        // A list for the canAcceptTraces functions to add suggestions to for improving the model.
        List<String> suggestions = new ArrayList<>();
        // Construct the back propagation functions
        // Extract the single entry and put it as a pair. TODO Add handling for when
        // this is not a single value.
        InferenceGenerator generator = (signature == null) ? null
                : InferenceGeneratorLookup.lookupConjugate(sample, signature, suggestions, compilationCtx);
        if(generator != null) {
            IRVoidFunction f = generator.constructFunction(sample, compilationCtx);
            compilationCtx.addFunction(SampleFunctionClass.INFERENCE, s, f);
            // TODO Add a method for generating the probability for a single pairing that
            // doesn't appear in our set of generators.
        } else {
            // If there are only a finite number possibilities we can iterate through all of
            // them generating probabilities, and then pick one.
            if(!s.randomVariable.isInfinite()) {
                // Get the variable we are going to create for.
                MarginalizationFunctions e = new MarginalizationFunctions();
                IRVoidFunction f = e.constructFunction(sample, compilationCtx);
                compilationCtx.addFunction(SampleFunctionClass.INFERENCE, s, f);
            } else {
                if(s.getOutput().getType() == VariableType.DoubleVariable)
                    generator = new MetropolisHastingsDoubleFunctions();
                else if(s.getOutput().getType() == VariableType.IntVariable)
                    generator = new MetropolisHastingsIntFunctions();
                else if(s.randomVariable.getType() == VariableType.Dirichlet)
                    generator = new MetropolisHastingsDirichletFunctions();
                else if(s.randomVariable.getType() == VariableType.Multinomial)
                    generator = new MetropolisHastingsMultinomialFunctions();

                if(generator != null && generator.canAcceptTraces(s, suggestions, compilationCtx)) {
                    Map<ProducingDataflowTask<?>, Set<TraceHandle>> conditionalTraces = compilationCtx.traces
                            .getTracesToConditionals(s);
                    String warningMessage = matropolisHastingsWarning(s, signature, suggestions, conditionalTraces);
                    compDesc.warnings.add(new SandwoodModelException(warningMessage, s));
                    IRVoidFunction f = generator.constructFunction(s, compilationCtx);
                    compilationCtx.addFunction(SampleFunctionClass.INFERENCE, s, f);
                } else {
                    if(compilationCtx.fullInferenceRequired()) {
                        String errorMessage = constructPairingFailureMessage(s, signature);
                        compDesc.errors.add(new SandwoodModelException(errorMessage, s));
                    } else {
                        String warningMessage = constructPairingFailureMessage(s, signature);
                        compDesc.warnings.add(new SandwoodModelException(warningMessage, s));
                    }
                    return;
                }
            }
        }
    }

    private static String matropolisHastingsWarning(SampleTask<?, ?> s, Map<RandomVariable<?, ?>, boolean[]> signature,
            List<String> suggestions, Map<ProducingDataflowTask<?>, Set<TraceHandle>> conditionalTraces) {
        StringBuffer sb = new StringBuffer();
        if(signature != null) {
            sb.append("Unable to generate inference function based on a conjugate prior for\n"
                    + s.randomVariable.getType() + " => {");
            boolean first = true;

            Set<RandomVariableType<?, ?>> types = new HashSet<>();
            for(RandomVariable<?, ?> rv:signature.keySet())
                types.add(rv.getType());

            for(RandomVariableType<?, ?> t:types) {
                if(first) {
                    sb.append(" " + t);
                    first = false;
                } else
                    sb.append(", " + t);
            }
            sb.append(" }\n");
        }

        if(!conditionalTraces.isEmpty()) {
            if(signature != null)
                sb.append("Also unable");
            else
                sb.append("Unable");

            sb.append(" to generate inference function based on a conjugate prior for\n" + s.randomVariable.getType()
                    + " because the result is used by a conditional guard.\n");
        }
        sb.append("Falling back on Metropolis–Hastings. This may make convergence slow.");
        for(String suggestion:suggestions)
            sb.append(suggestion + "\n");
        return sb.toString();
    }

    private static String constructPairingFailureMessage(SampleTask<?, ?> s,
            Map<RandomVariable<?, ?>, boolean[]> signature) {
        StringBuffer sb = new StringBuffer();
        sb.append("Unable to generate inference techniques for all the pairings.\nFrom "
                + s.randomVariable.getType().getAPIType());
        if(signature != null) {
            sb.append(" to ");
            PriorityQueue<RandomVariable<?, ?>> rvs = new PriorityQueue<>(signature.keySet());

            if(!rvs.isEmpty())
                getTargetRVDesc(rvs.poll(), signature, sb);

            while(rvs.size() > 1) {
                sb.append(", ");
                getTargetRVDesc(rvs.poll(), signature, sb);
            }

            if(!rvs.isEmpty()) {
                sb.append(", and ");
                getTargetRVDesc(rvs.poll(), signature, sb);
            }
        }
        sb.append(".");
        return sb.toString();
    }

    private static void getTargetRVDesc(RandomVariable<?, ?> rv, Map<RandomVariable<?, ?>, boolean[]> signature,
            StringBuffer sb) {
        boolean[] args = signature.get(rv);
        int argCount = 0;
        for(boolean b:args)
            if(b)
                argCount++;
        sb.append(rv.getType().getAPIType() + (argCount > 1 ? " arguments: " : " argument: "));
        boolean firstArg = true;
        for(int i = 0; i < args.length; i++) {
            if(args[i]) {
                if(firstArg)
                    firstArg = false;
                else
                    sb.append(",");
                sb.append(i + 1);
            }
        }
    }

    private static void gibbsRound(CompilationContext compilationCtx) {
        compilationCtx.initialize();
        // As this method calls the inference functions, it is treated as an inference function from the point of view
        // of which scopes need to be serialised.
        compilationCtx.setInInference(true);
        VariableDescription<BooleanVariable> flagName = new VariableDescription<>(
                VariableNames.internalSystemName("gibbsForward"), VariableType.BooleanVariable);
        compilationCtx.addConstructedClassField(flagName, constant(true));
        Map<SampleTask<?, ?>, IRFunction<?>> inferenceFunctions = compilationCtx
                .getFunctionMap(SampleFunctionClass.INFERENCE);

        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>(inferenceFunctions.keySet());
        while(!p.isEmpty()) {
            SampleTask<?, ?> s = p.poll();
            IRFunction<?> f = inferenceFunctions.get(s);
            callSampleMethod(s, f, compilationCtx);
        }
        IRTreeVoid forwardTree = compilationCtx.getOutermostScopeTree();
        IRTreeVoid reverseTree = new ReverseTreeTransformation().transform(forwardTree);

        IRTreeVoid conditional = ifElse(load(flagName), forwardTree, "Infer the samples in chronological order.",
                reverseTree, "Infer the samples in reverse chronological order.");

        IRTreeVoid tree = sequential(Tree.NoComment, conditional, store(flagName, negateBoolean(load(flagName)),
                "Reverse the direction of execution for the next iteration"));

        compilationCtx.addFunction(AuxFunctionType.GIBBS_ROUND, Visibility.PUBLIC, new ArgDesc[0], tree, true,
                "Method to execute one round of Gibbs sampling.");
        // Rest the in inference flag once the method completes.
        compilationCtx.setInInference(false);
    }

    private static void callSampleMethod(SampleTask<?, ?> sampleTask, IRFunction<?> f,
            CompilationContext compilationCtx) {
        Stack<Scope> scopes = new Stack<>();
        Scope scope = sampleTask.scope();
        while(scope != GlobalScope.scope) {
            scopes.push(scope);
            scope = scope.getEnclosingScope();
        }

        List<IRTreeReturn<?>> args = new ArrayList<>();
        IRTreeReturn<IntVariable> threadID = null;

        while(!scopes.isEmpty()) {
            scope = scopes.pop();
            if(scope.getScopeType() == ScopeType.FOR) {
                ForTask forScope = (ForTask) scope;
                IntVariable index = forScope.getIndex();
                args.add(load(index));
                if(!compilationCtx.serialScope(forScope) && (compilationCtx.target == ExecutionType.MultiThreadCPU)) {
                    threadID = load(VariableNames.threadIdName(index.getUniqueVarDesc().name));
                }
            }
        }

        if(threadID != null) {
            args.add(threadID);
            args.add(load(VariableNames.rngName(0)));
        }

        IRTreeReturn<?>[] argsArray = args.toArray(new IRTreeReturn<?>[args.size()]);
        IRTreeVoid functionCall = functionCall(f.name, Tree.NoComment, argsArray);
        IRTreeReturn<BooleanVariable> valueFixed = negateBoolean(load(VariableNames.fixedFlagName(sampleTask)));
        functionCall = ifElse(valueFixed, functionCall, Tree.NoComment);

        // Add the function to the tree of values to execute.
        compilationCtx.addTreeToScope(scope, functionCall);
    }

    private static Set<Variable<?>> forwardVariables(CompilationContext compilationCtx) {
        Set<Variable<?>> toConstruct = new HashSet<>();
        Set<Variable<?>> toReturn = new HashSet<>();

        // Construct functions for every sampled variable.
        for(SampleTask<?, ?> s:compilationCtx.traces.getAllSampleTasks()) {
            Variable<?> v = s.getOutput();
            toConstruct.add(v);
            RandomVariable<?, ?> r = s.randomVariable;
            if(r.isDistribution() && r.valueSampled())
                toReturn.add(r);
            if(s.isDistribution())
                toReturn.add(r);
        }

        /*
         * Get the set of all variables used to construct the sample outputs, terminal values, and observed variable
         * then filter.
         */
        toConstruct.addAll(compilationCtx.traces.getAllTerminalVariables());
        toConstruct.addAll(compilationCtx.traces.getAllObservedVariables());
        toConstruct.addAll(Variable.collectInputVariable(toConstruct));
        for(Variable<?> v:toConstruct)
            if((v.isIntermediate() && !v.isDeterministic()) || v.isSample())
                toReturn.add(v);

        return toReturn;
    }

    private static void addAllVars(Map<Variable<?>, Set<TraceHandle>> map, Variable<?> v,
            CompilationContext compilationCtx) {
        map.put(v, compilationCtx.traces.getVariableObservationsTraces(v));
        DataflowTask<?> task = v.getParent();
        while(task.getType() == DFType.PUT) {
            PutTask<?> putTask = (PutTask<?>) task;
            v = putTask.array;
            map.put(v, compilationCtx.traces.getVariableObservationsTraces(v));
            task = v.getParent();
        }
    }

    private static IRTreeVoid forwardBody(Map<Variable<?>, Set<TraceHandle>> vars, GuardStatus guardStatus,
            boolean useDistributions, CompilationContext compilationCtx) {
        // Clear function dependent values to prevent pollution from one context to
        // another.
        compilationCtx.initialize();
        PriorityQueue<Variable<?>> p = new PriorityQueue<>(vars.keySet());
        while(!p.isEmpty()) {
            Variable<?> v = p.poll();
            ForwardExecutionBuilder.constructForwardMethod(v, vars.get(v), guardStatus, useDistributions,
                    compilationCtx);
        }

        return compilationCtx.getOutermostScopeTree();

    }

    private static IRTreeVoid forwardBody(Set<Variable<?>> vars, GuardStatus guardStatus, boolean useDistributions,
            CompilationContext compilationCtx) {
        // Clear function dependent values to prevent pollution from one context to
        // another.
        compilationCtx.initialize();
        PriorityQueue<Variable<?>> p = new PriorityQueue<>(vars);
        while(!p.isEmpty()) {
            Variable<?> v = p.poll();
            ForwardExecutionBuilder.constructForwardMethod(v, guardStatus, useDistributions, compilationCtx);
        }

        return compilationCtx.getOutermostScopeTree();

    }

    private static void forwardPasses(Set<Variable<?>> vars, CompilationContext compilationCtx) {
        IRTreeVoid forwardBody = forwardBody(vars, GuardStatus.FREE, false, compilationCtx);
        compilationCtx.addFunction(AuxFunctionType.FORWARD_GENERATION, Visibility.PUBLIC, new ArgDesc[0], forwardBody,
                true, "Method to execute the model code conventionally.");

        Map<Variable<?>, Set<TraceHandle>> forwardVariables = getObservedVariableForwardableInputs(vars,
                compilationCtx);
        forwardBody = forwardBody(forwardVariables, GuardStatus.FREE, false, compilationCtx);
        compilationCtx.addFunction(AuxFunctionType.FORWARD_GENERATION_VALUES_NO_OUTPUTS, Visibility.PUBLIC,
                new ArgDesc[0], forwardBody, true,
                "Method to execute the model code conventionally, excluding the elements that generate observed values. Distributions are collapsed to single values.");

        forwardBody = forwardBody(forwardVariables, GuardStatus.FREE, true, compilationCtx);
        compilationCtx.addFunction(AuxFunctionType.FORWARD_GENERATION_DISTRIBUTIONS_NO_OUTPUTS, Visibility.PUBLIC,
                new ArgDesc[0], forwardBody, true,
                "Method to execute the model code conventionally, excluding the elements that generate observed values. Distributions are calculated and stored.");
    }

    private static void constructInitialisation(CompilationContext compilationCtx) {
        // Clear function dependent values to prevent pollution from one context to
        // another.
        compilationCtx.initialize();
        // Set the phase
        compilationCtx.phase = CompilationPhase.INITIALIZATION_OF_CONSTANTS;

        // Order the required variables and construct the trees to generate them.
        PriorityQueue<Variable<?>> p = new PriorityQueue<>(compilationCtx.traces.deterministicVariables());
        while(!p.isEmpty()) {
            Variable<?> v = p.poll();
            ForwardExecutionBuilder.constructForwardMethod(v, GuardStatus.NONE, true, compilationCtx);
        }

        IRTreeVoid body = compilationCtx.getOutermostScopeTree();
        compilationCtx.addFunction(AuxFunctionType.INITIALIZE, Visibility.PUBLIC, new ArgDesc[0], body, true,
                "Method for initialising the model into a valid state before commencing inference etc.");
    }

    private static void evidenceProbabilities(CompilationContext compilationCtx) {
        Set<SampleTask<?, ?>> observedSamples = compilationCtx.traces.getObservedSampleTasks();
        Set<SampleTask<?, ?>> allSamples = compilationCtx.traces.getAllSampleTasks();
        Map<SampleTask<?, ?>, IRFunction<?>> evidenceFunctions = compilationCtx
                .getFunctionMap(SampleFunctionClass.LOG_PROBABILITY_VALUE);

        // Filter and sort the variables
        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>();
        for(SampleTask<?, ?> s:allSamples)
            if(!s.isDistribution())
                p.add(s);

        // List of function calls needed to generate probabilities in the model for the
        // evidence pass.
        IRTreeVoid[] probabilityCalls = new IRTreeVoid[p.size() + 1];
        probabilityCalls[0] = functionCall(AuxFunctionType.INITIALIZE_LOG_PROBABILITY_FIELDS.functionName,
                "Reset all the non-fixed probabilities ready to calculate the new values.");

        int i = 1;
        while(!p.isEmpty()) {
            SampleTask<?, ?> sampleTask = p.poll();
            IRFunction<?> f = evidenceFunctions.get(sampleTask);
            if(observedSamples.contains(sampleTask))
                probabilityCalls[i++] = functionCall(f.name, Tree.NoComment);
            else
                probabilityCalls[i++] = ifElse(load(VariableNames.fixedFlagName(sampleTask)),
                        functionCall(f.name, Tree.NoComment), Tree.NoComment);
        }

        if(i > 1)
            probabilityCalls[1].prefixComment("Call each method in turn to generate the new probability values.");

        // Construct a method to generate the probabilities.
        compilationCtx.addFunction(AuxFunctionType.LOG_EVIDENCE_PROBABILITIES, Visibility.PRIVATE, new ArgDesc[0],
                sequential(probabilityCalls, Tree.NoComment), false, "Construct the evidence probabilities.");
    }

    private static void evidencePass(CompilationContext compilationCtx) {
        // Construct a method to call all the forward pass code, and then to call the
        // method required to generate the probabilities.
        IRTreeVoid[] calls = new IRTreeVoid[2];
        calls[0] = functionCall(AuxFunctionType.FORWARD_GENERATION_VALUES_NO_OUTPUTS.functionName,
                "Generate values for all the samples in the model that were not fixed or observed.");
        calls[1] = functionCall(AuxFunctionType.LOG_EVIDENCE_PROBABILITIES.functionName,
                "Calculate the probability for the resulting model.");

        compilationCtx.addFunction(AuxFunctionType.LOG_EVIDENCE_GENERATION, Visibility.PUBLIC, new ArgDesc[0],
                sequential(calls, Tree.NoComment), false,
                "Method to generate a new random state for the model excluding any fixed values and then calculate its probability.");
    }

    private static Map<Variable<?>, Set<TraceHandle>> getObservedVariableForwardableInputs(
            Set<Variable<?>> forwardableVars, CompilationContext compilationCtx) {
        Set<Variable<?>> evidenceVariables = compilationCtx.traces.getEvidenceVariables();

        // Calculate all the variables that will need forward functions
        Set<Variable<?>> inputVariables = Variable.collectInputVariable(evidenceVariables);
        Map<Variable<?>, Set<TraceHandle>> outputVariables = new HashMap<>();
        for(Variable<?> v:inputVariables)
            if(forwardableVars.contains(v) && !v.isFixed())
                addAllVars(outputVariables, v, compilationCtx);
        return outputVariables;
    }

    private static void allProbabilities(Set<Variable<?>> forwardableVars, CompilationContext compilationCtx) {
        compilationCtx.phase = CompilationContext.CompilationPhase.MAIN_METHODS;

        // Calculate the set of variable we want to use evidence functions for.
        Set<Variable<?>> evidenceVariables = new HashSet<>();
        for(Variable<?> v:compilationCtx.traces.getAllObservedVariables())
            evidenceVariables.addAll(compilationCtx.traces.getSourceRandomVariables(v));

        // Get the set of observed variables that we want the model to predict the
        // probability of generating.
        Set<Variable<?>> inputVariables = Variable.collectInputVariable(evidenceVariables);
        Map<Variable<?>, Set<TraceHandle>> forwardVariables = new HashMap<>();
        for(Variable<?> v:inputVariables)
            if(forwardableVars.contains(v) && !v.isFixed())
                forwardVariables.put(v, compilationCtx.traces.getVariableObservationsTraces(v));

        // Allocate a list to hold all the function calls that will be required.
        List<IRTreeVoid> calls = new ArrayList<>();

        calls.add(forwardBody(forwardVariables, GuardStatus.FREE, false, compilationCtx));

        callProbabilities(compilationCtx);
        calls.add(functionCall(AuxFunctionType.ALL_LOG_PROBABILITIES_CALCULATION_VAL.functionName,
                "Calculate the probabilities for every sample task in the model. "
                        + "These values are then used to calculate the probabilities of random variables and the model as a whole."));

        // Wrap up the function combining them all.
        compilationCtx.addFunction(AuxFunctionType.ALL_LOG_PROBABILITIES, Visibility.PUBLIC, new ArgDesc[0],
                sequential(calls, "Generate sample values for every call to sample in the model."), true,
                "Method to generate a random state of the model including random outputs, and then to calculate the probability of this random state.");
    }

    private static void callProbabilities(CompilationContext compilationCtx) {
        // Construct outer calling function
        // Get the functions for generating probabilities
        Map<SampleTask<?, ?>, IRFunction<?>> evidenceFunctionsVal = compilationCtx
                .getFunctionMap(SampleFunctionClass.LOG_PROBABILITY_VALUE);
        Map<SampleTask<?, ?>, IRFunction<?>> evidenceFunctionsDist = compilationCtx
                .getFunctionMap(SampleFunctionClass.LOG_PROBABILITY_DISTRIBUTIONS);

        List<IRTreeVoid> callsVal = new ArrayList<>();
        List<IRTreeVoid> callsDist = new ArrayList<>();
        // Initialize the fields for probabilities of intermediate variables.
        callsVal.add(functionCall(AuxFunctionType.INITIALIZE_LOG_PROBABILITY_FIELDS.functionName,
                "Reset all the non-fixed probabilities ready to calculate the new values."));
        callsDist.add(functionCall(AuxFunctionType.INITIALIZE_LOG_PROBABILITY_FIELDS.functionName,
                "Reset all the non-fixed probabilities ready to calculate the new values."));

        // Add in the functions for the probabilities
        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>(compilationCtx.traces.getAllSampleTasks());

        while(!p.isEmpty()) {
            // Add the method to generate the probability.
            SampleTask<?, ?> st = p.poll();
            IRFunction<?> fVal = evidenceFunctionsVal.get(st);
            IRFunctionCall fValCall = functionCall(fVal.name, Tree.NoComment, new IRTreeReturn[fVal.args.length]);
            callsVal.add(fValCall);

            IRFunction<?> fDist = evidenceFunctionsDist.get(st);
            if(fDist != null) {
                IRFunctionCall fDistCall = functionCall(fDist.name, Tree.NoComment,
                        new IRTreeReturn[fDist.args.length]);
                callsDist.add(fDistCall);
            } else
                callsDist.add(fValCall);
        }

        if(callsVal.size() > 1) {
            callsVal.get(1).prefixComment(
                    "Calculate the probabilities for each sample task in the model, generating probabilities for the random variables and whole model in the process using values only.");
            callsDist.get(1).prefixComment(
                    "Calculate the probabilities for each sample task in the model, generating probabilities for the random variables and whole model in the process using distributions where appropriate.");
        }

        compilationCtx.addFunction(AuxFunctionType.ALL_LOG_PROBABILITIES_CALCULATION_VAL, Visibility.PUBLIC,
                new ArgDesc[0], sequential(callsVal, Tree.NoComment), true,
                "Method to calculate the probabilities of all the samples in the model including those "
                        + "generating fixed data. In the process probabilities for all the random variables and for the model as a whole will be calculated. This model only uses values.");

        compilationCtx.addFunction(AuxFunctionType.ALL_LOG_PROBABILITIES_CALCULATION_DIST, Visibility.PUBLIC,
                new ArgDesc[0], sequential(callsDist, Tree.NoComment), true,
                "Method to calculate the probabilities of all the samples in the model including those "
                        + "generating fixed data. In the process probabilities for all the random variables and for the model as a whole will be calculated. This model uses distributions when possible.");
    }

    /**
     * Method to file the arguments found for random into a mapping of all the consumers of each sample task in the
     * system.
     *
     * @param random           The random variable consuming arguments.
     * @param sampleSignatures A mapping used to record types and select suitable conjugate generators.
     * @param compilationCtx   The compilation context for the compilation process.
     */
    private static void recordRandomVariable(RandomVariable<?, ?> random,
            Map<SampleTask<?, ?>, Map<RandomVariable<?, ?>, boolean[]>> sampleSignatures,
            CompilationContext compilationCtx) {

        // Get the random variables that you are consuming the output of, along with
        // the sample tasks it consumes from.
        List<Set<SampleTask<?, ?>>> args = compilationCtx.traces.getRandomVariablesPerArgument(random);

        // Construct a list array of all the types, and an array list of all the
        // variable instances.
        int noArgs = args.size();

        for(int i = 0; i < noArgs; i++) {
            Set<SampleTask<?, ?>> arg = args.get(i);
            for(SampleTask<?, ?> s:arg) {
                Map<RandomVariable<?, ?>, boolean[]> sampleSignature = sampleSignatures.computeIfAbsent(s,
                        k -> new HashMap<>());

                boolean[] argPositions = sampleSignature.computeIfAbsent(random, k -> new boolean[noArgs]);

                argPositions[i] = true;
            }
        }
    }
}