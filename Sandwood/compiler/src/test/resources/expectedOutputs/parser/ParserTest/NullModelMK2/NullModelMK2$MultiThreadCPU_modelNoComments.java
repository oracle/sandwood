package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NullModelMK2$MultiThreadCPU extends CoreModelMultiThreadCPU implements NullModelMK2$CoreInterface {
double bias;
	boolean constrainedFlag$sample10 = true;
	double eta;
	boolean fixedFlag$sample10 = false;
	boolean fixedProbFlag$sample10 = false;
	boolean fixedProbFlag$sample12 = false;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double logProbability$binomial;
	double logProbability$positiveCount;
	double min;
	int observedPositiveCount;
	int observedSampleCount;
	int positiveCount;
	boolean system$gibbsForward = true;

	public NullModelMK2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample10 = false;
		fixedProbFlag$sample12 = false;
	}

	@Override
	public final double get$eta() {
		return eta;
	}

	@Override
	public final void set$eta(double cv$value, boolean allocated$) {
		eta = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value, boolean allocated$) {
		fixedFlag$sample10 = cv$value;
		constrainedFlag$sample10 = (fixedFlag$sample10 || constrainedFlag$sample10);
		fixedProbFlag$sample10 = (fixedFlag$sample10 && fixedProbFlag$sample10);
		fixedProbFlag$sample12 = (fixedFlag$sample10 && fixedProbFlag$sample12);
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$binomial() {
		return logProbability$binomial;
	}

	@Override
	public final double get$logProbability$positiveCount() {
		return logProbability$positiveCount;
	}

	@Override
	public final double get$min() {
		return min;
	}

	@Override
	public final int get$observedPositiveCount() {
		return observedPositiveCount;
	}

	@Override
	public final void set$observedPositiveCount(int cv$value, boolean allocated$) {
		observedPositiveCount = cv$value;
	}

	@Override
	public final int get$observedSampleCount() {
		return observedSampleCount;
	}

	@Override
	public final void set$observedSampleCount(int cv$value, boolean allocated$) {
		observedSampleCount = cv$value;
	}

	@Override
	public final int get$positiveCount() {
		return positiveCount;
	}

	private final void drawValueSample10() {
		bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	private final void inferSample10() {
		if(true) {
			constrainedFlag$sample10 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = bias;
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((constrainedFlag$sample10 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						bias = cv$proposedValue;
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((min <= cv$currentValue) && (cv$currentValue < 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$bias$1_1 = cv$currentValue;
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												constrainedFlag$sample10 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(positiveCount, traceTempVariable$bias$1_1, observedSampleCount)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(positiveCount, traceTempVariable$bias$1_1, observedSampleCount)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(positiveCount, traceTempVariable$bias$1_1, observedSampleCount));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(positiveCount, traceTempVariable$bias$1_1, observedSampleCount)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(positiveCount, traceTempVariable$bias$1_1, observedSampleCount)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
												if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
													else
														cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
												}
											}
										}
									}
								}
							}
						}
						if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
						else {
							if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
								cv$stateProbabilityValue = cv$accumulatedProbabilities;
							else
								cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
						}
					}
					if((cv$valuePos == 0))
						cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					else
						cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio)))
							bias = cv$originalValue;
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = bias;
					{
						{
							double var8 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (((min <= cv$sampleValue) && (cv$sampleValue < var8))?(-Math.log((var8 - min))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bias = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = positiveCount;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, bias, observedSampleCount));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$binomial = cv$sampleAccumulator;
			logProbability$positiveCount = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$positiveCount;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$binomial = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocate() {}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
		positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
		positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample10)
				inferSample10();
		} else {
			if(!fixedFlag$sample10)
				inferSample10();
		}
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample10)
			drawValueSample10();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$bias = Double.NaN;
		logProbability$binomial = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$positiveCount = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		min = ((eta * 4.0) / 5.0);
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void propagateObservedValues() {
		positiveCount = observedPositiveCount;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model NullModelMK2(double eta, int observedSampleCount, int observedPositiveCount) {\n"
		     + "        double min = eta * 4.0/5.0;    \n"
		     + "        double bias = uniform(min, 1.0).sample();\n"
		     + "        \n"
		     + "        //Construct a binomial\n"
		     + "        Binomial binomial = binomial(bias, observedSampleCount);\n"
		     + "                \n"
		     + "        //Sample from it\n"
		     + "        int positiveCount = binomial.sample();\n"
		     + "        \n"
		     + "        //Link the sampled values to the observed values\n"
		     + "        positiveCount.observe(observedPositiveCount);\n"
		     + "}";
	}
}