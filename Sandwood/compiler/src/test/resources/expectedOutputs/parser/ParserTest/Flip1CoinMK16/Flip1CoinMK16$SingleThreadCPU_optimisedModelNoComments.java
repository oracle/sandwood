package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK16$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK16$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample11 = false;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample11 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean flip;
	private boolean flipMeasured;
	private double guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flip;
	private double logProbability$sample11;
	private double logProbability$sample9;
	private double logProbability$var6;
	private boolean setFlag$bias = false;
	private boolean setFlag$flip = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK16$SingleThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample11() {
		return fixedFlag$sample11;
	}

	@Override
	public final void set$fixedFlag$sample11(boolean cv$value) {
		fixedFlag$sample11 = cv$value;
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample11 = (cv$value && fixedProbFlag$sample11);
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
	public final double get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(double cv$value) {
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

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flip() {
		return logProbability$flip;
	}

	private final void logProbabilityValue$sample11() {
		if(!fixedProbFlag$sample11) {
			double cv$accumulator = 0.0;
			if(Double.isNaN(guard)) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flip, bias);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$bernoulli = cv$distributionAccumulator;
				logProbability$sample11 = cv$distributionAccumulator;
			}
			logProbability$flip = (logProbability$flip + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample11 = (fixedFlag$sample11 && fixedFlag$sample9);
		} else {
			double cv$accumulator = 0.0;
			if(Double.isNaN(guard)) {
				cv$accumulator = logProbability$sample11;
				logProbability$bernoulli = logProbability$sample11;
			}
			logProbability$flip = (logProbability$flip + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			if(Double.isNaN(guard)) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
				cv$accumulator = cv$distributionAccumulator;
				logProbability$var6 = cv$distributionAccumulator;
				logProbability$sample9 = cv$distributionAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			if(Double.isNaN(guard)) {
				cv$accumulator = logProbability$sample9;
				logProbability$var6 = logProbability$sample9;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample9() {
		int cv$sum = 0;
		if(flip)
			cv$sum = 1;
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, 1);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {}

	@Override
	public final void forwardGeneration() {
		if(Double.isNaN(guard)) {
			if(!fixedFlag$sample9)
				bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample11)
				flip = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			sample9();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var6 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$sample9 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flip = 0.0;
		if(!fixedProbFlag$sample11)
			logProbability$sample11 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample11();
	}

	@Override
	public final void logProbabilityGeneration() {
		if((Double.isNaN(guard) && !fixedFlag$sample9))
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		if(Double.isNaN(guard))
			flip = flipMeasured;
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK16(boolean flipMeasured, double guard) {\n    if(isNaN(guard)) {\n        double bias = beta(1.0, 1.0).sample();\n        Bernoulli bernoulli = bernoulli(bias);\n        boolean flip = bernoulli.sample();\n        flip.observe(flipMeasured);\n    }\n}\n";
	}
}