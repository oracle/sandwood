/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class IfElseAssignmentTask<A extends Variable<A>> extends ProducingDataflowTaskImplementation<A> {

    public final BooleanVariable guard;
    public final A ifValue, elseValue;

    public IfElseAssignmentTask(BooleanVariable guard, A ifValue, A elseValue, Location location) {
        super(DFType.IF_ASSIGNMENT, ifValue.getType(), location, guard, ifValue, elseValue);
        this.guard = guard;
        this.ifValue = ifValue;
        this.elseValue = elseValue;
    }

    @Override
    public String checkInversionError(int argPos) {
        if(argPos == 0)
            return "Inverse functions cannot be created for guards as both the \"if\" and the \"else\" assignments could be the same.";
        else
            return null;
    }

    @Override
    protected IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<A> taskOutput, BackTraceInfo backTraceInfo,
            CompilationContext compilationCtx) {
        switch(argPos) {
            case 0:
                throw new CompilerException(checkInversionError(argPos));
            case 1:
            case 2:
                return taskOutput;
            default:
                throw new CompilerException("Argument position " + argPos + " is outside of the range 0 - 2.");
        }
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return " = (" + guard.getExpression(compressSandwoodCode) + ")?" + ifValue.getExpression(compressSandwoodCode)
                + ":" + elseValue.getExpression(compressSandwoodCode) + ";\n";
    }

    @Override
    protected IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        A output = getOutput();
        VariableDescription<A> outputDesc = output.getUniqueVarDesc();

        Scope ifScope = ifValue.scope();
        compilationCtx.enterScope(ifScope);
        if(!output.isIntermediate()) {
            if(compilationCtx.codeGuardSet()) {
                // If the is a guard set for the code placed into scopes, the variable must be
                // initialised to keep the compiler happy.
                Type<?> type = output.getType();
                if(type == VariableType.IntVariable) {
                    compilationCtx.addTreeToScope(output.scope(), IRTree.initializeVariable(outputDesc,
                            (IRTreeReturn<A>) IRTree.constant(0), Tree.NoComment));
                } else if(type == VariableType.DoubleVariable) {
                    compilationCtx.addTreeToScope(output.scope(), IRTree.initializeVariable(outputDesc,
                            (IRTreeReturn<A>) IRTree.constant(0.0), Tree.NoComment));
                } else if(type == VariableType.BooleanVariable) {
                    compilationCtx.addTreeToScope(output.scope(), IRTree.initializeVariable(outputDesc,
                            (IRTreeReturn<A>) IRTree.constant(false), Tree.NoComment));
                } else {
                    throw new CompilerException("Unknown type");
                }
            } else {
                compilationCtx.addTreeToScope(output.scope(),
                        IRTree.initializeUnsetVariable(outputDesc, Tree.NoComment));
            }

        }
        compilationCtx.addTreeToScope(ifScope,
                IRTree.store(outputDesc, ifValue.getForwardIR(compilationCtx), Tree.NoComment));
        compilationCtx.leaveScope(ifScope);

        Scope elseScope = elseValue.scope();
        compilationCtx.enterScope(elseScope);
        compilationCtx.addTreeToScope(elseScope,
                IRTree.store(outputDesc, elseValue.getForwardIR(compilationCtx), Tree.NoComment));
        compilationCtx.leaveScope(elseScope);

        return IRTree.load(outputDesc);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        IfElseAssignmentTask<?> otherIfElse = (IfElseAssignmentTask<?>) other;
        if(!guard.equivalent(otherIfElse.guard))
            return false;
        if(!ifValue.equivalent(otherIfElse.ifValue))
            return false;
        if(!elseValue.equivalent(otherIfElse.elseValue))
            return false;
        return true;
    }
}
