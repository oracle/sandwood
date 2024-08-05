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

public abstract class Add<A extends NumberVariable<A>, B extends NumberVariable<B>, C extends NumberVariable<C>>
        extends NumberProducingDataflowTaskImplementation<C> {

    public final A left;
    public final B right;

    Add(VariableType.Type<C> outputType, Location location, A left, B right) {
        super(DFType.ADDITION, outputType, location, left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public String checkInversionError(int argPos) {
        return null;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return left.getExpression(compressSandwoodCode) + " + " + right.getExpression(compressSandwoodCode);
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(this.getType() != other.getType())
            return false;
        Add<?, ?, ?> dft = (Add<?, ?, ?>) other;
        return (left.equivalent(dft.left) && right.equivalent(dft.right))
                || (left.equivalent(dft.right) && right.equivalent(dft.left));
    }

    private static class AddDD extends Add<DoubleVariable, DoubleVariable, DoubleVariable> {
        AddDD(DoubleVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.addDD(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getInverseIRInternal(int argPos, IRTreeReturn<DoubleVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.subtractDD(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.subtractDD(taskOutput, left.getForwardIR(compilationCtx));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.addDD(left.getMax(compilationCtx), right.getMax(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.addDD(left.getMin(compilationCtx), right.getMin(compilationCtx));
        }
    }

    private static class AddDI extends Add<DoubleVariable, IntVariable, DoubleVariable> {
        AddDI(DoubleVariable a, IntVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.addDI(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.subtractDI(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.castToInteger(IRTree.subtractDD(taskOutput, left.getForwardIR(compilationCtx)));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.addDI(left.getMax(compilationCtx), right.getMax(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.addDI(left.getMin(compilationCtx), right.getMin(compilationCtx));
        }
    }

    private static class AddID extends Add<IntVariable, DoubleVariable, DoubleVariable> {

        AddID(IntVariable a, DoubleVariable b, Location location) {
            super(VariableType.DoubleVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> getForwardIRinternal(
                CompilationContext compilationCtx) {
            return IRTree.addID(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<? extends NumberVariable<?>> getInverseIRInternal(int argPos,
                IRTreeReturn<DoubleVariable> taskOutput, BackTraceInfo backTraceInfo,
                CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.castToInteger(IRTree.subtractDD(taskOutput, right.getForwardIR(compilationCtx)));
                }

                case 1: {
                    return IRTree.subtractDI(taskOutput, left.getForwardIR(compilationCtx));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.addID(left.getMax(compilationCtx), right.getMax(compilationCtx));
        }

        @Override
        public IRTreeReturn<DoubleVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.addID(left.getMin(compilationCtx), right.getMin(compilationCtx));
        }
    }

    private static class AddII extends Add<IntVariable, IntVariable, IntVariable> {
        AddII(IntVariable a, IntVariable b, Location location) {
            super(VariableType.IntVariable, location, a, b);
        }

        @Override
        public IRBinOp<IntVariable, IntVariable, IntVariable> getForwardIRinternal(CompilationContext compilationCtx) {
            return IRTree.addII(left.getForwardIR(compilationCtx), right.getForwardIR(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getInverseIRInternal(int argPos, IRTreeReturn<IntVariable> taskOutput,
                BackTraceInfo backTraceInfo, CompilationContext compilationCtx) {
            switch(argPos) {
                case 0: {
                    return IRTree.subtractII(taskOutput, right.getForwardIR(compilationCtx));
                }

                case 1: {
                    return IRTree.subtractII(taskOutput, left.getForwardIR(compilationCtx));
                }

                default:
                    throw new CompilerException(
                            "Argument position is " + argPos + ". Binary operations only take 2 arguments.");
            }
        }

        @Override
        public IRTreeReturn<IntVariable> getMax(CompilationContext compilationCtx) {
            return IRTree.addII(left.getMax(compilationCtx), right.getMax(compilationCtx));
        }

        @Override
        public IRTreeReturn<IntVariable> getMin(CompilationContext compilationCtx) {
            return IRTree.addII(left.getMin(compilationCtx), right.getMin(compilationCtx));
        }
    }

    /* Factory methods for construction */
    public static DoubleVariable add(DoubleVariable a, int i) {
        return add(a, Variable.intVariable(i));
    }

    public static DoubleVariable add(DoubleVariable a, int i, Location location) {
        return add(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable add(DoubleVariable a, double d) {
        return add(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable add(DoubleVariable a, double d, Location location) {
        return add(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable add(DoubleVariable a, DoubleVariable b) {
        return add(a, b, null);
    }

    public static DoubleVariable add(DoubleVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new AddDD(a, b, location));
    }

    public static DoubleVariable add(DoubleVariable a, IntVariable b) {
        return add(a, b, null);
    }

    public static DoubleVariable add(DoubleVariable a, IntVariable b, Location location) {
        return DoubleVariable.doubleVariable(new AddDI(a, b, location));
    }

    public static IntVariable add(IntVariable a, int i) {
        return add(a, Variable.intVariable(i));
    }

    public static IntVariable add(IntVariable a, int i, Location location) {
        return add(a, Variable.intVariable(i), location);
    }

    public static DoubleVariable add(IntVariable a, double d) {
        return add(a, Variable.doubleVariable(d));
    }

    public static DoubleVariable add(IntVariable a, double d, Location location) {
        return add(a, Variable.doubleVariable(d), location);
    }

    public static DoubleVariable add(IntVariable a, DoubleVariable b) {
        return add(a, b, null);
    }

    public static DoubleVariable add(IntVariable a, DoubleVariable b, Location location) {
        return DoubleVariable.doubleVariable(new AddID(a, b, location));
    }

    public static IntVariable add(IntVariable a, IntVariable b) {
        return add(a, b, null);
    }

    public static IntVariable add(IntVariable a, IntVariable b, Location location) {
        return IntVariable.intVariable(new AddII(a, b, location));
    }
}