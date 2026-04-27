package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinArrayCopyPassMK2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinArrayCopyPassMK2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinArrayCopyPassMK2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinArrayCopyPassMK2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i = 0; i < state.samples; i += 1)
			state.bias[(i + 1)] = state.bias[0];
	}

	private final void inferSample10() {
		state.constrainedFlag$sample10 = false;
		int cv$sum = 0;
		int cv$count = 0;
		if((0 < state.samples)) {
			state.constrainedFlag$sample10 = true;
			cv$count = 1;
			if(state.flips[0])
				cv$sum = 1;
		}
		for(int i = 0; i < state.samples; i += 1) {
			int index$i$2_2 = (i + 1);
			if((index$i$2_2 < state.samples)) {
				state.constrainedFlag$sample10 = true;
				cv$count = (cv$count + 1);
				if(state.flips[index$i$2_2])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample10) {
			state.bias[0] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
			for(int i = 0; i < state.samples; i += 1)
				state.bias[(i + 1)] = state.bias[0];
		}
	}

	private final void logProbabilityValue$sample10() {
		if(!state.fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0);
			state.logProbability$var10 = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var10);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var10);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var10);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!state.fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.samples; i += 1) {
				double var29 = state.bias[i];
				double cv$distributionAccumulator = (((0.0 <= var29) && (var29 <= 1.0))?Math.log((state.flips[i]?var29:(1.0 - var29))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$bernoulli[i] = cv$distributionAccumulator;
				state.logProbability$sample31[i] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample31 = state.fixedFlag$sample10;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.samples; i += 1) {
				double cv$rvAccumulator = state.logProbability$sample31[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli[i] = cv$rvAccumulator;
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
		for(int i = 0; i < state.samples; i += 1) {
			if(!state.fixedFlag$sample10)
				state.bias[(i + 1)] = state.bias[0];
			state.flips[i] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i = 0; i < state.samples; i += 1)
			state.bias[(i + 1)] = state.bias[0];
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i = 0; i < state.samples; i += 1) {
			state.bias[(i + 1)] = state.bias[0];
			state.flips[i] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[i]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			for(int i = 0; i < state.samples; i += 1)
				state.bias[(i + 1)] = state.bias[0];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i = 0; i < state.samples; i += 1)
			state.bias[(i + 1)] = state.bias[0];
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample10)
			inferSample10();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$var10 = Double.NaN;
		for(int i = 0; i < state.samples; i += 1)
			state.logProbability$bernoulli[i] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample31) {
			for(int i = 0; i < state.samples; i += 1)
				state.logProbability$sample31[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample31();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample31();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i = 0; i < state.samples; i += 1)
			state.bias[(i + 1)] = state.bias[0];
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
		     + "public model Flip1CoinArrayCopyPassMK2(int samples, boolean[] flipsMeasured) {\n"
		     + "    /*\n"
		     + "     * This is a bad example as there is a separation between the size of \n"
		     + "     * flips measured, and the size of noSamples.\n"
		     + "     */\n"
		     + "    double a = 1.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double[] bias = new double[samples+1];\n"
		     + "    bias[0] = beta(a, b).sample();\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "    for(int i:[0..samples)) {\n"
		     + "        bias[i+1] = bias[0];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[i]);\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "    }\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}