package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DirichletBernoulli$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DirichletBernoulli.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DirichletBernoulli$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public DirichletBernoulli$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample17() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.prior);
	}

	private final void inferSample17() {
		state.constrainedFlag$sample17 = false;
		double cv$originalProbability;
		int cv$indexToChange = (int)(DistributionSampling.sampleUniform(state.RNG$) * 2.0);
		double cv$movementRatio = ((DistributionSampling.sampleBeta(state.RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = state.prior[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - state.prior[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = state.prior[cv$loopIndex];
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1) {
				double cv$temp = state.prior[cv$loopIndex];
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.prior, state.v, 2);
			for(int i$var37 = 0; i$var37 < (state.length / 2); i$var37 += 1) {
				state.constrainedFlag$sample17 = true;
				double var19 = state.prior[0];
				cv$accumulatedProbabilities = ((((0.0 <= var19) && (var19 <= 1.0))?Math.log((state.output[i$var37]?var19:(1.0 - var19))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var50 = (state.length / 2); i$var50 < state.length; i$var50 += 1) {
				state.constrainedFlag$sample17 = true;
				double var22 = state.prior[1];
				cv$accumulatedProbabilities = ((((0.0 <= var22) && (var22 <= 1.0))?Math.log((state.output[i$var50]?var22:(1.0 - var22))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample17) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				state.prior[cv$loopIndex] = (state.prior[cv$loopIndex] - cv$proposedDifference);
			state.prior[cv$indexToChange] = (state.prior[cv$indexToChange] + cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1)
				state.prior[cv$loopIndex] = (state.prior[cv$loopIndex] - cv$proposedDifference);
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.prior, state.v, 2);
			for(int i$var37 = 0; i$var37 < (state.length / 2); i$var37 += 1) {
				state.constrainedFlag$sample17 = true;
				double var19 = state.prior[0];
				cv$accumulatedProbabilities = ((((0.0 <= var19) && (var19 <= 1.0))?Math.log((state.output[i$var37]?var19:(1.0 - var19))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var50 = (state.length / 2); i$var50 < state.length; i$var50 += 1) {
				state.constrainedFlag$sample17 = true;
				double var22 = state.prior[1];
				cv$accumulatedProbabilities = ((((0.0 <= var22) && (var22 <= 1.0))?Math.log((state.output[i$var50]?var22:(1.0 - var22))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
					state.prior[cv$loopIndex] = (state.prior[cv$loopIndex] + cv$proposedDifference);
				state.prior[cv$indexToChange] = (state.prior[cv$indexToChange] - cv$proposedDifference);
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 2; cv$loopIndex += 1)
					state.prior[cv$loopIndex] = (state.prior[cv$loopIndex] + cv$proposedDifference);
			}
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!state.fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.prior, state.v, 2);
			state.logProbability$prior = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample17 = state.fixedFlag$sample17;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$prior);
			if(state.fixedFlag$sample17)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$prior);
		}
	}

	private final void logProbabilityValue$sample38() {
		if(!state.fixedProbFlag$sample38) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var37 = 0; i$var37 < (state.length / 2); i$var37 += 1) {
				double var19 = state.prior[0];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var19) && (var19 <= 1.0))?Math.log((state.output[i$var37]?var19:(1.0 - var19))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$b1 = cv$sampleAccumulator;
			state.logProbability$var38 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample38 = state.fixedFlag$sample17;
		} else {
			state.logProbability$b1 = state.logProbability$var38;
			state.logProbability$output = (state.logProbability$output + state.logProbability$var38);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var38);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var38);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!state.fixedProbFlag$sample51) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var50 = (state.length / 2); i$var50 < state.length; i$var50 += 1) {
				double var22 = state.prior[1];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= var22) && (var22 <= 1.0))?Math.log((state.output[i$var50]?var22:(1.0 - var22))):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$b2 = cv$sampleAccumulator;
			state.logProbability$var51 = cv$sampleAccumulator;
			state.logProbability$output = (state.logProbability$output + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample51 = state.fixedFlag$sample17;
		} else {
			state.logProbability$b2 = state.logProbability$var51;
			state.logProbability$output = (state.logProbability$output + state.logProbability$var51);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var51);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var51);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.prior);
		parallelFor(state.RNG$, 0, (state.length / 2), 1,
			(int forStart$i$var37, int forEnd$i$var37, int threadID$i$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var37 = forStart$i$var37; i$var37 < forEnd$i$var37; i$var37 += 1)
						state.output[i$var37] = DistributionSampling.sampleBernoulli(RNG$1, state.prior[0]);
			}
		);
		parallelFor(state.RNG$, (state.length / 2), state.length, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
						state.output[i$var50] = DistributionSampling.sampleBernoulli(RNG$1, state.prior[1]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.prior);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.prior);
		parallelFor(state.RNG$, 0, (state.length / 2), 1,
			(int forStart$i$var37, int forEnd$i$var37, int threadID$i$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var37 = forStart$i$var37; i$var37 < forEnd$i$var37; i$var37 += 1)
						state.output[i$var37] = DistributionSampling.sampleBernoulli(RNG$1, state.prior[0]);
			}
		);
		parallelFor(state.RNG$, (state.length / 2), state.length, 1,
			(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
						state.output[i$var50] = DistributionSampling.sampleBernoulli(RNG$1, state.prior[1]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.prior);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample17)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.prior);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample17)
			inferSample17();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample17)
			drawValueSample17();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample17)
			state.logProbability$prior = Double.NaN;
		state.logProbability$b1 = Double.NaN;
		state.logProbability$output = 0.0;
		if(!state.fixedProbFlag$sample38)
			state.logProbability$var38 = Double.NaN;
		state.logProbability$b2 = Double.NaN;
		if(!state.fixedProbFlag$sample51)
			state.logProbability$var51 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.v[0] = 0.1;
		state.v[1] = 0.1;
		state.length = state.length$observed;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample38();
		logProbabilityValue$sample51();
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
		     + "public model DirichletBernoulli(boolean[] observed) {\n"
		     + "    double[] v = new double[2] <~ 0.1;\n"
		     + "    double[] prior = dirichlet(v).sample();\n"
		     + "    Bernoulli b1 = new Bernoulli(prior[0]);\n"
		     + "    Bernoulli b2 = new Bernoulli(prior[1]);\n"
		     + "    int length = observed.length;\n"
		     + "    boolean[] output = new boolean[length];\n"
		     + "    for(int i=0; i<length/2; i++)\n"
		     + "        output[i] = b1.sample();\n"
		     + "    for(int i=length/2; i<length; i++)\n"
		     + "        output[i] = b2.sample();\n"
		     + "    output.observe(observed);\n"
		     + "}\n"
		     + "";
	}
}