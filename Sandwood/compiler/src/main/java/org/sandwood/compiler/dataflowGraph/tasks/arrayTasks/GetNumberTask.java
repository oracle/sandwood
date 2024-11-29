/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.arrayTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.Sandwood;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.ScopeStack;
import org.sandwood.compiler.dataflowGraph.tasks.ArrayProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable.OuterArrayDesc;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class GetNumberTask<A extends NumberVariable<A>> extends GetTask<A> implements NumberProducingDataflowTask<A> {

    public GetNumberTask(ArrayVariable<A> array, IntVariable index, Location location) {
        super(array, index, location);
    }

    @Override
    public IRTreeReturn<A> getMax(CompilationContext compilationCtx) {
        compilationCtx.enterScope(scope());
        VariableDescription<A> max = VariableNames.calcVarName(output, "max", getOutputType());

        ArrayProducingDataflowTask<?> source = array.getSource();
        if(source.getType() == DFType.CONSTRUCT_INPUT) {
            // Input value: Find the largest possible to reach input value

            // Initialise max value
            IRTreeReturn<A> initialValue;
            if(getOutputType() == VariableType.DoubleVariable)
                initialValue = (IRTreeReturn<A>) IRTree.constant(Double.NEGATIVE_INFINITY);
            else
                initialValue = (IRTreeReturn<A>) IRTree.constant(Integer.MIN_VALUE);
            IRTreeVoid t = IRTree.initializeVariable(max, initialValue,
                    "Calculate the maximum size of the inputs to array " + array.getVarDesc() + ".");
            compilationCtx.addTreeToScope(GlobalScope.scope, t);

            // Iterate through all reachable values
            List<Scope> substitutions = new ArrayList<>();

            IRTreeReturn<ArrayVariable<A>> arrayTree = getInputArrayTree(array, substitutions, "max",
                    array.getType().getDepth(), compilationCtx);

            if(index.isDeterministic()) {
                // Read the max variable from the possible values to read from the array.
                t = IRTree.store(max,
                        IRTree.max(IRTree.load(max), IRTree.arrayGet(arrayTree, index.getForwardIR(compilationCtx))),
                        Tree.NoComment);
                compilationCtx.addTreeToScope(scope(), t);
            } else {
                // If the index is drawn from a random variable check all possible values.
                IRTreeReturn<IntVariable> start = IRTree.max(IRTree.constant(0), index.getMin(compilationCtx));
                IRTreeReturn<IntVariable> end = IRTree.min(
                        IRTree.getIntField(array.getForwardIR(compilationCtx), "length"),
                        IRTree.addII(index.getMax(compilationCtx), IRTree.constant(1)));
                VariableDescription<IntVariable> indexDesc = VariableNames.calcVarName(output, "maxIndex",
                        VariableType.IntVariable);
                t = IRTree.store(max, IRTree.max(IRTree.load(max), IRTree.arrayGet(arrayTree, IRTree.load(indexDesc))),
                        Tree.NoComment);
                t = IRTree.forStmt(t, start, end, IRTree.constant(1), indexDesc, true,
                        "Check all possible index values");
                compilationCtx.addTreeToScope(scope(), t);
            }

            for(Scope s:substitutions)
                compilationCtx.removeScopeSubstitute(s);
        } else {
            // Locally set value
            Set<PutTask<A>> puts = array.getPuts(scope(), id());

            if(puts.isEmpty())
                throw new SandwoodModelException(
                        "Unable to determine the maximum size of the elements of array " + array.getSource().getName()
                                + " as no values have been assigned to it at this point in the model.",
                        getLocation());
            else {
                // For each put value, find it's maximum value and calculate the maximum of all
                // these values.
                PriorityQueue<PutTask<A>> p = new PriorityQueue<>(puts);
                PutTask<A> pt = p.poll();
                A v = (A) pt.value;
                IRTreeVoid t = IRTree.initializeVariable(max, v.getMax(compilationCtx),
                        "Calculate the maximum size of the inputs to array " + array.getVarDesc() + ".");
                compilationCtx.addTreeToScope(GlobalScope.scope, t);
                while(!p.isEmpty()) {
                    pt = p.poll();
                    v = (A) pt.value;
                    t = IRTree.store(max, IRTree.max(IRTree.load(max), v.getMax(compilationCtx)), Tree.NoComment);
                    compilationCtx.addTreeToScope(GlobalScope.scope, t);
                }
            }
        }

        compilationCtx.leaveScope(scope());
        return IRTree.load(max);
    }

    @Override
    public IRTreeReturn<A> getMin(CompilationContext compilationCtx) {
        compilationCtx.enterScope(scope());
        VariableDescription<A> min = VariableNames.calcVarName(getOutput(), "min", getOutputType());

        ArrayProducingDataflowTask<?> source = array.getSource();
        if(source.getType() == DFType.CONSTRUCT_INPUT) {
            // Input value: Find the largest possible to reach input value

            // Initialise max value
            IRTreeReturn<A> initialValue;
            if(getOutputType() == VariableType.DoubleVariable)
                initialValue = (IRTreeReturn<A>) IRTree.constant(Double.POSITIVE_INFINITY);
            else
                initialValue = (IRTreeReturn<A>) IRTree.constant(Integer.MAX_VALUE);
            IRTreeVoid t = IRTree.initializeVariable(min, initialValue,
                    "Calculate the minimum size of the inputs to array " + array.getVarDesc() + ".");
            compilationCtx.addTreeToScope(GlobalScope.scope, t);

            // Iterate through all reachable values
            List<Scope> substitutions = new ArrayList<>();

            IRTreeReturn<ArrayVariable<A>> arrayTree = getInputArrayTree(array, substitutions, "min",
                    array.getType().getDepth(), compilationCtx);

            if(index.isDeterministic()) {
                // Read the max variable from the possible values to read from the array.
                t = IRTree.store(min,
                        IRTree.min(IRTree.load(min), IRTree.arrayGet(arrayTree, index.getForwardIR(compilationCtx))),
                        Tree.NoComment);
                compilationCtx.addTreeToScope(scope(), t);
            } else {
                // If the index is drawn from a random variable check all possible values.
                IRTreeReturn<IntVariable> start = IRTree.max(IRTree.constant(0), index.getMin(compilationCtx));
                IRTreeReturn<IntVariable> end = IRTree.min(
                        IRTree.getIntField(array.getForwardIR(compilationCtx), "length"),
                        IRTree.addII(index.getMax(compilationCtx), IRTree.constant(1)));
                VariableDescription<IntVariable> indexDesc = VariableNames.calcVarName(output, "maxIndex",
                        VariableType.IntVariable);
                t = IRTree.store(min, IRTree.min(IRTree.load(min), IRTree.arrayGet(arrayTree, IRTree.load(indexDesc))),
                        Tree.NoComment);
                t = IRTree.forStmt(t, start, end, IRTree.constant(1), indexDesc, true,
                        "Check all possible index values");
                compilationCtx.addTreeToScope(scope(), t);
            }

            for(Scope s:substitutions)
                compilationCtx.removeScopeSubstitute(s);
        } else {
            // Locally set value
            Set<PutTask<A>> puts = array.getPuts(scope(), id());

            if(puts.isEmpty())
                throw new SandwoodModelException(
                        "Unable to determine the minimum size of the elements of array " + array.getSource().getName()
                                + " as no values have been assigned to it at this point in the model.",
                        getLocation());
            else {
                // For each put value, find it's maximum value and calculate the maximum of all
                // these values.
                PriorityQueue<PutTask<A>> p = new PriorityQueue<>(puts);
                PutTask<A> pt = p.poll();
                A v = (A) pt.value;
                IRTreeVoid t = IRTree.initializeVariable(min, v.getMin(compilationCtx),
                        "Calculate the minimum size of the inputs to array " + array.getVarDesc() + ".");
                compilationCtx.addTreeToScope(GlobalScope.scope, t);
                while(!p.isEmpty()) {
                    pt = p.poll();
                    v = (A) pt.value;
                    t = IRTree.store(min, IRTree.min(IRTree.load(min), v.getMin(compilationCtx)), Tree.NoComment);
                    compilationCtx.addTreeToScope(GlobalScope.scope, t);
                }
            }
        }
        compilationCtx.leaveScope(scope());
        return IRTree.load(min);
    }

    private <B extends Variable<B>> IRTreeReturn<ArrayVariable<B>> getInputArrayTree(ArrayVariable<B> array,
            List<Scope> substitutions, String tag, int id, CompilationContext compilationCtx) {
        OuterArrayDesc<B> desc = array.getOuterArrayDesc();
        ArrayVariable<ArrayVariable<B>> outerArray = desc.getArray();
        // If this is the outer array just return the value.
        if(outerArray == null)
            return array.getForwardIR(compilationCtx);

        IRTreeReturn<ArrayVariable<ArrayVariable<B>>> outerArrayTree = getInputArrayTree(outerArray, substitutions, tag,
                id - 1, compilationCtx);
        VariableDescription<ArrayVariable<B>> innerName = VariableNames.calcVarName(output, tag + "Array" + id,
                array.getType());
        IntVariable arrayIndex = desc.getIndex();
        Scope arrayScope = desc.getScope();

        if(arrayIndex.isDeterministic()) {
            IRTreeVoid arrayGet = IRTree.initializeVariable(innerName,
                    IRTree.arrayGet(outerArrayTree, arrayIndex.getForwardIR(compilationCtx)), IRTree.NoComment);
            compilationCtx.addTreeToScope(arrayScope, arrayGet);
        } else {
            // Get index bounds
            IRTreeReturn<IntVariable> startTree = IRTree.max(IRTree.constant(0), index.getMin(compilationCtx));
            IRTreeReturn<IntVariable> endTree = IRTree.min(
                    IRTree.getIntField(array.getForwardIR(compilationCtx), "length"),
                    IRTree.addII(index.getMax(compilationCtx), IRTree.constant(1)));

            // Construct local value names to hold them
            VariableDescription<IntVariable> startDesc = VariableNames.calcVarName(output, tag + "Start" + id,
                    VariableType.IntVariable);
            VariableDescription<IntVariable> endDesc = VariableNames.calcVarName(output, tag + "End" + id,
                    VariableType.IntVariable);
            VariableDescription<IntVariable> indexDesc = VariableNames.calcVarName(output, tag + "Index" + id,
                    VariableType.IntVariable);

            // Add the value to the scope
            compilationCtx.addTreeToScope(arrayScope,
                    IRTree.initializeVariable(startDesc, startTree, "The minimum value of the index"));
            compilationCtx.addTreeToScope(arrayScope,
                    IRTree.initializeVariable(endDesc, endTree, "The maximum value of the index"));

            // Create variables that can be used in a for scope.
            IntVariable startVar = Variable.namedVariable(startDesc, arrayScope);
            IntVariable endVar = Variable.namedVariable(endDesc, arrayScope);
            IntVariable stepVar = Variable.intVariable(1);

            // Create a for loop to explore the possible values read from the array
            ScopeStack.pushScope(arrayScope);
            ForTask forScope = Sandwood.forLoop(startVar, endVar, stepVar, true, (index) -> {
                // Set alias for better readability, this has no effect on the generated code.
                index.setAlias(indexDesc);
                index.setUniqueVarDesc(indexDesc);
            });
            compilationCtx.touchScope(forScope);
            ScopeStack.popScope(arrayScope);

            // Read the value from the array
            IRTreeVoid arrayGet = IRTree.initializeVariable(innerName,
                    IRTree.arrayGet(outerArrayTree, forScope.getIndex().getForwardIR(compilationCtx)),
                    IRTree.NoComment);
            compilationCtx.addTreeToScope(forScope, arrayGet);

            // Add substitution into the new scope.
            compilationCtx.addScopeSubstitute(arrayScope, forScope);
            substitutions.add(forScope);
        }
        return IRTree.load(innerName);
    }
}
