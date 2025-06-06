package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK6$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK6$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double[] logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample20;
	private double[] logProbability$sample27;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private int[] shape;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK6$MultiThreadCPU(ExecutionTarget target) {
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
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (cv$value && fixedProbFlag$sample27);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample27 = false;
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
	public final double[] get$logProbability$beta() {
		return logProbability$beta;
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

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[j], 1.0, 1.0);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$beta[j] = cv$distributionAccumulator;
				logProbability$sample20[j] = cv$distributionAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample20[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$beta[j] = cv$rvAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var23 = 0; var23 < shape[j]; var23 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j][var23], bias[j]));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample27[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample27[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20(int j, int threadID$cv$j, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var23 = 0; var23 < shape[j]; var23 += 1) {
			cv$count = (cv$count + 1);
			if(flips[j][var23])
				cv$sum = (cv$sum + 1);
		}
		bias[j] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips) {
			flips = new boolean[shape.length][];
			for(int j = 0; j < shape.length; j += 1)
				flips[j] = new boolean[shape[j]];
		}
		if(!setFlag$bias)
			bias = new double[shape.length];
		logProbability$beta = new double[shape.length];
		logProbability$sample20 = new double[shape.length];
		logProbability$bernoulli = new double[shape.length];
		logProbability$sample27 = new double[shape.length];
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						if(!fixedFlag$sample20)
							bias[j] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
						if(!fixedFlag$sample27) {
							boolean[] var20 = flips[j];
							parallelFor(RNG$1, 0, shape[j], 1,
								(int forStart$var23, int forEnd$var23, int threadID$var23, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var23 = forStart$var23; var23 < forEnd$var23; var23 += 1)
											var20[var23] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
								}
							);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							bias[j] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							bias[j] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample20) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j = forStart$j; j < forEnd$j; j += 1)
								sample20(j, threadID$j, RNG$1);
					}
				);
			else
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int j = forStart$j; j < forEnd$j; j += 1)
								sample20(j, threadID$j, RNG$1);
					}
				);
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
		for(int j = 0; j < coins; j += 1)
			logProbability$beta[j] = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample20) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample20[j] = 0.0;
		}
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample27[j] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int j = forStart$j; j < forEnd$j; j += 1)
							bias[j] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK6(boolean[][] flipsMeasured, int[] shape) {\n\n    int coins = shape.length;\n                 \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        Beta beta = beta(1.0, 1.0);\n        public double bias = beta.sample();\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}";
	}
}