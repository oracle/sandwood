package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK19$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK19$CoreInterface {
	private int a;
	private int b;
	private double[][] bias;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var15;
	private double logProbability$var47;
	private double logProbability$var9;
	private double q;
	private int samples;
	private boolean system$gibbsForward = true;
	private double t;

	public Flip1CoinMK19$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int get$a() {
		return a;
	}

	@Override
	public final void set$a(int cv$value) {
		a = cv$value;
	}

	@Override
	public final int get$b() {
		return b;
	}

	@Override
	public final void set$b(int cv$value) {
		b = cv$value;
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
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
	public final double get$logProbability$q() {
		return logProbability$q;
	}

	@Override
	public final double get$logProbability$t() {
		return logProbability$t;
	}

	@Override
	public final double get$q() {
		return q;
	}

	@Override
	public final void set$q(double cv$value) {
		q = cv$value;
		fixedProbFlag$sample10 = false;
		fixedProbFlag$sample48 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	@Override
	public final double get$t() {
		return t;
	}

	@Override
	public final void set$t(double cv$value) {
		t = cv$value;
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample48 = false;
	}

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
			logProbability$var9 = cv$distributionAccumulator;
			logProbability$q = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$var9 = logProbability$q;
			logProbability$bias = (logProbability$bias + logProbability$q);
			logProbability$$model = (logProbability$$model + logProbability$q);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + logProbability$q);
		}
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
			logProbability$var15 = cv$distributionAccumulator;
			logProbability$t = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			logProbability$var15 = logProbability$t;
			logProbability$bias = (logProbability$bias + logProbability$t);
			logProbability$$model = (logProbability$$model + logProbability$t);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + logProbability$t);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < samples; var46 += 1) {
				double[] inner = bias[0];
				inner[0] = q;
				double var34 = inner[b];
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[var46]?var34:(1.0 - var34))));
			}
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample48 = (fixedFlag$sample10 && fixedFlag$sample16);
		} else {
			logProbability$bernoulli = logProbability$var47;
			logProbability$flips = (logProbability$flips + logProbability$var47);
			logProbability$$model = (logProbability$$model + logProbability$var47);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var47);
		}
	}

	private final void sample10() {
		int cv$sum = 0;
		int cv$count = 0;
		if((0 == b)) {
			for(int var46 = 0; var46 < samples; var46 += 1) {
				cv$count = (cv$count + 1);
				if(flips[var46])
					cv$sum = (cv$sum + 1);
			}
		}
		q = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0][0] = q;
	}

	private final void sample16() {
		int cv$sum = 0;
		int cv$count = 0;
		if((1 == b)) {
			for(int var46 = 0; var46 < samples; var46 += 1) {
				cv$count = (cv$count + 1);
				if(flips[var46])
					cv$sum = (cv$sum + 1);
			}
		}
		t = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0][1] = t;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		bias = new double[1][];
		bias[0] = new double[2];
		flips = new boolean[samples];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample16)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[] inner = bias[0];
		if(!fixedFlag$sample10)
			inner[0] = q;
		if(!fixedFlag$sample16)
			bias[0][1] = t;
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
						flips[var46] = DistributionSampling.sampleBernoulli(RNG$1, inner[b]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample16)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample10)
			bias[0][0] = q;
		if(!fixedFlag$sample16)
			bias[0][1] = t;
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample16)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample10)
			bias[0][0] = q;
		if(!fixedFlag$sample16)
			bias[0][1] = t;
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample10)
				sample10();
			if(!fixedFlag$sample16)
				sample16();
		} else {
			if(!fixedFlag$sample16)
				sample16();
			if(!fixedFlag$sample10)
				sample10();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var9 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$q = Double.NaN;
		logProbability$var15 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$t = Double.NaN;
		logProbability$bernoulli = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var47 = Double.NaN;
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
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample16();
		logProbabilityValue$sample48();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample16)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample10)
			bias[0][0] = q;
		if(!fixedFlag$sample16)
			bias[0][1] = t;
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		if(fixedFlag$sample10)
			bias[0][0] = q;
		if(fixedFlag$sample16)
			bias[0][1] = t;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK19(int samples, int a, int b, boolean[] flipsMeasured) {\n"
		     + "    \n"
		     + "    double q = beta(1,1).sample();\n"
		     + "    double t = beta(1,1).sample();\n"
		     + "    \n"
		     + "    double[][] bias = new double[1][];\n"
		     + "    private double[] inner = new double[2];\n"
		     + "    inner[0] = q;\n"
		     + "    bias[0] = inner;\n"
		     + "    bias[0][1] = t;\n"
		     + "    \n"
		     + "    Bernoulli bernoulli = bernoulli(inner[b]);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}