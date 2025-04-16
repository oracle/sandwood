package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics2$CoreInterface {
	private double[][] cv$distributionAccumulator$var120;
	private double[][] cv$var102$stateProbabilityGlobal;
	private double[][] cv$var121$stateProbabilityGlobal;
	private double[] cv$var19$countGlobal;
	private double[][] cv$var32$countGlobal;
	private double[][] distribution$sample104;
	private double[][][] distribution$sample123;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample157 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample145 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean[][][] guard$sample104gaussian156$global;
	private boolean[][][] guard$sample123gaussian156$global;
	private double[] initialStateDistribution;
	private int[] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_1d;
	private double logProbability$metric_g;
	private double logProbability$metric_mean;
	private double logProbability$metric_valid_1d;
	private double logProbability$metric_valid_bias;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_var;
	private double[] logProbability$sample104;
	private double[][] logProbability$sample123;
	private double[][] logProbability$sample145;
	private double[][] logProbability$sample157;
	private double logProbability$st;
	private double[] logProbability$var101;
	private double[][] logProbability$var120;
	private double[][] logProbability$var140;
	private double[][] logProbability$var150;
	private double logProbability$var18;
	private double logProbability$var20;
	private double logProbability$var32;
	private double logProbability$var39;
	private double logProbability$var51;
	private double logProbability$var55;
	private double logProbability$var67;
	private double logProbability$var71;
	private double logProbability$var83;
	private double[][] m;
	private double[][] metric;
	private double[][] metric_g;
	private double[] metric_mean;
	private boolean[][] metric_valid;
	private double[] metric_valid_bias;
	private boolean[][] metric_valid_g;
	private double[] metric_var;
	private int noSamples;
	private int noStates;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$distribution$sample104() {
		return distribution$sample104;
	}

	@Override
	public final void set$distribution$sample104(double[][] cv$value) {
		distribution$sample104 = cv$value;
	}

	@Override
	public final double[][][] get$distribution$sample123() {
		return distribution$sample123;
	}

	@Override
	public final void set$distribution$sample123(double[][][] cv$value) {
		distribution$sample123 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		fixedFlag$sample104 = cv$value;
		fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedProbFlag$sample104);
		fixedProbFlag$sample123 = (fixedFlag$sample104 && fixedProbFlag$sample123);
		fixedProbFlag$sample145 = (fixedFlag$sample104 && fixedProbFlag$sample145);
		fixedProbFlag$sample157 = (fixedFlag$sample104 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (fixedFlag$sample123 && fixedProbFlag$sample123);
		fixedProbFlag$sample145 = (fixedFlag$sample123 && fixedProbFlag$sample145);
		fixedProbFlag$sample157 = (fixedFlag$sample123 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample157() {
		return fixedFlag$sample157;
	}

	@Override
	public final void set$fixedFlag$sample157(boolean cv$value) {
		fixedFlag$sample157 = cv$value;
		fixedProbFlag$sample157 = (fixedFlag$sample157 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (fixedFlag$sample19 && fixedProbFlag$sample19);
		fixedProbFlag$sample104 = (fixedFlag$sample19 && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample123 = (fixedFlag$sample32 && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedProbFlag$sample52);
		fixedProbFlag$sample157 = (fixedFlag$sample52 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (fixedFlag$sample68 && fixedProbFlag$sample68);
		fixedProbFlag$sample157 = (fixedFlag$sample68 && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		fixedFlag$sample84 = cv$value;
		fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
		fixedProbFlag$sample145 = (fixedFlag$sample84 && fixedProbFlag$sample145);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		fixedProbFlag$sample19 = false;
		fixedProbFlag$sample104 = false;
	}

	@Override
	public final int[] get$length$metric() {
		return length$metric;
	}

	@Override
	public final void set$length$metric(int[] cv$value) {
		length$metric = cv$value;
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
	public final double get$logProbability$initialStateDistribution() {
		return logProbability$initialStateDistribution;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$metric_g() {
		return logProbability$metric_g;
	}

	@Override
	public final double get$logProbability$metric_mean() {
		return logProbability$metric_mean;
	}

	@Override
	public final double get$logProbability$metric_valid_bias() {
		return logProbability$metric_valid_bias;
	}

	@Override
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
	}

	@Override
	public final double get$logProbability$metric_var() {
		return logProbability$metric_var;
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
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample123 = false;
	}

	@Override
	public final double[][] get$metric() {
		return metric;
	}

	@Override
	public final void set$metric(double[][] cv$value) {
		metric = cv$value;
	}

	@Override
	public final double[][] get$metric_g() {
		return metric_g;
	}

	@Override
	public final double[] get$metric_mean() {
		return metric_mean;
	}

	@Override
	public final void set$metric_mean(double[] cv$value) {
		metric_mean = cv$value;
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final boolean[][] get$metric_valid() {
		return metric_valid;
	}

	@Override
	public final void set$metric_valid(boolean[][] cv$value) {
		metric_valid = cv$value;
	}

	@Override
	public final double[] get$metric_valid_bias() {
		return metric_valid_bias;
	}

	@Override
	public final void set$metric_valid_bias(double[] cv$value) {
		metric_valid_bias = cv$value;
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample145 = false;
	}

	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	@Override
	public final double[] get$metric_var() {
		return metric_var;
	}

	@Override
	public final void set$metric_var(double[] cv$value) {
		metric_var = cv$value;
		fixedProbFlag$sample68 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final int get$noSamples() {
		return noSamples;
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
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value) {
		st = cv$value;
		fixedProbFlag$sample104 = false;
		fixedProbFlag$sample123 = false;
		fixedProbFlag$sample145 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample104() {
		if(!fixedProbFlag$sample104) {
			if(fixedFlag$sample104) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample;
					{
						int cv$sampleValue = st[sample][0];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var101[((sample - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample104[((sample - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample104)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample104)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedFlag$sample19);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample104[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[((sample - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample104)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample123() {
		if(!fixedProbFlag$sample123) {
			if(fixedFlag$sample123) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var113;
						int index$sample$2 = sample;
						{
							int cv$sampleValue = st[sample][timeStep$var113];
							if(fixedFlag$sample104) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample)) {
										if((0 == (timeStep$var113 - 1))) {
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == st[sample][(timeStep$var113 - 1)])) {
													{
														double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
									if(true) {
										for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
											int distributionTempVariable$var102$8 = index$sample104$6;
											double cv$probabilitySample104Value7 = (1.0 * distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
											if((index$sample$5 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													for(int var31 = 0; var31 < noStates; var31 += 1) {
														if((var31 == st[sample][(timeStep$var113 - 1)])) {
															{
																double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if((index$sample$2 == sample)) {
								if((index$timeStep$1 == (timeStep$var113 - 1))) {
									for(int var31 = 0; var31 < noStates; var31 += 1) {
										if((var31 == st[sample][(timeStep$var113 - 1)])) {
											{
												double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample123) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var113 - 1))) {
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == st[sample][(timeStep$var113 - 1)])) {
														{
															double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
										if(!((index$sample$14 == index$sample$2) && (index$timeStep$15 == index$timeStep$1))) {
											for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
												int distributionTempVariable$var121$18 = index$sample123$16;
												double cv$probabilitySample123Value17 = (1.0 * distribution$sample123[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample123$16]);
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var113 - 1))) {
														for(int var31 = 0; var31 < noStates; var31 += 1) {
															if((var31 == st[sample][(timeStep$var113 - 1)])) {
																{
																	double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample123Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value17);
																}
															}
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
						logProbability$var120[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample123)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample123)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample123 = ((fixedFlag$sample123 && fixedFlag$sample32) && fixedFlag$sample104);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var120[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample123)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample145() {
		if(!fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var136];
						if(fixedFlag$sample104) {
							for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
								if((index$sample$2_1 == sample)) {
									if((0 == timeStep$var136)) {
										for(int var82 = 0; var82 < noStates; var82 += 1) {
											if((var82 == st[sample][timeStep$var136])) {
												{
													double var139 = metric_valid_bias[st[sample][timeStep$var136]];
													double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
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
							for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
								if(true) {
									for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
										int distributionTempVariable$var102$6 = index$sample104$4;
										double cv$probabilitySample104Value5 = (1.0 * distribution$sample104[((index$sample$3 - 0) / 1)][index$sample104$4]);
										if((index$sample$3 == sample)) {
											if((0 == timeStep$var136)) {
												for(int var82 = 0; var82 < noStates; var82 += 1) {
													if((var82 == st[sample][timeStep$var136])) {
														{
															double var139 = metric_valid_bias[st[sample][timeStep$var136]];
															double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample123) {
							for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$10_1]; timeStep$var113 += 1) {
									if((index$sample$10_1 == sample)) {
										if((timeStep$var113 == timeStep$var136)) {
											for(int var82 = 0; var82 < noStates; var82 += 1) {
												if((var82 == st[sample][timeStep$var136])) {
													{
														double var139 = metric_valid_bias[st[sample][timeStep$var136]];
														double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
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
							for(int index$sample$11 = 0; index$sample$11 < noSamples; index$sample$11 += 1) {
								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$11]; timeStep$var113 += 1) {
									if(true) {
										for(int index$sample123$13 = 0; index$sample123$13 < noStates; index$sample123$13 += 1) {
											int distributionTempVariable$var121$15 = index$sample123$13;
											double cv$probabilitySample123Value14 = (1.0 * distribution$sample123[((index$sample$11 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$13]);
											if((index$sample$11 == sample)) {
												if((timeStep$var113 == timeStep$var136)) {
													for(int var82 = 0; var82 < noStates; var82 += 1) {
														if((var82 == st[sample][timeStep$var136])) {
															{
																double var139 = metric_valid_bias[st[sample][timeStep$var136]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample123Value14) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value14);
															}
														}
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
					logProbability$var140[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var140[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							if(metric_valid_g[sample][timeStep$var136]) {
								double cv$sampleValue = metric_g[sample][timeStep$var136];
								if(fixedFlag$sample104) {
									for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
										if((index$sample$2_1 == sample)) {
											if((0 == timeStep$var136)) {
												if(metric_valid_g[sample][timeStep$var136]) {
													for(int var50 = 0; var50 < noStates; var50 += 1) {
														if(metric_valid_g[sample][timeStep$var136]) {
															if((var50 == st[sample][timeStep$var136])) {
																for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																	if((index$sample$10_1 == sample)) {
																		if((0 == timeStep$var136)) {
																			if(metric_valid_g[sample][timeStep$var136]) {
																				for(int var66 = 0; var66 < noStates; var66 += 1) {
																					if(metric_valid_g[sample][timeStep$var136]) {
																						if((var66 == st[sample][timeStep$var136])) {
																							{
																								double var148 = metric_mean[st[sample][timeStep$var136]];
																								double var149 = metric_var[st[sample][timeStep$var136]];
																								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
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
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
										if(true) {
											for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
												int distributionTempVariable$var102$6 = index$sample104$4;
												double cv$probabilitySample104Value5 = (1.0 * distribution$sample104[((index$sample$3 - 0) / 1)][index$sample104$4]);
												if((index$sample$3 == sample)) {
													if((0 == timeStep$var136)) {
														if(metric_valid_g[sample][timeStep$var136]) {
															for(int var50 = 0; var50 < noStates; var50 += 1) {
																if(metric_valid_g[sample][timeStep$var136]) {
																	if((var50 == st[sample][timeStep$var136])) {
																		if((index$sample$3 == sample)) {
																			if((0 == timeStep$var136)) {
																				if(metric_valid_g[sample][timeStep$var136]) {
																					for(int var66 = 0; var66 < noStates; var66 += 1) {
																						if(metric_valid_g[sample][timeStep$var136]) {
																							if((var66 == st[sample][timeStep$var136])) {
																								{
																									double var148 = metric_mean[st[sample][timeStep$var136]];
																									double var149 = metric_var[st[sample][timeStep$var136]];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																			if(!(index$sample$12 == index$sample$3)) {
																				for(int index$sample104$13 = 0; index$sample104$13 < noStates; index$sample104$13 += 1) {
																					int distributionTempVariable$var102$15 = index$sample104$13;
																					double cv$probabilitySample104Value14 = (cv$probabilitySample104Value5 * distribution$sample104[((index$sample$12 - 0) / 1)][index$sample104$13]);
																					if((index$sample$12 == sample)) {
																						if((0 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var66 = 0; var66 < noStates; var66 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var66 == st[sample][timeStep$var136])) {
																											{
																												double var148 = metric_mean[st[sample][timeStep$var136]];
																												double var149 = metric_var[st[sample][timeStep$var136]];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample104Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value14);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample104) {
									for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
										if((index$sample$20_1 == sample)) {
											if((0 == timeStep$var136)) {
												if(metric_valid_g[sample][timeStep$var136]) {
													for(int var50 = 0; var50 < noStates; var50 += 1) {
														if(metric_valid_g[sample][timeStep$var136]) {
															if((var50 == st[sample][timeStep$var136])) {
																if(fixedFlag$sample123) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$28_1]; timeStep$var113 += 1) {
																			if((index$sample$28_1 == sample)) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[sample][timeStep$var136]) {
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
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
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29]; timeStep$var113 += 1) {
																			if(true) {
																				for(int index$sample123$31 = 0; index$sample123$31 < noStates; index$sample123$31 += 1) {
																					int distributionTempVariable$var121$33 = index$sample123$31;
																					double cv$probabilitySample123Value32 = (1.0 * distribution$sample123[((index$sample$29 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$31]);
																					if((index$sample$29 == sample)) {
																						if((timeStep$var113 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var66 = 0; var66 < noStates; var66 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var66 == st[sample][timeStep$var136])) {
																											{
																												double var148 = metric_mean[st[sample][timeStep$var136]];
																												double var149 = metric_var[st[sample][timeStep$var136]];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample123Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value32);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
										if(true) {
											for(int index$sample104$22 = 0; index$sample104$22 < noStates; index$sample104$22 += 1) {
												int distributionTempVariable$var102$24 = index$sample104$22;
												double cv$probabilitySample104Value23 = (1.0 * distribution$sample104[((index$sample$21 - 0) / 1)][index$sample104$22]);
												if((index$sample$21 == sample)) {
													if((0 == timeStep$var136)) {
														if(metric_valid_g[sample][timeStep$var136]) {
															for(int var50 = 0; var50 < noStates; var50 += 1) {
																if(metric_valid_g[sample][timeStep$var136]) {
																	if((var50 == st[sample][timeStep$var136])) {
																		if(fixedFlag$sample123) {
																			for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$35_1]; timeStep$var113 += 1) {
																					if((index$sample$35_1 == sample)) {
																						if((timeStep$var113 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var66 = 0; var66 < noStates; var66 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var66 == st[sample][timeStep$var136])) {
																											{
																												double var148 = metric_mean[st[sample][timeStep$var136]];
																												double var149 = metric_var[st[sample][timeStep$var136]];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample104Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value23);
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
																			for(int index$sample$36 = 0; index$sample$36 < noSamples; index$sample$36 += 1) {
																				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$36]; timeStep$var113 += 1) {
																					if(true) {
																						for(int index$sample123$38 = 0; index$sample123$38 < noStates; index$sample123$38 += 1) {
																							int distributionTempVariable$var121$40 = index$sample123$38;
																							double cv$probabilitySample123Value39 = (cv$probabilitySample104Value23 * distribution$sample123[((index$sample$36 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$38]);
																							if((index$sample$36 == sample)) {
																								if((timeStep$var113 == timeStep$var136)) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										for(int var66 = 0; var66 < noStates; var66 += 1) {
																											if(metric_valid_g[sample][timeStep$var136]) {
																												if((var66 == st[sample][timeStep$var136])) {
																													{
																														double var148 = metric_mean[st[sample][timeStep$var136]];
																														double var149 = metric_var[st[sample][timeStep$var136]];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample123Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value39);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample123) {
									for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$46_1]; timeStep$var113 += 1) {
											if((index$sample$46_1 == sample)) {
												if((timeStep$var113 == timeStep$var136)) {
													if(metric_valid_g[sample][timeStep$var136]) {
														for(int var50 = 0; var50 < noStates; var50 += 1) {
															if(metric_valid_g[sample][timeStep$var136]) {
																if((var50 == st[sample][timeStep$var136])) {
																	for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																		for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1]; index$timeStep$55_2 += 1) {
																			if((index$sample$55_1 == sample)) {
																				if((index$timeStep$55_2 == timeStep$var136)) {
																					if(metric_valid_g[sample][timeStep$var136]) {
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
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
																}
															}
														}
													}
												}
											}
										}
									}
								} else {
									for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
													int distributionTempVariable$var121$51 = index$sample123$49;
													double cv$probabilitySample123Value50 = (1.0 * distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
													if((index$sample$47 == sample)) {
														if((timeStep$var113 == timeStep$var136)) {
															if(metric_valid_g[sample][timeStep$var136]) {
																for(int var50 = 0; var50 < noStates; var50 += 1) {
																	if(metric_valid_g[sample][timeStep$var136]) {
																		if((var50 == st[sample][timeStep$var136])) {
																			if((index$sample$47 == sample)) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[sample][timeStep$var136]) {
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																										if((cv$weightedProbability < cv$distributionAccumulator))
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																										else {
																											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																												cv$distributionAccumulator = cv$weightedProbability;
																											else
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																										}
																										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value50);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																				for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57]; index$timeStep$58 += 1) {
																					if(!((index$sample$57 == index$sample$47) && (index$timeStep$58 == timeStep$var113))) {
																						for(int index$sample123$59 = 0; index$sample123$59 < noStates; index$sample123$59 += 1) {
																							int distributionTempVariable$var121$61 = index$sample123$59;
																							double cv$probabilitySample123Value60 = (cv$probabilitySample123Value50 * distribution$sample123[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample123$59]);
																							if((index$sample$57 == sample)) {
																								if((index$timeStep$58 == timeStep$var136)) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										for(int var66 = 0; var66 < noStates; var66 += 1) {
																											if(metric_valid_g[sample][timeStep$var136]) {
																												if((var66 == st[sample][timeStep$var136])) {
																													{
																														double var148 = metric_mean[st[sample][timeStep$var136]];
																														double var149 = metric_var[st[sample][timeStep$var136]];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample123Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value60);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample123) {
									for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$66_1]; timeStep$var113 += 1) {
											if((index$sample$66_1 == sample)) {
												if((timeStep$var113 == timeStep$var136)) {
													if(metric_valid_g[sample][timeStep$var136]) {
														for(int var50 = 0; var50 < noStates; var50 += 1) {
															if(metric_valid_g[sample][timeStep$var136]) {
																if((var50 == st[sample][timeStep$var136])) {
																	if(fixedFlag$sample104) {
																		for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																			if((index$sample$75_1 == sample)) {
																				if((0 == timeStep$var136)) {
																					if(metric_valid_g[sample][timeStep$var136]) {
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								if((var66 == st[sample][timeStep$var136])) {
																									{
																										double var148 = metric_mean[st[sample][timeStep$var136]];
																										double var149 = metric_var[st[sample][timeStep$var136]];
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
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
																		for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																			if(true) {
																				for(int index$sample104$77 = 0; index$sample104$77 < noStates; index$sample104$77 += 1) {
																					int distributionTempVariable$var102$79 = index$sample104$77;
																					double cv$probabilitySample104Value78 = (1.0 * distribution$sample104[((index$sample$76 - 0) / 1)][index$sample104$77]);
																					if((index$sample$76 == sample)) {
																						if((0 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var66 = 0; var66 < noStates; var66 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var66 == st[sample][timeStep$var136])) {
																											{
																												double var148 = metric_mean[st[sample][timeStep$var136]];
																												double var149 = metric_var[st[sample][timeStep$var136]];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample104Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value78);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$67 = 0; index$sample$67 < noSamples; index$sample$67 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$67]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$69 = 0; index$sample123$69 < noStates; index$sample123$69 += 1) {
													int distributionTempVariable$var121$71 = index$sample123$69;
													double cv$probabilitySample123Value70 = (1.0 * distribution$sample123[((index$sample$67 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$69]);
													if((index$sample$67 == sample)) {
														if((timeStep$var113 == timeStep$var136)) {
															if(metric_valid_g[sample][timeStep$var136]) {
																for(int var50 = 0; var50 < noStates; var50 += 1) {
																	if(metric_valid_g[sample][timeStep$var136]) {
																		if((var50 == st[sample][timeStep$var136])) {
																			if(fixedFlag$sample104) {
																				for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																					if((index$sample$81_1 == sample)) {
																						if((0 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var66 = 0; var66 < noStates; var66 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var66 == st[sample][timeStep$var136])) {
																											{
																												double var148 = metric_mean[st[sample][timeStep$var136]];
																												double var149 = metric_var[st[sample][timeStep$var136]];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample123Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value70);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$82 = 0; index$sample$82 < noSamples; index$sample$82 += 1) {
																					if(true) {
																						for(int index$sample104$83 = 0; index$sample104$83 < noStates; index$sample104$83 += 1) {
																							int distributionTempVariable$var102$85 = index$sample104$83;
																							double cv$probabilitySample104Value84 = (cv$probabilitySample123Value70 * distribution$sample104[((index$sample$82 - 0) / 1)][index$sample104$83]);
																							if((index$sample$82 == sample)) {
																								if((0 == timeStep$var136)) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										for(int var66 = 0; var66 < noStates; var66 += 1) {
																											if(metric_valid_g[sample][timeStep$var136]) {
																												if((var66 == st[sample][timeStep$var136])) {
																													{
																														double var148 = metric_mean[st[sample][timeStep$var136]];
																														double var149 = metric_var[st[sample][timeStep$var136]];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample104Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value84);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
						logProbability$var150[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = ((((fixedFlag$sample157 && fixedFlag$sample52) && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var150[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample;
				{
					int cv$sampleValue = st[sample][0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var101[((sample - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample104[((sample - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample104[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var101[((sample - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var113;
					int index$sample$2 = sample;
					{
						int cv$sampleValue = st[sample][timeStep$var113];
						{
							{
								double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var120[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = ((fixedFlag$sample123 && fixedFlag$sample32) && fixedFlag$sample104);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var120[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample145() {
		if(!fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var136];
						{
							{
								double var139 = metric_valid_bias[st[sample][timeStep$var136]];
								double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var139:(1.0 - var139))));
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
					logProbability$var140[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var140[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							if(metric_valid_g[sample][timeStep$var136]) {
								double cv$sampleValue = metric_g[sample][timeStep$var136];
								{
									{
										double var148 = metric_mean[st[sample][timeStep$var136]];
										double var149 = metric_var[st[sample][timeStep$var136]];
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var148) / Math.sqrt(var149))) - (0.5 * Math.log(var149))));
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
						logProbability$var150[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = ((((fixedFlag$sample157 && fixedFlag$sample52) && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var150[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = initialStateDistribution;
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
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noStates; var31 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var31];
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var32;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < noStates; var50 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_mean[var50];
					{
						{
							double var37 = 0.0;
							double var38 = 100.0;
							double cv$weightedProbability = (Math.log(1.0) + (((var37 <= cv$sampleValue) && (cv$sampleValue < var38))?(-Math.log((var38 - var37))):Double.NEGATIVE_INFINITY));
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
			logProbability$var39 = cv$sampleAccumulator;
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var51;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var39 = cv$rvAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var66 = 0; var66 < noStates; var66 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_var[var66];
					{
						{
							double var53 = 1.0;
							double var54 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var53, var54));
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
			logProbability$var55 = cv$sampleAccumulator;
			logProbability$var67 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var67;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var55 = cv$rvAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noStates; var82 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_valid_bias[var82];
					{
						{
							double var69 = 1.0;
							double var70 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var69, var70));
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
			logProbability$var71 = cv$sampleAccumulator;
			logProbability$var83 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample84 = fixedFlag$sample84;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var83;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var71 = cv$rvAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample104(int sample, int threadID$cv$sample, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			int index$sample$1 = sample;
			{
				cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var102$stateProbabilityGlobal[threadID$cv$sample];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				int index$sample$2 = sample;
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$initialStateDistribution;
					{
						cv$temp$0$initialStateDistribution = initialStateDistribution;
					}
					int cv$temp$1$$var2609;
					{
						int $var2609 = noStates;
						cv$temp$1$$var2609 = $var2609;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var2609))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$var118$3_1 = cv$currentValue;
							for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
								if((sample == index$sample$3_2)) {
									for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$3_2]; timeStep$var113 += 1) {
										if((0 == (timeStep$var113 - 1))) {
											if(fixedFlag$sample123) {
												{
													int index$timeStep$5 = timeStep$var113;
													int index$sample$6 = index$sample$3_2;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var31 = 0; var31 < noStates; var31 += 1) {
															if((var31 == st[index$sample$3_2][(timeStep$var113 - 1)])) {
																{
																	{
																		double[] cv$temp$2$var119;
																		{
																			double[] var119 = m[traceTempVariable$var118$3_1];
																			cv$temp$2$var119 = var119;
																		}
																		int cv$temp$3$$var2622;
																		{
																			int $var2622 = noStates;
																			cv$temp$3$$var2622 = $var2622;
																		}
																		if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var113]) && (st[index$sample$3_2][timeStep$var113] < cv$temp$3$$var2622))?Math.log(cv$temp$2$var119[st[index$sample$3_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var113]) && (st[index$sample$3_2][timeStep$var113] < cv$temp$3$$var2622))?Math.log(cv$temp$2$var119[st[index$sample$3_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var113]) && (st[index$sample$3_2][timeStep$var113] < cv$temp$3$$var2622))?Math.log(cv$temp$2$var119[st[index$sample$3_2][timeStep$var113]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var113]) && (st[index$sample$3_2][timeStep$var113] < cv$temp$3$$var2622))?Math.log(cv$temp$2$var119[st[index$sample$3_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var113]) && (st[index$sample$3_2][timeStep$var113] < cv$temp$3$$var2622))?Math.log(cv$temp$2$var119[st[index$sample$3_2][timeStep$var113]]):Double.NEGATIVE_INFINITY)));
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
							int traceTempVariable$currentState$9_1 = cv$currentValue;
							for(int index$sample$9_2 = 0; index$sample$9_2 < noSamples; index$sample$9_2 += 1) {
								if((sample == index$sample$9_2)) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$9_2]; timeStep$var136 += 1) {
										if((0 == timeStep$var136)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var82 = 0; var82 < noStates; var82 += 1) {
														if((var82 == st[index$sample$9_2][timeStep$var136])) {
															{
																{
																	double cv$temp$4$var139;
																	{
																		double var139 = metric_valid_bias[traceTempVariable$currentState$9_1];
																		cv$temp$4$var139 = var139;
																	}
																	if(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$9_2][timeStep$var136]?cv$temp$4$var139:(1.0 - cv$temp$4$var139)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$9_2][timeStep$var136]?cv$temp$4$var139:(1.0 - cv$temp$4$var139)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[index$sample$9_2][timeStep$var136]?cv$temp$4$var139:(1.0 - cv$temp$4$var139))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[index$sample$9_2][timeStep$var136]?cv$temp$4$var139:(1.0 - cv$temp$4$var139)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[index$sample$9_2][timeStep$var136]?cv$temp$4$var139:(1.0 - cv$temp$4$var139)))));
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
					{
						{
							boolean[][] guard$sample104gaussian156 = guard$sample104gaussian156$global[threadID$cv$sample];
							for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
								if((sample == index$sample$13_1)) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$13_1]; timeStep$var136 += 1) {
										if((0 == timeStep$var136)) {
											if(metric_valid_g[index$sample$13_1][timeStep$var136])
												guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
										}
									}
								}
							}
							for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
								if((sample == index$sample$14_1)) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$14_1]; timeStep$var136 += 1) {
										if((0 == timeStep$var136)) {
											if(metric_valid_g[index$sample$14_1][timeStep$var136])
												guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
										}
									}
								}
							}
							int traceTempVariable$currentState$15_1 = cv$currentValue;
							for(int index$sample$15_2 = 0; index$sample$15_2 < noSamples; index$sample$15_2 += 1) {
								if((sample == index$sample$15_2)) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$15_2]; timeStep$var136 += 1) {
										if((0 == timeStep$var136)) {
											if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
												if(!guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
													guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
													{
														if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var50 = 0; var50 < noStates; var50 += 1) {
																	if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																		if((var50 == st[index$sample$15_2][timeStep$var136])) {
																			int traceTempVariable$currentState$20_1 = cv$currentValue;
																			if((index$sample$2 == index$sample$15_2)) {
																				if((0 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																								if((var66 == st[index$sample$15_2][timeStep$var136])) {
																									{
																										{
																											if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																												double cv$temp$5$var148;
																												{
																													double var148 = metric_mean[traceTempVariable$currentState$20_1];
																													cv$temp$5$var148 = var148;
																												}
																												double cv$temp$6$var149;
																												{
																													double var149 = metric_var[traceTempVariable$currentState$20_1];
																													cv$temp$6$var149 = var149;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$5$var148) / Math.sqrt(cv$temp$6$var149))) - (0.5 * Math.log(cv$temp$6$var149)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$5$var148) / Math.sqrt(cv$temp$6$var149))) - (0.5 * Math.log(cv$temp$6$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$5$var148) / Math.sqrt(cv$temp$6$var149))) - (0.5 * Math.log(cv$temp$6$var149))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$5$var148) / Math.sqrt(cv$temp$6$var149))) - (0.5 * Math.log(cv$temp$6$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$5$var148) / Math.sqrt(cv$temp$6$var149))) - (0.5 * Math.log(cv$temp$6$var149)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
																				if(!(index$sample$21 == index$sample$2)) {
																					for(int index$sample104$22 = 0; index$sample104$22 < noStates; index$sample104$22 += 1) {
																						int distributionTempVariable$var102$24 = index$sample104$22;
																						double cv$probabilitySample104Value23 = (1.0 * distribution$sample104[((index$sample$21 - 0) / 1)][index$sample104$22]);
																						int traceTempVariable$currentState$25_1 = cv$currentValue;
																						if((index$sample$21 == index$sample$15_2)) {
																							if((0 == timeStep$var136)) {
																								if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																											if((var66 == st[index$sample$15_2][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																															double cv$temp$7$var148;
																															{
																																double var148 = metric_mean[traceTempVariable$currentState$25_1];
																																cv$temp$7$var148 = var148;
																															}
																															double cv$temp$8$var149;
																															{
																																double var149 = metric_var[traceTempVariable$currentState$25_1];
																																cv$temp$8$var149 = var149;
																															}
																															if(((Math.log(cv$probabilitySample104Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$7$var148) / Math.sqrt(cv$temp$8$var149))) - (0.5 * Math.log(cv$temp$8$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$7$var148) / Math.sqrt(cv$temp$8$var149))) - (0.5 * Math.log(cv$temp$8$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$7$var148) / Math.sqrt(cv$temp$8$var149))) - (0.5 * Math.log(cv$temp$8$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$7$var148) / Math.sqrt(cv$temp$8$var149))) - (0.5 * Math.log(cv$temp$8$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$7$var148) / Math.sqrt(cv$temp$8$var149))) - (0.5 * Math.log(cv$temp$8$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value23);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var50 = 0; var50 < noStates; var50 += 1) {
																	if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																		if((var50 == st[index$sample$15_2][timeStep$var136])) {
																			if(fixedFlag$sample123) {
																				for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																						if((index$sample$29_1 == index$sample$15_2)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																											if((var66 == st[index$sample$15_2][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																															double cv$temp$9$var148;
																															{
																																double var148 = metric_mean[traceTempVariable$currentState$15_1];
																																cv$temp$9$var148 = var148;
																															}
																															double cv$temp$10$var149;
																															{
																																double var149 = metric_var[traceTempVariable$currentState$15_1];
																																cv$temp$10$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$9$var148) / Math.sqrt(cv$temp$10$var149))) - (0.5 * Math.log(cv$temp$10$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$9$var148) / Math.sqrt(cv$temp$10$var149))) - (0.5 * Math.log(cv$temp$10$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$9$var148) / Math.sqrt(cv$temp$10$var149))) - (0.5 * Math.log(cv$temp$10$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$9$var148) / Math.sqrt(cv$temp$10$var149))) - (0.5 * Math.log(cv$temp$10$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$9$var148) / Math.sqrt(cv$temp$10$var149))) - (0.5 * Math.log(cv$temp$10$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
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
																				for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$30]; timeStep$var113 += 1) {
																						if(true) {
																							for(int index$sample123$32 = 0; index$sample123$32 < noStates; index$sample123$32 += 1) {
																								int distributionTempVariable$var121$34 = index$sample123$32;
																								double cv$probabilitySample123Value33 = (1.0 * distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																								if((index$sample$30 == index$sample$15_2)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																													if((var66 == st[index$sample$15_2][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[index$sample$15_2][timeStep$var136]) {
																																	double cv$temp$11$var148;
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$15_1];
																																		cv$temp$11$var148 = var148;
																																	}
																																	double cv$temp$12$var149;
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$15_1];
																																		cv$temp$12$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$11$var148) / Math.sqrt(cv$temp$12$var149))) - (0.5 * Math.log(cv$temp$12$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$11$var148) / Math.sqrt(cv$temp$12$var149))) - (0.5 * Math.log(cv$temp$12$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$11$var148) / Math.sqrt(cv$temp$12$var149))) - (0.5 * Math.log(cv$temp$12$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$11$var148) / Math.sqrt(cv$temp$12$var149))) - (0.5 * Math.log(cv$temp$12$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var136] - cv$temp$11$var148) / Math.sqrt(cv$temp$12$var149))) - (0.5 * Math.log(cv$temp$12$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value33);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
							int traceTempVariable$currentState$16_1 = cv$currentValue;
							for(int index$sample$16_2 = 0; index$sample$16_2 < noSamples; index$sample$16_2 += 1) {
								if((sample == index$sample$16_2)) {
									for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$16_2]; timeStep$var136 += 1) {
										if((0 == timeStep$var136)) {
											if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
												if(!guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
													guard$sample104gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
													{
														if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$currentState$38_1 = cv$currentValue;
																if((index$sample$2 == index$sample$16_2)) {
																	if((0 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																				if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																					if((var50 == st[index$sample$16_2][timeStep$var136])) {
																						for(int var66 = 0; var66 < noStates; var66 += 1) {
																							if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																								if((var66 == st[index$sample$16_2][timeStep$var136])) {
																									{
																										{
																											if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																												double cv$temp$13$var148;
																												{
																													double var148 = metric_mean[traceTempVariable$currentState$38_1];
																													cv$temp$13$var148 = var148;
																												}
																												double cv$temp$14$var149;
																												{
																													double var149 = metric_var[traceTempVariable$currentState$38_1];
																													cv$temp$14$var149 = var149;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$13$var148) / Math.sqrt(cv$temp$14$var149))) - (0.5 * Math.log(cv$temp$14$var149)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$13$var148) / Math.sqrt(cv$temp$14$var149))) - (0.5 * Math.log(cv$temp$14$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$13$var148) / Math.sqrt(cv$temp$14$var149))) - (0.5 * Math.log(cv$temp$14$var149))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$13$var148) / Math.sqrt(cv$temp$14$var149))) - (0.5 * Math.log(cv$temp$14$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$13$var148) / Math.sqrt(cv$temp$14$var149))) - (0.5 * Math.log(cv$temp$14$var149)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																	if(!(index$sample$39 == index$sample$2)) {
																		for(int index$sample104$40 = 0; index$sample104$40 < noStates; index$sample104$40 += 1) {
																			int distributionTempVariable$var102$42 = index$sample104$40;
																			double cv$probabilitySample104Value41 = (1.0 * distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																			int traceTempVariable$currentState$43_1 = cv$currentValue;
																			if((index$sample$39 == index$sample$16_2)) {
																				if((0 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																						for(int var50 = 0; var50 < noStates; var50 += 1) {
																							if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																								if((var50 == st[index$sample$16_2][timeStep$var136])) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																											if((var66 == st[index$sample$16_2][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																															double cv$temp$15$var148;
																															{
																																double var148 = metric_mean[traceTempVariable$currentState$43_1];
																																cv$temp$15$var148 = var148;
																															}
																															double cv$temp$16$var149;
																															{
																																double var149 = metric_var[traceTempVariable$currentState$43_1];
																																cv$temp$16$var149 = var149;
																															}
																															if(((Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$15$var148) / Math.sqrt(cv$temp$16$var149))) - (0.5 * Math.log(cv$temp$16$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$15$var148) / Math.sqrt(cv$temp$16$var149))) - (0.5 * Math.log(cv$temp$16$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$15$var148) / Math.sqrt(cv$temp$16$var149))) - (0.5 * Math.log(cv$temp$16$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$15$var148) / Math.sqrt(cv$temp$16$var149))) - (0.5 * Math.log(cv$temp$16$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$15$var148) / Math.sqrt(cv$temp$16$var149))) - (0.5 * Math.log(cv$temp$16$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value41);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(fixedFlag$sample123) {
																	for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																		for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$48_1]; timeStep$var113 += 1) {
																			if((index$sample$48_1 == index$sample$16_2)) {
																				if((timeStep$var113 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																						for(int var50 = 0; var50 < noStates; var50 += 1) {
																							if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																								if((var50 == st[index$sample$16_2][timeStep$var136])) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																											if((var66 == st[index$sample$16_2][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																															double cv$temp$17$var148;
																															{
																																double var148 = metric_mean[traceTempVariable$currentState$16_1];
																																cv$temp$17$var148 = var148;
																															}
																															double cv$temp$18$var149;
																															{
																																double var149 = metric_var[traceTempVariable$currentState$16_1];
																																cv$temp$18$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$17$var148) / Math.sqrt(cv$temp$18$var149))) - (0.5 * Math.log(cv$temp$18$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$17$var148) / Math.sqrt(cv$temp$18$var149))) - (0.5 * Math.log(cv$temp$18$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$17$var148) / Math.sqrt(cv$temp$18$var149))) - (0.5 * Math.log(cv$temp$18$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$17$var148) / Math.sqrt(cv$temp$18$var149))) - (0.5 * Math.log(cv$temp$18$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$17$var148) / Math.sqrt(cv$temp$18$var149))) - (0.5 * Math.log(cv$temp$18$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
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
																	for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																		for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$49]; timeStep$var113 += 1) {
																			if(true) {
																				for(int index$sample123$51 = 0; index$sample123$51 < noStates; index$sample123$51 += 1) {
																					int distributionTempVariable$var121$53 = index$sample123$51;
																					double cv$probabilitySample123Value52 = (1.0 * distribution$sample123[((index$sample$49 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$51]);
																					if((index$sample$49 == index$sample$16_2)) {
																						if((timeStep$var113 == timeStep$var136)) {
																							if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																								for(int var50 = 0; var50 < noStates; var50 += 1) {
																									if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																										if((var50 == st[index$sample$16_2][timeStep$var136])) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																													if((var66 == st[index$sample$16_2][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[index$sample$16_2][timeStep$var136]) {
																																	double cv$temp$19$var148;
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$16_1];
																																		cv$temp$19$var148 = var148;
																																	}
																																	double cv$temp$20$var149;
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$16_1];
																																		cv$temp$20$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample123Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$19$var148) / Math.sqrt(cv$temp$20$var149))) - (0.5 * Math.log(cv$temp$20$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$19$var148) / Math.sqrt(cv$temp$20$var149))) - (0.5 * Math.log(cv$temp$20$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$19$var148) / Math.sqrt(cv$temp$20$var149))) - (0.5 * Math.log(cv$temp$20$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$19$var148) / Math.sqrt(cv$temp$20$var149))) - (0.5 * Math.log(cv$temp$20$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var136] - cv$temp$19$var148) / Math.sqrt(cv$temp$20$var149))) - (0.5 * Math.log(cv$temp$20$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value52);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
						int traceTempVariable$var118$67_1 = cv$currentValue;
						for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
							if((sample == index$sample$67_2)) {
								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$67_2]; timeStep$var113 += 1) {
									if((0 == (timeStep$var113 - 1))) {
										if(!fixedFlag$sample123) {
											{
												int index$timeStep$69 = timeStep$var113;
												int index$sample$70 = index$sample$67_2;
												double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120[threadID$cv$sample];
												for(int cv$i = 0; cv$i < noStates; cv$i += 1)
													cv$accumulatedConsumerDistributions[cv$i] = 0.0;
												double cv$reachedDistributionProbability = 0.0;
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == st[index$sample$67_2][(timeStep$var113 - 1)])) {
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double[] cv$temp$21$var119;
															{
																double[] var119 = m[traceTempVariable$var118$67_1];
																cv$temp$21$var119 = var119;
															}
															int cv$temp$22$$var2757;
															{
																int $var2757 = noStates;
																cv$temp$22$$var2757 = $var2757;
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
															DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$21$var119, cv$temp$22$$var2757);
														}
													}
												}
												double[] cv$sampleDistribution = distribution$sample123[((index$sample$67_2 - 0) / 1)][((timeStep$var113 - 1) / 1)];
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
			double[] cv$localProbability = distribution$sample104[((sample - 0) / 1)];
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

	private final void sample123(int sample, int timeStep$var113, int threadID$cv$sample, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			int index$timeStep$1 = timeStep$var113;
			int index$sample$2 = sample;
			if(fixedFlag$sample104) {
				for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
					if((index$sample$3_1 == sample)) {
						if((0 == (timeStep$var113 - 1))) {
							for(int var31 = 0; var31 < noStates; var31 += 1) {
								if((var31 == st[sample][(timeStep$var113 - 1)]))
									cv$numNumStates = Math.max(cv$numNumStates, noStates);
							}
						}
					}
				}
			} else {
				for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
					if(true) {
						for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
							int distributionTempVariable$var102$7 = index$sample104$5;
							double cv$probabilitySample104Value6 = (1.0 * distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
							if((index$sample$4 == sample)) {
								if((0 == (timeStep$var113 - 1))) {
									for(int var31 = 0; var31 < noStates; var31 += 1) {
										if((var31 == st[sample][(timeStep$var113 - 1)]))
											cv$numNumStates = Math.max(cv$numNumStates, noStates);
									}
								}
							}
						}
					}
				}
			}
			if((index$sample$2 == sample)) {
				if((index$timeStep$1 == (timeStep$var113 - 1))) {
					for(int var31 = 0; var31 < noStates; var31 += 1) {
						if((var31 == st[sample][(timeStep$var113 - 1)]))
							cv$numNumStates = Math.max(cv$numNumStates, noStates);
					}
				}
			}
			if(fixedFlag$sample123) {
				for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
					for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1]; index$timeStep$12_2 += 1) {
						if((index$sample$12_1 == sample)) {
							if((index$timeStep$12_2 == (timeStep$var113 - 1))) {
								for(int var31 = 0; var31 < noStates; var31 += 1) {
									if((var31 == st[sample][(timeStep$var113 - 1)]))
										cv$numNumStates = Math.max(cv$numNumStates, noStates);
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
					for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13]; index$timeStep$14 += 1) {
						if(!((index$sample$13 == index$sample$2) && (index$timeStep$14 == index$timeStep$1))) {
							for(int index$sample123$15 = 0; index$sample123$15 < noStates; index$sample123$15 += 1) {
								int distributionTempVariable$var121$17 = index$sample123$15;
								double cv$probabilitySample123Value16 = (1.0 * distribution$sample123[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample123$15]);
								if((index$sample$13 == sample)) {
									if((index$timeStep$14 == (timeStep$var113 - 1))) {
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[sample][(timeStep$var113 - 1)]))
												cv$numNumStates = Math.max(cv$numNumStates, noStates);
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var121$stateProbabilityGlobal[threadID$cv$sample];
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				int index$timeStep$22 = timeStep$var113;
				int index$sample$23 = sample;
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(fixedFlag$sample104) {
					for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
						if((index$sample$24_1 == sample)) {
							if((0 == (timeStep$var113 - 1))) {
								for(int var31 = 0; var31 < noStates; var31 += 1) {
									if((var31 == st[sample][(timeStep$var113 - 1)])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double[] cv$temp$0$var119;
										{
											double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
											cv$temp$0$var119 = var119;
										}
										int cv$temp$1$$var2843;
										{
											int $var2843 = noStates;
											cv$temp$1$$var2843 = $var2843;
										}
										double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var2843))?Math.log(cv$temp$0$var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												int traceTempVariable$var118$41_1 = cv$currentValue;
											}
										}
										{
											{
												int traceTempVariable$currentState$45_1 = cv$currentValue;
												for(int index$sample$45_2 = 0; index$sample$45_2 < noSamples; index$sample$45_2 += 1) {
													if((sample == index$sample$45_2)) {
														for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$45_2]; timeStep$var136 += 1) {
															if((timeStep$var113 == timeStep$var136)) {
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int var82 = 0; var82 < noStates; var82 += 1) {
																			if((var82 == st[index$sample$45_2][timeStep$var136])) {
																				{
																					{
																						double cv$temp$8$var139;
																						{
																							double var139 = metric_valid_bias[traceTempVariable$currentState$45_1];
																							cv$temp$8$var139 = var139;
																						}
																						if(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$45_2][timeStep$var136]?cv$temp$8$var139:(1.0 - cv$temp$8$var139)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$45_2][timeStep$var136]?cv$temp$8$var139:(1.0 - cv$temp$8$var139)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[index$sample$45_2][timeStep$var136]?cv$temp$8$var139:(1.0 - cv$temp$8$var139))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[index$sample$45_2][timeStep$var136]?cv$temp$8$var139:(1.0 - cv$temp$8$var139)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[index$sample$45_2][timeStep$var136]?cv$temp$8$var139:(1.0 - cv$temp$8$var139)))));
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
										{
											{
												boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
												for(int index$sample$61_1 = 0; index$sample$61_1 < noSamples; index$sample$61_1 += 1) {
													if((sample == index$sample$61_1)) {
														for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$61_1]; timeStep$var136 += 1) {
															if((timeStep$var113 == timeStep$var136)) {
																if(metric_valid_g[index$sample$61_1][timeStep$var136])
																	guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
															}
														}
													}
												}
												for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
													if((sample == index$sample$65_1)) {
														for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$65_1]; timeStep$var136 += 1) {
															if((timeStep$var113 == timeStep$var136)) {
																if(metric_valid_g[index$sample$65_1][timeStep$var136])
																	guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
															}
														}
													}
												}
												int traceTempVariable$currentState$69_1 = cv$currentValue;
												for(int index$sample$69_2 = 0; index$sample$69_2 < noSamples; index$sample$69_2 += 1) {
													if((sample == index$sample$69_2)) {
														for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$69_2]; timeStep$var136 += 1) {
															if((timeStep$var113 == timeStep$var136)) {
																if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																	if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																		guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																		{
																			if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var50 = 0; var50 < noStates; var50 += 1) {
																						if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																							if((var50 == st[index$sample$69_2][timeStep$var136])) {
																								for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																									if((index$sample$86_1 == index$sample$69_2)) {
																										if((0 == timeStep$var136)) {
																											if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																														if((var66 == st[index$sample$69_2][timeStep$var136])) {
																															{
																																{
																																	if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																																		double cv$temp$12$var148;
																																		{
																																			double var148 = metric_mean[traceTempVariable$currentState$69_1];
																																			cv$temp$12$var148 = var148;
																																		}
																																		double cv$temp$13$var149;
																																		{
																																			double var149 = metric_var[traceTempVariable$currentState$69_1];
																																			cv$temp$13$var149 = var149;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int var50 = 0; var50 < noStates; var50 += 1) {
																						if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																							if((var50 == st[index$sample$69_2][timeStep$var136])) {
																								int traceTempVariable$currentState$89_1 = cv$currentValue;
																								if((index$sample$23 == index$sample$69_2)) {
																									if((index$timeStep$22 == timeStep$var136)) {
																										if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																													if((var66 == st[index$sample$69_2][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																																	double cv$temp$14$var148;
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$89_1];
																																		cv$temp$14$var148 = var148;
																																	}
																																	double cv$temp$15$var149;
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$89_1];
																																		cv$temp$15$var149 = var149;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																								for(int index$sample$90 = 0; index$sample$90 < noSamples; index$sample$90 += 1) {
																									for(int index$timeStep$91 = 1; index$timeStep$91 < length$metric[index$sample$90]; index$timeStep$91 += 1) {
																										if(!((index$sample$90 == index$sample$23) && (index$timeStep$91 == index$timeStep$22))) {
																											for(int index$sample123$92 = 0; index$sample123$92 < noStates; index$sample123$92 += 1) {
																												int distributionTempVariable$var121$94 = index$sample123$92;
																												double cv$probabilitySample123Value93 = (1.0 * distribution$sample123[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample123$92]);
																												int traceTempVariable$currentState$95_1 = cv$currentValue;
																												if((index$sample$90 == index$sample$69_2)) {
																													if((index$timeStep$91 == timeStep$var136)) {
																														if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																																	if((var66 == st[index$sample$69_2][timeStep$var136])) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$69_2][timeStep$var136]) {
																																					double cv$temp$16$var148;
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$95_1];
																																						cv$temp$16$var148 = var148;
																																					}
																																					double cv$temp$17$var149;
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$95_1];
																																						cv$temp$17$var149 = var149;
																																					}
																																					if(((Math.log(cv$probabilitySample123Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value93);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
												int traceTempVariable$currentState$73_1 = cv$currentValue;
												for(int index$sample$73_2 = 0; index$sample$73_2 < noSamples; index$sample$73_2 += 1) {
													if((sample == index$sample$73_2)) {
														for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$73_2]; timeStep$var136 += 1) {
															if((timeStep$var113 == timeStep$var136)) {
																if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																	if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																		guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																		{
																			if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																						if((index$sample$157_1 == index$sample$73_2)) {
																							if((0 == timeStep$var136)) {
																								if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																											if((var50 == st[index$sample$73_2][timeStep$var136])) {
																												for(int var66 = 0; var66 < noStates; var66 += 1) {
																													if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																														if((var66 == st[index$sample$73_2][timeStep$var136])) {
																															{
																																{
																																	if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																																		double cv$temp$44$var148;
																																		{
																																			double var148 = metric_mean[traceTempVariable$currentState$73_1];
																																			cv$temp$44$var148 = var148;
																																		}
																																		double cv$temp$45$var149;
																																		{
																																			double var149 = metric_var[traceTempVariable$currentState$73_1];
																																			cv$temp$45$var149 = var149;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$44$var148) / Math.sqrt(cv$temp$45$var149))) - (0.5 * Math.log(cv$temp$45$var149)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$44$var148) / Math.sqrt(cv$temp$45$var149))) - (0.5 * Math.log(cv$temp$45$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$44$var148) / Math.sqrt(cv$temp$45$var149))) - (0.5 * Math.log(cv$temp$45$var149))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$44$var148) / Math.sqrt(cv$temp$45$var149))) - (0.5 * Math.log(cv$temp$45$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$44$var148) / Math.sqrt(cv$temp$45$var149))) - (0.5 * Math.log(cv$temp$45$var149)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					int traceTempVariable$currentState$160_1 = cv$currentValue;
																					if((index$sample$23 == index$sample$73_2)) {
																						if((index$timeStep$22 == timeStep$var136)) {
																							if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																								for(int var50 = 0; var50 < noStates; var50 += 1) {
																									if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																										if((var50 == st[index$sample$73_2][timeStep$var136])) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																													if((var66 == st[index$sample$73_2][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																																	double cv$temp$46$var148;
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$160_1];
																																		cv$temp$46$var148 = var148;
																																	}
																																	double cv$temp$47$var149;
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$160_1];
																																		cv$temp$47$var149 = var149;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$46$var148) / Math.sqrt(cv$temp$47$var149))) - (0.5 * Math.log(cv$temp$47$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$46$var148) / Math.sqrt(cv$temp$47$var149))) - (0.5 * Math.log(cv$temp$47$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$46$var148) / Math.sqrt(cv$temp$47$var149))) - (0.5 * Math.log(cv$temp$47$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$46$var148) / Math.sqrt(cv$temp$47$var149))) - (0.5 * Math.log(cv$temp$47$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$46$var148) / Math.sqrt(cv$temp$47$var149))) - (0.5 * Math.log(cv$temp$47$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																						for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161]; index$timeStep$162 += 1) {
																							if(!((index$sample$161 == index$sample$23) && (index$timeStep$162 == index$timeStep$22))) {
																								for(int index$sample123$163 = 0; index$sample123$163 < noStates; index$sample123$163 += 1) {
																									int distributionTempVariable$var121$165 = index$sample123$163;
																									double cv$probabilitySample123Value164 = (1.0 * distribution$sample123[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample123$163]);
																									int traceTempVariable$currentState$166_1 = cv$currentValue;
																									if((index$sample$161 == index$sample$73_2)) {
																										if((index$timeStep$162 == timeStep$var136)) {
																											if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																														if((var50 == st[index$sample$73_2][timeStep$var136])) {
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																																	if((var66 == st[index$sample$73_2][timeStep$var136])) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$73_2][timeStep$var136]) {
																																					double cv$temp$48$var148;
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$166_1];
																																						cv$temp$48$var148 = var148;
																																					}
																																					double cv$temp$49$var149;
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$166_1];
																																						cv$temp$49$var149 = var149;
																																					}
																																					if(((Math.log(cv$probabilitySample123Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$48$var148) / Math.sqrt(cv$temp$49$var149))) - (0.5 * Math.log(cv$temp$49$var149)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$48$var148) / Math.sqrt(cv$temp$49$var149))) - (0.5 * Math.log(cv$temp$49$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$48$var148) / Math.sqrt(cv$temp$49$var149))) - (0.5 * Math.log(cv$temp$49$var149))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$48$var148) / Math.sqrt(cv$temp$49$var149))) - (0.5 * Math.log(cv$temp$49$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var136] - cv$temp$48$var148) / Math.sqrt(cv$temp$49$var149))) - (0.5 * Math.log(cv$temp$49$var149)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value164);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
				} else {
					for(int index$sample$25 = 0; index$sample$25 < noSamples; index$sample$25 += 1) {
						if(true) {
							for(int index$sample104$26 = 0; index$sample104$26 < noStates; index$sample104$26 += 1) {
								int distributionTempVariable$var102$28 = index$sample104$26;
								double cv$probabilitySample104Value27 = (1.0 * distribution$sample104[((index$sample$25 - 0) / 1)][index$sample104$26]);
								if((index$sample$25 == sample)) {
									if((0 == (timeStep$var113 - 1))) {
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[sample][(timeStep$var113 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value27);
												double[] cv$temp$2$var119;
												{
													double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
													cv$temp$2$var119 = var119;
												}
												int cv$temp$3$$var2844;
												{
													int $var2844 = noStates;
													cv$temp$3$$var2844 = $var2844;
												}
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var2844))?Math.log(cv$temp$2$var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														int traceTempVariable$var118$42_1 = cv$currentValue;
													}
												}
												{
													{
														int traceTempVariable$currentState$46_1 = cv$currentValue;
														for(int index$sample$46_2 = 0; index$sample$46_2 < noSamples; index$sample$46_2 += 1) {
															if((sample == index$sample$46_2)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$46_2]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var82 = 0; var82 < noStates; var82 += 1) {
																					if((var82 == st[index$sample$46_2][timeStep$var136])) {
																						{
																							{
																								double cv$temp$9$var139;
																								{
																									double var139 = metric_valid_bias[traceTempVariable$currentState$46_1];
																									cv$temp$9$var139 = var139;
																								}
																								if(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$46_2][timeStep$var136]?cv$temp$9$var139:(1.0 - cv$temp$9$var139)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$46_2][timeStep$var136]?cv$temp$9$var139:(1.0 - cv$temp$9$var139)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[index$sample$46_2][timeStep$var136]?cv$temp$9$var139:(1.0 - cv$temp$9$var139))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[index$sample$46_2][timeStep$var136]?cv$temp$9$var139:(1.0 - cv$temp$9$var139)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[index$sample$46_2][timeStep$var136]?cv$temp$9$var139:(1.0 - cv$temp$9$var139)))));
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
												{
													{
														boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
														for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
															if((sample == index$sample$62_1)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$62_1]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$62_1][timeStep$var136])
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																	}
																}
															}
														}
														for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
															if((sample == index$sample$66_1)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$66_1]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$66_1][timeStep$var136])
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																	}
																}
															}
														}
														int traceTempVariable$currentState$70_1 = cv$currentValue;
														for(int index$sample$70_2 = 0; index$sample$70_2 < noSamples; index$sample$70_2 += 1) {
															if((sample == index$sample$70_2)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$70_2]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																			if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							for(int var50 = 0; var50 < noStates; var50 += 1) {
																								if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																									if((var50 == st[index$sample$70_2][timeStep$var136])) {
																										int traceTempVariable$currentState$99_1 = distributionTempVariable$var102$28;
																										if((index$sample$25 == index$sample$70_2)) {
																											if((0 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																															if((var66 == st[index$sample$70_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																			double cv$temp$18$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$99_1];
																																				cv$temp$18$var148 = var148;
																																			}
																																			double cv$temp$19$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$99_1];
																																				cv$temp$19$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										for(int index$sample$100 = 0; index$sample$100 < noSamples; index$sample$100 += 1) {
																											if(!(index$sample$100 == index$sample$25)) {
																												for(int index$sample104$101 = 0; index$sample104$101 < noStates; index$sample104$101 += 1) {
																													int distributionTempVariable$var102$103 = index$sample104$101;
																													double cv$probabilitySample104Value102 = (1.0 * distribution$sample104[((index$sample$100 - 0) / 1)][index$sample104$101]);
																													int traceTempVariable$currentState$104_1 = distributionTempVariable$var102$28;
																													if((index$sample$100 == index$sample$70_2)) {
																														if((0 == timeStep$var136)) {
																															if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																for(int var66 = 0; var66 < noStates; var66 += 1) {
																																	if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																		if((var66 == st[index$sample$70_2][timeStep$var136])) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																						double cv$temp$20$var148;
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$104_1];
																																							cv$temp$20$var148 = var148;
																																						}
																																						double cv$temp$21$var149;
																																						{
																																							double var149 = metric_var[traceTempVariable$currentState$104_1];
																																							cv$temp$21$var149 = var149;
																																						}
																																						if(((Math.log(cv$probabilitySample104Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value102);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int var50 = 0; var50 < noStates; var50 += 1) {
																								if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																									if((var50 == st[index$sample$70_2][timeStep$var136])) {
																										int traceTempVariable$currentState$108_1 = cv$currentValue;
																										if((index$sample$23 == index$sample$70_2)) {
																											if((index$timeStep$22 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																															if((var66 == st[index$sample$70_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																			double cv$temp$22$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$108_1];
																																				cv$temp$22$var148 = var148;
																																			}
																																			double cv$temp$23$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$108_1];
																																				cv$temp$23$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										for(int index$sample$109 = 0; index$sample$109 < noSamples; index$sample$109 += 1) {
																											for(int index$timeStep$110 = 1; index$timeStep$110 < length$metric[index$sample$109]; index$timeStep$110 += 1) {
																												if(!((index$sample$109 == index$sample$23) && (index$timeStep$110 == index$timeStep$22))) {
																													for(int index$sample123$111 = 0; index$sample123$111 < noStates; index$sample123$111 += 1) {
																														int distributionTempVariable$var121$113 = index$sample123$111;
																														double cv$probabilitySample123Value112 = (1.0 * distribution$sample123[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample123$111]);
																														int traceTempVariable$currentState$114_1 = cv$currentValue;
																														if((index$sample$109 == index$sample$70_2)) {
																															if((index$timeStep$110 == timeStep$var136)) {
																																if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																			if((var66 == st[index$sample$70_2][timeStep$var136])) {
																																				{
																																					{
																																						if(metric_valid_g[index$sample$70_2][timeStep$var136]) {
																																							double cv$temp$24$var148;
																																							{
																																								double var148 = metric_mean[traceTempVariable$currentState$114_1];
																																								cv$temp$24$var148 = var148;
																																							}
																																							double cv$temp$25$var149;
																																							{
																																								double var149 = metric_var[traceTempVariable$currentState$114_1];
																																								cv$temp$25$var149 = var149;
																																							}
																																							if(((Math.log(cv$probabilitySample123Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value112);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
														int traceTempVariable$currentState$74_1 = cv$currentValue;
														for(int index$sample$74_2 = 0; index$sample$74_2 < noSamples; index$sample$74_2 += 1) {
															if((sample == index$sample$74_2)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$74_2]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																			if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							int traceTempVariable$currentState$171_1 = distributionTempVariable$var102$28;
																							if((index$sample$25 == index$sample$74_2)) {
																								if((0 == timeStep$var136)) {
																									if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																												if((var50 == st[index$sample$74_2][timeStep$var136])) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																															if((var66 == st[index$sample$74_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																			double cv$temp$50$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$171_1];
																																				cv$temp$50$var148 = var148;
																																			}
																																			double cv$temp$51$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$171_1];
																																				cv$temp$51$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$50$var148) / Math.sqrt(cv$temp$51$var149))) - (0.5 * Math.log(cv$temp$51$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$50$var148) / Math.sqrt(cv$temp$51$var149))) - (0.5 * Math.log(cv$temp$51$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$50$var148) / Math.sqrt(cv$temp$51$var149))) - (0.5 * Math.log(cv$temp$51$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$50$var148) / Math.sqrt(cv$temp$51$var149))) - (0.5 * Math.log(cv$temp$51$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$50$var148) / Math.sqrt(cv$temp$51$var149))) - (0.5 * Math.log(cv$temp$51$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																								if(!(index$sample$172 == index$sample$25)) {
																									for(int index$sample104$173 = 0; index$sample104$173 < noStates; index$sample104$173 += 1) {
																										int distributionTempVariable$var102$175 = index$sample104$173;
																										double cv$probabilitySample104Value174 = (1.0 * distribution$sample104[((index$sample$172 - 0) / 1)][index$sample104$173]);
																										int traceTempVariable$currentState$176_1 = distributionTempVariable$var102$28;
																										if((index$sample$172 == index$sample$74_2)) {
																											if((0 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																															if((var50 == st[index$sample$74_2][timeStep$var136])) {
																																for(int var66 = 0; var66 < noStates; var66 += 1) {
																																	if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																		if((var66 == st[index$sample$74_2][timeStep$var136])) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																						double cv$temp$52$var148;
																																						{
																																							double var148 = metric_mean[traceTempVariable$currentState$176_1];
																																							cv$temp$52$var148 = var148;
																																						}
																																						double cv$temp$53$var149;
																																						{
																																							double var149 = metric_var[traceTempVariable$currentState$176_1];
																																							cv$temp$53$var149 = var149;
																																						}
																																						if(((Math.log(cv$probabilitySample104Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$52$var148) / Math.sqrt(cv$temp$53$var149))) - (0.5 * Math.log(cv$temp$53$var149)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$52$var148) / Math.sqrt(cv$temp$53$var149))) - (0.5 * Math.log(cv$temp$53$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$52$var148) / Math.sqrt(cv$temp$53$var149))) - (0.5 * Math.log(cv$temp$53$var149))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$52$var148) / Math.sqrt(cv$temp$53$var149))) - (0.5 * Math.log(cv$temp$53$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$52$var148) / Math.sqrt(cv$temp$53$var149))) - (0.5 * Math.log(cv$temp$53$var149)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value174);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$181_1 = cv$currentValue;
																							if((index$sample$23 == index$sample$74_2)) {
																								if((index$timeStep$22 == timeStep$var136)) {
																									if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																												if((var50 == st[index$sample$74_2][timeStep$var136])) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																															if((var66 == st[index$sample$74_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																			double cv$temp$54$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$181_1];
																																				cv$temp$54$var148 = var148;
																																			}
																																			double cv$temp$55$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$181_1];
																																				cv$temp$55$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$54$var148) / Math.sqrt(cv$temp$55$var149))) - (0.5 * Math.log(cv$temp$55$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$54$var148) / Math.sqrt(cv$temp$55$var149))) - (0.5 * Math.log(cv$temp$55$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$54$var148) / Math.sqrt(cv$temp$55$var149))) - (0.5 * Math.log(cv$temp$55$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$54$var148) / Math.sqrt(cv$temp$55$var149))) - (0.5 * Math.log(cv$temp$55$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$54$var148) / Math.sqrt(cv$temp$55$var149))) - (0.5 * Math.log(cv$temp$55$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																								for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182]; index$timeStep$183 += 1) {
																									if(!((index$sample$182 == index$sample$23) && (index$timeStep$183 == index$timeStep$22))) {
																										for(int index$sample123$184 = 0; index$sample123$184 < noStates; index$sample123$184 += 1) {
																											int distributionTempVariable$var121$186 = index$sample123$184;
																											double cv$probabilitySample123Value185 = (1.0 * distribution$sample123[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample123$184]);
																											int traceTempVariable$currentState$187_1 = cv$currentValue;
																											if((index$sample$182 == index$sample$74_2)) {
																												if((index$timeStep$183 == timeStep$var136)) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																														for(int var50 = 0; var50 < noStates; var50 += 1) {
																															if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																if((var50 == st[index$sample$74_2][timeStep$var136])) {
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																			if((var66 == st[index$sample$74_2][timeStep$var136])) {
																																				{
																																					{
																																						if(metric_valid_g[index$sample$74_2][timeStep$var136]) {
																																							double cv$temp$56$var148;
																																							{
																																								double var148 = metric_mean[traceTempVariable$currentState$187_1];
																																								cv$temp$56$var148 = var148;
																																							}
																																							double cv$temp$57$var149;
																																							{
																																								double var149 = metric_var[traceTempVariable$currentState$187_1];
																																								cv$temp$57$var149 = var149;
																																							}
																																							if(((Math.log(cv$probabilitySample123Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$56$var148) / Math.sqrt(cv$temp$57$var149))) - (0.5 * Math.log(cv$temp$57$var149)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$56$var148) / Math.sqrt(cv$temp$57$var149))) - (0.5 * Math.log(cv$temp$57$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$56$var148) / Math.sqrt(cv$temp$57$var149))) - (0.5 * Math.log(cv$temp$57$var149))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$56$var148) / Math.sqrt(cv$temp$57$var149))) - (0.5 * Math.log(cv$temp$57$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var136] - cv$temp$56$var148) / Math.sqrt(cv$temp$57$var149))) - (0.5 * Math.log(cv$temp$57$var149)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value185);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
				int traceTempVariable$var118$32_1 = cv$currentValue;
				if((index$sample$23 == sample)) {
					if((index$timeStep$22 == (timeStep$var113 - 1))) {
						for(int var31 = 0; var31 < noStates; var31 += 1) {
							if((var31 == st[sample][(timeStep$var113 - 1)])) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$4$var119;
								{
									double[] var119 = m[traceTempVariable$var118$32_1];
									cv$temp$4$var119 = var119;
								}
								int cv$temp$5$$var2845;
								{
									int $var2845 = noStates;
									cv$temp$5$$var2845 = $var2845;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var2845))?Math.log(cv$temp$4$var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var118$43_1 = cv$currentValue;
									}
								}
								{
									{
										int traceTempVariable$currentState$47_1 = cv$currentValue;
										for(int index$sample$47_2 = 0; index$sample$47_2 < noSamples; index$sample$47_2 += 1) {
											if((sample == index$sample$47_2)) {
												for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$47_2]; timeStep$var136 += 1) {
													if((timeStep$var113 == timeStep$var136)) {
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var82 = 0; var82 < noStates; var82 += 1) {
																	if((var82 == st[index$sample$47_2][timeStep$var136])) {
																		{
																			{
																				double cv$temp$10$var139;
																				{
																					double var139 = metric_valid_bias[traceTempVariable$currentState$47_1];
																					cv$temp$10$var139 = var139;
																				}
																				if(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$47_2][timeStep$var136]?cv$temp$10$var139:(1.0 - cv$temp$10$var139)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$47_2][timeStep$var136]?cv$temp$10$var139:(1.0 - cv$temp$10$var139)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[index$sample$47_2][timeStep$var136]?cv$temp$10$var139:(1.0 - cv$temp$10$var139))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[index$sample$47_2][timeStep$var136]?cv$temp$10$var139:(1.0 - cv$temp$10$var139)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[index$sample$47_2][timeStep$var136]?cv$temp$10$var139:(1.0 - cv$temp$10$var139)))));
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
								{
									{
										boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
										for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
											if((sample == index$sample$63_1)) {
												for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$63_1]; timeStep$var136 += 1) {
													if((timeStep$var113 == timeStep$var136)) {
														if(metric_valid_g[index$sample$63_1][timeStep$var136])
															guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
													}
												}
											}
										}
										for(int index$sample$67_1 = 0; index$sample$67_1 < noSamples; index$sample$67_1 += 1) {
											if((sample == index$sample$67_1)) {
												for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$67_1]; timeStep$var136 += 1) {
													if((timeStep$var113 == timeStep$var136)) {
														if(metric_valid_g[index$sample$67_1][timeStep$var136])
															guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
													}
												}
											}
										}
										int traceTempVariable$currentState$71_1 = cv$currentValue;
										for(int index$sample$71_2 = 0; index$sample$71_2 < noSamples; index$sample$71_2 += 1) {
											if((sample == index$sample$71_2)) {
												for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$71_2]; timeStep$var136 += 1) {
													if((timeStep$var113 == timeStep$var136)) {
														if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
															if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																{
																	if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																				if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																					if((var50 == st[index$sample$71_2][timeStep$var136])) {
																						if(fixedFlag$sample104) {
																							for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																								if((index$sample$118_1 == index$sample$71_2)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																													if((var66 == st[index$sample$71_2][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																																	double cv$temp$26$var148;
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$71_1];
																																		cv$temp$26$var148 = var148;
																																	}
																																	double cv$temp$27$var149;
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$71_1];
																																		cv$temp$27$var149 = var149;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
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
																							for(int index$sample$119 = 0; index$sample$119 < noSamples; index$sample$119 += 1) {
																								if(true) {
																									for(int index$sample104$120 = 0; index$sample104$120 < noStates; index$sample104$120 += 1) {
																										int distributionTempVariable$var102$122 = index$sample104$120;
																										double cv$probabilitySample104Value121 = (1.0 * distribution$sample104[((index$sample$119 - 0) / 1)][index$sample104$120]);
																										if((index$sample$119 == index$sample$71_2)) {
																											if((0 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																															if((var66 == st[index$sample$71_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																																			double cv$temp$28$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$71_1];
																																				cv$temp$28$var148 = var148;
																																			}
																																			double cv$temp$29$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$71_1];
																																				cv$temp$29$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample104Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value121);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int var50 = 0; var50 < noStates; var50 += 1) {
																				if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																					if((var50 == st[index$sample$71_2][timeStep$var136])) {
																						int traceTempVariable$currentState$127_1 = cv$currentValue;
																						if((index$sample$23 == index$sample$71_2)) {
																							if((index$timeStep$22 == timeStep$var136)) {
																								if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																											if((var66 == st[index$sample$71_2][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																															double cv$temp$30$var148;
																															{
																																double var148 = metric_mean[traceTempVariable$currentState$127_1];
																																cv$temp$30$var148 = var148;
																															}
																															double cv$temp$31$var149;
																															{
																																double var149 = metric_var[traceTempVariable$currentState$127_1];
																																cv$temp$31$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$30$var148) / Math.sqrt(cv$temp$31$var149))) - (0.5 * Math.log(cv$temp$31$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$30$var148) / Math.sqrt(cv$temp$31$var149))) - (0.5 * Math.log(cv$temp$31$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$30$var148) / Math.sqrt(cv$temp$31$var149))) - (0.5 * Math.log(cv$temp$31$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$30$var148) / Math.sqrt(cv$temp$31$var149))) - (0.5 * Math.log(cv$temp$31$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$30$var148) / Math.sqrt(cv$temp$31$var149))) - (0.5 * Math.log(cv$temp$31$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$128 = 0; index$sample$128 < noSamples; index$sample$128 += 1) {
																							for(int index$timeStep$129 = 1; index$timeStep$129 < length$metric[index$sample$128]; index$timeStep$129 += 1) {
																								if(!((index$sample$128 == index$sample$23) && (index$timeStep$129 == index$timeStep$22))) {
																									for(int index$sample123$130 = 0; index$sample123$130 < noStates; index$sample123$130 += 1) {
																										int distributionTempVariable$var121$132 = index$sample123$130;
																										double cv$probabilitySample123Value131 = (1.0 * distribution$sample123[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample123$130]);
																										int traceTempVariable$currentState$133_1 = cv$currentValue;
																										if((index$sample$128 == index$sample$71_2)) {
																											if((index$timeStep$129 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																															if((var66 == st[index$sample$71_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$71_2][timeStep$var136]) {
																																			double cv$temp$32$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$133_1];
																																				cv$temp$32$var148 = var148;
																																			}
																																			double cv$temp$33$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$133_1];
																																				cv$temp$33$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample123Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$32$var148) / Math.sqrt(cv$temp$33$var149))) - (0.5 * Math.log(cv$temp$33$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$32$var148) / Math.sqrt(cv$temp$33$var149))) - (0.5 * Math.log(cv$temp$33$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$32$var148) / Math.sqrt(cv$temp$33$var149))) - (0.5 * Math.log(cv$temp$33$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$32$var148) / Math.sqrt(cv$temp$33$var149))) - (0.5 * Math.log(cv$temp$33$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var136] - cv$temp$32$var148) / Math.sqrt(cv$temp$33$var149))) - (0.5 * Math.log(cv$temp$33$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value131);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
										int traceTempVariable$currentState$75_1 = cv$currentValue;
										for(int index$sample$75_2 = 0; index$sample$75_2 < noSamples; index$sample$75_2 += 1) {
											if((sample == index$sample$75_2)) {
												for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$75_2]; timeStep$var136 += 1) {
													if((timeStep$var113 == timeStep$var136)) {
														if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
															if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																{
																	if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample104) {
																				for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																					if((index$sample$192_1 == index$sample$75_2)) {
																						if((0 == timeStep$var136)) {
																							if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																								for(int var50 = 0; var50 < noStates; var50 += 1) {
																									if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																										if((var50 == st[index$sample$75_2][timeStep$var136])) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																													if((var66 == st[index$sample$75_2][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																																	double cv$temp$58$var148;
																																	{
																																		double var148 = metric_mean[traceTempVariable$currentState$75_1];
																																		cv$temp$58$var148 = var148;
																																	}
																																	double cv$temp$59$var149;
																																	{
																																		double var149 = metric_var[traceTempVariable$currentState$75_1];
																																		cv$temp$59$var149 = var149;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$58$var148) / Math.sqrt(cv$temp$59$var149))) - (0.5 * Math.log(cv$temp$59$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$58$var148) / Math.sqrt(cv$temp$59$var149))) - (0.5 * Math.log(cv$temp$59$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$58$var148) / Math.sqrt(cv$temp$59$var149))) - (0.5 * Math.log(cv$temp$59$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$58$var148) / Math.sqrt(cv$temp$59$var149))) - (0.5 * Math.log(cv$temp$59$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$58$var148) / Math.sqrt(cv$temp$59$var149))) - (0.5 * Math.log(cv$temp$59$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																															}
																														}
																													}
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
																				for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																					if(true) {
																						for(int index$sample104$194 = 0; index$sample104$194 < noStates; index$sample104$194 += 1) {
																							int distributionTempVariable$var102$196 = index$sample104$194;
																							double cv$probabilitySample104Value195 = (1.0 * distribution$sample104[((index$sample$193 - 0) / 1)][index$sample104$194]);
																							if((index$sample$193 == index$sample$75_2)) {
																								if((0 == timeStep$var136)) {
																									if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																												if((var50 == st[index$sample$75_2][timeStep$var136])) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																															if((var66 == st[index$sample$75_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																																			double cv$temp$60$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$75_1];
																																				cv$temp$60$var148 = var148;
																																			}
																																			double cv$temp$61$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$75_1];
																																				cv$temp$61$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample104Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$60$var148) / Math.sqrt(cv$temp$61$var149))) - (0.5 * Math.log(cv$temp$61$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$60$var148) / Math.sqrt(cv$temp$61$var149))) - (0.5 * Math.log(cv$temp$61$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$60$var148) / Math.sqrt(cv$temp$61$var149))) - (0.5 * Math.log(cv$temp$61$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$60$var148) / Math.sqrt(cv$temp$61$var149))) - (0.5 * Math.log(cv$temp$61$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$60$var148) / Math.sqrt(cv$temp$61$var149))) - (0.5 * Math.log(cv$temp$61$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value195);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			int traceTempVariable$currentState$202_1 = cv$currentValue;
																			if((index$sample$23 == index$sample$75_2)) {
																				if((index$timeStep$22 == timeStep$var136)) {
																					if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																						for(int var50 = 0; var50 < noStates; var50 += 1) {
																							if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																								if((var50 == st[index$sample$75_2][timeStep$var136])) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																											if((var66 == st[index$sample$75_2][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																															double cv$temp$62$var148;
																															{
																																double var148 = metric_mean[traceTempVariable$currentState$202_1];
																																cv$temp$62$var148 = var148;
																															}
																															double cv$temp$63$var149;
																															{
																																double var149 = metric_var[traceTempVariable$currentState$202_1];
																																cv$temp$63$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$62$var148) / Math.sqrt(cv$temp$63$var149))) - (0.5 * Math.log(cv$temp$63$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$62$var148) / Math.sqrt(cv$temp$63$var149))) - (0.5 * Math.log(cv$temp$63$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$62$var148) / Math.sqrt(cv$temp$63$var149))) - (0.5 * Math.log(cv$temp$63$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$62$var148) / Math.sqrt(cv$temp$63$var149))) - (0.5 * Math.log(cv$temp$63$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$62$var148) / Math.sqrt(cv$temp$63$var149))) - (0.5 * Math.log(cv$temp$63$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																				for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203]; index$timeStep$204 += 1) {
																					if(!((index$sample$203 == index$sample$23) && (index$timeStep$204 == index$timeStep$22))) {
																						for(int index$sample123$205 = 0; index$sample123$205 < noStates; index$sample123$205 += 1) {
																							int distributionTempVariable$var121$207 = index$sample123$205;
																							double cv$probabilitySample123Value206 = (1.0 * distribution$sample123[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample123$205]);
																							int traceTempVariable$currentState$208_1 = cv$currentValue;
																							if((index$sample$203 == index$sample$75_2)) {
																								if((index$timeStep$204 == timeStep$var136)) {
																									if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																												if((var50 == st[index$sample$75_2][timeStep$var136])) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																															if((var66 == st[index$sample$75_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$75_2][timeStep$var136]) {
																																			double cv$temp$64$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$208_1];
																																				cv$temp$64$var148 = var148;
																																			}
																																			double cv$temp$65$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$208_1];
																																				cv$temp$65$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample123Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$64$var148) / Math.sqrt(cv$temp$65$var149))) - (0.5 * Math.log(cv$temp$65$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$64$var148) / Math.sqrt(cv$temp$65$var149))) - (0.5 * Math.log(cv$temp$65$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$64$var148) / Math.sqrt(cv$temp$65$var149))) - (0.5 * Math.log(cv$temp$65$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$64$var148) / Math.sqrt(cv$temp$65$var149))) - (0.5 * Math.log(cv$temp$65$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var136] - cv$temp$64$var148) / Math.sqrt(cv$temp$65$var149))) - (0.5 * Math.log(cv$temp$65$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value206);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
				for(int index$sample$33 = 0; index$sample$33 < noSamples; index$sample$33 += 1) {
					for(int index$timeStep$34 = 1; index$timeStep$34 < length$metric[index$sample$33]; index$timeStep$34 += 1) {
						if(!((index$sample$33 == index$sample$23) && (index$timeStep$34 == index$timeStep$22))) {
							for(int index$sample123$35 = 0; index$sample123$35 < noStates; index$sample123$35 += 1) {
								int distributionTempVariable$var121$37 = index$sample123$35;
								double cv$probabilitySample123Value36 = (1.0 * distribution$sample123[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample123$35]);
								int traceTempVariable$var118$38_1 = cv$currentValue;
								if((index$sample$33 == sample)) {
									if((index$timeStep$34 == (timeStep$var113 - 1))) {
										for(int var31 = 0; var31 < noStates; var31 += 1) {
											if((var31 == st[sample][(timeStep$var113 - 1)])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value36);
												double[] cv$temp$6$var119;
												{
													double[] var119 = m[traceTempVariable$var118$38_1];
													cv$temp$6$var119 = var119;
												}
												int cv$temp$7$$var2846;
												{
													int $var2846 = noStates;
													cv$temp$7$$var2846 = $var2846;
												}
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample123Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var2846))?Math.log(cv$temp$6$var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
												{
													{
														int traceTempVariable$var118$44_1 = distributionTempVariable$var121$37;
													}
												}
												{
													{
														int traceTempVariable$currentState$48_1 = distributionTempVariable$var121$37;
														for(int index$sample$48_2 = 0; index$sample$48_2 < noSamples; index$sample$48_2 += 1) {
															if((sample == index$sample$48_2)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$48_2]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var82 = 0; var82 < noStates; var82 += 1) {
																					if((var82 == st[index$sample$48_2][timeStep$var136])) {
																						{
																							{
																								double cv$temp$11$var139;
																								{
																									double var139 = metric_valid_bias[traceTempVariable$currentState$48_1];
																									cv$temp$11$var139 = var139;
																								}
																								if(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$48_2][timeStep$var136]?cv$temp$11$var139:(1.0 - cv$temp$11$var139)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((metric_valid_g[index$sample$48_2][timeStep$var136]?cv$temp$11$var139:(1.0 - cv$temp$11$var139)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((metric_valid_g[index$sample$48_2][timeStep$var136]?cv$temp$11$var139:(1.0 - cv$temp$11$var139))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((metric_valid_g[index$sample$48_2][timeStep$var136]?cv$temp$11$var139:(1.0 - cv$temp$11$var139)))))) + 1)) + (Math.log(1.0) + Math.log((metric_valid_g[index$sample$48_2][timeStep$var136]?cv$temp$11$var139:(1.0 - cv$temp$11$var139)))));
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
												{
													{
														boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
														for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
															if((sample == index$sample$64_1)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$64_1]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$64_1][timeStep$var136])
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																	}
																}
															}
														}
														for(int index$sample$68_1 = 0; index$sample$68_1 < noSamples; index$sample$68_1 += 1) {
															if((sample == index$sample$68_1)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$68_1]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$68_1][timeStep$var136])
																			guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = false;
																	}
																}
															}
														}
														int traceTempVariable$currentState$72_1 = distributionTempVariable$var121$37;
														for(int index$sample$72_2 = 0; index$sample$72_2 < noSamples; index$sample$72_2 += 1) {
															if((sample == index$sample$72_2)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$72_2]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																			if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							for(int var50 = 0; var50 < noStates; var50 += 1) {
																								if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																									if((var50 == st[index$sample$72_2][timeStep$var136])) {
																										if(fixedFlag$sample104) {
																											for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																												if((index$sample$137_1 == index$sample$72_2)) {
																													if((0 == timeStep$var136)) {
																														if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																	if((var66 == st[index$sample$72_2][timeStep$var136])) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																					double cv$temp$34$var148;
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$72_1];
																																						cv$temp$34$var148 = var148;
																																					}
																																					double cv$temp$35$var149;
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$72_1];
																																						cv$temp$35$var149 = var149;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$34$var148) / Math.sqrt(cv$temp$35$var149))) - (0.5 * Math.log(cv$temp$35$var149)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$34$var148) / Math.sqrt(cv$temp$35$var149))) - (0.5 * Math.log(cv$temp$35$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$34$var148) / Math.sqrt(cv$temp$35$var149))) - (0.5 * Math.log(cv$temp$35$var149))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$34$var148) / Math.sqrt(cv$temp$35$var149))) - (0.5 * Math.log(cv$temp$35$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$34$var148) / Math.sqrt(cv$temp$35$var149))) - (0.5 * Math.log(cv$temp$35$var149)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
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
																											for(int index$sample$138 = 0; index$sample$138 < noSamples; index$sample$138 += 1) {
																												if(true) {
																													for(int index$sample104$139 = 0; index$sample104$139 < noStates; index$sample104$139 += 1) {
																														int distributionTempVariable$var102$141 = index$sample104$139;
																														double cv$probabilitySample104Value140 = (1.0 * distribution$sample104[((index$sample$138 - 0) / 1)][index$sample104$139]);
																														if((index$sample$138 == index$sample$72_2)) {
																															if((0 == timeStep$var136)) {
																																if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																			if((var66 == st[index$sample$72_2][timeStep$var136])) {
																																				{
																																					{
																																						if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																							double cv$temp$36$var148;
																																							{
																																								double var148 = metric_mean[traceTempVariable$currentState$72_1];
																																								cv$temp$36$var148 = var148;
																																							}
																																							double cv$temp$37$var149;
																																							{
																																								double var149 = metric_var[traceTempVariable$currentState$72_1];
																																								cv$temp$37$var149 = var149;
																																							}
																																							if(((Math.log(cv$probabilitySample104Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$36$var148) / Math.sqrt(cv$temp$37$var149))) - (0.5 * Math.log(cv$temp$37$var149)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$36$var148) / Math.sqrt(cv$temp$37$var149))) - (0.5 * Math.log(cv$temp$37$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$36$var148) / Math.sqrt(cv$temp$37$var149))) - (0.5 * Math.log(cv$temp$37$var149))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$36$var148) / Math.sqrt(cv$temp$37$var149))) - (0.5 * Math.log(cv$temp$37$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$36$var148) / Math.sqrt(cv$temp$37$var149))) - (0.5 * Math.log(cv$temp$37$var149)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value140);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int var50 = 0; var50 < noStates; var50 += 1) {
																								if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																									if((var50 == st[index$sample$72_2][timeStep$var136])) {
																										int traceTempVariable$currentState$146_1 = distributionTempVariable$var121$37;
																										if((index$sample$23 == index$sample$72_2)) {
																											if((index$timeStep$22 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																															if((var66 == st[index$sample$72_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																			double cv$temp$38$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$146_1];
																																				cv$temp$38$var148 = var148;
																																			}
																																			double cv$temp$39$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$146_1];
																																				cv$temp$39$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$38$var148) / Math.sqrt(cv$temp$39$var149))) - (0.5 * Math.log(cv$temp$39$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$38$var148) / Math.sqrt(cv$temp$39$var149))) - (0.5 * Math.log(cv$temp$39$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$38$var148) / Math.sqrt(cv$temp$39$var149))) - (0.5 * Math.log(cv$temp$39$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$38$var148) / Math.sqrt(cv$temp$39$var149))) - (0.5 * Math.log(cv$temp$39$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$38$var148) / Math.sqrt(cv$temp$39$var149))) - (0.5 * Math.log(cv$temp$39$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										int traceTempVariable$currentState$147_1 = distributionTempVariable$var121$37;
																										if((index$sample$33 == index$sample$72_2)) {
																											if((index$timeStep$34 == timeStep$var136)) {
																												if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																															if((var66 == st[index$sample$72_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																			double cv$temp$40$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$147_1];
																																				cv$temp$40$var148 = var148;
																																			}
																																			double cv$temp$41$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$147_1];
																																				cv$temp$41$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$40$var148) / Math.sqrt(cv$temp$41$var149))) - (0.5 * Math.log(cv$temp$41$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$40$var148) / Math.sqrt(cv$temp$41$var149))) - (0.5 * Math.log(cv$temp$41$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$40$var148) / Math.sqrt(cv$temp$41$var149))) - (0.5 * Math.log(cv$temp$41$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$40$var148) / Math.sqrt(cv$temp$41$var149))) - (0.5 * Math.log(cv$temp$41$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$40$var148) / Math.sqrt(cv$temp$41$var149))) - (0.5 * Math.log(cv$temp$41$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																										for(int index$sample$148 = 0; index$sample$148 < noSamples; index$sample$148 += 1) {
																											for(int index$timeStep$149 = 1; index$timeStep$149 < length$metric[index$sample$148]; index$timeStep$149 += 1) {
																												if((!((index$sample$148 == index$sample$23) && (index$timeStep$149 == index$timeStep$22)) && !((index$sample$148 == index$sample$33) && (index$timeStep$149 == index$timeStep$34)))) {
																													for(int index$sample123$150 = 0; index$sample123$150 < noStates; index$sample123$150 += 1) {
																														int distributionTempVariable$var121$152 = index$sample123$150;
																														double cv$probabilitySample123Value151 = (1.0 * distribution$sample123[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample123$150]);
																														int traceTempVariable$currentState$153_1 = distributionTempVariable$var121$37;
																														if((index$sample$148 == index$sample$72_2)) {
																															if((index$timeStep$149 == timeStep$var136)) {
																																if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																			if((var66 == st[index$sample$72_2][timeStep$var136])) {
																																				{
																																					{
																																						if(metric_valid_g[index$sample$72_2][timeStep$var136]) {
																																							double cv$temp$42$var148;
																																							{
																																								double var148 = metric_mean[traceTempVariable$currentState$153_1];
																																								cv$temp$42$var148 = var148;
																																							}
																																							double cv$temp$43$var149;
																																							{
																																								double var149 = metric_var[traceTempVariable$currentState$153_1];
																																								cv$temp$43$var149 = var149;
																																							}
																																							if(((Math.log(cv$probabilitySample123Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$42$var148) / Math.sqrt(cv$temp$43$var149))) - (0.5 * Math.log(cv$temp$43$var149)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$42$var148) / Math.sqrt(cv$temp$43$var149))) - (0.5 * Math.log(cv$temp$43$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$42$var148) / Math.sqrt(cv$temp$43$var149))) - (0.5 * Math.log(cv$temp$43$var149))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$42$var148) / Math.sqrt(cv$temp$43$var149))) - (0.5 * Math.log(cv$temp$43$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var136] - cv$temp$42$var148) / Math.sqrt(cv$temp$43$var149))) - (0.5 * Math.log(cv$temp$43$var149)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value151);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
														int traceTempVariable$currentState$76_1 = distributionTempVariable$var121$37;
														for(int index$sample$76_2 = 0; index$sample$76_2 < noSamples; index$sample$76_2 += 1) {
															if((sample == index$sample$76_2)) {
																for(int timeStep$var136 = 0; timeStep$var136 < length$metric[index$sample$76_2]; timeStep$var136 += 1) {
																	if((timeStep$var113 == timeStep$var136)) {
																		if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																			if(!guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)]) {
																				guard$sample123gaussian156[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample104) {
																								for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																									if((index$sample$213_1 == index$sample$76_2)) {
																										if((0 == timeStep$var136)) {
																											if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																												for(int var50 = 0; var50 < noStates; var50 += 1) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																														if((var50 == st[index$sample$76_2][timeStep$var136])) {
																															for(int var66 = 0; var66 < noStates; var66 += 1) {
																																if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																	if((var66 == st[index$sample$76_2][timeStep$var136])) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																					double cv$temp$66$var148;
																																					{
																																						double var148 = metric_mean[traceTempVariable$currentState$76_1];
																																						cv$temp$66$var148 = var148;
																																					}
																																					double cv$temp$67$var149;
																																					{
																																						double var149 = metric_var[traceTempVariable$currentState$76_1];
																																						cv$temp$67$var149 = var149;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$66$var148) / Math.sqrt(cv$temp$67$var149))) - (0.5 * Math.log(cv$temp$67$var149)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$66$var148) / Math.sqrt(cv$temp$67$var149))) - (0.5 * Math.log(cv$temp$67$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$66$var148) / Math.sqrt(cv$temp$67$var149))) - (0.5 * Math.log(cv$temp$67$var149))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$66$var148) / Math.sqrt(cv$temp$67$var149))) - (0.5 * Math.log(cv$temp$67$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$66$var148) / Math.sqrt(cv$temp$67$var149))) - (0.5 * Math.log(cv$temp$67$var149)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																			}
																																		}
																																	}
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
																								for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																									if(true) {
																										for(int index$sample104$215 = 0; index$sample104$215 < noStates; index$sample104$215 += 1) {
																											int distributionTempVariable$var102$217 = index$sample104$215;
																											double cv$probabilitySample104Value216 = (1.0 * distribution$sample104[((index$sample$214 - 0) / 1)][index$sample104$215]);
																											if((index$sample$214 == index$sample$76_2)) {
																												if((0 == timeStep$var136)) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																														for(int var50 = 0; var50 < noStates; var50 += 1) {
																															if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																if((var50 == st[index$sample$76_2][timeStep$var136])) {
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																			if((var66 == st[index$sample$76_2][timeStep$var136])) {
																																				{
																																					{
																																						if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																							double cv$temp$68$var148;
																																							{
																																								double var148 = metric_mean[traceTempVariable$currentState$76_1];
																																								cv$temp$68$var148 = var148;
																																							}
																																							double cv$temp$69$var149;
																																							{
																																								double var149 = metric_var[traceTempVariable$currentState$76_1];
																																								cv$temp$69$var149 = var149;
																																							}
																																							if(((Math.log(cv$probabilitySample104Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$68$var148) / Math.sqrt(cv$temp$69$var149))) - (0.5 * Math.log(cv$temp$69$var149)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$68$var148) / Math.sqrt(cv$temp$69$var149))) - (0.5 * Math.log(cv$temp$69$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$68$var148) / Math.sqrt(cv$temp$69$var149))) - (0.5 * Math.log(cv$temp$69$var149))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$68$var148) / Math.sqrt(cv$temp$69$var149))) - (0.5 * Math.log(cv$temp$69$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$68$var148) / Math.sqrt(cv$temp$69$var149))) - (0.5 * Math.log(cv$temp$69$var149)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value216);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$223_1 = distributionTempVariable$var121$37;
																							if((index$sample$23 == index$sample$76_2)) {
																								if((index$timeStep$22 == timeStep$var136)) {
																									if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																												if((var50 == st[index$sample$76_2][timeStep$var136])) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																															if((var66 == st[index$sample$76_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																			double cv$temp$70$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$223_1];
																																				cv$temp$70$var148 = var148;
																																			}
																																			double cv$temp$71$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$223_1];
																																				cv$temp$71$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$70$var148) / Math.sqrt(cv$temp$71$var149))) - (0.5 * Math.log(cv$temp$71$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$70$var148) / Math.sqrt(cv$temp$71$var149))) - (0.5 * Math.log(cv$temp$71$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$70$var148) / Math.sqrt(cv$temp$71$var149))) - (0.5 * Math.log(cv$temp$71$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$70$var148) / Math.sqrt(cv$temp$71$var149))) - (0.5 * Math.log(cv$temp$71$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$70$var148) / Math.sqrt(cv$temp$71$var149))) - (0.5 * Math.log(cv$temp$71$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$224_1 = distributionTempVariable$var121$37;
																							if((index$sample$33 == index$sample$76_2)) {
																								if((index$timeStep$34 == timeStep$var136)) {
																									if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																										for(int var50 = 0; var50 < noStates; var50 += 1) {
																											if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																												if((var50 == st[index$sample$76_2][timeStep$var136])) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																															if((var66 == st[index$sample$76_2][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																			double cv$temp$72$var148;
																																			{
																																				double var148 = metric_mean[traceTempVariable$currentState$224_1];
																																				cv$temp$72$var148 = var148;
																																			}
																																			double cv$temp$73$var149;
																																			{
																																				double var149 = metric_var[traceTempVariable$currentState$224_1];
																																				cv$temp$73$var149 = var149;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$72$var148) / Math.sqrt(cv$temp$73$var149))) - (0.5 * Math.log(cv$temp$73$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$72$var148) / Math.sqrt(cv$temp$73$var149))) - (0.5 * Math.log(cv$temp$73$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$72$var148) / Math.sqrt(cv$temp$73$var149))) - (0.5 * Math.log(cv$temp$73$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$72$var148) / Math.sqrt(cv$temp$73$var149))) - (0.5 * Math.log(cv$temp$73$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$72$var148) / Math.sqrt(cv$temp$73$var149))) - (0.5 * Math.log(cv$temp$73$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																								for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225]; index$timeStep$226 += 1) {
																									if((!((index$sample$225 == index$sample$23) && (index$timeStep$226 == index$timeStep$22)) && !((index$sample$225 == index$sample$33) && (index$timeStep$226 == index$timeStep$34)))) {
																										for(int index$sample123$227 = 0; index$sample123$227 < noStates; index$sample123$227 += 1) {
																											int distributionTempVariable$var121$229 = index$sample123$227;
																											double cv$probabilitySample123Value228 = (1.0 * distribution$sample123[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample123$227]);
																											int traceTempVariable$currentState$230_1 = distributionTempVariable$var121$37;
																											if((index$sample$225 == index$sample$76_2)) {
																												if((index$timeStep$226 == timeStep$var136)) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																														for(int var50 = 0; var50 < noStates; var50 += 1) {
																															if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																if((var50 == st[index$sample$76_2][timeStep$var136])) {
																																	for(int var66 = 0; var66 < noStates; var66 += 1) {
																																		if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																			if((var66 == st[index$sample$76_2][timeStep$var136])) {
																																				{
																																					{
																																						if(metric_valid_g[index$sample$76_2][timeStep$var136]) {
																																							double cv$temp$74$var148;
																																							{
																																								double var148 = metric_mean[traceTempVariable$currentState$230_1];
																																								cv$temp$74$var148 = var148;
																																							}
																																							double cv$temp$75$var149;
																																							{
																																								double var149 = metric_var[traceTempVariable$currentState$230_1];
																																								cv$temp$75$var149 = var149;
																																							}
																																							if(((Math.log(cv$probabilitySample123Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$74$var148) / Math.sqrt(cv$temp$75$var149))) - (0.5 * Math.log(cv$temp$75$var149)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$74$var148) / Math.sqrt(cv$temp$75$var149))) - (0.5 * Math.log(cv$temp$75$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$74$var148) / Math.sqrt(cv$temp$75$var149))) - (0.5 * Math.log(cv$temp$75$var149))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$74$var148) / Math.sqrt(cv$temp$75$var149))) - (0.5 * Math.log(cv$temp$75$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var136] - cv$temp$74$var148) / Math.sqrt(cv$temp$75$var149))) - (0.5 * Math.log(cv$temp$75$var149)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value228);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
				{
					{
						int traceTempVariable$var118$269_1 = cv$currentValue;
						for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
							if((sample == index$sample$269_2)) {
								for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2]; index$timeStep$269_3 += 1) {
									if((timeStep$var113 == (index$timeStep$269_3 - 1))) {
										{
											int index$timeStep$271 = index$timeStep$269_3;
											int index$sample$272 = index$sample$269_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120[threadID$cv$sample];
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == st[index$sample$269_2][(index$timeStep$269_3 - 1)])) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														if(fixedFlag$sample104) {
															for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
																if((index$sample$274_1 == sample)) {
																	if((0 == (timeStep$var113 - 1))) {
																		for(int index$var31$280_1 = 0; index$var31$280_1 < noStates; index$var31$280_1 += 1) {
																			if((index$var31$280_1 == st[sample][(timeStep$var113 - 1)]))
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																	}
																}
															}
														} else {
															for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
																if(true) {
																	for(int index$sample104$276 = 0; index$sample104$276 < noStates; index$sample104$276 += 1) {
																		int distributionTempVariable$var102$278 = index$sample104$276;
																		double cv$probabilitySample104Value277 = (1.0 * distribution$sample104[((index$sample$275 - 0) / 1)][index$sample104$276]);
																		if((index$sample$275 == sample)) {
																			if((0 == (timeStep$var113 - 1))) {
																				for(int index$var31$281_1 = 0; index$var31$281_1 < noStates; index$var31$281_1 += 1) {
																					if((index$var31$281_1 == st[sample][(timeStep$var113 - 1)]))
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample104Value277);
																				}
																			}
																		}
																	}
																}
															}
														}
														int traceTempVariable$var118$282_1 = cv$currentValue;
														if((index$sample$23 == sample)) {
															if((index$timeStep$22 == (timeStep$var113 - 1))) {
																for(int index$var31$289_1 = 0; index$var31$289_1 < noStates; index$var31$289_1 += 1) {
																	if((index$var31$289_1 == st[sample][(timeStep$var113 - 1)]))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
														for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
															for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283]; index$timeStep$284 += 1) {
																if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																	for(int index$sample123$285 = 0; index$sample123$285 < noStates; index$sample123$285 += 1) {
																		int distributionTempVariable$var121$287 = index$sample123$285;
																		double cv$probabilitySample123Value286 = (1.0 * distribution$sample123[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample123$285]);
																		int traceTempVariable$var118$288_1 = cv$currentValue;
																		if((index$sample$283 == sample)) {
																			if((index$timeStep$284 == (timeStep$var113 - 1))) {
																				for(int index$var31$290_1 = 0; index$var31$290_1 < noStates; index$var31$290_1 += 1) {
																					if((index$var31$290_1 == st[sample][(timeStep$var113 - 1)]))
																						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample123Value286);
																				}
																			}
																		}
																	}
																}
															}
														}
														double[] cv$temp$76$var119;
														{
															double[] var119 = m[traceTempVariable$var118$269_1];
															cv$temp$76$var119 = var119;
														}
														int cv$temp$77$$var3232;
														{
															int $var3232 = noStates;
															cv$temp$77$$var3232 = $var3232;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$76$var119, cv$temp$77$$var3232);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample123[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
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
			double[] cv$localProbability = distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
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

	private final void sample19() {
		if(true) {
			double[] cv$targetLocal = initialStateDistribution;
			double[] cv$countLocal = cv$var19$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							if(fixedFlag$sample104) {
								{
									int index$sample$3 = sample;
									{
										{
											{
												{
													cv$countLocal[st[sample][0]] = (cv$countLocal[st[sample][0]] + 1.0);
												}
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
					for(int sample = 0; sample < noSamples; sample += 1) {
						if(!fixedFlag$sample104) {
							{
								int index$sample$7 = sample;
								{
									{
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample104[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
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

	private final void sample32(int var31, int threadID$cv$var31, Rng RNG$) {
		if(true) {
			double[] cv$targetLocal = m[var31];
			double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								if(fixedFlag$sample104) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == (timeStep$var113 - 1))) {
												if((var31 == st[sample][(timeStep$var113 - 1)])) {
													if(fixedFlag$sample123) {
														{
															int index$timeStep$23 = timeStep$var113;
															int index$sample$24 = sample;
															{
																{
																	{
																		{
																			cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + 1.0);
																		}
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
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
												int distributionTempVariable$var102$7 = index$sample104$5;
												double cv$probabilitySample104Value6 = (1.0 * distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
												if((index$sample$4 == sample)) {
													if((0 == (timeStep$var113 - 1))) {
														if((var31 == st[sample][(timeStep$var113 - 1)])) {
															if(fixedFlag$sample123) {
																{
																	int index$timeStep$26 = timeStep$var113;
																	int index$sample$27 = sample;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + cv$probabilitySample104Value6);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								if(fixedFlag$sample123) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
											if((index$sample$13_1 == sample)) {
												if((index$timeStep$13_2 == (timeStep$var113 - 1))) {
													if((var31 == st[sample][(timeStep$var113 - 1)])) {
														if(fixedFlag$sample123) {
															{
																int index$timeStep$29 = timeStep$var113;
																int index$sample$30 = sample;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + 1.0);
																			}
																		}
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
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
											if(true) {
												for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * distribution$sample123[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample123$16]);
													if((index$sample$14 == sample)) {
														if((index$timeStep$15 == (timeStep$var113 - 1))) {
															if((var31 == st[sample][(timeStep$var113 - 1)])) {
																if(fixedFlag$sample123) {
																	{
																		int index$timeStep$32 = timeStep$var113;
																		int index$sample$33 = sample;
																		{
																			{
																				{
																					{
																						cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + cv$probabilitySample123Value17);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
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
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(fixedFlag$sample104) {
								for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
									if((index$sample$40_1 == sample)) {
										if((0 == (timeStep$var113 - 1))) {
											if((var31 == st[sample][(timeStep$var113 - 1)])) {
												if(!fixedFlag$sample123) {
													{
														int index$timeStep$60 = timeStep$var113;
														int index$sample$61 = sample;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$41 = 0; index$sample$41 < noSamples; index$sample$41 += 1) {
									if(true) {
										for(int index$sample104$42 = 0; index$sample104$42 < noStates; index$sample104$42 += 1) {
											int distributionTempVariable$var102$44 = index$sample104$42;
											double cv$probabilitySample104Value43 = (1.0 * distribution$sample104[((index$sample$41 - 0) / 1)][index$sample104$42]);
											if((index$sample$41 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													if((var31 == st[sample][(timeStep$var113 - 1)])) {
														if(!fixedFlag$sample123) {
															{
																int index$timeStep$63 = timeStep$var113;
																int index$sample$64 = sample;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample104Value43);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(fixedFlag$sample123) {
								for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
									for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
										if((index$sample$50_1 == sample)) {
											if((index$timeStep$50_2 == (timeStep$var113 - 1))) {
												if((var31 == st[sample][(timeStep$var113 - 1)])) {
													if(!fixedFlag$sample123) {
														{
															int index$timeStep$66 = timeStep$var113;
															int index$sample$67 = sample;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample$51 = 0; index$sample$51 < noSamples; index$sample$51 += 1) {
									for(int index$timeStep$52 = 1; index$timeStep$52 < length$metric[index$sample$51]; index$timeStep$52 += 1) {
										if(true) {
											for(int index$sample123$53 = 0; index$sample123$53 < noStates; index$sample123$53 += 1) {
												int distributionTempVariable$var121$55 = index$sample123$53;
												double cv$probabilitySample123Value54 = (1.0 * distribution$sample123[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample123$53]);
												if((index$sample$51 == sample)) {
													if((index$timeStep$52 == (timeStep$var113 - 1))) {
														if((var31 == st[sample][(timeStep$var113 - 1)])) {
															if(!fixedFlag$sample123) {
																{
																	int index$timeStep$69 = timeStep$var113;
																	int index$sample$70 = sample;
																	{
																		{
																			double scopeVariable$reachedSourceProbability = 0.0;
																			{
																				scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																			}
																			double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample123Value54);
																			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																				cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																		}
																	}
																}
															}
														}
													}
												}
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

	private final void sample52(int var50, int threadID$cv$var50, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double cv$originalValue = metric_mean[var50];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				double cv$currentValue;
				if((cv$valuePos == 0))
					cv$currentValue = cv$originalValue;
				else {
					cv$currentValue = cv$proposedValue;
					{
						double var51 = cv$proposedValue;
						{
							{
								metric_mean[var50] = cv$currentValue;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var37;
					{
						cv$temp$0$var37 = 0.0;
					}
					double cv$temp$1$var38;
					{
						cv$temp$1$var38 = 100.0;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var37 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var38))?(-Math.log((cv$temp$1$var38 - cv$temp$0$var37))):Double.NEGATIVE_INFINITY));
					{
						{
							for(int sample = 0; sample < noSamples; sample += 1) {
								for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
									if(fixedFlag$sample104) {
										for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
											if((index$sample$4_1 == sample)) {
												if((0 == timeStep$var136)) {
													if(metric_valid_g[sample][timeStep$var136]) {
														double traceTempVariable$var148$10_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var136]) {
															if((var50 == st[sample][timeStep$var136])) {
																{
																	if(metric_valid_g[sample][timeStep$var136]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int index$sample$27_1 = 0; index$sample$27_1 < noSamples; index$sample$27_1 += 1) {
																				if((index$sample$27_1 == sample)) {
																					if((0 == timeStep$var136)) {
																						if(metric_valid_g[sample][timeStep$var136]) {
																							for(int var66 = 0; var66 < noStates; var66 += 1) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									if((var66 == st[sample][timeStep$var136])) {
																										{
																											{
																												if(metric_valid_g[sample][timeStep$var136]) {
																													double cv$temp$2$var148;
																													{
																														double var148 = traceTempVariable$var148$10_1;
																														cv$temp$2$var148 = var148;
																													}
																													double cv$temp$3$var149;
																													{
																														double var149 = metric_var[st[sample][timeStep$var136]];
																														cv$temp$3$var149 = var149;
																													}
																													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			if(fixedFlag$sample123) {
																				for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																						if((index$sample$29_1 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var66 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$4$var148;
																															{
																																double var148 = traceTempVariable$var148$10_1;
																																cv$temp$4$var148 = var148;
																															}
																															double cv$temp$5$var149;
																															{
																																double var149 = metric_var[st[sample][timeStep$var136]];
																																cv$temp$5$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
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
																				for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$30]; timeStep$var113 += 1) {
																						if(true) {
																							for(int index$sample123$32 = 0; index$sample123$32 < noStates; index$sample123$32 += 1) {
																								int distributionTempVariable$var121$34 = index$sample123$32;
																								double cv$probabilitySample123Value33 = (1.0 * distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																								if((index$sample$30 == sample)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var66 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$6$var148;
																																	{
																																		double var148 = traceTempVariable$var148$10_1;
																																		cv$temp$6$var148 = var148;
																																	}
																																	double cv$temp$7$var149;
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		cv$temp$7$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value33);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
									} else {
										for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
											if(true) {
												for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
													int distributionTempVariable$var102$8 = index$sample104$6;
													double cv$probabilitySample104Value7 = (1.0 * distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
													if((index$sample$5 == sample)) {
														if((0 == timeStep$var136)) {
															if(metric_valid_g[sample][timeStep$var136]) {
																double traceTempVariable$var148$11_1 = cv$currentValue;
																if(metric_valid_g[sample][timeStep$var136]) {
																	if((var50 == st[sample][timeStep$var136])) {
																		{
																			if(metric_valid_g[sample][timeStep$var136]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if((index$sample$5 == sample)) {
																						if((0 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var66 = 0; var66 < noStates; var66 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var66 == st[sample][timeStep$var136])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var136]) {
																														double cv$temp$8$var148;
																														{
																															double var148 = traceTempVariable$var148$11_1;
																															cv$temp$8$var148 = var148;
																														}
																														double cv$temp$9$var149;
																														{
																															double var149 = metric_var[st[sample][timeStep$var136]];
																															cv$temp$9$var149 = var149;
																														}
																														if(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																						if(!(index$sample$39 == index$sample$5)) {
																							for(int index$sample104$40 = 0; index$sample104$40 < noStates; index$sample104$40 += 1) {
																								int distributionTempVariable$var102$42 = index$sample104$40;
																								double cv$probabilitySample104Value41 = (cv$probabilitySample104Value7 * distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																								if((index$sample$39 == sample)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var66 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$10$var148;
																																	{
																																		double var148 = traceTempVariable$var148$11_1;
																																		cv$temp$10$var148 = var148;
																																	}
																																	double cv$temp$11$var149;
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		cv$temp$11$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value41);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					if(fixedFlag$sample123) {
																						for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
																							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$46_1]; timeStep$var113 += 1) {
																								if((index$sample$46_1 == sample)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var66 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$12$var148;
																																	{
																																		double var148 = traceTempVariable$var148$11_1;
																																		cv$temp$12$var148 = var148;
																																	}
																																	double cv$temp$13$var149;
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		cv$temp$13$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
																																}
																															}
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
																						for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
																							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47]; timeStep$var113 += 1) {
																								if(true) {
																									for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
																										int distributionTempVariable$var121$51 = index$sample123$49;
																										double cv$probabilitySample123Value50 = (cv$probabilitySample104Value7 * distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
																										if((index$sample$47 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[sample][timeStep$var136]) {
																															if((var66 == st[sample][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[sample][timeStep$var136]) {
																																			double cv$temp$14$var148;
																																			{
																																				double var148 = traceTempVariable$var148$11_1;
																																				cv$temp$14$var148 = var148;
																																			}
																																			double cv$temp$15$var149;
																																			{
																																				double var149 = metric_var[st[sample][timeStep$var136]];
																																				cv$temp$15$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value50);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
								}
							}
							for(int sample = 0; sample < noSamples; sample += 1) {
								for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
									if(fixedFlag$sample123) {
										for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$14_1]; timeStep$var113 += 1) {
												if((index$sample$14_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														if(metric_valid_g[sample][timeStep$var136]) {
															double traceTempVariable$var148$21_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var136]) {
																if((var50 == st[sample][timeStep$var136])) {
																	{
																		if(metric_valid_g[sample][timeStep$var136]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				if(fixedFlag$sample104) {
																					for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																						if((index$sample$55_1 == sample)) {
																							if((0 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var66 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$16$var148;
																															{
																																double var148 = traceTempVariable$var148$21_1;
																																cv$temp$16$var148 = var148;
																															}
																															double cv$temp$17$var149;
																															{
																																double var149 = metric_var[st[sample][timeStep$var136]];
																																cv$temp$17$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
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
																					for(int index$sample$56 = 0; index$sample$56 < noSamples; index$sample$56 += 1) {
																						if(true) {
																							for(int index$sample104$57 = 0; index$sample104$57 < noStates; index$sample104$57 += 1) {
																								int distributionTempVariable$var102$59 = index$sample104$57;
																								double cv$probabilitySample104Value58 = (1.0 * distribution$sample104[((index$sample$56 - 0) / 1)][index$sample104$57]);
																								if((index$sample$56 == sample)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var66 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$18$var148;
																																	{
																																		double var148 = traceTempVariable$var148$21_1;
																																		cv$temp$18$var148 = var148;
																																	}
																																	double cv$temp$19$var149;
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		cv$temp$19$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value58);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
																					for(int index$timeStep$63_2 = 1; index$timeStep$63_2 < length$metric[index$sample$63_1]; index$timeStep$63_2 += 1) {
																						if((index$sample$63_1 == sample)) {
																							if((index$timeStep$63_2 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var66 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$20$var148;
																															{
																																double var148 = traceTempVariable$var148$21_1;
																																cv$temp$20$var148 = var148;
																															}
																															double cv$temp$21$var149;
																															{
																																double var149 = metric_var[st[sample][timeStep$var136]];
																																cv$temp$21$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
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
									} else {
										for(int index$sample$15 = 0; index$sample$15 < noSamples; index$sample$15 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$15]; timeStep$var113 += 1) {
												if(true) {
													for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
														int distributionTempVariable$var121$19 = index$sample123$17;
														double cv$probabilitySample123Value18 = (1.0 * distribution$sample123[((index$sample$15 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$17]);
														if((index$sample$15 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																if(metric_valid_g[sample][timeStep$var136]) {
																	double traceTempVariable$var148$22_1 = cv$currentValue;
																	if(metric_valid_g[sample][timeStep$var136]) {
																		if((var50 == st[sample][timeStep$var136])) {
																			{
																				if(metric_valid_g[sample][timeStep$var136]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample104) {
																							for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																								if((index$sample$65_1 == sample)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var66 = 0; var66 < noStates; var66 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var66 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$22$var148;
																																	{
																																		double var148 = traceTempVariable$var148$22_1;
																																		cv$temp$22$var148 = var148;
																																	}
																																	double cv$temp$23$var149;
																																	{
																																		double var149 = metric_var[st[sample][timeStep$var136]];
																																		cv$temp$23$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
																																}
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
																							for(int index$sample$66 = 0; index$sample$66 < noSamples; index$sample$66 += 1) {
																								if(true) {
																									for(int index$sample104$67 = 0; index$sample104$67 < noStates; index$sample104$67 += 1) {
																										int distributionTempVariable$var102$69 = index$sample104$67;
																										double cv$probabilitySample104Value68 = (cv$probabilitySample123Value18 * distribution$sample104[((index$sample$66 - 0) / 1)][index$sample104$67]);
																										if((index$sample$66 == sample)) {
																											if((0 == timeStep$var136)) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[sample][timeStep$var136]) {
																															if((var66 == st[sample][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[sample][timeStep$var136]) {
																																			double cv$temp$24$var148;
																																			{
																																				double var148 = traceTempVariable$var148$22_1;
																																				cv$temp$24$var148 = var148;
																																			}
																																			double cv$temp$25$var149;
																																			{
																																				double var149 = metric_var[st[sample][timeStep$var136]];
																																				cv$temp$25$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value68);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						if((index$sample$15 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var66 = 0; var66 < noStates; var66 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var66 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$26$var148;
																															{
																																double var148 = traceTempVariable$var148$22_1;
																																cv$temp$26$var148 = var148;
																															}
																															double cv$temp$27$var149;
																															{
																																double var149 = metric_var[st[sample][timeStep$var136]];
																																cv$temp$27$var149 = var149;
																															}
																															if(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$74 = 0; index$sample$74 < noSamples; index$sample$74 += 1) {
																							for(int index$timeStep$75 = 1; index$timeStep$75 < length$metric[index$sample$74]; index$timeStep$75 += 1) {
																								if(!((index$sample$74 == index$sample$15) && (index$timeStep$75 == timeStep$var113))) {
																									for(int index$sample123$76 = 0; index$sample123$76 < noStates; index$sample123$76 += 1) {
																										int distributionTempVariable$var121$78 = index$sample123$76;
																										double cv$probabilitySample123Value77 = (cv$probabilitySample123Value18 * distribution$sample123[((index$sample$74 - 0) / 1)][((index$timeStep$75 - 1) / 1)][index$sample123$76]);
																										if((index$sample$74 == sample)) {
																											if((index$timeStep$75 == timeStep$var136)) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													for(int var66 = 0; var66 < noStates; var66 += 1) {
																														if(metric_valid_g[sample][timeStep$var136]) {
																															if((var66 == st[sample][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[sample][timeStep$var136]) {
																																			double cv$temp$28$var148;
																																			{
																																				double var148 = traceTempVariable$var148$22_1;
																																				cv$temp$28$var148 = var148;
																																			}
																																			double cv$temp$29$var149;
																																			{
																																				double var149 = metric_var[st[sample][timeStep$var136]];
																																				cv$temp$29$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value77);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
				if((cv$valuePos == 0))
					cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
				else
					cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double cv$ratio = (cv$proposedProbability - cv$originalProbability);
			if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
				double var51 = cv$originalValue;
				{
					{
						metric_mean[var50] = var51;
					}
				}
			}
		}
	}

	private final void sample68(int var66, int threadID$cv$var66, Rng RNG$) {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, 2);
			}
			double cv$originalValue = metric_var[var66];
			double cv$originalProbability = 0.0;
			double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
			if((cv$var < (0.1 * 0.1)))
				cv$var = (0.1 * 0.1);
			double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
			double cv$proposedProbability = 0.0;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				double cv$currentValue;
				if((cv$valuePos == 0))
					cv$currentValue = cv$originalValue;
				else {
					cv$currentValue = cv$proposedValue;
					{
						double var67 = cv$proposedValue;
						{
							{
								metric_var[var66] = cv$currentValue;
							}
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$temp$0$var53;
					{
						cv$temp$0$var53 = 1.0;
					}
					double cv$temp$1$var54;
					{
						cv$temp$1$var54 = 1.0;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var53, cv$temp$1$var54));
					{
						{
							for(int sample = 0; sample < noSamples; sample += 1) {
								for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
									if(fixedFlag$sample104) {
										for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
											if((index$sample$4_1 == sample)) {
												if((0 == timeStep$var136)) {
													if(metric_valid_g[sample][timeStep$var136]) {
														double traceTempVariable$var149$10_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var136]) {
															if((var66 == st[sample][timeStep$var136])) {
																{
																	if(metric_valid_g[sample][timeStep$var136]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int index$sample$27_1 = 0; index$sample$27_1 < noSamples; index$sample$27_1 += 1) {
																				if((index$sample$27_1 == sample)) {
																					if((0 == timeStep$var136)) {
																						if(metric_valid_g[sample][timeStep$var136]) {
																							for(int var50 = 0; var50 < noStates; var50 += 1) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									if((var50 == st[sample][timeStep$var136])) {
																										{
																											{
																												if(metric_valid_g[sample][timeStep$var136]) {
																													double cv$temp$2$var148;
																													{
																														double var148 = metric_mean[st[sample][timeStep$var136]];
																														cv$temp$2$var148 = var148;
																													}
																													double cv$temp$3$var149;
																													{
																														double var149 = traceTempVariable$var149$10_1;
																														cv$temp$3$var149 = var149;
																													}
																													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$2$var148) / Math.sqrt(cv$temp$3$var149))) - (0.5 * Math.log(cv$temp$3$var149)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			if(fixedFlag$sample123) {
																				for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$29_1]; timeStep$var113 += 1) {
																						if((index$sample$29_1 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var50 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$4$var148;
																															{
																																double var148 = metric_mean[st[sample][timeStep$var136]];
																																cv$temp$4$var148 = var148;
																															}
																															double cv$temp$5$var149;
																															{
																																double var149 = traceTempVariable$var149$10_1;
																																cv$temp$5$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$4$var148) / Math.sqrt(cv$temp$5$var149))) - (0.5 * Math.log(cv$temp$5$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
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
																				for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$30]; timeStep$var113 += 1) {
																						if(true) {
																							for(int index$sample123$32 = 0; index$sample123$32 < noStates; index$sample123$32 += 1) {
																								int distributionTempVariable$var121$34 = index$sample123$32;
																								double cv$probabilitySample123Value33 = (1.0 * distribution$sample123[((index$sample$30 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$32]);
																								if((index$sample$30 == sample)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var50 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$6$var148;
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		cv$temp$6$var148 = var148;
																																	}
																																	double cv$temp$7$var149;
																																	{
																																		double var149 = traceTempVariable$var149$10_1;
																																		cv$temp$7$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$6$var148) / Math.sqrt(cv$temp$7$var149))) - (0.5 * Math.log(cv$temp$7$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value33);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
									} else {
										for(int index$sample$5 = 0; index$sample$5 < noSamples; index$sample$5 += 1) {
											if(true) {
												for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
													int distributionTempVariable$var102$8 = index$sample104$6;
													double cv$probabilitySample104Value7 = (1.0 * distribution$sample104[((index$sample$5 - 0) / 1)][index$sample104$6]);
													if((index$sample$5 == sample)) {
														if((0 == timeStep$var136)) {
															if(metric_valid_g[sample][timeStep$var136]) {
																double traceTempVariable$var149$11_1 = cv$currentValue;
																if(metric_valid_g[sample][timeStep$var136]) {
																	if((var66 == st[sample][timeStep$var136])) {
																		{
																			if(metric_valid_g[sample][timeStep$var136]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if((index$sample$5 == sample)) {
																						if((0 == timeStep$var136)) {
																							if(metric_valid_g[sample][timeStep$var136]) {
																								for(int var50 = 0; var50 < noStates; var50 += 1) {
																									if(metric_valid_g[sample][timeStep$var136]) {
																										if((var50 == st[sample][timeStep$var136])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var136]) {
																														double cv$temp$8$var148;
																														{
																															double var148 = metric_mean[st[sample][timeStep$var136]];
																															cv$temp$8$var148 = var148;
																														}
																														double cv$temp$9$var149;
																														{
																															double var149 = traceTempVariable$var149$11_1;
																															cv$temp$9$var149 = var149;
																														}
																														if(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$8$var148) / Math.sqrt(cv$temp$9$var149))) - (0.5 * Math.log(cv$temp$9$var149)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																						if(!(index$sample$39 == index$sample$5)) {
																							for(int index$sample104$40 = 0; index$sample104$40 < noStates; index$sample104$40 += 1) {
																								int distributionTempVariable$var102$42 = index$sample104$40;
																								double cv$probabilitySample104Value41 = (cv$probabilitySample104Value7 * distribution$sample104[((index$sample$39 - 0) / 1)][index$sample104$40]);
																								if((index$sample$39 == sample)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var50 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$10$var148;
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		cv$temp$10$var148 = var148;
																																	}
																																	double cv$temp$11$var149;
																																	{
																																		double var149 = traceTempVariable$var149$11_1;
																																		cv$temp$11$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$10$var148) / Math.sqrt(cv$temp$11$var149))) - (0.5 * Math.log(cv$temp$11$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value41);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					if(fixedFlag$sample123) {
																						for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
																							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$46_1]; timeStep$var113 += 1) {
																								if((index$sample$46_1 == sample)) {
																									if((timeStep$var113 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var50 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$12$var148;
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		cv$temp$12$var148 = var148;
																																	}
																																	double cv$temp$13$var149;
																																	{
																																		double var149 = traceTempVariable$var149$11_1;
																																		cv$temp$13$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$12$var148) / Math.sqrt(cv$temp$13$var149))) - (0.5 * Math.log(cv$temp$13$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value7);
																																}
																															}
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
																						for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
																							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$47]; timeStep$var113 += 1) {
																								if(true) {
																									for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
																										int distributionTempVariable$var121$51 = index$sample123$49;
																										double cv$probabilitySample123Value50 = (cv$probabilitySample104Value7 * distribution$sample123[((index$sample$47 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$49]);
																										if((index$sample$47 == sample)) {
																											if((timeStep$var113 == timeStep$var136)) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if(metric_valid_g[sample][timeStep$var136]) {
																															if((var50 == st[sample][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[sample][timeStep$var136]) {
																																			double cv$temp$14$var148;
																																			{
																																				double var148 = metric_mean[st[sample][timeStep$var136]];
																																				cv$temp$14$var148 = var148;
																																			}
																																			double cv$temp$15$var149;
																																			{
																																				double var149 = traceTempVariable$var149$11_1;
																																				cv$temp$15$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value50) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$14$var148) / Math.sqrt(cv$temp$15$var149))) - (0.5 * Math.log(cv$temp$15$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value50);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
								}
							}
							for(int sample = 0; sample < noSamples; sample += 1) {
								for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
									if(fixedFlag$sample123) {
										for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$14_1]; timeStep$var113 += 1) {
												if((index$sample$14_1 == sample)) {
													if((timeStep$var113 == timeStep$var136)) {
														if(metric_valid_g[sample][timeStep$var136]) {
															double traceTempVariable$var149$21_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var136]) {
																if((var66 == st[sample][timeStep$var136])) {
																	{
																		if(metric_valid_g[sample][timeStep$var136]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				if(fixedFlag$sample104) {
																					for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																						if((index$sample$55_1 == sample)) {
																							if((0 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var50 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$16$var148;
																															{
																																double var148 = metric_mean[st[sample][timeStep$var136]];
																																cv$temp$16$var148 = var148;
																															}
																															double cv$temp$17$var149;
																															{
																																double var149 = traceTempVariable$var149$21_1;
																																cv$temp$17$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$16$var148) / Math.sqrt(cv$temp$17$var149))) - (0.5 * Math.log(cv$temp$17$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
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
																					for(int index$sample$56 = 0; index$sample$56 < noSamples; index$sample$56 += 1) {
																						if(true) {
																							for(int index$sample104$57 = 0; index$sample104$57 < noStates; index$sample104$57 += 1) {
																								int distributionTempVariable$var102$59 = index$sample104$57;
																								double cv$probabilitySample104Value58 = (1.0 * distribution$sample104[((index$sample$56 - 0) / 1)][index$sample104$57]);
																								if((index$sample$56 == sample)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var50 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$18$var148;
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		cv$temp$18$var148 = var148;
																																	}
																																	double cv$temp$19$var149;
																																	{
																																		double var149 = traceTempVariable$var149$21_1;
																																		cv$temp$19$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value58) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$18$var148) / Math.sqrt(cv$temp$19$var149))) - (0.5 * Math.log(cv$temp$19$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value58);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
																					for(int index$timeStep$63_2 = 1; index$timeStep$63_2 < length$metric[index$sample$63_1]; index$timeStep$63_2 += 1) {
																						if((index$sample$63_1 == sample)) {
																							if((index$timeStep$63_2 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var50 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$20$var148;
																															{
																																double var148 = metric_mean[st[sample][timeStep$var136]];
																																cv$temp$20$var148 = var148;
																															}
																															double cv$temp$21$var149;
																															{
																																double var149 = traceTempVariable$var149$21_1;
																																cv$temp$21$var149 = var149;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$20$var148) / Math.sqrt(cv$temp$21$var149))) - (0.5 * Math.log(cv$temp$21$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																														}
																													}
																												}
																											}
																										}
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
									} else {
										for(int index$sample$15 = 0; index$sample$15 < noSamples; index$sample$15 += 1) {
											for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$15]; timeStep$var113 += 1) {
												if(true) {
													for(int index$sample123$17 = 0; index$sample123$17 < noStates; index$sample123$17 += 1) {
														int distributionTempVariable$var121$19 = index$sample123$17;
														double cv$probabilitySample123Value18 = (1.0 * distribution$sample123[((index$sample$15 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$17]);
														if((index$sample$15 == sample)) {
															if((timeStep$var113 == timeStep$var136)) {
																if(metric_valid_g[sample][timeStep$var136]) {
																	double traceTempVariable$var149$22_1 = cv$currentValue;
																	if(metric_valid_g[sample][timeStep$var136]) {
																		if((var66 == st[sample][timeStep$var136])) {
																			{
																				if(metric_valid_g[sample][timeStep$var136]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample104) {
																							for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																								if((index$sample$65_1 == sample)) {
																									if((0 == timeStep$var136)) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											for(int var50 = 0; var50 < noStates; var50 += 1) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													if((var50 == st[sample][timeStep$var136])) {
																														{
																															{
																																if(metric_valid_g[sample][timeStep$var136]) {
																																	double cv$temp$22$var148;
																																	{
																																		double var148 = metric_mean[st[sample][timeStep$var136]];
																																		cv$temp$22$var148 = var148;
																																	}
																																	double cv$temp$23$var149;
																																	{
																																		double var149 = traceTempVariable$var149$22_1;
																																		cv$temp$23$var149 = var149;
																																	}
																																	if(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$22$var148) / Math.sqrt(cv$temp$23$var149))) - (0.5 * Math.log(cv$temp$23$var149)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
																																}
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
																							for(int index$sample$66 = 0; index$sample$66 < noSamples; index$sample$66 += 1) {
																								if(true) {
																									for(int index$sample104$67 = 0; index$sample104$67 < noStates; index$sample104$67 += 1) {
																										int distributionTempVariable$var102$69 = index$sample104$67;
																										double cv$probabilitySample104Value68 = (cv$probabilitySample123Value18 * distribution$sample104[((index$sample$66 - 0) / 1)][index$sample104$67]);
																										if((index$sample$66 == sample)) {
																											if((0 == timeStep$var136)) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if(metric_valid_g[sample][timeStep$var136]) {
																															if((var50 == st[sample][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[sample][timeStep$var136]) {
																																			double cv$temp$24$var148;
																																			{
																																				double var148 = metric_mean[st[sample][timeStep$var136]];
																																				cv$temp$24$var148 = var148;
																																			}
																																			double cv$temp$25$var149;
																																			{
																																				double var149 = traceTempVariable$var149$22_1;
																																				cv$temp$25$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))))) + 1)) + (Math.log(cv$probabilitySample104Value68) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$24$var148) / Math.sqrt(cv$temp$25$var149))) - (0.5 * Math.log(cv$temp$25$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample104Value68);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						if((index$sample$15 == sample)) {
																							if((timeStep$var113 == timeStep$var136)) {
																								if(metric_valid_g[sample][timeStep$var136]) {
																									for(int var50 = 0; var50 < noStates; var50 += 1) {
																										if(metric_valid_g[sample][timeStep$var136]) {
																											if((var50 == st[sample][timeStep$var136])) {
																												{
																													{
																														if(metric_valid_g[sample][timeStep$var136]) {
																															double cv$temp$26$var148;
																															{
																																double var148 = metric_mean[st[sample][timeStep$var136]];
																																cv$temp$26$var148 = var148;
																															}
																															double cv$temp$27$var149;
																															{
																																double var149 = traceTempVariable$var149$22_1;
																																cv$temp$27$var149 = var149;
																															}
																															if(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value18) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$26$var148) / Math.sqrt(cv$temp$27$var149))) - (0.5 * Math.log(cv$temp$27$var149)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value18);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$74 = 0; index$sample$74 < noSamples; index$sample$74 += 1) {
																							for(int index$timeStep$75 = 1; index$timeStep$75 < length$metric[index$sample$74]; index$timeStep$75 += 1) {
																								if(!((index$sample$74 == index$sample$15) && (index$timeStep$75 == timeStep$var113))) {
																									for(int index$sample123$76 = 0; index$sample123$76 < noStates; index$sample123$76 += 1) {
																										int distributionTempVariable$var121$78 = index$sample123$76;
																										double cv$probabilitySample123Value77 = (cv$probabilitySample123Value18 * distribution$sample123[((index$sample$74 - 0) / 1)][((index$timeStep$75 - 1) / 1)][index$sample123$76]);
																										if((index$sample$74 == sample)) {
																											if((index$timeStep$75 == timeStep$var136)) {
																												if(metric_valid_g[sample][timeStep$var136]) {
																													for(int var50 = 0; var50 < noStates; var50 += 1) {
																														if(metric_valid_g[sample][timeStep$var136]) {
																															if((var50 == st[sample][timeStep$var136])) {
																																{
																																	{
																																		if(metric_valid_g[sample][timeStep$var136]) {
																																			double cv$temp$28$var148;
																																			{
																																				double var148 = metric_mean[st[sample][timeStep$var136]];
																																				cv$temp$28$var148 = var148;
																																			}
																																			double cv$temp$29$var149;
																																			{
																																				double var149 = traceTempVariable$var149$22_1;
																																				cv$temp$29$var149 = var149;
																																			}
																																			if(((Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))))) + 1)) + (Math.log(cv$probabilitySample123Value77) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var136] - cv$temp$28$var148) / Math.sqrt(cv$temp$29$var149))) - (0.5 * Math.log(cv$temp$29$var149)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample123Value77);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
				if((cv$valuePos == 0))
					cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
				else
					cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double cv$ratio = (cv$proposedProbability - cv$originalProbability);
			if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
				double var67 = cv$originalValue;
				{
					{
						metric_var[var66] = var67;
					}
				}
			}
		}
	}

	private final void sample84(int var82, int threadID$cv$var82, Rng RNG$) {
		if(true) {
			double cv$sum = 0.0;
			double cv$count = 0.0;
			{
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
								if(fixedFlag$sample104) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var136)) {
												if((var82 == st[sample][timeStep$var136])) {
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1.0);
																		if(metric_valid_g[sample][timeStep$var136])
																			cv$sum = (cv$sum + 1.0);
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
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample104$5 = 0; index$sample104$5 < noStates; index$sample104$5 += 1) {
												int distributionTempVariable$var102$7 = index$sample104$5;
												double cv$probabilitySample104Value6 = (1.0 * distribution$sample104[((index$sample$4 - 0) / 1)][index$sample104$5]);
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var136)) {
														if((var82 == st[sample][timeStep$var136])) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + cv$probabilitySample104Value6);
																				if(metric_valid_g[sample][timeStep$var136])
																					cv$sum = (cv$sum + cv$probabilitySample104Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
								if(fixedFlag$sample123) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$13_1]; timeStep$var113 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var113 == timeStep$var136)) {
													if((var82 == st[sample][timeStep$var136])) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + 1.0);
																			if(metric_valid_g[sample][timeStep$var136])
																				cv$sum = (cv$sum + 1.0);
																		}
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
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var113 = 1; timeStep$var113 < length$metric[index$sample$14]; timeStep$var113 += 1) {
											if(true) {
												for(int index$sample123$16 = 0; index$sample123$16 < noStates; index$sample123$16 += 1) {
													int distributionTempVariable$var121$18 = index$sample123$16;
													double cv$probabilitySample123Value17 = (1.0 * distribution$sample123[((index$sample$14 - 0) / 1)][((timeStep$var113 - 1) / 1)][index$sample123$16]);
													if((index$sample$14 == sample)) {
														if((timeStep$var113 == timeStep$var136)) {
															if((var82 == st[sample][timeStep$var136])) {
																{
																	{
																		{
																			{
																				{
																					cv$count = (cv$count + cv$probabilitySample123Value17);
																					if(metric_valid_g[sample][timeStep$var136])
																						cv$sum = (cv$sum + cv$probabilitySample123Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double var83 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					metric_valid_bias[var82] = var83;
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var19$countGlobal = new double[noStates];
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var32$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var32$countGlobal[cv$index] = new double[noStates];
			}
		}
		{
			int cv$var33$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$distributionAccumulator$var120 = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var120[cv$index] = new double[cv$var33$max];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var102$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var102$stateProbabilityGlobal[cv$index] = new double[noStates];
			}
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample104gaussian156$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample104gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
			}
		}
		{
			int cv$var33$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var121$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var121$stateProbabilityGlobal[cv$index] = new double[cv$var33$max];
			}
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample123gaussian156$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample123gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		if(!fixedFlag$sample19) {
			{
				initialStateDistribution = new double[noStates];
			}
		}
		if(!fixedFlag$sample32) {
			{
				m = new double[noStates][];
				for(int var31 = 0; var31 < noStates; var31 += 1)
					m[var31] = new double[noStates];
			}
		}
		if((!fixedFlag$sample104 || !fixedFlag$sample123)) {
			{
				st = new int[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					st[sample] = new int[length$metric[sample]];
			}
		}
		{
			metric_g = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_g[sample] = new double[length$metric[sample]];
		}
		{
			metric_valid_g = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_valid_g[sample] = new boolean[length$metric[sample]];
		}
		if(!fixedFlag$sample52) {
			{
				metric_mean = new double[noStates];
			}
		}
		if(!fixedFlag$sample68) {
			{
				metric_var = new double[noStates];
			}
		}
		if(!fixedFlag$sample84) {
			{
				metric_valid_bias = new double[noStates];
			}
		}
		{
			distribution$sample104 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample104[((sample - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample123 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)][];
				distribution$sample123[((sample - 0) / 1)] = subarray$0;
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					subarray$0[((timeStep$var113 - 1) / 1)] = new double[noStates];
			}
		}
		{
			logProbability$var101 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample104 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var120 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var120[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample123 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample123[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var140 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var140[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample145 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample145[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var150 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var150[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample157 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample157[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = m[var31];
						if(!fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var32);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!fixedFlag$sample52)
							metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!fixedFlag$sample68)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!fixedFlag$sample84)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int threadID$sample = threadID$index$sample;
						int[] var99 = st[sample];
						if(!fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var114 = st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(!fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var136, int forEnd$timeStep$var136, int threadID$timeStep$var136, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int timeStep$var136 = forStart$timeStep$var136; timeStep$var136 < forEnd$timeStep$var136; timeStep$var136 += 1) {
										metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var136]]);
										if(metric_valid_g[sample][timeStep$var136]) {
											if(!fixedFlag$sample157)
												metric_1d[timeStep$var136] = ((Math.sqrt(metric_var[st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var136]]);
										}
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
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = m[var31];
						if(!fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var32);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!fixedFlag$sample52)
							metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!fixedFlag$sample68)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!fixedFlag$sample84)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						double[] cv$distribution$sample104 = distribution$sample104[((sample - 0) / 1)];
						for(int index$var101 = 0; index$var101 < noStates; index$var101 += 1) {
							double cv$value = (((0.0 <= index$var101) && (index$var101 < noStates))?initialStateDistribution[index$var101]:0.0);
							if(!fixedFlag$sample104)
								cv$distribution$sample104[index$var101] = cv$value;
						}
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							double[] cv$distribution$sample123 = distribution$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)];
							for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
								if(!fixedFlag$sample123)
									cv$distribution$sample123[index$var120] = 0.0;
							}
							if(fixedFlag$sample104) {
								for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
									if((index$sample$1_1 == sample)) {
										if((0 == (timeStep$var113 - 1))) {
											for(int var31 = 0; var31 < noStates; var31 += 1) {
												if((var31 == st[sample][(timeStep$var113 - 1)])) {
													{
														if(!fixedFlag$sample123) {
															double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
															for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
																cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (1.0 * (((0.0 <= index$var120) && (index$var120 < noStates))?var119[index$var120]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$2 = 0; index$sample$2 < noSamples; index$sample$2 += 1) {
									if(true) {
										for(int index$sample104$3 = 0; index$sample104$3 < noStates; index$sample104$3 += 1) {
											int distributionTempVariable$var102$5 = index$sample104$3;
											double cv$probabilitySample104Value4 = (1.0 * distribution$sample104[((index$sample$2 - 0) / 1)][index$sample104$3]);
											if((index$sample$2 == sample)) {
												if((0 == (timeStep$var113 - 1))) {
													for(int var31 = 0; var31 < noStates; var31 += 1) {
														if((var31 == st[sample][(timeStep$var113 - 1)])) {
															{
																if(!fixedFlag$sample123) {
																	double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																	for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
																		cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * (((0.0 <= index$var120) && (index$var120 < noStates))?var119[index$var120]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample123) {
								for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
									for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
										if((index$sample$9_1 == sample)) {
											if((index$timeStep$9_2 == (timeStep$var113 - 1))) {
												for(int var31 = 0; var31 < noStates; var31 += 1) {
													if((var31 == st[sample][(timeStep$var113 - 1)])) {
														{
															if(!fixedFlag$sample123) {
																double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
																	cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (1.0 * (((0.0 <= index$var120) && (index$var120 < noStates))?var119[index$var120]:0.0)));
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$sample$10 = 0; index$sample$10 < noSamples; index$sample$10 += 1) {
									for(int index$timeStep$11 = 1; index$timeStep$11 < length$metric[index$sample$10]; index$timeStep$11 += 1) {
										if(true) {
											for(int index$sample123$12 = 0; index$sample123$12 < noStates; index$sample123$12 += 1) {
												int distributionTempVariable$var121$14 = index$sample123$12;
												double cv$probabilitySample123Value13 = (1.0 * distribution$sample123[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample123$12]);
												if((index$sample$10 == sample)) {
													if((index$timeStep$11 == (timeStep$var113 - 1))) {
														for(int var31 = 0; var31 < noStates; var31 += 1) {
															if((var31 == st[sample][(timeStep$var113 - 1)])) {
																{
																	if(!fixedFlag$sample123) {
																		double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
																		for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
																			cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * (((0.0 <= index$var120) && (index$var120 < noStates))?var119[index$var120]:0.0)));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							double cv$var120$sum = 0.0;
							for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
								if(!fixedFlag$sample123)
									cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
							}
							for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1) {
								if(!fixedFlag$sample123)
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
							}
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = m[var31];
						if(!fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var32);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!fixedFlag$sample52)
							metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!fixedFlag$sample68)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!fixedFlag$sample84)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						int[] var99 = st[sample];
						if(!fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var114 = st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(!fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample19)
				sample19();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
							if(!fixedFlag$sample32)
								sample32(var31, threadID$var31, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
							if(!fixedFlag$sample52)
								sample52(var50, threadID$var50, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
							if(!fixedFlag$sample68)
								sample68(var66, threadID$var66, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							if(!fixedFlag$sample84)
								sample84(var82, threadID$var82, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!fixedFlag$sample104)
								sample104(sample, threadID$sample, RNG$1);
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								if(!fixedFlag$sample123)
									sample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							for(int timeStep$var113 = (length$metric[sample] - ((((length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var113 >= ((1 - 1) + 1); timeStep$var113 -= 1) {
								if(!fixedFlag$sample123)
									sample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
							if(!fixedFlag$sample104)
								sample104(sample, threadID$sample, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							if(!fixedFlag$sample84)
								sample84(var82, threadID$var82, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
							if(!fixedFlag$sample68)
								sample68(var66, threadID$var66, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
							if(!fixedFlag$sample52)
								sample52(var50, threadID$var50, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
							if(!fixedFlag$sample32)
								sample32(var31, threadID$var31, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample19)
				sample19();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var15, int forEnd$var15, int threadID$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var15 = forStart$var15; var15 < forEnd$var15; var15 += 1)
						v[var15] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var32 = 0.0;
		logProbability$var39 = 0.0;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var51 = 0.0;
		logProbability$var55 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$var67 = 0.0;
		logProbability$var71 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var83 = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1)
			logProbability$var101[((sample - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample104[((sample - 0) / 1)] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
				logProbability$var120[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample123) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					logProbability$sample123[((sample - 0) / 1)][((timeStep$var113 - 1) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
				logProbability$var140[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = 0.0;
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample145) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					logProbability$sample145[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
				logProbability$var150[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = 0.0;
		}
		logProbability$metric_1d = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample157) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					logProbability$sample157[((sample - 0) / 1)][((timeStep$var136 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample145();
		logProbabilityDistribution$sample157();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityValue$sample104();
		logProbabilityValue$sample123();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1) {
						double[] var32 = m[var31];
						if(!fixedFlag$sample32)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, var32);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1) {
						if(!fixedFlag$sample52)
							metric_mean[var50] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1) {
						if(!fixedFlag$sample68)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
						if(!fixedFlag$sample84)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						int[] var99 = st[sample];
						if(!fixedFlag$sample104)
							var99[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						int[] var114 = st[sample];
						for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
							if(!fixedFlag$sample123)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			boolean[][] cv$source1 = metric_valid;
			boolean[][] cv$target1 = metric_valid_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[] cv$source2 = cv$source1[cv$index1];
				boolean[] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
		}
		for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
			for(int timeStep$var136 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var136 >= ((0 - 1) + 1); timeStep$var136 -= 1)
				metric_g[sample][timeStep$var136] = metric[sample][timeStep$var136];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics2(\n"
		     + "               double[][] metric,\n"
		     + "               boolean[][] metric_valid, \n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "\n"
		     + "    //Allocate space for states\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "\n"
		     + "    //Allocate space for generated metrics \n"
		     + "    double[][] metric_g = new double[noSamples][];\n"
		     + "    boolean[][] metric_valid_g = new boolean[noSamples][];\n"
		     + "    \n"
		     + "    //Calculate priors for the metric\n"
		     + "    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n"
		     + "    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n"
		     + "    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric value\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        //Calculate all the state transitions\n"
		     + "        int streamLength = metric[sample].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "        //Calculate metric values\n"
		     + "        double[] metric_1d = new double[streamLength];\n"
		     + "        metric_g[sample] = metric_1d;\n"
		     + "\n"
		     + "        boolean[] metric_valid_1d = new boolean[streamLength];\n"
		     + "        metric_valid_g[sample] = metric_valid_1d;\n"
		     + "\n"
		     + "        //Generate values.\n"
		     + "        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "            int currentState = st[sample][timeStep];\n"
		     + "            \n"
		     + "            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n"
		     + "            if(metric_valid_1d[timeStep])\n"
		     + "                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n"
		     + "            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n"
		     + "            metric_1d[timeStep].observe(metric[sample][timeStep]);\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "}";
	}
}