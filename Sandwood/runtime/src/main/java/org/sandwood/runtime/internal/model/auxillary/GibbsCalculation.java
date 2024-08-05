/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.auxillary;

import java.util.ArrayList;
import java.util.Collection;

import org.sandwood.runtime.internal.model.CoreModel;
import org.sandwood.runtime.internal.model.variables.ComputedVariableInternal;
import org.sandwood.runtime.internal.model.variables.CurrentProbability;

public class GibbsCalculation {
    public static void infer(int iterations, int burnin, int thinning,
            Collection<ComputedVariableInternal> computedVariables, CoreModel core,
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
                case NONE:
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

    private static <C extends CoreModel> void collectSamples(C core, ArrayList<ComputedVariableInternal> sample) {
        core.gibbsRound();

        for(ComputedVariableInternal c:sample)
            c.ingestSample();
    }

    private static <C extends CoreModel> double collectMap(C core, ArrayList<ComputedVariableInternal> map,
            double lastProbability, CurrentProbability[] currentProbabilities) {
        core.gibbsRound();
        core.logModelProbabilitiesDist();

        double probability = 0;
        for(CurrentProbability c:currentProbabilities)
            probability += c.getCurrentLogProbability();
        // System.out.print("," + probability);
        if(lastProbability <= probability) {
            for(ComputedVariableInternal c:map)
                c.ingestMap();
            lastProbability = probability;
        }
        return lastProbability;
    }

    private static void runThinning(CoreModel core, int thinning) {
        for(int i = 0; i < thinning; i++)
            core.gibbsRound();
    }

    private static double collectSampleAndMap(CoreModel core, ArrayList<ComputedVariableInternal> map,
            ArrayList<ComputedVariableInternal> sample, double lastProbability,
            CurrentProbability[] currentProbabilities) {
        core.gibbsRound();
        core.logModelProbabilitiesDist();

        for(ComputedVariableInternal c:sample)
            c.ingestSample();

        double probability = 0;
        for(CurrentProbability c:currentProbabilities)
            probability += c.getCurrentLogProbability();
        if(lastProbability < probability) {
            for(ComputedVariableInternal c:map)
                c.ingestMap();
            lastProbability = probability;
        }
        return lastProbability;
    }
}
