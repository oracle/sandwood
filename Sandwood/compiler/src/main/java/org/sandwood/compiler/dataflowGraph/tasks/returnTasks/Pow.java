/*
 * Sandwood
 *
 * Copyright (c) 2019-2026, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.NumberProducingDataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.guards.BackTraceInfo;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public abstract class Pow<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>>
        extends NumberProducingDataflowTaskImplementation<C> {

    public final A left;
    public final B right;

    Pow(VariableType.Type<C> outputType, Location location, A a, B b) {
        super(DFType.POW, outputType, location, a, b);
        this.left = a;
        this.right = b;
    }

    @Override
    public String checkInversionError(int argPos) {
        return "Power calculations are not invertible in the general case as the sign of the value being raised to the power is lost.";
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return "(" + left.getExpression(compressSandwoodCode) + "^" + right.getExpression(compressSandwoodCode) + ")";
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Pow<?, ?, ?> dft = (Pow<?, ?, ?>) other;
        return (left.equivalent(dft.left) && right.equivalent(dft.right));
    }

    @Override
    public final IRTreeReturn<?> getInverseIRInternal(int argPos, IRTreeReturn<C> taskOutput,
            BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
        throw new CompilerException(checkInversionError(argPos));
    }

    private static class PowDD extends Pow<DoubleVariable, DoubleVariable, DoubleVariable> {
        PowDD(DoubleVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.powDD(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<DoubleVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<DoubleVariable> leftMax = left.getMax(compilationCtx);
            if(leftMin == null || leftMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0.0)),
                    IRTree.lessThanEqual(IRTree.constant(0.0), leftMax),
                    IRTree.lessThan(right.getForwardIR(compilationCtx), IRTree.constant(0.0)));
            // min(0, minLeft^right)
            IRTreeReturn<DoubleVariable> trueCase = IRTree.constant(Double.POSITIVE_INFINITY);
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<DoubleVariable> falseCase = IRTree.max(
                    IRTree.powDD(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powDD(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<DoubleVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<DoubleVariable> leftMax = left.getMax(compilationCtx);
            if(leftMin == null || leftMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0.0)),
                    IRTree.lessThanEqual(IRTree.constant(0.0), leftMax),
                    IRTree.lessThanEqual(IRTree.constant(0.0), right.getForwardIR(compilationCtx)));
            // min(0, minLeft^right)
            IRTreeReturn<DoubleVariable> trueCase = IRTree.min(IRTree.constant(0.0),
                    IRTree.powDD(leftMin, right.getForwardIR(compilationCtx)));
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<DoubleVariable> falseCase = IRTree.min(
                    IRTree.powDD(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powDD(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }
    }

    private static class PowDI extends Pow<DoubleVariable, IntVariable, DoubleVariable> {
        PowDI(DoubleVariable a, IntVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.powDI(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<DoubleVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<DoubleVariable> leftMax = left.getMax(compilationCtx);
            if(leftMin == null || leftMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0.0)),
                    IRTree.lessThanEqual(IRTree.constant(0.0), leftMax),
                    IRTree.lessThan(right.getForwardIR(compilationCtx), IRTree.constant(0)));
            // min(0, minLeft^right)
            IRTreeReturn<DoubleVariable> trueCase = IRTree.constant(Double.POSITIVE_INFINITY);
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<DoubleVariable> falseCase = IRTree.max(
                    IRTree.powDI(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powDI(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<DoubleVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<DoubleVariable> leftMax = left.getMax(compilationCtx);
            IRTreeReturn<IntVariable> rightMax = right.getMax(compilationCtx);
            if(leftMin == null || leftMax == null || rightMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0.0)),
                    IRTree.lessThanEqual(IRTree.constant(0.0), leftMax), IRTree.lessThan(IRTree.constant(0), rightMax));
            // min(0, minLeft^right)
            IRTreeReturn<DoubleVariable> trueCase = IRTree.min(IRTree.constant(0.0),
                    IRTree.powDI(leftMin, right.getForwardIR(compilationCtx)));
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<DoubleVariable> falseCase = IRTree.min(
                    IRTree.powDI(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powDI(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }
    }

    private static class PowID extends Pow<IntVariable, DoubleVariable, DoubleVariable> {
        PowID(IntVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.powID(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<IntVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<IntVariable> leftMax = left.getMax(compilationCtx);
            if(leftMin == null || leftMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0)),
                    IRTree.lessThanEqual(IRTree.constant(0), leftMax),
                    IRTree.lessThan(right.getForwardIR(compilationCtx), IRTree.constant(0.0)));
            // min(0, minLeft^right)
            IRTreeReturn<DoubleVariable> trueCase = IRTree.constant(Double.POSITIVE_INFINITY);
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<DoubleVariable> falseCase = IRTree.max(
                    IRTree.powID(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powID(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<IntVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<IntVariable> leftMax = left.getMax(compilationCtx);
            IRTreeReturn<DoubleVariable> rightMax = right.getMax(compilationCtx);
            if(leftMin == null || leftMax == null || rightMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0)),
                    IRTree.lessThanEqual(IRTree.constant(0), leftMax), IRTree.lessThan(IRTree.constant(0.0), rightMax));
            // min(0, minLeft^right)
            IRTreeReturn<DoubleVariable> trueCase = IRTree.min(IRTree.constant(0.0),
                    IRTree.powID(leftMin, right.getForwardIR(compilationCtx)));
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<DoubleVariable> falseCase = IRTree.min(
                    IRTree.powID(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powID(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }
    }

    private static class PowII extends Pow<IntVariable, IntVariable, IntVariable> {
        PowII(IntVariable a, IntVariable b, Location location) {
            super(VariableType.IntVariable, location, a, b);
        }

        @Override
        public IRTreeReturn<IntVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.powII(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<IntVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<IntVariable> leftMax = left.getMax(compilationCtx);
            if(leftMin == null || leftMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0)),
                    IRTree.lessThanEqual(IRTree.constant(0), leftMax),
                    IRTree.lessThan(right.getForwardIR(compilationCtx), IRTree.constant(0)));
            // min(0, minLeft^right)
            IRTreeReturn<IntVariable> trueCase = IRTree.constant(Integer.MAX_VALUE);
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<IntVariable> falseCase = IRTree.max(IRTree.powII(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powII(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }

        @Override
        public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
            if(!right.isDeterministic())
                return null;

            IRTreeReturn<IntVariable> leftMin = left.getMin(compilationCtx);
            IRTreeReturn<IntVariable> leftMax = left.getMax(compilationCtx);
            IRTreeReturn<IntVariable> rightMax = right.getMax(compilationCtx);
            if(leftMin == null || leftMax == null || rightMax == null)
                return null;

            // Test if this is possibly 0 to a positive power, in which case the minimum happens at zero and is zero if
            // the power is even, and happens a the minimum value if it is an odd power.
            IRTreeReturn<BooleanVariable> gurad = IRTree.and(IRTree.lessThanEqual(leftMin, IRTree.constant(0)),
                    IRTree.lessThanEqual(IRTree.constant(0), leftMax), IRTree.lessThan(IRTree.constant(0), rightMax));
            // min(0, minLeft^right)
            IRTreeReturn<IntVariable> trueCase = IRTree.min(IRTree.constant(0),
                    IRTree.powII(leftMin, right.getForwardIR(compilationCtx)));
            // min(minLeft^b, maxLeft^b)
            IRTreeReturn<IntVariable> falseCase = IRTree.min(IRTree.powII(leftMin, right.getForwardIR(compilationCtx)),
                    IRTree.powII(leftMax, right.getForwardIR(compilationCtx)));
            return IRTree.conditionalAssignment(gurad, trueCase, falseCase);
        }
    }

    /* Factory methods for construction */
    public static DoubleVariable pow(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new PowDD(a, b, location));
    }

    public static DoubleVariable pow(DoubleVariable a, IntVariable b, Location location) {
        return DoubleVariable.doubleVariable(new PowDI(a, b, location));
    }

    public static DoubleVariable pow(IntVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new PowID(a, b, location));
    }

    public static IntVariable pow(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new PowII(a, b, location));
    }
}