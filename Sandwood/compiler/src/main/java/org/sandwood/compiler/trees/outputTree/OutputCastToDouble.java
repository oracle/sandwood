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

public class OutputCastToDouble extends OutputUniOp<IntVariable, DoubleVariable> {

    private boolean implicit;

    private OutputCastToDouble(OutputTreeReturn<IntVariable> input, boolean implicit) {
        super(OutputTreeType.CAST_TO_DOUBLE, input, "Cast to Double");
        this.implicit = implicit;
    }

    @Override
    protected OutputCastToDouble copy(Map<OutputTree, OutputTree> results) {
        OutputCastToDouble r = new OutputCastToDouble(input.copy(results), implicit);
        results.put(this, r);
        return r;
    }

    @Override
    protected void toJavaInternal(StringBuilder sb, int indent, Set<String> requiredImports) {
        if(!implicit)
            sb.append("(double)");
        input.toJava(sb, indent, requiredImports);
    }

    void setImplicit() {
        implicit = true;
    }

    static OutputCastToDouble getCastToDouble(OutputTreeReturn<IntVariable> toCast) {
        return new OutputCastToDouble(toCast, false);
    }
}
