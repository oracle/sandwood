package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK6$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK6.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK6$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK6$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample18(int j) {
		state.bias[((j - 0) / 1)] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample18(int j) {
		if(true) {
			state.constrainedFlag$sample18[((j - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							{
								{
									for(int var30 = 0; var30 < state.shape[j]; var30 += 1) {
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample18[((j - 0) / 1)] = true;
											{
												{
													{
														{
															{
																cv$count = (cv$count + 1);
																if(state.flips[j][var30])
																	cv$sum = (cv$sum + 1);
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
					}
				}
			}
			if(state.constrainedFlag$sample18[((j - 0) / 1)])
				state.bias[((j - 0) / 1)] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!state.fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[((j - 0) / 1)];
						{
							{
								double var15 = 1.0;
								double var16 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var15, var16));
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
				state.logProbability$beta[((j - 0) / 1)] = cv$sampleAccumulator;
				state.logProbability$sample18[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample18[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$beta[((j - 0) / 1)] = cv$rvAccumulator;
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var30 = 0; var30 < state.shape[j]; var30 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[j][var30];
							{
								{
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= state.bias[((j - 0) / 1)]) && (state.bias[((j - 0) / 1)] <= 1.0))?Math.log((cv$sampleValue?state.bias[((j - 0) / 1)]:(1.0 - state.bias[((j - 0) / 1)]))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample31[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var30 = 0; var30 < state.shape[j]; var30 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = state.logProbability$sample31[((j - 0) / 1)];
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
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[((j - 0) / 1)] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			boolean[] var21 = state.flips[j];
			for(int var30 = 0; var30 < state.shape[j]; var30 += 1)
				var21[var30] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[((j - 0) / 1)]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[((j - 0) / 1)] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[((j - 0) / 1)] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			boolean[] var21 = state.flips[j];
			for(int var30 = 0; var30 < state.shape[j]; var30 += 1)
				var21[var30] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[((j - 0) / 1)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[((j - 0) / 1)] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[((j - 0) / 1)] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int j = 0; j < state.coins; j += 1) {
				if(!state.fixedFlag$sample18)
					inferSample18(j);
			}
		} else {
			for(int j = (state.coins - ((((state.coins - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1) {
				if(!state.fixedFlag$sample18)
					inferSample18(j);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.constrainedFlag$sample18[((j - 0) / 1)])
				drawValueSample18(j);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$beta[((j - 0) / 1)] = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample18) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample18[((j - 0) / 1)] = Double.NaN;
		}
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[((j - 0) / 1)] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample31) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample31[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.shape.length;
		for(int index$constrainedFlag$sample18$1 = 0; index$constrainedFlag$sample18$1 < state.constrainedFlag$sample18.length; index$constrainedFlag$sample18$1 += 1)
			state.constrainedFlag$sample18[index$constrainedFlag$sample18$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample31();
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
		     + "public model Flip2CoinsMK6(boolean[][] flipsMeasured, int[] shape) {\n"
		     + "\n"
		     + "    int coins = shape.length;\n"
		     + "                 \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        Beta beta = beta(1.0, 1.0);\n"
		     + "        public double bias = beta.sample();\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}