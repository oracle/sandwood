package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK5$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK5.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK5$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK5$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample9() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample9() {
		state.constrainedFlag$sample9 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var21 = 0; var21 < state.samples1; var21 += 1) {
			state.constrainedFlag$sample9 = true;
			cv$count = (cv$count + 1);
			if(state.flips1[var21])
				cv$sum = (cv$sum + 1);
		}
		for(int var35 = 0; var35 < state.samples2; var35 += 1) {
			state.constrainedFlag$sample9 = true;
			cv$count = (cv$count + 1);
			if(state.flips2[var35])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample9)
			state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample22() {
		if(!state.fixedProbFlag$sample22) {
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < state.samples1; var21 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips1[var21]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli1 = cv$sampleAccumulator;
			state.logProbability$var22 = cv$sampleAccumulator;
			state.logProbability$flips1 = (state.logProbability$flips1 + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample22 = state.fixedFlag$sample9;
		} else {
			state.logProbability$bernoulli1 = state.logProbability$var22;
			state.logProbability$flips1 = (state.logProbability$flips1 + state.logProbability$var22);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var22);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var22);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!state.fixedProbFlag$sample36) {
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < state.samples2; var35 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips2[var35]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli2 = cv$sampleAccumulator;
			state.logProbability$var36 = cv$sampleAccumulator;
			state.logProbability$flips2 = (state.logProbability$flips2 + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample36 = state.fixedFlag$sample9;
		} else {
			state.logProbability$bernoulli2 = state.logProbability$var36;
			state.logProbability$flips2 = (state.logProbability$flips2 + state.logProbability$var36);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var36);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var36);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample9)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int var21 = 0; var21 < state.samples1; var21 += 1)
			state.flips1[var21] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
		for(int var35 = 0; var35 < state.samples2; var35 += 1)
			state.flips2[var35] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample9)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int var21 = 0; var21 < state.samples1; var21 += 1)
			state.flips1[var21] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
		for(int var35 = 0; var35 < state.samples2; var35 += 1)
			state.flips2[var35] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample9)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample9)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
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
		if(!state.fixedProbFlag$sample9)
			state.logProbability$bias = Double.NaN;
		state.logProbability$bernoulli1 = Double.NaN;
		state.logProbability$flips1 = 0.0;
		if(!state.fixedProbFlag$sample22)
			state.logProbability$var22 = Double.NaN;
		state.logProbability$bernoulli2 = Double.NaN;
		state.logProbability$flips2 = 0.0;
		if(!state.fixedProbFlag$sample36)
			state.logProbability$var36 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples1 = state.length$flipsMeasured1;
		state.samples2 = state.length$flipsMeasured2;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample36();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample36();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample36();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var50 = 0; i$var50 < state.samples1; i$var50 += 1)
			state.flips1[i$var50] = state.flipsMeasured1[i$var50];
		for(int i$var68 = 0; i$var68 < ((state.samples2 * 2) - 1); i$var68 += 2)
			state.flips2[(i$var68 / 2)] = state.flipsMeasured2[(i$var68 / 2)];
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
		     + "public model Flip1CoinMK5(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n"
		     + "    int samples1 = flipsMeasured1.length;\n"
		     + "    int samples2 = flipsMeasured2.length;\n"
		     + "        \n"
		     + "    double bias = beta(1.0, 1).sample();\n"
		     + "        \n"
		     + "    Bernoulli bernoulli1 = bernoulli(bias);\n"
		     + "    boolean[] flips1 = bernoulli1.sample(samples1);\n"
		     + "        \n"
		     + "    Bernoulli bernoulli2 = bernoulli(bias);\n"
		     + "    boolean[] flips2 = bernoulli2.sample(samples2);\n"
		     + "\n"
		     + "    for(int i=samples1-1; i>-1; --i)\n"
		     + "        flips1[i].observe(flipsMeasured1[i]);\n"
		     + "\n"
		     + "    for(int i=2*samples2-2; i>=0; i = i-2)\n"
		     + "        flips2[i/2].observe(flipsMeasured2[i/2]);\n"
		     + "}\n"
		     + "";
	}
}