package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart4$CoreInterface {
	private double[] bias;
	private double[][] cv$var121$stateProbabilityGlobal;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var81$stateProbabilityGlobal;
	private boolean fixedFlag$sample124 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample124 = false;
	private boolean fixedProbFlag$sample191 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[][][] logProbability$sample124;
	private double[][][] logProbability$sample191;
	private double logProbability$st;
	private double[][][] logProbability$var120;
	private double logProbability$var16;
	private double[][][] logProbability$var185;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var80;
	private double logProbability$var81;
	private double[][] m;
	private int samples;
	private int[][][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample191 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample124() {
		return fixedFlag$sample124;
	}

	@Override
	public final void set$fixedFlag$sample124(boolean cv$value) {
		fixedFlag$sample124 = cv$value;
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
		fixedProbFlag$sample191 = (cv$value && fixedProbFlag$sample191);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (cv$value && fixedProbFlag$sample28);
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		fixedProbFlag$sample124 = (cv$value && fixedProbFlag$sample124);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (cv$value && fixedProbFlag$sample45);
		fixedProbFlag$sample191 = (cv$value && fixedProbFlag$sample191);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		fixedFlag$sample84 = cv$value;
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		fixedProbFlag$sample191 = (cv$value && fixedProbFlag$sample191);
	}

	@Override
	public final boolean[][][] get$flips() {
		return flips;
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
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample124 = false;
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
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample124 = false;
		fixedProbFlag$sample191 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample124() {
		if(!fixedProbFlag$sample124) {
			double cv$accumulator = 0.0;
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1) {
						int cv$sampleValue = st[i1][j1][k1];
						double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var120[(i1 - 1)][j1][k1] = cv$distributionAccumulator;
						logProbability$sample124[(i1 - 1)][j1][k1] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample124)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample124 = (fixedFlag$sample124 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1) {
						double cv$rvAccumulator = logProbability$sample124[(i1 - 1)][j1][k1];
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var120[(i1 - 1)][j1][k1] = cv$rvAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample124)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample191() {
		if(!fixedProbFlag$sample191) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double var184 = bias[st[p][l][n]];
						double cv$distributionAccumulator = Math.log((flips[l][n][p]?var184:(1.0 - var184)));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var185[l][p][n] = cv$distributionAccumulator;
						logProbability$sample191[l][p][n] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample191 = ((fixedFlag$sample45 && fixedFlag$sample84) && fixedFlag$sample124);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$rvAccumulator = logProbability$sample191[l][p][n];
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var185[l][p][n] = cv$rvAccumulator;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(m[0], v, 2) + DistributionSampling.logProbabilityDirichlet(m[1], v, 2));
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			logProbability$var16 = logProbability$var28;
			logProbability$m = (logProbability$m + logProbability$var28);
			logProbability$$model = (logProbability$$model + logProbability$var28);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			logProbability$var32 = logProbability$var44;
			logProbability$bias = (logProbability$bias + logProbability$var44);
			logProbability$$model = (logProbability$$model + logProbability$var44);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			int cv$sampleValue = st[0][0][0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < 2))?Math.log(m[0][cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var80 = cv$distributionAccumulator;
			logProbability$var81 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedFlag$sample28);
		} else {
			logProbability$var80 = logProbability$var81;
			logProbability$st = (logProbability$st + logProbability$var81);
			logProbability$$model = (logProbability$$model + logProbability$var81);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var81);
		}
	}

	private final void sample124(int i1, int j1, int k1, int threadID$cv$k1, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var121$stateProbabilityGlobal[threadID$cv$k1];
		{
			double cv$temp$2$var184 = bias[0];
			cv$stateProbabilityLocal[0] = (Math.log((flips[j1][k1][i1]?cv$temp$2$var184:(1.0 - cv$temp$2$var184))) + Math.log(m[0][0]));
		}
		double cv$temp$2$var184 = bias[1];
		cv$stateProbabilityLocal[1] = (Math.log((flips[j1][k1][i1]?cv$temp$2$var184:(1.0 - cv$temp$2$var184))) + Math.log(m[0][1]));
		double cv$logSum;
		double cv$lseMax = cv$stateProbabilityLocal[0];
		double cv$lseElementValue = cv$stateProbabilityLocal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$stateProbabilityLocal[0] - cv$lseMax)) + Math.exp((cv$stateProbabilityLocal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$stateProbabilityLocal[0] = 0.5;
			cv$stateProbabilityLocal[1] = 0.5;
		} else {
			cv$stateProbabilityLocal[0] = Math.exp((cv$stateProbabilityLocal[0] - cv$logSum));
			cv$stateProbabilityLocal[1] = Math.exp((cv$stateProbabilityLocal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i1][j1][k1] = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, 2);
	}

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if((var27 == 0)) {
			cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						cv$countLocal[st[i1][j1][k1]] = (cv$countLocal[st[i1][j1][k1]] + 1.0);
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var27], 2);
	}

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					if((var43 == st[p][l][n])) {
						cv$count = (cv$count + 1);
						if(flips[l][n][p])
							cv$sum = (cv$sum + 1);
					}
				}
			}
		}
		bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample84() {
		{
			double cv$accumulatedProbabilities = Math.log(m[0][0]);
			if((0 < samples)) {
				double cv$temp$2$var184 = bias[0];
				cv$accumulatedProbabilities = (Math.log((flips[0][0][0]?cv$temp$2$var184:(1.0 - cv$temp$2$var184))) + cv$accumulatedProbabilities);
			}
			cv$var81$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double cv$accumulatedProbabilities = Math.log(m[0][1]);
		if((0 < samples)) {
			double cv$temp$2$var184 = bias[1];
			cv$accumulatedProbabilities = (Math.log((flips[0][0][0]?cv$temp$2$var184:(1.0 - cv$temp$2$var184))) + cv$accumulatedProbabilities);
		}
		cv$var81$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var81$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var81$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var81$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var81$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var81$stateProbabilityGlobal[0] = 0.5;
			cv$var81$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var81$stateProbabilityGlobal[0] = Math.exp((cv$var81$stateProbabilityGlobal[0] - cv$logSum));
			cv$var81$stateProbabilityGlobal[1] = Math.exp((cv$var81$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var81$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var81$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, cv$var81$stateProbabilityGlobal, 2);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$threadCount = threadCount();
			cv$var28$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var28$countGlobal[cv$index] = new double[2];
		}
		cv$var81$stateProbabilityGlobal = new double[2];
		int cv$threadCount = threadCount();
		cv$var121$stateProbabilityGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var121$stateProbabilityGlobal[cv$index] = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[2];
		if(!fixedFlag$sample28) {
			m = new double[2][];
			m[0] = new double[2];
			m[1] = new double[2];
		}
		if(!fixedFlag$sample45)
			bias = new double[2];
		if((!fixedFlag$sample84 || !fixedFlag$sample124)) {
			st = new int[length$flipsMeasured.length][][];
			for(int i$var57 = 0; i$var57 < length$flipsMeasured.length; i$var57 += 1) {
				int[][] subarray$0 = new int[length$flipsMeasured.length][];
				st[i$var57] = subarray$0;
				for(int j = 0; j < length$flipsMeasured.length; j += 1)
					subarray$0[j] = new int[length$flipsMeasured.length];
			}
		}
		flips = new boolean[length$flipsMeasured.length][][];
		for(int i2 = 0; i2 < length$flipsMeasured.length; i2 += 1) {
			boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
			flips[i2] = subarray$0;
			for(int j2 = 0; j2 < length$flipsMeasured.length; j2 += 1)
				subarray$0[j2] = new boolean[length$flipsMeasured.length];
		}
		logProbability$var120 = new double[(length$flipsMeasured.length - 1)][][];
		for(int i1 = 1; i1 < length$flipsMeasured.length; i1 += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$var120[(i1 - 1)] = subarray$0;
			for(int j1 = 0; j1 < length$flipsMeasured.length; j1 += 1)
				subarray$0[j1] = new double[length$flipsMeasured.length];
		}
		logProbability$sample124 = new double[(length$flipsMeasured.length - 1)][][];
		for(int i1 = 1; i1 < length$flipsMeasured.length; i1 += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$sample124[(i1 - 1)] = subarray$0;
			for(int j1 = 0; j1 < length$flipsMeasured.length; j1 += 1)
				subarray$0[j1] = new double[length$flipsMeasured.length];
		}
		logProbability$var185 = new double[length$flipsMeasured.length][][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$var185[l] = subarray$0;
			for(int p = 0; p < length$flipsMeasured.length; p += 1)
				subarray$0[p] = new double[length$flipsMeasured.length];
		}
		logProbability$sample191 = new double[length$flipsMeasured.length][][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$sample191[l] = subarray$0;
			for(int p = 0; p < length$flipsMeasured.length; p += 1)
				subarray$0[p] = new double[length$flipsMeasured.length];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample124)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = st[i1];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[][] var179 = flips[l];
						parallelFor(RNG$1, 0, samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										int threadID$p = threadID$index$p;
										parallelFor(RNG$2, 0, samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int n = forStart$n; n < forEnd$n; n += 1)
														var179[n][p] = DistributionSampling.sampleBernoulli(RNG$3, bias[st[p][l][n]]);
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
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample124)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = st[i1];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, m[0], 2);
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
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample124)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = st[i1];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, m[0], 2);
												}
											);
										}
								}
							);
						}
				}
			);

		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$l, int forEnd$index$l, int threadID$index$l, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$l = forStart$index$l; index$l < forEnd$index$l; index$l += 1) {
						int l = index$l;
						int threadID$l = threadID$index$l;
						boolean[][] var179 = flips[l];
						parallelFor(RNG$1, 0, samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										int threadID$p = threadID$index$p;
										parallelFor(RNG$2, 0, samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int n = forStart$n; n < forEnd$n; n += 1)
														var179[n][p] = DistributionSampling.sampleBernoulli(RNG$3, bias[st[p][l][n]]);
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
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample124)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = st[i1];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, m[0], 2);
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
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample28)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, 2, m[var27]);
				}
			);

		if(!fixedFlag$sample45)
			parallelFor(RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0], 2);
		if(!fixedFlag$sample124)
			parallelFor(RNG$, 1, samples, 1,
				(int forStart$i1, int forEnd$i1, int threadID$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i1 = forStart$i1; i1 < forEnd$i1; i1 += 1) {
							int[][] var116 = st[i1];
							parallelFor(RNG$1, 0, samples, 1,
								(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
											int j1 = index$j1;
											int threadID$j1 = threadID$index$j1;
											parallelFor(RNG$2, 0, samples, 1,
												(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
													for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
															var116[j1][k1] = DistributionSampling.sampleCategorical(RNG$3, m[0], 2);
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
			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample124)
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i1, int forEnd$index$i1, int threadID$index$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i1 = forStart$index$i1; index$i1 < forEnd$index$i1; index$i1 += 1) {
								int i1 = index$i1;
								int threadID$i1 = threadID$index$i1;
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
												int j1 = index$j1;
												int threadID$j1 = threadID$index$j1;
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
														for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
																sample124(i1, j1, k1, threadID$k1, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

		} else {
			if(!fixedFlag$sample124)
				parallelFor(RNG$, 1, samples, 1,
					(int forStart$index$i1, int forEnd$index$i1, int threadID$index$i1, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$i1 = forStart$index$i1; index$i1 < forEnd$index$i1; index$i1 += 1) {
								int i1 = index$i1;
								int threadID$i1 = threadID$index$i1;
								parallelFor(RNG$1, 0, samples, 1,
									(int forStart$index$j1, int forEnd$index$j1, int threadID$index$j1, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int index$j1 = forStart$index$j1; index$j1 < forEnd$index$j1; index$j1 += 1) {
												int j1 = index$j1;
												int threadID$j1 = threadID$index$j1;
												parallelFor(RNG$2, 0, samples, 1,
													(int forStart$k1, int forEnd$k1, int threadID$k1, org.sandwood.random.internal.Rng RNG$3) -> { 
														for(int k1 = forStart$k1; k1 < forEnd$k1; k1 += 1)
																sample124(i1, j1, k1, threadID$k1, RNG$3);
													}
												);
											}
									}
								);
							}
					}
				);

			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample45)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								sample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!fixedFlag$sample28)
				parallelFor(RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								sample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, 2, 1,
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
		samples = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		logProbability$var32 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = Double.NaN;
		logProbability$var80 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var81 = Double.NaN;
		for(int i1 = 1; i1 < samples; i1 += 1) {
			for(int j1 = 0; j1 < samples; j1 += 1) {
				for(int k1 = 0; k1 < samples; k1 += 1)
					logProbability$var120[(i1 - 1)][j1][k1] = Double.NaN;
			}
		}
		if(!fixedProbFlag$sample124) {
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						logProbability$sample124[(i1 - 1)][j1][k1] = Double.NaN;
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1)
					logProbability$var185[l][p][n] = Double.NaN;
			}
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample191) {
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						logProbability$sample191[l][p][n] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		if(fixedFlag$sample124)
			logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "model HMMTestPart4(boolean[][][] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "        \n"
		     + "        int[][][] st = new int[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i] = new int[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                st[i][j] = new int[samples];\n"
		     + "        }\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i1:[1..samples))\n"
		     + "            for(int j1:[0..samples))\n"
		     + "                for(int k1:[0..samples))\n"
		     + "                    st[i1][j1][k1] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i2:[0..samples)) {\n"
		     + "            flips[i2] = new boolean[samples][];\n"
		     + "            for(int j2:[0..samples))\n"
		     + "                flips[i2][j2] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}