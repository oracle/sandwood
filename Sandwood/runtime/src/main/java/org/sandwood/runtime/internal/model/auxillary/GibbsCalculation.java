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

import org.sandwood.runtime.internal.model.CoreModelBase;
import org.sandwood.runtime.internal.model.variables.ComputedVariableInternal;
import org.sandwood.runtime.internal.model.variables.CurrentProbability;

public class GibbsCalculation {
    public static void infer(int iterations, int burnin, int thinning,
            Collection<ComputedVariableInternal> computedVariables, CoreModelBase<?,?> core,
            final CurrentProbability[] currentProbabilities) {
        ArrayList<ComputedVariableInternal> map = new ArrayList<>();
        ArrayList<ComputedVariableInternal> sample = new ArrayList<>();

        double lastProbability = Double.NEGATIVE_INFINITY;

        for(ComputedVariableInternal c:computedVariables) {
            switch(c.getRetentionPolicy()) {
                case MAP:
                    map.add(c);
                    break;
                case SAMPLE:
                    sample.add(c);
                    c.initializeSamples(iterations);
                    break;
                case MAP_AND_SAMPLE:
                    map.add(c);
                    sample.add(c);
                    c.initializeSamples(iterations);
                    break;
                case NONE:
                case NA:
                    // do nothing
                    break;
            }
        }

        // Burnin step
        for(int i = 0; i < burnin; i++)
            core.gibbsRound();

        // Sampling steps
        if(map.size() != 0) {
            if(sample.size() != 0) { // Map and sample > 0
                for(int i = 0; i < iterations - 1; i++) {
                    lastProbability = collectSampleAndMap(core, map, sample, lastProbability, currentProbabilities);
                    runThinning(core, thinning);
                }
                lastProbability = collectSampleAndMap(core, map, sample, lastProbability, currentProbabilities);
            } else {
                for(int i = 0; i < iterations - 1; i++) {
                    lastProbability = collectMap(core, map, lastProbability, currentProbabilities);
                    runThinning(core, thinning);
                }
                lastProbability = collectMap(core, map, lastProbability, currentProbabilities);
            }
        } else {
            if(sample.size() != 0) { // only sample > 0
                for(int i = 0; i < iterations - 1; i++) {
                    collectSamples(core, sample);
                    runThinning(core, thinning);
                }
                collectSamples(core, sample);
            } else {
            } // We are not collecting any data so nothing needs to be done.
        }

        for(ComputedVariableInternal c:computedVariables)
            c.computeComplete();
    }

    private static void collectSamples(CoreModelBase<?,?> core, ArrayList<ComputedVariableInternal> sample) {
        core.gibbsRound();

        for(ComputedVariableInternal c:sample)
            c.ingestSample();
    }

    private static double collectMap(CoreModelBase<?,?> core, ArrayList<ComputedVariableInternal> map,
            double lastProbability, CurrentProbability[] currentProbabilities) {
        core.gibbsRound();
        core.logModelProbabilitiesDist();

        double probability = 0;
        for(CurrentProbability c:currentProbabilities) {
            double p = c.getCurrentLogProbability();
            if(!Double.isNaN(p))
                probability += p;
        }
        // System.out.print("," + probability);
        if(lastProbability <= probability) {
            for(ComputedVariableInternal c:map)
                c.ingestMap();
            lastProbability = probability;
        }
        return lastProbability;
    }

    private static void runThinning(CoreModelBase<?,?> core, int thinning) {
        for(int i = 0; i < thinning; i++)
            core.gibbsRound();
    }

    private static double collectSampleAndMap(CoreModelBase<?,?> core, ArrayList<ComputedVariableInternal> map,
            ArrayList<ComputedVariableInternal> sample, double lastProbability,
            CurrentProbability[] currentProbabilities) {
        core.gibbsRound();
        core.logModelProbabilitiesDist();

        for(ComputedVariableInternal c:sample)
            c.ingestSample();

        double probability = 0;
        for(CurrentProbability c:currentProbabilities) {
            double p = c.getCurrentLogProbability();
            if(!Double.isNaN(p))
                probability += p;
        }
        if(lastProbability < probability) {
            for(ComputedVariableInternal c:map)
                c.ingestMap();
            lastProbability = probability;
        }
        return lastProbability;
    }
}
