package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray5$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray5.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray5$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public RaggedArray5$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample39() {
		int lengthCV$a$37_13 = -1;
		if((0 == state.y))
			lengthCV$a$37_13 = 2;
		if((1 == state.y))
			lengthCV$a$37_13 = 3;
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_13, state.d);
	}

	private final void inferSample39() {
		state.constrainedFlag$sample39 = false;
		double cv$originalProbability;
		int lengthCV$a$37_11 = -1;
		if((0 == state.y))
			lengthCV$a$37_11 = 2;
		if((1 == state.y))
			lengthCV$a$37_11 = 3;
		int cv$indexToChange = (int)((double)lengthCV$a$37_11 * DistributionSampling.sampleUniform(state.RNG$));
		double cv$movementRatio = ((DistributionSampling.sampleBeta(state.RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = state.d[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - state.d[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = (state.d[cv$loopIndex] * (lengthCV$a$37_11 - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1) {
				double cv$temp = (state.d[cv$loopIndex] * (lengthCV$a$37_11 - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		double cv$rebalanceValue = (cv$proposedDifference / (lengthCV$a$37_11 - 1));
		{
			int lengthCV$a$37_12 = -1;
			if((0 == state.y))
				lengthCV$a$37_12 = 2;
			if((1 == state.y))
				lengthCV$a$37_12 = 3;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_12);
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				state.constrainedFlag$sample39 = true;
				double var38 = state.d[state.y];
				cv$accumulatedProbabilities = ((((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample39) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				state.d[cv$loopIndex] = (state.d[cv$loopIndex] - cv$rebalanceValue);
			state.d[cv$indexToChange] = (state.d[cv$indexToChange] + cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1)
				state.d[cv$loopIndex] = (state.d[cv$loopIndex] - cv$rebalanceValue);
			int lengthCV$a$37_12 = -1;
			if((0 == state.y))
				lengthCV$a$37_12 = 2;
			if((1 == state.y))
				lengthCV$a$37_12 = 3;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_12);
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				state.constrainedFlag$sample39 = true;
				double var38 = state.d[state.y];
				cv$accumulatedProbabilities = ((((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
					state.d[cv$loopIndex] = (state.d[cv$loopIndex] + cv$rebalanceValue);
				state.d[cv$indexToChange] = (state.d[cv$indexToChange] - cv$proposedDifference);
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < lengthCV$a$37_11; cv$loopIndex += 1)
					state.d[cv$loopIndex] = (state.d[cv$loopIndex] + cv$rebalanceValue);
			}
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!state.fixedProbFlag$sample39) {
			int lengthCV$a$37_14 = -1;
			if((0 == state.y))
				lengthCV$a$37_14 = 2;
			if((1 == state.y))
				lengthCV$a$37_14 = 3;
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_14);
			state.logProbability$d = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample39 = state.fixedFlag$sample39;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$d);
			if(state.fixedFlag$sample39)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$d);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!state.fixedProbFlag$sample54) {
			double cv$sampleAccumulator = 0.0;
			for(int var51 = 0; var51 < state.length$obs_measured; var51 += 1) {
				double var38 = state.d[state.y];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var38) && (var38 <= 1.0))?Math.log((state.obs[var51]?var38:(1.0 - var38))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$var52 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample54 = state.fixedFlag$sample39;
		} else {
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var52);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_15 = -1;
			if((0 == state.y))
				lengthCV$a$37_15 = 2;
			if((1 == state.y))
				lengthCV$a$37_15 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_15, state.d);
		}
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						state.obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, state.d[state.y]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_19 = -1;
			if((0 == state.y))
				lengthCV$a$37_19 = 2;
			if((1 == state.y))
				lengthCV$a$37_19 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_19, state.d);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_16 = -1;
			if((0 == state.y))
				lengthCV$a$37_16 = 2;
			if((1 == state.y))
				lengthCV$a$37_16 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_16, state.d);
		}
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var51, int forEnd$var51, int threadID$var51, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var51 = forStart$var51; var51 < forEnd$var51; var51 += 1)
						state.obs[var51] = DistributionSampling.sampleBernoulli(RNG$1, state.d[state.y]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_17 = -1;
			if((0 == state.y))
				lengthCV$a$37_17 = 2;
			if((1 == state.y))
				lengthCV$a$37_17 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_17, state.d);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_18 = -1;
			if((0 == state.y))
				lengthCV$a$37_18 = 2;
			if((1 == state.y))
				lengthCV$a$37_18 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_18, state.d);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample39)
			inferSample39();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample39)
			drawValueSample39();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample39)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample54)
			state.logProbability$var52 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		double[] var6 = state.a[0];
		var6[0] = 0.4;
		var6[1] = 0.6;
		double[] var19 = state.a[1];
		var19[0] = 0.2;
		var19[1] = 0.3;
		var19[2] = 0.5;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample54();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.obs[cv$index1] = state.obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray5(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    boolean[] obs = bernoulli(d[y]).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}