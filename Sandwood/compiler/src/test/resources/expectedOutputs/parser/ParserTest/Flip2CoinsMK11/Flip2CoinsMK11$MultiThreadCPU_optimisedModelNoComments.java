package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK11$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK11.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK11$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK11$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample22(int i$var21, int threadID$cv$i$var21, Rng RNG$) {
		state.bias[i$var21] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample9() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample22(int i$var21, int threadID$cv$i$var21, Rng RNG$) {
		state.constrainedFlag$sample22[(i$var21 - 1)] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var75 = 0; var75 < state.length$flipsMeasured[i$var21]; var75 += 1) {
			state.constrainedFlag$sample22[(i$var21 - 1)] = true;
			cv$count = (cv$count + 1);
			if(state.flips[i$var21][var75])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample22[(i$var21 - 1)])
			state.bias[i$var21] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var48 = 0; var48 < state.length$flipsMeasured[0]; var48 += 1) {
			state.constrainedFlag$sample9 = true;
			cv$count = (cv$count + 1);
			if(state.flips[0][var48])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample9)
			state.bias[0] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample22() {
		if(!state.fixedProbFlag$sample22) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var21 = 1; i$var21 < state.coins; i$var21 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[i$var21], 1.0, 1.0));
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			state.logProbability$var22 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample22)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample22 = state.fixedFlag$sample22;
		} else {
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var22);
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var22);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var22);
			if(state.fixedFlag$sample22)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var22);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!state.fixedProbFlag$sample49) {
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < state.length$flipsMeasured[0]; var48 += 1) {
				double var37 = state.bias[0];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var37) && (var37 <= 1.0))?Math.log((state.flips[0][var48]?var37:(1.0 - var37))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$bernoulli1[0] = cv$sampleAccumulator;
			state.logProbability$sample49[0] = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample49 = (state.fixedFlag$sample9 && state.fixedFlag$sample22);
		} else {
			double cv$rvAccumulator = state.logProbability$sample49[0];
			state.logProbability$bernoulli1[0] = cv$rvAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample77() {
		if(!state.fixedProbFlag$sample77) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var75 = 0; var75 < state.length$flipsMeasured[k]; var75 += 1) {
					double var64 = state.bias[k];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var64) && (var64 <= 1.0))?Math.log((state.flips[k][var75]?var64:(1.0 - var64))):Double.NEGATIVE_INFINITY));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli2[(k - 1)] = cv$sampleAccumulator;
				state.logProbability$sample77[(k - 1)] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample77 = (state.fixedFlag$sample9 && state.fixedFlag$sample22);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < state.coins; k += 1) {
				double cv$rvAccumulator = state.logProbability$sample77[(k - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli2[(k - 1)] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0);
			state.logProbability$beta = (state.logProbability$beta + cv$distributionAccumulator);
			state.logProbability$var9 = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			state.logProbability$beta = (state.logProbability$beta + state.logProbability$var9);
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var9);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var9);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var9);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample22)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
							state.bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var39 = state.flips[j];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var48, int forEnd$var48, int threadID$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var48 = forStart$var48; var48 < forEnd$var48; var48 += 1)
										var39[var48] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[j]);
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
						boolean[] var66 = state.flips[k];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[k], 1,
							(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
										var66[var75] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[k]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample22)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
							state.bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample22)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
							state.bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(state.RNG$, 0, 1, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var39 = state.flips[j];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[j], 1,
							(int forStart$var48, int forEnd$var48, int threadID$var48, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var48 = forStart$var48; var48 < forEnd$var48; var48 += 1)
										var39[var48] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[j]);
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
						boolean[] var66 = state.flips[k];
						parallelFor(RNG$1, 0, state.length$flipsMeasured[k], 1,
							(int forStart$var75, int forEnd$var75, int threadID$var75, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var75 = forStart$var75; var75 < forEnd$var75; var75 += 1)
										var66[var75] = DistributionSampling.sampleBernoulli(RNG$2, state.bias[k]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample22)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
							state.bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample22)
			parallelFor(state.RNG$, 1, state.coins, 1,
				(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
							state.bias[i$var21] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample22)
				parallelFor(state.RNG$, 1, state.coins, 1,
					(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
								inferSample22(i$var21, threadID$i$var21, RNG$1);
					}
				);

		} else {
			if(!state.fixedFlag$sample22)
				parallelFor(state.RNG$, 1, state.coins, 1,
					(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1)
								inferSample22(i$var21, threadID$i$var21, RNG$1);
					}
				);

			if(!state.fixedFlag$sample9)
				inferSample9();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		parallelFor(state.RNG$, 1, state.coins, 1,
			(int forStart$i$var21, int forEnd$i$var21, int threadID$i$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var21 = forStart$i$var21; i$var21 < forEnd$i$var21; i$var21 += 1) {
						if(!state.constrainedFlag$sample22[(i$var21 - 1)])
							drawValueSample22(i$var21, threadID$i$var21, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$beta = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$var9 = Double.NaN;
		if(!state.fixedProbFlag$sample22)
			state.logProbability$var22 = Double.NaN;
		state.logProbability$bernoulli1[0] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample49)
			state.logProbability$sample49[0] = Double.NaN;
		for(int k = 1; k < state.coins; k += 1)
			state.logProbability$bernoulli2[(k - 1)] = Double.NaN;
		if(!state.fixedProbFlag$sample77) {
			for(int k = 1; k < state.coins; k += 1)
				state.logProbability$sample77[(k - 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample22$1 = 0; index$constrainedFlag$sample22$1 < state.constrainedFlag$sample22.length; index$constrainedFlag$sample22$1 += 1)
			state.constrainedFlag$sample22[index$constrainedFlag$sample22$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		if(state.fixedFlag$sample22)
			logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample49();
		logProbabilityValue$sample77();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var88 = (state.coins - 1); i$var88 >= 0; i$var88 -= 1) {
			boolean[] cv$source1 = state.flipsMeasured[(state.coins - (i$var88 + 1))];
			boolean[] cv$target1 = state.flips[i$var88];
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
		     + "public model Flip2CoinsMK11(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "        \n"
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
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = flipsMeasured[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}