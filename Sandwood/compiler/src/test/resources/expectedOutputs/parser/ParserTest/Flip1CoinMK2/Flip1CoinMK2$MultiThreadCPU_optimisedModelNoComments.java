package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK2$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$var19;
	private double logProbability$var5;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return 1.0;
	}

	@Override
	public final double get$b() {
		return 1.0;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample19 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		fixedFlag$sample6 = cv$value;
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
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
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int i = 0; i < samples; i += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[i]?bias:(1.0 - bias))));
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample6;
		} else {
			logProbability$bernoulli = logProbability$var19;
			logProbability$flips = (logProbability$flips + logProbability$var19);
			logProbability$$model = (logProbability$$model + logProbability$var19);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!fixedProbFlag$sample6) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			logProbability$var5 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample6 = fixedFlag$sample6;
		} else {
			logProbability$var5 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample6)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void sample6() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int i = 0; i < samples; i += 1) {
			cv$count = (cv$count + 1);
			if(flips[i])
				cv$sum = (cv$sum + 1);
		}
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		flips = new boolean[samples];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						flips[i] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample6)
			sample6();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$bias = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$var19 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
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
		     + "public model Flip1CoinMK2(int samples, boolean[] flipsMeasured) {\n"
		     + "    /*\n"
		     + "     * This is a bad example as there is a separation between the size of \n"
		     + "     * flips measured, and the size of noSamples.\n"
		     + "     */\n"
		     + "    double a = 1.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double bias = beta(a, b).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "    for(int i:[0..samples)) {\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "    }\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}