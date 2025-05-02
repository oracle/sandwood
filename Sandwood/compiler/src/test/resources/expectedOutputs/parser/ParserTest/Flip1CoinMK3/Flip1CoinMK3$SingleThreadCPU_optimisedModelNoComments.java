package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK3$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK3$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample6 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample6 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample23;
	private double logProbability$var5;
	private int samples;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK3$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample6 = false;
		fixedProbFlag$sample23 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample6() {
		return fixedFlag$sample6;
	}

	@Override
	public final void set$fixedFlag$sample6(boolean cv$value) {
		fixedFlag$sample6 = cv$value;
		fixedProbFlag$sample6 = (cv$value && fixedProbFlag$sample6);
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
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

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			for(int i$var21 = 0; i$var21 < samples; i$var21 += 1) {
				double cv$distributionAccumulator = Math.log((flips[i$var21]?bias:(1.0 - bias)));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$bernoulli[i$var21] = cv$distributionAccumulator;
				logProbability$sample23[i$var21] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample23 = fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var21 = 0; i$var21 < samples; i$var21 += 1) {
				double cv$rvAccumulator = logProbability$sample23[i$var21];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[i$var21] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
		for(int i$var21 = 0; i$var21 < samples; i$var21 += 1) {
			cv$count = (cv$count + 1);
			if(flips[i$var21])
				cv$sum = (cv$sum + 1);
		}
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		flips = new boolean[length$flipsMeasured];
		logProbability$bernoulli = new double[length$flipsMeasured];
		logProbability$sample23 = new double[length$flipsMeasured];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i$var21 = 0; i$var21 < samples; i$var21 += 1)
			flips[i$var21] = DistributionSampling.sampleBernoulli(RNG$, bias);
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
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var5 = 0.0;
		if(!fixedProbFlag$sample6)
			logProbability$bias = Double.NaN;
		for(int i$var21 = 0; i$var21 < samples; i$var21 += 1)
			logProbability$bernoulli[i$var21] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample23) {
			for(int i$var21 = 0; i$var21 < samples; i$var21 += 1)
				logProbability$sample23[i$var21] = Double.NaN;
		}
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
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample6)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var36 = ((samples * 2) - ((((samples * 2) - 1) % 2) + 1)); i$var36 >= 0; i$var36 -= 2)
			flips[(i$var36 / 2)] = flipsMeasured[(i$var36 / 2)];
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
		     + "\n"
		     + "public model Flip1CoinMK3(boolean[] flipsMeasured) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "        \n"
		     + "    for(int i=0;i<=samples-1;++i) {\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    for(int i=0;i<2*samples;i=i+2) {\n"
		     + "        flips[i/2].observe(flipsMeasured[i/2]);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}