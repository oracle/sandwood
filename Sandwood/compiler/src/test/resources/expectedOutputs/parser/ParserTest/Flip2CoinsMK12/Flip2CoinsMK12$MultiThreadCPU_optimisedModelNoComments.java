package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip2CoinsMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip2CoinsMK12$CoreInterface {
	private double[] bias;
	private int coins;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample58 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample58 = false;
	private boolean fixedProbFlag$sample88 = false;
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
	private double[] logProbability$sample58;
	private double[] logProbability$sample88;
	private double logProbability$var14;
	private double logProbability$var27;
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
		fixedProbFlag$sample16 = false;
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample88 = false;
	}

	@Override
	public final int get$coins() {
		return coins;
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (cv$value && fixedProbFlag$sample16);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample58() {
		return fixedFlag$sample58;
	}

	@Override
	public final void set$fixedFlag$sample58(boolean cv$value) {
		fixedFlag$sample58 = cv$value;
		fixedProbFlag$sample58 = (cv$value && fixedProbFlag$sample58);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
	}

	@Override
	public final boolean[][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample58 = false;
		fixedProbFlag$sample88 = false;
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

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0);
			logProbability$beta = (logProbability$beta + cv$distributionAccumulator);
			logProbability$var14 = cv$distributionAccumulator;
			logProbability$bias = (logProbability$bias + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			logProbability$beta = (logProbability$beta + logProbability$var14);
			logProbability$bias = (logProbability$bias + logProbability$var14);
			logProbability$$model = (logProbability$$model + logProbability$var14);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var14);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int i$var26 = 1; i$var26 < coins; i$var26 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(bias[i$var26], 1.0, 1.0));
			logProbability$beta = (logProbability$beta + cv$sampleAccumulator);
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$beta = (logProbability$beta + logProbability$var27);
			logProbability$bias = (logProbability$bias + logProbability$var27);
			logProbability$$model = (logProbability$$model + logProbability$var27);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!fixedProbFlag$sample58) {
			double cv$sampleAccumulator = 0.0;
			for(int var54 = 0; var54 < length$flipsMeasured[0]; var54 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[0][var54], bias[0]));
			logProbability$bernoulli1[0] = cv$sampleAccumulator;
			logProbability$sample58[0] = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample58 = ((fixedFlag$sample58 && fixedFlag$sample16) && fixedFlag$sample29);
		} else {
			double cv$rvAccumulator = logProbability$sample58[0];
			logProbability$bernoulli1[0] = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$rvAccumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$sampleAccumulator = 0.0;
				for(int var82 = 0; var82 < length$flipsMeasured[k]; var82 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBernoulli(flips[k][var82], bias[k]));
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$bernoulli2[(k - 1)] = cv$sampleAccumulator;
				logProbability$sample88[(k - 1)] = cv$sampleAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = ((fixedFlag$sample88 && fixedFlag$sample16) && fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			for(int k = 1; k < coins; k += 1) {
				double cv$rvAccumulator = logProbability$sample88[(k - 1)];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$bernoulli2[(k - 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample16() {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var54 = 0; var54 < length$flipsMeasured[0]; var54 += 1) {
			cv$count = (cv$count + 1);
			if(flips[0][var54])
				cv$sum = (cv$sum + 1);
		}
		bias[0] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample29(int i$var26, int threadID$cv$i$var26, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int var82 = 0; var82 < length$flipsMeasured[i$var26]; var82 += 1) {
			cv$count = (cv$count + 1);
			if(flips[i$var26][var82])
				cv$sum = (cv$sum + 1);
		}
		bias[i$var26] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
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
		logProbability$sample58 = new double[1];
		logProbability$bernoulli2 = new double[(length$flipsMeasured.length - 1)];
		logProbability$sample88 = new double[(length$flipsMeasured.length - 1)];
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var26, int forEnd$i$var26, int threadID$i$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var26 = forStart$i$var26; i$var26 < forEnd$i$var26; i$var26 += 1)
							bias[i$var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample58)
			parallelFor(RNG$, 0, 1, 1,
				(int forStart$index$j, int forEnd$index$j, int threadID$index$j, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$j = forStart$index$j; index$j < forEnd$index$j; index$j += 1) {
							int j = index$j;
							int threadID$j = threadID$index$j;
							boolean[] var45 = flips[j];
							parallelFor(RNG$1, 0, length$flipsMeasured[j], 1,
								(int forStart$var54, int forEnd$var54, int threadID$var54, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var54 = forStart$var54; var54 < forEnd$var54; var54 += 1)
											var45[var54] = DistributionSampling.sampleBernoulli(RNG$2, bias[j]);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample88)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$index$k, int forEnd$index$k, int threadID$index$k, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$k = forStart$index$k; index$k < forEnd$index$k; index$k += 1) {
							int k = index$k;
							int threadID$k = threadID$index$k;
							boolean[] var73 = flips[k];
							parallelFor(RNG$1, 0, length$flipsMeasured[k], 1,
								(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
											var73[var82] = DistributionSampling.sampleBernoulli(RNG$2, bias[k]);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var26, int forEnd$i$var26, int threadID$i$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var26 = forStart$i$var26; i$var26 < forEnd$i$var26; i$var26 += 1)
							bias[i$var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var26, int forEnd$i$var26, int threadID$i$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var26 = forStart$i$var26; i$var26 < forEnd$i$var26; i$var26 += 1)
							bias[i$var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample16)
				sample16();
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 1, coins, 1,
					(int forStart$i$var26, int forEnd$i$var26, int threadID$i$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var26 = forStart$i$var26; i$var26 < forEnd$i$var26; i$var26 += 1)
								sample29(i$var26, threadID$i$var26, RNG$1);
					}
				);

		} else {
			if(!fixedFlag$sample29)
				parallelFor(RNG$, 1, coins, 1,
					(int forStart$i$var26, int forEnd$i$var26, int threadID$i$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var26 = forStart$i$var26; i$var26 < forEnd$i$var26; i$var26 += 1)
								sample29(i$var26, threadID$i$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample16)
				sample16();
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
		if(!fixedProbFlag$sample16)
			logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		logProbability$bernoulli1[0] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample58)
			logProbability$sample58[0] = 0.0;
		for(int k = 1; k < coins; k += 1)
			logProbability$bernoulli2[(k - 1)] = 0.0;
		if(!fixedProbFlag$sample88) {
			for(int k = 1; k < coins; k += 1)
				logProbability$sample88[(k - 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample58();
		logProbabilityValue$sample88();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample29();
		logProbabilityValue$sample58();
		logProbabilityValue$sample88();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample29();
		logProbabilityValue$sample58();
		logProbabilityValue$sample88();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample16)
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!fixedFlag$sample29)
			parallelFor(RNG$, 1, coins, 1,
				(int forStart$i$var26, int forEnd$i$var26, int threadID$i$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var26 = forStart$i$var26; i$var26 < forEnd$i$var26; i$var26 += 1)
							bias[i$var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
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
							(int forStart$m$var109, int forEnd$m$var109, int threadID$m$var109, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int m$var109 = forStart$m$var109; m$var109 < forEnd$m$var109; m$var109 += 1)
										target[m$var109] = flipsMeasured[l][m$var109];
							}
						);
					}
			}
		);
		for(int i$var122 = (coins - 1); i$var122 >= 0; i$var122 -= 1) {
			boolean[] cv$source1 = intermediateFlips[(coins - (i$var122 + 1))];
			boolean[] cv$target1 = flips[i$var122];
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
				cv$target1[cv$index1] = cv$source1[cv$index1];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip2CoinsMK12(boolean[][] flipsMeasured) {\n    int coins = flipsMeasured.length;\n         \n    boolean[][] flips = new boolean[coins][];\n    boolean[][] intermediateFlips = new boolean[coins][];\n    double [] bias = new double[coins];\n        \n    Beta beta = beta(1.0, 1.0);\n        \n    bias[0] = beta.sample();\n        \n    for(int i:[1..coins))\n        bias[i] = beta.sample();\n    \n    for(int j:[0..1)) {\n        int samples = flipsMeasured[j].length;\n        Bernoulli bernoulli1 = bernoulli(bias[j]);\n        flips[j] = bernoulli1.sample(samples);\n    }\n                \n    for(int k:[1..coins)) {\n        int samples = flipsMeasured[k].length;\n        Bernoulli bernoulli2 = bernoulli(bias[k]);\n        flips[k] = bernoulli2.sample(samples);\n    }\n        \n    for(int l:[0..coins)) {\n        boolean[] source = flipsMeasured[l];\n        int noFlips = source.length;\n        boolean[] target = new boolean[noFlips];\n        intermediateFlips[l] = target;\n        \n        for(int m:[0..noFlips))\n            target[m] = source[m];\n    }\n        \n    for(int i:[0..coins)) {\n        boolean[] f = flips[i];\n        boolean[] m = intermediateFlips[coins - (i+1)];\n        f.observe(m);\n    }\n}";
	}
}