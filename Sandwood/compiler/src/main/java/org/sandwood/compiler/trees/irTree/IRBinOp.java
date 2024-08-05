/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.irTree;

import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;
import org.sandwood.compiler.trees.irTree.transformations.TreeTransformation;
import org.sandwood.compiler.trees.transformationTree.TransTree;
import org.sandwood.compiler.trees.transformationTree.TransTreeReturn;
import org.sandwood.compiler.trees.transformationTree.binop.TransBinOp;

public abstract class IRBinOp<L extends Variable<L>, R extends Variable<R>, RT extends Variable<RT>>
        extends IRTreeReturn<RT> {

    protected final IRTreeReturn<L> left;
    protected final IRTreeReturn<R> right;
    protected final boolean commutative;

    private IRBinOp(IRTreeReturn<L> left, IRTreeReturn<R> right, boolean commutative) {
        super(IRTreeType.BINOP);
        this.left = left;
        this.right = right;
        this.commutative = commutative;
    }

    protected abstract String getOp();

    @Override
    public IRTree[] getChildren() {
        return new IRTree[] { left, right };
    }

    @Override
    public String getDescription() {
        return "(left " + getOp() + " right)";
    }

    @Override
    public int equivalentHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left.equivalentHashCode();
        result = prime * result + right.equivalentHashCode();
        return result;
    }

    @Override
    public boolean equivalent(IRTree tree) {
        if(this == tree)
            return true;
        if((tree == null) || (type != tree.type))
            return false;
        IRBinOp<?, ?, ?> other = (IRBinOp<?, ?, ?>) tree;
        if(!getOp().equals(other.getOp()))
            return false;
        if(commutative) {
            return (left.equivalent(other.left) && right.equivalent(other.right))
                    || (left.equivalent(other.right) && right.equivalent(other.left));
        } else {
            return left.equivalent(other.left) && right.equivalent(other.right);
        }
    }

    @Override
    public void traverseTree(TreeVisitor v) {
        v.visit(left);
        v.visit(right);
    }

    private static abstract class IRNumericBinOp<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
            extends IRBinOp<L, R, RT> {
        public IRNumericBinOp(IRTreeReturn<L> left, IRTreeReturn<R> right, boolean commutative) {
            super(left, right, commutative);
        }
    }

    private static abstract class IRAdd<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
            extends IRNumericBinOp<L, R, RT> {
        public IRAdd(IRTreeReturn<L> left, IRTreeReturn<R> right) {
            super(left, right, true);
        }

        @Override
        protected String getOp() {
            return "+";
        }
    }

    static IRAdd<DoubleVariable, DoubleVariable, DoubleVariable> getAddDD(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRAdd<>(left, right) {

            @Override
            public TransBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.addDD(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.addDD(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRAdd<IntVariable, DoubleVariable, DoubleVariable> getAddID(IRTreeReturn<IntVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRAdd<>(left, right) {

            @Override
            public TransBinOp<IntVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.addID(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.addID(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRAdd<DoubleVariable, IntVariable, DoubleVariable> getAddDI(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRAdd<>(left, right) {

            @Override
            public TransBinOp<DoubleVariable, IntVariable, DoubleVariable> toTransformationTree() {
                return TransTree.addDI(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.addDI(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRAdd<IntVariable, IntVariable, IntVariable> getAddII(IRTreeReturn<IntVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRAdd<>(left, right) {

            @Override
            public TransBinOp<IntVariable, IntVariable, IntVariable> toTransformationTree() {
                return TransTree.addII(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, IntVariable, IntVariable> applyTransformation(TreeTransformation t) {
                return IRTree.addII(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }
        };
    }

    private static abstract class IRSubtract<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
            extends IRNumericBinOp<L, R, RT> {
        public IRSubtract(IRTreeReturn<L> left, IRTreeReturn<R> right) {
            super(left, right, false);
        }

        @Override
        protected String getOp() {
            return "-";
        }
    }

    static IRSubtract<DoubleVariable, DoubleVariable, DoubleVariable> getSubtractDD(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRSubtract<>(left, right) {
            @Override
            public TransBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.subtractDD(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.subtractDD(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRSubtract<IntVariable, DoubleVariable, DoubleVariable> getSubtractID(IRTreeReturn<IntVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRSubtract<>(left, right) {
            @Override
            public TransBinOp<IntVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.subtractID(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.subtractID(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRSubtract<DoubleVariable, IntVariable, DoubleVariable> getSubtractDI(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRSubtract<>(left, right) {
            @Override
            public TransBinOp<DoubleVariable, IntVariable, DoubleVariable> toTransformationTree() {
                return TransTree.subtractDI(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.subtractDI(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRSubtract<IntVariable, IntVariable, IntVariable> getSubtractII(IRTreeReturn<IntVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRSubtract<>(left, right) {
            @Override
            public TransBinOp<IntVariable, IntVariable, IntVariable> toTransformationTree() {
                return TransTree.subtractII(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, IntVariable, IntVariable> applyTransformation(TreeTransformation t) {
                return IRTree.subtractII(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }
        };
    }

    private static abstract class IRMultiply<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
            extends IRNumericBinOp<L, R, RT> {
        public IRMultiply(IRTreeReturn<L> left, IRTreeReturn<R> right) {
            super(left, right, true);
        }

        @Override
        protected String getOp() {
            return "*";
        }
    }

    static IRMultiply<DoubleVariable, DoubleVariable, DoubleVariable> getMultiplyDD(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRMultiply<>(left, right) {
            @Override
            public TransBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.multiplyDD(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.multiplyDD(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRMultiply<IntVariable, DoubleVariable, DoubleVariable> getMultiplyID(IRTreeReturn<IntVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRMultiply<>(left, right) {
            @Override
            public TransBinOp<IntVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.multiplyID(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.multiplyID(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRMultiply<DoubleVariable, IntVariable, DoubleVariable> getMultiplyDI(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRMultiply<>(left, right) {
            @Override
            public TransBinOp<DoubleVariable, IntVariable, DoubleVariable> toTransformationTree() {
                return TransTree.multiplyDI(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.multiplyDI(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRMultiply<IntVariable, IntVariable, IntVariable> getMultiplyII(IRTreeReturn<IntVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRMultiply<>(left, right) {
            @Override
            public TransBinOp<IntVariable, IntVariable, IntVariable> toTransformationTree() {
                return TransTree.multiplyII(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, IntVariable, IntVariable> applyTransformation(TreeTransformation t) {
                return IRTree.multiplyII(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }
        };
    }

    private static abstract class IRDivide<L extends NumberVariable<L>, R extends NumberVariable<R>, RT extends NumberVariable<RT>>
            extends IRNumericBinOp<L, R, RT> {
        public IRDivide(IRTreeReturn<L> left, IRTreeReturn<R> right) {
            super(left, right, false);
        }

        @Override
        protected String getOp() {
            return "/";
        }
    }

    static IRDivide<DoubleVariable, DoubleVariable, DoubleVariable> getDivideDD(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRDivide<>(left, right) {
            @Override
            public TransBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.divideDD(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.divideDD(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRDivide<IntVariable, DoubleVariable, DoubleVariable> getDivideID(IRTreeReturn<IntVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRDivide<>(left, right) {
            @Override
            public TransBinOp<IntVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.divideID(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.divideID(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRDivide<DoubleVariable, IntVariable, DoubleVariable> getDivideDI(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRDivide<>(left, right) {
            @Override
            public TransBinOp<DoubleVariable, IntVariable, DoubleVariable> toTransformationTree() {
                return TransTree.divideDI(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.divideDI(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRDivide<IntVariable, IntVariable, IntVariable> getDivideII(IRTreeReturn<IntVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRDivide<>(left, right) {
            @Override
            public TransBinOp<IntVariable, IntVariable, IntVariable> toTransformationTree() {
                return TransTree.divideII(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, IntVariable, IntVariable> applyTransformation(TreeTransformation t) {
                return IRTree.divideII(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }
        };
    }

    static IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> getAnd(IRTreeReturn<BooleanVariable> left,
            IRTreeReturn<BooleanVariable> right) {
        return new IRBinOp<>(left, right, true) {
            @Override
            protected String getOp() {
                return "&&";
            }

            @Override
            public TransTreeReturn<BooleanVariable> toTransformationTree() {
                return TransTree.and(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> applyTransformation(
                    TreeTransformation t) {
                return IRTree.and(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<BooleanVariable> getOutputType() {
                return VariableType.BooleanVariable;
            }
        };
    }

    static IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> getOr(IRTreeReturn<BooleanVariable> left,
            IRTreeReturn<BooleanVariable> right) {
        return new IRBinOp<>(left, right, true) {
            @Override
            protected String getOp() {
                return "||";
            }

            @Override
            public TransTreeReturn<BooleanVariable> toTransformationTree() {
                return TransTree.or(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<BooleanVariable, BooleanVariable, BooleanVariable> applyTransformation(
                    TreeTransformation t) {
                return IRTree.or(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<BooleanVariable> getOutputType() {
                return VariableType.BooleanVariable;
            }
        };
    }

    static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<L, R, BooleanVariable> getLessThan(
            IRTreeReturn<L> left, IRTreeReturn<R> right) {
        return new IRBinOp<>(left, right, false) {
            @Override
            protected String getOp() {
                return "<";
            }

            @Override
            public TransBinOp<L, R, BooleanVariable> toTransformationTree() {
                return TransTree.lessThan(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<L, R, BooleanVariable> applyTransformation(TreeTransformation t) {
                return IRTree.lessThan(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<BooleanVariable> getOutputType() {
                return VariableType.BooleanVariable;
            }
        };
    }

    static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<L, R, BooleanVariable> getLessThanEqual(
            IRTreeReturn<L> left, IRTreeReturn<R> right) {
        return new IRBinOp<>(left, right, false) {
            @Override
            protected String getOp() {
                return "<=";
            }

            @Override
            public TransBinOp<L, R, BooleanVariable> toTransformationTree() {
                return TransTree.lessThanEqual(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<L, R, BooleanVariable> applyTransformation(TreeTransformation t) {
                return IRTree.lessThanEqual(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<BooleanVariable> getOutputType() {
                return VariableType.BooleanVariable;
            }
        };
    }

    static <A extends ScalarVariable<A>, B extends ScalarVariable<B>> IRBinOp<A, B, BooleanVariable> getEq(
            IRTreeReturn<A> left, IRTreeReturn<B> right) {
        return new IRBinOp<>(left, right, true) {
            @Override
            protected String getOp() {
                return "==";
            }

            @Override
            public TransBinOp<A, B, BooleanVariable> toTransformationTree() {
                return TransTree.eq(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<A, B, BooleanVariable> applyTransformation(TreeTransformation t) {
                return IRTree.eq(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<BooleanVariable> getOutputType() {
                return VariableType.BooleanVariable;
            }
        };
    }

    static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<R, L, BooleanVariable> getGreaterThan(
            IRTreeReturn<L> left, IRTreeReturn<R> right) {
        return getLessThan(right, left);
    }

    static <L extends NumberVariable<L>, R extends NumberVariable<R>> IRBinOp<R, L, BooleanVariable> getGreaterThanEqual(
            IRTreeReturn<L> left, IRTreeReturn<R> right) {
        return getLessThanEqual(right, left);
    }

    static IRBinOp<IntVariable, IntVariable, IntVariable> getRemainderII(IRTreeReturn<IntVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRBinOp<>(left, right, false) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            public TransBinOp<IntVariable, IntVariable, IntVariable> toTransformationTree() {
                return TransTree.remainderII(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, IntVariable, IntVariable> applyTransformation(TreeTransformation t) {
                return IRTree.remainderII(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<IntVariable> getOutputType() {
                return VariableType.IntVariable;
            }
        };
    }

    static IRBinOp<IntVariable, DoubleVariable, DoubleVariable> getRemainderID(IRTreeReturn<IntVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRBinOp<>(left, right, false) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            public TransBinOp<IntVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.remainderID(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<IntVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.remainderID(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRBinOp<DoubleVariable, IntVariable, DoubleVariable> getRemainderDI(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<IntVariable> right) {
        return new IRBinOp<>(left, right, false) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            public TransBinOp<DoubleVariable, IntVariable, DoubleVariable> toTransformationTree() {
                return TransTree.remainderDI(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, IntVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.remainderDI(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }

    static IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> getRemainderDD(IRTreeReturn<DoubleVariable> left,
            IRTreeReturn<DoubleVariable> right) {
        return new IRBinOp<>(left, right, false) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            public TransBinOp<DoubleVariable, DoubleVariable, DoubleVariable> toTransformationTree() {
                return TransTree.remainderDD(left.toTransformationTree(), right.toTransformationTree());
            }

            @Override
            public IRBinOp<DoubleVariable, DoubleVariable, DoubleVariable> applyTransformation(TreeTransformation t) {
                return IRTree.remainderDD(t.transformReturn(left), t.transformReturn(right));
            }

            @Override
            public Type<DoubleVariable> getOutputType() {
                return VariableType.DoubleVariable;
            }
        };
    }
}
