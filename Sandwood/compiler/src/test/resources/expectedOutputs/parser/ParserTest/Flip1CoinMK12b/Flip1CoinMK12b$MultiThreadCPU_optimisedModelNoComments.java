package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK12b$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK12b.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK12b$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK12b$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample16() {
		state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias = state.var14;
	}

	private final void drawValueSample28() {
		state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
		state.bias = state.var26;
	}

	private final void drawValueSample35() {
		state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
		state.bias = state.var33;
	}

	private final void inferSample16() {
		state.constrainedFlag$sample16 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var47 = 0; var47 < state.samples; var47 += 1) {
			state.constrainedFlag$sample16 = true;
			cv$count = (cv$count + 1);
			if(state.flips[var47])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample16) {
			state.var14 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
			state.bias = state.var14;
		}
	}

	private final void inferSample28() {
		state.constrainedFlag$sample28 = false;
		double cv$originalValue = (state.var26 * 2);
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var47 = 0; var47 < state.samples; var47 += 1) {
				state.constrainedFlag$sample28 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.var26) && (state.var26 <= 1.0))?Math.log((state.flips[var47]?state.var26:(1.0 - state.var26))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample28) {
			state.var26 = (cv$proposedValue / 2);
			state.bias = state.var26;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			for(int var47 = 0; var47 < state.samples; var47 += 1) {
				state.constrainedFlag$sample28 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.var26) && (state.var26 <= 1.0))?Math.log((state.flips[var47]?state.var26:(1.0 - state.var26))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.var26 = (cv$originalValue / 2);
				state.bias = state.var26;
			}
		}
	}

	private final void inferSample35() {
		state.constrainedFlag$sample35 = false;
		double cv$originalValue = (state.var33 * 3);
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, 1.0, 1.0);
			for(int var47 = 0; var47 < state.samples; var47 += 1) {
				state.constrainedFlag$sample35 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.var33) && (state.var33 <= 1.0))?Math.log((state.flips[var47]?state.var33:(1.0 - state.var33))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample35) {
			state.var33 = (cv$proposedValue / 3);
			state.bias = state.var33;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			for(int var47 = 0; var47 < state.samples; var47 += 1) {
				state.constrainedFlag$sample35 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.var33) && (state.var33 <= 1.0))?Math.log((state.flips[var47]?state.var33:(1.0 - state.var33))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.var33 = (cv$originalValue / 3);
				state.bias = state.var33;
			}
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!state.fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			if(state.guard1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.var14, 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				state.logProbability$sample16 = cv$distributionAccumulator;
			}
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			if(state.guard1)
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			if(state.guard1)
				cv$accumulator = state.logProbability$sample16;
			state.logProbability$var14 = (state.logProbability$var14 + cv$accumulator);
			if(state.guard1)
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			if((!state.guard1 && (state.guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((state.var26 * 2), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				state.logProbability$sample28 = cv$distributionAccumulator;
			}
			state.logProbability$var26 = (state.logProbability$var26 + cv$accumulator);
			if(((state.guard2 <= 2) && !state.guard1))
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			if((!state.guard1 && (state.guard2 <= 2)))
				cv$accumulator = state.logProbability$sample28;
			state.logProbability$var26 = (state.logProbability$var26 + cv$accumulator);
			if(((state.guard2 <= 2) && !state.guard1))
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!state.fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			if((!state.guard1 && !(state.guard2 <= 2))) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta((state.var33 * 3), 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				state.logProbability$sample35 = cv$distributionAccumulator;
			}
			state.logProbability$var33 = (state.logProbability$var33 + cv$accumulator);
			if((!(state.guard2 <= 2) && !state.guard1))
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample35 = state.fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			if((!state.guard1 && !(state.guard2 <= 2)))
				cv$accumulator = state.logProbability$sample35;
			state.logProbability$var33 = (state.logProbability$var33 + cv$accumulator);
			if((!(state.guard2 <= 2) && !state.guard1))
				state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample35)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < state.samples; var47 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[var47]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var48 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample52 = ((state.fixedFlag$sample16 && state.fixedFlag$sample28) && state.fixedFlag$sample35);
		} else {
			state.logProbability$bernoulli = state.logProbability$var48;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var48);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var48);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var48);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16) {
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
				state.bias = state.var14;
			}
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28) {
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
					state.bias = state.var26;
				}
			} else {
				if(!state.fixedFlag$sample35) {
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
					state.bias = state.var33;
				}
			}
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
						state.flips[var47] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16) {
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
				state.bias = state.var14;
			}
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28) {
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
					state.bias = state.var26;
				}
			} else {
				if(!state.fixedFlag$sample35) {
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
					state.bias = state.var33;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16) {
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
				state.bias = state.var14;
			}
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28) {
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
					state.bias = state.var26;
				}
			} else {
				if(!state.fixedFlag$sample35) {
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
					state.bias = state.var33;
				}
			}
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1)
						state.flips[var47] = DistributionSampling.sampleBernoulli(RNG$1, state.bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16) {
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
				state.bias = state.var14;
			}
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28) {
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
					state.bias = state.var26;
				}
			} else {
				if(!state.fixedFlag$sample35) {
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
					state.bias = state.var33;
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16) {
				state.var14 = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
				state.bias = state.var14;
			}
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28) {
					state.var26 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 2);
					state.bias = state.var26;
				}
			} else {
				if(!state.fixedFlag$sample35) {
					state.var33 = (DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0) / 3);
					state.bias = state.var33;
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.guard1) {
			if(!state.fixedFlag$sample16)
				inferSample16();
		} else {
			if((state.guard2 <= 2)) {
				if(!state.fixedFlag$sample28)
					inferSample28();
			} else {
				if(!state.fixedFlag$sample35)
					inferSample35();
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(state.guard1) {
			if(!state.constrainedFlag$sample16)
				drawValueSample16();
		} else {
			if((state.guard2 <= 2)) {
				if(!state.constrainedFlag$sample28)
					drawValueSample28();
			} else {
				if(!state.constrainedFlag$sample35)
					drawValueSample35();
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$var14 = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$sample16 = Double.NaN;
		state.logProbability$var26 = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$sample28 = Double.NaN;
		state.logProbability$var33 = 0.0;
		if(!state.fixedProbFlag$sample35)
			state.logProbability$sample35 = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var48 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - 1); i >= 0; i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample16)
				state.bias = state.var14;
		} else {
			if((state.guard2 <= 2)) {
				if(state.fixedFlag$sample28)
					state.bias = state.var26;
			} else {
				if(state.fixedFlag$sample35)
					state.bias = state.var33;
			}
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
		     + "public model Flip1CoinMK12b(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else {\n"
		     + "        double bias2;\n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias2 = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias2 = beta(1.0, 1).sample()/3;\n"
		     + "        bias = bias2;\n"
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