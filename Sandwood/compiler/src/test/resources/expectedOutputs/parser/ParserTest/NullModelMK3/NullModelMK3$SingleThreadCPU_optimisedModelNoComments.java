package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.NullModelMK3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.NullModelMK3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class NullModelMK3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public NullModelMK3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.bias = (state.min + ((1.0 - state.min) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	private final void inferSample10() {
		state.constrainedFlag$sample10 = false;
		double cv$originalValue = state.bias;
		double cv$var = ((state.bias * state.bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.bias);
		state.constrainedFlag$sample10 = true;
		double cv$originalProbability = (DistributionSampling.logProbabilityBinomial(state.positiveCount, state.bias, state.observedSampleCount) + (((state.min <= state.bias) && (state.bias < 1.0))?(-Math.log((1.0 - state.min))):Double.NEGATIVE_INFINITY));
		state.bias = cv$proposedValue;
		state.constrainedFlag$sample10 = true;
		double cv$ratio = ((DistributionSampling.logProbabilityBinomial(state.positiveCount, cv$proposedValue, state.observedSampleCount) + (((state.min <= cv$proposedValue) && (cv$proposedValue < 1.0))?(-Math.log((1.0 - state.min))):Double.NEGATIVE_INFINITY)) - cv$originalProbability);
		if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
			state.bias = cv$originalValue;
	}

	private final void logProbabilityValue$sample10() {
		if(!state.fixedProbFlag$sample10) {
			double cv$distributionAccumulator = (((state.min <= state.bias) && (state.bias < 1.0))?(-Math.log((1.0 - state.min))):Double.NEGATIVE_INFINITY);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!state.fixedProbFlag$sample12) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.positiveCount, state.bias, state.observedSampleCount);
			state.logProbability$binomial = cv$distributionAccumulator;
			state.logProbability$positiveCount = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample12 = state.fixedFlag$sample10;
		} else {
			state.logProbability$binomial = state.logProbability$positiveCount;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$positiveCount);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$positiveCount);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample10)
			state.bias = (state.min + ((1.0 - state.min) * DistributionSampling.sampleUniform(state.RNG$)));
		state.positiveCount = DistributionSampling.sampleBinomial(state.RNG$, state.bias, state.observedSampleCount);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias = (state.min + ((1.0 - state.min) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.bias = (state.min + ((1.0 - state.min) * DistributionSampling.sampleUniform(state.RNG$)));
		state.positiveCount = DistributionSampling.sampleBinomial(state.RNG$, state.bias, state.observedSampleCount);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.bias = (state.min + ((1.0 - state.min) * DistributionSampling.sampleUniform(state.RNG$)));
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.bias = (state.min + ((1.0 - state.min) * DistributionSampling.sampleUniform(state.RNG$)));
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
		if(!state.fixedProbFlag$sample10)
			state.logProbability$bias = Double.NaN;
		state.logProbability$binomial = 0.0;
		if(!state.fixedProbFlag$sample12)
			state.logProbability$positiveCount = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.min = ((state.eta * 4.0) / 5.0);
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void propagateObservedValues() {
		state.positiveCount = state.observedPositiveCount;
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
		     + "public model NullModelMK3(double eta, int observedSampleCount, int observedPositiveCount) {\n"
		     + "        double min = eta * 4.0/5.0;    \n"
		     + "        double bias = new Uniform(min, 1.0).sample();\n"
		     + "        \n"
		     + "        //Construct a binomial\n"
		     + "        Binomial binomial = new Binomial(bias, observedSampleCount);\n"
		     + "                \n"
		     + "        //Sample from it\n"
		     + "        int positiveCount = binomial.sample();\n"
		     + "        \n"
		     + "        //Link the sampled values to the observed values\n"
		     + "        positiveCount.observe(observedPositiveCount);\n"
		     + "}";
	}
}