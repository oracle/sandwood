/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.scopes;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class ReductionScopeCopied<A extends Variable<A>> extends ReductionScopeBase<A> {
    private final IRTreeReturn<IntVariable> maskedValue;
    private final ReductionScopeBase<A> original;

    public ReductionScopeCopied(Scope scope, ReductionScopeBase<A> rs) {
        this(scope, null, rs.emptyValue, rs);
    }

    public ReductionScopeCopied(Scope scope, IRTreeReturn<IntVariable> maskedValue, Variable<A> emptyValue,
            ReductionScopeBase<A> rs) {
        super(rs, emptyValue, scope);
        this.maskedValue = maskedValue;
        original = rs;
    }


    @Override
    protected VariableDescription<A> getReturnName() {
        return original.getReturnName();
    }

    @Override
    protected void constructForStmt(List<IRTreeVoid> reduceBody, List<IRTreeVoid> forBody,
            VariableDescription<IntVariable> indexName, CompilationContext compilationCtx) {
        if(maskedValue==null) {
            super.constructForStmt(reduceBody, forBody, indexName, compilationCtx);
        } else {
            reduceBody.add(IRTree.forStmt(IRTree.sequential(forBody, Tree.NoComment), start.getForwardIR(compilationCtx),
                    maskedValue, IRTree.constant(1), indexName, true,
                    "Reduce for every value except a masked value which will be skipped."));
            IRTreeReturn<IntVariable> startPos = IRTree.addII(maskedValue, IRTree.constant(1));
            reduceBody.add(IRTree.forStmt(IRTree.sequential(forBody, Tree.NoComment), startPos,
                    end.getForwardIR(compilationCtx), IRTree.constant(1), indexName, true, Tree.NoComment));
        }
    }

    @Override
    public VariableDescription<A> getVariableDesc(CompilationContext compilationCtx) {
        return original.getVariableDesc(compilationCtx);
    }
}
