package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK16$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK16$CoreInterface {
	private double bias;
	private boolean constrainedFlag$sample14 = true;
	private boolean flip;
	private boolean flipMeasured;
	private double guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$sample14;
	private double logProbability$sample16;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK16$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value, boolean allocated$) {
		bias = cv$value;
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
	public final double get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(double cv$value, boolean allocated$) {
		guard = cv$value;
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

	private final void drawValueSample14() {
		bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void inferSample14() {
		constrainedFlag$sample14 = false;
		int cv$sum = 0;
		constrainedFlag$sample14 = true;
		if(flip)
			cv$sum = 1;
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, 1);
	}

	private final void logProbabilityValue$sample14() {
		double cv$accumulator = 0.0;
		if(Double.isNaN(guard)) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			cv$accumulator = cv$distributionAccumulator;
			logProbability$sample14 = cv$distributionAccumulator;
		}
		logProbability$$model = (logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample16() {
		double cv$accumulator = 0.0;
		if(Double.isNaN(guard)) {
			double cv$distributionAccumulator = (((0.0 <= bias) && (bias <= 1.0))?Math.log((flip?bias:(1.0 - bias))):Double.NEGATIVE_INFINITY);
			cv$accumulator = cv$distributionAccumulator;
			logProbability$bernoulli = cv$distributionAccumulator;
			logProbability$sample16 = cv$distributionAccumulator;
		}
		logProbability$$model = (logProbability$$model + cv$accumulator);
		logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(Double.isNaN(guard)) {
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(Double.isNaN(guard))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(Double.isNaN(guard)) {
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(Double.isNaN(guard))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(Double.isNaN(guard))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(Double.isNaN(guard))
			inferSample14();
		system$gibbsForward = !system$gibbsForward;
		if((Double.isNaN(guard) && !constrainedFlag$sample14))
			drawValueSample14();
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$sample14 = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$sample16 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	@Override
	public final void propagateObservedValues() {
		if(Double.isNaN(guard))
			flip = flipMeasured;
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
		     + "public model Flip1CoinMK16(boolean flipMeasured, double guard) {\n"
		     + "    if(isNaN(guard)) {\n"
		     + "        double bias = beta(1.0, 1.0).sample();\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        boolean flip = bernoulli.sample();\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}