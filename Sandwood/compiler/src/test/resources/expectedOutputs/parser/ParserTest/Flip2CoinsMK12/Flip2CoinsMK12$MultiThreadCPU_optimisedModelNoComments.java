package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK12$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK12.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK12$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK12$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample23(int i$var22, int threadID$cv$i$var22, Rng RNG$) {
		state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void inferSample10() {
		state.constrainedFlag$sample10 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var49 = 0; var49 < state.length$flipsMeasured[0]; var49 += 1) {
			state.constrainedFlag$sample10 = true;
			cv$count = (cv$count + 1);
			if(state.flips[0][var49])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample10)
			state.bias[0] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample23(int i$var22, int threadID$cv$i$var22, Rng RNG$) {
		state.constrainedFlag$sample23[(i$var22 - 1)] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var76 = 0; var76 < state.length$flipsMeasured[i$var22]; var76 += 1) {
			state.constrainedFlag$sample23[(i$var22 - 1)] = true;
			cv$count = (cv$count + 1);
			if(state.flips[i$var22][var76])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample23[(i$var22 - 1)])
			state.bias[i$var22] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample10() {
		if(!state.fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0);
			state.logProbability$beta = (state.logProbability$beta + cv$distributionAccumulator);
			state.logProbability$var10 = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var10);
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var10);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var10);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var10);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!state.fixedProbFlag$sample23) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var22 = 1; i$var22 < state.coins; i$var22 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[i$var22], 1.0, 1.0));
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			state.logProbability$var23 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample23 = state.fixedFlag$sample23;
		} else {
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var23);
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var23);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var23);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var23);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < state.length$flipsMeasured[0]; var49 += 1) {
				double var38 = state.bias[0];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.flips[0][var49]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$bernoulli1[0] = cv$sampleAccumulator;
			state.logProbability$sample50[0] = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			double cv$rvAccumulator = state.logProbability$sample50[0];
			state.logProbability$bernoulli1[0] = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!state.fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var76 = 0; var76 < state.length$flipsMeasured[k]; var76 += 1) {
					double var65 = state.bias[k];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var65) && (var65 <= 1.0))?Math.log((state.flips[k][var76]?var65:(1.0 - var65))):Double.NEGATIVE_INFINITY));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli2[(k - 1)] = cv$sampleAccumulator;
				state.logProbability$sample78[(k - 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample78 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$rvAccumulator = state.logProbability$sample78[(k - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli2[(k - 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var40 = state.flips[j];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
										var40[var49] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[j]);
							}
						);
					}
			}
		);
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						boolean[] var67 = state.flips[k];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[k], 1,
							(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
										var67[var76] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[k]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var40 = state.flips[j];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
										var40[var49] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[j]);
							}
						);
					}
			}
		);
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
						int k = index$k;
						int threadID$k = threadID$index$k;
						boolean[] var67 = state.flips[k];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[k], 1,
							(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
										var67[var76] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[k]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							state.bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			if(!state.fixedFlag$sample23)
				parallelFor(state.RNG$, 1, state.coins, 1,
					(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
								inferSample23(i$var22, threadID$i$var22, RNG$1);
					}
				);

		} else {
			if(!state.fixedFlag$sample23)
				parallelFor(state.RNG$, 1, state.coins, 1,
					(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
								inferSample23(i$var22, threadID$i$var22, RNG$1);
					}
				);

			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1) {
						if(!state.constrainedFlag$sample23[(i$var22 - 1)])
							drawValueSample23(i$var22, threadID$i$var22, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$beta = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$var10 = Double.NaN;
		if(!state.fixedProbFlag$sample23)
			state.logProbability$var23 = Double.NaN;
		state.logProbability$bernoulli1[0] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$sample50[0] = Double.NaN;
		for(int k = 1; k < state.coins; k += 1)
			state.logProbability$bernoulli2[(k - 1)] = Double.NaN;
		if(!state.fixedProbFlag$sample78) {
			for(int k = 1; k < state.coins; k += 1)
				state.logProbability$sample78[(k - 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < state.constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
			state.constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(state.fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void propagateObservedValues() {
		parallelFor(state.RNG$, 0, state.length$flipsMeasured.length, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[] target = state.intermediateFlips[l];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[l], 1,
							(int forStart$m$var102, int forEnd$m$var102, int threadID$m$var102, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int m$var102 = forStart$m$var102; m$var102 < forEnd$m$var102; m$var102 += 1)
										target[m$var102] = state.flipsMeasured[l][m$var102];
							}
						);
					}
			}
		);
		for(int i$var115 = (state.coins - 1); i$var115 >= 0; i$var115 -= 1) {
			boolean[] cv$source1 = state.intermediateFlips[(state.coins - (i$var115 + 1))];
			boolean[] cv$target1 = state.flips[i$var115];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    boolean[][] intermediateFlips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "    \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int l:[0..coins)) {\n"
		     + "        boolean[] source = flipsMeasured[l];\n"
		     + "        int noFlips = source.length;\n"
		     + "        boolean[] target = new boolean[noFlips];\n"
		     + "        intermediateFlips[l] = target;\n"
		     + "        \n"
		     + "        for(int m:[0..noFlips))\n"
		     + "            target[m] = source[m];\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = intermediateFlips[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}