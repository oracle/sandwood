package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK12$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample10 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedProbFlag$sample10 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[][] flips;
	private boolean[][] flipsMeasured;
	private boolean[][] intermediateFlips;
	private int[] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double[] logProbability$bernoulli1;
	private double[] logProbability$bernoulli2;
	private double logProbability$beta;
	private double logProbability$bias;
	private double logProbability$flips;
	private double[] logProbability$sample50;
	private double[] logProbability$sample78;
	private double logProbability$var10;
	private double logProbability$var23;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip2CoinsMK12$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample10 = false;
		fixedProbFlag$sample23 = false;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample78 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample10() {
		return fixedFlag$sample10;
	}

	@Override
	public final void set$fixedFlag$sample10(boolean cv$value) {
		fixedFlag$sample10 = cv$value;
		fixedProbFlag$sample10 = (cv$value && fixedProbFlag$sample10);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		fixedFlag$sample78 = cv$value;
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample78 = false;
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
	public final boolean[][] get$intermediateFlips() {
		return intermediateFlips;
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
	public final double[] get$logProbability$bernoulli1() {
		return logProbability$bernoulli1;
	}

	@Override
	public final double[] get$logProbability$bernoulli2() {
		return logProbability$bernoulli2;
	}

	@Override
	public final double get$logProbability$beta() {
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

	private final void logProbabilityValue$sample10() {
		if(!fixedProbFlag$sample10) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0);
			logProbability$beta = (logProbability$beta + cv$distributionAccumulator);
			logProbability$var10 = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample10 = fixedFlag$sample10;
		} else {
			logProbability$beta = (logProbability$beta + logProbability$var10);
			logProbability$bias = (logProbability$bias + logProbability$var10);
			logProbability$$model = (logProbability$$model + logProbability$var10);
			if(fixedFlag$sample10)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var10);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var22 = 1; i$var22 < coins; i$var22 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[i$var22], 1.0, 1.0));
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$var23 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			logProbability$beta = (logProbability$beta + logProbability$var23);
			logProbability$bias = (logProbability$bias + logProbability$var23);
			logProbability$$model = (logProbability$$model + logProbability$var23);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var23);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$sampleAccumulator = 0.0;
			for(int var49 = 0; var49 < length$flipsMeasured[0]; var49 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[0][var49], bias[0]));
			logProbability$bernoulli1[0] = cv$sampleAccumulator;
			logProbability$sample50[0] = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample50 = ((fixedFlag$sample50 && fixedFlag$sample10) && fixedFlag$sample23);
		} else {
			double cv$rvAccumulator = logProbability$sample50[0];
			logProbability$bernoulli1[0] = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var76 = 0; var76 < length$flipsMeasured[k]; var76 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[k][var76], bias[k]));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli2[(k - 1)] = cv$sampleAccumulator;
				logProbability$sample78[(k - 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = ((fixedFlag$sample78 && fixedFlag$sample10) && fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = logProbability$sample78[(k - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[(k - 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample10() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var49 = 0; var49 < length$flipsMeasured[0]; var49 += 1) {
			cv$count = (cv$count + 1);
			if(flips[0][var49])
				cv$sum = (cv$sum + 1);
		}
		bias[0] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample23(int i$var22, int threadID$cv$i$var22, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var76 = 0; var76 < length$flipsMeasured[i$var22]; var76 += 1) {
			cv$count = (cv$count + 1);
			if(flips[i$var22][var76])
				cv$sum = (cv$sum + 1);
		}
		bias[i$var22] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips) {
			flips = new boolean[length$flipsMeasured.length][];
			flips[0] = new boolean[length$flipsMeasured[0]];
			for(int k = 1; k < length$flipsMeasured.length; k += 1)
				flips[k] = new boolean[length$flipsMeasured[k]];
		}
		intermediateFlips = new boolean[length$flipsMeasured.length][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1)
			intermediateFlips[l] = new boolean[length$flipsMeasured[l]];
		if(!setFlag$bias)
			bias = new double[length$flipsMeasured.length];
		logProbability$bernoulli1 = new double[1];
		logProbability$sample50 = new double[1];
		logProbability$bernoulli2 = new double[(length$flipsMeasured.length - 1)];
		logProbability$sample78 = new double[(length$flipsMeasured.length - 1)];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample50)
			parallelFor(RNG$, 0, 1, 1,
				(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
							int j = index$j;
							int threadID$j = threadID$index$j;
							boolean[] var40 = flips[j];
							parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
								(int forStart$var49, int forEnd$var49, int threadID$var49, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var49 = forStart$var49; var49 < forEnd$var49; var49 += 1)
											var40[var49] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample78)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							boolean[] var67 = flips[k];
							parallelFor(RNG$1, 0, length$flipsMeasured[k], 1,
								(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
											var67[var76] = DistributionSampling.sampleBernoulli(RNG$2, bias[k]);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample10)
				sample10();
			if(!fixedFlag$sample23)
				parallelFor(RNG$, 1, coins, 1,
					(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
								sample23(i$var22, threadID$i$var22, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample23)
				parallelFor(RNG$, 1, coins, 1,
					(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
								sample23(i$var22, threadID$i$var22, RNG$1);
					}
				);

			if(!fixedFlag$sample10)
				sample10();
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
		logProbability$beta = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample10)
			logProbability$var10 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$var23 = 0.0;
		logProbability$bernoulli1[0] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$sample50[0] = 0.0;
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[(k - 1)] = 0.0;
		if(!fixedProbFlag$sample78) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample78[(k - 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample10)
			logProbabilityValue$sample10();
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample10();
		logProbabilityValue$sample23();
		logProbabilityValue$sample50();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample10)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample23)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var22, int forEnd$i$var22, int threadID$i$var22, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var22 = forStart$i$var22; i$var22 < forEnd$i$var22; i$var22 += 1)
							bias[i$var22] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		parallelFor(RNG$, 0, length$flipsMeasured.length, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[] target = intermediateFlips[l];
						parallelFor(RNG$1, 0, length$flipsMeasured[l], 1,
							(int forStart$m$var102, int forEnd$m$var102, int threadID$m$var102, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int m$var102 = forStart$m$var102; m$var102 < forEnd$m$var102; m$var102 += 1)
										target[m$var102] = flipsMeasured[l][m$var102];
							}
						);
					}
			}
		);
		for(int i$var115 = (coins - 1); i$var115 >= 0; i$var115 -= 1) {
			boolean[] cv$source1 = intermediateFlips[(coins - (i$var115 + 1))];
			boolean[] cv$target1 = flips[i$var115];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "public model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n"
		     + "    int coins = flipsMeasured.length;\n"
		     + "         \n"
		     + "    boolean[][] flips = new boolean[coins][];\n"
		     + "    boolean[][] intermediateFlips = new boolean[coins][];\n"
		     + "    double [] bias = new double[coins];\n"
		     + "        \n"
		     + "    Beta beta = beta(1.0, 1.0);\n"
		     + "        \n"
		     + "    bias[0] = beta.sample();\n"
		     + "        \n"
		     + "    for(int i:[1..coins))\n"
		     + "        bias[i] = beta.sample();\n"
		     + "    \n"
		     + "    for(int j:[0..1)) {\n"
		     + "        int samples = flipsMeasured[j].length;\n"
		     + "        Bernoulli bernoulli1 = bernoulli(bias[j]);\n"
		     + "        flips[j] = bernoulli1.sample(samples);\n"
		     + "    }\n"
		     + "                \n"
		     + "    for(int k:[1..coins)) {\n"
		     + "        int samples = flipsMeasured[k].length;\n"
		     + "        Bernoulli bernoulli2 = bernoulli(bias[k]);\n"
		     + "        flips[k] = bernoulli2.sample(samples);\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int l:[0..coins)) {\n"
		     + "        boolean[] source = flipsMeasured[l];\n"
		     + "        int noFlips = source.length;\n"
		     + "        boolean[] target = new boolean[noFlips];\n"
		     + "        intermediateFlips[l] = target;\n"
		     + "        \n"
		     + "        for(int m:[0..noFlips))\n"
		     + "            target[m] = source[m];\n"
		     + "    }\n"
		     + "        \n"
		     + "    for(int i:[0..coins)) {\n"
		     + "        boolean[] f = flips[i];\n"
		     + "        boolean[] m = intermediateFlips[coins - (i+1)];\n"
		     + "        f.observe(m);\n"
		     + "    }\n"
		     + "}";
	}
}