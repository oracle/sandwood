/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.remainderII;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractII;

import java.util.List;
import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.compilation.ScopeTracking;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ForTask extends ScopedNumberProducingDataflowTask<IntVariable> {

    public final IntVariable start, end, step;
    public final boolean incrementing;
    protected IntVariable index;

    public ForTask(IntVariable start, IntVariable end, IntVariable step, boolean incrementing) {
        this(start, end, step, incrementing, null);
    }

    public ForTask(IntVariable start, IntVariable end, IntVariable step, boolean incrementing, Location location) {
        this(DFType.FOR, start, end, step, incrementing, location);
    }

    protected ForTask(DFType dfType, IntVariable start, IntVariable end, IntVariable step, boolean incrementing,
            Location location) {
        super(dfType, VariableType.IntVariable, location, start, end, step);
        this.start = start;
        this.end = end;
        this.step = step;
        this.incrementing = incrementing;
    }

    @Override
    public void testTask(List<SandwoodModelException> errors) {
        if(start.isDistribution())
            errors.add(new SandwoodModelException("Initial value of for loop cannot be a distribution.", this));
        else if(!start.isDeterministic())
            errors.add(new SandwoodModelException("Initial value of for loop must be deterministic.", this));

        if(end.isDistribution())
            errors.add(new SandwoodModelException("End value of for loop cannot be a distribution.", this));
        else if(!end.isDeterministic())
            errors.add(new SandwoodModelException("End value of for loop must be deterministic.", this));

        if(step.isDistribution())
            errors.add(new SandwoodModelException("Increment value of for loop cannot be a distribution.", this));
        else if(!step.isDeterministic())
            errors.add(new SandwoodModelException("Increment value of for loop must be deterministic.", this));
    }

    @Override
    public void setOutput(IntVariable output) {
        super.setOutput(output);
        index = output;
        output.setPrivate();
    }

    public IntVariable getIndex() {
        return index;
    }

    public IntVariable getStart() {
        return start;
    }

    public IntVariable getEnd() {
        return end;
    }

    public IntVariable getStep() {
        return step;
    }

    @Override
    public boolean iterating() {
        return true;
    }

    @Override
    public int getSandwoodCodePrefix(StringBuilder sb, int indent, boolean compressSandwoodCode) {
        return indent;
    }

    @Override
    public int getSandwoodCodePostfix(StringBuilder sb, int indent) {
        addIndent(sb, --indent);
        sb.append("}\n");
        return indent;
    }

    @Override
    public int getSandwoodCode(StringBuilder sb, int indent, Map<Variable<?>, Boolean> inlineableVariables,
            boolean compressSandwoodCode) {
        addIndent(sb, indent++);
        sb.append(getSandwoodString(compressSandwoodCode) + "\n");
        return indent;
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return getSandwoodExpression(compressSandwoodCode);
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        IntVariable index = getOutput();
        if(incrementing)
            return "for(" + index.getType().getJavaType() + " " + index.getVarDesc() + "="
                    + start.getExpression(compressSandwoodCode) + "; " + index.getVarDesc() + " < "
                    + end.getExpression(compressSandwoodCode) + "; " + index.getVarDesc() + " += "
                    + step.getExpression(compressSandwoodCode) + ") {";
        else
            return "for(" + index.getType().getJavaType() + " " + index.getVarDesc() + "="
                    + start.getExpression(compressSandwoodCode) + "; " + index.getVarDesc() + " > "
                    + end.getExpression(compressSandwoodCode) + "; " + index.getVarDesc() + " -= "
                    + step.getExpression(compressSandwoodCode) + ") {";
    }

    @Override
    public IRTreeReturn<IntVariable> getForwardIRinternal(CompilationContext compilationCtx) {
        compilationCtx.touchScope(this);
        return IRTree.load(index);
    }

    @Override
    public IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    @Override
    public String checkInversionError(int argPos) {
        return "It is not possible to construct an inverse function that goes via a for loops arguments "
                + "as information is lost when the index is created.";
    }

    // TODO Now that reversing of scopes has been added in, remove the inverse transformer and construct
    // the reversed Gibbs tree using this mechanism instead.
    @Override
    public IRTreeVoid getScopeTree(ScopeTracking scopeTracking, IRTreeVoid tree, boolean reverseScopes,
            CompilationContext compilationCtx) {
        IRTreeReturn<IntVariable> startTree = start.getForwardIR(compilationCtx);
        IRTreeReturn<IntVariable> endTree = end.getForwardIR(compilationCtx);
        IRTreeReturn<IntVariable> stepTree = step.getForwardIR(compilationCtx);
        VariableDescription<IntVariable> indexDesc = index.getUniqueVarDesc();
        if(reverseScopes) {
            // End must be updated first as it depends on the current start value.
            endTree = updateEnd(startTree, endTree, stepTree, incrementing);
            startTree = updateStart(startTree, incrementing);
            return forStmt(tree, endTree, startTree, stepTree, indexDesc, !incrementing, Tree.NoComment);
        } else
            return forStmt(tree, startTree, endTree, stepTree, indexDesc, incrementing, Tree.NoComment);
    }

    /**
     * Transform the start to make sure that the loop will end on the value it would have started on normally.
     * 
     * @param start
     * @param incrementing
     * @return
     */

    protected IRTreeReturn<IntVariable> updateStart(IRTreeReturn<IntVariable> start, boolean incrementing) {
        if(incrementing)// will be decrementing after the transformation
            return subtractII(start, constant(1));
        else
            return addII(start, constant(1));
    }

    /**
     * Transform the end to make sure that the loop will start on the value it would have finished on normally.
     * 
     * @param start
     * @param end
     * @param step
     * @param incrementing
     * @return
     */
    protected IRTreeReturn<IntVariable> updateEnd(IRTreeReturn<IntVariable> start, IRTreeReturn<IntVariable> end,
            IRTreeReturn<IntVariable> step, boolean incrementing) {
        if(incrementing)
            return subtractII(end,
                    addII(remainderII(subtractII(subtractII(end, constant(1)), start), step), constant(1)));
        else
            return addII(end, addII(remainderII(subtractII(start, addII(end, constant(1))), step), constant(1)));
    }

    @Override
    public String getDescription() {
        return "For " + id();
    }

    public IRTreeReturn<IntVariable> getMaxValue(CompilationContext compilationCtx) {
        if(incrementing)
            return end.subtract(Variable.intVariable(1)).getForwardIR(compilationCtx);
        else
            return start.getForwardIR(compilationCtx);
    }

    @Override
    public ScopeType getScopeType() {
        return ScopeType.FOR;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ForTask t = (ForTask) other;
        return incrementing == t.incrementing && start.equivalent(t.start) && end.equivalent(t.end)
                && step.equivalent(t.step);
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
        Scope scope = this.getEnclosingScope();
        sb.append("for(" + getIndex().getVarDesc() + ") id " + id() + "\n" + scope.toString(i + 1));
        return sb.toString();
    }

    @Override
    public boolean isSerial(CompilationContext compilationCtx) {
        return true;
    }

    @Override
    public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
        if(incrementing)
            return IRTree.subtractII(getEnd().getMax(compilationCtx), IRTree.constant(1));
        else
            return getStart().getMax(compilationCtx);
    }

    @Override
    public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
        if(incrementing)
            return getStart().getMin(compilationCtx);
        else
            return getEnd().getMin(compilationCtx);
    }
}
