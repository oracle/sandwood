package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics4$CoreInterface {
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[] cv$distributionAccumulator$var56;
	private double[] cv$var21$countGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var44$stateProbabilityGlobal;
	private double[] cv$var57$stateProbabilityGlobal;
	private double[][] distribution$sample48;
	private double[][][] distribution$sample61;
	private boolean fixedFlag$sample106 = false;
	private boolean fixedFlag$sample120 = false;
	private boolean fixedFlag$sample152 = false;
	private boolean fixedFlag$sample164 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample92 = false;
	private boolean fixedProbFlag$sample106 = false;
	private boolean fixedProbFlag$sample120 = false;
	private boolean fixedProbFlag$sample152 = false;
	private boolean fixedProbFlag$sample164 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample92 = false;
	private boolean[][][] guard$sample48gaussian163$global;
	private boolean[][][] guard$sample61gaussian163$global;
	private double[] initialStateDistribution;
	private int[][] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$current_metric_mean;
	private double logProbability$current_metric_valid_bias;
	private double logProbability$current_metric_var;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_g;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_valid_inner;
	private double[][][] logProbability$sample152;
	private double[][][] logProbability$sample164;
	private double[] logProbability$sample48;
	private double[][] logProbability$sample61;
	private double logProbability$st;
	private double logProbability$var101;
	private double logProbability$var106;
	private double logProbability$var115;
	private double logProbability$var131;
	private double[][][] logProbability$var145;
	private double[][][] logProbability$var155;
	private double logProbability$var20;
	private double logProbability$var22;
	private double logProbability$var27;
	private double[] logProbability$var43;
	private double[][] logProbability$var56;
	private double logProbability$var78;
	private double logProbability$var87;
	private double logProbability$var92;
	private double[][] m;
	private int max_metric;
	private double[][][] metric;
	private double[][][] metric_g;
	private boolean[][][] metric_valid;
	private boolean[][][] metric_valid_g;
	private int noSamples;
	private int noServers;
	private int noStates;
	private boolean setFlag$current_metric_mean = false;
	private boolean setFlag$current_metric_valid_bias = false;
	private boolean setFlag$current_metric_var = false;
	private boolean setFlag$initialStateDistribution = false;
	private boolean setFlag$m = false;
	private boolean setFlag$metric_g = false;
	private boolean setFlag$metric_valid_g = false;
	private boolean setFlag$st = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics4$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$current_metric_mean() {
		return current_metric_mean;
	}

	@Override
	public final void set$current_metric_mean(double[][] cv$value) {
		current_metric_mean = cv$value;
		setFlag$current_metric_mean = true;
	}

	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value) {
		current_metric_valid_bias = cv$value;
		setFlag$current_metric_valid_bias = true;
	}

	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	@Override
	public final void set$current_metric_var(double[][] cv$value) {
		current_metric_var = cv$value;
		setFlag$current_metric_var = true;
	}

	@Override
	public final boolean get$fixedFlag$sample106() {
		return fixedFlag$sample106;
	}

	@Override
	public final void set$fixedFlag$sample106(boolean cv$value) {
		fixedFlag$sample106 = cv$value;
		fixedProbFlag$sample106 = (fixedFlag$sample106 && fixedProbFlag$sample106);
		fixedProbFlag$sample164 = (fixedFlag$sample106 && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample120() {
		return fixedFlag$sample120;
	}

	@Override
	public final void set$fixedFlag$sample120(boolean cv$value) {
		fixedFlag$sample120 = cv$value;
		fixedProbFlag$sample120 = (fixedFlag$sample120 && fixedProbFlag$sample120);
		fixedProbFlag$sample152 = (fixedFlag$sample120 && fixedProbFlag$sample152);
	}

	@Override
	public final boolean get$fixedFlag$sample152() {
		return fixedFlag$sample152;
	}

	@Override
	public final void set$fixedFlag$sample152(boolean cv$value) {
		fixedFlag$sample152 = cv$value;
		fixedProbFlag$sample152 = (fixedFlag$sample152 && fixedProbFlag$sample152);
	}

	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	@Override
	public final void set$fixedFlag$sample164(boolean cv$value) {
		fixedFlag$sample164 = cv$value;
		fixedProbFlag$sample164 = (fixedFlag$sample164 && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		fixedProbFlag$sample48 = (fixedFlag$sample24 && fixedProbFlag$sample48);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		fixedProbFlag$sample61 = (fixedFlag$sample30 && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedProbFlag$sample48);
		fixedProbFlag$sample61 = (fixedFlag$sample48 && fixedProbFlag$sample61);
		fixedProbFlag$sample152 = (fixedFlag$sample48 && fixedProbFlag$sample152);
		fixedProbFlag$sample164 = (fixedFlag$sample48 && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (fixedFlag$sample61 && fixedProbFlag$sample61);
		fixedProbFlag$sample152 = (fixedFlag$sample61 && fixedProbFlag$sample152);
		fixedProbFlag$sample164 = (fixedFlag$sample61 && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample92() {
		return fixedFlag$sample92;
	}

	@Override
	public final void set$fixedFlag$sample92(boolean cv$value) {
		fixedFlag$sample92 = cv$value;
		fixedProbFlag$sample92 = (fixedFlag$sample92 && fixedProbFlag$sample92);
		fixedProbFlag$sample164 = (fixedFlag$sample92 && fixedProbFlag$sample164);
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
	public final int[][] get$length$metric() {
		return length$metric;
	}

	@Override
	public final void set$length$metric(int[][] cv$value) {
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
	public final double get$logProbability$current_metric_mean() {
		return logProbability$current_metric_mean;
	}

	@Override
	public final double get$logProbability$current_metric_valid_bias() {
		return logProbability$current_metric_valid_bias;
	}

	@Override
	public final double get$logProbability$current_metric_var() {
		return logProbability$current_metric_var;
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
	public final double get$logProbability$metric_valid_g() {
		return logProbability$metric_valid_g;
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
	public final int get$max_metric() {
		return max_metric;
	}

	@Override
	public final void set$max_metric(int cv$value) {
		max_metric = cv$value;
	}

	@Override
	public final double[][][] get$metric() {
		return metric;
	}

	@Override
	public final void set$metric(double[][][] cv$value) {
		metric = cv$value;
	}

	@Override
	public final double[][][] get$metric_g() {
		return metric_g;
	}

	@Override
	public final void set$metric_g(double[][][] cv$value) {
		metric_g = cv$value;
		setFlag$metric_g = true;
	}

	@Override
	public final boolean[][][] get$metric_valid() {
		return metric_valid;
	}

	@Override
	public final void set$metric_valid(boolean[][][] cv$value) {
		metric_valid = cv$value;
	}

	@Override
	public final boolean[][][] get$metric_valid_g() {
		return metric_valid_g;
	}

	@Override
	public final void set$metric_valid_g(boolean[][][] cv$value) {
		metric_valid_g = cv$value;
		setFlag$metric_valid_g = true;
	}

	@Override
	public final int get$noSamples() {
		return noSamples;
	}

	@Override
	public final int get$noServers() {
		return noServers;
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

	private final void logProbabilityDistribution$sample152() {
		if(!fixedProbFlag$sample152) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
							if(fixedFlag$sample48) {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									if((sample$var32 == sample$var120)) {
										if((0 == timeStep$var140)) {
											for(int var114 = 0; var114 < noStates; var114 += 1) {
												for(int var110 = 0; var110 < noServers; var110 += 1) {
													if((var110 == server)) {
														if((var114 == st[sample$var120][timeStep$var140])) {
															{
																double var144 = current_metric_valid_bias[server][st[sample$var120][timeStep$var140]];
																double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
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
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									if(true) {
										for(int index$sample48$4 = 0; index$sample48$4 < noStates; index$sample48$4 += 1) {
											int distributionTempVariable$var44$6 = index$sample48$4;
											double cv$probabilitySample48Value5 = (1.0 * distribution$sample48[((sample$var32 - 0) / 1)][index$sample48$4]);
											int traceTempVariable$currentState$7_1 = distributionTempVariable$var44$6;
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													for(int var114 = 0; var114 < noStates; var114 += 1) {
														for(int var110 = 0; var110 < noServers; var110 += 1) {
															if((var110 == server)) {
																if((var114 == traceTempVariable$currentState$7_1)) {
																	{
																		double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$7_1];
																		double cv$weightedProbability = (Math.log(cv$probabilitySample48Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value5);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample61) {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
										if((sample$var32 == sample$var120)) {
											if((timeStep$var49 == timeStep$var140)) {
												for(int var114 = 0; var114 < noStates; var114 += 1) {
													for(int var110 = 0; var110 < noServers; var110 += 1) {
														if((var110 == server)) {
															if((var114 == st[sample$var120][timeStep$var140])) {
																{
																	double var144 = current_metric_valid_bias[server][st[sample$var120][timeStep$var140]];
																	double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
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
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
										if(true) {
											for(int index$sample61$13 = 0; index$sample61$13 < noStates; index$sample61$13 += 1) {
												int distributionTempVariable$var57$15 = index$sample61$13;
												double cv$probabilitySample61Value14 = (1.0 * distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$13]);
												int traceTempVariable$currentState$16_1 = distributionTempVariable$var57$15;
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														for(int var114 = 0; var114 < noStates; var114 += 1) {
															for(int var110 = 0; var110 < noServers; var110 += 1) {
																if((var110 == server)) {
																	if((var114 == traceTempVariable$currentState$16_1)) {
																		{
																			double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$16_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample61Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value14);
																		}
																	}
																}
															}
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
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample152[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
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
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample152 = (((fixedFlag$sample152 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample120);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample152[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
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
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample164() {
		if(!fixedProbFlag$sample164) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
								if(fixedFlag$sample48) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												for(int var86 = 0; var86 < noStates; var86 += 1) {
													for(int var82 = 0; var82 < noServers; var82 += 1) {
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																			if((index$sample$10_1 == sample$var120)) {
																				if((0 == timeStep$var140)) {
																					for(int var100 = 0; var100 < noStates; var100 += 1) {
																						for(int var96 = 0; var96 < noServers; var96 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var96 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var100 == st[sample$var120][timeStep$var140])) {
																											{
																												double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																												double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																												double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
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
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if(true) {
											for(int index$sample48$4 = 0; index$sample48$4 < noStates; index$sample48$4 += 1) {
												int distributionTempVariable$var44$6 = index$sample48$4;
												double cv$probabilitySample48Value5 = (1.0 * distribution$sample48[((sample$var32 - 0) / 1)][index$sample48$4]);
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var44$6;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$7_1)) {
																				int traceTempVariable$currentState$11_1 = distributionTempVariable$var44$6;
																				if((sample$var32 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$11_1)) {
																												{
																													double var152 = current_metric_mean[server][traceTempVariable$currentState$11_1];
																													double var154 = current_metric_var[server][traceTempVariable$currentState$11_1];
																													double cv$weightedProbability = (Math.log(cv$probabilitySample48Value5) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																													if((cv$weightedProbability < cv$distributionAccumulator))
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																													else {
																														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																															cv$distributionAccumulator = cv$weightedProbability;
																														else
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																													}
																													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value5);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																					if(!(index$sample$12 == sample$var32)) {
																						for(int index$sample48$13 = 0; index$sample48$13 < noStates; index$sample48$13 += 1) {
																							int distributionTempVariable$var44$15 = index$sample48$13;
																							double cv$probabilitySample48Value14 = (cv$probabilitySample48Value5 * distribution$sample48[((index$sample$12 - 0) / 1)][index$sample48$13]);
																							int traceTempVariable$currentState$16_1 = distributionTempVariable$var44$15;
																							if((index$sample$12 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$16_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample48Value14) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value14);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample48) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												for(int var86 = 0; var86 < noStates; var86 += 1) {
													for(int var82 = 0; var82 < noServers; var82 += 1) {
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		if(fixedFlag$sample61) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$28_1][0]; timeStep$var49 += 1) {
																					if((index$sample$28_1 == sample$var120)) {
																						if((timeStep$var49 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
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
																		} else {
																			for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$29][0]; timeStep$var49 += 1) {
																					if(true) {
																						for(int index$sample61$31 = 0; index$sample61$31 < noStates; index$sample61$31 += 1) {
																							int distributionTempVariable$var57$33 = index$sample61$31;
																							double cv$probabilitySample61Value32 = (1.0 * distribution$sample61[((index$sample$29 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var57$33;
																							if((index$sample$29 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$34_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if(true) {
											for(int index$sample48$22 = 0; index$sample48$22 < noStates; index$sample48$22 += 1) {
												int distributionTempVariable$var44$24 = index$sample48$22;
												double cv$probabilitySample48Value23 = (1.0 * distribution$sample48[((sample$var32 - 0) / 1)][index$sample48$22]);
												int traceTempVariable$currentState$25_1 = distributionTempVariable$var44$24;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$25_1)) {
																				if(fixedFlag$sample61) {
																					for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$35_1][0]; timeStep$var49 += 1) {
																							if((index$sample$35_1 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$25_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample48Value23) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value23);
																															}
																														}
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
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$36][0]; timeStep$var49 += 1) {
																							if(true) {
																								for(int index$sample61$38 = 0; index$sample61$38 < noStates; index$sample61$38 += 1) {
																									int distributionTempVariable$var57$40 = index$sample61$38;
																									double cv$probabilitySample61Value39 = (cv$probabilitySample48Value23 * distribution$sample61[((index$sample$36 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$38]);
																									int traceTempVariable$currentState$41_1 = distributionTempVariable$var57$40;
																									if((index$sample$36 == sample$var120)) {
																										if((timeStep$var49 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$41_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$41_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$41_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample61Value39) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value39);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample61) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int var86 = 0; var86 < noStates; var86 += 1) {
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == st[sample$var120][timeStep$var140])) {
																			for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																				for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																					if((index$sample$55_1 == sample$var120)) {
																						if((index$timeStep$55_2 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
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
												}
											}
										}
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if(true) {
												for(int index$sample61$49 = 0; index$sample61$49 < noStates; index$sample61$49 += 1) {
													int distributionTempVariable$var57$51 = index$sample61$49;
													double cv$probabilitySample61Value50 = (1.0 * distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$49]);
													int traceTempVariable$currentState$52_1 = distributionTempVariable$var57$51;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																for(int var82 = 0; var82 < noServers; var82 += 1) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var82 == server)) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var86 == traceTempVariable$currentState$52_1)) {
																					int traceTempVariable$currentState$56_1 = distributionTempVariable$var57$51;
																					if((sample$var32 == sample$var120)) {
																						if((timeStep$var49 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == traceTempVariable$currentState$56_1)) {
																													{
																														double var152 = current_metric_mean[server][traceTempVariable$currentState$56_1];
																														double var154 = current_metric_var[server][traceTempVariable$currentState$56_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample61Value50) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value50);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																						for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57][0]; index$timeStep$58 += 1) {
																							if(!((index$sample$57 == sample$var32) && (index$timeStep$58 == timeStep$var49))) {
																								for(int index$sample61$59 = 0; index$sample61$59 < noStates; index$sample61$59 += 1) {
																									int distributionTempVariable$var57$61 = index$sample61$59;
																									double cv$probabilitySample61Value60 = (cv$probabilitySample61Value50 * distribution$sample61[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample61$59]);
																									int traceTempVariable$currentState$62_1 = distributionTempVariable$var57$61;
																									if((index$sample$57 == sample$var120)) {
																										if((index$timeStep$58 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$62_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$62_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$62_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample61Value60) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value60);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample61) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int var86 = 0; var86 < noStates; var86 += 1) {
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == st[sample$var120][timeStep$var140])) {
																			if(fixedFlag$sample48) {
																				for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																					if((index$sample$75_1 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
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
																			} else {
																				for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																					if(true) {
																						for(int index$sample48$77 = 0; index$sample48$77 < noStates; index$sample48$77 += 1) {
																							int distributionTempVariable$var44$79 = index$sample48$77;
																							double cv$probabilitySample48Value78 = (1.0 * distribution$sample48[((index$sample$76 - 0) / 1)][index$sample48$77]);
																							int traceTempVariable$currentState$80_1 = distributionTempVariable$var44$79;
																							if((index$sample$76 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$80_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample48Value78) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value78);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if(true) {
												for(int index$sample61$69 = 0; index$sample61$69 < noStates; index$sample61$69 += 1) {
													int distributionTempVariable$var57$71 = index$sample61$69;
													double cv$probabilitySample61Value70 = (1.0 * distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$69]);
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var57$71;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																for(int var82 = 0; var82 < noServers; var82 += 1) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var82 == server)) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var86 == traceTempVariable$currentState$72_1)) {
																					if(fixedFlag$sample48) {
																						for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																							if((index$sample$81_1 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$72_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample61Value70) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value70);
																															}
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
																						for(int index$sample$82 = 0; index$sample$82 < noSamples; index$sample$82 += 1) {
																							if(true) {
																								for(int index$sample48$83 = 0; index$sample48$83 < noStates; index$sample48$83 += 1) {
																									int distributionTempVariable$var44$85 = index$sample48$83;
																									double cv$probabilitySample48Value84 = (cv$probabilitySample61Value70 * distribution$sample48[((index$sample$82 - 0) / 1)][index$sample48$83]);
																									int traceTempVariable$currentState$86_1 = distributionTempVariable$var44$85;
																									if((index$sample$82 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$86_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$86_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$86_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample48Value84) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value84);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
							logProbability$sample164[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
							boolean cv$guard$metric_g = false;
							{
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
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
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample164 = ((((fixedFlag$sample164 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample92) && fixedFlag$sample106);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample164[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
							boolean cv$guard$metric_g = false;
							{
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
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
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample48() {
		if(!fixedProbFlag$sample48) {
			if(fixedFlag$sample48) {
				double cv$accumulator = 0.0;
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample$var32;
					{
						int cv$sampleValue = st[sample$var32][0];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, initialStateDistribution));
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
					logProbability$var43[((sample$var32 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample48[((sample$var32 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample48)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample48)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample24);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample48[((sample$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample48)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample61() {
		if(!fixedProbFlag$sample61) {
			if(fixedFlag$sample61) {
				double cv$accumulator = 0.0;
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var49;
						int index$sample$2 = sample$var32;
						{
							int cv$sampleValue = st[sample$var32][timeStep$var49];
							if(fixedFlag$sample48) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample$var32)) {
										if((0 == (timeStep$var49 - 1))) {
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
													{
														double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var55));
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
										for(int index$sample48$6 = 0; index$sample48$6 < noStates; index$sample48$6 += 1) {
											int distributionTempVariable$var44$8 = index$sample48$6;
											double cv$probabilitySample48Value7 = (1.0 * distribution$sample48[((index$sample$5 - 0) / 1)][index$sample48$6]);
											int traceTempVariable$var54$9_1 = distributionTempVariable$var44$8;
											if((index$sample$5 == sample$var32)) {
												if((0 == (timeStep$var49 - 1))) {
													for(int var26 = 0; var26 < noStates; var26 += 1) {
														if((var26 == traceTempVariable$var54$9_1)) {
															{
																double[] var55 = m[traceTempVariable$var54$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var55));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample48Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							int traceTempVariable$var54$12_1 = DistributionSampling.sampleCategorical(RNG$, m[st[index$sample$2][(index$timeStep$1 - 1)]]);
							if((index$sample$2 == sample$var32)) {
								if((index$timeStep$1 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$12_1)) {
											{
												double[] var55 = m[traceTempVariable$var54$12_1];
												double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var55));
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
							if(fixedFlag$sample61) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var32)) {
											if((index$timeStep$13_2 == (timeStep$var49 - 1))) {
												for(int var26 = 0; var26 < noStates; var26 += 1) {
													if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
														{
															double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var55));
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
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
										if(!((index$sample$14 == index$sample$2) && (index$timeStep$15 == index$timeStep$1))) {
											for(int index$sample61$16 = 0; index$sample61$16 < noStates; index$sample61$16 += 1) {
												int distributionTempVariable$var57$18 = index$sample61$16;
												double cv$probabilitySample61Value17 = (1.0 * distribution$sample61[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample61$16]);
												int traceTempVariable$var54$19_1 = distributionTempVariable$var57$18;
												if((index$sample$14 == sample$var32)) {
													if((index$timeStep$15 == (timeStep$var49 - 1))) {
														for(int var26 = 0; var26 < noStates; var26 += 1) {
															if((var26 == traceTempVariable$var54$19_1)) {
																{
																	double[] var55 = m[traceTempVariable$var54$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample61Value17) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var55));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value17);
																}
															}
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
						logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample61)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample61)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample30) && fixedFlag$sample48);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample61)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample106() {
		if(!fixedProbFlag$sample106) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_var[var96][var100];
						{
							{
								double var90 = 1.0;
								double var91 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var90, var91));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var92 = cv$sampleAccumulator;
			logProbability$var101 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample106)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample106 = fixedFlag$sample106;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var101;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var92 = cv$rvAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample106)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample120() {
		if(!fixedProbFlag$sample120) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_valid_bias[var110][var114];
						{
							{
								double var104 = 1.0;
								double var105 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var104, var105));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var106 = cv$sampleAccumulator;
			logProbability$var115 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample120)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample120 = fixedFlag$sample120;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var115;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var106 = cv$rvAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample120)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample152() {
		if(!fixedProbFlag$sample152) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
							{
								{
									double var144 = current_metric_valid_bias[server][st[sample$var120][timeStep$var140]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
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
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample152[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
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
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample152 = (((fixedFlag$sample152 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample120);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample152[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
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
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample164() {
		if(!fixedProbFlag$sample164) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
								{
									{
										double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
										double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(cv$sampleValue, var152, var154));
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
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleAccumulator;
							logProbability$sample164[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
							boolean cv$guard$metric_g = false;
							{
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
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
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample164 = ((((fixedFlag$sample164 && fixedFlag$sample48) && fixedFlag$sample61) && fixedFlag$sample92) && fixedFlag$sample106);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample164[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
							boolean cv$guard$metric_g = false;
							{
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
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
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			logProbability$var131 = (logProbability$var131 + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noStates; var26 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var26];
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
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var27;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var22 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample$var32;
				{
					int cv$sampleValue = st[sample$var32][0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, initialStateDistribution));
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
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample48[((sample$var32 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample24);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample48[((sample$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var49;
					int index$sample$2 = sample$var32;
					{
						int cv$sampleValue = st[sample$var32][timeStep$var49];
						{
							{
								double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$sampleValue, var55));
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
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample30) && fixedFlag$sample48);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample92() {
		if(!fixedProbFlag$sample92) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_mean[var82][var86];
						{
							{
								double var76 = 0.0;
								double var77 = (double)max_metric;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityUniform(cv$sampleValue, var76, var77));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var78 = cv$sampleAccumulator;
			logProbability$var87 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample92 = fixedFlag$sample92;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var87;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var78 = cv$rvAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample106(int var96, int var100) {
		double cv$originalValue = current_metric_var[var96][var100];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
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
					double var101 = cv$proposedValue;
					double[] var97 = current_metric_var[var96];
					var97[var100] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var90;
				{
					cv$temp$0$var90 = 1.0;
				}
				double cv$temp$1$var91;
				{
					cv$temp$1$var91 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var90, cv$temp$1$var91));
				{
					{
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample48) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													double traceTempVariable$var154$10_1 = cv$currentValue;
													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
														if((var96 == server)) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var100 == st[sample$var120][timeStep$var140])) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				if((index$sample$28_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var82 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var86 == st[sample$var120][timeStep$var140])) {
																												{
																													{
																														double cv$temp$2$var152;
																														{
																															double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																															cv$temp$2$var152 = var152;
																														}
																														double cv$temp$3$var154;
																														{
																															double var154 = traceTempVariable$var154$10_1;
																															cv$temp$3$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$30$var152;
																														{
																															double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																															cv$temp$30$var152 = var152;
																														}
																														double cv$temp$31$var154;
																														{
																															double var154 = traceTempVariable$var154$10_1;
																															cv$temp$31$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$58$var152;
																														{
																															double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																															cv$temp$58$var152 = var152;
																														}
																														double cv$temp$59$var154;
																														{
																															double var154 = traceTempVariable$var154$10_1;
																															cv$temp$59$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)));
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
																			if(fixedFlag$sample61) {
																				for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30_1][0]; timeStep$var49 += 1) {
																						if((index$sample$30_1 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$4$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$4$var152 = var152;
																																}
																																double cv$temp$5$var154;
																																{
																																	double var154 = traceTempVariable$var154$10_1;
																																	cv$temp$5$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$32$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$32$var152 = var152;
																																}
																																double cv$temp$33$var154;
																																{
																																	double var154 = traceTempVariable$var154$10_1;
																																	cv$temp$33$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$60$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$60$var152 = var152;
																																}
																																double cv$temp$61$var154;
																																{
																																	double var154 = traceTempVariable$var154$10_1;
																																	cv$temp$61$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)));
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
																				for(int index$sample$31 = 0; index$sample$31 < noSamples; index$sample$31 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$31][0]; timeStep$var49 += 1) {
																						if(true) {
																							for(int index$sample61$33 = 0; index$sample61$33 < noStates; index$sample61$33 += 1) {
																								int distributionTempVariable$var57$35 = index$sample61$33;
																								double cv$probabilitySample61Value34 = (1.0 * distribution$sample61[((index$sample$31 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$33]);
																								int traceTempVariable$currentState$36_1 = distributionTempVariable$var57$35;
																								if((index$sample$31 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$36_1)) {
																																{
																																	{
																																		double cv$temp$6$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$36_1];
																																			cv$temp$6$var152 = var152;
																																		}
																																		double cv$temp$7$var154;
																																		{
																																			double var154 = traceTempVariable$var154$10_1;
																																			cv$temp$7$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value34);
																																	}
																																	{
																																		double cv$temp$34$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$36_1];
																																			cv$temp$34$var152 = var152;
																																		}
																																		double cv$temp$35$var154;
																																		{
																																			double var154 = traceTempVariable$var154$10_1;
																																			cv$temp$35$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value34);
																																	}
																																	{
																																		double cv$temp$62$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$36_1];
																																			cv$temp$62$var152 = var152;
																																		}
																																		double cv$temp$63$var154;
																																		{
																																			double var154 = traceTempVariable$var154$10_1;
																																			cv$temp$63$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value34);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if(true) {
												for(int index$sample48$6 = 0; index$sample48$6 < noStates; index$sample48$6 += 1) {
													int distributionTempVariable$var44$8 = index$sample48$6;
													double cv$probabilitySample48Value7 = (1.0 * distribution$sample48[((sample$var32 - 0) / 1)][index$sample48$6]);
													int traceTempVariable$currentState$9_1 = distributionTempVariable$var44$8;
													if((sample$var32 == sample$var120)) {
														if((0 == timeStep$var140)) {
															double traceTempVariable$var154$11_1 = cv$currentValue;
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var96 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var100 == traceTempVariable$currentState$9_1)) {
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					int traceTempVariable$currentState$39_1 = distributionTempVariable$var44$8;
																					if((sample$var32 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$39_1)) {
																													{
																														{
																															double cv$temp$8$var152;
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$39_1];
																																cv$temp$8$var152 = var152;
																															}
																															double cv$temp$9$var154;
																															{
																																double var154 = traceTempVariable$var154$11_1;
																																cv$temp$9$var154 = var154;
																															}
																															if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																														}
																														{
																															double cv$temp$36$var152;
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$39_1];
																																cv$temp$36$var152 = var152;
																															}
																															double cv$temp$37$var154;
																															{
																																double var154 = traceTempVariable$var154$11_1;
																																cv$temp$37$var154 = var154;
																															}
																															if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																														}
																														{
																															double cv$temp$64$var152;
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$39_1];
																																cv$temp$64$var152 = var152;
																															}
																															double cv$temp$65$var154;
																															{
																																double var154 = traceTempVariable$var154$11_1;
																																cv$temp$65$var154 = var154;
																															}
																															if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$40 = 0; index$sample$40 < noSamples; index$sample$40 += 1) {
																						if(!(index$sample$40 == sample$var32)) {
																							for(int index$sample48$41 = 0; index$sample48$41 < noStates; index$sample48$41 += 1) {
																								int distributionTempVariable$var44$43 = index$sample48$41;
																								double cv$probabilitySample48Value42 = (cv$probabilitySample48Value7 * distribution$sample48[((index$sample$40 - 0) / 1)][index$sample48$41]);
																								int traceTempVariable$currentState$44_1 = distributionTempVariable$var44$43;
																								if((index$sample$40 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$44_1)) {
																																{
																																	{
																																		double cv$temp$10$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$44_1];
																																			cv$temp$10$var152 = var152;
																																		}
																																		double cv$temp$11$var154;
																																		{
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$11$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value42);
																																	}
																																	{
																																		double cv$temp$38$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$44_1];
																																			cv$temp$38$var152 = var152;
																																		}
																																		double cv$temp$39$var154;
																																		{
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$39$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value42);
																																	}
																																	{
																																		double cv$temp$66$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$44_1];
																																			cv$temp$66$var152 = var152;
																																		}
																																		double cv$temp$67$var154;
																																		{
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$67$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value42);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					if(fixedFlag$sample61) {
																						for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																								if((index$sample$47_1 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$9_1)) {
																																{
																																	{
																																		double cv$temp$12$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$9_1];
																																			cv$temp$12$var152 = var152;
																																		}
																																		double cv$temp$13$var154;
																																		{
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$13$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																																	}
																																	{
																																		double cv$temp$40$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$9_1];
																																			cv$temp$40$var152 = var152;
																																		}
																																		double cv$temp$41$var154;
																																		{
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$41$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																																	}
																																	{
																																		double cv$temp$68$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$9_1];
																																			cv$temp$68$var152 = var152;
																																		}
																																		double cv$temp$69$var154;
																																		{
																																			double var154 = traceTempVariable$var154$11_1;
																																			cv$temp$69$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																																	}
																																}
																															}
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
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48][0]; timeStep$var49 += 1) {
																								if(true) {
																									for(int index$sample61$50 = 0; index$sample61$50 < noStates; index$sample61$50 += 1) {
																										int distributionTempVariable$var57$52 = index$sample61$50;
																										double cv$probabilitySample61Value51 = (cv$probabilitySample48Value7 * distribution$sample61[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$50]);
																										int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																										if((index$sample$48 == sample$var120)) {
																											if((timeStep$var49 == timeStep$var140)) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													for(int var82 = 0; var82 < noServers; var82 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var82 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var86 == traceTempVariable$currentState$53_1)) {
																																		{
																																			{
																																				double cv$temp$14$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																					cv$temp$14$var152 = var152;
																																				}
																																				double cv$temp$15$var154;
																																				{
																																					double var154 = traceTempVariable$var154$11_1;
																																					cv$temp$15$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																			}
																																			{
																																				double cv$temp$42$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																					cv$temp$42$var152 = var152;
																																				}
																																				double cv$temp$43$var154;
																																				{
																																					double var154 = traceTempVariable$var154$11_1;
																																					cv$temp$43$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																			}
																																			{
																																				double cv$temp$70$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																					cv$temp$70$var152 = var152;
																																				}
																																				double cv$temp$71$var154;
																																				{
																																					double var154 = traceTempVariable$var154$11_1;
																																					cv$temp$71$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample61) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														double traceTempVariable$var154$22_1 = cv$currentValue;
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var96 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var100 == st[sample$var120][timeStep$var140])) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				if(fixedFlag$sample48) {
																					for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																						if((index$sample$56_1 == sample$var120)) {
																							if((0 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$16$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$16$var152 = var152;
																																}
																																double cv$temp$17$var154;
																																{
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$17$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$44$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$44$var152 = var152;
																																}
																																double cv$temp$45$var154;
																																{
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$45$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$72$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$72$var152 = var152;
																																}
																																double cv$temp$73$var154;
																																{
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$73$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)));
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
																					for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																						if(true) {
																							for(int index$sample48$58 = 0; index$sample48$58 < noStates; index$sample48$58 += 1) {
																								int distributionTempVariable$var44$60 = index$sample48$58;
																								double cv$probabilitySample48Value59 = (1.0 * distribution$sample48[((index$sample$57 - 0) / 1)][index$sample48$58]);
																								int traceTempVariable$currentState$61_1 = distributionTempVariable$var44$60;
																								if((index$sample$57 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$61_1)) {
																																{
																																	{
																																		double cv$temp$18$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$61_1];
																																			cv$temp$18$var152 = var152;
																																		}
																																		double cv$temp$19$var154;
																																		{
																																			double var154 = traceTempVariable$var154$22_1;
																																			cv$temp$19$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value59);
																																	}
																																	{
																																		double cv$temp$46$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$61_1];
																																			cv$temp$46$var152 = var152;
																																		}
																																		double cv$temp$47$var154;
																																		{
																																			double var154 = traceTempVariable$var154$22_1;
																																			cv$temp$47$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value59);
																																	}
																																	{
																																		double cv$temp$74$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$61_1];
																																			cv$temp$74$var152 = var152;
																																		}
																																		double cv$temp$75$var154;
																																		{
																																			double var154 = traceTempVariable$var154$22_1;
																																			cv$temp$75$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value59);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																					for(int index$timeStep$64_2 = 1; index$timeStep$64_2 < length$metric[index$sample$64_1][0]; index$timeStep$64_2 += 1) {
																						if((index$sample$64_1 == sample$var120)) {
																							if((index$timeStep$64_2 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$20$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$20$var152 = var152;
																																}
																																double cv$temp$21$var154;
																																{
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$21$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$48$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$48$var152 = var152;
																																}
																																double cv$temp$49$var154;
																																{
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$49$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$76$var152;
																																{
																																	double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$76$var152 = var152;
																																}
																																double cv$temp$77$var154;
																																{
																																	double var154 = traceTempVariable$var154$22_1;
																																	cv$temp$77$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)));
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
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if(true) {
													for(int index$sample61$18 = 0; index$sample61$18 < noStates; index$sample61$18 += 1) {
														int distributionTempVariable$var57$20 = index$sample61$18;
														double cv$probabilitySample61Value19 = (1.0 * distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$18]);
														int traceTempVariable$currentState$21_1 = distributionTempVariable$var57$20;
														if((sample$var32 == sample$var120)) {
															if((timeStep$var49 == timeStep$var140)) {
																double traceTempVariable$var154$23_1 = cv$currentValue;
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var96 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var100 == traceTempVariable$currentState$21_1)) {
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample48) {
																							for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																								if((index$sample$66_1 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$21_1)) {
																																{
																																	{
																																		double cv$temp$22$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$21_1];
																																			cv$temp$22$var152 = var152;
																																		}
																																		double cv$temp$23$var154;
																																		{
																																			double var154 = traceTempVariable$var154$23_1;
																																			cv$temp$23$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																																	}
																																	{
																																		double cv$temp$50$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$21_1];
																																			cv$temp$50$var152 = var152;
																																		}
																																		double cv$temp$51$var154;
																																		{
																																			double var154 = traceTempVariable$var154$23_1;
																																			cv$temp$51$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																																	}
																																	{
																																		double cv$temp$78$var152;
																																		{
																																			double var152 = current_metric_mean[server][traceTempVariable$currentState$21_1];
																																			cv$temp$78$var152 = var152;
																																		}
																																		double cv$temp$79$var154;
																																		{
																																			double var154 = traceTempVariable$var154$23_1;
																																			cv$temp$79$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																																	}
																																}
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
																								if(true) {
																									for(int index$sample48$68 = 0; index$sample48$68 < noStates; index$sample48$68 += 1) {
																										int distributionTempVariable$var44$70 = index$sample48$68;
																										double cv$probabilitySample48Value69 = (cv$probabilitySample61Value19 * distribution$sample48[((index$sample$67 - 0) / 1)][index$sample48$68]);
																										int traceTempVariable$currentState$71_1 = distributionTempVariable$var44$70;
																										if((index$sample$67 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													for(int var82 = 0; var82 < noServers; var82 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var82 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var86 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				double cv$temp$24$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																					cv$temp$24$var152 = var152;
																																				}
																																				double cv$temp$25$var154;
																																				{
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$25$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value69);
																																			}
																																			{
																																				double cv$temp$52$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																					cv$temp$52$var152 = var152;
																																				}
																																				double cv$temp$53$var154;
																																				{
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$53$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value69);
																																			}
																																			{
																																				double cv$temp$80$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																					cv$temp$80$var152 = var152;
																																				}
																																				double cv$temp$81$var154;
																																				{
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$81$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value69);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						int traceTempVariable$currentState$74_1 = distributionTempVariable$var57$20;
																						if((sample$var32 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var82 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var86 == traceTempVariable$currentState$74_1)) {
																														{
																															{
																																double cv$temp$26$var152;
																																{
																																	double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																	cv$temp$26$var152 = var152;
																																}
																																double cv$temp$27$var154;
																																{
																																	double var154 = traceTempVariable$var154$23_1;
																																	cv$temp$27$var154 = var154;
																																}
																																if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																															}
																															{
																																double cv$temp$54$var152;
																																{
																																	double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																	cv$temp$54$var152 = var152;
																																}
																																double cv$temp$55$var154;
																																{
																																	double var154 = traceTempVariable$var154$23_1;
																																	cv$temp$55$var154 = var154;
																																}
																																if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																															}
																															{
																																double cv$temp$82$var152;
																																{
																																	double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																	cv$temp$82$var152 = var152;
																																}
																																double cv$temp$83$var154;
																																{
																																	double var154 = traceTempVariable$var154$23_1;
																																	cv$temp$83$var154 = var154;
																																}
																																if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$75 = 0; index$sample$75 < noSamples; index$sample$75 += 1) {
																							for(int index$timeStep$76 = 1; index$timeStep$76 < length$metric[index$sample$75][0]; index$timeStep$76 += 1) {
																								if(!((index$sample$75 == sample$var32) && (index$timeStep$76 == timeStep$var49))) {
																									for(int index$sample61$77 = 0; index$sample61$77 < noStates; index$sample61$77 += 1) {
																										int distributionTempVariable$var57$79 = index$sample61$77;
																										double cv$probabilitySample61Value78 = (cv$probabilitySample61Value19 * distribution$sample61[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample61$77]);
																										int traceTempVariable$currentState$80_1 = distributionTempVariable$var57$79;
																										if((index$sample$75 == sample$var120)) {
																											if((index$timeStep$76 == timeStep$var140)) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													for(int var82 = 0; var82 < noServers; var82 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var82 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var86 == traceTempVariable$currentState$80_1)) {
																																		{
																																			{
																																				double cv$temp$28$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																					cv$temp$28$var152 = var152;
																																				}
																																				double cv$temp$29$var154;
																																				{
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$29$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value78);
																																			}
																																			{
																																				double cv$temp$56$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																					cv$temp$56$var152 = var152;
																																				}
																																				double cv$temp$57$var154;
																																				{
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$57$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value78);
																																			}
																																			{
																																				double cv$temp$84$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																					cv$temp$84$var152 = var152;
																																				}
																																				double cv$temp$85$var154;
																																				{
																																					double var154 = traceTempVariable$var154$23_1;
																																					cv$temp$85$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value78);
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN(cv$ratio))) {
			double var101 = cv$originalValue;
			double[] var97 = current_metric_var[var96];
			var97[var100] = var101;
		}
	}

	private final void sample120(int var110, int var114) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
								if(fixedFlag$sample48) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												if((var110 == server)) {
													if((var114 == st[sample$var120][timeStep$var140])) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + 1.0);
																			if(metric_valid_g[sample$var120][server][timeStep$var140])
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
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if(true) {
											for(int index$sample48$6 = 0; index$sample48$6 < noStates; index$sample48$6 += 1) {
												int distributionTempVariable$var44$8 = index$sample48$6;
												double cv$probabilitySample48Value7 = (1.0 * distribution$sample48[((sample$var32 - 0) / 1)][index$sample48$6]);
												int traceTempVariable$currentState$9_1 = distributionTempVariable$var44$8;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														if((var110 == server)) {
															if((var114 == traceTempVariable$currentState$9_1)) {
																{
																	{
																		{
																			{
																				{
																					cv$count = (cv$count + cv$probabilitySample48Value7);
																					if(metric_valid_g[sample$var120][server][timeStep$var140])
																						cv$sum = (cv$sum + cv$probabilitySample48Value7);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
								if(fixedFlag$sample61) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													if((var110 == server)) {
														if((var114 == st[sample$var120][timeStep$var140])) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + 1.0);
																				if(metric_valid_g[sample$var120][server][timeStep$var140])
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
									}
								} else {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if(true) {
												for(int index$sample61$18 = 0; index$sample61$18 < noStates; index$sample61$18 += 1) {
													int distributionTempVariable$var57$20 = index$sample61$18;
													double cv$probabilitySample61Value19 = (1.0 * distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$18]);
													int traceTempVariable$currentState$21_1 = distributionTempVariable$var57$20;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															if((var110 == server)) {
																if((var114 == traceTempVariable$currentState$21_1)) {
																	{
																		{
																			{
																				{
																					{
																						cv$count = (cv$count + cv$probabilitySample61Value19);
																						if(metric_valid_g[sample$var120][server][timeStep$var140])
																							cv$sum = (cv$sum + cv$probabilitySample61Value19);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var115 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		double[] var111 = current_metric_valid_bias[var110];
		var111[var114] = var115;
	}

	private final void sample24() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var21$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
						if(fixedFlag$sample48) {
							{
								int index$sample$3 = sample$var32;
								{
									{
										{
											{
												cv$countLocal[st[sample$var32][0]] = (cv$countLocal[st[sample$var32][0]] + 1.0);
											}
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
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					if(!fixedFlag$sample48) {
						{
							int index$sample$7 = sample$var32;
							{
								{
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample48[((sample$var32 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample30(int var26) {
		double[] cv$targetLocal = m[var26];
		double[] cv$countLocal = cv$var27$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
							if(fixedFlag$sample48) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample$var32)) {
										if((0 == (timeStep$var49 - 1))) {
											if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
												if(fixedFlag$sample61) {
													{
														int index$timeStep$23 = timeStep$var49;
														int index$sample$24 = sample$var32;
														{
															{
																{
																	{
																		cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + 1.0);
																	}
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
										for(int index$sample48$5 = 0; index$sample48$5 < noStates; index$sample48$5 += 1) {
											int distributionTempVariable$var44$7 = index$sample48$5;
											double cv$probabilitySample48Value6 = (1.0 * distribution$sample48[((index$sample$4 - 0) / 1)][index$sample48$5]);
											int traceTempVariable$var54$8_1 = distributionTempVariable$var44$7;
											if((index$sample$4 == sample$var32)) {
												if((0 == (timeStep$var49 - 1))) {
													if((var26 == traceTempVariable$var54$8_1)) {
														if(fixedFlag$sample61) {
															{
																int index$timeStep$26 = timeStep$var49;
																int index$sample$27 = sample$var32;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + cv$probabilitySample48Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
							if(fixedFlag$sample61) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var32)) {
											if((index$timeStep$13_2 == (timeStep$var49 - 1))) {
												if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
													if(fixedFlag$sample61) {
														{
															int index$timeStep$29 = timeStep$var49;
															int index$sample$30 = sample$var32;
															{
																{
																	{
																		{
																			cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + 1.0);
																		}
																	}
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
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14][0]; index$timeStep$15 += 1) {
										if(true) {
											for(int index$sample61$16 = 0; index$sample61$16 < noStates; index$sample61$16 += 1) {
												int distributionTempVariable$var57$18 = index$sample61$16;
												double cv$probabilitySample61Value17 = (1.0 * distribution$sample61[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample61$16]);
												int traceTempVariable$var54$19_1 = distributionTempVariable$var57$18;
												if((index$sample$14 == sample$var32)) {
													if((index$timeStep$15 == (timeStep$var49 - 1))) {
														if((var26 == traceTempVariable$var54$19_1)) {
															if(fixedFlag$sample61) {
																{
																	int index$timeStep$32 = timeStep$var49;
																	int index$sample$33 = sample$var32;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + cv$probabilitySample61Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						if(fixedFlag$sample48) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample$var32)) {
									if((0 == (timeStep$var49 - 1))) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											if(!fixedFlag$sample61) {
												{
													int index$timeStep$60 = timeStep$var49;
													int index$sample$61 = sample$var32;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample48$42 = 0; index$sample48$42 < noStates; index$sample48$42 += 1) {
										int distributionTempVariable$var44$44 = index$sample48$42;
										double cv$probabilitySample48Value43 = (1.0 * distribution$sample48[((index$sample$41 - 0) / 1)][index$sample48$42]);
										int traceTempVariable$var54$45_1 = distributionTempVariable$var44$44;
										if((index$sample$41 == sample$var32)) {
											if((0 == (timeStep$var49 - 1))) {
												if((var26 == traceTempVariable$var54$45_1)) {
													if(!fixedFlag$sample61) {
														{
															int index$timeStep$63 = timeStep$var49;
															int index$sample$64 = sample$var32;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample48Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
						if(fixedFlag$sample61) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample$var32)) {
										if((index$timeStep$50_2 == (timeStep$var49 - 1))) {
											if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
												if(!fixedFlag$sample61) {
													{
														int index$timeStep$66 = timeStep$var49;
														int index$sample$67 = sample$var32;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$timeStep$52 = 1; index$timeStep$52 < length$metric[index$sample$51][0]; index$timeStep$52 += 1) {
									if(true) {
										for(int index$sample61$53 = 0; index$sample61$53 < noStates; index$sample61$53 += 1) {
											int distributionTempVariable$var57$55 = index$sample61$53;
											double cv$probabilitySample61Value54 = (1.0 * distribution$sample61[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample61$53]);
											int traceTempVariable$var54$56_1 = distributionTempVariable$var57$55;
											if((index$sample$51 == sample$var32)) {
												if((index$timeStep$52 == (timeStep$var49 - 1))) {
													if((var26 == traceTempVariable$var54$56_1)) {
														if(!fixedFlag$sample61) {
															{
																int index$timeStep$69 = timeStep$var49;
																int index$sample$70 = sample$var32;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample61Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample48(int sample$var32) {
		double[] cv$stateProbabilityLocal = cv$var44$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$sample$1 = sample$var32;
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$initialStateDistribution));
				{
					{
						int traceTempVariable$var54$2_1 = cv$currentValue;
						for(int index$sample$2_2 = 0; index$sample$2_2 < noSamples; index$sample$2_2 += 1) {
							if((sample$var32 == index$sample$2_2)) {
								for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$2_2][0]; timeStep$var49 += 1) {
									if((0 == (timeStep$var49 - 1))) {
										if(fixedFlag$sample61) {
											{
												int index$timeStep$4 = timeStep$var49;
												int index$sample$5 = index$sample$2_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var26 = 0; var26 < noStates; var26 += 1) {
														if((var26 == traceTempVariable$var54$2_1)) {
															{
																{
																	double[] cv$temp$1$var55;
																	{
																		double[] var55 = m[traceTempVariable$var54$2_1];
																		cv$temp$1$var55 = var55;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(st[index$sample$2_2][timeStep$var49], cv$temp$1$var55)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityCategorical(st[index$sample$2_2][timeStep$var49], cv$temp$1$var55)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(st[index$sample$2_2][timeStep$var49], cv$temp$1$var55));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(st[index$sample$2_2][timeStep$var49], cv$temp$1$var55)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(st[index$sample$2_2][timeStep$var49], cv$temp$1$var55)));
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
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var114 = 0; var114 < noStates; var114 += 1) {
													for(int var110 = 0; var110 < noServers; var110 += 1) {
														if((var110 == server)) {
															if((var114 == traceTempVariable$currentState$8_1)) {
																{
																	{
																		double cv$temp$2$var144;
																		{
																			double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$8_1];
																			cv$temp$2$var144 = var144;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$2$var144)));
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
				{
					{
						boolean[][][] guard$sample48gaussian163 = guard$sample48gaussian163$global;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1)
											guard$sample48gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1)
											guard$sample48gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
									}
								}
							}
						}
						int traceTempVariable$currentState$14_1 = cv$currentValue;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											if(!guard$sample48gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
												guard$sample48gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$14_1)) {
																				int traceTempVariable$currentState$19_1 = cv$currentValue;
																				if((index$sample$1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$19_1)) {
																												{
																													{
																														double cv$temp$3$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$19_1];
																															cv$temp$3$var152 = var152;
																														}
																														double cv$temp$4$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$19_1];
																															cv$temp$4$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$3$var152, cv$temp$4$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$3$var152, cv$temp$4$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$3$var152, cv$temp$4$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$3$var152, cv$temp$4$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$3$var152, cv$temp$4$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$19$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$19_1];
																															cv$temp$19$var152 = var152;
																														}
																														double cv$temp$20$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$19_1];
																															cv$temp$20$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$19$var152, cv$temp$20$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$19$var152, cv$temp$20$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$19$var152, cv$temp$20$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$19$var152, cv$temp$20$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$19$var152, cv$temp$20$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$35$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$19_1];
																															cv$temp$35$var152 = var152;
																														}
																														double cv$temp$36$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$19_1];
																															cv$temp$36$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$35$var152, cv$temp$36$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$35$var152, cv$temp$36$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$35$var152, cv$temp$36$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$35$var152, cv$temp$36$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$35$var152, cv$temp$36$var154)));
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
																				for(int index$sample$20 = 0; index$sample$20 < noSamples; index$sample$20 += 1) {
																					if(!(index$sample$20 == index$sample$1)) {
																						for(int index$sample48$21 = 0; index$sample48$21 < noStates; index$sample48$21 += 1) {
																							int distributionTempVariable$var44$23 = index$sample48$21;
																							double cv$probabilitySample48Value22 = (1.0 * distribution$sample48[((index$sample$20 - 0) / 1)][index$sample48$21]);
																							int traceTempVariable$currentState$24_1 = distributionTempVariable$var44$23;
																							if((index$sample$20 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$24_1)) {
																															{
																																{
																																	double cv$temp$5$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$24_1];
																																		cv$temp$5$var152 = var152;
																																	}
																																	double cv$temp$6$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$24_1];
																																		cv$temp$6$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$5$var152, cv$temp$6$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$5$var152, cv$temp$6$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$5$var152, cv$temp$6$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$5$var152, cv$temp$6$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$5$var152, cv$temp$6$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value22);
																																}
																																{
																																	double cv$temp$21$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$24_1];
																																		cv$temp$21$var152 = var152;
																																	}
																																	double cv$temp$22$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$24_1];
																																		cv$temp$22$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$21$var152, cv$temp$22$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$21$var152, cv$temp$22$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$21$var152, cv$temp$22$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$21$var152, cv$temp$22$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$21$var152, cv$temp$22$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value22);
																																}
																																{
																																	double cv$temp$37$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$24_1];
																																		cv$temp$37$var152 = var152;
																																	}
																																	double cv$temp$38$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$24_1];
																																		cv$temp$38$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$37$var152, cv$temp$38$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$37$var152, cv$temp$38$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$37$var152, cv$temp$38$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$37$var152, cv$temp$38$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value22) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$37$var152, cv$temp$38$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value22);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$14_1)) {
																				if(fixedFlag$sample61) {
																					for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$28_1][0]; timeStep$var49 += 1) {
																							if((index$sample$28_1 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$14_1)) {
																															{
																																{
																																	double cv$temp$7$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$14_1];
																																		cv$temp$7$var152 = var152;
																																	}
																																	double cv$temp$8$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$14_1];
																																		cv$temp$8$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$7$var152, cv$temp$8$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$7$var152, cv$temp$8$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$7$var152, cv$temp$8$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$7$var152, cv$temp$8$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$7$var152, cv$temp$8$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$23$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$14_1];
																																		cv$temp$23$var152 = var152;
																																	}
																																	double cv$temp$24$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$14_1];
																																		cv$temp$24$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$23$var152, cv$temp$24$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$23$var152, cv$temp$24$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$23$var152, cv$temp$24$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$23$var152, cv$temp$24$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$23$var152, cv$temp$24$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$39$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$14_1];
																																		cv$temp$39$var152 = var152;
																																	}
																																	double cv$temp$40$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$14_1];
																																		cv$temp$40$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$39$var152, cv$temp$40$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$39$var152, cv$temp$40$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$39$var152, cv$temp$40$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$39$var152, cv$temp$40$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$39$var152, cv$temp$40$var154)));
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
																					for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$29][0]; timeStep$var49 += 1) {
																							if(true) {
																								for(int index$sample61$31 = 0; index$sample61$31 < noStates; index$sample61$31 += 1) {
																									int distributionTempVariable$var57$33 = index$sample61$31;
																									double cv$probabilitySample61Value32 = (1.0 * distribution$sample61[((index$sample$29 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$31]);
																									int traceTempVariable$currentState$34_1 = distributionTempVariable$var57$33;
																									if((index$sample$29 == sample$var120)) {
																										if((timeStep$var49 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$34_1)) {
																																	{
																																		{
																																			double cv$temp$9$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																				cv$temp$9$var152 = var152;
																																			}
																																			double cv$temp$10$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																				cv$temp$10$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$9$var152, cv$temp$10$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$9$var152, cv$temp$10$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$9$var152, cv$temp$10$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$9$var152, cv$temp$10$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$9$var152, cv$temp$10$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value32);
																																		}
																																		{
																																			double cv$temp$25$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																				cv$temp$25$var152 = var152;
																																			}
																																			double cv$temp$26$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																				cv$temp$26$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$25$var152, cv$temp$26$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$25$var152, cv$temp$26$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$25$var152, cv$temp$26$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$25$var152, cv$temp$26$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$25$var152, cv$temp$26$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value32);
																																		}
																																		{
																																			double cv$temp$41$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																				cv$temp$41$var152 = var152;
																																			}
																																			double cv$temp$42$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																				cv$temp$42$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$41$var152, cv$temp$42$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$41$var152, cv$temp$42$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$41$var152, cv$temp$42$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$41$var152, cv$temp$42$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value32) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$41$var152, cv$temp$42$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value32);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											if(!guard$sample48gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
												guard$sample48gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$currentState$37_1 = cv$currentValue;
														if((index$sample$1 == sample$var120)) {
															if((0 == timeStep$var140)) {
																for(int var86 = 0; var86 < noStates; var86 += 1) {
																	for(int var82 = 0; var82 < noServers; var82 += 1) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var82 == server)) {
																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																					if((var86 == traceTempVariable$currentState$37_1)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$37_1)) {
																												{
																													{
																														double cv$temp$11$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$37_1];
																															cv$temp$11$var152 = var152;
																														}
																														double cv$temp$12$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$37_1];
																															cv$temp$12$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$11$var152, cv$temp$12$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$11$var152, cv$temp$12$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$11$var152, cv$temp$12$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$11$var152, cv$temp$12$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$11$var152, cv$temp$12$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$27$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$37_1];
																															cv$temp$27$var152 = var152;
																														}
																														double cv$temp$28$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$37_1];
																															cv$temp$28$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$27$var152, cv$temp$28$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$27$var152, cv$temp$28$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$27$var152, cv$temp$28$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$27$var152, cv$temp$28$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$27$var152, cv$temp$28$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$43$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$37_1];
																															cv$temp$43$var152 = var152;
																														}
																														double cv$temp$44$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$37_1];
																															cv$temp$44$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$43$var152, cv$temp$44$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$43$var152, cv$temp$44$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$43$var152, cv$temp$44$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$43$var152, cv$temp$44$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$43$var152, cv$temp$44$var154)));
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
															}
														}
														for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
															if(!(index$sample$38 == index$sample$1)) {
																for(int index$sample48$39 = 0; index$sample48$39 < noStates; index$sample48$39 += 1) {
																	int distributionTempVariable$var44$41 = index$sample48$39;
																	double cv$probabilitySample48Value40 = (1.0 * distribution$sample48[((index$sample$38 - 0) / 1)][index$sample48$39]);
																	int traceTempVariable$currentState$42_1 = distributionTempVariable$var44$41;
																	if((index$sample$38 == sample$var120)) {
																		if((0 == timeStep$var140)) {
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$42_1)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$42_1)) {
																															{
																																{
																																	double cv$temp$13$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$42_1];
																																		cv$temp$13$var152 = var152;
																																	}
																																	double cv$temp$14$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$42_1];
																																		cv$temp$14$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$13$var152, cv$temp$14$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$13$var152, cv$temp$14$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$13$var152, cv$temp$14$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$13$var152, cv$temp$14$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$13$var152, cv$temp$14$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value40);
																																}
																																{
																																	double cv$temp$29$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$42_1];
																																		cv$temp$29$var152 = var152;
																																	}
																																	double cv$temp$30$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$42_1];
																																		cv$temp$30$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$29$var152, cv$temp$30$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$29$var152, cv$temp$30$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$29$var152, cv$temp$30$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$29$var152, cv$temp$30$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$29$var152, cv$temp$30$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value40);
																																}
																																{
																																	double cv$temp$45$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$42_1];
																																		cv$temp$45$var152 = var152;
																																	}
																																	double cv$temp$46$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$42_1];
																																		cv$temp$46$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$45$var152, cv$temp$46$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$45$var152, cv$temp$46$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$45$var152, cv$temp$46$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$45$var152, cv$temp$46$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value40) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$45$var152, cv$temp$46$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value40);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample61) {
															for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																	if((index$sample$47_1 == sample$var120)) {
																		if((timeStep$var49 == timeStep$var140)) {
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$15_1)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$15_1)) {
																															{
																																{
																																	double cv$temp$15$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																		cv$temp$15$var152 = var152;
																																	}
																																	double cv$temp$16$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																		cv$temp$16$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$15$var152, cv$temp$16$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$15$var152, cv$temp$16$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$15$var152, cv$temp$16$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$15$var152, cv$temp$16$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$15$var152, cv$temp$16$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$31$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																		cv$temp$31$var152 = var152;
																																	}
																																	double cv$temp$32$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																		cv$temp$32$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$31$var152, cv$temp$32$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$31$var152, cv$temp$32$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$31$var152, cv$temp$32$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$31$var152, cv$temp$32$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$31$var152, cv$temp$32$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$47$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																		cv$temp$47$var152 = var152;
																																	}
																																	double cv$temp$48$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																		cv$temp$48$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$47$var152, cv$temp$48$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$47$var152, cv$temp$48$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$47$var152, cv$temp$48$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$47$var152, cv$temp$48$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$47$var152, cv$temp$48$var154)));
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
																		}
																	}
																}
															}
														} else {
															for(int index$sample$48 = 0; index$sample$48 < noSamples; index$sample$48 += 1) {
																for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48][0]; timeStep$var49 += 1) {
																	if(true) {
																		for(int index$sample61$50 = 0; index$sample61$50 < noStates; index$sample61$50 += 1) {
																			int distributionTempVariable$var57$52 = index$sample61$50;
																			double cv$probabilitySample61Value51 = (1.0 * distribution$sample61[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$50]);
																			int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																			if((index$sample$48 == sample$var120)) {
																				if((timeStep$var49 == timeStep$var140)) {
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$53_1)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$53_1)) {
																																	{
																																		{
																																			double cv$temp$17$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																				cv$temp$17$var152 = var152;
																																			}
																																			double cv$temp$18$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																				cv$temp$18$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$17$var152, cv$temp$18$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$17$var152, cv$temp$18$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$17$var152, cv$temp$18$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$17$var152, cv$temp$18$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$17$var152, cv$temp$18$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																		}
																																		{
																																			double cv$temp$33$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																				cv$temp$33$var152 = var152;
																																			}
																																			double cv$temp$34$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																				cv$temp$34$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$33$var152, cv$temp$34$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$33$var152, cv$temp$34$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$33$var152, cv$temp$34$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$33$var152, cv$temp$34$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$33$var152, cv$temp$34$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																		}
																																		{
																																			double cv$temp$49$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																				cv$temp$49$var152 = var152;
																																			}
																																			double cv$temp$50$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																				cv$temp$50$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$49$var152, cv$temp$50$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$49$var152, cv$temp$50$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$49$var152, cv$temp$50$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$49$var152, cv$temp$50$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$49$var152, cv$temp$50$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
					int traceTempVariable$var54$82_1 = cv$currentValue;
					for(int index$sample$82_2 = 0; index$sample$82_2 < noSamples; index$sample$82_2 += 1) {
						if((sample$var32 == index$sample$82_2)) {
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$82_2][0]; timeStep$var49 += 1) {
								if((0 == (timeStep$var49 - 1))) {
									if(!fixedFlag$sample61) {
										{
											int index$timeStep$84 = timeStep$var49;
											int index$sample$85 = index$sample$82_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == traceTempVariable$var54$82_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$51$var55;
														{
															double[] var55 = m[traceTempVariable$var54$82_1];
															cv$temp$51$var55 = var55;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$51$var55);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample61[((index$sample$82_2 - 0) / 1)][((timeStep$var49 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample48[((sample$var32 - 0) / 1)];
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

	private final void sample61(int sample$var32, int timeStep$var49) {
		double[] cv$stateProbabilityLocal = cv$var57$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			int index$timeStep$1 = timeStep$var49;
			int index$sample$2 = sample$var32;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample48) {
				for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
					if((index$sample$3_1 == sample$var32)) {
						if((0 == (timeStep$var49 - 1))) {
							for(int var26 = 0; var26 < noStates; var26 += 1) {
								if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var55;
									{
										double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
										cv$temp$0$var55 = var55;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$0$var55));
									{
										{
											int traceTempVariable$var54$20_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$24_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var114 = 0; var114 < noStates; var114 += 1) {
																		for(int var110 = 0; var110 < noServers; var110 += 1) {
																			if((var110 == server)) {
																				if((var114 == traceTempVariable$currentState$24_1)) {
																					{
																						{
																							double cv$temp$4$var144;
																							{
																								double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$24_1];
																								cv$temp$4$var144 = var144;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$4$var144)));
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
									{
										{
											boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1)
																guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
														}
													}
												}
											}
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1)
																guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
														}
													}
												}
											}
											int traceTempVariable$currentState$48_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																	guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$48_1)) {
																									for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
																										if((index$sample$65_1 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$48_1)) {
																																		{
																																			{
																																				double cv$temp$8$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$48_1];
																																					cv$temp$8$var152 = var152;
																																				}
																																				double cv$temp$9$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$48_1];
																																					cv$temp$9$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																			{
																																				double cv$temp$72$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$48_1];
																																					cv$temp$72$var152 = var152;
																																				}
																																				double cv$temp$73$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$48_1];
																																					cv$temp$73$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																			{
																																				double cv$temp$136$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$48_1];
																																					cv$temp$136$var152 = var152;
																																				}
																																				double cv$temp$137$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$48_1];
																																					cv$temp$137$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$136$var152, cv$temp$137$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$136$var152, cv$temp$137$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$136$var152, cv$temp$137$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$136$var152, cv$temp$137$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$136$var152, cv$temp$137$var154)));
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
																					}
																				}
																			}
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$48_1)) {
																									int traceTempVariable$currentState$68_1 = cv$currentValue;
																									if((index$sample$2 == sample$var120)) {
																										if((index$timeStep$1 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$68_1)) {
																																	{
																																		{
																																			double cv$temp$10$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$68_1];
																																				cv$temp$10$var152 = var152;
																																			}
																																			double cv$temp$11$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$68_1];
																																				cv$temp$11$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$74$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$68_1];
																																				cv$temp$74$var152 = var152;
																																			}
																																			double cv$temp$75$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$68_1];
																																				cv$temp$75$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$138$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$68_1];
																																				cv$temp$138$var152 = var152;
																																			}
																																			double cv$temp$139$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$68_1];
																																				cv$temp$139$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$138$var152, cv$temp$139$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$138$var152, cv$temp$139$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$138$var152, cv$temp$139$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$138$var152, cv$temp$139$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$138$var152, cv$temp$139$var154)));
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
																									for(int index$sample$69 = 0; index$sample$69 < noSamples; index$sample$69 += 1) {
																										for(int index$timeStep$70 = 1; index$timeStep$70 < length$metric[index$sample$69][0]; index$timeStep$70 += 1) {
																											if(!((index$sample$69 == index$sample$2) && (index$timeStep$70 == index$timeStep$1))) {
																												for(int index$sample61$71 = 0; index$sample61$71 < noStates; index$sample61$71 += 1) {
																													int distributionTempVariable$var57$73 = index$sample61$71;
																													double cv$probabilitySample61Value72 = (1.0 * distribution$sample61[((index$sample$69 - 0) / 1)][((index$timeStep$70 - 1) / 1)][index$sample61$71]);
																													int traceTempVariable$currentState$74_1 = distributionTempVariable$var57$73;
																													if((index$sample$69 == sample$var120)) {
																														if((index$timeStep$70 == timeStep$var140)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$74_1)) {
																																					{
																																						{
																																							double cv$temp$12$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																								cv$temp$12$var152 = var152;
																																							}
																																							double cv$temp$13$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																								cv$temp$13$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value72);
																																						}
																																						{
																																							double cv$temp$76$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																								cv$temp$76$var152 = var152;
																																							}
																																							double cv$temp$77$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																								cv$temp$77$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value72);
																																						}
																																						{
																																							double cv$temp$140$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																								cv$temp$140$var152 = var152;
																																							}
																																							double cv$temp$141$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																								cv$temp$141$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$140$var152, cv$temp$141$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$140$var152, cv$temp$141$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$140$var152, cv$temp$141$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$140$var152, cv$temp$141$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value72) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$140$var152, cv$temp$141$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value72);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
											int traceTempVariable$currentState$52_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																	guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int index$sample$136_1 = 0; index$sample$136_1 < noSamples; index$sample$136_1 += 1) {
																				if((index$sample$136_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var82 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var86 == traceTempVariable$currentState$52_1)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$52_1)) {
																																		{
																																			{
																																				double cv$temp$40$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$52_1];
																																					cv$temp$40$var152 = var152;
																																				}
																																				double cv$temp$41$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$52_1];
																																					cv$temp$41$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																			{
																																				double cv$temp$104$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$52_1];
																																					cv$temp$104$var152 = var152;
																																				}
																																				double cv$temp$105$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$52_1];
																																					cv$temp$105$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$104$var152, cv$temp$105$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$104$var152, cv$temp$105$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$104$var152, cv$temp$105$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$104$var152, cv$temp$105$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$104$var152, cv$temp$105$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																			}
																																			{
																																				double cv$temp$168$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$52_1];
																																					cv$temp$168$var152 = var152;
																																				}
																																				double cv$temp$169$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$52_1];
																																					cv$temp$169$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$168$var152, cv$temp$169$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$168$var152, cv$temp$169$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$168$var152, cv$temp$169$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$168$var152, cv$temp$169$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$168$var152, cv$temp$169$var154)));
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
																					}
																				}
																			}
																			int traceTempVariable$currentState$139_1 = cv$currentValue;
																			if((index$sample$2 == sample$var120)) {
																				if((index$timeStep$1 == timeStep$var140)) {
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$139_1)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$139_1)) {
																																	{
																																		{
																																			double cv$temp$42$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$139_1];
																																				cv$temp$42$var152 = var152;
																																			}
																																			double cv$temp$43$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$139_1];
																																				cv$temp$43$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$106$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$139_1];
																																				cv$temp$106$var152 = var152;
																																			}
																																			double cv$temp$107$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$139_1];
																																				cv$temp$107$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$106$var152, cv$temp$107$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$106$var152, cv$temp$107$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$106$var152, cv$temp$107$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$106$var152, cv$temp$107$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$106$var152, cv$temp$107$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$170$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$139_1];
																																				cv$temp$170$var152 = var152;
																																			}
																																			double cv$temp$171$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$139_1];
																																				cv$temp$171$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$170$var152, cv$temp$171$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$170$var152, cv$temp$171$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$170$var152, cv$temp$171$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$170$var152, cv$temp$171$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$170$var152, cv$temp$171$var154)));
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
																				}
																			}
																			for(int index$sample$140 = 0; index$sample$140 < noSamples; index$sample$140 += 1) {
																				for(int index$timeStep$141 = 1; index$timeStep$141 < length$metric[index$sample$140][0]; index$timeStep$141 += 1) {
																					if(!((index$sample$140 == index$sample$2) && (index$timeStep$141 == index$timeStep$1))) {
																						for(int index$sample61$142 = 0; index$sample61$142 < noStates; index$sample61$142 += 1) {
																							int distributionTempVariable$var57$144 = index$sample61$142;
																							double cv$probabilitySample61Value143 = (1.0 * distribution$sample61[((index$sample$140 - 0) / 1)][((index$timeStep$141 - 1) / 1)][index$sample61$142]);
																							int traceTempVariable$currentState$145_1 = distributionTempVariable$var57$144;
																							if((index$sample$140 == sample$var120)) {
																								if((index$timeStep$141 == timeStep$var140)) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var82 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var86 == traceTempVariable$currentState$145_1)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$145_1)) {
																																					{
																																						{
																																							double cv$temp$44$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$145_1];
																																								cv$temp$44$var152 = var152;
																																							}
																																							double cv$temp$45$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$145_1];
																																								cv$temp$45$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value143);
																																						}
																																						{
																																							double cv$temp$108$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$145_1];
																																								cv$temp$108$var152 = var152;
																																							}
																																							double cv$temp$109$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$145_1];
																																								cv$temp$109$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$108$var152, cv$temp$109$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$108$var152, cv$temp$109$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$108$var152, cv$temp$109$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$108$var152, cv$temp$109$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$108$var152, cv$temp$109$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value143);
																																						}
																																						{
																																							double cv$temp$172$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$145_1];
																																								cv$temp$172$var152 = var152;
																																							}
																																							double cv$temp$173$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$145_1];
																																								cv$temp$173$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$172$var152, cv$temp$173$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$172$var152, cv$temp$173$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$172$var152, cv$temp$173$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$172$var152, cv$temp$173$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value143) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$172$var152, cv$temp$173$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value143);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
						for(int index$sample48$5 = 0; index$sample48$5 < noStates; index$sample48$5 += 1) {
							int distributionTempVariable$var44$7 = index$sample48$5;
							double cv$probabilitySample48Value6 = (1.0 * distribution$sample48[((index$sample$4 - 0) / 1)][index$sample48$5]);
							int traceTempVariable$var54$8_1 = distributionTempVariable$var44$7;
							if((index$sample$4 == sample$var32)) {
								if((0 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$8_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample48Value6);
											double[] cv$temp$1$var55;
											{
												double[] var55 = m[traceTempVariable$var54$8_1];
												cv$temp$1$var55 = var55;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample48Value6) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$1$var55));
											{
												{
													int traceTempVariable$var54$21_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$25_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var114 = 0; var114 < noStates; var114 += 1) {
																				for(int var110 = 0; var110 < noServers; var110 += 1) {
																					if((var110 == server)) {
																						if((var114 == traceTempVariable$currentState$25_1)) {
																							{
																								{
																									double cv$temp$5$var144;
																									{
																										double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$25_1];
																										cv$temp$5$var144 = var144;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$5$var144)));
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
											{
												{
													boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$49_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$49_1)) {
																											int traceTempVariable$currentState$78_1 = distributionTempVariable$var44$7;
																											if((index$sample$4 == sample$var120)) {
																												if((0 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$78_1)) {
																																			{
																																				{
																																					double cv$temp$14$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$78_1];
																																						cv$temp$14$var152 = var152;
																																					}
																																					double cv$temp$15$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$78_1];
																																						cv$temp$15$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$78$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$78_1];
																																						cv$temp$78$var152 = var152;
																																					}
																																					double cv$temp$79$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$78_1];
																																						cv$temp$79$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$142$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$78_1];
																																						cv$temp$142$var152 = var152;
																																					}
																																					double cv$temp$143$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$78_1];
																																						cv$temp$143$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$142$var152, cv$temp$143$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$142$var152, cv$temp$143$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$142$var152, cv$temp$143$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$142$var152, cv$temp$143$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$142$var152, cv$temp$143$var154)));
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
																											for(int index$sample$79 = 0; index$sample$79 < noSamples; index$sample$79 += 1) {
																												if(!(index$sample$79 == index$sample$4)) {
																													for(int index$sample48$80 = 0; index$sample48$80 < noStates; index$sample48$80 += 1) {
																														int distributionTempVariable$var44$82 = index$sample48$80;
																														double cv$probabilitySample48Value81 = (1.0 * distribution$sample48[((index$sample$79 - 0) / 1)][index$sample48$80]);
																														int traceTempVariable$currentState$83_1 = distributionTempVariable$var44$82;
																														if((index$sample$79 == sample$var120)) {
																															if((0 == timeStep$var140)) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																			if((var96 == server)) {
																																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																					if((var100 == traceTempVariable$currentState$83_1)) {
																																						{
																																							{
																																								double cv$temp$16$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$83_1];
																																									cv$temp$16$var152 = var152;
																																								}
																																								double cv$temp$17$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$83_1];
																																									cv$temp$17$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value81);
																																							}
																																							{
																																								double cv$temp$80$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$83_1];
																																									cv$temp$80$var152 = var152;
																																								}
																																								double cv$temp$81$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$83_1];
																																									cv$temp$81$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value81);
																																							}
																																							{
																																								double cv$temp$144$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$83_1];
																																									cv$temp$144$var152 = var152;
																																								}
																																								double cv$temp$145$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$83_1];
																																									cv$temp$145$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$144$var152, cv$temp$145$var154)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$144$var152, cv$temp$145$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$144$var152, cv$temp$145$var154));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$144$var152, cv$temp$145$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value81) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$144$var152, cv$temp$145$var154)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value81);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$49_1)) {
																											int traceTempVariable$currentState$87_1 = cv$currentValue;
																											if((index$sample$2 == sample$var120)) {
																												if((index$timeStep$1 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$87_1)) {
																																			{
																																				{
																																					double cv$temp$18$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$87_1];
																																						cv$temp$18$var152 = var152;
																																					}
																																					double cv$temp$19$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$87_1];
																																						cv$temp$19$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$82$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$87_1];
																																						cv$temp$82$var152 = var152;
																																					}
																																					double cv$temp$83$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$87_1];
																																						cv$temp$83$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$146$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$87_1];
																																						cv$temp$146$var152 = var152;
																																					}
																																					double cv$temp$147$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$87_1];
																																						cv$temp$147$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$146$var152, cv$temp$147$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$146$var152, cv$temp$147$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$146$var152, cv$temp$147$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$146$var152, cv$temp$147$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$146$var152, cv$temp$147$var154)));
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
																											for(int index$sample$88 = 0; index$sample$88 < noSamples; index$sample$88 += 1) {
																												for(int index$timeStep$89 = 1; index$timeStep$89 < length$metric[index$sample$88][0]; index$timeStep$89 += 1) {
																													if(!((index$sample$88 == index$sample$2) && (index$timeStep$89 == index$timeStep$1))) {
																														for(int index$sample61$90 = 0; index$sample61$90 < noStates; index$sample61$90 += 1) {
																															int distributionTempVariable$var57$92 = index$sample61$90;
																															double cv$probabilitySample61Value91 = (1.0 * distribution$sample61[((index$sample$88 - 0) / 1)][((index$timeStep$89 - 1) / 1)][index$sample61$90]);
																															int traceTempVariable$currentState$93_1 = distributionTempVariable$var57$92;
																															if((index$sample$88 == sample$var120)) {
																																if((index$timeStep$89 == timeStep$var140)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$93_1)) {
																																							{
																																								{
																																									double cv$temp$20$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$93_1];
																																										cv$temp$20$var152 = var152;
																																									}
																																									double cv$temp$21$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$93_1];
																																										cv$temp$21$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value91);
																																								}
																																								{
																																									double cv$temp$84$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$93_1];
																																										cv$temp$84$var152 = var152;
																																									}
																																									double cv$temp$85$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$93_1];
																																										cv$temp$85$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value91);
																																								}
																																								{
																																									double cv$temp$148$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$93_1];
																																										cv$temp$148$var152 = var152;
																																									}
																																									double cv$temp$149$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$93_1];
																																										cv$temp$149$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$148$var152, cv$temp$149$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$148$var152, cv$temp$149$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$148$var152, cv$temp$149$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$148$var152, cv$temp$149$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value91) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$148$var152, cv$temp$149$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value91);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
													int traceTempVariable$currentState$53_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					int traceTempVariable$currentState$150_1 = distributionTempVariable$var44$7;
																					if((index$sample$4 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$150_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$150_1)) {
																																			{
																																				{
																																					double cv$temp$46$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$150_1];
																																						cv$temp$46$var152 = var152;
																																					}
																																					double cv$temp$47$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$150_1];
																																						cv$temp$47$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$110$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$150_1];
																																						cv$temp$110$var152 = var152;
																																					}
																																					double cv$temp$111$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$150_1];
																																						cv$temp$111$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$110$var152, cv$temp$111$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$110$var152, cv$temp$111$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$110$var152, cv$temp$111$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$110$var152, cv$temp$111$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$110$var152, cv$temp$111$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$174$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$150_1];
																																						cv$temp$174$var152 = var152;
																																					}
																																					double cv$temp$175$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$150_1];
																																						cv$temp$175$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$174$var152, cv$temp$175$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$174$var152, cv$temp$175$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$174$var152, cv$temp$175$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$174$var152, cv$temp$175$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$174$var152, cv$temp$175$var154)));
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
																						}
																					}
																					for(int index$sample$151 = 0; index$sample$151 < noSamples; index$sample$151 += 1) {
																						if(!(index$sample$151 == index$sample$4)) {
																							for(int index$sample48$152 = 0; index$sample48$152 < noStates; index$sample48$152 += 1) {
																								int distributionTempVariable$var44$154 = index$sample48$152;
																								double cv$probabilitySample48Value153 = (1.0 * distribution$sample48[((index$sample$151 - 0) / 1)][index$sample48$152]);
																								int traceTempVariable$currentState$155_1 = distributionTempVariable$var44$154;
																								if((index$sample$151 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$155_1)) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																			if((var96 == server)) {
																																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																					if((var100 == traceTempVariable$currentState$155_1)) {
																																						{
																																							{
																																								double cv$temp$48$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$155_1];
																																									cv$temp$48$var152 = var152;
																																								}
																																								double cv$temp$49$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$155_1];
																																									cv$temp$49$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value153);
																																							}
																																							{
																																								double cv$temp$112$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$155_1];
																																									cv$temp$112$var152 = var152;
																																								}
																																								double cv$temp$113$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$155_1];
																																									cv$temp$113$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$112$var152, cv$temp$113$var154)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$112$var152, cv$temp$113$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$112$var152, cv$temp$113$var154));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$112$var152, cv$temp$113$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$112$var152, cv$temp$113$var154)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value153);
																																							}
																																							{
																																								double cv$temp$176$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$155_1];
																																									cv$temp$176$var152 = var152;
																																								}
																																								double cv$temp$177$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$155_1];
																																									cv$temp$177$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$176$var152, cv$temp$177$var154)) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$176$var152, cv$temp$177$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$176$var152, cv$temp$177$var154));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$176$var152, cv$temp$177$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value153) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$176$var152, cv$temp$177$var154)));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value153);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
																					if((index$sample$2 == sample$var120)) {
																						if((index$timeStep$1 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$160_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$160_1)) {
																																			{
																																				{
																																					double cv$temp$50$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																						cv$temp$50$var152 = var152;
																																					}
																																					double cv$temp$51$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																						cv$temp$51$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$114$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																						cv$temp$114$var152 = var152;
																																					}
																																					double cv$temp$115$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																						cv$temp$115$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$114$var152, cv$temp$115$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$114$var152, cv$temp$115$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$114$var152, cv$temp$115$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$114$var152, cv$temp$115$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$114$var152, cv$temp$115$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$178$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																						cv$temp$178$var152 = var152;
																																					}
																																					double cv$temp$179$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																						cv$temp$179$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$178$var152, cv$temp$179$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$178$var152, cv$temp$179$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$178$var152, cv$temp$179$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$178$var152, cv$temp$179$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$178$var152, cv$temp$179$var154)));
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
																						}
																					}
																					for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																						for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161][0]; index$timeStep$162 += 1) {
																							if(!((index$sample$161 == index$sample$2) && (index$timeStep$162 == index$timeStep$1))) {
																								for(int index$sample61$163 = 0; index$sample61$163 < noStates; index$sample61$163 += 1) {
																									int distributionTempVariable$var57$165 = index$sample61$163;
																									double cv$probabilitySample61Value164 = (1.0 * distribution$sample61[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample61$163]);
																									int traceTempVariable$currentState$166_1 = distributionTempVariable$var57$165;
																									if((index$sample$161 == sample$var120)) {
																										if((index$timeStep$162 == timeStep$var140)) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$166_1)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$166_1)) {
																																							{
																																								{
																																									double cv$temp$52$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																										cv$temp$52$var152 = var152;
																																									}
																																									double cv$temp$53$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																										cv$temp$53$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value164);
																																								}
																																								{
																																									double cv$temp$116$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																										cv$temp$116$var152 = var152;
																																									}
																																									double cv$temp$117$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																										cv$temp$117$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$116$var152, cv$temp$117$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$116$var152, cv$temp$117$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$116$var152, cv$temp$117$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$116$var152, cv$temp$117$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$116$var152, cv$temp$117$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value164);
																																								}
																																								{
																																									double cv$temp$180$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																										cv$temp$180$var152 = var152;
																																									}
																																									double cv$temp$181$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																										cv$temp$181$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$180$var152, cv$temp$181$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$180$var152, cv$temp$181$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$180$var152, cv$temp$181$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$180$var152, cv$temp$181$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value164) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$180$var152, cv$temp$181$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value164);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
			int traceTempVariable$var54$11_1 = cv$currentValue;
			if((index$sample$2 == sample$var32)) {
				if((index$timeStep$1 == (timeStep$var49 - 1))) {
					for(int var26 = 0; var26 < noStates; var26 += 1) {
						if((var26 == traceTempVariable$var54$11_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var55;
							{
								double[] var55 = m[traceTempVariable$var54$11_1];
								cv$temp$2$var55 = var55;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$2$var55));
							{
								{
									int traceTempVariable$var54$22_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$26_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var114 = 0; var114 < noStates; var114 += 1) {
																for(int var110 = 0; var110 < noServers; var110 += 1) {
																	if((var110 == server)) {
																		if((var114 == traceTempVariable$currentState$26_1)) {
																			{
																				{
																					double cv$temp$6$var144;
																					{
																						double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$26_1];
																						cv$temp$6$var144 = var144;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$6$var144)));
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
							{
								{
									boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1)
														guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
												}
											}
										}
									}
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1)
														guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
												}
											}
										}
									}
									int traceTempVariable$currentState$50_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
															guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var86 = 0; var86 < noStates; var86 += 1) {
																		for(int var82 = 0; var82 < noServers; var82 += 1) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var82 == server)) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var86 == traceTempVariable$currentState$50_1)) {
																							if(fixedFlag$sample48) {
																								for(int index$sample$97_1 = 0; index$sample$97_1 < noSamples; index$sample$97_1 += 1) {
																									if((index$sample$97_1 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$50_1)) {
																																	{
																																		{
																																			double cv$temp$22$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$50_1];
																																				cv$temp$22$var152 = var152;
																																			}
																																			double cv$temp$23$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$50_1];
																																				cv$temp$23$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$86$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$50_1];
																																				cv$temp$86$var152 = var152;
																																			}
																																			double cv$temp$87$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$50_1];
																																				cv$temp$87$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$86$var152, cv$temp$87$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$86$var152, cv$temp$87$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$86$var152, cv$temp$87$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$86$var152, cv$temp$87$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$86$var152, cv$temp$87$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$150$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$50_1];
																																				cv$temp$150$var152 = var152;
																																			}
																																			double cv$temp$151$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$50_1];
																																				cv$temp$151$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$150$var152, cv$temp$151$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$150$var152, cv$temp$151$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$150$var152, cv$temp$151$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$150$var152, cv$temp$151$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$150$var152, cv$temp$151$var154)));
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
																								for(int index$sample$98 = 0; index$sample$98 < noSamples; index$sample$98 += 1) {
																									if(true) {
																										for(int index$sample48$99 = 0; index$sample48$99 < noStates; index$sample48$99 += 1) {
																											int distributionTempVariable$var44$101 = index$sample48$99;
																											double cv$probabilitySample48Value100 = (1.0 * distribution$sample48[((index$sample$98 - 0) / 1)][index$sample48$99]);
																											int traceTempVariable$currentState$102_1 = distributionTempVariable$var44$101;
																											if((index$sample$98 == sample$var120)) {
																												if((0 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$102_1)) {
																																			{
																																				{
																																					double cv$temp$24$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$102_1];
																																						cv$temp$24$var152 = var152;
																																					}
																																					double cv$temp$25$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$102_1];
																																						cv$temp$25$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value100);
																																				}
																																				{
																																					double cv$temp$88$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$102_1];
																																						cv$temp$88$var152 = var152;
																																					}
																																					double cv$temp$89$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$102_1];
																																						cv$temp$89$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$88$var152, cv$temp$89$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$88$var152, cv$temp$89$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$88$var152, cv$temp$89$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$88$var152, cv$temp$89$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$88$var152, cv$temp$89$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value100);
																																				}
																																				{
																																					double cv$temp$152$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$102_1];
																																						cv$temp$152$var152 = var152;
																																					}
																																					double cv$temp$153$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$102_1];
																																						cv$temp$153$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$152$var152, cv$temp$153$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$152$var152, cv$temp$153$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$152$var152, cv$temp$153$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$152$var152, cv$temp$153$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value100) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$152$var152, cv$temp$153$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value100);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int var86 = 0; var86 < noStates; var86 += 1) {
																		for(int var82 = 0; var82 < noServers; var82 += 1) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var82 == server)) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var86 == traceTempVariable$currentState$50_1)) {
																							int traceTempVariable$currentState$106_1 = cv$currentValue;
																							if((index$sample$2 == sample$var120)) {
																								if((index$timeStep$1 == timeStep$var140)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$106_1)) {
																															{
																																{
																																	double cv$temp$26$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$106_1];
																																		cv$temp$26$var152 = var152;
																																	}
																																	double cv$temp$27$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$106_1];
																																		cv$temp$27$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$90$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$106_1];
																																		cv$temp$90$var152 = var152;
																																	}
																																	double cv$temp$91$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$106_1];
																																		cv$temp$91$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$90$var152, cv$temp$91$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$90$var152, cv$temp$91$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$90$var152, cv$temp$91$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$90$var152, cv$temp$91$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$90$var152, cv$temp$91$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$154$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$106_1];
																																		cv$temp$154$var152 = var152;
																																	}
																																	double cv$temp$155$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$106_1];
																																		cv$temp$155$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$154$var152, cv$temp$155$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$154$var152, cv$temp$155$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$154$var152, cv$temp$155$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$154$var152, cv$temp$155$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$154$var152, cv$temp$155$var154)));
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
																							for(int index$sample$107 = 0; index$sample$107 < noSamples; index$sample$107 += 1) {
																								for(int index$timeStep$108 = 1; index$timeStep$108 < length$metric[index$sample$107][0]; index$timeStep$108 += 1) {
																									if(!((index$sample$107 == index$sample$2) && (index$timeStep$108 == index$timeStep$1))) {
																										for(int index$sample61$109 = 0; index$sample61$109 < noStates; index$sample61$109 += 1) {
																											int distributionTempVariable$var57$111 = index$sample61$109;
																											double cv$probabilitySample61Value110 = (1.0 * distribution$sample61[((index$sample$107 - 0) / 1)][((index$timeStep$108 - 1) / 1)][index$sample61$109]);
																											int traceTempVariable$currentState$112_1 = distributionTempVariable$var57$111;
																											if((index$sample$107 == sample$var120)) {
																												if((index$timeStep$108 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$112_1)) {
																																			{
																																				{
																																					double cv$temp$28$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$112_1];
																																						cv$temp$28$var152 = var152;
																																					}
																																					double cv$temp$29$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$112_1];
																																						cv$temp$29$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value110);
																																				}
																																				{
																																					double cv$temp$92$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$112_1];
																																						cv$temp$92$var152 = var152;
																																					}
																																					double cv$temp$93$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$112_1];
																																						cv$temp$93$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$92$var152, cv$temp$93$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$92$var152, cv$temp$93$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$92$var152, cv$temp$93$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$92$var152, cv$temp$93$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$92$var152, cv$temp$93$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value110);
																																				}
																																				{
																																					double cv$temp$156$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$112_1];
																																						cv$temp$156$var152 = var152;
																																					}
																																					double cv$temp$157$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$112_1];
																																						cv$temp$157$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$156$var152, cv$temp$157$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$156$var152, cv$temp$157$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$156$var152, cv$temp$157$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$156$var152, cv$temp$157$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value110) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$156$var152, cv$temp$157$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value110);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
									int traceTempVariable$currentState$54_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
															guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	if(fixedFlag$sample48) {
																		for(int index$sample$171_1 = 0; index$sample$171_1 < noSamples; index$sample$171_1 += 1) {
																			if((index$sample$171_1 == sample$var120)) {
																				if((0 == timeStep$var140)) {
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$54_1)) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$54_1)) {
																																	{
																																		{
																																			double cv$temp$54$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$54_1];
																																				cv$temp$54$var152 = var152;
																																			}
																																			double cv$temp$55$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$54_1];
																																				cv$temp$55$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$118$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$54_1];
																																				cv$temp$118$var152 = var152;
																																			}
																																			double cv$temp$119$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$54_1];
																																				cv$temp$119$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$118$var152, cv$temp$119$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$118$var152, cv$temp$119$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$118$var152, cv$temp$119$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$118$var152, cv$temp$119$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$118$var152, cv$temp$119$var154)));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																		}
																																		{
																																			double cv$temp$182$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$54_1];
																																				cv$temp$182$var152 = var152;
																																			}
																																			double cv$temp$183$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$54_1];
																																				cv$temp$183$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$182$var152, cv$temp$183$var154)) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$182$var152, cv$temp$183$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$182$var152, cv$temp$183$var154));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$182$var152, cv$temp$183$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$182$var152, cv$temp$183$var154)));
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
																				}
																			}
																		}
																	} else {
																		for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																			if(true) {
																				for(int index$sample48$173 = 0; index$sample48$173 < noStates; index$sample48$173 += 1) {
																					int distributionTempVariable$var44$175 = index$sample48$173;
																					double cv$probabilitySample48Value174 = (1.0 * distribution$sample48[((index$sample$172 - 0) / 1)][index$sample48$173]);
																					int traceTempVariable$currentState$176_1 = distributionTempVariable$var44$175;
																					if((index$sample$172 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$176_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$176_1)) {
																																			{
																																				{
																																					double cv$temp$56$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																						cv$temp$56$var152 = var152;
																																					}
																																					double cv$temp$57$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																						cv$temp$57$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value174);
																																				}
																																				{
																																					double cv$temp$120$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																						cv$temp$120$var152 = var152;
																																					}
																																					double cv$temp$121$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																						cv$temp$121$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$120$var152, cv$temp$121$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$120$var152, cv$temp$121$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$120$var152, cv$temp$121$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$120$var152, cv$temp$121$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$120$var152, cv$temp$121$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value174);
																																				}
																																				{
																																					double cv$temp$184$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																						cv$temp$184$var152 = var152;
																																					}
																																					double cv$temp$185$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																						cv$temp$185$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$184$var152, cv$temp$185$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$184$var152, cv$temp$185$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$184$var152, cv$temp$185$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$184$var152, cv$temp$185$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value174) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$184$var152, cv$temp$185$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value174);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
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
																	if((index$sample$2 == sample$var120)) {
																		if((index$timeStep$1 == timeStep$var140)) {
																			for(int var86 = 0; var86 < noStates; var86 += 1) {
																				for(int var82 = 0; var82 < noServers; var82 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$181_1)) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$181_1)) {
																															{
																																{
																																	double cv$temp$58$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																		cv$temp$58$var152 = var152;
																																	}
																																	double cv$temp$59$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																		cv$temp$59$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$122$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																		cv$temp$122$var152 = var152;
																																	}
																																	double cv$temp$123$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																		cv$temp$123$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$122$var152, cv$temp$123$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$122$var152, cv$temp$123$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$122$var152, cv$temp$123$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$122$var152, cv$temp$123$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$122$var152, cv$temp$123$var154)));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																}
																																{
																																	double cv$temp$186$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																		cv$temp$186$var152 = var152;
																																	}
																																	double cv$temp$187$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																		cv$temp$187$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$186$var152, cv$temp$187$var154)) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$186$var152, cv$temp$187$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$186$var152, cv$temp$187$var154));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$186$var152, cv$temp$187$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$186$var152, cv$temp$187$var154)));
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
																		}
																	}
																	for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																		for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182][0]; index$timeStep$183 += 1) {
																			if(!((index$sample$182 == index$sample$2) && (index$timeStep$183 == index$timeStep$1))) {
																				for(int index$sample61$184 = 0; index$sample61$184 < noStates; index$sample61$184 += 1) {
																					int distributionTempVariable$var57$186 = index$sample61$184;
																					double cv$probabilitySample61Value185 = (1.0 * distribution$sample61[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample61$184]);
																					int traceTempVariable$currentState$187_1 = distributionTempVariable$var57$186;
																					if((index$sample$182 == sample$var120)) {
																						if((index$timeStep$183 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$187_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$187_1)) {
																																			{
																																				{
																																					double cv$temp$60$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																						cv$temp$60$var152 = var152;
																																					}
																																					double cv$temp$61$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																						cv$temp$61$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value185);
																																				}
																																				{
																																					double cv$temp$124$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																						cv$temp$124$var152 = var152;
																																					}
																																					double cv$temp$125$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																						cv$temp$125$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$124$var152, cv$temp$125$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$124$var152, cv$temp$125$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$124$var152, cv$temp$125$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$124$var152, cv$temp$125$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$124$var152, cv$temp$125$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value185);
																																				}
																																				{
																																					double cv$temp$188$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																						cv$temp$188$var152 = var152;
																																					}
																																					double cv$temp$189$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																						cv$temp$189$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$188$var152, cv$temp$189$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$188$var152, cv$temp$189$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$188$var152, cv$temp$189$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$188$var152, cv$temp$189$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value185) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$188$var152, cv$temp$189$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value185);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
				for(int index$timeStep$13 = 1; index$timeStep$13 < length$metric[index$sample$12][0]; index$timeStep$13 += 1) {
					if(!((index$sample$12 == index$sample$2) && (index$timeStep$13 == index$timeStep$1))) {
						for(int index$sample61$14 = 0; index$sample61$14 < noStates; index$sample61$14 += 1) {
							int distributionTempVariable$var57$16 = index$sample61$14;
							double cv$probabilitySample61Value15 = (1.0 * distribution$sample61[((index$sample$12 - 0) / 1)][((index$timeStep$13 - 1) / 1)][index$sample61$14]);
							int traceTempVariable$var54$17_1 = distributionTempVariable$var57$16;
							if((index$sample$12 == sample$var32)) {
								if((index$timeStep$13 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$17_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample61Value15);
											double[] cv$temp$3$var55;
											{
												double[] var55 = m[traceTempVariable$var54$17_1];
												cv$temp$3$var55 = var55;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample61Value15) + DistributionSampling.logProbabilityCategorical(cv$currentValue, cv$temp$3$var55));
											{
												{
													int traceTempVariable$var54$23_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$27_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var114 = 0; var114 < noStates; var114 += 1) {
																				for(int var110 = 0; var110 < noServers; var110 += 1) {
																					if((var110 == server)) {
																						if((var114 == traceTempVariable$currentState$27_1)) {
																							{
																								{
																									double cv$temp$7$var144;
																									{
																										double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$27_1];
																										cv$temp$7$var144 = var144;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], cv$temp$7$var144)));
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
											{
												{
													boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$51_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$51_1)) {
																											if(fixedFlag$sample48) {
																												for(int index$sample$116_1 = 0; index$sample$116_1 < noSamples; index$sample$116_1 += 1) {
																													if((index$sample$116_1 == sample$var120)) {
																														if((0 == timeStep$var140)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$51_1)) {
																																					{
																																						{
																																							double cv$temp$30$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$51_1];
																																								cv$temp$30$var152 = var152;
																																							}
																																							double cv$temp$31$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$51_1];
																																								cv$temp$31$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																						{
																																							double cv$temp$94$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$51_1];
																																								cv$temp$94$var152 = var152;
																																							}
																																							double cv$temp$95$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$51_1];
																																								cv$temp$95$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$94$var152, cv$temp$95$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$94$var152, cv$temp$95$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$94$var152, cv$temp$95$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$94$var152, cv$temp$95$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$94$var152, cv$temp$95$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																						{
																																							double cv$temp$158$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$51_1];
																																								cv$temp$158$var152 = var152;
																																							}
																																							double cv$temp$159$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$51_1];
																																								cv$temp$159$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$158$var152, cv$temp$159$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$158$var152, cv$temp$159$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$158$var152, cv$temp$159$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$158$var152, cv$temp$159$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$158$var152, cv$temp$159$var154)));
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
																												for(int index$sample$117 = 0; index$sample$117 < noSamples; index$sample$117 += 1) {
																													if(true) {
																														for(int index$sample48$118 = 0; index$sample48$118 < noStates; index$sample48$118 += 1) {
																															int distributionTempVariable$var44$120 = index$sample48$118;
																															double cv$probabilitySample48Value119 = (1.0 * distribution$sample48[((index$sample$117 - 0) / 1)][index$sample48$118]);
																															int traceTempVariable$currentState$121_1 = distributionTempVariable$var44$120;
																															if((index$sample$117 == sample$var120)) {
																																if((0 == timeStep$var140)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$121_1)) {
																																							{
																																								{
																																									double cv$temp$32$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$121_1];
																																										cv$temp$32$var152 = var152;
																																									}
																																									double cv$temp$33$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$121_1];
																																										cv$temp$33$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value119);
																																								}
																																								{
																																									double cv$temp$96$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$121_1];
																																										cv$temp$96$var152 = var152;
																																									}
																																									double cv$temp$97$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$121_1];
																																										cv$temp$97$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$96$var152, cv$temp$97$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$96$var152, cv$temp$97$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$96$var152, cv$temp$97$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$96$var152, cv$temp$97$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$96$var152, cv$temp$97$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value119);
																																								}
																																								{
																																									double cv$temp$160$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$121_1];
																																										cv$temp$160$var152 = var152;
																																									}
																																									double cv$temp$161$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$121_1];
																																										cv$temp$161$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$160$var152, cv$temp$161$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$160$var152, cv$temp$161$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$160$var152, cv$temp$161$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$160$var152, cv$temp$161$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value119) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$160$var152, cv$temp$161$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value119);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int var86 = 0; var86 < noStates; var86 += 1) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$51_1)) {
																											int traceTempVariable$currentState$125_1 = cv$currentValue;
																											if((index$sample$2 == sample$var120)) {
																												if((index$timeStep$1 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$125_1)) {
																																			{
																																				{
																																					double cv$temp$34$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$125_1];
																																						cv$temp$34$var152 = var152;
																																					}
																																					double cv$temp$35$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$125_1];
																																						cv$temp$35$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$98$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$125_1];
																																						cv$temp$98$var152 = var152;
																																					}
																																					double cv$temp$99$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$125_1];
																																						cv$temp$99$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$98$var152, cv$temp$99$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$98$var152, cv$temp$99$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$98$var152, cv$temp$99$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$98$var152, cv$temp$99$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$98$var152, cv$temp$99$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$162$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$125_1];
																																						cv$temp$162$var152 = var152;
																																					}
																																					double cv$temp$163$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$125_1];
																																						cv$temp$163$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$162$var152, cv$temp$163$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$162$var152, cv$temp$163$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$162$var152, cv$temp$163$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$162$var152, cv$temp$163$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$162$var152, cv$temp$163$var154)));
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
																											int traceTempVariable$currentState$126_1 = distributionTempVariable$var57$16;
																											if((index$sample$12 == sample$var120)) {
																												if((index$timeStep$13 == timeStep$var140)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$126_1)) {
																																			{
																																				{
																																					double cv$temp$36$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$126_1];
																																						cv$temp$36$var152 = var152;
																																					}
																																					double cv$temp$37$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$126_1];
																																						cv$temp$37$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$100$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$126_1];
																																						cv$temp$100$var152 = var152;
																																					}
																																					double cv$temp$101$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$126_1];
																																						cv$temp$101$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$100$var152, cv$temp$101$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$100$var152, cv$temp$101$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$100$var152, cv$temp$101$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$100$var152, cv$temp$101$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$100$var152, cv$temp$101$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$164$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$126_1];
																																						cv$temp$164$var152 = var152;
																																					}
																																					double cv$temp$165$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$126_1];
																																						cv$temp$165$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$164$var152, cv$temp$165$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$164$var152, cv$temp$165$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$164$var152, cv$temp$165$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$164$var152, cv$temp$165$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$164$var152, cv$temp$165$var154)));
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
																											for(int index$sample$127 = 0; index$sample$127 < noSamples; index$sample$127 += 1) {
																												for(int index$timeStep$128 = 1; index$timeStep$128 < length$metric[index$sample$127][0]; index$timeStep$128 += 1) {
																													if((!((index$sample$127 == index$sample$2) && (index$timeStep$128 == index$timeStep$1)) && !((index$sample$127 == index$sample$12) && (index$timeStep$128 == index$timeStep$13)))) {
																														for(int index$sample61$129 = 0; index$sample61$129 < noStates; index$sample61$129 += 1) {
																															int distributionTempVariable$var57$131 = index$sample61$129;
																															double cv$probabilitySample61Value130 = (1.0 * distribution$sample61[((index$sample$127 - 0) / 1)][((index$timeStep$128 - 1) / 1)][index$sample61$129]);
																															int traceTempVariable$currentState$132_1 = distributionTempVariable$var57$131;
																															if((index$sample$127 == sample$var120)) {
																																if((index$timeStep$128 == timeStep$var140)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$132_1)) {
																																							{
																																								{
																																									double cv$temp$38$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$132_1];
																																										cv$temp$38$var152 = var152;
																																									}
																																									double cv$temp$39$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$132_1];
																																										cv$temp$39$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value130);
																																								}
																																								{
																																									double cv$temp$102$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$132_1];
																																										cv$temp$102$var152 = var152;
																																									}
																																									double cv$temp$103$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$132_1];
																																										cv$temp$103$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$102$var152, cv$temp$103$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$102$var152, cv$temp$103$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$102$var152, cv$temp$103$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$102$var152, cv$temp$103$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$102$var152, cv$temp$103$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value130);
																																								}
																																								{
																																									double cv$temp$166$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$132_1];
																																										cv$temp$166$var152 = var152;
																																									}
																																									double cv$temp$167$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$132_1];
																																										cv$temp$167$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$166$var152, cv$temp$167$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$166$var152, cv$temp$167$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$166$var152, cv$temp$167$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$166$var152, cv$temp$167$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value130) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$166$var152, cv$temp$167$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value130);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
													int traceTempVariable$currentState$55_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample61gaussian163[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample48) {
																						for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																							if((index$sample$192_1 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var82 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var86 == traceTempVariable$currentState$55_1)) {
																															for(int var100 = 0; var100 < noStates; var100 += 1) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$55_1)) {
																																					{
																																						{
																																							double cv$temp$62$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$55_1];
																																								cv$temp$62$var152 = var152;
																																							}
																																							double cv$temp$63$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$55_1];
																																								cv$temp$63$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																						{
																																							double cv$temp$126$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$55_1];
																																								cv$temp$126$var152 = var152;
																																							}
																																							double cv$temp$127$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$55_1];
																																								cv$temp$127$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$126$var152, cv$temp$127$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$126$var152, cv$temp$127$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$126$var152, cv$temp$127$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$126$var152, cv$temp$127$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$126$var152, cv$temp$127$var154)));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																						}
																																						{
																																							double cv$temp$190$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$55_1];
																																								cv$temp$190$var152 = var152;
																																							}
																																							double cv$temp$191$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$55_1];
																																								cv$temp$191$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$190$var152, cv$temp$191$var154)) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$190$var152, cv$temp$191$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$190$var152, cv$temp$191$var154));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$190$var152, cv$temp$191$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$190$var152, cv$temp$191$var154)));
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
																								}
																							}
																						}
																					} else {
																						for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																							if(true) {
																								for(int index$sample48$194 = 0; index$sample48$194 < noStates; index$sample48$194 += 1) {
																									int distributionTempVariable$var44$196 = index$sample48$194;
																									double cv$probabilitySample48Value195 = (1.0 * distribution$sample48[((index$sample$193 - 0) / 1)][index$sample48$194]);
																									int traceTempVariable$currentState$197_1 = distributionTempVariable$var44$196;
																									if((index$sample$193 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$197_1)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$197_1)) {
																																							{
																																								{
																																									double cv$temp$64$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$197_1];
																																										cv$temp$64$var152 = var152;
																																									}
																																									double cv$temp$65$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$197_1];
																																										cv$temp$65$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value195);
																																								}
																																								{
																																									double cv$temp$128$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$197_1];
																																										cv$temp$128$var152 = var152;
																																									}
																																									double cv$temp$129$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$197_1];
																																										cv$temp$129$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$128$var152, cv$temp$129$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$128$var152, cv$temp$129$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$128$var152, cv$temp$129$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$128$var152, cv$temp$129$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$128$var152, cv$temp$129$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value195);
																																								}
																																								{
																																									double cv$temp$192$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$197_1];
																																										cv$temp$192$var152 = var152;
																																									}
																																									double cv$temp$193$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$197_1];
																																										cv$temp$193$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$192$var152, cv$temp$193$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$192$var152, cv$temp$193$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$192$var152, cv$temp$193$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$192$var152, cv$temp$193$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value195) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$192$var152, cv$temp$193$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value195);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
																					if((index$sample$2 == sample$var120)) {
																						if((index$timeStep$1 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$202_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$202_1)) {
																																			{
																																				{
																																					double cv$temp$66$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																						cv$temp$66$var152 = var152;
																																					}
																																					double cv$temp$67$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																						cv$temp$67$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$130$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																						cv$temp$130$var152 = var152;
																																					}
																																					double cv$temp$131$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																						cv$temp$131$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$130$var152, cv$temp$131$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$130$var152, cv$temp$131$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$130$var152, cv$temp$131$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$130$var152, cv$temp$131$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$130$var152, cv$temp$131$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$194$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																						cv$temp$194$var152 = var152;
																																					}
																																					double cv$temp$195$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																						cv$temp$195$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$194$var152, cv$temp$195$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$194$var152, cv$temp$195$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$194$var152, cv$temp$195$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$194$var152, cv$temp$195$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$194$var152, cv$temp$195$var154)));
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
																						}
																					}
																					int traceTempVariable$currentState$203_1 = distributionTempVariable$var57$16;
																					if((index$sample$12 == sample$var120)) {
																						if((index$timeStep$13 == timeStep$var140)) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$203_1)) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														for(int var96 = 0; var96 < noServers; var96 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$203_1)) {
																																			{
																																				{
																																					double cv$temp$68$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$203_1];
																																						cv$temp$68$var152 = var152;
																																					}
																																					double cv$temp$69$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$203_1];
																																						cv$temp$69$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$132$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$203_1];
																																						cv$temp$132$var152 = var152;
																																					}
																																					double cv$temp$133$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$203_1];
																																						cv$temp$133$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$132$var152, cv$temp$133$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$132$var152, cv$temp$133$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$132$var152, cv$temp$133$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$132$var152, cv$temp$133$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$132$var152, cv$temp$133$var154)));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																																				}
																																				{
																																					double cv$temp$196$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$203_1];
																																						cv$temp$196$var152 = var152;
																																					}
																																					double cv$temp$197$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$203_1];
																																						cv$temp$197$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$196$var152, cv$temp$197$var154)) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$196$var152, cv$temp$197$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$196$var152, cv$temp$197$var154));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$196$var152, cv$temp$197$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$196$var152, cv$temp$197$var154)));
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
																						}
																					}
																					for(int index$sample$204 = 0; index$sample$204 < noSamples; index$sample$204 += 1) {
																						for(int index$timeStep$205 = 1; index$timeStep$205 < length$metric[index$sample$204][0]; index$timeStep$205 += 1) {
																							if((!((index$sample$204 == index$sample$2) && (index$timeStep$205 == index$timeStep$1)) && !((index$sample$204 == index$sample$12) && (index$timeStep$205 == index$timeStep$13)))) {
																								for(int index$sample61$206 = 0; index$sample61$206 < noStates; index$sample61$206 += 1) {
																									int distributionTempVariable$var57$208 = index$sample61$206;
																									double cv$probabilitySample61Value207 = (1.0 * distribution$sample61[((index$sample$204 - 0) / 1)][((index$timeStep$205 - 1) / 1)][index$sample61$206]);
																									int traceTempVariable$currentState$209_1 = distributionTempVariable$var57$208;
																									if((index$sample$204 == sample$var120)) {
																										if((index$timeStep$205 == timeStep$var140)) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$209_1)) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		for(int var96 = 0; var96 < noServers; var96 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$209_1)) {
																																							{
																																								{
																																									double cv$temp$70$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$209_1];
																																										cv$temp$70$var152 = var152;
																																									}
																																									double cv$temp$71$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$209_1];
																																										cv$temp$71$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value207);
																																								}
																																								{
																																									double cv$temp$134$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$209_1];
																																										cv$temp$134$var152 = var152;
																																									}
																																									double cv$temp$135$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$209_1];
																																										cv$temp$135$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$134$var152, cv$temp$135$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$134$var152, cv$temp$135$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$134$var152, cv$temp$135$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$134$var152, cv$temp$135$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$134$var152, cv$temp$135$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value207);
																																								}
																																								{
																																									double cv$temp$198$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$209_1];
																																										cv$temp$198$var152 = var152;
																																									}
																																									double cv$temp$199$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$209_1];
																																										cv$temp$199$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$198$var152, cv$temp$199$var154)) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$198$var152, cv$temp$199$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$198$var152, cv$temp$199$var154));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$198$var152, cv$temp$199$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value207) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$198$var152, cv$temp$199$var154)));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value207);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
					int traceTempVariable$var54$312_1 = cv$currentValue;
					for(int index$sample$312_2 = 0; index$sample$312_2 < noSamples; index$sample$312_2 += 1) {
						if((sample$var32 == index$sample$312_2)) {
							for(int index$timeStep$312_3 = 1; index$timeStep$312_3 < length$metric[index$sample$312_2][0]; index$timeStep$312_3 += 1) {
								if((timeStep$var49 == (index$timeStep$312_3 - 1))) {
									{
										int index$timeStep$314 = index$timeStep$312_3;
										int index$sample$315 = index$sample$312_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var26 = 0; var26 < noStates; var26 += 1) {
											if((var26 == traceTempVariable$var54$312_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample48) {
														for(int index$sample$317_1 = 0; index$sample$317_1 < noSamples; index$sample$317_1 += 1) {
															if((index$sample$317_1 == sample$var32)) {
																if((0 == (timeStep$var49 - 1))) {
																	for(int index$var26$323_1 = 0; index$var26$323_1 < noStates; index$var26$323_1 += 1) {
																		if((index$var26$323_1 == st[sample$var32][(timeStep$var49 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$318 = 0; index$sample$318 < noSamples; index$sample$318 += 1) {
															if(true) {
																for(int index$sample48$319 = 0; index$sample48$319 < noStates; index$sample48$319 += 1) {
																	int distributionTempVariable$var44$321 = index$sample48$319;
																	double cv$probabilitySample48Value320 = (1.0 * distribution$sample48[((index$sample$318 - 0) / 1)][index$sample48$319]);
																	int traceTempVariable$var54$322_1 = distributionTempVariable$var44$321;
																	if((index$sample$318 == sample$var32)) {
																		if((0 == (timeStep$var49 - 1))) {
																			for(int index$var26$324_1 = 0; index$var26$324_1 < noStates; index$var26$324_1 += 1) {
																				if((index$var26$324_1 == traceTempVariable$var54$322_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample48Value320);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var54$325_1 = cv$currentValue;
													if((index$sample$2 == sample$var32)) {
														if((index$timeStep$1 == (timeStep$var49 - 1))) {
															for(int index$var26$332_1 = 0; index$var26$332_1 < noStates; index$var26$332_1 += 1) {
																if((index$var26$332_1 == traceTempVariable$var54$325_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$326 = 0; index$sample$326 < noSamples; index$sample$326 += 1) {
														for(int index$timeStep$327 = 1; index$timeStep$327 < length$metric[index$sample$326][0]; index$timeStep$327 += 1) {
															if((!((index$sample$326 == index$sample$2) && (index$timeStep$327 == index$timeStep$1)) && !((index$sample$326 == index$sample$315) && (index$timeStep$327 == index$timeStep$314)))) {
																for(int index$sample61$328 = 0; index$sample61$328 < noStates; index$sample61$328 += 1) {
																	int distributionTempVariable$var57$330 = index$sample61$328;
																	double cv$probabilitySample61Value329 = (1.0 * distribution$sample61[((index$sample$326 - 0) / 1)][((index$timeStep$327 - 1) / 1)][index$sample61$328]);
																	int traceTempVariable$var54$331_1 = distributionTempVariable$var57$330;
																	if((index$sample$326 == sample$var32)) {
																		if((index$timeStep$327 == (timeStep$var49 - 1))) {
																			for(int index$var26$333_1 = 0; index$var26$333_1 < noStates; index$var26$333_1 += 1) {
																				if((index$var26$333_1 == traceTempVariable$var54$331_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample61Value329);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$200$var55;
													{
														double[] var55 = m[traceTempVariable$var54$312_1];
														cv$temp$200$var55 = var55;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$200$var55);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample61[((index$sample$312_2 - 0) / 1)][((index$timeStep$312_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
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

	private final void sample92(int var82, int var86) {
		double cv$originalValue = current_metric_mean[var82][var86];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = DistributionSampling.sampleGaussian(RNG$, cv$originalValue, cv$var);
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
					double var87 = cv$proposedValue;
					double[] var83 = current_metric_mean[var82];
					var83[var86] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var76;
				{
					cv$temp$0$var76 = 0.0;
				}
				double cv$temp$1$var77;
				{
					cv$temp$1$var77 = max_metric;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityUniform(cv$currentValue, cv$temp$0$var76, cv$temp$1$var77));
				{
					{
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample48) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													double traceTempVariable$var152$10_1 = cv$currentValue;
													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
														if((var82 == server)) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var86 == st[sample$var120][timeStep$var140])) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				if((index$sample$28_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == st[sample$var120][timeStep$var140])) {
																												{
																													{
																														double cv$temp$2$var152;
																														{
																															double var152 = traceTempVariable$var152$10_1;
																															cv$temp$2$var152 = var152;
																														}
																														double cv$temp$3$var154;
																														{
																															double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																															cv$temp$3$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$2$var152, cv$temp$3$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$30$var152;
																														{
																															double var152 = traceTempVariable$var152$10_1;
																															cv$temp$30$var152 = var152;
																														}
																														double cv$temp$31$var154;
																														{
																															double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																															cv$temp$31$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$30$var152, cv$temp$31$var154)));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																													}
																													{
																														double cv$temp$58$var152;
																														{
																															double var152 = traceTempVariable$var152$10_1;
																															cv$temp$58$var152 = var152;
																														}
																														double cv$temp$59$var154;
																														{
																															double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																															cv$temp$59$var154 = var154;
																														}
																														if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$58$var152, cv$temp$59$var154)));
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
																			if(fixedFlag$sample61) {
																				for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30_1][0]; timeStep$var49 += 1) {
																						if((index$sample$30_1 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$4$var152;
																																{
																																	double var152 = traceTempVariable$var152$10_1;
																																	cv$temp$4$var152 = var152;
																																}
																																double cv$temp$5$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$5$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$4$var152, cv$temp$5$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$32$var152;
																																{
																																	double var152 = traceTempVariable$var152$10_1;
																																	cv$temp$32$var152 = var152;
																																}
																																double cv$temp$33$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$33$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$32$var152, cv$temp$33$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$60$var152;
																																{
																																	double var152 = traceTempVariable$var152$10_1;
																																	cv$temp$60$var152 = var152;
																																}
																																double cv$temp$61$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$61$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$60$var152, cv$temp$61$var154)));
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
																				for(int index$sample$31 = 0; index$sample$31 < noSamples; index$sample$31 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$31][0]; timeStep$var49 += 1) {
																						if(true) {
																							for(int index$sample61$33 = 0; index$sample61$33 < noStates; index$sample61$33 += 1) {
																								int distributionTempVariable$var57$35 = index$sample61$33;
																								double cv$probabilitySample61Value34 = (1.0 * distribution$sample61[((index$sample$31 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$33]);
																								int traceTempVariable$currentState$36_1 = distributionTempVariable$var57$35;
																								if((index$sample$31 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$36_1)) {
																																{
																																	{
																																		double cv$temp$6$var152;
																																		{
																																			double var152 = traceTempVariable$var152$10_1;
																																			cv$temp$6$var152 = var152;
																																		}
																																		double cv$temp$7$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$36_1];
																																			cv$temp$7$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$6$var152, cv$temp$7$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value34);
																																	}
																																	{
																																		double cv$temp$34$var152;
																																		{
																																			double var152 = traceTempVariable$var152$10_1;
																																			cv$temp$34$var152 = var152;
																																		}
																																		double cv$temp$35$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$36_1];
																																			cv$temp$35$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$34$var152, cv$temp$35$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value34);
																																	}
																																	{
																																		double cv$temp$62$var152;
																																		{
																																			double var152 = traceTempVariable$var152$10_1;
																																			cv$temp$62$var152 = var152;
																																		}
																																		double cv$temp$63$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$36_1];
																																			cv$temp$63$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value34) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$62$var152, cv$temp$63$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value34);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											if(true) {
												for(int index$sample48$6 = 0; index$sample48$6 < noStates; index$sample48$6 += 1) {
													int distributionTempVariable$var44$8 = index$sample48$6;
													double cv$probabilitySample48Value7 = (1.0 * distribution$sample48[((sample$var32 - 0) / 1)][index$sample48$6]);
													int traceTempVariable$currentState$9_1 = distributionTempVariable$var44$8;
													if((sample$var32 == sample$var120)) {
														if((0 == timeStep$var140)) {
															double traceTempVariable$var152$11_1 = cv$currentValue;
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == traceTempVariable$currentState$9_1)) {
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					int traceTempVariable$currentState$39_1 = distributionTempVariable$var44$8;
																					if((sample$var32 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == traceTempVariable$currentState$39_1)) {
																													{
																														{
																															double cv$temp$8$var152;
																															{
																																double var152 = traceTempVariable$var152$11_1;
																																cv$temp$8$var152 = var152;
																															}
																															double cv$temp$9$var154;
																															{
																																double var154 = current_metric_var[server][traceTempVariable$currentState$39_1];
																																cv$temp$9$var154 = var154;
																															}
																															if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$8$var152, cv$temp$9$var154)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																														}
																														{
																															double cv$temp$36$var152;
																															{
																																double var152 = traceTempVariable$var152$11_1;
																																cv$temp$36$var152 = var152;
																															}
																															double cv$temp$37$var154;
																															{
																																double var154 = current_metric_var[server][traceTempVariable$currentState$39_1];
																																cv$temp$37$var154 = var154;
																															}
																															if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$36$var152, cv$temp$37$var154)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																														}
																														{
																															double cv$temp$64$var152;
																															{
																																double var152 = traceTempVariable$var152$11_1;
																																cv$temp$64$var152 = var152;
																															}
																															double cv$temp$65$var154;
																															{
																																double var154 = current_metric_var[server][traceTempVariable$currentState$39_1];
																																cv$temp$65$var154 = var154;
																															}
																															if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$64$var152, cv$temp$65$var154)));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$40 = 0; index$sample$40 < noSamples; index$sample$40 += 1) {
																						if(!(index$sample$40 == sample$var32)) {
																							for(int index$sample48$41 = 0; index$sample48$41 < noStates; index$sample48$41 += 1) {
																								int distributionTempVariable$var44$43 = index$sample48$41;
																								double cv$probabilitySample48Value42 = (cv$probabilitySample48Value7 * distribution$sample48[((index$sample$40 - 0) / 1)][index$sample48$41]);
																								int traceTempVariable$currentState$44_1 = distributionTempVariable$var44$43;
																								if((index$sample$40 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$44_1)) {
																																{
																																	{
																																		double cv$temp$10$var152;
																																		{
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$10$var152 = var152;
																																		}
																																		double cv$temp$11$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$44_1];
																																			cv$temp$11$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$10$var152, cv$temp$11$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value42);
																																	}
																																	{
																																		double cv$temp$38$var152;
																																		{
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$38$var152 = var152;
																																		}
																																		double cv$temp$39$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$44_1];
																																			cv$temp$39$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$38$var152, cv$temp$39$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value42);
																																	}
																																	{
																																		double cv$temp$66$var152;
																																		{
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$66$var152 = var152;
																																		}
																																		double cv$temp$67$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$44_1];
																																			cv$temp$67$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value42) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$66$var152, cv$temp$67$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value42);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					if(fixedFlag$sample61) {
																						for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																								if((index$sample$47_1 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$9_1)) {
																																{
																																	{
																																		double cv$temp$12$var152;
																																		{
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$12$var152 = var152;
																																		}
																																		double cv$temp$13$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$9_1];
																																			cv$temp$13$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$12$var152, cv$temp$13$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																																	}
																																	{
																																		double cv$temp$40$var152;
																																		{
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$40$var152 = var152;
																																		}
																																		double cv$temp$41$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$9_1];
																																			cv$temp$41$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$40$var152, cv$temp$41$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																																	}
																																	{
																																		double cv$temp$68$var152;
																																		{
																																			double var152 = traceTempVariable$var152$11_1;
																																			cv$temp$68$var152 = var152;
																																		}
																																		double cv$temp$69$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$9_1];
																																			cv$temp$69$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$68$var152, cv$temp$69$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value7);
																																	}
																																}
																															}
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
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48][0]; timeStep$var49 += 1) {
																								if(true) {
																									for(int index$sample61$50 = 0; index$sample61$50 < noStates; index$sample61$50 += 1) {
																										int distributionTempVariable$var57$52 = index$sample61$50;
																										double cv$probabilitySample61Value51 = (cv$probabilitySample48Value7 * distribution$sample61[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$50]);
																										int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																										if((index$sample$48 == sample$var120)) {
																											if((timeStep$var49 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$53_1)) {
																																		{
																																			{
																																				double cv$temp$14$var152;
																																				{
																																					double var152 = traceTempVariable$var152$11_1;
																																					cv$temp$14$var152 = var152;
																																				}
																																				double cv$temp$15$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																					cv$temp$15$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$14$var152, cv$temp$15$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																			}
																																			{
																																				double cv$temp$42$var152;
																																				{
																																					double var152 = traceTempVariable$var152$11_1;
																																					cv$temp$42$var152 = var152;
																																				}
																																				double cv$temp$43$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																					cv$temp$43$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$42$var152, cv$temp$43$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																			}
																																			{
																																				double cv$temp$70$var152;
																																				{
																																					double var152 = traceTempVariable$var152$11_1;
																																					cv$temp$70$var152 = var152;
																																				}
																																				double cv$temp$71$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																					cv$temp$71$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value51) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$70$var152, cv$temp$71$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value51);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample61) {
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														double traceTempVariable$var152$22_1 = cv$currentValue;
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				if(fixedFlag$sample48) {
																					for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																						if((index$sample$56_1 == sample$var120)) {
																							if((0 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$16$var152;
																																{
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$16$var152 = var152;
																																}
																																double cv$temp$17$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$17$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$16$var152, cv$temp$17$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$44$var152;
																																{
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$44$var152 = var152;
																																}
																																double cv$temp$45$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$45$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$44$var152, cv$temp$45$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$72$var152;
																																{
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$72$var152 = var152;
																																}
																																double cv$temp$73$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$73$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$72$var152, cv$temp$73$var154)));
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
																					for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																						if(true) {
																							for(int index$sample48$58 = 0; index$sample48$58 < noStates; index$sample48$58 += 1) {
																								int distributionTempVariable$var44$60 = index$sample48$58;
																								double cv$probabilitySample48Value59 = (1.0 * distribution$sample48[((index$sample$57 - 0) / 1)][index$sample48$58]);
																								int traceTempVariable$currentState$61_1 = distributionTempVariable$var44$60;
																								if((index$sample$57 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$61_1)) {
																																{
																																	{
																																		double cv$temp$18$var152;
																																		{
																																			double var152 = traceTempVariable$var152$22_1;
																																			cv$temp$18$var152 = var152;
																																		}
																																		double cv$temp$19$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$61_1];
																																			cv$temp$19$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$18$var152, cv$temp$19$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value59);
																																	}
																																	{
																																		double cv$temp$46$var152;
																																		{
																																			double var152 = traceTempVariable$var152$22_1;
																																			cv$temp$46$var152 = var152;
																																		}
																																		double cv$temp$47$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$61_1];
																																			cv$temp$47$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$46$var152, cv$temp$47$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value59);
																																	}
																																	{
																																		double cv$temp$74$var152;
																																		{
																																			double var152 = traceTempVariable$var152$22_1;
																																			cv$temp$74$var152 = var152;
																																		}
																																		double cv$temp$75$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$61_1];
																																			cv$temp$75$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value59) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$74$var152, cv$temp$75$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value59);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																					for(int index$timeStep$64_2 = 1; index$timeStep$64_2 < length$metric[index$sample$64_1][0]; index$timeStep$64_2 += 1) {
																						if((index$sample$64_1 == sample$var120)) {
																							if((index$timeStep$64_2 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == st[sample$var120][timeStep$var140])) {
																														{
																															{
																																double cv$temp$20$var152;
																																{
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$20$var152 = var152;
																																}
																																double cv$temp$21$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$21$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$20$var152, cv$temp$21$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$48$var152;
																																{
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$48$var152 = var152;
																																}
																																double cv$temp$49$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$49$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$48$var152, cv$temp$49$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																															}
																															{
																																double cv$temp$76$var152;
																																{
																																	double var152 = traceTempVariable$var152$22_1;
																																	cv$temp$76$var152 = var152;
																																}
																																double cv$temp$77$var154;
																																{
																																	double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																																	cv$temp$77$var154 = var154;
																																}
																																if(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$76$var152, cv$temp$77$var154)));
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
										for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
											for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
												if(true) {
													for(int index$sample61$18 = 0; index$sample61$18 < noStates; index$sample61$18 += 1) {
														int distributionTempVariable$var57$20 = index$sample61$18;
														double cv$probabilitySample61Value19 = (1.0 * distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample61$18]);
														int traceTempVariable$currentState$21_1 = distributionTempVariable$var57$20;
														if((sample$var32 == sample$var120)) {
															if((timeStep$var49 == timeStep$var140)) {
																double traceTempVariable$var152$23_1 = cv$currentValue;
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$21_1)) {
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample48) {
																							for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																								if((index$sample$66_1 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var96 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var100 == traceTempVariable$currentState$21_1)) {
																																{
																																	{
																																		double cv$temp$22$var152;
																																		{
																																			double var152 = traceTempVariable$var152$23_1;
																																			cv$temp$22$var152 = var152;
																																		}
																																		double cv$temp$23$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$21_1];
																																			cv$temp$23$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$22$var152, cv$temp$23$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																																	}
																																	{
																																		double cv$temp$50$var152;
																																		{
																																			double var152 = traceTempVariable$var152$23_1;
																																			cv$temp$50$var152 = var152;
																																		}
																																		double cv$temp$51$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$21_1];
																																			cv$temp$51$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$50$var152, cv$temp$51$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																																	}
																																	{
																																		double cv$temp$78$var152;
																																		{
																																			double var152 = traceTempVariable$var152$23_1;
																																			cv$temp$78$var152 = var152;
																																		}
																																		double cv$temp$79$var154;
																																		{
																																			double var154 = current_metric_var[server][traceTempVariable$currentState$21_1];
																																			cv$temp$79$var154 = var154;
																																		}
																																		if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$78$var152, cv$temp$79$var154)));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																																	}
																																}
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
																								if(true) {
																									for(int index$sample48$68 = 0; index$sample48$68 < noStates; index$sample48$68 += 1) {
																										int distributionTempVariable$var44$70 = index$sample48$68;
																										double cv$probabilitySample48Value69 = (cv$probabilitySample61Value19 * distribution$sample48[((index$sample$67 - 0) / 1)][index$sample48$68]);
																										int traceTempVariable$currentState$71_1 = distributionTempVariable$var44$70;
																										if((index$sample$67 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				double cv$temp$24$var152;
																																				{
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$24$var152 = var152;
																																				}
																																				double cv$temp$25$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																					cv$temp$25$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$24$var152, cv$temp$25$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value69);
																																			}
																																			{
																																				double cv$temp$52$var152;
																																				{
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$52$var152 = var152;
																																				}
																																				double cv$temp$53$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																					cv$temp$53$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$52$var152, cv$temp$53$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value69);
																																			}
																																			{
																																				double cv$temp$80$var152;
																																				{
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$80$var152 = var152;
																																				}
																																				double cv$temp$81$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																					cv$temp$81$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)))) + 1)) + (Math.log(cv$probabilitySample48Value69) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$80$var152, cv$temp$81$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample48Value69);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						int traceTempVariable$currentState$74_1 = distributionTempVariable$var57$20;
																						if((sample$var32 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var96 == server)) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var100 == traceTempVariable$currentState$74_1)) {
																														{
																															{
																																double cv$temp$26$var152;
																																{
																																	double var152 = traceTempVariable$var152$23_1;
																																	cv$temp$26$var152 = var152;
																																}
																																double cv$temp$27$var154;
																																{
																																	double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																	cv$temp$27$var154 = var154;
																																}
																																if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$26$var152, cv$temp$27$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																															}
																															{
																																double cv$temp$54$var152;
																																{
																																	double var152 = traceTempVariable$var152$23_1;
																																	cv$temp$54$var152 = var152;
																																}
																																double cv$temp$55$var154;
																																{
																																	double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																	cv$temp$55$var154 = var154;
																																}
																																if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$54$var152, cv$temp$55$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																															}
																															{
																																double cv$temp$82$var152;
																																{
																																	double var152 = traceTempVariable$var152$23_1;
																																	cv$temp$82$var152 = var152;
																																}
																																double cv$temp$83$var154;
																																{
																																	double var154 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																	cv$temp$83$var154 = var154;
																																}
																																if(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$82$var152, cv$temp$83$var154)));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value19);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int index$sample$75 = 0; index$sample$75 < noSamples; index$sample$75 += 1) {
																							for(int index$timeStep$76 = 1; index$timeStep$76 < length$metric[index$sample$75][0]; index$timeStep$76 += 1) {
																								if(!((index$sample$75 == sample$var32) && (index$timeStep$76 == timeStep$var49))) {
																									for(int index$sample61$77 = 0; index$sample61$77 < noStates; index$sample61$77 += 1) {
																										int distributionTempVariable$var57$79 = index$sample61$77;
																										double cv$probabilitySample61Value78 = (cv$probabilitySample61Value19 * distribution$sample61[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample61$77]);
																										int traceTempVariable$currentState$80_1 = distributionTempVariable$var57$79;
																										if((index$sample$75 == sample$var120)) {
																											if((index$timeStep$76 == timeStep$var140)) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$80_1)) {
																																		{
																																			{
																																				double cv$temp$28$var152;
																																				{
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$28$var152 = var152;
																																				}
																																				double cv$temp$29$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																					cv$temp$29$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$28$var152, cv$temp$29$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value78);
																																			}
																																			{
																																				double cv$temp$56$var152;
																																				{
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$56$var152 = var152;
																																				}
																																				double cv$temp$57$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																					cv$temp$57$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$56$var152, cv$temp$57$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value78);
																																			}
																																			{
																																				double cv$temp$84$var152;
																																				{
																																					double var152 = traceTempVariable$var152$23_1;
																																					cv$temp$84$var152 = var152;
																																				}
																																				double cv$temp$85$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																					cv$temp$85$var154 = var154;
																																				}
																																				if(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)))) + 1)) + (Math.log(cv$probabilitySample61Value78) + DistributionSampling.logProbabilityGaussian(metric_g[sample$var120][server][timeStep$var140], cv$temp$84$var152, cv$temp$85$var154)));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample61Value78);
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$, 0.0, 1.0))) || Double.isNaN(cv$ratio))) {
			double var87 = cv$originalValue;
			double[] var83 = current_metric_mean[var82];
			var83[var86] = var87;
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var21$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var26 = 0; var26 < noStates; var26 += 1)
				cv$max = Math.max(cv$max, noStates);
			cv$var27$countGlobal = new double[cv$max];
		}
		{
			int cv$var28$max = noStates;
			cv$distributionAccumulator$var56 = new double[cv$var28$max];
		}
		{
			cv$var44$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_sample$var120 = 0;
			int cv$max_server = 0;
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, ((length$metric[sample$var120][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var120 = Math.max(cv$max_sample$var120, ((length$metric.length - 0) / 1));
			guard$sample48gaussian163$global = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
		}
		{
			int cv$var28$max = noStates;
			cv$var57$stateProbabilityGlobal = new double[cv$var28$max];
		}
		{
			int cv$max_sample$var120 = 0;
			int cv$max_server = 0;
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, ((length$metric[sample$var120][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var120 = Math.max(cv$max_sample$var120, ((length$metric.length - 0) / 1));
			guard$sample61gaussian163$global = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
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
				for(int var26 = 0; var26 < noStates; var26 += 1)
					m[var26] = new double[noStates];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$metric.length][];
				for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
					st[sample$var32] = new int[length$metric[sample$var32][0]];
			}
		}
		if(!setFlag$metric_g) {
			{
				metric_g = new double[length$metric.length][][];
				for(int var67 = 0; var67 < length$metric.length; var67 += 1) {
					double[][] subarray$0 = new double[length$metric[0].length][];
					metric_g[var67] = subarray$0;
				}
				for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						double[][] subarray$1 = metric_g[sample$var120];
						subarray$1[server] = new double[length$metric[sample$var120][0]];
					}
				}
			}
		}
		if(!setFlag$metric_valid_g) {
			{
				metric_valid_g = new boolean[length$metric.length][][];
				for(int var73 = 0; var73 < length$metric.length; var73 += 1) {
					boolean[][] subarray$0 = new boolean[length$metric[0].length][];
					metric_valid_g[var73] = subarray$0;
				}
				for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						boolean[][] subarray$1 = metric_valid_g[sample$var120];
						subarray$1[server] = new boolean[length$metric[sample$var120][0]];
					}
				}
			}
		}
		if(!setFlag$current_metric_mean) {
			{
				current_metric_mean = new double[length$metric[0].length][];
				for(int var82 = 0; var82 < length$metric[0].length; var82 += 1)
					current_metric_mean[var82] = new double[noStates];
			}
		}
		if(!setFlag$current_metric_var) {
			{
				current_metric_var = new double[length$metric[0].length][];
				for(int var96 = 0; var96 < length$metric[0].length; var96 += 1)
					current_metric_var[var96] = new double[noStates];
			}
		}
		if(!setFlag$current_metric_valid_bias) {
			{
				current_metric_valid_bias = new double[length$metric[0].length][];
				for(int var110 = 0; var110 < length$metric[0].length; var110 += 1)
					current_metric_valid_bias[var110] = new double[noStates];
			}
		}
		{
			distribution$sample61 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)][];
				distribution$sample61[((sample$var32 - 0) / 1)] = subarray$0;
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					subarray$0[((timeStep$var49 - 1) / 1)] = new double[noStates];
			}
		}
		{
			distribution$sample48 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				distribution$sample48[((sample$var32 - 0) / 1)] = new double[noStates];
		}
		{
			logProbability$var43 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample48 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var56 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				logProbability$var56[((sample$var32 - 0) / 1)] = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample61 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				logProbability$sample61[((sample$var32 - 0) / 1)] = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var145 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var145[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample152 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample152[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var155 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var155[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample164 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample164[((sample$var120 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var120][0] - 1) - 0) / 1) + 1)];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			int[] var41 = st[sample$var32];
			if(!fixedFlag$sample48)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample61)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample92)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample106)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample120)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			boolean[][] var135 = metric_valid_g[sample$var120];
			double[][] var131 = metric_g[sample$var120];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = var135[server];
				double[] metric_inner = metric_g[sample$var120][server];
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					if(!fixedFlag$sample152)
						metric_valid_g[sample$var120][server][timeStep$var140] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
						if(!fixedFlag$sample164)
							metric_inner[timeStep$var140] = DistributionSampling.sampleGaussian(RNG$, current_metric_mean[server][st[sample$var120][timeStep$var140]], current_metric_var[server][st[sample$var120][timeStep$var140]]);
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			double[] cv$distribution$sample48 = distribution$sample48[((sample$var32 - 0) / 1)];
			for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1) {
				double cv$value = DistributionSampling.probabilityCategorical(index$var43, initialStateDistribution);
				if(!fixedFlag$sample48)
					cv$distribution$sample48[index$var43] = cv$value;
			}
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				double[] cv$distribution$sample61 = distribution$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample61)
						cv$distribution$sample61[index$var56] = 0.0;
				}
				if(fixedFlag$sample48) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample$var32)) {
							if((0 == (timeStep$var49 - 1))) {
								for(int var26 = 0; var26 < noStates; var26 += 1) {
									if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
										{
											if(!fixedFlag$sample61) {
												double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
												for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
													cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (1.0 * DistributionSampling.probabilityCategorical(index$var56, var55)));
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
							for(int index$sample48$3 = 0; index$sample48$3 < noStates; index$sample48$3 += 1) {
								int distributionTempVariable$var44$5 = index$sample48$3;
								double cv$probabilitySample48Value4 = (1.0 * distribution$sample48[((index$sample$2 - 0) / 1)][index$sample48$3]);
								int traceTempVariable$var54$6_1 = distributionTempVariable$var44$5;
								if((index$sample$2 == sample$var32)) {
									if((0 == (timeStep$var49 - 1))) {
										for(int var26 = 0; var26 < noStates; var26 += 1) {
											if((var26 == traceTempVariable$var54$6_1)) {
												{
													if(!fixedFlag$sample61) {
														double[] var55 = m[traceTempVariable$var54$6_1];
														for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
															cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (cv$probabilitySample48Value4 * DistributionSampling.probabilityCategorical(index$var56, var55)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample61) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample$var32)) {
								if((index$timeStep$9_2 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											{
												if(!fixedFlag$sample61) {
													double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
													for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
														cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (1.0 * DistributionSampling.probabilityCategorical(index$var56, var55)));
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
						for(int index$timeStep$11 = 1; index$timeStep$11 < length$metric[index$sample$10][0]; index$timeStep$11 += 1) {
							if(true) {
								for(int index$sample61$12 = 0; index$sample61$12 < noStates; index$sample61$12 += 1) {
									int distributionTempVariable$var57$14 = index$sample61$12;
									double cv$probabilitySample61Value13 = (1.0 * distribution$sample61[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample61$12]);
									int traceTempVariable$var54$15_1 = distributionTempVariable$var57$14;
									if((index$sample$10 == sample$var32)) {
										if((index$timeStep$11 == (timeStep$var49 - 1))) {
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == traceTempVariable$var54$15_1)) {
													{
														if(!fixedFlag$sample61) {
															double[] var55 = m[traceTempVariable$var54$15_1];
															for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
																cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (cv$probabilitySample61Value13 * DistributionSampling.probabilityCategorical(index$var56, var55)));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				double cv$var56$sum = 0.0;
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample61)
						cv$var56$sum = (cv$var56$sum + cv$distribution$sample61[index$var56]);
				}
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample61)
						cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] / cv$var56$sum);
				}
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample92)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample106)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample120)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			int[] var41 = st[sample$var32];
			if(!fixedFlag$sample48)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample61)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample92)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample106)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample120)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample24)
				sample24();
			for(int var26 = 0; var26 < noStates; var26 += 1) {
				if(!fixedFlag$sample30)
					sample30(var26);
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if(!fixedFlag$sample48)
					sample48(sample$var32);
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					if(!fixedFlag$sample61)
						sample61(sample$var32, timeStep$var49);
				}
			}
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1) {
					if(!fixedFlag$sample92)
						sample92(var82, var86);
				}
			}
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1) {
					if(!fixedFlag$sample106)
						sample106(var96, var100);
				}
			}
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1) {
					if(!fixedFlag$sample120)
						sample120(var110, var114);
				}
			}
		} else {
			for(int var110 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var110 >= ((0 - 1) + 1); var110 -= 1) {
				for(int var114 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var114 >= ((0 - 1) + 1); var114 -= 1) {
					if(!fixedFlag$sample120)
						sample120(var110, var114);
				}
			}
			for(int var96 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var96 >= ((0 - 1) + 1); var96 -= 1) {
				for(int var100 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var100 >= ((0 - 1) + 1); var100 -= 1) {
					if(!fixedFlag$sample106)
						sample106(var96, var100);
				}
			}
			for(int var82 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var82 >= ((0 - 1) + 1); var82 -= 1) {
				for(int var86 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var86 >= ((0 - 1) + 1); var86 -= 1) {
					if(!fixedFlag$sample92)
						sample92(var82, var86);
				}
			}
			for(int sample$var32 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var32 >= ((0 - 1) + 1); sample$var32 -= 1) {
				for(int timeStep$var49 = (length$metric[sample$var32][0] - ((((length$metric[sample$var32][0] - 1) - 1) % 1) + 1)); timeStep$var49 >= ((1 - 1) + 1); timeStep$var49 -= 1) {
					if(!fixedFlag$sample61)
						sample61(sample$var32, timeStep$var49);
				}
				if(!fixedFlag$sample48)
					sample48(sample$var32);
			}
			for(int var26 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var26 >= ((0 - 1) + 1); var26 -= 1) {
				if(!fixedFlag$sample30)
					sample30(var26);
			}
			if(!fixedFlag$sample24)
				sample24();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var17 = 0; var17 < noStates; var17 += 1)
			v[var17] = 0.1;
		noServers = length$metric[0].length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		if(!fixedProbFlag$sample24)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var22 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$var27 = 0.0;
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
			logProbability$var43[((sample$var32 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample48) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				logProbability$sample48[((sample$var32 - 0) / 1)] = 0.0;
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample61) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					logProbability$sample61[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = 0.0;
			}
		}
		logProbability$var78 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample92)
			logProbability$var87 = 0.0;
		logProbability$var92 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample106)
			logProbability$var101 = 0.0;
		logProbability$var106 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample120)
			logProbability$var115 = 0.0;
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample152) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample152[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
			}
		}
		logProbability$var131 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample164) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample164[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample92)
			logProbabilityValue$sample92();
		if(fixedFlag$sample106)
			logProbabilityValue$sample106();
		if(fixedFlag$sample120)
			logProbabilityValue$sample120();
		logProbabilityValue$sample152();
		logProbabilityValue$sample164();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample30();
		logProbabilityDistribution$sample48();
		logProbabilityDistribution$sample61();
		logProbabilityValue$sample92();
		logProbabilityValue$sample106();
		logProbabilityValue$sample120();
		logProbabilityDistribution$sample152();
		logProbabilityDistribution$sample164();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample30();
		logProbabilityValue$sample48();
		logProbabilityValue$sample61();
		logProbabilityValue$sample92();
		logProbabilityValue$sample106();
		logProbabilityValue$sample120();
		logProbabilityValue$sample152();
		logProbabilityValue$sample164();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var26 = 0; var26 < noStates; var26 += 1) {
			double[] var27 = m[var26];
			if(!fixedFlag$sample30)
				DistributionSampling.sampleDirichlet(RNG$, v, var27);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			int[] var41 = st[sample$var32];
			if(!fixedFlag$sample48)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample61)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample92)
					var83[var86] = DistributionSampling.sampleUniform(RNG$, 0.0, max_metric);
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample106)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample120)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			boolean[][][] cv$source1 = metric_valid;
			boolean[][][] cv$target1 = metric_valid_g;
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
		{
			double[][][] cv$source1 = metric;
			double[][][] cv$target1 = metric_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				double[][] cv$source2 = cv$source1[cv$index1];
				double[][] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
					double[] cv$source3 = cv$source2[cv$index2];
					double[] cv$target3 = cv$target2[cv$index2];
					int cv$length3 = cv$target3.length;
					for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
						cv$target3[cv$index3] = cv$source3[cv$index3];
				}
			}
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics4(\n               double[][][] metric,\n               boolean[][][] metric_valid, \n               int max_metric,\n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    //Calculate all the state transitions\n    int[][] st = new int[noSamples][];\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n    }\n    \n    // Calculate the number of servers\n    int noServers = metric[0].length;    \n    \n    // Allocate space for each generated metric.    \n    double[][][] metric_g = new double[noSamples][noServers][];\n    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n\n    // Calculate metric parameters\n    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n    \n    // Compute the values of each metric\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        for(int server = 0; server < noServers; server++) {\n            //Allocate space for the time series\n            double[] metric_inner = new double[streamLength];\n            metric_g[sample][server] = metric_inner;\n            \n            boolean[] metric_valid_inner = new boolean[streamLength];\n            metric_valid_g[sample][server] = metric_valid_inner;\n            \n            //Generate values.\n            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n                int currentState = st[sample][timeStep];\n                \n                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n                if(metric_valid_inner[timeStep])\n                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n            }\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n    metric_g.observe(metric);\n}";
	}
}