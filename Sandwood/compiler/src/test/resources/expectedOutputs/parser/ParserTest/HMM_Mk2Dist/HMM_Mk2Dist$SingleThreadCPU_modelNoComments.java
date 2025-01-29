package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Mk2Dist$CoreInterface {
	private double[][] bias;
	private double[] cv$distributionAccumulator$var54;
	private double[] cv$distributionAccumulator$var73;
	private double[] cv$var25$countGlobal;
	private double[] cv$var32$countGlobal;
	private double[] cv$var45$countGlobal;
	private double[] cv$var47$stateProbabilityGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var74$stateProbabilityGlobal;
	private double[][] distribution$sample61;
	private double[][][] distribution$sample81;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample103 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample81 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[][] logProbability$sample103;
	private double[] logProbability$sample61;
	private double[][] logProbability$sample81;
	private double logProbability$st;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var27;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var46;
	private double[] logProbability$var54;
	private double[][] logProbability$var73;
	private double[][] logProbability$var93;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$events = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$weights = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2Dist$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[][] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final void set$events(int[][] cv$value) {
		events = cv$value;
		setFlag$events = true;
		fixedProbFlag$sample103 = false;
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
	public final boolean get$fixedFlag$sample103() {
		return fixedFlag$sample103;
	}

	@Override
	public final void set$fixedFlag$sample103(boolean cv$value) {
		fixedFlag$sample103 = cv$value;
		fixedProbFlag$sample103 = (fixedFlag$sample103 && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample61 = (fixedFlag$sample26 && fixedProbFlag$sample61);
		fixedProbFlag$sample81 = (fixedFlag$sample26 && fixedProbFlag$sample81);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (fixedFlag$sample34 && fixedProbFlag$sample34);
		fixedProbFlag$sample103 = (fixedFlag$sample34 && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
		fixedProbFlag$sample53 = (fixedFlag$sample51 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		fixedProbFlag$sample61 = (fixedFlag$sample53 && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedProbFlag$sample61);
		fixedProbFlag$sample81 = (fixedFlag$sample61 && fixedProbFlag$sample81);
		fixedProbFlag$sample103 = (fixedFlag$sample61 && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedProbFlag$sample81);
		fixedProbFlag$sample103 = (fixedFlag$sample81 && fixedProbFlag$sample103);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample61 = false;
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
		setFlag$m = true;
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample61 = false;
		fixedProbFlag$sample81 = false;
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
		setFlag$st = true;
		fixedProbFlag$sample61 = false;
		fixedProbFlag$sample81 = false;
		fixedProbFlag$sample103 = false;
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
		setFlag$weights = true;
		fixedProbFlag$sample51 = false;
		fixedProbFlag$sample53 = false;
	}

	private final void logProbabilityDistribution$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = (events[i$var80][j$var88] - 1);
						if(fixedFlag$sample61) {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if((i$var50 == i$var80)) {
									if((0 == j$var88)) {
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[i$var80][j$var88])) {
												{
													double[] var92 = bias[st[i$var80][j$var88]];
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample61$4 = 0; index$sample61$4 < noStates; index$sample61$4 += 1) {
										int distributionTempVariable$var55$6 = index$sample61$4;
										double cv$probabilitySample61Value5 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$4]);
										int traceTempVariable$var91$7_1 = distributionTempVariable$var55$6;
										if((i$var50 == i$var80)) {
											if((0 == j$var88)) {
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var91$7_1)) {
														{
															double[] var92 = bias[traceTempVariable$var91$7_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample61Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample81) {
							for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if((i$var60 == i$var80)) {
										if((j$var66 == j$var88)) {
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == st[i$var80][j$var88])) {
													{
														double[] var92 = bias[st[i$var80][j$var88]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if(true) {
										for(int index$sample81$13 = 0; index$sample81$13 < noStates; index$sample81$13 += 1) {
											int distributionTempVariable$var74$15 = index$sample81$13;
											double cv$probabilitySample81Value14 = (1.0 * distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][index$sample81$13]);
											int traceTempVariable$var91$16_1 = distributionTempVariable$var74$15;
											if((i$var60 == i$var80)) {
												if((j$var66 == j$var88)) {
													for(int var31 = 0; var31 < noStates; var31 += 1) {
														if((var31 == traceTempVariable$var91$16_1)) {
															{
																double[] var92 = bias[traceTempVariable$var91$16_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample81Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample81Value14);
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
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (((fixedFlag$sample103 && fixedFlag$sample34) && fixedFlag$sample61) && fixedFlag$sample81);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample61() {
		if(!fixedProbFlag$sample61) {
			if(fixedFlag$sample61) {
				double cv$accumulator = 0.0;
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var50;
					{
						int cv$sampleValue = st[i$var50][0];
						{
							{
								double[] var53 = m[initialState];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var54[((i$var50 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample61[((i$var50 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample61)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample61)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample26) && fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample61[((i$var50 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((i$var50 - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample61)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample81() {
		if(!fixedProbFlag$sample81) {
			if(fixedFlag$sample81) {
				double cv$accumulator = 0.0;
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$j$1 = j$var66;
						int index$i$2 = i$var60;
						{
							int cv$sampleValue = st[i$var60][j$var66];
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var60)) {
										if((0 == (j$var66 - 1))) {
											for(int var24 = 0; var24 < noStates; var24 += 1) {
												if((var24 == st[i$var60][(j$var66 - 1)])) {
													{
														double[] var72 = m[st[i$var60][(j$var66 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample61$6 = 0; index$sample61$6 < noStates; index$sample61$6 += 1) {
											int distributionTempVariable$var55$8 = index$sample61$6;
											double cv$probabilitySample61Value7 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$6]);
											int traceTempVariable$var71$9_1 = distributionTempVariable$var55$8;
											if((i$var50 == i$var60)) {
												if((0 == (j$var66 - 1))) {
													for(int var24 = 0; var24 < noStates; var24 += 1) {
														if((var24 == traceTempVariable$var71$9_1)) {
															{
																double[] var72 = m[traceTempVariable$var71$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample61Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							int traceTempVariable$var71$12_1 = DistributionSampling.sampleCategorical(RNG$, m[st[index$i$2][(index$j$1 - 1)]]);
							if((index$i$2 == i$var60)) {
								if((index$j$1 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == traceTempVariable$var71$12_1)) {
											{
												double[] var72 = m[traceTempVariable$var71$12_1];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample81) {
								for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
									for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
										if((index$i$13_1 == i$var60)) {
											if((index$j$13_2 == (j$var66 - 1))) {
												for(int var24 = 0; var24 < noStates; var24 += 1) {
													if((var24 == st[i$var60][(j$var66 - 1)])) {
														{
															double[] var72 = m[st[i$var60][(j$var66 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											for(int index$sample81$16 = 0; index$sample81$16 < noStates; index$sample81$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample81$16;
												double cv$probabilitySample81Value17 = (1.0 * distribution$sample81[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample81$16]);
												int traceTempVariable$var71$19_1 = distributionTempVariable$var74$18;
												if((index$i$14 == i$var60)) {
													if((index$j$15 == (j$var66 - 1))) {
														for(int var24 = 0; var24 < noStates; var24 += 1) {
															if((var24 == traceTempVariable$var71$19_1)) {
																{
																	double[] var72 = m[traceTempVariable$var71$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample81Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample81Value17);
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
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample81)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample81)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample26) && fixedFlag$sample61);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample81)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = (events[i$var80][j$var88] - 1);
						{
							{
								double[] var92 = bias[st[i$var80][j$var88]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (((fixedFlag$sample103 && fixedFlag$sample34) && fixedFlag$sample61) && fixedFlag$sample81);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noStates; var24 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var24];
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = bias[var31];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2));
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
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = weights;
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
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$weights = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var44 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = initialState;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weights.length))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$initialState = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample51);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var46 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var50;
				{
					int cv$sampleValue = st[i$var50][0];
					{
						{
							double[] var53 = m[initialState];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var54[((i$var50 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample61[((i$var50 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample26) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample61[((i$var50 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((i$var50 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$j$1 = j$var66;
					int index$i$2 = i$var60;
					{
						int cv$sampleValue = st[i$var60][j$var66];
						{
							{
								double[] var72 = m[st[i$var60][(j$var66 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample26) && fixedFlag$sample61);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int var24) {
		double[] cv$targetLocal = m[var24];
		double[] cv$countLocal = cv$var25$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var24 == initialState)) {
						for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
							if(fixedFlag$sample61) {
								{
									int index$i$3 = i$var50;
									{
										{
											{
												{
													cv$countLocal[st[i$var50][0]] = (cv$countLocal[st[i$var50][0]] + 1.0);
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
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var60)) {
										if((0 == (j$var66 - 1))) {
											if((var24 == st[i$var60][(j$var66 - 1)])) {
												if(fixedFlag$sample81) {
													{
														int index$j$27 = j$var66;
														int index$i$28 = i$var60;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + 1.0);
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
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample61$9 = 0; index$sample61$9 < noStates; index$sample61$9 += 1) {
											int distributionTempVariable$var55$11 = index$sample61$9;
											double cv$probabilitySample61Value10 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$9]);
											int traceTempVariable$var71$12_1 = distributionTempVariable$var55$11;
											if((i$var50 == i$var60)) {
												if((0 == (j$var66 - 1))) {
													if((var24 == traceTempVariable$var71$12_1)) {
														if(fixedFlag$sample81) {
															{
																int index$j$30 = j$var66;
																int index$i$31 = i$var60;
																{
																	{
																		{
																			{
																				cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + cv$probabilitySample61Value10);
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
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
							if(fixedFlag$sample81) {
								for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
									for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
										if((index$i$17_1 == i$var60)) {
											if((index$j$17_2 == (j$var66 - 1))) {
												if((var24 == st[i$var60][(j$var66 - 1)])) {
													if(fixedFlag$sample81) {
														{
															int index$j$33 = j$var66;
															int index$i$34 = i$var60;
															{
																{
																	{
																		{
																			cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + 1.0);
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
											for(int index$sample81$20 = 0; index$sample81$20 < noStates; index$sample81$20 += 1) {
												int distributionTempVariable$var74$22 = index$sample81$20;
												double cv$probabilitySample81Value21 = (1.0 * distribution$sample81[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample81$20]);
												int traceTempVariable$var71$23_1 = distributionTempVariable$var74$22;
												if((index$i$18 == i$var60)) {
													if((index$j$19 == (j$var66 - 1))) {
														if((var24 == traceTempVariable$var71$23_1)) {
															if(fixedFlag$sample81) {
																{
																	int index$j$36 = j$var66;
																	int index$i$37 = i$var60;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + cv$probabilitySample81Value21);
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
				if((var24 == initialState)) {
					for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
						if(!fixedFlag$sample61) {
							{
								int index$i$44 = i$var50;
								{
									{
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[((i$var50 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
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
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						if(fixedFlag$sample61) {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if((i$var50 == i$var60)) {
									if((0 == (j$var66 - 1))) {
										if((var24 == st[i$var60][(j$var66 - 1)])) {
											if(!fixedFlag$sample81) {
												{
													int index$j$67 = j$var66;
													int index$i$68 = i$var60;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
								if(true) {
									for(int index$sample61$49 = 0; index$sample61$49 < noStates; index$sample61$49 += 1) {
										int distributionTempVariable$var55$51 = index$sample61$49;
										double cv$probabilitySample61Value50 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$49]);
										int traceTempVariable$var71$52_1 = distributionTempVariable$var55$51;
										if((i$var50 == i$var60)) {
											if((0 == (j$var66 - 1))) {
												if((var24 == traceTempVariable$var71$52_1)) {
													if(!fixedFlag$sample81) {
														{
															int index$j$70 = j$var66;
															int index$i$71 = i$var60;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample61Value50);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						if(fixedFlag$sample81) {
							for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
								for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
									if((index$i$57_1 == i$var60)) {
										if((index$j$57_2 == (j$var66 - 1))) {
											if((var24 == st[i$var60][(j$var66 - 1)])) {
												if(!fixedFlag$sample81) {
													{
														int index$j$73 = j$var66;
														int index$i$74 = i$var60;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										for(int index$sample81$60 = 0; index$sample81$60 < noStates; index$sample81$60 += 1) {
											int distributionTempVariable$var74$62 = index$sample81$60;
											double cv$probabilitySample81Value61 = (1.0 * distribution$sample81[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample81$60]);
											int traceTempVariable$var71$63_1 = distributionTempVariable$var74$62;
											if((index$i$58 == i$var60)) {
												if((index$j$59 == (j$var66 - 1))) {
													if((var24 == traceTempVariable$var71$63_1)) {
														if(!fixedFlag$sample81) {
															{
																int index$j$76 = j$var66;
																int index$i$77 = i$var60;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample81Value61);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample34(int var31) {
		double[] cv$targetLocal = bias[var31];
		double[] cv$countLocal = cv$var32$countGlobal;
		int cv$arrayLength = noEvents;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
						for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
							if(fixedFlag$sample61) {
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if((i$var50 == i$var80)) {
										if((0 == j$var88)) {
											if((var31 == st[i$var80][j$var88])) {
												{
													{
														{
															{
																{
																	cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + 1.0);
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
								for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
									if(true) {
										for(int index$sample61$5 = 0; index$sample61$5 < noStates; index$sample61$5 += 1) {
											int distributionTempVariable$var55$7 = index$sample61$5;
											double cv$probabilitySample61Value6 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$5]);
											int traceTempVariable$var91$8_1 = distributionTempVariable$var55$7;
											if((i$var50 == i$var80)) {
												if((0 == j$var88)) {
													if((var31 == traceTempVariable$var91$8_1)) {
														{
															{
																{
																	{
																		{
																			cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + cv$probabilitySample61Value6);
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
					for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
						for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
							if(fixedFlag$sample81) {
								for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
									for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
										if((i$var60 == i$var80)) {
											if((j$var66 == j$var88)) {
												if((var31 == st[i$var80][j$var88])) {
													{
														{
															{
																{
																	{
																		cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + 1.0);
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
								for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
									for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
										if(true) {
											for(int index$sample81$16 = 0; index$sample81$16 < noStates; index$sample81$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample81$16;
												double cv$probabilitySample81Value17 = (1.0 * distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)][index$sample81$16]);
												int traceTempVariable$var91$19_1 = distributionTempVariable$var74$18;
												if((i$var60 == i$var80)) {
													if((j$var66 == j$var88)) {
														if((var31 == traceTempVariable$var91$19_1)) {
															{
																{
																	{
																		{
																			{
																				cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + cv$probabilitySample81Value17);
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal);
	}

	private final void sample51() {
		double[] cv$targetLocal = weights;
		double[] cv$countLocal = cv$var45$countGlobal;
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
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample53() {
		double[] cv$stateProbabilityLocal = cv$var47$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weights.length))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							if(fixedFlag$sample61) {
								{
									int index$i$3 = i$var50;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double[] cv$temp$1$var53;
													{
														double[] var53 = m[traceTempVariable$initialState$1_2];
														cv$temp$1$var53 = var53;
													}
													if(((Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY)));
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
					for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
						int traceTempVariable$initialState$5_2 = cv$currentValue;
						if(!fixedFlag$sample61) {
							{
								int index$i$7 = i$var50;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var54;
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								{
									{
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double[] cv$temp$2$var53;
										{
											double[] var53 = m[traceTempVariable$initialState$5_2];
											cv$temp$2$var53 = var53;
										}
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
										DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$2$var53);
									}
								}
								double[] cv$sampleDistribution = distribution$sample61[((i$var50 - 0) / 1)];
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	private final void sample61(int i$var50) {
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$i$1 = i$var50;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var53;
				{
					double[] var53 = m[initialState];
					cv$temp$0$var53 = var53;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var53.length))?Math.log(cv$temp$0$var53[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var71$2_1 = cv$currentValue;
						for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
							if((i$var50 == i$var60)) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
									if((0 == (j$var66 - 1))) {
										if(fixedFlag$sample81) {
											{
												int index$j$4 = j$var66;
												int index$i$5 = i$var60;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var24 = 0; var24 < noStates; var24 += 1) {
														if((var24 == traceTempVariable$var71$2_1)) {
															{
																{
																	double[] cv$temp$1$var72;
																	{
																		double[] var72 = m[traceTempVariable$var71$2_1];
																		cv$temp$1$var72 = var72;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var60][j$var66]) && (st[i$var60][j$var66] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var60][j$var66]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var91$8_1 = cv$currentValue;
						for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
							if((i$var50 == i$var80)) {
								for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
									if((0 == j$var88)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == traceTempVariable$var91$8_1)) {
														{
															{
																double[] cv$temp$2$var92;
																{
																	double[] var92 = bias[traceTempVariable$var91$8_1];
																	cv$temp$2$var92 = var92;
																}
																if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$2$var92.length))?Math.log(cv$temp$2$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
					int traceTempVariable$var71$12_1 = cv$currentValue;
					for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
						if((i$var50 == i$var60)) {
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
								if((0 == (j$var66 - 1))) {
									if(!fixedFlag$sample81) {
										{
											int index$j$14 = j$var66;
											int index$i$15 = i$var60;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var24 = 0; var24 < noStates; var24 += 1) {
												if((var24 == traceTempVariable$var71$12_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$3$var72;
														{
															double[] var72 = m[traceTempVariable$var71$12_1];
															cv$temp$3$var72 = var72;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var72);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample61[((i$var50 - 0) / 1)];
		double cv$logSum = 0.0;
		{
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample81(int i$var60, int j$var66) {
		double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$j$1 = j$var66;
			int index$i$2 = i$var60;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample61) {
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					if((i$var50 == i$var60)) {
						if((0 == (j$var66 - 1))) {
							for(int var24 = 0; var24 < noStates; var24 += 1) {
								if((var24 == st[i$var60][(j$var66 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var72;
									{
										double[] var72 = m[st[i$var60][(j$var66 - 1)]];
										cv$temp$0$var72 = var72;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var72.length))?Math.log(cv$temp$0$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var71$20_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$var91$24_1 = cv$currentValue;
											for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
												if((i$var60 == i$var80)) {
													for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
														if((j$var66 == j$var88)) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var31 = 0; var31 < noStates; var31 += 1) {
																		if((var31 == traceTempVariable$var91$24_1)) {
																			{
																				{
																					double[] cv$temp$4$var92;
																					{
																						double[] var92 = bias[traceTempVariable$var91$24_1];
																						cv$temp$4$var92 = var92;
																					}
																					if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$4$var92.length))?Math.log(cv$temp$4$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					if(true) {
						for(int index$sample61$5 = 0; index$sample61$5 < noStates; index$sample61$5 += 1) {
							int distributionTempVariable$var55$7 = index$sample61$5;
							double cv$probabilitySample61Value6 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$5]);
							int traceTempVariable$var71$8_1 = distributionTempVariable$var55$7;
							if((i$var50 == i$var60)) {
								if((0 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == traceTempVariable$var71$8_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample61Value6);
											double[] cv$temp$1$var72;
											{
												double[] var72 = m[traceTempVariable$var71$8_1];
												cv$temp$1$var72 = var72;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample61Value6) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var71$21_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$var91$25_1 = cv$currentValue;
													for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
														if((i$var60 == i$var80)) {
															for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
																if((j$var66 == j$var88)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var31 = 0; var31 < noStates; var31 += 1) {
																				if((var31 == traceTempVariable$var91$25_1)) {
																					{
																						{
																							double[] cv$temp$5$var92;
																							{
																								double[] var92 = bias[traceTempVariable$var91$25_1];
																								cv$temp$5$var92 = var92;
																							}
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$5$var92.length))?Math.log(cv$temp$5$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
			int traceTempVariable$var71$11_1 = cv$currentValue;
			if((index$i$2 == i$var60)) {
				if((index$j$1 == (j$var66 - 1))) {
					for(int var24 = 0; var24 < noStates; var24 += 1) {
						if((var24 == traceTempVariable$var71$11_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var72;
							{
								double[] var72 = m[traceTempVariable$var71$11_1];
								cv$temp$2$var72 = var72;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var72.length))?Math.log(cv$temp$2$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var71$22_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$var91$26_1 = cv$currentValue;
									for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
										if((i$var60 == i$var80)) {
											for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
												if((j$var66 == j$var88)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var31 = 0; var31 < noStates; var31 += 1) {
																if((var31 == traceTempVariable$var91$26_1)) {
																	{
																		{
																			double[] cv$temp$6$var92;
																			{
																				double[] var92 = bias[traceTempVariable$var91$26_1];
																				cv$temp$6$var92 = var92;
																			}
																			if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$6$var92.length))?Math.log(cv$temp$6$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
			for(int index$i$12 = 0; index$i$12 < samples; index$i$12 += 1) {
				for(int index$j$13 = 1; index$j$13 < length$eventsMeasured[index$i$12]; index$j$13 += 1) {
					if(!((index$i$12 == index$i$2) && (index$j$13 == index$j$1))) {
						for(int index$sample81$14 = 0; index$sample81$14 < noStates; index$sample81$14 += 1) {
							int distributionTempVariable$var74$16 = index$sample81$14;
							double cv$probabilitySample81Value15 = (1.0 * distribution$sample81[((index$i$12 - 0) / 1)][((index$j$13 - 1) / 1)][index$sample81$14]);
							int traceTempVariable$var71$17_1 = distributionTempVariable$var74$16;
							if((index$i$12 == i$var60)) {
								if((index$j$13 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == traceTempVariable$var71$17_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample81Value15);
											double[] cv$temp$3$var72;
											{
												double[] var72 = m[traceTempVariable$var71$17_1];
												cv$temp$3$var72 = var72;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample81Value15) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var72.length))?Math.log(cv$temp$3$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var71$23_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$var91$27_1 = cv$currentValue;
													for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
														if((i$var60 == i$var80)) {
															for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
																if((j$var66 == j$var88)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var31 = 0; var31 < noStates; var31 += 1) {
																				if((var31 == traceTempVariable$var91$27_1)) {
																					{
																						{
																							double[] cv$temp$7$var92;
																							{
																								double[] var92 = bias[traceTempVariable$var91$27_1];
																								cv$temp$7$var92 = var92;
																							}
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var80][j$var88] - 1)) && ((events[i$var80][j$var88] - 1) < cv$temp$7$var92.length))?Math.log(cv$temp$7$var92[(events[i$var80][j$var88] - 1)]):Double.NEGATIVE_INFINITY)));
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
					int traceTempVariable$var71$40_1 = cv$currentValue;
					for(int index$i$40_2 = 0; index$i$40_2 < samples; index$i$40_2 += 1) {
						if((i$var60 == index$i$40_2)) {
							for(int index$j$40_3 = 1; index$j$40_3 < length$eventsMeasured[index$i$40_2]; index$j$40_3 += 1) {
								if((j$var66 == (index$j$40_3 - 1))) {
									{
										int index$j$42 = index$j$40_3;
										int index$i$43 = index$i$40_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var24 = 0; var24 < noStates; var24 += 1) {
											if((var24 == traceTempVariable$var71$40_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample61) {
														for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
															if((i$var50 == i$var60)) {
																if((0 == (j$var66 - 1))) {
																	for(int index$var24$51_1 = 0; index$var24$51_1 < noStates; index$var24$51_1 += 1) {
																		if((index$var24$51_1 == st[i$var60][(j$var66 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
															if(true) {
																for(int index$sample61$47 = 0; index$sample61$47 < noStates; index$sample61$47 += 1) {
																	int distributionTempVariable$var55$49 = index$sample61$47;
																	double cv$probabilitySample61Value48 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$47]);
																	int traceTempVariable$var71$50_1 = distributionTempVariable$var55$49;
																	if((i$var50 == i$var60)) {
																		if((0 == (j$var66 - 1))) {
																			for(int index$var24$52_1 = 0; index$var24$52_1 < noStates; index$var24$52_1 += 1) {
																				if((index$var24$52_1 == traceTempVariable$var71$50_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample61Value48);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var71$53_1 = cv$currentValue;
													if((index$i$2 == i$var60)) {
														if((index$j$1 == (j$var66 - 1))) {
															for(int index$var24$60_1 = 0; index$var24$60_1 < noStates; index$var24$60_1 += 1) {
																if((index$var24$60_1 == traceTempVariable$var71$53_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$i$54 = 0; index$i$54 < samples; index$i$54 += 1) {
														for(int index$j$55 = 1; index$j$55 < length$eventsMeasured[index$i$54]; index$j$55 += 1) {
															if((!((index$i$54 == index$i$2) && (index$j$55 == index$j$1)) && !((index$i$54 == index$i$43) && (index$j$55 == index$j$42)))) {
																for(int index$sample81$56 = 0; index$sample81$56 < noStates; index$sample81$56 += 1) {
																	int distributionTempVariable$var74$58 = index$sample81$56;
																	double cv$probabilitySample81Value57 = (1.0 * distribution$sample81[((index$i$54 - 0) / 1)][((index$j$55 - 1) / 1)][index$sample81$56]);
																	int traceTempVariable$var71$59_1 = distributionTempVariable$var74$58;
																	if((index$i$54 == i$var60)) {
																		if((index$j$55 == (j$var66 - 1))) {
																			for(int index$var24$61_1 = 0; index$var24$61_1 < noStates; index$var24$61_1 += 1) {
																				if((index$var24$61_1 == traceTempVariable$var71$59_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample81Value57);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$8$var72;
													{
														double[] var72 = m[traceTempVariable$var71$40_1];
														cv$temp$8$var72 = var72;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var72);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample81[((index$i$40_2 - 0) / 1)][((index$j$40_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
		double cv$logSum = 0.0;
		{
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
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var24 = 0; var24 < noStates; var24 += 1)
				cv$max = Math.max(cv$max, noStates);
			cv$var25$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var31 = 0; var31 < noStates; var31 += 1)
				cv$max = Math.max(cv$max, noEvents);
			cv$var32$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var45$countGlobal = new double[cv$max];
		}
		{
			int cv$var26$max = noStates;
			cv$distributionAccumulator$var54 = new double[cv$var26$max];
		}
		{
			cv$var47$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$var26$max = noStates;
			cv$distributionAccumulator$var73 = new double[cv$var26$max];
		}
		{
			int cv$var26$max = noStates;
			cv$var55$stateProbabilityGlobal = new double[cv$var26$max];
		}
		{
			int cv$var26$max = noStates;
			cv$var74$stateProbabilityGlobal = new double[cv$var26$max];
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
		if(!setFlag$m) {
			{
				m = new double[noStates][];
				for(int var24 = 0; var24 < noStates; var24 += 1)
					m[var24] = new double[noStates];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[noStates][];
				for(int var31 = 0; var31 < noStates; var31 += 1)
					bias[var31] = new double[noEvents];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var38 = 0; i$var38 < length$eventsMeasured.length; i$var38 += 1)
					st[i$var38] = new int[length$eventsMeasured[i$var38]];
			}
		}
		if(!setFlag$weights) {
			{
				weights = new double[noStates];
			}
		}
		if(!setFlag$events) {
			{
				events = new int[length$eventsMeasured.length][];
				for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
					events[i$var80] = new int[length$eventsMeasured[i$var80]];
			}
		}
		{
			distribution$sample81 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)][];
				distribution$sample81[((i$var60 - 0) / 1)] = subarray$0;
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					subarray$0[((j$var66 - 1) / 1)] = new double[noStates];
			}
		}
		{
			distribution$sample61 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var50 = 0; i$var50 < length$eventsMeasured.length; i$var50 += 1)
				distribution$sample61[((i$var50 - 0) / 1)] = new double[noStates];
		}
		{
			logProbability$var54 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample61 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var73 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
				logProbability$var73[((i$var60 - 0) / 1)] = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample81 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
				logProbability$sample81[((i$var60 - 0) / 1)] = new double[((((length$eventsMeasured[i$var60] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var93 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				logProbability$var93[((i$var80 - 0) / 1)] = new double[((((length$eventsMeasured[i$var80] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample103 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				logProbability$sample103[((i$var80 - 0) / 1)] = new double[((((length$eventsMeasured[i$var80] - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample61)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample81)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			int[] var89 = events[i$var80];
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
				if(!fixedFlag$sample103)
					var89[j$var88] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var80][j$var88]]) + 1);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			double[] cv$distribution$sample61 = distribution$sample61[((i$var50 - 0) / 1)];
			double[] var53 = m[initialState];
			for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1) {
				double cv$value = (((0.0 <= index$var54) && (index$var54 < var53.length))?var53[index$var54]:0.0);
				if(!fixedFlag$sample61)
					cv$distribution$sample61[index$var54] = cv$value;
			}
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				double[] cv$distribution$sample81 = distribution$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)];
				for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
					if(!fixedFlag$sample81)
						cv$distribution$sample81[index$var73] = 0.0;
				}
				if(fixedFlag$sample61) {
					for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
						if((i$var50 == i$var60)) {
							if((0 == (j$var66 - 1))) {
								for(int var24 = 0; var24 < noStates; var24 += 1) {
									if((var24 == st[i$var60][(j$var66 - 1)])) {
										{
											if(!fixedFlag$sample81) {
												double[] var72 = m[st[i$var60][(j$var66 - 1)]];
												for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
													cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				} else {
					for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
						if(true) {
							for(int index$sample61$3 = 0; index$sample61$3 < noStates; index$sample61$3 += 1) {
								int distributionTempVariable$var55$5 = index$sample61$3;
								double cv$probabilitySample61Value4 = (1.0 * distribution$sample61[((i$var50 - 0) / 1)][index$sample61$3]);
								int traceTempVariable$var71$6_1 = distributionTempVariable$var55$5;
								if((i$var50 == i$var60)) {
									if((0 == (j$var66 - 1))) {
										for(int var24 = 0; var24 < noStates; var24 += 1) {
											if((var24 == traceTempVariable$var71$6_1)) {
												{
													if(!fixedFlag$sample81) {
														double[] var72 = m[traceTempVariable$var71$6_1];
														for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
															cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (cv$probabilitySample61Value4 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
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
				if(fixedFlag$sample81) {
					for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
						for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
							if((index$i$9_1 == i$var60)) {
								if((index$j$9_2 == (j$var66 - 1))) {
									for(int var24 = 0; var24 < noStates; var24 += 1) {
										if((var24 == st[i$var60][(j$var66 - 1)])) {
											{
												if(!fixedFlag$sample81) {
													double[] var72 = m[st[i$var60][(j$var66 - 1)]];
													for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
														cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
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
								for(int index$sample81$12 = 0; index$sample81$12 < noStates; index$sample81$12 += 1) {
									int distributionTempVariable$var74$14 = index$sample81$12;
									double cv$probabilitySample81Value13 = (1.0 * distribution$sample81[((index$i$10 - 0) / 1)][((index$j$11 - 1) / 1)][index$sample81$12]);
									int traceTempVariable$var71$15_1 = distributionTempVariable$var74$14;
									if((index$i$10 == i$var60)) {
										if((index$j$11 == (j$var66 - 1))) {
											for(int var24 = 0; var24 < noStates; var24 += 1) {
												if((var24 == traceTempVariable$var71$15_1)) {
													{
														if(!fixedFlag$sample81) {
															double[] var72 = m[traceTempVariable$var71$15_1];
															for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (cv$probabilitySample81Value13 * (((0.0 <= index$var73) && (index$var73 < var72.length))?var72[index$var73]:0.0)));
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
				double cv$var73$sum = 0.0;
				for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
					if(!fixedFlag$sample81)
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample81[index$var73]);
				}
				for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
					if(!fixedFlag$sample81)
						cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] / cv$var73$sum);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample61)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample81)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var24 = 0; var24 < noStates; var24 += 1) {
				if(!fixedFlag$sample26)
					sample26(var24);
			}
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				if(!fixedFlag$sample34)
					sample34(var31);
			}
			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample53)
				sample53();
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				if(!fixedFlag$sample61)
					sample61(i$var50);
			}
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					if(!fixedFlag$sample81)
						sample81(i$var60, j$var66);
				}
			}
		} else {
			for(int i$var60 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var60 >= ((0 - 1) + 1); i$var60 -= 1) {
				for(int j$var66 = (length$eventsMeasured[i$var60] - ((((length$eventsMeasured[i$var60] - 1) - 1) % 1) + 1)); j$var66 >= ((1 - 1) + 1); j$var66 -= 1) {
					if(!fixedFlag$sample81)
						sample81(i$var60, j$var66);
				}
			}
			for(int i$var50 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var50 >= ((0 - 1) + 1); i$var50 -= 1) {
				if(!fixedFlag$sample61)
					sample61(i$var50);
			}
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample51)
				sample51();
			for(int var31 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var31 >= ((0 - 1) + 1); var31 -= 1) {
				if(!fixedFlag$sample34)
					sample34(var31);
			}
			for(int var24 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var24 >= ((0 - 1) + 1); var24 -= 1) {
				if(!fixedFlag$sample26)
					sample26(var24);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		for(int var11 = 0; var11 < noStates; var11 += 1)
			v[var11] = 0.1;
		for(int var17 = 0; var17 < noEvents; var17 += 1)
			v2[var17] = 0.1;
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var27 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var32 = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$weights = 0.0;
		logProbability$var46 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$initialState = 0.0;
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
			logProbability$var54[((i$var50 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				logProbability$sample61[((i$var50 - 0) / 1)] = 0.0;
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
				logProbability$var73[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					logProbability$sample81[((i$var60 - 0) / 1)][((j$var66 - 1) / 1)] = 0.0;
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
				logProbability$var93[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
					logProbability$sample103[((i$var80 - 0) / 1)][((j$var88 - 1) / 1)] = 0.0;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityDistribution$sample61();
		logProbabilityDistribution$sample81();
		logProbabilityDistribution$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample81();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var24 = 0; var24 < noStates; var24 += 1) {
			double[] var25 = m[var24];
			if(!fixedFlag$sample26)
				DistributionSampling.sampleDirichlet(RNG$, v, var25);
		}
		for(int var31 = 0; var31 < noStates; var31 += 1) {
			double[] var32 = bias[var31];
			if(!fixedFlag$sample34)
				DistributionSampling.sampleDirichlet(RNG$, v2, var32);
		}
		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
			int[] var51 = st[i$var50];
			if(!fixedFlag$sample61)
				var51[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState]);
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			int[] var67 = st[i$var60];
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
				if(!fixedFlag$sample81)
					var67[j$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var60][(j$var66 - 1)]]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sampleDistribution();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
	}
}