/*
 * Sandwood
 *
 * Copyright (c) 2019-2024, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.compiler.dataflowGraph.variables.randomVariables;

import org.sandwood.compiler.compilation.CompilationContext;
import org.sandwood.compiler.dataflowGraph.scopes.Scope;
import org.sandwood.compiler.dataflowGraph.variables.Variable;
import org.sandwood.compiler.dataflowGraph.variables.VariableType;
import org.sandwood.compiler.dataflowGraph.variables.VariableType.RandomVariableType;
import org.sandwood.compiler.dataflowGraph.variables.arrayVariable.ArrayVariable;
import org.sandwood.compiler.dataflowGraph.variables.scalarVariables.IntVariable;
import org.sandwood.compiler.srcTools.sourceToSource.Location;
import org.sandwood.compiler.trees.irTree.IRTreeReturn;

public interface RandomVariable<A extends Variable<A>, B extends RandomVariable<A, B>> extends Variable<B> {
    VariableType.Type<A> getSampleType();

    Scope getRandomScope(CompilationContext compilationCtx);

    /**
     * Method to determine if a value is sampled from this random variable.
     * 
     * @return
     */
    boolean valueSampled();

    /**
     * method to determine if a distribution is sampled from this random variable.
     * 
     * @return
     */
    boolean distributionSampled();

    A sample();

    A sample(Location location);

    // Sampling methods are written out verbatim as the typing of the return value will be problematic
    // otherwise.
    ArrayVariable<A> sample(int count);

    ArrayVariable<A> sample(IntVariable count);

    ArrayVariable<A> sample(IntVariable count, Location location);

    ArrayVariable<ArrayVariable<A>> sample(int count1, int count2);

    ArrayVariable<ArrayVariable<A>> sample(IntVariable count1, IntVariable count2);

    ArrayVariable<ArrayVariable<A>> sample(IntVariable count1, IntVariable count2, Location location);

    ArrayVariable<ArrayVariable<ArrayVariable<A>>> sample(int count1, int count2, int count3);

    ArrayVariable<ArrayVariable<ArrayVariable<A>>> sample(IntVariable count1, IntVariable count2, IntVariable count3);

    ArrayVariable<ArrayVariable<ArrayVariable<A>>> sample(IntVariable count1, IntVariable count2, IntVariable count3,
            Location location);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> sample(int count1, int count2, int count3,
            int count4);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> sample(IntVariable count1, IntVariable count2,
            IntVariable count3, IntVariable count4);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>> sample(IntVariable count1, IntVariable count2,
            IntVariable count3, IntVariable count4, Location location);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> sample(int count1, int count2,
            int count3, int count4, int count5);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> sample(IntVariable count1,
            IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>> sample(IntVariable count1,
            IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5, Location location);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> sample(int count1,
            int count2, int count3, int count4, int count5, int count6);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, Location location);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> sample(
            int count1, int count2, int count3, int count4, int count5, int count6, int count7);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7, Location location);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> sample(
            int count1, int count2, int count3, int count4, int count5, int count6, int count7, int count8);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7, IntVariable count8);

    ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<ArrayVariable<A>>>>>>>> sample(
            IntVariable count1, IntVariable count2, IntVariable count3, IntVariable count4, IntVariable count5,
            IntVariable count6, IntVariable count7, IntVariable count8, Location location);

    IRTreeReturn<A> getSampleTree(A sample, CompilationContext compilationCtx);

    @Override
    RandomVariableType<A, B> getType();

    boolean isInfinite();
}
