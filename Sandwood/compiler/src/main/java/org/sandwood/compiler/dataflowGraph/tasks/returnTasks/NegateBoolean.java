/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.ProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRNegateBoolean;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public class NegateBoolean extends ProducingDataflowTaskImplementation<BooleanVariable> {

    public final BooleanVariable b;

    private NegateBoolean(BooleanVariable b, Location location) {
        super(DFType.NEGATE_BOOLEAN, VariableType.BooleanVariable, location, b);
        this.b = b;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "!" + b.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        NegateBoolean dft = (NegateBoolean) other;
        return b.equivalent(dft.b);
    }

    @Override
    public IRNegateBoolean getForwardIRinternal(CompilationContext compilationCtx) {
        return IRTree.negateBoolean(b.getForwardIR(compilationCtx));
    }

    @Override
    public IRTreeReturn<BooleanVariable> getInverseIRInternal(int argPos, IRTreeReturn<BooleanVariable> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        return IRTree.negateBoolean(taskOutput);
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    /* Factory methods for construction */
    public static BooleanVariable negate(boolean b) {
        return negate(Variable.booleanVariable(b), null);
    }

    public static BooleanVariable negate(boolean b, Location location) {
        return negate(Variable.booleanVariable(b), location);
    }

    public static BooleanVariable negate(BooleanVariable b) {
        return negate(b, null);
    }

    public static BooleanVariable negate(BooleanVariable b, Location location) {
        return BooleanVariable.booleanVariable(new NegateBoolean(b, location));
    }
}