package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var77$stateProbabilityGlobal;
		double[] cv$var80$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var77$stateProbabilityGlobal = new double[2];
			cv$var80$stateProbabilityGlobal = new double[3];
		}
	}


	public RaggedArray2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample81() {
		state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		state.p = state.b[state.y][state.i];
	}

	private final void drawValueSample84() {
		int lengthCV$a$82_13 = -1;
		if((0 == state.y))
			lengthCV$a$82_13 = 2;
		if((1 == state.y))
			lengthCV$a$82_13 = 3;
		state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_13);
		state.p = state.b[state.y][state.i];
	}

	private final void inferSample81() {
		state.constrainedFlag$sample81 = false;
		{
			state.y = 0;
			state.p = state.b[0][state.i];
			double cv$accumulatedProbabilities = (((0.0 <= state.c[0]) && (state.c[0] <= 1.0))?Math.log(state.c[0]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample84 || state.constrainedFlag$sample84)) {
				state.constrainedFlag$sample81 = true;
				double[] var78 = state.a[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.i) && (state.i < 2)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
				state.constrainedFlag$sample81 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var77$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.y = 1;
		state.p = state.b[1][state.i];
		double cv$accumulatedProbabilities = (((0.0 <= state.c[1]) && (state.c[1] <= 1.0))?Math.log(state.c[1]):Double.NEGATIVE_INFINITY);
		if((state.fixedFlag$sample84 || state.constrainedFlag$sample84)) {
			state.constrainedFlag$sample81 = true;
			double[] var78 = state.a[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.i) && (state.i < 3)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
			state.constrainedFlag$sample81 = true;
			cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var77$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample81) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var77$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var77$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var77$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var77$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var77$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var77$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var77$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var77$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.y = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var77$stateProbabilityGlobal, 2);
			state.p = state.b[state.y][state.i];
		}
	}

	private final void inferSample84() {
		state.constrainedFlag$sample84 = false;
		int lengthCV$a$82_11 = -1;
		if((0 == state.y))
			lengthCV$a$82_11 = 2;
		if((1 == state.y))
			lengthCV$a$82_11 = 3;
		int cv$numStates = Math.max(0, lengthCV$a$82_11);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			state.i = cv$valuePos;
			state.p = state.b[state.y][cv$valuePos];
			double[] var78 = state.a[state.y];
			int lengthCV$a$82_12 = -1;
			if((0 == state.y))
				lengthCV$a$82_12 = 2;
			if((1 == state.y))
				lengthCV$a$82_12 = 3;
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$a$82_12) && (0 < lengthCV$a$82_12)) && (0.0 <= var78[cv$valuePos])) && (var78[cv$valuePos] <= 1.0))?Math.log(var78[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1) {
				state.constrainedFlag$sample84 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var80$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample84) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var80$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var80$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var80$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var80$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var80$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var80$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var80$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var80$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var80$stateProbabilityGlobal, cv$numStates);
			state.p = state.b[state.y][state.i];
		}
	}

	private final void logProbabilityValue$sample100() {
		if(!state.fixedProbFlag$sample100) {
			double cv$sampleAccumulator = 0.0;
			for(int var95 = 0; var95 < state.length$obs_measured; var95 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var95]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY));
			state.logProbability$var96 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample100 = (state.fixedFlag$sample81 && state.fixedFlag$sample84);
		} else {
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var96);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var96);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var96);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!state.fixedProbFlag$sample81) {
			double cv$distributionAccumulator = (((((0.0 <= state.y) && (state.y < 2)) && (0.0 <= state.c[state.y])) && (state.c[state.y] <= 1.0))?Math.log(state.c[state.y]):Double.NEGATIVE_INFINITY);
			state.logProbability$y = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample81)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample81 = state.fixedFlag$sample81;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$y);
			if(state.fixedFlag$sample81)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$y);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!state.fixedProbFlag$sample84) {
			double[] var78 = state.a[state.y];
			int lengthCV$a$82_14 = -1;
			if((0 == state.y))
				lengthCV$a$82_14 = 2;
			if((1 == state.y))
				lengthCV$a$82_14 = 3;
			double cv$distributionAccumulator = ((((((0.0 <= state.i) && (state.i < lengthCV$a$82_14)) && (0 < lengthCV$a$82_14)) && (0.0 <= var78[state.i])) && (var78[state.i] <= 1.0))?Math.log(var78[state.i]):Double.NEGATIVE_INFINITY);
			state.logProbability$i = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample84 = (state.fixedFlag$sample84 && state.fixedFlag$sample81);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$i);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$i);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		if(!state.fixedFlag$sample84) {
			int lengthCV$a$82_15 = -1;
			if((0 == state.y))
				lengthCV$a$82_15 = 2;
			if((1 == state.y))
				lengthCV$a$82_15 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_15);
		}
		if((!state.fixedFlag$sample81 || !state.fixedFlag$sample84))
			state.p = state.b[state.y][state.i];
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						state.obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, state.p);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		if(!state.fixedFlag$sample84) {
			int lengthCV$a$82_19 = -1;
			if((0 == state.y))
				lengthCV$a$82_19 = 2;
			if((1 == state.y))
				lengthCV$a$82_19 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_19);
		}
		state.p = state.b[state.y][state.i];
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		if(!state.fixedFlag$sample84) {
			int lengthCV$a$82_16 = -1;
			if((0 == state.y))
				lengthCV$a$82_16 = 2;
			if((1 == state.y))
				lengthCV$a$82_16 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_16);
		}
		state.p = state.b[state.y][state.i];
		parallelFor(state.RNG$, 0, state.length$obs_measured, 1,
			(int forStart$var95, int forEnd$var95, int threadID$var95, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var95 = forStart$var95; var95 < forEnd$var95; var95 += 1)
						state.obs[var95] = DistributionSampling.sampleBernoulli(RNG$1, state.p);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		if(!state.fixedFlag$sample84) {
			int lengthCV$a$82_17 = -1;
			if((0 == state.y))
				lengthCV$a$82_17 = 2;
			if((1 == state.y))
				lengthCV$a$82_17 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_17);
		}
		if((!state.fixedFlag$sample81 || !state.fixedFlag$sample84))
			state.p = state.b[state.y][state.i];
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample81)
			state.y = DistributionSampling.sampleCategorical(state.RNG$, state.c, 2);
		if(!state.fixedFlag$sample84) {
			int lengthCV$a$82_18 = -1;
			if((0 == state.y))
				lengthCV$a$82_18 = 2;
			if((1 == state.y))
				lengthCV$a$82_18 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$82_18);
		}
		state.p = state.b[state.y][state.i];
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample81)
				inferSample81();
			if(!state.fixedFlag$sample84)
				inferSample84();
		} else {
			if(!state.fixedFlag$sample84)
				inferSample84();
			if(!state.fixedFlag$sample81)
				inferSample81();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample81)
			drawValueSample81();
		if(!state.constrainedFlag$sample84)
			drawValueSample84();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample81)
			state.logProbability$y = Double.NaN;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$i = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample100)
			state.logProbability$var96 = Double.NaN;
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
		double[] var37 = state.b[0];
		var37[0] = 0.2;
		var37[1] = 0.8;
		double[] var50 = state.b[1];
		var50[0] = 0.4;
		var50[1] = 0.2;
		var50[2] = 0.6;
		state.c[0] = 0.35;
		state.c[1] = 0.65;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample81)
			logProbabilityValue$sample81();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample81();
		logProbabilityValue$sample84();
		logProbabilityValue$sample100();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.obs.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.obs[cv$index1] = state.obs_measured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		state.p = state.b[state.y][state.i];
	}

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
		     + "public model RaggedArray2(boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    double[] c = { 0.35, 0.65 };\n"
		     + "    int y = categorical(c).sample();\n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}