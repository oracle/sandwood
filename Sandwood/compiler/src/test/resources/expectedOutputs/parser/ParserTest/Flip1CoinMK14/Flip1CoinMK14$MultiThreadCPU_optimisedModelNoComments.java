package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK14$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK14.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK14$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK14$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample8() {
		state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1)
			state.bias = state.b;
		else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
		}
	}

	private final void inferSample8() {
		state.constrainedFlag$sample8 = false;
		double cv$originalValue = state.b;
		double cv$originalProbability;
		double cv$var = (((state.b < 0)?(-state.b):state.b) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			if(state.guard1) {
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= state.b) && (state.b <= 1.0))?Math.log((state.flips[var34]?state.b:(1.0 - state.b))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				double traceTempVariable$var21$5_2 = (state.b / 2);
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var21$5_2) && (traceTempVariable$var21$5_2 <= 1.0))?Math.log((state.flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				state.bias = state.c[0];
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if(state.guard1) {
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var34]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				double traceTempVariable$var21$5_2 = (cv$proposedValue / 2);
				for(int var34 = 0; var34 < state.samples; var34 += 1) {
					state.constrainedFlag$sample8 = true;
					cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$var21$5_2) && (traceTempVariable$var21$5_2 <= 1.0))?Math.log((state.flips[var34]?traceTempVariable$var21$5_2:(1.0 - traceTempVariable$var21$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.b = cv$originalValue;
				if(state.guard1)
					state.bias = cv$originalValue;
				else {
					state.c[0] = (cv$originalValue / 2);
					state.bias = state.c[0];
				}
			}
		}
	}

	private final void logProbabilityValue$sample37() {
		if(!state.fixedProbFlag$sample37) {
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < state.samples; var34 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[var34]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var35 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample37 = state.fixedFlag$sample8;
		} else {
			state.logProbability$bernoulli = state.logProbability$var35;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var35);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var35);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var35);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!state.fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			state.logProbability$b = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			if(state.guard1) {
				cv$guard$bias = true;
				state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			}
			if((!state.guard1 && !cv$guard$bias))
				state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample8 = state.fixedFlag$sample8;
		} else {
			boolean cv$guard$bias = false;
			if(state.guard1) {
				cv$guard$bias = true;
				state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
			}
			if((!state.guard1 && !cv$guard$bias))
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
				state.bias = state.c[0];
			}
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						state.flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
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
			state.bias = state.c[0];
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
			state.bias = state.c[0];
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1)
						state.flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
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
				state.bias = state.c[0];
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
			state.bias = state.c[0];
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
		if(!state.fixedProbFlag$sample37)
			state.logProbability$var35 = Double.NaN;
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
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample37();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - 1); i >= 0; i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample8)
				state.bias = state.b;
		} else {
			state.c[0] = (state.b / 2);
			state.bias = state.c[0];
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
		     + "public model Flip1CoinMK14(boolean[] flipsMeasured, boolean guard1) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else {\n"
		     + "      double[] c = new double[1];\n"
		     + "      c[0] = b/2;\n"
		     + "      bias = c[0];\n"
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