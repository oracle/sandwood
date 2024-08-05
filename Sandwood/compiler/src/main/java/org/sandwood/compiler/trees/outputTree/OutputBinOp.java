/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.trees.outputTree;

import java.util.Map;
import java.util.Set;

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.ScalarVariable;

public abstract class OutputBinOp<L extends ScalarVariable<L>, R extends ScalarVariable<R>, RT extends ScalarVariable<RT>>
        extends OutputTreeReturn<RT> {

    protected final OutputTreeReturn<L> left;
    protected final OutputTreeReturn<R> right;

    private OutputBinOp(OutputTreeReturn<L> left, OutputTreeReturn<R> right) {
        super(OutputTreeType.BINOP);
        this.left = left;
        this.right = right;
    }

    protected abstract String getOp();

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("(");
        left.toJava(sb, indent, requiredImports);
        sb.append(" " + getOp() + " ");
        right.toJava(sb, indent, requiredImports);
        sb.append(")");
    }

    @Override
    public OutputTree[] getChildren() {
        return new OutputTree[] { left, right };
    }

    @Override
    public String getDescription() {
        return "(left " + getOp() + " right)";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + left.hashCode();
        result = prime * result + right.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!super.equals(obj) || (getClass() != obj.getClass()))
            return false;
        OutputBinOp<?, ?, ?> other = (OutputBinOp<?, ?, ?>) obj;
        if(!getOp().equals(other.getOp()))
            return false;
        if(!left.equals(other.left))
            return false;
        return right.equals(other.right);
    }

    // TODO simplify these to one class per Op where the inputs and outputs extend
    // Number.
    // TODO use rename each method to just add,.... only possible once the first
    // step is done (if at all).
    // TODO move these to Tree? Wrap them in other methods form Tree? will become
    // apparent once more of the above is done.
    public static OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> addDD(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "+";
            }

            @Override
            protected OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.addDD(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> addID(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "+";
            }

            @Override
            protected OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.addID(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> addDI(OutputTreeReturn<DoubleVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "+";
            }

            @Override
            protected OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> b = OutputBinOp.addDI(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, IntVariable> addII(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "+";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, IntVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, IntVariable> b = OutputBinOp.addII(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> subtractDD(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "-";
            }

            @Override
            protected OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> b = OutputBinOp
                        .subtractDD(left.copy(results), right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> subtractID(
            OutputTreeReturn<IntVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "-";
            }

            @Override
            protected OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.subtractID(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> subtractDI(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "-";
            }

            @Override
            protected OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> b = OutputBinOp.subtractDI(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, IntVariable> subtractII(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "-";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, IntVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, IntVariable> b = OutputBinOp.subtractII(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> multiplyDD(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "*";
            }

            @Override
            protected OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> b = OutputBinOp
                        .multiplyDD(left.copy(results), right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> multiplyID(
            OutputTreeReturn<IntVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "*";
            }

            @Override
            protected OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.multiplyID(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> multiplyDI(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "*";
            }

            @Override
            protected OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> b = OutputBinOp.multiplyDI(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, IntVariable> multiplyII(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "*";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, IntVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, IntVariable> b = OutputBinOp.multiplyII(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> divideDD(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "/";
            }

            @Override
            protected OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.divideDD(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> divideID(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "/";
            }

            @Override
            protected OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.divideID(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> divideDI(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "/";
            }

            @Override
            protected OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> b = OutputBinOp.divideDI(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, IntVariable> divideII(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "/";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, IntVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, IntVariable> b = OutputBinOp.divideII(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> and(
            OutputTreeReturn<BooleanVariable> left, OutputTreeReturn<BooleanVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "&&";
            }

            @Override
            protected OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> b = OutputBinOp.and(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> or(
            OutputTreeReturn<BooleanVariable> left, OutputTreeReturn<BooleanVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "||";
            }

            @Override
            protected OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<BooleanVariable, BooleanVariable, BooleanVariable> b = OutputBinOp.or(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> OutputBinOp<L, R, BooleanVariable> lessThan(
            OutputTreeReturn<L> left, OutputTreeReturn<R> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "<";
            }

            @Override
            protected OutputBinOp<L, R, BooleanVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<L, R, BooleanVariable> b = OutputBinOp.lessThan(left.copy(results), right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static <L extends NumberVariable<L>, R extends NumberVariable<R>> OutputBinOp<L, R, BooleanVariable> lessThanEqual(
            OutputTreeReturn<L> left, OutputTreeReturn<R> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "<=";
            }

            @Override
            protected OutputBinOp<L, R, BooleanVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<L, R, BooleanVariable> b = OutputBinOp.lessThanEqual(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static <L extends ScalarVariable<L>, R extends ScalarVariable<R>> OutputBinOp<L, R, BooleanVariable> eq(
            OutputTreeReturn<L> left, OutputTreeReturn<R> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "==";
            }

            @Override
            protected OutputBinOp<L, R, BooleanVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<L, R, BooleanVariable> b = OutputBinOp.eq(left.copy(results), right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, BooleanVariable> greaterThan(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return ">";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, BooleanVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, BooleanVariable> b = OutputBinOp.greaterThan(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, BooleanVariable> greaterThanEqual(
            OutputTreeReturn<IntVariable> left, OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return ">=";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, BooleanVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, BooleanVariable> b = OutputBinOp
                        .greaterThanEqual(left.copy(results), right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, IntVariable, IntVariable> remainderII(OutputTreeReturn<IntVariable> left,
            OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            protected OutputBinOp<IntVariable, IntVariable, IntVariable> copy(Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, IntVariable, IntVariable> b = OutputBinOp.remainderII(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> remainderID(
            OutputTreeReturn<IntVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            protected OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<IntVariable, DoubleVariable, DoubleVariable> b = OutputBinOp.remainderID(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> remainderDI(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<IntVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            protected OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, IntVariable, DoubleVariable> b = OutputBinOp.remainderDI(left.copy(results),
                        right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }

    public static OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> remainderDD(
            OutputTreeReturn<DoubleVariable> left, OutputTreeReturn<DoubleVariable> right) {
        return new OutputBinOp<>(left, right) {
            @Override
            protected String getOp() {
                return "%";
            }

            @Override
            protected OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> copy(
                    Map<OutputTree, OutputTree> results) {
                OutputBinOp<DoubleVariable, DoubleVariable, DoubleVariable> b = OutputBinOp
                        .remainderDD(left.copy(results), right.copy(results));
                results.put(this, b);
                return b;
            }
        };
    }
}
