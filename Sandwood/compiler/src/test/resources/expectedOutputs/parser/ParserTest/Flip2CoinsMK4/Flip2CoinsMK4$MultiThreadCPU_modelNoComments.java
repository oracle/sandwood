package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK4$CoreInterface {
	private double a;
	private double b;
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample17;
	private double[] logProbability$sample44;
	private double[] logProbability$var16;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK4$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample44 = false;
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
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		fixedProbFlag$sample44 = (fixedFlag$sample17 && fixedProbFlag$sample44);
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
			boolean cv$sampleReached = false;
			for(int i = 0; i < coins; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[i];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, a, b));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + 1.0);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var16[((i - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample17[((i - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < coins; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample17[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var16[((i - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < coins; j += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var43 = 0; var43 < length$flipsMeasured[j]; var43 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = flips[j][var43];
						{
							{
								double var32 = bias[j];
								double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var32:(1.0 - var32))));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				}
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample44[((j - 0) / 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample44 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < coins; j += 1) {
				double cv$rvAccumulator = 0.0;
				for(int var43 = 0; var43 < length$flipsMeasured[j]; var43 += 1)
					cv$sampleReached = true;
				double cv$sampleValue = logProbability$sample44[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int i, int threadID$cv$i, Rng RNG$) {
		if(true) {
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						for(int j = 0; j < coins; j += 1) {
							if((i == j)) {
								{
									for(int var43 = 0; var43 < length$flipsMeasured[j]; var43 += 1) {
										cv$count = (cv$count + 1);
										if(flips[j][var43])
											cv$sum = (cv$sum + 1);
									}
								}
							}
						}
					}
				}
			}
			double var17 = Conjugates.sampleConjugateBetaBinomial(RNG$, a, b, cv$sum, cv$count);
			{
				{
					bias[i] = var17;
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample17) {
			{
				bias = new double[length$flipsMeasured.length];
			}
		}
		{
			flips = new boolean[length$flipsMeasured.length][];
			for(int j = 0; j < length$flipsMeasured.length; j += 1)
				flips[j] = new boolean[length$flipsMeasured[j]];
		}
		{
			logProbability$var16 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample17 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$bernoulli = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample44 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample17)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
					}
			}
		);
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var34 = flips[j];
						parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
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
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample17)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample17)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
					}
			}
		);
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
						int j = index$j;
						int threadID$j = threadID$index$j;
						boolean[] var34 = flips[j];
						parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
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
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample17)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(RNG$, 0, coins, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!fixedFlag$sample17)
							bias[i] = DistributionSampling.sampleBeta(RNG$1, a, b);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward)
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample17)
								sample17(i, threadID$i, RNG$1);
						}
				}
			);
		else
			parallelFor(RNG$, 0, coins, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!fixedFlag$sample17)
								sample17(i, threadID$i, RNG$1);
						}
				}
			);
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
			logProbability$var16[((i - 0) / 1)] = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample17) {
			for(int i = 0; i < coins; i += 1)
				logProbability$sample17[((i - 0) / 1)] = Double.NaN;
		}
		for(int j = 0; j < coins; j += 1)
			logProbability$bernoulli[((j - 0) / 1)] = Double.NaN;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample44) {
			for(int j = 0; j < coins; j += 1)
				logProbability$sample44[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample44();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample44();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[][] cv$source1 = flipsMeasured;
		boolean[][] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = cv$source1[cv$index1];
			boolean[] cv$target2 = cv$target1[cv$index1];
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
		     + "public model Flip2CoinsMK4(double a, double b, boolean[][] flipsMeasured) {\n"
		     + "\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "    double[] bias = new double[coins];\n"
		     + "    for(int i:[0..coins)) \n"
		     + "        bias[i] = beta(a, b).sample();\n"
		     + "                \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "        \n"
		     + "    for(int j:[0..coins)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli.sample(samples);\n"
		     + "    }\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}