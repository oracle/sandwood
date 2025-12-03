package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK17$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK17.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.internal.numericTools.Gaussian;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK17$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK17$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample7() {
		state.bias = ((Math.sqrt(1.0) * DistributionSampling.sampleTruncatedGaussian(state.RNG$, ((0.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0))), ((1.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))))) + 0.5);
	}

	private final void inferSample7() {
		if(true) {
			state.constrainedFlag$sample7 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.bias;
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample7 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.bias = cv$proposedValue;
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + ((((0.0 <= cv$currentValue) && (cv$currentValue <= 1.0)) && (0.0 < 1.0))?(((0.0 < 1.0)?(DistributionSampling.logProbabilityGaussian(((cv$currentValue - 0.5) / Math.sqrt(1.0))) - (0.5 * Math.log(1.0))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))) - Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0)))))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$bias$1_1 = cv$currentValue;
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample7 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	if(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$1_1) && (traceTempVariable$bias$1_1 <= 1.0))?Math.log((state.flip?traceTempVariable$bias$1_1:(1.0 - traceTempVariable$bias$1_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$bias$1_1) && (traceTempVariable$bias$1_1 <= 1.0))?Math.log((state.flip?traceTempVariable$bias$1_1:(1.0 - traceTempVariable$bias$1_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$1_1) && (traceTempVariable$bias$1_1 <= 1.0))?Math.log((state.flip?traceTempVariable$bias$1_1:(1.0 - traceTempVariable$bias$1_1))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$1_1) && (traceTempVariable$bias$1_1 <= 1.0))?Math.log((state.flip?traceTempVariable$bias$1_1:(1.0 - traceTempVariable$bias$1_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$bias$1_1) && (traceTempVariable$bias$1_1 <= 1.0))?Math.log((state.flip?traceTempVariable$bias$1_1:(1.0 - traceTempVariable$bias$1_1))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio)))
							state.bias = cv$originalValue;
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.bias;
					{
						{
							double var2 = 0.5;
							double var3 = 1.0;
							double var4 = 0.0;
							double var5 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + (((((var4 <= cv$sampleValue) && (cv$sampleValue <= var5)) && (var4 < var5)) && (0.0 < var3))?(((0.0 < var3)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var2) / Math.sqrt(var3))) - (0.5 * Math.log(var3))):Double.NEGATIVE_INFINITY) - Math.log((Gaussian.cdf(((var5 - var2) / Math.sqrt(var3))) - Gaussian.cdf(((var4 - var2) / Math.sqrt(var3)))))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$bias = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample7 = state.fixedFlag$sample7;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					boolean cv$sampleValue = state.flip;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((cv$sampleValue?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$flip = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample7;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$flip;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample7)
			state.bias = ((Math.sqrt(1.0) * DistributionSampling.sampleTruncatedGaussian(state.RNG$, ((0.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0))), ((1.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))))) + 0.5);
		state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = ((Math.sqrt(1.0) * DistributionSampling.sampleTruncatedGaussian(state.RNG$, ((0.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0))), ((1.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))))) + 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = ((Math.sqrt(1.0) * DistributionSampling.sampleTruncatedGaussian(state.RNG$, ((0.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0))), ((1.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))))) + 0.5);
		state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.bias = ((Math.sqrt(1.0) * DistributionSampling.sampleTruncatedGaussian(state.RNG$, ((0.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0))), ((1.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))))) + 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = ((Math.sqrt(1.0) * DistributionSampling.sampleTruncatedGaussian(state.RNG$, ((0.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((0.0 - 0.5) / Math.sqrt(1.0))), ((1.0 - 0.5) / Math.sqrt(1.0)), Gaussian.cdf(((1.0 - 0.5) / Math.sqrt(1.0))))) + 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample7)
				inferSample7();
		} else {
			if(!state.fixedFlag$sample7)
				inferSample7();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample7)
			drawValueSample7();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$bias = Double.NaN;
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$flip = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void propagateObservedValues() {
		state.flip = state.flipMeasured;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK17(boolean flipMeasured) {\n"
		     + "    double bias = truncatedGaussian(0.5, 1.0, 0.0, 1.0).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean flip = bernoulli.sample();\n"
		     + "    flip.observe(flipMeasured);\n"
		     + "}\n"
		     + "";
	}
}