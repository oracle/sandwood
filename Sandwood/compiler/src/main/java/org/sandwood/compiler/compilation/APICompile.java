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
import static org.sandwood.compiler.trees.irTree.IRTree.functionCall;
import static org.sandwood.compiler.trees.irTree.IRTree.ifElse;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.multiplyDD;
import static org.sandwood.compiler.trees.irTree.IRTree.negateBoolean;
import static org.sandwood.compiler.trees.irTree.IRTree.nop;
import static org.sandwood.compiler.trees.irTree.IRTree.sequential;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.ArrayList;
import java.util.Collection;
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
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.GetTask;
import org.sandwood.compiler.dataflowGraph.tasks.arrayTasks.PutTask;
import org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks.ObserveVariableTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.DistributionSampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.returnTasks.SampleTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.IfElseAssignmentTask;
import org.sandwood.compiler.dataflowGraph.transformations.DAGTransformations;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableName;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.DistributableRandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.RandomVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.ClassName;
import org.sandwood.compiler.names.ModelClassName;
import org.sandwood.compiler.names.PackageName;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.traces.Trace;
import org.sandwood.compiler.traces.TraceHandle;
import org.sandwood.compiler.traces.Traces;
import org.sandwood.compiler.traces.TracesImplementation;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.traces.guards.ScopeConstructor;
import org.sandwood.compiler.traces.guards.TreeBuilderInfo;
import org.sandwood.compiler.trees.ArgDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.Visibility;
import org.sandwood.compiler.trees.irTree.IRFunction;
import org.sandwood.compiler.trees.irTree.IRFunctionCall;
import org.sandwood.compiler.trees.irTree.IRSandwoodClassGenerated;
import org.sandwood.compiler.trees.irTree.IRTree;
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

            // Apply DAG transformations before we start the rest of the compilation.
            DAGTransformations.apply(vs);

            // Start the compilation of the model.
            Traces traces = TracesImplementation.getTraces(vs);

            // Test the graph for errors.
            for(DataflowTask<?> d:traces.getAllTasks())
                d.testTask(compDesc.errors);

            // Test the required inversions for errors.
            compDesc.errors.addAll(inversionCheck(traces));

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

                constructSetIntermediates(compilationCtx);
                constructPropogateObservedValues(compilationCtx);

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
                            try {
                                transCls = transCls.applyOptimisations();
                            } catch(Throwable e) {
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                                throw e;
                            }

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
                    compilationCtx.getClassFields(), traces, comment, ExecutionType.supportedTypes));

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

    private static void constructPropogateObservedValues(CompilationContext compilationCtx) {

        // Set any observed variables required as input to the observation task.
        IRTreeVoid t1;
        {
            // Set the phase
            compilationCtx.phase = CompilationPhase.INITIALIZATION_OF_CONSTANTS;
            // Clear function dependent values to prevent pollution from one context to
            // another.
            compilationCtx.initialize();

            // Order the required variables and construct the trees to generate them.
            PriorityQueue<Variable<?>> p = new PriorityQueue<>(compilationCtx.traces.observedIntermediateVariables());
            while(!p.isEmpty()) {
                Variable<?> v = p.poll();
                constructForwardMethod(v, false, true, compilationCtx);
            }

            t1 = IRTree.treeScope(compilationCtx.getOutermostScopeTree(),
                    "Setting intermediate variables that are used to constrain other variables.");
        }

        // Propagate back from the observation task to the intermediate variables in the model.
        IRTreeVoid t2;
        {
            // Set the phase
            compilationCtx.phase = CompilationPhase.INITIALIZATION_OF_INTERMEDIATES;
            // Clear function dependent values to prevent pollution from one context to another.
            compilationCtx.initialize();
            // Make this serial for now to protect the unit tests. This may be removed later, but it will break the unit
            // tests as multiple RNGs will be created giving a different stream of numbers.
            compilationCtx.pushIsSerial(true);
            compilationCtx.setreverseScopes(true);

            Map<Variable<?>, Set<TraceHandle>> segmentedTraces = new HashMap<>();
            for(SampleTask<?, ?> sTask:compilationCtx.traces.getAllSampleTasks()) {
                if(compilationCtx.traces.isObserved(sTask)) {
                    // Test that the traces are valid
                    Map<Variable<?>, Set<TraceHandle>> t = compilationCtx.traces.getObservedTraces(sTask);
                    if(t == null)
                        throw new CompilerException("No observed trace provided.");

                    /*
                     * TODO weaken this guard so that traces that split on an if else are allowed, or traces that go via
                     * different arrays are allowed. This is non trivial as arrays can be used in may ways to recombine
                     * the traces.
                     */
                    int size = t.size();
                    if(size != 1) {
                        String s = "\"";
                        for(Variable<?> v:t.keySet()) {
                            s = s + v.getAlias();
                            if(--size != 0) {
                                if(size == 1)
                                    s = s + "\", \"";
                                else
                                    s = s + "\", and \"";
                            } else
                                s = s + "\"";
                        }

                        throw new SandwoodModelException(
                                "Multiple variables linked to sample task. This could result in inconsistencies. "
                                        + "Relevant variables are " + s + ".",
                                sTask);
                    }

                    ingestTrace(t.values(), segmentedTraces);
                }
            }

            // Construct the code to populate the values.
            PriorityQueue<Variable<?>> p = new PriorityQueue<>(Collections.reverseOrder());
            p.addAll(segmentedTraces.keySet());

            while(!p.isEmpty()) {
                Variable<?> end = p.poll();
                if(end.isObserved()) {
                    copyObservation(end, compilationCtx);
                } else {
                    for(TraceHandle th:segmentedTraces.get(end)) {
                        Variable<?> start = th.peek().task.getOutput();
                        if(start.isObserved())
                            start = start.getObservation().source;
                        IRTreeReturn<?> current = start.getForwardIR(compilationCtx);
                        processTask(th.size() - 1, th, current, new BackTraceInfo(), compilationCtx);
                    }
                }
            }

            compilationCtx.setreverseScopes(false);
            compilationCtx.popIsSerial();

            t2 = IRTree.treeScope(compilationCtx.getOutermostScopeTree(),
                    "Propagating values back from observations into the models intermediate variables.");
        }

        IRTreeVoid funcBody = IRTree.sequential(Tree.NoComment, t1, t2);
        compilationCtx.addFunction(AuxFunctionType.OBSERVATION_PROPAGATION, Visibility.PUBLIC, new ArgDesc[0], funcBody,
                true, "Method to propagate observed values back into the model.");
    }

    // TODO Replace the deep copies here with shallow copies of just the references, and then reallocate the variables
    // assigned prior to forward execution.
    private static <A extends Variable<A>, B extends Variable<B>> void copyObservation(Variable<A> end,
            CompilationContext compilationCtx) {
        ObserveVariableTask<A> ot = end.getObservation();
        IRTreeReturn<A> current = ot.source.getForwardIR(compilationCtx);
        DataflowTask<A> source = ot.target.getParent();
        compilationCtx.enterScope(source.scope());
        switch(source.getType()) {
            case GET: {
                // get Version
                int outputDepth = end.getType().getDepth();
                GetTask<A> gt = (GetTask<A>) source;
                IRTreeReturn<ArrayVariable<A>> arrayTree = gt.array.getForwardIR(compilationCtx);

                IRTreeReturn<IntVariable> indexTree = gt.index.getForwardIR(compilationCtx);
                if(outputDepth == 0) {
                    // If the observed value was a scalar just assign it to the array.
                    IRTreeVoid store = IRTree.arrayPut(arrayTree, indexTree, current, Tree.NoComment);
                    compilationCtx.addTreeToScope(gt.scope(), store);
                } else {
                    IRTreeVoid copyBody = TreeUtils.copyArray((IRTreeReturn<ArrayVariable<B>>) current,
                            (IRTreeReturn<ArrayVariable<B>>) IRTree.arrayGet(arrayTree, indexTree));
                    compilationCtx.addTreeToScope(end.scope(), copyBody);
                }
                break;
            }
            case PUT: {
                // Put version
                PutTask<A> pt = (PutTask<A>) source;

                // Get the tree for the array
                IRTreeReturn<ArrayVariable<A>> arrayTree = pt.array.getForwardIR(compilationCtx);

                IRTreeVoid copyBody = TreeUtils.copyArray((IRTreeReturn<ArrayVariable<A>>) current, arrayTree);
                compilationCtx.addTreeToScope(end.scope(), copyBody);
                break;
            }
            default: {
                // All other instances
                saveIntermediate(end, current, compilationCtx);
                break;
            }
        }
        compilationCtx.leaveScope(source.scope());
    }

    /**
     * A function to work out the segments of trace that values should be propogate back over and to Store the results
     * in a map indexed by the variable they create so they can be recovered and the required code computed in the
     * correct order.
     * 
     * @param observedTraces
     * @param segmentedTraces
     */
    private static void ingestTrace(Collection<Set<TraceHandle>> observedTraces,
            Map<Variable<?>, Set<TraceHandle>> segmentedTraces) {
        for(Set<TraceHandle> traces:observedTraces) {
            if(multiplePaths(traces)) {
                TraceHandle t = traces.iterator().next();
                Variable<?> output = t.peek().task.getOutput();
                throw new SandwoodModelException("Multiple traces to observed variable " + output.getAlias()
                        + ". This could result in inconsistencies.", output.getObservation());
            }

            TraceHandle t = traces.iterator().next();
            Trace segment = new Trace();
            int i = 0;

            // Skip all the tasks before the sample variable.
            DataflowTaskArgDesc d = t.get(i++);
            Variable<?> output = d.task.getOutput();
            while(!output.isSample()) {
                d = t.get(i++);
                output = d.task.getOutput();
            }

            // If the sample came from a put into an array skip it and find the outermost point of the trace.
            while(d.task.getType() == DFType.PUT && !output.isObserved()) {
                d = t.get(i++);
                output = d.task.getOutput();
            }

            if(output.isObserved())
                segmentedTraces.computeIfAbsent(output.instanceHandle(), k -> new HashSet<>());
            else {
                Variable<?> result = output.instanceHandle();
                while(true) {
                    // Add the value as this is where the trace segment ends
                    segment.add(d);
                    // If the output is observed, this is also the end of the trace so store the segment and stop.
                    if(output.isObserved()) {
                        Set<TraceHandle> segments = segmentedTraces.computeIfAbsent(result, k -> new HashSet<>());
                        segments.add(TraceHandle.getTraceHandle(segment));
                        break;
                    }

                    // Move along a segment
                    d = t.get(i++);
                    output = d.task.getOutput();
                    // If the next intermediate has been found store the segment and start the next segment.
                    if(output.isIntermediate()) {
                        Set<TraceHandle> segments = segmentedTraces.computeIfAbsent(result, k -> new HashSet<>());
                        segment.add(d);
                        segments.add(TraceHandle.getTraceHandle(segment));
                        segment.clear();
                        result = output.instanceHandle();
                    }
                }
            }
        }
    }

    /**
     * A method to check if a set of traces represents more than one path through the DAG. A single path can have more
     * than one trace if there are implicit puts, and puts specified by the user in the model that are semantically
     * equivalent.
     * 
     * @param traces The set of traces to compare
     * @return Returns true if the traces represent at least 2 different patterns.
     */
    private static boolean multiplePaths(Set<TraceHandle> traces) {
        Iterator<TraceHandle> i = traces.iterator();
        TraceHandle pattern = i.next();
        int size = pattern.size();
        while(i.hasNext()) {
            TraceHandle test = i.next();
            if(size != test.size())
                return true;
            for(int j = 0; j < size; j++) {
                DataflowTaskArgDesc dt = test.get(j);
                DataflowTaskArgDesc pt = pattern.get(j);
                if(!dt.equals(pt)) {
                    // If the 2 trace elements are not the same check that they are both puts entered through the same
                    // input and with the same index. This situation can occur because implicit puts are created when a
                    // value is put into an array that is itself in an array.
                    if(dt.argPos != pt.argPos)
                        return true;
                    if(dt.task.getType() != DFType.PUT || pt.task.getType() != DFType.PUT)
                        return true;
                    if(((PutTask<?>) dt.task).index != ((PutTask<?>) pt.task).index)
                        return true;
                }
            }
        }

        return false;
    }

    private static <A extends Variable<A>, B extends Variable<B>> void processTask(int index, TraceHandle traceHandle,
            IRTreeReturn<A> current, BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        DataflowTaskArgDesc d = traceHandle.get(index);
        ProducingDataflowTask<?> task = d.task;

        switch(task.getType()) {
            case SAMPLE: {
                // If this is an unobserved intermediate populate it with the current value.
                Variable<?> v = task.getOutput();
                if(v.isIntermediate() && v.getType().getDepth() == 0)
                    saveIntermediate(v, current, compilationCtx);
                break;
            }
            case GET: {
                if(d.argPos == 1)
                    throw new CompilerException(
                            "Unique values are not guaranteed in arrays, this prevents indexes being "
                                    + "calculated when inverting the model.");
                if(d.argPos != 0)
                    throw new CompilerException(
                            "There are only 2 arguments to a get, this value of argPos is not valid.");

                compilationCtx.enterScope(task.scope());

                Variable<?> output = task.getOutput();
                int outputDepth = output.getType().getDepth();
                GetTask<A> gt = (GetTask<A>) task;
                IRTreeReturn<ArrayVariable<A>> arrayTree = gt.array.getForwardIR(compilationCtx);

                if(outputDepth == 0) {
                    // If the value was a scalar just assign it to the array.
                    IRTreeReturn<IntVariable> indexTree = gt.index.getForwardIR(compilationCtx);
                    IRTreeVoid store = IRTree.arrayPut(arrayTree, indexTree, current, Tree.NoComment);
                    compilationCtx.addTreeToScope(gt.scope(), store);
                }
                // If the value was an array as arrays can only be assigned to a single parent array and this
                // will have happened in the allocator there is nothing to do.

                if(index != 0)
                    processTask(index - 1, traceHandle, arrayTree, backTraceInfo, compilationCtx);

                compilationCtx.leaveScope(task.scope());
                break;
            }
            case PUT: {
                switch(d.argPos) {
                    case 0:
                        throw new CompilerException("Assignments to arrays where the input is the array"
                                + " being assigned to should not appear in traces.");
                    case 1:
                        // The array was indexed by the value of a sample. If and only if we are sure
                        // the array only contains unique elements
                        // we could construct a reverse lookup and use that to determine the value of i
                        // based on the forward value of arg 3 and
                        // the current state of the array.
                        throw new CompilerException("Unable to construct inverse function as put "
                                + "tasks cannot currently determine the index a value came from.");
                    case 2:
                        compilationCtx.enterScope(task.scope());
                        PutTask<B> pt = (PutTask<B>) task;

                        // Get the tree for the array
                        IRTreeReturn<ArrayVariable<B>> arrayTree;
                        if(compilationCtx.initialized(pt.array)) {
                            pt.array.markStopPoint();
                            arrayTree = pt.array.getForwardIR(compilationCtx);
                            pt.array.unmarkStopPoint();
                        } else
                            arrayTree = pt.array.getForwardIR(compilationCtx);

                        IRTreeReturn<IntVariable> indexTree = pt.index.getForwardIR(compilationCtx);

                        // If this is not the last step in this trace segment and an array is being placed into this
                        // array declare that array in scope so that it can be used in other code.
                        if(index != 0) {
                            Variable<B> value = pt.value.instanceHandle();
                            if(value.getType().getDepth() != 0) {
                                if(!compilationCtx.initialized(value)) {
                                    compilationCtx.addTreeToScope(value.scope(),
                                            IRTree.initializeUnsetVariable(value.getUniqueVarDesc(), Tree.NoComment));
                                    compilationCtx.addInitialized(value);
                                }
                                compilationCtx.addTreeToScope(pt.scope(), IRTree.store(value.getUniqueVarDesc(),
                                        IRTree.arrayGet(arrayTree, indexTree), Tree.NoComment));
                            }
                        }

                        if(index != 0)
                            processTask(index - 1, traceHandle, IRTree.arrayGet(arrayTree, indexTree), backTraceInfo,
                                    compilationCtx);

                        compilationCtx.leaveScope(task.scope());
                        break;

                    default:
                        throw new CompilerException("Unknown operation put only accepts arguments in positions 0-2");
                }
                break;
            }
            default: {
                // If this is an unobserved intermediate populate it with the current value.
                Variable<?> output = task.getOutput();
                if(output.isIntermediate() && index == 0)
                    saveIntermediate(output, current, compilationCtx);
                IRTreeReturn<?> result = task.getInverseIR(d.argPos, current, backTraceInfo, compilationCtx);
                if(index != 0)
                    processTask(index - 1, traceHandle, result, backTraceInfo, compilationCtx);
            }
        }
    }

    private static <A extends Variable<A>> void saveIntermediate(Variable<A> variable, IRTreeReturn<?> value,
            CompilationContext compilationCtx) {
        IRTreeVoid tree = TreeUtils.putIndirectValue(variable, (IRTreeReturn<A>) value, Tree.NoComment, compilationCtx);
        compilationCtx.addTreeToScope(variable.getParent().scope(), tree);
    }

    // TODO make sure only currentInstances are used when allocating fields, otherwise the same fields will be allocated
    // again and again.
    private static void declareClassFields(CompilationContext compilationCtx) {
        // Construct the distribution fields required for sampled distributions probabilities.
        for(DistributionSampleTask<?, ?> rv:compilationCtx.traces.getDistributionSampleTasks()) {
            ScopeStack.pushScope(rv.scope());

            IntVariable noStates = rv.randomVariable.getNoStates();
            ArrayVariable<DoubleVariable> v = Variable.arrayVariable(VariableType.DoubleVariable, noStates);

            VariableDescription<ArrayVariable<DoubleVariable>> distributionName = VariableNames.distribution(rv);
            v.setUniqueVarDesc(distributionName);
            v.setIntermediate();
            v.setPrivate();

            ScopeStack.popScope(rv.scope());

            rv.addProbabilityDistribution(v);
            compilationCtx.addConstructedClassField(v, false, false, compilationCtx);
        }

        // Intermediates and sample variables
        for(Variable<?> v:compilationCtx.traces.getAllVariables()) {
            boolean isSubArray = v.getType().isArray() && ((ArrayVariable<?>) v).isSubArray();
            boolean isModelInput = v.getParent().getType() == DFType.CONSTRUCT_INPUT;
            if((v.isIntermediate() || (v.isSample() && !isSubArray)) && !isModelInput) {
                boolean setter = !v.isPrivate() && !v.isDeterministic() && setBySampleValue(v);
                compilationCtx.addConstructedClassField(v, !v.isPrivate(), setter, compilationCtx);
                // TODO decide if the setflag and the fixed flag should be merged into a single flag.
                if(setter && (v.getType().isArray() || v.scope() != GlobalScope.scope)) {
                    compilationCtx.addConstructedClassField(VariableNames.setFlagName(v.getVarDesc()), null, null,
                            false, false, true, false, false, false, IRTree.constant(false), Tree.NoComment);
                }
            }
        }

        // Fixed sample flags
        for(SampleTask<?, ?> s:compilationCtx.traces.getAllSampleTasks()) {
            VariableDescription<BooleanVariable> flagName = VariableNames.fixedFlagName(s);
            compilationCtx.addConstructedClassField(flagName, true, true, null, constant(false));
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

    private static Set<SandwoodModelException> inversionCheck(Traces traces) {
        Set<SandwoodModelException> errors = new HashSet<>();
        for(SampleTask<?, ?> s:traces.getAllIntermediateSamples()) {
            for(RandomVariable<?, ?> consumingRV:traces.getTracesRVToSampleTask(s).keySet()) {
                for(TraceHandle t:traces.getObservedTraces(consumingRV)) {
                    Map<ProducingDataflowTask<?>, String> tErrors = t.inversionErrors();
                    // Remove the sample task as we know it is present and cannot be inverted.
                    tErrors.remove(t.get(0).task);
                    for(ProducingDataflowTask<?> task:tErrors.keySet())
                        errors.add(new SandwoodModelException(tErrors.get(task), task));
                }
                for(TraceHandle t:traces.getIntermediateVariableTraces(consumingRV)) {
                    Map<ProducingDataflowTask<?>, String> tErrors = t.inversionErrors();
                    // Remove the sample task as we know it is present and cannot be inverted.
                    tErrors.remove(t.get(0).task);
                    for(ProducingDataflowTask<?> task:tErrors.keySet())
                        errors.add(new SandwoodModelException(tErrors.get(task), task));
                }
            }
        }
        return errors;
    }

    private static void constructSetIntermediates(CompilationContext compilationCtx) {
        compilationCtx.phase = CompilationPhase.INITIALIZATION_OF_INTERMEDIATES;
        compilationCtx.initialize();
        PriorityQueue<Variable<?>> p = new PriorityQueue<>(compilationCtx.traces.getIntermediates());
        while(!p.isEmpty()) {
            Variable<?> v = p.poll();
            if(!v.isDeterministic() && !setBySampleValue(v))
                constructIntermediate(v, compilationCtx);
        }

        compilationCtx.addFunction(AuxFunctionType.SET_INTERMEDIATES, Visibility.PUBLIC, new ArgDesc[0],
                compilationCtx.getOutermostScopeTree(), true,
                "A method to set array values that depend on the output of a sample task, but are not directly set by the sample task.");
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

        while(v.getParent().getType() == DFType.PUT) {
            PutTask<?> pt = (PutTask<?>) v.getParent();
            if(pt.value.isSample() && !pt.value.isIntermediate())
                return true;
            v = pt.value;
        }
        return false;
    }

    private static <A extends Variable<A>> void constructIntermediate(Variable<A> v,
            CompilationContext compilationCtx) {
        // TODO See if the uses of this guard can be constrained.
        BooleanVariable guard = constructGuard(v, compilationCtx);

        Scope targetScope = v.getParent().scope();
        Scope newScope = new IfScope(targetScope, guard);

        compilationCtx.touchScope(newScope);
        compilationCtx.addScopeSubstitute(targetScope, newScope);

        IRTreeReturn<A> t = v.getParent().getForwardIR(compilationCtx);
        if(!compilationCtx.initialized(v)) {
            compilationCtx.addTreeToScope(newScope, TreeUtils.putIndirectValue(v, t, Tree.NoComment, compilationCtx));
            compilationCtx.addInitialized(v);
        }

        compilationCtx.removeScopeSubstitute(targetScope);
    }

    private static <A extends Variable<A>> BooleanVariable constructGuard(Variable<A> v,
            CompilationContext compilationCtx) {
        Collection<Variable<?>> dependencies = compilationCtx.traces.getIntermediateSampleVarDependencies(v);

        PriorityQueue<Variable<?>> guardQueue = new PriorityQueue<>(dependencies);
        BooleanVariable guard = BooleanVariable.booleanVariable(true, GlobalScope.scope);
        while(!guardQueue.isEmpty()) {
            Variable<?> sourceVar = guardQueue.poll();
            // Test that the array is visible, and check for set values on arrays in case they have not been allocated
            // yet.
            if(!sourceVar.isPrivate() && sourceVar.getType().isArray())
                guard = guard.and(Variable.namedVariable(VariableNames.setFlagName(sourceVar.getUniqueVarDesc())));
        }
        return guard;
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
                    compDesc.warnings
                            .add(new SandwoodModelException(matropolisHastingsWarning(s, signature, suggestions), s));
                    IRVoidFunction f = generator.constructFunction(s, compilationCtx);
                    compilationCtx.addFunction(SampleFunctionClass.INFERENCE, s, f);
                } else {
                    if(compilationCtx.fullInferenceRequired())
                        compDesc.errors
                                .add(new SandwoodModelException(constructPairingFailureMessage(s, signature), s));
                    else
                        compDesc.warnings
                                .add(new SandwoodModelException(constructPairingFailureMessage(s, signature), s));
                    return;
                }
            }
        }
    }

    private static String matropolisHastingsWarning(SampleTask<?, ?> s, Map<RandomVariable<?, ?>, boolean[]> signature,
            List<String> suggestions) {
        StringBuffer sb = new StringBuffer();
        sb.append("Unable to generate inference function based on a conjugate prior for\n" + s.randomVariable.getType()
                + " => {");
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
        sb.append(" }\nFalling back on Metropolis–Hastings. This may make convergence slow.");
        for(String suggestion:suggestions)
            sb.append(suggestion + "\n");
        return sb.toString();
    }

    private static String constructPairingFailureMessage(SampleTask<?, ?> s,
            Map<RandomVariable<?, ?>, boolean[]> signature) {
        StringBuffer sb = new StringBuffer();
        sb.append("Unable to generate inference techniques for all the pairings.\n");
        sb.append("From " + s.randomVariable.getType().getAPIType() + " to ");
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

        System.out.println(sb.toString());
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
        compilationCtx.addConstructedClassField(flagName, nop(), constant(true));
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

    private static void addAllVars(Set<Variable<?>> set, Variable<?> v) {
        set.add(v);
        DataflowTask<?> task = v.getParent();
        while(task.getType() == DFType.PUT) {
            PutTask<?> putTask = (PutTask<?>) task;
            v = putTask.array;
            set.add(v);
            task = v.getParent();
        }
    }

    private static IRTreeVoid forwardBody(Set<Variable<?>> vars, boolean useDistributions,
            CompilationContext compilationCtx) {
        // Clear function dependent values to prevent pollution from one context to
        // another.
        compilationCtx.initialize();
        PriorityQueue<Variable<?>> p = new PriorityQueue<>(vars);
        while(!p.isEmpty()) {
            Variable<?> v = p.poll();
            constructForwardMethod(v, true, useDistributions, compilationCtx);
        }

        return compilationCtx.getOutermostScopeTree();

    }

    private static void forwardPasses(Set<Variable<?>> vars, CompilationContext compilationCtx) {
        IRTreeVoid forwardBody = forwardBody(vars, false, compilationCtx);
        compilationCtx.addFunction(AuxFunctionType.FORWARD_GENERATION, Visibility.PUBLIC, new ArgDesc[0], forwardBody,
                true, "Method to execute the model code conventionally.");

        Set<Variable<?>> forwardVariables = getObservedVariableForwardableInputs(vars, compilationCtx);
        forwardBody = forwardBody(forwardVariables, false, compilationCtx);
        compilationCtx.addFunction(AuxFunctionType.FORWARD_GENERATION_VALUES_NO_OUTPUTS, Visibility.PUBLIC,
                new ArgDesc[0], forwardBody, true,
                "Method to execute the model code conventionally, excluding the elements that generate observed values. Distributions are collapsed to single values.");

        forwardBody = forwardBody(forwardVariables, true, compilationCtx);
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
            constructForwardMethod(v, false, true, compilationCtx);
        }

        IRTreeVoid body = compilationCtx.getOutermostScopeTree();
        compilationCtx.addFunction(AuxFunctionType.INITIALIZE, Visibility.PUBLIC, new ArgDesc[0], body, true,
                "Method for initialising the model into a valid state before commencing inference etc.");
    }

    private static IRTreeReturn<BooleanVariable> constructGuard(Set<SampleTask<?, ?>> sTasks) {
        IRTreeReturn<BooleanVariable> guard = null;

        PriorityQueue<SampleTask<?, ?>> p = new PriorityQueue<>(sTasks);
        if(!p.isEmpty()) {
            VariableDescription<BooleanVariable> flagName = VariableNames.fixedFlagName(p.poll());
            guard = load(flagName);

            while(!p.isEmpty()) {
                flagName = VariableNames.fixedFlagName(p.poll());
                guard = and(guard, load(flagName));
            }

            guard = negateBoolean(guard);
        }
        return guard;

    }

    // TODO make sure this is only called on intermediates. Once this is done calls
    // to isIntermediate can be removed as they will always be true.
    private static void constructForwardMethod(Variable<?> intermediate, Boolean restricted, boolean useDistributions,
            CompilationContext compilationCtx) {
        if(intermediate instanceof RandomVariable) {
            RandomVariable<?, ?> rv = (RandomVariable<?, ?>) intermediate;

            if(rv.distributionSampled() && useDistributions) {
                // If we are using restrictions in this code construct a guard for the code
                // generating this intermediate.
                IRTreeReturn<BooleanVariable> guard = null;
                if(restricted) {
                    // We want to generate new values from the distribution unless all the consumers
                    // won't use it.
                    Set<SampleTask<?, ?>> sTasks = new HashSet<>();
                    for(DataflowTask<?> task:rv.getConsumers()) {
                        SampleTask<?, ?> sTask = (SampleTask<?, ?>) task;
                        sTasks.add(sTask);
                    }
                    guard = constructGuard(sTasks);
                    compilationCtx.setCodeGuard(guard);
                }

                constructForwardRVDistribution((DistributableRandomVariable<?, ?>) rv, compilationCtx);

                // If a guard was generated clear it.
                if(guard != null)
                    compilationCtx.clearCodeGuard();
            }
        } else {
            if(!(intermediate.isDistribution() && useDistributions)) {
                // A special case for if assignments.
                if(intermediate.getParent().getType() == DFType.IF_ASSIGNMENT) {
                    forwardIfAssignment(intermediate, restricted, compilationCtx);
                } else {
                    // If we are using restrictions in this code construct a guard for the code
                    // generating this intermediate.
                    IRTreeReturn<BooleanVariable> guard = null;
                    if(restricted) {
                        Set<SampleTask<?, ?>> sTasks = getSourceSampleTasks(intermediate, compilationCtx);
                        guard = constructGuard(sTasks);
                        compilationCtx.setCodeGuard(guard);
                    }

                    constructVariableForward(intermediate, compilationCtx);

                    // If a guard was generated clear it.
                    if(guard != null)
                        compilationCtx.clearCodeGuard();
                }
            }
        }
    }

    private static <A extends Variable<A>> void forwardIfAssignment(Variable<A> intermediate, Boolean restricted,
            CompilationContext compilationCtx) {
        /*
         * This indirection is done to allow for the case where the intermediate variable is allocated inside a for
         * loop. When this happens an array is created to allow each instantiation of the intermediate variable to be
         * saved, and this array needs dereferencing. As this is constructed by the compiler there is no problem with
         * complex indices, or different orders to the indices.
         */
        List<Variable<A>> sources = new ArrayList<>();
        sources.add(intermediate);
        while(!sources.isEmpty()) {
            Variable<A> v = sources.remove(sources.size() - 1);
            DataflowTask<A> parent = v.getParent();
            if(parent.getType() == DFType.IF_ASSIGNMENT) {
                IfElseAssignmentTask<A> ifElseParent = (IfElseAssignmentTask<A>) parent;
                sources.add(ifElseParent.ifValue);
                sources.add(ifElseParent.elseValue);
            } else {
                // If we are using restrictions in this code construct a guard for the code
                // generating this intermediate.
                IRTreeReturn<BooleanVariable> guard = null;
                if(restricted) {
                    Set<SampleTask<?, ?>> sTasks = getSourceSampleTasks(v, compilationCtx);
                    guard = constructGuard(sTasks);
                    compilationCtx.setCodeGuard(guard);
                }

                IRTreeReturn<A> tree;
                if(v.isIntermediate() || v.isSample())
                    tree = IRTree.load(v);
                else
                    tree = v.getForwardIR(compilationCtx);
                compilationCtx.addTreeToScope(v.scope(),
                        IRTree.store(intermediate.getUniqueVarDesc(), tree, Tree.NoComment));

                // If a guard was generated clear it.
                if(guard != null)
                    compilationCtx.clearCodeGuard();
            }
        }
    }

    /**
     * Method to get all the sample tasks that a variable is dependent on when performing forward execution.
     * 
     * @param v              The variable to get the dependent sample tasks for.
     * @param compilationCtx The compilaiton context.
     * @return A set containing all the dependent variables.
     */
    private static Set<SampleTask<?, ?>> getSourceSampleTasks(Variable<?> v, CompilationContext compilationCtx) {
        return getSourceSampleTasks(v, new HashSet<>(), compilationCtx.traces);
    }

    /**
     * Method to get all the sample tasks that a variable is dependent on when performing forward execution.
     * 
     * @param v      The variable to get the dependent sample tasks for.
     * @param traces The traces object to query.
     * @param seen   A set containing all the variables already seen.
     * @return A set containing all the dependent variables.
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

    private static <A extends Variable<A>> void constructVariableForward(Variable<A> v,
            CompilationContext compilationCtx) {
        // Otherwise just generate regular code.
        // NOTE Get parent is required to avoid tests that stop code generation at
        // intermediate
        // values.
        if(!v.isDeterministic() || compilationCtx.phase == CompilationPhase.INITIALIZATION_OF_CONSTANTS) {
            IRTreeReturn<A> returnBody = v.getParent().getForwardIR(compilationCtx);

            // Typically this will be if the value is from a sample task and is written to an intermediate array.
            // In this case the result will have already been written out to the outer array.
            if(v.getType().isArray() && ((ArrayVariable<?>) v).isSubArray())
                return;

            /*
             * This indirection is done to allow for the case where the intermediate variable is allocated inside a for
             * loop. When this happens an array is created to allow each instantiation of the intermediate variable to
             * be saved, and this array needs dereferencing. As this is constructed by the compiler there is no problem
             * with complex indices, or different orders to the indices.
             */
            IRTreeVoid storedResult = TreeUtils.putIndirectValue(v, returnBody, Tree.NoComment, compilationCtx);
            compilationCtx.addTreeToScope(v.scope(), storedResult);
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
                IntVariable noStates = rv.getNoStates();

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
                IntVariable noStates = rv.getNoStates();

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
                IntVariable noStates = rv.getNoStates();
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
            IntVariable noStates = rv.getNoStates();

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

    private static Set<Variable<?>> getObservedVariableForwardableInputs(Set<Variable<?>> forwardableVars,
            CompilationContext compilationCtx) {
        Set<Variable<?>> evidenceVariables = getObservedSampleTaskInputs(compilationCtx);

        // Calculate all the variables that will need forward functions
        Set<Variable<?>> inputVariables = Variable.collectInputVariable(evidenceVariables);
        Set<Variable<?>> outputVariables = new HashSet<>();
        for(Variable<?> v:inputVariables)
            if(forwardableVars.contains(v))
                addAllVars(outputVariables, v);
        return outputVariables;
    }

    private static Set<Variable<?>> getObservedSampleTaskInputs(CompilationContext compilationCtx) {
        // Calculate the set of variable we want to use evidence functions for.
        Set<Variable<?>> evidenceVariables = new HashSet<>();
        for(Variable<?> v:compilationCtx.traces.getAllObservedVariables())
            evidenceVariables.addAll(compilationCtx.traces.getSourceRandomVariables(v));
        return evidenceVariables;
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
        Set<Variable<?>> forwardVariables = new HashSet<>();
        for(Variable<?> v:inputVariables)
            if(forwardableVars.contains(v))
                forwardVariables.add(v);

        // Allocate a list to hold all the function calls that will be required.
        List<IRTreeVoid> calls = new ArrayList<>();

        calls.add(forwardBody(forwardVariables, false, compilationCtx));

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
        // their sample task it consumes from.
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