package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ParallelMK3$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ParallelMK3$CoreInterface {
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample44 = false;
	private double[] generated;
	private boolean[] guard$sample26gaussian43$global;
	private double[] indirection;
	private int length$observed;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$generated;
	private double logProbability$indirection;
	private double logProbability$sample;
	private double[] logProbability$sample44;
	private double logProbability$var22;
	private double[] logProbability$var39;
	private double[] observed;
	private double[] sample;
	private boolean setFlag$generated = false;
	private boolean setFlag$sample = false;
	private boolean system$gibbsForward = true;
	private double[] v;

	public ParallelMK3$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		fixedFlag$sample44 = cv$value;
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
	}

	@Override
	public final double[] get$generated() {
		return generated;
	}

	@Override
	public final void set$generated(double[] cv$value) {
		generated = cv$value;
		setFlag$generated = true;
		fixedProbFlag$sample44 = false;
	}

	@Override
	public final double[] get$indirection() {
		return indirection;
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
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample44 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(sample, v);
			logProbability$var22 = cv$distributionAccumulator;
			logProbability$sample = cv$distributionAccumulator;
			if((0 < length$observed))
				logProbability$indirection = (logProbability$indirection + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			logProbability$var22 = logProbability$sample;
			if((0 < length$observed))
				logProbability$indirection = (logProbability$indirection + logProbability$sample);
			logProbability$$model = (logProbability$$model + logProbability$sample);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + logProbability$sample);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double var38 = indirection[i];
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(var38))) - (Math.log(var38) * 0.5));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var39[i] = cv$distributionAccumulator;
				logProbability$sample44[i] = cv$distributionAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedFlag$sample26);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < length$observed; i += 1) {
				double cv$rvAccumulator = logProbability$sample44[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var39[i] = cv$rvAccumulator;
			}
			logProbability$generated = (logProbability$generated + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26() {
		double cv$originalProbability;
		int cv$arrayLength = sample.length;
		int cv$indexToChange = (int)((double)cv$arrayLength * DistributionSampling.sampleUniform(RNG$));
		double cv$movementRatio = ((DistributionSampling.sampleBeta(RNG$, 5, 5) * 1.9999) - 1);
		double cv$proposedDifference;
		if((cv$movementRatio < 0))
			cv$proposedDifference = sample[cv$indexToChange];
		else {
			cv$proposedDifference = (1.0 - sample[cv$indexToChange]);
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
				double cv$temp = (sample[cv$loopIndex] * (cv$arrayLength - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
				double cv$temp = (sample[cv$loopIndex] * (cv$arrayLength - 1));
				if((cv$temp < cv$proposedDifference))
					cv$proposedDifference = cv$temp;
			}
		}
		cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
		double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(sample, v);
			for(int i = 0; i < length$observed; i += 1)
				guard$sample26gaussian43$global[i] = false;
			for(int i = 0; i < length$observed; i += 1) {
				if(!guard$sample26gaussian43$global[i]) {
					guard$sample26gaussian43$global[i] = true;
					double cv$temp$2$var38 = indirection[i];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(cv$temp$2$var38))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$2$var38) * 0.5));
				}
			}
			for(int i = 0; i < length$observed; i += 1) {
				if(!guard$sample26gaussian43$global[i]) {
					double traceTempVariable$var38$5_2 = sample[i];
					cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(traceTempVariable$var38$5_2))) + cv$accumulatedProbabilities) - (Math.log(traceTempVariable$var38$5_2) * 0.5));
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
			sample[cv$loopIndex] = (sample[cv$loopIndex] - cv$rebalanceValue);
		sample[cv$indexToChange] = (sample[cv$indexToChange] + cv$proposedDifference);
		for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			sample[cv$loopIndex] = (sample[cv$loopIndex] - cv$rebalanceValue);
		for(int i = 0; i < length$observed; i += 1)
			indirection[i] = sample[i];
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityDirichlet(sample, v);
		for(int i = 0; i < length$observed; i += 1)
			guard$sample26gaussian43$global[i] = false;
		for(int i = 0; i < length$observed; i += 1) {
			if(!guard$sample26gaussian43$global[i]) {
				guard$sample26gaussian43$global[i] = true;
				double cv$temp$2$var38 = indirection[i];
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(cv$temp$2$var38))) + cv$accumulatedProbabilities) - (Math.log(cv$temp$2$var38) * 0.5));
			}
		}
		for(int i = 0; i < length$observed; i += 1) {
			if(!guard$sample26gaussian43$global[i]) {
				double traceTempVariable$var38$5_2 = sample[i];
				guard$sample26gaussian43$global[i] = true;
				cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityGaussian(((generated[i] - sample[i]) / Math.sqrt(traceTempVariable$var38$5_2))) + cv$accumulatedProbabilities) - (Math.log(traceTempVariable$var38$5_2) * 0.5));
			}
		}
		if(((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$)))) {
			for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
				sample[cv$loopIndex] = (sample[cv$loopIndex] + cv$rebalanceValue);
			sample[cv$indexToChange] = (sample[cv$indexToChange] - cv$proposedDifference);
			for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				sample[cv$loopIndex] = (sample[cv$loopIndex] + cv$rebalanceValue);
			for(int i = 0; i < length$observed; i += 1)
				indirection[i] = sample[i];
		}
	}

	@Override
	public final void allocateScratch() {
		guard$sample26gaussian43$global = new boolean[length$observed];
	}

	@Override
	public final void allocator() {
		if(!setFlag$generated)
			generated = new double[length$observed];
		indirection = new double[length$observed];
		v = new double[10];
		if(!setFlag$sample)
			sample = new double[10];
		logProbability$var39 = new double[length$observed];
		logProbability$sample44 = new double[length$observed];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample26)
			DistributionSampling.sampleDirichlet(RNG$, v, sample);
		for(int i = 0; i < length$observed; i += 1) {
			if(!fixedFlag$sample26)
				indirection[i] = sample[i];
			if(!fixedFlag$sample44)
				generated[i] = ((Math.sqrt(indirection[i]) * DistributionSampling.sampleGaussian(RNG$)) + sample[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample26) {
			DistributionSampling.sampleDirichlet(RNG$, v, sample);
			for(int i = 0; i < length$observed; i += 1)
				indirection[i] = sample[i];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample26) {
			DistributionSampling.sampleDirichlet(RNG$, v, sample);
			for(int i = 0; i < length$observed; i += 1)
				indirection[i] = sample[i];
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample26)
			sample26();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var19 = 0; var19 < 10; var19 += 1)
			v[var19] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var22 = 0.0;
		logProbability$indirection = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$sample = 0.0;
		for(int i = 0; i < length$observed; i += 1)
			logProbability$var39[i] = 0.0;
		logProbability$generated = 0.0;
		if(!fixedProbFlag$sample44) {
			for(int i = 0; i < length$observed; i += 1)
				logProbability$sample44[i] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample26) {
			DistributionSampling.sampleDirichlet(RNG$, v, sample);
			for(int i = 0; i < length$observed; i += 1)
				indirection[i] = sample[i];
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
	public final void setIntermediates() {
		if(setFlag$sample) {
			for(int i = 0; i < length$observed; i += 1)
				indirection[i] = sample[i];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ParallelMK3(double[] observed) {\n    double[] generated = new double[observed.length];\n    double[] indirection = new double[observed.length];\n    double[] v = new double[10] <~ 0.1;\n\n\n    double[] sample = dirichlet(v).sample();\n    for(int i=0; i<observed.length; i++) {\n        indirection[i] = sample[i];\n        generated[i] = gaussian(sample[i], indirection[i]).sample();\n    }\n\n    generated.observe(observed);\n}";
	}
}