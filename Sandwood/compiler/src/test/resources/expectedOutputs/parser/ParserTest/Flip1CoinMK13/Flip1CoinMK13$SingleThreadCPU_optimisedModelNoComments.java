package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK13$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK13.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK13$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK13$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample9() {
		state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1)
			state.bias = state.b;
		else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
	}

	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		double cv$originalValue = state.b;
		double cv$originalProbability;
		double cv$var = (((state.b < 0)?(-state.b):state.b) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.b);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			if(state.guard1) {
				for(int var35 = 0; var35 < state.samples; var35 += 1) {
					state.constrainedFlag$sample9 = true;
					cv$accumulatedProbabilities = ((((0.0 <= state.b) && (state.b <= 1.0))?Math.log((state.flips[var35]?state.b:(1.0 - state.b))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				if(state.guard2) {
					double traceTempVariable$bias$5_2 = (state.b / 2);
					for(int var35 = 0; var35 < state.samples; var35 += 1) {
						state.constrainedFlag$sample9 = true;
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				} else {
					double traceTempVariable$bias$6_2 = (state.b / 3);
					for(int var35 = 0; var35 < state.samples; var35 += 1) {
						state.constrainedFlag$sample9 = true;
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample9) {
			state.b = cv$proposedValue;
			if(state.guard1)
				state.bias = cv$proposedValue;
			else {
				if(state.guard2)
					state.bias = (cv$proposedValue / 2);
				else
					state.bias = (cv$proposedValue / 3);
			}
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, 1.0, 1.0);
			if(state.guard1) {
				for(int var35 = 0; var35 < state.samples; var35 += 1) {
					state.constrainedFlag$sample9 = true;
					cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flips[var35]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				if(state.guard2) {
					double traceTempVariable$bias$5_2 = (cv$proposedValue / 2);
					for(int var35 = 0; var35 < state.samples; var35 += 1) {
						state.constrainedFlag$sample9 = true;
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$5_2) && (traceTempVariable$bias$5_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$5_2:(1.0 - traceTempVariable$bias$5_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				} else {
					double traceTempVariable$bias$6_2 = (cv$proposedValue / 3);
					for(int var35 = 0; var35 < state.samples; var35 += 1) {
						state.constrainedFlag$sample9 = true;
						cv$accumulatedProbabilities = ((((0.0 <= traceTempVariable$bias$6_2) && (traceTempVariable$bias$6_2 <= 1.0))?Math.log((state.flips[var35]?traceTempVariable$bias$6_2:(1.0 - traceTempVariable$bias$6_2))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.b = cv$originalValue;
				if(state.guard1)
					state.bias = cv$originalValue;
				else {
					if(state.guard2)
						state.bias = (cv$originalValue / 2);
					else
						state.bias = (cv$originalValue / 3);
				}
			}
		}
	}

	private final void logProbabilityValue$sample40() {
		if(!state.fixedProbFlag$sample40) {
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < state.samples; var35 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[var35]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var36 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample40 = state.fixedFlag$sample9;
		} else {
			state.logProbability$bernoulli = state.logProbability$var36;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var36);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var36);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var36);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.b, 1.0, 1.0);
			state.logProbability$b = cv$distributionAccumulator;
			boolean cv$guard$bias = false;
			if(state.guard1)
				state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			else {
				if(state.guard2) {
					cv$guard$bias = true;
					state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
				}
				if((!state.guard2 && !cv$guard$bias))
					state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			}
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			boolean cv$guard$bias = false;
			if(state.guard1)
				state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
			else {
				if(state.guard2) {
					cv$guard$bias = true;
					state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
				}
				if((!state.guard2 && !cv$guard$bias))
					state.logProbability$bias = (state.logProbability$bias + state.logProbability$b);
			}
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9) {
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(state.guard1)
				state.bias = state.b;
			else {
				if(state.guard2)
					state.bias = (state.b / 2);
				else
					state.bias = (state.b / 3);
			}
		}
		for(int var35 = 0; var35 < state.samples; var35 += 1)
			state.flips[var35] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
		for(int var35 = 0; var35 < state.samples; var35 += 1)
			state.flips[var35] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9) {
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			if(state.guard1)
				state.bias = state.b;
			else {
				if(state.guard2)
					state.bias = (state.b / 2);
				else
					state.bias = (state.b / 3);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.b = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(state.guard1) {
			if(!state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample9)
			inferSample9();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$b = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample40)
			state.logProbability$var36 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample40();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - 1); i >= 0; i -= 1)
			state.flips[i] = state.flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(state.guard1) {
			if(state.fixedFlag$sample9)
				state.bias = state.b;
		} else {
			if(state.guard2)
				state.bias = (state.b / 2);
			else
				state.bias = (state.b / 3);
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
		     + "public model Flip1CoinMK13(boolean[] flipsMeasured, boolean guard1, boolean guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double b = beta(1.0, 1).sample();\n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = b;\n"
		     + "    else { \n"
		     + "        if(guard2) {\n"
		     + "            bias = b/2;\n"
		     + "        } else\n"
		     + "            bias = b/3;\n"
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