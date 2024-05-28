package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK5$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK5$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample53;
	private double logProbability$var12;
	private double logProbability$var24;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private int[] shape;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK5$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample27 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final boolean[][] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[][] cv$value) {
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
	public final int[] get$shape() {
		return shape;
	}

	@Override
	public final void set$shape(int[] cv$value) {
		shape = cv$value;
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$sampleAccumulator = 0.0;
			for(int var23 = 0; var23 < coins; var23 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var23], 1.0, 1.0));
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$var24 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			logProbability$var12 = logProbability$var24;
			logProbability$bias = (logProbability$bias + logProbability$var24);
			logProbability$$model = (logProbability$$model + logProbability$var24);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var24);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var49 = 0; var49 < shape[j]; var49 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j][var49], bias[j]));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample53[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample53[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample27(int var23) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var49 = 0; var49 < shape[var23]; var49 += 1) {
			cv$count = (cv$count + 1);
			if(flips[var23][var49])
				cv$sum = (cv$sum + 1);
		}
		bias[var23] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$bias)
			bias = new double[shape.length];
		if(!setFlag$flips) {
			flips = new boolean[shape.length][];
			for(int j = 0; j < shape.length; j += 1)
				flips[j] = new boolean[shape[j]];
		}
		logProbability$bernoulli = new double[shape.length];
		logProbability$sample53 = new double[shape.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample27) {
			for(int var23 = 0; var23 < coins; var23 += 1)
				bias[var23] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53) {
			for(int j = 0; j < coins; j += 1) {
				boolean[] var40 = flips[j];
				for(int var49 = 0; var49 < shape[j]; var49 += 1)
					var40[var49] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample27) {
			for(int var23 = 0; var23 < coins; var23 += 1)
				bias[var23] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample27) {
			for(int var23 = 0; var23 < coins; var23 += 1)
				bias[var23] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample27) {
			if(system$gibbsForward) {
				for(int var23 = 0; var23 < coins; var23 += 1)
					sample27(var23);
			} else {
				for(int var23 = (coins - 1); var23 >= 0; var23 -= 1)
					sample27(var23);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		coins = shape.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$var24 = 0.0;
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample53) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample53[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample27)
			logProbabilityValue$sample27();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample27();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample27();
		logProbabilityValue$sample53();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample27) {
			for(int var23 = 0; var23 < coins; var23 += 1)
				bias[var23] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = flipsMeasured[cv$index1];
			boolean[] cv$target2 = flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK5(boolean[][] flipsMeasured, int[] shape) {\n \n    int coins = shape.length;\n    double[] bias = beta(1.0, 1.0).sample(coins);\n        \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}\n\n";
	}
}