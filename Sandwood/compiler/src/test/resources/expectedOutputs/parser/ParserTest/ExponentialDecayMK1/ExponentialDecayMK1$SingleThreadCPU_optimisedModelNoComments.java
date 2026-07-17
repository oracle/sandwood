package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ExponentialDecayMK1$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ExponentialDecayMK1.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ExponentialDecayMK1$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public ExponentialDecayMK1$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample6() {
		state.rate = DistributionSampling.sampleGamma(state.RNG$, state.a, state.b);
	}

	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var18 = 0; var18 < state.samples; var18 += 1) {
			state.constrainedFlag$sample6 = true;
			cv$sum = (cv$sum + state.decay[var18]);
			cv$count = (cv$count + 1);
		}
		if(state.constrainedFlag$sample6)
			state.rate = Conjugates.sampleConjugateGammaExponential(state.RNG$, state.a, state.b, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample19() {
		if(!state.fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < state.samples; var18 += 1) {
				double cv$sampleValue = state.decay[var18];
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((0.0 <= cv$sampleValue) && !(cv$sampleValue == Double.POSITIVE_INFINITY)) && (0.0 < state.rate))?(Math.log(state.rate) - (state.rate * cv$sampleValue)):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$exponential = cv$sampleAccumulator;
			state.logProbability$var19 = cv$sampleAccumulator;
			state.logProbability$decay = (state.logProbability$decay + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample19 = state.fixedFlag$sample6;
		} else {
			state.logProbability$exponential = state.logProbability$var19;
			state.logProbability$decay = (state.logProbability$decay + state.logProbability$var19);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var19);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!state.fixedProbFlag$sample6) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(state.rate, state.a, state.b);
			state.logProbability$rate = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$rate);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$rate);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample6)
			state.rate = DistributionSampling.sampleGamma(state.RNG$, state.a, state.b);
		for(int var18 = 0; var18 < state.samples; var18 += 1)
			state.decay[var18] = (DistributionSampling.sampleExponential(state.RNG$) / state.rate);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample6)
			state.rate = DistributionSampling.sampleGamma(state.RNG$, state.a, state.b);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample6)
			state.rate = DistributionSampling.sampleGamma(state.RNG$, state.a, state.b);
		for(int var18 = 0; var18 < state.samples; var18 += 1)
			state.decay[var18] = (DistributionSampling.sampleExponential(state.RNG$) / state.rate);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample6)
			state.rate = DistributionSampling.sampleGamma(state.RNG$, state.a, state.b);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample6)
			state.rate = DistributionSampling.sampleGamma(state.RNG$, state.a, state.b);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample6)
			inferSample6();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample6)
			state.logProbability$rate = Double.NaN;
		state.logProbability$exponential = Double.NaN;
		state.logProbability$decay = 0.0;
		if(!state.fixedProbFlag$sample19)
			state.logProbability$var19 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$decayDetected;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.decay.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.decay[cv$index1] = state.decayDetected[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ExponentialDecayMK1(double[] decayDetected, double a, double b) {\n"
		     + "    \n"
		     + "        int samples = decayDetected.length;\n"
		     + "        double rate = gamma(a, b).sample();\n"
		     + "        \n"
		     + "        Exponential exponential = exponential(rate);\n"
		     + "        double[] decay = exponential.sample(samples);\n"
		     + "        decay.observe(decayDetected);\n"
		     + "}";
	}
}