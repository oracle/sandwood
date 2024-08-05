/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.scopes;

import java.util.ArrayList;
import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ScopeTracking;
import org.sandwood.compiler.dataflowGraph.Id;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ReductionInput;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRArrayGet;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public abstract class ReductionScopeBase<A extends Variable<A>> extends Id implements Scope {

    private final Scope scope;
    public final ArrayVariable<A> array;
    public final IntVariable start;
    public final IntVariable end;
    public final Variable<A> emptyValue;
    public Variable<A> i, j, returnVar;

    protected ReductionScopeBase(ReductionScopeBase<A> rs, Variable<A> emptyValue, Scope scope) {
        this.scope = scope;
        start = rs.start;
        end = rs.end;
        array = rs.array;
        this.emptyValue = emptyValue.getCurrentInstance();
        i = rs.i;
        j = rs.j;
        returnVar = rs.returnVar;
    }
    
    public ReductionScopeBase(IntVariable start, IntVariable end, ArrayVariable<A> array, Variable<A> emptyValue) {
        this.start = start;
        this.end = end;
        this.array = array;
        this.emptyValue = emptyValue;
        scope = ScopeStack.getCurrentScope();
    }

    @Override
    public boolean iterating() {
        return false;
    }

    @Override
    public IRTreeVoid getScopeTree(ScopeTracking scopeTracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        List<IRTreeVoid> reduceBody = new ArrayList<>();

        VariableDescription<A> returnName = getReturnName();

        reduceBody.add(IRTree.initializeVariable(returnName, emptyValue.getForwardIR(compilationCtx),
                "A generated name to prevent name collisions if the reduction is implemented more than once in "
                        + "inference and probability code. Initialize the variable to the unit value"));

        VariableDescription<IntVariable> indexName = indexName();
        List<IRTreeVoid> forBody = new ArrayList<>();

        forBody.add(IRTree.initializeVariable(i, IRTree.load(returnName),
                "Set the left hand term of the reduction function to the return variable value."));

        IRArrayGet<A> arrayGet = IRTree.arrayGet(array.getForwardIR(compilationCtx), IRTree.load(indexName));
        forBody.add(IRTree.initializeVariable(j, arrayGet,
                "Set the right hand term to a value from the array " + array.getVarDesc()));
        tree.prefixComment("Execute the reduction function, saving the result into the return value.");
        forBody.add(tree);

        constructForStmt(reduceBody, forBody, indexName, compilationCtx);

        return IRTree.sequential(reduceBody, "Reduction of array " + array.getAlias());
    }

    protected void constructForStmt(List<IRTreeVoid> reduceBody, List<IRTreeVoid> forBody,
            VariableDescription<IntVariable> indexName, CompilationContext compilationCtx) {
        reduceBody.add(IRTree.forStmt(IRTree.sequential(forBody, Tree.NoComment), start.getForwardIR(compilationCtx),
                end.getForwardIR(compilationCtx), IRTree.constant(1), indexName, true,
                "For each index in the array to be reduced"));
    }

    protected abstract VariableDescription<A> getReturnName();

    private VariableDescription<IntVariable> indexName() {
        return VariableNames.calcVarName("reduction" + id() + "Index", VariableType.IntVariable, false);
    }

    public Variable<A> reduceEmptyValue(ReductionInput<A> ri, CompilationContext compilationCtx) {
        IRTreeReturn<IntVariable> mask = ri.start.getForwardIR(compilationCtx);
        Variable<A> emptyValue = ri.array.get(ri.start);

        return constructReduceValues(ri, mask, emptyValue, compilationCtx);
    }

    public Variable<A> reduceArrayValue(ReductionInput<A> ri, IRTreeReturn<IntVariable> mask,
            CompilationContext compilationCtx) {
        return constructReduceValues(ri, mask, ri.emptyValue, compilationCtx);
    }

    private Variable<A> constructReduceValues(ReductionInput<A> ri, IRTreeReturn<IntVariable> mask,
            Variable<A> emptyValue, CompilationContext compilationCtx) {
        ReductionScopeBase<A> reductionScope = ri.scope();
        Scope outerScope = compilationCtx.substituteScope(reductionScope.getEnclosingScope());

        // Construct outer scopes
        IfScope ifScope = new IfScope(outerScope,
                ri.start.lessThan(ri.array.length()).and(ri.end.greaterThan(ri.start)));
        ReductionScopeCopied<A> newReductionScope = new ReductionScopeCopied<>(ifScope, mask, emptyValue, reductionScope);

        // Set the substitution, and evaluate the function minus the masked value.
        compilationCtx.addScopeSubstitute(reductionScope, newReductionScope);
        // TODO add something here to make it run through intermediates (arrays) if they are encased within the scope.
        Variable<A> returnVar = reductionScope.returnVar;
        returnVar.calculateIntermediate(true);
        IRTreeReturn<A> reduced = returnVar.getForwardIR(compilationCtx);
        returnVar.calculateIntermediate(false);
        VariableDescription<A> reducedName = VariableNames.calcVarName("reduced" + reductionScope.id(),
                reduced.getOutputType(), true);
        IRTreeVoid reducedStore = IRTree.initializeVariable(reducedName, reduced, Tree.NoComment);
        compilationCtx.addTreeToScope(ifScope, reducedStore);

        // Tidy up and set for the remaining elements in the trace.
        compilationCtx.removeScopeSubstitute(reductionScope);
        return Variable.namedVariable(reducedName, ifScope);
    }

    @Override
    public String getDescription() {
        return "Reduction " + id();
    }

    @Override
    public ScopeType getScopeType() {
        return ScopeType.REDUCE;
    }

    @Override
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        for(int i = 0; i < indent; i++)
            sb.append("\t");
        sb.append(returnVar.getType().getJavaType() + " " + returnVar.getExpression(false) + " = reduce("
                + array.getExpression(false) + ", " + start.getExpression(compressSandwoodCode) + ", "
                + end.getExpression(compressSandwoodCode) + ", " + emptyValue.getExpression(compressSandwoodCode) + ")"
                + " (" + array.getElementType().getJavaType() + " " + i.getExpression(false) + ", "
                + array.getElementType().getJavaType() + " " + j.getExpression(false) + ") -> {\n");
        return indent + 1;

    }

    @Override
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        --indent;
        for(int i = 0; i < indent; i++)
            sb.append("\t");
        sb.append("}\n");
        return indent;
    }

    @Override
    public Scope getEnclosingScope() {
        return scope;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public String toString(int i) {
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < i; j++)
            sb.append("\t");
        sb.append("reduction scope " + id() + "\n" + scope.toString(i + 1));
        return sb.toString();
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return true;
    }

    public abstract VariableDescription<A> getVariableDesc(CompilationContext compilationCtx);
}
