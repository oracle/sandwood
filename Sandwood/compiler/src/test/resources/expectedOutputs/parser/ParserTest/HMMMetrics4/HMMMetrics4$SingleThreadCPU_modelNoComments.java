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
	private double[][] distribution$sample50;
	private double[][][] distribution$sample63;
	private boolean fixedFlag$sample111 = false;
	private boolean fixedFlag$sample126 = false;
	private boolean fixedFlag$sample161 = false;
	private boolean fixedFlag$sample173 = false;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample50 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedFlag$sample96 = false;
	private boolean fixedProbFlag$sample111 = false;
	private boolean fixedProbFlag$sample126 = false;
	private boolean fixedProbFlag$sample161 = false;
	private boolean fixedProbFlag$sample173 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample50 = false;
	private boolean fixedProbFlag$sample63 = false;
	private boolean fixedProbFlag$sample96 = false;
	private boolean[][][] guard$sample50gaussian172$global;
	private boolean[][][] guard$sample63gaussian172$global;
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
	private double[][][] logProbability$sample161;
	private double[][][] logProbability$sample173;
	private double[] logProbability$sample50;
	private double[][] logProbability$sample63;
	private double logProbability$st;
	private double logProbability$var101;
	private double logProbability$var106;
	private double logProbability$var115;
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
		fixedProbFlag$sample96 = false;
		fixedProbFlag$sample173 = false;
	}

	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value) {
		current_metric_valid_bias = cv$value;
		setFlag$current_metric_valid_bias = true;
		fixedProbFlag$sample126 = false;
		fixedProbFlag$sample161 = false;
	}

	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	@Override
	public final void set$current_metric_var(double[][] cv$value) {
		current_metric_var = cv$value;
		setFlag$current_metric_var = true;
		fixedProbFlag$sample111 = false;
		fixedProbFlag$sample173 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample111() {
		return fixedFlag$sample111;
	}

	@Override
	public final void set$fixedFlag$sample111(boolean cv$value) {
		fixedFlag$sample111 = cv$value;
		fixedProbFlag$sample111 = (fixedFlag$sample111 && fixedProbFlag$sample111);
		fixedProbFlag$sample173 = (fixedFlag$sample111 && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	@Override
	public final void set$fixedFlag$sample126(boolean cv$value) {
		fixedFlag$sample126 = cv$value;
		fixedProbFlag$sample126 = (fixedFlag$sample126 && fixedProbFlag$sample126);
		fixedProbFlag$sample161 = (fixedFlag$sample126 && fixedProbFlag$sample161);
	}

	@Override
	public final boolean get$fixedFlag$sample161() {
		return fixedFlag$sample161;
	}

	@Override
	public final void set$fixedFlag$sample161(boolean cv$value) {
		fixedFlag$sample161 = cv$value;
		fixedProbFlag$sample161 = (fixedFlag$sample161 && fixedProbFlag$sample161);
	}

	@Override
	public final boolean get$fixedFlag$sample173() {
		return fixedFlag$sample173;
	}

	@Override
	public final void set$fixedFlag$sample173(boolean cv$value) {
		fixedFlag$sample173 = cv$value;
		fixedProbFlag$sample173 = (fixedFlag$sample173 && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		fixedProbFlag$sample50 = (fixedFlag$sample24 && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		fixedProbFlag$sample63 = (fixedFlag$sample30 && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedProbFlag$sample50);
		fixedProbFlag$sample63 = (fixedFlag$sample50 && fixedProbFlag$sample63);
		fixedProbFlag$sample161 = (fixedFlag$sample50 && fixedProbFlag$sample161);
		fixedProbFlag$sample173 = (fixedFlag$sample50 && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		fixedFlag$sample63 = cv$value;
		fixedProbFlag$sample63 = (fixedFlag$sample63 && fixedProbFlag$sample63);
		fixedProbFlag$sample161 = (fixedFlag$sample63 && fixedProbFlag$sample161);
		fixedProbFlag$sample173 = (fixedFlag$sample63 && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample96() {
		return fixedFlag$sample96;
	}

	@Override
	public final void set$fixedFlag$sample96(boolean cv$value) {
		fixedFlag$sample96 = cv$value;
		fixedProbFlag$sample96 = (fixedFlag$sample96 && fixedProbFlag$sample96);
		fixedProbFlag$sample173 = (fixedFlag$sample96 && fixedProbFlag$sample173);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample24 = false;
		fixedProbFlag$sample50 = false;
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
		fixedProbFlag$sample30 = false;
		fixedProbFlag$sample63 = false;
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
		fixedProbFlag$sample173 = false;
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
		fixedProbFlag$sample50 = false;
		fixedProbFlag$sample63 = false;
		fixedProbFlag$sample161 = false;
		fixedProbFlag$sample173 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample161() {
		if(!fixedProbFlag$sample161) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
							if(fixedFlag$sample50) {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									if((sample$var32 == sample$var120)) {
										if((0 == timeStep$var140)) {
											for(int var110 = 0; var110 < noServers; var110 += 1) {
												for(int var114 = 0; var114 < noStates; var114 += 1) {
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
										for(int index$sample50$4 = 0; index$sample50$4 < noStates; index$sample50$4 += 1) {
											int distributionTempVariable$var44$6 = index$sample50$4;
											double cv$probabilitySample50Value5 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$4]);
											int traceTempVariable$currentState$7_1 = distributionTempVariable$var44$6;
											if((sample$var32 == sample$var120)) {
												if((0 == timeStep$var140)) {
													for(int var110 = 0; var110 < noServers; var110 += 1) {
														for(int var114 = 0; var114 < noStates; var114 += 1) {
															if((var110 == server)) {
																if((var114 == traceTempVariable$currentState$7_1)) {
																	{
																		double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$7_1];
																		double cv$weightedProbability = (Math.log(cv$probabilitySample50Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value5);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample63) {
								for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
									for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
										if((sample$var32 == sample$var120)) {
											if((timeStep$var49 == timeStep$var140)) {
												for(int var110 = 0; var110 < noServers; var110 += 1) {
													for(int var114 = 0; var114 < noStates; var114 += 1) {
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
											for(int index$sample63$13 = 0; index$sample63$13 < noStates; index$sample63$13 += 1) {
												int distributionTempVariable$var57$15 = index$sample63$13;
												double cv$probabilitySample63Value14 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$13]);
												int traceTempVariable$currentState$16_1 = distributionTempVariable$var57$15;
												if((sample$var32 == sample$var120)) {
													if((timeStep$var49 == timeStep$var140)) {
														for(int var110 = 0; var110 < noServers; var110 += 1) {
															for(int var114 = 0; var114 < noStates; var114 += 1) {
																if((var110 == server)) {
																	if((var114 == traceTempVariable$currentState$16_1)) {
																		{
																			double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$16_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample63Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var144));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value14);
																		}
																	}
																}
															}
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
						logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						{
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
			fixedProbFlag$sample161 = (((fixedFlag$sample161 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						{
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

	private final void logProbabilityDistribution$sample173() {
		if(!fixedProbFlag$sample173) {
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
								if(fixedFlag$sample50) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												for(int var82 = 0; var82 < noServers; var82 += 1) {
													for(int var86 = 0; var86 < noStates; var86 += 1) {
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																			if((index$sample$10_1 == sample$var120)) {
																				if((0 == timeStep$var140)) {
																					for(int var96 = 0; var96 < noServers; var96 += 1) {
																						for(int var100 = 0; var100 < noStates; var100 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var96 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var100 == st[sample$var120][timeStep$var140])) {
																											{
																												double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																												double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																												double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
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
											for(int index$sample50$4 = 0; index$sample50$4 < noStates; index$sample50$4 += 1) {
												int distributionTempVariable$var44$6 = index$sample50$4;
												double cv$probabilitySample50Value5 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$4]);
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var44$6;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$7_1)) {
																				int traceTempVariable$currentState$11_1 = distributionTempVariable$var44$6;
																				if((sample$var32 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var96 = 0; var96 < noServers; var96 += 1) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$11_1)) {
																												{
																													double var152 = current_metric_mean[server][traceTempVariable$currentState$11_1];
																													double var154 = current_metric_var[server][traceTempVariable$currentState$11_1];
																													double cv$weightedProbability = (Math.log(cv$probabilitySample50Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																													if((cv$weightedProbability < cv$distributionAccumulator))
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																													else {
																														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																															cv$distributionAccumulator = cv$weightedProbability;
																														else
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																													}
																													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value5);
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
																						for(int index$sample50$13 = 0; index$sample50$13 < noStates; index$sample50$13 += 1) {
																							int distributionTempVariable$var44$15 = index$sample50$13;
																							double cv$probabilitySample50Value14 = (cv$probabilitySample50Value5 * distribution$sample50[((index$sample$12 - 0) / 1)][index$sample50$13]);
																							int traceTempVariable$currentState$16_1 = distributionTempVariable$var44$15;
																							if((index$sample$12 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$16_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value14);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample50) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										if((sample$var32 == sample$var120)) {
											if((0 == timeStep$var140)) {
												for(int var82 = 0; var82 < noServers; var82 += 1) {
													for(int var86 = 0; var86 < noStates; var86 += 1) {
														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
															if((var82 == server)) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var86 == st[sample$var120][timeStep$var140])) {
																		if(fixedFlag$sample63) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$28_1][0]; timeStep$var49 += 1) {
																					if((index$sample$28_1 == sample$var120)) {
																						if((timeStep$var49 == timeStep$var140)) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
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
																						for(int index$sample63$31 = 0; index$sample63$31 < noStates; index$sample63$31 += 1) {
																							int distributionTempVariable$var57$33 = index$sample63$31;
																							double cv$probabilitySample63Value32 = (1.0 * distribution$sample63[((index$sample$29 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var57$33;
																							if((index$sample$29 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$34_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample63Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
											for(int index$sample50$22 = 0; index$sample50$22 < noStates; index$sample50$22 += 1) {
												int distributionTempVariable$var44$24 = index$sample50$22;
												double cv$probabilitySample50Value23 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$22]);
												int traceTempVariable$currentState$25_1 = distributionTempVariable$var44$24;
												if((sample$var32 == sample$var120)) {
													if((0 == timeStep$var140)) {
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$25_1)) {
																				if(fixedFlag$sample63) {
																					for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$35_1][0]; timeStep$var49 += 1) {
																							if((index$sample$35_1 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$25_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value23);
																															}
																														}
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
																								for(int index$sample63$38 = 0; index$sample63$38 < noStates; index$sample63$38 += 1) {
																									int distributionTempVariable$var57$40 = index$sample63$38;
																									double cv$probabilitySample63Value39 = (cv$probabilitySample50Value23 * distribution$sample63[((index$sample$36 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$38]);
																									int traceTempVariable$currentState$41_1 = distributionTempVariable$var57$40;
																									if((index$sample$36 == sample$var120)) {
																										if((timeStep$var49 == timeStep$var140)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$41_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$41_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$41_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample63Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value39);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample63) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int var82 = 0; var82 < noServers; var82 += 1) {
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == st[sample$var120][timeStep$var140])) {
																			for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																				for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																					if((index$sample$55_1 == sample$var120)) {
																						if((index$timeStep$55_2 == timeStep$var140)) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
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
												for(int index$sample63$49 = 0; index$sample63$49 < noStates; index$sample63$49 += 1) {
													int distributionTempVariable$var57$51 = index$sample63$49;
													double cv$probabilitySample63Value50 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$49]);
													int traceTempVariable$currentState$52_1 = distributionTempVariable$var57$51;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																for(int var86 = 0; var86 < noStates; var86 += 1) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var82 == server)) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var86 == traceTempVariable$currentState$52_1)) {
																					int traceTempVariable$currentState$56_1 = distributionTempVariable$var57$51;
																					if((sample$var32 == sample$var120)) {
																						if((timeStep$var49 == timeStep$var140)) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == traceTempVariable$currentState$56_1)) {
																													{
																														double var152 = current_metric_mean[server][traceTempVariable$currentState$56_1];
																														double var154 = current_metric_var[server][traceTempVariable$currentState$56_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample63Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value50);
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
																								for(int index$sample63$59 = 0; index$sample63$59 < noStates; index$sample63$59 += 1) {
																									int distributionTempVariable$var57$61 = index$sample63$59;
																									double cv$probabilitySample63Value60 = (cv$probabilitySample63Value50 * distribution$sample63[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample63$59]);
																									int traceTempVariable$currentState$62_1 = distributionTempVariable$var57$61;
																									if((index$sample$57 == sample$var120)) {
																										if((index$timeStep$58 == timeStep$var140)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$62_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$62_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$62_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample63Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value60);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample63) {
									for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
										for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
											if((sample$var32 == sample$var120)) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int var82 = 0; var82 < noServers; var82 += 1) {
														for(int var86 = 0; var86 < noStates; var86 += 1) {
															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																if((var82 == server)) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var86 == st[sample$var120][timeStep$var140])) {
																			if(fixedFlag$sample50) {
																				for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																					if((index$sample$75_1 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var96 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var100 == st[sample$var120][timeStep$var140])) {
																													{
																														double var152 = current_metric_mean[server][st[sample$var120][timeStep$var140]];
																														double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
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
																						for(int index$sample50$77 = 0; index$sample50$77 < noStates; index$sample50$77 += 1) {
																							int distributionTempVariable$var44$79 = index$sample50$77;
																							double cv$probabilitySample50Value78 = (1.0 * distribution$sample50[((index$sample$76 - 0) / 1)][index$sample50$77]);
																							int traceTempVariable$currentState$80_1 = distributionTempVariable$var44$79;
																							if((index$sample$76 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$80_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value78);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
												for(int index$sample63$69 = 0; index$sample63$69 < noStates; index$sample63$69 += 1) {
													int distributionTempVariable$var57$71 = index$sample63$69;
													double cv$probabilitySample63Value70 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$69]);
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var57$71;
													if((sample$var32 == sample$var120)) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int var82 = 0; var82 < noServers; var82 += 1) {
																for(int var86 = 0; var86 < noStates; var86 += 1) {
																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																		if((var82 == server)) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var86 == traceTempVariable$currentState$72_1)) {
																					if(fixedFlag$sample50) {
																						for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																							if((index$sample$81_1 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$72_1)) {
																															{
																																double var152 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																double var154 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample63Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value70);
																															}
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
																								for(int index$sample50$83 = 0; index$sample50$83 < noStates; index$sample50$83 += 1) {
																									int distributionTempVariable$var44$85 = index$sample50$83;
																									double cv$probabilitySample50Value84 = (cv$probabilitySample63Value70 * distribution$sample50[((index$sample$82 - 0) / 1)][index$sample50$83]);
																									int traceTempVariable$currentState$86_1 = distributionTempVariable$var44$85;
																									if((index$sample$82 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$86_1)) {
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$86_1];
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$86_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample50Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value84);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
							logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample173 = ((((fixedFlag$sample173 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample96) && fixedFlag$sample111);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample50() {
		if(!fixedProbFlag$sample50) {
			if(fixedFlag$sample50) {
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
					logProbability$var43[((sample$var32 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample50[((sample$var32 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample50)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample50)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample24);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample50[((sample$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample50)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample63() {
		if(!fixedProbFlag$sample63) {
			if(fixedFlag$sample63) {
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
							if(fixedFlag$sample50) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample$var32)) {
										if((0 == (timeStep$var49 - 1))) {
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
													{
														double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
											int distributionTempVariable$var44$8 = index$sample50$6;
											double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((index$sample$5 - 0) / 1)][index$sample50$6]);
											int traceTempVariable$var54$9_1 = distributionTempVariable$var44$8;
											if((index$sample$5 == sample$var32)) {
												if((0 == (timeStep$var49 - 1))) {
													for(int var26 = 0; var26 < noStates; var26 += 1) {
														if((var26 == traceTempVariable$var54$9_1)) {
															{
																double[] var55 = m[traceTempVariable$var54$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample50Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample50Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if((index$sample$2 == sample$var32)) {
								if((index$timeStep$1 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											{
												double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample63) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var32)) {
											if((index$timeStep$13_2 == (timeStep$var49 - 1))) {
												for(int var26 = 0; var26 < noStates; var26 += 1) {
													if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
														{
															double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											for(int index$sample63$16 = 0; index$sample63$16 < noStates; index$sample63$16 += 1) {
												int distributionTempVariable$var57$18 = index$sample63$16;
												double cv$probabilitySample63Value17 = (1.0 * distribution$sample63[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample63$16]);
												int traceTempVariable$var54$19_1 = distributionTempVariable$var57$18;
												if((index$sample$14 == sample$var32)) {
													if((index$timeStep$15 == (timeStep$var49 - 1))) {
														for(int var26 = 0; var26 < noStates; var26 += 1) {
															if((var26 == traceTempVariable$var54$19_1)) {
																{
																	double[] var55 = m[traceTempVariable$var54$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample63Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample63Value17);
																}
															}
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
						logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample63)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample63)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample63 = ((fixedFlag$sample63 && fixedFlag$sample30) && fixedFlag$sample50);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample63)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample111() {
		if(!fixedProbFlag$sample111) {
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
			if(fixedFlag$sample111)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample111 = fixedFlag$sample111;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var101;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var92 = cv$rvAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample111)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!fixedProbFlag$sample126) {
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
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample126 = fixedFlag$sample126;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var115;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var106 = cv$rvAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample161() {
		if(!fixedProbFlag$sample161) {
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
						logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						{
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
			fixedProbFlag$sample161 = (((fixedFlag$sample161 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						{
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

	private final void logProbabilityValue$sample173() {
		if(!fixedProbFlag$sample173) {
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
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var152) / Math.sqrt(var154))) - (0.5 * Math.log(var154))));
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
							logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample173 = ((((fixedFlag$sample173 && fixedFlag$sample50) && fixedFlag$sample63) && fixedFlag$sample96) && fixedFlag$sample111);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = cv$rvAccumulator;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
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

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
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
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample50[((sample$var32 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample24);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample50[((sample$var32 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[((sample$var32 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample63() {
		if(!fixedProbFlag$sample63) {
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
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample63 = ((fixedFlag$sample63 && fixedFlag$sample30) && fixedFlag$sample50);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample96() {
		if(!fixedProbFlag$sample96) {
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
								double cv$weightedProbability = (Math.log(1.0) + (((var76 <= cv$sampleValue) && (cv$sampleValue <= var77))?(-Math.log((var77 - var76))):Double.NEGATIVE_INFINITY));
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
			if(fixedFlag$sample96)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample96 = fixedFlag$sample96;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var87;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var78 = cv$rvAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample96)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample111(int var96, int var100) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = current_metric_var[var96][var100];
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
									if(fixedFlag$sample50) {
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
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))));
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
																			if(fixedFlag$sample63) {
																				for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30_1][0]; timeStep$var49 += 1) {
																						if((index$sample$30_1 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))));
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
																							for(int index$sample63$33 = 0; index$sample63$33 < noStates; index$sample63$33 += 1) {
																								int distributionTempVariable$var57$35 = index$sample63$33;
																								double cv$probabilitySample63Value34 = (1.0 * distribution$sample63[((index$sample$31 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$33]);
																								int traceTempVariable$currentState$36_1 = distributionTempVariable$var57$35;
																								if((index$sample$31 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																		if(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value34);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
												for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
													int distributionTempVariable$var44$8 = index$sample50$6;
													double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$6]);
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
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																															if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																														}
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
																							for(int index$sample50$41 = 0; index$sample50$41 < noStates; index$sample50$41 += 1) {
																								int distributionTempVariable$var44$43 = index$sample50$41;
																								double cv$probabilitySample50Value42 = (cv$probabilitySample50Value7 * distribution$sample50[((index$sample$40 - 0) / 1)][index$sample50$41]);
																								int traceTempVariable$currentState$44_1 = distributionTempVariable$var44$43;
																								if((index$sample$40 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																		if(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value42);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					if(fixedFlag$sample63) {
																						for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																								if((index$sample$47_1 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																		if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																																	}
																																}
																															}
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
																									for(int index$sample63$50 = 0; index$sample63$50 < noStates; index$sample63$50 += 1) {
																										int distributionTempVariable$var57$52 = index$sample63$50;
																										double cv$probabilitySample63Value51 = (cv$probabilitySample50Value7 * distribution$sample63[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$50]);
																										int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																										if((index$sample$48 == sample$var120)) {
																											if((timeStep$var49 == timeStep$var140)) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																				if(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value51);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
									if(fixedFlag$sample63) {
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
																				if(fixedFlag$sample50) {
																					for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																						if((index$sample$56_1 == sample$var120)) {
																							if((0 == timeStep$var140)) {
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))));
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
																							for(int index$sample50$58 = 0; index$sample50$58 < noStates; index$sample50$58 += 1) {
																								int distributionTempVariable$var44$60 = index$sample50$58;
																								double cv$probabilitySample50Value59 = (1.0 * distribution$sample50[((index$sample$57 - 0) / 1)][index$sample50$58]);
																								int traceTempVariable$currentState$61_1 = distributionTempVariable$var44$60;
																								if((index$sample$57 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																		if(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value59);
																																	}
																																}
																															}
																														}
																													}
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
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))));
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
													for(int index$sample63$18 = 0; index$sample63$18 < noStates; index$sample63$18 += 1) {
														int distributionTempVariable$var57$20 = index$sample63$18;
														double cv$probabilitySample63Value19 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$18]);
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
																						if(fixedFlag$sample50) {
																							for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																								if((index$sample$66_1 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																		if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																																	}
																																}
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
																									for(int index$sample50$68 = 0; index$sample50$68 < noStates; index$sample50$68 += 1) {
																										int distributionTempVariable$var44$70 = index$sample50$68;
																										double cv$probabilitySample50Value69 = (cv$probabilitySample63Value19 * distribution$sample50[((index$sample$67 - 0) / 1)][index$sample50$68]);
																										int traceTempVariable$currentState$71_1 = distributionTempVariable$var44$70;
																										if((index$sample$67 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																				if(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value69);
																																			}
																																		}
																																	}
																																}
																															}
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
																								for(int var82 = 0; var82 < noServers; var82 += 1) {
																									for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																															}
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
																									for(int index$sample63$77 = 0; index$sample63$77 < noStates; index$sample63$77 += 1) {
																										int distributionTempVariable$var57$79 = index$sample63$77;
																										double cv$probabilitySample63Value78 = (cv$probabilitySample63Value19 * distribution$sample63[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample63$77]);
																										int traceTempVariable$currentState$80_1 = distributionTempVariable$var57$79;
																										if((index$sample$75 == sample$var120)) {
																											if((index$timeStep$76 == timeStep$var140)) {
																												for(int var82 = 0; var82 < noServers; var82 += 1) {
																													for(int var86 = 0; var86 < noStates; var86 += 1) {
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
																																				if(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value78);
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var101 = cv$originalValue;
			double[] var97 = current_metric_var[var96];
			var97[var100] = var101;
		}
	}

	private final void sample126(int var110, int var114) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
								if(fixedFlag$sample50) {
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
											for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
												int distributionTempVariable$var44$8 = index$sample50$6;
												double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$6]);
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
																					cv$count = (cv$count + cv$probabilitySample50Value7);
																					if(metric_valid_g[sample$var120][server][timeStep$var140])
																						cv$sum = (cv$sum + cv$probabilitySample50Value7);
																				}
																			}
																		}
																	}
																}
															}
														}
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
								if(fixedFlag$sample63) {
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
												for(int index$sample63$18 = 0; index$sample63$18 < noStates; index$sample63$18 += 1) {
													int distributionTempVariable$var57$20 = index$sample63$18;
													double cv$probabilitySample63Value19 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$18]);
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
																						cv$count = (cv$count + cv$probabilitySample63Value19);
																						if(metric_valid_g[sample$var120][server][timeStep$var140])
																							cv$sum = (cv$sum + cv$probabilitySample63Value19);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
						if(fixedFlag$sample50) {
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
					if(!fixedFlag$sample50) {
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
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample50[((sample$var32 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							if(fixedFlag$sample50) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample$var32)) {
										if((0 == (timeStep$var49 - 1))) {
											if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
												if(fixedFlag$sample63) {
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
										for(int index$sample50$5 = 0; index$sample50$5 < noStates; index$sample50$5 += 1) {
											int distributionTempVariable$var44$7 = index$sample50$5;
											double cv$probabilitySample50Value6 = (1.0 * distribution$sample50[((index$sample$4 - 0) / 1)][index$sample50$5]);
											int traceTempVariable$var54$8_1 = distributionTempVariable$var44$7;
											if((index$sample$4 == sample$var32)) {
												if((0 == (timeStep$var49 - 1))) {
													if((var26 == traceTempVariable$var54$8_1)) {
														if(fixedFlag$sample63) {
															{
																int index$timeStep$26 = timeStep$var49;
																int index$sample$27 = sample$var32;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + cv$probabilitySample50Value6);
																			}
																		}
																	}
																}
															}
														}
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
							if(fixedFlag$sample63) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var32)) {
											if((index$timeStep$13_2 == (timeStep$var49 - 1))) {
												if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
													if(fixedFlag$sample63) {
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
											for(int index$sample63$16 = 0; index$sample63$16 < noStates; index$sample63$16 += 1) {
												int distributionTempVariable$var57$18 = index$sample63$16;
												double cv$probabilitySample63Value17 = (1.0 * distribution$sample63[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample63$16]);
												int traceTempVariable$var54$19_1 = distributionTempVariable$var57$18;
												if((index$sample$14 == sample$var32)) {
													if((index$timeStep$15 == (timeStep$var49 - 1))) {
														if((var26 == traceTempVariable$var54$19_1)) {
															if(fixedFlag$sample63) {
																{
																	int index$timeStep$32 = timeStep$var49;
																	int index$sample$33 = sample$var32;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + cv$probabilitySample63Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
						if(fixedFlag$sample50) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample$var32)) {
									if((0 == (timeStep$var49 - 1))) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											if(!fixedFlag$sample63) {
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
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample50$42 = 0; index$sample50$42 < noStates; index$sample50$42 += 1) {
										int distributionTempVariable$var44$44 = index$sample50$42;
										double cv$probabilitySample50Value43 = (1.0 * distribution$sample50[((index$sample$41 - 0) / 1)][index$sample50$42]);
										int traceTempVariable$var54$45_1 = distributionTempVariable$var44$44;
										if((index$sample$41 == sample$var32)) {
											if((0 == (timeStep$var49 - 1))) {
												if((var26 == traceTempVariable$var54$45_1)) {
													if(!fixedFlag$sample63) {
														{
															int index$timeStep$63 = timeStep$var49;
															int index$sample$64 = sample$var32;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample50Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
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
						if(fixedFlag$sample63) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample$var32)) {
										if((index$timeStep$50_2 == (timeStep$var49 - 1))) {
											if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
												if(!fixedFlag$sample63) {
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
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										for(int index$sample63$53 = 0; index$sample63$53 < noStates; index$sample63$53 += 1) {
											int distributionTempVariable$var57$55 = index$sample63$53;
											double cv$probabilitySample63Value54 = (1.0 * distribution$sample63[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample63$53]);
											int traceTempVariable$var54$56_1 = distributionTempVariable$var57$55;
											if((index$sample$51 == sample$var32)) {
												if((index$timeStep$52 == (timeStep$var49 - 1))) {
													if((var26 == traceTempVariable$var54$56_1)) {
														if(!fixedFlag$sample63) {
															{
																int index$timeStep$69 = timeStep$var49;
																int index$sample$70 = sample$var32;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample63Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample50(int sample$var32) {
		int cv$noStates = 0;
		int index$sample$1 = sample$var32;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var44$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$sample$2 = sample$var32;
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
						int traceTempVariable$var54$3_1 = cv$currentValue;
						for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
							if((sample$var32 == index$sample$3_2)) {
								for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$3_2][0]; timeStep$var49 += 1) {
									if((0 == (timeStep$var49 - 1))) {
										if(fixedFlag$sample63) {
											{
												int index$timeStep$5 = timeStep$var49;
												int index$sample$6 = index$sample$3_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var26 = 0; var26 < noStates; var26 += 1) {
														if((var26 == traceTempVariable$var54$3_1)) {
															{
																{
																	double[] cv$temp$1$var55;
																	{
																		double[] var55 = m[traceTempVariable$var54$3_1];
																		cv$temp$1$var55 = var55;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var49]) && (st[index$sample$3_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$3_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var49]) && (st[index$sample$3_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$3_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var49]) && (st[index$sample$3_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$3_2][timeStep$var49]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var49]) && (st[index$sample$3_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$3_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var49]) && (st[index$sample$3_2][timeStep$var49] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[index$sample$3_2][timeStep$var49]]):Double.NEGATIVE_INFINITY)));
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
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var110 = 0; var110 < noServers; var110 += 1) {
													for(int var114 = 0; var114 < noStates; var114 += 1) {
														if((var110 == server)) {
															if((var114 == traceTempVariable$currentState$9_1)) {
																{
																	{
																		double cv$temp$2$var144;
																		{
																			double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$9_1];
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
						boolean[][][] guard$sample50gaussian172 = guard$sample50gaussian172$global;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1)
											guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1)
											guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
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
											if(!guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
												guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$15_1)) {
																				int traceTempVariable$currentState$20_1 = cv$currentValue;
																				if((index$sample$2 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var96 = 0; var96 < noServers; var96 += 1) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$20_1)) {
																												{
																													{
																														double cv$temp$3$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$20_1];
																															cv$temp$3$var152 = var152;
																														}
																														double cv$temp$4$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$20_1];
																															cv$temp$4$var154 = var154;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$3$var152) / Math.sqrt(cv$temp$4$var154))) - (0.5 * Math.log(cv$temp$4$var154)))));
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
																				for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
																					if(!(index$sample$21 == index$sample$2)) {
																						for(int index$sample50$22 = 0; index$sample50$22 < noStates; index$sample50$22 += 1) {
																							int distributionTempVariable$var44$24 = index$sample50$22;
																							double cv$probabilitySample50Value23 = (1.0 * distribution$sample50[((index$sample$21 - 0) / 1)][index$sample50$22]);
																							int traceTempVariable$currentState$25_1 = distributionTempVariable$var44$24;
																							if((index$sample$21 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$25_1)) {
																															{
																																{
																																	double cv$temp$5$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																		cv$temp$5$var152 = var152;
																																	}
																																	double cv$temp$6$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																		cv$temp$6$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$5$var152) / Math.sqrt(cv$temp$6$var154))) - (0.5 * Math.log(cv$temp$6$var154)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value23);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														for(int var82 = 0; var82 < noServers; var82 += 1) {
															for(int var86 = 0; var86 < noStates; var86 += 1) {
																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																	if((var82 == server)) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var86 == traceTempVariable$currentState$15_1)) {
																				if(fixedFlag$sample63) {
																					for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$29_1][0]; timeStep$var49 += 1) {
																							if((index$sample$29_1 == sample$var120)) {
																								if((timeStep$var49 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$15_1)) {
																															{
																																{
																																	double cv$temp$7$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																		cv$temp$7$var152 = var152;
																																	}
																																	double cv$temp$8$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																		cv$temp$8$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$7$var152) / Math.sqrt(cv$temp$8$var154))) - (0.5 * Math.log(cv$temp$8$var154)))));
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
																					for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																						for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30][0]; timeStep$var49 += 1) {
																							if(true) {
																								for(int index$sample63$32 = 0; index$sample63$32 < noStates; index$sample63$32 += 1) {
																									int distributionTempVariable$var57$34 = index$sample63$32;
																									double cv$probabilitySample63Value33 = (1.0 * distribution$sample63[((index$sample$30 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$32]);
																									int traceTempVariable$currentState$35_1 = distributionTempVariable$var57$34;
																									if((index$sample$30 == sample$var120)) {
																										if((timeStep$var49 == timeStep$var140)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$35_1)) {
																																	{
																																		{
																																			double cv$temp$9$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$35_1];
																																				cv$temp$9$var152 = var152;
																																			}
																																			double cv$temp$10$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$35_1];
																																				cv$temp$10$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample63Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$9$var152) / Math.sqrt(cv$temp$10$var154))) - (0.5 * Math.log(cv$temp$10$var154)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value33);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
						int traceTempVariable$currentState$16_1 = cv$currentValue;
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							if((sample$var32 == sample$var120)) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if((0 == timeStep$var140)) {
										for(int server = 0; server < noServers; server += 1) {
											if(!guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
												guard$sample50gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														int traceTempVariable$currentState$38_1 = cv$currentValue;
														if((index$sample$2 == sample$var120)) {
															if((0 == timeStep$var140)) {
																for(int var82 = 0; var82 < noServers; var82 += 1) {
																	for(int var86 = 0; var86 < noStates; var86 += 1) {
																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																			if((var82 == server)) {
																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																					if((var86 == traceTempVariable$currentState$38_1)) {
																						for(int var96 = 0; var96 < noServers; var96 += 1) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var96 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var100 == traceTempVariable$currentState$38_1)) {
																												{
																													{
																														double cv$temp$11$var152;
																														{
																															double var152 = current_metric_mean[server][traceTempVariable$currentState$38_1];
																															cv$temp$11$var152 = var152;
																														}
																														double cv$temp$12$var154;
																														{
																															double var154 = current_metric_var[server][traceTempVariable$currentState$38_1];
																															cv$temp$12$var154 = var154;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$11$var152) / Math.sqrt(cv$temp$12$var154))) - (0.5 * Math.log(cv$temp$12$var154)))));
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
														for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
															if(!(index$sample$39 == index$sample$2)) {
																for(int index$sample50$40 = 0; index$sample50$40 < noStates; index$sample50$40 += 1) {
																	int distributionTempVariable$var44$42 = index$sample50$40;
																	double cv$probabilitySample50Value41 = (1.0 * distribution$sample50[((index$sample$39 - 0) / 1)][index$sample50$40]);
																	int traceTempVariable$currentState$43_1 = distributionTempVariable$var44$42;
																	if((index$sample$39 == sample$var120)) {
																		if((0 == timeStep$var140)) {
																			for(int var82 = 0; var82 < noServers; var82 += 1) {
																				for(int var86 = 0; var86 < noStates; var86 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$43_1)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$43_1)) {
																															{
																																{
																																	double cv$temp$13$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$43_1];
																																		cv$temp$13$var152 = var152;
																																	}
																																	double cv$temp$14$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$43_1];
																																		cv$temp$14$var154 = var154;
																																	}
																																	if(((Math.log(cv$probabilitySample50Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$13$var152) / Math.sqrt(cv$temp$14$var154))) - (0.5 * Math.log(cv$temp$14$var154)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value41);
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
														if(fixedFlag$sample63) {
															for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$48_1][0]; timeStep$var49 += 1) {
																	if((index$sample$48_1 == sample$var120)) {
																		if((timeStep$var49 == timeStep$var140)) {
																			for(int var82 = 0; var82 < noServers; var82 += 1) {
																				for(int var86 = 0; var86 < noStates; var86 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$16_1)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$16_1)) {
																															{
																																{
																																	double cv$temp$15$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																		cv$temp$15$var152 = var152;
																																	}
																																	double cv$temp$16$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																		cv$temp$16$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$15$var152) / Math.sqrt(cv$temp$16$var154))) - (0.5 * Math.log(cv$temp$16$var154)))));
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
															for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$49][0]; timeStep$var49 += 1) {
																	if(true) {
																		for(int index$sample63$51 = 0; index$sample63$51 < noStates; index$sample63$51 += 1) {
																			int distributionTempVariable$var57$53 = index$sample63$51;
																			double cv$probabilitySample63Value52 = (1.0 * distribution$sample63[((index$sample$49 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$51]);
																			int traceTempVariable$currentState$54_1 = distributionTempVariable$var57$53;
																			if((index$sample$49 == sample$var120)) {
																				if((timeStep$var49 == timeStep$var140)) {
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$54_1)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$54_1)) {
																																	{
																																		{
																																			double cv$temp$17$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$54_1];
																																				cv$temp$17$var152 = var152;
																																			}
																																			double cv$temp$18$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$54_1];
																																				cv$temp$18$var154 = var154;
																																			}
																																			if(((Math.log(cv$probabilitySample63Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$17$var152) / Math.sqrt(cv$temp$18$var154))) - (0.5 * Math.log(cv$temp$18$var154)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value52);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
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
					int traceTempVariable$var54$67_1 = cv$currentValue;
					for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
						if((sample$var32 == index$sample$67_2)) {
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$67_2][0]; timeStep$var49 += 1) {
								if((0 == (timeStep$var49 - 1))) {
									if(!fixedFlag$sample63) {
										{
											int index$timeStep$69 = timeStep$var49;
											int index$sample$70 = index$sample$67_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == traceTempVariable$var54$67_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var55;
														{
															double[] var55 = m[traceTempVariable$var54$67_1];
															cv$temp$19$var55 = var55;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var55);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample63[((index$sample$67_2 - 0) / 1)][((timeStep$var49 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample50[((sample$var32 - 0) / 1)];
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

	private final void sample63(int sample$var32, int timeStep$var49) {
		int cv$noStates = 0;
		int index$timeStep$1 = timeStep$var49;
		int index$sample$2 = sample$var32;
		if(fixedFlag$sample50) {
			for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
				if((index$sample$3_1 == sample$var32)) {
					if((0 == (timeStep$var49 - 1))) {
						for(int var26 = 0; var26 < noStates; var26 += 1) {
							if((var26 == st[sample$var32][(timeStep$var49 - 1)]))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
				if(true) {
					for(int index$sample50$5 = 0; index$sample50$5 < noStates; index$sample50$5 += 1) {
						int distributionTempVariable$var44$7 = index$sample50$5;
						double cv$probabilitySample50Value6 = (1.0 * distribution$sample50[((index$sample$4 - 0) / 1)][index$sample50$5]);
						int traceTempVariable$var54$8_1 = distributionTempVariable$var44$7;
						if((index$sample$4 == sample$var32)) {
							if((0 == (timeStep$var49 - 1))) {
								for(int var26 = 0; var26 < noStates; var26 += 1) {
									if((var26 == traceTempVariable$var54$8_1))
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		if((index$sample$2 == sample$var32)) {
			if((index$timeStep$1 == (timeStep$var49 - 1))) {
				for(int var26 = 0; var26 < noStates; var26 += 1) {
					if((var26 == st[sample$var32][(timeStep$var49 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample63) {
			for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
				for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1][0]; index$timeStep$12_2 += 1) {
					if((index$sample$12_1 == sample$var32)) {
						if((index$timeStep$12_2 == (timeStep$var49 - 1))) {
							for(int var26 = 0; var26 < noStates; var26 += 1) {
								if((var26 == st[sample$var32][(timeStep$var49 - 1)]))
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
				for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13][0]; index$timeStep$14 += 1) {
					if(!((index$sample$13 == index$sample$2) && (index$timeStep$14 == index$timeStep$1))) {
						for(int index$sample63$15 = 0; index$sample63$15 < noStates; index$sample63$15 += 1) {
							int distributionTempVariable$var57$17 = index$sample63$15;
							double cv$probabilitySample63Value16 = (1.0 * distribution$sample63[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample63$15]);
							int traceTempVariable$var54$18_1 = distributionTempVariable$var57$17;
							if((index$sample$13 == sample$var32)) {
								if((index$timeStep$14 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$18_1))
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var57$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$timeStep$22 = timeStep$var49;
			int index$sample$23 = sample$var32;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample50) {
				for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
					if((index$sample$24_1 == sample$var32)) {
						if((0 == (timeStep$var49 - 1))) {
							for(int var26 = 0; var26 < noStates; var26 += 1) {
								if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var55;
									{
										double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
										cv$temp$0$var55 = var55;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var55.length))?Math.log(cv$temp$0$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var54$41_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$45_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var110 = 0; var110 < noServers; var110 += 1) {
																		for(int var114 = 0; var114 < noStates; var114 += 1) {
																			if((var110 == server)) {
																				if((var114 == traceTempVariable$currentState$45_1)) {
																					{
																						{
																							double cv$temp$4$var144;
																							{
																								double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$45_1];
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
											boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1)
																guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
														}
													}
												}
											}
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1)
																guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
														}
													}
												}
											}
											int traceTempVariable$currentState$69_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																	guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var82 = 0; var82 < noServers; var82 += 1) {
																				for(int var86 = 0; var86 < noStates; var86 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$69_1)) {
																									for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																										if((index$sample$86_1 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$69_1)) {
																																		{
																																			{
																																				double cv$temp$8$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$69_1];
																																					cv$temp$8$var152 = var152;
																																				}
																																				double cv$temp$9$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$69_1];
																																					cv$temp$9$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))));
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
																			for(int var82 = 0; var82 < noServers; var82 += 1) {
																				for(int var86 = 0; var86 < noStates; var86 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$69_1)) {
																									int traceTempVariable$currentState$89_1 = cv$currentValue;
																									if((index$sample$23 == sample$var120)) {
																										if((index$timeStep$22 == timeStep$var140)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$89_1)) {
																																	{
																																		{
																																			double cv$temp$10$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$89_1];
																																				cv$temp$10$var152 = var152;
																																			}
																																			double cv$temp$11$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$89_1];
																																				cv$temp$11$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))));
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
																									for(int index$sample$90 = 0; index$sample$90 < noSamples; index$sample$90 += 1) {
																										for(int index$timeStep$91 = 1; index$timeStep$91 < length$metric[index$sample$90][0]; index$timeStep$91 += 1) {
																											if(!((index$sample$90 == index$sample$23) && (index$timeStep$91 == index$timeStep$22))) {
																												for(int index$sample63$92 = 0; index$sample63$92 < noStates; index$sample63$92 += 1) {
																													int distributionTempVariable$var57$94 = index$sample63$92;
																													double cv$probabilitySample63Value93 = (1.0 * distribution$sample63[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample63$92]);
																													int traceTempVariable$currentState$95_1 = distributionTempVariable$var57$94;
																													if((index$sample$90 == sample$var120)) {
																														if((index$timeStep$91 == timeStep$var140)) {
																															for(int var96 = 0; var96 < noServers; var96 += 1) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$95_1)) {
																																					{
																																						{
																																							double cv$temp$12$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$95_1];
																																								cv$temp$12$var152 = var152;
																																							}
																																							double cv$temp$13$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$95_1];
																																								cv$temp$13$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample63Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value93);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
											int traceTempVariable$currentState$73_1 = cv$currentValue;
											for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
												if((sample$var32 == sample$var120)) {
													for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
														if((timeStep$var49 == timeStep$var140)) {
															for(int server = 0; server < noServers; server += 1) {
																if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																	guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																				if((index$sample$157_1 == sample$var120)) {
																					if((0 == timeStep$var140)) {
																						for(int var82 = 0; var82 < noServers; var82 += 1) {
																							for(int var86 = 0; var86 < noStates; var86 += 1) {
																								if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																									if((var82 == server)) {
																										if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																											if((var86 == traceTempVariable$currentState$73_1)) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var96 == server)) {
																																if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																	if((var100 == traceTempVariable$currentState$73_1)) {
																																		{
																																			{
																																				double cv$temp$40$var152;
																																				{
																																					double var152 = current_metric_mean[server][traceTempVariable$currentState$73_1];
																																					cv$temp$40$var152 = var152;
																																				}
																																				double cv$temp$41$var154;
																																				{
																																					double var154 = current_metric_var[server][traceTempVariable$currentState$73_1];
																																					cv$temp$41$var154 = var154;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$40$var152) / Math.sqrt(cv$temp$41$var154))) - (0.5 * Math.log(cv$temp$41$var154)))));
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
																			int traceTempVariable$currentState$160_1 = cv$currentValue;
																			if((index$sample$23 == sample$var120)) {
																				if((index$timeStep$22 == timeStep$var140)) {
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$160_1)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$160_1)) {
																																	{
																																		{
																																			double cv$temp$42$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																				cv$temp$42$var152 = var152;
																																			}
																																			double cv$temp$43$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																				cv$temp$43$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$42$var152) / Math.sqrt(cv$temp$43$var154))) - (0.5 * Math.log(cv$temp$43$var154)))));
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
																					if(!((index$sample$161 == index$sample$23) && (index$timeStep$162 == index$timeStep$22))) {
																						for(int index$sample63$163 = 0; index$sample63$163 < noStates; index$sample63$163 += 1) {
																							int distributionTempVariable$var57$165 = index$sample63$163;
																							double cv$probabilitySample63Value164 = (1.0 * distribution$sample63[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample63$163]);
																							int traceTempVariable$currentState$166_1 = distributionTempVariable$var57$165;
																							if((index$sample$161 == sample$var120)) {
																								if((index$timeStep$162 == timeStep$var140)) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var82 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var86 == traceTempVariable$currentState$166_1)) {
																															for(int var96 = 0; var96 < noServers; var96 += 1) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$166_1)) {
																																					{
																																						{
																																							double cv$temp$44$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																								cv$temp$44$var152 = var152;
																																							}
																																							double cv$temp$45$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																								cv$temp$45$var154 = var154;
																																							}
																																							if(((Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$44$var152) / Math.sqrt(cv$temp$45$var154))) - (0.5 * Math.log(cv$temp$45$var154)))));
																																							}
																																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value164);
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
				for(int index$sample$25 = 0; index$sample$25 < noSamples; index$sample$25 += 1) {
					if(true) {
						for(int index$sample50$26 = 0; index$sample50$26 < noStates; index$sample50$26 += 1) {
							int distributionTempVariable$var44$28 = index$sample50$26;
							double cv$probabilitySample50Value27 = (1.0 * distribution$sample50[((index$sample$25 - 0) / 1)][index$sample50$26]);
							int traceTempVariable$var54$29_1 = distributionTempVariable$var44$28;
							if((index$sample$25 == sample$var32)) {
								if((0 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$29_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample50Value27);
											double[] cv$temp$1$var55;
											{
												double[] var55 = m[traceTempVariable$var54$29_1];
												cv$temp$1$var55 = var55;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample50Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var54$42_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$46_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var110 = 0; var110 < noServers; var110 += 1) {
																				for(int var114 = 0; var114 < noStates; var114 += 1) {
																					if((var110 == server)) {
																						if((var114 == traceTempVariable$currentState$46_1)) {
																							{
																								{
																									double cv$temp$5$var144;
																									{
																										double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$46_1];
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
													boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$70_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$70_1)) {
																											int traceTempVariable$currentState$99_1 = distributionTempVariable$var44$28;
																											if((index$sample$25 == sample$var120)) {
																												if((0 == timeStep$var140)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$99_1)) {
																																			{
																																				{
																																					double cv$temp$14$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$99_1];
																																						cv$temp$14$var152 = var152;
																																					}
																																					double cv$temp$15$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$99_1];
																																						cv$temp$15$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))));
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
																											for(int index$sample$100 = 0; index$sample$100 < noSamples; index$sample$100 += 1) {
																												if(!(index$sample$100 == index$sample$25)) {
																													for(int index$sample50$101 = 0; index$sample50$101 < noStates; index$sample50$101 += 1) {
																														int distributionTempVariable$var44$103 = index$sample50$101;
																														double cv$probabilitySample50Value102 = (1.0 * distribution$sample50[((index$sample$100 - 0) / 1)][index$sample50$101]);
																														int traceTempVariable$currentState$104_1 = distributionTempVariable$var44$103;
																														if((index$sample$100 == sample$var120)) {
																															if((0 == timeStep$var140)) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																			if((var96 == server)) {
																																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																					if((var100 == traceTempVariable$currentState$104_1)) {
																																						{
																																							{
																																								double cv$temp$16$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$104_1];
																																									cv$temp$16$var152 = var152;
																																								}
																																								double cv$temp$17$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$104_1];
																																									cv$temp$17$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample50Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value102);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$70_1)) {
																											int traceTempVariable$currentState$108_1 = cv$currentValue;
																											if((index$sample$23 == sample$var120)) {
																												if((index$timeStep$22 == timeStep$var140)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$108_1)) {
																																			{
																																				{
																																					double cv$temp$18$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$108_1];
																																						cv$temp$18$var152 = var152;
																																					}
																																					double cv$temp$19$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$108_1];
																																						cv$temp$19$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))));
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
																											for(int index$sample$109 = 0; index$sample$109 < noSamples; index$sample$109 += 1) {
																												for(int index$timeStep$110 = 1; index$timeStep$110 < length$metric[index$sample$109][0]; index$timeStep$110 += 1) {
																													if(!((index$sample$109 == index$sample$23) && (index$timeStep$110 == index$timeStep$22))) {
																														for(int index$sample63$111 = 0; index$sample63$111 < noStates; index$sample63$111 += 1) {
																															int distributionTempVariable$var57$113 = index$sample63$111;
																															double cv$probabilitySample63Value112 = (1.0 * distribution$sample63[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample63$111]);
																															int traceTempVariable$currentState$114_1 = distributionTempVariable$var57$113;
																															if((index$sample$109 == sample$var120)) {
																																if((index$timeStep$110 == timeStep$var140)) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		for(int var100 = 0; var100 < noStates; var100 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$114_1)) {
																																							{
																																								{
																																									double cv$temp$20$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$114_1];
																																										cv$temp$20$var152 = var152;
																																									}
																																									double cv$temp$21$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$114_1];
																																										cv$temp$21$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample63Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value112);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
													int traceTempVariable$currentState$74_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					int traceTempVariable$currentState$171_1 = distributionTempVariable$var44$28;
																					if((index$sample$25 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$171_1)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$171_1)) {
																																			{
																																				{
																																					double cv$temp$46$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$171_1];
																																						cv$temp$46$var152 = var152;
																																					}
																																					double cv$temp$47$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$171_1];
																																						cv$temp$47$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$46$var152) / Math.sqrt(cv$temp$47$var154))) - (0.5 * Math.log(cv$temp$47$var154)))));
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
																					for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																						if(!(index$sample$172 == index$sample$25)) {
																							for(int index$sample50$173 = 0; index$sample50$173 < noStates; index$sample50$173 += 1) {
																								int distributionTempVariable$var44$175 = index$sample50$173;
																								double cv$probabilitySample50Value174 = (1.0 * distribution$sample50[((index$sample$172 - 0) / 1)][index$sample50$173]);
																								int traceTempVariable$currentState$176_1 = distributionTempVariable$var44$175;
																								if((index$sample$172 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var82 = 0; var82 < noServers; var82 += 1) {
																											for(int var86 = 0; var86 < noStates; var86 += 1) {
																												if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																													if((var82 == server)) {
																														if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																															if((var86 == traceTempVariable$currentState$176_1)) {
																																for(int var96 = 0; var96 < noServers; var96 += 1) {
																																	for(int var100 = 0; var100 < noStates; var100 += 1) {
																																		if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																			if((var96 == server)) {
																																				if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																					if((var100 == traceTempVariable$currentState$176_1)) {
																																						{
																																							{
																																								double cv$temp$48$var152;
																																								{
																																									double var152 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																									cv$temp$48$var152 = var152;
																																								}
																																								double cv$temp$49$var154;
																																								{
																																									double var154 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																									cv$temp$49$var154 = var154;
																																								}
																																								if(((Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$temp$49$var154))) - (0.5 * Math.log(cv$temp$49$var154)))));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value174);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
																					if((index$sample$23 == sample$var120)) {
																						if((index$timeStep$22 == timeStep$var140)) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$181_1)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$181_1)) {
																																			{
																																				{
																																					double cv$temp$50$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																						cv$temp$50$var152 = var152;
																																					}
																																					double cv$temp$51$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																						cv$temp$51$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$50$var152) / Math.sqrt(cv$temp$51$var154))) - (0.5 * Math.log(cv$temp$51$var154)))));
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
																							if(!((index$sample$182 == index$sample$23) && (index$timeStep$183 == index$timeStep$22))) {
																								for(int index$sample63$184 = 0; index$sample63$184 < noStates; index$sample63$184 += 1) {
																									int distributionTempVariable$var57$186 = index$sample63$184;
																									double cv$probabilitySample63Value185 = (1.0 * distribution$sample63[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample63$184]);
																									int traceTempVariable$currentState$187_1 = distributionTempVariable$var57$186;
																									if((index$sample$182 == sample$var120)) {
																										if((index$timeStep$183 == timeStep$var140)) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$187_1)) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		for(int var100 = 0; var100 < noStates; var100 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$187_1)) {
																																							{
																																								{
																																									double cv$temp$52$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																										cv$temp$52$var152 = var152;
																																									}
																																									double cv$temp$53$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																										cv$temp$53$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$52$var152) / Math.sqrt(cv$temp$53$var154))) - (0.5 * Math.log(cv$temp$53$var154)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value185);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
			int traceTempVariable$var54$32_1 = cv$currentValue;
			if((index$sample$23 == sample$var32)) {
				if((index$timeStep$22 == (timeStep$var49 - 1))) {
					for(int var26 = 0; var26 < noStates; var26 += 1) {
						if((var26 == traceTempVariable$var54$32_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var55;
							{
								double[] var55 = m[traceTempVariable$var54$32_1];
								cv$temp$2$var55 = var55;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var55.length))?Math.log(cv$temp$2$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var54$43_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$47_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var110 = 0; var110 < noServers; var110 += 1) {
																for(int var114 = 0; var114 < noStates; var114 += 1) {
																	if((var110 == server)) {
																		if((var114 == traceTempVariable$currentState$47_1)) {
																			{
																				{
																					double cv$temp$6$var144;
																					{
																						double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$47_1];
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
									boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1)
														guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
												}
											}
										}
									}
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1)
														guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
												}
											}
										}
									}
									int traceTempVariable$currentState$71_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
															guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var82 = 0; var82 < noServers; var82 += 1) {
																		for(int var86 = 0; var86 < noStates; var86 += 1) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var82 == server)) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var86 == traceTempVariable$currentState$71_1)) {
																							if(fixedFlag$sample50) {
																								for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																									if((index$sample$118_1 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$71_1)) {
																																	{
																																		{
																																			double cv$temp$22$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																				cv$temp$22$var152 = var152;
																																			}
																																			double cv$temp$23$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																				cv$temp$23$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))));
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
																								for(int index$sample$119 = 0; index$sample$119 < noSamples; index$sample$119 += 1) {
																									if(true) {
																										for(int index$sample50$120 = 0; index$sample50$120 < noStates; index$sample50$120 += 1) {
																											int distributionTempVariable$var44$122 = index$sample50$120;
																											double cv$probabilitySample50Value121 = (1.0 * distribution$sample50[((index$sample$119 - 0) / 1)][index$sample50$120]);
																											int traceTempVariable$currentState$123_1 = distributionTempVariable$var44$122;
																											if((index$sample$119 == sample$var120)) {
																												if((0 == timeStep$var140)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$123_1)) {
																																			{
																																				{
																																					double cv$temp$24$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$123_1];
																																						cv$temp$24$var152 = var152;
																																					}
																																					double cv$temp$25$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$123_1];
																																						cv$temp$25$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample50Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value121);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																	for(int var82 = 0; var82 < noServers; var82 += 1) {
																		for(int var86 = 0; var86 < noStates; var86 += 1) {
																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																				if((var82 == server)) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var86 == traceTempVariable$currentState$71_1)) {
																							int traceTempVariable$currentState$127_1 = cv$currentValue;
																							if((index$sample$23 == sample$var120)) {
																								if((index$timeStep$22 == timeStep$var140)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$127_1)) {
																															{
																																{
																																	double cv$temp$26$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$127_1];
																																		cv$temp$26$var152 = var152;
																																	}
																																	double cv$temp$27$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$127_1];
																																		cv$temp$27$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))));
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
																							for(int index$sample$128 = 0; index$sample$128 < noSamples; index$sample$128 += 1) {
																								for(int index$timeStep$129 = 1; index$timeStep$129 < length$metric[index$sample$128][0]; index$timeStep$129 += 1) {
																									if(!((index$sample$128 == index$sample$23) && (index$timeStep$129 == index$timeStep$22))) {
																										for(int index$sample63$130 = 0; index$sample63$130 < noStates; index$sample63$130 += 1) {
																											int distributionTempVariable$var57$132 = index$sample63$130;
																											double cv$probabilitySample63Value131 = (1.0 * distribution$sample63[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample63$130]);
																											int traceTempVariable$currentState$133_1 = distributionTempVariable$var57$132;
																											if((index$sample$128 == sample$var120)) {
																												if((index$timeStep$129 == timeStep$var140)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$133_1)) {
																																			{
																																				{
																																					double cv$temp$28$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$133_1];
																																						cv$temp$28$var152 = var152;
																																					}
																																					double cv$temp$29$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$133_1];
																																						cv$temp$29$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample63Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value131);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
									int traceTempVariable$currentState$75_1 = cv$currentValue;
									for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
										if((sample$var32 == sample$var120)) {
											for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
												if((timeStep$var49 == timeStep$var140)) {
													for(int server = 0; server < noServers; server += 1) {
														if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
															guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	if(fixedFlag$sample50) {
																		for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																			if((index$sample$192_1 == sample$var120)) {
																				if((0 == timeStep$var140)) {
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$75_1)) {
																											for(int var96 = 0; var96 < noServers; var96 += 1) {
																												for(int var100 = 0; var100 < noStates; var100 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var96 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var100 == traceTempVariable$currentState$75_1)) {
																																	{
																																		{
																																			double cv$temp$54$var152;
																																			{
																																				double var152 = current_metric_mean[server][traceTempVariable$currentState$75_1];
																																				cv$temp$54$var152 = var152;
																																			}
																																			double cv$temp$55$var154;
																																			{
																																				double var154 = current_metric_var[server][traceTempVariable$currentState$75_1];
																																				cv$temp$55$var154 = var154;
																																			}
																																			if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$54$var152) / Math.sqrt(cv$temp$55$var154))) - (0.5 * Math.log(cv$temp$55$var154)))));
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
																				for(int index$sample50$194 = 0; index$sample50$194 < noStates; index$sample50$194 += 1) {
																					int distributionTempVariable$var44$196 = index$sample50$194;
																					double cv$probabilitySample50Value195 = (1.0 * distribution$sample50[((index$sample$193 - 0) / 1)][index$sample50$194]);
																					int traceTempVariable$currentState$197_1 = distributionTempVariable$var44$196;
																					if((index$sample$193 == sample$var120)) {
																						if((0 == timeStep$var140)) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$197_1)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$197_1)) {
																																			{
																																				{
																																					double cv$temp$56$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$197_1];
																																						cv$temp$56$var152 = var152;
																																					}
																																					double cv$temp$57$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$197_1];
																																						cv$temp$57$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$56$var152) / Math.sqrt(cv$temp$57$var154))) - (0.5 * Math.log(cv$temp$57$var154)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value195);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
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
																	if((index$sample$23 == sample$var120)) {
																		if((index$timeStep$22 == timeStep$var140)) {
																			for(int var82 = 0; var82 < noServers; var82 += 1) {
																				for(int var86 = 0; var86 < noStates; var86 += 1) {
																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																						if((var82 == server)) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var86 == traceTempVariable$currentState$202_1)) {
																									for(int var96 = 0; var96 < noServers; var96 += 1) {
																										for(int var100 = 0; var100 < noStates; var100 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var96 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var100 == traceTempVariable$currentState$202_1)) {
																															{
																																{
																																	double cv$temp$58$var152;
																																	{
																																		double var152 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																		cv$temp$58$var152 = var152;
																																	}
																																	double cv$temp$59$var154;
																																	{
																																		double var154 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																		cv$temp$59$var154 = var154;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$58$var152) / Math.sqrt(cv$temp$59$var154))) - (0.5 * Math.log(cv$temp$59$var154)))));
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
																	for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																		for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203][0]; index$timeStep$204 += 1) {
																			if(!((index$sample$203 == index$sample$23) && (index$timeStep$204 == index$timeStep$22))) {
																				for(int index$sample63$205 = 0; index$sample63$205 < noStates; index$sample63$205 += 1) {
																					int distributionTempVariable$var57$207 = index$sample63$205;
																					double cv$probabilitySample63Value206 = (1.0 * distribution$sample63[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample63$205]);
																					int traceTempVariable$currentState$208_1 = distributionTempVariable$var57$207;
																					if((index$sample$203 == sample$var120)) {
																						if((index$timeStep$204 == timeStep$var140)) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$208_1)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$208_1)) {
																																			{
																																				{
																																					double cv$temp$60$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$208_1];
																																						cv$temp$60$var152 = var152;
																																					}
																																					double cv$temp$61$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$208_1];
																																						cv$temp$61$var154 = var154;
																																					}
																																					if(((Math.log(cv$probabilitySample63Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$60$var152) / Math.sqrt(cv$temp$61$var154))) - (0.5 * Math.log(cv$temp$61$var154)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value206);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
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
			for(int index$sample$33 = 0; index$sample$33 < noSamples; index$sample$33 += 1) {
				for(int index$timeStep$34 = 1; index$timeStep$34 < length$metric[index$sample$33][0]; index$timeStep$34 += 1) {
					if(!((index$sample$33 == index$sample$23) && (index$timeStep$34 == index$timeStep$22))) {
						for(int index$sample63$35 = 0; index$sample63$35 < noStates; index$sample63$35 += 1) {
							int distributionTempVariable$var57$37 = index$sample63$35;
							double cv$probabilitySample63Value36 = (1.0 * distribution$sample63[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample63$35]);
							int traceTempVariable$var54$38_1 = distributionTempVariable$var57$37;
							if((index$sample$33 == sample$var32)) {
								if((index$timeStep$34 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == traceTempVariable$var54$38_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample63Value36);
											double[] cv$temp$3$var55;
											{
												double[] var55 = m[traceTempVariable$var54$38_1];
												cv$temp$3$var55 = var55;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample63Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var55.length))?Math.log(cv$temp$3$var55[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var54$44_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$48_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var110 = 0; var110 < noServers; var110 += 1) {
																				for(int var114 = 0; var114 < noStates; var114 += 1) {
																					if((var110 == server)) {
																						if((var114 == traceTempVariable$currentState$48_1)) {
																							{
																								{
																									double cv$temp$7$var144;
																									{
																										double var144 = current_metric_valid_bias[server][traceTempVariable$currentState$48_1];
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
													boolean[][][] guard$sample63gaussian172 = guard$sample63gaussian172$global;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1)
																		guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$72_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$72_1)) {
																											if(fixedFlag$sample50) {
																												for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																													if((index$sample$137_1 == sample$var120)) {
																														if((0 == timeStep$var140)) {
																															for(int var96 = 0; var96 < noServers; var96 += 1) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$72_1)) {
																																					{
																																						{
																																							double cv$temp$30$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																								cv$temp$30$var152 = var152;
																																							}
																																							double cv$temp$31$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																								cv$temp$31$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$30$var152) / Math.sqrt(cv$temp$31$var154))) - (0.5 * Math.log(cv$temp$31$var154)))));
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
																												for(int index$sample$138 = 0; index$sample$138 < noSamples; index$sample$138 += 1) {
																													if(true) {
																														for(int index$sample50$139 = 0; index$sample50$139 < noStates; index$sample50$139 += 1) {
																															int distributionTempVariable$var44$141 = index$sample50$139;
																															double cv$probabilitySample50Value140 = (1.0 * distribution$sample50[((index$sample$138 - 0) / 1)][index$sample50$139]);
																															int traceTempVariable$currentState$142_1 = distributionTempVariable$var44$141;
																															if((index$sample$138 == sample$var120)) {
																																if((0 == timeStep$var140)) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		for(int var100 = 0; var100 < noStates; var100 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$142_1)) {
																																							{
																																								{
																																									double cv$temp$32$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$142_1];
																																										cv$temp$32$var152 = var152;
																																									}
																																									double cv$temp$33$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$142_1];
																																										cv$temp$33$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample50Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$32$var152) / Math.sqrt(cv$temp$33$var154))) - (0.5 * Math.log(cv$temp$33$var154)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value140);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int var82 = 0; var82 < noServers; var82 += 1) {
																						for(int var86 = 0; var86 < noStates; var86 += 1) {
																							if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																								if((var82 == server)) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var86 == traceTempVariable$currentState$72_1)) {
																											int traceTempVariable$currentState$146_1 = cv$currentValue;
																											if((index$sample$23 == sample$var120)) {
																												if((index$timeStep$22 == timeStep$var140)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$146_1)) {
																																			{
																																				{
																																					double cv$temp$34$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$146_1];
																																						cv$temp$34$var152 = var152;
																																					}
																																					double cv$temp$35$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$146_1];
																																						cv$temp$35$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$34$var152) / Math.sqrt(cv$temp$35$var154))) - (0.5 * Math.log(cv$temp$35$var154)))));
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
																											int traceTempVariable$currentState$147_1 = distributionTempVariable$var57$37;
																											if((index$sample$33 == sample$var120)) {
																												if((index$timeStep$34 == timeStep$var140)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$147_1)) {
																																			{
																																				{
																																					double cv$temp$36$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$147_1];
																																						cv$temp$36$var152 = var152;
																																					}
																																					double cv$temp$37$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$147_1];
																																						cv$temp$37$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$36$var152) / Math.sqrt(cv$temp$37$var154))) - (0.5 * Math.log(cv$temp$37$var154)))));
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
																											for(int index$sample$148 = 0; index$sample$148 < noSamples; index$sample$148 += 1) {
																												for(int index$timeStep$149 = 1; index$timeStep$149 < length$metric[index$sample$148][0]; index$timeStep$149 += 1) {
																													if((!((index$sample$148 == index$sample$23) && (index$timeStep$149 == index$timeStep$22)) && !((index$sample$148 == index$sample$33) && (index$timeStep$149 == index$timeStep$34)))) {
																														for(int index$sample63$150 = 0; index$sample63$150 < noStates; index$sample63$150 += 1) {
																															int distributionTempVariable$var57$152 = index$sample63$150;
																															double cv$probabilitySample63Value151 = (1.0 * distribution$sample63[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample63$150]);
																															int traceTempVariable$currentState$153_1 = distributionTempVariable$var57$152;
																															if((index$sample$148 == sample$var120)) {
																																if((index$timeStep$149 == timeStep$var140)) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		for(int var100 = 0; var100 < noStates; var100 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$153_1)) {
																																							{
																																								{
																																									double cv$temp$38$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$153_1];
																																										cv$temp$38$var152 = var152;
																																									}
																																									double cv$temp$39$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$153_1];
																																										cv$temp$39$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample63Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$38$var152) / Math.sqrt(cv$temp$39$var154))) - (0.5 * Math.log(cv$temp$39$var154)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value151);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
													int traceTempVariable$currentState$76_1 = cv$currentValue;
													for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
														if((sample$var32 == sample$var120)) {
															for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
																if((timeStep$var49 == timeStep$var140)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(!guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)]) {
																			guard$sample63gaussian172[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = true;
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample50) {
																						for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																							if((index$sample$213_1 == sample$var120)) {
																								if((0 == timeStep$var140)) {
																									for(int var82 = 0; var82 < noServers; var82 += 1) {
																										for(int var86 = 0; var86 < noStates; var86 += 1) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var82 == server)) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var86 == traceTempVariable$currentState$76_1)) {
																															for(int var96 = 0; var96 < noServers; var96 += 1) {
																																for(int var100 = 0; var100 < noStates; var100 += 1) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var96 == server)) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var100 == traceTempVariable$currentState$76_1)) {
																																					{
																																						{
																																							double cv$temp$62$var152;
																																							{
																																								double var152 = current_metric_mean[server][traceTempVariable$currentState$76_1];
																																								cv$temp$62$var152 = var152;
																																							}
																																							double cv$temp$63$var154;
																																							{
																																								double var154 = current_metric_var[server][traceTempVariable$currentState$76_1];
																																								cv$temp$63$var154 = var154;
																																							}
																																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))) < cv$accumulatedConsumerProbabilities))
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																							else {
																																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154))));
																																								else
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$62$var152) / Math.sqrt(cv$temp$63$var154))) - (0.5 * Math.log(cv$temp$63$var154)))));
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
																						for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																							if(true) {
																								for(int index$sample50$215 = 0; index$sample50$215 < noStates; index$sample50$215 += 1) {
																									int distributionTempVariable$var44$217 = index$sample50$215;
																									double cv$probabilitySample50Value216 = (1.0 * distribution$sample50[((index$sample$214 - 0) / 1)][index$sample50$215]);
																									int traceTempVariable$currentState$218_1 = distributionTempVariable$var44$217;
																									if((index$sample$214 == sample$var120)) {
																										if((0 == timeStep$var140)) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$218_1)) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		for(int var100 = 0; var100 < noStates; var100 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$218_1)) {
																																							{
																																								{
																																									double cv$temp$64$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$218_1];
																																										cv$temp$64$var152 = var152;
																																									}
																																									double cv$temp$65$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$218_1];
																																										cv$temp$65$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample50Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$64$var152) / Math.sqrt(cv$temp$65$var154))) - (0.5 * Math.log(cv$temp$65$var154)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value216);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
																					if((index$sample$23 == sample$var120)) {
																						if((index$timeStep$22 == timeStep$var140)) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$223_1)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$223_1)) {
																																			{
																																				{
																																					double cv$temp$66$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$223_1];
																																						cv$temp$66$var152 = var152;
																																					}
																																					double cv$temp$67$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$223_1];
																																						cv$temp$67$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$66$var152) / Math.sqrt(cv$temp$67$var154))) - (0.5 * Math.log(cv$temp$67$var154)))));
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
																					int traceTempVariable$currentState$224_1 = distributionTempVariable$var57$37;
																					if((index$sample$33 == sample$var120)) {
																						if((index$timeStep$34 == timeStep$var140)) {
																							for(int var82 = 0; var82 < noServers; var82 += 1) {
																								for(int var86 = 0; var86 < noStates; var86 += 1) {
																									if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																										if((var82 == server)) {
																											if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																												if((var86 == traceTempVariable$currentState$224_1)) {
																													for(int var96 = 0; var96 < noServers; var96 += 1) {
																														for(int var100 = 0; var100 < noStates; var100 += 1) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var96 == server)) {
																																	if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																		if((var100 == traceTempVariable$currentState$224_1)) {
																																			{
																																				{
																																					double cv$temp$68$var152;
																																					{
																																						double var152 = current_metric_mean[server][traceTempVariable$currentState$224_1];
																																						cv$temp$68$var152 = var152;
																																					}
																																					double cv$temp$69$var154;
																																					{
																																						double var154 = current_metric_var[server][traceTempVariable$currentState$224_1];
																																						cv$temp$69$var154 = var154;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$68$var152) / Math.sqrt(cv$temp$69$var154))) - (0.5 * Math.log(cv$temp$69$var154)))));
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
																					for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																						for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225][0]; index$timeStep$226 += 1) {
																							if((!((index$sample$225 == index$sample$23) && (index$timeStep$226 == index$timeStep$22)) && !((index$sample$225 == index$sample$33) && (index$timeStep$226 == index$timeStep$34)))) {
																								for(int index$sample63$227 = 0; index$sample63$227 < noStates; index$sample63$227 += 1) {
																									int distributionTempVariable$var57$229 = index$sample63$227;
																									double cv$probabilitySample63Value228 = (1.0 * distribution$sample63[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample63$227]);
																									int traceTempVariable$currentState$230_1 = distributionTempVariable$var57$229;
																									if((index$sample$225 == sample$var120)) {
																										if((index$timeStep$226 == timeStep$var140)) {
																											for(int var82 = 0; var82 < noServers; var82 += 1) {
																												for(int var86 = 0; var86 < noStates; var86 += 1) {
																													if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																														if((var82 == server)) {
																															if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																if((var86 == traceTempVariable$currentState$230_1)) {
																																	for(int var96 = 0; var96 < noServers; var96 += 1) {
																																		for(int var100 = 0; var100 < noStates; var100 += 1) {
																																			if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																				if((var96 == server)) {
																																					if(metric_valid_g[sample$var120][server][timeStep$var140]) {
																																						if((var100 == traceTempVariable$currentState$230_1)) {
																																							{
																																								{
																																									double cv$temp$70$var152;
																																									{
																																										double var152 = current_metric_mean[server][traceTempVariable$currentState$230_1];
																																										cv$temp$70$var152 = var152;
																																									}
																																									double cv$temp$71$var154;
																																									{
																																										double var154 = current_metric_var[server][traceTempVariable$currentState$230_1];
																																										cv$temp$71$var154 = var154;
																																									}
																																									if(((Math.log(cv$probabilitySample63Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$70$var152) / Math.sqrt(cv$temp$71$var154))) - (0.5 * Math.log(cv$temp$71$var154)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value228);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
					int traceTempVariable$var54$269_1 = cv$currentValue;
					for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
						if((sample$var32 == index$sample$269_2)) {
							for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2][0]; index$timeStep$269_3 += 1) {
								if((timeStep$var49 == (index$timeStep$269_3 - 1))) {
									{
										int index$timeStep$271 = index$timeStep$269_3;
										int index$sample$272 = index$sample$269_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var26 = 0; var26 < noStates; var26 += 1) {
											if((var26 == traceTempVariable$var54$269_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample50) {
														for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
															if((index$sample$274_1 == sample$var32)) {
																if((0 == (timeStep$var49 - 1))) {
																	for(int index$var26$280_1 = 0; index$var26$280_1 < noStates; index$var26$280_1 += 1) {
																		if((index$var26$280_1 == st[sample$var32][(timeStep$var49 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
															if(true) {
																for(int index$sample50$276 = 0; index$sample50$276 < noStates; index$sample50$276 += 1) {
																	int distributionTempVariable$var44$278 = index$sample50$276;
																	double cv$probabilitySample50Value277 = (1.0 * distribution$sample50[((index$sample$275 - 0) / 1)][index$sample50$276]);
																	int traceTempVariable$var54$279_1 = distributionTempVariable$var44$278;
																	if((index$sample$275 == sample$var32)) {
																		if((0 == (timeStep$var49 - 1))) {
																			for(int index$var26$281_1 = 0; index$var26$281_1 < noStates; index$var26$281_1 += 1) {
																				if((index$var26$281_1 == traceTempVariable$var54$279_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample50Value277);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var54$282_1 = cv$currentValue;
													if((index$sample$23 == sample$var32)) {
														if((index$timeStep$22 == (timeStep$var49 - 1))) {
															for(int index$var26$289_1 = 0; index$var26$289_1 < noStates; index$var26$289_1 += 1) {
																if((index$var26$289_1 == traceTempVariable$var54$282_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
														for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283][0]; index$timeStep$284 += 1) {
															if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																for(int index$sample63$285 = 0; index$sample63$285 < noStates; index$sample63$285 += 1) {
																	int distributionTempVariable$var57$287 = index$sample63$285;
																	double cv$probabilitySample63Value286 = (1.0 * distribution$sample63[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample63$285]);
																	int traceTempVariable$var54$288_1 = distributionTempVariable$var57$287;
																	if((index$sample$283 == sample$var32)) {
																		if((index$timeStep$284 == (timeStep$var49 - 1))) {
																			for(int index$var26$290_1 = 0; index$var26$290_1 < noStates; index$var26$290_1 += 1) {
																				if((index$var26$290_1 == traceTempVariable$var54$288_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample63Value286);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var55;
													{
														double[] var55 = m[traceTempVariable$var54$269_1];
														cv$temp$72$var55 = var55;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var55);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample63[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
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

	private final void sample96(int var82, int var86) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = current_metric_mean[var82][var86];
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
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var76 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var77))?(-Math.log((cv$temp$1$var77 - cv$temp$0$var76))):Double.NEGATIVE_INFINITY));
				{
					{
						for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
									if(fixedFlag$sample50) {
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
																						for(int var96 = 0; var96 < noServers; var96 += 1) {
																							for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$2$var152) / Math.sqrt(cv$temp$3$var154))) - (0.5 * Math.log(cv$temp$3$var154)))));
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
																			if(fixedFlag$sample63) {
																				for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$30_1][0]; timeStep$var49 += 1) {
																						if((index$sample$30_1 == sample$var120)) {
																							if((timeStep$var49 == timeStep$var140)) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$4$var152) / Math.sqrt(cv$temp$5$var154))) - (0.5 * Math.log(cv$temp$5$var154)))));
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
																							for(int index$sample63$33 = 0; index$sample63$33 < noStates; index$sample63$33 += 1) {
																								int distributionTempVariable$var57$35 = index$sample63$33;
																								double cv$probabilitySample63Value34 = (1.0 * distribution$sample63[((index$sample$31 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$33]);
																								int traceTempVariable$currentState$36_1 = distributionTempVariable$var57$35;
																								if((index$sample$31 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																		if(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$6$var152) / Math.sqrt(cv$temp$7$var154))) - (0.5 * Math.log(cv$temp$7$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value34);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
												for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
													int distributionTempVariable$var44$8 = index$sample50$6;
													double cv$probabilitySample50Value7 = (1.0 * distribution$sample50[((sample$var32 - 0) / 1)][index$sample50$6]);
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
																							for(int var96 = 0; var96 < noServers; var96 += 1) {
																								for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																															if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$8$var152) / Math.sqrt(cv$temp$9$var154))) - (0.5 * Math.log(cv$temp$9$var154)))));
																															}
																															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																														}
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
																							for(int index$sample50$41 = 0; index$sample50$41 < noStates; index$sample50$41 += 1) {
																								int distributionTempVariable$var44$43 = index$sample50$41;
																								double cv$probabilitySample50Value42 = (cv$probabilitySample50Value7 * distribution$sample50[((index$sample$40 - 0) / 1)][index$sample50$41]);
																								int traceTempVariable$currentState$44_1 = distributionTempVariable$var44$43;
																								if((index$sample$40 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																		if(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$10$var152) / Math.sqrt(cv$temp$11$var154))) - (0.5 * Math.log(cv$temp$11$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value42);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					if(fixedFlag$sample63) {
																						for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[index$sample$47_1][0]; timeStep$var49 += 1) {
																								if((index$sample$47_1 == sample$var120)) {
																									if((timeStep$var49 == timeStep$var140)) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																		if(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$12$var152) / Math.sqrt(cv$temp$13$var154))) - (0.5 * Math.log(cv$temp$13$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value7);
																																	}
																																}
																															}
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
																									for(int index$sample63$50 = 0; index$sample63$50 < noStates; index$sample63$50 += 1) {
																										int distributionTempVariable$var57$52 = index$sample63$50;
																										double cv$probabilitySample63Value51 = (cv$probabilitySample50Value7 * distribution$sample63[((index$sample$48 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$50]);
																										int traceTempVariable$currentState$53_1 = distributionTempVariable$var57$52;
																										if((index$sample$48 == sample$var120)) {
																											if((timeStep$var49 == timeStep$var140)) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																				if(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$14$var152) / Math.sqrt(cv$temp$15$var154))) - (0.5 * Math.log(cv$temp$15$var154)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value51);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
									if(fixedFlag$sample63) {
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
																				if(fixedFlag$sample50) {
																					for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																						if((index$sample$56_1 == sample$var120)) {
																							if((0 == timeStep$var140)) {
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$16$var152) / Math.sqrt(cv$temp$17$var154))) - (0.5 * Math.log(cv$temp$17$var154)))));
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
																							for(int index$sample50$58 = 0; index$sample50$58 < noStates; index$sample50$58 += 1) {
																								int distributionTempVariable$var44$60 = index$sample50$58;
																								double cv$probabilitySample50Value59 = (1.0 * distribution$sample50[((index$sample$57 - 0) / 1)][index$sample50$58]);
																								int traceTempVariable$currentState$61_1 = distributionTempVariable$var44$60;
																								if((index$sample$57 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																		if(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$18$var152) / Math.sqrt(cv$temp$19$var154))) - (0.5 * Math.log(cv$temp$19$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value59);
																																	}
																																}
																															}
																														}
																													}
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
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$20$var152) / Math.sqrt(cv$temp$21$var154))) - (0.5 * Math.log(cv$temp$21$var154)))));
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
													for(int index$sample63$18 = 0; index$sample63$18 < noStates; index$sample63$18 += 1) {
														int distributionTempVariable$var57$20 = index$sample63$18;
														double cv$probabilitySample63Value19 = (1.0 * distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)][index$sample63$18]);
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
																						if(fixedFlag$sample50) {
																							for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																								if((index$sample$66_1 == sample$var120)) {
																									if((0 == timeStep$var140)) {
																										for(int var96 = 0; var96 < noServers; var96 += 1) {
																											for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																		if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$22$var152) / Math.sqrt(cv$temp$23$var154))) - (0.5 * Math.log(cv$temp$23$var154)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																																	}
																																}
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
																									for(int index$sample50$68 = 0; index$sample50$68 < noStates; index$sample50$68 += 1) {
																										int distributionTempVariable$var44$70 = index$sample50$68;
																										double cv$probabilitySample50Value69 = (cv$probabilitySample63Value19 * distribution$sample50[((index$sample$67 - 0) / 1)][index$sample50$68]);
																										int traceTempVariable$currentState$71_1 = distributionTempVariable$var44$70;
																										if((index$sample$67 == sample$var120)) {
																											if((0 == timeStep$var140)) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																				if(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))))) + 1)) + (Math.log(cv$probabilitySample50Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$24$var152) / Math.sqrt(cv$temp$25$var154))) - (0.5 * Math.log(cv$temp$25$var154)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample50Value69);
																																			}
																																		}
																																	}
																																}
																															}
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
																								for(int var96 = 0; var96 < noServers; var96 += 1) {
																									for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																if(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$26$var152) / Math.sqrt(cv$temp$27$var154))) - (0.5 * Math.log(cv$temp$27$var154)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value19);
																															}
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
																									for(int index$sample63$77 = 0; index$sample63$77 < noStates; index$sample63$77 += 1) {
																										int distributionTempVariable$var57$79 = index$sample63$77;
																										double cv$probabilitySample63Value78 = (cv$probabilitySample63Value19 * distribution$sample63[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample63$77]);
																										int traceTempVariable$currentState$80_1 = distributionTempVariable$var57$79;
																										if((index$sample$75 == sample$var120)) {
																											if((index$timeStep$76 == timeStep$var140)) {
																												for(int var96 = 0; var96 < noServers; var96 += 1) {
																													for(int var100 = 0; var100 < noStates; var100 += 1) {
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
																																				if(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))))) + 1)) + (Math.log(cv$probabilitySample63Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - cv$temp$28$var152) / Math.sqrt(cv$temp$29$var154))) - (0.5 * Math.log(cv$temp$29$var154)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample63Value78);
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
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
			guard$sample50gaussian172$global = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
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
			guard$sample63gaussian172$global = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
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
			distribution$sample50 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				distribution$sample50[((sample$var32 - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample63 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)][];
				distribution$sample63[((sample$var32 - 0) / 1)] = subarray$0;
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					subarray$0[((timeStep$var49 - 1) / 1)] = new double[noStates];
			}
		}
		{
			logProbability$var43 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample50 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var56 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				logProbability$var56[((sample$var32 - 0) / 1)] = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample63 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				logProbability$sample63[((sample$var32 - 0) / 1)] = new double[((((length$metric[sample$var32][0] - 1) - 1) / 1) + 1)];
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
			logProbability$sample161 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample161[((sample$var120 - 0) / 1)] = subarray$0;
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
			logProbability$sample173 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample173[((sample$var120 - 0) / 1)] = subarray$0;
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
			if(!fixedFlag$sample50)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample63)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			boolean[][] var135 = metric_valid_g[sample$var120];
			double[][] var131 = metric_g[sample$var120];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var120][server];
				double[] metric_inner = var131[server];
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					if(!fixedFlag$sample161)
						metric_valid_inner[timeStep$var140] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
					if(var135[server][timeStep$var140]) {
						if(!fixedFlag$sample173)
							metric_inner[timeStep$var140] = ((Math.sqrt(current_metric_var[server][st[sample$var120][timeStep$var140]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var120][timeStep$var140]]);
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
			double[] cv$distribution$sample50 = distribution$sample50[((sample$var32 - 0) / 1)];
			for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1) {
				double cv$value = (((0.0 <= index$var43) && (index$var43 < initialStateDistribution.length))?initialStateDistribution[index$var43]:0.0);
				if(!fixedFlag$sample50)
					cv$distribution$sample50[index$var43] = cv$value;
			}
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				double[] cv$distribution$sample63 = distribution$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)];
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample63)
						cv$distribution$sample63[index$var56] = 0.0;
				}
				if(fixedFlag$sample50) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample$var32)) {
							if((0 == (timeStep$var49 - 1))) {
								for(int var26 = 0; var26 < noStates; var26 += 1) {
									if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
										{
											if(!fixedFlag$sample63) {
												double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
												for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
													cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (1.0 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
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
							for(int index$sample50$3 = 0; index$sample50$3 < noStates; index$sample50$3 += 1) {
								int distributionTempVariable$var44$5 = index$sample50$3;
								double cv$probabilitySample50Value4 = (1.0 * distribution$sample50[((index$sample$2 - 0) / 1)][index$sample50$3]);
								int traceTempVariable$var54$6_1 = distributionTempVariable$var44$5;
								if((index$sample$2 == sample$var32)) {
									if((0 == (timeStep$var49 - 1))) {
										for(int var26 = 0; var26 < noStates; var26 += 1) {
											if((var26 == traceTempVariable$var54$6_1)) {
												{
													if(!fixedFlag$sample63) {
														double[] var55 = m[traceTempVariable$var54$6_1];
														for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
															cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (cv$probabilitySample50Value4 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample63) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample$var32)) {
								if((index$timeStep$9_2 == (timeStep$var49 - 1))) {
									for(int var26 = 0; var26 < noStates; var26 += 1) {
										if((var26 == st[sample$var32][(timeStep$var49 - 1)])) {
											{
												if(!fixedFlag$sample63) {
													double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
													for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
														cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (1.0 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
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
								for(int index$sample63$12 = 0; index$sample63$12 < noStates; index$sample63$12 += 1) {
									int distributionTempVariable$var57$14 = index$sample63$12;
									double cv$probabilitySample63Value13 = (1.0 * distribution$sample63[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample63$12]);
									int traceTempVariable$var54$15_1 = distributionTempVariable$var57$14;
									if((index$sample$10 == sample$var32)) {
										if((index$timeStep$11 == (timeStep$var49 - 1))) {
											for(int var26 = 0; var26 < noStates; var26 += 1) {
												if((var26 == traceTempVariable$var54$15_1)) {
													{
														if(!fixedFlag$sample63) {
															double[] var55 = m[traceTempVariable$var54$15_1];
															for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
																cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (cv$probabilitySample63Value13 * (((0.0 <= index$var56) && (index$var56 < var55.length))?var55[index$var56]:0.0)));
														}
													}
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
					if(!fixedFlag$sample63)
						cv$var56$sum = (cv$var56$sum + cv$distribution$sample63[index$var56]);
				}
				for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1) {
					if(!fixedFlag$sample63)
						cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] / cv$var56$sum);
				}
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
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
			if(!fixedFlag$sample50)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample63)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
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
				if(!fixedFlag$sample50)
					sample50(sample$var32);
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					if(!fixedFlag$sample63)
						sample63(sample$var32, timeStep$var49);
				}
			}
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1) {
					if(!fixedFlag$sample96)
						sample96(var82, var86);
				}
			}
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1) {
					if(!fixedFlag$sample111)
						sample111(var96, var100);
				}
			}
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1) {
					if(!fixedFlag$sample126)
						sample126(var110, var114);
				}
			}
		} else {
			for(int var110 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var110 >= ((0 - 1) + 1); var110 -= 1) {
				for(int var114 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var114 >= ((0 - 1) + 1); var114 -= 1) {
					if(!fixedFlag$sample126)
						sample126(var110, var114);
				}
			}
			for(int var96 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var96 >= ((0 - 1) + 1); var96 -= 1) {
				for(int var100 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var100 >= ((0 - 1) + 1); var100 -= 1) {
					if(!fixedFlag$sample111)
						sample111(var96, var100);
				}
			}
			for(int var82 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var82 >= ((0 - 1) + 1); var82 -= 1) {
				for(int var86 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var86 >= ((0 - 1) + 1); var86 -= 1) {
					if(!fixedFlag$sample96)
						sample96(var82, var86);
				}
			}
			for(int sample$var32 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var32 >= ((0 - 1) + 1); sample$var32 -= 1) {
				for(int timeStep$var49 = (length$metric[sample$var32][0] - ((((length$metric[sample$var32][0] - 1) - 1) % 1) + 1)); timeStep$var49 >= ((1 - 1) + 1); timeStep$var49 -= 1) {
					if(!fixedFlag$sample63)
						sample63(sample$var32, timeStep$var49);
				}
				if(!fixedFlag$sample50)
					sample50(sample$var32);
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
		if(!fixedProbFlag$sample50) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				logProbability$sample50[((sample$var32 - 0) / 1)] = 0.0;
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				logProbability$var56[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample63) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					logProbability$sample63[((sample$var32 - 0) / 1)][((timeStep$var49 - 1) / 1)] = 0.0;
			}
		}
		logProbability$var78 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample96)
			logProbability$var87 = 0.0;
		logProbability$var92 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample111)
			logProbability$var101 = 0.0;
		logProbability$var106 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample126)
			logProbability$var115 = 0.0;
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var145[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample161) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample161[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var155[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample173) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample173[((sample$var120 - 0) / 1)][((server - 0) / 1)][((timeStep$var140 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample96)
			logProbabilityValue$sample96();
		if(fixedFlag$sample111)
			logProbabilityValue$sample111();
		if(fixedFlag$sample126)
			logProbabilityValue$sample126();
		logProbabilityValue$sample161();
		logProbabilityValue$sample173();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample30();
		logProbabilityDistribution$sample50();
		logProbabilityDistribution$sample63();
		logProbabilityValue$sample96();
		logProbabilityValue$sample111();
		logProbabilityValue$sample126();
		logProbabilityDistribution$sample161();
		logProbabilityDistribution$sample173();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample30();
		logProbabilityValue$sample50();
		logProbabilityValue$sample63();
		logProbabilityValue$sample96();
		logProbabilityValue$sample111();
		logProbabilityValue$sample126();
		logProbabilityValue$sample161();
		logProbabilityValue$sample173();
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
			if(!fixedFlag$sample50)
				var41[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var50 = st[sample$var32];
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
				if(!fixedFlag$sample63)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		for(int var82 = 0; var82 < noServers; var82 += 1) {
			double[] var83 = current_metric_mean[var82];
			for(int var86 = 0; var86 < noStates; var86 += 1) {
				if(!fixedFlag$sample96)
					var83[var86] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var96 = 0; var96 < noServers; var96 += 1) {
			double[] var97 = current_metric_var[var96];
			for(int var100 = 0; var100 < noStates; var100 += 1) {
				if(!fixedFlag$sample111)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var110 = 0; var110 < noServers; var110 += 1) {
			double[] var111 = current_metric_valid_bias[var110];
			for(int var114 = 0; var114 < noStates; var114 += 1) {
				if(!fixedFlag$sample126)
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