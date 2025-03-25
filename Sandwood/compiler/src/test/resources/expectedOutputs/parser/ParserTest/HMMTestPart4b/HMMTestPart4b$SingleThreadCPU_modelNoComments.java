package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart4b$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart4b$CoreInterface {
	private double[] bias;
	private double[] cv$var119$stateProbabilityGlobal;
	private double[] cv$var28$countGlobal;
	private double[] cv$var79$stateProbabilityGlobal;
	private boolean fixedFlag$sample122 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample82 = false;
	private boolean fixedProbFlag$sample122 = false;
	private boolean fixedProbFlag$sample189 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample82 = false;
	private boolean[][][] flips;
	private boolean[][][] flipsMeasured;
	private int[][] length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[][][] logProbability$sample122;
	private double[][][] logProbability$sample189;
	private double logProbability$st;
	private double[][][] logProbability$var118;
	private double logProbability$var16;
	private double[][][] logProbability$var183;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var78;
	private double logProbability$var79;
	private double[][] m;
	private int samples;
	private int[][][] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart4b$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample189 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample122() {
		return fixedFlag$sample122;
	}

	@Override
	public final void set$fixedFlag$sample122(boolean cv$value) {
		fixedFlag$sample122 = cv$value;
		fixedProbFlag$sample122 = (fixedFlag$sample122 && fixedProbFlag$sample122);
		fixedProbFlag$sample189 = (fixedFlag$sample122 && fixedProbFlag$sample189);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample82 = (fixedFlag$sample28 && fixedProbFlag$sample82);
		fixedProbFlag$sample122 = (fixedFlag$sample28 && fixedProbFlag$sample122);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample189 = (fixedFlag$sample45 && fixedProbFlag$sample189);
	}

	@Override
	public final boolean get$fixedFlag$sample82() {
		return fixedFlag$sample82;
	}

	@Override
	public final void set$fixedFlag$sample82(boolean cv$value) {
		fixedFlag$sample82 = cv$value;
		fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedProbFlag$sample82);
		fixedProbFlag$sample189 = (fixedFlag$sample82 && fixedProbFlag$sample189);
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
		fixedProbFlag$sample82 = false;
		fixedProbFlag$sample122 = false;
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
		fixedProbFlag$sample82 = false;
		fixedProbFlag$sample122 = false;
		fixedProbFlag$sample189 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityValue$sample122() {
		if(!fixedProbFlag$sample122) {
			double cv$accumulator = 0.0;
			for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							int cv$sampleValue = st[i$var95][j$var104][k];
							{
								{
									double[] var117 = m[0];
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var117[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						logProbability$var118[((i$var95 - 1) / 1)][((j$var104 - 0) / 1)][((k - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample122[((i$var95 - 1) / 1)][((j$var104 - 0) / 1)][((k - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample122)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample122 = (fixedFlag$sample122 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
					for(int k = 0; k < samples; k += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample122[((i$var95 - 1) / 1)][((j$var104 - 0) / 1)][((k - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var118[((i$var95 - 1) / 1)][((j$var104 - 0) / 1)][((k - 0) / 1)] = cv$rvAccumulator;
					}
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample122)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample189() {
		if(!fixedProbFlag$sample189) {
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
									double var182 = bias[st[p][l][n]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var182));
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
						logProbability$var183[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample189[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample189 = ((fixedFlag$sample45 && fixedFlag$sample82) && fixedFlag$sample122);
		} else {
			double cv$accumulator = 0.0;
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample189[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var183[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$rvAccumulator;
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
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var27];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
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
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var43];
					{
						{
							double var30 = 1.0;
							double var31 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample82() {
		if(!fixedProbFlag$sample82) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0][0][0];
				{
					{
						double[] var77 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var77[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var78 = cv$sampleAccumulator;
			logProbability$var79 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample82 = (fixedFlag$sample82 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var79;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var78 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample82)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample122(int i$var95, int j$var104, int k) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var119$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var119 = cv$currentValue;
			{
				{
					int[][] var114 = st[i$var95];
					int[] var115 = var114[j$var104];
					var115[k] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var117;
				{
					double[] var117 = m[0];
					cv$temp$0$var117 = var117;
				}
				int cv$temp$1$$var317;
				{
					int $var317 = states;
					cv$temp$1$$var317 = $var317;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var317))?Math.log(cv$temp$0$var117[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var181$2_1 = cv$currentValue;
						for(int p = 0; p < samples; p += 1) {
							if((i$var95 == p)) {
								for(int l = 0; l < samples; l += 1) {
									if((j$var104 == l)) {
										for(int n = 0; n < samples; n += 1) {
											if((k == n)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$2$var182;
																	{
																		double var182 = bias[traceTempVariable$var181$2_1];
																		cv$temp$2$var182 = var182;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)));
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var119 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		{
			{
				int[][] var114 = st[i$var95];
				int[] var115 = var114[j$var104];
				var115[k] = var119;
			}
		}
	}

	private final void sample28(int var27) {
		double[] cv$targetLocal = m[var27];
		double[] cv$countLocal = cv$var28$countGlobal;
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var27 == 0)) {
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
					if((var27 == 0)) {
						for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
							for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
								for(int k = 0; k < samples; k += 1)
									cv$countLocal[st[i$var95][j$var104][k]] = (cv$countLocal[st[i$var95][j$var104][k]] + 1.0);
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
	}

	private final void sample45(int var43) {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					for(int l = 0; l < samples; l += 1) {
						for(int p = 0; p < samples; p += 1) {
							for(int n = 0; n < samples; n += 1) {
								if((var43 == st[p][l][n])) {
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
		double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		{
			{
				bias[var43] = var44;
			}
		}
	}

	private final void sample82() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var79$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int var79 = cv$currentValue;
			{
				{
					int[][] var72 = st[0];
					int[] var74 = var72[0];
					var74[0] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var77;
				{
					double[] var77 = m[0];
					cv$temp$0$var77 = var77;
				}
				int cv$temp$1$$var293;
				{
					int $var293 = states;
					cv$temp$1$$var293 = $var293;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var293))?Math.log(cv$temp$0$var77[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var181$2_1 = cv$currentValue;
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
																	double cv$temp$2$var182;
																	{
																		double var182 = bias[traceTempVariable$var181$2_1];
																		cv$temp$2$var182 = var182;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[l][n][p], cv$temp$2$var182)));
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
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var79 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		{
			{
				int[][] var72 = st[0];
				int[] var74 = var72[0];
				var74[0] = var79;
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var28$countGlobal = new double[2];
		}
		{
			int cv$var29$max = 2;
			cv$var79$stateProbabilityGlobal = new double[cv$var29$max];
		}
		{
			int cv$var29$max = 2;
			cv$var119$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[2];
		}
		if(!fixedFlag$sample28) {
			{
				m = new double[2][];
				for(int var27 = 0; var27 < 2; var27 += 1)
					m[var27] = new double[2];
			}
		}
		if(!fixedFlag$sample45) {
			{
				bias = new double[2];
			}
		}
		if((!fixedFlag$sample82 || !fixedFlag$sample122)) {
			{
				st = new int[length$flipsMeasured.length][][];
				for(int var57 = 0; var57 < length$flipsMeasured.length; var57 += 1) {
					int[][] subarray$0 = new int[length$flipsMeasured.length][];
					st[var57] = subarray$0;
					for(int var67 = 0; var67 < length$flipsMeasured.length; var67 += 1)
						subarray$0[var67] = new int[length$flipsMeasured.length];
				}
			}
		}
		{
			flips = new boolean[length$flipsMeasured.length][][];
			for(int i$var133 = 0; i$var133 < length$flipsMeasured.length; i$var133 += 1) {
				boolean[][] subarray$0 = new boolean[length$flipsMeasured.length][];
				flips[i$var133] = subarray$0;
				for(int j$var144 = 0; j$var144 < length$flipsMeasured.length; j$var144 += 1)
					subarray$0[j$var144] = new boolean[length$flipsMeasured.length];
			}
		}
		{
			logProbability$var118 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)][][];
			for(int i$var95 = 1; i$var95 < length$flipsMeasured.length; i$var95 += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$var118[((i$var95 - 1) / 1)] = subarray$0;
				for(int j$var104 = 0; j$var104 < length$flipsMeasured.length; j$var104 += 1)
					subarray$0[((j$var104 - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample122 = new double[((((length$flipsMeasured.length - 1) - 1) / 1) + 1)][][];
			for(int i$var95 = 1; i$var95 < length$flipsMeasured.length; i$var95 += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$sample122[((i$var95 - 1) / 1)] = subarray$0;
				for(int j$var104 = 0; j$var104 < length$flipsMeasured.length; j$var104 += 1)
					subarray$0[((j$var104 - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var183 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$var183[((l - 0) / 1)] = subarray$0;
				for(int p = 0; p < length$flipsMeasured.length; p += 1)
					subarray$0[((p - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample189 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int l = 0; l < length$flipsMeasured.length; l += 1) {
				double[][] subarray$0 = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)][];
				logProbability$sample189[((l - 0) / 1)] = subarray$0;
				for(int p = 0; p < length$flipsMeasured.length; p += 1)
					subarray$0[((p - 0) / 1)] = new double[((((length$flipsMeasured.length - 1) - 0) / 1) + 1)];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var72 = st[0];
		int[] var74 = var72[0];
		if(!fixedFlag$sample82)
			var74[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
			int[][] var114 = st[i$var95];
			for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var115 = var114[j$var104];
					if(!fixedFlag$sample122)
						var115[k] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			boolean[][] var177 = flips[l];
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1) {
					boolean[] var178 = var177[n];
					var178[p] = DistributionSampling.sampleBernoulli(RNG$, bias[st[p][l][n]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var72 = st[0];
		int[] var74 = var72[0];
		if(!fixedFlag$sample82)
			var74[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
			int[][] var114 = st[i$var95];
			for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var115 = var114[j$var104];
					if(!fixedFlag$sample122)
						var115[k] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var72 = st[0];
		int[] var74 = var72[0];
		if(!fixedFlag$sample82)
			var74[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
			int[][] var114 = st[i$var95];
			for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var115 = var114[j$var104];
					if(!fixedFlag$sample122)
						var115[k] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if(!fixedFlag$sample28)
					sample28(var27);
			}
			for(int var43 = 0; var43 < states; var43 += 1) {
				if(!fixedFlag$sample45)
					sample45(var43);
			}
			if(!fixedFlag$sample82)
				sample82();
			for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
					for(int k = 0; k < samples; k += 1) {
						if(!fixedFlag$sample122)
							sample122(i$var95, j$var104, k);
					}
				}
			}
		} else {
			for(int i$var95 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var95 >= ((1 - 1) + 1); i$var95 -= 1) {
				for(int j$var104 = (samples - ((((samples - 1) - 0) % 1) + 1)); j$var104 >= ((0 - 1) + 1); j$var104 -= 1) {
					for(int k = (samples - ((((samples - 1) - 0) % 1) + 1)); k >= ((0 - 1) + 1); k -= 1) {
						if(!fixedFlag$sample122)
							sample122(i$var95, j$var104, k);
					}
				}
			}
			if(!fixedFlag$sample82)
				sample82();
			for(int var43 = (states - ((((states - 1) - 0) % 1) + 1)); var43 >= ((0 - 1) + 1); var43 -= 1) {
				if(!fixedFlag$sample45)
					sample45(var43);
			}
			for(int var27 = (states - ((((states - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!fixedFlag$sample28)
					sample28(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 2;
		for(int i$var13 = 0; i$var13 < 2; i$var13 += 1)
			v[i$var13] = 0.1;
		samples = length$flipsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var78 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample82)
			logProbability$var79 = 0.0;
		for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
			for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
				for(int k = 0; k < samples; k += 1)
					logProbability$var118[((i$var95 - 1) / 1)][((j$var104 - 0) / 1)][((k - 0) / 1)] = 0.0;
			}
		}
		if(!fixedProbFlag$sample122) {
			for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
				for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
					for(int k = 0; k < samples; k += 1)
						logProbability$sample122[((i$var95 - 1) / 1)][((j$var104 - 0) / 1)][((k - 0) / 1)] = 0.0;
				}
			}
		}
		for(int l = 0; l < samples; l += 1) {
			for(int p = 0; p < samples; p += 1) {
				for(int n = 0; n < samples; n += 1)
					logProbability$var183[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = 0.0;
			}
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample189) {
			for(int l = 0; l < samples; l += 1) {
				for(int p = 0; p < samples; p += 1) {
					for(int n = 0; n < samples; n += 1)
						logProbability$sample189[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample82)
			logProbabilityValue$sample82();
		if(fixedFlag$sample122)
			logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample82();
		logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample82();
		logProbabilityValue$sample122();
		logProbabilityValue$sample189();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		int[][] var72 = st[0];
		int[] var74 = var72[0];
		if(!fixedFlag$sample82)
			var74[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var95 = 1; i$var95 < samples; i$var95 += 1) {
			int[][] var114 = st[i$var95];
			for(int j$var104 = 0; j$var104 < samples; j$var104 += 1) {
				for(int k = 0; k < samples; k += 1) {
					int[] var115 = var114[j$var104];
					if(!fixedFlag$sample122)
						var115[k] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
				}
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
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
		     + "model HMMTestPart4b(boolean[][][] flipsMeasured) {\n"
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
		     + "        int[][][] st = new int[samples][samples][samples];\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            for(int j:[0..samples))\n"
		     + "                for(int k:[0..samples))\n"
		     + "                    st[i][j][k] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            flips[i] = new boolean[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                flips[i][j] = new boolean[samples];\n"
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