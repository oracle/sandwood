package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK5$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK5$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample44;
	private double logProbability$var18;
	private double logProbability$var6;
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
		fixedProbFlag$sample18 = false;
		fixedProbFlag$sample44 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (cv$value && fixedProbFlag$sample18);
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
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

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$sampleAccumulator = 0.0;
			for(int var17 = 0; var17 < coins; var17 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[var17], 1.0, 1.0));
			logProbability$var6 = cv$sampleAccumulator;
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			logProbability$var6 = logProbability$var18;
			logProbability$bias = (logProbability$bias + logProbability$var18);
			logProbability$$model = (logProbability$$model + logProbability$var18);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var18);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var43 = 0; var43 < shape[j]; var43 += 1) {
					double var32 = bias[j];
					cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[j][var43]?var32:(1.0 - var32))));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample44[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample44 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample44[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample18(int var17, int threadID$cv$var17, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var43 = 0; var43 < shape[var17]; var43 += 1) {
			cv$count = (cv$count + 1);
			if(flips[var17][var43])
				cv$sum = (cv$sum + 1);
		}
		bias[var17] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample18)
			bias = new double[shape.length];
		flips = new boolean[shape.length][];
		for(int j = 0; j < shape.length; j += 1)
			flips[j] = new boolean[shape[j]];
		logProbability$bernoulli = new double[shape.length];
		logProbability$sample44 = new double[shape.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							bias[var17] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var34 = flips[j];
						parallelFor(RNG$1, 0, shape[j], 1,
							(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
										var34[var43] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							bias[var17] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							bias[var17] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var34 = flips[j];
						parallelFor(RNG$1, 0, shape[j], 1,
							(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
										var34[var43] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							bias[var17] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample18)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							bias[var17] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample18) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample18(var17, threadID$var17, RNG$1);
					}
				);
			else
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample18(var17, threadID$var17, RNG$1);
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
		logProbability$var6 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var18 = Double.NaN;
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample44) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample44[j] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample44();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "public model Flip2CoinsMK5(boolean[][] flipsMeasured, int[] shape) {\n"
		     + " \n"
		     + "    int coins = shape.length;\n"
		     + "    double[] bias = beta(1.0, 1.0).sample(coins);\n"
		     + "        \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = shape[j];\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "\n"
		     + "";
	}
}