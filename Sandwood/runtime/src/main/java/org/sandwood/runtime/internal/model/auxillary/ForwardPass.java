/*
 * Sandwood
 *
 * Copyright (c) 2019-2025, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.auxillary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.sandwood.runtime.internal.model.CoreModel;
import org.sandwood.runtime.internal.model.variables.ComputedVariableInternal;
import org.sandwood.runtime.model.RetentionPolicy;

/**
 * A class to hold static methods for the regular execution of a model.
 */
public class ForwardPass {
    /**
     * A private constructor to stop the class ever being instantiated.
     */
    private ForwardPass() {}

    /**
     * A method to perform regular execution of a model.
     * 
     * @param iterations        The number of iterations the model should be executed for.
     * @param computedVariables The computed variables in the model. These are the variables that will be computed on
     *                          each iteration by this model.
     * @param core              The core that each iteration will be performed by and one.
     */
    public static void forward(int iterations, Collection<ComputedVariableInternal> computedVariables, CoreModel core) {
        List<ComputedVariableInternal> toSample = new ArrayList<>();
        for(ComputedVariableInternal c:computedVariables) {
            RetentionPolicy r = c.getRetentionPolicy();
            if(r == RetentionPolicy.SAMPLE || r == RetentionPolicy.MAP_AND_SAMPLE)
                toSample.add(c);
        }

        for(ComputedVariableInternal c:toSample)
            c.initializeSamples(iterations);
        for(int i = 0; i < iterations; i++) {
            core.forwardGeneration();
            for(ComputedVariableInternal c:toSample) {
                c.ingestSample();
            }
        }
        for(ComputedVariableInternal c:computedVariables)
            c.computeComplete();
    }
}
