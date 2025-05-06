package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK8$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK8$CoreInterface {
	private double a;
	private double b;
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample46 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample17;
	private double[] logProbability$sample46;
	private double[] logProbability$var16;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK8$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample17 = false;
		fixedProbFlag$sample46 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (cv$value && fixedProbFlag$sample17);
		fixedProbFlag$sample46 = (cv$value && fixedProbFlag$sample46);
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

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < coins; i += 1) {
				double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[i], a, b);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var16[i] = cv$distributionAccumulator;
				logProbability$sample17[i] = cv$distributionAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < coins; i += 1) {
				double cv$rvAccumulator = logProbability$sample17[i];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var16[i] = cv$rvAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample46() {
		if(!fixedProbFlag$sample46) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var45 = 0; var45 < length$flipsMeasured[j]; var45 += 1) {
					double var34 = (1 - bias[j]);
					cv$sampleAccumulator = (cv$sampleAccumulator + Math.log((flips[j][var45]?var34:(1.0 - var34))));
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[j] = cv$sampleAccumulator;
				logProbability$sample46[j] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample46 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = logProbability$sample46[j];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[j] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int i, int threadID$cv$i, Rng RNG$) {
		double cv$originalValue = bias[i];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$originalValue, a, b);
			for(int var45 = 0; var45 < length$flipsMeasured[i]; var45 += 1) {
				double cv$temp$2$var34 = (1 - cv$originalValue);
				cv$accumulatedProbabilities = (Math.log((flips[i][var45]?cv$temp$2$var34:(1.0 - cv$temp$2$var34))) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		bias[i] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityBeta(cv$proposedValue, a, b);
		for(int var45 = 0; var45 < length$flipsMeasured[i]; var45 += 1) {
			double cv$temp$2$var34 = (1 - cv$proposedValue);
			cv$accumulatedProbabilities = (Math.log((flips[i][var45]?cv$temp$2$var34:(1.0 - cv$temp$2$var34))) + cv$accumulatedProbabilities);
		}
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			bias[i] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample17)
			bias = new double[length$flipsMeasured.length];
		flips = new boolean[length$flipsMeasured.length][];
		for(int j = 0; j < length$flipsMeasured.length; j += 1)
			flips[j] = new boolean[length$flipsMeasured[j]];
		logProbability$var16 = new double[length$flipsMeasured.length];
		logProbability$sample17 = new double[length$flipsMeasured.length];
		logProbability$bernoulli = new double[length$flipsMeasured.length];
		logProbability$sample46 = new double[length$flipsMeasured.length];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
				}
			);

		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var36 = flips[j];
						parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
							(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
										var36[var45] = DistributionSampling.sampleBernoulli(RNG$2, (1 - bias[j]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
				}
			);

		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var36 = flips[j];
						parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
							(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1)
										var36[var45] = DistributionSampling.sampleBernoulli(RNG$2, (1 - bias[j]));
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample17)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(!fixedFlag$sample17) {
			if(system$gibbsForward)
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								sample17(i, threadID$i, RNG$1);
					}
				);
			else
				parallelFor(RNG$, 0, coins, 1,
					(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i = forStart$i; i < forEnd$i; i += 1)
								sample17(i, threadID$i, RNG$1);
					}
				);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		coins = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i = 0; i < coins; i += 1)
			logProbability$var16[i] = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample17) {
			for(int i = 0; i < coins; i += 1)
				logProbability$sample17[i] = Double.NaN;
		}
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[j] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample46) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample46[j] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample46();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample46();
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
		     + "public model Flip2CoinsMK8(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "     \n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins)) \n"
		     + "        bias[i] = beta(a, b).sample();\n"
		     + "            \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli = bernoulli(1 - bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}