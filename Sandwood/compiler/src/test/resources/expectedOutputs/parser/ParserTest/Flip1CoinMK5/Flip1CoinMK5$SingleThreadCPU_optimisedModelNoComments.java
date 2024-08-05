package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinMK5$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample30 = false;
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
	private double logProbability$var12;
	private double logProbability$var19;
	private double logProbability$var26;
	private int samples1;
	private int samples2;
	private boolean setFlag$flips1 = false;
	private boolean setFlag$flips2 = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK5$SingleThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
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
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
	}

	@Override
	public final boolean[] get$flips1() {
		return flips1;
	}

	@Override
	public final void set$flips1(boolean[] cv$value) {
		flips1 = cv$value;
		setFlag$flips1 = true;
	}

	@Override
	public final boolean[] get$flips2() {
		return flips2;
	}

	@Override
	public final void set$flips2(boolean[] cv$value) {
		flips2 = cv$value;
		setFlag$flips2 = true;
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

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias, 1.0, 1.0);
			logProbability$var12 = cv$distributionAccumulator;
			logProbability$bias = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			logProbability$var12 = logProbability$bias;
			logProbability$$model = (logProbability$$model + logProbability$bias);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + logProbability$bias);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$sampleAccumulator = 0.0;
			for(int var18 = 0; var18 < samples1; var18 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips1[var18], bias));
			logProbability$bernoulli1 = cv$sampleAccumulator;
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$flips1 = (logProbability$flips1 + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedFlag$sample17);
		} else {
			logProbability$bernoulli1 = logProbability$var19;
			logProbability$flips1 = (logProbability$flips1 + logProbability$var19);
			logProbability$$model = (logProbability$$model + logProbability$var19);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$sampleAccumulator = 0.0;
			for(int var25 = 0; var25 < samples2; var25 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips2[var25], bias));
			logProbability$bernoulli2 = cv$sampleAccumulator;
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$flips2 = (logProbability$flips2 + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedFlag$sample17);
		} else {
			logProbability$bernoulli2 = logProbability$var26;
			logProbability$flips2 = (logProbability$flips2 + logProbability$var26);
			logProbability$$model = (logProbability$$model + logProbability$var26);
			logProbability$$evidence = (logProbability$$evidence + logProbability$var26);
		}
	}

	private final void sample17() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var18 = 0; var18 < samples1; var18 += 1) {
			cv$count = (cv$count + 1);
			if(flips1[var18])
				cv$sum = (cv$sum + 1);
		}
		for(int var25 = 0; var25 < samples2; var25 += 1) {
			cv$count = (cv$count + 1);
			if(flips2[var25])
				cv$sum = (cv$sum + 1);
		}
		bias = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips1)
			flips1 = new boolean[length$flipsMeasured1];
		if(!setFlag$flips2)
			flips2 = new boolean[length$flipsMeasured2];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample23) {
			for(int var18 = 0; var18 < samples1; var18 += 1)
				flips1[var18] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
		if(!fixedFlag$sample30) {
			for(int var25 = 0; var25 < samples2; var25 += 1)
				flips2[var25] = DistributionSampling.sampleBernoulli(RNG$, bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample17)
			sample17();
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
		logProbability$var12 = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$bias = 0.0;
		logProbability$bernoulli1 = 0.0;
		logProbability$flips1 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var19 = 0.0;
		logProbability$bernoulli2 = 0.0;
		logProbability$flips2 = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var26 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample23();
		logProbabilityValue$sample30();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample23();
		logProbabilityValue$sample30();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample23();
		logProbabilityValue$sample30();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample17)
			bias = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i$var44 = 0; i$var44 < ((samples2 * 2) - 1); i$var44 += 2)
			flips2[(i$var44 / 2)] = flipsMeasured2[(i$var44 / 2)];
		for(int i$var33 = 0; i$var33 < samples1; i$var33 += 1)
			flips1[i$var33] = flipsMeasured1[i$var33];
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK5(boolean[] flipsMeasured1, boolean[] flipsMeasured2) {\n    int samples1 = flipsMeasured1.length;\n    int samples2 = flipsMeasured2.length;\n        \n    double bias = beta(1.0, 1).sample();\n        \n    Bernoulli bernoulli1 = bernoulli(bias);\n    boolean[] flips1 = bernoulli1.sample(samples1);\n        \n    Bernoulli bernoulli2 = bernoulli(bias);\n    boolean[] flips2 = bernoulli2.sample(samples2);\n\n    for(int i=samples1-1; i>-1; --i)\n        flips1[i].observe(flipsMeasured1[i]);\n\n    for(int i=2*samples2-2; i>=0; i = i-2)\n        flips2[i/2].observe(flipsMeasured2[i/2]);\n}\n";
	}
}