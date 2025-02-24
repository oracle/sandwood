package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics4$CoreInterface {
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[] cv$distributionAccumulator$var73;
	private double[] cv$var20$countGlobal;
	private double[] cv$var232$stateProbabilityGlobal;
	private double[] cv$var33$countGlobal;
	private double[] cv$var55$stateProbabilityGlobal;
	private double[] cv$var74$stateProbabilityGlobal;
	private double[][] distribution$sample57;
	private double[][][] distribution$sample76;
	private boolean fixedFlag$sample134 = false;
	private boolean fixedFlag$sample162 = false;
	private boolean fixedFlag$sample190 = false;
	private boolean fixedFlag$sample20 = false;
	private boolean fixedFlag$sample241 = false;
	private boolean fixedFlag$sample256 = false;
	private boolean fixedFlag$sample33 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample76 = false;
	private boolean fixedProbFlag$sample134 = false;
	private boolean fixedProbFlag$sample162 = false;
	private boolean fixedProbFlag$sample190 = false;
	private boolean fixedProbFlag$sample20 = false;
	private boolean fixedProbFlag$sample241 = false;
	private boolean fixedProbFlag$sample256 = false;
	private boolean fixedProbFlag$sample33 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample76 = false;
	private boolean[][][] guard$sample57gaussian255$global;
	private boolean[][][] guard$sample76gaussian255$global;
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
	private double[][][] logProbability$sample241;
	private double[][][] logProbability$sample256;
	private double[] logProbability$sample57;
	private double[][] logProbability$sample76;
	private double logProbability$st;
	private double logProbability$var108;
	private double logProbability$var130;
	private double logProbability$var135;
	private double logProbability$var157;
	private double logProbability$var162;
	private double logProbability$var184;
	private double logProbability$var19;
	private double logProbability$var21;
	private double[][][] logProbability$var231;
	private double[][][] logProbability$var244;
	private double logProbability$var33;
	private double[] logProbability$var54;
	private double[][] logProbability$var73;
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
		fixedProbFlag$sample134 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final double[][] get$current_metric_valid_bias() {
		return current_metric_valid_bias;
	}

	@Override
	public final void set$current_metric_valid_bias(double[][] cv$value) {
		current_metric_valid_bias = cv$value;
		setFlag$current_metric_valid_bias = true;
		fixedProbFlag$sample190 = false;
		fixedProbFlag$sample241 = false;
	}

	@Override
	public final double[][] get$current_metric_var() {
		return current_metric_var;
	}

	@Override
	public final void set$current_metric_var(double[][] cv$value) {
		current_metric_var = cv$value;
		setFlag$current_metric_var = true;
		fixedProbFlag$sample162 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample134() {
		return fixedFlag$sample134;
	}

	@Override
	public final void set$fixedFlag$sample134(boolean cv$value) {
		fixedFlag$sample134 = cv$value;
		fixedProbFlag$sample134 = (fixedFlag$sample134 && fixedProbFlag$sample134);
		fixedProbFlag$sample256 = (fixedFlag$sample134 && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample162() {
		return fixedFlag$sample162;
	}

	@Override
	public final void set$fixedFlag$sample162(boolean cv$value) {
		fixedFlag$sample162 = cv$value;
		fixedProbFlag$sample162 = (fixedFlag$sample162 && fixedProbFlag$sample162);
		fixedProbFlag$sample256 = (fixedFlag$sample162 && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	@Override
	public final void set$fixedFlag$sample190(boolean cv$value) {
		fixedFlag$sample190 = cv$value;
		fixedProbFlag$sample190 = (fixedFlag$sample190 && fixedProbFlag$sample190);
		fixedProbFlag$sample241 = (fixedFlag$sample190 && fixedProbFlag$sample241);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (fixedFlag$sample20 && fixedProbFlag$sample20);
		fixedProbFlag$sample57 = (fixedFlag$sample20 && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample241() {
		return fixedFlag$sample241;
	}

	@Override
	public final void set$fixedFlag$sample241(boolean cv$value) {
		fixedFlag$sample241 = cv$value;
		fixedProbFlag$sample241 = (fixedFlag$sample241 && fixedProbFlag$sample241);
	}

	@Override
	public final boolean get$fixedFlag$sample256() {
		return fixedFlag$sample256;
	}

	@Override
	public final void set$fixedFlag$sample256(boolean cv$value) {
		fixedFlag$sample256 = cv$value;
		fixedProbFlag$sample256 = (fixedFlag$sample256 && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
		fixedProbFlag$sample33 = (fixedFlag$sample33 && fixedProbFlag$sample33);
		fixedProbFlag$sample76 = (fixedFlag$sample33 && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		fixedProbFlag$sample76 = (fixedFlag$sample57 && fixedProbFlag$sample76);
		fixedProbFlag$sample241 = (fixedFlag$sample57 && fixedProbFlag$sample241);
		fixedProbFlag$sample256 = (fixedFlag$sample57 && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (fixedFlag$sample76 && fixedProbFlag$sample76);
		fixedProbFlag$sample241 = (fixedFlag$sample76 && fixedProbFlag$sample241);
		fixedProbFlag$sample256 = (fixedFlag$sample76 && fixedProbFlag$sample256);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample20 = false;
		fixedProbFlag$sample57 = false;
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
		fixedProbFlag$sample33 = false;
		fixedProbFlag$sample76 = false;
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
		fixedProbFlag$sample256 = false;
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
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample76 = false;
		fixedProbFlag$sample241 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample241() {
		if(!fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
							if(fixedFlag$sample57) {
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									if((sample$var45 == sample$var196)) {
										if((0 == timeStep$var226)) {
											for(int var173 = 0; var173 < noServers; var173 += 1) {
												for(int var183 = 0; var183 < noStates; var183 += 1) {
													if((var173 == server)) {
														if((var183 == st[sample$var196][timeStep$var226])) {
															{
																double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var230));
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
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									if(true) {
										for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
											int distributionTempVariable$var55$6 = index$sample57$4;
											double cv$probabilitySample57Value5 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													for(int var173 = 0; var173 < noServers; var173 += 1) {
														for(int var183 = 0; var183 < noStates; var183 += 1) {
															if((var173 == server)) {
																if((var183 == st[sample$var196][timeStep$var226])) {
																	{
																		double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																		double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var230));
																		if((cv$weightedProbability < cv$distributionAccumulator))
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																		else {
																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																				cv$distributionAccumulator = cv$weightedProbability;
																			else
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																		}
																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if(fixedFlag$sample76) {
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
										if((sample$var45 == sample$var196)) {
											if((timeStep$var66 == timeStep$var226)) {
												for(int var173 = 0; var173 < noServers; var173 += 1) {
													for(int var183 = 0; var183 < noStates; var183 += 1) {
														if((var173 == server)) {
															if((var183 == st[sample$var196][timeStep$var226])) {
																{
																	double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																	double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var230));
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
								for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
									for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
										if(true) {
											for(int index$sample76$13 = 0; index$sample76$13 < noStates; index$sample76$13 += 1) {
												int distributionTempVariable$var74$15 = index$sample76$13;
												double cv$probabilitySample76Value14 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$13]);
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														for(int var173 = 0; var173 < noServers; var173 += 1) {
															for(int var183 = 0; var183 < noStates; var183 += 1) {
																if((var173 == server)) {
																	if((var183 == st[sample$var196][timeStep$var226])) {
																		{
																			double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																			double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var230));
																			if((cv$weightedProbability < cv$distributionAccumulator))
																				cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																			else {
																				if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																					cv$distributionAccumulator = cv$weightedProbability;
																				else
																					cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																			}
																			cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value14);
																		}
																	}
																}
															}
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
						logProbability$var231[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
						{
							for(int index$timeStep$24_1 = 0; index$timeStep$24_1 < length$metric[sample$var196][0]; index$timeStep$24_1 += 1) {
								if((timeStep$var226 == index$timeStep$24_1)) {
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
			fixedProbFlag$sample241 = (((fixedFlag$sample241 && fixedFlag$sample57) && fixedFlag$sample76) && fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var231[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
						{
							for(int index$timeStep$26_1 = 0; index$timeStep$26_1 < length$metric[sample$var196][0]; index$timeStep$26_1 += 1) {
								if((timeStep$var226 == index$timeStep$26_1)) {
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

	private final void logProbabilityDistribution$sample256() {
		if(!fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								double cv$sampleValue = metric_g[sample$var196][server][timeStep$var226];
								if(fixedFlag$sample57) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if((sample$var45 == sample$var196)) {
											if((0 == timeStep$var226)) {
												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
													for(int var119 = 0; var119 < noServers; var119 += 1) {
														for(int var129 = 0; var129 < noStates; var129 += 1) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																if((var119 == server)) {
																	if((var129 == st[sample$var196][timeStep$var226])) {
																		for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																			if((index$sample$10_1 == sample$var196)) {
																				if((0 == timeStep$var226)) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						for(int var146 = 0; var146 < noServers; var146 += 1) {
																							for(int var156 = 0; var156 < noStates; var156 += 1) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									if((var146 == server)) {
																										if((var156 == st[sample$var196][timeStep$var226])) {
																											{
																												double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																												double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																												double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if(true) {
											for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
												int distributionTempVariable$var55$6 = index$sample57$4;
												double cv$probabilitySample57Value5 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															for(int var119 = 0; var119 < noServers; var119 += 1) {
																for(int var129 = 0; var129 < noStates; var129 += 1) {
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		if((var119 == server)) {
																			if((var129 == st[sample$var196][timeStep$var226])) {
																				if((sample$var45 == sample$var196)) {
																					if((0 == timeStep$var226)) {
																						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																							for(int var146 = 0; var146 < noServers; var146 += 1) {
																								for(int var156 = 0; var156 < noStates; var156 += 1) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										if((var146 == server)) {
																											if((var156 == st[sample$var196][timeStep$var226])) {
																												{
																													double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																													double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																													double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																													if((cv$weightedProbability < cv$distributionAccumulator))
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																													else {
																														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																															cv$distributionAccumulator = cv$weightedProbability;
																														else
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																													}
																													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
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
																					if(!(index$sample$12 == sample$var45)) {
																						for(int index$sample57$13 = 0; index$sample57$13 < noStates; index$sample57$13 += 1) {
																							int distributionTempVariable$var55$15 = index$sample57$13;
																							double cv$probabilitySample57Value14 = (cv$probabilitySample57Value5 * distribution$sample57[((index$sample$12 - 0) / 1)][index$sample57$13]);
																							if((index$sample$12 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value14);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample57) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if((sample$var45 == sample$var196)) {
											if((0 == timeStep$var226)) {
												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
													for(int var119 = 0; var119 < noServers; var119 += 1) {
														for(int var129 = 0; var129 < noStates; var129 += 1) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																if((var119 == server)) {
																	if((var129 == st[sample$var196][timeStep$var226])) {
																		if(fixedFlag$sample76) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$28_1][0]; timeStep$var66 += 1) {
																					if((index$sample$28_1 == sample$var196)) {
																						if((timeStep$var66 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
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
																				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$29][0]; timeStep$var66 += 1) {
																					if(true) {
																						for(int index$sample76$31 = 0; index$sample76$31 < noStates; index$sample76$31 += 1) {
																							int distributionTempVariable$var74$33 = index$sample76$31;
																							double cv$probabilitySample76Value32 = (1.0 * distribution$sample76[((index$sample$29 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$31]);
																							if((index$sample$29 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample76Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value32);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if(true) {
											for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
												int distributionTempVariable$var55$24 = index$sample57$22;
												double cv$probabilitySample57Value23 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$22]);
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															for(int var119 = 0; var119 < noServers; var119 += 1) {
																for(int var129 = 0; var129 < noStates; var129 += 1) {
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		if((var119 == server)) {
																			if((var129 == st[sample$var196][timeStep$var226])) {
																				if(fixedFlag$sample76) {
																					for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$35_1][0]; timeStep$var66 += 1) {
																							if((index$sample$35_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value23);
																															}
																														}
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
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$36][0]; timeStep$var66 += 1) {
																							if(true) {
																								for(int index$sample76$38 = 0; index$sample76$38 < noStates; index$sample76$38 += 1) {
																									int distributionTempVariable$var74$40 = index$sample76$38;
																									double cv$probabilitySample76Value39 = (cv$probabilitySample57Value23 * distribution$sample76[((index$sample$36 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$38]);
																									if((index$sample$36 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample76Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value39);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample76) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if((sample$var45 == sample$var196)) {
												if((timeStep$var66 == timeStep$var226)) {
													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
														for(int var119 = 0; var119 < noServers; var119 += 1) {
															for(int var129 = 0; var129 < noStates; var129 += 1) {
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	if((var119 == server)) {
																		if((var129 == st[sample$var196][timeStep$var226])) {
																			for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																				for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1][0]; index$timeStep$55_2 += 1) {
																					if((index$sample$55_1 == sample$var196)) {
																						if((index$timeStep$55_2 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												for(int index$sample76$49 = 0; index$sample76$49 < noStates; index$sample76$49 += 1) {
													int distributionTempVariable$var74$51 = index$sample76$49;
													double cv$probabilitySample76Value50 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$49]);
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																for(int var119 = 0; var119 < noServers; var119 += 1) {
																	for(int var129 = 0; var129 < noStates; var129 += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if((var119 == server)) {
																				if((var129 == st[sample$var196][timeStep$var226])) {
																					if((sample$var45 == sample$var196)) {
																						if((timeStep$var66 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample76Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value50);
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
																							if(!((index$sample$57 == sample$var45) && (index$timeStep$58 == timeStep$var66))) {
																								for(int index$sample76$59 = 0; index$sample76$59 < noStates; index$sample76$59 += 1) {
																									int distributionTempVariable$var74$61 = index$sample76$59;
																									double cv$probabilitySample76Value60 = (cv$probabilitySample76Value50 * distribution$sample76[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample76$59]);
																									if((index$sample$57 == sample$var196)) {
																										if((index$timeStep$58 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample76Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value60);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample76) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if((sample$var45 == sample$var196)) {
												if((timeStep$var66 == timeStep$var226)) {
													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
														for(int var119 = 0; var119 < noServers; var119 += 1) {
															for(int var129 = 0; var129 < noStates; var129 += 1) {
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	if((var119 == server)) {
																		if((var129 == st[sample$var196][timeStep$var226])) {
																			if(fixedFlag$sample57) {
																				for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																					if((index$sample$75_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																														double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																														double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
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
																						for(int index$sample57$77 = 0; index$sample57$77 < noStates; index$sample57$77 += 1) {
																							int distributionTempVariable$var55$79 = index$sample57$77;
																							double cv$probabilitySample57Value78 = (1.0 * distribution$sample57[((index$sample$76 - 0) / 1)][index$sample57$77]);
																							if((index$sample$76 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value78);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												for(int index$sample76$69 = 0; index$sample76$69 < noStates; index$sample76$69 += 1) {
													int distributionTempVariable$var74$71 = index$sample76$69;
													double cv$probabilitySample76Value70 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$69]);
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																for(int var119 = 0; var119 < noServers; var119 += 1) {
																	for(int var129 = 0; var129 < noStates; var129 += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if((var119 == server)) {
																				if((var129 == st[sample$var196][timeStep$var226])) {
																					if(fixedFlag$sample57) {
																						for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																							if((index$sample$81_1 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																double cv$weightedProbability = (Math.log(cv$probabilitySample76Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																if((cv$weightedProbability < cv$distributionAccumulator))
																																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																else {
																																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																		cv$distributionAccumulator = cv$weightedProbability;
																																	else
																																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																}
																																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value70);
																															}
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
																								for(int index$sample57$83 = 0; index$sample57$83 < noStates; index$sample57$83 += 1) {
																									int distributionTempVariable$var55$85 = index$sample57$83;
																									double cv$probabilitySample57Value84 = (cv$probabilitySample76Value70 * distribution$sample57[((index$sample$82 - 0) / 1)][index$sample57$83]);
																									if((index$sample$82 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		double cv$weightedProbability = (Math.log(cv$probabilitySample57Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
																																		if((cv$weightedProbability < cv$distributionAccumulator))
																																			cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																																		else {
																																			if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																				cv$distributionAccumulator = cv$weightedProbability;
																																			else
																																				cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																																		}
																																		cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value84);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
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
							logProbability$var244[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleAccumulator;
							logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample256 = ((((fixedFlag$sample256 && fixedFlag$sample57) && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var244[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$rvAccumulator;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample57() {
		if(!fixedProbFlag$sample57) {
			if(fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample$var45;
					{
						int cv$sampleValue = st[sample$var45][0];
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
					logProbability$var54[((sample$var45 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample57[((sample$var45 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample57)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample57)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((sample$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((sample$var45 - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample57)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample76() {
		if(!fixedProbFlag$sample76) {
			if(fixedFlag$sample76) {
				double cv$accumulator = 0.0;
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var66;
						int index$sample$2 = sample$var45;
						{
							int cv$sampleValue = st[sample$var45][timeStep$var66];
							if(fixedFlag$sample57) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											for(int var32 = 0; var32 < noStates; var32 += 1) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													{
														double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
											int distributionTempVariable$var55$8 = index$sample57$6;
											double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[((index$sample$5 - 0) / 1)][index$sample57$6]);
											if((index$sample$5 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													for(int var32 = 0; var32 < noStates; var32 += 1) {
														if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
															{
																double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if((index$sample$2 == sample$var45)) {
								if((index$timeStep$1 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
											{
												double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample76) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var45)) {
											if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
												for(int var32 = 0; var32 < noStates; var32 += 1) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														{
															double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
											for(int index$sample76$16 = 0; index$sample76$16 < noStates; index$sample76$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample76$16;
												double cv$probabilitySample76Value17 = (1.0 * distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
												if((index$sample$14 == sample$var45)) {
													if((index$timeStep$15 == (timeStep$var66 - 1))) {
														for(int var32 = 0; var32 < noStates; var32 += 1) {
															if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
																{
																	double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample76Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample76Value17);
																}
															}
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
						logProbability$var73[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample76)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample76)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample33) && fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample76)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample134() {
		if(!fixedProbFlag$sample134) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_mean[var119][var129];
						{
							{
								double var106 = 0.0;
								double var107 = (double)max_metric;
								double cv$weightedProbability = (Math.log(1.0) + (((var106 <= cv$sampleValue) && (cv$sampleValue <= var107))?(-Math.log((var107 - var106))):Double.NEGATIVE_INFINITY));
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
			logProbability$var108 = cv$sampleAccumulator;
			logProbability$var130 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample134 = fixedFlag$sample134;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var130;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var108 = cv$rvAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample162() {
		if(!fixedProbFlag$sample162) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_var[var146][var156];
						{
							{
								double var133 = 1.0;
								double var134 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var133, var134));
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
			logProbability$var135 = cv$sampleAccumulator;
			logProbability$var157 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample162 = fixedFlag$sample162;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var157;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var135 = cv$rvAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						double cv$sampleValue = current_metric_valid_bias[var173][var183];
						{
							{
								double var160 = 1.0;
								double var161 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var160, var161));
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
			logProbability$var162 = cv$sampleAccumulator;
			logProbability$var184 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample190 = fixedFlag$sample190;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var184;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var162 = cv$rvAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
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
			logProbability$var19 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var19 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample241() {
		if(!fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
							{
								{
									double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var230));
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
						logProbability$var231[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
							}
						}
						{
							for(int index$timeStep$4_1 = 0; index$timeStep$4_1 < length$metric[sample$var196][0]; index$timeStep$4_1 += 1) {
								if((timeStep$var226 == index$timeStep$4_1)) {
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
			fixedProbFlag$sample241 = (((fixedFlag$sample241 && fixedFlag$sample57) && fixedFlag$sample76) && fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var231[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_valid_g = false;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_valid_g) {
								cv$guard$metric_valid_g = true;
								logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
							}
						}
						{
							for(int index$timeStep$6_1 = 0; index$timeStep$6_1 < length$metric[sample$var196][0]; index$timeStep$6_1 += 1) {
								if((timeStep$var226 == index$timeStep$6_1)) {
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

	private final void logProbabilityValue$sample256() {
		if(!fixedProbFlag$sample256) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$sampleAccumulator = 0.0;
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							{
								double cv$sampleValue = metric_g[sample$var196][server][timeStep$var226];
								{
									{
										double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var241) / Math.sqrt(var243))) - (0.5 * Math.log(var243))));
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
							logProbability$var244[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleAccumulator;
							logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$sampleProbability;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample256 = ((((fixedFlag$sample256 && fixedFlag$sample57) && fixedFlag$sample76) && fixedFlag$sample134) && fixedFlag$sample162);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(metric_valid_g[sample$var196][server][timeStep$var226]) {
							double cv$rvAccumulator = 0.0;
							double cv$sampleValue = logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)];
							cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var244[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = cv$rvAccumulator;
						}
					}
				}
			}
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample33() {
		if(!fixedProbFlag$sample33) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < noStates; var32 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var32];
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
			logProbability$var21 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample33 = fixedFlag$sample33;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var21 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample$var45;
				{
					int cv$sampleValue = st[sample$var45][0];
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
				logProbability$var54[((sample$var45 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample57[((sample$var45 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample57[((sample$var45 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[((sample$var45 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample76() {
		if(!fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var66;
					int index$sample$2 = sample$var45;
					{
						int cv$sampleValue = st[sample$var45][timeStep$var66];
						{
							{
								double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var73[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample76 = ((fixedFlag$sample76 && fixedFlag$sample33) && fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample134(int var119, int var129) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = current_metric_mean[var119][var129];
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
					double var130 = cv$proposedValue;
					double[] var120 = current_metric_mean[var119];
					var120[var129] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var106;
				{
					cv$temp$0$var106 = 0.0;
				}
				double cv$temp$1$var107;
				{
					cv$temp$1$var107 = max_metric;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var106 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var107))?(-Math.log((cv$temp$1$var107 - cv$temp$0$var106))):Double.NEGATIVE_INFINITY));
				{
					{
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(fixedFlag$sample57) {
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
														double traceTempVariable$var241$10_1 = cv$currentValue;
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															if((var119 == server)) {
																if((var129 == st[sample$var196][timeStep$var226])) {
																	{
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																					if((index$sample$28_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$2$var241;
																															{
																																double var241 = traceTempVariable$var241$10_1;
																																cv$temp$2$var241 = var241;
																															}
																															double cv$temp$3$var243;
																															{
																																double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																cv$temp$3$var243 = var243;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))));
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
																				if(fixedFlag$sample76) {
																					for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$30_1][0]; timeStep$var66 += 1) {
																							if((index$sample$30_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$4$var241;
																																	{
																																		double var241 = traceTempVariable$var241$10_1;
																																		cv$temp$4$var241 = var241;
																																	}
																																	double cv$temp$5$var243;
																																	{
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$5$var243 = var243;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))));
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
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$31][0]; timeStep$var66 += 1) {
																							if(true) {
																								for(int index$sample76$33 = 0; index$sample76$33 < noStates; index$sample76$33 += 1) {
																									int distributionTempVariable$var74$35 = index$sample76$33;
																									double cv$probabilitySample76Value34 = (1.0 * distribution$sample76[((index$sample$31 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$33]);
																									if((index$sample$31 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$6$var241;
																																			{
																																				double var241 = traceTempVariable$var241$10_1;
																																				cv$temp$6$var241 = var241;
																																			}
																																			double cv$temp$7$var243;
																																			{
																																				double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$7$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value34);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											if(true) {
												for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
													int distributionTempVariable$var55$8 = index$sample57$6;
													double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$6]);
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																double traceTempVariable$var241$11_1 = cv$currentValue;
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	if((var119 == server)) {
																		if((var129 == st[sample$var196][timeStep$var226])) {
																			{
																				if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if((sample$var45 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									for(int var146 = 0; var146 < noServers; var146 += 1) {
																										for(int var156 = 0; var156 < noStates; var156 += 1) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												if((var146 == server)) {
																													if((var156 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$8$var241;
																																{
																																	double var241 = traceTempVariable$var241$11_1;
																																	cv$temp$8$var241 = var241;
																																}
																																double cv$temp$9$var243;
																																{
																																	double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$9$var243 = var243;
																																}
																																if(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value7);
																															}
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
																							if(!(index$sample$40 == sample$var45)) {
																								for(int index$sample57$41 = 0; index$sample57$41 < noStates; index$sample57$41 += 1) {
																									int distributionTempVariable$var55$43 = index$sample57$41;
																									double cv$probabilitySample57Value42 = (cv$probabilitySample57Value7 * distribution$sample57[((index$sample$40 - 0) / 1)][index$sample57$41]);
																									if((index$sample$40 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$10$var241;
																																			{
																																				double var241 = traceTempVariable$var241$11_1;
																																				cv$temp$10$var241 = var241;
																																			}
																																			double cv$temp$11$var243;
																																			{
																																				double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$11$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value42);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						if(fixedFlag$sample76) {
																							for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$47_1][0]; timeStep$var66 += 1) {
																									if((index$sample$47_1 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$12$var241;
																																			{
																																				double var241 = traceTempVariable$var241$11_1;
																																				cv$temp$12$var241 = var241;
																																			}
																																			double cv$temp$13$var243;
																																			{
																																				double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$13$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value7);
																																		}
																																	}
																																}
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
																								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$48][0]; timeStep$var66 += 1) {
																									if(true) {
																										for(int index$sample76$50 = 0; index$sample76$50 < noStates; index$sample76$50 += 1) {
																											int distributionTempVariable$var74$52 = index$sample76$50;
																											double cv$probabilitySample76Value51 = (cv$probabilitySample57Value7 * distribution$sample76[((index$sample$48 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$50]);
																											if((index$sample$48 == sample$var196)) {
																												if((timeStep$var66 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var146 = 0; var146 < noServers; var146 += 1) {
																															for(int var156 = 0; var156 < noStates; var156 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$14$var241;
																																					{
																																						double var241 = traceTempVariable$var241$11_1;
																																						cv$temp$14$var241 = var241;
																																					}
																																					double cv$temp$15$var243;
																																					{
																																						double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																						cv$temp$15$var243 = var243;
																																					}
																																					if(((Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value51);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(fixedFlag$sample76) {
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															double traceTempVariable$var241$22_1 = cv$currentValue;
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																if((var119 == server)) {
																	if((var129 == st[sample$var196][timeStep$var226])) {
																		{
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample57) {
																						for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																							if((index$sample$56_1 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$16$var241;
																																	{
																																		double var241 = traceTempVariable$var241$22_1;
																																		cv$temp$16$var241 = var241;
																																	}
																																	double cv$temp$17$var243;
																																	{
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$17$var243 = var243;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))));
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
																								for(int index$sample57$58 = 0; index$sample57$58 < noStates; index$sample57$58 += 1) {
																									int distributionTempVariable$var55$60 = index$sample57$58;
																									double cv$probabilitySample57Value59 = (1.0 * distribution$sample57[((index$sample$57 - 0) / 1)][index$sample57$58]);
																									if((index$sample$57 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$18$var241;
																																			{
																																				double var241 = traceTempVariable$var241$22_1;
																																				cv$temp$18$var241 = var241;
																																			}
																																			double cv$temp$19$var243;
																																			{
																																				double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$19$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value59);
																																		}
																																	}
																																}
																															}
																														}
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
																							if((index$sample$64_1 == sample$var196)) {
																								if((index$timeStep$64_2 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$20$var241;
																																	{
																																		double var241 = traceTempVariable$var241$22_1;
																																		cv$temp$20$var241 = var241;
																																	}
																																	double cv$temp$21$var243;
																																	{
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$21$var243 = var243;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))));
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
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													for(int index$sample76$18 = 0; index$sample76$18 < noStates; index$sample76$18 += 1) {
														int distributionTempVariable$var74$20 = index$sample76$18;
														double cv$probabilitySample76Value19 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$18]);
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	double traceTempVariable$var241$23_1 = cv$currentValue;
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		if((var119 == server)) {
																			if((var129 == st[sample$var196][timeStep$var226])) {
																				{
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample57) {
																								for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																									if((index$sample$66_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var146 = 0; var146 < noServers; var146 += 1) {
																													for(int var156 = 0; var156 < noStates; var156 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var146 == server)) {
																																if((var156 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$22$var241;
																																			{
																																				double var241 = traceTempVariable$var241$23_1;
																																				cv$temp$22$var241 = var241;
																																			}
																																			double cv$temp$23$var243;
																																			{
																																				double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$23$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value19);
																																		}
																																	}
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
																										for(int index$sample57$68 = 0; index$sample57$68 < noStates; index$sample57$68 += 1) {
																											int distributionTempVariable$var55$70 = index$sample57$68;
																											double cv$probabilitySample57Value69 = (cv$probabilitySample76Value19 * distribution$sample57[((index$sample$67 - 0) / 1)][index$sample57$68]);
																											if((index$sample$67 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var146 = 0; var146 < noServers; var146 += 1) {
																															for(int var156 = 0; var156 < noStates; var156 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$24$var241;
																																					{
																																						double var241 = traceTempVariable$var241$23_1;
																																						cv$temp$24$var241 = var241;
																																					}
																																					double cv$temp$25$var243;
																																					{
																																						double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																						cv$temp$25$var243 = var243;
																																					}
																																					if(((Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value69);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							if((sample$var45 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var146 = 0; var146 < noServers; var146 += 1) {
																											for(int var156 = 0; var156 < noStates; var156 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var146 == server)) {
																														if((var156 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$26$var241;
																																	{
																																		double var241 = traceTempVariable$var241$23_1;
																																		cv$temp$26$var241 = var241;
																																	}
																																	double cv$temp$27$var243;
																																	{
																																		double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$27$var243 = var243;
																																	}
																																	if(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value19);
																																}
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
																									if(!((index$sample$75 == sample$var45) && (index$timeStep$76 == timeStep$var66))) {
																										for(int index$sample76$77 = 0; index$sample76$77 < noStates; index$sample76$77 += 1) {
																											int distributionTempVariable$var74$79 = index$sample76$77;
																											double cv$probabilitySample76Value78 = (cv$probabilitySample76Value19 * distribution$sample76[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample76$77]);
																											if((index$sample$75 == sample$var196)) {
																												if((index$timeStep$76 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var146 = 0; var146 < noServers; var146 += 1) {
																															for(int var156 = 0; var156 < noStates; var156 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$28$var241;
																																					{
																																						double var241 = traceTempVariable$var241$23_1;
																																						cv$temp$28$var241 = var241;
																																					}
																																					double cv$temp$29$var243;
																																					{
																																						double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
																																						cv$temp$29$var243 = var243;
																																					}
																																					if(((Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value78);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
			double var130 = cv$originalValue;
			double[] var120 = current_metric_mean[var119];
			var120[var129] = var130;
		}
	}

	private final void sample162(int var146, int var156) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = current_metric_var[var146][var156];
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
					double var157 = cv$proposedValue;
					double[] var147 = current_metric_var[var146];
					var147[var156] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var133;
				{
					cv$temp$0$var133 = 1.0;
				}
				double cv$temp$1$var134;
				{
					cv$temp$1$var134 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var133, cv$temp$1$var134));
				{
					{
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(fixedFlag$sample57) {
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											if((sample$var45 == sample$var196)) {
												if((0 == timeStep$var226)) {
													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
														double traceTempVariable$var243$10_1 = cv$currentValue;
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															if((var146 == server)) {
																if((var156 == st[sample$var196][timeStep$var226])) {
																	{
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																					if((index$sample$28_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$2$var241;
																															{
																																double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																cv$temp$2$var241 = var241;
																															}
																															double cv$temp$3$var243;
																															{
																																double var243 = traceTempVariable$var243$10_1;
																																cv$temp$3$var243 = var243;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$2$var241) / Math.sqrt(cv$temp$3$var243))) - (0.5 * Math.log(cv$temp$3$var243)))));
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
																				if(fixedFlag$sample76) {
																					for(int index$sample$30_1 = 0; index$sample$30_1 < noSamples; index$sample$30_1 += 1) {
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$30_1][0]; timeStep$var66 += 1) {
																							if((index$sample$30_1 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$4$var241;
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$4$var241 = var241;
																																	}
																																	double cv$temp$5$var243;
																																	{
																																		double var243 = traceTempVariable$var243$10_1;
																																		cv$temp$5$var243 = var243;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$4$var241) / Math.sqrt(cv$temp$5$var243))) - (0.5 * Math.log(cv$temp$5$var243)))));
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
																						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$31][0]; timeStep$var66 += 1) {
																							if(true) {
																								for(int index$sample76$33 = 0; index$sample76$33 < noStates; index$sample76$33 += 1) {
																									int distributionTempVariable$var74$35 = index$sample76$33;
																									double cv$probabilitySample76Value34 = (1.0 * distribution$sample76[((index$sample$31 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$33]);
																									if((index$sample$31 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$6$var241;
																																			{
																																				double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$6$var241 = var241;
																																			}
																																			double cv$temp$7$var243;
																																			{
																																				double var243 = traceTempVariable$var243$10_1;
																																				cv$temp$7$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value34) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$6$var241) / Math.sqrt(cv$temp$7$var243))) - (0.5 * Math.log(cv$temp$7$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value34);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											if(true) {
												for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
													int distributionTempVariable$var55$8 = index$sample57$6;
													double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$6]);
													if((sample$var45 == sample$var196)) {
														if((0 == timeStep$var226)) {
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																double traceTempVariable$var243$11_1 = cv$currentValue;
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	if((var146 == server)) {
																		if((var156 == st[sample$var196][timeStep$var226])) {
																			{
																				if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if((sample$var45 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														{
																															{
																																double cv$temp$8$var241;
																																{
																																	double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																	cv$temp$8$var241 = var241;
																																}
																																double cv$temp$9$var243;
																																{
																																	double var243 = traceTempVariable$var243$11_1;
																																	cv$temp$9$var243 = var243;
																																}
																																if(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$8$var241) / Math.sqrt(cv$temp$9$var243))) - (0.5 * Math.log(cv$temp$9$var243)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value7);
																															}
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
																							if(!(index$sample$40 == sample$var45)) {
																								for(int index$sample57$41 = 0; index$sample57$41 < noStates; index$sample57$41 += 1) {
																									int distributionTempVariable$var55$43 = index$sample57$41;
																									double cv$probabilitySample57Value42 = (cv$probabilitySample57Value7 * distribution$sample57[((index$sample$40 - 0) / 1)][index$sample57$41]);
																									if((index$sample$40 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$10$var241;
																																			{
																																				double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$10$var241 = var241;
																																			}
																																			double cv$temp$11$var243;
																																			{
																																				double var243 = traceTempVariable$var243$11_1;
																																				cv$temp$11$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value42) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$10$var241) / Math.sqrt(cv$temp$11$var243))) - (0.5 * Math.log(cv$temp$11$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value42);
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						if(fixedFlag$sample76) {
																							for(int index$sample$47_1 = 0; index$sample$47_1 < noSamples; index$sample$47_1 += 1) {
																								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$47_1][0]; timeStep$var66 += 1) {
																									if((index$sample$47_1 == sample$var196)) {
																										if((timeStep$var66 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$12$var241;
																																			{
																																				double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$12$var241 = var241;
																																			}
																																			double cv$temp$13$var243;
																																			{
																																				double var243 = traceTempVariable$var243$11_1;
																																				cv$temp$13$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value7) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value7);
																																		}
																																	}
																																}
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
																								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$48][0]; timeStep$var66 += 1) {
																									if(true) {
																										for(int index$sample76$50 = 0; index$sample76$50 < noStates; index$sample76$50 += 1) {
																											int distributionTempVariable$var74$52 = index$sample76$50;
																											double cv$probabilitySample76Value51 = (cv$probabilitySample57Value7 * distribution$sample76[((index$sample$48 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$50]);
																											if((index$sample$48 == sample$var196)) {
																												if((timeStep$var66 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var119 = 0; var119 < noServers; var119 += 1) {
																															for(int var129 = 0; var129 < noStates; var129 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var119 == server)) {
																																		if((var129 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$14$var241;
																																					{
																																						double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																						cv$temp$14$var241 = var241;
																																					}
																																					double cv$temp$15$var243;
																																					{
																																						double var243 = traceTempVariable$var243$11_1;
																																						cv$temp$15$var243 = var243;
																																					}
																																					if(((Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value51) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value51);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							for(int server = 0; server < noServers; server += 1) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if(fixedFlag$sample76) {
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if((sample$var45 == sample$var196)) {
													if((timeStep$var66 == timeStep$var226)) {
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															double traceTempVariable$var243$22_1 = cv$currentValue;
															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																if((var146 == server)) {
																	if((var156 == st[sample$var196][timeStep$var226])) {
																		{
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample57) {
																						for(int index$sample$56_1 = 0; index$sample$56_1 < noSamples; index$sample$56_1 += 1) {
																							if((index$sample$56_1 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$16$var241;
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$16$var241 = var241;
																																	}
																																	double cv$temp$17$var243;
																																	{
																																		double var243 = traceTempVariable$var243$22_1;
																																		cv$temp$17$var243 = var243;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))));
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
																								for(int index$sample57$58 = 0; index$sample57$58 < noStates; index$sample57$58 += 1) {
																									int distributionTempVariable$var55$60 = index$sample57$58;
																									double cv$probabilitySample57Value59 = (1.0 * distribution$sample57[((index$sample$57 - 0) / 1)][index$sample57$58]);
																									if((index$sample$57 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$18$var241;
																																			{
																																				double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$18$var241 = var241;
																																			}
																																			double cv$temp$19$var243;
																																			{
																																				double var243 = traceTempVariable$var243$22_1;
																																				cv$temp$19$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value59) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value59);
																																		}
																																	}
																																}
																															}
																														}
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
																							if((index$sample$64_1 == sample$var196)) {
																								if((index$timeStep$64_2 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$20$var241;
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$20$var241 = var241;
																																	}
																																	double cv$temp$21$var243;
																																	{
																																		double var243 = traceTempVariable$var243$22_1;
																																		cv$temp$21$var243 = var243;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))));
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
										for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
											for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
												if(true) {
													for(int index$sample76$18 = 0; index$sample76$18 < noStates; index$sample76$18 += 1) {
														int distributionTempVariable$var74$20 = index$sample76$18;
														double cv$probabilitySample76Value19 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$18]);
														if((sample$var45 == sample$var196)) {
															if((timeStep$var66 == timeStep$var226)) {
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	double traceTempVariable$var243$23_1 = cv$currentValue;
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		if((var146 == server)) {
																			if((var156 == st[sample$var196][timeStep$var226])) {
																				{
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample57) {
																								for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
																									if((index$sample$66_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	{
																																		{
																																			double cv$temp$22$var241;
																																			{
																																				double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																				cv$temp$22$var241 = var241;
																																			}
																																			double cv$temp$23$var243;
																																			{
																																				double var243 = traceTempVariable$var243$23_1;
																																				cv$temp$23$var243 = var243;
																																			}
																																			if(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) < cv$accumulatedConsumerProbabilities))
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																			else {
																																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243))));
																																				else
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))));
																																			}
																																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value19);
																																		}
																																	}
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
																										for(int index$sample57$68 = 0; index$sample57$68 < noStates; index$sample57$68 += 1) {
																											int distributionTempVariable$var55$70 = index$sample57$68;
																											double cv$probabilitySample57Value69 = (cv$probabilitySample76Value19 * distribution$sample57[((index$sample$67 - 0) / 1)][index$sample57$68]);
																											if((index$sample$67 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var119 = 0; var119 < noServers; var119 += 1) {
																															for(int var129 = 0; var129 < noStates; var129 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var119 == server)) {
																																		if((var129 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$24$var241;
																																					{
																																						double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																						cv$temp$24$var241 = var241;
																																					}
																																					double cv$temp$25$var243;
																																					{
																																						double var243 = traceTempVariable$var243$23_1;
																																						cv$temp$25$var243 = var243;
																																					}
																																					if(((Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value69) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value69);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							if((sample$var45 == sample$var196)) {
																								if((timeStep$var66 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															{
																																{
																																	double cv$temp$26$var241;
																																	{
																																		double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																		cv$temp$26$var241 = var241;
																																	}
																																	double cv$temp$27$var243;
																																	{
																																		double var243 = traceTempVariable$var243$23_1;
																																		cv$temp$27$var243 = var243;
																																	}
																																	if(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value19) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))));
																																	}
																																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value19);
																																}
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
																									if(!((index$sample$75 == sample$var45) && (index$timeStep$76 == timeStep$var66))) {
																										for(int index$sample76$77 = 0; index$sample76$77 < noStates; index$sample76$77 += 1) {
																											int distributionTempVariable$var74$79 = index$sample76$77;
																											double cv$probabilitySample76Value78 = (cv$probabilitySample76Value19 * distribution$sample76[((index$sample$75 - 0) / 1)][((index$timeStep$76 - 1) / 1)][index$sample76$77]);
																											if((index$sample$75 == sample$var196)) {
																												if((index$timeStep$76 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var119 = 0; var119 < noServers; var119 += 1) {
																															for(int var129 = 0; var129 < noStates; var129 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var119 == server)) {
																																		if((var129 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$28$var241;
																																					{
																																						double var241 = current_metric_mean[server][st[sample$var196][timeStep$var226]];
																																						cv$temp$28$var241 = var241;
																																					}
																																					double cv$temp$29$var243;
																																					{
																																						double var243 = traceTempVariable$var243$23_1;
																																						cv$temp$29$var243 = var243;
																																					}
																																					if(((Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value78) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value78);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
			double var157 = cv$originalValue;
			double[] var147 = current_metric_var[var146];
			var147[var156] = var157;
		}
	}

	private final void sample190(int var173, int var183) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
								if(fixedFlag$sample57) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if((sample$var45 == sample$var196)) {
											if((0 == timeStep$var226)) {
												if((var173 == server)) {
													if((var183 == st[sample$var196][timeStep$var226])) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + 1.0);
																			if(metric_valid_g[sample$var196][server][timeStep$var226])
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										if(true) {
											for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
												int distributionTempVariable$var55$8 = index$sample57$6;
												double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$6]);
												if((sample$var45 == sample$var196)) {
													if((0 == timeStep$var226)) {
														if((var173 == server)) {
															if((var183 == st[sample$var196][timeStep$var226])) {
																{
																	{
																		{
																			{
																				{
																					cv$count = (cv$count + cv$probabilitySample57Value7);
																					if(metric_valid_g[sample$var196][server][timeStep$var226])
																						cv$sum = (cv$sum + cv$probabilitySample57Value7);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
						for(int server = 0; server < noServers; server += 1) {
							for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
								if(fixedFlag$sample76) {
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if((sample$var45 == sample$var196)) {
												if((timeStep$var66 == timeStep$var226)) {
													if((var173 == server)) {
														if((var183 == st[sample$var196][timeStep$var226])) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + 1.0);
																				if(metric_valid_g[sample$var196][server][timeStep$var226])
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
									for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
										for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
											if(true) {
												for(int index$sample76$18 = 0; index$sample76$18 < noStates; index$sample76$18 += 1) {
													int distributionTempVariable$var74$20 = index$sample76$18;
													double cv$probabilitySample76Value19 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$18]);
													if((sample$var45 == sample$var196)) {
														if((timeStep$var66 == timeStep$var226)) {
															if((var173 == server)) {
																if((var183 == st[sample$var196][timeStep$var226])) {
																	{
																		{
																			{
																				{
																					{
																						cv$count = (cv$count + cv$probabilitySample76Value19);
																						if(metric_valid_g[sample$var196][server][timeStep$var226])
																							cv$sum = (cv$sum + cv$probabilitySample76Value19);
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var184 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		double[] var174 = current_metric_valid_bias[var173];
		var174[var183] = var184;
	}

	private final void sample20() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var20$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						if(fixedFlag$sample57) {
							{
								int index$sample$3 = sample$var45;
								{
									{
										{
											{
												cv$countLocal[st[sample$var45][0]] = (cv$countLocal[st[sample$var45][0]] + 1.0);
											}
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
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					if(!fixedFlag$sample57) {
						{
							int index$sample$7 = sample$var45;
							{
								{
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[((sample$var45 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
	}

	private final void sample241(int sample$var196, int server, int timeStep$var226) {
		boolean cv$varObserved = false;
		{
			cv$varObserved = true;
		}
		if(!cv$varObserved) {
			int cv$numNumStates = 0;
			if(fixedFlag$sample57) {
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					if((sample$var45 == sample$var196)) {
						if((0 == timeStep$var226)) {
							for(int var173 = 0; var173 < noServers; var173 += 1) {
								for(int var183 = 0; var183 < noStates; var183 += 1) {
									if((var173 == server)) {
										if((var183 == st[sample$var196][timeStep$var226]))
											cv$numNumStates = Math.max(cv$numNumStates, 2);
									}
								}
							}
						}
					}
				}
			} else {
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					if(true) {
						for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
							int distributionTempVariable$var55$6 = index$sample57$4;
							double cv$probabilitySample57Value5 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$4]);
							if((sample$var45 == sample$var196)) {
								if((0 == timeStep$var226)) {
									for(int var173 = 0; var173 < noServers; var173 += 1) {
										for(int var183 = 0; var183 < noStates; var183 += 1) {
											if((var173 == server)) {
												if((var183 == st[sample$var196][timeStep$var226]))
													cv$numNumStates = Math.max(cv$numNumStates, 2);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample76) {
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						if((sample$var45 == sample$var196)) {
							if((timeStep$var66 == timeStep$var226)) {
								for(int var173 = 0; var173 < noServers; var173 += 1) {
									for(int var183 = 0; var183 < noStates; var183 += 1) {
										if((var173 == server)) {
											if((var183 == st[sample$var196][timeStep$var226]))
												cv$numNumStates = Math.max(cv$numNumStates, 2);
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						if(true) {
							for(int index$sample76$13 = 0; index$sample76$13 < noStates; index$sample76$13 += 1) {
								int distributionTempVariable$var74$15 = index$sample76$13;
								double cv$probabilitySample76Value14 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$13]);
								if((sample$var45 == sample$var196)) {
									if((timeStep$var66 == timeStep$var226)) {
										for(int var173 = 0; var173 < noServers; var173 += 1) {
											for(int var183 = 0; var183 < noStates; var183 += 1) {
												if((var173 == server)) {
													if((var183 == st[sample$var196][timeStep$var226]))
														cv$numNumStates = Math.max(cv$numNumStates, 2);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var232$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var232 = cv$currentValue;
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				metric_valid_inner[timeStep$var226] = cv$currentValue;
				if(fixedFlag$sample57) {
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						if((sample$var45 == sample$var196)) {
							if((0 == timeStep$var226)) {
								for(int var173 = 0; var173 < noServers; var173 += 1) {
									for(int var183 = 0; var183 < noStates; var183 += 1) {
										if((var173 == server)) {
											if((var183 == st[sample$var196][timeStep$var226])) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
												double cv$temp$0$var230;
												{
													double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
													cv$temp$0$var230 = var230;
												}
												double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$0$var230));
												{
													{
														for(int index$timeStep$36_1 = 0; index$timeStep$36_1 < length$metric[sample$var196][0]; index$timeStep$36_1 += 1) {
															if((timeStep$var226 == index$timeStep$36_1)) {
																if(metric_valid_g[sample$var196][server][index$timeStep$36_1]) {
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
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						if(true) {
							for(int index$sample57$21 = 0; index$sample57$21 < noStates; index$sample57$21 += 1) {
								int distributionTempVariable$var55$23 = index$sample57$21;
								double cv$probabilitySample57Value22 = (1.0 * distribution$sample57[((sample$var45 - 0) / 1)][index$sample57$21]);
								if((sample$var45 == sample$var196)) {
									if((0 == timeStep$var226)) {
										for(int var173 = 0; var173 < noServers; var173 += 1) {
											for(int var183 = 0; var183 < noStates; var183 += 1) {
												if((var173 == server)) {
													if((var183 == st[sample$var196][timeStep$var226])) {
														cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value22);
														double cv$temp$1$var230;
														{
															double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
															cv$temp$1$var230 = var230;
														}
														double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value22) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$1$var230));
														{
															{
																for(int index$timeStep$37_1 = 0; index$timeStep$37_1 < length$metric[sample$var196][0]; index$timeStep$37_1 += 1) {
																	if((timeStep$var226 == index$timeStep$37_1)) {
																		if(metric_valid_g[sample$var196][server][index$timeStep$37_1]) {
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
				if(fixedFlag$sample76) {
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if((sample$var45 == sample$var196)) {
								if((timeStep$var66 == timeStep$var226)) {
									for(int var173 = 0; var173 < noServers; var173 += 1) {
										for(int var183 = 0; var183 < noStates; var183 += 1) {
											if((var173 == server)) {
												if((var183 == st[sample$var196][timeStep$var226])) {
													cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
													double cv$temp$2$var230;
													{
														double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
														cv$temp$2$var230 = var230;
													}
													double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$2$var230));
													{
														{
															for(int index$timeStep$38_1 = 0; index$timeStep$38_1 < length$metric[sample$var196][0]; index$timeStep$38_1 += 1) {
																if((timeStep$var226 == index$timeStep$38_1)) {
																	if(metric_valid_g[sample$var196][server][index$timeStep$38_1]) {
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
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(true) {
								for(int index$sample76$30 = 0; index$sample76$30 < noStates; index$sample76$30 += 1) {
									int distributionTempVariable$var74$32 = index$sample76$30;
									double cv$probabilitySample76Value31 = (1.0 * distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$30]);
									if((sample$var45 == sample$var196)) {
										if((timeStep$var66 == timeStep$var226)) {
											for(int var173 = 0; var173 < noServers; var173 += 1) {
												for(int var183 = 0; var183 < noStates; var183 += 1) {
													if((var173 == server)) {
														if((var183 == st[sample$var196][timeStep$var226])) {
															cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value31);
															double cv$temp$3$var230;
															{
																double var230 = current_metric_valid_bias[server][st[sample$var196][timeStep$var226]];
																cv$temp$3$var230 = var230;
															}
															double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value31) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$3$var230));
															{
																{
																	for(int index$timeStep$39_1 = 0; index$timeStep$39_1 < length$metric[sample$var196][0]; index$timeStep$39_1 += 1) {
																		if((timeStep$var226 == index$timeStep$39_1)) {
																			if(metric_valid_g[sample$var196][server][index$timeStep$39_1]) {
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
			boolean var232 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates) == 1);
			boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
			metric_valid_inner[timeStep$var226] = var232;
		}
	}

	private final void sample33(int var32) {
		double[] cv$targetLocal = m[var32];
		double[] cv$countLocal = cv$var33$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(fixedFlag$sample57) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample$var45)) {
										if((0 == (timeStep$var66 - 1))) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
												if(fixedFlag$sample76) {
													{
														int index$timeStep$23 = timeStep$var66;
														int index$sample$24 = sample$var45;
														{
															{
																{
																	{
																		cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + 1.0);
																	}
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
										for(int index$sample57$5 = 0; index$sample57$5 < noStates; index$sample57$5 += 1) {
											int distributionTempVariable$var55$7 = index$sample57$5;
											double cv$probabilitySample57Value6 = (1.0 * distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
											if((index$sample$4 == sample$var45)) {
												if((0 == (timeStep$var66 - 1))) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														if(fixedFlag$sample76) {
															{
																int index$timeStep$26 = timeStep$var66;
																int index$sample$27 = sample$var45;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + cv$probabilitySample57Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
						for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
							if(fixedFlag$sample76) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1][0]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample$var45)) {
											if((index$timeStep$13_2 == (timeStep$var66 - 1))) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													if(fixedFlag$sample76) {
														{
															int index$timeStep$29 = timeStep$var66;
															int index$sample$30 = sample$var45;
															{
																{
																	{
																		{
																			cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + 1.0);
																		}
																	}
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
											for(int index$sample76$16 = 0; index$sample76$16 < noStates; index$sample76$16 += 1) {
												int distributionTempVariable$var74$18 = index$sample76$16;
												double cv$probabilitySample76Value17 = (1.0 * distribution$sample76[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample76$16]);
												if((index$sample$14 == sample$var45)) {
													if((index$timeStep$15 == (timeStep$var66 - 1))) {
														if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
															if(fixedFlag$sample76) {
																{
																	int index$timeStep$32 = timeStep$var66;
																	int index$sample$33 = sample$var45;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + cv$probabilitySample76Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						if(fixedFlag$sample57) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample$var45)) {
									if((0 == (timeStep$var66 - 1))) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
											if(!fixedFlag$sample76) {
												{
													int index$timeStep$60 = timeStep$var66;
													int index$sample$61 = sample$var45;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
										int distributionTempVariable$var55$44 = index$sample57$42;
										double cv$probabilitySample57Value43 = (1.0 * distribution$sample57[((index$sample$41 - 0) / 1)][index$sample57$42]);
										if((index$sample$41 == sample$var45)) {
											if((0 == (timeStep$var66 - 1))) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													if(!fixedFlag$sample76) {
														{
															int index$timeStep$63 = timeStep$var66;
															int index$sample$64 = sample$var45;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample57Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
					for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
						if(fixedFlag$sample76) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1][0]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample$var45)) {
										if((index$timeStep$50_2 == (timeStep$var66 - 1))) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
												if(!fixedFlag$sample76) {
													{
														int index$timeStep$66 = timeStep$var66;
														int index$sample$67 = sample$var45;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
										for(int index$sample76$53 = 0; index$sample76$53 < noStates; index$sample76$53 += 1) {
											int distributionTempVariable$var74$55 = index$sample76$53;
											double cv$probabilitySample76Value54 = (1.0 * distribution$sample76[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample76$53]);
											if((index$sample$51 == sample$var45)) {
												if((index$timeStep$52 == (timeStep$var66 - 1))) {
													if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
														if(!fixedFlag$sample76) {
															{
																int index$timeStep$69 = timeStep$var66;
																int index$sample$70 = sample$var45;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample76Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample57(int sample$var45) {
		int cv$numNumStates = 0;
		int index$sample$1 = sample$var45;
		{
			cv$numNumStates = Math.max(cv$numNumStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			int index$sample$2 = sample$var45;
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
				int cv$temp$1$$var633;
				{
					int $var633 = noStates;
					cv$temp$1$$var633 = $var633;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var633))?Math.log(cv$temp$0$initialStateDistribution[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var71$3_1 = cv$currentValue;
						for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
							if((sample$var45 == index$sample$3_2)) {
								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$3_2][0]; timeStep$var66 += 1) {
									if((0 == (timeStep$var66 - 1))) {
										if(fixedFlag$sample76) {
											{
												int index$timeStep$5 = timeStep$var66;
												int index$sample$6 = index$sample$3_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var32 = 0; var32 < noStates; var32 += 1) {
														if((var32 == st[index$sample$3_2][(timeStep$var66 - 1)])) {
															{
																{
																	double[] cv$temp$2$var72;
																	{
																		double[] var72 = m[traceTempVariable$var71$3_1];
																		cv$temp$2$var72 = var72;
																	}
																	int cv$temp$3$$var646;
																	{
																		int $var646 = noStates;
																		cv$temp$3$$var646 = $var646;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var646))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var646))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var646))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var646))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var66]) && (st[index$sample$3_2][timeStep$var66] < cv$temp$3$$var646))?Math.log(cv$temp$2$var72[st[index$sample$3_2][timeStep$var66]]):Double.NEGATIVE_INFINITY)));
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
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							if((sample$var45 == sample$var196)) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if((0 == timeStep$var226)) {
										for(int server = 0; server < noServers; server += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var173 = 0; var173 < noServers; var173 += 1) {
													for(int var183 = 0; var183 < noStates; var183 += 1) {
														if((var173 == server)) {
															if((var183 == st[sample$var196][timeStep$var226])) {
																{
																	{
																		double cv$temp$4$var230;
																		{
																			double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$9_1];
																			cv$temp$4$var230 = var230;
																		}
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$4$var230)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$4$var230)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$4$var230));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$4$var230)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$4$var230)));
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
						boolean[][][] guard$sample57gaussian255 = guard$sample57gaussian255$global;
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							if((sample$var45 == sample$var196)) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if((0 == timeStep$var226)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var196][server][timeStep$var226])
												guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							if((sample$var45 == sample$var196)) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if((0 == timeStep$var226)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var196][server][timeStep$var226])
												guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
										}
									}
								}
							}
						}
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							if((sample$var45 == sample$var196)) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if((0 == timeStep$var226)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
													guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
													{
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var119 = 0; var119 < noServers; var119 += 1) {
																	for(int var129 = 0; var129 < noStates; var129 += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if((var119 == server)) {
																				if((var129 == st[sample$var196][timeStep$var226])) {
																					int traceTempVariable$currentState$20_1 = cv$currentValue;
																					if((index$sample$2 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$5$var241;
																															{
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$20_1];
																																cv$temp$5$var241 = var241;
																															}
																															double cv$temp$6$var243;
																															{
																																double var243 = current_metric_var[server][traceTempVariable$currentState$20_1];
																																cv$temp$6$var243 = var243;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$5$var241) / Math.sqrt(cv$temp$6$var243))) - (0.5 * Math.log(cv$temp$6$var243)))));
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
																							for(int index$sample57$22 = 0; index$sample57$22 < noStates; index$sample57$22 += 1) {
																								int distributionTempVariable$var55$24 = index$sample57$22;
																								double cv$probabilitySample57Value23 = (1.0 * distribution$sample57[((index$sample$21 - 0) / 1)][index$sample57$22]);
																								int traceTempVariable$currentState$25_1 = cv$currentValue;
																								if((index$sample$21 == sample$var196)) {
																									if((0 == timeStep$var226)) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$7$var241;
																																		{
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$25_1];
																																			cv$temp$7$var241 = var241;
																																		}
																																		double cv$temp$8$var243;
																																		{
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$25_1];
																																			cv$temp$8$var243 = var243;
																																		}
																																		if(((Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$7$var241) / Math.sqrt(cv$temp$8$var243))) - (0.5 * Math.log(cv$temp$8$var243)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value23);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																for(int var119 = 0; var119 < noServers; var119 += 1) {
																	for(int var129 = 0; var129 < noStates; var129 += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if((var119 == server)) {
																				if((var129 == st[sample$var196][timeStep$var226])) {
																					if(fixedFlag$sample76) {
																						for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$29_1][0]; timeStep$var66 += 1) {
																								if((index$sample$29_1 == sample$var196)) {
																									if((timeStep$var66 == timeStep$var226)) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$9$var241;
																																		{
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																			cv$temp$9$var241 = var241;
																																		}
																																		double cv$temp$10$var243;
																																		{
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																			cv$temp$10$var243 = var243;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$9$var241) / Math.sqrt(cv$temp$10$var243))) - (0.5 * Math.log(cv$temp$10$var243)))));
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
																							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$30][0]; timeStep$var66 += 1) {
																								if(true) {
																									for(int index$sample76$32 = 0; index$sample76$32 < noStates; index$sample76$32 += 1) {
																										int distributionTempVariable$var74$34 = index$sample76$32;
																										double cv$probabilitySample76Value33 = (1.0 * distribution$sample76[((index$sample$30 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$32]);
																										if((index$sample$30 == sample$var196)) {
																											if((timeStep$var66 == timeStep$var226)) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var146 == server)) {
																																	if((var156 == st[sample$var196][timeStep$var226])) {
																																		{
																																			{
																																				double cv$temp$11$var241;
																																				{
																																					double var241 = current_metric_mean[server][traceTempVariable$currentState$15_1];
																																					cv$temp$11$var241 = var241;
																																				}
																																				double cv$temp$12$var243;
																																				{
																																					double var243 = current_metric_var[server][traceTempVariable$currentState$15_1];
																																					cv$temp$12$var243 = var243;
																																				}
																																				if(((Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$11$var241) / Math.sqrt(cv$temp$12$var243))) - (0.5 * Math.log(cv$temp$12$var243)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value33);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
						for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
							if((sample$var45 == sample$var196)) {
								for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
									if((0 == timeStep$var226)) {
										for(int server = 0; server < noServers; server += 1) {
											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
												if(!guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
													guard$sample57gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
													{
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																int traceTempVariable$currentState$38_1 = cv$currentValue;
																if((index$sample$2 == sample$var196)) {
																	if((0 == timeStep$var226)) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			for(int var119 = 0; var119 < noServers; var119 += 1) {
																				for(int var129 = 0; var129 < noStates; var129 += 1) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						if((var119 == server)) {
																							if((var129 == st[sample$var196][timeStep$var226])) {
																								for(int var146 = 0; var146 < noServers; var146 += 1) {
																									for(int var156 = 0; var156 < noStates; var156 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var146 == server)) {
																												if((var156 == st[sample$var196][timeStep$var226])) {
																													{
																														{
																															double cv$temp$13$var241;
																															{
																																double var241 = current_metric_mean[server][traceTempVariable$currentState$38_1];
																																cv$temp$13$var241 = var241;
																															}
																															double cv$temp$14$var243;
																															{
																																double var243 = current_metric_var[server][traceTempVariable$currentState$38_1];
																																cv$temp$14$var243 = var243;
																															}
																															if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))) < cv$accumulatedConsumerProbabilities))
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																															else {
																																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243))));
																																else
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$13$var241) / Math.sqrt(cv$temp$14$var243))) - (0.5 * Math.log(cv$temp$14$var243)))));
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
																		for(int index$sample57$40 = 0; index$sample57$40 < noStates; index$sample57$40 += 1) {
																			int distributionTempVariable$var55$42 = index$sample57$40;
																			double cv$probabilitySample57Value41 = (1.0 * distribution$sample57[((index$sample$39 - 0) / 1)][index$sample57$40]);
																			int traceTempVariable$currentState$43_1 = cv$currentValue;
																			if((index$sample$39 == sample$var196)) {
																				if((0 == timeStep$var226)) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						for(int var119 = 0; var119 < noServers; var119 += 1) {
																							for(int var129 = 0; var129 < noStates; var129 += 1) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$15$var241;
																																		{
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$43_1];
																																			cv$temp$15$var241 = var241;
																																		}
																																		double cv$temp$16$var243;
																																		{
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$43_1];
																																			cv$temp$16$var243 = var243;
																																		}
																																		if(((Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$15$var241) / Math.sqrt(cv$temp$16$var243))) - (0.5 * Math.log(cv$temp$16$var243)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value41);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
																if(fixedFlag$sample76) {
																	for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																		for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$48_1][0]; timeStep$var66 += 1) {
																			if((index$sample$48_1 == sample$var196)) {
																				if((timeStep$var66 == timeStep$var226)) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						for(int var119 = 0; var119 < noServers; var119 += 1) {
																							for(int var129 = 0; var129 < noStates; var129 += 1) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$17$var241;
																																		{
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																			cv$temp$17$var241 = var241;
																																		}
																																		double cv$temp$18$var243;
																																		{
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																			cv$temp$18$var243 = var243;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$17$var241) / Math.sqrt(cv$temp$18$var243))) - (0.5 * Math.log(cv$temp$18$var243)))));
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
																		for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$49][0]; timeStep$var66 += 1) {
																			if(true) {
																				for(int index$sample76$51 = 0; index$sample76$51 < noStates; index$sample76$51 += 1) {
																					int distributionTempVariable$var74$53 = index$sample76$51;
																					double cv$probabilitySample76Value52 = (1.0 * distribution$sample76[((index$sample$49 - 0) / 1)][((timeStep$var66 - 1) / 1)][index$sample76$51]);
																					if((index$sample$49 == sample$var196)) {
																						if((timeStep$var66 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var146 == server)) {
																																	if((var156 == st[sample$var196][timeStep$var226])) {
																																		{
																																			{
																																				double cv$temp$19$var241;
																																				{
																																					double var241 = current_metric_mean[server][traceTempVariable$currentState$16_1];
																																					cv$temp$19$var241 = var241;
																																				}
																																				double cv$temp$20$var243;
																																				{
																																					double var243 = current_metric_var[server][traceTempVariable$currentState$16_1];
																																					cv$temp$20$var243 = var243;
																																				}
																																				if(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$19$var241) / Math.sqrt(cv$temp$20$var243))) - (0.5 * Math.log(cv$temp$20$var243)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value52);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
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
					int traceTempVariable$var71$67_1 = cv$currentValue;
					for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
						if((sample$var45 == index$sample$67_2)) {
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[index$sample$67_2][0]; timeStep$var66 += 1) {
								if((0 == (timeStep$var66 - 1))) {
									if(!fixedFlag$sample76) {
										{
											int index$timeStep$69 = timeStep$var66;
											int index$sample$70 = index$sample$67_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var32 = 0; var32 < noStates; var32 += 1) {
												if((var32 == st[index$sample$67_2][(timeStep$var66 - 1)])) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$21$var72;
														{
															double[] var72 = m[traceTempVariable$var71$67_1];
															cv$temp$21$var72 = var72;
														}
														int cv$temp$22$$var811;
														{
															int $var811 = noStates;
															cv$temp$22$$var811 = $var811;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$21$var72, cv$temp$22$$var811);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample76[((index$sample$67_2 - 0) / 1)][((timeStep$var66 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample57[((sample$var45 - 0) / 1)];
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

	private final void sample76(int sample$var45, int timeStep$var66) {
		int cv$numNumStates = 0;
		int index$timeStep$1 = timeStep$var66;
		int index$sample$2 = sample$var45;
		if(fixedFlag$sample57) {
			for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
				if((index$sample$3_1 == sample$var45)) {
					if((0 == (timeStep$var66 - 1))) {
						for(int var32 = 0; var32 < noStates; var32 += 1) {
							if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
								cv$numNumStates = Math.max(cv$numNumStates, noStates);
						}
					}
				}
			}
		} else {
			for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
				if(true) {
					for(int index$sample57$5 = 0; index$sample57$5 < noStates; index$sample57$5 += 1) {
						int distributionTempVariable$var55$7 = index$sample57$5;
						double cv$probabilitySample57Value6 = (1.0 * distribution$sample57[((index$sample$4 - 0) / 1)][index$sample57$5]);
						if((index$sample$4 == sample$var45)) {
							if((0 == (timeStep$var66 - 1))) {
								for(int var32 = 0; var32 < noStates; var32 += 1) {
									if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
										cv$numNumStates = Math.max(cv$numNumStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		if((index$sample$2 == sample$var45)) {
			if((index$timeStep$1 == (timeStep$var66 - 1))) {
				for(int var32 = 0; var32 < noStates; var32 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		if(fixedFlag$sample76) {
			for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
				for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1][0]; index$timeStep$12_2 += 1) {
					if((index$sample$12_1 == sample$var45)) {
						if((index$timeStep$12_2 == (timeStep$var66 - 1))) {
							for(int var32 = 0; var32 < noStates; var32 += 1) {
								if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
									cv$numNumStates = Math.max(cv$numNumStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
				for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13][0]; index$timeStep$14 += 1) {
					if(!((index$sample$13 == index$sample$2) && (index$timeStep$14 == index$timeStep$1))) {
						for(int index$sample76$15 = 0; index$sample76$15 < noStates; index$sample76$15 += 1) {
							int distributionTempVariable$var74$17 = index$sample76$15;
							double cv$probabilitySample76Value16 = (1.0 * distribution$sample76[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample76$15]);
							if((index$sample$13 == sample$var45)) {
								if((index$timeStep$14 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
											cv$numNumStates = Math.max(cv$numNumStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			int index$timeStep$22 = timeStep$var66;
			int index$sample$23 = sample$var45;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample57) {
				for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
					if((index$sample$24_1 == sample$var45)) {
						if((0 == (timeStep$var66 - 1))) {
							for(int var32 = 0; var32 < noStates; var32 += 1) {
								if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var72;
									{
										double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
										cv$temp$0$var72 = var72;
									}
									int cv$temp$1$$var897;
									{
										int $var897 = noStates;
										cv$temp$1$$var897 = $var897;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var897))?Math.log(cv$temp$0$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var71$41_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$45_1 = cv$currentValue;
											for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
												if((sample$var45 == sample$var196)) {
													for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int server = 0; server < noServers; server += 1) {
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var173 = 0; var173 < noServers; var173 += 1) {
																		for(int var183 = 0; var183 < noStates; var183 += 1) {
																			if((var173 == server)) {
																				if((var183 == st[sample$var196][timeStep$var226])) {
																					{
																						{
																							double cv$temp$8$var230;
																							{
																								double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$45_1];
																								cv$temp$8$var230 = var230;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$8$var230)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$8$var230)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$8$var230));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$8$var230)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$8$var230)));
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
											boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global;
											for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
												if((sample$var45 == sample$var196)) {
													for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var196][server][timeStep$var226])
																	guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
															}
														}
													}
												}
											}
											for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
												if((sample$var45 == sample$var196)) {
													for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var196][server][timeStep$var226])
																	guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
															}
														}
													}
												}
											}
											int traceTempVariable$currentState$69_1 = cv$currentValue;
											for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
												if((sample$var45 == sample$var196)) {
													for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																		guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																		{
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								if((var119 == server)) {
																									if((var129 == st[sample$var196][timeStep$var226])) {
																										for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																											if((index$sample$86_1 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var146 = 0; var146 < noServers; var146 += 1) {
																															for(int var156 = 0; var156 < noStates; var156 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$12$var241;
																																					{
																																						double var241 = current_metric_mean[server][traceTempVariable$currentState$69_1];
																																						cv$temp$12$var241 = var241;
																																					}
																																					double cv$temp$13$var243;
																																					{
																																						double var243 = current_metric_var[server][traceTempVariable$currentState$69_1];
																																						cv$temp$13$var243 = var243;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$12$var241) / Math.sqrt(cv$temp$13$var243))) - (0.5 * Math.log(cv$temp$13$var243)))));
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
																					for(int var119 = 0; var119 < noServers; var119 += 1) {
																						for(int var129 = 0; var129 < noStates; var129 += 1) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								if((var119 == server)) {
																									if((var129 == st[sample$var196][timeStep$var226])) {
																										int traceTempVariable$currentState$89_1 = cv$currentValue;
																										if((index$sample$23 == sample$var196)) {
																											if((index$timeStep$22 == timeStep$var226)) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var146 == server)) {
																																	if((var156 == st[sample$var196][timeStep$var226])) {
																																		{
																																			{
																																				double cv$temp$14$var241;
																																				{
																																					double var241 = current_metric_mean[server][traceTempVariable$currentState$89_1];
																																					cv$temp$14$var241 = var241;
																																				}
																																				double cv$temp$15$var243;
																																				{
																																					double var243 = current_metric_var[server][traceTempVariable$currentState$89_1];
																																					cv$temp$15$var243 = var243;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$14$var241) / Math.sqrt(cv$temp$15$var243))) - (0.5 * Math.log(cv$temp$15$var243)))));
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
																													for(int index$sample76$92 = 0; index$sample76$92 < noStates; index$sample76$92 += 1) {
																														int distributionTempVariable$var74$94 = index$sample76$92;
																														double cv$probabilitySample76Value93 = (1.0 * distribution$sample76[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample76$92]);
																														int traceTempVariable$currentState$95_1 = cv$currentValue;
																														if((index$sample$90 == sample$var196)) {
																															if((index$timeStep$91 == timeStep$var226)) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																				if((var146 == server)) {
																																					if((var156 == st[sample$var196][timeStep$var226])) {
																																						{
																																							{
																																								double cv$temp$16$var241;
																																								{
																																									double var241 = current_metric_mean[server][traceTempVariable$currentState$95_1];
																																									cv$temp$16$var241 = var241;
																																								}
																																								double cv$temp$17$var243;
																																								{
																																									double var243 = current_metric_var[server][traceTempVariable$currentState$95_1];
																																									cv$temp$17$var243 = var243;
																																								}
																																								if(((Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$16$var241) / Math.sqrt(cv$temp$17$var243))) - (0.5 * Math.log(cv$temp$17$var243)))));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value93);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
											for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
												if((sample$var45 == sample$var196)) {
													for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
														if((timeStep$var66 == timeStep$var226)) {
															for(int server = 0; server < noServers; server += 1) {
																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																	if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																		guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																		{
																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																						if((index$sample$157_1 == sample$var196)) {
																							if((0 == timeStep$var226)) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									for(int var119 = 0; var119 < noServers; var119 += 1) {
																										for(int var129 = 0; var129 < noStates; var129 += 1) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												if((var119 == server)) {
																													if((var129 == st[sample$var196][timeStep$var226])) {
																														for(int var146 = 0; var146 < noServers; var146 += 1) {
																															for(int var156 = 0; var156 < noStates; var156 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var146 == server)) {
																																		if((var156 == st[sample$var196][timeStep$var226])) {
																																			{
																																				{
																																					double cv$temp$44$var241;
																																					{
																																						double var241 = current_metric_mean[server][traceTempVariable$currentState$73_1];
																																						cv$temp$44$var241 = var241;
																																					}
																																					double cv$temp$45$var243;
																																					{
																																						double var243 = current_metric_var[server][traceTempVariable$currentState$73_1];
																																						cv$temp$45$var243 = var243;
																																					}
																																					if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$44$var241) / Math.sqrt(cv$temp$45$var243))) - (0.5 * Math.log(cv$temp$45$var243)))));
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
																					if((index$sample$23 == sample$var196)) {
																						if((index$timeStep$22 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var146 == server)) {
																																	if((var156 == st[sample$var196][timeStep$var226])) {
																																		{
																																			{
																																				double cv$temp$46$var241;
																																				{
																																					double var241 = current_metric_mean[server][traceTempVariable$currentState$160_1];
																																					cv$temp$46$var241 = var241;
																																				}
																																				double cv$temp$47$var243;
																																				{
																																					double var243 = current_metric_var[server][traceTempVariable$currentState$160_1];
																																					cv$temp$47$var243 = var243;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$46$var241) / Math.sqrt(cv$temp$47$var243))) - (0.5 * Math.log(cv$temp$47$var243)))));
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
																								for(int index$sample76$163 = 0; index$sample76$163 < noStates; index$sample76$163 += 1) {
																									int distributionTempVariable$var74$165 = index$sample76$163;
																									double cv$probabilitySample76Value164 = (1.0 * distribution$sample76[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample76$163]);
																									int traceTempVariable$currentState$166_1 = cv$currentValue;
																									if((index$sample$161 == sample$var196)) {
																										if((index$timeStep$162 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																				if((var146 == server)) {
																																					if((var156 == st[sample$var196][timeStep$var226])) {
																																						{
																																							{
																																								double cv$temp$48$var241;
																																								{
																																									double var241 = current_metric_mean[server][traceTempVariable$currentState$166_1];
																																									cv$temp$48$var241 = var241;
																																								}
																																								double cv$temp$49$var243;
																																								{
																																									double var243 = current_metric_var[server][traceTempVariable$currentState$166_1];
																																									cv$temp$49$var243 = var243;
																																								}
																																								if(((Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$48$var241) / Math.sqrt(cv$temp$49$var243))) - (0.5 * Math.log(cv$temp$49$var243)))));
																																								}
																																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value164);
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
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
						for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
							int distributionTempVariable$var55$28 = index$sample57$26;
							double cv$probabilitySample57Value27 = (1.0 * distribution$sample57[((index$sample$25 - 0) / 1)][index$sample57$26]);
							if((index$sample$25 == sample$var45)) {
								if((0 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
											double[] cv$temp$2$var72;
											{
												double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
												cv$temp$2$var72 = var72;
											}
											int cv$temp$3$$var898;
											{
												int $var898 = noStates;
												cv$temp$3$$var898 = $var898;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var898))?Math.log(cv$temp$2$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var71$42_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$46_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var173 = 0; var173 < noServers; var173 += 1) {
																				for(int var183 = 0; var183 < noStates; var183 += 1) {
																					if((var173 == server)) {
																						if((var183 == st[sample$var196][timeStep$var226])) {
																							{
																								{
																									double cv$temp$9$var230;
																									{
																										double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$46_1];
																										cv$temp$9$var230 = var230;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$9$var230)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$9$var230)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$9$var230));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$9$var230)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$9$var230)));
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
													boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226])
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226])
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													int traceTempVariable$currentState$70_1 = cv$currentValue;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												int traceTempVariable$currentState$99_1 = distributionTempVariable$var55$28;
																												if((index$sample$25 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$18$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$99_1];
																																							cv$temp$18$var241 = var241;
																																						}
																																						double cv$temp$19$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$99_1];
																																							cv$temp$19$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$18$var241) / Math.sqrt(cv$temp$19$var243))) - (0.5 * Math.log(cv$temp$19$var243)))));
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
																														for(int index$sample57$101 = 0; index$sample57$101 < noStates; index$sample57$101 += 1) {
																															int distributionTempVariable$var55$103 = index$sample57$101;
																															double cv$probabilitySample57Value102 = (1.0 * distribution$sample57[((index$sample$100 - 0) / 1)][index$sample57$101]);
																															int traceTempVariable$currentState$104_1 = distributionTempVariable$var55$28;
																															if((index$sample$100 == sample$var196)) {
																																if((0 == timeStep$var226)) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		for(int var146 = 0; var146 < noServers; var146 += 1) {
																																			for(int var156 = 0; var156 < noStates; var156 += 1) {
																																				if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																					if((var146 == server)) {
																																						if((var156 == st[sample$var196][timeStep$var226])) {
																																							{
																																								{
																																									double cv$temp$20$var241;
																																									{
																																										double var241 = current_metric_mean[server][traceTempVariable$currentState$104_1];
																																										cv$temp$20$var241 = var241;
																																									}
																																									double cv$temp$21$var243;
																																									{
																																										double var243 = current_metric_var[server][traceTempVariable$currentState$104_1];
																																										cv$temp$21$var243 = var243;
																																									}
																																									if(((Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$20$var241) / Math.sqrt(cv$temp$21$var243))) - (0.5 * Math.log(cv$temp$21$var243)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value102);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												int traceTempVariable$currentState$108_1 = cv$currentValue;
																												if((index$sample$23 == sample$var196)) {
																													if((index$timeStep$22 == timeStep$var226)) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$22$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$108_1];
																																							cv$temp$22$var241 = var241;
																																						}
																																						double cv$temp$23$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$108_1];
																																							cv$temp$23$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$22$var241) / Math.sqrt(cv$temp$23$var243))) - (0.5 * Math.log(cv$temp$23$var243)))));
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
																															for(int index$sample76$111 = 0; index$sample76$111 < noStates; index$sample76$111 += 1) {
																																int distributionTempVariable$var74$113 = index$sample76$111;
																																double cv$probabilitySample76Value112 = (1.0 * distribution$sample76[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample76$111]);
																																int traceTempVariable$currentState$114_1 = cv$currentValue;
																																if((index$sample$109 == sample$var196)) {
																																	if((index$timeStep$110 == timeStep$var226)) {
																																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																						if((var146 == server)) {
																																							if((var156 == st[sample$var196][timeStep$var226])) {
																																								{
																																									{
																																										double cv$temp$24$var241;
																																										{
																																											double var241 = current_metric_mean[server][traceTempVariable$currentState$114_1];
																																											cv$temp$24$var241 = var241;
																																										}
																																										double cv$temp$25$var243;
																																										{
																																											double var243 = current_metric_var[server][traceTempVariable$currentState$114_1];
																																											cv$temp$25$var243 = var243;
																																										}
																																										if(((Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$24$var241) / Math.sqrt(cv$temp$25$var243))) - (0.5 * Math.log(cv$temp$25$var243)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value112);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							int traceTempVariable$currentState$171_1 = distributionTempVariable$var55$28;
																							if((index$sample$25 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$50$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$171_1];
																																							cv$temp$50$var241 = var241;
																																						}
																																						double cv$temp$51$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$171_1];
																																							cv$temp$51$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$50$var241) / Math.sqrt(cv$temp$51$var243))) - (0.5 * Math.log(cv$temp$51$var243)))));
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
																									for(int index$sample57$173 = 0; index$sample57$173 < noStates; index$sample57$173 += 1) {
																										int distributionTempVariable$var55$175 = index$sample57$173;
																										double cv$probabilitySample57Value174 = (1.0 * distribution$sample57[((index$sample$172 - 0) / 1)][index$sample57$173]);
																										int traceTempVariable$currentState$176_1 = distributionTempVariable$var55$28;
																										if((index$sample$172 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													for(int var119 = 0; var119 < noServers; var119 += 1) {
																														for(int var129 = 0; var129 < noStates; var129 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var119 == server)) {
																																	if((var129 == st[sample$var196][timeStep$var226])) {
																																		for(int var146 = 0; var146 < noServers; var146 += 1) {
																																			for(int var156 = 0; var156 < noStates; var156 += 1) {
																																				if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																					if((var146 == server)) {
																																						if((var156 == st[sample$var196][timeStep$var226])) {
																																							{
																																								{
																																									double cv$temp$52$var241;
																																									{
																																										double var241 = current_metric_mean[server][traceTempVariable$currentState$176_1];
																																										cv$temp$52$var241 = var241;
																																									}
																																									double cv$temp$53$var243;
																																									{
																																										double var243 = current_metric_var[server][traceTempVariable$currentState$176_1];
																																										cv$temp$53$var243 = var243;
																																									}
																																									if(((Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))) < cv$accumulatedConsumerProbabilities))
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																									else {
																																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243))));
																																										else
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$52$var241) / Math.sqrt(cv$temp$53$var243))) - (0.5 * Math.log(cv$temp$53$var243)))));
																																									}
																																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value174);
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
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
																							if((index$sample$23 == sample$var196)) {
																								if((index$timeStep$22 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$54$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$181_1];
																																							cv$temp$54$var241 = var241;
																																						}
																																						double cv$temp$55$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$181_1];
																																							cv$temp$55$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$54$var241) / Math.sqrt(cv$temp$55$var243))) - (0.5 * Math.log(cv$temp$55$var243)))));
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
																										for(int index$sample76$184 = 0; index$sample76$184 < noStates; index$sample76$184 += 1) {
																											int distributionTempVariable$var74$186 = index$sample76$184;
																											double cv$probabilitySample76Value185 = (1.0 * distribution$sample76[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample76$184]);
																											int traceTempVariable$currentState$187_1 = cv$currentValue;
																											if((index$sample$182 == sample$var196)) {
																												if((index$timeStep$183 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var119 = 0; var119 < noServers; var119 += 1) {
																															for(int var129 = 0; var129 < noStates; var129 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var119 == server)) {
																																		if((var129 == st[sample$var196][timeStep$var226])) {
																																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																						if((var146 == server)) {
																																							if((var156 == st[sample$var196][timeStep$var226])) {
																																								{
																																									{
																																										double cv$temp$56$var241;
																																										{
																																											double var241 = current_metric_mean[server][traceTempVariable$currentState$187_1];
																																											cv$temp$56$var241 = var241;
																																										}
																																										double cv$temp$57$var243;
																																										{
																																											double var243 = current_metric_var[server][traceTempVariable$currentState$187_1];
																																											cv$temp$57$var243 = var243;
																																										}
																																										if(((Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$56$var241) / Math.sqrt(cv$temp$57$var243))) - (0.5 * Math.log(cv$temp$57$var243)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value185);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
			int traceTempVariable$var71$32_1 = cv$currentValue;
			if((index$sample$23 == sample$var45)) {
				if((index$timeStep$22 == (timeStep$var66 - 1))) {
					for(int var32 = 0; var32 < noStates; var32 += 1) {
						if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$4$var72;
							{
								double[] var72 = m[traceTempVariable$var71$32_1];
								cv$temp$4$var72 = var72;
							}
							int cv$temp$5$$var899;
							{
								int $var899 = noStates;
								cv$temp$5$$var899 = $var899;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var899))?Math.log(cv$temp$4$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var71$43_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$47_1 = cv$currentValue;
									for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
										if((sample$var45 == sample$var196)) {
											for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int server = 0; server < noServers; server += 1) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var173 = 0; var173 < noServers; var173 += 1) {
																for(int var183 = 0; var183 < noStates; var183 += 1) {
																	if((var173 == server)) {
																		if((var183 == st[sample$var196][timeStep$var226])) {
																			{
																				{
																					double cv$temp$10$var230;
																					{
																						double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$47_1];
																						cv$temp$10$var230 = var230;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$10$var230)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$10$var230)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$10$var230));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$10$var230)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$10$var230)));
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
									boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global;
									for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
										if((sample$var45 == sample$var196)) {
											for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var196][server][timeStep$var226])
															guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
													}
												}
											}
										}
									}
									for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
										if((sample$var45 == sample$var196)) {
											for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var196][server][timeStep$var226])
															guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
													}
												}
											}
										}
									}
									int traceTempVariable$currentState$71_1 = cv$currentValue;
									for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
										if((sample$var45 == sample$var196)) {
											for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																{
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var119 = 0; var119 < noServers; var119 += 1) {
																				for(int var129 = 0; var129 < noStates; var129 += 1) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						if((var119 == server)) {
																							if((var129 == st[sample$var196][timeStep$var226])) {
																								if(fixedFlag$sample57) {
																									for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																										if((index$sample$118_1 == sample$var196)) {
																											if((0 == timeStep$var226)) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var146 == server)) {
																																	if((var156 == st[sample$var196][timeStep$var226])) {
																																		{
																																			{
																																				double cv$temp$26$var241;
																																				{
																																					double var241 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																					cv$temp$26$var241 = var241;
																																				}
																																				double cv$temp$27$var243;
																																				{
																																					double var243 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																					cv$temp$27$var243 = var243;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$26$var241) / Math.sqrt(cv$temp$27$var243))) - (0.5 * Math.log(cv$temp$27$var243)))));
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
																											for(int index$sample57$120 = 0; index$sample57$120 < noStates; index$sample57$120 += 1) {
																												int distributionTempVariable$var55$122 = index$sample57$120;
																												double cv$probabilitySample57Value121 = (1.0 * distribution$sample57[((index$sample$119 - 0) / 1)][index$sample57$120]);
																												if((index$sample$119 == sample$var196)) {
																													if((0 == timeStep$var226)) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$28$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$71_1];
																																							cv$temp$28$var241 = var241;
																																						}
																																						double cv$temp$29$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$71_1];
																																							cv$temp$29$var243 = var243;
																																						}
																																						if(((Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$28$var241) / Math.sqrt(cv$temp$29$var243))) - (0.5 * Math.log(cv$temp$29$var243)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value121);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int var119 = 0; var119 < noServers; var119 += 1) {
																				for(int var129 = 0; var129 < noStates; var129 += 1) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						if((var119 == server)) {
																							if((var129 == st[sample$var196][timeStep$var226])) {
																								int traceTempVariable$currentState$127_1 = cv$currentValue;
																								if((index$sample$23 == sample$var196)) {
																									if((index$timeStep$22 == timeStep$var226)) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$30$var241;
																																		{
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$127_1];
																																			cv$temp$30$var241 = var241;
																																		}
																																		double cv$temp$31$var243;
																																		{
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$127_1];
																																			cv$temp$31$var243 = var243;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$30$var241) / Math.sqrt(cv$temp$31$var243))) - (0.5 * Math.log(cv$temp$31$var243)))));
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
																											for(int index$sample76$130 = 0; index$sample76$130 < noStates; index$sample76$130 += 1) {
																												int distributionTempVariable$var74$132 = index$sample76$130;
																												double cv$probabilitySample76Value131 = (1.0 * distribution$sample76[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample76$130]);
																												int traceTempVariable$currentState$133_1 = cv$currentValue;
																												if((index$sample$128 == sample$var196)) {
																													if((index$timeStep$129 == timeStep$var226)) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$32$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$133_1];
																																							cv$temp$32$var241 = var241;
																																						}
																																						double cv$temp$33$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$133_1];
																																							cv$temp$33$var243 = var243;
																																						}
																																						if(((Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$32$var241) / Math.sqrt(cv$temp$33$var243))) - (0.5 * Math.log(cv$temp$33$var243)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value131);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
									for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
										if((sample$var45 == sample$var196)) {
											for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
												if((timeStep$var66 == timeStep$var226)) {
													for(int server = 0; server < noServers; server += 1) {
														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
															if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																{
																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample57) {
																				for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																					if((index$sample$192_1 == sample$var196)) {
																						if((0 == timeStep$var226)) {
																							if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																								for(int var119 = 0; var119 < noServers; var119 += 1) {
																									for(int var129 = 0; var129 < noStates; var129 += 1) {
																										if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																											if((var119 == server)) {
																												if((var129 == st[sample$var196][timeStep$var226])) {
																													for(int var146 = 0; var146 < noServers; var146 += 1) {
																														for(int var156 = 0; var156 < noStates; var156 += 1) {
																															if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																if((var146 == server)) {
																																	if((var156 == st[sample$var196][timeStep$var226])) {
																																		{
																																			{
																																				double cv$temp$58$var241;
																																				{
																																					double var241 = current_metric_mean[server][traceTempVariable$currentState$75_1];
																																					cv$temp$58$var241 = var241;
																																				}
																																				double cv$temp$59$var243;
																																				{
																																					double var243 = current_metric_var[server][traceTempVariable$currentState$75_1];
																																					cv$temp$59$var243 = var243;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$58$var241) / Math.sqrt(cv$temp$59$var243))) - (0.5 * Math.log(cv$temp$59$var243)))));
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
																						for(int index$sample57$194 = 0; index$sample57$194 < noStates; index$sample57$194 += 1) {
																							int distributionTempVariable$var55$196 = index$sample57$194;
																							double cv$probabilitySample57Value195 = (1.0 * distribution$sample57[((index$sample$193 - 0) / 1)][index$sample57$194]);
																							if((index$sample$193 == sample$var196)) {
																								if((0 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$60$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$75_1];
																																							cv$temp$60$var241 = var241;
																																						}
																																						double cv$temp$61$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$75_1];
																																							cv$temp$61$var243 = var243;
																																						}
																																						if(((Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$60$var241) / Math.sqrt(cv$temp$61$var243))) - (0.5 * Math.log(cv$temp$61$var243)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value195);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
																			if((index$sample$23 == sample$var196)) {
																				if((index$timeStep$22 == timeStep$var226)) {
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						for(int var119 = 0; var119 < noServers; var119 += 1) {
																							for(int var129 = 0; var129 < noStates; var129 += 1) {
																								if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																									if((var119 == server)) {
																										if((var129 == st[sample$var196][timeStep$var226])) {
																											for(int var146 = 0; var146 < noServers; var146 += 1) {
																												for(int var156 = 0; var156 < noStates; var156 += 1) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														if((var146 == server)) {
																															if((var156 == st[sample$var196][timeStep$var226])) {
																																{
																																	{
																																		double cv$temp$62$var241;
																																		{
																																			double var241 = current_metric_mean[server][traceTempVariable$currentState$202_1];
																																			cv$temp$62$var241 = var241;
																																		}
																																		double cv$temp$63$var243;
																																		{
																																			double var243 = current_metric_var[server][traceTempVariable$currentState$202_1];
																																			cv$temp$63$var243 = var243;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$62$var241) / Math.sqrt(cv$temp$63$var243))) - (0.5 * Math.log(cv$temp$63$var243)))));
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
																						for(int index$sample76$205 = 0; index$sample76$205 < noStates; index$sample76$205 += 1) {
																							int distributionTempVariable$var74$207 = index$sample76$205;
																							double cv$probabilitySample76Value206 = (1.0 * distribution$sample76[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample76$205]);
																							int traceTempVariable$currentState$208_1 = cv$currentValue;
																							if((index$sample$203 == sample$var196)) {
																								if((index$timeStep$204 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$64$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$208_1];
																																							cv$temp$64$var241 = var241;
																																						}
																																						double cv$temp$65$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$208_1];
																																							cv$temp$65$var243 = var243;
																																						}
																																						if(((Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$64$var241) / Math.sqrt(cv$temp$65$var243))) - (0.5 * Math.log(cv$temp$65$var243)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value206);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
						for(int index$sample76$35 = 0; index$sample76$35 < noStates; index$sample76$35 += 1) {
							int distributionTempVariable$var74$37 = index$sample76$35;
							double cv$probabilitySample76Value36 = (1.0 * distribution$sample76[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample76$35]);
							int traceTempVariable$var71$38_1 = cv$currentValue;
							if((index$sample$33 == sample$var45)) {
								if((index$timeStep$34 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value36);
											double[] cv$temp$6$var72;
											{
												double[] var72 = m[traceTempVariable$var71$38_1];
												cv$temp$6$var72 = var72;
											}
											int cv$temp$7$$var900;
											{
												int $var900 = noStates;
												cv$temp$7$$var900 = $var900;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var900))?Math.log(cv$temp$6$var72[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var71$44_1 = distributionTempVariable$var74$37;
												}
											}
											{
												{
													int traceTempVariable$currentState$48_1 = distributionTempVariable$var74$37;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var173 = 0; var173 < noServers; var173 += 1) {
																				for(int var183 = 0; var183 < noStates; var183 += 1) {
																					if((var173 == server)) {
																						if((var183 == st[sample$var196][timeStep$var226])) {
																							{
																								{
																									double cv$temp$11$var230;
																									{
																										double var230 = current_metric_valid_bias[server][traceTempVariable$currentState$48_1];
																										cv$temp$11$var230 = var230;
																									}
																									if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$11$var230)) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$11$var230)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$11$var230));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$11$var230)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], cv$temp$11$var230)));
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
													boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226])
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226])
																			guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = false;
																	}
																}
															}
														}
													}
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var74$37;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												if(fixedFlag$sample57) {
																													for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																														if((index$sample$137_1 == sample$var196)) {
																															if((0 == timeStep$var226)) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																				if((var146 == server)) {
																																					if((var156 == st[sample$var196][timeStep$var226])) {
																																						{
																																							{
																																								double cv$temp$34$var241;
																																								{
																																									double var241 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																									cv$temp$34$var241 = var241;
																																								}
																																								double cv$temp$35$var243;
																																								{
																																									double var243 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																									cv$temp$35$var243 = var243;
																																								}
																																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$34$var241) / Math.sqrt(cv$temp$35$var243))) - (0.5 * Math.log(cv$temp$35$var243)))));
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
																															for(int index$sample57$139 = 0; index$sample57$139 < noStates; index$sample57$139 += 1) {
																																int distributionTempVariable$var55$141 = index$sample57$139;
																																double cv$probabilitySample57Value140 = (1.0 * distribution$sample57[((index$sample$138 - 0) / 1)][index$sample57$139]);
																																if((index$sample$138 == sample$var196)) {
																																	if((0 == timeStep$var226)) {
																																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																						if((var146 == server)) {
																																							if((var156 == st[sample$var196][timeStep$var226])) {
																																								{
																																									{
																																										double cv$temp$36$var241;
																																										{
																																											double var241 = current_metric_mean[server][traceTempVariable$currentState$72_1];
																																											cv$temp$36$var241 = var241;
																																										}
																																										double cv$temp$37$var243;
																																										{
																																											double var243 = current_metric_var[server][traceTempVariable$currentState$72_1];
																																											cv$temp$37$var243 = var243;
																																										}
																																										if(((Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$36$var241) / Math.sqrt(cv$temp$37$var243))) - (0.5 * Math.log(cv$temp$37$var243)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value140);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							for(int var119 = 0; var119 < noServers; var119 += 1) {
																								for(int var129 = 0; var129 < noStates; var129 += 1) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										if((var119 == server)) {
																											if((var129 == st[sample$var196][timeStep$var226])) {
																												int traceTempVariable$currentState$146_1 = distributionTempVariable$var74$37;
																												if((index$sample$23 == sample$var196)) {
																													if((index$timeStep$22 == timeStep$var226)) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$38$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$146_1];
																																							cv$temp$38$var241 = var241;
																																						}
																																						double cv$temp$39$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$146_1];
																																							cv$temp$39$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$38$var241) / Math.sqrt(cv$temp$39$var243))) - (0.5 * Math.log(cv$temp$39$var243)))));
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
																												int traceTempVariable$currentState$147_1 = distributionTempVariable$var74$37;
																												if((index$sample$33 == sample$var196)) {
																													if((index$timeStep$34 == timeStep$var226)) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$40$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$147_1];
																																							cv$temp$40$var241 = var241;
																																						}
																																						double cv$temp$41$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$147_1];
																																							cv$temp$41$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$40$var241) / Math.sqrt(cv$temp$41$var243))) - (0.5 * Math.log(cv$temp$41$var243)))));
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
																															for(int index$sample76$150 = 0; index$sample76$150 < noStates; index$sample76$150 += 1) {
																																int distributionTempVariable$var74$152 = index$sample76$150;
																																double cv$probabilitySample76Value151 = (1.0 * distribution$sample76[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample76$150]);
																																int traceTempVariable$currentState$153_1 = distributionTempVariable$var74$37;
																																if((index$sample$148 == sample$var196)) {
																																	if((index$timeStep$149 == timeStep$var226)) {
																																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																						if((var146 == server)) {
																																							if((var156 == st[sample$var196][timeStep$var226])) {
																																								{
																																									{
																																										double cv$temp$42$var241;
																																										{
																																											double var241 = current_metric_mean[server][traceTempVariable$currentState$153_1];
																																											cv$temp$42$var241 = var241;
																																										}
																																										double cv$temp$43$var243;
																																										{
																																											double var243 = current_metric_var[server][traceTempVariable$currentState$153_1];
																																											cv$temp$43$var243 = var243;
																																										}
																																										if(((Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$42$var241) / Math.sqrt(cv$temp$43$var243))) - (0.5 * Math.log(cv$temp$43$var243)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value151);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
													int traceTempVariable$currentState$76_1 = distributionTempVariable$var74$37;
													for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
														if((sample$var45 == sample$var196)) {
															for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
																if((timeStep$var66 == timeStep$var226)) {
																	for(int server = 0; server < noServers; server += 1) {
																		if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																			if(!guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)]) {
																				guard$sample76gaussian255[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = true;
																				{
																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																						double cv$consumerDistributionProbabilityAccumulator = 1.0;
																						{
																							if(fixedFlag$sample57) {
																								for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																									if((index$sample$213_1 == sample$var196)) {
																										if((0 == timeStep$var226)) {
																											if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																												for(int var119 = 0; var119 < noServers; var119 += 1) {
																													for(int var129 = 0; var129 < noStates; var129 += 1) {
																														if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																															if((var119 == server)) {
																																if((var129 == st[sample$var196][timeStep$var226])) {
																																	for(int var146 = 0; var146 < noServers; var146 += 1) {
																																		for(int var156 = 0; var156 < noStates; var156 += 1) {
																																			if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																				if((var146 == server)) {
																																					if((var156 == st[sample$var196][timeStep$var226])) {
																																						{
																																							{
																																								double cv$temp$66$var241;
																																								{
																																									double var241 = current_metric_mean[server][traceTempVariable$currentState$76_1];
																																									cv$temp$66$var241 = var241;
																																								}
																																								double cv$temp$67$var243;
																																								{
																																									double var243 = current_metric_var[server][traceTempVariable$currentState$76_1];
																																									cv$temp$67$var243 = var243;
																																								}
																																								if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))) < cv$accumulatedConsumerProbabilities))
																																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																								else {
																																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																										cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243))));
																																									else
																																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$66$var241) / Math.sqrt(cv$temp$67$var243))) - (0.5 * Math.log(cv$temp$67$var243)))));
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
																										for(int index$sample57$215 = 0; index$sample57$215 < noStates; index$sample57$215 += 1) {
																											int distributionTempVariable$var55$217 = index$sample57$215;
																											double cv$probabilitySample57Value216 = (1.0 * distribution$sample57[((index$sample$214 - 0) / 1)][index$sample57$215]);
																											if((index$sample$214 == sample$var196)) {
																												if((0 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var119 = 0; var119 < noServers; var119 += 1) {
																															for(int var129 = 0; var129 < noStates; var129 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var119 == server)) {
																																		if((var129 == st[sample$var196][timeStep$var226])) {
																																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																						if((var146 == server)) {
																																							if((var156 == st[sample$var196][timeStep$var226])) {
																																								{
																																									{
																																										double cv$temp$68$var241;
																																										{
																																											double var241 = current_metric_mean[server][traceTempVariable$currentState$76_1];
																																											cv$temp$68$var241 = var241;
																																										}
																																										double cv$temp$69$var243;
																																										{
																																											double var243 = current_metric_var[server][traceTempVariable$currentState$76_1];
																																											cv$temp$69$var243 = var243;
																																										}
																																										if(((Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))))) + 1)) + (Math.log(cv$probabilitySample57Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$68$var241) / Math.sqrt(cv$temp$69$var243))) - (0.5 * Math.log(cv$temp$69$var243)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample57Value216);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																							int traceTempVariable$currentState$223_1 = distributionTempVariable$var74$37;
																							if((index$sample$23 == sample$var196)) {
																								if((index$timeStep$22 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$70$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$223_1];
																																							cv$temp$70$var241 = var241;
																																						}
																																						double cv$temp$71$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$223_1];
																																							cv$temp$71$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$70$var241) / Math.sqrt(cv$temp$71$var243))) - (0.5 * Math.log(cv$temp$71$var243)))));
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
																							int traceTempVariable$currentState$224_1 = distributionTempVariable$var74$37;
																							if((index$sample$33 == sample$var196)) {
																								if((index$timeStep$34 == timeStep$var226)) {
																									if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																										for(int var119 = 0; var119 < noServers; var119 += 1) {
																											for(int var129 = 0; var129 < noStates; var129 += 1) {
																												if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																													if((var119 == server)) {
																														if((var129 == st[sample$var196][timeStep$var226])) {
																															for(int var146 = 0; var146 < noServers; var146 += 1) {
																																for(int var156 = 0; var156 < noStates; var156 += 1) {
																																	if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																		if((var146 == server)) {
																																			if((var156 == st[sample$var196][timeStep$var226])) {
																																				{
																																					{
																																						double cv$temp$72$var241;
																																						{
																																							double var241 = current_metric_mean[server][traceTempVariable$currentState$224_1];
																																							cv$temp$72$var241 = var241;
																																						}
																																						double cv$temp$73$var243;
																																						{
																																							double var243 = current_metric_var[server][traceTempVariable$currentState$224_1];
																																							cv$temp$73$var243 = var243;
																																						}
																																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$72$var241) / Math.sqrt(cv$temp$73$var243))) - (0.5 * Math.log(cv$temp$73$var243)))));
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
																										for(int index$sample76$227 = 0; index$sample76$227 < noStates; index$sample76$227 += 1) {
																											int distributionTempVariable$var74$229 = index$sample76$227;
																											double cv$probabilitySample76Value228 = (1.0 * distribution$sample76[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample76$227]);
																											int traceTempVariable$currentState$230_1 = distributionTempVariable$var74$37;
																											if((index$sample$225 == sample$var196)) {
																												if((index$timeStep$226 == timeStep$var226)) {
																													if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																														for(int var119 = 0; var119 < noServers; var119 += 1) {
																															for(int var129 = 0; var129 < noStates; var129 += 1) {
																																if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																	if((var119 == server)) {
																																		if((var129 == st[sample$var196][timeStep$var226])) {
																																			for(int var146 = 0; var146 < noServers; var146 += 1) {
																																				for(int var156 = 0; var156 < noStates; var156 += 1) {
																																					if(metric_valid_g[sample$var196][server][timeStep$var226]) {
																																						if((var146 == server)) {
																																							if((var156 == st[sample$var196][timeStep$var226])) {
																																								{
																																									{
																																										double cv$temp$74$var241;
																																										{
																																											double var241 = current_metric_mean[server][traceTempVariable$currentState$230_1];
																																											cv$temp$74$var241 = var241;
																																										}
																																										double cv$temp$75$var243;
																																										{
																																											double var243 = current_metric_var[server][traceTempVariable$currentState$230_1];
																																											cv$temp$75$var243 = var243;
																																										}
																																										if(((Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))) < cv$accumulatedConsumerProbabilities))
																																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																										else {
																																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243))));
																																											else
																																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))))) + 1)) + (Math.log(cv$probabilitySample76Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - cv$temp$74$var241) / Math.sqrt(cv$temp$75$var243))) - (0.5 * Math.log(cv$temp$75$var243)))));
																																										}
																																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample76Value228);
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
					int traceTempVariable$var71$269_1 = cv$currentValue;
					for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
						if((sample$var45 == index$sample$269_2)) {
							for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2][0]; index$timeStep$269_3 += 1) {
								if((timeStep$var66 == (index$timeStep$269_3 - 1))) {
									{
										int index$timeStep$271 = index$timeStep$269_3;
										int index$sample$272 = index$sample$269_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var32 = 0; var32 < noStates; var32 += 1) {
											if((var32 == st[index$sample$269_2][(index$timeStep$269_3 - 1)])) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample57) {
														for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
															if((index$sample$274_1 == sample$var45)) {
																if((0 == (timeStep$var66 - 1))) {
																	for(int index$var32$280_1 = 0; index$var32$280_1 < noStates; index$var32$280_1 += 1) {
																		if((index$var32$280_1 == st[sample$var45][(timeStep$var66 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
															if(true) {
																for(int index$sample57$276 = 0; index$sample57$276 < noStates; index$sample57$276 += 1) {
																	int distributionTempVariable$var55$278 = index$sample57$276;
																	double cv$probabilitySample57Value277 = (1.0 * distribution$sample57[((index$sample$275 - 0) / 1)][index$sample57$276]);
																	if((index$sample$275 == sample$var45)) {
																		if((0 == (timeStep$var66 - 1))) {
																			for(int index$var32$281_1 = 0; index$var32$281_1 < noStates; index$var32$281_1 += 1) {
																				if((index$var32$281_1 == st[sample$var45][(timeStep$var66 - 1)]))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value277);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var71$282_1 = cv$currentValue;
													if((index$sample$23 == sample$var45)) {
														if((index$timeStep$22 == (timeStep$var66 - 1))) {
															for(int index$var32$289_1 = 0; index$var32$289_1 < noStates; index$var32$289_1 += 1) {
																if((index$var32$289_1 == st[sample$var45][(timeStep$var66 - 1)]))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
														for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283][0]; index$timeStep$284 += 1) {
															if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																for(int index$sample76$285 = 0; index$sample76$285 < noStates; index$sample76$285 += 1) {
																	int distributionTempVariable$var74$287 = index$sample76$285;
																	double cv$probabilitySample76Value286 = (1.0 * distribution$sample76[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample76$285]);
																	int traceTempVariable$var71$288_1 = cv$currentValue;
																	if((index$sample$283 == sample$var45)) {
																		if((index$timeStep$284 == (timeStep$var66 - 1))) {
																			for(int index$var32$290_1 = 0; index$var32$290_1 < noStates; index$var32$290_1 += 1) {
																				if((index$var32$290_1 == st[sample$var45][(timeStep$var66 - 1)]))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample76Value286);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$76$var72;
													{
														double[] var72 = m[traceTempVariable$var71$269_1];
														cv$temp$76$var72 = var72;
													}
													int cv$temp$77$$var1400;
													{
														int $var1400 = noStates;
														cv$temp$77$$var1400 = $var1400;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$76$var72, cv$temp$77$$var1400);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample76[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
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

	@Override
	public final void allocateScratch() {
		{
			cv$var20$countGlobal = new double[noStates];
		}
		{
			cv$var33$countGlobal = new double[noStates];
		}
		{
			int cv$var34$max = noStates;
			cv$distributionAccumulator$var73 = new double[cv$var34$max];
		}
		{
			cv$var55$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_sample$var196 = 0;
			int cv$max_server = 0;
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((length$metric[sample$var196][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((length$metric.length - 0) / 1));
			guard$sample57gaussian255$global = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
		}
		{
			int cv$var34$max = noStates;
			cv$var74$stateProbabilityGlobal = new double[cv$var34$max];
		}
		{
			int cv$max_sample$var196 = 0;
			int cv$max_server = 0;
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, ((length$metric[sample$var196][0] - 0) / 1));
				cv$max_server = Math.max(cv$max_server, ((length$metric[0].length - 0) / 1));
			}
			cv$max_sample$var196 = Math.max(cv$max_sample$var196, ((length$metric.length - 0) / 1));
			guard$sample76gaussian255$global = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
		}
		{
			cv$var232$stateProbabilityGlobal = new double[2];
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
				for(int var32 = 0; var32 < noStates; var32 += 1)
					m[var32] = new double[noStates];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$metric.length][];
				for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
					st[sample$var45] = new int[length$metric[sample$var45][0]];
			}
		}
		if(!setFlag$metric_g) {
			{
				metric_g = new double[length$metric.length][][];
				for(int var90 = 0; var90 < length$metric.length; var90 += 1) {
					double[][] subarray$0 = new double[length$metric[0].length][];
					metric_g[var90] = subarray$0;
				}
				for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						double[][] subarray$1 = metric_g[sample$var196];
						subarray$1[server] = new double[length$metric[sample$var196][0]];
					}
				}
			}
		}
		if(!setFlag$metric_valid_g) {
			{
				metric_valid_g = new boolean[length$metric.length][][];
				for(int var103 = 0; var103 < length$metric.length; var103 += 1) {
					boolean[][] subarray$0 = new boolean[length$metric[0].length][];
					metric_valid_g[var103] = subarray$0;
				}
				for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
					for(int server = 0; server < length$metric[0].length; server += 1) {
						boolean[][] subarray$1 = metric_valid_g[sample$var196];
						subarray$1[server] = new boolean[length$metric[sample$var196][0]];
					}
				}
			}
		}
		if(!setFlag$current_metric_mean) {
			{
				current_metric_mean = new double[length$metric[0].length][];
				for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
					current_metric_mean[var119] = new double[noStates];
			}
		}
		if(!setFlag$current_metric_var) {
			{
				current_metric_var = new double[length$metric[0].length][];
				for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
					current_metric_var[var146] = new double[noStates];
			}
		}
		if(!setFlag$current_metric_valid_bias) {
			{
				current_metric_valid_bias = new double[length$metric[0].length][];
				for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
					current_metric_valid_bias[var173] = new double[noStates];
			}
		}
		{
			distribution$sample57 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				distribution$sample57[((sample$var45 - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample76 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample$var45][0] - 1) - 1) / 1) + 1)][];
				distribution$sample76[((sample$var45 - 0) / 1)] = subarray$0;
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					subarray$0[((timeStep$var66 - 1) / 1)] = new double[noStates];
			}
		}
		{
			logProbability$var54 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample57 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var73 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				logProbability$var73[((sample$var45 - 0) / 1)] = new double[((((length$metric[sample$var45][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample76 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				logProbability$sample76[((sample$var45 - 0) / 1)] = new double[((((length$metric[sample$var45][0] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var231 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var231[((sample$var196 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var196][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample241 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample241[((sample$var196 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var196][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$var244 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$var244[((sample$var196 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var196][0] - 1) - 0) / 1) + 1)];
			}
		}
		{
			logProbability$sample256 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				double[][] subarray$0 = new double[((((length$metric[0].length - 1) - 0) / 1) + 1)][];
				logProbability$sample256[((sample$var196 - 0) / 1)] = subarray$0;
				for(int server = 0; server < length$metric[0].length; server += 1)
					subarray$0[((server - 0) / 1)] = new double[((((length$metric[sample$var196][0] - 1) - 0) / 1) + 1)];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var32 = 0; var32 < noStates; var32 += 1) {
			double[] var33 = m[var32];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			int[] var52 = st[sample$var45];
			if(!fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var67 = st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		for(int var119 = 0; var119 < noServers; var119 += 1) {
			double[] var120 = current_metric_mean[var119];
			for(int var129 = 0; var129 < noStates; var129 += 1) {
				if(!fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var146 = 0; var146 < noServers; var146 += 1) {
			double[] var147 = current_metric_var[var146];
			for(int var156 = 0; var156 < noStates; var156 += 1) {
				if(!fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < noServers; var173 += 1) {
			double[] var174 = current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < noStates; var183 += 1) {
				if(!fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			boolean[][] var215 = metric_valid_g[sample$var196];
			double[][] var211 = metric_g[sample$var196];
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				double[] metric_inner = var211[server];
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(!fixedFlag$sample241)
						metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
					if(var215[server][timeStep$var226]) {
						if(!(fixedFlag$sample241 && fixedFlag$sample256))
							metric_inner[timeStep$var226] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
					}
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var32 = 0; var32 < noStates; var32 += 1) {
			double[] var33 = m[var32];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			double[] cv$distribution$sample57 = distribution$sample57[((sample$var45 - 0) / 1)];
			for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1) {
				double cv$value = (((0.0 <= index$var54) && (index$var54 < noStates))?initialStateDistribution[index$var54]:0.0);
				if(!fixedFlag$sample57)
					cv$distribution$sample57[index$var54] = cv$value;
			}
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
				double[] cv$distribution$sample76 = distribution$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)];
				for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
					if(!fixedFlag$sample76)
						cv$distribution$sample76[index$var73] = 0.0;
				}
				if(fixedFlag$sample57) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample$var45)) {
							if((0 == (timeStep$var66 - 1))) {
								for(int var32 = 0; var32 < noStates; var32 += 1) {
									if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
										{
											if(!fixedFlag$sample76) {
												double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
												for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
													cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
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
							for(int index$sample57$3 = 0; index$sample57$3 < noStates; index$sample57$3 += 1) {
								int distributionTempVariable$var55$5 = index$sample57$3;
								double cv$probabilitySample57Value4 = (1.0 * distribution$sample57[((index$sample$2 - 0) / 1)][index$sample57$3]);
								if((index$sample$2 == sample$var45)) {
									if((0 == (timeStep$var66 - 1))) {
										for(int var32 = 0; var32 < noStates; var32 += 1) {
											if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
												{
													if(!fixedFlag$sample76) {
														double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
														for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
															cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample76) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1][0]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample$var45)) {
								if((index$timeStep$9_2 == (timeStep$var66 - 1))) {
									for(int var32 = 0; var32 < noStates; var32 += 1) {
										if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
											{
												if(!fixedFlag$sample76) {
													double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
													for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
														cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (1.0 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
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
								for(int index$sample76$12 = 0; index$sample76$12 < noStates; index$sample76$12 += 1) {
									int distributionTempVariable$var74$14 = index$sample76$12;
									double cv$probabilitySample76Value13 = (1.0 * distribution$sample76[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample76$12]);
									if((index$sample$10 == sample$var45)) {
										if((index$timeStep$11 == (timeStep$var66 - 1))) {
											for(int var32 = 0; var32 < noStates; var32 += 1) {
												if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
													{
														if(!fixedFlag$sample76) {
															double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
															for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
																cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * (((0.0 <= index$var73) && (index$var73 < noStates))?var72[index$var73]:0.0)));
														}
													}
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
					if(!fixedFlag$sample76)
						cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
				}
				for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1) {
					if(!fixedFlag$sample76)
						cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
				}
			}
		}
		for(int var119 = 0; var119 < noServers; var119 += 1) {
			double[] var120 = current_metric_mean[var119];
			for(int var129 = 0; var129 < noStates; var129 += 1) {
				if(!fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var146 = 0; var146 < noServers; var146 += 1) {
			double[] var147 = current_metric_var[var146];
			for(int var156 = 0; var156 < noStates; var156 += 1) {
				if(!fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < noServers; var173 += 1) {
			double[] var174 = current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < noStates; var183 += 1) {
				if(!fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(!fixedFlag$sample241)
						metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var32 = 0; var32 < noStates; var32 += 1) {
			double[] var33 = m[var32];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			int[] var52 = st[sample$var45];
			if(!fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var67 = st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		for(int var119 = 0; var119 < noServers; var119 += 1) {
			double[] var120 = current_metric_mean[var119];
			for(int var129 = 0; var129 < noStates; var129 += 1) {
				if(!fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var146 = 0; var146 < noServers; var146 += 1) {
			double[] var147 = current_metric_var[var146];
			for(int var156 = 0; var156 < noStates; var156 += 1) {
				if(!fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < noServers; var173 += 1) {
			double[] var174 = current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < noStates; var183 += 1) {
				if(!fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if(!fixedFlag$sample241)
						metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			for(int var32 = 0; var32 < noStates; var32 += 1) {
				if(!fixedFlag$sample33)
					sample33(var32);
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if(!fixedFlag$sample57)
					sample57(sample$var45);
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if(!fixedFlag$sample76)
						sample76(sample$var45, timeStep$var66);
				}
			}
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1) {
					if(!fixedFlag$sample134)
						sample134(var119, var129);
				}
			}
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1) {
					if(!fixedFlag$sample162)
						sample162(var146, var156);
				}
			}
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1) {
					if(!fixedFlag$sample190)
						sample190(var173, var183);
				}
			}
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						if(!fixedFlag$sample241)
							sample241(sample$var196, server, timeStep$var226);
					}
				}
			}
		} else {
			for(int sample$var196 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var196 >= ((0 - 1) + 1); sample$var196 -= 1) {
				for(int server = (noServers - ((((noServers - 1) - 0) % 1) + 1)); server >= ((0 - 1) + 1); server -= 1) {
					for(int timeStep$var226 = (length$metric[sample$var196][0] - ((((length$metric[sample$var196][0] - 1) - 0) % 1) + 1)); timeStep$var226 >= ((0 - 1) + 1); timeStep$var226 -= 1) {
						if(!fixedFlag$sample241)
							sample241(sample$var196, server, timeStep$var226);
					}
				}
			}
			for(int var173 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var173 >= ((0 - 1) + 1); var173 -= 1) {
				for(int var183 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var183 >= ((0 - 1) + 1); var183 -= 1) {
					if(!fixedFlag$sample190)
						sample190(var173, var183);
				}
			}
			for(int var146 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var146 >= ((0 - 1) + 1); var146 -= 1) {
				for(int var156 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var156 >= ((0 - 1) + 1); var156 -= 1) {
					if(!fixedFlag$sample162)
						sample162(var146, var156);
				}
			}
			for(int var119 = (noServers - ((((noServers - 1) - 0) % 1) + 1)); var119 >= ((0 - 1) + 1); var119 -= 1) {
				for(int var129 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var129 >= ((0 - 1) + 1); var129 -= 1) {
					if(!fixedFlag$sample134)
						sample134(var119, var129);
				}
			}
			for(int sample$var45 = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample$var45 >= ((0 - 1) + 1); sample$var45 -= 1) {
				for(int timeStep$var66 = (length$metric[sample$var45][0] - ((((length$metric[sample$var45][0] - 1) - 1) % 1) + 1)); timeStep$var66 >= ((1 - 1) + 1); timeStep$var66 -= 1) {
					if(!fixedFlag$sample76)
						sample76(sample$var45, timeStep$var66);
				}
				if(!fixedFlag$sample57)
					sample57(sample$var45);
			}
			for(int var32 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var32 >= ((0 - 1) + 1); var32 -= 1) {
				if(!fixedFlag$sample33)
					sample33(var32);
			}
			if(!fixedFlag$sample20)
				sample20();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var16 = 0; var16 < noStates; var16 += 1)
			v[var16] = 0.1;
		noServers = length$metric[0].length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var19 = 0.0;
		if(!fixedProbFlag$sample20)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var21 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample33)
			logProbability$var33 = 0.0;
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
			logProbability$var54[((sample$var45 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				logProbability$sample57[((sample$var45 - 0) / 1)] = 0.0;
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
				logProbability$var73[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					logProbability$sample76[((sample$var45 - 0) / 1)][((timeStep$var66 - 1) / 1)] = 0.0;
			}
		}
		logProbability$var108 = 0.0;
		logProbability$current_metric_mean = 0.0;
		if(!fixedProbFlag$sample134)
			logProbability$var130 = 0.0;
		logProbability$var135 = 0.0;
		logProbability$current_metric_var = 0.0;
		if(!fixedProbFlag$sample162)
			logProbability$var157 = 0.0;
		logProbability$var162 = 0.0;
		logProbability$current_metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample190)
			logProbability$var184 = 0.0;
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
					logProbability$var231[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample241) {
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						logProbability$sample241[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = 0.0;
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
					logProbability$var244[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = 0.0;
			}
		}
		if(!fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						logProbability$sample256[((sample$var196 - 0) / 1)][((server - 0) / 1)][((timeStep$var226 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample20)
			logProbabilityValue$sample20();
		if(fixedFlag$sample33)
			logProbabilityValue$sample33();
		if(fixedFlag$sample134)
			logProbabilityValue$sample134();
		if(fixedFlag$sample162)
			logProbabilityValue$sample162();
		if(fixedFlag$sample190)
			logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityDistribution$sample241();
		logProbabilityDistribution$sample256();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample20();
		logProbabilityValue$sample33();
		logProbabilityValue$sample57();
		logProbabilityValue$sample76();
		logProbabilityValue$sample134();
		logProbabilityValue$sample162();
		logProbabilityValue$sample190();
		logProbabilityValue$sample241();
		logProbabilityValue$sample256();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		for(int var32 = 0; var32 < noStates; var32 += 1) {
			double[] var33 = m[var32];
			if(!fixedFlag$sample33)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var33);
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			int[] var52 = st[sample$var45];
			if(!fixedFlag$sample57)
				var52[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution, noStates);
			int[] var67 = st[sample$var45];
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
				if(!fixedFlag$sample76)
					var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
			}
		}
		for(int var119 = 0; var119 < noServers; var119 += 1) {
			double[] var120 = current_metric_mean[var119];
			for(int var129 = 0; var129 < noStates; var129 += 1) {
				if(!fixedFlag$sample134)
					var120[var129] = (0.0 + (((double)max_metric - 0.0) * DistributionSampling.sampleUniform(RNG$)));
			}
		}
		for(int var146 = 0; var146 < noServers; var146 += 1) {
			double[] var147 = current_metric_var[var146];
			for(int var156 = 0; var156 < noStates; var156 += 1) {
				if(!fixedFlag$sample162)
					var147[var156] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		for(int var173 = 0; var173 < noServers; var173 += 1) {
			double[] var174 = current_metric_valid_bias[var173];
			for(int var183 = 0; var183 < noStates; var183 += 1) {
				if(!fixedFlag$sample190)
					var174[var183] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
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