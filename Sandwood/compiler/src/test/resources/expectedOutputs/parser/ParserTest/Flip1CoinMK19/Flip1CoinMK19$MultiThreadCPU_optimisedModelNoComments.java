package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK19$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK19$CoreInterface {
	private int a;
	private int b;
	private double[][] bias;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$q;
	private double logProbability$t;
	private double logProbability$var11;
	private double logProbability$var17;
	private double logProbability$var49;
	private double q;
	private int samples;
	private boolean setFlag$flips = false;
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
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		fixedFlag$sample13 = cv$value;
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample51 = false;
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
		fixedProbFlag$sample13 = false;
		fixedProbFlag$sample51 = false;
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
		fixedProbFlag$sample19 = false;
		fixedProbFlag$sample51 = false;
	}

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(q, 1.0, 1.0);
			logProbability$var11 = cv$distributionAccumulator;
			logProbability$q = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = fixedFlag$sample13;
		} else {
			logProbability$var11 = logProbability$q;
			logProbability$bias = (logProbability$bias + logProbability$q);
			logProbability$$model = (logProbability$$model + logProbability$q);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + logProbability$q);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(t, 1.0, 1.0);
			logProbability$var17 = cv$distributionAccumulator;
			logProbability$t = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			logProbability$var17 = logProbability$t;
			logProbability$bias = (logProbability$bias + logProbability$t);
			logProbability$$model = (logProbability$$model + logProbability$t);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + logProbability$t);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$sampleAccumulator = 0.0;
			for(int var48 = 0; var48 < samples; var48 += 1) {
				double[] inner = bias[0];
				inner[0] = q;
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[var48], inner[b]));
			}
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var49 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample51 = ((fixedFlag$sample51 && fixedFlag$sample13) && fixedFlag$sample19);
		} else {
			logProbability$bernoulli = logProbability$var49;
			logProbability$flips = (logProbability$flips + logProbability$var49);
			logProbability$$model = (logProbability$$model + logProbability$var49);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	private final void sample13() {
		int cv$sum = 0;
		int cv$count = 0;
		if((0 == b)) {
			for(int var48 = 0; var48 < samples; var48 += 1) {
				cv$count = (cv$count + 1);
				if(flips[var48])
					cv$sum = (cv$sum + 1);
			}
		}
		q = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[0][0] = q;
	}

	private final void sample19() {
		int cv$sum = 0;
		int cv$count = 0;
		if((1 == b)) {
			for(int var48 = 0; var48 < samples; var48 += 1) {
				cv$count = (cv$count + 1);
				if(flips[var48])
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
		if(!setFlag$flips)
			flips = new boolean[samples];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample19)
			t = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		double[] inner = bias[0];
		if(!fixedFlag$sample13)
			inner[0] = q;
		if(!fixedFlag$sample19)
			bias[0][1] = t;
		if(!fixedFlag$sample51) {
			inner[0] = q;
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$var48, int forEnd$var48, int threadID$var48, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var48 = forStart$var48; var48 < forEnd$var48; var48 += 1)
							flips[var48] = DistributionSampling.sampleBernoulli(RNG$1, inner[b]);
				}
			);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample13)
				sample13();
			if(!fixedFlag$sample19)
				sample19();
		} else {
			if(!fixedFlag$sample19)
				sample19();
			if(!fixedFlag$sample13)
				sample13();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample13)
			logProbability$q = 0.0;
		logProbability$var17 = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$t = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var49 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample13)
			logProbabilityValue$sample13();
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample19();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample19();
		logProbabilityValue$sample51();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample13)
			q = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			flips[cv$index1] = flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		bias[0][0] = q;
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