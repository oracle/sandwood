package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK17$SingleThreadCPU extends CoreModelSingleThreadCPU implements Flip1CoinMK17$CoreInterface {
double bias;
	boolean constrainedFlag$sample7 = true;
	boolean fixedFlag$sample7 = false;
	boolean fixedProbFlag$sample7 = false;
	boolean fixedProbFlag$sample9 = false;
	boolean flip;
	boolean flipMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bernoulli;
	double logProbability$bias;
	double logProbability$flip;
	boolean system$gibbsForward = true;

	public Flip1CoinMK17$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample7 = false;
		fixedProbFlag$sample9 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample7() {
		return fixedFlag$sample7;
	}

	@Override
	public final void set$fixedFlag$sample7(boolean cv$value, boolean allocated$) {
		fixedFlag$sample7 = cv$value;
		constrainedFlag$sample7 = (cv$value || constrainedFlag$sample7);
		fixedProbFlag$sample7 = (cv$value && fixedProbFlag$sample7);
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
	}

	@Override
	public final boolean get$flip() {
		return flip;
	}

	@Override
	public final boolean get$flipMeasured() {
		return flipMeasured;
	}

	@Override
	public final void set$flipMeasured(boolean cv$value, boolean allocated$) {
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

	private final void drawValueSample7() {
		bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	private final void inferSample7() {
		constrainedFlag$sample7 = false;
		double cv$originalValue = bias;
		double cv$var = ((bias * bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + bias);
		constrainedFlag$sample7 = true;
		double cv$originalProbability = ((((0.0 <= bias) && (bias <= 1.0))?Math.log((flip?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY) + (((0.0 <= bias) && (bias <= 1.0))?(DistributionSampling.logProbabilityGaussian((bias - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY));
		bias = cv$proposedValue;
		constrainedFlag$sample7 = true;
		double cv$ratio = (((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((flip?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?(DistributionSampling.logProbabilityGaussian((cv$proposedValue - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY)) - cv$originalProbability);
		if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
			bias = cv$originalValue;
	}

	private final void logProbabilityValue$sample7() {
		if(!fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (((0.0 <= bias) && (bias <= 1.0))?(DistributionSampling.logProbabilityGaussian((bias - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY);
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample7 = fixedFlag$sample7;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample7)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (((0.0 <= bias) && (bias <= 1.0))?Math.log((flip?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY);
			logProbability$bernoulli = cv$distributionAccumulator;
			logProbability$flip = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample7;
		} else {
			logProbability$bernoulli = logProbability$flip;
			logProbability$$model = (logProbability$$model + logProbability$flip);
			logProbability$$evidence = (logProbability$$evidence + logProbability$flip);
		}
	}

	@Override
	public final void allocate() {}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
		flip = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
		flip = DistributionSampling.sampleBernoulli(RNG$, bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample7)
			bias = (DistributionSampling.sampleTruncatedGaussian(RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample7)
			inferSample7();
		system$gibbsForward = !system$gibbsForward;
		if(!constrainedFlag$sample7)
			drawValueSample7();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample7)
			logProbability$bias = Double.NaN;
		logProbability$bernoulli = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$flip = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
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
	public final void propagateObservedValues() {
		flip = flipMeasured;
	}

	@Override
	public final void setIntermediates() {}

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
		     + "public model Flip1CoinMK17(boolean flipMeasured) {\n"
		     + "    double bias = truncatedGaussian(0.5, 1.0, 0.0, 1.0).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean flip = bernoulli.sample();\n"
		     + "    flip.observe(flipMeasured);\n"
		     + "}\n"
		     + "";
	}
}