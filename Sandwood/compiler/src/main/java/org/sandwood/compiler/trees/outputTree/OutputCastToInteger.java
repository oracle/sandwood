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
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;

public class OutputCastToInteger extends OutputUniOp<DoubleVariable, IntVariable> {

    private OutputCastToInteger(OutputTreeReturn<DoubleVariable> input) {
        super(OutputTreeType.CAST_TO_INT, input, "Cast to Integer");
    }

    @Override
    protected OutputCastToInteger copy(Map<OutputTree, OutputTree> results) {
        OutputCastToInteger r = OutputTree.castToInteger(input.copy(results));
        results.put(this, r);
        return r;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        sb.append("(int)");
        input.toJava(sb, indent, requiredImports);

    }

    static OutputCastToInteger getCastToInteger(OutputTreeReturn<DoubleVariable> toCast) {
        return new OutputCastToInteger(toCast);
    }
}
