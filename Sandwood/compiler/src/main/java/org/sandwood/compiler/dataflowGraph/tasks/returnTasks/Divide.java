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
import static org.sandwood.compiler.trees.irTree.IRTree.max;
import static org.sandwood.compiler.trees.irTree.IRTree.min;
import static org.sandwood.compiler.trees.irTree.IRTree.negate;

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

public abstract class Divide<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>>
        extends NumberProducingDataflowTaskImplementation<C> {

    public final A left;
    public final B right;

    Divide(VariableType.Type<C> outputType, Location location, A left, B right) {
        super(DFType.DIVISION, outputType, location, left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " / " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Divide<?, ?, ?> dft = (Divide<?, ?, ?>) other;
        return left.equivalent(dft.left) && right.equivalent(dft.right);
    }

    /**
     * A helper method for building trees for min max calculations
     * 
     * @param <R>      The type of the result
     * @param <G>      The type of the values used in the guards
     * @param min      The minimum value that can be seen
     * @param max      The maximum value that can be seen
     * @param positive The result to return if all the values are positive
     * @param negative The result to return if all the values are negative
     * @param both     The results to return if the values can be positive and negative
     * @return The result
     */
    protected <G extends NumberVariable<G>> IRTreeReturn<C> rangeTree(IRTreeReturn<G> min, IRTreeReturn<G> max,
            IRTreeReturn<C> positive, IRTreeReturn<C> negative, IRTreeReturn<C> both) {
        IRTreeReturn<DoubleVariable> zero = constant(0.0);

        return conditionalAssignment(lessThanEqual(zero, min),
                // Is positive
                positive, conditionalAssignment(lessThan(max, zero),
                        // Is negative
                        negative,
                        // Is both positive and negative
                        both));
    }

    private interface Operation<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>> {
        IRTreeReturn<C> apply(IRTreeReturn<A> left, IRTreeReturn<B> right);
    }

    protected IRTreeReturn<C> getMaxInternal(Operation<A, B, C> op, CompilationContext compilationCtx) {
        IRTreeReturn<A> leftMin = left.getMin(compilationCtx);
        IRTreeReturn<A> leftMax = left.getMax(compilationCtx);
        IRTreeReturn<B> rightMin = right.getMin(compilationCtx);
        IRTreeReturn<B> rightMax = right.getMax(compilationCtx);

        return rangeTree(leftMin, leftMax,
                // Non-negative left
                rangeTree(rightMin, rightMax, op.apply(leftMax, rightMin), op.apply(leftMin, rightMin),
                        (IRTreeReturn<C>) constant(Double.POSITIVE_INFINITY)),
                // Negative left
                rangeTree(rightMin, rightMax, op.apply(leftMax, rightMax), op.apply(leftMin, rightMax),
                        (IRTreeReturn<C>) constant(Double.POSITIVE_INFINITY)),
                // Positive or negative left
                rangeTree(rightMin, rightMax, op.apply(leftMax, rightMin), op.apply(leftMin, rightMax),
                        (IRTreeReturn<C>) constant(Double.POSITIVE_INFINITY)));
    }

    protected IRTreeReturn<C> getMinInternal(Operation<A, B, C> op, CompilationContext compilationCtx) {
        IRTreeReturn<A> leftMin = left.getMin(compilationCtx);
        IRTreeReturn<A> leftMax = left.getMax(compilationCtx);
        IRTreeReturn<B> rightMin = right.getMin(compilationCtx);
        IRTreeReturn<B> rightMax = right.getMax(compilationCtx);

        return rangeTree(leftMin, leftMax,
                // Non-negative left
                rangeTree(rightMin, rightMax, op.apply(leftMin, rightMax), op.apply(leftMax, rightMax),
                        op.apply(leftMax, rightMax)),
                // Negative left
                rangeTree(rightMin, rightMax, op.apply(leftMin, rightMin), op.apply(leftMax, rightMin),
                        (IRTreeReturn<C>) constant(Double.NEGATIVE_INFINITY)),
                // Positive or negative left
                rangeTree(rightMin, rightMax, op.apply(leftMin, rightMin), op.apply(leftMax, rightMin),
                        (IRTreeReturn<C>) constant(Double.NEGATIVE_INFINITY)));
    }

    private static class DivideDD extends Divide<DoubleVariable, DoubleVariable, DoubleVariable> {

        DivideDD(DoubleVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.divideDD(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.multiplyDD(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.divideDD(left.getForwardIR(compilationCtx), taskOutput);
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::divideDD, compilationCtx);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::divideDD, compilationCtx);
        }
    }

    private static class DivideDI extends Divide<DoubleVariable, IntVariable, DoubleVariable> {

        DivideDI(DoubleVariable a, IntVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.divideDI(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.multiplyDI(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.castToInteger(IRTree.divideDD(left.getForwardIR(compilationCtx), taskOutput));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::divideDI, compilationCtx);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::divideDI, compilationCtx);
        }
    }

    private static class DivideID extends Divide<IntVariable, DoubleVariable, DoubleVariable> {

        DivideID(IntVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.divideID(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.castToInteger(IRTree.multiplyDD(taskOutput, right.getForwardIR(compilationCtx)));
                }

                case 1: {
                    return IRTree.divideID(left.getForwardIR(compilationCtx), taskOutput);
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return getMaxInternal(IRTree::divideID, compilationCtx);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return getMinInternal(IRTree::divideID, compilationCtx);
        }
    }

    private static class DivideII extends Divide<IntVariable, IntVariable, IntVariable> {
        DivideII(IntVariable a, IntVariable b, Location location) {
            super(VariableType.IntVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, IntVariable, IntVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.divideII(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.multiplyII(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.divideII(left.getForwardIR(compilationCtx), taskOutput);
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
            IRTreeReturn<IntVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<IntVariable> leftMax = left.getMax(compilationCtx);
            IRTreeReturn<IntVariable> rightMin = right.getMin(compilationCtx);
            IRTreeReturn<IntVariable> rightMax = right.getMax(compilationCtx);

            // These assume that there will not be a divide by zero possibility as that would be an error
            // in the model
            return rangeTree(leftMin, leftMax,
                    // Non-negative left
                    rangeTree(rightMin, rightMax, IRTree.divideII(leftMax, rightMin),
                            IRTree.divideII(leftMin, rightMin), leftMax),
                    // Negative left
                    rangeTree(rightMin, rightMax, IRTree.divideII(leftMax, rightMax),
                            IRTree.divideII(leftMin, rightMax), negate(leftMin)),
                    // Positive or negative left
                    rangeTree(rightMin, rightMax, IRTree.divideII(leftMax, rightMin),
                            IRTree.divideII(leftMin, rightMax), max(leftMax, negate(leftMin))));
        }

        @Override
        public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
            IRTreeReturn<IntVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<IntVariable> leftMax = left.getMax(compilationCtx);
            IRTreeReturn<IntVariable> rightMin = right.getMin(compilationCtx);
            IRTreeReturn<IntVariable> rightMax = right.getMax(compilationCtx);

            // These assume that there will not be a divide by zero possibility as that would be an error
            // in the model
            return rangeTree(leftMin, leftMax,
                    // Non-negative left
                    rangeTree(rightMin, rightMax, IRTree.divideII(leftMin, rightMax),
                            IRTree.divideII(leftMax, rightMax), negate(leftMax)),
                    // Negative left
                    rangeTree(rightMin, rightMax, IRTree.divideII(leftMin, rightMin),
                            IRTree.divideII(leftMax, rightMin), leftMin),
                    // Positive or negative left
                    rangeTree(rightMin, rightMax, IRTree.divideII(leftMin, rightMin),
                            IRTree.divideII(leftMax, rightMin), min(negate(leftMax), leftMin)));
        }
    }

    /* Factory methods for construction */
    public static DoubleVariable divide(DoubleVariable a, int i) {
        return divide(a, Variable.intVariable(i));
    }

    public static DoubleVariable divide(DoubleVariable a, int i, Location location) {
        return divide(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable divide(DoubleVariable a, double d) {
        return divide(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable divide(DoubleVariable a, double d, Location location) {
        return divide(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable divide(DoubleVariable a, DoubleVariable b) {
        return divide(a, b, null);
    }

    public static DoubleVariable divide(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new DivideDD(a, b, location));
    }

    public static DoubleVariable divide(DoubleVariable a, IntVariable b) {
        return divide(a, b, null);
    }

    public static DoubleVariable divide(DoubleVariable a, IntVariable b, Location location) {
        return DoubleVariable.doubleVariable(new DivideDI(a, b, location));
    }

    public static IntVariable divide(IntVariable a, int i) {
        return divide(a, Variable.intVariable(i));
    }

    public static IntVariable divide(IntVariable a, int i, Location location) {
        return divide(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable divide(IntVariable a, double d) {
        return divide(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable divide(IntVariable a, double d, Location location) {
        return divide(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable divide(IntVariable a, DoubleVariable b) {
        return divide(a, b, null);
    }

    public static DoubleVariable divide(IntVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new DivideID(a, b, location));
    }

    public static IntVariable divide(IntVariable a, IntVariable b) {
        return divide(a, b, null);
    }

    public static IntVariable divide(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new DivideII(a, b, location));
    }
}