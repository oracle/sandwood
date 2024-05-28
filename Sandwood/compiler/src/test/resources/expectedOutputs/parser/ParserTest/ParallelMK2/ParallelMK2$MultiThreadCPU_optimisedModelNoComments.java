package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements ParallelMK2$CoreInterface {
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample38 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample38 = false;
	private double[] generated;
	private boolean[] guard$sample32gaussian37$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double[] logProbability$sample32;
	private double[] logProbability$sample38;
	private double[] logProbability$var27;
	private double[] logProbability$var33;
	private double[] observed;
	private double[] sample;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection = false;
	private boolean setFlag$sample = false;
	private boolean system$gibbsForward = true;

	public ParallelMK2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
	}

	@Override
	public final boolean get$fixedFlag$sample38() {
		return fixedFlag$sample38;
	}

	@Override
	public final void set$fixedFlag$sample38(boolean cv$value) {
		fixedFlag$sample38 = cv$value;
		fixedProbFlag$sample38 = (cv$value && fixedProbFlag$sample38);
	}

	@Override
	public final double[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(double[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
		fixedProbFlag$sample38 = false;
	}

	@Override
	public final double[] get$indirection() {
		return indirection;
	}

	@Override
	public final void set$indirection(double[] cv$value) {
		indirection = cv$value;
		setFlag$indirection = true;
	}

	@Override
	public final int get$length$observed() {
		return length$observed;
	}

	@Override
	public final void set$length$observed(int cv$value) {
		length$observed = cv$value;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$generated() {
		return logProbability$generated;
	}

	@Override
	public final double get$logProbability$indirection() {
		return logProbability$indirection;
	}

	@Override
	public final double get$logProbability$sample() {
		return logProbability$sample;
	}

	@Override
	public final double[] get$observed() {
		return observed;
	}

	@Override
	public final void set$observed(double[] cv$value) {
		observed = cv$value;
	}

	@Override
	public final double[] get$sample() {
		return sample;
	}

	@Override
	public final void set$sample(double[] cv$value) {
		sample = cv$value;
		setFlag$sample = true;
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleValue = sample[i];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var27[i] = cv$distributionAccumulator;
				logProbability$sample32[i] = cv$distributionAccumulator;
				logProbability$indirection = (logProbability$indirection + cv$distributionAccumulator);
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleValue = logProbability$sample32[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var27[i] = cv$sampleValue;
				logProbability$indirection = (logProbability$indirection + cv$sampleValue);
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample38() {
		if(!fixedProbFlag$sample38) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double var32 = indirection[i];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(var32))) - (Math.log(var32) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var33[i] = cv$distributionAccumulator;
				logProbability$sample38[i] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample38 = (fixedFlag$sample38 && fixedFlag$sample32);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = logProbability$sample38[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var33[i] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample32(int i) {
		double cv$originalValue = sample[i];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
			guard$sample32gaussian37$global[i] = false;
			if(!guard$sample32gaussian37$global[i]) {
				guard$sample32gaussian37$global[i] = true;
				double cv$temp$3$var32 = indirection[i];
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - cv$originalValue) / Math.sqrt(cv$temp$3$var32))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$3$var32) * 0.5));
			}
			if(!guard$sample32gaussian37$global[i]) {
				int index$i$5_2 = (i + 1);
				if((index$i$5_2 < length$observed))
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$originalValue) / Math.sqrt(cv$originalValue))) + cv$accumulatedProbabilities) - (Math.log(cv$originalValue) * 0.5));
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		sample[i] = cv$proposedValue;
		indirection[(i + 1)] = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?0.0:Double.NEGATIVE_INFINITY);
		guard$sample32gaussian37$global[i] = false;
		if(!guard$sample32gaussian37$global[i]) {
			guard$sample32gaussian37$global[i] = true;
			double cv$temp$3$var32 = indirection[i];
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - cv$proposedValue) / Math.sqrt(cv$temp$3$var32))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$3$var32) * 0.5));
		}
		int index$i$5_2 = (i + 1);
		if(((index$i$5_2 < length$observed) && !guard$sample32gaussian37$global[i])) {
			guard$sample32gaussian37$global[i] = true;
			cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[index$i$5_2] - cv$proposedValue) / Math.sqrt(cv$proposedValue))) + cv$accumulatedProbabilities) - (Math.log(cv$proposedValue) * 0.5));
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			sample[i] = cv$originalValue;
			indirection[(i + 1)] = sample[i];
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample32gaussian37$global = new boolean[length$observed];
	}

	@Override
	public final void allocator() {
		if(!setFlag$generated)
			generated = new double[length$observed];
		if(!setFlag$indirection)
			indirection = new double[(length$observed + 1)];
		if(!setFlag$sample)
			sample = new double[length$observed];
		logProbability$var27 = new double[length$observed];
		logProbability$sample32 = new double[length$observed];
		logProbability$var33 = new double[length$observed];
		logProbability$sample38 = new double[length$observed];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample32) {
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
							indirection[(i + 1)] = sample[i];
						}
						if(!fixedFlag$sample38)
							generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
							indirection[(i + 1)] = sample[i];
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
							indirection[(i + 1)] = sample[i];
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample32) {
			if(system$gibbsForward) {
				for(int i = 0; i < length$observed; i += 1)
					sample32(i);
			} else {
				for(int i = (length$observed - 1); i >= 0; i -= 1)
					sample32(i);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		indirection[0] = 1.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var27[i] = 0.0;
		logProbability$sample = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample32[i] = 0.0;
		}
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var33[i] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample38) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample38[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample32();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, length$observed, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							sample[i] = DistributionSampling.sampleUniform(RNG$1);
							indirection[(i + 1)] = sample[i];
						}
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = generated.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			generated[cv$index1] = observed[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK2(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length + 1];\n    indirection[0] = 1.0;\n\n    for(int i=0; i<observed.length; i++) {\n        double sample = uniform(0.0, 1.0).sample();\n        indirection[i + 1] = sample;\n        generated[i] = gaussian(sample, indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}