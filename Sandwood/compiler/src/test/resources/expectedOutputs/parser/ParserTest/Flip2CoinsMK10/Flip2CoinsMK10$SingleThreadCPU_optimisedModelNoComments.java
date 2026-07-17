package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip2CoinsMK10$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip2CoinsMK10.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip2CoinsMK10$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip2CoinsMK10$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample23(int i) {
		state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample10() {
		state.constrainedFlag$sample10 = false;
		int cv$sum = 0;
		int cv$count = 0;
		if((0 < state.coins)) {
			for(int var47 = 0; var47 < state.shape[0]; var47 += 1) {
				state.constrainedFlag$sample10 = true;
				cv$count = (cv$count + 1);
				if(state.flips[0][var47])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample10)
			state.bias[0] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample23(int i) {
		state.constrainedFlag$sample23[(i - 1)] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var47 = 0; var47 < state.shape[i]; var47 += 1) {
			state.constrainedFlag$sample23[(i - 1)] = true;
			cv$count = (cv$count + 1);
			if(state.flips[i][var47])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample23[(i - 1)])
			state.bias[i] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
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
			for(int i = 1; i < state.coins; i += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[i], 1.0, 1.0));
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

	private final void logProbabilityValue$sample48() {
		if(!state.fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var47 = 0; var47 < state.shape[j]; var47 += 1) {
					double var36 = state.bias[j];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var36) && (var36 <= 1.0))?Math.log((state.flips[j][var47]?var36:(1.0 - var36))):Double.NEGATIVE_INFINITY));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$bernoulli[j] = cv$sampleAccumulator;
				state.logProbability$sample48[j] = cv$sampleAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample48 = (state.fixedFlag$sample10 && state.fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.coins; j += 1) {
				double cv$rvAccumulator = state.logProbability$sample48[j];
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
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var38 = state.flips[j];
			for(int var47 = 0; var47 < state.shape[j]; var47 += 1)
				var38[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int j = 0; j < state.coins; j += 1) {
			boolean[] var38 = state.flips[j];
			for(int var47 = 0; var47 < state.shape[j]; var47 += 1)
				var38[var47] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[j]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample23) {
			for(int i = 1; i < state.coins; i += 1)
				state.bias[i] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			if(!state.fixedFlag$sample23) {
				for(int i = 1; i < state.coins; i += 1)
					inferSample23(i);
			}
		} else {
			if(!state.fixedFlag$sample23) {
				for(int i = (state.coins - 1); i >= 1; i -= 1)
					inferSample23(i);
			}
			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		for(int i = 1; i < state.coins; i += 1) {
			if(!state.constrainedFlag$sample23[(i - 1)])
				drawValueSample23(i);
		}
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
		for(int j = 0; j < state.coins; j += 1)
			state.logProbability$bernoulli[j] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample48) {
			for(int j = 0; j < state.coins; j += 1)
				state.logProbability$sample48[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.coins = state.shape.length;
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
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample48();
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
		     + "public model Flip2CoinsMK10(boolean[][] flipsMeasured, int[] shape) {\n"
		     + "    int coins = shape.length;\n"
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
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}