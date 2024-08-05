/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.scopes;

import java.util.HashSet;
import java.util.Set;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.sandwoodOperators.ForTask;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.names.VariableNames;

public class ReductionScope<A extends Variable<A>> extends ReductionScopeBase<A> {
    private int varId = 0;
    private VariableDescription<A> returnName;

    public ReductionScope(IntVariable start, IntVariable end, ArrayVariable<A> array, Variable<A> emptyValue) {
        super(start, end, array, emptyValue);
    }
    
    @Override
    protected VariableDescription<A> getReturnName() {
            returnName = VariableNames.reduceName(returnVar, varId++);
            return returnName;
    }

    @Override
    public VariableDescription<A> getVariableDesc(CompilationContext compilationCtx) {
        Set<IntVariable> indexs = new HashSet<>();
        Scope s = this;
        while(s != GlobalScope.scope) {
            if(s.getScopeType() == ScopeType.FOR) {
                ForTask ft = (ForTask) s;
                indexs.add((IntVariable) compilationCtx.getSubstitute(ft.getIndex()));
            }
            s = s.getEnclosingScope();
        }
        assert (returnName != null);
        return returnName;
    }

    public void setReturnVar(Variable<A> returnVar) {
        this.returnVar = returnVar;
    }

    public void setFirstArg(Variable<A> i) {
        this.i = i;
    }

    public void setSecondArg(Variable<A> j) {
        this.j = j;
    }
}
