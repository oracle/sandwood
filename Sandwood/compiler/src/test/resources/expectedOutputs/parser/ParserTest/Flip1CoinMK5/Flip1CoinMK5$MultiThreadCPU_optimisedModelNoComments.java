package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK5$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample9 = false;
	private boolean fixedProbFlag$sample22 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample9 = false;
	private boolean[] flips1;
	private boolean[] flips2;
	private boolean[] flipsMeasured1;
	private boolean[] flipsMeasured2;
	private int length$flipsMeasured1;
	private int length$flipsMeasured2;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli1;
	private double logProbability$bernoulli2;
	private double logProbability$bias;
	private double logProbability$flips1;
	private double logProbability$flips2;
	private double logProbability$var22;
	private double logProbability$var36;
	private double logProbability$var8;
	private int samples1;
	private int samples2;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK5$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
		fixedProbFlag$sample9 = false;
		fixedProbFlag$sample22 = false;
		fixedProbFlag$sample36 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample9() {
		return fixedFlag$sample9;
	}

	@Override
	public final void set$fixedFlag$sample9(boolean cv$value) {
		fixedFlag$sample9 = cv$value;
		fixedProbFlag$sample9 = (cv$value && fixedProbFlag$sample9);
		fixedProbFlag$sample22 = (cv$value && fixedProbFlag$sample22);
		fixedProbFlag$sample36 = (cv$value && fixedProbFlag$sample36);
	}

	@Override
	public final boolean[] get$flips1() {
		return flips1;
	}

	@Override
	public final boolean[] get$flips2() {
		return flips2;
	}

	@Override
	public final boolean[] get$flipsMeasured1() {
		return flipsMeasured1;
	}

	@Override
	public final void set$flipsMeasured1(boolean[] cv$value) {
		flipsMeasured1 = cv$value;
	}

	@Override
	public final boolean[] get$flipsMeasured2() {
		return flipsMeasured2;
	}

	@Override
	public final void set$flipsMeasured2(boolean[] cv$value) {
		flipsMeasured2 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured1() {
		return length$flipsMeasured1;
	}

	@Override
	public final void set$length$flipsMeasured1(int cv$value) {
		length$flipsMeasured1 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured2() {
		return length$flipsMeasured2;
	}

	@Override
	public final void set$length$flipsMeasured2(int cv$value) {
		length$flipsMeasured2 = cv$value;
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
	public final double get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	@Override
	public final double get$logProbability$bernoulli2() {
		return logProbability$bernoulli2;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips1() {
		return logProbability$flips1;
	}

	@Override
	public final double get$logProbability$flips2() {
		return logProbability$flips2;
	}

	@Override
	public final int get$samples1() {
		return samples1;
	}

	@Override
	public final int get$samples2() {
		return samples2;
	}

	private final void logProbabilityValue$sample22() {
		if(!fixedProbFlag$sample22) {
			double cv$sampleAccumulator = 0.0;
			for(int var21 = 0; var21 < samples1; var21 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips1[var21]?bias:(1.0 - bias))));
			logProbability$bernoulli1 = cv$sampleAccumulator;
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$flips1 = (logProbability$flips1 + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample22 = fixedFlag$sample9;
		} else {
			logProbability$bernoulli1 = logProbability$var22;
			logProbability$flips1 = (logProbability$flips1 + logProbability$var22);
			logProbability$$model = (logProbability$$model + logProbability$var22);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var22);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$sampleAccumulator = 0.0;
			for(int var35 = 0; var35 < samples2; var35 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips2[var35]?bias:(1.0 - bias))));
			logProbability$bernoulli2 = cv$sampleAccumulator;
			logProbability$var36 = cv$sampleAccumulator;
			logProbability$flips2 = (logProbability$flips2 + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample36 = fixedFlag$sample9;
		} else {
			logProbability$bernoulli2 = logProbability$var36;
			logProbability$flips2 = (logProbability$flips2 + logProbability$var36);
			logProbability$$model = (logProbability$$model + logProbability$var36);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var36);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!fixedProbFlag$sample9) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			logProbability$var8 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample9 = fixedFlag$sample9;
		} else {
			logProbability$var8 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample9)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void sample9() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var21 = 0; var21 < samples1; var21 += 1) {
			cv$count = (cv$count + 1);
			if(flips1[var21])
				cv$sum = (cv$sum + 1);
		}
		for(int var35 = 0; var35 < samples2; var35 += 1) {
			cv$count = (cv$count + 1);
			if(flips2[var35])
				cv$sum = (cv$sum + 1);
		}
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		flips1 = new boolean[length$flipsMeasured1];
		flips2 = new boolean[length$flipsMeasured2];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		parallelFor(RNG$, 0, samples1, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
						flips1[var21] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
		parallelFor(RNG$, 0, samples2, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1)
						flips2[var35] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		parallelFor(RNG$, 0, samples1, 1,
			(int forStart$var21, int forEnd$var21, int threadID$var21, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var21 = forStart$var21; var21 < forEnd$var21; var21 += 1)
						flips1[var21] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
		parallelFor(RNG$, 0, samples2, 1,
			(int forStart$var35, int forEnd$var35, int threadID$var35, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var35 = forStart$var35; var35 < forEnd$var35; var35 += 1)
						flips2[var35] = DistributionSampling.sampleBernoulli(RNG$1, bias);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample9)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample9)
			sample9();
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples1 = length$flipsMeasured1;
		samples2 = length$flipsMeasured2;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var8 = 0.0;
		if(!fixedProbFlag$sample9)
			logProbability$bias = Double.NaN;
		logProbability$bernoulli1 = Double.NaN;
		logProbability$flips1 = 0.0;
		if(!fixedProbFlag$sample22)
			logProbability$var22 = Double.NaN;
		logProbability$bernoulli2 = Double.NaN;
		logProbability$flips2 = 0.0;
		if(!fixedProbFlag$sample36)
			logProbability$var36 = Double.NaN;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample9)
			logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample36();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample36();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample9();
		logProbabilityValue$sample22();
		logProbabilityValue$sample36();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var50 = 0; i$var50 < samples1; i$var50 += 1)
			flips1[i$var50] = flipsMeasured1[i$var50];
		for(int i$var68 = 0; i$var68 < ((samples2 * 2) - 1); i$var68 += 2)
			flips2[(i$var68 / 2)] = flipsMeasured2[(i$var68 / 2)];
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
		     + "public model Flip1CoinMK5(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n"
		     + "    int samples1 = flipsMeasured1.length;\n"
		     + "    int samples2 = flipsMeasured2.length;\n"
		     + "        \n"
		     + "    double bias = beta(1.0, 1).sample();\n"
		     + "        \n"
		     + "    Bernoulli bernoulli1 = bernoulli(bias);\n"
		     + "    boolean[] flips1 = bernoulli1.sample(samples1);\n"
		     + "        \n"
		     + "    Bernoulli bernoulli2 = bernoulli(bias);\n"
		     + "    boolean[] flips2 = bernoulli2.sample(samples2);\n"
		     + "\n"
		     + "    for(int i=samples1-1; i>-1; --i)\n"
		     + "        flips1[i].observe(flipsMeasured1[i]);\n"
		     + "\n"
		     + "    for(int i=2*samples2-2; i>=0; i = i-2)\n"
		     + "        flips2[i/2].observe(flipsMeasured2[i/2]);\n"
		     + "}\n"
		     + "";
	}
}