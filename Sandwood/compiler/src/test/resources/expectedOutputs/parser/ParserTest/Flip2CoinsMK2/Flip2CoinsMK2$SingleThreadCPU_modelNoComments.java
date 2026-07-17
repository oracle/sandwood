package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample20(int var19) {
		state.bias[var19] = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
	}

	private final void inferSample20(int var19) {
		if(true) {
			state.constrainedFlag$sample20[((var19 - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int j = 0; j < state.coins; j += 1) {
								if((var19 == j)) {
									for(int i = 0; i < state.samples; i += 1) {
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample20[((var19 - 0) / 1)] = true;
											{
												{
													{
														{
															{
																cv$count = (cv$count + 1);
																if(state.flips[i][j])
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
			if(state.constrainedFlag$sample20[((var19 - 0) / 1)]) {
				double var20 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, state.a, state.b, cv$sum, cv$count);
				{
					{
						{
							state.bias[var19] = var20;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var19 = 0; var19 < state.coins; var19 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[var19];
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			state.logProbability$var20 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample20 = state.fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var19 = 0; var19 < state.coins; var19 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var20;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample20)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.samples; i += 1) {
				for(int j = 0; j < state.coins; j += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							boolean cv$sampleValue = state.flips[i][j];
							{
								{
									double var43 = state.bias[j];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var43) && (var43 <= 1.0))?Math.log((cv$sampleValue?var43:(1.0 - var43))):Double.NEGATIVE_INFINITY));
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
					state.logProbability$bernoulli[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleAccumulator;
					state.logProbability$sample45[((i - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = state.fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.samples; i += 1) {
				for(int j = 0; j < state.coins; j += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = state.logProbability$sample45[((i - 0) / 1)][((j - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					state.logProbability$bernoulli[((i - 0) / 1)][((j - 0) / 1)] = cv$rvAccumulator;
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int var19 = 0; var19 < state.coins; var19 += 1) {
			if(!state.fixedFlag$sample20)
				state.bias[var19] = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		}
		for(int i = 0; i < state.samples; i += 1) {
			boolean[] sample = state.flips[i];
			for(int j = 0; j < state.coins; j += 1)
				sample[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var19 = 0; var19 < state.coins; var19 += 1) {
			if(!state.fixedFlag$sample20)
				state.bias[var19] = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var19 = 0; var19 < state.coins; var19 += 1) {
			if(!state.fixedFlag$sample20)
				state.bias[var19] = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		}
		for(int i = 0; i < state.samples; i += 1) {
			boolean[] sample = state.flips[i];
			for(int j = 0; j < state.coins; j += 1)
				sample[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var19 = 0; var19 < state.coins; var19 += 1) {
			if(!state.fixedFlag$sample20)
				state.bias[var19] = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var19 = 0; var19 < state.coins; var19 += 1) {
			if(!state.fixedFlag$sample20)
				state.bias[var19] = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var19 = 0; var19 < state.coins; var19 += 1) {
				if(!state.fixedFlag$sample20)
					inferSample20(var19);
			}
		} else {
			for(int var19 = (state.coins - ((((state.coins - 1) - 0) % 1) + 1)); var19 >= ((0 - 1) + 1); var19 -= 1) {
				if(!state.fixedFlag$sample20)
					inferSample20(var19);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var19 = 0; var19 < state.coins; var19 += 1) {
			if(!state.constrainedFlag$sample20[((var19 - 0) / 1)])
				drawValueSample20(var19);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$var20 = Double.NaN;
		for(int i = 0; i < state.samples; i += 1) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$bernoulli[((i - 0) / 1)][((j - 0) / 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample45) {
			for(int i = 0; i < state.samples; i += 1) {
				for(int j = 0; j < state.coins; j += 1)
					state.logProbability$sample45[((i - 0) / 1)][((j - 0) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured.length;
		state.coins = state.length$flipsMeasured[0];
		for(int index$constrainedFlag$sample20$1 = 0; index$constrainedFlag$sample20$1 < state.constrainedFlag$sample20.length; index$constrainedFlag$sample20$1 += 1)
			state.constrainedFlag$sample20[index$constrainedFlag$sample20$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample45();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample45();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample45();
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
		     + "public model Flip2CoinsMK2(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "    int coins = flipsMeasured[0].length;\n"
		     + "    double[] bias = beta(a, b).sample(coins);\n"
		     + "    boolean[][] flips = new boolean[samples][];\n"
		     + "    for(int i:[0..samples)) {\n"
		     + "        boolean[] sample = new boolean[coins];\n"
		     + "        for(int j:[0..coins)) {\n"
		     + "            Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "            sample[j] = bernoulli.sample();\n"
		     + "        }\n"
		     + "        flips[i] = sample;\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}