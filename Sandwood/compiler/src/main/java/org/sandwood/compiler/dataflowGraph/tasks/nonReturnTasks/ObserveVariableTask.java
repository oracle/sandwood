/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.nonReturnTasks;

import java.util.List;
import java.util.Map;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTask;
import org.sandwood.compiler.dataflowGraph.tasks.DataflowTaskImplementation;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

/**
 * TODO Make a second version of this that takes Java base types and use this to set all constants in out programs. TODO
 * Making int for example will then be shorthand for creating an unset int and observing its values.
 *
 * @param <A>
 */
public class ObserveVariableTask<A extends Variable<A>> extends DataflowTaskImplementation<A> {

    public final Variable<A> source;
    public final Variable<A> target;

    public ObserveVariableTask(A target, A source, Location location) {
        super(DFType.OBSERVE_VARIABLE, location, target.getCurrentInstance(), source.getCurrentInstance());
        this.source = source.getCurrentInstance();
        this.target = target.getCurrentInstance();
        inlineableTask = false;
    }

    @Override
    public void testTask(List<SandwoodModelException> errors) {
        if(this.target.isDistribution())
            errors.add(new SandwoodModelException("Currently, distributions cannot be " + "observed, \""
                    + target.getVarDesc() + "\" is a distribution, not a value.", this));
        else if(this.target.isDeterministic())
            errors.add(new SandwoodModelException("Cannot set constant \"" + target.getVarDesc()
                    + "\" to the value of \"" + source.getVarDesc() + "\".", this));
        if(!this.source.isDeterministic())
            errors.add(new SandwoodModelException("Currently, only deterministic values can be observed, \""
                    + source.getVarDesc() + "\" is not deterministic.", this));
    }

    @Override
    public String getSandwoodString(boolean compressSandwoodCode) {
        return target.getExpression(false) + ".observe(" + source.getExpression(compressSandwoodCode) + ");";
    }

    @Override
    public IRTreeReturn<A> getForwardIRinternal(CompilationContext compilationCtx) {
        return source.getForwardIR(compilationCtx);
    }

    @Override
    public int getSandwoodCode(StringBuilder sb, int indent, Map<Variable<?>, Boolean> inlineableVariables,
            boolean compressSandwoodCode) {
        addIndent(sb, indent);
        sb.append(getSandwoodString(compressSandwoodCode) + "\n");
        return indent;
    }

    @Override
    public String getSandwoodExpression(boolean compressSandwoodCode) {
        return null;
    }

    @Override
    public boolean equivalent(DataflowTask<?> other) {
        if(getType() != other.getType())
            return false;
        ObserveVariableTask<?> t = (ObserveVariableTask<?>) other;
        return source.equivalent(t.source);
    }
}
