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

public abstract class Subtract<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>>
        extends NumberProducingDataflowTaskImplementation<C> {

    public final A left;
    public final B right;

    private Subtract(VariableType.Type<C> outputType, Location location, A left, B right) {
        super(DFType.SUBTRACTION, outputType, location, left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        // Excessive bracketing to be sure
        return "(" + left.getExpression(compressSandwoodCode) + " - " + right.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Subtract<?, ?, ?> dft = (Subtract<?, ?, ?>) other;
        return left.equivalent(dft.left) && right.equivalent(dft.right);
    }

    private static class SubtractDD extends Subtract<DoubleVariable, DoubleVariable, DoubleVariable> {

        SubtractDD(DoubleVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.subtractDD(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.addDD(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.subtractDD(left.getForwardIR(compilationCtx), taskOutput);
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.subtractDD(left.getMax(compilationCtx), right.getMin(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.subtractDD(left.getMin(compilationCtx), right.getMax(compilationCtx));
        }
    }

    private static class SubtractDI extends Subtract<DoubleVariable, IntVariable, DoubleVariable> {

        SubtractDI(DoubleVariable a, IntVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.subtractDI(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.addDI(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.castToInteger(IRTree.subtractDD(left.getForwardIR(compilationCtx), taskOutput));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.subtractDI(left.getMax(compilationCtx), right.getMin(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.subtractDI(left.getMin(compilationCtx), right.getMax(compilationCtx));
        }
    }

    private static class SubtractID extends Subtract<IntVariable, DoubleVariable, DoubleVariable> {

        SubtractID(IntVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.subtractID(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.castToInteger(IRTree.addDD(taskOutput, right.getForwardIR(compilationCtx)));
                }

                case 1: {
                    return IRTree.subtractID(left.getForwardIR(compilationCtx), taskOutput);
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.subtractID(left.getMax(compilationCtx), right.getMin(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.subtractID(left.getMin(compilationCtx), right.getMax(compilationCtx));
        }
    }

    private static class SubtractII extends Subtract<IntVariable, IntVariable, IntVariable> {

        SubtractII(IntVariable a, IntVariable b, Location location) {
            super(VariableType.IntVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, IntVariable, IntVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.subtractII(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.addII(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.subtractII(left.getForwardIR(compilationCtx), taskOutput);
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.subtractII(left.getMax(compilationCtx), right.getMin(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.subtractII(left.getMin(compilationCtx), right.getMax(compilationCtx));
        }
    }

    /* Factory methods for construction */
    public static DoubleVariable subtract(DoubleVariable a, int i) {
        return subtract(a, Variable.intVariable(i));
    }

    public static DoubleVariable subtract(DoubleVariable a, int i, Location location) {
        return subtract(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable subtract(DoubleVariable a, double d) {
        return subtract(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable subtract(DoubleVariable a, double d, Location location) {
        return subtract(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable subtract(DoubleVariable a, DoubleVariable b) {
        return subtract(a, b, null);
    }

    public static DoubleVariable subtract(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new SubtractDD(a, b, location));
    }

    public static DoubleVariable subtract(DoubleVariable a, IntVariable b) {
        return subtract(a, b, null);
    }

    public static DoubleVariable subtract(DoubleVariable a, IntVariable b, Location location) {
        return DoubleVariable.doubleVariable(new SubtractDI(a, b, location));
    }

    public static IntVariable subtract(IntVariable a, int i) {
        return subtract(a, Variable.intVariable(i));
    }

    public static IntVariable subtract(IntVariable a, int i, Location location) {
        return subtract(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable subtract(IntVariable a, double d) {
        return subtract(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable subtract(IntVariable a, double d, Location location) {
        return subtract(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable subtract(IntVariable a, DoubleVariable b) {
        return subtract(a, b, null);
    }

    public static DoubleVariable subtract(IntVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new SubtractID(a, b, location));
    }

    public static IntVariable subtract(IntVariable a, IntVariable b) {
        return subtract(a, b, null);
    }

    public static IntVariable subtract(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new SubtractII(a, b, location));
    }
}