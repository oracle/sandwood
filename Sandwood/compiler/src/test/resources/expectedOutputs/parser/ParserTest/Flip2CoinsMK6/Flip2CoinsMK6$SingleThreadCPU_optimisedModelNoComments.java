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
		state.bias[j] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample18(int j) {
		state.constrainedFlag$sample18[j] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var30 = 0; var30 < state.shape[j]; var30 += 1) {
			state.constrainedFlag$sample18[j] = true;
			cv$count = (cv$count + 1);
			if(state.flips[j][var30])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample18[j])
			state.bias[j] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample18() {
		if(!state.fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[j], 1.0, 1.0);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$beta[j] = cv$distributionAccumulator;
				state.logProbability$sample18[j] = cv$distributionAccumulator;
			}
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample18)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample18 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = state.logProbability$sample18[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$beta[j] = cv$rvAccumulator;
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
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var30 = 0; var30 < state.shape[j]; var30 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias[j]) && (state.bias[j] <= 1.0))?Math.log((state.flips[j][var30]?state.bias[j]:(1.0 - state.bias[j]))):Double.NEGATIVE_INFINITY));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli[j] = cv$sampleAccumulator;
				state.logProbability$sample31[j] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = state.logProbability$sample31[j];
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
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[j] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			boolean[] var21 = state.flips[j];
			for(int var30 = 0; var30 < state.shape[j]; var30 += 1)
				var21[var30] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample18) {
			for(int j = 0; j < state.coins; j += 1)
				state.bias[j] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.fixedFlag$sample18)
				state.bias[j] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			boolean[] var21 = state.flips[j];
			for(int var30 = 0; var30 < state.shape[j]; var30 += 1)
				var21[var30] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample18) {
			for(int j = 0; j < state.coins; j += 1)
				state.bias[j] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample18) {
			for(int j = 0; j < state.coins; j += 1)
				state.bias[j] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample18) {
			if(state.system$gibbsForward) {
				for(int j = 0; j < state.coins; j += 1)
					inferSample18(j);
			} else {
				for(int j = (state.coins - 1); j >= 0; j -= 1)
					inferSample18(j);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int j = 0; j < state.coins; j += 1) {
			if(!state.constrainedFlag$sample18[j])
				drawValueSample18(j);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$beta[j] = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample18) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample18[j] = Double.NaN;
		}
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[j] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample31) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample31[j] = Double.NaN;
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