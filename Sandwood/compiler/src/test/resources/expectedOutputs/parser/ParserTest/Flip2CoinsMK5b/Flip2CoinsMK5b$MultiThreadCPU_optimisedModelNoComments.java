package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK5b$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK5b.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK5b$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK5b$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample18(int i, int threadID$cv$i, Rng RNG$) {
		state.bias[i] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void inferSample18(int i, int threadID$cv$i, Rng RNG$) {
		state.constrainedFlag$sample18[i] = false;
		int cv$sum = 0;
		int cv$count = 0;
		int j = (state.coins - (i + 1));
		if((0 <= j)) {
			for(int var46 = 0; var46 < state.shape[j]; var46 += 1) {
				state.constrainedFlag$sample18[i] = true;
				cv$count = (cv$count + 1);
				if(state.flips[j][var46])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample18[i])
			state.bias[i] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample18() {
		if(!state.fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.coins; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[i], 1.0, 1.0);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample18[i] = cv$distributionAccumulator;
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.coins; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample18[i]);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var46 = 0; var46 < state.shape[j]; var46 += 1) {
					double var35 = state.bias[(state.coins - (j + 1))];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var35) && (var35 <= 1.0))?Math.log((state.flips[j][var46]?var35:(1.0 - var35))):Double.NEGATIVE_INFINITY));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli[j] = cv$sampleAccumulator;
				state.logProbability$sample47[j] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = state.logProbability$sample47[j];
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
		if(!state.fixedFlag$sample18)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var37 = state.flips[j];
						parallelFor(RNG$1, 0, state.shape[j], 1,
							(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
										var37[var46] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[(state.coins - (j + 1))]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample18)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample18)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, state.coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var37 = state.flips[j];
						parallelFor(RNG$1, 0, state.shape[j], 1,
							(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
										var37[var46] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[(state.coins - (j + 1))]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample18)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample18)
			parallelFor(state.RNG$, 0, state.coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							state.bias[i] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample18) {
			if(state.system$gibbsForward)
				parallelFor(state.RNG$, 0, state.coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample18(i, threadID$i, RNG$1);
					}
				);
			else
				parallelFor(state.RNG$, 0, state.coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								inferSample18(i, threadID$i, RNG$1);
					}
				);
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.constrainedFlag$sample18[i])
							drawValueSample18(i, threadID$i, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample18) {
			for(int i = 0; i < state.coins; i += 1)
				state.logProbability$sample18[i] = Double.NaN;
		}
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[j] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample47) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample47[j] = Double.NaN;
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
		logProbabilityValue$sample47();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample47();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample47();
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
		     + "public model Flip2CoinsMK5b(boolean[][] flipsMeasured, int[] shape) {\n"
		     + "     \n"
		     + "    int coins = shape.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins))\n"
		     + "      bias[i] = beta(1.0, 1.0).sample();\n"
		     + "        \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[coins-(j+1)]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "\n"
		     + "";
	}
}