package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK19$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK19.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK19$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK19$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample10() {
		state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias[0][0] = state.q;
	}

	private final void drawValueSample16() {
		state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias[0][1] = state.t;
	}

	private final void inferSample10() {
		state.constrainedFlag$sample10 = false;
		int cv$sum = 0;
		int cv$count = 0;
		if((0 == state.b)) {
			for(int var46 = 0; var46 < state.samples; var46 += 1) {
				state.constrainedFlag$sample10 = true;
				cv$count = (cv$count + 1);
				if(state.flips[var46])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample10) {
			state.q = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
			state.bias[0][0] = state.q;
		}
	}

	private final void inferSample16() {
		state.constrainedFlag$sample16 = false;
		int cv$sum = 0;
		int cv$count = 0;
		if((1 == state.b)) {
			for(int var46 = 0; var46 < state.samples; var46 += 1) {
				state.constrainedFlag$sample16 = true;
				cv$count = (cv$count + 1);
				if(state.flips[var46])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample16) {
			state.t = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
			state.bias[0][1] = state.t;
		}
	}

	private final void logProbabilityValue$sample10() {
		if(!state.fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.q, 1.0, 1.0);
			state.logProbability$q = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample10 = state.fixedFlag$sample10;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$q);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$q);
			if(state.fixedFlag$sample10)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$q);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!state.fixedProbFlag$sample16) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.t, 1.0, 1.0);
			state.logProbability$t = cv$distributionAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample16 = state.fixedFlag$sample16;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$t);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$t);
			if(state.fixedFlag$sample16)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$t);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!state.fixedProbFlag$sample48) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < state.samples; var46 += 1) {
				double[] inner = state.bias[0];
				inner[0] = state.q;
				double var34 = inner[state.b];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var34) && (var34 <= 1.0))?Math.log((state.flips[var46]?var34:(1.0 - var34))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var47 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample48 = (state.fixedFlag$sample10 && state.fixedFlag$sample16);
		} else {
			state.logProbability$bernoulli = state.logProbability$var47;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var47);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var47);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var47);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		if(!state.fixedFlag$sample10)
			inner[0] = state.q;
		if(!state.fixedFlag$sample16)
			state.bias[0][1] = state.t;
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						state.flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, inner[state.b]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias[0][0] = state.q;
		state.bias[0][1] = state.t;
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		double[] inner = state.bias[0];
		inner[0] = state.q;
		state.bias[0][1] = state.t;
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						state.flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, inner[state.b]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample10)
			state.bias[0][0] = state.q;
		if(!state.fixedFlag$sample16)
			state.bias[0][1] = state.t;
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample10)
			state.q = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample16)
			state.t = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.bias[0][0] = state.q;
		state.bias[0][1] = state.t;
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample10)
				inferSample10();
			if(!state.fixedFlag$sample16)
				inferSample16();
		} else {
			if(!state.fixedFlag$sample16)
				inferSample16();
			if(!state.fixedFlag$sample10)
				inferSample10();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample10)
			drawValueSample10();
		if(!state.constrainedFlag$sample16)
			drawValueSample16();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample10)
			state.logProbability$q = Double.NaN;
		if(!state.fixedProbFlag$sample16)
			state.logProbability$t = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample48)
			state.logProbability$var47 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(state.fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		state.bias[0][0] = state.q;
		state.bias[0][1] = state.t;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK19(int samples, int a, int b, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    \n"
		     + "    double[][] bias = new double[1][];\n"
		     + "    private double[] inner = new double[2];\n"
		     + "    inner[0] = q;\n"
		     + "    bias[0] = inner;\n"
		     + "    bias[0][1] = t;\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(inner[b]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}