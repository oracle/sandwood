package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class ExponentialDecayMK1$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements ExponentialDecayMK1$CoreInterface {
	private double a;
	private double b;
	private double[] decay;
	private double[] decayDetected;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample16 = false;
	private int length$decayDetected;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$decay;
	private double logProbability$exponential;
	private double logProbability$rate;
	private double logProbability$var14;
	private double logProbability$var7;
	private double rate;
	private int samples;
	private boolean setFlag$decay = false;
	private boolean system$gibbsForward = true;

	public ExponentialDecayMK1$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final void set$a(double cv$value) {
		a = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
	}

	@Override
	public final double[] get$decay() {
		return decay;
	}

	@Override
	public final void set$decay(double[] cv$value) {
		decay = cv$value;
		setFlag$decay = true;
	}

	@Override
	public final double[] get$decayDetected() {
		return decayDetected;
	}

	@Override
	public final void set$decayDetected(double[] cv$value) {
		decayDetected = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
	}

	@Override
	public final int get$length$decayDetected() {
		return length$decayDetected;
	}

	@Override
	public final void set$length$decayDetected(int cv$value) {
		length$decayDetected = cv$value;
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
	public final double get$logProbability$decay() {
		return logProbability$decay;
	}

	@Override
	public final double get$logProbability$exponential() {
		return logProbability$exponential;
	}

	@Override
	public final double get$logProbability$rate() {
		return logProbability$rate;
	}

	@Override
	public final double get$rate() {
		return rate;
	}

	@Override
	public final void set$rate(double cv$value) {
		rate = cv$value;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityGamma(rate, a, b);
			logProbability$var7 = cv$distributionAccumulator;
			logProbability$rate = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$var7 = logProbability$rate;
			logProbability$$model = (logProbability$$model + logProbability$rate);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + logProbability$rate);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$sampleAccumulator = 0.0;
			for(int var13 = 0; var13 < samples; var13 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityExponential(decay[var13], rate));
			logProbability$exponential = cv$sampleAccumulator;
			logProbability$var14 = cv$sampleAccumulator;
			logProbability$decay = (logProbability$decay + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedFlag$sample10);
		} else {
			logProbability$exponential = logProbability$var14;
			logProbability$decay = (logProbability$decay + logProbability$var14);
			logProbability$$model = (logProbability$$model + logProbability$var14);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var14);
		}
	}

	private final void sample10() {
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var13 = 0; var13 < samples; var13 += 1) {
			cv$sum = (cv$sum + decay[var13]);
			cv$count = (cv$count + 1);
		}
		rate = Conjugates.sampleConjugateGammaExponential(RNG$, a, b, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$decay)
			decay = new double[length$decayDetected];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
		if(!fixedFlag$sample16) {
			for(int var13 = 0; var13 < samples; var13 += 1)
				decay[var13] = DistributionSampling.sampleExponential(RNG$, rate);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample10)
			sample10();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$decayDetected;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var7 = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$rate = 0.0;
		logProbability$exponential = 0.0;
		logProbability$decay = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$var14 = 0.0;
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
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			rate = DistributionSampling.sampleGamma(RNG$, a, b);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = decay.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			decay[cv$index1] = decayDetected[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model ExponentialDecayMK1(double[] decayDetected, double a, double b) {\n    \n        int samples = decayDetected.length;\n        double rate = gamma(a, b).sample();\n        \n        Exponential exponential = exponential(rate);\n        double[] decay = exponential.sample(samples);\n        decay.observe(decayDetected);\n}";
	}
}