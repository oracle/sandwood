/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import static org.sandwood.compiler.trees.irTree.IRTree.conditionalAssignment;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThan;
import static org.sandwood.compiler.trees.irTree.IRTree.lessThanEqual;

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

public abstract class Multiply<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>>
        extends NumberProducingDataflowTaskImplementation<C> {

    public final A left;
    public final B right;

    private Multiply(VariableType.Type<C> outputType, Location location, A left, B right) {
        super(DFType.MULTIPLICATION, outputType, location, left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " * " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        Multiply<?, ?, ?> dft = (Multiply<?, ?, ?>) other;
        return (left.equivalent(dft.left) && right.equivalent(dft.right))
                || (left.equivalent(dft.right) && right.equivalent(dft.left));
    }

    private interface Operation<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>> {
        IRTreeReturn<C> apply(IRTreeReturn<A> left, IRTreeReturn<B> right);
    }

    protected IRTreeReturn<C> getMaxInternal(Operation<A, B, C> op, CompilationContext compilationCtx) {
        IRTreeReturn<A> leftMin = left.getMin(compilationCtx);
        IRTreeReturn<A> leftMax = left.getMax(compilationCtx);
        IRTreeReturn<B> rightMin = right.getMin(compilationCtx);
        IRTreeReturn<B> rightMax = right.getMax(compilationCtx);

        IRTreeReturn<C> maxMax = op.apply(leftMax, rightMax);
        IRTreeReturn<C> minMin = op.apply(leftMin, rightMin);
        IRTreeReturn<C> maxMinMax = IRTree.max(maxMax, minMin);

        IRTreeReturn<DoubleVariable> zero = constant(0.0);

        return conditionalAssignment(lessThanEqual(zero, leftMin),
                // Left is positive
                maxMax, conditionalAssignment(lessThan(leftMax, zero),
                        // Left is negative
                        minMin, conditionalAssignment(lessThanEqual(zero, rightMin),
                                // Right is positive
                                maxMax, conditionalAssignment(lessThan(rightMax, zero),
                                        // Right is negative
                                        minMin,
                                        // They both span positive and negative values so the larger of two values
                                        // must be taken.
                                        maxMinMax))));
    }

    protected IRTreeReturn<C> getMinInternal(Operation<A, B, C> op, CompilationContext compilationCtx) {
        IRTreeReturn<A> leftMin = left.getMin(compilationCtx);
        IRTreeReturn<A> leftMax = left.getMax(compilationCtx);
        IRTreeReturn<B> rightMin = right.getMin(compilationCtx);
        IRTreeReturn<B> rightMax = right.getMax(compilationCtx);

        IRTreeReturn<C> maxMax = op.apply(leftMax, rightMax);
        IRTreeReturn<C> minMin = op.apply(leftMin, rightMin);
        IRTreeReturn<C> minMinMax = IRTree.min(maxMax, minMin);

        IRTreeReturn<DoubleVariable> zero = constant(0.0);

        return conditionalAssignment(lessThanEqual(zero, leftMin),
                // Left is positive
                minMin, conditionalAssignment(lessThan(leftMax, zero),
                        // Left is negative
                        maxMax, conditionalAssignment(lessThanEqual(zero, rightMin),
                                // Right is positive
                                minMin, conditionalAssignment(lessThan(rightMax, zero),
                                        // Right is negative
                                        maxMax,
                                        // They both span positive and negative values so the larger of two values
                                        // must be taken.
                                        minMinMax))));
    }

    private static class MultiplyDD extends Multiply<DoubleVariable, DoubleVariable, DoubleVariable> {

        MultiplyDD(DoubleVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.multiplyDD(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.divideDD(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.divideDD(taskOutput, left.getForwardIR(compilationCtx));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::multiplyDD, compilationCtx);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::multiplyDD, compilationCtx);
        }
    }

    private static class MultiplyDI extends Multiply<DoubleVariable, IntVariable, DoubleVariable> {

        MultiplyDI(DoubleVariable a, IntVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.multiplyDI(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.divideDI(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.castToInteger(IRTree.divideDD(taskOutput, left.getForwardIR(compilationCtx)));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::multiplyDI, compilationCtx);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::multiplyDI, compilationCtx);
        }
    }

    private static class MultiplyID extends Multiply<IntVariable, DoubleVariable, DoubleVariable> {

        MultiplyID(IntVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.multiplyID(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.castToInteger(IRTree.divideDD(taskOutput, right.getForwardIR(compilationCtx)));
                }

                case 1: {
                    return IRTree.divideDI(taskOutput, left.getForwardIR(compilationCtx));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::multiplyID, compilationCtx);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::multiplyID, compilationCtx);
        }
    }

    private static class MultiplyII extends Multiply<IntVariable, IntVariable, IntVariable> {

        MultiplyII(IntVariable a, IntVariable b, Location location) {
            super(VariableType.IntVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, IntVariable, IntVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.multiplyII(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.divideII(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.divideII(taskOutput, left.getForwardIR(compilationCtx));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::multiplyII, compilationCtx);
        }

        @Override
        public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::multiplyII, compilationCtx);
        }
    }

    /* Factory methods for construction */
    public static DoubleVariable multiply(DoubleVariable a, int i) {
        return multiply(a, Variable.intVariable(i));
    }

    public static DoubleVariable multiply(DoubleVariable a, int i, Location location) {
        return multiply(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable multiply(DoubleVariable a, double d) {
        return multiply(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable multiply(DoubleVariable a, double d, Location location) {
        return multiply(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable multiply(DoubleVariable a, DoubleVariable b) {
        return multiply(a, b, null);
    }

    public static DoubleVariable multiply(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new MultiplyDD(a, b, location));
    }

    public static DoubleVariable multiply(DoubleVariable a, IntVariable b) {
        return multiply(a, b, null);
    }

    public static DoubleVariable multiply(DoubleVariable a, IntVariable b, Location location) {
        return DoubleVariable.doubleVariable(new MultiplyDI(a, b, location));
    }

    public static IntVariable multiply(IntVariable a, int i) {
        return multiply(a, Variable.intVariable(i));
    }

    public static IntVariable multiply(IntVariable a, int i, Location location) {
        return multiply(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable multiply(IntVariable a, double d) {
        return multiply(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable multiply(IntVariable a, double d, Location location) {
        return multiply(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable multiply(IntVariable a, DoubleVariable b) {
        return multiply(a, b, null);
    }

    public static DoubleVariable multiply(IntVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new MultiplyID(a, b, location));
    }

    public static IntVariable multiply(IntVariable a, IntVariable b) {
        return multiply(a, b, null);
    }

    public static IntVariable multiply(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new MultiplyII(a, b, location));
    }
}