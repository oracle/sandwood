package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK4$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var21;
	private double logProbability$var7;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK4$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample23 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
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
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample23 = false;
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

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < samples; var20 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var20], bias));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var21 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample10);
		} else {
			logProbability$bernoulli = logProbability$var21;
			logProbability$flips = (logProbability$flips + logProbability$var21);
			logProbability$$model = (logProbability$$model + logProbability$var21);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var21);
		}
	}

	private final void sample10() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var20 = 0; var20 < samples; var20 += 1) {
			cv$count = (cv$count + 1);
			if(flips[var20])
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
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							flips[var20] = DistributionSampling.sampleBernoulli(RNG$1, bias);
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
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var21 = 0.0;
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
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i = 0; i < samples; i += 1)
			flips[i] = flipsMeasured[i];
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
		     + "public model Flip1CoinMK4(boolean[] flipsMeasured) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "    private Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "        \n"
		     + "    for(int i=samples-1; i>=0; i--)\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}