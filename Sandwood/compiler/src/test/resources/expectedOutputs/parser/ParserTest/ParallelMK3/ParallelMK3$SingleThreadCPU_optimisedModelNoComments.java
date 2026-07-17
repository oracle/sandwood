package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample21gaussian37$global;

		@Override
		public final void allocateScratch() {
			guard$sample21gaussian37$global = new boolean[state.length$observed];
		}
	}


	public ParallelMK3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample21() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

	private final void inferSample21() {
		state.constrainedFlag$sample21 = false;
		double cv$originalProbability;
		int cv$indexToChange = (int)(DistributionSampling.sampleUniform(state.RNG$) * 10.0);
		double cv$movementRatio = ((DistributionSampling.sampleBeta(state.RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = state.sample[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - state.sample[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = (state.sample[cv$loopIndex] * 9);
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 10; cv$loopIndex += 1) {
				double cv$temp = (state.sample[cv$loopIndex] * 9);
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		double cv$rebalanceValue = (cv$proposedDifference / 9);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.sample, state.v, 10);
			for(int i = 0; i < state.length$observed; i += 1)
				scratch.guard$sample21gaussian37$global[i] = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				if(!scratch.guard$sample21gaussian37$global[i]) {
					scratch.guard$sample21gaussian37$global[i] = true;
					state.constrainedFlag$sample21 = true;
					double var36 = state.indirection[i];
					cv$accumulatedProbabilities = (((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var36))) - (Math.log(var36) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.length$observed; i += 1) {
				if(!scratch.guard$sample21gaussian37$global[i]) {
					double traceTempVariable$var36$5_2 = state.sample[i];
					scratch.guard$sample21gaussian37$global[i] = true;
					state.constrainedFlag$sample21 = true;
					cv$accumulatedProbabilities = (((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(traceTempVariable$var36$5_2))) - (Math.log(traceTempVariable$var36$5_2) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample21) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] - cv$rebalanceValue);
			state.sample[cv$indexToChange] = (state.sample[cv$indexToChange] + cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 10; cv$loopIndex += 1)
				state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] - cv$rebalanceValue);
			for(int i = 0; i < state.length$observed; i += 1)
				state.indirection[i] = state.sample[i];
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(state.sample, state.v, 10);
			for(int i = 0; i < state.length$observed; i += 1)
				scratch.guard$sample21gaussian37$global[i] = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				if(!scratch.guard$sample21gaussian37$global[i]) {
					scratch.guard$sample21gaussian37$global[i] = true;
					state.constrainedFlag$sample21 = true;
					double var36 = state.indirection[i];
					cv$accumulatedProbabilities = (((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var36))) - (Math.log(var36) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.length$observed; i += 1) {
				if(!scratch.guard$sample21gaussian37$global[i]) {
					double traceTempVariable$var36$5_2 = state.sample[i];
					scratch.guard$sample21gaussian37$global[i] = true;
					state.constrainedFlag$sample21 = true;
					cv$accumulatedProbabilities = (((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(traceTempVariable$var36$5_2))) - (Math.log(traceTempVariable$var36$5_2) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
					state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] + cv$rebalanceValue);
				state.sample[cv$indexToChange] = (state.sample[cv$indexToChange] - cv$proposedDifference);
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < 10; cv$loopIndex += 1)
					state.sample[cv$loopIndex] = (state.sample[cv$loopIndex] + cv$rebalanceValue);
				for(int i = 0; i < state.length$observed; i += 1)
					state.indirection[i] = state.sample[i];
			}
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.sample, state.v, 10);
			state.logProbability$sample = cv$distributionAccumulator;
			if((0 < state.length$observed))
				state.logProbability$indirection = (state.logProbability$indirection + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			if((0 < state.length$observed))
				state.logProbability$indirection = (state.logProbability$indirection + state.logProbability$sample);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sample);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sample);
		}
	}

	private final void logProbabilityValue$sample38() {
		if(!state.fixedProbFlag$sample38) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1) {
				double var36 = state.indirection[i];
				double cv$distributionAccumulator = ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var36))) - (Math.log(var36) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample38[i] = cv$distributionAccumulator;
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample38 = state.fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample38[i]);
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1) {
			if(!state.fixedFlag$sample21)
				state.indirection[i] = state.sample[i];
			state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.sample[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1) {
			state.indirection[i] = state.sample[i];
			state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.sample[i]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample21) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
			for(int i = 0; i < state.length$observed; i += 1)
				state.indirection[i] = state.sample[i];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample21)
			inferSample21();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample21)
			drawValueSample21();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$indirection = 0.0;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$sample = Double.NaN;
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample38) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample38[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var17 = 0; var17 < 10; var17 += 1)
			state.v[var17] = 0.1;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample38();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.generated[cv$index1] = state.observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i = 0; i < state.length$observed; i += 1)
			state.indirection[i] = state.sample[i];
	}

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
		     + "public model ParallelMK3(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length];\n"
		     + "    double[] v = new double[10] <~ 0.1;\n"
		     + "\n"
		     + "\n"
		     + "    double[] sample = dirichlet(v).sample();\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        indirection[i] = sample[i];\n"
		     + "        generated[i] = gaussian(sample[i], indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}