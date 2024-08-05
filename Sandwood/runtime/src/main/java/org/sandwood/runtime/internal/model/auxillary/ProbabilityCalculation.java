/*
 * Sandwood
 *
 * Copyright (c) 2019-2023, Oracle and/or its affiliates
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package org.sandwood.runtime.internal.model.auxillary;

import org.sandwood.runtime.internal.model.CoreModel;
import org.sandwood.runtime.internal.model.variables.HasProbabilityInternal;
import org.sandwood.runtime.internal.numericTools.LogSumExponential;

public class ProbabilityCalculation {
    public static double generateLogProbabilities(int iterations, CoreModel core, HasProbabilityInternal[] vars) {
        double modelProbability = 0;
        if(iterations > 0) { // Ensure we have enough iterations
            for(HasProbabilityInternal v:vars)
                v.startLogProbability();

            modelProbability = iterateLog(iterations, core, vars);

            for(HasProbabilityInternal v:vars)
                v.completeLogProbability(iterations);
        }
        return modelProbability;
    }

    private static double iterateLog(int iterations, CoreModel core, HasProbabilityInternal[] vars) {
        LogSumExponential logProbabilitySum = new LogSumExponential();

        // TODO we might need to add batching here to ensure the difference between sum
        // and a single measurement doesn't become too high.
        for(int i = 0; i < iterations; i++) {
            core.logProbabilityGeneration();
            for(HasProbabilityInternal v:vars)
                v.ingestLogProbability();
            logProbabilitySum.add(core.get$logProbability$$evidence());
        }

        return logProbabilitySum.getSum() - Math.log(iterations);
    }

    public static double generateLogProbabilities(double percentageDifference, int iterations, int maxIterations,
            CoreModel core, HasProbabilityInternal[] vars) {
        LogSumExponential logProbabilitySum = new LogSumExponential();

        // Incremented so that we only need to compare rations. (max-min)/min < p ==
        // max/min < p + 1
        percentageDifference++;
        percentageDifference = Math.log(percentageDifference);

        // Construct storage space for partial results
        int n = 5; // Segments needs to be even, so this is the parameter that you could tweak.
        int segments = 2 * n;
        double[] segResults = new double[segments];
        int segmentSize = iterations / segments;
        if(segmentSize == 0) // Test to ensure that we have some iterations. Iterations shouldn't be set
            // small enough for this to be an issue in regular use cases.
            segmentSize = 1;
        iterations = segmentSize * segments;

        for(HasProbabilityInternal v:vars)
            v.startLogProbability();

        // Run the models for each segment
        for(int i = 0; i < segments; i++)
            segResults[i] = iterateLog(segmentSize, core, vars);

        // Calculate the variance
        double min = Double.MAX_VALUE;
        double max = Double.NEGATIVE_INFINITY;
        for(int i = 0; i < segments; i++) {
            if(min > segResults[i])
                min = segResults[i];
            if(max < segResults[i])
                max = segResults[i];
        }

        double currentPercentageDifference;
        if(max == Double.NEGATIVE_INFINITY)
            currentPercentageDifference = max;
        else if(min == Double.NEGATIVE_INFINITY)
            currentPercentageDifference = Double.MAX_VALUE;
        else
            currentPercentageDifference = max - min;

        while(currentPercentageDifference > percentageDifference && 2 * iterations <= maxIterations) {
            iterations *= 2;

            // Merge existing results into half the segments
            segmentSize *= 2;
            for(int i = 0; i < n; i++) {
                logProbabilitySum.clear();
                logProbabilitySum.add(segResults[i]);
                logProbabilitySum.add(segResults[i + n]);
                segResults[i] = logProbabilitySum.getSum() - Math.log(2);
            }

            // Run the models for each segment
            for(int i = n; i < segments; i++)
                segResults[i] = iterateLog(segmentSize, core, vars);

            // Calculate the variance
            min = Double.MAX_VALUE;
            max = Double.NEGATIVE_INFINITY;
            for(int i = 0; i < segments; i++) {
                if(min > segResults[i])
                    min = segResults[i];
                if(max < segResults[i])
                    max = segResults[i];
            }

            if(max == Double.NEGATIVE_INFINITY)
                currentPercentageDifference = max;
            else if(min == Double.NEGATIVE_INFINITY)
                currentPercentageDifference = Double.MAX_VALUE;
            else
                currentPercentageDifference = max - min;
        }

        // Complete the variable results
        for(HasProbabilityInternal v:vars)
            v.completeLogProbability(iterations);

        // Combine the partial model results.
        logProbabilitySum.clear();
        for(int i = 0; i < segments; i++)
            logProbabilitySum.add(segResults[i]);
        return logProbabilitySum.getSum() - Math.log(segments);
    }
}
