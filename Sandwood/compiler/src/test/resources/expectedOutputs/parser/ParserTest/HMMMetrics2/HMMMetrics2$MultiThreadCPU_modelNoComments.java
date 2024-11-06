package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics2$CoreInterface {
	private double[][] cv$distributionAccumulator$var81;
	private double[] cv$var20$countGlobal;
	private double[][] cv$var26$countGlobal;
	private double[][] cv$var69$stateProbabilityGlobal;
	private double[][] cv$var82$stateProbabilityGlobal;
	private double[][] distribution$sample73;
	private double[][][] distribution$sample86;
	private boolean fixedFlag$sample100 = false;
	private boolean fixedFlag$sample109 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample41 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample59 = false;
	private boolean fixedFlag$sample73 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedProbFlag$sample100 = false;
	private boolean fixedProbFlag$sample109 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample41 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample59 = false;
	private boolean fixedProbFlag$sample73 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean[][][] guard$sample73gaussian108$global;
	private boolean[][][] guard$sample86gaussian108$global;
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
	private double[][] logProbability$sample100;
	private double[][] logProbability$sample109;
	private double[] logProbability$sample73;
	private double[][] logProbability$sample86;
	private double logProbability$st;
	private double[][] logProbability$var102;
	private double logProbability$var19;
	private double logProbability$var21;
	private double logProbability$var26;
	private double logProbability$var33;
	private double logProbability$var38;
	private double logProbability$var42;
	private double logProbability$var47;
	private double logProbability$var51;
	private double logProbability$var56;
	private double[] logProbability$var68;
	private double[][] logProbability$var81;
	private double[][] logProbability$var95;
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
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$metric_g = false;
	private boolean setFlag$metric_mean = false;
	private boolean setFlag$metric_valid_bias = false;
	private boolean setFlag$metric_valid_g = false;
	private boolean setFlag$metric_var = false;
	private boolean setFlag$st = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample100() {
		return fixedFlag$sample100;
	}

	@Override
	public final void set$fixedFlag$sample100(boolean cv$value) {
		fixedFlag$sample100 = cv$value;
		fixedProbFlag$sample100 = (fixedFlag$sample100 && fixedProbFlag$sample100);
	}

	@Override
	public final boolean get$fixedFlag$sample109() {
		return fixedFlag$sample109;
	}

	@Override
	public final void set$fixedFlag$sample109(boolean cv$value) {
		fixedFlag$sample109 = cv$value;
		fixedProbFlag$sample109 = (fixedFlag$sample109 && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedProbFlag$sample23);
		fixedProbFlag$sample73 = (fixedFlag$sample23 && fixedProbFlag$sample73);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		fixedProbFlag$sample86 = (fixedFlag$sample29 && fixedProbFlag$sample86);
	}

	@Override
	public final boolean get$fixedFlag$sample41() {
		return fixedFlag$sample41;
	}

	@Override
	public final void set$fixedFlag$sample41(boolean cv$value) {
		fixedFlag$sample41 = cv$value;
		fixedProbFlag$sample41 = (fixedFlag$sample41 && fixedProbFlag$sample41);
		fixedProbFlag$sample109 = (fixedFlag$sample41 && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		fixedProbFlag$sample109 = (fixedFlag$sample50 && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample59() {
		return fixedFlag$sample59;
	}

	@Override
	public final void set$fixedFlag$sample59(boolean cv$value) {
		fixedFlag$sample59 = cv$value;
		fixedProbFlag$sample59 = (fixedFlag$sample59 && fixedProbFlag$sample59);
		fixedProbFlag$sample100 = (fixedFlag$sample59 && fixedProbFlag$sample100);
	}

	@Override
	public final boolean get$fixedFlag$sample73() {
		return fixedFlag$sample73;
	}

	@Override
	public final void set$fixedFlag$sample73(boolean cv$value) {
		fixedFlag$sample73 = cv$value;
		fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedProbFlag$sample73);
		fixedProbFlag$sample86 = (fixedFlag$sample73 && fixedProbFlag$sample86);
		fixedProbFlag$sample100 = (fixedFlag$sample73 && fixedProbFlag$sample100);
		fixedProbFlag$sample109 = (fixedFlag$sample73 && fixedProbFlag$sample109);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean cv$value) {
		fixedFlag$sample86 = cv$value;
		fixedProbFlag$sample86 = (fixedFlag$sample86 && fixedProbFlag$sample86);
		fixedProbFlag$sample100 = (fixedFlag$sample86 && fixedProbFlag$sample100);
		fixedProbFlag$sample109 = (fixedFlag$sample86 && fixedProbFlag$sample109);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
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
		setFlag$m = true;
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
	public final void set$metric_g(double[][] cv$value) {
		metric_g = cv$value;
		setFlag$metric_g = true;
	}

	@Override
	public final double[] get$metric_mean() {
		return metric_mean;
	}

	@Override
	public final void set$metric_mean(double[] cv$value) {
		metric_mean = cv$value;
		setFlag$metric_mean = true;
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
		setFlag$metric_valid_bias = true;
	}

	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	@Override
	public final void set$metric_valid_g(boolean[][] cv$value) {
		metric_valid_g = cv$value;
		setFlag$metric_valid_g = true;
	}

	@Override
	public final double[] get$metric_var() {
		return metric_var;
	}

	@Override
	public final void set$metric_var(double[] cv$value) {
		metric_var = cv$value;
		setFlag$metric_var = true;
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
		setFlag$st = true;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample100() {
		if(!fixedProbFlag$sample100) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var91];
						if(fixedFlag$sample73) {
							for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
								if((index$sample$2_1 == sample)) {
									if((0 == timeStep$var91)) {
										for(int var55 = 0; var55 < noStates; var55 += 1) {
											if((var55 == st[sample][timeStep$var91])) {
												{
													double var94 = metric_valid_bias[st[sample][timeStep$var91]];
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
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
									for(int index$sample73$4 = 0; index$sample73$4 < noStates; index$sample73$4 += 1) {
										int distributionTempVariable$var69$6 = index$sample73$4;
										double cv$probabilitySample73Value5 = (1.0 * distribution$sample73[((index$sample$3 - 0) / 1)][index$sample73$4]);
										int traceTempVariable$currentState$7_1 = distributionTempVariable$var69$6;
										if((index$sample$3 == sample)) {
											if((0 == timeStep$var91)) {
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == traceTempVariable$currentState$7_1)) {
														{
															double var94 = metric_valid_bias[traceTempVariable$currentState$7_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample73Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample86) {
							for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
								for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$10_1]; timeStep$var74 += 1) {
									if((index$sample$10_1 == sample)) {
										if((timeStep$var74 == timeStep$var91)) {
											for(int var55 = 0; var55 < noStates; var55 += 1) {
												if((var55 == st[sample][timeStep$var91])) {
													{
														double var94 = metric_valid_bias[st[sample][timeStep$var91]];
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
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
								for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$11]; timeStep$var74 += 1) {
									if(true) {
										for(int index$sample86$13 = 0; index$sample86$13 < noStates; index$sample86$13 += 1) {
											int distributionTempVariable$var82$15 = index$sample86$13;
											double cv$probabilitySample86Value14 = (1.0 * distribution$sample86[((index$sample$11 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$13]);
											int traceTempVariable$currentState$16_1 = distributionTempVariable$var82$15;
											if((index$sample$11 == sample)) {
												if((timeStep$var74 == timeStep$var91)) {
													for(int var55 = 0; var55 < noStates; var55 += 1) {
														if((var55 == traceTempVariable$currentState$16_1)) {
															{
																double var94 = metric_valid_bias[traceTempVariable$currentState$16_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample86Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value14);
															}
														}
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
					logProbability$var95[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample100[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
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
			fixedProbFlag$sample100 = (((fixedFlag$sample100 && fixedFlag$sample59) && fixedFlag$sample73) && fixedFlag$sample86);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample100[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var95[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
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

	private final void logProbabilityDistribution$sample109() {
		if(!fixedProbFlag$sample109) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							double cv$sampleValue = metric_g[sample][timeStep$var91];
							if(fixedFlag$sample73) {
								for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
									if((index$sample$2_1 == sample)) {
										if((0 == timeStep$var91)) {
											for(int var37 = 0; var37 < noStates; var37 += 1) {
												if(metric_valid_g[sample][timeStep$var91]) {
													if((var37 == st[sample][timeStep$var91])) {
														for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
															if((index$sample$10_1 == sample)) {
																if((0 == timeStep$var91)) {
																	for(int var46 = 0; var46 < noStates; var46 += 1) {
																		if(metric_valid_g[sample][timeStep$var91]) {
																			if((var46 == st[sample][timeStep$var91])) {
																				{
																					double var100 = metric_mean[st[sample][timeStep$var91]];
																					double var101 = metric_var[st[sample][timeStep$var91]];
																					double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
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
							} else {
								for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
									if(true) {
										for(int index$sample73$4 = 0; index$sample73$4 < noStates; index$sample73$4 += 1) {
											int distributionTempVariable$var69$6 = index$sample73$4;
											double cv$probabilitySample73Value5 = (1.0 * distribution$sample73[((index$sample$3 - 0) / 1)][index$sample73$4]);
											int traceTempVariable$currentState$7_1 = distributionTempVariable$var69$6;
											if((index$sample$3 == sample)) {
												if((0 == timeStep$var91)) {
													for(int var37 = 0; var37 < noStates; var37 += 1) {
														if(metric_valid_g[sample][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$7_1)) {
																int traceTempVariable$currentState$11_1 = distributionTempVariable$var69$6;
																if((index$sample$3 == sample)) {
																	if((0 == timeStep$var91)) {
																		for(int var46 = 0; var46 < noStates; var46 += 1) {
																			if(metric_valid_g[sample][timeStep$var91]) {
																				if((var46 == traceTempVariable$currentState$11_1)) {
																					{
																						double var100 = metric_mean[traceTempVariable$currentState$11_1];
																						double var101 = metric_var[traceTempVariable$currentState$11_1];
																						double cv$weightedProbability = (Math.log(cv$probabilitySample73Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value5);
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																	if(!(index$sample$12 == index$sample$3)) {
																		for(int index$sample73$13 = 0; index$sample73$13 < noStates; index$sample73$13 += 1) {
																			int distributionTempVariable$var69$15 = index$sample73$13;
																			double cv$probabilitySample73Value14 = (cv$probabilitySample73Value5 * distribution$sample73[((index$sample$12 - 0) / 1)][index$sample73$13]);
																			int traceTempVariable$currentState$16_1 = distributionTempVariable$var69$15;
																			if((index$sample$12 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$16_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$16_1];
																									double var101 = metric_var[traceTempVariable$currentState$16_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample73Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value14);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample73) {
								for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
									if((index$sample$20_1 == sample)) {
										if((0 == timeStep$var91)) {
											for(int var37 = 0; var37 < noStates; var37 += 1) {
												if(metric_valid_g[sample][timeStep$var91]) {
													if((var37 == st[sample][timeStep$var91])) {
														if(fixedFlag$sample86) {
															for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$28_1]; timeStep$var74 += 1) {
																	if((index$sample$28_1 == sample)) {
																		if((timeStep$var74 == timeStep$var91)) {
																			for(int var46 = 0; var46 < noStates; var46 += 1) {
																				if(metric_valid_g[sample][timeStep$var91]) {
																					if((var46 == st[sample][timeStep$var91])) {
																						{
																							double var100 = metric_mean[st[sample][timeStep$var91]];
																							double var101 = metric_var[st[sample][timeStep$var91]];
																							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
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
															for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$29]; timeStep$var74 += 1) {
																	if(true) {
																		for(int index$sample86$31 = 0; index$sample86$31 < noStates; index$sample86$31 += 1) {
																			int distributionTempVariable$var82$33 = index$sample86$31;
																			double cv$probabilitySample86Value32 = (1.0 * distribution$sample86[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$31]);
																			int traceTempVariable$currentState$34_1 = distributionTempVariable$var82$33;
																			if((index$sample$29 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$34_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$34_1];
																									double var101 = metric_var[traceTempVariable$currentState$34_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value32);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
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
										for(int index$sample73$22 = 0; index$sample73$22 < noStates; index$sample73$22 += 1) {
											int distributionTempVariable$var69$24 = index$sample73$22;
											double cv$probabilitySample73Value23 = (1.0 * distribution$sample73[((index$sample$21 - 0) / 1)][index$sample73$22]);
											int traceTempVariable$currentState$25_1 = distributionTempVariable$var69$24;
											if((index$sample$21 == sample)) {
												if((0 == timeStep$var91)) {
													for(int var37 = 0; var37 < noStates; var37 += 1) {
														if(metric_valid_g[sample][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$25_1)) {
																if(fixedFlag$sample86) {
																	for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$35_1]; timeStep$var74 += 1) {
																			if((index$sample$35_1 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$25_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$25_1];
																									double var101 = metric_var[traceTempVariable$currentState$25_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample73Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value23);
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
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$36]; timeStep$var74 += 1) {
																			if(true) {
																				for(int index$sample86$38 = 0; index$sample86$38 < noStates; index$sample86$38 += 1) {
																					int distributionTempVariable$var82$40 = index$sample86$38;
																					double cv$probabilitySample86Value39 = (cv$probabilitySample73Value23 * distribution$sample86[((index$sample$36 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$38]);
																					int traceTempVariable$currentState$41_1 = distributionTempVariable$var82$40;
																					if((index$sample$36 == sample)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$41_1)) {
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$41_1];
																											double var101 = metric_var[traceTempVariable$currentState$41_1];
																											double cv$weightedProbability = (Math.log(cv$probabilitySample86Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value39);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample86) {
								for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$46_1]; timeStep$var74 += 1) {
										if((index$sample$46_1 == sample)) {
											if((timeStep$var74 == timeStep$var91)) {
												for(int var37 = 0; var37 < noStates; var37 += 1) {
													if(metric_valid_g[sample][timeStep$var91]) {
														if((var37 == st[sample][timeStep$var91])) {
															for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1]; index$timeStep$55_2 += 1) {
																	if((index$sample$55_1 == sample)) {
																		if((index$timeStep$55_2 == timeStep$var91)) {
																			for(int var46 = 0; var46 < noStates; var46 += 1) {
																				if(metric_valid_g[sample][timeStep$var91]) {
																					if((var46 == st[sample][timeStep$var91])) {
																						{
																							double var100 = metric_mean[st[sample][timeStep$var91]];
																							double var101 = metric_var[st[sample][timeStep$var91]];
																							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
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
								for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$47]; timeStep$var74 += 1) {
										if(true) {
											for(int index$sample86$49 = 0; index$sample86$49 < noStates; index$sample86$49 += 1) {
												int distributionTempVariable$var82$51 = index$sample86$49;
												double cv$probabilitySample86Value50 = (1.0 * distribution$sample86[((index$sample$47 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$49]);
												int traceTempVariable$currentState$52_1 = distributionTempVariable$var82$51;
												if((index$sample$47 == sample)) {
													if((timeStep$var74 == timeStep$var91)) {
														for(int var37 = 0; var37 < noStates; var37 += 1) {
															if(metric_valid_g[sample][timeStep$var91]) {
																if((var37 == traceTempVariable$currentState$52_1)) {
																	int traceTempVariable$currentState$56_1 = distributionTempVariable$var82$51;
																	if((index$sample$47 == sample)) {
																		if((timeStep$var74 == timeStep$var91)) {
																			for(int var46 = 0; var46 < noStates; var46 += 1) {
																				if(metric_valid_g[sample][timeStep$var91]) {
																					if((var46 == traceTempVariable$currentState$56_1)) {
																						{
																							double var100 = metric_mean[traceTempVariable$currentState$56_1];
																							double var101 = metric_var[traceTempVariable$currentState$56_1];
																							double cv$weightedProbability = (Math.log(cv$probabilitySample86Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value50);
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																		for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57]; index$timeStep$58 += 1) {
																			if(!((index$timeStep$58 == timeStep$var74) && (index$sample$57 == index$sample$47))) {
																				for(int index$sample86$59 = 0; index$sample86$59 < noStates; index$sample86$59 += 1) {
																					int distributionTempVariable$var82$61 = index$sample86$59;
																					double cv$probabilitySample86Value60 = (cv$probabilitySample86Value50 * distribution$sample86[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample86$59]);
																					int traceTempVariable$currentState$62_1 = distributionTempVariable$var82$61;
																					if((index$sample$57 == sample)) {
																						if((index$timeStep$58 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$62_1)) {
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$62_1];
																											double var101 = metric_var[traceTempVariable$currentState$62_1];
																											double cv$weightedProbability = (Math.log(cv$probabilitySample86Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value60);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample86) {
								for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$66_1]; timeStep$var74 += 1) {
										if((index$sample$66_1 == sample)) {
											if((timeStep$var74 == timeStep$var91)) {
												for(int var37 = 0; var37 < noStates; var37 += 1) {
													if(metric_valid_g[sample][timeStep$var91]) {
														if((var37 == st[sample][timeStep$var91])) {
															if(fixedFlag$sample73) {
																for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																	if((index$sample$75_1 == sample)) {
																		if((0 == timeStep$var91)) {
																			for(int var46 = 0; var46 < noStates; var46 += 1) {
																				if(metric_valid_g[sample][timeStep$var91]) {
																					if((var46 == st[sample][timeStep$var91])) {
																						{
																							double var100 = metric_mean[st[sample][timeStep$var91]];
																							double var101 = metric_var[st[sample][timeStep$var91]];
																							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
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
																for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																	if(true) {
																		for(int index$sample73$77 = 0; index$sample73$77 < noStates; index$sample73$77 += 1) {
																			int distributionTempVariable$var69$79 = index$sample73$77;
																			double cv$probabilitySample73Value78 = (1.0 * distribution$sample73[((index$sample$76 - 0) / 1)][index$sample73$77]);
																			int traceTempVariable$currentState$80_1 = distributionTempVariable$var69$79;
																			if((index$sample$76 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$80_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$80_1];
																									double var101 = metric_var[traceTempVariable$currentState$80_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample73Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value78);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
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
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$67]; timeStep$var74 += 1) {
										if(true) {
											for(int index$sample86$69 = 0; index$sample86$69 < noStates; index$sample86$69 += 1) {
												int distributionTempVariable$var82$71 = index$sample86$69;
												double cv$probabilitySample86Value70 = (1.0 * distribution$sample86[((index$sample$67 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$69]);
												int traceTempVariable$currentState$72_1 = distributionTempVariable$var82$71;
												if((index$sample$67 == sample)) {
													if((timeStep$var74 == timeStep$var91)) {
														for(int var37 = 0; var37 < noStates; var37 += 1) {
															if(metric_valid_g[sample][timeStep$var91]) {
																if((var37 == traceTempVariable$currentState$72_1)) {
																	if(fixedFlag$sample73) {
																		for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																			if((index$sample$81_1 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$72_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$72_1];
																									double var101 = metric_var[traceTempVariable$currentState$72_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample86Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value70);
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
																				for(int index$sample73$83 = 0; index$sample73$83 < noStates; index$sample73$83 += 1) {
																					int distributionTempVariable$var69$85 = index$sample73$83;
																					double cv$probabilitySample73Value84 = (cv$probabilitySample86Value70 * distribution$sample73[((index$sample$82 - 0) / 1)][index$sample73$83]);
																					int traceTempVariable$currentState$86_1 = distributionTempVariable$var69$85;
																					if((index$sample$82 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$86_1)) {
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$86_1];
																											double var101 = metric_var[traceTempVariable$currentState$86_1];
																											double cv$weightedProbability = (Math.log(cv$probabilitySample73Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value84);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
						logProbability$var102[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample109[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
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
			fixedProbFlag$sample109 = ((((fixedFlag$sample109 && fixedFlag$sample41) && fixedFlag$sample50) && fixedFlag$sample73) && fixedFlag$sample86);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample109[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var102[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
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

	private final void logProbabilityDistribution$sample73() {
		if(!fixedProbFlag$sample73) {
			if(fixedFlag$sample73) {
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
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var68[((sample - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample73[((sample - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample73)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample73)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedFlag$sample23);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample73[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[((sample - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample73)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample86() {
		if(!fixedProbFlag$sample86) {
			if(fixedFlag$sample86) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var74;
						int index$sample$2 = sample;
						{
							int cv$sampleValue = st[sample][timeStep$var74];
							if(fixedFlag$sample73) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample)) {
										if((0 == (timeStep$var74 - 1))) {
											for(int var25 = 0; var25 < noStates; var25 += 1) {
												if((var25 == st[sample][(timeStep$var74 - 1)])) {
													{
														double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										for(int index$sample73$6 = 0; index$sample73$6 < noStates; index$sample73$6 += 1) {
											int distributionTempVariable$var69$8 = index$sample73$6;
											double cv$probabilitySample73Value7 = (1.0 * distribution$sample73[((index$sample$5 - 0) / 1)][index$sample73$6]);
											int traceTempVariable$var79$9_1 = distributionTempVariable$var69$8;
											if((index$sample$5 == sample)) {
												if((0 == (timeStep$var74 - 1))) {
													for(int var25 = 0; var25 < noStates; var25 += 1) {
														if((var25 == traceTempVariable$var79$9_1)) {
															{
																double[] var80 = m[traceTempVariable$var79$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample73Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample73Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							int traceTempVariable$var79$12_1 = DistributionSampling.sampleCategorical(RNG$, m[st[index$sample$2][(index$timeStep$1 - 1)]]);
							if((index$sample$2 == sample)) {
								if((index$timeStep$1 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == traceTempVariable$var79$12_1)) {
											{
												double[] var80 = m[traceTempVariable$var79$12_1];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample86) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var74 - 1))) {
												for(int var25 = 0; var25 < noStates; var25 += 1) {
													if((var25 == st[sample][(timeStep$var74 - 1)])) {
														{
															double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										if(!((index$timeStep$15 == index$timeStep$1) && (index$sample$14 == index$sample$2))) {
											for(int index$sample86$16 = 0; index$sample86$16 < noStates; index$sample86$16 += 1) {
												int distributionTempVariable$var82$18 = index$sample86$16;
												double cv$probabilitySample86Value17 = (1.0 * distribution$sample86[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample86$16]);
												int traceTempVariable$var79$19_1 = distributionTempVariable$var82$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var74 - 1))) {
														for(int var25 = 0; var25 < noStates; var25 += 1) {
															if((var25 == traceTempVariable$var79$19_1)) {
																{
																	double[] var80 = m[traceTempVariable$var79$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample86Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample86Value17);
																}
															}
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
						logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample86)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample86)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample86 = ((fixedFlag$sample86 && fixedFlag$sample29) && fixedFlag$sample73);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample86)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample100() {
		if(!fixedProbFlag$sample100) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var91];
						{
							{
								double var94 = metric_valid_bias[st[sample][timeStep$var91]];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
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
					logProbability$var95[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample100[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
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
			fixedProbFlag$sample100 = (((fixedFlag$sample100 && fixedFlag$sample59) && fixedFlag$sample73) && fixedFlag$sample86);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample100[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var95[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$metric_valid_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
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

	private final void logProbabilityValue$sample109() {
		if(!fixedProbFlag$sample109) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							double cv$sampleValue = metric_g[sample][timeStep$var91];
							{
								{
									double var100 = metric_mean[st[sample][timeStep$var91]];
									double var101 = metric_var[st[sample][timeStep$var91]];
									double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
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
						logProbability$var102[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample109[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
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
			fixedProbFlag$sample109 = ((((fixedFlag$sample109 && fixedFlag$sample41) && fixedFlag$sample50) && fixedFlag$sample73) && fixedFlag$sample86);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample109[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var102[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
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

	private final void logProbabilityValue$sample23() {
		if(!fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = initialStateDistribution;
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
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var19 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var25 = 0; var25 < noStates; var25 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var25];
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
			logProbability$var21 = cv$sampleAccumulator;
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var26;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var21 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var37 = 0; var37 < noStates; var37 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_mean[var37];
					{
						{
							double var31 = 0.0;
							double var32 = 100.0;
							double cv$weightedProbability = (Math.log(1.0) + (((var31 <= cv$sampleValue) && (cv$sampleValue <= var32))?(-Math.log((var32 - var31))):Double.NEGATIVE_INFINITY));
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
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$var38 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample41 = fixedFlag$sample41;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var38;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var33 = cv$rvAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample41)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noStates; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_var[var46];
					{
						{
							double var40 = 1.0;
							double var41 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var40, var41));
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
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = fixedFlag$sample50;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample59() {
		if(!fixedProbFlag$sample59) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_valid_bias[var55];
					{
						{
							double var49 = 1.0;
							double var50 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var49, var50));
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
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$var56 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample59)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample59 = fixedFlag$sample59;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample59)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample73() {
		if(!fixedProbFlag$sample73) {
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
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var68[((sample - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample73[((sample - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample73 = (fixedFlag$sample73 && fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample73[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[((sample - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample73)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var74;
					int index$sample$2 = sample;
					{
						int cv$sampleValue = st[sample][timeStep$var74];
						{
							{
								double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample86 = ((fixedFlag$sample86 && fixedFlag$sample29) && fixedFlag$sample73);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample23() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var20$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						if(fixedFlag$sample73) {
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
					if(!fixedFlag$sample73) {
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
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample73[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample29(int var25, int threadID$cv$var25, Rng RNG$) {
		double[] cv$targetLocal = m[var25];
		double[] cv$countLocal = cv$var26$countGlobal[threadID$cv$var25];
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							if(fixedFlag$sample73) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == (timeStep$var74 - 1))) {
											if((var25 == st[sample][(timeStep$var74 - 1)])) {
												if(fixedFlag$sample86) {
													{
														int index$timeStep$23 = timeStep$var74;
														int index$sample$24 = sample;
														{
															{
																{
																	{
																		cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + 1.0);
																	}
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
										for(int index$sample73$5 = 0; index$sample73$5 < noStates; index$sample73$5 += 1) {
											int distributionTempVariable$var69$7 = index$sample73$5;
											double cv$probabilitySample73Value6 = (1.0 * distribution$sample73[((index$sample$4 - 0) / 1)][index$sample73$5]);
											int traceTempVariable$var79$8_1 = distributionTempVariable$var69$7;
											if((index$sample$4 == sample)) {
												if((0 == (timeStep$var74 - 1))) {
													if((var25 == traceTempVariable$var79$8_1)) {
														if(fixedFlag$sample86) {
															{
																int index$timeStep$26 = timeStep$var74;
																int index$sample$27 = sample;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + cv$probabilitySample73Value6);
																			}
																		}
																	}
																}
															}
														}
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
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							if(fixedFlag$sample86) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var74 - 1))) {
												if((var25 == st[sample][(timeStep$var74 - 1)])) {
													if(fixedFlag$sample86) {
														{
															int index$timeStep$29 = timeStep$var74;
															int index$sample$30 = sample;
															{
																{
																	{
																		{
																			cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + 1.0);
																		}
																	}
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
											for(int index$sample86$16 = 0; index$sample86$16 < noStates; index$sample86$16 += 1) {
												int distributionTempVariable$var82$18 = index$sample86$16;
												double cv$probabilitySample86Value17 = (1.0 * distribution$sample86[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample86$16]);
												int traceTempVariable$var79$19_1 = distributionTempVariable$var82$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var74 - 1))) {
														if((var25 == traceTempVariable$var79$19_1)) {
															if(fixedFlag$sample86) {
																{
																	int index$timeStep$32 = timeStep$var74;
																	int index$sample$33 = sample;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + cv$probabilitySample86Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
					for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
						if(fixedFlag$sample73) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample)) {
									if((0 == (timeStep$var74 - 1))) {
										if((var25 == st[sample][(timeStep$var74 - 1)])) {
											if(!fixedFlag$sample86) {
												{
													int index$timeStep$60 = timeStep$var74;
													int index$sample$61 = sample;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample73$42 = 0; index$sample73$42 < noStates; index$sample73$42 += 1) {
										int distributionTempVariable$var69$44 = index$sample73$42;
										double cv$probabilitySample73Value43 = (1.0 * distribution$sample73[((index$sample$41 - 0) / 1)][index$sample73$42]);
										int traceTempVariable$var79$45_1 = distributionTempVariable$var69$44;
										if((index$sample$41 == sample)) {
											if((0 == (timeStep$var74 - 1))) {
												if((var25 == traceTempVariable$var79$45_1)) {
													if(!fixedFlag$sample86) {
														{
															int index$timeStep$63 = timeStep$var74;
															int index$sample$64 = sample;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample73Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
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
					for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
						if(fixedFlag$sample86) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample)) {
										if((index$timeStep$50_2 == (timeStep$var74 - 1))) {
											if((var25 == st[sample][(timeStep$var74 - 1)])) {
												if(!fixedFlag$sample86) {
													{
														int index$timeStep$66 = timeStep$var74;
														int index$sample$67 = sample;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										for(int index$sample86$53 = 0; index$sample86$53 < noStates; index$sample86$53 += 1) {
											int distributionTempVariable$var82$55 = index$sample86$53;
											double cv$probabilitySample86Value54 = (1.0 * distribution$sample86[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample86$53]);
											int traceTempVariable$var79$56_1 = distributionTempVariable$var82$55;
											if((index$sample$51 == sample)) {
												if((index$timeStep$52 == (timeStep$var74 - 1))) {
													if((var25 == traceTempVariable$var79$56_1)) {
														if(!fixedFlag$sample86) {
															{
																int index$timeStep$69 = timeStep$var74;
																int index$sample$70 = sample;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample86Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample41(int var37, int threadID$cv$var37, Rng RNG$) {
		double cv$originalValue = metric_mean[var37];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var38 = cv$proposedValue;
					metric_mean[var37] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var31;
				{
					cv$temp$0$var31 = 0.0;
				}
				double cv$temp$1$var32;
				{
					cv$temp$1$var32 = 100.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var31 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var32))?(-Math.log((cv$temp$1$var32 - cv$temp$0$var31))):Double.NEGATIVE_INFINITY));
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
								if(fixedFlag$sample73) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var91)) {
												double traceTempVariable$var100$9_1 = cv$currentValue;
												if(metric_valid_g[sample][timeStep$var91]) {
													if((var37 == st[sample][timeStep$var91])) {
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int index$sample$26_1 = 0; index$sample$26_1 < noSamples; index$sample$26_1 += 1) {
																	if((index$sample$26_1 == sample)) {
																		if((0 == timeStep$var91)) {
																			for(int var46 = 0; var46 < noStates; var46 += 1) {
																				if(metric_valid_g[sample][timeStep$var91]) {
																					if((var46 == st[sample][timeStep$var91])) {
																						{
																							{
																								double cv$temp$2$var100;
																								{
																									double var100 = traceTempVariable$var100$9_1;
																									cv$temp$2$var100 = var100;
																								}
																								double cv$temp$3$var101;
																								{
																									double var101 = metric_var[st[sample][timeStep$var91]];
																									cv$temp$3$var101 = var101;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))));
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
																if(fixedFlag$sample86) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$28_1]; timeStep$var74 += 1) {
																			if((index$sample$28_1 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == st[sample][timeStep$var91])) {
																								{
																									{
																										double cv$temp$4$var100;
																										{
																											double var100 = traceTempVariable$var100$9_1;
																											cv$temp$4$var100 = var100;
																										}
																										double cv$temp$5$var101;
																										{
																											double var101 = metric_var[st[sample][timeStep$var91]];
																											cv$temp$5$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))));
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
																} else {
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$29]; timeStep$var74 += 1) {
																			if(true) {
																				for(int index$sample86$31 = 0; index$sample86$31 < noStates; index$sample86$31 += 1) {
																					int distributionTempVariable$var82$33 = index$sample86$31;
																					double cv$probabilitySample86Value32 = (1.0 * distribution$sample86[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$31]);
																					int traceTempVariable$currentState$34_1 = distributionTempVariable$var82$33;
																					if((index$sample$29 == sample)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$34_1)) {
																										{
																											{
																												double cv$temp$6$var100;
																												{
																													double var100 = traceTempVariable$var100$9_1;
																													cv$temp$6$var100 = var100;
																												}
																												double cv$temp$7$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$34_1];
																													cv$temp$7$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value32);
																											}
																										}
																									}
																								}
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
								} else {
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample73$5 = 0; index$sample73$5 < noStates; index$sample73$5 += 1) {
												int distributionTempVariable$var69$7 = index$sample73$5;
												double cv$probabilitySample73Value6 = (1.0 * distribution$sample73[((index$sample$4 - 0) / 1)][index$sample73$5]);
												int traceTempVariable$currentState$8_1 = distributionTempVariable$var69$7;
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var91)) {
														double traceTempVariable$var100$10_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$8_1)) {
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		int traceTempVariable$currentState$37_1 = distributionTempVariable$var69$7;
																		if((index$sample$4 == sample)) {
																			if((0 == timeStep$var91)) {
																				for(int var46 = 0; var46 < noStates; var46 += 1) {
																					if(metric_valid_g[sample][timeStep$var91]) {
																						if((var46 == traceTempVariable$currentState$37_1)) {
																							{
																								{
																									double cv$temp$8$var100;
																									{
																										double var100 = traceTempVariable$var100$10_1;
																										cv$temp$8$var100 = var100;
																									}
																									double cv$temp$9$var101;
																									{
																										double var101 = metric_var[traceTempVariable$currentState$37_1];
																										cv$temp$9$var101 = var101;
																									}
																									if(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value6);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																			if(!(index$sample$38 == index$sample$4)) {
																				for(int index$sample73$39 = 0; index$sample73$39 < noStates; index$sample73$39 += 1) {
																					int distributionTempVariable$var69$41 = index$sample73$39;
																					double cv$probabilitySample73Value40 = (cv$probabilitySample73Value6 * distribution$sample73[((index$sample$38 - 0) / 1)][index$sample73$39]);
																					int traceTempVariable$currentState$42_1 = distributionTempVariable$var69$41;
																					if((index$sample$38 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$42_1)) {
																										{
																											{
																												double cv$temp$10$var100;
																												{
																													double var100 = traceTempVariable$var100$10_1;
																													cv$temp$10$var100 = var100;
																												}
																												double cv$temp$11$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$42_1];
																													cv$temp$11$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value40);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample86) {
																			for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
																				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$45_1]; timeStep$var74 += 1) {
																					if((index$sample$45_1 == sample)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$8_1)) {
																										{
																											{
																												double cv$temp$12$var100;
																												{
																													double var100 = traceTempVariable$var100$10_1;
																													cv$temp$12$var100 = var100;
																												}
																												double cv$temp$13$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$8_1];
																													cv$temp$13$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value6);
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
																			for(int index$sample$46 = 0; index$sample$46 < noSamples; index$sample$46 += 1) {
																				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$46]; timeStep$var74 += 1) {
																					if(true) {
																						for(int index$sample86$48 = 0; index$sample86$48 < noStates; index$sample86$48 += 1) {
																							int distributionTempVariable$var82$50 = index$sample86$48;
																							double cv$probabilitySample86Value49 = (cv$probabilitySample73Value6 * distribution$sample86[((index$sample$46 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$48]);
																							int traceTempVariable$currentState$51_1 = distributionTempVariable$var82$50;
																							if((index$sample$46 == sample)) {
																								if((timeStep$var74 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[sample][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$51_1)) {
																												{
																													{
																														double cv$temp$14$var100;
																														{
																															double var100 = traceTempVariable$var100$10_1;
																															cv$temp$14$var100 = var100;
																														}
																														double cv$temp$15$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$51_1];
																															cv$temp$15$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value49);
																													}
																												}
																											}
																										}
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
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
								if(fixedFlag$sample86) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$13_1]; timeStep$var74 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var74 == timeStep$var91)) {
													double traceTempVariable$var100$20_1 = cv$currentValue;
													if(metric_valid_g[sample][timeStep$var91]) {
														if((var37 == st[sample][timeStep$var91])) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	if(fixedFlag$sample73) {
																		for(int index$sample$54_1 = 0; index$sample$54_1 < noSamples; index$sample$54_1 += 1) {
																			if((index$sample$54_1 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == st[sample][timeStep$var91])) {
																								{
																									{
																										double cv$temp$16$var100;
																										{
																											double var100 = traceTempVariable$var100$20_1;
																											cv$temp$16$var100 = var100;
																										}
																										double cv$temp$17$var101;
																										{
																											double var101 = metric_var[st[sample][timeStep$var91]];
																											cv$temp$17$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))));
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
																	} else {
																		for(int index$sample$55 = 0; index$sample$55 < noSamples; index$sample$55 += 1) {
																			if(true) {
																				for(int index$sample73$56 = 0; index$sample73$56 < noStates; index$sample73$56 += 1) {
																					int distributionTempVariable$var69$58 = index$sample73$56;
																					double cv$probabilitySample73Value57 = (1.0 * distribution$sample73[((index$sample$55 - 0) / 1)][index$sample73$56]);
																					int traceTempVariable$currentState$59_1 = distributionTempVariable$var69$58;
																					if((index$sample$55 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$59_1)) {
																										{
																											{
																												double cv$temp$18$var100;
																												{
																													double var100 = traceTempVariable$var100$20_1;
																													cv$temp$18$var100 = var100;
																												}
																												double cv$temp$19$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$59_1];
																													cv$temp$19$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value57);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																		for(int index$timeStep$62_2 = 1; index$timeStep$62_2 < length$metric[index$sample$62_1]; index$timeStep$62_2 += 1) {
																			if((index$sample$62_1 == sample)) {
																				if((index$timeStep$62_2 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == st[sample][timeStep$var91])) {
																								{
																									{
																										double cv$temp$20$var100;
																										{
																											double var100 = traceTempVariable$var100$20_1;
																											cv$temp$20$var100 = var100;
																										}
																										double cv$temp$21$var101;
																										{
																											double var101 = metric_var[st[sample][timeStep$var91]];
																											cv$temp$21$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))));
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
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$14]; timeStep$var74 += 1) {
											if(true) {
												for(int index$sample86$16 = 0; index$sample86$16 < noStates; index$sample86$16 += 1) {
													int distributionTempVariable$var82$18 = index$sample86$16;
													double cv$probabilitySample86Value17 = (1.0 * distribution$sample86[((index$sample$14 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$16]);
													int traceTempVariable$currentState$19_1 = distributionTempVariable$var82$18;
													if((index$sample$14 == sample)) {
														if((timeStep$var74 == timeStep$var91)) {
															double traceTempVariable$var100$21_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var91]) {
																if((var37 == traceTempVariable$currentState$19_1)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample73) {
																				for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																					if((index$sample$64_1 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$19_1)) {
																										{
																											{
																												double cv$temp$22$var100;
																												{
																													double var100 = traceTempVariable$var100$21_1;
																													cv$temp$22$var100 = var100;
																												}
																												double cv$temp$23$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$19_1];
																													cv$temp$23$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value17);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$65 = 0; index$sample$65 < noSamples; index$sample$65 += 1) {
																					if(true) {
																						for(int index$sample73$66 = 0; index$sample73$66 < noStates; index$sample73$66 += 1) {
																							int distributionTempVariable$var69$68 = index$sample73$66;
																							double cv$probabilitySample73Value67 = (cv$probabilitySample86Value17 * distribution$sample73[((index$sample$65 - 0) / 1)][index$sample73$66]);
																							int traceTempVariable$currentState$69_1 = distributionTempVariable$var69$68;
																							if((index$sample$65 == sample)) {
																								if((0 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[sample][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$69_1)) {
																												{
																													{
																														double cv$temp$24$var100;
																														{
																															double var100 = traceTempVariable$var100$21_1;
																															cv$temp$24$var100 = var100;
																														}
																														double cv$temp$25$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$69_1];
																															cv$temp$25$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value67);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			int traceTempVariable$currentState$72_1 = distributionTempVariable$var82$18;
																			if((index$sample$14 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$72_1)) {
																								{
																									{
																										double cv$temp$26$var100;
																										{
																											double var100 = traceTempVariable$var100$21_1;
																											cv$temp$26$var100 = var100;
																										}
																										double cv$temp$27$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$72_1];
																											cv$temp$27$var101 = var101;
																										}
																										if(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value17);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$73 = 0; index$sample$73 < noSamples; index$sample$73 += 1) {
																				for(int index$timeStep$74 = 1; index$timeStep$74 < length$metric[index$sample$73]; index$timeStep$74 += 1) {
																					if(!((index$timeStep$74 == timeStep$var74) && (index$sample$73 == index$sample$14))) {
																						for(int index$sample86$75 = 0; index$sample86$75 < noStates; index$sample86$75 += 1) {
																							int distributionTempVariable$var82$77 = index$sample86$75;
																							double cv$probabilitySample86Value76 = (cv$probabilitySample86Value17 * distribution$sample86[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample86$75]);
																							int traceTempVariable$currentState$78_1 = distributionTempVariable$var82$77;
																							if((index$sample$73 == sample)) {
																								if((index$timeStep$74 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[sample][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$78_1)) {
																												{
																													{
																														double cv$temp$28$var100;
																														{
																															double var100 = traceTempVariable$var100$21_1;
																															cv$temp$28$var100 = var100;
																														}
																														double cv$temp$29$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$78_1];
																															cv$temp$29$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value76);
																													}
																												}
																											}
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
			double var38 = cv$originalValue;
			metric_mean[var37] = var38;
		}
	}

	private final void sample50(int var46, int threadID$cv$var46, Rng RNG$) {
		double cv$originalValue = metric_var[var46];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var47 = cv$proposedValue;
					metric_var[var46] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var40;
				{
					cv$temp$0$var40 = 1.0;
				}
				double cv$temp$1$var41;
				{
					cv$temp$1$var41 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var40, cv$temp$1$var41));
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
								if(fixedFlag$sample73) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var91)) {
												double traceTempVariable$var101$9_1 = cv$currentValue;
												if(metric_valid_g[sample][timeStep$var91]) {
													if((var46 == st[sample][timeStep$var91])) {
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int index$sample$26_1 = 0; index$sample$26_1 < noSamples; index$sample$26_1 += 1) {
																	if((index$sample$26_1 == sample)) {
																		if((0 == timeStep$var91)) {
																			for(int var37 = 0; var37 < noStates; var37 += 1) {
																				if(metric_valid_g[sample][timeStep$var91]) {
																					if((var37 == st[sample][timeStep$var91])) {
																						{
																							{
																								double cv$temp$2$var100;
																								{
																									double var100 = metric_mean[st[sample][timeStep$var91]];
																									cv$temp$2$var100 = var100;
																								}
																								double cv$temp$3$var101;
																								{
																									double var101 = traceTempVariable$var101$9_1;
																									cv$temp$3$var101 = var101;
																								}
																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$2$var100) / Math.sqrt(cv$temp$3$var101))) - (0.5 * Math.log(cv$temp$3$var101)))));
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
																if(fixedFlag$sample86) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$28_1]; timeStep$var74 += 1) {
																			if((index$sample$28_1 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var37 = 0; var37 < noStates; var37 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var37 == st[sample][timeStep$var91])) {
																								{
																									{
																										double cv$temp$4$var100;
																										{
																											double var100 = metric_mean[st[sample][timeStep$var91]];
																											cv$temp$4$var100 = var100;
																										}
																										double cv$temp$5$var101;
																										{
																											double var101 = traceTempVariable$var101$9_1;
																											cv$temp$5$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$4$var100) / Math.sqrt(cv$temp$5$var101))) - (0.5 * Math.log(cv$temp$5$var101)))));
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
																} else {
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$29]; timeStep$var74 += 1) {
																			if(true) {
																				for(int index$sample86$31 = 0; index$sample86$31 < noStates; index$sample86$31 += 1) {
																					int distributionTempVariable$var82$33 = index$sample86$31;
																					double cv$probabilitySample86Value32 = (1.0 * distribution$sample86[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$31]);
																					int traceTempVariable$currentState$34_1 = distributionTempVariable$var82$33;
																					if((index$sample$29 == sample)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var37 = 0; var37 < noStates; var37 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var37 == traceTempVariable$currentState$34_1)) {
																										{
																											{
																												double cv$temp$6$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$34_1];
																													cv$temp$6$var100 = var100;
																												}
																												double cv$temp$7$var101;
																												{
																													double var101 = traceTempVariable$var101$9_1;
																													cv$temp$7$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value32);
																											}
																										}
																									}
																								}
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
								} else {
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample73$5 = 0; index$sample73$5 < noStates; index$sample73$5 += 1) {
												int distributionTempVariable$var69$7 = index$sample73$5;
												double cv$probabilitySample73Value6 = (1.0 * distribution$sample73[((index$sample$4 - 0) / 1)][index$sample73$5]);
												int traceTempVariable$currentState$8_1 = distributionTempVariable$var69$7;
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var91)) {
														double traceTempVariable$var101$10_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var91]) {
															if((var46 == traceTempVariable$currentState$8_1)) {
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		int traceTempVariable$currentState$37_1 = distributionTempVariable$var69$7;
																		if((index$sample$4 == sample)) {
																			if((0 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[sample][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$37_1)) {
																							{
																								{
																									double cv$temp$8$var100;
																									{
																										double var100 = metric_mean[traceTempVariable$currentState$37_1];
																										cv$temp$8$var100 = var100;
																									}
																									double cv$temp$9$var101;
																									{
																										double var101 = traceTempVariable$var101$10_1;
																										cv$temp$9$var101 = var101;
																									}
																									if(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value6);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																			if(!(index$sample$38 == index$sample$4)) {
																				for(int index$sample73$39 = 0; index$sample73$39 < noStates; index$sample73$39 += 1) {
																					int distributionTempVariable$var69$41 = index$sample73$39;
																					double cv$probabilitySample73Value40 = (cv$probabilitySample73Value6 * distribution$sample73[((index$sample$38 - 0) / 1)][index$sample73$39]);
																					int traceTempVariable$currentState$42_1 = distributionTempVariable$var69$41;
																					if((index$sample$38 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var37 = 0; var37 < noStates; var37 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var37 == traceTempVariable$currentState$42_1)) {
																										{
																											{
																												double cv$temp$10$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$42_1];
																													cv$temp$10$var100 = var100;
																												}
																												double cv$temp$11$var101;
																												{
																													double var101 = traceTempVariable$var101$10_1;
																													cv$temp$11$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value40);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample86) {
																			for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
																				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$45_1]; timeStep$var74 += 1) {
																					if((index$sample$45_1 == sample)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var37 = 0; var37 < noStates; var37 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var37 == traceTempVariable$currentState$8_1)) {
																										{
																											{
																												double cv$temp$12$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$8_1];
																													cv$temp$12$var100 = var100;
																												}
																												double cv$temp$13$var101;
																												{
																													double var101 = traceTempVariable$var101$10_1;
																													cv$temp$13$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value6);
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
																			for(int index$sample$46 = 0; index$sample$46 < noSamples; index$sample$46 += 1) {
																				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$46]; timeStep$var74 += 1) {
																					if(true) {
																						for(int index$sample86$48 = 0; index$sample86$48 < noStates; index$sample86$48 += 1) {
																							int distributionTempVariable$var82$50 = index$sample86$48;
																							double cv$probabilitySample86Value49 = (cv$probabilitySample73Value6 * distribution$sample86[((index$sample$46 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$48]);
																							int traceTempVariable$currentState$51_1 = distributionTempVariable$var82$50;
																							if((index$sample$46 == sample)) {
																								if((timeStep$var74 == timeStep$var91)) {
																									for(int var37 = 0; var37 < noStates; var37 += 1) {
																										if(metric_valid_g[sample][timeStep$var91]) {
																											if((var37 == traceTempVariable$currentState$51_1)) {
																												{
																													{
																														double cv$temp$14$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$51_1];
																															cv$temp$14$var100 = var100;
																														}
																														double cv$temp$15$var101;
																														{
																															double var101 = traceTempVariable$var101$10_1;
																															cv$temp$15$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value49);
																													}
																												}
																											}
																										}
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
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
								if(fixedFlag$sample86) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$13_1]; timeStep$var74 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var74 == timeStep$var91)) {
													double traceTempVariable$var101$20_1 = cv$currentValue;
													if(metric_valid_g[sample][timeStep$var91]) {
														if((var46 == st[sample][timeStep$var91])) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	if(fixedFlag$sample73) {
																		for(int index$sample$54_1 = 0; index$sample$54_1 < noSamples; index$sample$54_1 += 1) {
																			if((index$sample$54_1 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var37 = 0; var37 < noStates; var37 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var37 == st[sample][timeStep$var91])) {
																								{
																									{
																										double cv$temp$16$var100;
																										{
																											double var100 = metric_mean[st[sample][timeStep$var91]];
																											cv$temp$16$var100 = var100;
																										}
																										double cv$temp$17$var101;
																										{
																											double var101 = traceTempVariable$var101$20_1;
																											cv$temp$17$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))));
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
																	} else {
																		for(int index$sample$55 = 0; index$sample$55 < noSamples; index$sample$55 += 1) {
																			if(true) {
																				for(int index$sample73$56 = 0; index$sample73$56 < noStates; index$sample73$56 += 1) {
																					int distributionTempVariable$var69$58 = index$sample73$56;
																					double cv$probabilitySample73Value57 = (1.0 * distribution$sample73[((index$sample$55 - 0) / 1)][index$sample73$56]);
																					int traceTempVariable$currentState$59_1 = distributionTempVariable$var69$58;
																					if((index$sample$55 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var37 = 0; var37 < noStates; var37 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var37 == traceTempVariable$currentState$59_1)) {
																										{
																											{
																												double cv$temp$18$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$59_1];
																													cv$temp$18$var100 = var100;
																												}
																												double cv$temp$19$var101;
																												{
																													double var101 = traceTempVariable$var101$20_1;
																													cv$temp$19$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value57);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																		for(int index$timeStep$62_2 = 1; index$timeStep$62_2 < length$metric[index$sample$62_1]; index$timeStep$62_2 += 1) {
																			if((index$sample$62_1 == sample)) {
																				if((index$timeStep$62_2 == timeStep$var91)) {
																					for(int var37 = 0; var37 < noStates; var37 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var37 == st[sample][timeStep$var91])) {
																								{
																									{
																										double cv$temp$20$var100;
																										{
																											double var100 = metric_mean[st[sample][timeStep$var91]];
																											cv$temp$20$var100 = var100;
																										}
																										double cv$temp$21$var101;
																										{
																											double var101 = traceTempVariable$var101$20_1;
																											cv$temp$21$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))));
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
								} else {
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$14]; timeStep$var74 += 1) {
											if(true) {
												for(int index$sample86$16 = 0; index$sample86$16 < noStates; index$sample86$16 += 1) {
													int distributionTempVariable$var82$18 = index$sample86$16;
													double cv$probabilitySample86Value17 = (1.0 * distribution$sample86[((index$sample$14 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$16]);
													int traceTempVariable$currentState$19_1 = distributionTempVariable$var82$18;
													if((index$sample$14 == sample)) {
														if((timeStep$var74 == timeStep$var91)) {
															double traceTempVariable$var101$21_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var91]) {
																if((var46 == traceTempVariable$currentState$19_1)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample73) {
																				for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																					if((index$sample$64_1 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var37 = 0; var37 < noStates; var37 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var37 == traceTempVariable$currentState$19_1)) {
																										{
																											{
																												double cv$temp$22$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$19_1];
																													cv$temp$22$var100 = var100;
																												}
																												double cv$temp$23$var101;
																												{
																													double var101 = traceTempVariable$var101$21_1;
																													cv$temp$23$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value17);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			} else {
																				for(int index$sample$65 = 0; index$sample$65 < noSamples; index$sample$65 += 1) {
																					if(true) {
																						for(int index$sample73$66 = 0; index$sample73$66 < noStates; index$sample73$66 += 1) {
																							int distributionTempVariable$var69$68 = index$sample73$66;
																							double cv$probabilitySample73Value67 = (cv$probabilitySample86Value17 * distribution$sample73[((index$sample$65 - 0) / 1)][index$sample73$66]);
																							int traceTempVariable$currentState$69_1 = distributionTempVariable$var69$68;
																							if((index$sample$65 == sample)) {
																								if((0 == timeStep$var91)) {
																									for(int var37 = 0; var37 < noStates; var37 += 1) {
																										if(metric_valid_g[sample][timeStep$var91]) {
																											if((var37 == traceTempVariable$currentState$69_1)) {
																												{
																													{
																														double cv$temp$24$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$69_1];
																															cv$temp$24$var100 = var100;
																														}
																														double cv$temp$25$var101;
																														{
																															double var101 = traceTempVariable$var101$21_1;
																															cv$temp$25$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value67);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			int traceTempVariable$currentState$72_1 = distributionTempVariable$var82$18;
																			if((index$sample$14 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var37 = 0; var37 < noStates; var37 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var37 == traceTempVariable$currentState$72_1)) {
																								{
																									{
																										double cv$temp$26$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$72_1];
																											cv$temp$26$var100 = var100;
																										}
																										double cv$temp$27$var101;
																										{
																											double var101 = traceTempVariable$var101$21_1;
																											cv$temp$27$var101 = var101;
																										}
																										if(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value17);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$73 = 0; index$sample$73 < noSamples; index$sample$73 += 1) {
																				for(int index$timeStep$74 = 1; index$timeStep$74 < length$metric[index$sample$73]; index$timeStep$74 += 1) {
																					if(!((index$timeStep$74 == timeStep$var74) && (index$sample$73 == index$sample$14))) {
																						for(int index$sample86$75 = 0; index$sample86$75 < noStates; index$sample86$75 += 1) {
																							int distributionTempVariable$var82$77 = index$sample86$75;
																							double cv$probabilitySample86Value76 = (cv$probabilitySample86Value17 * distribution$sample86[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample86$75]);
																							int traceTempVariable$currentState$78_1 = distributionTempVariable$var82$77;
																							if((index$sample$73 == sample)) {
																								if((index$timeStep$74 == timeStep$var91)) {
																									for(int var37 = 0; var37 < noStates; var37 += 1) {
																										if(metric_valid_g[sample][timeStep$var91]) {
																											if((var37 == traceTempVariable$currentState$78_1)) {
																												{
																													{
																														double cv$temp$28$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$78_1];
																															cv$temp$28$var100 = var100;
																														}
																														double cv$temp$29$var101;
																														{
																															double var101 = traceTempVariable$var101$21_1;
																															cv$temp$29$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value76);
																													}
																												}
																											}
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
			double var47 = cv$originalValue;
			metric_var[var46] = var47;
		}
	}

	private final void sample59(int var55, int threadID$cv$var55, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
							if(fixedFlag$sample73) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == timeStep$var91)) {
											if((var55 == st[sample][timeStep$var91])) {
												{
													{
														{
															{
																{
																	cv$count = (cv$count + 1.0);
																	if(metric_valid_g[sample][timeStep$var91])
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
										for(int index$sample73$5 = 0; index$sample73$5 < noStates; index$sample73$5 += 1) {
											int distributionTempVariable$var69$7 = index$sample73$5;
											double cv$probabilitySample73Value6 = (1.0 * distribution$sample73[((index$sample$4 - 0) / 1)][index$sample73$5]);
											int traceTempVariable$currentState$8_1 = distributionTempVariable$var69$7;
											if((index$sample$4 == sample)) {
												if((0 == timeStep$var91)) {
													if((var55 == traceTempVariable$currentState$8_1)) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + cv$probabilitySample73Value6);
																			if(metric_valid_g[sample][timeStep$var91])
																				cv$sum = (cv$sum + cv$probabilitySample73Value6);
																		}
																	}
																}
															}
														}
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
						for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
							if(fixedFlag$sample86) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$13_1]; timeStep$var74 += 1) {
										if((index$sample$13_1 == sample)) {
											if((timeStep$var74 == timeStep$var91)) {
												if((var55 == st[sample][timeStep$var91])) {
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1.0);
																		if(metric_valid_g[sample][timeStep$var91])
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
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$14]; timeStep$var74 += 1) {
										if(true) {
											for(int index$sample86$16 = 0; index$sample86$16 < noStates; index$sample86$16 += 1) {
												int distributionTempVariable$var82$18 = index$sample86$16;
												double cv$probabilitySample86Value17 = (1.0 * distribution$sample86[((index$sample$14 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$16]);
												int traceTempVariable$currentState$19_1 = distributionTempVariable$var82$18;
												if((index$sample$14 == sample)) {
													if((timeStep$var74 == timeStep$var91)) {
														if((var55 == traceTempVariable$currentState$19_1)) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + cv$probabilitySample86Value17);
																				if(metric_valid_g[sample][timeStep$var91])
																					cv$sum = (cv$sum + cv$probabilitySample86Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var56 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		metric_valid_bias[var55] = var56;
	}

	private final void sample73(int sample, int threadID$cv$sample, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var69$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$sample$1 = sample;
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$initialStateDistribution.length))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var79$2_1 = cv$currentValue;
						for(int index$sample$2_2 = 0; index$sample$2_2 < noSamples; index$sample$2_2 += 1) {
							if((sample == index$sample$2_2)) {
								for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$2_2]; timeStep$var74 += 1) {
									if((0 == (timeStep$var74 - 1))) {
										if(fixedFlag$sample86) {
											{
												int index$timeStep$4 = timeStep$var74;
												int index$sample$5 = index$sample$2_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var25 = 0; var25 < noStates; var25 += 1) {
														if((var25 == traceTempVariable$var79$2_1)) {
															{
																{
																	double[] cv$temp$1$var80;
																	{
																		double[] var80 = m[traceTempVariable$var79$2_1];
																		cv$temp$1$var80 = var80;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var74]) && (st[index$sample$2_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$2_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var74]) && (st[index$sample$2_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$2_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var74]) && (st[index$sample$2_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$2_2][timeStep$var74]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var74]) && (st[index$sample$2_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$2_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$2_2][timeStep$var74]) && (st[index$sample$2_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$2_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$currentState$8_1 = cv$currentValue;
						for(int index$sample$8_2 = 0; index$sample$8_2 < noSamples; index$sample$8_2 += 1) {
							if((sample == index$sample$8_2)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$8_2]; timeStep$var91 += 1) {
									if((0 == timeStep$var91)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == traceTempVariable$currentState$8_1)) {
														{
															{
																double cv$temp$2$var94;
																{
																	double var94 = metric_valid_bias[traceTempVariable$currentState$8_1];
																	cv$temp$2$var94 = var94;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$8_2][timeStep$var91], cv$temp$2$var94)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$8_2][timeStep$var91], cv$temp$2$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$8_2][timeStep$var91], cv$temp$2$var94));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$8_2][timeStep$var91], cv$temp$2$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$8_2][timeStep$var91], cv$temp$2$var94)));
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
						boolean[][] guard$sample73gaussian108 = guard$sample73gaussian108$global[threadID$cv$sample];
						for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
							if((sample == index$sample$12_1)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$12_1]; timeStep$var91 += 1) {
									if((0 == timeStep$var91))
										guard$sample73gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
								}
							}
						}
						for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
							if((sample == index$sample$13_1)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$13_1]; timeStep$var91 += 1) {
									if((0 == timeStep$var91))
										guard$sample73gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
								}
							}
						}
						int traceTempVariable$currentState$14_1 = cv$currentValue;
						for(int index$sample$14_2 = 0; index$sample$14_2 < noSamples; index$sample$14_2 += 1) {
							if((sample == index$sample$14_2)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$14_2]; timeStep$var91 += 1) {
									if((0 == timeStep$var91)) {
										if(!guard$sample73gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
											guard$sample73gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var37 = 0; var37 < noStates; var37 += 1) {
														if(metric_valid_g[index$sample$14_2][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$14_1)) {
																int traceTempVariable$currentState$19_1 = cv$currentValue;
																if((index$sample$1 == index$sample$14_2)) {
																	if((0 == timeStep$var91)) {
																		for(int var46 = 0; var46 < noStates; var46 += 1) {
																			if(metric_valid_g[index$sample$14_2][timeStep$var91]) {
																				if((var46 == traceTempVariable$currentState$19_1)) {
																					{
																						{
																							double cv$temp$3$var100;
																							{
																								double var100 = metric_mean[traceTempVariable$currentState$19_1];
																								cv$temp$3$var100 = var100;
																							}
																							double cv$temp$4$var101;
																							{
																								double var101 = metric_var[traceTempVariable$currentState$19_1];
																								cv$temp$4$var101 = var101;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$sample$20 = 0; index$sample$20 < noSamples; index$sample$20 += 1) {
																	if(!(index$sample$20 == index$sample$1)) {
																		for(int index$sample73$21 = 0; index$sample73$21 < noStates; index$sample73$21 += 1) {
																			int distributionTempVariable$var69$23 = index$sample73$21;
																			double cv$probabilitySample73Value22 = (1.0 * distribution$sample73[((index$sample$20 - 0) / 1)][index$sample73$21]);
																			int traceTempVariable$currentState$24_1 = distributionTempVariable$var69$23;
																			if((index$sample$20 == index$sample$14_2)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$14_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$24_1)) {
																								{
																									{
																										double cv$temp$5$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$24_1];
																											cv$temp$5$var100 = var100;
																										}
																										double cv$temp$6$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$24_1];
																											cv$temp$6$var101 = var101;
																										}
																										if(((Math.log(cv$probabilitySample73Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value22) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value22);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													for(int var37 = 0; var37 < noStates; var37 += 1) {
														if(metric_valid_g[index$sample$14_2][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$14_1)) {
																if(fixedFlag$sample86) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$28_1]; timeStep$var74 += 1) {
																			if((index$sample$28_1 == index$sample$14_2)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$14_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$14_1)) {
																								{
																									{
																										double cv$temp$7$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$14_1];
																											cv$temp$7$var100 = var100;
																										}
																										double cv$temp$8$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$14_1];
																											cv$temp$8$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))));
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
																} else {
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$29]; timeStep$var74 += 1) {
																			if(true) {
																				for(int index$sample86$31 = 0; index$sample86$31 < noStates; index$sample86$31 += 1) {
																					int distributionTempVariable$var82$33 = index$sample86$31;
																					double cv$probabilitySample86Value32 = (1.0 * distribution$sample86[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$31]);
																					int traceTempVariable$currentState$34_1 = distributionTempVariable$var82$33;
																					if((index$sample$29 == index$sample$14_2)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$14_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$34_1)) {
																										{
																											{
																												double cv$temp$9$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$34_1];
																													cv$temp$9$var100 = var100;
																												}
																												double cv$temp$10$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$34_1];
																													cv$temp$10$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$14_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value32);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int index$sample$15_2 = 0; index$sample$15_2 < noSamples; index$sample$15_2 += 1) {
							if((sample == index$sample$15_2)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$15_2]; timeStep$var91 += 1) {
									if((0 == timeStep$var91)) {
										if(!guard$sample73gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
											guard$sample73gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													int traceTempVariable$currentState$37_1 = cv$currentValue;
													if((index$sample$1 == index$sample$15_2)) {
														if((0 == timeStep$var91)) {
															for(int var37 = 0; var37 < noStates; var37 += 1) {
																if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																	if((var37 == traceTempVariable$currentState$37_1)) {
																		for(int var46 = 0; var46 < noStates; var46 += 1) {
																			if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																				if((var46 == traceTempVariable$currentState$37_1)) {
																					{
																						{
																							double cv$temp$11$var100;
																							{
																								double var100 = metric_mean[traceTempVariable$currentState$37_1];
																								cv$temp$11$var100 = var100;
																							}
																							double cv$temp$12$var101;
																							{
																								double var101 = metric_var[traceTempVariable$currentState$37_1];
																								cv$temp$12$var101 = var101;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))));
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
													for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
														if(!(index$sample$38 == index$sample$1)) {
															for(int index$sample73$39 = 0; index$sample73$39 < noStates; index$sample73$39 += 1) {
																int distributionTempVariable$var69$41 = index$sample73$39;
																double cv$probabilitySample73Value40 = (1.0 * distribution$sample73[((index$sample$38 - 0) / 1)][index$sample73$39]);
																int traceTempVariable$currentState$42_1 = distributionTempVariable$var69$41;
																if((index$sample$38 == index$sample$15_2)) {
																	if((0 == timeStep$var91)) {
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$42_1)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$42_1)) {
																								{
																									{
																										double cv$temp$13$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$42_1];
																											cv$temp$13$var100 = var100;
																										}
																										double cv$temp$14$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$42_1];
																											cv$temp$14$var101 = var101;
																										}
																										if(((Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value40);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													if(fixedFlag$sample86) {
														for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
															for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$47_1]; timeStep$var74 += 1) {
																if((index$sample$47_1 == index$sample$15_2)) {
																	if((timeStep$var74 == timeStep$var91)) {
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$15_1)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$15_1)) {
																								{
																									{
																										double cv$temp$15$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$15_1];
																											cv$temp$15$var100 = var100;
																										}
																										double cv$temp$16$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$15_1];
																											cv$temp$16$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))));
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
													} else {
														for(int index$sample$48 = 0; index$sample$48 < noSamples; index$sample$48 += 1) {
															for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$48]; timeStep$var74 += 1) {
																if(true) {
																	for(int index$sample86$50 = 0; index$sample86$50 < noStates; index$sample86$50 += 1) {
																		int distributionTempVariable$var82$52 = index$sample86$50;
																		double cv$probabilitySample86Value51 = (1.0 * distribution$sample86[((index$sample$48 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample86$50]);
																		int traceTempVariable$currentState$53_1 = distributionTempVariable$var82$52;
																		if((index$sample$48 == index$sample$15_2)) {
																			if((timeStep$var74 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$53_1)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$53_1)) {
																										{
																											{
																												double cv$temp$17$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$53_1];
																													cv$temp$17$var100 = var100;
																												}
																												double cv$temp$18$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$53_1];
																													cv$temp$18$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample86Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value51);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
					int traceTempVariable$var79$66_1 = cv$currentValue;
					for(int index$sample$66_2 = 0; index$sample$66_2 < noSamples; index$sample$66_2 += 1) {
						if((sample == index$sample$66_2)) {
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$66_2]; timeStep$var74 += 1) {
								if((0 == (timeStep$var74 - 1))) {
									if(!fixedFlag$sample86) {
										{
											int index$timeStep$68 = timeStep$var74;
											int index$sample$69 = index$sample$66_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var81[threadID$cv$sample];
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var25 = 0; var25 < noStates; var25 += 1) {
												if((var25 == traceTempVariable$var79$66_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var80;
														{
															double[] var80 = m[traceTempVariable$var79$66_1];
															cv$temp$19$var80 = var80;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var80);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample86[((index$sample$66_2 - 0) / 1)][((timeStep$var74 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample73[((sample - 0) / 1)];
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

	private final void sample86(int sample, int timeStep$var74, int threadID$cv$sample, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$timeStep$1 = timeStep$var74;
			int index$sample$2 = sample;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample73) {
				for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
					if((index$sample$3_1 == sample)) {
						if((0 == (timeStep$var74 - 1))) {
							for(int var25 = 0; var25 < noStates; var25 += 1) {
								if((var25 == st[sample][(timeStep$var74 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var80;
									{
										double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
										cv$temp$0$var80 = var80;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var80.length))?Math.log(cv$temp$0$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var79$20_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$24_1 = cv$currentValue;
											for(int index$sample$24_2 = 0; index$sample$24_2 < noSamples; index$sample$24_2 += 1) {
												if((sample == index$sample$24_2)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$24_2]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91)) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var55 = 0; var55 < noStates; var55 += 1) {
																		if((var55 == traceTempVariable$currentState$24_1)) {
																			{
																				{
																					double cv$temp$4$var94;
																					{
																						double var94 = metric_valid_bias[traceTempVariable$currentState$24_1];
																						cv$temp$4$var94 = var94;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$24_2][timeStep$var91], cv$temp$4$var94)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$24_2][timeStep$var91], cv$temp$4$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$24_2][timeStep$var91], cv$temp$4$var94));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$24_2][timeStep$var91], cv$temp$4$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$24_2][timeStep$var91], cv$temp$4$var94)));
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
											boolean[][] guard$sample86gaussian108 = guard$sample86gaussian108$global[threadID$cv$sample];
											for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
												if((sample == index$sample$40_1)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$40_1]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91))
															guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
													}
												}
											}
											for(int index$sample$44_1 = 0; index$sample$44_1 < noSamples; index$sample$44_1 += 1) {
												if((sample == index$sample$44_1)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$44_1]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91))
															guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
													}
												}
											}
											int traceTempVariable$currentState$48_1 = cv$currentValue;
											for(int index$sample$48_2 = 0; index$sample$48_2 < noSamples; index$sample$48_2 += 1) {
												if((sample == index$sample$48_2)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$48_2]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91)) {
															if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$48_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$48_1)) {
																					for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																						if((index$sample$65_1 == index$sample$48_2)) {
																							if((0 == timeStep$var91)) {
																								for(int var46 = 0; var46 < noStates; var46 += 1) {
																									if(metric_valid_g[index$sample$48_2][timeStep$var91]) {
																										if((var46 == traceTempVariable$currentState$48_1)) {
																											{
																												{
																													double cv$temp$8$var100;
																													{
																														double var100 = metric_mean[traceTempVariable$currentState$48_1];
																														cv$temp$8$var100 = var100;
																													}
																													double cv$temp$9$var101;
																													{
																														double var101 = metric_var[traceTempVariable$currentState$48_1];
																														cv$temp$9$var101 = var101;
																													}
																													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))));
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
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$48_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$48_1)) {
																					int traceTempVariable$currentState$68_1 = cv$currentValue;
																					if((index$sample$2 == index$sample$48_2)) {
																						if((index$timeStep$1 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$48_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$68_1)) {
																										{
																											{
																												double cv$temp$10$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$68_1];
																													cv$temp$10$var100 = var100;
																												}
																												double cv$temp$11$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$68_1];
																													cv$temp$11$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$69 = 0; index$sample$69 < noSamples; index$sample$69 += 1) {
																						for(int index$timeStep$70 = 1; index$timeStep$70 < length$metric[index$sample$69]; index$timeStep$70 += 1) {
																							if(!((index$timeStep$70 == index$timeStep$1) && (index$sample$69 == index$sample$2))) {
																								for(int index$sample86$71 = 0; index$sample86$71 < noStates; index$sample86$71 += 1) {
																									int distributionTempVariable$var82$73 = index$sample86$71;
																									double cv$probabilitySample86Value72 = (1.0 * distribution$sample86[((index$sample$69 - 0) / 1)][((index$timeStep$70 - 1) / 1)][index$sample86$71]);
																									int traceTempVariable$currentState$74_1 = distributionTempVariable$var82$73;
																									if((index$sample$69 == index$sample$48_2)) {
																										if((index$timeStep$70 == timeStep$var91)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$48_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$74_1)) {
																														{
																															{
																																double cv$temp$12$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$74_1];
																																	cv$temp$12$var100 = var100;
																																}
																																double cv$temp$13$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$74_1];
																																	cv$temp$13$var101 = var101;
																																}
																																if(((Math.log(cv$probabilitySample86Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value72) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$48_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value72);
																															}
																														}
																													}
																												}
																											}
																										}
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
											int traceTempVariable$currentState$52_1 = cv$currentValue;
											for(int index$sample$52_2 = 0; index$sample$52_2 < noSamples; index$sample$52_2 += 1) {
												if((sample == index$sample$52_2)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$52_2]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91)) {
															if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int index$sample$136_1 = 0; index$sample$136_1 < noSamples; index$sample$136_1 += 1) {
																			if((index$sample$136_1 == index$sample$52_2)) {
																				if((0 == timeStep$var91)) {
																					for(int var37 = 0; var37 < noStates; var37 += 1) {
																						if(metric_valid_g[index$sample$52_2][timeStep$var91]) {
																							if((var37 == traceTempVariable$currentState$52_1)) {
																								for(int var46 = 0; var46 < noStates; var46 += 1) {
																									if(metric_valid_g[index$sample$52_2][timeStep$var91]) {
																										if((var46 == traceTempVariable$currentState$52_1)) {
																											{
																												{
																													double cv$temp$40$var100;
																													{
																														double var100 = metric_mean[traceTempVariable$currentState$52_1];
																														cv$temp$40$var100 = var100;
																													}
																													double cv$temp$41$var101;
																													{
																														double var101 = metric_var[traceTempVariable$currentState$52_1];
																														cv$temp$41$var101 = var101;
																													}
																													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))));
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
																		int traceTempVariable$currentState$139_1 = cv$currentValue;
																		if((index$sample$2 == index$sample$52_2)) {
																			if((index$timeStep$1 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$52_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$139_1)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$52_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$139_1)) {
																										{
																											{
																												double cv$temp$42$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$139_1];
																													cv$temp$42$var100 = var100;
																												}
																												double cv$temp$43$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$139_1];
																													cv$temp$43$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))));
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
																		for(int index$sample$140 = 0; index$sample$140 < noSamples; index$sample$140 += 1) {
																			for(int index$timeStep$141 = 1; index$timeStep$141 < length$metric[index$sample$140]; index$timeStep$141 += 1) {
																				if(!((index$timeStep$141 == index$timeStep$1) && (index$sample$140 == index$sample$2))) {
																					for(int index$sample86$142 = 0; index$sample86$142 < noStates; index$sample86$142 += 1) {
																						int distributionTempVariable$var82$144 = index$sample86$142;
																						double cv$probabilitySample86Value143 = (1.0 * distribution$sample86[((index$sample$140 - 0) / 1)][((index$timeStep$141 - 1) / 1)][index$sample86$142]);
																						int traceTempVariable$currentState$145_1 = distributionTempVariable$var82$144;
																						if((index$sample$140 == index$sample$52_2)) {
																							if((index$timeStep$141 == timeStep$var91)) {
																								for(int var37 = 0; var37 < noStates; var37 += 1) {
																									if(metric_valid_g[index$sample$52_2][timeStep$var91]) {
																										if((var37 == traceTempVariable$currentState$145_1)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$52_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$145_1)) {
																														{
																															{
																																double cv$temp$44$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$145_1];
																																	cv$temp$44$var100 = var100;
																																}
																																double cv$temp$45$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$145_1];
																																	cv$temp$45$var101 = var101;
																																}
																																if(((Math.log(cv$probabilitySample86Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value143) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$52_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value143);
																															}
																														}
																													}
																												}
																											}
																										}
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
				for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
					if(true) {
						for(int index$sample73$5 = 0; index$sample73$5 < noStates; index$sample73$5 += 1) {
							int distributionTempVariable$var69$7 = index$sample73$5;
							double cv$probabilitySample73Value6 = (1.0 * distribution$sample73[((index$sample$4 - 0) / 1)][index$sample73$5]);
							int traceTempVariable$var79$8_1 = distributionTempVariable$var69$7;
							if((index$sample$4 == sample)) {
								if((0 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == traceTempVariable$var79$8_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample73Value6);
											double[] cv$temp$1$var80;
											{
												double[] var80 = m[traceTempVariable$var79$8_1];
												cv$temp$1$var80 = var80;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample73Value6) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var79$21_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$25_1 = cv$currentValue;
													for(int index$sample$25_2 = 0; index$sample$25_2 < noSamples; index$sample$25_2 += 1) {
														if((sample == index$sample$25_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$25_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var55 = 0; var55 < noStates; var55 += 1) {
																				if((var55 == traceTempVariable$currentState$25_1)) {
																					{
																						{
																							double cv$temp$5$var94;
																							{
																								double var94 = metric_valid_bias[traceTempVariable$currentState$25_1];
																								cv$temp$5$var94 = var94;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$25_2][timeStep$var91], cv$temp$5$var94)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$25_2][timeStep$var91], cv$temp$5$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$25_2][timeStep$var91], cv$temp$5$var94));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$25_2][timeStep$var91], cv$temp$5$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$25_2][timeStep$var91], cv$temp$5$var94)));
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
													boolean[][] guard$sample86gaussian108 = guard$sample86gaussian108$global[threadID$cv$sample];
													for(int index$sample$41_1 = 0; index$sample$41_1 < noSamples; index$sample$41_1 += 1) {
														if((sample == index$sample$41_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$41_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
														if((sample == index$sample$45_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$45_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													int traceTempVariable$currentState$49_1 = cv$currentValue;
													for(int index$sample$49_2 = 0; index$sample$49_2 < noSamples; index$sample$49_2 += 1) {
														if((sample == index$sample$49_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$49_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$49_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$49_1)) {
																							int traceTempVariable$currentState$78_1 = distributionTempVariable$var69$7;
																							if((index$sample$4 == index$sample$49_2)) {
																								if((0 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$49_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$78_1)) {
																												{
																													{
																														double cv$temp$14$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$78_1];
																															cv$temp$14$var100 = var100;
																														}
																														double cv$temp$15$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$78_1];
																															cv$temp$15$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$79 = 0; index$sample$79 < noSamples; index$sample$79 += 1) {
																								if(!(index$sample$79 == index$sample$4)) {
																									for(int index$sample73$80 = 0; index$sample73$80 < noStates; index$sample73$80 += 1) {
																										int distributionTempVariable$var69$82 = index$sample73$80;
																										double cv$probabilitySample73Value81 = (1.0 * distribution$sample73[((index$sample$79 - 0) / 1)][index$sample73$80]);
																										int traceTempVariable$currentState$83_1 = distributionTempVariable$var69$82;
																										if((index$sample$79 == index$sample$49_2)) {
																											if((0 == timeStep$var91)) {
																												for(int var46 = 0; var46 < noStates; var46 += 1) {
																													if(metric_valid_g[index$sample$49_2][timeStep$var91]) {
																														if((var46 == traceTempVariable$currentState$83_1)) {
																															{
																																{
																																	double cv$temp$16$var100;
																																	{
																																		double var100 = metric_mean[traceTempVariable$currentState$83_1];
																																		cv$temp$16$var100 = var100;
																																	}
																																	double cv$temp$17$var101;
																																	{
																																		double var101 = metric_var[traceTempVariable$currentState$83_1];
																																		cv$temp$17$var101 = var101;
																																	}
																																	if(((Math.log(cv$probabilitySample73Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value81) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value81);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$49_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$49_1)) {
																							int traceTempVariable$currentState$87_1 = cv$currentValue;
																							if((index$sample$2 == index$sample$49_2)) {
																								if((index$timeStep$1 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$49_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$87_1)) {
																												{
																													{
																														double cv$temp$18$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$87_1];
																															cv$temp$18$var100 = var100;
																														}
																														double cv$temp$19$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$87_1];
																															cv$temp$19$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$88 = 0; index$sample$88 < noSamples; index$sample$88 += 1) {
																								for(int index$timeStep$89 = 1; index$timeStep$89 < length$metric[index$sample$88]; index$timeStep$89 += 1) {
																									if(!((index$timeStep$89 == index$timeStep$1) && (index$sample$88 == index$sample$2))) {
																										for(int index$sample86$90 = 0; index$sample86$90 < noStates; index$sample86$90 += 1) {
																											int distributionTempVariable$var82$92 = index$sample86$90;
																											double cv$probabilitySample86Value91 = (1.0 * distribution$sample86[((index$sample$88 - 0) / 1)][((index$timeStep$89 - 1) / 1)][index$sample86$90]);
																											int traceTempVariable$currentState$93_1 = distributionTempVariable$var82$92;
																											if((index$sample$88 == index$sample$49_2)) {
																												if((index$timeStep$89 == timeStep$var91)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$49_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$93_1)) {
																																{
																																	{
																																		double cv$temp$20$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$93_1];
																																			cv$temp$20$var100 = var100;
																																		}
																																		double cv$temp$21$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$93_1];
																																			cv$temp$21$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample86Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value91) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$49_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value91);
																																	}
																																}
																															}
																														}
																													}
																												}
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
													int traceTempVariable$currentState$53_1 = cv$currentValue;
													for(int index$sample$53_2 = 0; index$sample$53_2 < noSamples; index$sample$53_2 += 1) {
														if((sample == index$sample$53_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$53_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				int traceTempVariable$currentState$150_1 = distributionTempVariable$var69$7;
																				if((index$sample$4 == index$sample$53_2)) {
																					if((0 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$150_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$150_1)) {
																												{
																													{
																														double cv$temp$46$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$150_1];
																															cv$temp$46$var100 = var100;
																														}
																														double cv$temp$47$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$150_1];
																															cv$temp$47$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))));
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
																				for(int index$sample$151 = 0; index$sample$151 < noSamples; index$sample$151 += 1) {
																					if(!(index$sample$151 == index$sample$4)) {
																						for(int index$sample73$152 = 0; index$sample73$152 < noStates; index$sample73$152 += 1) {
																							int distributionTempVariable$var69$154 = index$sample73$152;
																							double cv$probabilitySample73Value153 = (1.0 * distribution$sample73[((index$sample$151 - 0) / 1)][index$sample73$152]);
																							int traceTempVariable$currentState$155_1 = distributionTempVariable$var69$154;
																							if((index$sample$151 == index$sample$53_2)) {
																								if((0 == timeStep$var91)) {
																									for(int var37 = 0; var37 < noStates; var37 += 1) {
																										if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																											if((var37 == traceTempVariable$currentState$155_1)) {
																												for(int var46 = 0; var46 < noStates; var46 += 1) {
																													if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																														if((var46 == traceTempVariable$currentState$155_1)) {
																															{
																																{
																																	double cv$temp$48$var100;
																																	{
																																		double var100 = metric_mean[traceTempVariable$currentState$155_1];
																																		cv$temp$48$var100 = var100;
																																	}
																																	double cv$temp$49$var101;
																																	{
																																		double var101 = metric_var[traceTempVariable$currentState$155_1];
																																		cv$temp$49$var101 = var101;
																																	}
																																	if(((Math.log(cv$probabilitySample73Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value153) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value153);
																																}
																															}
																														}
																													}
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
																				if((index$sample$2 == index$sample$53_2)) {
																					if((index$timeStep$1 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$160_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$160_1)) {
																												{
																													{
																														double cv$temp$50$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$160_1];
																															cv$temp$50$var100 = var100;
																														}
																														double cv$temp$51$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$160_1];
																															cv$temp$51$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))));
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
																				for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																					for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161]; index$timeStep$162 += 1) {
																						if(!((index$timeStep$162 == index$timeStep$1) && (index$sample$161 == index$sample$2))) {
																							for(int index$sample86$163 = 0; index$sample86$163 < noStates; index$sample86$163 += 1) {
																								int distributionTempVariable$var82$165 = index$sample86$163;
																								double cv$probabilitySample86Value164 = (1.0 * distribution$sample86[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample86$163]);
																								int traceTempVariable$currentState$166_1 = distributionTempVariable$var82$165;
																								if((index$sample$161 == index$sample$53_2)) {
																									if((index$timeStep$162 == timeStep$var91)) {
																										for(int var37 = 0; var37 < noStates; var37 += 1) {
																											if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																												if((var37 == traceTempVariable$currentState$166_1)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$53_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$166_1)) {
																																{
																																	{
																																		double cv$temp$52$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$166_1];
																																			cv$temp$52$var100 = var100;
																																		}
																																		double cv$temp$53$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$166_1];
																																			cv$temp$53$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample86Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$53_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value164);
																																	}
																																}
																															}
																														}
																													}
																												}
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
			int traceTempVariable$var79$11_1 = cv$currentValue;
			if((index$sample$2 == sample)) {
				if((index$timeStep$1 == (timeStep$var74 - 1))) {
					for(int var25 = 0; var25 < noStates; var25 += 1) {
						if((var25 == traceTempVariable$var79$11_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var80;
							{
								double[] var80 = m[traceTempVariable$var79$11_1];
								cv$temp$2$var80 = var80;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var79$22_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$26_1 = cv$currentValue;
									for(int index$sample$26_2 = 0; index$sample$26_2 < noSamples; index$sample$26_2 += 1) {
										if((sample == index$sample$26_2)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$26_2]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var55 = 0; var55 < noStates; var55 += 1) {
																if((var55 == traceTempVariable$currentState$26_1)) {
																	{
																		{
																			double cv$temp$6$var94;
																			{
																				double var94 = metric_valid_bias[traceTempVariable$currentState$26_1];
																				cv$temp$6$var94 = var94;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$26_2][timeStep$var91], cv$temp$6$var94)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$26_2][timeStep$var91], cv$temp$6$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$26_2][timeStep$var91], cv$temp$6$var94));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$26_2][timeStep$var91], cv$temp$6$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$26_2][timeStep$var91], cv$temp$6$var94)));
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
									boolean[][] guard$sample86gaussian108 = guard$sample86gaussian108$global[threadID$cv$sample];
									for(int index$sample$42_1 = 0; index$sample$42_1 < noSamples; index$sample$42_1 += 1) {
										if((sample == index$sample$42_1)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$42_1]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91))
													guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
											}
										}
									}
									for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
										if((sample == index$sample$46_1)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$46_1]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91))
													guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
											}
										}
									}
									int traceTempVariable$currentState$50_1 = cv$currentValue;
									for(int index$sample$50_2 = 0; index$sample$50_2 < noSamples; index$sample$50_2 += 1) {
										if((sample == index$sample$50_2)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$50_2]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91)) {
													if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
														guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var37 = 0; var37 < noStates; var37 += 1) {
																	if(metric_valid_g[index$sample$50_2][timeStep$var91]) {
																		if((var37 == traceTempVariable$currentState$50_1)) {
																			if(fixedFlag$sample73) {
																				for(int index$sample$97_1 = 0; index$sample$97_1 < noSamples; index$sample$97_1 += 1) {
																					if((index$sample$97_1 == index$sample$50_2)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$50_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$50_1)) {
																										{
																											{
																												double cv$temp$22$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$50_1];
																													cv$temp$22$var100 = var100;
																												}
																												double cv$temp$23$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$50_1];
																													cv$temp$23$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))));
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
																			} else {
																				for(int index$sample$98 = 0; index$sample$98 < noSamples; index$sample$98 += 1) {
																					if(true) {
																						for(int index$sample73$99 = 0; index$sample73$99 < noStates; index$sample73$99 += 1) {
																							int distributionTempVariable$var69$101 = index$sample73$99;
																							double cv$probabilitySample73Value100 = (1.0 * distribution$sample73[((index$sample$98 - 0) / 1)][index$sample73$99]);
																							int traceTempVariable$currentState$102_1 = distributionTempVariable$var69$101;
																							if((index$sample$98 == index$sample$50_2)) {
																								if((0 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$50_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$102_1)) {
																												{
																													{
																														double cv$temp$24$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$102_1];
																															cv$temp$24$var100 = var100;
																														}
																														double cv$temp$25$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$102_1];
																															cv$temp$25$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample73Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value100) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value100);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var37 = 0; var37 < noStates; var37 += 1) {
																	if(metric_valid_g[index$sample$50_2][timeStep$var91]) {
																		if((var37 == traceTempVariable$currentState$50_1)) {
																			int traceTempVariable$currentState$106_1 = cv$currentValue;
																			if((index$sample$2 == index$sample$50_2)) {
																				if((index$timeStep$1 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$50_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$106_1)) {
																								{
																									{
																										double cv$temp$26$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$106_1];
																											cv$temp$26$var100 = var100;
																										}
																										double cv$temp$27$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$106_1];
																											cv$temp$27$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$107 = 0; index$sample$107 < noSamples; index$sample$107 += 1) {
																				for(int index$timeStep$108 = 1; index$timeStep$108 < length$metric[index$sample$107]; index$timeStep$108 += 1) {
																					if(!((index$timeStep$108 == index$timeStep$1) && (index$sample$107 == index$sample$2))) {
																						for(int index$sample86$109 = 0; index$sample86$109 < noStates; index$sample86$109 += 1) {
																							int distributionTempVariable$var82$111 = index$sample86$109;
																							double cv$probabilitySample86Value110 = (1.0 * distribution$sample86[((index$sample$107 - 0) / 1)][((index$timeStep$108 - 1) / 1)][index$sample86$109]);
																							int traceTempVariable$currentState$112_1 = distributionTempVariable$var82$111;
																							if((index$sample$107 == index$sample$50_2)) {
																								if((index$timeStep$108 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$50_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$112_1)) {
																												{
																													{
																														double cv$temp$28$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$112_1];
																															cv$temp$28$var100 = var100;
																														}
																														double cv$temp$29$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$112_1];
																															cv$temp$29$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample86Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value110) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$50_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value110);
																													}
																												}
																											}
																										}
																									}
																								}
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
									int traceTempVariable$currentState$54_1 = cv$currentValue;
									for(int index$sample$54_2 = 0; index$sample$54_2 < noSamples; index$sample$54_2 += 1) {
										if((sample == index$sample$54_2)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$54_2]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91)) {
													if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
														guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample73) {
																	for(int index$sample$171_1 = 0; index$sample$171_1 < noSamples; index$sample$171_1 += 1) {
																		if((index$sample$171_1 == index$sample$54_2)) {
																			if((0 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$54_1)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$54_1)) {
																										{
																											{
																												double cv$temp$54$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$54_1];
																													cv$temp$54$var100 = var100;
																												}
																												double cv$temp$55$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$54_1];
																													cv$temp$55$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))));
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
																	for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																		if(true) {
																			for(int index$sample73$173 = 0; index$sample73$173 < noStates; index$sample73$173 += 1) {
																				int distributionTempVariable$var69$175 = index$sample73$173;
																				double cv$probabilitySample73Value174 = (1.0 * distribution$sample73[((index$sample$172 - 0) / 1)][index$sample73$173]);
																				int traceTempVariable$currentState$176_1 = distributionTempVariable$var69$175;
																				if((index$sample$172 == index$sample$54_2)) {
																					if((0 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$176_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$176_1)) {
																												{
																													{
																														double cv$temp$56$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$176_1];
																															cv$temp$56$var100 = var100;
																														}
																														double cv$temp$57$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$176_1];
																															cv$temp$57$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample73Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value174);
																													}
																												}
																											}
																										}
																									}
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
																if((index$sample$2 == index$sample$54_2)) {
																	if((index$timeStep$1 == timeStep$var91)) {
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$181_1)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$181_1)) {
																								{
																									{
																										double cv$temp$58$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$181_1];
																											cv$temp$58$var100 = var100;
																										}
																										double cv$temp$59$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$181_1];
																											cv$temp$59$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))));
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
																for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																	for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182]; index$timeStep$183 += 1) {
																		if(!((index$timeStep$183 == index$timeStep$1) && (index$sample$182 == index$sample$2))) {
																			for(int index$sample86$184 = 0; index$sample86$184 < noStates; index$sample86$184 += 1) {
																				int distributionTempVariable$var82$186 = index$sample86$184;
																				double cv$probabilitySample86Value185 = (1.0 * distribution$sample86[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample86$184]);
																				int traceTempVariable$currentState$187_1 = distributionTempVariable$var82$186;
																				if((index$sample$182 == index$sample$54_2)) {
																					if((index$timeStep$183 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$187_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$54_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$187_1)) {
																												{
																													{
																														double cv$temp$60$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$187_1];
																															cv$temp$60$var100 = var100;
																														}
																														double cv$temp$61$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$187_1];
																															cv$temp$61$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample86Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$54_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value185);
																													}
																												}
																											}
																										}
																									}
																								}
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
			for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
				for(int index$timeStep$13 = 1; index$timeStep$13 < length$metric[index$sample$12]; index$timeStep$13 += 1) {
					if(!((index$timeStep$13 == index$timeStep$1) && (index$sample$12 == index$sample$2))) {
						for(int index$sample86$14 = 0; index$sample86$14 < noStates; index$sample86$14 += 1) {
							int distributionTempVariable$var82$16 = index$sample86$14;
							double cv$probabilitySample86Value15 = (1.0 * distribution$sample86[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample86$14]);
							int traceTempVariable$var79$17_1 = distributionTempVariable$var82$16;
							if((index$sample$12 == sample)) {
								if((index$timeStep$13 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == traceTempVariable$var79$17_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample86Value15);
											double[] cv$temp$3$var80;
											{
												double[] var80 = m[traceTempVariable$var79$17_1];
												cv$temp$3$var80 = var80;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample86Value15) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var80.length))?Math.log(cv$temp$3$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var79$23_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$27_1 = cv$currentValue;
													for(int index$sample$27_2 = 0; index$sample$27_2 < noSamples; index$sample$27_2 += 1) {
														if((sample == index$sample$27_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$27_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var55 = 0; var55 < noStates; var55 += 1) {
																				if((var55 == traceTempVariable$currentState$27_1)) {
																					{
																						{
																							double cv$temp$7$var94;
																							{
																								double var94 = metric_valid_bias[traceTempVariable$currentState$27_1];
																								cv$temp$7$var94 = var94;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$27_2][timeStep$var91], cv$temp$7$var94)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$27_2][timeStep$var91], cv$temp$7$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$27_2][timeStep$var91], cv$temp$7$var94));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$27_2][timeStep$var91], cv$temp$7$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$27_2][timeStep$var91], cv$temp$7$var94)));
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
													boolean[][] guard$sample86gaussian108 = guard$sample86gaussian108$global[threadID$cv$sample];
													for(int index$sample$43_1 = 0; index$sample$43_1 < noSamples; index$sample$43_1 += 1) {
														if((sample == index$sample$43_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$43_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
														if((sample == index$sample$47_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$47_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													int traceTempVariable$currentState$51_1 = cv$currentValue;
													for(int index$sample$51_2 = 0; index$sample$51_2 < noSamples; index$sample$51_2 += 1) {
														if((sample == index$sample$51_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$51_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$51_1)) {
																							if(fixedFlag$sample73) {
																								for(int index$sample$116_1 = 0; index$sample$116_1 < noSamples; index$sample$116_1 += 1) {
																									if((index$sample$116_1 == index$sample$51_2)) {
																										if((0 == timeStep$var91)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$51_1)) {
																														{
																															{
																																double cv$temp$30$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$51_1];
																																	cv$temp$30$var100 = var100;
																																}
																																double cv$temp$31$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$51_1];
																																	cv$temp$31$var101 = var101;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))));
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
																							} else {
																								for(int index$sample$117 = 0; index$sample$117 < noSamples; index$sample$117 += 1) {
																									if(true) {
																										for(int index$sample73$118 = 0; index$sample73$118 < noStates; index$sample73$118 += 1) {
																											int distributionTempVariable$var69$120 = index$sample73$118;
																											double cv$probabilitySample73Value119 = (1.0 * distribution$sample73[((index$sample$117 - 0) / 1)][index$sample73$118]);
																											int traceTempVariable$currentState$121_1 = distributionTempVariable$var69$120;
																											if((index$sample$117 == index$sample$51_2)) {
																												if((0 == timeStep$var91)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$121_1)) {
																																{
																																	{
																																		double cv$temp$32$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$121_1];
																																			cv$temp$32$var100 = var100;
																																		}
																																		double cv$temp$33$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$121_1];
																																			cv$temp$33$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample73Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value119) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value119);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$51_1)) {
																							int traceTempVariable$currentState$125_1 = cv$currentValue;
																							if((index$sample$2 == index$sample$51_2)) {
																								if((index$timeStep$1 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$125_1)) {
																												{
																													{
																														double cv$temp$34$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$125_1];
																															cv$temp$34$var100 = var100;
																														}
																														double cv$temp$35$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$125_1];
																															cv$temp$35$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$126_1 = distributionTempVariable$var82$16;
																							if((index$sample$12 == index$sample$51_2)) {
																								if((index$timeStep$13 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$126_1)) {
																												{
																													{
																														double cv$temp$36$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$126_1];
																															cv$temp$36$var100 = var100;
																														}
																														double cv$temp$37$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$126_1];
																															cv$temp$37$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$127 = 0; index$sample$127 < noSamples; index$sample$127 += 1) {
																								for(int index$timeStep$128 = 1; index$timeStep$128 < length$metric[index$sample$127]; index$timeStep$128 += 1) {
																									if((!((index$timeStep$128 == index$timeStep$1) && (index$sample$127 == index$sample$2)) && !((index$timeStep$128 == index$timeStep$13) && (index$sample$127 == index$sample$12)))) {
																										for(int index$sample86$129 = 0; index$sample86$129 < noStates; index$sample86$129 += 1) {
																											int distributionTempVariable$var82$131 = index$sample86$129;
																											double cv$probabilitySample86Value130 = (1.0 * distribution$sample86[((index$sample$127 - 0) / 1)][((index$timeStep$128 - 1) / 1)][index$sample86$129]);
																											int traceTempVariable$currentState$132_1 = distributionTempVariable$var82$131;
																											if((index$sample$127 == index$sample$51_2)) {
																												if((index$timeStep$128 == timeStep$var91)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$51_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$132_1)) {
																																{
																																	{
																																		double cv$temp$38$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$132_1];
																																			cv$temp$38$var100 = var100;
																																		}
																																		double cv$temp$39$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$132_1];
																																			cv$temp$39$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample86Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value130) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$51_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value130);
																																	}
																																}
																															}
																														}
																													}
																												}
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
													int traceTempVariable$currentState$55_1 = cv$currentValue;
													for(int index$sample$55_2 = 0; index$sample$55_2 < noSamples; index$sample$55_2 += 1) {
														if((sample == index$sample$55_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$55_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample86gaussian108[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				if(fixedFlag$sample73) {
																					for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																						if((index$sample$192_1 == index$sample$55_2)) {
																							if((0 == timeStep$var91)) {
																								for(int var37 = 0; var37 < noStates; var37 += 1) {
																									if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																										if((var37 == traceTempVariable$currentState$55_1)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$55_1)) {
																														{
																															{
																																double cv$temp$62$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$55_1];
																																	cv$temp$62$var100 = var100;
																																}
																																double cv$temp$63$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$55_1];
																																	cv$temp$63$var101 = var101;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))));
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
																					for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																						if(true) {
																							for(int index$sample73$194 = 0; index$sample73$194 < noStates; index$sample73$194 += 1) {
																								int distributionTempVariable$var69$196 = index$sample73$194;
																								double cv$probabilitySample73Value195 = (1.0 * distribution$sample73[((index$sample$193 - 0) / 1)][index$sample73$194]);
																								int traceTempVariable$currentState$197_1 = distributionTempVariable$var69$196;
																								if((index$sample$193 == index$sample$55_2)) {
																									if((0 == timeStep$var91)) {
																										for(int var37 = 0; var37 < noStates; var37 += 1) {
																											if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																												if((var37 == traceTempVariable$currentState$197_1)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$197_1)) {
																																{
																																	{
																																		double cv$temp$64$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$197_1];
																																			cv$temp$64$var100 = var100;
																																		}
																																		double cv$temp$65$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$197_1];
																																			cv$temp$65$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample73Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample73Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample73Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample73Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))))) + 1)) + (Math.log(cv$probabilitySample73Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample73Value195);
																																	}
																																}
																															}
																														}
																													}
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
																				if((index$sample$2 == index$sample$55_2)) {
																					if((index$timeStep$1 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$202_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$202_1)) {
																												{
																													{
																														double cv$temp$66$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$202_1];
																															cv$temp$66$var100 = var100;
																														}
																														double cv$temp$67$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$202_1];
																															cv$temp$67$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))));
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
																				int traceTempVariable$currentState$203_1 = distributionTempVariable$var82$16;
																				if((index$sample$12 == index$sample$55_2)) {
																					if((index$timeStep$13 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$203_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$203_1)) {
																												{
																													{
																														double cv$temp$68$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$203_1];
																															cv$temp$68$var100 = var100;
																														}
																														double cv$temp$69$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$203_1];
																															cv$temp$69$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))));
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
																				for(int index$sample$204 = 0; index$sample$204 < noSamples; index$sample$204 += 1) {
																					for(int index$timeStep$205 = 1; index$timeStep$205 < length$metric[index$sample$204]; index$timeStep$205 += 1) {
																						if((!((index$timeStep$205 == index$timeStep$1) && (index$sample$204 == index$sample$2)) && !((index$timeStep$205 == index$timeStep$13) && (index$sample$204 == index$sample$12)))) {
																							for(int index$sample86$206 = 0; index$sample86$206 < noStates; index$sample86$206 += 1) {
																								int distributionTempVariable$var82$208 = index$sample86$206;
																								double cv$probabilitySample86Value207 = (1.0 * distribution$sample86[((index$sample$204 - 0) / 1)][((index$timeStep$205 - 1) / 1)][index$sample86$206]);
																								int traceTempVariable$currentState$209_1 = distributionTempVariable$var82$208;
																								if((index$sample$204 == index$sample$55_2)) {
																									if((index$timeStep$205 == timeStep$var91)) {
																										for(int var37 = 0; var37 < noStates; var37 += 1) {
																											if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																												if((var37 == traceTempVariable$currentState$209_1)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$55_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$209_1)) {
																																{
																																	{
																																		double cv$temp$70$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$209_1];
																																			cv$temp$70$var100 = var100;
																																		}
																																		double cv$temp$71$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$209_1];
																																			cv$temp$71$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample86Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample86Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample86Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample86Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))))) + 1)) + (Math.log(cv$probabilitySample86Value207) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$55_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample86Value207);
																																	}
																																}
																															}
																														}
																													}
																												}
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
					int traceTempVariable$var79$248_1 = cv$currentValue;
					for(int index$sample$248_2 = 0; index$sample$248_2 < noSamples; index$sample$248_2 += 1) {
						if((sample == index$sample$248_2)) {
							for(int index$timeStep$248_3 = 1; index$timeStep$248_3 < length$metric[index$sample$248_2]; index$timeStep$248_3 += 1) {
								if((timeStep$var74 == (index$timeStep$248_3 - 1))) {
									{
										int index$timeStep$250 = index$timeStep$248_3;
										int index$sample$251 = index$sample$248_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var81[threadID$cv$sample];
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var25 = 0; var25 < noStates; var25 += 1) {
											if((var25 == traceTempVariable$var79$248_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample73) {
														for(int index$sample$253_1 = 0; index$sample$253_1 < noSamples; index$sample$253_1 += 1) {
															if((index$sample$253_1 == sample)) {
																if((0 == (timeStep$var74 - 1))) {
																	for(int index$var25$259_1 = 0; index$var25$259_1 < noStates; index$var25$259_1 += 1) {
																		if((index$var25$259_1 == st[sample][(timeStep$var74 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$254 = 0; index$sample$254 < noSamples; index$sample$254 += 1) {
															if(true) {
																for(int index$sample73$255 = 0; index$sample73$255 < noStates; index$sample73$255 += 1) {
																	int distributionTempVariable$var69$257 = index$sample73$255;
																	double cv$probabilitySample73Value256 = (1.0 * distribution$sample73[((index$sample$254 - 0) / 1)][index$sample73$255]);
																	int traceTempVariable$var79$258_1 = distributionTempVariable$var69$257;
																	if((index$sample$254 == sample)) {
																		if((0 == (timeStep$var74 - 1))) {
																			for(int index$var25$260_1 = 0; index$var25$260_1 < noStates; index$var25$260_1 += 1) {
																				if((index$var25$260_1 == traceTempVariable$var79$258_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample73Value256);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var79$261_1 = cv$currentValue;
													if((index$sample$2 == sample)) {
														if((index$timeStep$1 == (timeStep$var74 - 1))) {
															for(int index$var25$268_1 = 0; index$var25$268_1 < noStates; index$var25$268_1 += 1) {
																if((index$var25$268_1 == traceTempVariable$var79$261_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$262 = 0; index$sample$262 < noSamples; index$sample$262 += 1) {
														for(int index$timeStep$263 = 1; index$timeStep$263 < length$metric[index$sample$262]; index$timeStep$263 += 1) {
															if((!((index$timeStep$263 == index$timeStep$1) && (index$sample$262 == index$sample$2)) && !((index$timeStep$263 == index$timeStep$250) && (index$sample$262 == index$sample$251)))) {
																for(int index$sample86$264 = 0; index$sample86$264 < noStates; index$sample86$264 += 1) {
																	int distributionTempVariable$var82$266 = index$sample86$264;
																	double cv$probabilitySample86Value265 = (1.0 * distribution$sample86[((index$sample$262 - 0) / 1)][((index$timeStep$263 - 1) / 1)][index$sample86$264]);
																	int traceTempVariable$var79$267_1 = distributionTempVariable$var82$266;
																	if((index$sample$262 == sample)) {
																		if((index$timeStep$263 == (timeStep$var74 - 1))) {
																			for(int index$var25$269_1 = 0; index$var25$269_1 < noStates; index$var25$269_1 += 1) {
																				if((index$var25$269_1 == traceTempVariable$var79$267_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample86Value265);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var80;
													{
														double[] var80 = m[traceTempVariable$var79$248_1];
														cv$temp$72$var80 = var80;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var80);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample86[((index$sample$248_2 - 0) / 1)][((index$timeStep$248_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
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
			cv$max = Math.max(cv$max, noStates);
			cv$var20$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var25 = 0; var25 < noStates; var25 += 1)
				cv$max = Math.max(cv$max, noStates);
			{
				int cv$threadCount = threadCount();
				cv$var26$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var26$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var27$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$distributionAccumulator$var81 = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var81[cv$index] = new double[cv$var27$max];
			}
		}
		{
			{
				int cv$threadCount = threadCount();
				cv$var69$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var69$stateProbabilityGlobal[cv$index] = new double[noStates];
			}
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var91 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample73gaussian108$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample73gaussian108$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var91];
			}
		}
		{
			int cv$var27$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var82$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var82$stateProbabilityGlobal[cv$index] = new double[cv$var27$max];
			}
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var91 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			{
				int cv$threadCount = threadCount();
				guard$sample86gaussian108$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample86gaussian108$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var91];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		if(!setFlag$initialStateDistribution) {
			{
				initialStateDistribution = new double[noStates];
			}
		}
		if(!setFlag$m) {
			{
				m = new double[noStates][];
				for(int var25 = 0; var25 < noStates; var25 += 1)
					m[var25] = new double[noStates];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					st[sample] = new int[length$metric[sample]];
			}
		}
		if(!setFlag$metric_g) {
			{
				metric_g = new double[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					metric_g[sample] = new double[length$metric[sample]];
			}
		}
		if(!setFlag$metric_valid_g) {
			{
				metric_valid_g = new boolean[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					metric_valid_g[sample] = new boolean[length$metric[sample]];
			}
		}
		if(!setFlag$metric_mean) {
			{
				metric_mean = new double[noStates];
			}
		}
		if(!setFlag$metric_var) {
			{
				metric_var = new double[noStates];
			}
		}
		if(!setFlag$metric_valid_bias) {
			{
				metric_valid_bias = new double[noStates];
			}
		}
		{
			distribution$sample86 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)][];
				distribution$sample86[((sample - 0) / 1)] = subarray$0;
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					subarray$0[((timeStep$var74 - 1) / 1)] = new double[noStates];
			}
		}
		{
			distribution$sample73 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample73[((sample - 0) / 1)] = new double[noStates];
		}
		{
			logProbability$var68 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample73 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var81 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var81[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample86 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample86[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var95 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var95[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample100 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample100[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var102 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var102[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample109 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample109[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1) {
						double[] var26 = m[var25];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, var26);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1) {
						if(!fixedFlag$sample41)
							metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample50)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!fixedFlag$sample59)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int[] var66 = st[sample];
						if(!fixedFlag$sample73)
							var66[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						int[] var75 = st[sample];
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							if(!fixedFlag$sample86)
								var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var74 - 1)]]);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var91, int forEnd$timeStep$var91, int threadID$timeStep$var91, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int timeStep$var91 = forStart$timeStep$var91; timeStep$var91 < forEnd$timeStep$var91; timeStep$var91 += 1) {
										if(!fixedFlag$sample100)
											metric_valid_g[sample][timeStep$var91] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var91]]);
										if(metric_valid_g[sample][timeStep$var91]) {
											if(!fixedFlag$sample109)
												metric_g[sample][timeStep$var91] = ((Math.sqrt(metric_var[st[sample][timeStep$var91]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var91]]);
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
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1) {
						double[] var26 = m[var25];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, var26);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1) {
						if(!fixedFlag$sample41)
							metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample50)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!fixedFlag$sample59)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						double[] cv$distribution$sample73 = distribution$sample73[((sample - 0) / 1)];
						for(int index$var68 = 0; index$var68 < noStates; index$var68 += 1) {
							double cv$value = (((0.0 <= index$var68) && (index$var68 < initialStateDistribution.length))?initialStateDistribution[index$var68]:0.0);
							if(!fixedFlag$sample73)
								cv$distribution$sample73[index$var68] = cv$value;
						}
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							double[] cv$distribution$sample86 = distribution$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
							for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1) {
								if(!fixedFlag$sample86)
									cv$distribution$sample86[index$var81] = 0.0;
							}
							if(fixedFlag$sample73) {
								for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
									if((index$sample$1_1 == sample)) {
										if((0 == (timeStep$var74 - 1))) {
											for(int var25 = 0; var25 < noStates; var25 += 1) {
												if((var25 == st[sample][(timeStep$var74 - 1)])) {
													{
														if(!fixedFlag$sample86) {
															double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
															for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
																cv$distribution$sample86[index$var81] = (cv$distribution$sample86[index$var81] + (1.0 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
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
										for(int index$sample73$3 = 0; index$sample73$3 < noStates; index$sample73$3 += 1) {
											int distributionTempVariable$var69$5 = index$sample73$3;
											double cv$probabilitySample73Value4 = (1.0 * distribution$sample73[((index$sample$2 - 0) / 1)][index$sample73$3]);
											int traceTempVariable$var79$6_1 = distributionTempVariable$var69$5;
											if((index$sample$2 == sample)) {
												if((0 == (timeStep$var74 - 1))) {
													for(int var25 = 0; var25 < noStates; var25 += 1) {
														if((var25 == traceTempVariable$var79$6_1)) {
															{
																if(!fixedFlag$sample86) {
																	double[] var80 = m[traceTempVariable$var79$6_1];
																	for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
																		cv$distribution$sample86[index$var81] = (cv$distribution$sample86[index$var81] + (cv$probabilitySample73Value4 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample86) {
								for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
									for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
										if((index$sample$9_1 == sample)) {
											if((index$timeStep$9_2 == (timeStep$var74 - 1))) {
												for(int var25 = 0; var25 < noStates; var25 += 1) {
													if((var25 == st[sample][(timeStep$var74 - 1)])) {
														{
															if(!fixedFlag$sample86) {
																double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
																for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
																	cv$distribution$sample86[index$var81] = (cv$distribution$sample86[index$var81] + (1.0 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
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
											for(int index$sample86$12 = 0; index$sample86$12 < noStates; index$sample86$12 += 1) {
												int distributionTempVariable$var82$14 = index$sample86$12;
												double cv$probabilitySample86Value13 = (1.0 * distribution$sample86[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample86$12]);
												int traceTempVariable$var79$15_1 = distributionTempVariable$var82$14;
												if((index$sample$10 == sample)) {
													if((index$timeStep$11 == (timeStep$var74 - 1))) {
														for(int var25 = 0; var25 < noStates; var25 += 1) {
															if((var25 == traceTempVariable$var79$15_1)) {
																{
																	if(!fixedFlag$sample86) {
																		double[] var80 = m[traceTempVariable$var79$15_1];
																		for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
																			cv$distribution$sample86[index$var81] = (cv$distribution$sample86[index$var81] + (cv$probabilitySample86Value13 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							double cv$var81$sum = 0.0;
							for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1) {
								if(!fixedFlag$sample86)
									cv$var81$sum = (cv$var81$sum + cv$distribution$sample86[index$var81]);
							}
							for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1) {
								if(!fixedFlag$sample86)
									cv$distribution$sample86[index$var81] = (cv$distribution$sample86[index$var81] / cv$var81$sum);
							}
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1) {
						double[] var26 = m[var25];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, var26);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1) {
						if(!fixedFlag$sample41)
							metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample50)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!fixedFlag$sample59)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						int[] var66 = st[sample];
						if(!fixedFlag$sample73)
							var66[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						int[] var75 = st[sample];
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							if(!fixedFlag$sample86)
								var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var74 - 1)]]);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample23)
				sample23();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1) {
							if(!fixedFlag$sample29)
								sample29(var25, threadID$var25, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1) {
							if(!fixedFlag$sample41)
								sample41(var37, threadID$var37, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!fixedFlag$sample50)
								sample50(var46, threadID$var46, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!fixedFlag$sample59)
								sample59(var55, threadID$var55, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!fixedFlag$sample73)
								sample73(sample, threadID$sample, RNG$1);
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
								if(!fixedFlag$sample86)
									sample86(sample, timeStep$var74, threadID$sample, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							for(int timeStep$var74 = (length$metric[sample] - ((((length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var74 >= ((1 - 1) + 1); timeStep$var74 -= 1) {
								if(!fixedFlag$sample86)
									sample86(sample, timeStep$var74, threadID$sample, RNG$1);
							}
							if(!fixedFlag$sample73)
								sample73(sample, threadID$sample, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
							if(!fixedFlag$sample59)
								sample59(var55, threadID$var55, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
							if(!fixedFlag$sample50)
								sample50(var46, threadID$var46, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1) {
							if(!fixedFlag$sample41)
								sample41(var37, threadID$var37, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1) {
							if(!fixedFlag$sample29)
								sample29(var25, threadID$var25, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample23)
				sample23();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var16, int forEnd$var16, int threadID$var16, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var16 = forStart$var16; var16 < forEnd$var16; var16 += 1)
						v[var16] = 0.1;
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample23)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var21 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var26 = 0.0;
		logProbability$var33 = 0.0;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample41)
			logProbability$var38 = 0.0;
		logProbability$var42 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample50)
			logProbability$var47 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample59)
			logProbability$var56 = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1)
			logProbability$var68[((sample - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample73) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample73[((sample - 0) / 1)] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
				logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample86) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					logProbability$sample86[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var95[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample100) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample100[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var102[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_1d = 0.0;
		if(!fixedProbFlag$sample109) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample109[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample23)
			logProbabilityValue$sample23();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		if(fixedFlag$sample41)
			logProbabilityValue$sample41();
		if(fixedFlag$sample50)
			logProbabilityValue$sample50();
		if(fixedFlag$sample59)
			logProbabilityValue$sample59();
		logProbabilityValue$sample100();
		logProbabilityValue$sample109();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample23();
		logProbabilityValue$sample29();
		logProbabilityValue$sample41();
		logProbabilityValue$sample50();
		logProbabilityValue$sample59();
		logProbabilityDistribution$sample73();
		logProbabilityDistribution$sample86();
		logProbabilityDistribution$sample100();
		logProbabilityDistribution$sample109();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample23();
		logProbabilityValue$sample29();
		logProbabilityValue$sample41();
		logProbabilityValue$sample50();
		logProbabilityValue$sample59();
		logProbabilityValue$sample73();
		logProbabilityValue$sample86();
		logProbabilityValue$sample100();
		logProbabilityValue$sample109();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var25, int forEnd$var25, int threadID$var25, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var25 = forStart$var25; var25 < forEnd$var25; var25 += 1) {
						double[] var26 = m[var25];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, var26);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var37, int forEnd$var37, int threadID$var37, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var37 = forStart$var37; var37 < forEnd$var37; var37 += 1) {
						if(!fixedFlag$sample41)
							metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$1)));
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample50)
							metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!fixedFlag$sample59)
							metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						int[] var66 = st[sample];
						if(!fixedFlag$sample73)
							var66[0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						int[] var75 = st[sample];
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							if(!fixedFlag$sample86)
								var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var74 - 1)]]);
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
			for(int timeStep$var91 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var91 >= ((0 - 1) + 1); timeStep$var91 -= 1)
				metric_g[sample][timeStep$var91] = metric[sample][timeStep$var91];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics2(\n               double[][] metric,\n               boolean[][] metric_valid, \n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n\n    //Allocate space for states\n    int[][] st = new int[noSamples][];\n\n    //Allocate space for generated metrics \n    double[][] metric_g = new double[noSamples][];\n    boolean[][] metric_valid_g = new boolean[noSamples][];\n    \n    //Calculate priors for the metric\n    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n    \n    // Compute the values of each metric value\n    for(int sample = 0; sample < noSamples; sample++) {\n        //Calculate all the state transitions\n        int streamLength = metric[sample].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n        \n        //Calculate metric values\n        double[] metric_1d = new double[streamLength];\n        metric_g[sample] = metric_1d;\n\n        boolean[] metric_valid_1d = new boolean[streamLength];\n        metric_valid_g[sample] = metric_valid_1d;\n\n        //Generate values.\n        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n            int currentState = st[sample][timeStep];\n            \n            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n            if(metric_valid_1d[timeStep])\n                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n            metric_1d[timeStep].observe(metric[sample][timeStep]);\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n}";
	}
}