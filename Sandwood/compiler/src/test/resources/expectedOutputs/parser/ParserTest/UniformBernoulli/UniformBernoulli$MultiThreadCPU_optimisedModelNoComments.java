package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.UniformBernoulli$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.UniformBernoulli.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class UniformBernoulli$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public UniformBernoulli$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample5() {
		state.prior = DistributionSampling.sampleUniform(state.RNG$);
	}

	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		double cv$originalValue = state.prior;
		double cv$originalProbability;
		double cv$var = (((state.prior < 0)?(-state.prior):state.prior) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.prior);
		{
			double cv$accumulatedProbabilities = (((0.0 <= state.prior) && (state.prior < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			for(int var18 = 0; var18 < state.length$observed; var18 += 1) {
				state.constrainedFlag$sample5 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.prior) && (state.prior <= 1.0))?Math.log((state.output[var18]?state.prior:(1.0 - state.prior))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample5) {
			state.prior = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			for(int var18 = 0; var18 < state.length$observed; var18 += 1) {
				state.constrainedFlag$sample5 = true;
				cv$accumulatedProbabilities = ((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.output[var18]?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.prior = cv$originalValue;
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!state.fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < state.length$observed; var18 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.prior) && (state.prior <= 1.0))?Math.log((state.output[var18]?state.prior:(1.0 - state.prior))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var19 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample19 = state.fixedFlag$sample5;
		} else {
			state.logProbability$bernoulli = state.logProbability$var19;
			state.logProbability$output = (state.logProbability$output + state.logProbability$var19);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var19);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			double cv$distributionAccumulator = (((0.0 <= state.prior) && (state.prior < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			state.logProbability$prior = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$prior);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$prior);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.prior = DistributionSampling.sampleUniform(state.RNG$);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						state.output[var18] = DistributionSampling.sampleBernoulli(RNG$1, state.prior);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.prior = DistributionSampling.sampleUniform(state.RNG$);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.prior = DistributionSampling.sampleUniform(state.RNG$);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						state.output[var18] = DistributionSampling.sampleBernoulli(RNG$1, state.prior);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.prior = DistributionSampling.sampleUniform(state.RNG$);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.prior = DistributionSampling.sampleUniform(state.RNG$);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample5)
			inferSample5();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$prior = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$output = 0.0;
		if(!state.fixedProbFlag$sample19)
			state.logProbability$var19 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.output.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.output[cv$index1] = state.observed[cv$index1];
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
		     + "public model UniformBernoulli(boolean[] observed) {\n"
		     + "    double a = 0.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double prior = uniform(a, b).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(prior);\n"
		     + "    boolean[] output = bernoulli.sample(observed.length);\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}