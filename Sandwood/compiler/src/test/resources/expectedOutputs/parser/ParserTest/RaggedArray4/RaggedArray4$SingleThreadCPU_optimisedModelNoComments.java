package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var45$stateProbabilityGlobal;
		double[] cv$var48$countGlobal;

		@Override
		public final void allocateScratch() {
			cv$var45$stateProbabilityGlobal = new double[2];
			cv$var48$countGlobal = new double[3];
		}
	}


	public RaggedArray4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample47() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
	}

	private final void drawValueSample50() {
		int lengthCV$a$48_4 = -1;
		if((0 == state.y))
			lengthCV$a$48_4 = 2;
		if((1 == state.y))
			lengthCV$a$48_4 = 3;
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_4, state.d);
	}

	private final void inferSample47() {
		state.constrainedFlag$sample47 = false;
		{
			state.y = 0;
			double cv$accumulatedProbabilities = (((0.0 <= state.b[0]) && (state.b[0] <= 1.0))?Math.log(state.b[0]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample50 || state.constrainedFlag$sample50)) {
				state.constrainedFlag$sample47 = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(state.d, state.a[0], 2) + cv$accumulatedProbabilities);
			}
			scratch.cv$var45$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.y = 1;
		double cv$accumulatedProbabilities = (((0.0 <= state.b[1]) && (state.b[1] <= 1.0))?Math.log(state.b[1]):Double.NEGATIVE_INFINITY);
		if((state.fixedFlag$sample50 || state.constrainedFlag$sample50)) {
			state.constrainedFlag$sample47 = true;
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityDirichlet(state.d, state.a[1], 3) + cv$accumulatedProbabilities);
		}
		scratch.cv$var45$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample47) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var45$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var45$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var45$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var45$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var45$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var45$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var45$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var45$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var45$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.y = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var45$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample50() {
		state.constrainedFlag$sample50 = false;
		int lengthCV$a$48_2 = -1;
		if((0 == state.y))
			lengthCV$a$48_2 = 2;
		if((1 == state.y))
			lengthCV$a$48_2 = 3;
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$48_2; cv$loopIndex += 1)
			scratch.cv$var48$countGlobal[cv$loopIndex] = 0.0;
		for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1) {
			state.constrainedFlag$sample50 = true;
			scratch.cv$var48$countGlobal[state.obs[var61]] = (scratch.cv$var48$countGlobal[state.obs[var61]] + 1.0);
		}
		if(state.constrainedFlag$sample50) {
			int lengthCV$a$48_3 = -1;
			if((0 == state.y))
				lengthCV$a$48_3 = 2;
			if((1 == state.y))
				lengthCV$a$48_3 = 3;
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.a[state.y], scratch.cv$var48$countGlobal, state.d, lengthCV$a$48_3);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$distributionAccumulator = (((((0.0 <= state.y) && (state.y < 2)) && (0.0 <= state.b[state.y])) && (state.b[state.y] <= 1.0))?Math.log(state.b[state.y]):Double.NEGATIVE_INFINITY);
			state.logProbability$y = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$y);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$y);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!state.fixedProbFlag$sample50) {
			int lengthCV$a$48_5 = -1;
			if((0 == state.y))
				lengthCV$a$48_5 = 2;
			if((1 == state.y))
				lengthCV$a$48_5 = 3;
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$48_5);
			state.logProbability$d = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample50)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample50 = (state.fixedFlag$sample50 && state.fixedFlag$sample47);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$d);
			if(state.fixedFlag$sample50)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$d);
		}
	}

	private final void logProbabilityValue$sample64() {
		if(!state.fixedProbFlag$sample64) {
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1) {
				int cv$sampleValue = state.obs[var61];
				int lengthCV$a$48_6 = -1;
				if((0 == state.y))
					lengthCV$a$48_6 = 2;
				if((1 == state.y))
					lengthCV$a$48_6 = 3;
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$48_6)) && (0 < lengthCV$a$48_6)) && (0.0 <= state.d[cv$sampleValue])) && (state.d[cv$sampleValue] <= 1.0))?Math.log(state.d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$var62 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample64 = state.fixedFlag$sample50;
		} else {
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var62);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var62);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var62);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		if(!state.fixedFlag$sample50) {
			int lengthCV$a$48_7 = -1;
			if((0 == state.y))
				lengthCV$a$48_7 = 2;
			if((1 == state.y))
				lengthCV$a$48_7 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_7, state.d);
		}
		int lengthCV$a$48_8 = -1;
		if((0 == state.y))
			lengthCV$a$48_8 = 2;
		if((1 == state.y))
			lengthCV$a$48_8 = 3;
		for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1)
			state.obs[var61] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$48_8);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		if(!state.fixedFlag$sample50) {
			int lengthCV$a$48_13 = -1;
			if((0 == state.y))
				lengthCV$a$48_13 = 2;
			if((1 == state.y))
				lengthCV$a$48_13 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_13, state.d);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		if(!state.fixedFlag$sample50) {
			int lengthCV$a$48_9 = -1;
			if((0 == state.y))
				lengthCV$a$48_9 = 2;
			if((1 == state.y))
				lengthCV$a$48_9 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_9, state.d);
		}
		int lengthCV$a$48_10 = -1;
		if((0 == state.y))
			lengthCV$a$48_10 = 2;
		if((1 == state.y))
			lengthCV$a$48_10 = 3;
		for(int var61 = 0; var61 < state.length$obs_measured; var61 += 1)
			state.obs[var61] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$48_10);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		if(!state.fixedFlag$sample50) {
			int lengthCV$a$48_11 = -1;
			if((0 == state.y))
				lengthCV$a$48_11 = 2;
			if((1 == state.y))
				lengthCV$a$48_11 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_11, state.d);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample47)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.b, 2);
		if(!state.fixedFlag$sample50) {
			int lengthCV$a$48_12 = -1;
			if((0 == state.y))
				lengthCV$a$48_12 = 2;
			if((1 == state.y))
				lengthCV$a$48_12 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$48_12, state.d);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample47)
				inferSample47();
			if(!state.fixedFlag$sample50)
				inferSample50();
		} else {
			if(!state.fixedFlag$sample50)
				inferSample50();
			if(!state.fixedFlag$sample47)
				inferSample47();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample47)
			drawValueSample47();
		if(!state.constrainedFlag$sample50)
			drawValueSample50();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample50)
			state.logProbability$d = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample64)
			state.logProbability$var62 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		double[] var5 = state.a[0];
		var5[0] = 0.4;
		var5[1] = 0.6;
		double[] var18 = state.a[1];
		var18[0] = 0.2;
		var18[1] = 0.3;
		var18[2] = 0.5;
		state.b[0] = 0.35;
		state.b[1] = 0.65;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample50)
			logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample50();
		logProbabilityValue$sample64();
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
		     + " \n"
		     + " package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray4(int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[] b = { 0.35, 0.65 };\n"
		     + "    int y = categorical(b).sample();\n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}