/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.castToDouble;
import static org.sandwood.compiler.trees.irTree.IRTree.conditionalAssignment;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThan;
import static org.sandwood.compiler.trees.irTree.IRTree.max;
import static org.sandwood.compiler.trees.irTree.IRTree.min;
import static org.sandwood.compiler.trees.irTree.IRTree.negate;
import static org.sandwood.compiler.trees.irTree.IRTree.subtractII;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRBinOp;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class Remainder<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>>
        extends NumberProducingDataflowTaskImplementation<C> {

    public final A left;
    public final B right;

    Remainder(VariableType.Type<C> outputType, Location location, A left, B right) {
        super(DFType.REMAINDER, outputType, location, left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public String checkInversionError(int argPos) {
        return "There is no inverse of a remainder operation.";
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " % " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Remainder<?, ?, ?> dft = (Remainder<?, ?, ?>) other;
        return left.equivalent(dft.left) && right.equivalent(dft.right);
    }

    private static class RemainderDD extends Remainder<DoubleVariable, DoubleVariable, DoubleVariable> {

        RemainderDD(DoubleVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.remainderDD(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            throw new CompilerException(checkInversionError(argPos));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            min(left.getMax(compilationCtx), right.getMax(compilationCtx)),
                            min(left.getMax(compilationCtx), negate(right.getMin(compilationCtx)))),
                    constant(0.0));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)), constant(0.0),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            max(left.getMin(compilationCtx), negate(right.getMax(compilationCtx))),
                            max(left.getMin(compilationCtx), right.getMin(compilationCtx))));
        }
    }

    private static class RemainderDI extends Remainder<DoubleVariable, IntVariable, DoubleVariable> {

        RemainderDI(DoubleVariable a, IntVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.remainderDI(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            throw new CompilerException(checkInversionError(argPos));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            min(left.getMax(compilationCtx), castToDouble(right.getMax(compilationCtx))),
                            min(left.getMax(compilationCtx), castToDouble(negate(right.getMin(compilationCtx))))),
                    constant(0.0));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)), constant(0.0),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            max(left.getMin(compilationCtx), castToDouble(negate(right.getMax(compilationCtx)))),
                            max(left.getMin(compilationCtx), castToDouble(right.getMin(compilationCtx)))));
        }
    }

    private static class RemainderID extends Remainder<IntVariable, DoubleVariable, DoubleVariable> {

        RemainderID(IntVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.remainderID(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            throw new CompilerException(checkInversionError(argPos));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            min(castToDouble(left.getMax(compilationCtx)), right.getMax(compilationCtx)),
                            min(castToDouble(left.getMax(compilationCtx)), negate(right.getMin(compilationCtx)))),
                    constant(0.0));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)), constant(0.0),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            max(castToDouble(left.getMin(compilationCtx)), negate(right.getMax(compilationCtx))),
                            max(castToDouble(left.getMin(compilationCtx)), right.getMin(compilationCtx))));
        }
    }

    private static class RemainderII extends Remainder<IntVariable, IntVariable, IntVariable> {
        RemainderII(IntVariable a, IntVariable b, Location location) {
            super(VariableType.IntVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, IntVariable, IntVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.remainderII(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            throw new CompilerException(checkInversionError(argPos));
        }

        @Override
        public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            min(left.getMax(compilationCtx), subtractII(right.getMax(compilationCtx), constant(1))),
                            min(left.getMax(compilationCtx),
                                    subtractII(negate(right.getMin(compilationCtx)), constant(1)))),
                    constant(0));
        }

        @Override
        public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
            return conditionalAssignment(lessThan(constant(0), left.getForwardIR(compilationCtx)), constant(0),
                    conditionalAssignment(lessThan(constant(0), right.getForwardIR(compilationCtx)),
                            max(left.getMin(compilationCtx), subtractII(constant(1), right.getMax(compilationCtx))),
                            max(left.getMin(compilationCtx), addII(constant(1), right.getMin(compilationCtx)))));
        }
    }

    /* Factory methods for construction */
    public static DoubleVariable remainder(DoubleVariable a, int i) {
        return remainder(a, Variable.intVariable(i));
    }

    public static DoubleVariable remainder(DoubleVariable a, int i, Location location) {
        return remainder(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable remainder(DoubleVariable a, double d) {
        return remainder(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable remainder(DoubleVariable a, double d, Location location) {
        return remainder(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable remainder(DoubleVariable a, DoubleVariable b) {
        return remainder(a, b, null);
    }

    public static DoubleVariable remainder(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new RemainderDD(a, b, location));
    }

    public static DoubleVariable remainder(DoubleVariable a, IntVariable b) {
        return remainder(a, b, null);
    }

    public static DoubleVariable remainder(DoubleVariable a, IntVariable b, Location location) {
        return DoubleVariable.doubleVariable(new RemainderDI(a, b, location));
    }

    public static IntVariable remainder(IntVariable a, int i) {
        return remainder(a, Variable.intVariable(i));
    }

    public static IntVariable remainder(IntVariable a, int i, Location location) {
        return remainder(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable remainder(IntVariable a, double d) {
        return remainder(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable remainder(IntVariable a, double d, Location location) {
        return remainder(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable remainder(IntVariable a, DoubleVariable b) {
        return remainder(a, b, null);
    }

    public static DoubleVariable remainder(IntVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new RemainderID(a, b, location));
    }

    public static IntVariable remainder(IntVariable a, IntVariable b) {
        return remainder(a, b, null);
    }

    public static IntVariable remainder(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new RemainderII(a, b, location));
    }
}