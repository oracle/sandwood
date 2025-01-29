package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK5$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample33;
	private double logProbability$var12;
	private double logProbability$var17;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private int[] shape;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK5$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample33 = false;
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

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$sampleAccumulator = 0.0;
			for(int var16 = 0; var16 < coins; var16 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var16], 1.0, 1.0));
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var12 = logProbability$var17;
			logProbability$bias = (logProbability$bias + logProbability$var17);
			logProbability$$model = (logProbability$$model + logProbability$var17);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var17);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var29 = 0; var29 < shape[j]; var29 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[j][var29], bias[j]));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample33[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = (fixedFlag$sample33 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample33[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample20(int var16, int threadID$cv$var16, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var29 = 0; var29 < shape[var16]; var29 += 1) {
			cv$count = (cv$count + 1);
			if(flips[var16][var29])
				cv$sum = (cv$sum + 1);
		}
		bias[var16] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
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
		logProbability$sample33 = new double[shape.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							bias[var16] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample33)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
							int j = index$j;
							boolean[] var26 = flips[j];
							parallelFor(RNG$1, 0, shape[j], 1,
								(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1)
											var26[var29] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							bias[var16] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							bias[var16] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample20) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
								sample20(var16, threadID$var16, RNG$1);
					}
				);
			else
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
								sample20(var16, threadID$var16, RNG$1);
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
		logProbability$var12 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$var17 = 0.0;
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample33) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample33[j] = 0.0;
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
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
							bias[var16] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK5(boolean[][] flipsMeasured, int[] shape) {\n \n    int coins = shape.length;\n    double[] bias = beta(1.0, 1.0).sample(coins);\n        \n    boolean[][] flips = new boolean[coins][];\n        \n    for(int j:[0..coins)) {\n        int samples = shape[j];\n        Bernoulli bernoulli = bernoulli(bias[j]);\n        flips[j] = bernoulli.sample(samples);\n    }\n        \n    flips.observe(flipsMeasured);\n}\n\n";
	}
}