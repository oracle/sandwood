package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK15$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK15.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK15$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK15$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample8() {
		state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1)
			state.bias = state.b;
		else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			state.bias = (state.c[0] + state.c[1]);
		}
	}

	private final void inferSample8() {
		state.constrainedFlag$sample8 = false;
		double cv$originalValue = state.b;
		double cv$originalProbability;
		double cv$var = ((state.b * state.b) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			if(state.guard1) {
				for(int var46 = 0; var46 < state.samples; var46 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= state.b) && (state.b <= 1.0))?Math.log((state.flips[var46]?state.b:(1.0 - state.b))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				double reduceVar$var33$16 = ((state.b / 2) + state.c[1]);
				for(int var46 = 0; var46 < state.samples; var46 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= reduceVar$var33$16) && (reduceVar$var33$16 <= 1.0))?Math.log((state.flips[var46]?reduceVar$var33$16:(1.0 - reduceVar$var33$16))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample8) {
			state.b = cv$proposedValue;
			if(state.guard1)
				state.bias = cv$proposedValue;
			else {
				state.c[0] = (cv$proposedValue / 2);
				state.c[1] = (cv$proposedValue / 2);
				state.bias = (state.c[0] + state.c[1]);
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if(state.guard1) {
				for(int var46 = 0; var46 < state.samples; var46 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var46]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				double reduceVar$var33$16 = ((cv$proposedValue / 2) + state.c[1]);
				for(int var46 = 0; var46 < state.samples; var46 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= reduceVar$var33$16) && (reduceVar$var33$16 <= 1.0))?Math.log((state.flips[var46]?reduceVar$var33$16:(1.0 - reduceVar$var33$16))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.b = cv$originalValue;
				if(state.guard1)
					state.bias = cv$originalValue;
				else {
					state.c[0] = (cv$originalValue / 2);
					state.c[1] = (cv$originalValue / 2);
					state.bias = (state.c[0] + state.c[1]);
				}
			}
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < state.samples; var46 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[var46]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var47 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample50 = state.fixedFlag$sample8;
		} else {
			state.logProbability$bernoulli = state.logProbability$var47;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var47);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var47);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var47);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!state.fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			state.logProbability$b = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample8 = state.fixedFlag$sample8;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample8) {
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(state.guard1)
				state.bias = state.b;
			else {
				state.c[0] = (state.b / 2);
				state.c[1] = (state.b / 2);
				state.bias = (state.c[0] + state.c[1]);
			}
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						state.flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			state.bias = (state.c[0] + state.c[1]);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			state.bias = (state.c[0] + state.c[1]);
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						state.flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample8) {
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(state.guard1)
				state.bias = state.b;
			else {
				state.c[0] = (state.b / 2);
				state.c[1] = (state.b / 2);
				state.bias = (state.c[0] + state.c[1]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			state.bias = (state.c[0] + state.c[1]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample8)
			inferSample8();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample8)
			drawValueSample8();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$b = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$var47 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample50();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var58 = (state.samples - 1); i$var58 >= 0; i$var58 -= 1)
			state.flips[i$var58] = state.flipsMeasured[i$var58];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.c[1] = (state.b / 2);
			state.bias = (state.c[0] + state.c[1]);
		}
	}

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
		     + "public model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[2];\n"
		     + "      c[0] = b/2;\n"
		     + "      c[1] = b/2;\n"
		     + "      bias = reduce(c, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}