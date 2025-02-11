package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart4$CoreInterface {
	private double[] bias;
	private double[] cv$var18$countGlobal;
	private double[] cv$var51$stateProbabilityGlobal;
	private double[] cv$var69$stateProbabilityGlobal;
	private boolean fixedFlag$sample109 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample56 = false;
	private boolean fixedFlag$sample74 = false;
	private boolean fixedProbFlag$sample109 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample56 = false;
	private boolean fixedProbFlag$sample74 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[][][] logProbability$sample109;
	private double[][][] logProbability$sample74;
	private double logProbability$st;
	private double[][][] logProbability$var101;
	private double logProbability$var13;
	private double logProbability$var18;
	private double logProbability$var22;
	private double logProbability$var27;
	private double logProbability$var50;
	private double logProbability$var51;
	private double[][][] logProbability$var68;
	private double[][] m;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[][][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample109 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample109() {
		return fixedFlag$sample109;
	}

	@Override
	public final void set$fixedFlag$sample109(boolean cv$value) {
		fixedFlag$sample109 = cv$value;
		fixedProbFlag$sample109 = (cv$value && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample109 = (cv$value && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample56() {
		return fixedFlag$sample56;
	}

	@Override
	public final void set$fixedFlag$sample56(boolean cv$value) {
		fixedFlag$sample56 = cv$value;
		fixedProbFlag$sample56 = (cv$value && fixedProbFlag$sample56);
		fixedProbFlag$sample109 = (cv$value && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample74() {
		return fixedFlag$sample74;
	}

	@Override
	public final void set$fixedFlag$sample74(boolean cv$value) {
		fixedFlag$sample74 = cv$value;
		fixedProbFlag$sample74 = (cv$value && fixedProbFlag$sample74);
		fixedProbFlag$sample109 = (cv$value && fixedProbFlag$sample109);
	}

	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample109 = false;
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
		fixedProbFlag$sample19 = false;
		fixedProbFlag$sample56 = false;
		fixedProbFlag$sample74 = false;
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
		fixedProbFlag$sample56 = false;
		fixedProbFlag$sample74 = false;
		fixedProbFlag$sample109 = false;
	}

	@Override
	public final int get$states() {
		return 2;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample109() {
		if(!fixedProbFlag$sample109) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(flips[l][n][p], bias[st[p][l][n]]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var101[l][p][n] = cv$distributionAccumulator;
						logProbability$sample109[l][p][n] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample109 = (((fixedFlag$sample109 && fixedFlag$sample29) && fixedFlag$sample56) && fixedFlag$sample74);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$rvAccumulator = logProbability$sample109[l][p][n];
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var101[l][p][n] = cv$rvAccumulator;
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

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(bias[1], 1.0, 1.0));
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$var22 = logProbability$var27;
			logProbability$bias = (logProbability$bias + logProbability$var27);
			logProbability$$model = (logProbability$$model + logProbability$var27);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	private final void logProbabilityValue$sample56() {
		if(!fixedProbFlag$sample56) {
			int cv$sampleValue = st[0][0][0];
			double[] var49 = m[0];
			double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var49.length))?Math.log(var49[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			logProbability$var50 = cv$distributionAccumulator;
			logProbability$var51 = cv$distributionAccumulator;
			logProbability$st = (logProbability$st + cv$distributionAccumulator);
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample56)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample56 = (fixedFlag$sample56 && fixedFlag$sample19);
		} else {
			logProbability$var50 = logProbability$var51;
			logProbability$st = (logProbability$st + logProbability$var51);
			logProbability$$model = (logProbability$$model + logProbability$var51);
			if(fixedFlag$sample56)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	private final void logProbabilityValue$sample74() {
		if(!fixedProbFlag$sample74) {
			double cv$accumulator = 0.0;
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1) {
						int cv$sampleValue = st[i1][j1][k1];
						double[] var67 = m[0];
						double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var67.length))?Math.log(var67[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var68[(i1 - 1)][j1][k1] = cv$distributionAccumulator;
						logProbability$sample74[(i1 - 1)][j1][k1] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample74)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample74 = (fixedFlag$sample74 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1) {
						double cv$rvAccumulator = logProbability$sample74[(i1 - 1)][j1][k1];
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var68[(i1 - 1)][j1][k1] = cv$rvAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample74)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample19(int var17) {
		cv$var18$countGlobal[0] = 0.0;
		cv$var18$countGlobal[1] = 0.0;
		if((var17 == 0)) {
			cv$var18$countGlobal[st[0][0][0]] = (cv$var18$countGlobal[st[0][0][0]] + 1.0);
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						cv$var18$countGlobal[st[i1][j1][k1]] = (cv$var18$countGlobal[st[i1][j1][k1]] + 1.0);
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var18$countGlobal, m[var17]);
	}

	private final void sample29(int var26) {
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

	private final void sample56() {
		{
			double[] cv$temp$0$var49 = m[0];
			double cv$accumulatedProbabilities = ((0 < cv$temp$0$var49.length)?Math.log(cv$temp$0$var49[0]):Double.NEGATIVE_INFINITY);
			if((0 < samples))
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0][0][0], bias[0]) + cv$accumulatedProbabilities);
			cv$var51$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		double[] cv$temp$0$var49 = m[0];
		double cv$accumulatedProbabilities = ((1 < cv$temp$0$var49.length)?Math.log(cv$temp$0$var49[1]):Double.NEGATIVE_INFINITY);
		if((0 < samples))
			cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(flips[0][0][0], bias[1]) + cv$accumulatedProbabilities);
		cv$var51$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		double cv$logSum;
		double cv$lseMax = cv$var51$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var51$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var51$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var51$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var51$stateProbabilityGlobal[0] = 0.5;
			cv$var51$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var51$stateProbabilityGlobal[0] = Math.exp((cv$var51$stateProbabilityGlobal[0] - cv$logSum));
			cv$var51$stateProbabilityGlobal[1] = Math.exp((cv$var51$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var51$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var51$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, cv$var51$stateProbabilityGlobal);
	}

	private final void sample74(int i1, int j1, int k1) {
		{
			double[] cv$temp$0$var67 = m[0];
			cv$var69$stateProbabilityGlobal[0] = (DistributionSampling.logProbabilityBernoulli(flips[j1][k1][i1], bias[0]) + ((0 < cv$temp$0$var67.length)?Math.log(cv$temp$0$var67[0]):Double.NEGATIVE_INFINITY));
		}
		double[] cv$temp$0$var67 = m[0];
		cv$var69$stateProbabilityGlobal[1] = (DistributionSampling.logProbabilityBernoulli(flips[j1][k1][i1], bias[1]) + ((1 < cv$temp$0$var67.length)?Math.log(cv$temp$0$var67[1]):Double.NEGATIVE_INFINITY));
		double cv$logSum;
		double cv$lseMax = cv$var69$stateProbabilityGlobal[0];
		double cv$lseElementValue = cv$var69$stateProbabilityGlobal[1];
		if((cv$lseMax < cv$lseElementValue))
			cv$lseMax = cv$lseElementValue;
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else
			cv$logSum = (Math.log((Math.exp((cv$var69$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((cv$var69$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			cv$var69$stateProbabilityGlobal[0] = 0.5;
			cv$var69$stateProbabilityGlobal[1] = 0.5;
		} else {
			cv$var69$stateProbabilityGlobal[0] = Math.exp((cv$var69$stateProbabilityGlobal[0] - cv$logSum));
			cv$var69$stateProbabilityGlobal[1] = Math.exp((cv$var69$stateProbabilityGlobal[1] - cv$logSum));
		}
		for(int cv$indexName = 2; cv$indexName < cv$var69$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var69$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		st[i1][j1][k1] = DistributionSampling.sampleCategorical(RNG$, cv$var69$stateProbabilityGlobal);
	}

	@Override
	public final void allocateScratch() {
		cv$var18$countGlobal = new double[2];
		cv$var51$stateProbabilityGlobal = new double[2];
		cv$var69$stateProbabilityGlobal = new double[2];
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
			for(int i$var33 = 0; i$var33 < length$flipsMeasured.length; i$var33 += 1) {
				int[][] subarray$0 = new int[length$flipsMeasured.length][];
				st[i$var33] = subarray$0;
				for(int j = 0; j < length$flipsMeasured.length; j += 1)
					subarray$0[j] = new int[length$flipsMeasured.length];
			}
		}
		if(!setFlag$flips) {
			flips = new boolean[length$flipsMeasured.length][][];
			for(int i2 = 0; i2 < length$flipsMeasured.length; i2 += 1) {
				boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
				flips[i2] = subarray$0;
				for(int j2 = 0; j2 < length$flipsMeasured.length; j2 += 1)
					subarray$0[j2] = new boolean[length$flipsMeasured.length];
			}
		}
		logProbability$var68 = new double[(length$flipsMeasured.length - 1)][][];
		for(int i1 = 1; i1 < length$flipsMeasured.length; i1 += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$var68[(i1 - 1)] = subarray$0;
			for(int j1 = 0; j1 < length$flipsMeasured.length; j1 += 1)
				subarray$0[j1] = new double[length$flipsMeasured.length];
		}
		logProbability$sample74 = new double[(length$flipsMeasured.length - 1)][][];
		for(int i1 = 1; i1 < length$flipsMeasured.length; i1 += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$sample74[(i1 - 1)] = subarray$0;
			for(int j1 = 0; j1 < length$flipsMeasured.length; j1 += 1)
				subarray$0[j1] = new double[length$flipsMeasured.length];
		}
		logProbability$var101 = new double[length$flipsMeasured.length][][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$var101[l] = subarray$0;
			for(int p = 0; p < length$flipsMeasured.length; p += 1)
				subarray$0[p] = new double[length$flipsMeasured.length];
		}
		logProbability$sample109 = new double[length$flipsMeasured.length][][];
		for(int l = 0; l < length$flipsMeasured.length; l += 1) {
			double[][] subarray$0 = new double[length$flipsMeasured.length][];
			logProbability$sample109[l] = subarray$0;
			for(int p = 0; p < length$flipsMeasured.length; p += 1)
				subarray$0[p] = new double[length$flipsMeasured.length];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample29) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample56)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample74) {
			for(int i1 = 1; i1 < samples; i1 += 1) {
				int[][] var64 = st[i1];
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						var64[j1][k1] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
		if(!fixedFlag$sample109) {
			for(int l = 0; l < samples; l += 1) {
				boolean[][] var95 = flips[l];
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						var95[n][p] = DistributionSampling.sampleBernoulli(RNG$, bias[st[p][l][n]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample19) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample29) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample56)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample74) {
			for(int i1 = 1; i1 < samples; i1 += 1) {
				int[][] var64 = st[i1];
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						var64[j1][k1] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample29) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample56)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample74) {
			for(int i1 = 1; i1 < samples; i1 += 1) {
				int[][] var64 = st[i1];
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						var64[j1][k1] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample19) {
				sample19(0);
				sample19(1);
			}
			if(!fixedFlag$sample29) {
				sample29(0);
				sample29(1);
			}
			if(!fixedFlag$sample56)
				sample56();
			if(!fixedFlag$sample74) {
				for(int i1 = 1; i1 < samples; i1 += 1) {
					for(int j1 = 0; j1 < samples; j1 += 1) {
						for(int k1 = 0; k1 < samples; k1 += 1)
							sample74(i1, j1, k1);
					}
				}
			}
		} else {
			if(!fixedFlag$sample74) {
				for(int i1 = (samples - 1); i1 >= 1; i1 -= 1) {
					for(int j1 = (samples - 1); j1 >= 0; j1 -= 1) {
						for(int k1 = (samples - 1); k1 >= 0; k1 -= 1)
							sample74(i1, j1, k1);
					}
				}
			}
			if(!fixedFlag$sample56)
				sample56();
			if(!fixedFlag$sample29) {
				sample29(1);
				sample29(0);
			}
			if(!fixedFlag$sample19) {
				sample19(1);
				sample19(0);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		v[0] = 0.1;
		v[1] = 0.1;
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
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		logProbability$var50 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample56)
			logProbability$var51 = 0.0;
		for(int i1 = 1; i1 < samples; i1 += 1) {
			for(int j1 = 0; j1 < samples; j1 += 1) {
				for(int k1 = 0; k1 < samples; k1 += 1)
					logProbability$var68[(i1 - 1)][j1][k1] = 0.0;
			}
		}
		if(!fixedProbFlag$sample74) {
			for(int i1 = 1; i1 < samples; i1 += 1) {
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						logProbability$sample74[(i1 - 1)][j1][k1] = 0.0;
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1)
					logProbability$var101[l][p][n] = 0.0;
			}
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample109) {
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						logProbability$sample109[l][p][n] = 0.0;
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
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample56)
			logProbabilityValue$sample56();
		if(fixedFlag$sample74)
			logProbabilityValue$sample74();
		logProbabilityValue$sample109();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample29();
		logProbabilityValue$sample56();
		logProbabilityValue$sample74();
		logProbabilityValue$sample109();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample29();
		logProbabilityValue$sample56();
		logProbabilityValue$sample74();
		logProbabilityValue$sample109();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample19) {
			DistributionSampling.sampleDirichlet(RNG$, v, m[0]);
			DistributionSampling.sampleDirichlet(RNG$, v, m[1]);
		}
		if(!fixedFlag$sample29) {
			bias[0] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			bias[1] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample56)
			st[0][0][0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		if(!fixedFlag$sample74) {
			for(int i1 = 1; i1 < samples; i1 += 1) {
				int[][] var64 = st[i1];
				for(int j1 = 0; j1 < samples; j1 += 1) {
					for(int k1 = 0; k1 < samples; k1 += 1)
						var64[j1][k1] = DistributionSampling.sampleCategorical(RNG$, m[0]);
				}
			}
		}
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart4(boolean[][][] flipsMeasured) {\n        int states = 2;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        \n        \n        int[][][] st = new int[samples][][];\n        for(int i:[0..samples)) {\n            st[i] = new int[samples][];\n            for(int j:[0..samples))\n                st[i][j] = new int[samples];\n        }\n\n        st[0][0][0] = categorical(m[0]).sample();\n\n        for(int i1:[1..samples))\n            for(int j1:[0..samples))\n                for(int k1:[0..samples))\n                    st[i1][j1][k1] = categorical(m[0]).sample();\n            \n        boolean[][][] flips = new boolean[samples][][];\n        for(int i2:[0..samples)) {\n            flips[i2] = new boolean[samples][];\n            for(int j2:[0..samples))\n                flips[i2][j2] = new boolean[samples];\n        }\n            \n        for(int l:[0..samples))\n            for(int p:[0..samples))\n                for(int n:[0..samples))\n                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}