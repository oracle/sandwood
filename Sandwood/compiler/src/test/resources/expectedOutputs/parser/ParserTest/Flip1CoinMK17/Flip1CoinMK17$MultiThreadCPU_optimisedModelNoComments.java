package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK17$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK17$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample7 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample7 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$var6;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK17$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value) {
		fixedFlag$sample7 = cv$value;
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
	}

	@Override
	public final boolean get$flip() {
		return flip;
	}

	@Override
	public final void set$flip(boolean cv$value) {
		flip = cv$value;
	}

	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	@Override
	public final void set$flipMeasured(boolean cv$value) {
		flipMeasured = cv$value;
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
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flip() {
		return logProbability$flip;
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (((0.0 <= bias) && (bias <= 1.0))?(DistributionSampling.logProbabilityGaussian((bias - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY);
			logProbability$var6 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$var6 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flip, bias);
			logProbability$bernoulli = cv$distributionAccumulator;
			logProbability$flip = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = (fixedFlag$sample9 && fixedFlag$sample7);
		} else {
			logProbability$bernoulli = logProbability$flip;
			logProbability$$model = (logProbability$$model + logProbability$flip);
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	private final void sample7() {
		double cv$originalValue = bias;
		double cv$var = ((bias * bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		double cv$originalProbability = (DistributionSampling.logProbabilityBernoulli(flip, bias) + (((0.0 <= bias) && (bias <= 1.0))?(DistributionSampling.logProbabilityGaussian((bias - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY));
		bias = cv$proposedValue;
		double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flip, cv$proposedValue) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?(DistributionSampling.logProbabilityGaussian((cv$proposedValue - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY));
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			bias = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
		if(!fixedFlag$sample9)
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample7)
			sample7();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var6 = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$bias = 0.0;
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$flip = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		flip = flipMeasured;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK17(boolean flipMeasured) {\n    double bias = truncatedGaussian(0.5, 1.0, 0.0, 1.0).sample();\n    Bernoulli bernoulli = bernoulli(bias);\n    boolean flip = bernoulli.sample();\n    flip.observe(flipMeasured);\n}\n";
	}
}