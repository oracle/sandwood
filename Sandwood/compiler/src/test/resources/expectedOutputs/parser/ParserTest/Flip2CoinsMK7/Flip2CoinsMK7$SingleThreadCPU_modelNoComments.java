package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK7$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK7.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK7$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK7$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample18(int i) {
		state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
	}

	private final void inferSample18(int i) {
		if(true) {
			state.constrainedFlag$sample18[((i - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, 2);
			}
			double cv$originalValue = (1 - state.bias[i]);
			double cv$originalProbability = 0.0;
			double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
			if((cv$var < 0.01))
				cv$var = 0.01;
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				if((state.constrainedFlag$sample18[((i - 0) / 1)] || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					double cv$currentValue;
					if((cv$valuePos == 0))
						cv$currentValue = cv$originalValue;
					else {
						cv$currentValue = cv$proposedValue;
						double var18 = cv$proposedValue;
						{
							{
								{
									state.bias[i] = (1 - cv$currentValue);
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, state.a, state.b));
						{
							{
								{
									double traceTempVariable$var34$2_1 = (1 - cv$currentValue);
									for(int j = 0; j < state.coins; j += 1) {
										if((i == j)) {
											{
												{
													for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1) {
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															state.constrainedFlag$sample18[((i - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				if(((Math.log(1.0) + (((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[j][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[j][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[j][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[j][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= traceTempVariable$var34$2_1) && (traceTempVariable$var34$2_1 <= 1.0))?Math.log((state.flips[j][var45]?traceTempVariable$var34$2_1:(1.0 - traceTempVariable$var34$2_1))):Double.NEGATIVE_INFINITY)));
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
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							double var18 = cv$originalValue;
							{
								{
									{
										state.bias[i] = (1 - var18);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!state.fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.coins; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = (1 - state.bias[i]);
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, state.a, state.b));
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
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$sample18[((i - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.coins; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample18[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!state.fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[j][var45];
							{
								{
									double var34 = state.bias[j];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((cv$sampleValue?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$bernoulli[((j - 0) / 1)] = cv$sampleAccumulator;
				state.logProbability$sample46[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample46 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample46[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli[((j - 0) / 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var36 = state.flips[j];
			for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1)
				var36[var45] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var36 = state.flips[j];
			for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1)
				var36[var45] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[i] = (1 - DistributionSampling.sampleBeta(state.RNG$, state.a, state.b));
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int i = 0; i < state.coins; i += 1) {
				if(!state.fixedFlag$sample18)
					inferSample18(i);
			}
		} else {
			for(int i = (state.coins - ((((state.coins - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1) {
				if(!state.fixedFlag$sample18)
					inferSample18(i);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < state.coins; i += 1) {
			if(!state.constrainedFlag$sample18[((i - 0) / 1)])
				drawValueSample18(i);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.logProbability$sample18[((i - 0) / 1)] = Double.NaN;
		}
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[((j - 0) / 1)] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample46) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample46[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample18$1 = 0; index$constrainedFlag$sample18$1 < state.constrainedFlag$sample18.length; index$constrainedFlag$sample18$1 += 1)
			state.constrainedFlag$sample18[index$constrainedFlag$sample18$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample46();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[][] cv$source1 = state.flipsMeasured;
		boolean[][] cv$target1 = state.flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = cv$source1[cv$index1];
			boolean[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
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
		     + "public model Flip2CoinsMK7(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "     \n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins)) \n"
		     + "        bias[i] = 1 - beta(a, b).sample();\n"
		     + "                \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}