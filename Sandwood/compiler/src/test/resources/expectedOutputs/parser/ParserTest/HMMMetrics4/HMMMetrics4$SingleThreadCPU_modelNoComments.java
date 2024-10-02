package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics4$CoreInterface {
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[] cv$distributionAccumulator$var83;
	private double[] cv$var245$stateProbabilityGlobal;
	private double[] cv$var28$countGlobal;
	private double[] cv$var41$countGlobal;
	private double[] cv$var65$stateProbabilityGlobal;
	private double[] cv$var84$stateProbabilityGlobal;
	private double[][] distribution$sample71;
	private double[][][] distribution$sample90;
	private boolean fixedFlag$sample150 = false;
	private boolean fixedFlag$sample178 = false;
	private boolean fixedFlag$sample206 = false;
	private boolean fixedFlag$sample260 = false;
	private boolean fixedFlag$sample275 = false;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample44 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedFlag$sample90 = false;
	private boolean fixedProbFlag$sample150 = false;
	private boolean fixedProbFlag$sample178 = false;
	private boolean fixedProbFlag$sample206 = false;
	private boolean fixedProbFlag$sample260 = false;
	private boolean fixedProbFlag$sample275 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample44 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample90 = false;
	private boolean[][][] guard$sample71gaussian274$global;
	private boolean[][][] guard$sample90gaussian274$global;
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
	private double[][][] logProbability$sample260;
	private double[][][] logProbability$sample275;
	private double[] logProbability$sample71;
	private double[][] logProbability$sample90;
	private double logProbability$st;
	private double logProbability$var119;
	private double logProbability$var141;
	private double logProbability$var146;
	private double logProbability$var168;
	private double logProbability$var173;
	private double logProbability$var195;
	private double[][][] logProbability$var244;
	private double[][][] logProbability$var257;
	private double logProbability$var27;
	private double logProbability$var29;
	private double logProbability$var41;
	private double[] logProbability$var64;
	private double[][] logProbability$var83;
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
		fixedProbFlag$sample150 = false;
		fixedProbFlag$sample275 = false;
	}

	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value) {
		current_metric_valid_bias = cv$value;
		setFlag$current_metric_valid_bias = true;
		fixedProbFlag$sample206 = false;
		fixedProbFlag$sample260 = false;
	}

	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	@Override
	public final void set$current_metric_var(double[][] cv$value) {
		current_metric_var = cv$value;
		setFlag$current_metric_var = true;
		fixedProbFlag$sample178 = false;
		fixedProbFlag$sample275 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample150() {
		return fixedFlag$sample150;
	}

	@Override
	public final void set$fixedFlag$sample150(boolean cv$value) {
		fixedFlag$sample150 = cv$value;
		fixedProbFlag$sample150 = (fixedFlag$sample150 && fixedProbFlag$sample150);
		fixedProbFlag$sample275 = (fixedFlag$sample150 && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample178() {
		return fixedFlag$sample178;
	}

	@Override
	public final void set$fixedFlag$sample178(boolean cv$value) {
		fixedFlag$sample178 = cv$value;
		fixedProbFlag$sample178 = (fixedFlag$sample178 && fixedProbFlag$sample178);
		fixedProbFlag$sample275 = (fixedFlag$sample178 && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample206() {
		return fixedFlag$sample206;
	}

	@Override
	public final void set$fixedFlag$sample206(boolean cv$value) {
		fixedFlag$sample206 = cv$value;
		fixedProbFlag$sample206 = (fixedFlag$sample206 && fixedProbFlag$sample206);
		fixedProbFlag$sample260 = (fixedFlag$sample206 && fixedProbFlag$sample260);
	}

	@Override
	public final boolean get$fixedFlag$sample260() {
		return fixedFlag$sample260;
	}

	@Override
	public final void set$fixedFlag$sample260(boolean cv$value) {
		fixedFlag$sample260 = cv$value;
		fixedProbFlag$sample260 = (fixedFlag$sample260 && fixedProbFlag$sample260);
	}

	@Override
	public final boolean get$fixedFlag$sample275() {
		return fixedFlag$sample275;
	}

	@Override
	public final void set$fixedFlag$sample275(boolean cv$value) {
		fixedFlag$sample275 = cv$value;
		fixedProbFlag$sample275 = (fixedFlag$sample275 && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample71 = (fixedFlag$sample31 && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		fixedFlag$sample44 = cv$value;
		fixedProbFlag$sample44 = (fixedFlag$sample44 && fixedProbFlag$sample44);
		fixedProbFlag$sample90 = (fixedFlag$sample44 && fixedProbFlag$sample90);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedProbFlag$sample71);
		fixedProbFlag$sample90 = (fixedFlag$sample71 && fixedProbFlag$sample90);
		fixedProbFlag$sample260 = (fixedFlag$sample71 && fixedProbFlag$sample260);
		fixedProbFlag$sample275 = (fixedFlag$sample71 && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (fixedFlag$sample90 && fixedProbFlag$sample90);
		fixedProbFlag$sample260 = (fixedFlag$sample90 && fixedProbFlag$sample260);
		fixedProbFlag$sample275 = (fixedFlag$sample90 && fixedProbFlag$sample275);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample71 = false;
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
		fixedProbFlag$sample44 = false;
		fixedProbFlag$sample90 = false;
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
		fixedProbFlag$sample275 = false;
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
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample90 = false;
		fixedProbFlag$sample260 = false;
		fixedProbFlag$sample275 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample260() {
		if(!fixedProbFlag$sample260) {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var207][server][timeStep$var239];
							if(fixedFlag$sample71) {
								for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
									if((sample$var53 == sample$var207)) {
										if((0 == timeStep$var239)) {
											for(int var184 = 0; var184 < noServers; var184 += 1) {
												for(int var194 = 0; var194 < noStates; var194 += 1) {
													if((var184 == server)) {
														if((var194 == st[sample$var207][timeStep$var239])) {
															{
																double var243 = current_metric_valid_bias[server][st[sample$var207][timeStep$var239]];
																double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var243));
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
								for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
									if(true) {
										for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
											int distributionTempVariable$var65$6 = index$sample71$4;
											double cv$probabilitySample71Value5 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$4]);
											int traceTempVariable$currentState$7_1 = distributionTempVariable$var65$6;
											if((sample$var53 == sample$var207)) {
												if((0 == timeStep$var239)) {
													for(int var184 = 0; var184 < noServers; var184 += 1) {
														for(int var194 = 0; var194 < noStates; var194 += 1) {
															if((var184 == server)) {
																if((var194 == traceTempVariable$currentState$7_1)) {
																	{
																		double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$7_1];
																		double cv$weightedProbability = (Math.log(cv$probabilitySample71Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var243));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value5);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample90) {
								for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
									for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
										if((sample$var53 == sample$var207)) {
											if((timeStep$var76 == timeStep$var239)) {
												for(int var184 = 0; var184 < noServers; var184 += 1) {
													for(int var194 = 0; var194 < noStates; var194 += 1) {
														if((var184 == server)) {
															if((var194 == st[sample$var207][timeStep$var239])) {
																{
																	double var243 = current_metric_valid_bias[server][st[sample$var207][timeStep$var239]];
																	double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var243));
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
								for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
									for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
										if(true) {
											for(int index$sample90$13 = 0; index$sample90$13 < noStates; index$sample90$13 += 1) {
												int distributionTempVariable$var84$15 = index$sample90$13;
												double cv$probabilitySample90Value14 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$13]);
												int traceTempVariable$currentState$16_1 = distributionTempVariable$var84$15;
												if((sample$var53 == sample$var207)) {
													if((timeStep$var76 == timeStep$var239)) {
														for(int var184 = 0; var184 < noServers; var184 += 1) {
															for(int var194 = 0; var194 < noStates; var194 += 1) {
																if((var184 == server)) {
																	if((var194 == traceTempVariable$currentState$16_1)) {
																		{
																			double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$16_1];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample90Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var243));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value14);
																		}
																	}
																}
															}
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
						logProbability$var244[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample260[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
						{
							for(int index$timeStep$24_1 = 0; index$timeStep$24_1 < length$metric[sample$var207][0]; index$timeStep$24_1 += 1) {
								if((timeStep$var239 == index$timeStep$24_1)) {
									if(!cv$guard$metric_g) {
										cv$guard$metric_g = true;
										logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample260 = (((fixedFlag$sample260 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample206);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample260[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var244[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
						{
							for(int index$timeStep$26_1 = 0; index$timeStep$26_1 < length$metric[sample$var207][0]; index$timeStep$26_1 += 1) {
								if((timeStep$var239 == index$timeStep$26_1)) {
									if(!cv$guard$metric_g) {
										cv$guard$metric_g = true;
										logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
									}
								}
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

	private final void logProbabilityDistribution$sample275() {
		if(!fixedProbFlag$sample275) {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								double cv$sampleValue = metric_g[sample$var207][server][timeStep$var239];
								if(fixedFlag$sample71) {
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										if((sample$var53 == sample$var207)) {
											if((0 == timeStep$var239)) {
												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
													for(int var130 = 0; var130 < noServers; var130 += 1) {
														for(int var140 = 0; var140 < noStates; var140 += 1) {
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																if((var130 == server)) {
																	if((var140 == st[sample$var207][timeStep$var239])) {
																		for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																			if((index$sample$10_1 == sample$var207)) {
																				if((0 == timeStep$var239)) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						for(int var157 = 0; var157 < noServers; var157 += 1) {
																							for(int var167 = 0; var167 < noStates; var167 += 1) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									if((var157 == server)) {
																										if((var167 == st[sample$var207][timeStep$var239])) {
																											{
																												double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																												double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																												double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
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
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										if(true) {
											for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
												int distributionTempVariable$var65$6 = index$sample71$4;
												double cv$probabilitySample71Value5 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$4]);
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var65$6;
												if((sample$var53 == sample$var207)) {
													if((0 == timeStep$var239)) {
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															for(int var130 = 0; var130 < noServers; var130 += 1) {
																for(int var140 = 0; var140 < noStates; var140 += 1) {
																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																		if((var130 == server)) {
																			if((var140 == traceTempVariable$currentState$7_1)) {
																				int traceTempVariable$currentState$11_1 = distributionTempVariable$var65$6;
																				if((sample$var53 == sample$var207)) {
																					if((0 == timeStep$var239)) {
																						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																							for(int var157 = 0; var157 < noServers; var157 += 1) {
																								for(int var167 = 0; var167 < noStates; var167 += 1) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										if((var157 == server)) {
																											if((var167 == traceTempVariable$currentState$11_1)) {
																												{
																													double var254 = current_metric_mean[server][traceTempVariable$currentState$11_1];
																													double var256 = current_metric_var[server][traceTempVariable$currentState$11_1];
																													double cv$weightedProbability = (Math.log(cv$probabilitySample71Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																													if((cv$weightedProbability < cv$distributionAccumulator))
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																													else {
																														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																															cv$distributionAccumulator = cv$weightedProbability;
																														else
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																													}
																													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value5);
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
																					if(!(index$sample$12 == sample$var53)) {
																						for(int index$sample71$13 = 0; index$sample71$13 < noStates; index$sample71$13 += 1) {
																							int distributionTempVariable$var65$15 = index$sample71$13;
																							double cv$probabilitySample71Value14 = (cv$probabilitySample71Value5 * distribution$sample71[((index$sample$12 - 0) / 1)][index$sample71$13]);
																							int traceTempVariable$currentState$16_1 = distributionTempVariable$var65$15;
																							if((index$sample$12 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == traceTempVariable$currentState$16_1)) {
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																double var256 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample71Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value14);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample71) {
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										if((sample$var53 == sample$var207)) {
											if((0 == timeStep$var239)) {
												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
													for(int var130 = 0; var130 < noServers; var130 += 1) {
														for(int var140 = 0; var140 < noStates; var140 += 1) {
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																if((var130 == server)) {
																	if((var140 == st[sample$var207][timeStep$var239])) {
																		if(fixedFlag$sample90) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$28_1][0]; timeStep$var76 += 1) {
																					if((index$sample$28_1 == sample$var207)) {
																						if((timeStep$var76 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == st[sample$var207][timeStep$var239])) {
																													{
																														double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																														double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
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
																				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$29][0]; timeStep$var76 += 1) {
																					if(true) {
																						for(int index$sample90$31 = 0; index$sample90$31 < noStates; index$sample90$31 += 1) {
																							int distributionTempVariable$var84$33 = index$sample90$31;
																							double cv$probabilitySample90Value32 = (1.0 * distribution$sample90[((index$sample$29 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var84$33;
																							if((index$sample$29 == sample$var207)) {
																								if((timeStep$var76 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == traceTempVariable$currentState$34_1)) {
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$34_1];
																																double var256 = current_metric_var[server][traceTempVariable$currentState$34_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample90Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										if(true) {
											for(int index$sample71$22 = 0; index$sample71$22 < noStates; index$sample71$22 += 1) {
												int distributionTempVariable$var65$24 = index$sample71$22;
												double cv$probabilitySample71Value23 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$22]);
												int traceTempVariable$currentState$25_1 = distributionTempVariable$var65$24;
												if((sample$var53 == sample$var207)) {
													if((0 == timeStep$var239)) {
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															for(int var130 = 0; var130 < noServers; var130 += 1) {
																for(int var140 = 0; var140 < noStates; var140 += 1) {
																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																		if((var130 == server)) {
																			if((var140 == traceTempVariable$currentState$25_1)) {
																				if(fixedFlag$sample90) {
																					for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$35_1][0]; timeStep$var76 += 1) {
																							if((index$sample$35_1 == sample$var207)) {
																								if((timeStep$var76 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == traceTempVariable$currentState$25_1)) {
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																double var256 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample71Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value23);
																															}
																														}
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
																						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$36][0]; timeStep$var76 += 1) {
																							if(true) {
																								for(int index$sample90$38 = 0; index$sample90$38 < noStates; index$sample90$38 += 1) {
																									int distributionTempVariable$var84$40 = index$sample90$38;
																									double cv$probabilitySample90Value39 = (cv$probabilitySample71Value23 * distribution$sample90[((index$sample$36 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$38]);
																									int traceTempVariable$currentState$41_1 = distributionTempVariable$var84$40;
																									if((index$sample$36 == sample$var207)) {
																										if((timeStep$var76 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$41_1)) {
																																	{
																																		double var254 = current_metric_mean[server][traceTempVariable$currentState$41_1];
																																		double var256 = current_metric_var[server][traceTempVariable$currentState$41_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample90Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value39);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample90) {
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
											if((sample$var53 == sample$var207)) {
												if((timeStep$var76 == timeStep$var239)) {
													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
														for(int var130 = 0; var130 < noServers; var130 += 1) {
															for(int var140 = 0; var140 < noStates; var140 += 1) {
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	if((var130 == server)) {
																		if((var140 == st[sample$var207][timeStep$var239])) {
																			for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																				for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																					if((index$sample$55_1 == sample$var207)) {
																						if((index$timeStep$55_2 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == st[sample$var207][timeStep$var239])) {
																													{
																														double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																														double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
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
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
											if(true) {
												for(int index$sample90$49 = 0; index$sample90$49 < noStates; index$sample90$49 += 1) {
													int distributionTempVariable$var84$51 = index$sample90$49;
													double cv$probabilitySample90Value50 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$49]);
													int traceTempVariable$currentState$52_1 = distributionTempVariable$var84$51;
													if((sample$var53 == sample$var207)) {
														if((timeStep$var76 == timeStep$var239)) {
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																for(int var130 = 0; var130 < noServers; var130 += 1) {
																	for(int var140 = 0; var140 < noStates; var140 += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if((var130 == server)) {
																				if((var140 == traceTempVariable$currentState$52_1)) {
																					int traceTempVariable$currentState$56_1 = distributionTempVariable$var84$51;
																					if((sample$var53 == sample$var207)) {
																						if((timeStep$var76 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == traceTempVariable$currentState$56_1)) {
																													{
																														double var254 = current_metric_mean[server][traceTempVariable$currentState$56_1];
																														double var256 = current_metric_var[server][traceTempVariable$currentState$56_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample90Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value50);
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
																							if(!((index$sample$57 == sample$var53) && (index$timeStep$58 == timeStep$var76))) {
																								for(int index$sample90$59 = 0; index$sample90$59 < noStates; index$sample90$59 += 1) {
																									int distributionTempVariable$var84$61 = index$sample90$59;
																									double cv$probabilitySample90Value60 = (cv$probabilitySample90Value50 * distribution$sample90[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample90$59]);
																									int traceTempVariable$currentState$62_1 = distributionTempVariable$var84$61;
																									if((index$sample$57 == sample$var207)) {
																										if((index$timeStep$58 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$62_1)) {
																																	{
																																		double var254 = current_metric_mean[server][traceTempVariable$currentState$62_1];
																																		double var256 = current_metric_var[server][traceTempVariable$currentState$62_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample90Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value60);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample90) {
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
											if((sample$var53 == sample$var207)) {
												if((timeStep$var76 == timeStep$var239)) {
													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
														for(int var130 = 0; var130 < noServers; var130 += 1) {
															for(int var140 = 0; var140 < noStates; var140 += 1) {
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	if((var130 == server)) {
																		if((var140 == st[sample$var207][timeStep$var239])) {
																			if(fixedFlag$sample71) {
																				for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																					if((index$sample$75_1 == sample$var207)) {
																						if((0 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == st[sample$var207][timeStep$var239])) {
																													{
																														double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																														double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
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
																						for(int index$sample71$77 = 0; index$sample71$77 < noStates; index$sample71$77 += 1) {
																							int distributionTempVariable$var65$79 = index$sample71$77;
																							double cv$probabilitySample71Value78 = (1.0 * distribution$sample71[((index$sample$76 - 0) / 1)][index$sample71$77]);
																							int traceTempVariable$currentState$80_1 = distributionTempVariable$var65$79;
																							if((index$sample$76 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == traceTempVariable$currentState$80_1)) {
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																double var256 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample71Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value78);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
											if(true) {
												for(int index$sample90$69 = 0; index$sample90$69 < noStates; index$sample90$69 += 1) {
													int distributionTempVariable$var84$71 = index$sample90$69;
													double cv$probabilitySample90Value70 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$69]);
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var84$71;
													if((sample$var53 == sample$var207)) {
														if((timeStep$var76 == timeStep$var239)) {
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																for(int var130 = 0; var130 < noServers; var130 += 1) {
																	for(int var140 = 0; var140 < noStates; var140 += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if((var130 == server)) {
																				if((var140 == traceTempVariable$currentState$72_1)) {
																					if(fixedFlag$sample71) {
																						for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																							if((index$sample$81_1 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == traceTempVariable$currentState$72_1)) {
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																double var256 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample90Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value70);
																															}
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
																								for(int index$sample71$83 = 0; index$sample71$83 < noStates; index$sample71$83 += 1) {
																									int distributionTempVariable$var65$85 = index$sample71$83;
																									double cv$probabilitySample71Value84 = (cv$probabilitySample90Value70 * distribution$sample71[((index$sample$82 - 0) / 1)][index$sample71$83]);
																									int traceTempVariable$currentState$86_1 = distributionTempVariable$var65$85;
																									if((index$sample$82 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$86_1)) {
																																	{
																																		double var254 = current_metric_mean[server][traceTempVariable$currentState$86_1];
																																		double var256 = current_metric_var[server][traceTempVariable$currentState$86_1];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample71Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value84);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
							logProbability$var257[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleAccumulator;
							logProbability$sample275[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample275 = ((((fixedFlag$sample275 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample150) && fixedFlag$sample178);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample275[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var257[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$rvAccumulator;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample71() {
		if(!fixedProbFlag$sample71) {
			if(fixedFlag$sample71) {
				double cv$accumulator = 0.0;
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample$var53;
					{
						int cv$sampleValue = st[sample$var53][0];
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
					logProbability$var64[((sample$var53 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample71[((sample$var53 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample71)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample71)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample31);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((sample$var53 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var64[((sample$var53 - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample71)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample90() {
		if(!fixedProbFlag$sample90) {
			if(fixedFlag$sample90) {
				double cv$accumulator = 0.0;
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var76;
						int index$sample$2 = sample$var53;
						{
							int cv$sampleValue = st[sample$var53][timeStep$var76];
							if(fixedFlag$sample71) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample$var53)) {
										if((0 == (timeStep$var76 - 1))) {
											for(int var40 = 0; var40 < noStates; var40 += 1) {
												if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
													{
														double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										for(int index$sample71$6 = 0; index$sample71$6 < noStates; index$sample71$6 += 1) {
											int distributionTempVariable$var65$8 = index$sample71$6;
											double cv$probabilitySample71Value7 = (1.0 * distribution$sample71[((index$sample$5 - 0) / 1)][index$sample71$6]);
											int traceTempVariable$var81$9_1 = distributionTempVariable$var65$8;
											if((index$sample$5 == sample$var53)) {
												if((0 == (timeStep$var76 - 1))) {
													for(int var40 = 0; var40 < noStates; var40 += 1) {
														if((var40 == traceTempVariable$var81$9_1)) {
															{
																double[] var82 = m[traceTempVariable$var81$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample71Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if((index$sample$2 == sample$var53)) {
								if((index$timeStep$1 == (timeStep$var76 - 1))) {
									for(int var40 = 0; var40 < noStates; var40 += 1) {
										if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
											{
												double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample90) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var53)) {
											if((index$timeStep$13_2 == (timeStep$var76 - 1))) {
												for(int var40 = 0; var40 < noStates; var40 += 1) {
													if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
														{
															double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											for(int index$sample90$16 = 0; index$sample90$16 < noStates; index$sample90$16 += 1) {
												int distributionTempVariable$var84$18 = index$sample90$16;
												double cv$probabilitySample90Value17 = (1.0 * distribution$sample90[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample90$16]);
												int traceTempVariable$var81$19_1 = distributionTempVariable$var84$18;
												if((index$sample$14 == sample$var53)) {
													if((index$timeStep$15 == (timeStep$var76 - 1))) {
														for(int var40 = 0; var40 < noStates; var40 += 1) {
															if((var40 == traceTempVariable$var81$19_1)) {
																{
																	double[] var82 = m[traceTempVariable$var81$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample90Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample90Value17);
																}
															}
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
						logProbability$var83[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample90)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample90)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample90 = ((fixedFlag$sample90 && fixedFlag$sample44) && fixedFlag$sample71);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var83[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample90)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample150() {
		if(!fixedProbFlag$sample150) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				for(int var140 = 0; var140 < noStates; var140 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_mean[var130][var140];
						{
							{
								double var117 = 0.0;
								double var118 = (double)max_metric;
								double cv$weightedProbability = (Math.log(1.0) + (((var117 <= cv$sampleValue) && (cv$sampleValue <= var118))?(-Math.log((var118 - var117))):Double.NEGATIVE_INFINITY));
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
			logProbability$var119 = cv$sampleAccumulator;
			logProbability$var141 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample150)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample150 = fixedFlag$sample150;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var141;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var119 = cv$rvAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample150)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample178() {
		if(!fixedProbFlag$sample178) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				for(int var167 = 0; var167 < noStates; var167 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_var[var157][var167];
						{
							{
								double var144 = 1.0;
								double var145 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var144, var145));
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
			logProbability$var146 = cv$sampleAccumulator;
			logProbability$var168 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample178)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample178 = fixedFlag$sample178;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var168;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var146 = cv$rvAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample178)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample206() {
		if(!fixedProbFlag$sample206) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				for(int var194 = 0; var194 < noStates; var194 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_valid_bias[var184][var194];
						{
							{
								double var171 = 1.0;
								double var172 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var171, var172));
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
			logProbability$var173 = cv$sampleAccumulator;
			logProbability$var195 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample206)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample206 = fixedFlag$sample206;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var195;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var173 = cv$rvAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample206)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample260() {
		if(!fixedProbFlag$sample260) {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var207][server][timeStep$var239];
							{
								{
									double var243 = current_metric_valid_bias[server][st[sample$var207][timeStep$var239]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var243));
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
						logProbability$var244[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample260[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
						{
							for(int index$timeStep$4_1 = 0; index$timeStep$4_1 < length$metric[sample$var207][0]; index$timeStep$4_1 += 1) {
								if((timeStep$var239 == index$timeStep$4_1)) {
									if(!cv$guard$metric_g) {
										cv$guard$metric_g = true;
										logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample260 = (((fixedFlag$sample260 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample206);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample260[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var244[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
						{
							for(int index$timeStep$6_1 = 0; index$timeStep$6_1 < length$metric[sample$var207][0]; index$timeStep$6_1 += 1) {
								if((timeStep$var239 == index$timeStep$6_1)) {
									if(!cv$guard$metric_g) {
										cv$guard$metric_g = true;
										logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
									}
								}
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

	private final void logProbabilityValue$sample275() {
		if(!fixedProbFlag$sample275) {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								double cv$sampleValue = metric_g[sample$var207][server][timeStep$var239];
								{
									{
										double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
										double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var254) / Math.sqrt(var256))) - (0.5 * Math.log(var256))));
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
							logProbability$var257[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleAccumulator;
							logProbability$sample275[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample275 = ((((fixedFlag$sample275 && fixedFlag$sample71) && fixedFlag$sample90) && fixedFlag$sample150) && fixedFlag$sample178);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(metric_valid_g[sample$var207][server][timeStep$var239]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample275[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var257[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = cv$rvAccumulator;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
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
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var27 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < noStates; var40 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var40];
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
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$var41 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample44 = fixedFlag$sample44;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var41;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var29 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample$var53;
				{
					int cv$sampleValue = st[sample$var53][0];
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
				logProbability$var64[((sample$var53 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample71[((sample$var53 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((sample$var53 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var64[((sample$var53 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample90() {
		if(!fixedProbFlag$sample90) {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var76;
					int index$sample$2 = sample$var53;
					{
						int cv$sampleValue = st[sample$var53][timeStep$var76];
						{
							{
								double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var83[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample90 = ((fixedFlag$sample90 && fixedFlag$sample44) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var83[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample150(int var130, int var140) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = current_metric_mean[var130][var140];
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
					double var141 = cv$proposedValue;
					double[] var131 = current_metric_mean[var130];
					var131[var140] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var117;
				{
					cv$temp$0$var117 = 0.0;
				}
				double cv$temp$1$var118;
				{
					cv$temp$1$var118 = max_metric;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var117 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var118))?(-Math.log((cv$temp$1$var118 - cv$temp$0$var117))):Double.NEGATIVE_INFINITY));
				{
					{
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if(fixedFlag$sample71) {
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											if((sample$var53 == sample$var207)) {
												if((0 == timeStep$var239)) {
													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
														double traceTempVariable$var254$10_1 = cv$currentValue;
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															if((var130 == server)) {
																if((var140 == st[sample$var207][timeStep$var239])) {
																	{
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																					if((index$sample$28_1 == sample$var207)) {
																						if((0 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == st[sample$var207][timeStep$var239])) {
																													{
																														{
																															double cv$temp$2$var254;
																															{
																																double var254 = traceTempVariable$var254$10_1;
																																cv$temp$2$var254 = var254;
																															}
																															double cv$temp$3$var256;
																															{
																																double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																																cv$temp$3$var256 = var256;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))));
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
																				if(fixedFlag$sample90) {
																					for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$30_1][0]; timeStep$var76 += 1) {
																							if((index$sample$30_1 == sample$var207)) {
																								if((timeStep$var76 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == st[sample$var207][timeStep$var239])) {
																															{
																																{
																																	double cv$temp$4$var254;
																																	{
																																		double var254 = traceTempVariable$var254$10_1;
																																		cv$temp$4$var254 = var254;
																																	}
																																	double cv$temp$5$var256;
																																	{
																																		double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																																		cv$temp$5$var256 = var256;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))));
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
																						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$31][0]; timeStep$var76 += 1) {
																							if(true) {
																								for(int index$sample90$33 = 0; index$sample90$33 < noStates; index$sample90$33 += 1) {
																									int distributionTempVariable$var84$35 = index$sample90$33;
																									double cv$probabilitySample90Value34 = (1.0 * distribution$sample90[((index$sample$31 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$33]);
																									int traceTempVariable$currentState$36_1 = distributionTempVariable$var84$35;
																									if((index$sample$31 == sample$var207)) {
																										if((timeStep$var76 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$36_1)) {
																																	{
																																		{
																																			double cv$temp$6$var254;
																																			{
																																				double var254 = traceTempVariable$var254$10_1;
																																				cv$temp$6$var254 = var254;
																																			}
																																			double cv$temp$7$var256;
																																			{
																																				double var256 = current_metric_var[server][traceTempVariable$currentState$36_1];
																																				cv$temp$7$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value34);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											if(true) {
												for(int index$sample71$6 = 0; index$sample71$6 < noStates; index$sample71$6 += 1) {
													int distributionTempVariable$var65$8 = index$sample71$6;
													double cv$probabilitySample71Value7 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$6]);
													int traceTempVariable$currentState$9_1 = distributionTempVariable$var65$8;
													if((sample$var53 == sample$var207)) {
														if((0 == timeStep$var239)) {
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																double traceTempVariable$var254$11_1 = cv$currentValue;
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	if((var130 == server)) {
																		if((var140 == traceTempVariable$currentState$9_1)) {
																			{
																				if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						int traceTempVariable$currentState$39_1 = distributionTempVariable$var65$8;
																						if((sample$var53 == sample$var207)) {
																							if((0 == timeStep$var239)) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									for(int var157 = 0; var157 < noServers; var157 += 1) {
																										for(int var167 = 0; var167 < noStates; var167 += 1) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												if((var157 == server)) {
																													if((var167 == traceTempVariable$currentState$39_1)) {
																														{
																															{
																																double cv$temp$8$var254;
																																{
																																	double var254 = traceTempVariable$var254$11_1;
																																	cv$temp$8$var254 = var254;
																																}
																																double cv$temp$9$var256;
																																{
																																	double var256 = current_metric_var[server][traceTempVariable$currentState$39_1];
																																	cv$temp$9$var256 = var256;
																																}
																																if(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value7);
																															}
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
																							if(!(index$sample$40 == sample$var53)) {
																								for(int index$sample71$41 = 0; index$sample71$41 < noStates; index$sample71$41 += 1) {
																									int distributionTempVariable$var65$43 = index$sample71$41;
																									double cv$probabilitySample71Value42 = (cv$probabilitySample71Value7 * distribution$sample71[((index$sample$40 - 0) / 1)][index$sample71$41]);
																									int traceTempVariable$currentState$44_1 = distributionTempVariable$var65$43;
																									if((index$sample$40 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$44_1)) {
																																	{
																																		{
																																			double cv$temp$10$var254;
																																			{
																																				double var254 = traceTempVariable$var254$11_1;
																																				cv$temp$10$var254 = var254;
																																			}
																																			double cv$temp$11$var256;
																																			{
																																				double var256 = current_metric_var[server][traceTempVariable$currentState$44_1];
																																				cv$temp$11$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value42);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						if(fixedFlag$sample90) {
																							for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																								for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$47_1][0]; timeStep$var76 += 1) {
																									if((index$sample$47_1 == sample$var207)) {
																										if((timeStep$var76 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$9_1)) {
																																	{
																																		{
																																			double cv$temp$12$var254;
																																			{
																																				double var254 = traceTempVariable$var254$11_1;
																																				cv$temp$12$var254 = var254;
																																			}
																																			double cv$temp$13$var256;
																																			{
																																				double var256 = current_metric_var[server][traceTempVariable$currentState$9_1];
																																				cv$temp$13$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value7);
																																		}
																																	}
																																}
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
																								for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$48][0]; timeStep$var76 += 1) {
																									if(true) {
																										for(int index$sample90$50 = 0; index$sample90$50 < noStates; index$sample90$50 += 1) {
																											int distributionTempVariable$var84$52 = index$sample90$50;
																											double cv$probabilitySample90Value51 = (cv$probabilitySample71Value7 * distribution$sample90[((index$sample$48 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$50]);
																											int traceTempVariable$currentState$53_1 = distributionTempVariable$var84$52;
																											if((index$sample$48 == sample$var207)) {
																												if((timeStep$var76 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var157 = 0; var157 < noServers; var157 += 1) {
																															for(int var167 = 0; var167 < noStates; var167 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var157 == server)) {
																																		if((var167 == traceTempVariable$currentState$53_1)) {
																																			{
																																				{
																																					double cv$temp$14$var254;
																																					{
																																						double var254 = traceTempVariable$var254$11_1;
																																						cv$temp$14$var254 = var254;
																																					}
																																					double cv$temp$15$var256;
																																					{
																																						double var256 = current_metric_var[server][traceTempVariable$currentState$53_1];
																																						cv$temp$15$var256 = var256;
																																					}
																																					if(((Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value51);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if(fixedFlag$sample90) {
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
												if((sample$var53 == sample$var207)) {
													if((timeStep$var76 == timeStep$var239)) {
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															double traceTempVariable$var254$22_1 = cv$currentValue;
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																if((var130 == server)) {
																	if((var140 == st[sample$var207][timeStep$var239])) {
																		{
																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample71) {
																						for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																							if((index$sample$56_1 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == st[sample$var207][timeStep$var239])) {
																															{
																																{
																																	double cv$temp$16$var254;
																																	{
																																		double var254 = traceTempVariable$var254$22_1;
																																		cv$temp$16$var254 = var254;
																																	}
																																	double cv$temp$17$var256;
																																	{
																																		double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																																		cv$temp$17$var256 = var256;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))));
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
																								for(int index$sample71$58 = 0; index$sample71$58 < noStates; index$sample71$58 += 1) {
																									int distributionTempVariable$var65$60 = index$sample71$58;
																									double cv$probabilitySample71Value59 = (1.0 * distribution$sample71[((index$sample$57 - 0) / 1)][index$sample71$58]);
																									int traceTempVariable$currentState$61_1 = distributionTempVariable$var65$60;
																									if((index$sample$57 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$61_1)) {
																																	{
																																		{
																																			double cv$temp$18$var254;
																																			{
																																				double var254 = traceTempVariable$var254$22_1;
																																				cv$temp$18$var254 = var254;
																																			}
																																			double cv$temp$19$var256;
																																			{
																																				double var256 = current_metric_var[server][traceTempVariable$currentState$61_1];
																																				cv$temp$19$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value59);
																																		}
																																	}
																																}
																															}
																														}
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
																							if((index$sample$64_1 == sample$var207)) {
																								if((index$timeStep$64_2 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == st[sample$var207][timeStep$var239])) {
																															{
																																{
																																	double cv$temp$20$var254;
																																	{
																																		double var254 = traceTempVariable$var254$22_1;
																																		cv$temp$20$var254 = var254;
																																	}
																																	double cv$temp$21$var256;
																																	{
																																		double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
																																		cv$temp$21$var256 = var256;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))));
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
										}
									} else {
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
												if(true) {
													for(int index$sample90$18 = 0; index$sample90$18 < noStates; index$sample90$18 += 1) {
														int distributionTempVariable$var84$20 = index$sample90$18;
														double cv$probabilitySample90Value19 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$18]);
														int traceTempVariable$currentState$21_1 = distributionTempVariable$var84$20;
														if((sample$var53 == sample$var207)) {
															if((timeStep$var76 == timeStep$var239)) {
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	double traceTempVariable$var254$23_1 = cv$currentValue;
																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																		if((var130 == server)) {
																			if((var140 == traceTempVariable$currentState$21_1)) {
																				{
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample71) {
																								for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																									if((index$sample$66_1 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var157 = 0; var157 < noServers; var157 += 1) {
																													for(int var167 = 0; var167 < noStates; var167 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var157 == server)) {
																																if((var167 == traceTempVariable$currentState$21_1)) {
																																	{
																																		{
																																			double cv$temp$22$var254;
																																			{
																																				double var254 = traceTempVariable$var254$23_1;
																																				cv$temp$22$var254 = var254;
																																			}
																																			double cv$temp$23$var256;
																																			{
																																				double var256 = current_metric_var[server][traceTempVariable$currentState$21_1];
																																				cv$temp$23$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value19);
																																		}
																																	}
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
																										for(int index$sample71$68 = 0; index$sample71$68 < noStates; index$sample71$68 += 1) {
																											int distributionTempVariable$var65$70 = index$sample71$68;
																											double cv$probabilitySample71Value69 = (cv$probabilitySample90Value19 * distribution$sample71[((index$sample$67 - 0) / 1)][index$sample71$68]);
																											int traceTempVariable$currentState$71_1 = distributionTempVariable$var65$70;
																											if((index$sample$67 == sample$var207)) {
																												if((0 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var157 = 0; var157 < noServers; var157 += 1) {
																															for(int var167 = 0; var167 < noStates; var167 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var157 == server)) {
																																		if((var167 == traceTempVariable$currentState$71_1)) {
																																			{
																																				{
																																					double cv$temp$24$var254;
																																					{
																																						double var254 = traceTempVariable$var254$23_1;
																																						cv$temp$24$var254 = var254;
																																					}
																																					double cv$temp$25$var256;
																																					{
																																						double var256 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																						cv$temp$25$var256 = var256;
																																					}
																																					if(((Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value69);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$74_1 = distributionTempVariable$var84$20;
																							if((sample$var53 == sample$var207)) {
																								if((timeStep$var76 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var157 = 0; var157 < noServers; var157 += 1) {
																											for(int var167 = 0; var167 < noStates; var167 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var157 == server)) {
																														if((var167 == traceTempVariable$currentState$74_1)) {
																															{
																																{
																																	double cv$temp$26$var254;
																																	{
																																		double var254 = traceTempVariable$var254$23_1;
																																		cv$temp$26$var254 = var254;
																																	}
																																	double cv$temp$27$var256;
																																	{
																																		double var256 = current_metric_var[server][traceTempVariable$currentState$74_1];
																																		cv$temp$27$var256 = var256;
																																	}
																																	if(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value19);
																																}
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
																									if(!((index$sample$75 == sample$var53) && (index$timeStep$76 == timeStep$var76))) {
																										for(int index$sample90$77 = 0; index$sample90$77 < noStates; index$sample90$77 += 1) {
																											int distributionTempVariable$var84$79 = index$sample90$77;
																											double cv$probabilitySample90Value78 = (cv$probabilitySample90Value19 * distribution$sample90[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample90$77]);
																											int traceTempVariable$currentState$80_1 = distributionTempVariable$var84$79;
																											if((index$sample$75 == sample$var207)) {
																												if((index$timeStep$76 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var157 = 0; var157 < noServers; var157 += 1) {
																															for(int var167 = 0; var167 < noStates; var167 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var157 == server)) {
																																		if((var167 == traceTempVariable$currentState$80_1)) {
																																			{
																																				{
																																					double cv$temp$28$var254;
																																					{
																																						double var254 = traceTempVariable$var254$23_1;
																																						cv$temp$28$var254 = var254;
																																					}
																																					double cv$temp$29$var256;
																																					{
																																						double var256 = current_metric_var[server][traceTempVariable$currentState$80_1];
																																						cv$temp$29$var256 = var256;
																																					}
																																					if(((Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value78);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
			double var141 = cv$originalValue;
			double[] var131 = current_metric_mean[var130];
			var131[var140] = var141;
		}
	}

	private final void sample178(int var157, int var167) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = current_metric_var[var157][var167];
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
					double var168 = cv$proposedValue;
					double[] var158 = current_metric_var[var157];
					var158[var167] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var144;
				{
					cv$temp$0$var144 = 1.0;
				}
				double cv$temp$1$var145;
				{
					cv$temp$1$var145 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var144, cv$temp$1$var145));
				{
					{
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if(fixedFlag$sample71) {
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											if((sample$var53 == sample$var207)) {
												if((0 == timeStep$var239)) {
													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
														double traceTempVariable$var256$10_1 = cv$currentValue;
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															if((var157 == server)) {
																if((var167 == st[sample$var207][timeStep$var239])) {
																	{
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																					if((index$sample$28_1 == sample$var207)) {
																						if((0 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var130 = 0; var130 < noServers; var130 += 1) {
																									for(int var140 = 0; var140 < noStates; var140 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var130 == server)) {
																												if((var140 == st[sample$var207][timeStep$var239])) {
																													{
																														{
																															double cv$temp$2$var254;
																															{
																																double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																																cv$temp$2$var254 = var254;
																															}
																															double cv$temp$3$var256;
																															{
																																double var256 = traceTempVariable$var256$10_1;
																																cv$temp$3$var256 = var256;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$2$var254) / Math.sqrt(cv$temp$3$var256))) - (0.5 * Math.log(cv$temp$3$var256)))));
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
																				if(fixedFlag$sample90) {
																					for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$30_1][0]; timeStep$var76 += 1) {
																							if((index$sample$30_1 == sample$var207)) {
																								if((timeStep$var76 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == st[sample$var207][timeStep$var239])) {
																															{
																																{
																																	double cv$temp$4$var254;
																																	{
																																		double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																																		cv$temp$4$var254 = var254;
																																	}
																																	double cv$temp$5$var256;
																																	{
																																		double var256 = traceTempVariable$var256$10_1;
																																		cv$temp$5$var256 = var256;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$4$var254) / Math.sqrt(cv$temp$5$var256))) - (0.5 * Math.log(cv$temp$5$var256)))));
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
																						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$31][0]; timeStep$var76 += 1) {
																							if(true) {
																								for(int index$sample90$33 = 0; index$sample90$33 < noStates; index$sample90$33 += 1) {
																									int distributionTempVariable$var84$35 = index$sample90$33;
																									double cv$probabilitySample90Value34 = (1.0 * distribution$sample90[((index$sample$31 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$33]);
																									int traceTempVariable$currentState$36_1 = distributionTempVariable$var84$35;
																									if((index$sample$31 == sample$var207)) {
																										if((timeStep$var76 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$36_1)) {
																																	{
																																		{
																																			double cv$temp$6$var254;
																																			{
																																				double var254 = current_metric_mean[server][traceTempVariable$currentState$36_1];
																																				cv$temp$6$var254 = var254;
																																			}
																																			double cv$temp$7$var256;
																																			{
																																				double var256 = traceTempVariable$var256$10_1;
																																				cv$temp$7$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$6$var254) / Math.sqrt(cv$temp$7$var256))) - (0.5 * Math.log(cv$temp$7$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value34);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											if(true) {
												for(int index$sample71$6 = 0; index$sample71$6 < noStates; index$sample71$6 += 1) {
													int distributionTempVariable$var65$8 = index$sample71$6;
													double cv$probabilitySample71Value7 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$6]);
													int traceTempVariable$currentState$9_1 = distributionTempVariable$var65$8;
													if((sample$var53 == sample$var207)) {
														if((0 == timeStep$var239)) {
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																double traceTempVariable$var256$11_1 = cv$currentValue;
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	if((var157 == server)) {
																		if((var167 == traceTempVariable$currentState$9_1)) {
																			{
																				if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						int traceTempVariable$currentState$39_1 = distributionTempVariable$var65$8;
																						if((sample$var53 == sample$var207)) {
																							if((0 == timeStep$var239)) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									for(int var130 = 0; var130 < noServers; var130 += 1) {
																										for(int var140 = 0; var140 < noStates; var140 += 1) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												if((var130 == server)) {
																													if((var140 == traceTempVariable$currentState$39_1)) {
																														{
																															{
																																double cv$temp$8$var254;
																																{
																																	double var254 = current_metric_mean[server][traceTempVariable$currentState$39_1];
																																	cv$temp$8$var254 = var254;
																																}
																																double cv$temp$9$var256;
																																{
																																	double var256 = traceTempVariable$var256$11_1;
																																	cv$temp$9$var256 = var256;
																																}
																																if(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value7);
																															}
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
																							if(!(index$sample$40 == sample$var53)) {
																								for(int index$sample71$41 = 0; index$sample71$41 < noStates; index$sample71$41 += 1) {
																									int distributionTempVariable$var65$43 = index$sample71$41;
																									double cv$probabilitySample71Value42 = (cv$probabilitySample71Value7 * distribution$sample71[((index$sample$40 - 0) / 1)][index$sample71$41]);
																									int traceTempVariable$currentState$44_1 = distributionTempVariable$var65$43;
																									if((index$sample$40 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$44_1)) {
																																	{
																																		{
																																			double cv$temp$10$var254;
																																			{
																																				double var254 = current_metric_mean[server][traceTempVariable$currentState$44_1];
																																				cv$temp$10$var254 = var254;
																																			}
																																			double cv$temp$11$var256;
																																			{
																																				double var256 = traceTempVariable$var256$11_1;
																																				cv$temp$11$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value42);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						if(fixedFlag$sample90) {
																							for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																								for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$47_1][0]; timeStep$var76 += 1) {
																									if((index$sample$47_1 == sample$var207)) {
																										if((timeStep$var76 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$9_1)) {
																																	{
																																		{
																																			double cv$temp$12$var254;
																																			{
																																				double var254 = current_metric_mean[server][traceTempVariable$currentState$9_1];
																																				cv$temp$12$var254 = var254;
																																			}
																																			double cv$temp$13$var256;
																																			{
																																				double var256 = traceTempVariable$var256$11_1;
																																				cv$temp$13$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value7);
																																		}
																																	}
																																}
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
																								for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$48][0]; timeStep$var76 += 1) {
																									if(true) {
																										for(int index$sample90$50 = 0; index$sample90$50 < noStates; index$sample90$50 += 1) {
																											int distributionTempVariable$var84$52 = index$sample90$50;
																											double cv$probabilitySample90Value51 = (cv$probabilitySample71Value7 * distribution$sample90[((index$sample$48 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$50]);
																											int traceTempVariable$currentState$53_1 = distributionTempVariable$var84$52;
																											if((index$sample$48 == sample$var207)) {
																												if((timeStep$var76 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var130 = 0; var130 < noServers; var130 += 1) {
																															for(int var140 = 0; var140 < noStates; var140 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var130 == server)) {
																																		if((var140 == traceTempVariable$currentState$53_1)) {
																																			{
																																				{
																																					double cv$temp$14$var254;
																																					{
																																						double var254 = current_metric_mean[server][traceTempVariable$currentState$53_1];
																																						cv$temp$14$var254 = var254;
																																					}
																																					double cv$temp$15$var256;
																																					{
																																						double var256 = traceTempVariable$var256$11_1;
																																						cv$temp$15$var256 = var256;
																																					}
																																					if(((Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value51);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if(fixedFlag$sample90) {
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
												if((sample$var53 == sample$var207)) {
													if((timeStep$var76 == timeStep$var239)) {
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															double traceTempVariable$var256$22_1 = cv$currentValue;
															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																if((var157 == server)) {
																	if((var167 == st[sample$var207][timeStep$var239])) {
																		{
																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample71) {
																						for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																							if((index$sample$56_1 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == st[sample$var207][timeStep$var239])) {
																															{
																																{
																																	double cv$temp$16$var254;
																																	{
																																		double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																																		cv$temp$16$var254 = var254;
																																	}
																																	double cv$temp$17$var256;
																																	{
																																		double var256 = traceTempVariable$var256$22_1;
																																		cv$temp$17$var256 = var256;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))));
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
																								for(int index$sample71$58 = 0; index$sample71$58 < noStates; index$sample71$58 += 1) {
																									int distributionTempVariable$var65$60 = index$sample71$58;
																									double cv$probabilitySample71Value59 = (1.0 * distribution$sample71[((index$sample$57 - 0) / 1)][index$sample71$58]);
																									int traceTempVariable$currentState$61_1 = distributionTempVariable$var65$60;
																									if((index$sample$57 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$61_1)) {
																																	{
																																		{
																																			double cv$temp$18$var254;
																																			{
																																				double var254 = current_metric_mean[server][traceTempVariable$currentState$61_1];
																																				cv$temp$18$var254 = var254;
																																			}
																																			double cv$temp$19$var256;
																																			{
																																				double var256 = traceTempVariable$var256$22_1;
																																				cv$temp$19$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value59);
																																		}
																																	}
																																}
																															}
																														}
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
																							if((index$sample$64_1 == sample$var207)) {
																								if((index$timeStep$64_2 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == st[sample$var207][timeStep$var239])) {
																															{
																																{
																																	double cv$temp$20$var254;
																																	{
																																		double var254 = current_metric_mean[server][st[sample$var207][timeStep$var239]];
																																		cv$temp$20$var254 = var254;
																																	}
																																	double cv$temp$21$var256;
																																	{
																																		double var256 = traceTempVariable$var256$22_1;
																																		cv$temp$21$var256 = var256;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))));
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
										}
									} else {
										for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
											for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
												if(true) {
													for(int index$sample90$18 = 0; index$sample90$18 < noStates; index$sample90$18 += 1) {
														int distributionTempVariable$var84$20 = index$sample90$18;
														double cv$probabilitySample90Value19 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$18]);
														int traceTempVariable$currentState$21_1 = distributionTempVariable$var84$20;
														if((sample$var53 == sample$var207)) {
															if((timeStep$var76 == timeStep$var239)) {
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	double traceTempVariable$var256$23_1 = cv$currentValue;
																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																		if((var157 == server)) {
																			if((var167 == traceTempVariable$currentState$21_1)) {
																				{
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample71) {
																								for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																									if((index$sample$66_1 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$21_1)) {
																																	{
																																		{
																																			double cv$temp$22$var254;
																																			{
																																				double var254 = current_metric_mean[server][traceTempVariable$currentState$21_1];
																																				cv$temp$22$var254 = var254;
																																			}
																																			double cv$temp$23$var256;
																																			{
																																				double var256 = traceTempVariable$var256$23_1;
																																				cv$temp$23$var256 = var256;
																																			}
																																			if(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value19);
																																		}
																																	}
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
																										for(int index$sample71$68 = 0; index$sample71$68 < noStates; index$sample71$68 += 1) {
																											int distributionTempVariable$var65$70 = index$sample71$68;
																											double cv$probabilitySample71Value69 = (cv$probabilitySample90Value19 * distribution$sample71[((index$sample$67 - 0) / 1)][index$sample71$68]);
																											int traceTempVariable$currentState$71_1 = distributionTempVariable$var65$70;
																											if((index$sample$67 == sample$var207)) {
																												if((0 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var130 = 0; var130 < noServers; var130 += 1) {
																															for(int var140 = 0; var140 < noStates; var140 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var130 == server)) {
																																		if((var140 == traceTempVariable$currentState$71_1)) {
																																			{
																																				{
																																					double cv$temp$24$var254;
																																					{
																																						double var254 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																						cv$temp$24$var254 = var254;
																																					}
																																					double cv$temp$25$var256;
																																					{
																																						double var256 = traceTempVariable$var256$23_1;
																																						cv$temp$25$var256 = var256;
																																					}
																																					if(((Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value69);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$74_1 = distributionTempVariable$var84$20;
																							if((sample$var53 == sample$var207)) {
																								if((timeStep$var76 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$74_1)) {
																															{
																																{
																																	double cv$temp$26$var254;
																																	{
																																		double var254 = current_metric_mean[server][traceTempVariable$currentState$74_1];
																																		cv$temp$26$var254 = var254;
																																	}
																																	double cv$temp$27$var256;
																																	{
																																		double var256 = traceTempVariable$var256$23_1;
																																		cv$temp$27$var256 = var256;
																																	}
																																	if(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value19);
																																}
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
																									if(!((index$sample$75 == sample$var53) && (index$timeStep$76 == timeStep$var76))) {
																										for(int index$sample90$77 = 0; index$sample90$77 < noStates; index$sample90$77 += 1) {
																											int distributionTempVariable$var84$79 = index$sample90$77;
																											double cv$probabilitySample90Value78 = (cv$probabilitySample90Value19 * distribution$sample90[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample90$77]);
																											int traceTempVariable$currentState$80_1 = distributionTempVariable$var84$79;
																											if((index$sample$75 == sample$var207)) {
																												if((index$timeStep$76 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var130 = 0; var130 < noServers; var130 += 1) {
																															for(int var140 = 0; var140 < noStates; var140 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var130 == server)) {
																																		if((var140 == traceTempVariable$currentState$80_1)) {
																																			{
																																				{
																																					double cv$temp$28$var254;
																																					{
																																						double var254 = current_metric_mean[server][traceTempVariable$currentState$80_1];
																																						cv$temp$28$var254 = var254;
																																					}
																																					double cv$temp$29$var256;
																																					{
																																						double var256 = traceTempVariable$var256$23_1;
																																						cv$temp$29$var256 = var256;
																																					}
																																					if(((Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value78);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
			double var168 = cv$originalValue;
			double[] var158 = current_metric_var[var157];
			var158[var167] = var168;
		}
	}

	private final void sample206(int var184, int var194) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
								if(fixedFlag$sample71) {
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										if((sample$var53 == sample$var207)) {
											if((0 == timeStep$var239)) {
												if((var184 == server)) {
													if((var194 == st[sample$var207][timeStep$var239])) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + 1.0);
																			if(metric_valid_g[sample$var207][server][timeStep$var239])
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
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										if(true) {
											for(int index$sample71$6 = 0; index$sample71$6 < noStates; index$sample71$6 += 1) {
												int distributionTempVariable$var65$8 = index$sample71$6;
												double cv$probabilitySample71Value7 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$6]);
												int traceTempVariable$currentState$9_1 = distributionTempVariable$var65$8;
												if((sample$var53 == sample$var207)) {
													if((0 == timeStep$var239)) {
														if((var184 == server)) {
															if((var194 == traceTempVariable$currentState$9_1)) {
																{
																	{
																		{
																			{
																				{
																					cv$count = (cv$count + cv$probabilitySample71Value7);
																					if(metric_valid_g[sample$var207][server][timeStep$var239])
																						cv$sum = (cv$sum + cv$probabilitySample71Value7);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
								if(fixedFlag$sample90) {
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
											if((sample$var53 == sample$var207)) {
												if((timeStep$var76 == timeStep$var239)) {
													if((var184 == server)) {
														if((var194 == st[sample$var207][timeStep$var239])) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + 1.0);
																				if(metric_valid_g[sample$var207][server][timeStep$var239])
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
									for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
										for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
											if(true) {
												for(int index$sample90$18 = 0; index$sample90$18 < noStates; index$sample90$18 += 1) {
													int distributionTempVariable$var84$20 = index$sample90$18;
													double cv$probabilitySample90Value19 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$18]);
													int traceTempVariable$currentState$21_1 = distributionTempVariable$var84$20;
													if((sample$var53 == sample$var207)) {
														if((timeStep$var76 == timeStep$var239)) {
															if((var184 == server)) {
																if((var194 == traceTempVariable$currentState$21_1)) {
																	{
																		{
																			{
																				{
																					{
																						cv$count = (cv$count + cv$probabilitySample90Value19);
																						if(metric_valid_g[sample$var207][server][timeStep$var239])
																							cv$sum = (cv$sum + cv$probabilitySample90Value19);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var195 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		double[] var185 = current_metric_valid_bias[var184];
		var185[var194] = var195;
	}

	private final void sample260(int sample$var207, int server, int timeStep$var239) {
		boolean cv$varObserved = false;
		{
			cv$varObserved = true;
		}
		if(!cv$varObserved) {
			int cv$noStates = 0;
			if(fixedFlag$sample71) {
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					if((sample$var53 == sample$var207)) {
						if((0 == timeStep$var239)) {
							for(int var184 = 0; var184 < noServers; var184 += 1) {
								for(int var194 = 0; var194 < noStates; var194 += 1) {
									if((var184 == server)) {
										if((var194 == st[sample$var207][timeStep$var239]))
											cv$noStates = Math.max(cv$noStates, 2);
									}
								}
							}
						}
					}
				}
			} else {
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					if(true) {
						for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
							int distributionTempVariable$var65$6 = index$sample71$4;
							double cv$probabilitySample71Value5 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$4]);
							int traceTempVariable$currentState$7_1 = distributionTempVariable$var65$6;
							if((sample$var53 == sample$var207)) {
								if((0 == timeStep$var239)) {
									for(int var184 = 0; var184 < noServers; var184 += 1) {
										for(int var194 = 0; var194 < noStates; var194 += 1) {
											if((var184 == server)) {
												if((var194 == traceTempVariable$currentState$7_1))
													cv$noStates = Math.max(cv$noStates, 2);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample90) {
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
						if((sample$var53 == sample$var207)) {
							if((timeStep$var76 == timeStep$var239)) {
								for(int var184 = 0; var184 < noServers; var184 += 1) {
									for(int var194 = 0; var194 < noStates; var194 += 1) {
										if((var184 == server)) {
											if((var194 == st[sample$var207][timeStep$var239]))
												cv$noStates = Math.max(cv$noStates, 2);
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
						if(true) {
							for(int index$sample90$13 = 0; index$sample90$13 < noStates; index$sample90$13 += 1) {
								int distributionTempVariable$var84$15 = index$sample90$13;
								double cv$probabilitySample90Value14 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$13]);
								int traceTempVariable$currentState$16_1 = distributionTempVariable$var84$15;
								if((sample$var53 == sample$var207)) {
									if((timeStep$var76 == timeStep$var239)) {
										for(int var184 = 0; var184 < noServers; var184 += 1) {
											for(int var194 = 0; var194 < noStates; var194 += 1) {
												if((var184 == server)) {
													if((var194 == traceTempVariable$currentState$16_1))
														cv$noStates = Math.max(cv$noStates, 2);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var245$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var245 = cv$currentValue;
				boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
				metric_valid_inner[timeStep$var239] = cv$currentValue;
				if(fixedFlag$sample71) {
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						if((sample$var53 == sample$var207)) {
							if((0 == timeStep$var239)) {
								for(int var184 = 0; var184 < noServers; var184 += 1) {
									for(int var194 = 0; var194 < noStates; var194 += 1) {
										if((var184 == server)) {
											if((var194 == st[sample$var207][timeStep$var239])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												double cv$temp$0$var243;
												{
													double var243 = current_metric_valid_bias[server][st[sample$var207][timeStep$var239]];
													cv$temp$0$var243 = var243;
												}
												double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$0$var243));
												{
													{
														for(int index$timeStep$36_1 = 0; index$timeStep$36_1 < length$metric[sample$var207][0]; index$timeStep$36_1 += 1) {
															if((timeStep$var239 == index$timeStep$36_1)) {
																if(metric_valid_g[sample$var207][server][index$timeStep$36_1]) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
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
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						if(true) {
							for(int index$sample71$21 = 0; index$sample71$21 < noStates; index$sample71$21 += 1) {
								int distributionTempVariable$var65$23 = index$sample71$21;
								double cv$probabilitySample71Value22 = (1.0 * distribution$sample71[((sample$var53 - 0) / 1)][index$sample71$21]);
								int traceTempVariable$currentState$24_1 = distributionTempVariable$var65$23;
								if((sample$var53 == sample$var207)) {
									if((0 == timeStep$var239)) {
										for(int var184 = 0; var184 < noServers; var184 += 1) {
											for(int var194 = 0; var194 < noStates; var194 += 1) {
												if((var184 == server)) {
													if((var194 == traceTempVariable$currentState$24_1)) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value22);
														double cv$temp$1$var243;
														{
															double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$24_1];
															cv$temp$1$var243 = var243;
														}
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value22) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$1$var243));
														{
															{
																for(int index$timeStep$37_1 = 0; index$timeStep$37_1 < length$metric[sample$var207][0]; index$timeStep$37_1 += 1) {
																	if((timeStep$var239 == index$timeStep$37_1)) {
																		if(metric_valid_g[sample$var207][server][index$timeStep$37_1]) {
																			{
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
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
				if(fixedFlag$sample90) {
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
							if((sample$var53 == sample$var207)) {
								if((timeStep$var76 == timeStep$var239)) {
									for(int var184 = 0; var184 < noServers; var184 += 1) {
										for(int var194 = 0; var194 < noStates; var194 += 1) {
											if((var184 == server)) {
												if((var194 == st[sample$var207][timeStep$var239])) {
													cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
													double cv$temp$2$var243;
													{
														double var243 = current_metric_valid_bias[server][st[sample$var207][timeStep$var239]];
														cv$temp$2$var243 = var243;
													}
													double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$2$var243));
													{
														{
															for(int index$timeStep$38_1 = 0; index$timeStep$38_1 < length$metric[sample$var207][0]; index$timeStep$38_1 += 1) {
																if((timeStep$var239 == index$timeStep$38_1)) {
																	if(metric_valid_g[sample$var207][server][index$timeStep$38_1]) {
																		{
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
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
				} else {
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
							if(true) {
								for(int index$sample90$30 = 0; index$sample90$30 < noStates; index$sample90$30 += 1) {
									int distributionTempVariable$var84$32 = index$sample90$30;
									double cv$probabilitySample90Value31 = (1.0 * distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$30]);
									int traceTempVariable$currentState$33_1 = distributionTempVariable$var84$32;
									if((sample$var53 == sample$var207)) {
										if((timeStep$var76 == timeStep$var239)) {
											for(int var184 = 0; var184 < noServers; var184 += 1) {
												for(int var194 = 0; var194 < noStates; var194 += 1) {
													if((var184 == server)) {
														if((var194 == traceTempVariable$currentState$33_1)) {
															cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample90Value31);
															double cv$temp$3$var243;
															{
																double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$33_1];
																cv$temp$3$var243 = var243;
															}
															double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample90Value31) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$3$var243));
															{
																{
																	for(int index$timeStep$39_1 = 0; index$timeStep$39_1 < length$metric[sample$var207][0]; index$timeStep$39_1 += 1) {
																		if((timeStep$var239 == index$timeStep$39_1)) {
																			if(metric_valid_g[sample$var207][server][index$timeStep$39_1]) {
																				{
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
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
			boolean var245 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal) == 1);
			boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
			metric_valid_inner[timeStep$var239] = var245;
		}
	}

	private final void sample31() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var28$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						if(fixedFlag$sample71) {
							{
								int index$sample$3 = sample$var53;
								{
									{
										{
											{
												cv$countLocal[st[sample$var53][0]] = (cv$countLocal[st[sample$var53][0]] + 1.0);
											}
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
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					if(!fixedFlag$sample71) {
						{
							int index$sample$7 = sample$var53;
							{
								{
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((sample$var53 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample44(int var40) {
		double[] cv$targetLocal = m[var40];
		double[] cv$countLocal = cv$var41$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
							if(fixedFlag$sample71) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample$var53)) {
										if((0 == (timeStep$var76 - 1))) {
											if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
												if(fixedFlag$sample90) {
													{
														int index$timeStep$23 = timeStep$var76;
														int index$sample$24 = sample$var53;
														{
															{
																{
																	{
																		cv$countLocal[st[sample$var53][timeStep$var76]] = (cv$countLocal[st[sample$var53][timeStep$var76]] + 1.0);
																	}
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
										for(int index$sample71$5 = 0; index$sample71$5 < noStates; index$sample71$5 += 1) {
											int distributionTempVariable$var65$7 = index$sample71$5;
											double cv$probabilitySample71Value6 = (1.0 * distribution$sample71[((index$sample$4 - 0) / 1)][index$sample71$5]);
											int traceTempVariable$var81$8_1 = distributionTempVariable$var65$7;
											if((index$sample$4 == sample$var53)) {
												if((0 == (timeStep$var76 - 1))) {
													if((var40 == traceTempVariable$var81$8_1)) {
														if(fixedFlag$sample90) {
															{
																int index$timeStep$26 = timeStep$var76;
																int index$sample$27 = sample$var53;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample$var53][timeStep$var76]] = (cv$countLocal[st[sample$var53][timeStep$var76]] + cv$probabilitySample71Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
						for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
							if(fixedFlag$sample90) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var53)) {
											if((index$timeStep$13_2 == (timeStep$var76 - 1))) {
												if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
													if(fixedFlag$sample90) {
														{
															int index$timeStep$29 = timeStep$var76;
															int index$sample$30 = sample$var53;
															{
																{
																	{
																		{
																			cv$countLocal[st[sample$var53][timeStep$var76]] = (cv$countLocal[st[sample$var53][timeStep$var76]] + 1.0);
																		}
																	}
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
											for(int index$sample90$16 = 0; index$sample90$16 < noStates; index$sample90$16 += 1) {
												int distributionTempVariable$var84$18 = index$sample90$16;
												double cv$probabilitySample90Value17 = (1.0 * distribution$sample90[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample90$16]);
												int traceTempVariable$var81$19_1 = distributionTempVariable$var84$18;
												if((index$sample$14 == sample$var53)) {
													if((index$timeStep$15 == (timeStep$var76 - 1))) {
														if((var40 == traceTempVariable$var81$19_1)) {
															if(fixedFlag$sample90) {
																{
																	int index$timeStep$32 = timeStep$var76;
																	int index$sample$33 = sample$var53;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample$var53][timeStep$var76]] = (cv$countLocal[st[sample$var53][timeStep$var76]] + cv$probabilitySample90Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
						if(fixedFlag$sample71) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample$var53)) {
									if((0 == (timeStep$var76 - 1))) {
										if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
											if(!fixedFlag$sample90) {
												{
													int index$timeStep$60 = timeStep$var76;
													int index$sample$61 = sample$var53;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample71$42 = 0; index$sample71$42 < noStates; index$sample71$42 += 1) {
										int distributionTempVariable$var65$44 = index$sample71$42;
										double cv$probabilitySample71Value43 = (1.0 * distribution$sample71[((index$sample$41 - 0) / 1)][index$sample71$42]);
										int traceTempVariable$var81$45_1 = distributionTempVariable$var65$44;
										if((index$sample$41 == sample$var53)) {
											if((0 == (timeStep$var76 - 1))) {
												if((var40 == traceTempVariable$var81$45_1)) {
													if(!fixedFlag$sample90) {
														{
															int index$timeStep$63 = timeStep$var76;
															int index$sample$64 = sample$var53;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample71Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
						if(fixedFlag$sample90) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample$var53)) {
										if((index$timeStep$50_2 == (timeStep$var76 - 1))) {
											if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
												if(!fixedFlag$sample90) {
													{
														int index$timeStep$66 = timeStep$var76;
														int index$sample$67 = sample$var53;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										for(int index$sample90$53 = 0; index$sample90$53 < noStates; index$sample90$53 += 1) {
											int distributionTempVariable$var84$55 = index$sample90$53;
											double cv$probabilitySample90Value54 = (1.0 * distribution$sample90[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample90$53]);
											int traceTempVariable$var81$56_1 = distributionTempVariable$var84$55;
											if((index$sample$51 == sample$var53)) {
												if((index$timeStep$52 == (timeStep$var76 - 1))) {
													if((var40 == traceTempVariable$var81$56_1)) {
														if(!fixedFlag$sample90) {
															{
																int index$timeStep$69 = timeStep$var76;
																int index$sample$70 = sample$var53;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample90Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample71(int sample$var53) {
		int cv$noStates = 0;
		int index$sample$1 = sample$var53;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var65$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$sample$2 = sample$var53;
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
						int traceTempVariable$var81$3_1 = cv$currentValue;
						for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
							if((sample$var53 == index$sample$3_2)) {
								for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$3_2][0]; timeStep$var76 += 1) {
									if((0 == (timeStep$var76 - 1))) {
										if(fixedFlag$sample90) {
											{
												int index$timeStep$5 = timeStep$var76;
												int index$sample$6 = index$sample$3_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var40 = 0; var40 < noStates; var40 += 1) {
														if((var40 == traceTempVariable$var81$3_1)) {
															{
																{
																	double[] cv$temp$1$var82;
																	{
																		double[] var82 = m[traceTempVariable$var81$3_1];
																		cv$temp$1$var82 = var82;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var76]) && (st[index$sample$3_2][timeStep$var76] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[index$sample$3_2][timeStep$var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var76]) && (st[index$sample$3_2][timeStep$var76] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[index$sample$3_2][timeStep$var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var76]) && (st[index$sample$3_2][timeStep$var76] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[index$sample$3_2][timeStep$var76]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var76]) && (st[index$sample$3_2][timeStep$var76] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[index$sample$3_2][timeStep$var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var76]) && (st[index$sample$3_2][timeStep$var76] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[index$sample$3_2][timeStep$var76]]):Double.NEGATIVE_INFINITY)));
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
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							if((sample$var53 == sample$var207)) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if((0 == timeStep$var239)) {
										for(int server = 0; server < noServers; server += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var184 = 0; var184 < noServers; var184 += 1) {
													for(int var194 = 0; var194 < noStates; var194 += 1) {
														if((var184 == server)) {
															if((var194 == traceTempVariable$currentState$9_1)) {
																{
																	{
																		double cv$temp$2$var243;
																		{
																			double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$9_1];
																			cv$temp$2$var243 = var243;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$2$var243)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$2$var243)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$2$var243));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$2$var243)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$2$var243)));
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
						boolean[][][] guard$sample71gaussian274 = guard$sample71gaussian274$global;
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							if((sample$var53 == sample$var207)) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if((0 == timeStep$var239)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var207][server][timeStep$var239])
												guard$sample71gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							if((sample$var53 == sample$var207)) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if((0 == timeStep$var239)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var207][server][timeStep$var239])
												guard$sample71gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							if((sample$var53 == sample$var207)) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if((0 == timeStep$var239)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
												if(!guard$sample71gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
													guard$sample71gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
													{
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var130 = 0; var130 < noServers; var130 += 1) {
																	for(int var140 = 0; var140 < noStates; var140 += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if((var130 == server)) {
																				if((var140 == traceTempVariable$currentState$15_1)) {
																					int traceTempVariable$currentState$20_1 = cv$currentValue;
																					if((index$sample$2 == sample$var207)) {
																						if((0 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == traceTempVariable$currentState$20_1)) {
																													{
																														{
																															double cv$temp$3$var254;
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$20_1];
																																cv$temp$3$var254 = var254;
																															}
																															double cv$temp$4$var256;
																															{
																																double var256 = current_metric_var[server][traceTempVariable$currentState$20_1];
																																cv$temp$4$var256 = var256;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$3$var254) / Math.sqrt(cv$temp$4$var256))) - (0.5 * Math.log(cv$temp$4$var256)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$3$var254) / Math.sqrt(cv$temp$4$var256))) - (0.5 * Math.log(cv$temp$4$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$3$var254) / Math.sqrt(cv$temp$4$var256))) - (0.5 * Math.log(cv$temp$4$var256))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$3$var254) / Math.sqrt(cv$temp$4$var256))) - (0.5 * Math.log(cv$temp$4$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$3$var254) / Math.sqrt(cv$temp$4$var256))) - (0.5 * Math.log(cv$temp$4$var256)))));
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
																							for(int index$sample71$22 = 0; index$sample71$22 < noStates; index$sample71$22 += 1) {
																								int distributionTempVariable$var65$24 = index$sample71$22;
																								double cv$probabilitySample71Value23 = (1.0 * distribution$sample71[((index$sample$21 - 0) / 1)][index$sample71$22]);
																								int traceTempVariable$currentState$25_1 = distributionTempVariable$var65$24;
																								if((index$sample$21 == sample$var207)) {
																									if((0 == timeStep$var239)) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											for(int var157 = 0; var157 < noServers; var157 += 1) {
																												for(int var167 = 0; var167 < noStates; var167 += 1) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														if((var157 == server)) {
																															if((var167 == traceTempVariable$currentState$25_1)) {
																																{
																																	{
																																		double cv$temp$5$var254;
																																		{
																																			double var254 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																			cv$temp$5$var254 = var254;
																																		}
																																		double cv$temp$6$var256;
																																		{
																																			double var256 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																			cv$temp$6$var256 = var256;
																																		}
																																		if(((Math.log(cv$probabilitySample71Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$5$var254) / Math.sqrt(cv$temp$6$var256))) - (0.5 * Math.log(cv$temp$6$var256)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$5$var254) / Math.sqrt(cv$temp$6$var256))) - (0.5 * Math.log(cv$temp$6$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$5$var254) / Math.sqrt(cv$temp$6$var256))) - (0.5 * Math.log(cv$temp$6$var256))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$5$var254) / Math.sqrt(cv$temp$6$var256))) - (0.5 * Math.log(cv$temp$6$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$5$var254) / Math.sqrt(cv$temp$6$var256))) - (0.5 * Math.log(cv$temp$6$var256)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value23);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var130 = 0; var130 < noServers; var130 += 1) {
																	for(int var140 = 0; var140 < noStates; var140 += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if((var130 == server)) {
																				if((var140 == traceTempVariable$currentState$15_1)) {
																					if(fixedFlag$sample90) {
																						for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$29_1][0]; timeStep$var76 += 1) {
																								if((index$sample$29_1 == sample$var207)) {
																									if((timeStep$var76 == timeStep$var239)) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											for(int var157 = 0; var157 < noServers; var157 += 1) {
																												for(int var167 = 0; var167 < noStates; var167 += 1) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														if((var157 == server)) {
																															if((var167 == traceTempVariable$currentState$15_1)) {
																																{
																																	{
																																		double cv$temp$7$var254;
																																		{
																																			double var254 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																			cv$temp$7$var254 = var254;
																																		}
																																		double cv$temp$8$var256;
																																		{
																																			double var256 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																			cv$temp$8$var256 = var256;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$7$var254) / Math.sqrt(cv$temp$8$var256))) - (0.5 * Math.log(cv$temp$8$var256)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$7$var254) / Math.sqrt(cv$temp$8$var256))) - (0.5 * Math.log(cv$temp$8$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$7$var254) / Math.sqrt(cv$temp$8$var256))) - (0.5 * Math.log(cv$temp$8$var256))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$7$var254) / Math.sqrt(cv$temp$8$var256))) - (0.5 * Math.log(cv$temp$8$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$7$var254) / Math.sqrt(cv$temp$8$var256))) - (0.5 * Math.log(cv$temp$8$var256)))));
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
																							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$30][0]; timeStep$var76 += 1) {
																								if(true) {
																									for(int index$sample90$32 = 0; index$sample90$32 < noStates; index$sample90$32 += 1) {
																										int distributionTempVariable$var84$34 = index$sample90$32;
																										double cv$probabilitySample90Value33 = (1.0 * distribution$sample90[((index$sample$30 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$32]);
																										int traceTempVariable$currentState$35_1 = distributionTempVariable$var84$34;
																										if((index$sample$30 == sample$var207)) {
																											if((timeStep$var76 == timeStep$var239)) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													for(int var157 = 0; var157 < noServers; var157 += 1) {
																														for(int var167 = 0; var167 < noStates; var167 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var157 == server)) {
																																	if((var167 == traceTempVariable$currentState$35_1)) {
																																		{
																																			{
																																				double cv$temp$9$var254;
																																				{
																																					double var254 = current_metric_mean[server][traceTempVariable$currentState$35_1];
																																					cv$temp$9$var254 = var254;
																																				}
																																				double cv$temp$10$var256;
																																				{
																																					double var256 = current_metric_var[server][traceTempVariable$currentState$35_1];
																																					cv$temp$10$var256 = var256;
																																				}
																																				if(((Math.log(cv$probabilitySample90Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$9$var254) / Math.sqrt(cv$temp$10$var256))) - (0.5 * Math.log(cv$temp$10$var256)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$9$var254) / Math.sqrt(cv$temp$10$var256))) - (0.5 * Math.log(cv$temp$10$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$9$var254) / Math.sqrt(cv$temp$10$var256))) - (0.5 * Math.log(cv$temp$10$var256))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$9$var254) / Math.sqrt(cv$temp$10$var256))) - (0.5 * Math.log(cv$temp$10$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$9$var254) / Math.sqrt(cv$temp$10$var256))) - (0.5 * Math.log(cv$temp$10$var256)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value33);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
						int traceTempVariable$currentState$16_1 = cv$currentValue;
						for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
							if((sample$var53 == sample$var207)) {
								for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
									if((0 == timeStep$var239)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
												if(!guard$sample71gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
													guard$sample71gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
													{
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$currentState$38_1 = cv$currentValue;
																if((index$sample$2 == sample$var207)) {
																	if((0 == timeStep$var239)) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			for(int var130 = 0; var130 < noServers; var130 += 1) {
																				for(int var140 = 0; var140 < noStates; var140 += 1) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						if((var130 == server)) {
																							if((var140 == traceTempVariable$currentState$38_1)) {
																								for(int var157 = 0; var157 < noServers; var157 += 1) {
																									for(int var167 = 0; var167 < noStates; var167 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var157 == server)) {
																												if((var167 == traceTempVariable$currentState$38_1)) {
																													{
																														{
																															double cv$temp$11$var254;
																															{
																																double var254 = current_metric_mean[server][traceTempVariable$currentState$38_1];
																																cv$temp$11$var254 = var254;
																															}
																															double cv$temp$12$var256;
																															{
																																double var256 = current_metric_var[server][traceTempVariable$currentState$38_1];
																																cv$temp$12$var256 = var256;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$11$var254) / Math.sqrt(cv$temp$12$var256))) - (0.5 * Math.log(cv$temp$12$var256)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$11$var254) / Math.sqrt(cv$temp$12$var256))) - (0.5 * Math.log(cv$temp$12$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$11$var254) / Math.sqrt(cv$temp$12$var256))) - (0.5 * Math.log(cv$temp$12$var256))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$11$var254) / Math.sqrt(cv$temp$12$var256))) - (0.5 * Math.log(cv$temp$12$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$11$var254) / Math.sqrt(cv$temp$12$var256))) - (0.5 * Math.log(cv$temp$12$var256)))));
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
																for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																	if(!(index$sample$39 == index$sample$2)) {
																		for(int index$sample71$40 = 0; index$sample71$40 < noStates; index$sample71$40 += 1) {
																			int distributionTempVariable$var65$42 = index$sample71$40;
																			double cv$probabilitySample71Value41 = (1.0 * distribution$sample71[((index$sample$39 - 0) / 1)][index$sample71$40]);
																			int traceTempVariable$currentState$43_1 = distributionTempVariable$var65$42;
																			if((index$sample$39 == sample$var207)) {
																				if((0 == timeStep$var239)) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						for(int var130 = 0; var130 < noServers; var130 += 1) {
																							for(int var140 = 0; var140 < noStates; var140 += 1) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									if((var130 == server)) {
																										if((var140 == traceTempVariable$currentState$43_1)) {
																											for(int var157 = 0; var157 < noServers; var157 += 1) {
																												for(int var167 = 0; var167 < noStates; var167 += 1) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														if((var157 == server)) {
																															if((var167 == traceTempVariable$currentState$43_1)) {
																																{
																																	{
																																		double cv$temp$13$var254;
																																		{
																																			double var254 = current_metric_mean[server][traceTempVariable$currentState$43_1];
																																			cv$temp$13$var254 = var254;
																																		}
																																		double cv$temp$14$var256;
																																		{
																																			double var256 = current_metric_var[server][traceTempVariable$currentState$43_1];
																																			cv$temp$14$var256 = var256;
																																		}
																																		if(((Math.log(cv$probabilitySample71Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$13$var254) / Math.sqrt(cv$temp$14$var256))) - (0.5 * Math.log(cv$temp$14$var256)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$13$var254) / Math.sqrt(cv$temp$14$var256))) - (0.5 * Math.log(cv$temp$14$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$13$var254) / Math.sqrt(cv$temp$14$var256))) - (0.5 * Math.log(cv$temp$14$var256))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$13$var254) / Math.sqrt(cv$temp$14$var256))) - (0.5 * Math.log(cv$temp$14$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$13$var254) / Math.sqrt(cv$temp$14$var256))) - (0.5 * Math.log(cv$temp$14$var256)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value41);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(fixedFlag$sample90) {
																	for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																		for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$48_1][0]; timeStep$var76 += 1) {
																			if((index$sample$48_1 == sample$var207)) {
																				if((timeStep$var76 == timeStep$var239)) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						for(int var130 = 0; var130 < noServers; var130 += 1) {
																							for(int var140 = 0; var140 < noStates; var140 += 1) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									if((var130 == server)) {
																										if((var140 == traceTempVariable$currentState$16_1)) {
																											for(int var157 = 0; var157 < noServers; var157 += 1) {
																												for(int var167 = 0; var167 < noStates; var167 += 1) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														if((var157 == server)) {
																															if((var167 == traceTempVariable$currentState$16_1)) {
																																{
																																	{
																																		double cv$temp$15$var254;
																																		{
																																			double var254 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																			cv$temp$15$var254 = var254;
																																		}
																																		double cv$temp$16$var256;
																																		{
																																			double var256 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																			cv$temp$16$var256 = var256;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$15$var254) / Math.sqrt(cv$temp$16$var256))) - (0.5 * Math.log(cv$temp$16$var256)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$15$var254) / Math.sqrt(cv$temp$16$var256))) - (0.5 * Math.log(cv$temp$16$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$15$var254) / Math.sqrt(cv$temp$16$var256))) - (0.5 * Math.log(cv$temp$16$var256))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$15$var254) / Math.sqrt(cv$temp$16$var256))) - (0.5 * Math.log(cv$temp$16$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$15$var254) / Math.sqrt(cv$temp$16$var256))) - (0.5 * Math.log(cv$temp$16$var256)))));
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
																	for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																		for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$49][0]; timeStep$var76 += 1) {
																			if(true) {
																				for(int index$sample90$51 = 0; index$sample90$51 < noStates; index$sample90$51 += 1) {
																					int distributionTempVariable$var84$53 = index$sample90$51;
																					double cv$probabilitySample90Value52 = (1.0 * distribution$sample90[((index$sample$49 - 0) / 1)][((timeStep$var76 - 1) / 1)][index$sample90$51]);
																					int traceTempVariable$currentState$54_1 = distributionTempVariable$var84$53;
																					if((index$sample$49 == sample$var207)) {
																						if((timeStep$var76 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var130 = 0; var130 < noServers; var130 += 1) {
																									for(int var140 = 0; var140 < noStates; var140 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var130 == server)) {
																												if((var140 == traceTempVariable$currentState$54_1)) {
																													for(int var157 = 0; var157 < noServers; var157 += 1) {
																														for(int var167 = 0; var167 < noStates; var167 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var157 == server)) {
																																	if((var167 == traceTempVariable$currentState$54_1)) {
																																		{
																																			{
																																				double cv$temp$17$var254;
																																				{
																																					double var254 = current_metric_mean[server][traceTempVariable$currentState$54_1];
																																					cv$temp$17$var254 = var254;
																																				}
																																				double cv$temp$18$var256;
																																				{
																																					double var256 = current_metric_var[server][traceTempVariable$currentState$54_1];
																																					cv$temp$18$var256 = var256;
																																				}
																																				if(((Math.log(cv$probabilitySample90Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$17$var254) / Math.sqrt(cv$temp$18$var256))) - (0.5 * Math.log(cv$temp$18$var256)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$17$var254) / Math.sqrt(cv$temp$18$var256))) - (0.5 * Math.log(cv$temp$18$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$17$var254) / Math.sqrt(cv$temp$18$var256))) - (0.5 * Math.log(cv$temp$18$var256))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$17$var254) / Math.sqrt(cv$temp$18$var256))) - (0.5 * Math.log(cv$temp$18$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$17$var254) / Math.sqrt(cv$temp$18$var256))) - (0.5 * Math.log(cv$temp$18$var256)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value52);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
					int traceTempVariable$var81$67_1 = cv$currentValue;
					for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
						if((sample$var53 == index$sample$67_2)) {
							for(int timeStep$var76 = 1; timeStep$var76 < length$metric[index$sample$67_2][0]; timeStep$var76 += 1) {
								if((0 == (timeStep$var76 - 1))) {
									if(!fixedFlag$sample90) {
										{
											int index$timeStep$69 = timeStep$var76;
											int index$sample$70 = index$sample$67_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var83;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var40 = 0; var40 < noStates; var40 += 1) {
												if((var40 == traceTempVariable$var81$67_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var82;
														{
															double[] var82 = m[traceTempVariable$var81$67_1];
															cv$temp$19$var82 = var82;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var82);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample90[((index$sample$67_2 - 0) / 1)][((timeStep$var76 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample71[((sample$var53 - 0) / 1)];
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

	private final void sample90(int sample$var53, int timeStep$var76) {
		int cv$noStates = 0;
		int index$timeStep$1 = timeStep$var76;
		int index$sample$2 = sample$var53;
		if(fixedFlag$sample71) {
			for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
				if((index$sample$3_1 == sample$var53)) {
					if((0 == (timeStep$var76 - 1))) {
						for(int var40 = 0; var40 < noStates; var40 += 1) {
							if((var40 == st[sample$var53][(timeStep$var76 - 1)]))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
				if(true) {
					for(int index$sample71$5 = 0; index$sample71$5 < noStates; index$sample71$5 += 1) {
						int distributionTempVariable$var65$7 = index$sample71$5;
						double cv$probabilitySample71Value6 = (1.0 * distribution$sample71[((index$sample$4 - 0) / 1)][index$sample71$5]);
						int traceTempVariable$var81$8_1 = distributionTempVariable$var65$7;
						if((index$sample$4 == sample$var53)) {
							if((0 == (timeStep$var76 - 1))) {
								for(int var40 = 0; var40 < noStates; var40 += 1) {
									if((var40 == traceTempVariable$var81$8_1))
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		if((index$sample$2 == sample$var53)) {
			if((index$timeStep$1 == (timeStep$var76 - 1))) {
				for(int var40 = 0; var40 < noStates; var40 += 1) {
					if((var40 == st[sample$var53][(timeStep$var76 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample90) {
			for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
				for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1][0]; index$timeStep$12_2 += 1) {
					if((index$sample$12_1 == sample$var53)) {
						if((index$timeStep$12_2 == (timeStep$var76 - 1))) {
							for(int var40 = 0; var40 < noStates; var40 += 1) {
								if((var40 == st[sample$var53][(timeStep$var76 - 1)]))
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
						for(int index$sample90$15 = 0; index$sample90$15 < noStates; index$sample90$15 += 1) {
							int distributionTempVariable$var84$17 = index$sample90$15;
							double cv$probabilitySample90Value16 = (1.0 * distribution$sample90[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample90$15]);
							int traceTempVariable$var81$18_1 = distributionTempVariable$var84$17;
							if((index$sample$13 == sample$var53)) {
								if((index$timeStep$14 == (timeStep$var76 - 1))) {
									for(int var40 = 0; var40 < noStates; var40 += 1) {
										if((var40 == traceTempVariable$var81$18_1))
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var84$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$timeStep$22 = timeStep$var76;
			int index$sample$23 = sample$var53;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample71) {
				for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
					if((index$sample$24_1 == sample$var53)) {
						if((0 == (timeStep$var76 - 1))) {
							for(int var40 = 0; var40 < noStates; var40 += 1) {
								if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var82;
									{
										double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
										cv$temp$0$var82 = var82;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var82.length))?Math.log(cv$temp$0$var82[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var81$41_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$45_1 = cv$currentValue;
											for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
												if((sample$var53 == sample$var207)) {
													for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
														if((timeStep$var76 == timeStep$var239)) {
															for(int server = 0; server < noServers; server += 1) {
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var184 = 0; var184 < noServers; var184 += 1) {
																		for(int var194 = 0; var194 < noStates; var194 += 1) {
																			if((var184 == server)) {
																				if((var194 == traceTempVariable$currentState$45_1)) {
																					{
																						{
																							double cv$temp$4$var243;
																							{
																								double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$45_1];
																								cv$temp$4$var243 = var243;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$4$var243)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$4$var243)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$4$var243));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$4$var243)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$4$var243)));
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
											boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global;
											for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
												if((sample$var53 == sample$var207)) {
													for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
														if((timeStep$var76 == timeStep$var239)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var207][server][timeStep$var239])
																	guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
															}
														}
													}
												}
											}
											for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
												if((sample$var53 == sample$var207)) {
													for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
														if((timeStep$var76 == timeStep$var239)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var207][server][timeStep$var239])
																	guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
															}
														}
													}
												}
											}
											int traceTempVariable$currentState$69_1 = cv$currentValue;
											for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
												if((sample$var53 == sample$var207)) {
													for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
														if((timeStep$var76 == timeStep$var239)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																		guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																		{
																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var130 = 0; var130 < noServers; var130 += 1) {
																						for(int var140 = 0; var140 < noStates; var140 += 1) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								if((var130 == server)) {
																									if((var140 == traceTempVariable$currentState$69_1)) {
																										for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																											if((index$sample$86_1 == sample$var207)) {
																												if((0 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var157 = 0; var157 < noServers; var157 += 1) {
																															for(int var167 = 0; var167 < noStates; var167 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var157 == server)) {
																																		if((var167 == traceTempVariable$currentState$69_1)) {
																																			{
																																				{
																																					double cv$temp$8$var254;
																																					{
																																						double var254 = current_metric_mean[server][traceTempVariable$currentState$69_1];
																																						cv$temp$8$var254 = var254;
																																					}
																																					double cv$temp$9$var256;
																																					{
																																						double var256 = current_metric_var[server][traceTempVariable$currentState$69_1];
																																						cv$temp$9$var256 = var256;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$8$var254) / Math.sqrt(cv$temp$9$var256))) - (0.5 * Math.log(cv$temp$9$var256)))));
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
																					for(int var130 = 0; var130 < noServers; var130 += 1) {
																						for(int var140 = 0; var140 < noStates; var140 += 1) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								if((var130 == server)) {
																									if((var140 == traceTempVariable$currentState$69_1)) {
																										int traceTempVariable$currentState$89_1 = cv$currentValue;
																										if((index$sample$23 == sample$var207)) {
																											if((index$timeStep$22 == timeStep$var239)) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													for(int var157 = 0; var157 < noServers; var157 += 1) {
																														for(int var167 = 0; var167 < noStates; var167 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var157 == server)) {
																																	if((var167 == traceTempVariable$currentState$89_1)) {
																																		{
																																			{
																																				double cv$temp$10$var254;
																																				{
																																					double var254 = current_metric_mean[server][traceTempVariable$currentState$89_1];
																																					cv$temp$10$var254 = var254;
																																				}
																																				double cv$temp$11$var256;
																																				{
																																					double var256 = current_metric_var[server][traceTempVariable$currentState$89_1];
																																					cv$temp$11$var256 = var256;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$10$var254) / Math.sqrt(cv$temp$11$var256))) - (0.5 * Math.log(cv$temp$11$var256)))));
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
																													for(int index$sample90$92 = 0; index$sample90$92 < noStates; index$sample90$92 += 1) {
																														int distributionTempVariable$var84$94 = index$sample90$92;
																														double cv$probabilitySample90Value93 = (1.0 * distribution$sample90[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample90$92]);
																														int traceTempVariable$currentState$95_1 = distributionTempVariable$var84$94;
																														if((index$sample$90 == sample$var207)) {
																															if((index$timeStep$91 == timeStep$var239)) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	for(int var157 = 0; var157 < noServers; var157 += 1) {
																																		for(int var167 = 0; var167 < noStates; var167 += 1) {
																																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																				if((var157 == server)) {
																																					if((var167 == traceTempVariable$currentState$95_1)) {
																																						{
																																							{
																																								double cv$temp$12$var254;
																																								{
																																									double var254 = current_metric_mean[server][traceTempVariable$currentState$95_1];
																																									cv$temp$12$var254 = var254;
																																								}
																																								double cv$temp$13$var256;
																																								{
																																									double var256 = current_metric_var[server][traceTempVariable$currentState$95_1];
																																									cv$temp$13$var256 = var256;
																																								}
																																								if(((Math.log(cv$probabilitySample90Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$12$var254) / Math.sqrt(cv$temp$13$var256))) - (0.5 * Math.log(cv$temp$13$var256)))));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value93);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
											int traceTempVariable$currentState$73_1 = cv$currentValue;
											for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
												if((sample$var53 == sample$var207)) {
													for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
														if((timeStep$var76 == timeStep$var239)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																	if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																		guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																		{
																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																						if((index$sample$157_1 == sample$var207)) {
																							if((0 == timeStep$var239)) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									for(int var130 = 0; var130 < noServers; var130 += 1) {
																										for(int var140 = 0; var140 < noStates; var140 += 1) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												if((var130 == server)) {
																													if((var140 == traceTempVariable$currentState$73_1)) {
																														for(int var157 = 0; var157 < noServers; var157 += 1) {
																															for(int var167 = 0; var167 < noStates; var167 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var157 == server)) {
																																		if((var167 == traceTempVariable$currentState$73_1)) {
																																			{
																																				{
																																					double cv$temp$40$var254;
																																					{
																																						double var254 = current_metric_mean[server][traceTempVariable$currentState$73_1];
																																						cv$temp$40$var254 = var254;
																																					}
																																					double cv$temp$41$var256;
																																					{
																																						double var256 = current_metric_var[server][traceTempVariable$currentState$73_1];
																																						cv$temp$41$var256 = var256;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$40$var254) / Math.sqrt(cv$temp$41$var256))) - (0.5 * Math.log(cv$temp$41$var256)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$40$var254) / Math.sqrt(cv$temp$41$var256))) - (0.5 * Math.log(cv$temp$41$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$40$var254) / Math.sqrt(cv$temp$41$var256))) - (0.5 * Math.log(cv$temp$41$var256))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$40$var254) / Math.sqrt(cv$temp$41$var256))) - (0.5 * Math.log(cv$temp$41$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$40$var254) / Math.sqrt(cv$temp$41$var256))) - (0.5 * Math.log(cv$temp$41$var256)))));
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
																					int traceTempVariable$currentState$160_1 = cv$currentValue;
																					if((index$sample$23 == sample$var207)) {
																						if((index$timeStep$22 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var130 = 0; var130 < noServers; var130 += 1) {
																									for(int var140 = 0; var140 < noStates; var140 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var130 == server)) {
																												if((var140 == traceTempVariable$currentState$160_1)) {
																													for(int var157 = 0; var157 < noServers; var157 += 1) {
																														for(int var167 = 0; var167 < noStates; var167 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var157 == server)) {
																																	if((var167 == traceTempVariable$currentState$160_1)) {
																																		{
																																			{
																																				double cv$temp$42$var254;
																																				{
																																					double var254 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																					cv$temp$42$var254 = var254;
																																				}
																																				double cv$temp$43$var256;
																																				{
																																					double var256 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																					cv$temp$43$var256 = var256;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$42$var254) / Math.sqrt(cv$temp$43$var256))) - (0.5 * Math.log(cv$temp$43$var256)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$42$var254) / Math.sqrt(cv$temp$43$var256))) - (0.5 * Math.log(cv$temp$43$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$42$var254) / Math.sqrt(cv$temp$43$var256))) - (0.5 * Math.log(cv$temp$43$var256))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$42$var254) / Math.sqrt(cv$temp$43$var256))) - (0.5 * Math.log(cv$temp$43$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$42$var254) / Math.sqrt(cv$temp$43$var256))) - (0.5 * Math.log(cv$temp$43$var256)))));
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
																					for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																						for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161][0]; index$timeStep$162 += 1) {
																							if(!((index$sample$161 == index$sample$23) && (index$timeStep$162 == index$timeStep$22))) {
																								for(int index$sample90$163 = 0; index$sample90$163 < noStates; index$sample90$163 += 1) {
																									int distributionTempVariable$var84$165 = index$sample90$163;
																									double cv$probabilitySample90Value164 = (1.0 * distribution$sample90[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample90$163]);
																									int traceTempVariable$currentState$166_1 = distributionTempVariable$var84$165;
																									if((index$sample$161 == sample$var207)) {
																										if((index$timeStep$162 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$166_1)) {
																																	for(int var157 = 0; var157 < noServers; var157 += 1) {
																																		for(int var167 = 0; var167 < noStates; var167 += 1) {
																																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																				if((var157 == server)) {
																																					if((var167 == traceTempVariable$currentState$166_1)) {
																																						{
																																							{
																																								double cv$temp$44$var254;
																																								{
																																									double var254 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																									cv$temp$44$var254 = var254;
																																								}
																																								double cv$temp$45$var256;
																																								{
																																									double var256 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																									cv$temp$45$var256 = var256;
																																								}
																																								if(((Math.log(cv$probabilitySample90Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$44$var254) / Math.sqrt(cv$temp$45$var256))) - (0.5 * Math.log(cv$temp$45$var256)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$44$var254) / Math.sqrt(cv$temp$45$var256))) - (0.5 * Math.log(cv$temp$45$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$44$var254) / Math.sqrt(cv$temp$45$var256))) - (0.5 * Math.log(cv$temp$45$var256))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$44$var254) / Math.sqrt(cv$temp$45$var256))) - (0.5 * Math.log(cv$temp$45$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$44$var254) / Math.sqrt(cv$temp$45$var256))) - (0.5 * Math.log(cv$temp$45$var256)))));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value164);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
						for(int index$sample71$26 = 0; index$sample71$26 < noStates; index$sample71$26 += 1) {
							int distributionTempVariable$var65$28 = index$sample71$26;
							double cv$probabilitySample71Value27 = (1.0 * distribution$sample71[((index$sample$25 - 0) / 1)][index$sample71$26]);
							int traceTempVariable$var81$29_1 = distributionTempVariable$var65$28;
							if((index$sample$25 == sample$var53)) {
								if((0 == (timeStep$var76 - 1))) {
									for(int var40 = 0; var40 < noStates; var40 += 1) {
										if((var40 == traceTempVariable$var81$29_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value27);
											double[] cv$temp$1$var82;
											{
												double[] var82 = m[traceTempVariable$var81$29_1];
												cv$temp$1$var82 = var82;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var81$42_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$46_1 = cv$currentValue;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var184 = 0; var184 < noServers; var184 += 1) {
																				for(int var194 = 0; var194 < noStates; var194 += 1) {
																					if((var184 == server)) {
																						if((var194 == traceTempVariable$currentState$46_1)) {
																							{
																								{
																									double cv$temp$5$var243;
																									{
																										double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$46_1];
																										cv$temp$5$var243 = var243;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$5$var243)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$5$var243)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$5$var243));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$5$var243)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$5$var243)));
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
													boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239])
																			guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239])
																			guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													int traceTempVariable$currentState$70_1 = cv$currentValue;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																				guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							for(int var130 = 0; var130 < noServers; var130 += 1) {
																								for(int var140 = 0; var140 < noStates; var140 += 1) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										if((var130 == server)) {
																											if((var140 == traceTempVariable$currentState$70_1)) {
																												int traceTempVariable$currentState$99_1 = distributionTempVariable$var65$28;
																												if((index$sample$25 == sample$var207)) {
																													if((0 == timeStep$var239)) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$99_1)) {
																																				{
																																					{
																																						double cv$temp$14$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$99_1];
																																							cv$temp$14$var254 = var254;
																																						}
																																						double cv$temp$15$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$99_1];
																																							cv$temp$15$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$14$var254) / Math.sqrt(cv$temp$15$var256))) - (0.5 * Math.log(cv$temp$15$var256)))));
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
																														for(int index$sample71$101 = 0; index$sample71$101 < noStates; index$sample71$101 += 1) {
																															int distributionTempVariable$var65$103 = index$sample71$101;
																															double cv$probabilitySample71Value102 = (1.0 * distribution$sample71[((index$sample$100 - 0) / 1)][index$sample71$101]);
																															int traceTempVariable$currentState$104_1 = distributionTempVariable$var65$103;
																															if((index$sample$100 == sample$var207)) {
																																if((0 == timeStep$var239)) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		for(int var157 = 0; var157 < noServers; var157 += 1) {
																																			for(int var167 = 0; var167 < noStates; var167 += 1) {
																																				if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																					if((var157 == server)) {
																																						if((var167 == traceTempVariable$currentState$104_1)) {
																																							{
																																								{
																																									double cv$temp$16$var254;
																																									{
																																										double var254 = current_metric_mean[server][traceTempVariable$currentState$104_1];
																																										cv$temp$16$var254 = var254;
																																									}
																																									double cv$temp$17$var256;
																																									{
																																										double var256 = current_metric_var[server][traceTempVariable$currentState$104_1];
																																										cv$temp$17$var256 = var256;
																																									}
																																									if(((Math.log(cv$probabilitySample71Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$16$var254) / Math.sqrt(cv$temp$17$var256))) - (0.5 * Math.log(cv$temp$17$var256)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value102);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int var130 = 0; var130 < noServers; var130 += 1) {
																								for(int var140 = 0; var140 < noStates; var140 += 1) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										if((var130 == server)) {
																											if((var140 == traceTempVariable$currentState$70_1)) {
																												int traceTempVariable$currentState$108_1 = cv$currentValue;
																												if((index$sample$23 == sample$var207)) {
																													if((index$timeStep$22 == timeStep$var239)) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$108_1)) {
																																				{
																																					{
																																						double cv$temp$18$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$108_1];
																																							cv$temp$18$var254 = var254;
																																						}
																																						double cv$temp$19$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$108_1];
																																							cv$temp$19$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$18$var254) / Math.sqrt(cv$temp$19$var256))) - (0.5 * Math.log(cv$temp$19$var256)))));
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
																															for(int index$sample90$111 = 0; index$sample90$111 < noStates; index$sample90$111 += 1) {
																																int distributionTempVariable$var84$113 = index$sample90$111;
																																double cv$probabilitySample90Value112 = (1.0 * distribution$sample90[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample90$111]);
																																int traceTempVariable$currentState$114_1 = distributionTempVariable$var84$113;
																																if((index$sample$109 == sample$var207)) {
																																	if((index$timeStep$110 == timeStep$var239)) {
																																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																			for(int var157 = 0; var157 < noServers; var157 += 1) {
																																				for(int var167 = 0; var167 < noStates; var167 += 1) {
																																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																						if((var157 == server)) {
																																							if((var167 == traceTempVariable$currentState$114_1)) {
																																								{
																																									{
																																										double cv$temp$20$var254;
																																										{
																																											double var254 = current_metric_mean[server][traceTempVariable$currentState$114_1];
																																											cv$temp$20$var254 = var254;
																																										}
																																										double cv$temp$21$var256;
																																										{
																																											double var256 = current_metric_var[server][traceTempVariable$currentState$114_1];
																																											cv$temp$21$var256 = var256;
																																										}
																																										if(((Math.log(cv$probabilitySample90Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$20$var254) / Math.sqrt(cv$temp$21$var256))) - (0.5 * Math.log(cv$temp$21$var256)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value112);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
													int traceTempVariable$currentState$74_1 = cv$currentValue;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																				guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							int traceTempVariable$currentState$171_1 = distributionTempVariable$var65$28;
																							if((index$sample$25 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$171_1)) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$171_1)) {
																																				{
																																					{
																																						double cv$temp$46$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$171_1];
																																							cv$temp$46$var254 = var254;
																																						}
																																						double cv$temp$47$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$171_1];
																																							cv$temp$47$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$46$var254) / Math.sqrt(cv$temp$47$var256))) - (0.5 * Math.log(cv$temp$47$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$46$var254) / Math.sqrt(cv$temp$47$var256))) - (0.5 * Math.log(cv$temp$47$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$46$var254) / Math.sqrt(cv$temp$47$var256))) - (0.5 * Math.log(cv$temp$47$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$46$var254) / Math.sqrt(cv$temp$47$var256))) - (0.5 * Math.log(cv$temp$47$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$46$var254) / Math.sqrt(cv$temp$47$var256))) - (0.5 * Math.log(cv$temp$47$var256)))));
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
																							for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																								if(!(index$sample$172 == index$sample$25)) {
																									for(int index$sample71$173 = 0; index$sample71$173 < noStates; index$sample71$173 += 1) {
																										int distributionTempVariable$var65$175 = index$sample71$173;
																										double cv$probabilitySample71Value174 = (1.0 * distribution$sample71[((index$sample$172 - 0) / 1)][index$sample71$173]);
																										int traceTempVariable$currentState$176_1 = distributionTempVariable$var65$175;
																										if((index$sample$172 == sample$var207)) {
																											if((0 == timeStep$var239)) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													for(int var130 = 0; var130 < noServers; var130 += 1) {
																														for(int var140 = 0; var140 < noStates; var140 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var130 == server)) {
																																	if((var140 == traceTempVariable$currentState$176_1)) {
																																		for(int var157 = 0; var157 < noServers; var157 += 1) {
																																			for(int var167 = 0; var167 < noStates; var167 += 1) {
																																				if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																					if((var157 == server)) {
																																						if((var167 == traceTempVariable$currentState$176_1)) {
																																							{
																																								{
																																									double cv$temp$48$var254;
																																									{
																																										double var254 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																										cv$temp$48$var254 = var254;
																																									}
																																									double cv$temp$49$var256;
																																									{
																																										double var256 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																										cv$temp$49$var256 = var256;
																																									}
																																									if(((Math.log(cv$probabilitySample71Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$48$var254) / Math.sqrt(cv$temp$49$var256))) - (0.5 * Math.log(cv$temp$49$var256)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$48$var254) / Math.sqrt(cv$temp$49$var256))) - (0.5 * Math.log(cv$temp$49$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$48$var254) / Math.sqrt(cv$temp$49$var256))) - (0.5 * Math.log(cv$temp$49$var256))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$48$var254) / Math.sqrt(cv$temp$49$var256))) - (0.5 * Math.log(cv$temp$49$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$48$var254) / Math.sqrt(cv$temp$49$var256))) - (0.5 * Math.log(cv$temp$49$var256)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value174);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
																							if((index$sample$23 == sample$var207)) {
																								if((index$timeStep$22 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$181_1)) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$181_1)) {
																																				{
																																					{
																																						double cv$temp$50$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																							cv$temp$50$var254 = var254;
																																						}
																																						double cv$temp$51$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																							cv$temp$51$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$50$var254) / Math.sqrt(cv$temp$51$var256))) - (0.5 * Math.log(cv$temp$51$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$50$var254) / Math.sqrt(cv$temp$51$var256))) - (0.5 * Math.log(cv$temp$51$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$50$var254) / Math.sqrt(cv$temp$51$var256))) - (0.5 * Math.log(cv$temp$51$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$50$var254) / Math.sqrt(cv$temp$51$var256))) - (0.5 * Math.log(cv$temp$51$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$50$var254) / Math.sqrt(cv$temp$51$var256))) - (0.5 * Math.log(cv$temp$51$var256)))));
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
																							for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																								for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182][0]; index$timeStep$183 += 1) {
																									if(!((index$sample$182 == index$sample$23) && (index$timeStep$183 == index$timeStep$22))) {
																										for(int index$sample90$184 = 0; index$sample90$184 < noStates; index$sample90$184 += 1) {
																											int distributionTempVariable$var84$186 = index$sample90$184;
																											double cv$probabilitySample90Value185 = (1.0 * distribution$sample90[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample90$184]);
																											int traceTempVariable$currentState$187_1 = distributionTempVariable$var84$186;
																											if((index$sample$182 == sample$var207)) {
																												if((index$timeStep$183 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var130 = 0; var130 < noServers; var130 += 1) {
																															for(int var140 = 0; var140 < noStates; var140 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var130 == server)) {
																																		if((var140 == traceTempVariable$currentState$187_1)) {
																																			for(int var157 = 0; var157 < noServers; var157 += 1) {
																																				for(int var167 = 0; var167 < noStates; var167 += 1) {
																																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																						if((var157 == server)) {
																																							if((var167 == traceTempVariable$currentState$187_1)) {
																																								{
																																									{
																																										double cv$temp$52$var254;
																																										{
																																											double var254 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																											cv$temp$52$var254 = var254;
																																										}
																																										double cv$temp$53$var256;
																																										{
																																											double var256 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																											cv$temp$53$var256 = var256;
																																										}
																																										if(((Math.log(cv$probabilitySample90Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$52$var254) / Math.sqrt(cv$temp$53$var256))) - (0.5 * Math.log(cv$temp$53$var256)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$52$var254) / Math.sqrt(cv$temp$53$var256))) - (0.5 * Math.log(cv$temp$53$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$52$var254) / Math.sqrt(cv$temp$53$var256))) - (0.5 * Math.log(cv$temp$53$var256))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$52$var254) / Math.sqrt(cv$temp$53$var256))) - (0.5 * Math.log(cv$temp$53$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$52$var254) / Math.sqrt(cv$temp$53$var256))) - (0.5 * Math.log(cv$temp$53$var256)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value185);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
			int traceTempVariable$var81$32_1 = cv$currentValue;
			if((index$sample$23 == sample$var53)) {
				if((index$timeStep$22 == (timeStep$var76 - 1))) {
					for(int var40 = 0; var40 < noStates; var40 += 1) {
						if((var40 == traceTempVariable$var81$32_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var82;
							{
								double[] var82 = m[traceTempVariable$var81$32_1];
								cv$temp$2$var82 = var82;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var82.length))?Math.log(cv$temp$2$var82[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var81$43_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$47_1 = cv$currentValue;
									for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
										if((sample$var53 == sample$var207)) {
											for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
												if((timeStep$var76 == timeStep$var239)) {
													for(int server = 0; server < noServers; server += 1) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var184 = 0; var184 < noServers; var184 += 1) {
																for(int var194 = 0; var194 < noStates; var194 += 1) {
																	if((var184 == server)) {
																		if((var194 == traceTempVariable$currentState$47_1)) {
																			{
																				{
																					double cv$temp$6$var243;
																					{
																						double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$47_1];
																						cv$temp$6$var243 = var243;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$6$var243)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$6$var243)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$6$var243));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$6$var243)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$6$var243)));
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
									boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global;
									for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
										if((sample$var53 == sample$var207)) {
											for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
												if((timeStep$var76 == timeStep$var239)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var207][server][timeStep$var239])
															guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
													}
												}
											}
										}
									}
									for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
										if((sample$var53 == sample$var207)) {
											for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
												if((timeStep$var76 == timeStep$var239)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var207][server][timeStep$var239])
															guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
													}
												}
											}
										}
									}
									int traceTempVariable$currentState$71_1 = cv$currentValue;
									for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
										if((sample$var53 == sample$var207)) {
											for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
												if((timeStep$var76 == timeStep$var239)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																{
																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var130 = 0; var130 < noServers; var130 += 1) {
																				for(int var140 = 0; var140 < noStates; var140 += 1) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						if((var130 == server)) {
																							if((var140 == traceTempVariable$currentState$71_1)) {
																								if(fixedFlag$sample71) {
																									for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																										if((index$sample$118_1 == sample$var207)) {
																											if((0 == timeStep$var239)) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													for(int var157 = 0; var157 < noServers; var157 += 1) {
																														for(int var167 = 0; var167 < noStates; var167 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var157 == server)) {
																																	if((var167 == traceTempVariable$currentState$71_1)) {
																																		{
																																			{
																																				double cv$temp$22$var254;
																																				{
																																					double var254 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																					cv$temp$22$var254 = var254;
																																				}
																																				double cv$temp$23$var256;
																																				{
																																					double var256 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																					cv$temp$23$var256 = var256;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$22$var254) / Math.sqrt(cv$temp$23$var256))) - (0.5 * Math.log(cv$temp$23$var256)))));
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
																											for(int index$sample71$120 = 0; index$sample71$120 < noStates; index$sample71$120 += 1) {
																												int distributionTempVariable$var65$122 = index$sample71$120;
																												double cv$probabilitySample71Value121 = (1.0 * distribution$sample71[((index$sample$119 - 0) / 1)][index$sample71$120]);
																												int traceTempVariable$currentState$123_1 = distributionTempVariable$var65$122;
																												if((index$sample$119 == sample$var207)) {
																													if((0 == timeStep$var239)) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$123_1)) {
																																				{
																																					{
																																						double cv$temp$24$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$123_1];
																																							cv$temp$24$var254 = var254;
																																						}
																																						double cv$temp$25$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$123_1];
																																							cv$temp$25$var256 = var256;
																																						}
																																						if(((Math.log(cv$probabilitySample71Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$24$var254) / Math.sqrt(cv$temp$25$var256))) - (0.5 * Math.log(cv$temp$25$var256)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value121);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int var130 = 0; var130 < noServers; var130 += 1) {
																				for(int var140 = 0; var140 < noStates; var140 += 1) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						if((var130 == server)) {
																							if((var140 == traceTempVariable$currentState$71_1)) {
																								int traceTempVariable$currentState$127_1 = cv$currentValue;
																								if((index$sample$23 == sample$var207)) {
																									if((index$timeStep$22 == timeStep$var239)) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											for(int var157 = 0; var157 < noServers; var157 += 1) {
																												for(int var167 = 0; var167 < noStates; var167 += 1) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														if((var157 == server)) {
																															if((var167 == traceTempVariable$currentState$127_1)) {
																																{
																																	{
																																		double cv$temp$26$var254;
																																		{
																																			double var254 = current_metric_mean[server][traceTempVariable$currentState$127_1];
																																			cv$temp$26$var254 = var254;
																																		}
																																		double cv$temp$27$var256;
																																		{
																																			double var256 = current_metric_var[server][traceTempVariable$currentState$127_1];
																																			cv$temp$27$var256 = var256;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$26$var254) / Math.sqrt(cv$temp$27$var256))) - (0.5 * Math.log(cv$temp$27$var256)))));
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
																											for(int index$sample90$130 = 0; index$sample90$130 < noStates; index$sample90$130 += 1) {
																												int distributionTempVariable$var84$132 = index$sample90$130;
																												double cv$probabilitySample90Value131 = (1.0 * distribution$sample90[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample90$130]);
																												int traceTempVariable$currentState$133_1 = distributionTempVariable$var84$132;
																												if((index$sample$128 == sample$var207)) {
																													if((index$timeStep$129 == timeStep$var239)) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$133_1)) {
																																				{
																																					{
																																						double cv$temp$28$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$133_1];
																																							cv$temp$28$var254 = var254;
																																						}
																																						double cv$temp$29$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$133_1];
																																							cv$temp$29$var256 = var256;
																																						}
																																						if(((Math.log(cv$probabilitySample90Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$28$var254) / Math.sqrt(cv$temp$29$var256))) - (0.5 * Math.log(cv$temp$29$var256)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value131);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
									int traceTempVariable$currentState$75_1 = cv$currentValue;
									for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
										if((sample$var53 == sample$var207)) {
											for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
												if((timeStep$var76 == timeStep$var239)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
															if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																{
																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample71) {
																				for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																					if((index$sample$192_1 == sample$var207)) {
																						if((0 == timeStep$var239)) {
																							if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																								for(int var130 = 0; var130 < noServers; var130 += 1) {
																									for(int var140 = 0; var140 < noStates; var140 += 1) {
																										if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																											if((var130 == server)) {
																												if((var140 == traceTempVariable$currentState$75_1)) {
																													for(int var157 = 0; var157 < noServers; var157 += 1) {
																														for(int var167 = 0; var167 < noStates; var167 += 1) {
																															if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																if((var157 == server)) {
																																	if((var167 == traceTempVariable$currentState$75_1)) {
																																		{
																																			{
																																				double cv$temp$54$var254;
																																				{
																																					double var254 = current_metric_mean[server][traceTempVariable$currentState$75_1];
																																					cv$temp$54$var254 = var254;
																																				}
																																				double cv$temp$55$var256;
																																				{
																																					double var256 = current_metric_var[server][traceTempVariable$currentState$75_1];
																																					cv$temp$55$var256 = var256;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$54$var254) / Math.sqrt(cv$temp$55$var256))) - (0.5 * Math.log(cv$temp$55$var256)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$54$var254) / Math.sqrt(cv$temp$55$var256))) - (0.5 * Math.log(cv$temp$55$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$54$var254) / Math.sqrt(cv$temp$55$var256))) - (0.5 * Math.log(cv$temp$55$var256))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$54$var254) / Math.sqrt(cv$temp$55$var256))) - (0.5 * Math.log(cv$temp$55$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$54$var254) / Math.sqrt(cv$temp$55$var256))) - (0.5 * Math.log(cv$temp$55$var256)))));
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
																			} else {
																				for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																					if(true) {
																						for(int index$sample71$194 = 0; index$sample71$194 < noStates; index$sample71$194 += 1) {
																							int distributionTempVariable$var65$196 = index$sample71$194;
																							double cv$probabilitySample71Value195 = (1.0 * distribution$sample71[((index$sample$193 - 0) / 1)][index$sample71$194]);
																							int traceTempVariable$currentState$197_1 = distributionTempVariable$var65$196;
																							if((index$sample$193 == sample$var207)) {
																								if((0 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$197_1)) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$197_1)) {
																																				{
																																					{
																																						double cv$temp$56$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$197_1];
																																							cv$temp$56$var254 = var254;
																																						}
																																						double cv$temp$57$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$197_1];
																																							cv$temp$57$var256 = var256;
																																						}
																																						if(((Math.log(cv$probabilitySample71Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$56$var254) / Math.sqrt(cv$temp$57$var256))) - (0.5 * Math.log(cv$temp$57$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$56$var254) / Math.sqrt(cv$temp$57$var256))) - (0.5 * Math.log(cv$temp$57$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$56$var254) / Math.sqrt(cv$temp$57$var256))) - (0.5 * Math.log(cv$temp$57$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$56$var254) / Math.sqrt(cv$temp$57$var256))) - (0.5 * Math.log(cv$temp$57$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$56$var254) / Math.sqrt(cv$temp$57$var256))) - (0.5 * Math.log(cv$temp$57$var256)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value195);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
																			if((index$sample$23 == sample$var207)) {
																				if((index$timeStep$22 == timeStep$var239)) {
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						for(int var130 = 0; var130 < noServers; var130 += 1) {
																							for(int var140 = 0; var140 < noStates; var140 += 1) {
																								if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																									if((var130 == server)) {
																										if((var140 == traceTempVariable$currentState$202_1)) {
																											for(int var157 = 0; var157 < noServers; var157 += 1) {
																												for(int var167 = 0; var167 < noStates; var167 += 1) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														if((var157 == server)) {
																															if((var167 == traceTempVariable$currentState$202_1)) {
																																{
																																	{
																																		double cv$temp$58$var254;
																																		{
																																			double var254 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																			cv$temp$58$var254 = var254;
																																		}
																																		double cv$temp$59$var256;
																																		{
																																			double var256 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																			cv$temp$59$var256 = var256;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$58$var254) / Math.sqrt(cv$temp$59$var256))) - (0.5 * Math.log(cv$temp$59$var256)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$58$var254) / Math.sqrt(cv$temp$59$var256))) - (0.5 * Math.log(cv$temp$59$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$58$var254) / Math.sqrt(cv$temp$59$var256))) - (0.5 * Math.log(cv$temp$59$var256))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$58$var254) / Math.sqrt(cv$temp$59$var256))) - (0.5 * Math.log(cv$temp$59$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$58$var254) / Math.sqrt(cv$temp$59$var256))) - (0.5 * Math.log(cv$temp$59$var256)))));
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
																			for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																				for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203][0]; index$timeStep$204 += 1) {
																					if(!((index$sample$203 == index$sample$23) && (index$timeStep$204 == index$timeStep$22))) {
																						for(int index$sample90$205 = 0; index$sample90$205 < noStates; index$sample90$205 += 1) {
																							int distributionTempVariable$var84$207 = index$sample90$205;
																							double cv$probabilitySample90Value206 = (1.0 * distribution$sample90[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample90$205]);
																							int traceTempVariable$currentState$208_1 = distributionTempVariable$var84$207;
																							if((index$sample$203 == sample$var207)) {
																								if((index$timeStep$204 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$208_1)) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$208_1)) {
																																				{
																																					{
																																						double cv$temp$60$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$208_1];
																																							cv$temp$60$var254 = var254;
																																						}
																																						double cv$temp$61$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$208_1];
																																							cv$temp$61$var256 = var256;
																																						}
																																						if(((Math.log(cv$probabilitySample90Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$60$var254) / Math.sqrt(cv$temp$61$var256))) - (0.5 * Math.log(cv$temp$61$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$60$var254) / Math.sqrt(cv$temp$61$var256))) - (0.5 * Math.log(cv$temp$61$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$60$var254) / Math.sqrt(cv$temp$61$var256))) - (0.5 * Math.log(cv$temp$61$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$60$var254) / Math.sqrt(cv$temp$61$var256))) - (0.5 * Math.log(cv$temp$61$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$60$var254) / Math.sqrt(cv$temp$61$var256))) - (0.5 * Math.log(cv$temp$61$var256)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value206);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
						for(int index$sample90$35 = 0; index$sample90$35 < noStates; index$sample90$35 += 1) {
							int distributionTempVariable$var84$37 = index$sample90$35;
							double cv$probabilitySample90Value36 = (1.0 * distribution$sample90[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample90$35]);
							int traceTempVariable$var81$38_1 = distributionTempVariable$var84$37;
							if((index$sample$33 == sample$var53)) {
								if((index$timeStep$34 == (timeStep$var76 - 1))) {
									for(int var40 = 0; var40 < noStates; var40 += 1) {
										if((var40 == traceTempVariable$var81$38_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample90Value36);
											double[] cv$temp$3$var82;
											{
												double[] var82 = m[traceTempVariable$var81$38_1];
												cv$temp$3$var82 = var82;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample90Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var82.length))?Math.log(cv$temp$3$var82[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var81$44_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$48_1 = cv$currentValue;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var184 = 0; var184 < noServers; var184 += 1) {
																				for(int var194 = 0; var194 < noStates; var194 += 1) {
																					if((var184 == server)) {
																						if((var194 == traceTempVariable$currentState$48_1)) {
																							{
																								{
																									double cv$temp$7$var243;
																									{
																										double var243 = current_metric_valid_bias[server][traceTempVariable$currentState$48_1];
																										cv$temp$7$var243 = var243;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$7$var243)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$7$var243)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$7$var243));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$7$var243)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], cv$temp$7$var243)));
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
													boolean[][][] guard$sample90gaussian274 = guard$sample90gaussian274$global;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239])
																			guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239])
																			guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													int traceTempVariable$currentState$72_1 = cv$currentValue;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																				guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							for(int var130 = 0; var130 < noServers; var130 += 1) {
																								for(int var140 = 0; var140 < noStates; var140 += 1) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										if((var130 == server)) {
																											if((var140 == traceTempVariable$currentState$72_1)) {
																												if(fixedFlag$sample71) {
																													for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																														if((index$sample$137_1 == sample$var207)) {
																															if((0 == timeStep$var239)) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	for(int var157 = 0; var157 < noServers; var157 += 1) {
																																		for(int var167 = 0; var167 < noStates; var167 += 1) {
																																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																				if((var157 == server)) {
																																					if((var167 == traceTempVariable$currentState$72_1)) {
																																						{
																																							{
																																								double cv$temp$30$var254;
																																								{
																																									double var254 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																									cv$temp$30$var254 = var254;
																																								}
																																								double cv$temp$31$var256;
																																								{
																																									double var256 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																									cv$temp$31$var256 = var256;
																																								}
																																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$30$var254) / Math.sqrt(cv$temp$31$var256))) - (0.5 * Math.log(cv$temp$31$var256)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$30$var254) / Math.sqrt(cv$temp$31$var256))) - (0.5 * Math.log(cv$temp$31$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$30$var254) / Math.sqrt(cv$temp$31$var256))) - (0.5 * Math.log(cv$temp$31$var256))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$30$var254) / Math.sqrt(cv$temp$31$var256))) - (0.5 * Math.log(cv$temp$31$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$30$var254) / Math.sqrt(cv$temp$31$var256))) - (0.5 * Math.log(cv$temp$31$var256)))));
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
																															for(int index$sample71$139 = 0; index$sample71$139 < noStates; index$sample71$139 += 1) {
																																int distributionTempVariable$var65$141 = index$sample71$139;
																																double cv$probabilitySample71Value140 = (1.0 * distribution$sample71[((index$sample$138 - 0) / 1)][index$sample71$139]);
																																int traceTempVariable$currentState$142_1 = distributionTempVariable$var65$141;
																																if((index$sample$138 == sample$var207)) {
																																	if((0 == timeStep$var239)) {
																																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																			for(int var157 = 0; var157 < noServers; var157 += 1) {
																																				for(int var167 = 0; var167 < noStates; var167 += 1) {
																																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																						if((var157 == server)) {
																																							if((var167 == traceTempVariable$currentState$142_1)) {
																																								{
																																									{
																																										double cv$temp$32$var254;
																																										{
																																											double var254 = current_metric_mean[server][traceTempVariable$currentState$142_1];
																																											cv$temp$32$var254 = var254;
																																										}
																																										double cv$temp$33$var256;
																																										{
																																											double var256 = current_metric_var[server][traceTempVariable$currentState$142_1];
																																											cv$temp$33$var256 = var256;
																																										}
																																										if(((Math.log(cv$probabilitySample71Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$32$var254) / Math.sqrt(cv$temp$33$var256))) - (0.5 * Math.log(cv$temp$33$var256)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$32$var254) / Math.sqrt(cv$temp$33$var256))) - (0.5 * Math.log(cv$temp$33$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$32$var254) / Math.sqrt(cv$temp$33$var256))) - (0.5 * Math.log(cv$temp$33$var256))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$32$var254) / Math.sqrt(cv$temp$33$var256))) - (0.5 * Math.log(cv$temp$33$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$32$var254) / Math.sqrt(cv$temp$33$var256))) - (0.5 * Math.log(cv$temp$33$var256)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value140);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int var130 = 0; var130 < noServers; var130 += 1) {
																								for(int var140 = 0; var140 < noStates; var140 += 1) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										if((var130 == server)) {
																											if((var140 == traceTempVariable$currentState$72_1)) {
																												int traceTempVariable$currentState$146_1 = cv$currentValue;
																												if((index$sample$23 == sample$var207)) {
																													if((index$timeStep$22 == timeStep$var239)) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$146_1)) {
																																				{
																																					{
																																						double cv$temp$34$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$146_1];
																																							cv$temp$34$var254 = var254;
																																						}
																																						double cv$temp$35$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$146_1];
																																							cv$temp$35$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$34$var254) / Math.sqrt(cv$temp$35$var256))) - (0.5 * Math.log(cv$temp$35$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$34$var254) / Math.sqrt(cv$temp$35$var256))) - (0.5 * Math.log(cv$temp$35$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$34$var254) / Math.sqrt(cv$temp$35$var256))) - (0.5 * Math.log(cv$temp$35$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$34$var254) / Math.sqrt(cv$temp$35$var256))) - (0.5 * Math.log(cv$temp$35$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$34$var254) / Math.sqrt(cv$temp$35$var256))) - (0.5 * Math.log(cv$temp$35$var256)))));
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
																												int traceTempVariable$currentState$147_1 = distributionTempVariable$var84$37;
																												if((index$sample$33 == sample$var207)) {
																													if((index$timeStep$34 == timeStep$var239)) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$147_1)) {
																																				{
																																					{
																																						double cv$temp$36$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$147_1];
																																							cv$temp$36$var254 = var254;
																																						}
																																						double cv$temp$37$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$147_1];
																																							cv$temp$37$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$36$var254) / Math.sqrt(cv$temp$37$var256))) - (0.5 * Math.log(cv$temp$37$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$36$var254) / Math.sqrt(cv$temp$37$var256))) - (0.5 * Math.log(cv$temp$37$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$36$var254) / Math.sqrt(cv$temp$37$var256))) - (0.5 * Math.log(cv$temp$37$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$36$var254) / Math.sqrt(cv$temp$37$var256))) - (0.5 * Math.log(cv$temp$37$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$36$var254) / Math.sqrt(cv$temp$37$var256))) - (0.5 * Math.log(cv$temp$37$var256)))));
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
																															for(int index$sample90$150 = 0; index$sample90$150 < noStates; index$sample90$150 += 1) {
																																int distributionTempVariable$var84$152 = index$sample90$150;
																																double cv$probabilitySample90Value151 = (1.0 * distribution$sample90[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample90$150]);
																																int traceTempVariable$currentState$153_1 = distributionTempVariable$var84$152;
																																if((index$sample$148 == sample$var207)) {
																																	if((index$timeStep$149 == timeStep$var239)) {
																																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																			for(int var157 = 0; var157 < noServers; var157 += 1) {
																																				for(int var167 = 0; var167 < noStates; var167 += 1) {
																																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																						if((var157 == server)) {
																																							if((var167 == traceTempVariable$currentState$153_1)) {
																																								{
																																									{
																																										double cv$temp$38$var254;
																																										{
																																											double var254 = current_metric_mean[server][traceTempVariable$currentState$153_1];
																																											cv$temp$38$var254 = var254;
																																										}
																																										double cv$temp$39$var256;
																																										{
																																											double var256 = current_metric_var[server][traceTempVariable$currentState$153_1];
																																											cv$temp$39$var256 = var256;
																																										}
																																										if(((Math.log(cv$probabilitySample90Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$38$var254) / Math.sqrt(cv$temp$39$var256))) - (0.5 * Math.log(cv$temp$39$var256)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$38$var254) / Math.sqrt(cv$temp$39$var256))) - (0.5 * Math.log(cv$temp$39$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$38$var254) / Math.sqrt(cv$temp$39$var256))) - (0.5 * Math.log(cv$temp$39$var256))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$38$var254) / Math.sqrt(cv$temp$39$var256))) - (0.5 * Math.log(cv$temp$39$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$38$var254) / Math.sqrt(cv$temp$39$var256))) - (0.5 * Math.log(cv$temp$39$var256)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value151);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
													int traceTempVariable$currentState$76_1 = cv$currentValue;
													for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
														if((sample$var53 == sample$var207)) {
															for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
																if((timeStep$var76 == timeStep$var239)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																			if(!guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)]) {
																				guard$sample90gaussian274[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample71) {
																								for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																									if((index$sample$213_1 == sample$var207)) {
																										if((0 == timeStep$var239)) {
																											if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																												for(int var130 = 0; var130 < noServers; var130 += 1) {
																													for(int var140 = 0; var140 < noStates; var140 += 1) {
																														if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																															if((var130 == server)) {
																																if((var140 == traceTempVariable$currentState$76_1)) {
																																	for(int var157 = 0; var157 < noServers; var157 += 1) {
																																		for(int var167 = 0; var167 < noStates; var167 += 1) {
																																			if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																				if((var157 == server)) {
																																					if((var167 == traceTempVariable$currentState$76_1)) {
																																						{
																																							{
																																								double cv$temp$62$var254;
																																								{
																																									double var254 = current_metric_mean[server][traceTempVariable$currentState$76_1];
																																									cv$temp$62$var254 = var254;
																																								}
																																								double cv$temp$63$var256;
																																								{
																																									double var256 = current_metric_var[server][traceTempVariable$currentState$76_1];
																																									cv$temp$63$var256 = var256;
																																								}
																																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$62$var254) / Math.sqrt(cv$temp$63$var256))) - (0.5 * Math.log(cv$temp$63$var256)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$62$var254) / Math.sqrt(cv$temp$63$var256))) - (0.5 * Math.log(cv$temp$63$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$62$var254) / Math.sqrt(cv$temp$63$var256))) - (0.5 * Math.log(cv$temp$63$var256))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$62$var254) / Math.sqrt(cv$temp$63$var256))) - (0.5 * Math.log(cv$temp$63$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$62$var254) / Math.sqrt(cv$temp$63$var256))) - (0.5 * Math.log(cv$temp$63$var256)))));
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
																							} else {
																								for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																									if(true) {
																										for(int index$sample71$215 = 0; index$sample71$215 < noStates; index$sample71$215 += 1) {
																											int distributionTempVariable$var65$217 = index$sample71$215;
																											double cv$probabilitySample71Value216 = (1.0 * distribution$sample71[((index$sample$214 - 0) / 1)][index$sample71$215]);
																											int traceTempVariable$currentState$218_1 = distributionTempVariable$var65$217;
																											if((index$sample$214 == sample$var207)) {
																												if((0 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var130 = 0; var130 < noServers; var130 += 1) {
																															for(int var140 = 0; var140 < noStates; var140 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var130 == server)) {
																																		if((var140 == traceTempVariable$currentState$218_1)) {
																																			for(int var157 = 0; var157 < noServers; var157 += 1) {
																																				for(int var167 = 0; var167 < noStates; var167 += 1) {
																																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																						if((var157 == server)) {
																																							if((var167 == traceTempVariable$currentState$218_1)) {
																																								{
																																									{
																																										double cv$temp$64$var254;
																																										{
																																											double var254 = current_metric_mean[server][traceTempVariable$currentState$218_1];
																																											cv$temp$64$var254 = var254;
																																										}
																																										double cv$temp$65$var256;
																																										{
																																											double var256 = current_metric_var[server][traceTempVariable$currentState$218_1];
																																											cv$temp$65$var256 = var256;
																																										}
																																										if(((Math.log(cv$probabilitySample71Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$64$var254) / Math.sqrt(cv$temp$65$var256))) - (0.5 * Math.log(cv$temp$65$var256)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample71Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$64$var254) / Math.sqrt(cv$temp$65$var256))) - (0.5 * Math.log(cv$temp$65$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample71Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$64$var254) / Math.sqrt(cv$temp$65$var256))) - (0.5 * Math.log(cv$temp$65$var256))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample71Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$64$var254) / Math.sqrt(cv$temp$65$var256))) - (0.5 * Math.log(cv$temp$65$var256)))))) + 1)) + (Math.log(cv$probabilitySample71Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$64$var254) / Math.sqrt(cv$temp$65$var256))) - (0.5 * Math.log(cv$temp$65$var256)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample71Value216);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
																							if((index$sample$23 == sample$var207)) {
																								if((index$timeStep$22 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$223_1)) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$223_1)) {
																																				{
																																					{
																																						double cv$temp$66$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$223_1];
																																							cv$temp$66$var254 = var254;
																																						}
																																						double cv$temp$67$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$223_1];
																																							cv$temp$67$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$66$var254) / Math.sqrt(cv$temp$67$var256))) - (0.5 * Math.log(cv$temp$67$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$66$var254) / Math.sqrt(cv$temp$67$var256))) - (0.5 * Math.log(cv$temp$67$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$66$var254) / Math.sqrt(cv$temp$67$var256))) - (0.5 * Math.log(cv$temp$67$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$66$var254) / Math.sqrt(cv$temp$67$var256))) - (0.5 * Math.log(cv$temp$67$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$66$var254) / Math.sqrt(cv$temp$67$var256))) - (0.5 * Math.log(cv$temp$67$var256)))));
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
																							int traceTempVariable$currentState$224_1 = distributionTempVariable$var84$37;
																							if((index$sample$33 == sample$var207)) {
																								if((index$timeStep$34 == timeStep$var239)) {
																									if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																										for(int var130 = 0; var130 < noServers; var130 += 1) {
																											for(int var140 = 0; var140 < noStates; var140 += 1) {
																												if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																													if((var130 == server)) {
																														if((var140 == traceTempVariable$currentState$224_1)) {
																															for(int var157 = 0; var157 < noServers; var157 += 1) {
																																for(int var167 = 0; var167 < noStates; var167 += 1) {
																																	if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																		if((var157 == server)) {
																																			if((var167 == traceTempVariable$currentState$224_1)) {
																																				{
																																					{
																																						double cv$temp$68$var254;
																																						{
																																							double var254 = current_metric_mean[server][traceTempVariable$currentState$224_1];
																																							cv$temp$68$var254 = var254;
																																						}
																																						double cv$temp$69$var256;
																																						{
																																							double var256 = current_metric_var[server][traceTempVariable$currentState$224_1];
																																							cv$temp$69$var256 = var256;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$68$var254) / Math.sqrt(cv$temp$69$var256))) - (0.5 * Math.log(cv$temp$69$var256)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$68$var254) / Math.sqrt(cv$temp$69$var256))) - (0.5 * Math.log(cv$temp$69$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$68$var254) / Math.sqrt(cv$temp$69$var256))) - (0.5 * Math.log(cv$temp$69$var256))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$68$var254) / Math.sqrt(cv$temp$69$var256))) - (0.5 * Math.log(cv$temp$69$var256)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$68$var254) / Math.sqrt(cv$temp$69$var256))) - (0.5 * Math.log(cv$temp$69$var256)))));
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
																							for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																								for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225][0]; index$timeStep$226 += 1) {
																									if((!((index$sample$225 == index$sample$23) && (index$timeStep$226 == index$timeStep$22)) && !((index$sample$225 == index$sample$33) && (index$timeStep$226 == index$timeStep$34)))) {
																										for(int index$sample90$227 = 0; index$sample90$227 < noStates; index$sample90$227 += 1) {
																											int distributionTempVariable$var84$229 = index$sample90$227;
																											double cv$probabilitySample90Value228 = (1.0 * distribution$sample90[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample90$227]);
																											int traceTempVariable$currentState$230_1 = distributionTempVariable$var84$229;
																											if((index$sample$225 == sample$var207)) {
																												if((index$timeStep$226 == timeStep$var239)) {
																													if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																														for(int var130 = 0; var130 < noServers; var130 += 1) {
																															for(int var140 = 0; var140 < noStates; var140 += 1) {
																																if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																	if((var130 == server)) {
																																		if((var140 == traceTempVariable$currentState$230_1)) {
																																			for(int var157 = 0; var157 < noServers; var157 += 1) {
																																				for(int var167 = 0; var167 < noStates; var167 += 1) {
																																					if(metric_valid_g[sample$var207][server][timeStep$var239]) {
																																						if((var157 == server)) {
																																							if((var167 == traceTempVariable$currentState$230_1)) {
																																								{
																																									{
																																										double cv$temp$70$var254;
																																										{
																																											double var254 = current_metric_mean[server][traceTempVariable$currentState$230_1];
																																											cv$temp$70$var254 = var254;
																																										}
																																										double cv$temp$71$var256;
																																										{
																																											double var256 = current_metric_var[server][traceTempVariable$currentState$230_1];
																																											cv$temp$71$var256 = var256;
																																										}
																																										if(((Math.log(cv$probabilitySample90Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$70$var254) / Math.sqrt(cv$temp$71$var256))) - (0.5 * Math.log(cv$temp$71$var256)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample90Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$70$var254) / Math.sqrt(cv$temp$71$var256))) - (0.5 * Math.log(cv$temp$71$var256)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample90Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$70$var254) / Math.sqrt(cv$temp$71$var256))) - (0.5 * Math.log(cv$temp$71$var256))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample90Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$70$var254) / Math.sqrt(cv$temp$71$var256))) - (0.5 * Math.log(cv$temp$71$var256)))))) + 1)) + (Math.log(cv$probabilitySample90Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - cv$temp$70$var254) / Math.sqrt(cv$temp$71$var256))) - (0.5 * Math.log(cv$temp$71$var256)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample90Value228);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
					int traceTempVariable$var81$269_1 = cv$currentValue;
					for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
						if((sample$var53 == index$sample$269_2)) {
							for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2][0]; index$timeStep$269_3 += 1) {
								if((timeStep$var76 == (index$timeStep$269_3 - 1))) {
									{
										int index$timeStep$271 = index$timeStep$269_3;
										int index$sample$272 = index$sample$269_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var83;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var40 = 0; var40 < noStates; var40 += 1) {
											if((var40 == traceTempVariable$var81$269_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample71) {
														for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
															if((index$sample$274_1 == sample$var53)) {
																if((0 == (timeStep$var76 - 1))) {
																	for(int index$var40$280_1 = 0; index$var40$280_1 < noStates; index$var40$280_1 += 1) {
																		if((index$var40$280_1 == st[sample$var53][(timeStep$var76 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
															if(true) {
																for(int index$sample71$276 = 0; index$sample71$276 < noStates; index$sample71$276 += 1) {
																	int distributionTempVariable$var65$278 = index$sample71$276;
																	double cv$probabilitySample71Value277 = (1.0 * distribution$sample71[((index$sample$275 - 0) / 1)][index$sample71$276]);
																	int traceTempVariable$var81$279_1 = distributionTempVariable$var65$278;
																	if((index$sample$275 == sample$var53)) {
																		if((0 == (timeStep$var76 - 1))) {
																			for(int index$var40$281_1 = 0; index$var40$281_1 < noStates; index$var40$281_1 += 1) {
																				if((index$var40$281_1 == traceTempVariable$var81$279_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample71Value277);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var81$282_1 = cv$currentValue;
													if((index$sample$23 == sample$var53)) {
														if((index$timeStep$22 == (timeStep$var76 - 1))) {
															for(int index$var40$289_1 = 0; index$var40$289_1 < noStates; index$var40$289_1 += 1) {
																if((index$var40$289_1 == traceTempVariable$var81$282_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
														for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283][0]; index$timeStep$284 += 1) {
															if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																for(int index$sample90$285 = 0; index$sample90$285 < noStates; index$sample90$285 += 1) {
																	int distributionTempVariable$var84$287 = index$sample90$285;
																	double cv$probabilitySample90Value286 = (1.0 * distribution$sample90[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample90$285]);
																	int traceTempVariable$var81$288_1 = distributionTempVariable$var84$287;
																	if((index$sample$283 == sample$var53)) {
																		if((index$timeStep$284 == (timeStep$var76 - 1))) {
																			for(int index$var40$290_1 = 0; index$var40$290_1 < noStates; index$var40$290_1 += 1) {
																				if((index$var40$290_1 == traceTempVariable$var81$288_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample90Value286);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var82;
													{
														double[] var82 = m[traceTempVariable$var81$269_1];
														cv$temp$72$var82 = var82;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var82);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample90[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)];
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
			cv$var28$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var40 = 0; var40 < noStates; var40 += 1)
				cv$max = Math.max(cv$max, noStates);
			cv$var41$countGlobal = new double[cv$max];
		}
		{
			int cv$var42$max = noStates;
			cv$distributionAccumulator$var83 = new double[cv$var42$max];
		}
		{
			cv$var65$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_sample$var207 = 0;
			int cv$max_server = 0;
			int cv$max_timeStep$var239 = 0;
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var239 = Math.max(cv$max_timeStep$var239, ((length$metric[sample$var207][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var207 = Math.max(cv$max_sample$var207, ((length$metric.length - 0) / 1));
			guard$sample71gaussian274$global = new boolean[cv$max_sample$var207][cv$max_server][cv$max_timeStep$var239];
		}
		{
			int cv$var42$max = noStates;
			cv$var84$stateProbabilityGlobal = new double[cv$var42$max];
		}
		{
			int cv$max_sample$var207 = 0;
			int cv$max_server = 0;
			int cv$max_timeStep$var239 = 0;
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var239 = Math.max(cv$max_timeStep$var239, ((length$metric[sample$var207][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var207 = Math.max(cv$max_sample$var207, ((length$metric.length - 0) / 1));
			guard$sample90gaussian274$global = new boolean[cv$max_sample$var207][cv$max_server][cv$max_timeStep$var239];
		}
		{
			cv$var245$stateProbabilityGlobal = new double[2];
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
				for(int var40 = 0; var40 < noStates; var40 += 1)
					m[var40] = new double[noStates];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$metric.length][];
				for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
					st[sample$var53] = new int[length$metric[sample$var53][0]];
			}
		}
		if(!setFlag$metric_g) {
			{
				metric_g = new double[length$metric.length][][];
				for(int var101 = 0; var101 < length$metric.length; var101 += 1) {
					double[][] subarray$0 = new double[length$metric[0].length][];
					metric_g[var101] = subarray$0;
				}
				for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						double[][] subarray$1 = metric_g[sample$var207];
						subarray$1[server] = new double[length$metric[sample$var207][0]];
					}
				}
			}
		}
		if(!setFlag$metric_valid_g) {
			{
				metric_valid_g = new boolean[length$metric.length][][];
				for(int var114 = 0; var114 < length$metric.length; var114 += 1) {
					boolean[][] subarray$0 = new boolean[length$metric[0].length][];
					metric_valid_g[var114] = subarray$0;
				}
				for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						boolean[][] subarray$1 = metric_valid_g[sample$var207];
						subarray$1[server] = new boolean[length$metric[sample$var207][0]];
					}
				}
			}
		}
		if(!setFlag$current_metric_mean) {
			{
				current_metric_mean = new double[length$metric[0].length][];
				for(int var130 = 0; var130 < length$metric[0].length; var130 += 1)
					current_metric_mean[var130] = new double[noStates];
			}
		}
		if(!setFlag$current_metric_var) {
			{
				current_metric_var = new double[length$metric[0].length][];
				for(int var157 = 0; var157 < length$metric[0].length; var157 += 1)
					current_metric_var[var157] = new double[noStates];
			}
		}
		if(!setFlag$current_metric_valid_bias) {
			{
				current_metric_valid_bias = new double[length$metric[0].length][];
				for(int var184 = 0; var184 < length$metric[0].length; var184 += 1)
					current_metric_valid_bias[var184] = new double[noStates];
			}
		}
		{
			distribution$sample71 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
				distribution$sample71[((sample$var53 - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample90 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample$var53][0] - 1) - 1) / 1) + 1)][];
				distribution$sample90[((sample$var53 - 0) / 1)] = subarray$0;
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					subarray$0[((timeStep$var76 - 1) / 1)] = new double[noStates];
			}
		}
		{
			logProbability$var64 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample71 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var83 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
				logProbability$var83[((sample$var53 - 0) / 1)] = new double[((((length$metric[sample$var53][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample90 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
				logProbability$sample90[((sample$var53 - 0) / 1)] = new double[((((length$metric[sample$var53][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var244 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var244[((sample$var207 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var207][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample260 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample260[((sample$var207 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var207][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var257 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var257[((sample$var207 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var207][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample275 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample275[((sample$var207 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var207][0] - 1) - 0) / 1) + 1)];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var40 = 0; var40 < noStates; var40 += 1) {
			double[] var41 = m[var40];
			if(!fixedFlag$sample44)
				DistributionSampling.sampleDirichlet(RNG$, v, var41);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			int[] var62 = st[sample$var53];
			if(!fixedFlag$sample71)
				var62[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var77 = st[sample$var53];
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
				if(!fixedFlag$sample90)
					var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var53][(timeStep$var76 - 1)]]);
			}
		}
		for(int var130 = 0; var130 < noServers; var130 += 1) {
			double[] var131 = current_metric_mean[var130];
			for(int var140 = 0; var140 < noStates; var140 += 1) {
				if(!fixedFlag$sample150)
					var131[var140] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var157 = 0; var157 < noServers; var157 += 1) {
			double[] var158 = current_metric_var[var157];
			for(int var167 = 0; var167 < noStates; var167 += 1) {
				if(!fixedFlag$sample178)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var184 = 0; var184 < noServers; var184 += 1) {
			double[] var185 = current_metric_valid_bias[var184];
			for(int var194 = 0; var194 < noStates; var194 += 1) {
				if(!fixedFlag$sample206)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			boolean[][] var228 = metric_valid_g[sample$var207];
			double[][] var224 = metric_g[sample$var207];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
				double[] metric_inner = var224[server];
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					if(!fixedFlag$sample260)
						metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
					if(var228[server][timeStep$var239]) {
						if(!(fixedFlag$sample260 && fixedFlag$sample275))
							metric_inner[timeStep$var239] = ((Math.sqrt(current_metric_var[server][st[sample$var207][timeStep$var239]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var207][timeStep$var239]]);
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var40 = 0; var40 < noStates; var40 += 1) {
			double[] var41 = m[var40];
			if(!fixedFlag$sample44)
				DistributionSampling.sampleDirichlet(RNG$, v, var41);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			double[] cv$distribution$sample71 = distribution$sample71[((sample$var53 - 0) / 1)];
			for(int index$var64 = 0; index$var64 < noStates; index$var64 += 1) {
				double cv$value = (((0.0 <= index$var64) && (index$var64 < initialStateDistribution.length))?initialStateDistribution[index$var64]:0.0);
				if(!fixedFlag$sample71)
					cv$distribution$sample71[index$var64] = cv$value;
			}
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
				double[] cv$distribution$sample90 = distribution$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)];
				for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1) {
					if(!fixedFlag$sample90)
						cv$distribution$sample90[index$var83] = 0.0;
				}
				if(fixedFlag$sample71) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample$var53)) {
							if((0 == (timeStep$var76 - 1))) {
								for(int var40 = 0; var40 < noStates; var40 += 1) {
									if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
										{
											if(!fixedFlag$sample90) {
												double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
												for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
													cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (1.0 * (((0.0 <= index$var83) && (index$var83 < var82.length))?var82[index$var83]:0.0)));
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
							for(int index$sample71$3 = 0; index$sample71$3 < noStates; index$sample71$3 += 1) {
								int distributionTempVariable$var65$5 = index$sample71$3;
								double cv$probabilitySample71Value4 = (1.0 * distribution$sample71[((index$sample$2 - 0) / 1)][index$sample71$3]);
								int traceTempVariable$var81$6_1 = distributionTempVariable$var65$5;
								if((index$sample$2 == sample$var53)) {
									if((0 == (timeStep$var76 - 1))) {
										for(int var40 = 0; var40 < noStates; var40 += 1) {
											if((var40 == traceTempVariable$var81$6_1)) {
												{
													if(!fixedFlag$sample90) {
														double[] var82 = m[traceTempVariable$var81$6_1];
														for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
															cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (cv$probabilitySample71Value4 * (((0.0 <= index$var83) && (index$var83 < var82.length))?var82[index$var83]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample90) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample$var53)) {
								if((index$timeStep$9_2 == (timeStep$var76 - 1))) {
									for(int var40 = 0; var40 < noStates; var40 += 1) {
										if((var40 == st[sample$var53][(timeStep$var76 - 1)])) {
											{
												if(!fixedFlag$sample90) {
													double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
													for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
														cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (1.0 * (((0.0 <= index$var83) && (index$var83 < var82.length))?var82[index$var83]:0.0)));
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
								for(int index$sample90$12 = 0; index$sample90$12 < noStates; index$sample90$12 += 1) {
									int distributionTempVariable$var84$14 = index$sample90$12;
									double cv$probabilitySample90Value13 = (1.0 * distribution$sample90[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample90$12]);
									int traceTempVariable$var81$15_1 = distributionTempVariable$var84$14;
									if((index$sample$10 == sample$var53)) {
										if((index$timeStep$11 == (timeStep$var76 - 1))) {
											for(int var40 = 0; var40 < noStates; var40 += 1) {
												if((var40 == traceTempVariable$var81$15_1)) {
													{
														if(!fixedFlag$sample90) {
															double[] var82 = m[traceTempVariable$var81$15_1];
															for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
																cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (cv$probabilitySample90Value13 * (((0.0 <= index$var83) && (index$var83 < var82.length))?var82[index$var83]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				double cv$var83$sum = 0.0;
				for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1) {
					if(!fixedFlag$sample90)
						cv$var83$sum = (cv$var83$sum + cv$distribution$sample90[index$var83]);
				}
				for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1) {
					if(!fixedFlag$sample90)
						cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] / cv$var83$sum);
				}
			}
		}
		for(int var130 = 0; var130 < noServers; var130 += 1) {
			double[] var131 = current_metric_mean[var130];
			for(int var140 = 0; var140 < noStates; var140 += 1) {
				if(!fixedFlag$sample150)
					var131[var140] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var157 = 0; var157 < noServers; var157 += 1) {
			double[] var158 = current_metric_var[var157];
			for(int var167 = 0; var167 < noStates; var167 += 1) {
				if(!fixedFlag$sample178)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var184 = 0; var184 < noServers; var184 += 1) {
			double[] var185 = current_metric_valid_bias[var184];
			for(int var194 = 0; var194 < noStates; var194 += 1) {
				if(!fixedFlag$sample206)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					if(!fixedFlag$sample260)
						metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var40 = 0; var40 < noStates; var40 += 1) {
			double[] var41 = m[var40];
			if(!fixedFlag$sample44)
				DistributionSampling.sampleDirichlet(RNG$, v, var41);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			int[] var62 = st[sample$var53];
			if(!fixedFlag$sample71)
				var62[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var77 = st[sample$var53];
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
				if(!fixedFlag$sample90)
					var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var53][(timeStep$var76 - 1)]]);
			}
		}
		for(int var130 = 0; var130 < noServers; var130 += 1) {
			double[] var131 = current_metric_mean[var130];
			for(int var140 = 0; var140 < noStates; var140 += 1) {
				if(!fixedFlag$sample150)
					var131[var140] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var157 = 0; var157 < noServers; var157 += 1) {
			double[] var158 = current_metric_var[var157];
			for(int var167 = 0; var167 < noStates; var167 += 1) {
				if(!fixedFlag$sample178)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var184 = 0; var184 < noServers; var184 += 1) {
			double[] var185 = current_metric_valid_bias[var184];
			for(int var194 = 0; var194 < noStates; var194 += 1) {
				if(!fixedFlag$sample206)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					if(!fixedFlag$sample260)
						metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				sample31();
			for(int var40 = 0; var40 < noStates; var40 += 1) {
				if(!fixedFlag$sample44)
					sample44(var40);
			}
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				if(!fixedFlag$sample71)
					sample71(sample$var53);
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					if(!fixedFlag$sample90)
						sample90(sample$var53, timeStep$var76);
				}
			}
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				for(int var140 = 0; var140 < noStates; var140 += 1) {
					if(!fixedFlag$sample150)
						sample150(var130, var140);
				}
			}
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				for(int var167 = 0; var167 < noStates; var167 += 1) {
					if(!fixedFlag$sample178)
						sample178(var157, var167);
				}
			}
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				for(int var194 = 0; var194 < noStates; var194 += 1) {
					if(!fixedFlag$sample206)
						sample206(var184, var194);
				}
			}
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						if(!fixedFlag$sample260)
							sample260(sample$var207, server, timeStep$var239);
					}
				}
			}
		} else {
			for(int sample$var207 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var207 >= ((0 - 1) + 1); sample$var207 -= 1) {
				for(int server = (noServers - ((((noServers - 1) - 0) % 1) + 1)); server >= ((0 - 1) + 1); server -= 1) {
					for(int timeStep$var239 = (length$metric[sample$var207][0] - ((((length$metric[sample$var207][0] - 1) - 0) % 1) + 1)); timeStep$var239 >= ((0 - 1) + 1); timeStep$var239 -= 1) {
						if(!fixedFlag$sample260)
							sample260(sample$var207, server, timeStep$var239);
					}
				}
			}
			for(int var184 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var184 >= ((0 - 1) + 1); var184 -= 1) {
				for(int var194 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var194 >= ((0 - 1) + 1); var194 -= 1) {
					if(!fixedFlag$sample206)
						sample206(var184, var194);
				}
			}
			for(int var157 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var157 >= ((0 - 1) + 1); var157 -= 1) {
				for(int var167 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var167 >= ((0 - 1) + 1); var167 -= 1) {
					if(!fixedFlag$sample178)
						sample178(var157, var167);
				}
			}
			for(int var130 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var130 >= ((0 - 1) + 1); var130 -= 1) {
				for(int var140 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var140 >= ((0 - 1) + 1); var140 -= 1) {
					if(!fixedFlag$sample150)
						sample150(var130, var140);
				}
			}
			for(int sample$var53 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var53 >= ((0 - 1) + 1); sample$var53 -= 1) {
				for(int timeStep$var76 = (length$metric[sample$var53][0] - ((((length$metric[sample$var53][0] - 1) - 1) % 1) + 1)); timeStep$var76 >= ((1 - 1) + 1); timeStep$var76 -= 1) {
					if(!fixedFlag$sample90)
						sample90(sample$var53, timeStep$var76);
				}
				if(!fixedFlag$sample71)
					sample71(sample$var53);
			}
			for(int var40 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var40 >= ((0 - 1) + 1); var40 -= 1) {
				if(!fixedFlag$sample44)
					sample44(var40);
			}
			if(!fixedFlag$sample31)
				sample31();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var24 = 0; var24 < noStates; var24 += 1)
			v[var24] = 0.1;
		noServers = length$metric[0].length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var27 = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var29 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample44)
			logProbability$var41 = 0.0;
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
			logProbability$var64[((sample$var53 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
				logProbability$sample71[((sample$var53 - 0) / 1)] = 0.0;
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
				logProbability$var83[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample90) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					logProbability$sample90[((sample$var53 - 0) / 1)][((timeStep$var76 - 1) / 1)] = 0.0;
			}
		}
		logProbability$var119 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample150)
			logProbability$var141 = 0.0;
		logProbability$var146 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample178)
			logProbability$var168 = 0.0;
		logProbability$var173 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample206)
			logProbability$var195 = 0.0;
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
					logProbability$var244[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample260) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						logProbability$sample260[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = 0.0;
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
					logProbability$var257[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = 0.0;
			}
		}
		if(!fixedProbFlag$sample275) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						logProbability$sample275[((sample$var207 - 0) / 1)][((server - 0) / 1)][((timeStep$var239 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample44)
			logProbabilityValue$sample44();
		if(fixedFlag$sample150)
			logProbabilityValue$sample150();
		if(fixedFlag$sample178)
			logProbabilityValue$sample178();
		if(fixedFlag$sample206)
			logProbabilityValue$sample206();
		logProbabilityValue$sample260();
		logProbabilityValue$sample275();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample44();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample90();
		logProbabilityValue$sample150();
		logProbabilityValue$sample178();
		logProbabilityValue$sample206();
		logProbabilityDistribution$sample260();
		logProbabilityDistribution$sample275();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample44();
		logProbabilityValue$sample71();
		logProbabilityValue$sample90();
		logProbabilityValue$sample150();
		logProbabilityValue$sample178();
		logProbabilityValue$sample206();
		logProbabilityValue$sample260();
		logProbabilityValue$sample275();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var40 = 0; var40 < noStates; var40 += 1) {
			double[] var41 = m[var40];
			if(!fixedFlag$sample44)
				DistributionSampling.sampleDirichlet(RNG$, v, var41);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			int[] var62 = st[sample$var53];
			if(!fixedFlag$sample71)
				var62[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var77 = st[sample$var53];
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
				if(!fixedFlag$sample90)
					var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var53][(timeStep$var76 - 1)]]);
			}
		}
		for(int var130 = 0; var130 < noServers; var130 += 1) {
			double[] var131 = current_metric_mean[var130];
			for(int var140 = 0; var140 < noStates; var140 += 1) {
				if(!fixedFlag$sample150)
					var131[var140] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var157 = 0; var157 < noServers; var157 += 1) {
			double[] var158 = current_metric_var[var157];
			for(int var167 = 0; var167 < noStates; var167 += 1) {
				if(!fixedFlag$sample178)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var184 = 0; var184 < noServers; var184 += 1) {
			double[] var185 = current_metric_valid_bias[var184];
			for(int var194 = 0; var194 < noStates; var194 += 1) {
				if(!fixedFlag$sample206)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
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
		     + "model HMMMetrics4(\n"
		     + "               double[][][] metric,\n"
		     + "               boolean[][][] metric_valid, \n"
		     + "               int max_metric,\n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "    \n"
		     + "    //Calculate all the state transitions\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
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
		     + "    }\n"
		     + "    \n"
		     + "    // Calculate the number of servers\n"
		     + "    int noServers = metric[0].length;    \n"
		     + "    \n"
		     + "    // Allocate space for each generated metric.    \n"
		     + "    double[][][] metric_g = new double[noSamples][noServers][];\n"
		     + "    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n"
		     + "\n"
		     + "    // Calculate metric parameters\n"
		     + "    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        int streamLength = metric[sample][0].length;\n"
		     + "        for(int server = 0; server < noServers; server++) {\n"
		     + "            //Allocate space for the time series\n"
		     + "            double[] metric_inner = new double[streamLength];\n"
		     + "            metric_g[sample][server] = metric_inner;\n"
		     + "            \n"
		     + "            boolean[] metric_valid_inner = new boolean[streamLength];\n"
		     + "            metric_valid_g[sample][server] = metric_valid_inner;\n"
		     + "            \n"
		     + "            //Generate values.\n"
		     + "            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "                int currentState = st[sample][timeStep];\n"
		     + "                \n"
		     + "                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n"
		     + "                if(metric_valid_inner[timeStep])\n"
		     + "                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n"
		     + "            }\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "    metric_g.observe(metric);\n"
		     + "}";
	}
}