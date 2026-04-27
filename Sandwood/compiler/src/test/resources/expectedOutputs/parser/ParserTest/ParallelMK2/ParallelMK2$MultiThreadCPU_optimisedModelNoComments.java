package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK2.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample26gaussian31$global;

		@Override
		public final void allocateScratch() {
			guard$sample26gaussian31$global = new boolean[state.length$observed];
		}
	}


	public ParallelMK2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample26(int i) {
		state.sample[i] = DistributionSampling.sampleUniform(state.RNG$);
		state.indirection[(i + 1)] = state.sample[i];
	}

	private final void inferSample26(int i) {
		state.constrainedFlag$sample26[i] = false;
		double cv$originalValue = state.sample[i];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			scratch.guard$sample26gaussian31$global[i] = false;
			if((i < (state.length$observed - 1)))
				scratch.guard$sample26gaussian31$global[i] = false;
			if(!scratch.guard$sample26gaussian31$global[i]) {
				scratch.guard$sample26gaussian31$global[i] = true;
				state.constrainedFlag$sample26[i] = true;
				double var30 = state.indirection[i];
				cv$accumulatedProbabilities = (((0.0 < var30)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - cv$originalValue) / Math.sqrt(var30))) - (Math.log(var30) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			int index$i$5_2 = (i + 1);
			if(((index$i$5_2 < state.length$observed) && !scratch.guard$sample26gaussian31$global[i])) {
				scratch.guard$sample26gaussian31$global[i] = true;
				state.constrainedFlag$sample26[i] = true;
				cv$accumulatedProbabilities = (((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_2] - cv$originalValue) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample26[i]) {
			state.sample[i] = cv$proposedValue;
			state.indirection[(i + 1)] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
			scratch.guard$sample26gaussian31$global[i] = false;
			if((i < (state.length$observed - 1)))
				scratch.guard$sample26gaussian31$global[i] = false;
			if(!scratch.guard$sample26gaussian31$global[i]) {
				scratch.guard$sample26gaussian31$global[i] = true;
				state.constrainedFlag$sample26[i] = true;
				double var30 = state.indirection[i];
				cv$accumulatedProbabilities = (((0.0 < var30)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - cv$proposedValue) / Math.sqrt(var30))) - (Math.log(var30) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			int index$i$5_2 = (i + 1);
			if(((index$i$5_2 < state.length$observed) && !scratch.guard$sample26gaussian31$global[i])) {
				scratch.guard$sample26gaussian31$global[i] = true;
				state.constrainedFlag$sample26[i] = true;
				cv$accumulatedProbabilities = (((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_2] - cv$proposedValue) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.sample[i] = cv$originalValue;
				state.indirection[(i + 1)] = state.sample[i];
			}
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!state.fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1) {
				double cv$sampleValue = state.sample[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 1.0))?0.0:Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample26[i] = cv$distributionAccumulator;
			}
			state.logProbability$sample = (state.logProbability$sample + cv$accumulator);
			state.logProbability$indirection = (state.logProbability$indirection + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample26 = state.fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample26[i]);
			state.logProbability$sample = (state.logProbability$sample + cv$accumulator);
			state.logProbability$indirection = (state.logProbability$indirection + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample26)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!state.fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1) {
				double var30 = state.indirection[i];
				double cv$distributionAccumulator = ((0.0 < var30)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - state.sample[i]) / Math.sqrt(var30))) - (Math.log(var30) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample32[i] = cv$distributionAccumulator;
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample32 = state.fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.length$observed; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample32[i]);
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26) {
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
							state.indirection[(i + 1)] = state.sample[i];
						}
						state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + state.sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26)
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
						state.indirection[(i + 1)] = state.sample[i];
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26)
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
						state.indirection[(i + 1)] = state.sample[i];
						state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + state.sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample26)
			parallelFor(state.RNG$, 0, state.length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
							state.indirection[(i + 1)] = state.sample[i];
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample26)
							state.sample[i] = DistributionSampling.sampleUniform(RNG$1);
						state.indirection[(i + 1)] = state.sample[i];
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample26) {
			if(state.system$gibbsForward) {
				for(int i = 0; i < state.length$observed; i += 1)
					inferSample26(i);
			} else {
				for(int i = (state.length$observed - 1); i >= 0; i -= 1)
					inferSample26(i);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i = 0; i < state.length$observed; i += 1) {
			if(!state.constrainedFlag$sample26[i])
				drawValueSample26(i);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$sample = 0.0;
		state.logProbability$indirection = 0.0;
		if(!state.fixedProbFlag$sample26) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample26[i] = Double.NaN;
		}
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample32) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample32[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.indirection[0] = 1.0;
		for(int index$constrainedFlag$sample26$1 = 0; index$constrainedFlag$sample26$1 < state.constrainedFlag$sample26.length; index$constrainedFlag$sample26$1 += 1)
			state.constrainedFlag$sample26[index$constrainedFlag$sample26$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample32();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample32();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.generated[cv$index1] = state.observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.indirection[(i + 1)] = state.sample[i];
			}
		);
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
		     + "public model ParallelMK2(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length + 1];\n"
		     + "    indirection[0] = 1.0;\n"
		     + "\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        double sample = uniform(0.0, 1.0).sample();\n"
		     + "        indirection[i + 1] = sample;\n"
		     + "        generated[i] = gaussian(sample, indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}