package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LDATest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LDATest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LDATest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var42$countGlobal;
		double[] cv$var57$countGlobal;
		double[] cv$var88$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var42$countGlobal = new double[state.vocabSize];
			cv$var57$countGlobal = new double[state.noTopics];
			cv$var88$stateProbabilityGlobal = new double[state.noTopics];
		}
	}


	public LDATest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample42(int var41) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
	}

	private final void drawValueSample58(int var56) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
	}

	private final void drawValueSample90(int i$var71, int j) {
		state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
	}

	private final void inferSample42(int var41) {
		state.constrainedFlag$sample42[var41] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.vocabSize; cv$loopIndex += 1)
			scratch.cv$var42$countGlobal[cv$loopIndex] = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				if((var41 == state.z[i$var71][j])) {
					state.constrainedFlag$sample42[var41] = true;
					scratch.cv$var42$countGlobal[state.w[i$var71][j]] = (scratch.cv$var42$countGlobal[state.w[i$var71][j]] + 1.0);
				}
			}
		}
		if(state.constrainedFlag$sample42[var41])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.beta, scratch.cv$var42$countGlobal, state.phi[var41], state.vocabSize);
	}

	private final void inferSample58(int var56) {
		state.constrainedFlag$sample58[var56] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noTopics; cv$loopIndex += 1)
			scratch.cv$var57$countGlobal[cv$loopIndex] = 0.0;
		for(int j = 0; j < state.length$documents[var56]; j += 1) {
			if(state.constrainedFlag$sample90[var56][j]) {
				state.constrainedFlag$sample58[var56] = true;
				scratch.cv$var57$countGlobal[state.z[var56][j]] = (scratch.cv$var57$countGlobal[state.z[var56][j]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample58[var56])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.alpha, scratch.cv$var57$countGlobal, state.theta[var56], state.noTopics);
	}

	private final void inferSample90(int i$var71, int j) {
		state.constrainedFlag$sample90[i$var71][j] = false;
		int cv$numStates = Math.max(0, state.noTopics);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			state.z[i$var71][j] = cv$valuePos;
			double[] var86 = state.theta[i$var71];
			state.constrainedFlag$sample90[i$var71][j] = true;
			double[] var89 = state.phi[cv$valuePos];
			scratch.cv$var88$stateProbabilityGlobal[cv$valuePos] = (((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY) + (((((cv$valuePos < state.noTopics) && (0 < state.noTopics)) && (0.0 <= var86[cv$valuePos])) && (var86[cv$valuePos] <= 1.0))?Math.log(var86[cv$valuePos]):Double.NEGATIVE_INFINITY));
		}
		if(state.constrainedFlag$sample90[i$var71][j]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var88$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var88$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var88$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var88$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var88$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var88$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var88$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var88$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var88$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!state.fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.phi[var41], state.beta, state.vocabSize));
			state.logProbability$var42 = cv$sampleAccumulator;
			state.logProbability$phi = (state.logProbability$phi + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			state.logProbability$phi = (state.logProbability$phi + state.logProbability$var42);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var42);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var42);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!state.fixedProbFlag$sample58) {
			double cv$sampleAccumulator = 0.0;
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.theta[var56], state.alpha, state.noTopics));
			state.logProbability$var57 = cv$sampleAccumulator;
			state.logProbability$theta = (state.logProbability$theta + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample58)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample58 = state.fixedFlag$sample58;
		} else {
			state.logProbability$theta = (state.logProbability$theta + state.logProbability$var57);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var57);
			if(state.fixedFlag$sample58)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var57);
		}
	}

	private final void logProbabilityValue$sample90() {
		double cv$accumulator = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				int cv$sampleValue = state.z[i$var71][j];
				double[] var86 = state.theta[i$var71];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noTopics)) && (0 < state.noTopics)) && (0.0 <= var86[cv$sampleValue])) && (var86[cv$sampleValue] <= 1.0))?Math.log(var86[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample90[i$var71][j] = cv$distributionAccumulator;
			}
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample93() {
		double cv$accumulator = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				int cv$sampleValue = state.w[i$var71][j];
				double[] var89 = state.phi[state.z[i$var71][j]];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[cv$sampleValue])) && (var89[cv$sampleValue] <= 1.0))?Math.log(var89[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample93[i$var71][j] = cv$distributionAccumulator;
			}
		}
		state.logProbability$w = (state.logProbability$w + cv$accumulator);
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			int[] t = state.w[i$var71];
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
				t[j] = DistributionSampling.sampleCategorical(state.RNG$, state.phi[state.z[i$var71][j]], state.vocabSize);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			int[] t = state.w[i$var71];
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
				t[j] = DistributionSampling.sampleCategorical(state.RNG$, state.phi[state.z[i$var71][j]], state.vocabSize);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample42) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, state.phi[var41]);
		}
		if(!state.fixedFlag$sample58) {
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, state.theta[var56]);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[i$var71][j] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample42) {
				for(int var41 = 0; var41 < state.noTopics; var41 += 1)
					inferSample42(var41);
			}
			if(!state.fixedFlag$sample58) {
				for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
					inferSample58(var56);
			}
			for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
				for(int j = 0; j < state.length$documents[i$var71]; j += 1)
					inferSample90(i$var71, j);
			}
		} else {
			for(int i$var71 = (state.length$documents.length - 1); i$var71 >= 0; i$var71 -= 1) {
				for(int j = (state.length$documents[i$var71] - 1); j >= 0; j -= 1)
					inferSample90(i$var71, j);
			}
			if(!state.fixedFlag$sample58) {
				for(int var56 = (state.length$documents.length - 1); var56 >= 0; var56 -= 1)
					inferSample58(var56);
			}
			if(!state.fixedFlag$sample42) {
				for(int var41 = (state.noTopics - 1); var41 >= 0; var41 -= 1)
					inferSample42(var41);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			if(!state.constrainedFlag$sample42[var41])
				drawValueSample42(var41);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			if(!state.constrainedFlag$sample58[var56])
				drawValueSample58(var56);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				if(!state.constrainedFlag$sample90[i$var71][j])
					drawValueSample90(i$var71, j);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$phi = 0.0;
		if(!state.fixedProbFlag$sample42)
			state.logProbability$var42 = Double.NaN;
		state.logProbability$theta = 0.0;
		if(!state.fixedProbFlag$sample58)
			state.logProbability$var57 = Double.NaN;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.logProbability$sample90[i$var71][j] = Double.NaN;
		}
		state.logProbability$w = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.logProbability$sample93[i$var71][j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < state.noTopics; i$var14 += 1)
			state.alpha[i$var14] = 0.1;
		for(int i$var27 = 0; i$var27 < state.vocabSize; i$var27 += 1)
			state.beta[i$var27] = 0.1;
		for(int index$constrainedFlag$sample90$1 = 0; index$constrainedFlag$sample90$1 < state.constrainedFlag$sample90.length; index$constrainedFlag$sample90$1 += 1) {
			boolean[] cv$constrainedFlag$sample90$1 = state.constrainedFlag$sample90[index$constrainedFlag$sample90$1];
			for(int index$constrainedFlag$sample90$2 = 0; index$constrainedFlag$sample90$2 < cv$constrainedFlag$sample90$1.length; index$constrainedFlag$sample90$2 += 1)
				cv$constrainedFlag$sample90$1[index$constrainedFlag$sample90$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < state.constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			state.constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample58$1 = 0; index$constrainedFlag$sample58$1 < state.constrainedFlag$sample58.length; index$constrainedFlag$sample58$1 += 1)
			state.constrainedFlag$sample58[index$constrainedFlag$sample58$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(state.fixedFlag$sample58)
			logProbabilityValue$sample58();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.w.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.documents[cv$index1];
			int[] cv$target2 = state.w[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
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
		     + "model LDATest(int noTopics, int vocabSize, int[][] documents) {\n"
		     + "\n"
		     + "    double[] alpha = new double[noTopics];\n"
		     + "    for(int i:[0..noTopics))\n"
		     + "        alpha[i] = 0.1;\n"
		     + "\n"
		     + "    double[] beta = new double[vocabSize];\n"
		     + "    for(int i:[0..vocabSize))\n"
		     + "        beta[i] = 0.1;\n"
		     + "\n"
		     + "    double[][] phi = dirichlet(beta).sample(noTopics);\n"
		     + "    double[][] theta = dirichlet(alpha).sample(documents.length);\n"
		     + "    int[][] w = new int[documents.length][];\n"
		     + "\n"
		     + "    for(int i:[0..documents.length)) {\n"
		     + "        int[] t = new int[documents[i].length];\n"
		     + "        for(int j:[0..documents[i].length)) {\n"
		     + "            int z = categorical(theta[i]).sample();\n"
		     + "            t[j] = categorical(phi[z]).sample();\n"
		     + "        }\n"
		     + "        w[i] = t;\n"
		     + "    }\n"
		     + "\n"
		     + "    w.observe(documents);\n"
		     + "}\n"
		     + "";
	}
}