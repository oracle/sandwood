package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Flip2CoinsMK2$CoreInterface {
	private double a;
	private double b;
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[][] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[][] logProbability$sample33;
	private double logProbability$var13;
	private double logProbability$var18;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$a() {
		return a;
	}

	@Override
	public final void set$a(double cv$value) {
		a = cv$value;
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample33 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (cv$value && fixedProbFlag$sample21);
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample33 = false;
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
	public final int[] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int[] cv$value) {
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
	public final double[][] get$logProbability$bernoulli() {
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

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$sampleAccumulator = 0.0;
			for(int var17 = 0; var17 < coins; var17 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var17], a, b));
			logProbability$var13 = cv$sampleAccumulator;
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			logProbability$var13 = logProbability$var18;
			logProbability$bias = (logProbability$bias + logProbability$var18);
			logProbability$$model = (logProbability$$model + logProbability$var18);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var18);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				for(int j = 0; j < coins; j += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[i][j], bias[j]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$bernoulli[i][j] = cv$distributionAccumulator;
					logProbability$sample33[i][j] = cv$distributionAccumulator;
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = (fixedFlag$sample33 && fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < samples; i += 1) {
				for(int j = 0; j < coins; j += 1) {
					double cv$rvAccumulator = logProbability$sample33[i][j];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$bernoulli[i][j] = cv$rvAccumulator;
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample21(int var17) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int i = 0; i < samples; i += 1) {
			cv$count = (cv$count + 1);
			if(flips[i][var17])
				cv$sum = (cv$sum + 1);
		}
		bias[var17] = Conjugates.sampleConjugateBetaBinomial(RNG$, a, b, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$bias)
			bias = new double[length$flipsMeasured[0]];
		if(!setFlag$flips) {
			flips = new boolean[length$flipsMeasured.length][];
			for(int i = 0; i < length$flipsMeasured.length; i += 1)
				flips[i] = new boolean[length$flipsMeasured[0]];
		}
		logProbability$bernoulli = new double[length$flipsMeasured.length][];
		for(int i = 0; i < length$flipsMeasured.length; i += 1)
			logProbability$bernoulli[i] = new double[length$flipsMeasured[0]];
		logProbability$sample33 = new double[length$flipsMeasured.length][];
		for(int i = 0; i < length$flipsMeasured.length; i += 1)
			logProbability$sample33[i] = new double[length$flipsMeasured[0]];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample21) {
			for(int var17 = 0; var17 < coins; var17 += 1)
				bias[var17] = DistributionSampling.sampleBeta(RNG$, a, b);
		}
		if(!fixedFlag$sample33) {
			for(int i = 0; i < samples; i += 1) {
				boolean[] sample = flips[i];
				for(int j = 0; j < coins; j += 1)
					sample[j] = DistributionSampling.sampleBernoulli(RNG$, bias[j]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample21) {
			for(int var17 = 0; var17 < coins; var17 += 1)
				bias[var17] = DistributionSampling.sampleBeta(RNG$, a, b);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample21) {
			for(int var17 = 0; var17 < coins; var17 += 1)
				bias[var17] = DistributionSampling.sampleBeta(RNG$, a, b);
		}
	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample21) {
			if(system$gibbsForward) {
				for(int var17 = 0; var17 < coins; var17 += 1)
					sample21(var17);
			} else {
				for(int var17 = (coins - 1); var17 >= 0; var17 -= 1)
					sample21(var17);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured.length;
		coins = length$flipsMeasured[0];
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var13 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$var18 = 0.0;
		for(int i = 0; i < samples; i += 1) {
			for(int j = 0; j < coins; j += 1)
				logProbability$bernoulli[i][j] = 0.0;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample33) {
			for(int i = 0; i < samples; i += 1) {
				for(int j = 0; j < coins; j += 1)
					logProbability$sample33[i][j] = 0.0;
			}
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample21) {
			for(int var17 = 0; var17 < coins; var17 += 1)
				bias[var17] = DistributionSampling.sampleBeta(RNG$, a, b);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK2(double a, double b, boolean[][] flipsMeasured) {\n    int samples = flipsMeasured.length;\n    int coins = flipsMeasured[0].length;\n    double[] bias = beta(a, b).sample(coins);\n    boolean[][] flips = new boolean[samples][];\n    for(int i:[0..samples)) {\n        boolean[] sample = new boolean[coins];\n        for(int j:[0..coins)) {\n            Bernoulli bernoulli = bernoulli(bias[j]);\n            sample[j] = bernoulli.sample();\n        }\n        flips[i] = sample;\n    }\n\n    flips.observe(flipsMeasured);\n}\n";
	}
}