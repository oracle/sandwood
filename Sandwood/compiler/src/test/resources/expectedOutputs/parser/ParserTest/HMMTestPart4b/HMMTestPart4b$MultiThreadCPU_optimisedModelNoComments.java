package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4b$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart4b$CoreInterface {
	private double[] bias;
	private double[][] cv$var18$countGlobal;
	private double[] cv$var49$stateProbabilityGlobal;
	private double[][] cv$var67$stateProbabilityGlobal;
	private boolean fixedFlag$sample102 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample69 = false;
	private boolean fixedProbFlag$sample102 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample69 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[][][] logProbability$sample102;
	private double[][][] logProbability$sample69;
	private double logProbability$st;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double logProbability$var48;
	private double logProbability$var49;
	private double[][][] logProbability$var66;
	private double[][][] logProbability$var99;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[][][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4b$MultiThreadCPU(ExecutionTarget target) {
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
	public final boolean get$fixedFlag$sample102() {
		return fixedFlag$sample102;
	}

	@Override
	public final void set$fixedFlag$sample102(boolean cv$value) {
		fixedFlag$sample102 = cv$value;
		fixedProbFlag$sample102 = (cv$value && fixedProbFlag$sample102);
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample102 = (cv$value && fixedProbFlag$sample102);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample102 = (cv$value && fixedProbFlag$sample102);
	}

	@Override
	public final boolean get$fixedFlag$sample69() {
		return fixedFlag$sample69;
	}

	@Override
	public final void set$fixedFlag$sample69(boolean cv$value) {
		fixedFlag$sample69 = cv$value;
		fixedProbFlag$sample69 = (cv$value && fixedProbFlag$sample69);
		fixedProbFlag$sample102 = (cv$value && fixedProbFlag$sample102);
	}

	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
	}

	@Override
	public final boolean[][][] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[][][] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int[][] get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int[][] cv$value) {
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[][][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][][] cv$value) {
		st = cv$value;
		setFlag$st = true;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample102() {
		if(!fixedProbFlag$sample102) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[l][n][p], bias[st[p][l][n]]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var99[l][p][n] = cv$distributionAccumulator;
						logProbability$sample102[l][p][n] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample102 = (((fixedFlag$sample102 && fixedFlag$sample28) && fixedFlag$sample51) && fixedFlag$sample69);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$rvAccumulator = logProbability$sample102[l][p][n];
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var99[l][p][n] = cv$rvAccumulator;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v) + DistributionSampling.logProbabilityDirichlet(m[1], v));
			logProbability$var13 = cv$sampleAccumulator;
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			logProbability$var13 = logProbability$var18;
			logProbability$m = (logProbability$m + logProbability$var18);
			logProbability$$model = (logProbability$$model + logProbability$var18);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var18);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$var22 = logProbability$var27;
			logProbability$bias = (logProbability$bias + logProbability$var27);
			logProbability$$model = (logProbability$$model + logProbability$var27);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[0][0][0], m[0]);
			logProbability$var48 = cv$distributionAccumulator;
			logProbability$var49 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedFlag$sample19);
		} else {
			logProbability$var48 = logProbability$var49;
			logProbability$st = (logProbability$st + logProbability$var49);
			logProbability$$model = (logProbability$$model + logProbability$var49);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var49);
		}
	}

	private final void logProbabilityValue$sample69() {
		if(!fixedProbFlag$sample69) {
			double cv$accumulator = 0.0;
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityCategorical(st[i$var55][j$var58][k], m[0]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var66[(i$var55 - 1)][j$var58][k] = cv$distributionAccumulator;
						logProbability$sample69[(i$var55 - 1)][j$var58][k] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample69 = (fixedFlag$sample69 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$rvAccumulator = logProbability$sample69[(i$var55 - 1)][j$var58][k];
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var66[(i$var55 - 1)][j$var58][k] = cv$rvAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample69)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample19(int var17, int threadID$cv$var17, Rng RNG$) {
		double[] cv$countLocal = cv$var18$countGlobal[threadID$cv$var17];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var17 == 0)) {
			cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1)
						cv$countLocal[st[i$var55][j$var58][k]] = (cv$countLocal[st[i$var55][j$var58][k]] + 1.0);
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var17]);
	}

	private final void sample28(int var26, int threadID$cv$var26, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					if((var26 == st[p][l][n])) {
						cv$count = (cv$count + 1);
						if(flips[l][n][p])
							cv$sum = (cv$sum + 1);
					}
				}
			}
		}
		bias[var26] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample51() {
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(0, m[0]);
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0][0][0], bias[0]) + cv$accumulatedProbabilities);
			cv$var49$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityCategorical(1, m[0]);
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0][0][0], bias[1]) + cv$accumulatedProbabilities);
		cv$var49$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var49$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var49$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var49$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var49$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var49$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var49$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var49$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$var49$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var49$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$var49$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var49$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, cv$var49$stateProbabilityGlobal);
	}

	private final void sample69(int i$var55, int j$var58, int k, int threadID$cv$k, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var67$stateProbabilityGlobal[threadID$cv$k];
		cv$stateProbabilityLocal[0] = (DistributionSampling.logProbabilityBernoulli(flips[j$var58][k][i$var55], bias[0]) + DistributionSampling.logProbabilityCategorical(0, m[0]));
		cv$stateProbabilityLocal[1] = (DistributionSampling.logProbabilityBernoulli(flips[j$var58][k][i$var55], bias[1]) + DistributionSampling.logProbabilityCategorical(1, m[0]));
		double cv$logSum;
		double cv$lseMax = cv$stateProbabilityLocal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		st[i$var55][j$var58][k] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$threadCount = threadCount();
			cv$var18$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var18$countGlobal[cv$index] = new double[2];
		}
		cv$var49$stateProbabilityGlobal = new double[2];
		int cv$threadCount = threadCount();
		cv$var67$stateProbabilityGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var67$stateProbabilityGlobal[cv$index] = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!setFlag$m) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!setFlag$bias)
			bias = new double[2];
		if(!setFlag$st) {
			st = new int[length$flipsMeasured.length][][];
			for(int var33 = 0; var33 < length$flipsMeasured.length; var33 += 1) {
				int[][] subarray$0 = new int[length$flipsMeasured.length][];
				st[var33] = subarray$0;
				for(int var37 = 0; var37 < length$flipsMeasured.length; var37 += 1)
					subarray$0[var37] = new int[length$flipsMeasured.length];
			}
		}
		if(!setFlag$flips) {
			flips = new boolean[length$flipsMeasured.length][][];
			for(int i$var74 = 0; i$var74 < length$flipsMeasured.length; i$var74 += 1) {
				boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
				flips[i$var74] = subarray$0;
				for(int j$var79 = 0; j$var79 < length$flipsMeasured.length; j$var79 += 1)
					subarray$0[j$var79] = new boolean[length$flipsMeasured.length];
			}
		}
		logProbability$var66 = new double[(length$flipsMeasured.length - 1)][][];
		for(int i$var55 = 1; i$var55 < length$flipsMeasured.length; i$var55 += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$var66[(i$var55 - 1)] = subarray$0;
			for(int j$var58 = 0; j$var58 < length$flipsMeasured.length; j$var58 += 1)
				subarray$0[j$var58] = new double[length$flipsMeasured.length];
		}
		logProbability$sample69 = new double[(length$flipsMeasured.length - 1)][][];
		for(int i$var55 = 1; i$var55 < length$flipsMeasured.length; i$var55 += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$sample69[(i$var55 - 1)] = subarray$0;
			for(int j$var58 = 0; j$var58 < length$flipsMeasured.length; j$var58 += 1)
				subarray$0[j$var58] = new double[length$flipsMeasured.length];
		}
		logProbability$var99 = new double[length$flipsMeasured.length][][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$var99[l] = subarray$0;
			for(int p = 0; p < length$flipsMeasured.length; p += 1)
				subarray$0[p] = new double[length$flipsMeasured.length];
		}
		logProbability$sample102 = new double[length$flipsMeasured.length][][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$sample102[l] = subarray$0;
			for(int p = 0; p < length$flipsMeasured.length; p += 1)
				subarray$0[p] = new double[length$flipsMeasured.length];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample51)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample69)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

		if(!fixedFlag$sample102)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
							int l = index$l;
							boolean[][] var93 = flips[l];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
											int p = index$p;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int n = forStart$n; n < forEnd$n; n += 1)
															var93[n][p] = DistributionSampling.sampleBernoulli(RNG$3, bias[st[p][l][n]]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample19)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample51)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample69)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample51)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample69)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample19)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample19(var17, threadID$var17, RNG$1);
					}
				);

			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample28(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample69)
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i$var55, int forEnd$index$i$var55, int threadID$index$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i$var55 = forStart$index$i$var55; index$i$var55 < forEnd$index$i$var55; index$i$var55 += 1) {
								int i$var55 = index$i$var55;
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
												int j$var58 = index$j$var58;
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														for(int k = forStart$k; k < forEnd$k; k += 1)
																sample69(i$var55, j$var58, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

		} else {
			if(!fixedFlag$sample69)
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i$var55, int forEnd$index$i$var55, int threadID$index$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i$var55 = forStart$index$i$var55; index$i$var55 < forEnd$index$i$var55; index$i$var55 += 1) {
								int i$var55 = index$i$var55;
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
												int j$var58 = index$j$var58;
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
														for(int k = forStart$k; k < forEnd$k; k += 1)
																sample69(i$var55, j$var58, k, threadID$k, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample28(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample19)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
								sample19(var17, threadID$var17, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var10, int forEnd$i$var10, int threadID$i$var10, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var10 = forStart$i$var10; i$var10 < forEnd$i$var10; i$var10 += 1)
						v[i$var10] = 0.1;
			}
		);
		samples = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var13 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$var18 = 0.0;
		logProbability$var22 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var27 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var49 = 0.0;
		for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
			for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
				for(int k = 0; k < samples; k += 1)
					logProbability$var66[(i$var55 - 1)][j$var58][k] = 0.0;
			}
		}
		if(!fixedProbFlag$sample69) {
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1)
						logProbability$sample69[(i$var55 - 1)][j$var58][k] = 0.0;
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1)
					logProbability$var99[l][p][n] = 0.0;
			}
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample102) {
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						logProbability$sample102[l][p][n] = 0.0;
				}
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
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample69)
			logProbabilityValue$sample69();
		logProbabilityValue$sample102();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample28();
		logProbabilityValue$sample51();
		logProbabilityValue$sample69();
		logProbabilityValue$sample102();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample28();
		logProbabilityValue$sample51();
		logProbabilityValue$sample69();
		logProbabilityValue$sample102();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample19)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var17]);
				}
			);

		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample51)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample69)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i$var55, int forEnd$i$var55, int threadID$i$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var55 = forStart$i$var55; i$var55 < forEnd$i$var55; i$var55 += 1) {
							int[][] var62 = st[i$var55];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j$var58, int forEnd$index$j$var58, int threadID$index$j$var58, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j$var58 = forStart$index$j$var58; index$j$var58 < forEnd$index$j$var58; index$j$var58 += 1) {
											int j$var58 = index$j$var58;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k, int forEnd$k, int threadID$k, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k = forStart$k; k < forEnd$k; k += 1)
															var62[j$var58][k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
												}
											);
										}
								}
							);
						}
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = flipsMeasured[cv$index1];
			boolean[][] cv$target2 = flips[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart4b(boolean[][][] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        \n        int[][][] st = new int[samples][samples][samples];\n\n        st[0][0][0] = categorical(m[0]).sample();\n\n        for(int i:[1..samples))\n            for(int j:[0..samples))\n                for(int k:[0..samples))\n                    st[i][j][k] = categorical(m[0]).sample();\n            \n        boolean[][][] flips = new boolean[samples][][];\n        for(int i:[0..samples)) {\n            flips[i] = new boolean[samples][];\n            for(int j:[0..samples))\n                flips[i][j] = new boolean[samples];\n        }\n            \n        for(int l:[0..samples))\n            for(int p:[0..samples))\n                for(int n:[0..samples))\n                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}