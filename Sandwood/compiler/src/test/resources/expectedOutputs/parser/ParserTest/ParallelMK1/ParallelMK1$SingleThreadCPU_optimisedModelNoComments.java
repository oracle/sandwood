package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK1$CoreInterface {
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample23 = false;
	private double[] generated;
	private boolean[] guard$sample19gaussian22$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double[] logProbability$sample19;
	private double[] logProbability$sample23;
	private double[] logProbability$var14;
	private double[] logProbability$var18;
	private double[] observed;
	private double[] sample;
	private boolean setFlag$generated = false;
	private boolean setFlag$indirection = false;
	private boolean setFlag$sample = false;
	private boolean system$gibbsForward = true;

	public ParallelMK1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
	}

	@Override
	public final double[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(double[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
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

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityUniform(sample[i], 0.0, 1.0);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var14[i] = cv$distributionAccumulator;
				logProbability$sample19[i] = cv$distributionAccumulator;
				logProbability$indirection = (logProbability$indirection + cv$distributionAccumulator);
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$sampleValue = logProbability$sample19[i];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				logProbability$var14[i] = cv$sampleValue;
				logProbability$indirection = (logProbability$indirection + cv$sampleValue);
			}
			logProbability$sample = (logProbability$sample + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityGaussian(generated[i], sample[i], indirection[i]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var18[i] = cv$distributionAccumulator;
				logProbability$sample23[i] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = logProbability$sample23[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18[i] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample19(int i) {
		double cv$originalValue = sample[i];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityUniform(cv$originalValue, 0.0, 1.0);
			guard$sample19gaussian22$global[i] = false;
			if(!guard$sample19gaussian22$global[i]) {
				guard$sample19gaussian22$global[i] = true;
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(generated[i], cv$originalValue, indirection[i]) + cv$accumulatedProbabilities);
			}
			if(!guard$sample19gaussian22$global[i])
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(generated[i], cv$originalValue, cv$originalValue) + cv$accumulatedProbabilities);
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		sample[i] = cv$proposedValue;
		indirection[i] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityUniform(cv$proposedValue, 0.0, 1.0);
		guard$sample19gaussian22$global[i] = false;
		if(!guard$sample19gaussian22$global[i]) {
			guard$sample19gaussian22$global[i] = true;
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(generated[i], cv$proposedValue, indirection[i]) + cv$accumulatedProbabilities);
		}
		if(!guard$sample19gaussian22$global[i]) {
			guard$sample19gaussian22$global[i] = true;
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian(generated[i], cv$proposedValue, cv$proposedValue) + cv$accumulatedProbabilities);
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability)))) {
			sample[i] = cv$originalValue;
			indirection[i] = sample[i];
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample19gaussian22$global = new boolean[length$observed];
	}

	@Override
	public final void allocator() {
		if(!setFlag$generated)
			generated = new double[length$observed];
		if(!setFlag$indirection)
			indirection = new double[length$observed];
		if(!setFlag$sample)
			sample = new double[length$observed];
		logProbability$var14 = new double[length$observed];
		logProbability$sample19 = new double[length$observed];
		logProbability$var18 = new double[length$observed];
		logProbability$sample23 = new double[length$observed];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int i = 0; i < length$observed; i += 1) {
			if(!fixedFlag$sample19) {
				sample[i] = DistributionSampling.sampleUniform(RNG$, 0.0, 1.0);
				indirection[i] = sample[i];
			}
			if(!fixedFlag$sample23)
				generated[i] = DistributionSampling.sampleGaussian(RNG$, sample[i], indirection[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample19) {
			for(int i = 0; i < length$observed; i += 1) {
				sample[i] = DistributionSampling.sampleUniform(RNG$, 0.0, 1.0);
				indirection[i] = sample[i];
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19) {
			for(int i = 0; i < length$observed; i += 1) {
				sample[i] = DistributionSampling.sampleUniform(RNG$, 0.0, 1.0);
				indirection[i] = sample[i];
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample19) {
			if(system$gibbsForward) {
				for(int i = 0; i < length$observed; i += 1)
					sample19(i);
			} else {
				for(int i = (length$observed - 1); i >= 0; i -= 1)
					sample19(i);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var14[i] = 0.0;
		logProbability$sample = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample19) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample19[i] = 0.0;
		}
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var18[i] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample23) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample23[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample19) {
			for(int i = 0; i < length$observed; i += 1) {
				sample[i] = DistributionSampling.sampleUniform(RNG$, 0.0, 1.0);
				indirection[i] = sample[i];
			}
		}
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK1(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length];\n\n    for(int i=0; i<observed.length; i++) {\n        double sample = uniform(0.0, 1.0).sample();\n        indirection[i] = sample;\n        generated[i] = gaussian(sample, indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}