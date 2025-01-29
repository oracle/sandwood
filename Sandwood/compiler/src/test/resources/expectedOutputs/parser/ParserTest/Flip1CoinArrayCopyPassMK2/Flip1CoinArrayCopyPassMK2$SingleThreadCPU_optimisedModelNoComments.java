package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinArrayCopyPassMK2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip1CoinArrayCopyPassMK2$CoreInterface {
	private double[] bias;
	private boolean fixedFlag$sample13 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedProbFlag$sample13 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample26;
	private double logProbability$var11;
	private double logProbability$var12;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinArrayCopyPassMK2$SingleThreadCPU(ExecutionTarget target) {
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
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample13 = false;
		fixedProbFlag$sample26 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample13() {
		return fixedFlag$sample13;
	}

	@Override
	public final void set$fixedFlag$sample13(boolean cv$value) {
		fixedFlag$sample13 = cv$value;
		fixedProbFlag$sample13 = (cv$value && fixedProbFlag$sample13);
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample26 = false;
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

	@Override
	public final void set$samples(int cv$value) {
		samples = cv$value;
	}

	private final void logProbabilityValue$sample13() {
		if(!fixedProbFlag$sample13) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0);
			logProbability$var11 = cv$distributionAccumulator;
			logProbability$var12 = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample13 = fixedFlag$sample13;
		} else {
			logProbability$var11 = logProbability$var12;
			logProbability$bias = (logProbability$bias + logProbability$var12);
			logProbability$$model = (logProbability$$model + logProbability$var12);
			if(fixedFlag$sample13)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var12);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[i], bias[i]);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$bernoulli[i] = cv$distributionAccumulator;
				logProbability$sample26[i] = cv$distributionAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedFlag$sample13);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				double cv$rvAccumulator = logProbability$sample26[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[i] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample13() {
		int cv$sum = 0;
		int cv$count = 0;
		if((0 < samples)) {
			cv$count = 1;
			if(flips[0])
				cv$sum = 1;
		}
		for(int i = 0; i < samples; i += 1) {
			int index$i$2_2 = (i + 1);
			if((index$i$2_2 < samples)) {
				cv$count = (cv$count + 1);
				if(flips[index$i$2_2])
					cv$sum = (cv$sum + 1);
			}
		}
		bias[0] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		for(int i = 0; i < samples; i += 1)
			bias[(i + 1)] = bias[0];
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$bias)
			bias = new double[(samples + 1)];
		if(!setFlag$flips)
			flips = new boolean[samples];
		logProbability$bernoulli = new double[samples];
		logProbability$sample26 = new double[samples];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample13)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		for(int i = 0; i < samples; i += 1) {
			if(!fixedFlag$sample13)
				bias[(i + 1)] = bias[0];
			if(!fixedFlag$sample26)
				flips[i] = DistributionSampling.sampleBernoulli(RNG$, bias[i]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample13) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			for(int i = 0; i < samples; i += 1)
				bias[(i + 1)] = bias[0];
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample13) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			for(int i = 0; i < samples; i += 1)
				bias[(i + 1)] = bias[0];
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample13)
			sample13();
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
			logProbability$var12 = 0.0;
		for(int i = 0; i < samples; i += 1)
			logProbability$bernoulli[i] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample26) {
			for(int i = 0; i < samples; i += 1)
				logProbability$sample26[i] = 0.0;
		}
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
		logProbabilityValue$sample26();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample26();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample13();
		logProbabilityValue$sample26();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample13) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			for(int i = 0; i < samples; i += 1)
				bias[(i + 1)] = bias[0];
		}
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
		if(setFlag$bias) {
			for(int i = 0; i < samples; i += 1)
				bias[(i + 1)] = bias[0];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinArrayCopyPassMK2(int samples, boolean[] flipsMeasured) {\n    /*\n     * This is a bad example as there is a separation between the size of \n     * flips measured, and the size of noSamples.\n     */\n    double a = 1.0;\n    double b = 1.0;\n    double[] bias = new double[samples+1];\n    bias[0] = beta(a, b).sample();\n    boolean[] flips = new boolean[samples];\n    for(int i:[0..samples)) {\n        bias[i+1] = bias[0];\n        Bernoulli bernoulli = bernoulli(bias[i]);\n        flips[i] = bernoulli.sample();\n    }\n    flips.observe(flipsMeasured);\n}";
	}
}