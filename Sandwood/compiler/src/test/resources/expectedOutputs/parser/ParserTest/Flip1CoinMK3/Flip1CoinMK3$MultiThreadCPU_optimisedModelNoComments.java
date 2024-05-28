package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK3$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK3$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample27;
	private double logProbability$var7;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK3$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample10 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double[] get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			logProbability$var7 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$var7 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i$var23 = 0; i$var23 < samples; i$var23 += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[i$var23], bias);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$bernoulli[i$var23] = cv$distributionAccumulator;
				logProbability$sample27[i$var23] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedFlag$sample10);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var23 = 0; i$var23 < samples; i$var23 += 1) {
				double cv$rvAccumulator = logProbability$sample27[i$var23];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[i$var23] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample10() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int i$var23 = 0; i$var23 < samples; i$var23 += 1) {
			cv$count = (cv$count + 1);
			if(flips[i$var23])
				cv$sum = (cv$sum + 1);
		}
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips)
			flips = new boolean[length$flipsMeasured];
		logProbability$bernoulli = new double[length$flipsMeasured];
		logProbability$sample27 = new double[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample27)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1)
							flips[i$var23] = DistributionSampling.sampleBernoulli(RNG$1, bias);
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample10)
			sample10();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var7 = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$bias = 0.0;
		for(int i$var23 = 0; i$var23 < samples; i$var23 += 1)
			logProbability$bernoulli[i$var23] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i$var23 = 0; i$var23 < samples; i$var23 += 1)
				logProbability$sample27[i$var23] = 0.0;
		}
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
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i$var38 = ((samples * 2) - ((((samples * 2) - 1) % 2) + 1)); i$var38 >= 0; i$var38 -= 2)
			flips[(i$var38 / 2)] = flipsMeasured[(i$var38 / 2)];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n\npublic model Flip1CoinMK3(boolean[] flipsMeasured) {\n    int samples = flipsMeasured.length;\n    double bias = beta(1.0, 1.0).sample();\n    boolean[] flips = new boolean[samples];\n        \n    for(int i=0;i<=samples-1;++i) {\n        Bernoulli bernoulli = bernoulli(bias);\n        flips[i] = bernoulli.sample();\n    }\n\n    for(int i=0;i<2*samples;i=i+2) {\n        flips[i/2].observe(flipsMeasured[i/2]);\n    }\n}\n";
	}
}