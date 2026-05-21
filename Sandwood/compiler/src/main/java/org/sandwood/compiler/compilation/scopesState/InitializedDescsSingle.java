/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 * 
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.compilation.scopesState;

import java.util.HashMap;
import java.util.Map;

import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.Type;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.BooleanVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.CompilerException;
import org.sandwood.compiler.names.VariableNames;

public class InitializedDescsSingle extends InitializedDescs {
    private final Scope changeableParentScope;
    private final Map<Variable<?>, Variable<?>> trackedVars = new HashMap<>();

    InitializedDescsSingle() {
        super(null);
        changeableParentScope = null;
    }

    public InitializedDescsSingle(InitializedDescs parent, Scope changeableParentScope) {
        super(parent);
        this.changeableParentScope = changeableParentScope;
    }

    @Override
    public <A extends Variable<A>> Variable<A> getInitializedVariable(Variable<A> v) {
        Variable<?> var = trackedVars.get(v.instanceHandle());
        if(var != null)
            return (Variable<A>) var;
        else
            return parent.getInitializedVariable(v);
    }

    @Override
    public boolean isInitialized(Variable<?> v) {
        if(trackedVars.containsKey(v.instanceHandle()))
            return true;

        if(parent == null)
            return false;

        if(changeableParentScope != null) {
            Scope s = v.scope();
            while(s != GlobalScope.scope) {
                if(s == changeableParentScope)
                    return false;
                s = s.getEnclosingScope();
            }
        }

        return parent.isInitialized(v);
    }

    @Override
    public <A extends Variable<A>> Variable<A> addInitialized(Variable<A> v) {
        assert !isInitialized(v);
        Variable<A> var = getVar(v);
        trackedVars.put(v.instanceHandle(), var);
        return var;
    }

    private <A extends Variable<A>> Variable<A> getVar(Variable<A> v) {
        if(v.isIntermediate())
            return v;

        Variable<A> altVar;
        Variable<A> vi = v.instanceHandle();

        VersionId id = versionCount.get(vi);
        if(id != null && id.usedId() != 0) {
            Type<?> type = v.getType();
            int vc = id.usedId();
            if(type.isArray()) {
                ArrayVariable<?> a = (ArrayVariable<?>) v;
                altVar = (Variable<A>) ArrayVariable.getNamedArray(VariableNames.scopedCopySubstitute(a, vc++),
                        v.scope());
            } else if(type == VariableType.BooleanVariable) {
                BooleanVariable b = (BooleanVariable) v;
                altVar = (Variable<A>) BooleanVariable.namedBoolean(VariableNames.scopedCopySubstitute(b, vc++),
                        v.scope());
            } else if(type == VariableType.DoubleVariable) {
                DoubleVariable d = (DoubleVariable) v;
                altVar = (Variable<A>) DoubleVariable.namedDouble(VariableNames.scopedCopySubstitute(d, vc++),
                        v.scope());
            } else if(type == VariableType.IntVariable) {
                IntVariable i = (IntVariable) v;
                altVar = (Variable<A>) IntVariable.namedInt(VariableNames.scopedCopySubstitute(i, vc++), v.scope());
            } else {
                throw new CompilerException("Unexpected type " + type);
            }
            updateVersionCount(vi, vc);
        } else {
            updateVersionCount(vi, 1);
            altVar = v;
        }
        return altVar;
    }
}
