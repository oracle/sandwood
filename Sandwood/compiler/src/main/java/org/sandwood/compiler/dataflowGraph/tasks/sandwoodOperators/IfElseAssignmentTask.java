/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.ElseScope;
import org.sandwood.compiler.dataflowGraph.scopes.IfScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope.ScopeType;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.LocalVariableDescription;
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
    public final IfScope ifScope;

    public IfElseAssignmentTask(BooleanVariable guard, A ifValue, A elseValue, IfScope ifScope, Location location) {
        super(DFType.IF_ASSIGNMENT, ifValue.getType(), location, guard, ifValue, elseValue);
        this.guard = guard;
        this.ifValue = ifValue;
        this.elseValue = elseValue;
        this.ifScope = ifScope;
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
        return "((" + guard.getExpression(compressSandwoodCode) + ")?" + ifValue.getExpression(compressSandwoodCode)
                + ":" + elseValue.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    protected IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        A output = getOutput();

        if(compilationCtx.initializedInScope(output)) {
            VariableDescription<A> outputDesc = compilationCtx.getInitializedVariable(output).getUniqueVarDesc();
            return IRTree.load(outputDesc);
        } else {
            Variable<A> outputInit = compilationCtx.addInitialized(output);
            VariableDescription<A> outputDesc = outputInit.getUniqueVarDesc();
            // If is not substituted to a non conditional location
            Scope newScope = compilationCtx.getTargetScope(ifScope);
            if(newScope.getScopeType() == ScopeType.IF && ifScope.guard == ((IfScope) newScope).guard) {
                // Else is not substituted to a non conditional location
                newScope = compilationCtx.getTargetScope(ifScope.elseScope);
                if(newScope.getScopeType() == ScopeType.ELSE && ifScope.guard == ((ElseScope) newScope).ifScope.guard) {
                    compilationCtx.enterScope(ifScope);
                    if(!output.isIntermediate()) {
                        LocalVariableDescription<A> localOutputDesc = (LocalVariableDescription<A>) outputDesc;
                        if(compilationCtx.codeGuardSet()) {
                            // If the is a guard set for the code placed into scopes, the variable must be
                            // initialised to keep the compiler happy.
                            Type<?> type = output.getType();
                            if(type == VariableType.IntVariable) {
                                compilationCtx.addTreeToScope(output.scope(), IRTree.initializeVariable(localOutputDesc,
                                        (IRTreeReturn<A>) IRTree.constant(0), Tree.NoComment));
                            } else if(type == VariableType.DoubleVariable) {
                                compilationCtx.addTreeToScope(output.scope(), IRTree.initializeVariable(localOutputDesc,
                                        (IRTreeReturn<A>) IRTree.constant(0.0), Tree.NoComment));
                            } else if(type == VariableType.BooleanVariable) {
                                compilationCtx.addTreeToScope(output.scope(), IRTree.initializeVariable(localOutputDesc,
                                        (IRTreeReturn<A>) IRTree.constant(false), Tree.NoComment));
                            } else {
                                throw new CompilerException("Unknown type");
                            }
                        } else {
                            compilationCtx.addTreeToScope(output.scope(),
                                    IRTree.initializeUnsetVariable(localOutputDesc, Tree.NoComment));
                        }
                    }

                    compilationCtx.addTreeToScope(ifScope,
                            IRTree.store(outputDesc, ifValue.getForwardIR(compilationCtx), Tree.NoComment));
                    compilationCtx.leaveScope(ifScope);

                    ElseScope elseScope = ifScope.elseScope;
                    compilationCtx.enterScope(elseScope);
                    compilationCtx.addTreeToScope(elseScope,
                            IRTree.store(outputDesc, elseValue.getForwardIR(compilationCtx), Tree.NoComment));
                    compilationCtx.leaveScope(elseScope);
                } else {
                    // "Else" is a non conditional location, so the value should be set directly
                    if(output.isIntermediate())
                        compilationCtx.addTreeToScope(output.scope(),
                                IRTree.store(outputDesc, elseValue.getForwardIR(compilationCtx), Tree.NoComment));
                    else
                        compilationCtx.addTreeToScope(output.scope(),
                                IRTree.initializeVariable((LocalVariableDescription<A>) outputDesc,
                                        elseValue.getForwardIR(compilationCtx), Tree.NoComment));
                }
            } else {
                // "If" is a non conditional location and should be set directly
                if(output.isIntermediate())
                    compilationCtx.addTreeToScope(output.scope(),
                            IRTree.store(outputDesc, ifValue.getForwardIR(compilationCtx), Tree.NoComment));
                else
                    compilationCtx.addTreeToScope(output.scope(),
                            IRTree.initializeVariable((LocalVariableDescription<A>) outputDesc,
                                    ifValue.getForwardIR(compilationCtx), Tree.NoComment));
            }
            return IRTree.load(outputDesc);
        }
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