package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.UniformBernoulli$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.UniformBernoulli.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class UniformBernoulli$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public UniformBernoulli$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample5() {
		state.prior = (state.a + ((state.b - state.a) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	private final void inferSample5() {
		if(true) {
			state.constrainedFlag$sample5 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = state.prior;
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample5 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						state.prior = cv$proposedValue;
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((state.a <= cv$currentValue) && (cv$currentValue < state.b))?(-Math.log((state.b - state.a))):Double.NEGATIVE_INFINITY));
						{
							{
								{
									double traceTempVariable$prior$1_1 = cv$currentValue;
									{
										{
											for(int var18 = 0; var18 < state.length$observed; var18 += 1) {
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample5 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		if(((Math.log(1.0) + (((0.0 <= traceTempVariable$prior$1_1) && (traceTempVariable$prior$1_1 <= 1.0))?Math.log((state.output[var18]?traceTempVariable$prior$1_1:(1.0 - traceTempVariable$prior$1_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$prior$1_1) && (traceTempVariable$prior$1_1 <= 1.0))?Math.log((state.output[var18]?traceTempVariable$prior$1_1:(1.0 - traceTempVariable$prior$1_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$prior$1_1) && (traceTempVariable$prior$1_1 <= 1.0))?Math.log((state.output[var18]?traceTempVariable$prior$1_1:(1.0 - traceTempVariable$prior$1_1))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$prior$1_1) && (traceTempVariable$prior$1_1 <= 1.0))?Math.log((state.output[var18]?traceTempVariable$prior$1_1:(1.0 - traceTempVariable$prior$1_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$prior$1_1) && (traceTempVariable$prior$1_1 <= 1.0))?Math.log((state.output[var18]?traceTempVariable$prior$1_1:(1.0 - traceTempVariable$prior$1_1))):Double.NEGATIVE_INFINITY)));
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
							state.prior = cv$originalValue;
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!state.fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var18 = 0; var18 < state.length$observed; var18 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.output[var18];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.prior) && (state.prior <= 1.0))?Math.log((cv$sampleValue?state.prior:(1.0 - state.prior))):Double.NEGATIVE_INFINITY));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var19 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample19 = state.fixedFlag$sample5;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var18 = 0; var18 < state.length$observed; var18 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var19;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bernoulli = cv$rvAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double cv$sampleValue = state.prior;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((state.a <= cv$sampleValue) && (cv$sampleValue < state.b))?(-Math.log((state.b - state.a))):Double.NEGATIVE_INFINITY));
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
			state.logProbability$prior = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$prior;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.prior = (state.a + ((state.b - state.a) * DistributionSampling.sampleUniform(state.RNG$)));
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						state.output[var18] = DistributionSampling.sampleBernoulli(RNG$1, state.prior);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.prior = (state.a + ((state.b - state.a) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.prior = (state.a + ((state.b - state.a) * DistributionSampling.sampleUniform(state.RNG$)));
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						state.output[var18] = DistributionSampling.sampleBernoulli(RNG$1, state.prior);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.prior = (state.a + ((state.b - state.a) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.prior = (state.a + ((state.b - state.a) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample5)
				inferSample5();
		} else {
			if(!state.fixedFlag$sample5)
				inferSample5();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$prior = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$output = 0.0;
		if(!state.fixedProbFlag$sample19)
			state.logProbability$var19 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.a = 0.0;
		state.b = 1.0;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.observed;
		boolean[] cv$target1 = state.output;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model UniformBernoulli(boolean[] observed) {\n"
		     + "    double a = 0.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double prior = uniform(a, b).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(prior);\n"
		     + "    boolean[] output = bernoulli.sample(observed.length);\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}