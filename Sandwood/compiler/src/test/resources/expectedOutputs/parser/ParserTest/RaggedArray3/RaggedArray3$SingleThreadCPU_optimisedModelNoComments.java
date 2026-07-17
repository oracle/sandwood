package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.RaggedArray3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.RaggedArray3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class RaggedArray3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var37$countGlobal;

		@Override
		public final void allocateScratch() {
			cv$var37$countGlobal = new double[3];
		}
	}


	public RaggedArray3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample39() {
		int lengthCV$a$37_3 = -1;
		if((0 == state.y))
			lengthCV$a$37_3 = 2;
		if((1 == state.y))
			lengthCV$a$37_3 = 3;
		DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_3, state.d);
	}

	private final void inferSample39() {
		state.constrainedFlag$sample39 = false;
		int lengthCV$a$37_1 = -1;
		if((0 == state.y))
			lengthCV$a$37_1 = 2;
		if((1 == state.y))
			lengthCV$a$37_1 = 3;
		for(int cv$loopIndex = 0; cv$loopIndex < lengthCV$a$37_1; cv$loopIndex += 1)
			scratch.cv$var37$countGlobal[cv$loopIndex] = 0.0;
		for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1) {
			state.constrainedFlag$sample39 = true;
			scratch.cv$var37$countGlobal[state.obs[var50]] = (scratch.cv$var37$countGlobal[state.obs[var50]] + 1.0);
		}
		if(state.constrainedFlag$sample39) {
			int lengthCV$a$37_2 = -1;
			if((0 == state.y))
				lengthCV$a$37_2 = 2;
			if((1 == state.y))
				lengthCV$a$37_2 = 3;
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.a[state.y], scratch.cv$var37$countGlobal, state.d, lengthCV$a$37_2);
		}
	}

	private final void logProbabilityValue$sample39() {
		if(!state.fixedProbFlag$sample39) {
			int lengthCV$a$37_4 = -1;
			if((0 == state.y))
				lengthCV$a$37_4 = 2;
			if((1 == state.y))
				lengthCV$a$37_4 = 3;
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.d, state.a[state.y], lengthCV$a$37_4);
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

	private final void logProbabilityValue$sample53() {
		if(!state.fixedProbFlag$sample53) {
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1) {
				int cv$sampleValue = state.obs[var50];
				int lengthCV$a$37_5 = -1;
				if((0 == state.y))
					lengthCV$a$37_5 = 2;
				if((1 == state.y))
					lengthCV$a$37_5 = 3;
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < lengthCV$a$37_5)) && (0 < lengthCV$a$37_5)) && (0.0 <= state.d[cv$sampleValue])) && (state.d[cv$sampleValue] <= 1.0))?Math.log(state.d[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$var51 = cv$sampleAccumulator;
			state.logProbability$obs = (state.logProbability$obs + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample53 = state.fixedFlag$sample39;
		} else {
			state.logProbability$obs = (state.logProbability$obs + state.logProbability$var51);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var51);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var51);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_6 = -1;
			if((0 == state.y))
				lengthCV$a$37_6 = 2;
			if((1 == state.y))
				lengthCV$a$37_6 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_6, state.d);
		}
		int lengthCV$a$37_7 = -1;
		if((0 == state.y))
			lengthCV$a$37_7 = 2;
		if((1 == state.y))
			lengthCV$a$37_7 = 3;
		for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1)
			state.obs[var50] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$37_7);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_12 = -1;
			if((0 == state.y))
				lengthCV$a$37_12 = 2;
			if((1 == state.y))
				lengthCV$a$37_12 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_12, state.d);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_8 = -1;
			if((0 == state.y))
				lengthCV$a$37_8 = 2;
			if((1 == state.y))
				lengthCV$a$37_8 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_8, state.d);
		}
		int lengthCV$a$37_9 = -1;
		if((0 == state.y))
			lengthCV$a$37_9 = 2;
		if((1 == state.y))
			lengthCV$a$37_9 = 3;
		for(int var50 = 0; var50 < state.length$obs_measured; var50 += 1)
			state.obs[var50] = DistributionSampling.sampleCategorical(state.RNG$, state.d, lengthCV$a$37_9);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_10 = -1;
			if((0 == state.y))
				lengthCV$a$37_10 = 2;
			if((1 == state.y))
				lengthCV$a$37_10 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_10, state.d);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample39) {
			int lengthCV$a$37_11 = -1;
			if((0 == state.y))
				lengthCV$a$37_11 = 2;
			if((1 == state.y))
				lengthCV$a$37_11 = 3;
			DistributionSampling.sampleDirichlet(state.RNG$, state.a[state.y], lengthCV$a$37_11, state.d);
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
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var51 = Double.NaN;
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
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample39();
		logProbabilityValue$sample53();
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
		     + "public model RaggedArray3(int y, int[] obs_measured) {\n"
		     + "    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n"
		     + "    \n"
		     + "    double[] d = dirichlet(a[y]).sample();\n"
		     + "    int[] obs = categorical(d).sample(obs_measured.length);\n"
		     + "    obs.observe(obs_measured);\n"
		     + "}";
	}
}