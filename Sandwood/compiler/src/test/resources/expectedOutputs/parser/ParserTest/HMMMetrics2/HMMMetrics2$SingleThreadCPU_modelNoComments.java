package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics2$CoreInterface {
	private double[] cv$distributionAccumulator$var81;
	private double[] cv$var20$countGlobal;
	private double[] cv$var26$countGlobal;
	private double[] cv$var69$stateProbabilityGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[][] distribution$sample75;
	private double[][][] distribution$sample88;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample113 = false;
	private boolean fixedFlag$sample23 = false;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample60 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample113 = false;
	private boolean fixedProbFlag$sample23 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample60 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample88 = false;
	private boolean[][] guard$sample75gaussian112$global;
	private boolean[][] guard$sample88gaussian112$global;
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
	private double[][] logProbability$sample104;
	private double[][] logProbability$sample113;
	private double[] logProbability$sample75;
	private double[][] logProbability$sample88;
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

	public HMMMetrics2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		fixedFlag$sample104 = cv$value;
		fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	@Override
	public final void set$fixedFlag$sample113(boolean cv$value) {
		fixedFlag$sample113 = cv$value;
		fixedProbFlag$sample113 = (fixedFlag$sample113 && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (fixedFlag$sample23 && fixedProbFlag$sample23);
		fixedProbFlag$sample75 = (fixedFlag$sample23 && fixedProbFlag$sample75);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		fixedProbFlag$sample88 = (fixedFlag$sample29 && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		fixedProbFlag$sample113 = (fixedFlag$sample42 && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (fixedFlag$sample51 && fixedProbFlag$sample51);
		fixedProbFlag$sample113 = (fixedFlag$sample51 && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample60() {
		return fixedFlag$sample60;
	}

	@Override
	public final void set$fixedFlag$sample60(boolean cv$value) {
		fixedFlag$sample60 = cv$value;
		fixedProbFlag$sample60 = (fixedFlag$sample60 && fixedProbFlag$sample60);
		fixedProbFlag$sample104 = (fixedFlag$sample60 && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		fixedFlag$sample75 = cv$value;
		fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedProbFlag$sample75);
		fixedProbFlag$sample88 = (fixedFlag$sample75 && fixedProbFlag$sample88);
		fixedProbFlag$sample104 = (fixedFlag$sample75 && fixedProbFlag$sample104);
		fixedProbFlag$sample113 = (fixedFlag$sample75 && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		fixedProbFlag$sample104 = (fixedFlag$sample88 && fixedProbFlag$sample104);
		fixedProbFlag$sample113 = (fixedFlag$sample88 && fixedProbFlag$sample113);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample23 = false;
		fixedProbFlag$sample75 = false;
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
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample88 = false;
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
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample113 = false;
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
		fixedProbFlag$sample60 = false;
		fixedProbFlag$sample104 = false;
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
		fixedProbFlag$sample51 = false;
		fixedProbFlag$sample113 = false;
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
		fixedProbFlag$sample75 = false;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample104 = false;
		fixedProbFlag$sample113 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var91];
						if(fixedFlag$sample75) {
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
									for(int index$sample75$4 = 0; index$sample75$4 < noStates; index$sample75$4 += 1) {
										int distributionTempVariable$var69$6 = index$sample75$4;
										double cv$probabilitySample75Value5 = (1.0 * distribution$sample75[((index$sample$3 - 0) / 1)][index$sample75$4]);
										int traceTempVariable$currentState$7_1 = distributionTempVariable$var69$6;
										if((index$sample$3 == sample)) {
											if((0 == timeStep$var91)) {
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == traceTempVariable$currentState$7_1)) {
														{
															double var94 = metric_valid_bias[traceTempVariable$currentState$7_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample75Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value5);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample88) {
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
										for(int index$sample88$13 = 0; index$sample88$13 < noStates; index$sample88$13 += 1) {
											int distributionTempVariable$var82$15 = index$sample88$13;
											double cv$probabilitySample88Value14 = (1.0 * distribution$sample88[((index$sample$11 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$13]);
											int traceTempVariable$currentState$16_1 = distributionTempVariable$var82$15;
											if((index$sample$11 == sample)) {
												if((timeStep$var74 == timeStep$var91)) {
													for(int var55 = 0; var55 < noStates; var55 += 1) {
														if((var55 == traceTempVariable$currentState$16_1)) {
															{
																double var94 = metric_valid_bias[traceTempVariable$currentState$16_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample88Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var94));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value14);
															}
														}
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
					logProbability$sample104[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
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
			fixedProbFlag$sample104 = (((fixedFlag$sample104 && fixedFlag$sample60) && fixedFlag$sample75) && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample104[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
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
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample113() {
		if(!fixedProbFlag$sample113) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							double cv$sampleValue = metric_g[sample][timeStep$var91];
							if(fixedFlag$sample75) {
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
										for(int index$sample75$4 = 0; index$sample75$4 < noStates; index$sample75$4 += 1) {
											int distributionTempVariable$var69$6 = index$sample75$4;
											double cv$probabilitySample75Value5 = (1.0 * distribution$sample75[((index$sample$3 - 0) / 1)][index$sample75$4]);
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
																						double cv$weightedProbability = (Math.log(cv$probabilitySample75Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																						if((cv$weightedProbability < cv$distributionAccumulator))
																							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																						else {
																							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																								cv$distributionAccumulator = cv$weightedProbability;
																							else
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																						}
																						cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value5);
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																	if(!(index$sample$12 == index$sample$3)) {
																		for(int index$sample75$13 = 0; index$sample75$13 < noStates; index$sample75$13 += 1) {
																			int distributionTempVariable$var69$15 = index$sample75$13;
																			double cv$probabilitySample75Value14 = (cv$probabilitySample75Value5 * distribution$sample75[((index$sample$12 - 0) / 1)][index$sample75$13]);
																			int traceTempVariable$currentState$16_1 = distributionTempVariable$var69$15;
																			if((index$sample$12 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$16_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$16_1];
																									double var101 = metric_var[traceTempVariable$currentState$16_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample75Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value14);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample75) {
								for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
									if((index$sample$20_1 == sample)) {
										if((0 == timeStep$var91)) {
											for(int var37 = 0; var37 < noStates; var37 += 1) {
												if(metric_valid_g[sample][timeStep$var91]) {
													if((var37 == st[sample][timeStep$var91])) {
														if(fixedFlag$sample88) {
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
																		for(int index$sample88$31 = 0; index$sample88$31 < noStates; index$sample88$31 += 1) {
																			int distributionTempVariable$var82$33 = index$sample88$31;
																			double cv$probabilitySample88Value32 = (1.0 * distribution$sample88[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$31]);
																			int traceTempVariable$currentState$34_1 = distributionTempVariable$var82$33;
																			if((index$sample$29 == sample)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$34_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$34_1];
																									double var101 = metric_var[traceTempVariable$currentState$34_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value32);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
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
										for(int index$sample75$22 = 0; index$sample75$22 < noStates; index$sample75$22 += 1) {
											int distributionTempVariable$var69$24 = index$sample75$22;
											double cv$probabilitySample75Value23 = (1.0 * distribution$sample75[((index$sample$21 - 0) / 1)][index$sample75$22]);
											int traceTempVariable$currentState$25_1 = distributionTempVariable$var69$24;
											if((index$sample$21 == sample)) {
												if((0 == timeStep$var91)) {
													for(int var37 = 0; var37 < noStates; var37 += 1) {
														if(metric_valid_g[sample][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$25_1)) {
																if(fixedFlag$sample88) {
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
																									double cv$weightedProbability = (Math.log(cv$probabilitySample75Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value23);
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
																				for(int index$sample88$38 = 0; index$sample88$38 < noStates; index$sample88$38 += 1) {
																					int distributionTempVariable$var82$40 = index$sample88$38;
																					double cv$probabilitySample88Value39 = (cv$probabilitySample75Value23 * distribution$sample88[((index$sample$36 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$38]);
																					int traceTempVariable$currentState$41_1 = distributionTempVariable$var82$40;
																					if((index$sample$36 == sample)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$41_1)) {
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$41_1];
																											double var101 = metric_var[traceTempVariable$currentState$41_1];
																											double cv$weightedProbability = (Math.log(cv$probabilitySample88Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value39);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample88) {
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
											for(int index$sample88$49 = 0; index$sample88$49 < noStates; index$sample88$49 += 1) {
												int distributionTempVariable$var82$51 = index$sample88$49;
												double cv$probabilitySample88Value50 = (1.0 * distribution$sample88[((index$sample$47 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$49]);
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
																							double cv$weightedProbability = (Math.log(cv$probabilitySample88Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																							if((cv$weightedProbability < cv$distributionAccumulator))
																								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																							else {
																								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																									cv$distributionAccumulator = cv$weightedProbability;
																								else
																									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																							}
																							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value50);
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																		for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57]; index$timeStep$58 += 1) {
																			if(!((index$timeStep$58 == timeStep$var74) && (index$sample$57 == index$sample$47))) {
																				for(int index$sample88$59 = 0; index$sample88$59 < noStates; index$sample88$59 += 1) {
																					int distributionTempVariable$var82$61 = index$sample88$59;
																					double cv$probabilitySample88Value60 = (cv$probabilitySample88Value50 * distribution$sample88[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample88$59]);
																					int traceTempVariable$currentState$62_1 = distributionTempVariable$var82$61;
																					if((index$sample$57 == sample)) {
																						if((index$timeStep$58 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$62_1)) {
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$62_1];
																											double var101 = metric_var[traceTempVariable$currentState$62_1];
																											double cv$weightedProbability = (Math.log(cv$probabilitySample88Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value60);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample88) {
								for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
									for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$66_1]; timeStep$var74 += 1) {
										if((index$sample$66_1 == sample)) {
											if((timeStep$var74 == timeStep$var91)) {
												for(int var37 = 0; var37 < noStates; var37 += 1) {
													if(metric_valid_g[sample][timeStep$var91]) {
														if((var37 == st[sample][timeStep$var91])) {
															if(fixedFlag$sample75) {
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
																		for(int index$sample75$77 = 0; index$sample75$77 < noStates; index$sample75$77 += 1) {
																			int distributionTempVariable$var69$79 = index$sample75$77;
																			double cv$probabilitySample75Value78 = (1.0 * distribution$sample75[((index$sample$76 - 0) / 1)][index$sample75$77]);
																			int traceTempVariable$currentState$80_1 = distributionTempVariable$var69$79;
																			if((index$sample$76 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$80_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$80_1];
																									double var101 = metric_var[traceTempVariable$currentState$80_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample75Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value78);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
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
											for(int index$sample88$69 = 0; index$sample88$69 < noStates; index$sample88$69 += 1) {
												int distributionTempVariable$var82$71 = index$sample88$69;
												double cv$probabilitySample88Value70 = (1.0 * distribution$sample88[((index$sample$67 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$69]);
												int traceTempVariable$currentState$72_1 = distributionTempVariable$var82$71;
												if((index$sample$67 == sample)) {
													if((timeStep$var74 == timeStep$var91)) {
														for(int var37 = 0; var37 < noStates; var37 += 1) {
															if(metric_valid_g[sample][timeStep$var91]) {
																if((var37 == traceTempVariable$currentState$72_1)) {
																	if(fixedFlag$sample75) {
																		for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																			if((index$sample$81_1 == sample)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[sample][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$72_1)) {
																								{
																									double var100 = metric_mean[traceTempVariable$currentState$72_1];
																									double var101 = metric_var[traceTempVariable$currentState$72_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample88Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																									if((cv$weightedProbability < cv$distributionAccumulator))
																										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																									else {
																										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																											cv$distributionAccumulator = cv$weightedProbability;
																										else
																											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																									}
																									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value70);
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
																				for(int index$sample75$83 = 0; index$sample75$83 < noStates; index$sample75$83 += 1) {
																					int distributionTempVariable$var69$85 = index$sample75$83;
																					double cv$probabilitySample75Value84 = (cv$probabilitySample88Value70 * distribution$sample75[((index$sample$82 - 0) / 1)][index$sample75$83]);
																					int traceTempVariable$currentState$86_1 = distributionTempVariable$var69$85;
																					if((index$sample$82 == sample)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[sample][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$86_1)) {
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$86_1];
																											double var101 = metric_var[traceTempVariable$currentState$86_1];
																											double cv$weightedProbability = (Math.log(cv$probabilitySample75Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var100) / Math.sqrt(var101))) - (0.5 * Math.log(var101))));
																											if((cv$weightedProbability < cv$distributionAccumulator))
																												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																											else {
																												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																													cv$distributionAccumulator = cv$weightedProbability;
																												else
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																											}
																											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value84);
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
						logProbability$sample113[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
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
			fixedProbFlag$sample113 = ((((fixedFlag$sample113 && fixedFlag$sample42) && fixedFlag$sample51) && fixedFlag$sample75) && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample113[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
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
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!fixedProbFlag$sample75) {
			if(fixedFlag$sample75) {
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
					logProbability$sample75[((sample - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample75)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample75)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample23);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[((sample - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample75)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample88() {
		if(!fixedProbFlag$sample88) {
			if(fixedFlag$sample88) {
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
							if(fixedFlag$sample75) {
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
										for(int index$sample75$6 = 0; index$sample75$6 < noStates; index$sample75$6 += 1) {
											int distributionTempVariable$var69$8 = index$sample75$6;
											double cv$probabilitySample75Value7 = (1.0 * distribution$sample75[((index$sample$5 - 0) / 1)][index$sample75$6]);
											int traceTempVariable$var79$9_1 = distributionTempVariable$var69$8;
											if((index$sample$5 == sample)) {
												if((0 == (timeStep$var74 - 1))) {
													for(int var25 = 0; var25 < noStates; var25 += 1) {
														if((var25 == traceTempVariable$var79$9_1)) {
															{
																double[] var80 = m[traceTempVariable$var79$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample75Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value7);
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
								if((index$timeStep$1 == (timeStep$var74 - 1))) {
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
							if(fixedFlag$sample88) {
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
											for(int index$sample88$16 = 0; index$sample88$16 < noStates; index$sample88$16 += 1) {
												int distributionTempVariable$var82$18 = index$sample88$16;
												double cv$probabilitySample88Value17 = (1.0 * distribution$sample88[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample88$16]);
												int traceTempVariable$var79$19_1 = distributionTempVariable$var82$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var74 - 1))) {
														for(int var25 = 0; var25 < noStates; var25 += 1) {
															if((var25 == traceTempVariable$var79$19_1)) {
																{
																	double[] var80 = m[traceTempVariable$var79$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample88Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample88Value17);
																}
															}
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
						logProbability$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample88)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample88)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample88 = ((fixedFlag$sample88 && fixedFlag$sample29) && fixedFlag$sample75);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample88)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!fixedProbFlag$sample104) {
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
					logProbability$sample104[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
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
			fixedProbFlag$sample104 = (((fixedFlag$sample104 && fixedFlag$sample60) && fixedFlag$sample75) && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample104[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
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
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample113() {
		if(!fixedProbFlag$sample113) {
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
						logProbability$sample113[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = cv$sampleProbability;
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
			fixedProbFlag$sample113 = ((((fixedFlag$sample113 && fixedFlag$sample42) && fixedFlag$sample51) && fixedFlag$sample75) && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample113[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)];
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

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
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
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var38;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var33 = cv$rvAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
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
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!fixedProbFlag$sample60) {
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
			if(fixedFlag$sample60)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample60 = fixedFlag$sample60;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample60)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
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
				logProbability$sample75[((sample - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[((sample - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
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
					logProbability$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = ((fixedFlag$sample88 && fixedFlag$sample29) && fixedFlag$sample75);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
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
						if(fixedFlag$sample75) {
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
					if(!fixedFlag$sample75) {
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
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample29(int var25) {
		double[] cv$targetLocal = m[var25];
		double[] cv$countLocal = cv$var26$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
							if(fixedFlag$sample75) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == (timeStep$var74 - 1))) {
											if((var25 == st[sample][(timeStep$var74 - 1)])) {
												if(fixedFlag$sample88) {
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
										for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
											int distributionTempVariable$var69$7 = index$sample75$5;
											double cv$probabilitySample75Value6 = (1.0 * distribution$sample75[((index$sample$4 - 0) / 1)][index$sample75$5]);
											int traceTempVariable$var79$8_1 = distributionTempVariable$var69$7;
											if((index$sample$4 == sample)) {
												if((0 == (timeStep$var74 - 1))) {
													if((var25 == traceTempVariable$var79$8_1)) {
														if(fixedFlag$sample88) {
															{
																int index$timeStep$26 = timeStep$var74;
																int index$sample$27 = sample;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + cv$probabilitySample75Value6);
																			}
																		}
																	}
																}
															}
														}
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
							if(fixedFlag$sample88) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var74 - 1))) {
												if((var25 == st[sample][(timeStep$var74 - 1)])) {
													if(fixedFlag$sample88) {
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
											for(int index$sample88$16 = 0; index$sample88$16 < noStates; index$sample88$16 += 1) {
												int distributionTempVariable$var82$18 = index$sample88$16;
												double cv$probabilitySample88Value17 = (1.0 * distribution$sample88[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample88$16]);
												int traceTempVariable$var79$19_1 = distributionTempVariable$var82$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var74 - 1))) {
														if((var25 == traceTempVariable$var79$19_1)) {
															if(fixedFlag$sample88) {
																{
																	int index$timeStep$32 = timeStep$var74;
																	int index$sample$33 = sample;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample][timeStep$var74]] = (cv$countLocal[st[sample][timeStep$var74]] + cv$probabilitySample88Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
						if(fixedFlag$sample75) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample)) {
									if((0 == (timeStep$var74 - 1))) {
										if((var25 == st[sample][(timeStep$var74 - 1)])) {
											if(!fixedFlag$sample88) {
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
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample75$42 = 0; index$sample75$42 < noStates; index$sample75$42 += 1) {
										int distributionTempVariable$var69$44 = index$sample75$42;
										double cv$probabilitySample75Value43 = (1.0 * distribution$sample75[((index$sample$41 - 0) / 1)][index$sample75$42]);
										int traceTempVariable$var79$45_1 = distributionTempVariable$var69$44;
										if((index$sample$41 == sample)) {
											if((0 == (timeStep$var74 - 1))) {
												if((var25 == traceTempVariable$var79$45_1)) {
													if(!fixedFlag$sample88) {
														{
															int index$timeStep$63 = timeStep$var74;
															int index$sample$64 = sample;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample75Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
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
						if(fixedFlag$sample88) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample)) {
										if((index$timeStep$50_2 == (timeStep$var74 - 1))) {
											if((var25 == st[sample][(timeStep$var74 - 1)])) {
												if(!fixedFlag$sample88) {
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
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										for(int index$sample88$53 = 0; index$sample88$53 < noStates; index$sample88$53 += 1) {
											int distributionTempVariable$var82$55 = index$sample88$53;
											double cv$probabilitySample88Value54 = (1.0 * distribution$sample88[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample88$53]);
											int traceTempVariable$var79$56_1 = distributionTempVariable$var82$55;
											if((index$sample$51 == sample)) {
												if((index$timeStep$52 == (timeStep$var74 - 1))) {
													if((var25 == traceTempVariable$var79$56_1)) {
														if(!fixedFlag$sample88) {
															{
																int index$timeStep$69 = timeStep$var74;
																int index$sample$70 = sample;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample88Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample42(int var37) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = metric_mean[var37];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
								if(fixedFlag$sample75) {
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
																if(fixedFlag$sample88) {
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
																				for(int index$sample88$31 = 0; index$sample88$31 < noStates; index$sample88$31 += 1) {
																					int distributionTempVariable$var82$33 = index$sample88$31;
																					double cv$probabilitySample88Value32 = (1.0 * distribution$sample88[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$31]);
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
																												if(((Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value32);
																											}
																										}
																									}
																								}
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
											for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
												int distributionTempVariable$var69$7 = index$sample75$5;
												double cv$probabilitySample75Value6 = (1.0 * distribution$sample75[((index$sample$4 - 0) / 1)][index$sample75$5]);
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
																									if(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value6);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																			if(!(index$sample$38 == index$sample$4)) {
																				for(int index$sample75$39 = 0; index$sample75$39 < noStates; index$sample75$39 += 1) {
																					int distributionTempVariable$var69$41 = index$sample75$39;
																					double cv$probabilitySample75Value40 = (cv$probabilitySample75Value6 * distribution$sample75[((index$sample$38 - 0) / 1)][index$sample75$39]);
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
																												if(((Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value40);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample88) {
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
																												if(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value6);
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
																						for(int index$sample88$48 = 0; index$sample88$48 < noStates; index$sample88$48 += 1) {
																							int distributionTempVariable$var82$50 = index$sample88$48;
																							double cv$probabilitySample88Value49 = (cv$probabilitySample75Value6 * distribution$sample88[((index$sample$46 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$48]);
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
																														if(((Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value49);
																													}
																												}
																											}
																										}
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
								if(fixedFlag$sample88) {
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
																	if(fixedFlag$sample75) {
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
																				for(int index$sample75$56 = 0; index$sample75$56 < noStates; index$sample75$56 += 1) {
																					int distributionTempVariable$var69$58 = index$sample75$56;
																					double cv$probabilitySample75Value57 = (1.0 * distribution$sample75[((index$sample$55 - 0) / 1)][index$sample75$56]);
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
																												if(((Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value57);
																											}
																										}
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
												for(int index$sample88$16 = 0; index$sample88$16 < noStates; index$sample88$16 += 1) {
													int distributionTempVariable$var82$18 = index$sample88$16;
													double cv$probabilitySample88Value17 = (1.0 * distribution$sample88[((index$sample$14 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$16]);
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
																			if(fixedFlag$sample75) {
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
																												if(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value17);
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
																						for(int index$sample75$66 = 0; index$sample75$66 < noStates; index$sample75$66 += 1) {
																							int distributionTempVariable$var69$68 = index$sample75$66;
																							double cv$probabilitySample75Value67 = (cv$probabilitySample88Value17 * distribution$sample75[((index$sample$65 - 0) / 1)][index$sample75$66]);
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
																														if(((Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value67);
																													}
																												}
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
																										if(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value17);
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
																						for(int index$sample88$75 = 0; index$sample88$75 < noStates; index$sample88$75 += 1) {
																							int distributionTempVariable$var82$77 = index$sample88$75;
																							double cv$probabilitySample88Value76 = (cv$probabilitySample88Value17 * distribution$sample88[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample88$75]);
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
																														if(((Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value76);
																													}
																												}
																											}
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

	private final void sample51(int var46) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = metric_var[var46];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
								if(fixedFlag$sample75) {
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
																if(fixedFlag$sample88) {
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
																				for(int index$sample88$31 = 0; index$sample88$31 < noStates; index$sample88$31 += 1) {
																					int distributionTempVariable$var82$33 = index$sample88$31;
																					double cv$probabilitySample88Value32 = (1.0 * distribution$sample88[((index$sample$29 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$31]);
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
																												if(((Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$6$var100) / Math.sqrt(cv$temp$7$var101))) - (0.5 * Math.log(cv$temp$7$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value32);
																											}
																										}
																									}
																								}
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
											for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
												int distributionTempVariable$var69$7 = index$sample75$5;
												double cv$probabilitySample75Value6 = (1.0 * distribution$sample75[((index$sample$4 - 0) / 1)][index$sample75$5]);
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
																									if(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))));
																									}
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value6);
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																			if(!(index$sample$38 == index$sample$4)) {
																				for(int index$sample75$39 = 0; index$sample75$39 < noStates; index$sample75$39 += 1) {
																					int distributionTempVariable$var69$41 = index$sample75$39;
																					double cv$probabilitySample75Value40 = (cv$probabilitySample75Value6 * distribution$sample75[((index$sample$38 - 0) / 1)][index$sample75$39]);
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
																												if(((Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value40);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		if(fixedFlag$sample88) {
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
																												if(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value6);
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
																						for(int index$sample88$48 = 0; index$sample88$48 < noStates; index$sample88$48 += 1) {
																							int distributionTempVariable$var82$50 = index$sample88$48;
																							double cv$probabilitySample88Value49 = (cv$probabilitySample75Value6 * distribution$sample88[((index$sample$46 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$48]);
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
																														if(((Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value49);
																													}
																												}
																											}
																										}
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
								if(fixedFlag$sample88) {
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
																	if(fixedFlag$sample75) {
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
																				for(int index$sample75$56 = 0; index$sample75$56 < noStates; index$sample75$56 += 1) {
																					int distributionTempVariable$var69$58 = index$sample75$56;
																					double cv$probabilitySample75Value57 = (1.0 * distribution$sample75[((index$sample$55 - 0) / 1)][index$sample75$56]);
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
																												if(((Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value57);
																											}
																										}
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
												for(int index$sample88$16 = 0; index$sample88$16 < noStates; index$sample88$16 += 1) {
													int distributionTempVariable$var82$18 = index$sample88$16;
													double cv$probabilitySample88Value17 = (1.0 * distribution$sample88[((index$sample$14 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$16]);
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
																			if(fixedFlag$sample75) {
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
																												if(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value17);
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
																						for(int index$sample75$66 = 0; index$sample75$66 < noStates; index$sample75$66 += 1) {
																							int distributionTempVariable$var69$68 = index$sample75$66;
																							double cv$probabilitySample75Value67 = (cv$probabilitySample88Value17 * distribution$sample75[((index$sample$65 - 0) / 1)][index$sample75$66]);
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
																														if(((Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value67);
																													}
																												}
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
																										if(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value17);
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
																						for(int index$sample88$75 = 0; index$sample88$75 < noStates; index$sample88$75 += 1) {
																							int distributionTempVariable$var82$77 = index$sample88$75;
																							double cv$probabilitySample88Value76 = (cv$probabilitySample88Value17 * distribution$sample88[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample88$75]);
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
																														if(((Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value76);
																													}
																												}
																											}
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

	private final void sample60(int var55) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
							if(fixedFlag$sample75) {
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
										for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
											int distributionTempVariable$var69$7 = index$sample75$5;
											double cv$probabilitySample75Value6 = (1.0 * distribution$sample75[((index$sample$4 - 0) / 1)][index$sample75$5]);
											int traceTempVariable$currentState$8_1 = distributionTempVariable$var69$7;
											if((index$sample$4 == sample)) {
												if((0 == timeStep$var91)) {
													if((var55 == traceTempVariable$currentState$8_1)) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + cv$probabilitySample75Value6);
																			if(metric_valid_g[sample][timeStep$var91])
																				cv$sum = (cv$sum + cv$probabilitySample75Value6);
																		}
																	}
																}
															}
														}
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
							if(fixedFlag$sample88) {
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
											for(int index$sample88$16 = 0; index$sample88$16 < noStates; index$sample88$16 += 1) {
												int distributionTempVariable$var82$18 = index$sample88$16;
												double cv$probabilitySample88Value17 = (1.0 * distribution$sample88[((index$sample$14 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$16]);
												int traceTempVariable$currentState$19_1 = distributionTempVariable$var82$18;
												if((index$sample$14 == sample)) {
													if((timeStep$var74 == timeStep$var91)) {
														if((var55 == traceTempVariable$currentState$19_1)) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + cv$probabilitySample88Value17);
																				if(metric_valid_g[sample][timeStep$var91])
																					cv$sum = (cv$sum + cv$probabilitySample88Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample75(int sample) {
		int cv$noStates = 0;
		int index$sample$1 = sample;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var69$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$initialStateDistribution.length))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var79$3_1 = cv$currentValue;
						for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
							if((sample == index$sample$3_2)) {
								for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$3_2]; timeStep$var74 += 1) {
									if((0 == (timeStep$var74 - 1))) {
										if(fixedFlag$sample88) {
											{
												int index$timeStep$5 = timeStep$var74;
												int index$sample$6 = index$sample$3_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var25 = 0; var25 < noStates; var25 += 1) {
														if((var25 == traceTempVariable$var79$3_1)) {
															{
																{
																	double[] cv$temp$1$var80;
																	{
																		double[] var80 = m[traceTempVariable$var79$3_1];
																		cv$temp$1$var80 = var80;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var74]) && (st[index$sample$3_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$3_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var74]) && (st[index$sample$3_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$3_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var74]) && (st[index$sample$3_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$3_2][timeStep$var74]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var74]) && (st[index$sample$3_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$3_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var74]) && (st[index$sample$3_2][timeStep$var74] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[index$sample$3_2][timeStep$var74]]):Double.NEGATIVE_INFINITY)));
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
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$9_2]; timeStep$var91 += 1) {
									if((0 == timeStep$var91)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var55 = 0; var55 < noStates; var55 += 1) {
													if((var55 == traceTempVariable$currentState$9_1)) {
														{
															{
																double cv$temp$2$var94;
																{
																	double var94 = metric_valid_bias[traceTempVariable$currentState$9_1];
																	cv$temp$2$var94 = var94;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var91], cv$temp$2$var94)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var91], cv$temp$2$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var91], cv$temp$2$var94));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var91], cv$temp$2$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var91], cv$temp$2$var94)));
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
						boolean[][] guard$sample75gaussian112 = guard$sample75gaussian112$global;
						for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
							if((sample == index$sample$13_1)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$13_1]; timeStep$var91 += 1) {
									if((0 == timeStep$var91))
										guard$sample75gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
								}
							}
						}
						for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
							if((sample == index$sample$14_1)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$14_1]; timeStep$var91 += 1) {
									if((0 == timeStep$var91))
										guard$sample75gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
								}
							}
						}
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int index$sample$15_2 = 0; index$sample$15_2 < noSamples; index$sample$15_2 += 1) {
							if((sample == index$sample$15_2)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$15_2]; timeStep$var91 += 1) {
									if((0 == timeStep$var91)) {
										if(!guard$sample75gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
											guard$sample75gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var37 = 0; var37 < noStates; var37 += 1) {
														if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$15_1)) {
																int traceTempVariable$currentState$20_1 = cv$currentValue;
																if((index$sample$2 == index$sample$15_2)) {
																	if((0 == timeStep$var91)) {
																		for(int var46 = 0; var46 < noStates; var46 += 1) {
																			if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																				if((var46 == traceTempVariable$currentState$20_1)) {
																					{
																						{
																							double cv$temp$3$var100;
																							{
																								double var100 = metric_mean[traceTempVariable$currentState$20_1];
																								cv$temp$3$var100 = var100;
																							}
																							double cv$temp$4$var101;
																							{
																								double var101 = metric_var[traceTempVariable$currentState$20_1];
																								cv$temp$4$var101 = var101;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$3$var100) / Math.sqrt(cv$temp$4$var101))) - (0.5 * Math.log(cv$temp$4$var101)))));
																							}
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
																	if(!(index$sample$21 == index$sample$2)) {
																		for(int index$sample75$22 = 0; index$sample75$22 < noStates; index$sample75$22 += 1) {
																			int distributionTempVariable$var69$24 = index$sample75$22;
																			double cv$probabilitySample75Value23 = (1.0 * distribution$sample75[((index$sample$21 - 0) / 1)][index$sample75$22]);
																			int traceTempVariable$currentState$25_1 = distributionTempVariable$var69$24;
																			if((index$sample$21 == index$sample$15_2)) {
																				if((0 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$25_1)) {
																								{
																									{
																										double cv$temp$5$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$25_1];
																											cv$temp$5$var100 = var100;
																										}
																										double cv$temp$6$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$25_1];
																											cv$temp$6$var101 = var101;
																										}
																										if(((Math.log(cv$probabilitySample75Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$5$var100) / Math.sqrt(cv$temp$6$var101))) - (0.5 * Math.log(cv$temp$6$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value23);
																									}
																								}
																							}
																						}
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
														if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
															if((var37 == traceTempVariable$currentState$15_1)) {
																if(fixedFlag$sample88) {
																	for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$29_1]; timeStep$var74 += 1) {
																			if((index$sample$29_1 == index$sample$15_2)) {
																				if((timeStep$var74 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$15_1)) {
																								{
																									{
																										double cv$temp$7$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$15_1];
																											cv$temp$7$var100 = var100;
																										}
																										double cv$temp$8$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$15_1];
																											cv$temp$8$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$7$var100) / Math.sqrt(cv$temp$8$var101))) - (0.5 * Math.log(cv$temp$8$var101)))));
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
																	for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																		for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$30]; timeStep$var74 += 1) {
																			if(true) {
																				for(int index$sample88$32 = 0; index$sample88$32 < noStates; index$sample88$32 += 1) {
																					int distributionTempVariable$var82$34 = index$sample88$32;
																					double cv$probabilitySample88Value33 = (1.0 * distribution$sample88[((index$sample$30 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$32]);
																					int traceTempVariable$currentState$35_1 = distributionTempVariable$var82$34;
																					if((index$sample$30 == index$sample$15_2)) {
																						if((timeStep$var74 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$15_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$35_1)) {
																										{
																											{
																												double cv$temp$9$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$35_1];
																													cv$temp$9$var100 = var100;
																												}
																												double cv$temp$10$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$35_1];
																													cv$temp$10$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample88Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var91] - cv$temp$9$var100) / Math.sqrt(cv$temp$10$var101))) - (0.5 * Math.log(cv$temp$10$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value33);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
						int traceTempVariable$currentState$16_1 = cv$currentValue;
						for(int index$sample$16_2 = 0; index$sample$16_2 < noSamples; index$sample$16_2 += 1) {
							if((sample == index$sample$16_2)) {
								for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$16_2]; timeStep$var91 += 1) {
									if((0 == timeStep$var91)) {
										if(!guard$sample75gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
											guard$sample75gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													int traceTempVariable$currentState$38_1 = cv$currentValue;
													if((index$sample$2 == index$sample$16_2)) {
														if((0 == timeStep$var91)) {
															for(int var37 = 0; var37 < noStates; var37 += 1) {
																if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																	if((var37 == traceTempVariable$currentState$38_1)) {
																		for(int var46 = 0; var46 < noStates; var46 += 1) {
																			if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																				if((var46 == traceTempVariable$currentState$38_1)) {
																					{
																						{
																							double cv$temp$11$var100;
																							{
																								double var100 = metric_mean[traceTempVariable$currentState$38_1];
																								cv$temp$11$var100 = var100;
																							}
																							double cv$temp$12$var101;
																							{
																								double var101 = metric_var[traceTempVariable$currentState$38_1];
																								cv$temp$12$var101 = var101;
																							}
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$11$var100) / Math.sqrt(cv$temp$12$var101))) - (0.5 * Math.log(cv$temp$12$var101)))));
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
													for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
														if(!(index$sample$39 == index$sample$2)) {
															for(int index$sample75$40 = 0; index$sample75$40 < noStates; index$sample75$40 += 1) {
																int distributionTempVariable$var69$42 = index$sample75$40;
																double cv$probabilitySample75Value41 = (1.0 * distribution$sample75[((index$sample$39 - 0) / 1)][index$sample75$40]);
																int traceTempVariable$currentState$43_1 = distributionTempVariable$var69$42;
																if((index$sample$39 == index$sample$16_2)) {
																	if((0 == timeStep$var91)) {
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$43_1)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$43_1)) {
																								{
																									{
																										double cv$temp$13$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$43_1];
																											cv$temp$13$var100 = var100;
																										}
																										double cv$temp$14$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$43_1];
																											cv$temp$14$var101 = var101;
																										}
																										if(((Math.log(cv$probabilitySample75Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$13$var100) / Math.sqrt(cv$temp$14$var101))) - (0.5 * Math.log(cv$temp$14$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value41);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													if(fixedFlag$sample88) {
														for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
															for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$48_1]; timeStep$var74 += 1) {
																if((index$sample$48_1 == index$sample$16_2)) {
																	if((timeStep$var74 == timeStep$var91)) {
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$16_1)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$16_1)) {
																								{
																									{
																										double cv$temp$15$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$16_1];
																											cv$temp$15$var100 = var100;
																										}
																										double cv$temp$16$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$16_1];
																											cv$temp$16$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$15$var100) / Math.sqrt(cv$temp$16$var101))) - (0.5 * Math.log(cv$temp$16$var101)))));
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
														for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
															for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$49]; timeStep$var74 += 1) {
																if(true) {
																	for(int index$sample88$51 = 0; index$sample88$51 < noStates; index$sample88$51 += 1) {
																		int distributionTempVariable$var82$53 = index$sample88$51;
																		double cv$probabilitySample88Value52 = (1.0 * distribution$sample88[((index$sample$49 - 0) / 1)][((timeStep$var74 - 1) / 1)][index$sample88$51]);
																		int traceTempVariable$currentState$54_1 = distributionTempVariable$var82$53;
																		if((index$sample$49 == index$sample$16_2)) {
																			if((timeStep$var74 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$54_1)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$16_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$54_1)) {
																										{
																											{
																												double cv$temp$17$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$54_1];
																													cv$temp$17$var100 = var100;
																												}
																												double cv$temp$18$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$54_1];
																													cv$temp$18$var101 = var101;
																												}
																												if(((Math.log(cv$probabilitySample88Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var91] - cv$temp$17$var100) / Math.sqrt(cv$temp$18$var101))) - (0.5 * Math.log(cv$temp$18$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value52);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
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
					int traceTempVariable$var79$67_1 = cv$currentValue;
					for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
						if((sample == index$sample$67_2)) {
							for(int timeStep$var74 = 1; timeStep$var74 < length$metric[index$sample$67_2]; timeStep$var74 += 1) {
								if((0 == (timeStep$var74 - 1))) {
									if(!fixedFlag$sample88) {
										{
											int index$timeStep$69 = timeStep$var74;
											int index$sample$70 = index$sample$67_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var81;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var25 = 0; var25 < noStates; var25 += 1) {
												if((var25 == traceTempVariable$var79$67_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var80;
														{
															double[] var80 = m[traceTempVariable$var79$67_1];
															cv$temp$19$var80 = var80;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var80);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample88[((index$sample$67_2 - 0) / 1)][((timeStep$var74 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample75[((sample - 0) / 1)];
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
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample88(int sample, int timeStep$var74) {
		int cv$noStates = 0;
		int index$timeStep$1 = timeStep$var74;
		int index$sample$2 = sample;
		if(fixedFlag$sample75) {
			for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
				if((index$sample$3_1 == sample)) {
					if((0 == (timeStep$var74 - 1))) {
						for(int var25 = 0; var25 < noStates; var25 += 1) {
							if((var25 == st[sample][(timeStep$var74 - 1)]))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
				if(true) {
					for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
						int distributionTempVariable$var69$7 = index$sample75$5;
						double cv$probabilitySample75Value6 = (1.0 * distribution$sample75[((index$sample$4 - 0) / 1)][index$sample75$5]);
						int traceTempVariable$var79$8_1 = distributionTempVariable$var69$7;
						if((index$sample$4 == sample)) {
							if((0 == (timeStep$var74 - 1))) {
								for(int var25 = 0; var25 < noStates; var25 += 1) {
									if((var25 == traceTempVariable$var79$8_1))
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		if((index$sample$2 == sample)) {
			if((index$timeStep$1 == (timeStep$var74 - 1))) {
				for(int var25 = 0; var25 < noStates; var25 += 1) {
					if((var25 == st[sample][(timeStep$var74 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample88) {
			for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
				for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1]; index$timeStep$12_2 += 1) {
					if((index$sample$12_1 == sample)) {
						if((index$timeStep$12_2 == (timeStep$var74 - 1))) {
							for(int var25 = 0; var25 < noStates; var25 += 1) {
								if((var25 == st[sample][(timeStep$var74 - 1)]))
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
				for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13]; index$timeStep$14 += 1) {
					if(!((index$timeStep$14 == index$timeStep$1) && (index$sample$13 == index$sample$2))) {
						for(int index$sample88$15 = 0; index$sample88$15 < noStates; index$sample88$15 += 1) {
							int distributionTempVariable$var82$17 = index$sample88$15;
							double cv$probabilitySample88Value16 = (1.0 * distribution$sample88[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample88$15]);
							int traceTempVariable$var79$18_1 = distributionTempVariable$var82$17;
							if((index$sample$13 == sample)) {
								if((index$timeStep$14 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == traceTempVariable$var79$18_1))
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$timeStep$22 = timeStep$var74;
			int index$sample$23 = sample;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample75) {
				for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
					if((index$sample$24_1 == sample)) {
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
											int traceTempVariable$var79$41_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$45_1 = cv$currentValue;
											for(int index$sample$45_2 = 0; index$sample$45_2 < noSamples; index$sample$45_2 += 1) {
												if((sample == index$sample$45_2)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$45_2]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91)) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var55 = 0; var55 < noStates; var55 += 1) {
																		if((var55 == traceTempVariable$currentState$45_1)) {
																			{
																				{
																					double cv$temp$4$var94;
																					{
																						double var94 = metric_valid_bias[traceTempVariable$currentState$45_1];
																						cv$temp$4$var94 = var94;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var91], cv$temp$4$var94)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var91], cv$temp$4$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var91], cv$temp$4$var94));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var91], cv$temp$4$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var91], cv$temp$4$var94)));
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
											boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global;
											for(int index$sample$61_1 = 0; index$sample$61_1 < noSamples; index$sample$61_1 += 1) {
												if((sample == index$sample$61_1)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$61_1]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91))
															guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
													}
												}
											}
											for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
												if((sample == index$sample$65_1)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$65_1]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91))
															guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
													}
												}
											}
											int traceTempVariable$currentState$69_1 = cv$currentValue;
											for(int index$sample$69_2 = 0; index$sample$69_2 < noSamples; index$sample$69_2 += 1) {
												if((sample == index$sample$69_2)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$69_2]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91)) {
															if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$69_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$69_1)) {
																					for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																						if((index$sample$86_1 == index$sample$69_2)) {
																							if((0 == timeStep$var91)) {
																								for(int var46 = 0; var46 < noStates; var46 += 1) {
																									if(metric_valid_g[index$sample$69_2][timeStep$var91]) {
																										if((var46 == traceTempVariable$currentState$69_1)) {
																											{
																												{
																													double cv$temp$8$var100;
																													{
																														double var100 = metric_mean[traceTempVariable$currentState$69_1];
																														cv$temp$8$var100 = var100;
																													}
																													double cv$temp$9$var101;
																													{
																														double var101 = metric_var[traceTempVariable$currentState$69_1];
																														cv$temp$9$var101 = var101;
																													}
																													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$8$var100) / Math.sqrt(cv$temp$9$var101))) - (0.5 * Math.log(cv$temp$9$var101)))));
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
																			if(metric_valid_g[index$sample$69_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$69_1)) {
																					int traceTempVariable$currentState$89_1 = cv$currentValue;
																					if((index$sample$23 == index$sample$69_2)) {
																						if((index$timeStep$22 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$69_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$89_1)) {
																										{
																											{
																												double cv$temp$10$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$89_1];
																													cv$temp$10$var100 = var100;
																												}
																												double cv$temp$11$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$89_1];
																													cv$temp$11$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$10$var100) / Math.sqrt(cv$temp$11$var101))) - (0.5 * Math.log(cv$temp$11$var101)))));
																												}
																												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$90 = 0; index$sample$90 < noSamples; index$sample$90 += 1) {
																						for(int index$timeStep$91 = 1; index$timeStep$91 < length$metric[index$sample$90]; index$timeStep$91 += 1) {
																							if(!((index$timeStep$91 == index$timeStep$22) && (index$sample$90 == index$sample$23))) {
																								for(int index$sample88$92 = 0; index$sample88$92 < noStates; index$sample88$92 += 1) {
																									int distributionTempVariable$var82$94 = index$sample88$92;
																									double cv$probabilitySample88Value93 = (1.0 * distribution$sample88[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample88$92]);
																									int traceTempVariable$currentState$95_1 = distributionTempVariable$var82$94;
																									if((index$sample$90 == index$sample$69_2)) {
																										if((index$timeStep$91 == timeStep$var91)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$69_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$95_1)) {
																														{
																															{
																																double cv$temp$12$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$95_1];
																																	cv$temp$12$var100 = var100;
																																}
																																double cv$temp$13$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$95_1];
																																	cv$temp$13$var101 = var101;
																																}
																																if(((Math.log(cv$probabilitySample88Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var91] - cv$temp$12$var100) / Math.sqrt(cv$temp$13$var101))) - (0.5 * Math.log(cv$temp$13$var101)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value93);
																															}
																														}
																													}
																												}
																											}
																										}
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
											int traceTempVariable$currentState$73_1 = cv$currentValue;
											for(int index$sample$73_2 = 0; index$sample$73_2 < noSamples; index$sample$73_2 += 1) {
												if((sample == index$sample$73_2)) {
													for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$73_2]; timeStep$var91 += 1) {
														if((timeStep$var74 == timeStep$var91)) {
															if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																{
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																			if((index$sample$157_1 == index$sample$73_2)) {
																				if((0 == timeStep$var91)) {
																					for(int var37 = 0; var37 < noStates; var37 += 1) {
																						if(metric_valid_g[index$sample$73_2][timeStep$var91]) {
																							if((var37 == traceTempVariable$currentState$73_1)) {
																								for(int var46 = 0; var46 < noStates; var46 += 1) {
																									if(metric_valid_g[index$sample$73_2][timeStep$var91]) {
																										if((var46 == traceTempVariable$currentState$73_1)) {
																											{
																												{
																													double cv$temp$40$var100;
																													{
																														double var100 = metric_mean[traceTempVariable$currentState$73_1];
																														cv$temp$40$var100 = var100;
																													}
																													double cv$temp$41$var101;
																													{
																														double var101 = metric_var[traceTempVariable$currentState$73_1];
																														cv$temp$41$var101 = var101;
																													}
																													if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$40$var100) / Math.sqrt(cv$temp$41$var101))) - (0.5 * Math.log(cv$temp$41$var101)))));
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
																		int traceTempVariable$currentState$160_1 = cv$currentValue;
																		if((index$sample$23 == index$sample$73_2)) {
																			if((index$timeStep$22 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$73_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$160_1)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$73_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$160_1)) {
																										{
																											{
																												double cv$temp$42$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$160_1];
																													cv$temp$42$var100 = var100;
																												}
																												double cv$temp$43$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$160_1];
																													cv$temp$43$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$42$var100) / Math.sqrt(cv$temp$43$var101))) - (0.5 * Math.log(cv$temp$43$var101)))));
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
																				if(!((index$timeStep$162 == index$timeStep$22) && (index$sample$161 == index$sample$23))) {
																					for(int index$sample88$163 = 0; index$sample88$163 < noStates; index$sample88$163 += 1) {
																						int distributionTempVariable$var82$165 = index$sample88$163;
																						double cv$probabilitySample88Value164 = (1.0 * distribution$sample88[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample88$163]);
																						int traceTempVariable$currentState$166_1 = distributionTempVariable$var82$165;
																						if((index$sample$161 == index$sample$73_2)) {
																							if((index$timeStep$162 == timeStep$var91)) {
																								for(int var37 = 0; var37 < noStates; var37 += 1) {
																									if(metric_valid_g[index$sample$73_2][timeStep$var91]) {
																										if((var37 == traceTempVariable$currentState$166_1)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$73_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$166_1)) {
																														{
																															{
																																double cv$temp$44$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$166_1];
																																	cv$temp$44$var100 = var100;
																																}
																																double cv$temp$45$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$166_1];
																																	cv$temp$45$var101 = var101;
																																}
																																if(((Math.log(cv$probabilitySample88Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var91] - cv$temp$44$var100) / Math.sqrt(cv$temp$45$var101))) - (0.5 * Math.log(cv$temp$45$var101)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value164);
																															}
																														}
																													}
																												}
																											}
																										}
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
				for(int index$sample$25 = 0; index$sample$25 < noSamples; index$sample$25 += 1) {
					if(true) {
						for(int index$sample75$26 = 0; index$sample75$26 < noStates; index$sample75$26 += 1) {
							int distributionTempVariable$var69$28 = index$sample75$26;
							double cv$probabilitySample75Value27 = (1.0 * distribution$sample75[((index$sample$25 - 0) / 1)][index$sample75$26]);
							int traceTempVariable$var79$29_1 = distributionTempVariable$var69$28;
							if((index$sample$25 == sample)) {
								if((0 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == traceTempVariable$var79$29_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value27);
											double[] cv$temp$1$var80;
											{
												double[] var80 = m[traceTempVariable$var79$29_1];
												cv$temp$1$var80 = var80;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample75Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var79$42_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$46_1 = cv$currentValue;
													for(int index$sample$46_2 = 0; index$sample$46_2 < noSamples; index$sample$46_2 += 1) {
														if((sample == index$sample$46_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$46_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var55 = 0; var55 < noStates; var55 += 1) {
																				if((var55 == traceTempVariable$currentState$46_1)) {
																					{
																						{
																							double cv$temp$5$var94;
																							{
																								double var94 = metric_valid_bias[traceTempVariable$currentState$46_1];
																								cv$temp$5$var94 = var94;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var91], cv$temp$5$var94)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var91], cv$temp$5$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var91], cv$temp$5$var94));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var91], cv$temp$5$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var91], cv$temp$5$var94)));
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
													boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global;
													for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
														if((sample == index$sample$62_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$62_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
														if((sample == index$sample$66_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$66_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													int traceTempVariable$currentState$70_1 = cv$currentValue;
													for(int index$sample$70_2 = 0; index$sample$70_2 < noSamples; index$sample$70_2 += 1) {
														if((sample == index$sample$70_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$70_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$70_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$70_1)) {
																							int traceTempVariable$currentState$99_1 = distributionTempVariable$var69$28;
																							if((index$sample$25 == index$sample$70_2)) {
																								if((0 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$70_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$99_1)) {
																												{
																													{
																														double cv$temp$14$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$99_1];
																															cv$temp$14$var100 = var100;
																														}
																														double cv$temp$15$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$99_1];
																															cv$temp$15$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$14$var100) / Math.sqrt(cv$temp$15$var101))) - (0.5 * Math.log(cv$temp$15$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$100 = 0; index$sample$100 < noSamples; index$sample$100 += 1) {
																								if(!(index$sample$100 == index$sample$25)) {
																									for(int index$sample75$101 = 0; index$sample75$101 < noStates; index$sample75$101 += 1) {
																										int distributionTempVariable$var69$103 = index$sample75$101;
																										double cv$probabilitySample75Value102 = (1.0 * distribution$sample75[((index$sample$100 - 0) / 1)][index$sample75$101]);
																										int traceTempVariable$currentState$104_1 = distributionTempVariable$var69$103;
																										if((index$sample$100 == index$sample$70_2)) {
																											if((0 == timeStep$var91)) {
																												for(int var46 = 0; var46 < noStates; var46 += 1) {
																													if(metric_valid_g[index$sample$70_2][timeStep$var91]) {
																														if((var46 == traceTempVariable$currentState$104_1)) {
																															{
																																{
																																	double cv$temp$16$var100;
																																	{
																																		double var100 = metric_mean[traceTempVariable$currentState$104_1];
																																		cv$temp$16$var100 = var100;
																																	}
																																	double cv$temp$17$var101;
																																	{
																																		double var101 = metric_var[traceTempVariable$currentState$104_1];
																																		cv$temp$17$var101 = var101;
																																	}
																																	if(((Math.log(cv$probabilitySample75Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$16$var100) / Math.sqrt(cv$temp$17$var101))) - (0.5 * Math.log(cv$temp$17$var101)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value102);
																																}
																															}
																														}
																													}
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
																					if(metric_valid_g[index$sample$70_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$70_1)) {
																							int traceTempVariable$currentState$108_1 = cv$currentValue;
																							if((index$sample$23 == index$sample$70_2)) {
																								if((index$timeStep$22 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$70_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$108_1)) {
																												{
																													{
																														double cv$temp$18$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$108_1];
																															cv$temp$18$var100 = var100;
																														}
																														double cv$temp$19$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$108_1];
																															cv$temp$19$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$18$var100) / Math.sqrt(cv$temp$19$var101))) - (0.5 * Math.log(cv$temp$19$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$109 = 0; index$sample$109 < noSamples; index$sample$109 += 1) {
																								for(int index$timeStep$110 = 1; index$timeStep$110 < length$metric[index$sample$109]; index$timeStep$110 += 1) {
																									if(!((index$timeStep$110 == index$timeStep$22) && (index$sample$109 == index$sample$23))) {
																										for(int index$sample88$111 = 0; index$sample88$111 < noStates; index$sample88$111 += 1) {
																											int distributionTempVariable$var82$113 = index$sample88$111;
																											double cv$probabilitySample88Value112 = (1.0 * distribution$sample88[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample88$111]);
																											int traceTempVariable$currentState$114_1 = distributionTempVariable$var82$113;
																											if((index$sample$109 == index$sample$70_2)) {
																												if((index$timeStep$110 == timeStep$var91)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$70_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$114_1)) {
																																{
																																	{
																																		double cv$temp$20$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$114_1];
																																			cv$temp$20$var100 = var100;
																																		}
																																		double cv$temp$21$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$114_1];
																																			cv$temp$21$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample88Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var91] - cv$temp$20$var100) / Math.sqrt(cv$temp$21$var101))) - (0.5 * Math.log(cv$temp$21$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value112);
																																	}
																																}
																															}
																														}
																													}
																												}
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
													int traceTempVariable$currentState$74_1 = cv$currentValue;
													for(int index$sample$74_2 = 0; index$sample$74_2 < noSamples; index$sample$74_2 += 1) {
														if((sample == index$sample$74_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$74_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				int traceTempVariable$currentState$171_1 = distributionTempVariable$var69$28;
																				if((index$sample$25 == index$sample$74_2)) {
																					if((0 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$171_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$171_1)) {
																												{
																													{
																														double cv$temp$46$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$171_1];
																															cv$temp$46$var100 = var100;
																														}
																														double cv$temp$47$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$171_1];
																															cv$temp$47$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$46$var100) / Math.sqrt(cv$temp$47$var101))) - (0.5 * Math.log(cv$temp$47$var101)))));
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
																				for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																					if(!(index$sample$172 == index$sample$25)) {
																						for(int index$sample75$173 = 0; index$sample75$173 < noStates; index$sample75$173 += 1) {
																							int distributionTempVariable$var69$175 = index$sample75$173;
																							double cv$probabilitySample75Value174 = (1.0 * distribution$sample75[((index$sample$172 - 0) / 1)][index$sample75$173]);
																							int traceTempVariable$currentState$176_1 = distributionTempVariable$var69$175;
																							if((index$sample$172 == index$sample$74_2)) {
																								if((0 == timeStep$var91)) {
																									for(int var37 = 0; var37 < noStates; var37 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																											if((var37 == traceTempVariable$currentState$176_1)) {
																												for(int var46 = 0; var46 < noStates; var46 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																														if((var46 == traceTempVariable$currentState$176_1)) {
																															{
																																{
																																	double cv$temp$48$var100;
																																	{
																																		double var100 = metric_mean[traceTempVariable$currentState$176_1];
																																		cv$temp$48$var100 = var100;
																																	}
																																	double cv$temp$49$var101;
																																	{
																																		double var101 = metric_var[traceTempVariable$currentState$176_1];
																																		cv$temp$49$var101 = var101;
																																	}
																																	if(((Math.log(cv$probabilitySample75Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$48$var100) / Math.sqrt(cv$temp$49$var101))) - (0.5 * Math.log(cv$temp$49$var101)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value174);
																																}
																															}
																														}
																													}
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
																					if((index$timeStep$22 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$181_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$181_1)) {
																												{
																													{
																														double cv$temp$50$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$181_1];
																															cv$temp$50$var100 = var100;
																														}
																														double cv$temp$51$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$181_1];
																															cv$temp$51$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$50$var100) / Math.sqrt(cv$temp$51$var101))) - (0.5 * Math.log(cv$temp$51$var101)))));
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
																						if(!((index$timeStep$183 == index$timeStep$22) && (index$sample$182 == index$sample$23))) {
																							for(int index$sample88$184 = 0; index$sample88$184 < noStates; index$sample88$184 += 1) {
																								int distributionTempVariable$var82$186 = index$sample88$184;
																								double cv$probabilitySample88Value185 = (1.0 * distribution$sample88[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample88$184]);
																								int traceTempVariable$currentState$187_1 = distributionTempVariable$var82$186;
																								if((index$sample$182 == index$sample$74_2)) {
																									if((index$timeStep$183 == timeStep$var91)) {
																										for(int var37 = 0; var37 < noStates; var37 += 1) {
																											if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																												if((var37 == traceTempVariable$currentState$187_1)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$74_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$187_1)) {
																																{
																																	{
																																		double cv$temp$52$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$187_1];
																																			cv$temp$52$var100 = var100;
																																		}
																																		double cv$temp$53$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$187_1];
																																			cv$temp$53$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample88Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var91] - cv$temp$52$var100) / Math.sqrt(cv$temp$53$var101))) - (0.5 * Math.log(cv$temp$53$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value185);
																																	}
																																}
																															}
																														}
																													}
																												}
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
			int traceTempVariable$var79$32_1 = cv$currentValue;
			if((index$sample$23 == sample)) {
				if((index$timeStep$22 == (timeStep$var74 - 1))) {
					for(int var25 = 0; var25 < noStates; var25 += 1) {
						if((var25 == traceTempVariable$var79$32_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var80;
							{
								double[] var80 = m[traceTempVariable$var79$32_1];
								cv$temp$2$var80 = var80;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var79$43_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$47_1 = cv$currentValue;
									for(int index$sample$47_2 = 0; index$sample$47_2 < noSamples; index$sample$47_2 += 1) {
										if((sample == index$sample$47_2)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$47_2]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var55 = 0; var55 < noStates; var55 += 1) {
																if((var55 == traceTempVariable$currentState$47_1)) {
																	{
																		{
																			double cv$temp$6$var94;
																			{
																				double var94 = metric_valid_bias[traceTempVariable$currentState$47_1];
																				cv$temp$6$var94 = var94;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var91], cv$temp$6$var94)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var91], cv$temp$6$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var91], cv$temp$6$var94));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var91], cv$temp$6$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var91], cv$temp$6$var94)));
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
									boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global;
									for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
										if((sample == index$sample$63_1)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$63_1]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91))
													guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
											}
										}
									}
									for(int index$sample$67_1 = 0; index$sample$67_1 < noSamples; index$sample$67_1 += 1) {
										if((sample == index$sample$67_1)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$67_1]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91))
													guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
											}
										}
									}
									int traceTempVariable$currentState$71_1 = cv$currentValue;
									for(int index$sample$71_2 = 0; index$sample$71_2 < noSamples; index$sample$71_2 += 1) {
										if((sample == index$sample$71_2)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$71_2]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91)) {
													if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
														guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var37 = 0; var37 < noStates; var37 += 1) {
																	if(metric_valid_g[index$sample$71_2][timeStep$var91]) {
																		if((var37 == traceTempVariable$currentState$71_1)) {
																			if(fixedFlag$sample75) {
																				for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																					if((index$sample$118_1 == index$sample$71_2)) {
																						if((0 == timeStep$var91)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$71_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$71_1)) {
																										{
																											{
																												double cv$temp$22$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$71_1];
																													cv$temp$22$var100 = var100;
																												}
																												double cv$temp$23$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$71_1];
																													cv$temp$23$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$22$var100) / Math.sqrt(cv$temp$23$var101))) - (0.5 * Math.log(cv$temp$23$var101)))));
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
																				for(int index$sample$119 = 0; index$sample$119 < noSamples; index$sample$119 += 1) {
																					if(true) {
																						for(int index$sample75$120 = 0; index$sample75$120 < noStates; index$sample75$120 += 1) {
																							int distributionTempVariable$var69$122 = index$sample75$120;
																							double cv$probabilitySample75Value121 = (1.0 * distribution$sample75[((index$sample$119 - 0) / 1)][index$sample75$120]);
																							int traceTempVariable$currentState$123_1 = distributionTempVariable$var69$122;
																							if((index$sample$119 == index$sample$71_2)) {
																								if((0 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$71_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$123_1)) {
																												{
																													{
																														double cv$temp$24$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$123_1];
																															cv$temp$24$var100 = var100;
																														}
																														double cv$temp$25$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$123_1];
																															cv$temp$25$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample75Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$24$var100) / Math.sqrt(cv$temp$25$var101))) - (0.5 * Math.log(cv$temp$25$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value121);
																													}
																												}
																											}
																										}
																									}
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
																	if(metric_valid_g[index$sample$71_2][timeStep$var91]) {
																		if((var37 == traceTempVariable$currentState$71_1)) {
																			int traceTempVariable$currentState$127_1 = cv$currentValue;
																			if((index$sample$23 == index$sample$71_2)) {
																				if((index$timeStep$22 == timeStep$var91)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$71_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$127_1)) {
																								{
																									{
																										double cv$temp$26$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$127_1];
																											cv$temp$26$var100 = var100;
																										}
																										double cv$temp$27$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$127_1];
																											cv$temp$27$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$26$var100) / Math.sqrt(cv$temp$27$var101))) - (0.5 * Math.log(cv$temp$27$var101)))));
																										}
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$128 = 0; index$sample$128 < noSamples; index$sample$128 += 1) {
																				for(int index$timeStep$129 = 1; index$timeStep$129 < length$metric[index$sample$128]; index$timeStep$129 += 1) {
																					if(!((index$timeStep$129 == index$timeStep$22) && (index$sample$128 == index$sample$23))) {
																						for(int index$sample88$130 = 0; index$sample88$130 < noStates; index$sample88$130 += 1) {
																							int distributionTempVariable$var82$132 = index$sample88$130;
																							double cv$probabilitySample88Value131 = (1.0 * distribution$sample88[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample88$130]);
																							int traceTempVariable$currentState$133_1 = distributionTempVariable$var82$132;
																							if((index$sample$128 == index$sample$71_2)) {
																								if((index$timeStep$129 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$71_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$133_1)) {
																												{
																													{
																														double cv$temp$28$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$133_1];
																															cv$temp$28$var100 = var100;
																														}
																														double cv$temp$29$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$133_1];
																															cv$temp$29$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample88Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var91] - cv$temp$28$var100) / Math.sqrt(cv$temp$29$var101))) - (0.5 * Math.log(cv$temp$29$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value131);
																													}
																												}
																											}
																										}
																									}
																								}
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
									int traceTempVariable$currentState$75_1 = cv$currentValue;
									for(int index$sample$75_2 = 0; index$sample$75_2 < noSamples; index$sample$75_2 += 1) {
										if((sample == index$sample$75_2)) {
											for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$75_2]; timeStep$var91 += 1) {
												if((timeStep$var74 == timeStep$var91)) {
													if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
														guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																if(fixedFlag$sample75) {
																	for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																		if((index$sample$192_1 == index$sample$75_2)) {
																			if((0 == timeStep$var91)) {
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$75_1)) {
																							for(int var46 = 0; var46 < noStates; var46 += 1) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																									if((var46 == traceTempVariable$currentState$75_1)) {
																										{
																											{
																												double cv$temp$54$var100;
																												{
																													double var100 = metric_mean[traceTempVariable$currentState$75_1];
																													cv$temp$54$var100 = var100;
																												}
																												double cv$temp$55$var101;
																												{
																													double var101 = metric_var[traceTempVariable$currentState$75_1];
																													cv$temp$55$var101 = var101;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$54$var100) / Math.sqrt(cv$temp$55$var101))) - (0.5 * Math.log(cv$temp$55$var101)))));
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
																			for(int index$sample75$194 = 0; index$sample75$194 < noStates; index$sample75$194 += 1) {
																				int distributionTempVariable$var69$196 = index$sample75$194;
																				double cv$probabilitySample75Value195 = (1.0 * distribution$sample75[((index$sample$193 - 0) / 1)][index$sample75$194]);
																				int traceTempVariable$currentState$197_1 = distributionTempVariable$var69$196;
																				if((index$sample$193 == index$sample$75_2)) {
																					if((0 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$197_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$197_1)) {
																												{
																													{
																														double cv$temp$56$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$197_1];
																															cv$temp$56$var100 = var100;
																														}
																														double cv$temp$57$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$197_1];
																															cv$temp$57$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample75Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$56$var100) / Math.sqrt(cv$temp$57$var101))) - (0.5 * Math.log(cv$temp$57$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value195);
																													}
																												}
																											}
																										}
																									}
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
																	if((index$timeStep$22 == timeStep$var91)) {
																		for(int var37 = 0; var37 < noStates; var37 += 1) {
																			if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																				if((var37 == traceTempVariable$currentState$202_1)) {
																					for(int var46 = 0; var46 < noStates; var46 += 1) {
																						if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																							if((var46 == traceTempVariable$currentState$202_1)) {
																								{
																									{
																										double cv$temp$58$var100;
																										{
																											double var100 = metric_mean[traceTempVariable$currentState$202_1];
																											cv$temp$58$var100 = var100;
																										}
																										double cv$temp$59$var101;
																										{
																											double var101 = metric_var[traceTempVariable$currentState$202_1];
																											cv$temp$59$var101 = var101;
																										}
																										if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$58$var100) / Math.sqrt(cv$temp$59$var101))) - (0.5 * Math.log(cv$temp$59$var101)))));
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
																for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																	for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203]; index$timeStep$204 += 1) {
																		if(!((index$timeStep$204 == index$timeStep$22) && (index$sample$203 == index$sample$23))) {
																			for(int index$sample88$205 = 0; index$sample88$205 < noStates; index$sample88$205 += 1) {
																				int distributionTempVariable$var82$207 = index$sample88$205;
																				double cv$probabilitySample88Value206 = (1.0 * distribution$sample88[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample88$205]);
																				int traceTempVariable$currentState$208_1 = distributionTempVariable$var82$207;
																				if((index$sample$203 == index$sample$75_2)) {
																					if((index$timeStep$204 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$208_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$208_1)) {
																												{
																													{
																														double cv$temp$60$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$208_1];
																															cv$temp$60$var100 = var100;
																														}
																														double cv$temp$61$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$208_1];
																															cv$temp$61$var101 = var101;
																														}
																														if(((Math.log(cv$probabilitySample88Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var91] - cv$temp$60$var100) / Math.sqrt(cv$temp$61$var101))) - (0.5 * Math.log(cv$temp$61$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value206);
																													}
																												}
																											}
																										}
																									}
																								}
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
			for(int index$sample$33 = 0; index$sample$33 < noSamples; index$sample$33 += 1) {
				for(int index$timeStep$34 = 1; index$timeStep$34 < length$metric[index$sample$33]; index$timeStep$34 += 1) {
					if(!((index$timeStep$34 == index$timeStep$22) && (index$sample$33 == index$sample$23))) {
						for(int index$sample88$35 = 0; index$sample88$35 < noStates; index$sample88$35 += 1) {
							int distributionTempVariable$var82$37 = index$sample88$35;
							double cv$probabilitySample88Value36 = (1.0 * distribution$sample88[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample88$35]);
							int traceTempVariable$var79$38_1 = distributionTempVariable$var82$37;
							if((index$sample$33 == sample)) {
								if((index$timeStep$34 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == traceTempVariable$var79$38_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample88Value36);
											double[] cv$temp$3$var80;
											{
												double[] var80 = m[traceTempVariable$var79$38_1];
												cv$temp$3$var80 = var80;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample88Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var80.length))?Math.log(cv$temp$3$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var79$44_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$48_1 = cv$currentValue;
													for(int index$sample$48_2 = 0; index$sample$48_2 < noSamples; index$sample$48_2 += 1) {
														if((sample == index$sample$48_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$48_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var55 = 0; var55 < noStates; var55 += 1) {
																				if((var55 == traceTempVariable$currentState$48_1)) {
																					{
																						{
																							double cv$temp$7$var94;
																							{
																								double var94 = metric_valid_bias[traceTempVariable$currentState$48_1];
																								cv$temp$7$var94 = var94;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var91], cv$temp$7$var94)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var91], cv$temp$7$var94)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var91], cv$temp$7$var94));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var91], cv$temp$7$var94)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var91], cv$temp$7$var94)));
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
													boolean[][] guard$sample88gaussian112 = guard$sample88gaussian112$global;
													for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
														if((sample == index$sample$64_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$64_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													for(int index$sample$68_1 = 0; index$sample$68_1 < noSamples; index$sample$68_1 += 1) {
														if((sample == index$sample$68_1)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$68_1]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91))
																	guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = false;
															}
														}
													}
													int traceTempVariable$currentState$72_1 = cv$currentValue;
													for(int index$sample$72_2 = 0; index$sample$72_2 < noSamples; index$sample$72_2 += 1) {
														if((sample == index$sample$72_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$72_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var37 = 0; var37 < noStates; var37 += 1) {
																					if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$72_1)) {
																							if(fixedFlag$sample75) {
																								for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																									if((index$sample$137_1 == index$sample$72_2)) {
																										if((0 == timeStep$var91)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$72_1)) {
																														{
																															{
																																double cv$temp$30$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$72_1];
																																	cv$temp$30$var100 = var100;
																																}
																																double cv$temp$31$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$72_1];
																																	cv$temp$31$var101 = var101;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$30$var100) / Math.sqrt(cv$temp$31$var101))) - (0.5 * Math.log(cv$temp$31$var101)))));
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
																								for(int index$sample$138 = 0; index$sample$138 < noSamples; index$sample$138 += 1) {
																									if(true) {
																										for(int index$sample75$139 = 0; index$sample75$139 < noStates; index$sample75$139 += 1) {
																											int distributionTempVariable$var69$141 = index$sample75$139;
																											double cv$probabilitySample75Value140 = (1.0 * distribution$sample75[((index$sample$138 - 0) / 1)][index$sample75$139]);
																											int traceTempVariable$currentState$142_1 = distributionTempVariable$var69$141;
																											if((index$sample$138 == index$sample$72_2)) {
																												if((0 == timeStep$var91)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$142_1)) {
																																{
																																	{
																																		double cv$temp$32$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$142_1];
																																			cv$temp$32$var100 = var100;
																																		}
																																		double cv$temp$33$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$142_1];
																																			cv$temp$33$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample75Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$32$var100) / Math.sqrt(cv$temp$33$var101))) - (0.5 * Math.log(cv$temp$33$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value140);
																																	}
																																}
																															}
																														}
																													}
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
																					if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																						if((var37 == traceTempVariable$currentState$72_1)) {
																							int traceTempVariable$currentState$146_1 = cv$currentValue;
																							if((index$sample$23 == index$sample$72_2)) {
																								if((index$timeStep$22 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$146_1)) {
																												{
																													{
																														double cv$temp$34$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$146_1];
																															cv$temp$34$var100 = var100;
																														}
																														double cv$temp$35$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$146_1];
																															cv$temp$35$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$34$var100) / Math.sqrt(cv$temp$35$var101))) - (0.5 * Math.log(cv$temp$35$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$147_1 = distributionTempVariable$var82$37;
																							if((index$sample$33 == index$sample$72_2)) {
																								if((index$timeStep$34 == timeStep$var91)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$147_1)) {
																												{
																													{
																														double cv$temp$36$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$147_1];
																															cv$temp$36$var100 = var100;
																														}
																														double cv$temp$37$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$147_1];
																															cv$temp$37$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$36$var100) / Math.sqrt(cv$temp$37$var101))) - (0.5 * Math.log(cv$temp$37$var101)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int index$sample$148 = 0; index$sample$148 < noSamples; index$sample$148 += 1) {
																								for(int index$timeStep$149 = 1; index$timeStep$149 < length$metric[index$sample$148]; index$timeStep$149 += 1) {
																									if((!((index$timeStep$149 == index$timeStep$22) && (index$sample$148 == index$sample$23)) && !((index$timeStep$149 == index$timeStep$34) && (index$sample$148 == index$sample$33)))) {
																										for(int index$sample88$150 = 0; index$sample88$150 < noStates; index$sample88$150 += 1) {
																											int distributionTempVariable$var82$152 = index$sample88$150;
																											double cv$probabilitySample88Value151 = (1.0 * distribution$sample88[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample88$150]);
																											int traceTempVariable$currentState$153_1 = distributionTempVariable$var82$152;
																											if((index$sample$148 == index$sample$72_2)) {
																												if((index$timeStep$149 == timeStep$var91)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$72_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$153_1)) {
																																{
																																	{
																																		double cv$temp$38$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$153_1];
																																			cv$temp$38$var100 = var100;
																																		}
																																		double cv$temp$39$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$153_1];
																																			cv$temp$39$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample88Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var91] - cv$temp$38$var100) / Math.sqrt(cv$temp$39$var101))) - (0.5 * Math.log(cv$temp$39$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value151);
																																	}
																																}
																															}
																														}
																													}
																												}
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
													int traceTempVariable$currentState$76_1 = cv$currentValue;
													for(int index$sample$76_2 = 0; index$sample$76_2 < noSamples; index$sample$76_2 += 1) {
														if((sample == index$sample$76_2)) {
															for(int timeStep$var91 = 0; timeStep$var91 < length$metric[index$sample$76_2]; timeStep$var91 += 1) {
																if((timeStep$var74 == timeStep$var91)) {
																	if(!guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)]) {
																		guard$sample88gaussian112[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = true;
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				if(fixedFlag$sample75) {
																					for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																						if((index$sample$213_1 == index$sample$76_2)) {
																							if((0 == timeStep$var91)) {
																								for(int var37 = 0; var37 < noStates; var37 += 1) {
																									if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																										if((var37 == traceTempVariable$currentState$76_1)) {
																											for(int var46 = 0; var46 < noStates; var46 += 1) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																													if((var46 == traceTempVariable$currentState$76_1)) {
																														{
																															{
																																double cv$temp$62$var100;
																																{
																																	double var100 = metric_mean[traceTempVariable$currentState$76_1];
																																	cv$temp$62$var100 = var100;
																																}
																																double cv$temp$63$var101;
																																{
																																	double var101 = metric_var[traceTempVariable$currentState$76_1];
																																	cv$temp$63$var101 = var101;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$62$var100) / Math.sqrt(cv$temp$63$var101))) - (0.5 * Math.log(cv$temp$63$var101)))));
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
																					for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																						if(true) {
																							for(int index$sample75$215 = 0; index$sample75$215 < noStates; index$sample75$215 += 1) {
																								int distributionTempVariable$var69$217 = index$sample75$215;
																								double cv$probabilitySample75Value216 = (1.0 * distribution$sample75[((index$sample$214 - 0) / 1)][index$sample75$215]);
																								int traceTempVariable$currentState$218_1 = distributionTempVariable$var69$217;
																								if((index$sample$214 == index$sample$76_2)) {
																									if((0 == timeStep$var91)) {
																										for(int var37 = 0; var37 < noStates; var37 += 1) {
																											if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																												if((var37 == traceTempVariable$currentState$218_1)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$218_1)) {
																																{
																																	{
																																		double cv$temp$64$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$218_1];
																																			cv$temp$64$var100 = var100;
																																		}
																																		double cv$temp$65$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$218_1];
																																			cv$temp$65$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample75Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample75Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample75Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample75Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))))) + 1)) + (Math.log(cv$probabilitySample75Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$64$var100) / Math.sqrt(cv$temp$65$var101))) - (0.5 * Math.log(cv$temp$65$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample75Value216);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				int traceTempVariable$currentState$223_1 = cv$currentValue;
																				if((index$sample$23 == index$sample$76_2)) {
																					if((index$timeStep$22 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$223_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$223_1)) {
																												{
																													{
																														double cv$temp$66$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$223_1];
																															cv$temp$66$var100 = var100;
																														}
																														double cv$temp$67$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$223_1];
																															cv$temp$67$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$66$var100) / Math.sqrt(cv$temp$67$var101))) - (0.5 * Math.log(cv$temp$67$var101)))));
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
																				int traceTempVariable$currentState$224_1 = distributionTempVariable$var82$37;
																				if((index$sample$33 == index$sample$76_2)) {
																					if((index$timeStep$34 == timeStep$var91)) {
																						for(int var37 = 0; var37 < noStates; var37 += 1) {
																							if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																								if((var37 == traceTempVariable$currentState$224_1)) {
																									for(int var46 = 0; var46 < noStates; var46 += 1) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																											if((var46 == traceTempVariable$currentState$224_1)) {
																												{
																													{
																														double cv$temp$68$var100;
																														{
																															double var100 = metric_mean[traceTempVariable$currentState$224_1];
																															cv$temp$68$var100 = var100;
																														}
																														double cv$temp$69$var101;
																														{
																															double var101 = metric_var[traceTempVariable$currentState$224_1];
																															cv$temp$69$var101 = var101;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$68$var100) / Math.sqrt(cv$temp$69$var101))) - (0.5 * Math.log(cv$temp$69$var101)))));
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
																				for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																					for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225]; index$timeStep$226 += 1) {
																						if((!((index$timeStep$226 == index$timeStep$22) && (index$sample$225 == index$sample$23)) && !((index$timeStep$226 == index$timeStep$34) && (index$sample$225 == index$sample$33)))) {
																							for(int index$sample88$227 = 0; index$sample88$227 < noStates; index$sample88$227 += 1) {
																								int distributionTempVariable$var82$229 = index$sample88$227;
																								double cv$probabilitySample88Value228 = (1.0 * distribution$sample88[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample88$227]);
																								int traceTempVariable$currentState$230_1 = distributionTempVariable$var82$229;
																								if((index$sample$225 == index$sample$76_2)) {
																									if((index$timeStep$226 == timeStep$var91)) {
																										for(int var37 = 0; var37 < noStates; var37 += 1) {
																											if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																												if((var37 == traceTempVariable$currentState$230_1)) {
																													for(int var46 = 0; var46 < noStates; var46 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var91]) {
																															if((var46 == traceTempVariable$currentState$230_1)) {
																																{
																																	{
																																		double cv$temp$70$var100;
																																		{
																																			double var100 = metric_mean[traceTempVariable$currentState$230_1];
																																			cv$temp$70$var100 = var100;
																																		}
																																		double cv$temp$71$var101;
																																		{
																																			double var101 = metric_var[traceTempVariable$currentState$230_1];
																																			cv$temp$71$var101 = var101;
																																		}
																																		if(((Math.log(cv$probabilitySample88Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample88Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample88Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample88Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))))) + 1)) + (Math.log(cv$probabilitySample88Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var91] - cv$temp$70$var100) / Math.sqrt(cv$temp$71$var101))) - (0.5 * Math.log(cv$temp$71$var101)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample88Value228);
																																	}
																																}
																															}
																														}
																													}
																												}
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
					int traceTempVariable$var79$269_1 = cv$currentValue;
					for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
						if((sample == index$sample$269_2)) {
							for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2]; index$timeStep$269_3 += 1) {
								if((timeStep$var74 == (index$timeStep$269_3 - 1))) {
									{
										int index$timeStep$271 = index$timeStep$269_3;
										int index$sample$272 = index$sample$269_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var81;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var25 = 0; var25 < noStates; var25 += 1) {
											if((var25 == traceTempVariable$var79$269_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample75) {
														for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
															if((index$sample$274_1 == sample)) {
																if((0 == (timeStep$var74 - 1))) {
																	for(int index$var25$280_1 = 0; index$var25$280_1 < noStates; index$var25$280_1 += 1) {
																		if((index$var25$280_1 == st[sample][(timeStep$var74 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
															if(true) {
																for(int index$sample75$276 = 0; index$sample75$276 < noStates; index$sample75$276 += 1) {
																	int distributionTempVariable$var69$278 = index$sample75$276;
																	double cv$probabilitySample75Value277 = (1.0 * distribution$sample75[((index$sample$275 - 0) / 1)][index$sample75$276]);
																	int traceTempVariable$var79$279_1 = distributionTempVariable$var69$278;
																	if((index$sample$275 == sample)) {
																		if((0 == (timeStep$var74 - 1))) {
																			for(int index$var25$281_1 = 0; index$var25$281_1 < noStates; index$var25$281_1 += 1) {
																				if((index$var25$281_1 == traceTempVariable$var79$279_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample75Value277);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var79$282_1 = cv$currentValue;
													if((index$sample$23 == sample)) {
														if((index$timeStep$22 == (timeStep$var74 - 1))) {
															for(int index$var25$289_1 = 0; index$var25$289_1 < noStates; index$var25$289_1 += 1) {
																if((index$var25$289_1 == traceTempVariable$var79$282_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
														for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283]; index$timeStep$284 += 1) {
															if((!((index$timeStep$284 == index$timeStep$22) && (index$sample$283 == index$sample$23)) && !((index$timeStep$284 == index$timeStep$271) && (index$sample$283 == index$sample$272)))) {
																for(int index$sample88$285 = 0; index$sample88$285 < noStates; index$sample88$285 += 1) {
																	int distributionTempVariable$var82$287 = index$sample88$285;
																	double cv$probabilitySample88Value286 = (1.0 * distribution$sample88[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample88$285]);
																	int traceTempVariable$var79$288_1 = distributionTempVariable$var82$287;
																	if((index$sample$283 == sample)) {
																		if((index$timeStep$284 == (timeStep$var74 - 1))) {
																			for(int index$var25$290_1 = 0; index$var25$290_1 < noStates; index$var25$290_1 += 1) {
																				if((index$var25$290_1 == traceTempVariable$var79$288_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample88Value286);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var80;
													{
														double[] var80 = m[traceTempVariable$var79$269_1];
														cv$temp$72$var80 = var80;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var80);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample88[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
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
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
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
			cv$var26$countGlobal = new double[cv$max];
		}
		{
			int cv$var27$max = noStates;
			cv$distributionAccumulator$var81 = new double[cv$var27$max];
		}
		{
			cv$var69$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var91 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			guard$sample75gaussian112$global = new boolean[cv$max_sample][cv$max_timeStep$var91];
		}
		{
			int cv$var27$max = noStates;
			cv$var82$stateProbabilityGlobal = new double[cv$var27$max];
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var91 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			guard$sample88gaussian112$global = new boolean[cv$max_sample][cv$max_timeStep$var91];
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
			distribution$sample88 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)][];
				distribution$sample88[((sample - 0) / 1)] = subarray$0;
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					subarray$0[((timeStep$var74 - 1) / 1)] = new double[noStates];
			}
		}
		{
			distribution$sample75 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample75[((sample - 0) / 1)] = new double[noStates];
		}
		{
			logProbability$var68 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample75 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var81 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var81[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample88 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample88[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var95 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var95[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample104 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample104[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var102 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var102[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample113 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample113[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var25 = 0; var25 < noStates; var25 += 1) {
			double[] var26 = m[var25];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, var26);
		}
		for(int var37 = 0; var37 < noStates; var37 += 1) {
			if(!fixedFlag$sample42)
				metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var46 = 0; var46 < noStates; var46 += 1) {
			if(!fixedFlag$sample51)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			if(!fixedFlag$sample60)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var66 = st[sample];
			if(!fixedFlag$sample75)
				var66[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var75 = st[sample];
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
				if(!fixedFlag$sample88)
					var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var74 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(!fixedFlag$sample104)
					metric_valid_1d[timeStep$var91] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var91]]);
				if(metric_valid_g[sample][timeStep$var91]) {
					if(!fixedFlag$sample113)
						metric_1d[timeStep$var91] = ((Math.sqrt(metric_var[st[sample][timeStep$var91]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var91]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var25 = 0; var25 < noStates; var25 += 1) {
			double[] var26 = m[var25];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, var26);
		}
		for(int var37 = 0; var37 < noStates; var37 += 1) {
			if(!fixedFlag$sample42)
				metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var46 = 0; var46 < noStates; var46 += 1) {
			if(!fixedFlag$sample51)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			if(!fixedFlag$sample60)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			double[] cv$distribution$sample75 = distribution$sample75[((sample - 0) / 1)];
			for(int index$var68 = 0; index$var68 < noStates; index$var68 += 1) {
				double cv$value = (((0.0 <= index$var68) && (index$var68 < initialStateDistribution.length))?initialStateDistribution[index$var68]:0.0);
				if(!fixedFlag$sample75)
					cv$distribution$sample75[index$var68] = cv$value;
			}
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
				double[] cv$distribution$sample88 = distribution$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)];
				for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1) {
					if(!fixedFlag$sample88)
						cv$distribution$sample88[index$var81] = 0.0;
				}
				if(fixedFlag$sample75) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample)) {
							if((0 == (timeStep$var74 - 1))) {
								for(int var25 = 0; var25 < noStates; var25 += 1) {
									if((var25 == st[sample][(timeStep$var74 - 1)])) {
										{
											if(!fixedFlag$sample88) {
												double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
												for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
													cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (1.0 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
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
							for(int index$sample75$3 = 0; index$sample75$3 < noStates; index$sample75$3 += 1) {
								int distributionTempVariable$var69$5 = index$sample75$3;
								double cv$probabilitySample75Value4 = (1.0 * distribution$sample75[((index$sample$2 - 0) / 1)][index$sample75$3]);
								int traceTempVariable$var79$6_1 = distributionTempVariable$var69$5;
								if((index$sample$2 == sample)) {
									if((0 == (timeStep$var74 - 1))) {
										for(int var25 = 0; var25 < noStates; var25 += 1) {
											if((var25 == traceTempVariable$var79$6_1)) {
												{
													if(!fixedFlag$sample88) {
														double[] var80 = m[traceTempVariable$var79$6_1];
														for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
															cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (cv$probabilitySample75Value4 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample88) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample)) {
								if((index$timeStep$9_2 == (timeStep$var74 - 1))) {
									for(int var25 = 0; var25 < noStates; var25 += 1) {
										if((var25 == st[sample][(timeStep$var74 - 1)])) {
											{
												if(!fixedFlag$sample88) {
													double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
													for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
														cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (1.0 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
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
								for(int index$sample88$12 = 0; index$sample88$12 < noStates; index$sample88$12 += 1) {
									int distributionTempVariable$var82$14 = index$sample88$12;
									double cv$probabilitySample88Value13 = (1.0 * distribution$sample88[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample88$12]);
									int traceTempVariable$var79$15_1 = distributionTempVariable$var82$14;
									if((index$sample$10 == sample)) {
										if((index$timeStep$11 == (timeStep$var74 - 1))) {
											for(int var25 = 0; var25 < noStates; var25 += 1) {
												if((var25 == traceTempVariable$var79$15_1)) {
													{
														if(!fixedFlag$sample88) {
															double[] var80 = m[traceTempVariable$var79$15_1];
															for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
																cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (cv$probabilitySample88Value13 * (((0.0 <= index$var81) && (index$var81 < var80.length))?var80[index$var81]:0.0)));
														}
													}
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
					if(!fixedFlag$sample88)
						cv$var81$sum = (cv$var81$sum + cv$distribution$sample88[index$var81]);
				}
				for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1) {
					if(!fixedFlag$sample88)
						cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] / cv$var81$sum);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var25 = 0; var25 < noStates; var25 += 1) {
			double[] var26 = m[var25];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, var26);
		}
		for(int var37 = 0; var37 < noStates; var37 += 1) {
			if(!fixedFlag$sample42)
				metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var46 = 0; var46 < noStates; var46 += 1) {
			if(!fixedFlag$sample51)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			if(!fixedFlag$sample60)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var66 = st[sample];
			if(!fixedFlag$sample75)
				var66[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var75 = st[sample];
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
				if(!fixedFlag$sample88)
					var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var74 - 1)]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample23)
				sample23();
			for(int var25 = 0; var25 < noStates; var25 += 1) {
				if(!fixedFlag$sample29)
					sample29(var25);
			}
			for(int var37 = 0; var37 < noStates; var37 += 1) {
				if(!fixedFlag$sample42)
					sample42(var37);
			}
			for(int var46 = 0; var46 < noStates; var46 += 1) {
				if(!fixedFlag$sample51)
					sample51(var46);
			}
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				if(!fixedFlag$sample60)
					sample60(var55);
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(!fixedFlag$sample75)
					sample75(sample);
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					if(!fixedFlag$sample88)
						sample88(sample, timeStep$var74);
				}
			}
		} else {
			for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
				for(int timeStep$var74 = (length$metric[sample] - ((((length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var74 >= ((1 - 1) + 1); timeStep$var74 -= 1) {
					if(!fixedFlag$sample88)
						sample88(sample, timeStep$var74);
				}
				if(!fixedFlag$sample75)
					sample75(sample);
			}
			for(int var55 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var55 >= ((0 - 1) + 1); var55 -= 1) {
				if(!fixedFlag$sample60)
					sample60(var55);
			}
			for(int var46 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var46 >= ((0 - 1) + 1); var46 -= 1) {
				if(!fixedFlag$sample51)
					sample51(var46);
			}
			for(int var37 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var37 >= ((0 - 1) + 1); var37 -= 1) {
				if(!fixedFlag$sample42)
					sample42(var37);
			}
			for(int var25 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var25 >= ((0 - 1) + 1); var25 -= 1) {
				if(!fixedFlag$sample29)
					sample29(var25);
			}
			if(!fixedFlag$sample23)
				sample23();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var16 = 0; var16 < noStates; var16 += 1)
			v[var16] = 0.1;
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
		if(!fixedProbFlag$sample42)
			logProbability$var38 = 0.0;
		logProbability$var42 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$var47 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample60)
			logProbability$var56 = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1)
			logProbability$var68[((sample - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample75[((sample - 0) / 1)] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
				logProbability$var81[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample88) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					logProbability$sample88[((sample - 0) / 1)][((timeStep$var74 - 1) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var95[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample104[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var102[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
		}
		logProbability$metric_1d = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample113) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample113[((sample - 0) / 1)][((timeStep$var91 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample60)
			logProbabilityValue$sample60();
		logProbabilityValue$sample104();
		logProbabilityValue$sample113();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample23();
		logProbabilityValue$sample29();
		logProbabilityValue$sample42();
		logProbabilityValue$sample51();
		logProbabilityValue$sample60();
		logProbabilityDistribution$sample75();
		logProbabilityDistribution$sample88();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample113();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample23();
		logProbabilityValue$sample29();
		logProbabilityValue$sample42();
		logProbabilityValue$sample51();
		logProbabilityValue$sample60();
		logProbabilityValue$sample75();
		logProbabilityValue$sample88();
		logProbabilityValue$sample104();
		logProbabilityValue$sample113();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var25 = 0; var25 < noStates; var25 += 1) {
			double[] var26 = m[var25];
			if(!fixedFlag$sample29)
				DistributionSampling.sampleDirichlet(RNG$, v, var26);
		}
		for(int var37 = 0; var37 < noStates; var37 += 1) {
			if(!fixedFlag$sample42)
				metric_mean[var37] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var46 = 0; var46 < noStates; var46 += 1) {
			if(!fixedFlag$sample51)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			if(!fixedFlag$sample60)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var66 = st[sample];
			if(!fixedFlag$sample75)
				var66[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var75 = st[sample];
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
				if(!fixedFlag$sample88)
					var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var74 - 1)]]);
			}
		}
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