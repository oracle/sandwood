/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.tasks.returnTasks.rvConstructor;

import static org.sandwood.compiler.trees.irTree.IRTree.addII;
import static org.sandwood.compiler.trees.irTree.IRTree.arrayGet;
import static org.sandwood.compiler.trees.irTree.IRTree.constant;
import static org.sandwood.compiler.trees.irTree.IRTree.forStmt;
import static org.sandwood.compiler.trees.irTree.IRTree.getIntField;
import static org.sandwood.compiler.trees.irTree.IRTree.initializeVariable;
import static org.sandwood.compiler.trees.irTree.IRTree.load;
import static org.sandwood.compiler.trees.irTree.IRTree.store;

import java.util.List;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.GlobalScope;
import org.sandwood.compiler.dataflowGraph.tasks.DFType;
import org.sandwood.compiler.dataflowGraph.variables.VariableDescription;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.auxillary.DataflowTaskArgDesc;
import org.sandwood.compiler.dataflowGraph.variables.randomVariables.Multinomial;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.DoubleVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.exceptions.SandwoodModelException;
import org.sandwood.compiler.names.VariableNames;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.traces.TraceConstructionDesc;
import org.sandwood.compiler.trees.Tree;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;
import org.sandwood.compiler.trees.irTree.IRTreeVoid;

public class MultinomialTask extends RandomVariableConstructorTask<ArrayVariable<IntVariable>, Multinomial> {
    public final ArrayVariable<DoubleVariable> p;
    public final IntVariable n;

    public MultinomialTask(ArrayVariable<DoubleVariable> p, IntVariable n, Location location) {
        super(DFType.MULTINOMIAL, VariableType.Multinomial, location, p.getCurrentInstance(), n);

        this.p = p.getCurrentInstance();
        this.n = n;
    }

    @Override
    public void testTask(List<SandwoodModelException> errors) {
        super.testTask(errors);
        if(!(n.isFixed() || n.isDeterministic()))
            errors.add(new SandwoodModelException(
                    "The number of values drawn from a Multinomial distribution must be observed or deterministic "
                            + "as Sandwood does not currently support the required block sampling to allow multiple "
                            + "values to change in a single inference step. This ability is required if this this "
                            + "argument is able to change as the output of this Multinomial would have to change at "
                            + "the same time.",
                    this));
    }

    @Override
    public void constructTrace(TraceConstructionDesc desc) {
        // If the trace starts here proceed as normal, otherwise this is observed data being propagated, so only follow
        // the variable that can be inverted.
        if(desc.sink == output)
            super.constructTrace(desc);
        else {
            desc.trace.push(new DataflowTaskArgDesc(this, 1));
            n.constructTrace(desc);
            desc.trace.pop();
        }
    }

    @Override
    public String checkInversionError(int argPos) {
        if(argPos == 1)
            return null;
        else
            return super.checkInversionError(argPos);
    }

    /**
     * A method to where possible construct a tree providing the value of the requested random variable argument based
     * on the value of a sample.
     * 
     * @param current        An IRTree providing the value of the sample.
     * @param argPos         The position of the argument to calculate.
     * @param compilationCtx The compilation context.
     * @return A tree returning the value of the argument.
     */
    @Override
    public IRTreeReturn<?> getInverseArg(IRTreeReturn<ArrayVariable<IntVariable>> current, int argPos,
            CompilationContext compilationCtx) {
        if(argPos == 1) {
            VariableDescription<IntVariable> accName = VariableNames.calcVarName("multinomialSum" + id(),
                    VariableType.IntVariable, true);
            VariableDescription<IntVariable> indexName = VariableNames.calcVarName("multinomialIndex" + id(),
                    VariableType.IntVariable, true);
            compilationCtx.addTreeToScope(GlobalScope.scope, initializeVariable(accName, constant(0), Tree.NoComment));
            IRTreeVoid sum = store(accName, addII(arrayGet(current, load(indexName)), load(accName)), Tree.NoComment);
            IRTreeReturn<IntVariable> length = getIntField(current, "length");
            IRTreeVoid loop = forStmt(sum, constant(0), length, constant(1), indexName, true,
                    "Sum the number of samples in the multinomial output.");
            compilationCtx.addTreeToScope(GlobalScope.scope, loop);
            return load(accName);
        } else
            return super.getInverseArg(current, argPos, compilationCtx);
    }
}
