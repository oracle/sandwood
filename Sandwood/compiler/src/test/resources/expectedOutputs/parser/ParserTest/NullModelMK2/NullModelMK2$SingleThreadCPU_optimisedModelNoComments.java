package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class NullModelMK2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements NullModelMK2$CoreInterface {
	private double bias;
	private double eta;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample12 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$binomial;
	private double logProbability$positiveCount;
	private double logProbability$var9;
	private double min;
	private int observedPositiveCount;
	private int observedSampleCount;
	private int positiveCount;
	private boolean system$gibbsForward = true;

	public NullModelMK2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
	}

	@Override
	public final double get$eta() {
		return eta;
	}

	@Override
	public final void set$eta(double cv$value) {
		eta = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		fixedFlag$sample12 = cv$value;
		fixedProbFlag$sample12 = (cv$value && fixedProbFlag$sample12);
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$binomial() {
		return logProbability$binomial;
	}

	@Override
	public final double get$logProbability$positiveCount() {
		return logProbability$positiveCount;
	}

	@Override
	public final double get$min() {
		return min;
	}

	@Override
	public final int get$observedPositiveCount() {
		return observedPositiveCount;
	}

	@Override
	public final void set$observedPositiveCount(int cv$value) {
		observedPositiveCount = cv$value;
	}

	@Override
	public final int get$observedSampleCount() {
		return observedSampleCount;
	}

	@Override
	public final void set$observedSampleCount(int cv$value) {
		observedSampleCount = cv$value;
	}

	@Override
	public final int get$positiveCount() {
		return positiveCount;
	}

	@Override
	public final void set$positiveCount(int cv$value) {
		positiveCount = cv$value;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = (((min <= bias) && (bias <= 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$var9 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(positiveCount, bias, observedSampleCount);
			logProbability$binomial = cv$distributionAccumulator;
			logProbability$positiveCount = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedFlag$sample10);
		} else {
			logProbability$binomial = logProbability$positiveCount;
			logProbability$$model = (logProbability$$model + logProbability$positiveCount);
			logProbability$$evidence = (logProbability$$evidence + logProbability$positiveCount);
		}
	}

	private final void sample10() {
		double cv$originalValue = bias;
		double cv$var = ((bias * bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		double cv$originalProbability = (DistributionSampling.logProbabilityBinomial(positiveCount, bias, observedSampleCount) + (((min <= bias) && (bias <= 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY));
		bias = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBinomial(positiveCount, cv$proposedValue, observedSampleCount) + (((min <= cv$proposedValue) && (cv$proposedValue <= 1.0))?(-Math.log((1.0 - min))):Double.NEGATIVE_INFINITY));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			bias = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
		if(!fixedFlag$sample12)
			positiveCount = DistributionSampling.sampleBinomial(RNG$, bias, observedSampleCount);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample10)
			sample10();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		min = ((eta * 4.0) / 5.0);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$bias = 0.0;
		logProbability$binomial = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$positiveCount = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			bias = (min + ((1.0 - min) * DistributionSampling.sampleUniform(RNG$)));
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		positiveCount = observedPositiveCount;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model NullModelMK2(double eta, int observedSampleCount, int observedPositiveCount) {\n        double min = eta * 4.0/5.0;    \n        double bias = uniform(min, 1.0).sample();\n        \n        //Construct a binomial\n        Binomial binomial = binomial(bias, observedSampleCount);\n                \n        //Sample from it\n        int positiveCount = binomial.sample();\n        \n        //Link the sampled values to the observed values\n        positiveCount.observe(observedPositiveCount);\n}";
	}
}