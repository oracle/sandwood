package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK8$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK8.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK8$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK8$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample17(int i, int threadID$cv$i, Rng RNG$) {
		state.bias[i] = DistributionSampling.sampleBeta(RNG$, state.a, state.b);
	}

	private final void inferSample17(int i, int threadID$cv$i, Rng RNG$) {
		state.constrainedFlag$sample17[i] = false;
		double cv$originalValue = state.bias[i];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, state.a, state.b);
			for(int var45 = 0; var45 < state.length$flipsMeasured[i]; var45 += 1) {
				state.constrainedFlag$sample17[i] = true;
				double var34 = (1 - cv$originalValue);
				cv$accumulatedProbabilities = ((((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.flips[i][var45]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample17[i]) {
			state.bias[i] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, state.a, state.b);
			for(int var45 = 0; var45 < state.length$flipsMeasured[i]; var45 += 1) {
				state.constrainedFlag$sample17[i] = true;
				double var34 = (1 - cv$proposedValue);
				cv$accumulatedProbabilities = ((((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.flips[i][var45]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				state.bias[i] = cv$originalValue;
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.coins; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[i], state.a, state.b);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample17[i] = cv$distributionAccumulator;
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.coins; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample17[i]);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!state.fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var45 = 0; var45 < state.length$flipsMeasured[j]; var45 += 1) {
					double var34 = (1 - state.bias[j]);
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.flips[j][var45]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli[j] = cv$sampleAccumulator;
				state.logProbability$sample46[j] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample46 = state.fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = state.logProbability$sample46[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, state.a, state.b);
				}
			);

		parallelFor(state.RNG$, 0, state.coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var36 = state.flips[j];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
										var36[var45] = DistributionSampling.sampleBernoulli(RNG$2, (1 - state.bias[j]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, state.a, state.b);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, state.a, state.b);
				}
			);

		parallelFor(state.RNG$, 0, state.coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var36 = state.flips[j];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
										var36[var45] = DistributionSampling.sampleBernoulli(RNG$2, (1 - state.bias[j]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, state.a, state.b);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, state.a, state.b);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample17) {
			if(state.system$gibbsForward)
				parallelFor(state.RNG$, 0, state.coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample17(i, threadID$i, RNG$1);
					}
				);
			else
				parallelFor(state.RNG$, 0, state.coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample17(i, threadID$i, RNG$1);
					}
				);
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.constrainedFlag$sample17[i])
							drawValueSample17(i, threadID$i, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample17) {
			for(int i = 0; i < state.coins; i += 1)
				state.logProbability$sample17[i] = Double.NaN;
		}
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[j] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample46) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample46[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample17$1 = 0; index$constrainedFlag$sample17$1 < state.constrainedFlag$sample17.length; index$constrainedFlag$sample17$1 += 1)
			state.constrainedFlag$sample17[index$constrainedFlag$sample17$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample46();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = state.flipsMeasured[cv$index1];
			boolean[] cv$target2 = state.flips[cv$index1];
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
		     + "public model Flip2CoinsMK8(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "     \n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins)) \n"
		     + "        bias[i] = beta(a, b).sample();\n"
		     + "            \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli = bernoulli(1 - bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}