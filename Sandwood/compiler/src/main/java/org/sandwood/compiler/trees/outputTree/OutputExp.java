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

import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.NumberVariable;

public class OutputExp<A extends NumberVariable<A>> extends OutputUniOp<A, DoubleVariable> {

    private OutputExp(OutputTreeReturn<A> input) {
        super(OutputTreeType.EXP, input, "exp");
    }

    @Override
    protected OutputExp<A> copy(Map<OutputTree, OutputTree> results) {
        OutputExp<A> r = OutputTree.exp(input.copy(results));
        results.put(this, r);
        return r;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("Math.exp(");
        input.toJava(sb, indent, requiredImports);
        sb.append(")");
    }

    static <A extends NumberVariable<A>> OutputExp<A> getExp(OutputTreeReturn<A> input) {
        return new OutputExp<>(input);
    }

}
