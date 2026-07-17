package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var69$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var69$stateProbabilityGlobal = new double[3];
		}
	}


	public RaggedArray$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample73() {
		int lengthCV$a$71_2 = -1;
		if((0 == state.y))
			lengthCV$a$71_2 = 2;
		if((1 == state.y))
			lengthCV$a$71_2 = 3;
		state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$71_2);
		state.p = state.b[state.y][state.i];
	}

	private final void inferSample73() {
		state.constrainedFlag$sample73 = false;
		int lengthCV$a$71_0 = -1;
		if((0 == state.y))
			lengthCV$a$71_0 = 2;
		if((1 == state.y))
			lengthCV$a$71_0 = 3;
		int cv$numStates = Math.max(0, lengthCV$a$71_0);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			state.i = cv$valuePos;
			state.p = state.b[state.y][cv$valuePos];
			double[] var67 = state.a[state.y];
			int lengthCV$a$71_1 = -1;
			if((0 == state.y))
				lengthCV$a$71_1 = 2;
			if((1 == state.y))
				lengthCV$a$71_1 = 3;
			double cv$accumulatedProbabilities = (((((cv$valuePos < lengthCV$a$71_1) && (0 < lengthCV$a$71_1)) && (0.0 <= var67[cv$valuePos])) && (var67[cv$valuePos] <= 1.0))?Math.log(var67[cv$valuePos]):Double.NEGATIVE_INFINITY);
			for(int var84 = 0; var84 < state.length$obs_measured; var84 += 1) {
				state.constrainedFlag$sample73 = true;
				cv$accumulatedProbabilities = ((((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var84]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var69$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample73) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var69$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var69$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var69$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var69$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var69$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var69$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var69$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var69$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var69$stateProbabilityGlobal, cv$numStates);
			state.p = state.b[state.y][state.i];
		}
	}

	private final void logProbabilityValue$sample73() {
		if(!state.fixedProbFlag$sample73) {
			double[] var67 = state.a[state.y];
			int lengthCV$a$71_3 = -1;
			if((0 == state.y))
				lengthCV$a$71_3 = 2;
			if((1 == state.y))
				lengthCV$a$71_3 = 3;
			double cv$distributionAccumulator = ((((((0.0 <= state.i) && (state.i < lengthCV$a$71_3)) && (0 < lengthCV$a$71_3)) && (0.0 <= var67[state.i])) && (var67[state.i] <= 1.0))?Math.log(var67[state.i]):Double.NEGATIVE_INFINITY);
			state.logProbability$i = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample73)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample73 = state.fixedFlag$sample73;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$i);
			if(state.fixedFlag$sample73)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$i);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!state.fixedProbFlag$sample89) {
			double cv$sampleAccumulator = 0.0;
			for(int var84 = 0; var84 < state.length$obs_measured; var84 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.p) && (state.p <= 1.0))?Math.log((state.obs[var84]?state.p:(1.0 - state.p))):Double.NEGATIVE_INFINITY));
			state.logProbability$var85 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample89 = state.fixedFlag$sample73;
		} else {
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var85);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var85);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var85);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample73) {
			int lengthCV$a$71_4 = -1;
			if((0 == state.y))
				lengthCV$a$71_4 = 2;
			if((1 == state.y))
				lengthCV$a$71_4 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$71_4);
			state.p = state.b[state.y][state.i];
		}
		for(int var84 = 0; var84 < state.length$obs_measured; var84 += 1)
			state.obs[var84] = DistributionSampling.sampleBernoulli(state.RNG$, state.p);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample73) {
			int lengthCV$a$71_8 = -1;
			if((0 == state.y))
				lengthCV$a$71_8 = 2;
			if((1 == state.y))
				lengthCV$a$71_8 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$71_8);
		}
		state.p = state.b[state.y][state.i];
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample73) {
			int lengthCV$a$71_5 = -1;
			if((0 == state.y))
				lengthCV$a$71_5 = 2;
			if((1 == state.y))
				lengthCV$a$71_5 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$71_5);
		}
		state.p = state.b[state.y][state.i];
		for(int var84 = 0; var84 < state.length$obs_measured; var84 += 1)
			state.obs[var84] = DistributionSampling.sampleBernoulli(state.RNG$, state.p);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample73) {
			int lengthCV$a$71_6 = -1;
			if((0 == state.y))
				lengthCV$a$71_6 = 2;
			if((1 == state.y))
				lengthCV$a$71_6 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$71_6);
			state.p = state.b[state.y][state.i];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample73) {
			int lengthCV$a$71_7 = -1;
			if((0 == state.y))
				lengthCV$a$71_7 = 2;
			if((1 == state.y))
				lengthCV$a$71_7 = 3;
			state.i = DistributionSampling.sampleCategorical(state.RNG$, state.a[state.y], lengthCV$a$71_7);
		}
		state.p = state.b[state.y][state.i];
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample73)
			inferSample73();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample73)
			drawValueSample73();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample73)
			state.logProbability$i = Double.NaN;
		state.logProbability$obs = 0.0;
		if(!state.fixedProbFlag$sample89)
			state.logProbability$var85 = Double.NaN;
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
		double[] var38 = state.b[0];
		var38[0] = 0.2;
		var38[1] = 0.8;
		double[] var51 = state.b[1];
		var51[0] = 0.4;
		var51[1] = 0.2;
		var51[2] = 0.6;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample73)
			logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample73();
		logProbabilityValue$sample89();
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
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model RaggedArray(int y, boolean[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n"
		     + "    \n"
		     + "    int i = categorical(a[y]).sample();\n"
		     + "    double p = b[y][i];\n"
		     + "    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}