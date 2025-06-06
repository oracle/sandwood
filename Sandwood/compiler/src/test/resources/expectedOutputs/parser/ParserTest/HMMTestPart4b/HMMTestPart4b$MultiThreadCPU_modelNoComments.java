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
	private boolean fixedFlag$sample107 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedProbFlag$sample107 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean fixedProbFlag$sample72 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[][][] logProbability$sample107;
	private double[][][] logProbability$sample72;
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
	private int states;
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
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample107 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample107() {
		return fixedFlag$sample107;
	}

	@Override
	public final void set$fixedFlag$sample107(boolean cv$value) {
		fixedFlag$sample107 = cv$value;
		fixedProbFlag$sample107 = (fixedFlag$sample107 && fixedProbFlag$sample107);
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (fixedFlag$sample19 && fixedProbFlag$sample19);
		fixedProbFlag$sample54 = (fixedFlag$sample19 && fixedProbFlag$sample54);
		fixedProbFlag$sample72 = (fixedFlag$sample19 && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		fixedProbFlag$sample107 = (fixedFlag$sample29 && fixedProbFlag$sample107);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
		fixedProbFlag$sample107 = (fixedFlag$sample54 && fixedProbFlag$sample107);
	}

	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		fixedFlag$sample72 = cv$value;
		fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedProbFlag$sample72);
		fixedProbFlag$sample107 = (fixedFlag$sample72 && fixedProbFlag$sample107);
	}

	@Override
	public final boolean[][][] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[][][] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample107 = false;
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
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample72 = false;
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
		fixedProbFlag$sample54 = false;
		fixedProbFlag$sample72 = false;
		fixedProbFlag$sample107 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample107() {
		if(!fixedProbFlag$sample107) {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = flips[l][n][p];
							{
								{
									double var98 = bias[st[p][l][n]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var98));
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
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var99[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample107[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample107 = (((fixedFlag$sample107 && fixedFlag$sample29) && fixedFlag$sample54) && fixedFlag$sample72);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample107[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var99[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$rvAccumulator;
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
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var17 = 0; var17 < states; var17 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var17];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v));
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
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var13 = cv$sampleAccumulator;
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var18;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var13 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < states; var26 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var26];
					{
						{
							double var20 = 1.0;
							double var21 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var20, var21));
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
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var27;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0][0][0];
				{
					{
						double[] var47 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var47.length))?Math.log(var47[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$var49 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var49;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample54)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							int cv$sampleValue = st[i$var55][j$var58][k];
							{
								{
									double[] var65 = m[0];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var65.length))?Math.log(var65[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var66[((i$var55 - 1) / 1)][((j$var58 - 0) / 1)][((k - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample72[((i$var55 - 1) / 1)][((j$var58 - 0) / 1)][((k - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample72)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample72[((i$var55 - 1) / 1)][((j$var58 - 0) / 1)][((k - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var66[((i$var55 - 1) / 1)][((j$var58 - 0) / 1)][((k - 0) / 1)] = cv$rvAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample72)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample19(int var17, int threadID$cv$var17, Rng RNG$) {
		double[] cv$targetLocal = m[var17];
		double[] cv$countLocal = cv$var18$countGlobal[threadID$cv$var17];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var17 == 0)) {
						{
							{
								{
									{
										{
											cv$countLocal[st[0][0][0]] = (cv$countLocal[st[0][0][0]] + 1.0);
										}
									}
								}
							}
						}
					}
				}
			}
			{
				{
					if((var17 == 0)) {
						for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
							for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
								for(int k = 0; k < samples; k += 1)
									cv$countLocal[st[i$var55][j$var58][k]] = (cv$countLocal[st[i$var55][j$var58][k]] + 1.0);
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample29(int var26, int threadID$cv$var26, Rng RNG$) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int l = 0; l < samples; l += 1) {
						for(int p = 0; p < samples; p += 1) {
							for(int n = 0; n < samples; n += 1) {
								if((var26 == st[p][l][n])) {
									{
										{
											{
												{
													{
														cv$count = (cv$count + 1);
														if(flips[l][n][p])
															cv$sum = (cv$sum + 1);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var27 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var26] = var27;
	}

	private final void sample54() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var49$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var49 = cv$currentValue;
			int[][] var42 = st[0];
			int[] var44 = var42[0];
			var44[0] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var47;
				{
					double[] var47 = m[0];
					cv$temp$0$var47 = var47;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var47.length))?Math.log(cv$temp$0$var47[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var97$1_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((0 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((0 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((0 == n)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$1$var98;
																	{
																		double var98 = bias[traceTempVariable$var97$1_1];
																		cv$temp$1$var98 = var98;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
													if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
														else
															cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var49 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[][] var42 = st[0];
		int[] var44 = var42[0];
		var44[0] = var49;
	}

	private final void sample72(int i$var55, int j$var58, int k, int threadID$cv$k, Rng RNG$) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var67$stateProbabilityGlobal[threadID$cv$k];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var67 = cv$currentValue;
			int[][] var62 = st[i$var55];
			int[] var63 = var62[j$var58];
			var63[k] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var65;
				{
					double[] var65 = m[0];
					cv$temp$0$var65 = var65;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var65.length))?Math.log(cv$temp$0$var65[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var97$1_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((i$var55 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((j$var58 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((k == n)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$1$var98;
																	{
																		double var98 = bias[traceTempVariable$var97$1_1];
																		cv$temp$1$var98 = var98;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$1$var98)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
													if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
														else
															cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var67 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		int[][] var62 = st[i$var55];
		int[] var63 = var62[j$var58];
		var63[k] = var67;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var17 = 0; var17 < 2; var17 += 1)
				cv$max = Math.max(cv$max, 2);
			{
				int cv$threadCount = threadCount();
				cv$var18$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var18$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var19$max = 2;
			cv$var49$stateProbabilityGlobal = new double[cv$var19$max];
		}
		{
			int cv$var19$max = 2;
			{
				int cv$threadCount = threadCount();
				cv$var67$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var67$stateProbabilityGlobal[cv$index] = new double[cv$var19$max];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[2];
		}
		if(!setFlag$m) {
			{
				m = new double[2][];
				for(int var17 = 0; var17 < 2; var17 += 1)
					m[var17] = new double[2];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[2];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$flipsMeasured.length][][];
				for(int var33 = 0; var33 < length$flipsMeasured.length; var33 += 1) {
					int[][] subarray$0 = new int[length$flipsMeasured.length][];
					st[var33] = subarray$0;
					for(int var37 = 0; var37 < length$flipsMeasured.length; var37 += 1)
						subarray$0[var37] = new int[length$flipsMeasured.length];
				}
			}
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured.length][][];
				for(int i$var74 = 0; i$var74 < length$flipsMeasured.length; i$var74 += 1) {
					boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
					flips[i$var74] = subarray$0;
					for(int j$var79 = 0; j$var79 < length$flipsMeasured.length; j$var79 += 1)
						subarray$0[j$var79] = new boolean[length$flipsMeasured.length];
				}
			}
		}
		{
			logProbability$var66 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)][][];
			for(int i$var55 = 1; i$var55 < length$flipsMeasured.length; i$var55 += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$var66[((i$var55 - 1) / 1)] = subarray$0;
				for(int j$var58 = 0; j$var58 < length$flipsMeasured.length; j$var58 += 1)
					subarray$0[((j$var58 - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample72 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)][][];
			for(int i$var55 = 1; i$var55 < length$flipsMeasured.length; i$var55 += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$sample72[((i$var55 - 1) / 1)] = subarray$0;
				for(int j$var58 = 0; j$var58 < length$flipsMeasured.length; j$var58 += 1)
					subarray$0[((j$var58 - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var99 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$var99[((l - 0) / 1)] = subarray$0;
				for(int p = 0; p < length$flipsMeasured.length; p += 1)
					subarray$0[((p - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample107 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$sample107[((l - 0) / 1)] = subarray$0;
				for(int p = 0; p < length$flipsMeasured.length; p += 1)
					subarray$0[((p - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample19)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		int[][] var42 = st[0];
		int[] var44 = var42[0];
		if(!fixedFlag$sample54)
			var44[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
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
												for(int k = forStart$k; k < forEnd$k; k += 1) {
														int[] var63 = var62[j$var58];
														if(!fixedFlag$sample72)
															var63[k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
													}
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
						boolean[][] var93 = flips[l];
						parallelFor(RNG$1, 0, samples, 1,
							(int forStart$index$p, int forEnd$index$p, int threadID$index$p, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$p = forStart$index$p; index$p < forEnd$index$p; index$p += 1) {
										int p = index$p;
										parallelFor(RNG$2, 0, samples, 1,
											(int forStart$n, int forEnd$n, int threadID$n, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int n = forStart$n; n < forEnd$n; n += 1) {
														boolean[] var94 = var93[n];
														if(!fixedFlag$sample107)
															var94[p] = DistributionSampling.sampleBernoulli(RNG$3, bias[st[p][l][n]]);
													}
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
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample19)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		int[][] var42 = st[0];
		int[] var44 = var42[0];
		if(!fixedFlag$sample54)
			var44[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
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
												for(int k = forStart$k; k < forEnd$k; k += 1) {
														int[] var63 = var62[j$var58];
														if(!fixedFlag$sample72)
															var63[k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
													}
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
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample19)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		int[][] var42 = st[0];
		int[] var44 = var42[0];
		if(!fixedFlag$sample54)
			var44[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
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
												for(int k = forStart$k; k < forEnd$k; k += 1) {
														int[] var63 = var62[j$var58];
														if(!fixedFlag$sample72)
															var63[k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
													}
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
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
							if(!fixedFlag$sample19)
								sample19(var17, threadID$var17, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
							if(!fixedFlag$sample29)
								sample29(var26, threadID$var26, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample54)
				sample54();
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
													for(int k = forStart$k; k < forEnd$k; k += 1) {
															if(!fixedFlag$sample72)
																sample72(i$var55, j$var58, k, threadID$k, RNG$3);
														}
												}
											);
										}
								}
							);
						}
				}
			);
		} else {
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
													for(int k = forStart$k; k < forEnd$k; k += 1) {
															if(!fixedFlag$sample72)
																sample72(i$var55, j$var58, k, threadID$k, RNG$3);
														}
												}
											);
										}
								}
							);
						}
				}
			);
			if(!fixedFlag$sample54)
				sample54();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
							if(!fixedFlag$sample29)
								sample29(var26, threadID$var26, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
							if(!fixedFlag$sample19)
								sample19(var17, threadID$var17, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 2;
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
		if(!fixedProbFlag$sample29)
			logProbability$var27 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample54)
			logProbability$var49 = 0.0;
		for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
			for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
				for(int k = 0; k < samples; k += 1)
					logProbability$var66[((i$var55 - 1) / 1)][((j$var58 - 0) / 1)][((k - 0) / 1)] = 0.0;
			}
		}
		if(!fixedProbFlag$sample72) {
			for(int i$var55 = 1; i$var55 < samples; i$var55 += 1) {
				for(int j$var58 = 0; j$var58 < samples; j$var58 += 1) {
					for(int k = 0; k < samples; k += 1)
						logProbability$sample72[((i$var55 - 1) / 1)][((j$var58 - 0) / 1)][((k - 0) / 1)] = 0.0;
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1)
					logProbability$var99[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = 0.0;
			}
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample107) {
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						logProbability$sample107[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(fixedFlag$sample72)
			logProbabilityValue$sample72();
		logProbabilityValue$sample107();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample29();
		logProbabilityValue$sample54();
		logProbabilityValue$sample72();
		logProbabilityValue$sample107();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample29();
		logProbabilityValue$sample54();
		logProbabilityValue$sample72();
		logProbabilityValue$sample107();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1) {
						double[] var18 = m[var17];
						if(!fixedFlag$sample19)
							DistributionSampling.sampleDirichlet(RNG$1, v, var18);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1) {
						if(!fixedFlag$sample29)
							bias[var26] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		int[][] var42 = st[0];
		int[] var44 = var42[0];
		if(!fixedFlag$sample54)
			var44[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
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
												for(int k = forStart$k; k < forEnd$k; k += 1) {
														int[] var63 = var62[j$var58];
														if(!fixedFlag$sample72)
															var63[k] = DistributionSampling.sampleCategorical(RNG$3, m[0]);
													}
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
		boolean[][][] cv$source1 = flipsMeasured;
		boolean[][][] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = cv$source1[cv$index1];
			boolean[][] cv$target2 = cv$target1[cv$index1];
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