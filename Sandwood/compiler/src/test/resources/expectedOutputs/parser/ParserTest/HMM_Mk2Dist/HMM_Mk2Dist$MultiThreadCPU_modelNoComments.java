package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	private double[][] bias;
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
	private double[][] logProbability$var122;
	private double[][] logProbability$var154;
	private double logProbability$var30;
	private double logProbability$var42;
	private double logProbability$var44;
	private double logProbability$var56;
	private double logProbability$var74;
	private double logProbability$var76;
	private double[] logProbability$var91;
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
							int cv$sampleValue = st[i$var104][j$var115];
							if(fixedFlag$sample95) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													{
														double[] var121 = m[st[i$var104][(j$var115 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							} else {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										for(int index$sample95$6 = 0; index$sample95$6 < noStates; index$sample95$6 += 1) {
											int distributionTempVariable$var92$8 = index$sample95$6;
											double cv$probabilitySample95Value7 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$6]);
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													for(int var41 = 0; var41 < noStates; var41 += 1) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															{
																double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample95Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if((index$i$2 == i$var104)) {
								if((index$j$1 == (j$var115 - 1))) {
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)])) {
											{
												double[] var121 = m[st[i$var104][(j$var115 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample126) {
								for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
									for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
										if((index$i$13_1 == i$var104)) {
											if((index$j$13_2 == (j$var115 - 1))) {
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														{
															double[] var121 = m[st[i$var104][(j$var115 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							} else {
								for(int index$i$14 = 0; index$i$14 < samples; index$i$14 += 1) {
									for(int index$j$15 = 1; index$j$15 < length$eventsMeasured[index$i$14]; index$j$15 += 1) {
										if(!((index$i$14 == index$i$2) && (index$j$15 == index$j$1))) {
											for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1) {
												int distributionTempVariable$var123$18 = index$sample126$16;
												double cv$probabilitySample126Value17 = (1.0 * distribution$sample126[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample126$16]);
												if((index$i$14 == i$var104)) {
													if((index$j$15 == (j$var115 - 1))) {
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample126Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						double cv$sampleProbability = cv$distributionAccumulator;
						cv$sampleReached = true;
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleAccumulator;
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
					logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$rvAccumulator;
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
						int cv$sampleValue = (events[i$var136][j$var149] - 1);
						if(fixedFlag$sample95) {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if((i$var87 == i$var136)) {
									if((0 == j$var149)) {
										for(int var55 = 0; var55 < noStates; var55 += 1) {
											if((var55 == st[i$var136][j$var149])) {
												{
													double[] var153 = bias[st[i$var136][j$var149]];
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if(true) {
									for(int index$sample95$4 = 0; index$sample95$4 < noStates; index$sample95$4 += 1) {
										int distributionTempVariable$var92$6 = index$sample95$4;
										double cv$probabilitySample95Value5 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$4]);
										if((i$var87 == i$var136)) {
											if((0 == j$var149)) {
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == st[i$var136][j$var149])) {
														{
															double[] var153 = bias[st[i$var136][j$var149]];
															double cv$weightedProbability = (Math.log(cv$probabilitySample95Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample126) {
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((i$var104 == i$var136)) {
										if((j$var115 == j$var149)) {
											for(int var55 = 0; var55 < noStates; var55 += 1) {
												if((var55 == st[i$var136][j$var149])) {
													{
														double[] var153 = bias[st[i$var136][j$var149]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if(true) {
										for(int index$sample126$13 = 0; index$sample126$13 < noStates; index$sample126$13 += 1) {
											int distributionTempVariable$var123$15 = index$sample126$13;
											double cv$probabilitySample126Value14 = (1.0 * distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][index$sample126$13]);
											if((i$var104 == i$var136)) {
												if((j$var115 == j$var149)) {
													for(int var55 = 0; var55 < noStates; var55 += 1) {
														if((var55 == st[i$var136][j$var149])) {
															{
																double[] var153 = bias[st[i$var136][j$var149]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample126Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleAccumulator;
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
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$rvAccumulator;
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
						int cv$sampleValue = st[i$var87][0];
						{
							{
								double[] var90 = m[initialState];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var91[((i$var87 - 0) / 1)] = cv$sampleAccumulator;
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
				logProbability$var91[((i$var87 - 0) / 1)] = cv$rvAccumulator;
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
						int cv$sampleValue = st[i$var104][j$var115];
						{
							{
								double[] var121 = m[st[i$var104][(j$var115 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleAccumulator;
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
					logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$rvAccumulator;
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
						int cv$sampleValue = (events[i$var136][j$var149] - 1);
						{
							{
								double[] var153 = bias[st[i$var136][j$var149]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleAccumulator;
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
					logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$rvAccumulator;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var30 = cv$sampleAccumulator;
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
			logProbability$var30 = cv$rvAccumulator;
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var44 = cv$sampleAccumulator;
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
			logProbability$var44 = cv$rvAccumulator;
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
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var74 = cv$sampleAccumulator;
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
			logProbability$var74 = cv$rvAccumulator;
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
				int cv$sampleValue = initialState;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var76 = cv$sampleAccumulator;
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
			logProbability$var76 = cv$rvAccumulator;
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
					int cv$sampleValue = st[i$var87][0];
					{
						{
							double[] var90 = m[initialState];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var91[((i$var87 - 0) / 1)] = cv$sampleAccumulator;
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
				logProbability$var91[((i$var87 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			int index$j$1 = j$var115;
			int index$i$2 = i$var104;
			if(fixedFlag$sample95) {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					if((i$var87 == i$var104)) {
						if((0 == (j$var115 - 1))) {
							for(int var41 = 0; var41 < noStates; var41 += 1) {
								if((var41 == st[i$var104][(j$var115 - 1)]))
									cv$numNumStates = Math.max(cv$numNumStates, noStates);
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
							if((i$var87 == i$var104)) {
								if((0 == (j$var115 - 1))) {
									for(int var41 = 0; var41 < noStates; var41 += 1) {
										if((var41 == st[i$var104][(j$var115 - 1)]))
											cv$numNumStates = Math.max(cv$numNumStates, noStates);
									}
								}
							}
						}
					}
				}
			}
			if((index$i$2 == i$var104)) {
				if((index$j$1 == (j$var115 - 1))) {
					for(int var41 = 0; var41 < noStates; var41 += 1) {
						if((var41 == st[i$var104][(j$var115 - 1)]))
							cv$numNumStates = Math.max(cv$numNumStates, noStates);
					}
				}
			}
			if(fixedFlag$sample126) {
				for(int index$i$12_1 = 0; index$i$12_1 < samples; index$i$12_1 += 1) {
					for(int index$j$12_2 = 1; index$j$12_2 < length$eventsMeasured[index$i$12_1]; index$j$12_2 += 1) {
						if((index$i$12_1 == i$var104)) {
							if((index$j$12_2 == (j$var115 - 1))) {
								for(int var41 = 0; var41 < noStates; var41 += 1) {
									if((var41 == st[i$var104][(j$var115 - 1)]))
										cv$numNumStates = Math.max(cv$numNumStates, noStates);
								}
							}
						}
					}
				}
			} else {
				for(int index$i$13 = 0; index$i$13 < samples; index$i$13 += 1) {
					for(int index$j$14 = 1; index$j$14 < length$eventsMeasured[index$i$13]; index$j$14 += 1) {
						if(!((index$i$13 == index$i$2) && (index$j$14 == index$j$1))) {
							for(int index$sample126$15 = 0; index$sample126$15 < noStates; index$sample126$15 += 1) {
								int distributionTempVariable$var123$17 = index$sample126$15;
								double cv$probabilitySample126Value16 = (1.0 * distribution$sample126[((index$i$13 - 0) / 1)][((index$j$14 - 1) / 1)][index$sample126$15]);
								if((index$i$13 == i$var104)) {
									if((index$j$14 == (j$var115 - 1))) {
										for(int var41 = 0; var41 < noStates; var41 += 1) {
											if((var41 == st[i$var104][(j$var115 - 1)]))
												cv$numNumStates = Math.max(cv$numNumStates, noStates);
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				int index$j$22 = j$var115;
				int index$i$23 = i$var104;
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(fixedFlag$sample95) {
					for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
						if((i$var87 == i$var104)) {
							if((0 == (j$var115 - 1))) {
								for(int var41 = 0; var41 < noStates; var41 += 1) {
									if((var41 == st[i$var104][(j$var115 - 1)])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] cv$temp$0$var121;
										{
											double[] var121 = m[st[i$var104][(j$var115 - 1)]];
											cv$temp$0$var121 = var121;
										}
										int cv$temp$1$$var1516;
										{
											int $var1516 = noStates;
											cv$temp$1$$var1516 = $var1516;
										}
										double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1516))?Math.log(cv$temp$0$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												int traceTempVariable$var120$41_1 = cv$currentValue;
											}
										}
										{
											{
												int traceTempVariable$var152$45_1 = cv$currentValue;
												for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
													if((i$var104 == i$var136)) {
														for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
															if((j$var115 == j$var149)) {
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int var55 = 0; var55 < noStates; var55 += 1) {
																			if((var55 == st[i$var136][j$var149])) {
																				{
																					{
																						double[] cv$temp$8$var153;
																						{
																							double[] var153 = bias[traceTempVariable$var152$45_1];
																							cv$temp$8$var153 = var153;
																						}
																						int cv$temp$9$$var1548;
																						{
																							int $var1548 = noEvents;
																							cv$temp$9$$var1548 = $var1548;
																						}
																						if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1548))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1548))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1548))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1548))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$9$$var1548))?Math.log(cv$temp$8$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				} else {
					for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
						if(true) {
							for(int index$sample95$26 = 0; index$sample95$26 < noStates; index$sample95$26 += 1) {
								int distributionTempVariable$var92$28 = index$sample95$26;
								double cv$probabilitySample95Value27 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$26]);
								if((i$var87 == i$var104)) {
									if((0 == (j$var115 - 1))) {
										for(int var41 = 0; var41 < noStates; var41 += 1) {
											if((var41 == st[i$var104][(j$var115 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample95Value27);
												double[] cv$temp$2$var121;
												{
													double[] var121 = m[st[i$var104][(j$var115 - 1)]];
													cv$temp$2$var121 = var121;
												}
												int cv$temp$3$$var1517;
												{
													int $var1517 = noStates;
													cv$temp$3$$var1517 = $var1517;
												}
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample95Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var1517))?Math.log(cv$temp$2$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														int traceTempVariable$var120$42_1 = cv$currentValue;
													}
												}
												{
													{
														int traceTempVariable$var152$46_1 = cv$currentValue;
														for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
															if((i$var104 == i$var136)) {
																for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																	if((j$var115 == j$var149)) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var55 = 0; var55 < noStates; var55 += 1) {
																					if((var55 == st[i$var136][j$var149])) {
																						{
																							{
																								double[] cv$temp$10$var153;
																								{
																									double[] var153 = bias[traceTempVariable$var152$46_1];
																									cv$temp$10$var153 = var153;
																								}
																								int cv$temp$11$$var1549;
																								{
																									int $var1549 = noEvents;
																									cv$temp$11$$var1549 = $var1549;
																								}
																								if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1549))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1549))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1549))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1549))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$11$$var1549))?Math.log(cv$temp$10$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				int traceTempVariable$var120$32_1 = cv$currentValue;
				if((index$i$23 == i$var104)) {
					if((index$j$22 == (j$var115 - 1))) {
						for(int var41 = 0; var41 < noStates; var41 += 1) {
							if((var41 == st[i$var104][(j$var115 - 1)])) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$4$var121;
								{
									double[] var121 = m[traceTempVariable$var120$32_1];
									cv$temp$4$var121 = var121;
								}
								int cv$temp$5$$var1518;
								{
									int $var1518 = noStates;
									cv$temp$5$$var1518 = $var1518;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var1518))?Math.log(cv$temp$4$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var120$43_1 = cv$currentValue;
									}
								}
								{
									{
										int traceTempVariable$var152$47_1 = cv$currentValue;
										for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
											if((i$var104 == i$var136)) {
												for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
													if((j$var115 == j$var149)) {
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var55 = 0; var55 < noStates; var55 += 1) {
																	if((var55 == st[i$var136][j$var149])) {
																		{
																			{
																				double[] cv$temp$12$var153;
																				{
																					double[] var153 = bias[traceTempVariable$var152$47_1];
																					cv$temp$12$var153 = var153;
																				}
																				int cv$temp$13$$var1550;
																				{
																					int $var1550 = noEvents;
																					cv$temp$13$$var1550 = $var1550;
																				}
																				if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1550))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1550))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1550))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1550))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$13$$var1550))?Math.log(cv$temp$12$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				for(int index$i$33 = 0; index$i$33 < samples; index$i$33 += 1) {
					for(int index$j$34 = 1; index$j$34 < length$eventsMeasured[index$i$33]; index$j$34 += 1) {
						if(!((index$i$33 == index$i$23) && (index$j$34 == index$j$22))) {
							for(int index$sample126$35 = 0; index$sample126$35 < noStates; index$sample126$35 += 1) {
								int distributionTempVariable$var123$37 = index$sample126$35;
								double cv$probabilitySample126Value36 = (1.0 * distribution$sample126[((index$i$33 - 0) / 1)][((index$j$34 - 1) / 1)][index$sample126$35]);
								int traceTempVariable$var120$38_1 = cv$currentValue;
								if((index$i$33 == i$var104)) {
									if((index$j$34 == (j$var115 - 1))) {
										for(int var41 = 0; var41 < noStates; var41 += 1) {
											if((var41 == st[i$var104][(j$var115 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample126Value36);
												double[] cv$temp$6$var121;
												{
													double[] var121 = m[traceTempVariable$var120$38_1];
													cv$temp$6$var121 = var121;
												}
												int cv$temp$7$$var1519;
												{
													int $var1519 = noStates;
													cv$temp$7$$var1519 = $var1519;
												}
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample126Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var1519))?Math.log(cv$temp$6$var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														int traceTempVariable$var120$44_1 = distributionTempVariable$var123$37;
													}
												}
												{
													{
														int traceTempVariable$var152$48_1 = distributionTempVariable$var123$37;
														for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
															if((i$var104 == i$var136)) {
																for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
																	if((j$var115 == j$var149)) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var55 = 0; var55 < noStates; var55 += 1) {
																					if((var55 == st[i$var136][j$var149])) {
																						{
																							{
																								double[] cv$temp$14$var153;
																								{
																									double[] var153 = bias[traceTempVariable$var152$48_1];
																									cv$temp$14$var153 = var153;
																								}
																								int cv$temp$15$$var1551;
																								{
																									int $var1551 = noEvents;
																									cv$temp$15$$var1551 = $var1551;
																								}
																								if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1551))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1551))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1551))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1551))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$15$$var1551))?Math.log(cv$temp$14$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				{
					{
						int traceTempVariable$var120$61_1 = cv$currentValue;
						for(int index$i$61_2 = 0; index$i$61_2 < samples; index$i$61_2 += 1) {
							if((i$var104 == index$i$61_2)) {
								for(int index$j$61_3 = 1; index$j$61_3 < length$eventsMeasured[index$i$61_2]; index$j$61_3 += 1) {
									if((j$var115 == (index$j$61_3 - 1))) {
										{
											int index$j$63 = index$j$61_3;
											int index$i$64 = index$i$61_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var104];
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == st[index$i$61_2][(index$j$61_3 - 1)])) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														if(fixedFlag$sample95) {
															for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
																if((i$var87 == i$var104)) {
																	if((0 == (j$var115 - 1))) {
																		for(int index$var41$72_1 = 0; index$var41$72_1 < noStates; index$var41$72_1 += 1) {
																			if((index$var41$72_1 == st[i$var104][(j$var115 - 1)]))
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
														} else {
															for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
																if(true) {
																	for(int index$sample95$68 = 0; index$sample95$68 < noStates; index$sample95$68 += 1) {
																		int distributionTempVariable$var92$70 = index$sample95$68;
																		double cv$probabilitySample95Value69 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$68]);
																		if((i$var87 == i$var104)) {
																			if((0 == (j$var115 - 1))) {
																				for(int index$var41$73_1 = 0; index$var41$73_1 < noStates; index$var41$73_1 += 1) {
																					if((index$var41$73_1 == st[i$var104][(j$var115 - 1)]))
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample95Value69);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$var120$74_1 = cv$currentValue;
														if((index$i$23 == i$var104)) {
															if((index$j$22 == (j$var115 - 1))) {
																for(int index$var41$81_1 = 0; index$var41$81_1 < noStates; index$var41$81_1 += 1) {
																	if((index$var41$81_1 == st[i$var104][(j$var115 - 1)]))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
														for(int index$i$75 = 0; index$i$75 < samples; index$i$75 += 1) {
															for(int index$j$76 = 1; index$j$76 < length$eventsMeasured[index$i$75]; index$j$76 += 1) {
																if((!((index$i$75 == index$i$23) && (index$j$76 == index$j$22)) && !((index$i$75 == index$i$64) && (index$j$76 == index$j$63)))) {
																	for(int index$sample126$77 = 0; index$sample126$77 < noStates; index$sample126$77 += 1) {
																		int distributionTempVariable$var123$79 = index$sample126$77;
																		double cv$probabilitySample126Value78 = (1.0 * distribution$sample126[((index$i$75 - 0) / 1)][((index$j$76 - 1) / 1)][index$sample126$77]);
																		int traceTempVariable$var120$80_1 = cv$currentValue;
																		if((index$i$75 == i$var104)) {
																			if((index$j$76 == (j$var115 - 1))) {
																				for(int index$var41$82_1 = 0; index$var41$82_1 < noStates; index$var41$82_1 += 1) {
																					if((index$var41$82_1 == st[i$var104][(j$var115 - 1)]))
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample126Value78);
																				}
																			}
																		}
																	}
																}
															}
														}
														double[] cv$temp$16$var121;
														{
															double[] var121 = m[traceTempVariable$var120$61_1];
															cv$temp$16$var121 = var121;
														}
														int cv$temp$17$$var1589;
														{
															int $var1589 = noStates;
															cv$temp$17$$var1589 = $var1589;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$16$var121, cv$temp$17$$var1589);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample126[((index$i$61_2 - 0) / 1)][((index$j$61_3 - 1) / 1)];
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
			double[] cv$localProbability = distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample42(int var41, int threadID$cv$var41, Rng RNG$) {
		if(true) {
			double[] cv$targetLocal = m[var41];
			double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						if((var41 == initialState)) {
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								if(fixedFlag$sample95) {
									{
										int index$i$3 = i$var87;
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
				{
					{
						for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(fixedFlag$sample95) {
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if((i$var87 == i$var104)) {
											if((0 == (j$var115 - 1))) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													if(fixedFlag$sample126) {
														{
															int index$j$27 = j$var115;
															int index$i$28 = i$var104;
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
								} else {
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if(true) {
											for(int index$sample95$9 = 0; index$sample95$9 < noStates; index$sample95$9 += 1) {
												int distributionTempVariable$var92$11 = index$sample95$9;
												double cv$probabilitySample95Value10 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$9]);
												if((i$var87 == i$var104)) {
													if((0 == (j$var115 - 1))) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															if(fixedFlag$sample126) {
																{
																	int index$j$30 = j$var115;
																	int index$i$31 = i$var104;
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
						for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								if(fixedFlag$sample126) {
									for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
										for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
											if((index$i$17_1 == i$var104)) {
												if((index$j$17_2 == (j$var115 - 1))) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														if(fixedFlag$sample126) {
															{
																int index$j$33 = j$var115;
																int index$i$34 = i$var104;
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
								} else {
									for(int index$i$18 = 0; index$i$18 < samples; index$i$18 += 1) {
										for(int index$j$19 = 1; index$j$19 < length$eventsMeasured[index$i$18]; index$j$19 += 1) {
											if(true) {
												for(int index$sample126$20 = 0; index$sample126$20 < noStates; index$sample126$20 += 1) {
													int distributionTempVariable$var123$22 = index$sample126$20;
													double cv$probabilitySample126Value21 = (1.0 * distribution$sample126[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample126$20]);
													if((index$i$18 == i$var104)) {
														if((index$j$19 == (j$var115 - 1))) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																if(fixedFlag$sample126) {
																	{
																		int index$j$36 = j$var115;
																		int index$i$37 = i$var104;
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
			{
				{
					if((var41 == initialState)) {
						for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
							if(!fixedFlag$sample95) {
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
			{
				{
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(fixedFlag$sample95) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											if((var41 == st[i$var104][(j$var115 - 1)])) {
												if(!fixedFlag$sample126) {
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
							} else {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if(true) {
										for(int index$sample95$49 = 0; index$sample95$49 < noStates; index$sample95$49 += 1) {
											int distributionTempVariable$var92$51 = index$sample95$49;
											double cv$probabilitySample95Value50 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$49]);
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														if(!fixedFlag$sample126) {
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
					for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(fixedFlag$sample126) {
								for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
									for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
										if((index$i$57_1 == i$var104)) {
											if((index$j$57_2 == (j$var115 - 1))) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													if(!fixedFlag$sample126) {
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
							} else {
								for(int index$i$58 = 0; index$i$58 < samples; index$i$58 += 1) {
									for(int index$j$59 = 1; index$j$59 < length$eventsMeasured[index$i$58]; index$j$59 += 1) {
										if(true) {
											for(int index$sample126$60 = 0; index$sample126$60 < noStates; index$sample126$60 += 1) {
												int distributionTempVariable$var123$62 = index$sample126$60;
												double cv$probabilitySample126Value61 = (1.0 * distribution$sample126[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample126$60]);
												if((index$i$58 == i$var104)) {
													if((index$j$59 == (j$var115 - 1))) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															if(!fixedFlag$sample126) {
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void sample57(int var55, int threadID$cv$var55, Rng RNG$) {
		if(true) {
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
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if((i$var87 == i$var136)) {
											if((0 == j$var149)) {
												if((var55 == st[i$var136][j$var149])) {
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
								} else {
									for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
										if(true) {
											for(int index$sample95$5 = 0; index$sample95$5 < noStates; index$sample95$5 += 1) {
												int distributionTempVariable$var92$7 = index$sample95$5;
												double cv$probabilitySample95Value6 = (1.0 * distribution$sample95[((i$var87 - 0) / 1)][index$sample95$5]);
												if((i$var87 == i$var136)) {
													if((0 == j$var149)) {
														if((var55 == st[i$var136][j$var149])) {
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
						for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
							for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
								if(fixedFlag$sample126) {
									for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
										for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
											if((i$var104 == i$var136)) {
												if((j$var115 == j$var149)) {
													if((var55 == st[i$var136][j$var149])) {
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
								} else {
									for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
										for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
											if(true) {
												for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1) {
													int distributionTempVariable$var123$18 = index$sample126$16;
													double cv$probabilitySample126Value17 = (1.0 * distribution$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)][index$sample126$16]);
													if((i$var104 == i$var136)) {
														if((j$var115 == j$var149)) {
															if((var55 == st[i$var136][j$var149])) {
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal, noEvents);
		}
	}

	private final void sample78() {
		if(true) {
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
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void sample80() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var77$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				initialState = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$weights;
					{
						cv$temp$0$weights = weights;
					}
					int cv$temp$1$$var1353;
					{
						int $var1353 = noStates;
						cv$temp$1$$var1353 = $var1353;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1353))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
								int traceTempVariable$initialState$1_2 = cv$currentValue;
								if(fixedFlag$sample95) {
									{
										int index$i$3 = i$var87;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double[] cv$temp$2$var90;
														{
															double[] var90 = m[traceTempVariable$initialState$1_2];
															cv$temp$2$var90 = var90;
														}
														int cv$temp$3$$var1363;
														{
															int $var1363 = noStates;
															cv$temp$3$$var1363 = $var1363;
														}
														if(((Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1363))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1363))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1363))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1363))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var87][0]) && (st[i$var87][0] < cv$temp$3$$var1363))?Math.log(cv$temp$2$var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)));
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
						for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
							int traceTempVariable$initialState$5_2 = cv$currentValue;
							if(!fixedFlag$sample95) {
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
											double[] cv$temp$4$var90;
											{
												double[] var90 = m[traceTempVariable$initialState$5_2];
												cv$temp$4$var90 = var90;
											}
											int cv$temp$5$$var1374;
											{
												int $var1374 = noStates;
												cv$temp$5$$var1374 = $var1374;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$4$var90, cv$temp$5$$var1374);
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
			initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		}
	}

	private final void sample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			int index$i$1 = i$var87;
			{
				cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				int index$i$2 = i$var87;
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var90;
					{
						double[] var90 = m[initialState];
						cv$temp$0$var90 = var90;
					}
					int cv$temp$1$$var1393;
					{
						int $var1393 = noStates;
						cv$temp$1$$var1393 = $var1393;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var1393))?Math.log(cv$temp$0$var90[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$var120$3_1 = cv$currentValue;
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								if((i$var87 == i$var104)) {
									for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
										if((0 == (j$var115 - 1))) {
											if(fixedFlag$sample126) {
												{
													int index$j$5 = j$var115;
													int index$i$6 = i$var104;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	{
																		double[] cv$temp$2$var121;
																		{
																			double[] var121 = m[traceTempVariable$var120$3_1];
																			cv$temp$2$var121 = var121;
																		}
																		int cv$temp$3$$var1406;
																		{
																			int $var1406 = noStates;
																			cv$temp$3$$var1406 = $var1406;
																		}
																		if(((Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1406))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1406))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1406))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1406))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < cv$temp$3$$var1406))?Math.log(cv$temp$2$var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							int traceTempVariable$var152$9_1 = cv$currentValue;
							for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
								if((i$var87 == i$var136)) {
									for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
										if((0 == j$var149)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var55 = 0; var55 < noStates; var55 += 1) {
														if((var55 == st[i$var136][j$var149])) {
															{
																{
																	double[] cv$temp$4$var153;
																	{
																		double[] var153 = bias[traceTempVariable$var152$9_1];
																		cv$temp$4$var153 = var153;
																	}
																	int cv$temp$5$$var1417;
																	{
																		int $var1417 = noEvents;
																		cv$temp$5$$var1417 = $var1417;
																	}
																	if(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1417))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1417))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1417))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1417))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < cv$temp$5$$var1417))?Math.log(cv$temp$4$var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var120$13_1 = cv$currentValue;
						for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
							if((i$var87 == i$var104)) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((0 == (j$var115 - 1))) {
										if(!fixedFlag$sample126) {
											{
												int index$j$15 = j$var115;
												int index$i$16 = i$var104;
												double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var87];
												for(int cv$i = 0; cv$i < noStates; cv$i += 1)
													cv$accumulatedConsumerDistributions[cv$i] = 0.0;
												double cv$reachedDistributionProbability = 0.0;
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double[] cv$temp$6$var121;
															{
																double[] var121 = m[traceTempVariable$var120$13_1];
																cv$temp$6$var121 = var121;
															}
															int cv$temp$7$$var1430;
															{
																int $var1430 = noStates;
																cv$temp$7$$var1430 = $var1430;
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
															DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$6$var121, cv$temp$7$$var1430);
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double[] cv$localProbability = distribution$sample95[((i$var87 - 0) / 1)];
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
					cv$localProbability[cv$indexName] = (1.0 / cv$numNumStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
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
			distribution$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)][];
				distribution$sample126[((i$var104 - 0) / 1)] = subarray$0;
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					subarray$0[((j$var115 - 1) / 1)] = new double[noStates];
			}
		}
		{
			distribution$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var87 = 0; i$var87 < length$eventsMeasured.length; i$var87 += 1)
				distribution$sample95[((i$var87 - 0) / 1)] = new double[noStates];
		}
		{
			logProbability$var91 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var122 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				logProbability$var122[((i$var104 - 0) / 1)] = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				logProbability$sample126[((i$var104 - 0) / 1)] = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var154 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				logProbability$var154[((i$var136 - 0) / 1)] = new double[((((length$eventsMeasured[i$var136] - 1) - 1) / 1) + 1)];
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
	public final void forwardGenerationDistributionsNoOutputs() {
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
							double cv$value = (((0.0 <= index$var91) && (index$var91 < noStates))?var90[index$var91]:0.0);
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
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									if((i$var87 == i$var104)) {
										if((0 == (j$var115 - 1))) {
											for(int var41 = 0; var41 < noStates; var41 += 1) {
												if((var41 == st[i$var104][(j$var115 - 1)])) {
													{
														if(!fixedFlag$sample126) {
															double[] var121 = m[st[i$var104][(j$var115 - 1)]];
															for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (1.0 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
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
											if((i$var87 == i$var104)) {
												if((0 == (j$var115 - 1))) {
													for(int var41 = 0; var41 < noStates; var41 += 1) {
														if((var41 == st[i$var104][(j$var115 - 1)])) {
															{
																if(!fixedFlag$sample126) {
																	double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																	for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																		cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample95Value4 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
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
								for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
									for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
										if((index$i$9_1 == i$var104)) {
											if((index$j$9_2 == (j$var115 - 1))) {
												for(int var41 = 0; var41 < noStates; var41 += 1) {
													if((var41 == st[i$var104][(j$var115 - 1)])) {
														{
															if(!fixedFlag$sample126) {
																double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																	cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (1.0 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
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
												if((index$i$10 == i$var104)) {
													if((index$j$11 == (j$var115 - 1))) {
														for(int var41 = 0; var41 < noStates; var41 += 1) {
															if((var41 == st[i$var104][(j$var115 - 1)])) {
																{
																	if(!fixedFlag$sample126) {
																		double[] var121 = m[st[i$var104][(j$var115 - 1)]];
																		for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
																			cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample126Value13 * (((0.0 <= index$var122) && (index$var122 < noStates))?var121[index$var122]:0.0)));
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

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var14, int forEnd$var14, int threadID$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var14 = forStart$var14; var14 < forEnd$var14; var14 += 1)
						v[var14] = 0.1;
			}
		);
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
						v2[var27] = 0.1;
			}
		);
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$var44 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = Double.NaN;
		logProbability$var74 = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$weights = Double.NaN;
		logProbability$var76 = 0.0;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = Double.NaN;
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
			logProbability$var91[((i$var87 - 0) / 1)] = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				logProbability$sample95[((i$var87 - 0) / 1)] = Double.NaN;
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
				logProbability$var122[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = Double.NaN;
			}
		}
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
				logProbability$var154[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = Double.NaN;
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
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
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
	public final void logProbabilityGeneration() {
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
		logModelProbabilitiesVal();
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