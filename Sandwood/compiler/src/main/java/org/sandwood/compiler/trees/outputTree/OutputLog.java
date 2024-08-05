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

public class OutputLog<A extends NumberVariable<A>> extends OutputUniOp<A, DoubleVariable> {

    private OutputLog(OutputTreeReturn<A> input) {
        super(OutputTreeType.LOG, input, "log");
    }

    @Override
    protected OutputLog<A> copy(Map<OutputTree, OutputTree> results) {
        OutputLog<A> r = OutputTree.log(input.copy(results));
        results.put(this, r);
        return r;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("Math.log(");
        input.toJava(sb, indent, requiredImports);
        sb.append(")");
    }

    static <A extends NumberVariable<A>> OutputLog<A> getLog(OutputTreeReturn<A> input) {
        return new OutputLog<>(input);
    }
}
