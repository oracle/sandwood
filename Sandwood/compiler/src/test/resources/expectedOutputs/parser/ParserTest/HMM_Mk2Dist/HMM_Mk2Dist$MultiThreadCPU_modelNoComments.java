package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	private double[][] bias;
	private boolean[][] constrainedFlag$sample126;
	private boolean[] constrainedFlag$sample42;
	private boolean[] constrainedFlag$sample57;
	private boolean constrainedFlag$sample78 = true;
	private boolean constrainedFlag$sample80 = true;
	private boolean[] constrainedFlag$sample95;
	private double[][] cv$distributionAccumulator$var122;
	private double[] cv$distributionAccumulator$var91;
	private double[][] cv$var123$stateProbabilityGlobal;
	private double[][] cv$var42$countGlobal;
	private double[][] cv$var56$countGlobal;
	private double[] cv$var75$countGlobal;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[][] cv$var92$stateProbabilityGlobal;
	private double[][][] distribution$sample126;
	private double[][] distribution$sample95;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample126 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample126 = false;
	private boolean fixedProbFlag$sample159 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean fixedProbFlag$sample95 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[][] logProbability$sample126;
	private double[][] logProbability$sample159;
	private double[] logProbability$sample95;
	private double logProbability$st;
	private double logProbability$var42;
	private double logProbability$var56;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2Dist$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[][] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample159 = false;
	}

	@Override
	public final double[][][] get$distribution$sample126() {
		return distribution$sample126;
	}

	@Override
	public final void set$distribution$sample126(double[][][] cv$value) {
		distribution$sample126 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample95() {
		return distribution$sample95;
	}

	@Override
	public final void set$distribution$sample95(double[][] cv$value) {
		distribution$sample95 = cv$value;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	@Override
	public final void set$eventsMeasured(int[][] cv$value) {
		eventsMeasured = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	@Override
	public final void set$fixedFlag$sample126(boolean cv$value) {
		fixedFlag$sample126 = cv$value;
		fixedProbFlag$sample126 = (fixedFlag$sample126 && fixedProbFlag$sample126);
		fixedProbFlag$sample159 = (fixedFlag$sample126 && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		fixedProbFlag$sample95 = (fixedFlag$sample42 && fixedProbFlag$sample95);
		fixedProbFlag$sample126 = (fixedFlag$sample42 && fixedProbFlag$sample126);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		fixedProbFlag$sample159 = (fixedFlag$sample57 && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		fixedFlag$sample78 = cv$value;
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
		fixedProbFlag$sample80 = (fixedFlag$sample78 && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		fixedFlag$sample80 = cv$value;
		fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedProbFlag$sample80);
		fixedProbFlag$sample95 = (fixedFlag$sample80 && fixedProbFlag$sample95);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		fixedFlag$sample95 = cv$value;
		fixedProbFlag$sample95 = (fixedFlag$sample95 && fixedProbFlag$sample95);
		fixedProbFlag$sample126 = (fixedFlag$sample95 && fixedProbFlag$sample126);
		fixedProbFlag$sample159 = (fixedFlag$sample95 && fixedProbFlag$sample159);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
		fixedProbFlag$sample80 = false;
		fixedProbFlag$sample95 = false;
	}

	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	@Override
	public final void set$length$eventsMeasured(int[] cv$value) {
		length$eventsMeasured = cv$value;
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
	public final double get$logProbability$events() {
		return logProbability$events;
	}

	@Override
	public final double get$logProbability$initialState() {
		return logProbability$initialState;
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
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample126 = false;
	}

	@Override
	public final int get$noEvents() {
		return noEvents;
	}

	@Override
	public final void set$noEvents(int cv$value) {
		noEvents = cv$value;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value) {
		noStates = cv$value;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value) {
		st = cv$value;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample126 = false;
		fixedProbFlag$sample159 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	@Override
	public final double[] get$v2() {
		return v2;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		fixedProbFlag$sample78 = false;
		fixedProbFlag$sample80 = false;
	}

	private final void logProbabilityDistribution$sample126() {
		if(!fixedProbFlag$sample126) {
			if(fixedFlag$sample126) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$j$1 = j$var115;
						int index$i$2 = i$var104;
						{
							{
								int cv$sampleValue = st[i$var104][j$var115];
								if(fixedFlag$sample95) {
									{
										for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													{
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
													}
												}
											}
										}
									}
								} else {
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if(true) {
											for(int index$sample95$6 = 0; index$sample95$6 < noStates; index$sample95$6 += 1) {
												int distributionTempVariable$var92$8 = index$sample95$6;
												double cv$probabilitySample95Value7 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$6]);
												{
													int traceTempVariable$var120$9_1 = distributionTempVariable$var92$8;
													if((i$var87 == i$var104)) {
														if((0 == (j$var115 - 1))) {
															{
																for(int var41 = 0; var41 < noStates; var41 += 1) {
																	if((var41 == traceTempVariable$var120$9_1)) {
																		{
																			double[] var121 = m[traceTempVariable$var120$9_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample95Value7) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value7);
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
								{
									if((index$i$2 == i$var104)) {
										if((index$j$1 == (j$var115 - 1))) {
											{
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														{
															double[] var121 = m[st[i$var104][(j$var115 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											}
										}
									}
								}
								if(fixedFlag$sample126) {
									{
										for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
											for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
												if((index$i$13_1 == i$var104)) {
													if((index$j$13_2 == (j$var115 - 1))) {
														{
															for(int var41 = 0; var41 < noStates; var41 += 1) {
																if((var41 == st[i$var104][(j$var115 - 1)])) {
																	{
																		double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																		double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$i$14 = 0; index$i$14 < samples; index$i$14 += 1) {
										for(int index$j$15 = 1; index$j$15 < length$eventsMeasured[index$i$14]; index$j$15 += 1) {
											if(!((index$j$15 == index$j$1) && (index$i$14 == index$i$2))) {
												for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1) {
													int distributionTempVariable$var123$18 = index$sample126$16;
													double cv$probabilitySample126Value17 = (1.0 * distribution$sample126[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample126$16]);
													{
														int traceTempVariable$var120$19_1 = distributionTempVariable$var123$18;
														if((index$i$14 == i$var104)) {
															if((index$j$15 == (j$var115 - 1))) {
																{
																	for(int var41 = 0; var41 < noStates; var41 += 1) {
																		if((var41 == traceTempVariable$var120$19_1)) {
																			{
																				double[] var121 = m[traceTempVariable$var120$19_1];
																				double cv$weightedProbability = (Math.log(cv$probabilitySample126Value17) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																				if((cv$weightedProbability < cv$distributionAccumulator))
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																				else {
																					if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																						cv$distributionAccumulator = cv$weightedProbability;
																					else
																						cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																				}
																				cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value17);
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
						logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample126)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample126)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			if(fixedFlag$sample126)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample159() {
		if(!fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = (events[i$var136][j$var149] - 1);
							if(fixedFlag$sample95) {
								{
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if((i$var87 == i$var136)) {
											if((0 == j$var149)) {
												{
													for(int var55 = 0; var55 < noStates; var55 += 1) {
														if((var55 == st[i$var136][j$var149])) {
															{
																double[] var153 = bias[st[i$var136][j$var149]];
																double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
												}
											}
										}
									}
								}
							} else {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										for(int index$sample95$4 = 0; index$sample95$4 < noStates; index$sample95$4 += 1) {
											int distributionTempVariable$var92$6 = index$sample95$4;
											double cv$probabilitySample95Value5 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$4]);
											{
												int traceTempVariable$var152$7_1 = distributionTempVariable$var92$6;
												if((i$var87 == i$var136)) {
													if((0 == j$var149)) {
														{
															for(int var55 = 0; var55 < noStates; var55 += 1) {
																if((var55 == traceTempVariable$var152$7_1)) {
																	{
																		double[] var153 = bias[traceTempVariable$var152$7_1];
																		double cv$weightedProbability = (Math.log(cv$probabilitySample95Value5) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value5);
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
							if(fixedFlag$sample126) {
								{
									for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
										for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
											if((i$var104 == i$var136)) {
												if((j$var115 == j$var149)) {
													{
														for(int var55 = 0; var55 < noStates; var55 += 1) {
															if((var55 == st[i$var136][j$var149])) {
																{
																	double[] var153 = bias[st[i$var136][j$var149]];
																	double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
													}
												}
											}
										}
									}
								}
							} else {
								for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
									for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
										if(true) {
											for(int index$sample126$13 = 0; index$sample126$13 < noStates; index$sample126$13 += 1) {
												int distributionTempVariable$var123$15 = index$sample126$13;
												double cv$probabilitySample126Value14 = (1.0 * distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][index$sample126$13]);
												{
													int traceTempVariable$var152$16_1 = distributionTempVariable$var123$15;
													if((i$var104 == i$var136)) {
														if((j$var115 == j$var149)) {
															{
																for(int var55 = 0; var55 < noStates; var55 += 1) {
																	if((var55 == traceTempVariable$var152$16_1)) {
																		{
																			double[] var153 = bias[traceTempVariable$var152$16_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample126Value14) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value14);
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
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample159 = ((fixedFlag$sample57 && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample95() {
		if(!fixedProbFlag$sample95) {
			if(fixedFlag$sample95) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var87;
					{
						{
							int cv$sampleValue = st[i$var87][0];
							{
								{
									double[] var90 = m[initialState];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample95)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample95)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(fixedFlag$sample95)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!fixedProbFlag$sample126) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$j$1 = j$var115;
					int index$i$2 = i$var104;
					{
						{
							int cv$sampleValue = st[i$var104][j$var115];
							{
								{
									double[] var121 = m[st[i$var104][(j$var115 - 1)]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample159() {
		if(!fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = (events[i$var136][j$var149] - 1);
							{
								{
									double[] var153 = bias[st[i$var136][j$var149]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample159 = ((fixedFlag$sample57 && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < noStates; var41 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var41];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < noStates; var41 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = bias[var55];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2, noEvents));
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
			logProbability$var56 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < noStates; var55 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = weights;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$weights = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = fixedFlag$sample78;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = initialState;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= weights[cv$sampleValue])) && (weights[cv$sampleValue] <= 1.0))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$initialState = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedFlag$sample78);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var87;
				{
					{
						int cv$sampleValue = st[i$var87][0];
						{
							{
								double[] var90 = m[initialState];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		int index$j$1 = j$var115;
		int index$i$2 = i$var104;
		if(true) {
			constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = false;
			int cv$numStates = 0;
			if(fixedFlag$sample95) {
				{
					for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
						if((i$var87 == i$var104)) {
							if((0 == (j$var115 - 1))) {
								{
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)]))
											cv$numStates = Math.max(cv$numStates, noStates);
									}
								}
							}
						}
					}
				}
			} else {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					if(true) {
						for(int index$sample95$5 = 0; index$sample95$5 < noStates; index$sample95$5 += 1) {
							int distributionTempVariable$var92$7 = index$sample95$5;
							double cv$probabilitySample95Value6 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$5]);
							{
								int traceTempVariable$var120$8_1 = distributionTempVariable$var92$7;
								if((i$var87 == i$var104)) {
									if((0 == (j$var115 - 1))) {
										{
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == traceTempVariable$var120$8_1))
													cv$numStates = Math.max(cv$numStates, noStates);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			{
				if((index$i$2 == i$var104)) {
					if((index$j$1 == (j$var115 - 1))) {
						{
							for(int var41 = 0; var41 < noStates; var41 += 1) {
								if((var41 == st[i$var104][(j$var115 - 1)]))
									cv$numStates = Math.max(cv$numStates, noStates);
							}
						}
					}
				}
			}
			for(int index$i$12 = 0; index$i$12 < samples; index$i$12 += 1) {
				for(int index$j$13 = 1; index$j$13 < length$eventsMeasured[index$i$12]; index$j$13 += 1) {
					if(!((index$j$13 == index$j$1) && (index$i$12 == index$i$2))) {
						for(int index$sample126$14 = 0; index$sample126$14 < noStates; index$sample126$14 += 1) {
							int distributionTempVariable$var123$16 = index$sample126$14;
							double cv$probabilitySample126Value15 = (1.0 * distribution$sample126[((index$i$12 - 0) / 1)][((index$j$13 - 1) / 1)][index$sample126$14]);
							{
								int traceTempVariable$var120$17_1 = distributionTempVariable$var123$16;
								if((index$i$12 == i$var104)) {
									if((index$j$13 == (j$var115 - 1))) {
										{
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == traceTempVariable$var120$17_1))
													cv$numStates = Math.max(cv$numStates, noStates);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(fixedFlag$sample95) {
					{
						for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
							if((i$var87 == i$var104)) {
								if((0 == (j$var115 - 1))) {
									{
										for(int var41 = 0; var41 < noStates; var41 += 1) {
											if((var41 == st[i$var104][(j$var115 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												double[] var121 = m[st[i$var104][(j$var115 - 1)]];
												double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														{
															int traceTempVariable$var120$37_1 = cv$currentValue;
														}
													}
												}
												{
													{
														{
															int traceTempVariable$var152$41_1 = cv$currentValue;
															for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
																if((i$var104 == i$var136)) {
																	for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																		if((j$var115 == j$var149)) {
																			{
																				{
																					boolean cv$sampleConstrained = true;
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							{
																								for(int var55 = 0; var55 < noStates; var55 += 1) {
																									if((var55 == traceTempVariable$var152$41_1)) {
																										{
																											{
																												{
																													double[] var153 = bias[traceTempVariable$var152$41_1];
																													if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																												}
																											}
																										}
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
										}
									}
								}
							}
						}
					}
				} else {
					for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
						if(true) {
							for(int index$sample95$22 = 0; index$sample95$22 < noStates; index$sample95$22 += 1) {
								int distributionTempVariable$var92$24 = index$sample95$22;
								double cv$probabilitySample95Value23 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$22]);
								{
									int traceTempVariable$var120$25_1 = distributionTempVariable$var92$24;
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											{
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == traceTempVariable$var120$25_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample95Value23);
														double[] var121 = m[traceTempVariable$var120$25_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample95Value23) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var120$38_1 = cv$currentValue;
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$var152$42_1 = cv$currentValue;
																	for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
																		if((i$var104 == i$var136)) {
																			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																				if((j$var115 == j$var149)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var55 = 0; var55 < noStates; var55 += 1) {
																											if((var55 == traceTempVariable$var152$42_1)) {
																												{
																													{
																														{
																															double[] var153 = bias[traceTempVariable$var152$42_1];
																															if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
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
												}
											}
										}
									}
								}
							}
						}
					}
				}
				{
					int traceTempVariable$var120$28_1 = cv$currentValue;
					if((index$i$2 == i$var104)) {
						if((index$j$1 == (j$var115 - 1))) {
							{
								for(int var41 = 0; var41 < noStates; var41 += 1) {
									if((var41 == traceTempVariable$var120$28_1)) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] var121 = m[traceTempVariable$var120$28_1];
										double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												{
													int traceTempVariable$var120$39_1 = cv$currentValue;
												}
											}
										}
										{
											{
												{
													int traceTempVariable$var152$43_1 = cv$currentValue;
													for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
														if((i$var104 == i$var136)) {
															for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																if((j$var115 == j$var149)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					{
																						for(int var55 = 0; var55 < noStates; var55 += 1) {
																							if((var55 == traceTempVariable$var152$43_1)) {
																								{
																									{
																										{
																											double[] var153 = bias[traceTempVariable$var152$43_1];
																											if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																											}
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																										}
																									}
																								}
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
								}
							}
						}
					}
				}
				for(int index$i$29 = 0; index$i$29 < samples; index$i$29 += 1) {
					for(int index$j$30 = 1; index$j$30 < length$eventsMeasured[index$i$29]; index$j$30 += 1) {
						if(!((index$j$30 == index$j$1) && (index$i$29 == index$i$2))) {
							for(int index$sample126$31 = 0; index$sample126$31 < noStates; index$sample126$31 += 1) {
								int distributionTempVariable$var123$33 = index$sample126$31;
								double cv$probabilitySample126Value32 = (1.0 * distribution$sample126[((index$i$29 - 0) / 1)][((index$j$30 - 1) / 1)][index$sample126$31]);
								{
									int traceTempVariable$var120$34_1 = distributionTempVariable$var123$33;
									if((index$i$29 == i$var104)) {
										if((index$j$30 == (j$var115 - 1))) {
											{
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == traceTempVariable$var120$34_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample126Value32);
														double[] var121 = m[traceTempVariable$var120$34_1];
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample126Value32) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
														{
															{
																{
																	int traceTempVariable$var120$40_1 = distributionTempVariable$var123$33;
																}
															}
														}
														{
															{
																{
																	int traceTempVariable$var152$44_1 = distributionTempVariable$var123$33;
																	for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
																		if((i$var104 == i$var136)) {
																			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																				if((j$var115 == j$var149)) {
																					{
																						{
																							boolean cv$sampleConstrained = true;
																							if(cv$sampleConstrained) {
																								constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
																								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																								double cv$consumerDistributionProbabilityAccumulator = 1.0;
																								{
																									{
																										for(int var55 = 0; var55 < noStates; var55 += 1) {
																											if((var55 == traceTempVariable$var152$44_1)) {
																												{
																													{
																														{
																															double[] var153 = bias[traceTempVariable$var152$44_1];
																															if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
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
												}
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
						{
							int traceTempVariable$var120$57_1 = cv$currentValue;
							for(int index$i$57_2 = 0; index$i$57_2 < samples; index$i$57_2 += 1) {
								if((i$var104 == index$i$57_2)) {
									for(int index$j$57_3 = 1; index$j$57_3 < length$eventsMeasured[index$i$57_2]; index$j$57_3 += 1) {
										if((j$var115 == (index$j$57_3 - 1))) {
											{
												{
													int index$j$59 = index$j$57_3;
													int index$i$60 = index$i$57_2;
													double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var104];
													for(int cv$i = 0; cv$i < noStates; cv$i += 1)
														cv$accumulatedConsumerDistributions[cv$i] = 0.0;
													double cv$reachedDistributionProbability = 0.0;
													{
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == traceTempVariable$var120$57_1)) {
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	if(fixedFlag$sample95) {
																		{
																			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
																				if((i$var87 == i$var104)) {
																					if((0 == (j$var115 - 1))) {
																						{
																							for(int index$var41$68_1 = 0; index$var41$68_1 < noStates; index$var41$68_1 += 1) {
																								if((index$var41$68_1 == st[i$var104][(j$var115 - 1)]))
																									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																							}
																						}
																					}
																				}
																			}
																		}
																	} else {
																		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
																			if(true) {
																				for(int index$sample95$64 = 0; index$sample95$64 < noStates; index$sample95$64 += 1) {
																					int distributionTempVariable$var92$66 = index$sample95$64;
																					double cv$probabilitySample95Value65 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$64]);
																					{
																						int traceTempVariable$var120$67_1 = distributionTempVariable$var92$66;
																						if((i$var87 == i$var104)) {
																							if((0 == (j$var115 - 1))) {
																								{
																									for(int index$var41$69_1 = 0; index$var41$69_1 < noStates; index$var41$69_1 += 1) {
																										if((index$var41$69_1 == traceTempVariable$var120$67_1))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample95Value65);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	{
																		int traceTempVariable$var120$70_1 = cv$currentValue;
																		if((index$i$2 == i$var104)) {
																			if((index$j$1 == (j$var115 - 1))) {
																				{
																					for(int index$var41$77_1 = 0; index$var41$77_1 < noStates; index$var41$77_1 += 1) {
																						if((index$var41$77_1 == traceTempVariable$var120$70_1))
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																				}
																			}
																		}
																	}
																	for(int index$i$71 = 0; index$i$71 < samples; index$i$71 += 1) {
																		for(int index$j$72 = 1; index$j$72 < length$eventsMeasured[index$i$71]; index$j$72 += 1) {
																			if((!((index$j$72 == index$j$1) && (index$i$71 == index$i$2)) && !((index$j$72 == index$j$59) && (index$i$71 == index$i$60)))) {
																				for(int index$sample126$73 = 0; index$sample126$73 < noStates; index$sample126$73 += 1) {
																					int distributionTempVariable$var123$75 = index$sample126$73;
																					double cv$probabilitySample126Value74 = (1.0 * distribution$sample126[((index$i$71 - 0) / 1)][((index$j$72 - 1) / 1)][index$sample126$73]);
																					{
																						int traceTempVariable$var120$76_1 = distributionTempVariable$var123$75;
																						if((index$i$71 == i$var104)) {
																							if((index$j$72 == (j$var115 - 1))) {
																								{
																									for(int index$var41$78_1 = 0; index$var41$78_1 < noStates; index$var41$78_1 += 1) {
																										if((index$var41$78_1 == traceTempVariable$var120$76_1))
																											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample126Value74);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	double[] var121 = m[traceTempVariable$var120$57_1];
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																	DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var121, noStates);
																}
															}
														}
													}
													double[] cv$sampleDistribution = distribution$sample126[((index$i$57_2 - 0) / 1)][((index$j$57_3 - 1) / 1)];
													double cv$overlap = 0.0;
													for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
														double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
														double cv$sampleDistValue = cv$sampleDistribution[cv$i];
														if((cv$sampleDistValue < cv$normalisedDistValue))
															cv$overlap = (cv$overlap + cv$sampleDistValue);
														else
															cv$overlap = (cv$overlap + cv$normalisedDistValue);
													}
													cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
												}
											}
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]) {
				double[] cv$localProbability = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void sample42(int var41, int threadID$cv$var41, Rng RNG$) {
		if(true) {
			constrainedFlag$sample42[((var41 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var41];
			double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if((var41 == initialState)) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(fixedFlag$sample95) {
										{
											{
												int index$i$3 = i$var87;
												boolean cv$sampleConstrained = (fixedFlag$sample95 || constrainedFlag$sample95[((i$var87 - 0) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample42[((var41 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$countLocal[st[i$var87][0]] = (cv$countLocal[st[i$var87][0]] + 1.0);
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
					}
				}
				{
					{
						for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(fixedFlag$sample95) {
									{
										for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													{
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															if(fixedFlag$sample126) {
																{
																	{
																		int index$j$27 = j$var115;
																		int index$i$28 = i$var104;
																		boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample42[((var41 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
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
											}
										}
									}
								} else {
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if(true) {
											for(int index$sample95$9 = 0; index$sample95$9 < noStates; index$sample95$9 += 1) {
												int distributionTempVariable$var92$11 = index$sample95$9;
												double cv$probabilitySample95Value10 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$9]);
												{
													int traceTempVariable$var120$12_1 = distributionTempVariable$var92$11;
													if((i$var87 == i$var104)) {
														if((0 == (j$var115 - 1))) {
															{
																if((var41 == traceTempVariable$var120$12_1)) {
																	if(fixedFlag$sample126) {
																		{
																			{
																				int index$j$30 = j$var115;
																				int index$i$31 = i$var104;
																				boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample42[((var41 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + cv$probabilitySample95Value10);
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
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(fixedFlag$sample126) {
									{
										for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
											for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
												if((index$i$17_1 == i$var104)) {
													if((index$j$17_2 == (j$var115 - 1))) {
														{
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																if(fixedFlag$sample126) {
																	{
																		{
																			int index$j$33 = j$var115;
																			int index$i$34 = i$var104;
																			boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample42[((var41 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
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
												}
											}
										}
									}
								} else {
									for(int index$i$18 = 0; index$i$18 < samples; index$i$18 += 1) {
										for(int index$j$19 = 1; index$j$19 < length$eventsMeasured[index$i$18]; index$j$19 += 1) {
											if(true) {
												for(int index$sample126$20 = 0; index$sample126$20 < noStates; index$sample126$20 += 1) {
													int distributionTempVariable$var123$22 = index$sample126$20;
													double cv$probabilitySample126Value21 = (1.0 * distribution$sample126[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample126$20]);
													{
														int traceTempVariable$var120$23_1 = distributionTempVariable$var123$22;
														if((index$i$18 == i$var104)) {
															if((index$j$19 == (j$var115 - 1))) {
																{
																	if((var41 == traceTempVariable$var120$23_1)) {
																		if(fixedFlag$sample126) {
																			{
																				{
																					int index$j$36 = j$var115;
																					int index$i$37 = i$var104;
																					boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
																					if(cv$sampleConstrained) {
																						constrainedFlag$sample42[((var41 - 0) / 1)] = true;
																						{
																							{
																								{
																									{
																										{
																											cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + cv$probabilitySample126Value21);
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
			{
				{
					{
						if((var41 == initialState)) {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if(!fixedFlag$sample95) {
									{
										{
											int index$i$44 = i$var87;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample95[((i$var87 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
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
			{
				{
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(fixedFlag$sample95) {
								{
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if((i$var87 == i$var104)) {
											if((0 == (j$var115 - 1))) {
												{
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														if(!fixedFlag$sample126) {
															{
																{
																	int index$j$67 = j$var115;
																	int index$i$68 = i$var104;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							} else {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										for(int index$sample95$49 = 0; index$sample95$49 < noStates; index$sample95$49 += 1) {
											int distributionTempVariable$var92$51 = index$sample95$49;
											double cv$probabilitySample95Value50 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$49]);
											{
												int traceTempVariable$var120$52_1 = distributionTempVariable$var92$51;
												if((i$var87 == i$var104)) {
													if((0 == (j$var115 - 1))) {
														{
															if((var41 == traceTempVariable$var120$52_1)) {
																if(!fixedFlag$sample126) {
																	{
																		{
																			int index$j$70 = j$var115;
																			int index$i$71 = i$var104;
																			{
																				{
																					double scopeVariable$reachedSourceProbability = 0.0;
																					{
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																					}
																					double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample95Value50);
																					for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																						cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								}
							}
						}
					}
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(fixedFlag$sample126) {
								{
									for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
										for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
											if((index$i$57_1 == i$var104)) {
												if((index$j$57_2 == (j$var115 - 1))) {
													{
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															if(!fixedFlag$sample126) {
																{
																	{
																		int index$j$73 = j$var115;
																		int index$i$74 = i$var104;
																		{
																			{
																				double scopeVariable$reachedSourceProbability = 0.0;
																				{
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																				}
																				double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																				for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							} else {
								for(int index$i$58 = 0; index$i$58 < samples; index$i$58 += 1) {
									for(int index$j$59 = 1; index$j$59 < length$eventsMeasured[index$i$58]; index$j$59 += 1) {
										if(true) {
											for(int index$sample126$60 = 0; index$sample126$60 < noStates; index$sample126$60 += 1) {
												int distributionTempVariable$var123$62 = index$sample126$60;
												double cv$probabilitySample126Value61 = (1.0 * distribution$sample126[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample126$60]);
												{
													int traceTempVariable$var120$63_1 = distributionTempVariable$var123$62;
													if((index$i$58 == i$var104)) {
														if((index$j$59 == (j$var115 - 1))) {
															{
																if((var41 == traceTempVariable$var120$63_1)) {
																	if(!fixedFlag$sample126) {
																		{
																			{
																				int index$j$76 = j$var115;
																				int index$i$77 = i$var104;
																				{
																					{
																						double scopeVariable$reachedSourceProbability = 0.0;
																						{
																							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																						}
																						double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample126Value61);
																						for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample42[((var41 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void sample57(int var55, int threadID$cv$var55, Rng RNG$) {
		if(true) {
			constrainedFlag$sample57[((var55 - 0) / 1)] = false;
			double[] cv$targetLocal = bias[var55];
			double[] cv$countLocal = cv$var56$countGlobal[threadID$cv$var55];
			int cv$arrayLength = noEvents;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
							for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
								if(fixedFlag$sample95) {
									{
										for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
											if((i$var87 == i$var136)) {
												if((0 == j$var149)) {
													{
														if((var55 == st[i$var136][j$var149])) {
															{
																{
																	boolean cv$sampleConstrained = true;
																	if(cv$sampleConstrained) {
																		constrainedFlag$sample57[((var55 - 0) / 1)] = true;
																		{
																			{
																				{
																					{
																						{
																							cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
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
										}
									}
								} else {
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if(true) {
											for(int index$sample95$5 = 0; index$sample95$5 < noStates; index$sample95$5 += 1) {
												int distributionTempVariable$var92$7 = index$sample95$5;
												double cv$probabilitySample95Value6 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$5]);
												{
													int traceTempVariable$var152$8_1 = distributionTempVariable$var92$7;
													if((i$var87 == i$var136)) {
														if((0 == j$var149)) {
															{
																if((var55 == traceTempVariable$var152$8_1)) {
																	{
																		{
																			boolean cv$sampleConstrained = true;
																			if(cv$sampleConstrained) {
																				constrainedFlag$sample57[((var55 - 0) / 1)] = true;
																				{
																					{
																						{
																							{
																								{
																									cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + cv$probabilitySample95Value6);
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
												}
											}
										}
									}
								}
							}
						}
						for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
							for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
								if(fixedFlag$sample126) {
									{
										for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
											for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
												if((i$var104 == i$var136)) {
													if((j$var115 == j$var149)) {
														{
															if((var55 == st[i$var136][j$var149])) {
																{
																	{
																		boolean cv$sampleConstrained = true;
																		if(cv$sampleConstrained) {
																			constrainedFlag$sample57[((var55 - 0) / 1)] = true;
																			{
																				{
																					{
																						{
																							{
																								cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
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
											}
										}
									}
								} else {
									for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
										for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
											if(true) {
												for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1) {
													int distributionTempVariable$var123$18 = index$sample126$16;
													double cv$probabilitySample126Value17 = (1.0 * distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][index$sample126$16]);
													{
														int traceTempVariable$var152$19_1 = distributionTempVariable$var123$18;
														if((i$var104 == i$var136)) {
															if((j$var115 == j$var149)) {
																{
																	if((var55 == traceTempVariable$var152$19_1)) {
																		{
																			{
																				boolean cv$sampleConstrained = true;
																				if(cv$sampleConstrained) {
																					constrainedFlag$sample57[((var55 - 0) / 1)] = true;
																					{
																						{
																							{
																								{
																									{
																										cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + cv$probabilitySample126Value17);
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
			if(constrainedFlag$sample57[((var55 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal, noEvents);
		}
	}

	private final void sample78() {
		if(true) {
			constrainedFlag$sample78 = false;
			double[] cv$targetLocal = weights;
			double[] cv$countLocal = cv$var75$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									boolean cv$sampleConstrained = (fixedFlag$sample80 || constrainedFlag$sample80);
									if(cv$sampleConstrained) {
										constrainedFlag$sample78 = true;
										{
											{
												{
													{
														{
															cv$countLocal[initialState] = (cv$countLocal[initialState] + 1.0);
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
			if(constrainedFlag$sample78)
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void sample80() {
		if(true) {
			constrainedFlag$sample80 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var77$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				initialState = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= weights[cv$currentValue])) && (weights[cv$currentValue] <= 1.0))?Math.log(weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									int traceTempVariable$initialState$1_2 = cv$currentValue;
									if(fixedFlag$sample95) {
										{
											{
												int index$i$3 = i$var87;
												boolean cv$sampleConstrained = (fixedFlag$sample95 || constrainedFlag$sample95[((i$var87 - 0) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample80 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double[] var90 = m[traceTempVariable$initialState$1_2];
																		if(((Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)));
																		}
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
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
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
				{
					{
						{
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								int traceTempVariable$initialState$5_2 = cv$currentValue;
								if(!fixedFlag$sample95) {
									{
										{
											int index$i$7 = i$var87;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var91;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double[] var90 = m[traceTempVariable$initialState$5_2];
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var90, noStates);
												}
											}
											double[] cv$sampleDistribution = distribution$sample95[((i$var87 - 0) / 1)];
											double cv$overlap = 0.0;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
												double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
												double cv$sampleDistValue = cv$sampleDistribution[cv$i];
												if((cv$sampleDistValue < cv$normalisedDistValue))
													cv$overlap = (cv$overlap + cv$sampleDistValue);
												else
													cv$overlap = (cv$overlap + cv$normalisedDistValue);
											}
											cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
										}
									}
								}
							}
						}
					}
				}
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample80) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void sample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		int index$i$1 = i$var87;
		if(true) {
			constrainedFlag$sample95[((i$var87 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var90 = m[initialState];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$currentValue])) && (var90[cv$currentValue] <= 1.0))?Math.log(var90[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
									if((i$var87 == i$var104)) {
										for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
											if((0 == (j$var115 - 1))) {
												if(fixedFlag$sample126) {
													{
														{
															int index$j$4 = j$var115;
															int index$i$5 = i$var104;
															boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
															if(cv$sampleConstrained) {
																constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		for(int var41 = 0; var41 < noStates; var41 += 1) {
																			if((var41 == traceTempVariable$var120$2_1)) {
																				{
																					{
																						{
																							double[] var121 = m[traceTempVariable$var120$2_1];
																							if(((Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
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
						}
					}
					{
						{
							{
								int traceTempVariable$var152$8_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
									if((i$var87 == i$var136)) {
										for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((0 == j$var149)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	for(int var55 = 0; var55 < noStates; var55 += 1) {
																		if((var55 == traceTempVariable$var152$8_1)) {
																			{
																				{
																					{
																						double[] var153 = bias[traceTempVariable$var152$8_1];
																						if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
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
				{
					{
						{
							int traceTempVariable$var120$12_1 = cv$currentValue;
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								if((i$var87 == i$var104)) {
									for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
										if((0 == (j$var115 - 1))) {
											if(!fixedFlag$sample126) {
												{
													{
														int index$j$14 = j$var115;
														int index$i$15 = i$var104;
														double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var87];
														for(int cv$i = 0; cv$i < noStates; cv$i += 1)
															cv$accumulatedConsumerDistributions[cv$i] = 0.0;
														double cv$reachedDistributionProbability = 0.0;
														{
															for(int var41 = 0; var41 < noStates; var41 += 1) {
																if((var41 == traceTempVariable$var120$12_1)) {
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double[] var121 = m[traceTempVariable$var120$12_1];
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																		cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
																		DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, var121, noStates);
																	}
																}
															}
														}
														double[] cv$sampleDistribution = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
														double cv$overlap = 0.0;
														for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
															double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
															double cv$sampleDistValue = cv$sampleDistribution[cv$i];
															if((cv$sampleDistValue < cv$normalisedDistValue))
																cv$overlap = (cv$overlap + cv$sampleDistValue);
															else
																cv$overlap = (cv$overlap + cv$normalisedDistValue);
														}
														cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(((cv$overlap * cv$reachedDistributionProbability) + (1.0 - Math.min(cv$reachedDistributionProbability, 1.0)))));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample95[((i$var87 - 0) / 1)]) {
				double[] cv$localProbability = distribution$sample95[((i$var87 - 0) / 1)];
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			{
				int cv$threadCount = threadCount();
				cv$var42$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var42$countGlobal[cv$index] = new double[noStates];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var56$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var56$countGlobal[cv$index] = new double[noEvents];
			}
		}
		{
			cv$var75$countGlobal = new double[noStates];
		}
		{
			int cv$var43$max = noStates;
			cv$distributionAccumulator$var91 = new double[cv$var43$max];
		}
		{
			cv$var77$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$var43$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$distributionAccumulator$var122 = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var122[cv$index] = new double[cv$var43$max];
			}
		}
		{
			int cv$var43$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var92$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var92$stateProbabilityGlobal[cv$index] = new double[cv$var43$max];
			}
		}
		{
			int cv$var43$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var123$stateProbabilityGlobal[cv$index] = new double[cv$var43$max];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		{
			v2 = new double[noEvents];
		}
		if(!fixedFlag$sample42) {
			{
				m = new double[noStates][];
				for(int var41 = 0; var41 < noStates; var41 += 1)
					m[var41] = new double[noStates];
			}
		}
		if(!fixedFlag$sample57) {
			{
				bias = new double[noStates][];
				for(int var55 = 0; var55 < noStates; var55 += 1)
					bias[var55] = new double[noEvents];
			}
		}
		if((!fixedFlag$sample95 || !fixedFlag$sample126)) {
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
					st[i$var69] = new int[length$eventsMeasured[i$var69]];
			}
		}
		if(!fixedFlag$sample78) {
			{
				weights = new double[noStates];
			}
		}
		{
			events = new int[length$eventsMeasured.length][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				events[i$var136] = new int[length$eventsMeasured[i$var136]];
		}
		{
			distribution$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var87 = 0; i$var87 < length$eventsMeasured.length; i$var87 += 1)
				distribution$sample95[((i$var87 - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)][];
				distribution$sample126[((i$var104 - 0) / 1)] = subarray$0;
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					subarray$0[((j$var115 - 1) / 1)] = new double[noStates];
			}
		}
		{
			constrainedFlag$sample95 = new boolean[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample126 = new boolean[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				constrainedFlag$sample126[((i$var104 - 0) / 1)] = new boolean[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		{
			constrainedFlag$sample42 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample57 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				logProbability$sample126[((i$var104 - 0) / 1)] = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample159 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				logProbability$sample159[((i$var136 - 0) / 1)] = new double[((((length$eventsMeasured[i$var136] - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = events[i$var136];
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var136][j$var149]], noEvents) + 1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						double[] cv$distribution$sample95 = distribution$sample95[((i$var87 - 0) / 1)];
						double[] var90 = m[initialState];
						for(int index$var91 = 0; index$var91 < noStates; index$var91 += 1) {
							double cv$value = ((((((0.0 <= index$var91) && (index$var91 < noStates)) && (0 < noStates)) && (0.0 <= var90[index$var91])) && (var90[index$var91] <= 1.0))?var90[index$var91]:0.0);
							if(!fixedFlag$sample95)
								cv$distribution$sample95[index$var91] = cv$value;
						}
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							double[] cv$distribution$sample126 = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
							for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
								if(!fixedFlag$sample126)
									cv$distribution$sample126[index$var122] = 0.0;
							}
							if(fixedFlag$sample95) {
								{
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if((i$var87 == i$var104)) {
											if((0 == (j$var115 - 1))) {
												{
													for(int var41 = 0; var41 < noStates; var41 += 1) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															{
																double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
																	if(!fixedFlag$sample126)
																		cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (1.0 * ((((((0.0 <= index$var122) && (index$var122 < noStates)) && (0 < noStates)) && (0.0 <= var121[index$var122])) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										for(int index$sample95$3 = 0; index$sample95$3 < noStates; index$sample95$3 += 1) {
											int distributionTempVariable$var92$5 = index$sample95$3;
											double cv$probabilitySample95Value4 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$3]);
											{
												int traceTempVariable$var120$6_1 = distributionTempVariable$var92$5;
												if((i$var87 == i$var104)) {
													if((0 == (j$var115 - 1))) {
														{
															for(int var41 = 0; var41 < noStates; var41 += 1) {
																if((var41 == traceTempVariable$var120$6_1)) {
																	{
																		double[] var121 = m[traceTempVariable$var120$6_1];
																		for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
																			if(!fixedFlag$sample126)
																				cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample95Value4 * ((((((0.0 <= index$var122) && (index$var122 < noStates)) && (0 < noStates)) && (0.0 <= var121[index$var122])) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
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
							if(fixedFlag$sample126) {
								{
									for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
										for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
											if((index$i$9_1 == i$var104)) {
												if((index$j$9_2 == (j$var115 - 1))) {
													{
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																	for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
																		if(!fixedFlag$sample126)
																			cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (1.0 * ((((((0.0 <= index$var122) && (index$var122 < noStates)) && (0 < noStates)) && (0.0 <= var121[index$var122])) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
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
							} else {
								for(int index$i$10 = 0; index$i$10 < samples; index$i$10 += 1) {
									for(int index$j$11 = 1; index$j$11 < length$eventsMeasured[index$i$10]; index$j$11 += 1) {
										if(true) {
											for(int index$sample126$12 = 0; index$sample126$12 < noStates; index$sample126$12 += 1) {
												int distributionTempVariable$var123$14 = index$sample126$12;
												double cv$probabilitySample126Value13 = (1.0 * distribution$sample126[((index$i$10 - 0) / 1)][((index$j$11 - 1) / 1)][index$sample126$12]);
												{
													int traceTempVariable$var120$15_1 = distributionTempVariable$var123$14;
													if((index$i$10 == i$var104)) {
														if((index$j$11 == (j$var115 - 1))) {
															{
																for(int var41 = 0; var41 < noStates; var41 += 1) {
																	if((var41 == traceTempVariable$var120$15_1)) {
																		{
																			double[] var121 = m[traceTempVariable$var120$15_1];
																			for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
																				if(!fixedFlag$sample126)
																					cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample126Value13 * ((((((0.0 <= index$var122) && (index$var122 < noStates)) && (0 < noStates)) && (0.0 <= var121[index$var122])) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
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
							}
							double cv$var122$sum = 0.0;
							for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
								if(!fixedFlag$sample126)
									cv$var122$sum = (cv$var122$sum + cv$distribution$sample126[index$var122]);
							}
							for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1) {
								if(!fixedFlag$sample126)
									cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] / cv$var122$sum);
							}
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = events[i$var136];
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var136][j$var149]], noEvents) + 1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						double[] var42 = m[var41];
						if(!fixedFlag$sample42)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var42);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						double[] var56 = bias[var55];
						if(!fixedFlag$sample57)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, var56);
					}
			}
		);
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						int[] var88 = st[i$var87];
						if(!fixedFlag$sample95)
							var88[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						int[] var116 = st[i$var104];
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!fixedFlag$sample126)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
							if(!fixedFlag$sample42)
								sample42(var41, threadID$var41, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!fixedFlag$sample57)
								sample57(var55, threadID$var55, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample78)
				sample78();
			if(!fixedFlag$sample80)
				sample80();
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							if(!fixedFlag$sample95)
								sample95(i$var87, threadID$i$var87, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(!fixedFlag$sample126)
									sample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = (length$eventsMeasured[i$var104] - ((((length$eventsMeasured[i$var104] - 1) - 1) % 1) + 1)); j$var115 >= ((1 - 1) + 1); j$var115 -= 1) {
								if(!fixedFlag$sample126)
									sample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
						}
				}
			);
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							if(!fixedFlag$sample95)
								sample95(i$var87, threadID$i$var87, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample80)
				sample80();
			if(!fixedFlag$sample78)
				sample78();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!fixedFlag$sample57)
								sample57(var55, threadID$var55, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
							if(!fixedFlag$sample42)
								sample42(var41, threadID$var41, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = Double.NaN;
		if(!fixedProbFlag$sample78)
			logProbability$weights = Double.NaN;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				logProbability$sample95[((i$var87 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = Double.NaN;
			}
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		for(int var14 = 0; var14 < noStates; var14 += 1)
			v[var14] = 0.1;
		for(int var27 = 0; var27 < noEvents; var27 += 1)
			v2[var27] = 0.1;
		samples = length$eventsMeasured.length;
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
			boolean[] cv$constrainedFlag$sample126$1 = constrainedFlag$sample126[index$constrainedFlag$sample126$1];
			for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
				cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityDistribution$sample95();
		logProbabilityDistribution$sample126();
		logProbabilityDistribution$sample159();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void propagateObservedValues() {
		int[][] cv$source1 = eventsMeasured;
		int[][] cv$target1 = events;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
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
		     + "model HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sampleDistribution();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}