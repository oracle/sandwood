package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics4$CoreInterface {
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[][] cv$distributionAccumulator$var73;
	private double[] cv$var20$countGlobal;
	private double[][] cv$var33$countGlobal;
	private double[][] cv$var55$stateProbabilityGlobal;
	private double[][] cv$var74$stateProbabilityGlobal;
	private double[][] distribution$sample57;
	private double[][][] distribution$sample76;
	private boolean fixedFlag$sample134 = false;
	private boolean fixedFlag$sample162 = false;
	private boolean fixedFlag$sample190 = false;
	private boolean fixedFlag$sample20 = false;
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
	private boolean[][][][] guard$sample57gaussian255$global;
	private boolean[][][][] guard$sample76gaussian255$global;
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
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMMetrics4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$current_metric_mean() {
		return current_metric_mean;
	}

	@Override
	public final void set$current_metric_mean(double[][] cv$value) {
		current_metric_mean = cv$value;
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
		fixedProbFlag$sample162 = false;
		fixedProbFlag$sample256 = false;
	}

	@Override
	public final double[][] get$distribution$sample57() {
		return distribution$sample57;
	}

	@Override
	public final void set$distribution$sample57(double[][] cv$value) {
		distribution$sample57 = cv$value;
	}

	@Override
	public final double[][][] get$distribution$sample76() {
		return distribution$sample76;
	}

	@Override
	public final void set$distribution$sample76(double[][][] cv$value) {
		distribution$sample76 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample134() {
		return fixedFlag$sample134;
	}

	@Override
	public final void set$fixedFlag$sample134(boolean cv$value) {
		fixedFlag$sample134 = cv$value;
		fixedProbFlag$sample134 = (cv$value && fixedProbFlag$sample134);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample162() {
		return fixedFlag$sample162;
	}

	@Override
	public final void set$fixedFlag$sample162(boolean cv$value) {
		fixedFlag$sample162 = cv$value;
		fixedProbFlag$sample162 = (cv$value && fixedProbFlag$sample162);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample190() {
		return fixedFlag$sample190;
	}

	@Override
	public final void set$fixedFlag$sample190(boolean cv$value) {
		fixedFlag$sample190 = cv$value;
		fixedProbFlag$sample190 = (cv$value && fixedProbFlag$sample190);
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
	}

	@Override
	public final boolean get$fixedFlag$sample20() {
		return fixedFlag$sample20;
	}

	@Override
	public final void set$fixedFlag$sample20(boolean cv$value) {
		fixedFlag$sample20 = cv$value;
		fixedProbFlag$sample20 = (cv$value && fixedProbFlag$sample20);
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
	}

	@Override
	public final boolean get$fixedFlag$sample256() {
		return fixedFlag$sample256;
	}

	@Override
	public final void set$fixedFlag$sample256(boolean cv$value) {
		fixedFlag$sample256 = cv$value;
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample33() {
		return fixedFlag$sample33;
	}

	@Override
	public final void set$fixedFlag$sample33(boolean cv$value) {
		fixedFlag$sample33 = cv$value;
		fixedProbFlag$sample33 = (cv$value && fixedProbFlag$sample33);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final boolean get$fixedFlag$sample76() {
		return fixedFlag$sample76;
	}

	@Override
	public final void set$fixedFlag$sample76(boolean cv$value) {
		fixedFlag$sample76 = cv$value;
		fixedProbFlag$sample76 = (cv$value && fixedProbFlag$sample76);
		fixedProbFlag$sample241 = (cv$value && fixedProbFlag$sample241);
		fixedProbFlag$sample256 = (cv$value && fixedProbFlag$sample256);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						boolean cv$sampleValue = metric_valid_g[sample$var196][server][timeStep$var226];
						if((0 == timeStep$var226)) {
							if(fixedFlag$sample57) {
								int var183 = st[sample$var196][0];
								if(((0 <= var183) && (var183 < noStates))) {
									cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var196][0]]);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
									double cv$probabilitySample57Value5 = distribution$sample57[sample$var196][index$sample57$4];
									int var183 = st[sample$var196][0];
									if(((0 <= var183) && (var183 < noStates))) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var196][0]]));
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
						if((1 <= timeStep$var226)) {
							if(fixedFlag$sample76) {
								int var183 = st[sample$var196][timeStep$var226];
								if(((0 <= var183) && (var183 < noStates))) {
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
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
							} else {
								for(int index$sample76$13 = 0; index$sample76$13 < noStates; index$sample76$13 += 1) {
									double cv$probabilitySample76Value14 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$13];
									int var183 = st[sample$var196][timeStep$var226];
									if(((0 <= var183) && (var183 < noStates))) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample76Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var231[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$sampleValue = logProbability$sample241[sample$var196][server][timeStep$var226];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var231[sample$var196][server][timeStep$var226] = cv$sampleValue;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							double cv$sampleValue = metric_g[sample$var196][server][timeStep$var226];
							if((((0 == timeStep$var226) && metric_valid_g[sample$var196][server][0]) && (0 <= st[sample$var196][0]))) {
								if(fixedFlag$sample57) {
									int var129 = st[sample$var196][0];
									if(((0 <= var129) && (var129 < noStates))) {
										double var243 = current_metric_var[server][st[sample$var196][0]];
										cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][0]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5));
										cv$probabilityReached = 1.0;
									}
								} else {
									for(int index$sample57$4 = 0; index$sample57$4 < noStates; index$sample57$4 += 1) {
										double cv$probabilitySample57Value5 = distribution$sample57[sample$var196][index$sample57$4];
										int var129 = st[sample$var196][0];
										if(((0 <= var129) && (var129 < noStates))) {
											double var243 = current_metric_var[server][st[sample$var196][0]];
											double cv$weightedProbability = ((Math.log(cv$probabilitySample57Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][0]]) / Math.sqrt(var243)))) - (Math.log(var243) * 0.5));
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
							if(((1 <= timeStep$var226) && (0 <= st[sample$var196][timeStep$var226]))) {
								if(fixedFlag$sample76) {
									int var129 = st[sample$var196][timeStep$var226];
									if(((0 <= var129) && (var129 < noStates))) {
										double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
										double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5));
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
								} else {
									for(int index$sample76$49 = 0; index$sample76$49 < noStates; index$sample76$49 += 1) {
										double cv$probabilitySample76Value50 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$49];
										int var129 = st[sample$var196][timeStep$var226];
										if(((0 <= var129) && (var129 < noStates))) {
											double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
											double cv$weightedProbability = ((Math.log(cv$probabilitySample76Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243)))) - (Math.log(var243) * 0.5));
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
							if((cv$probabilityReached == 0.0))
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var244[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
							logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
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
							double cv$rvAccumulator = logProbability$sample256[sample$var196][server][timeStep$var226];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var244[sample$var196][server][timeStep$var226] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var45][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var54[sample$var45] = cv$distributionAccumulator;
					logProbability$sample57[sample$var45] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = fixedFlag$sample20;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = logProbability$sample57[sample$var45];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[sample$var45] = cv$rvAccumulator;
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample$var45][timeStep$var66];
						if((1 == timeStep$var66)) {
							if(fixedFlag$sample57) {
								int var32 = st[sample$var45][0];
								if(((0 <= var32) && (var32 < noStates))) {
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
									double cv$probabilitySample57Value7 = distribution$sample57[sample$var45][index$sample57$6];
									int var32 = st[sample$var45][0];
									if(((0 <= var32) && (var32 < noStates))) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample57Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if((2 <= timeStep$var66)) {
							int var32 = st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= var32) && (var32 < noStates))) {
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][(timeStep$var66 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var73[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
						logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample76 = (fixedFlag$sample33 && fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					double cv$rvAccumulator = logProbability$sample76[sample$var45][(timeStep$var66 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[sample$var45][(timeStep$var66 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var119 = 0; var119 < noServers; var119 += 1) {
				for(int var129 = 0; var129 < noStates; var129 += 1) {
					double cv$sampleValue = current_metric_mean[var119][var129];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var108 = cv$sampleAccumulator;
			logProbability$var130 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample134 = fixedFlag$sample134;
		} else {
			logProbability$var108 = logProbability$var130;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var130);
			logProbability$$model = (logProbability$$model + logProbability$var130);
			if(fixedFlag$sample134)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var130);
		}
	}

	private final void logProbabilityValue$sample162() {
		if(!fixedProbFlag$sample162) {
			double cv$sampleAccumulator = 0.0;
			for(int var146 = 0; var146 < noServers; var146 += 1) {
				for(int var156 = 0; var156 < noStates; var156 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var146][var156], 1.0, 1.0));
			}
			logProbability$var135 = cv$sampleAccumulator;
			logProbability$var157 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample162 = fixedFlag$sample162;
		} else {
			logProbability$var135 = logProbability$var157;
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var157);
			logProbability$$model = (logProbability$$model + logProbability$var157);
			if(fixedFlag$sample162)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var157);
		}
	}

	private final void logProbabilityValue$sample190() {
		if(!fixedProbFlag$sample190) {
			double cv$sampleAccumulator = 0.0;
			for(int var173 = 0; var173 < noServers; var173 += 1) {
				for(int var183 = 0; var183 < noStates; var183 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var173][var183], 1.0, 1.0));
			}
			logProbability$var162 = cv$sampleAccumulator;
			logProbability$var184 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample190 = fixedFlag$sample190;
		} else {
			logProbability$var162 = logProbability$var184;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var184);
			logProbability$$model = (logProbability$$model + logProbability$var184);
			if(fixedFlag$sample190)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var184);
		}
	}

	private final void logProbabilityValue$sample20() {
		if(!fixedProbFlag$sample20) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			logProbability$var19 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample20 = fixedFlag$sample20;
		} else {
			logProbability$var19 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample20)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample241() {
		if(!fixedProbFlag$sample241) {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var196][server][timeStep$var226], current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var231[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						logProbability$sample241[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
					}
				}
			}
			logProbability$metric_valid_inner = (logProbability$metric_valid_inner + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample241 = ((fixedFlag$sample57 && fixedFlag$sample76) && fixedFlag$sample190);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
						double cv$sampleValue = logProbability$sample241[sample$var196][server][timeStep$var226];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var231[sample$var196][server][timeStep$var226] = cv$sampleValue;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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
							double var243 = current_metric_var[server][st[sample$var196][timeStep$var226]];
							double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][server][timeStep$var226] - current_metric_mean[server][st[sample$var196][timeStep$var226]]) / Math.sqrt(var243))) - (Math.log(var243) * 0.5));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var244[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
							logProbability$sample256[sample$var196][server][timeStep$var226] = cv$distributionAccumulator;
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
							double cv$rvAccumulator = logProbability$sample256[sample$var196][server][timeStep$var226];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var244[sample$var196][server][timeStep$var226] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var32 = 0; var32 < noStates; var32 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var32], v, noStates));
			logProbability$var21 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample33 = fixedFlag$sample33;
		} else {
			logProbability$var21 = logProbability$var33;
			logProbability$m = (logProbability$m + logProbability$var33);
			logProbability$$model = (logProbability$$model + logProbability$var33);
			if(fixedFlag$sample33)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var33);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				int cv$sampleValue = st[sample$var45][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var54[sample$var45] = cv$distributionAccumulator;
				logProbability$sample57[sample$var45] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample20);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				double cv$rvAccumulator = logProbability$sample57[sample$var45];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[sample$var45] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var45][timeStep$var66];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[sample$var45][(timeStep$var66 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var73[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
					logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample76[sample$var45][(timeStep$var66 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[sample$var45][(timeStep$var66 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample76)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample134(int var119, int var129, int threadID$cv$var129, Rng RNG$) {
		double cv$originalValue = current_metric_mean[var119][var129];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if((((var129 == st[sample$var196][0]) && metric_valid_g[sample$var196][var119][0]) && (0 < length$metric[sample$var196][0]))) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var156 = st[sample$var196][0];
						if(((0 <= var156) && (var156 < noStates))) {
							double cv$temp$3$var243 = current_metric_var[var119][st[sample$var196][0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var243))) - (Math.log(cv$temp$3$var243) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
							double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][0];
							if(((0 <= var156) && (var156 < noStates))) {
								double cv$temp$9$var243 = current_metric_var[var119][st[sample$var196][0]];
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var243)))) - (Math.log(cv$temp$9$var243) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if((metric_valid_g[sample$var196][var119][timeStep$var226] && (var129 == st[sample$var196][timeStep$var226]))) {
						if(fixedFlag$sample76) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][timeStep$var226];
							if(((0 <= var156) && (var156 < noStates))) {
								double cv$temp$21$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(cv$temp$21$var243))) - (Math.log(cv$temp$21$var243) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
								double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var156 = st[sample$var196][timeStep$var226];
								if(((0 <= var156) && (var156 < noStates))) {
									double cv$temp$27$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][timeStep$var226] - cv$originalValue) / Math.sqrt(cv$temp$27$var243)))) - (Math.log(cv$temp$27$var243) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_mean[var119][var129] = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			if((((var129 == st[sample$var196][0]) && metric_valid_g[sample$var196][var119][0]) && (0 < length$metric[sample$var196][0]))) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var156 = st[sample$var196][0];
					if(((0 <= var156) && (var156 < noStates))) {
						double cv$temp$3$var243 = current_metric_var[var119][st[sample$var196][0]];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var243))) - (Math.log(cv$temp$3$var243) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var156 = st[sample$var196][0];
						if(((0 <= var156) && (var156 < noStates))) {
							double cv$temp$9$var243 = current_metric_var[var119][st[sample$var196][0]];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var243)))) - (Math.log(cv$temp$9$var243) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if((metric_valid_g[sample$var196][var119][timeStep$var226] && (var129 == st[sample$var196][timeStep$var226]))) {
					if(fixedFlag$sample76) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var156 = st[sample$var196][timeStep$var226];
						if(((0 <= var156) && (var156 < noStates))) {
							double cv$temp$21$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(cv$temp$21$var243))) - (Math.log(cv$temp$21$var243) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var156 = st[sample$var196][timeStep$var226];
							if(((0 <= var156) && (var156 < noStates))) {
								double cv$temp$27$var243 = current_metric_var[var119][st[sample$var196][timeStep$var226]];
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var119][timeStep$var226] - cv$proposedValue) / Math.sqrt(cv$temp$27$var243)))) - (Math.log(cv$temp$27$var243) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			current_metric_mean[var119][var129] = cv$originalValue;
	}

	private final void sample162(int var146, int var156, int threadID$cv$var156, Rng RNG$) {
		double cv$originalValue = current_metric_var[var146][var156];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				if((((var156 == st[sample$var196][0]) && metric_valid_g[sample$var196][var146][0]) && (0 < length$metric[sample$var196][0]))) {
					if(fixedFlag$sample57) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var129 = st[sample$var196][0];
						if(((0 <= var129) && (var129 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
							double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][0];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
					if((metric_valid_g[sample$var196][var146][timeStep$var226] && (var156 == st[sample$var196][timeStep$var226]))) {
						if(fixedFlag$sample76) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][timeStep$var226];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						} else {
							for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
								double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var129 = st[sample$var196][timeStep$var226];
								if(((0 <= var129) && (var129 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
									cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_var[var146][var156] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			if((((var156 == st[sample$var196][0]) && metric_valid_g[sample$var196][var146][0]) && (0 < length$metric[sample$var196][0]))) {
				if(fixedFlag$sample57) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var129 = st[sample$var196][0];
					if(((0 <= var129) && (var129 < noStates))) {
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					for(int index$sample57$7 = 0; index$sample57$7 < noStates; index$sample57$7 += 1) {
						double cv$probabilitySample57Value8 = distribution$sample57[sample$var196][index$sample57$7];
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var129 = st[sample$var196][0];
						if(((0 <= var129) && (var129 < noStates))) {
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample57Value8) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][0] - current_metric_mean[var146][st[sample$var196][0]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample57Value8);
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
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if((metric_valid_g[sample$var196][var146][timeStep$var226] && (var156 == st[sample$var196][timeStep$var226]))) {
					if(fixedFlag$sample76) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var129 = st[sample$var196][timeStep$var226];
						if(((0 <= var129) && (var129 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							cv$consumerDistributionProbabilityAccumulator = 0.0;
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
					} else {
						for(int index$sample76$19 = 0; index$sample76$19 < noStates; index$sample76$19 += 1) {
							double cv$probabilitySample76Value20 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$19];
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var129 = st[sample$var196][timeStep$var226];
							if(((0 <= var129) && (var129 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample76Value20) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var196][var146][timeStep$var226] - current_metric_mean[var146][st[sample$var196][timeStep$var226]]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = (1.0 - cv$probabilitySample76Value20);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			current_metric_var[var146][var156] = cv$originalValue;
	}

	private final void sample190(int var173, int var183, int threadID$cv$var183, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			if(((var183 == st[sample$var196][0]) && (0 < length$metric[sample$var196][0]))) {
				if(fixedFlag$sample57) {
					cv$count = (cv$count + 1.0);
					if(metric_valid_g[sample$var196][var173][0])
						cv$sum = (cv$sum + 1.0);
				} else {
					for(int index$sample57$6 = 0; index$sample57$6 < noStates; index$sample57$6 += 1) {
						double cv$probabilitySample57Value7 = distribution$sample57[sample$var196][index$sample57$6];
						cv$count = (cv$count + cv$probabilitySample57Value7);
						if(metric_valid_g[sample$var196][var173][0])
							cv$sum = (cv$sum + cv$probabilitySample57Value7);
					}
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int timeStep$var226 = 1; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1) {
				if((var183 == st[sample$var196][timeStep$var226])) {
					if(fixedFlag$sample76) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var196][var173][timeStep$var226])
							cv$sum = (cv$sum + 1.0);
					} else {
						for(int index$sample76$18 = 0; index$sample76$18 < noStates; index$sample76$18 += 1) {
							double cv$probabilitySample76Value19 = distribution$sample76[sample$var196][(timeStep$var226 - 1)][index$sample76$18];
							cv$count = (cv$count + cv$probabilitySample76Value19);
							if(metric_valid_g[sample$var196][var173][timeStep$var226])
								cv$sum = (cv$sum + cv$probabilitySample76Value19);
						}
					}
				}
			}
		}
		current_metric_valid_bias[var173][var183] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample20() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				cv$var20$countGlobal[st[sample$var45][0]] = (cv$var20$countGlobal[st[sample$var45][0]] + 1.0);
		} else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var20$countGlobal[cv$loopIndex] = (cv$var20$countGlobal[cv$loopIndex] + distribution$sample57[sample$var45][cv$loopIndex]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var20$countGlobal, initialStateDistribution, noStates);
	}

	private final void sample33(int var32, int threadID$cv$var32, Rng RNG$) {
		double[] cv$countLocal = cv$var33$countGlobal[threadID$cv$var32];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if(((var32 == st[sample$var45][0]) && (1 < length$metric[sample$var45][0]))) {
					if(fixedFlag$sample57)
						cv$countLocal[st[sample$var45][1]] = (cv$countLocal[st[sample$var45][1]] + 1.0);
					else {
						for(int index$sample57$5 = 0; index$sample57$5 < noStates; index$sample57$5 += 1)
							cv$countLocal[st[sample$var45][1]] = (cv$countLocal[st[sample$var45][1]] + distribution$sample57[sample$var45][index$sample57$5]);
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 2; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)]))
						cv$countLocal[st[sample$var45][timeStep$var66]] = (cv$countLocal[st[sample$var45][timeStep$var66]] + 1.0);
				}
			}
		} else {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				if(((var32 == st[sample$var45][0]) && (1 < length$metric[sample$var45][0]))) {
					if(fixedFlag$sample57) {
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample76[sample$var45][0][cv$loopIndex]);
					} else {
						for(int index$sample57$42 = 0; index$sample57$42 < noStates; index$sample57$42 += 1) {
							double cv$distributionProbability = distribution$sample57[sample$var45][index$sample57$42];
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[sample$var45][0][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
					if((var32 == st[sample$var45][(timeStep$var66 - 1)])) {
						int index$timeStep$52 = (timeStep$var66 - 1);
						if((1 <= index$timeStep$52)) {
							for(int index$sample76$53 = 0; index$sample76$53 < noStates; index$sample76$53 += 1) {
								double cv$distributionProbability = distribution$sample76[sample$var45][(index$timeStep$52 - 1)][index$sample76$53];
								for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
									cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample76[sample$var45][(timeStep$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var32], noStates);
	}

	private final void sample57(int sample$var45, int threadID$cv$sample$var45, Rng RNG$) {
		int cv$numNumStates = Math.max(0, noStates);
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal[threadID$cv$sample$var45];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				int var32 = st[sample$var45][0];
				if(((0 <= var32) && (var32 < noStates))) {
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample$var45][1]) && (st[sample$var45][1] < noStates))?Math.log(m[cv$valuePos][st[sample$var45][1]]):Double.NEGATIVE_INFINITY);
					cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((0 < length$metric[sample$var45][0])) {
				for(int server = 0; server < noServers; server += 1) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var183 = st[sample$var45][0];
					if(((0 <= var183) && (var183 < noStates))) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var45][server][0], current_metric_valid_bias[server][cv$valuePos]);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				boolean[][][] guard$sample57gaussian255 = guard$sample57gaussian255$global[threadID$cv$sample$var45];
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var45][server][0])
						guard$sample57gaussian255[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var45][server][0])
						guard$sample57gaussian255[sample$var45][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255[sample$var45][server][0])) {
						guard$sample57gaussian255[sample$var45][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((0 <= st[sample$var45][0])) {
							int var129 = st[sample$var45][0];
							if(((0 <= var129) && (var129 < noStates))) {
								double cv$temp$6$var243 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$6$var243))) - (Math.log(cv$temp$6$var243) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var45][server][0] && !guard$sample57gaussian255[sample$var45][server][0])) {
						guard$sample57gaussian255[sample$var45][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((0 <= st[sample$var45][0])) {
							int var129 = st[sample$var45][0];
							if(((0 <= var129) && (var129 < noStates))) {
								double cv$temp$14$var243 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$14$var243))) - (Math.log(cv$temp$14$var243) * 0.5));
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			if((!fixedFlag$sample76 && (1 < length$metric[sample$var45][0]))) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$sample$var45];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var32 = st[sample$var45][0];
				if(((0 <= var32) && (var32 < noStates))) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample57[sample$var45];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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

	private final void sample76(int sample$var45, int timeStep$var66, int threadID$cv$sample$var45, Rng RNG$) {
		int cv$numNumStates = 0;
		if((1 == timeStep$var66)) {
			if(fixedFlag$sample57) {
				int var32 = st[sample$var45][0];
				if(((0 <= var32) && (var32 < noStates)))
					cv$numNumStates = Math.max(0, noStates);
			} else {
				if((0 < noStates)) {
					int var32 = st[sample$var45][0];
					if(((0 <= var32) && (var32 < noStates)))
						cv$numNumStates = noStates;
				}
			}
		}
		if(fixedFlag$sample76) {
			if((2 <= timeStep$var66)) {
				int var32 = st[sample$var45][(timeStep$var66 - 1)];
				if(((0 <= var32) && (var32 < noStates)))
					cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var66 - 1);
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var66))) {
					int var32 = st[sample$var45][(timeStep$var66 - 1)];
					if(((0 <= var32) && (var32 < noStates)))
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal[threadID$cv$sample$var45];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var66)) {
				if(fixedFlag$sample57) {
					int var32 = st[sample$var45][0];
					if(((0 <= var32) && (var32 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[st[sample$var45][0]][cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample$var45][0])) {
							for(int server = 0; server < noServers; server += 1) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var183 = st[sample$var45][1];
								if(((0 <= var183) && (var183 < noStates))) {
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var45][server][1], current_metric_valid_bias[server][cv$valuePos]);
									cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var45][server][1])
									guard$sample76gaussian255[sample$var45][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255[sample$var45][server][1])) {
									guard$sample76gaussian255[sample$var45][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((0 <= st[sample$var45][1])) {
										int var129 = st[sample$var45][1];
										if(((0 <= var129) && (var129 < noStates))) {
											double cv$temp$15$var243 = current_metric_var[server][cv$valuePos];
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$15$var243))) - (Math.log(cv$temp$15$var243) * 0.5));
											cv$consumerDistributionProbabilityAccumulator = 0.0;
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
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255[sample$var45][server][1])) {
									guard$sample76gaussian255[sample$var45][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((0 <= st[sample$var45][1])) {
										int var129 = st[sample$var45][1];
										if(((0 <= var129) && (var129 < noStates))) {
											double cv$temp$47$var243 = current_metric_var[server][cv$valuePos];
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$47$var243))) - (Math.log(cv$temp$47$var243) * 0.5));
											cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample57$26 = 0; index$sample57$26 < noStates; index$sample57$26 += 1) {
						double cv$probabilitySample57Value27 = distribution$sample57[sample$var45][index$sample57$26];
						int var32 = st[sample$var45][0];
						if(((0 <= var32) && (var32 < noStates))) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value27);
							double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value27) + ((cv$valuePos < noStates)?Math.log(m[st[sample$var45][0]][cv$valuePos]):Double.NEGATIVE_INFINITY));
							if((1 < length$metric[sample$var45][0])) {
								for(int server = 0; server < noServers; server += 1) {
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									int var183 = st[sample$var45][1];
									if(((0 <= var183) && (var183 < noStates))) {
										cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var45][server][1], current_metric_valid_bias[server][cv$valuePos]);
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
								boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
								for(int server = 0; server < noServers; server += 1) {
									if(metric_valid_g[sample$var45][server][1])
										guard$sample76gaussian255[sample$var45][server][1] = false;
								}
								for(int server = 0; server < noServers; server += 1) {
									if(metric_valid_g[sample$var45][server][1])
										guard$sample76gaussian255[sample$var45][server][1] = false;
								}
								for(int server = 0; server < noServers; server += 1) {
									if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255[sample$var45][server][1])) {
										guard$sample76gaussian255[sample$var45][server][1] = true;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										if((0 <= st[sample$var45][1])) {
											int var129 = st[sample$var45][1];
											if(((0 <= var129) && (var129 < noStates))) {
												double cv$temp$23$var243 = current_metric_var[server][cv$valuePos];
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$23$var243))) - (Math.log(cv$temp$23$var243) * 0.5));
												cv$consumerDistributionProbabilityAccumulator = 0.0;
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
								for(int server = 0; server < noServers; server += 1) {
									if((metric_valid_g[sample$var45][server][1] && !guard$sample76gaussian255[sample$var45][server][1])) {
										guard$sample76gaussian255[sample$var45][server][1] = true;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										if((0 <= st[sample$var45][1])) {
											int var129 = st[sample$var45][1];
											if(((0 <= var129) && (var129 < noStates))) {
												double cv$temp$55$var243 = current_metric_var[server][cv$valuePos];
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$55$var243))) - (Math.log(cv$temp$55$var243) * 0.5));
												cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			int index$timeStep$34 = (timeStep$var66 - 1);
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var66))) {
				for(int index$sample76$35 = 0; index$sample76$35 < noStates; index$sample76$35 += 1) {
					double cv$probabilitySample76Value36 = distribution$sample76[sample$var45][(index$timeStep$34 - 1)][index$sample76$35];
					int var32 = st[sample$var45][(timeStep$var66 - 1)];
					if(((0 <= var32) && (var32 < noStates))) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample76Value36);
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample76Value36) + ((cv$valuePos < noStates)?Math.log(m[cv$valuePos][cv$valuePos]):Double.NEGATIVE_INFINITY));
						for(int server = 0; server < noServers; server += 1) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var183 = st[sample$var45][timeStep$var66];
							if(((0 <= var183) && (var183 < noStates))) {
								cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var45][server][timeStep$var66], current_metric_valid_bias[server][index$sample76$35]);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						boolean[][][] guard$sample76gaussian255 = guard$sample76gaussian255$global[threadID$cv$sample$var45];
						for(int server = 0; server < noServers; server += 1) {
							if(metric_valid_g[sample$var45][server][timeStep$var66])
								guard$sample76gaussian255[sample$var45][server][timeStep$var66] = false;
						}
						for(int server = 0; server < noServers; server += 1) {
							if(metric_valid_g[sample$var45][server][timeStep$var66])
								guard$sample76gaussian255[sample$var45][server][timeStep$var66] = false;
						}
						for(int server = 0; server < noServers; server += 1) {
							if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255[sample$var45][server][timeStep$var66])) {
								guard$sample76gaussian255[sample$var45][server][timeStep$var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[sample$var45][timeStep$var66])) {
									int var129 = st[sample$var45][timeStep$var66];
									if(((0 <= var129) && (var129 < noStates))) {
										double cv$temp$39$var243 = current_metric_var[server][index$sample76$35];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][timeStep$var66] - current_metric_mean[server][index$sample76$35]) / Math.sqrt(cv$temp$39$var243))) - (Math.log(cv$temp$39$var243) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						for(int server = 0; server < noServers; server += 1) {
							if((metric_valid_g[sample$var45][server][timeStep$var66] && !guard$sample76gaussian255[sample$var45][server][timeStep$var66])) {
								guard$sample76gaussian255[sample$var45][server][timeStep$var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((0 <= st[sample$var45][timeStep$var66])) {
									int var129 = st[sample$var45][timeStep$var66];
									if(((0 <= var129) && (var129 < noStates))) {
										double cv$temp$71$var243 = current_metric_var[server][index$sample76$35];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var45][server][timeStep$var66] - current_metric_mean[server][index$sample76$35]) / Math.sqrt(cv$temp$71$var243))) - (Math.log(cv$temp$71$var243) * 0.5));
										cv$consumerDistributionProbabilityAccumulator = 0.0;
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
			int index$timeStep$269_3 = (timeStep$var66 + 1);
			if((index$timeStep$269_3 < length$metric[sample$var45][0])) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$sample$var45];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var32 = st[sample$var45][(index$timeStep$269_3 - 1)];
				if(((0 <= var32) && (var32 < noStates))) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var66)) {
						if(fixedFlag$sample57) {
							int index$var32$280_1 = st[sample$var45][0];
							if(((0 <= index$var32$280_1) && (index$var32$280_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample57$276 = 0; index$sample57$276 < noStates; index$sample57$276 += 1) {
								int index$var32$281_1 = st[sample$var45][0];
								if(((0 <= index$var32$281_1) && (index$var32$281_1 < noStates)))
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample57[sample$var45][index$sample57$276]);
							}
						}
					}
					int index$timeStep$284 = (timeStep$var66 - 1);
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var66)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						for(int index$sample76$285 = 0; index$sample76$285 < noStates; index$sample76$285 += 1) {
							int index$var32$290_1 = st[sample$var45][(timeStep$var66 - 1)];
							if(((0 <= index$var32$290_1) && (index$var32$290_1 < noStates)))
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample76[sample$var45][(index$timeStep$284 - 1)][index$sample76$285]);
						}
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample76[sample$var45][(index$timeStep$269_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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
		cv$var20$countGlobal = new double[noStates];
		{
			int cv$threadCount = threadCount();
			cv$var33$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var33$countGlobal[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$distributionAccumulator$var73 = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$distributionAccumulator$var73[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$var55$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var55$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		{
			int cv$max_server = 0;
			int cv$max_timeStep$var226 = 0;
			for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, length$metric[sample$var196][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			int cv$max_sample$var196 = length$metric.length;
			int cv$threadCount = threadCount();
			guard$sample57gaussian255$global = new boolean[cv$threadCount][][][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample57gaussian255$global[cv$index] = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
		}
		{
			int cv$threadCount = threadCount();
			cv$var74$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var74$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		int cv$max_server = 0;
		int cv$max_timeStep$var226 = 0;
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var226 = Math.max(cv$max_timeStep$var226, length$metric[sample$var196][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		int cv$max_sample$var196 = length$metric.length;
		int cv$threadCount = threadCount();
		guard$sample76gaussian255$global = new boolean[cv$threadCount][][][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample76gaussian255$global[cv$index] = new boolean[cv$max_sample$var196][cv$max_server][cv$max_timeStep$var226];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!fixedFlag$sample20)
			initialStateDistribution = new double[noStates];
		if(!fixedFlag$sample33) {
			m = new double[noStates][];
			for(int var32 = 0; var32 < noStates; var32 += 1)
				m[var32] = new double[noStates];
		}
		if((!fixedFlag$sample57 || !fixedFlag$sample76)) {
			st = new int[length$metric.length][];
			for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
				st[sample$var45] = new int[length$metric[sample$var45][0]];
		}
		metric_g = new double[length$metric.length][][];
		for(int var90 = 0; var90 < length$metric.length; var90 += 1)
			metric_g[var90] = new double[length$metric[0].length][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			for(int server = 0; server < length$metric[0].length; server += 1)
				metric_g[sample$var196][server] = new double[length$metric[sample$var196][0]];
		}
		metric_valid_g = new boolean[length$metric.length][][];
		for(int var103 = 0; var103 < length$metric.length; var103 += 1)
			metric_valid_g[var103] = new boolean[length$metric[0].length][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			for(int server = 0; server < length$metric[0].length; server += 1)
				metric_valid_g[sample$var196][server] = new boolean[length$metric[sample$var196][0]];
		}
		if(!fixedFlag$sample134) {
			current_metric_mean = new double[length$metric[0].length][];
			for(int var119 = 0; var119 < length$metric[0].length; var119 += 1)
				current_metric_mean[var119] = new double[noStates];
		}
		if(!fixedFlag$sample162) {
			current_metric_var = new double[length$metric[0].length][];
			for(int var146 = 0; var146 < length$metric[0].length; var146 += 1)
				current_metric_var[var146] = new double[noStates];
		}
		if(!fixedFlag$sample190) {
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var173 = 0; var173 < length$metric[0].length; var173 += 1)
				current_metric_valid_bias[var173] = new double[noStates];
		}
		distribution$sample57 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			distribution$sample57[sample$var45] = new double[noStates];
		distribution$sample76 = new double[length$metric.length][][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var45][0] - 1)][];
			distribution$sample76[sample$var45] = subarray$0;
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
				subarray$0[(timeStep$var66 - 1)] = new double[noStates];
		}
		logProbability$var54 = new double[length$metric.length];
		logProbability$sample57 = new double[length$metric.length];
		logProbability$var73 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			logProbability$var73[sample$var45] = new double[(length$metric[sample$var45][0] - 1)];
		logProbability$sample76 = new double[length$metric.length][];
		for(int sample$var45 = 0; sample$var45 < length$metric.length; sample$var45 += 1)
			logProbability$sample76[sample$var45] = new double[(length$metric[sample$var45][0] - 1)];
		logProbability$var231 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var231[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		logProbability$sample241 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample241[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		logProbability$var244 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var244[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		logProbability$sample256 = new double[length$metric.length][][];
		for(int sample$var196 = 0; sample$var196 < length$metric.length; sample$var196 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample256[sample$var196] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var196][0]];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var32]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						if(!fixedFlag$sample57)
							st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample76) {
							int[] var67 = st[sample$var45];
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		if(!fixedFlag$sample134)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
							double[] var120 = current_metric_mean[var119];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1)
											var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample162)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
							double[] var147 = current_metric_var[var146];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample190)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
							double[] var174 = current_metric_valid_bias[var173];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample$var196, int forEnd$index$sample$var196, int threadID$index$sample$var196, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample$var196 = forStart$index$sample$var196; index$sample$var196 < forEnd$index$sample$var196; index$sample$var196 += 1) {
						int sample$var196 = index$sample$var196;
						int threadID$sample$var196 = threadID$index$sample$var196;
						boolean[][] var215 = metric_valid_g[sample$var196];
						double[][] var211 = metric_g[sample$var196];
						parallelFor(RNG$1, 0, noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										int threadID$server = threadID$index$server;
										boolean[] metric_valid_inner = metric_valid_g[sample$var196][server];
										double[] metric_inner = var211[server];
										parallelFor(RNG$2, 0, length$metric[sample$var196][0], 1,
											(int forStart$timeStep$var226, int forEnd$timeStep$var226, int threadID$timeStep$var226, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int timeStep$var226 = forStart$timeStep$var226; timeStep$var226 < forEnd$timeStep$var226; timeStep$var226 += 1) {
														metric_valid_inner[timeStep$var226] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var196][timeStep$var226]]);
														if((var215[server][timeStep$var226] && !fixedFlag$sample256))
															metric_inner[timeStep$var226] = ((Math.sqrt(current_metric_var[server][st[sample$var196][timeStep$var226]]) * DistributionSampling.sampleGaussian(RNG$3)) + current_metric_mean[server][st[sample$var196][timeStep$var226]]);
													}
											}
										);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var32]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						if(!fixedFlag$sample57) {
							double[] cv$distribution$sample57 = distribution$sample57[sample$var45];
							for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1)
								cv$distribution$sample57[index$var54] = initialStateDistribution[index$var54];
						}
						if(!fixedFlag$sample76) {
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1) {
								double[] cv$distribution$sample76 = distribution$sample76[sample$var45][(timeStep$var66 - 1)];
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$distribution$sample76[index$var73] = 0.0;
								if((1 == timeStep$var66)) {
									if(fixedFlag$sample57) {
										int var32 = st[sample$var45][0];
										if(((0 <= var32) && (var32 < noStates))) {
											double[] var72 = m[st[sample$var45][0]];
											for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
												cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + var72[index$var73]);
										}
									} else {
										for(int index$sample57$3 = 0; index$sample57$3 < noStates; index$sample57$3 += 1) {
											double cv$probabilitySample57Value4 = distribution$sample57[sample$var45][index$sample57$3];
											int var32 = st[sample$var45][0];
											if(((0 <= var32) && (var32 < noStates))) {
												double[] var72 = m[st[sample$var45][0]];
												for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
													cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample57Value4 * var72[index$var73]));
											}
										}
									}
								}
								int index$timeStep$11 = (timeStep$var66 - 1);
								if((1 <= index$timeStep$11)) {
									for(int index$sample76$12 = 0; index$sample76$12 < noStates; index$sample76$12 += 1) {
										double cv$probabilitySample76Value13 = distribution$sample76[sample$var45][(index$timeStep$11 - 1)][index$sample76$12];
										int var32 = st[sample$var45][(timeStep$var66 - 1)];
										if(((0 <= var32) && (var32 < noStates))) {
											double[] var72 = m[st[sample$var45][(timeStep$var66 - 1)]];
											for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
												cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] + (cv$probabilitySample76Value13 * var72[index$var73]));
										}
									}
								}
								double cv$var73$sum = 0.0;
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$var73$sum = (cv$var73$sum + cv$distribution$sample76[index$var73]);
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$distribution$sample76[index$var73] = (cv$distribution$sample76[index$var73] / cv$var73$sum);
							}
						}
					}
			}
		);
		if(!fixedFlag$sample134)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
							double[] var120 = current_metric_mean[var119];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1)
											var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample162)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
							double[] var147 = current_metric_var[var146];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample190)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
							double[] var174 = current_metric_valid_bias[var173];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample20)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample33)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var32]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						if(!fixedFlag$sample57)
							st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample76) {
							int[] var67 = st[sample$var45];
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		if(!fixedFlag$sample134)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
							double[] var120 = current_metric_mean[var119];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1)
											var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample162)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
							double[] var147 = current_metric_var[var146];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample190)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
							double[] var174 = current_metric_valid_bias[var173];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample20)
				sample20();
			if(!fixedFlag$sample33)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
								sample33(var32, threadID$var32, RNG$1);
					}
				);

			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
							if(!fixedFlag$sample57)
								sample57(sample$var45, threadID$sample$var45, RNG$1);
							if(!fixedFlag$sample76) {
								for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
									sample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
							}
						}
				}
			);
			if(!fixedFlag$sample134)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
								int var119 = index$var119;
								int threadID$var119 = threadID$index$var119;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1)
												sample134(var119, var129, threadID$var129, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample162)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
								int var146 = index$var146;
								int threadID$var146 = threadID$index$var146;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1)
												sample162(var146, var156, threadID$var156, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample190)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
								int var173 = index$var173;
								int threadID$var173 = threadID$index$var173;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1)
												sample190(var173, var183, threadID$var183, RNG$2);
									}
								);
							}
					}
				);

		} else {
			if(!fixedFlag$sample190)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var173, int forEnd$index$var173, int threadID$index$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var173 = forStart$index$var173; index$var173 < forEnd$index$var173; index$var173 += 1) {
								int var173 = index$var173;
								int threadID$var173 = threadID$index$var173;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1)
												sample190(var173, var183, threadID$var183, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample162)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var146, int forEnd$index$var146, int threadID$index$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var146 = forStart$index$var146; index$var146 < forEnd$index$var146; index$var146 += 1) {
								int var146 = index$var146;
								int threadID$var146 = threadID$index$var146;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1)
												sample162(var146, var156, threadID$var156, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample134)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var119, int forEnd$index$var119, int threadID$index$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var119 = forStart$index$var119; index$var119 < forEnd$index$var119; index$var119 += 1) {
								int var119 = index$var119;
								int threadID$var119 = threadID$index$var119;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1)
												sample134(var119, var129, threadID$var129, RNG$2);
									}
								);
							}
					}
				);

			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
							if(!fixedFlag$sample76) {
								for(int timeStep$var66 = (length$metric[sample$var45][0] - 1); timeStep$var66 >= 1; timeStep$var66 -= 1)
									sample76(sample$var45, timeStep$var66, threadID$sample$var45, RNG$1);
							}
							if(!fixedFlag$sample57)
								sample57(sample$var45, threadID$sample$var45, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample33)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
								sample33(var32, threadID$var32, RNG$1);
					}
				);

			if(!fixedFlag$sample20)
				sample20();
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
			logProbability$var54[sample$var45] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1)
				logProbability$sample57[sample$var45] = 0.0;
		}
		for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
			for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
				logProbability$var73[sample$var45][(timeStep$var66 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample76) {
			for(int sample$var45 = 0; sample$var45 < noSamples; sample$var45 += 1) {
				for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
					logProbability$sample76[sample$var45][(timeStep$var66 - 1)] = 0.0;
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
					logProbability$var231[sample$var196][server][timeStep$var226] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample241) {
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						logProbability$sample241[sample$var196][server][timeStep$var226] = 0.0;
				}
			}
		}
		for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
					logProbability$var244[sample$var196][server][timeStep$var226] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample256) {
			for(int sample$var196 = 0; sample$var196 < noSamples; sample$var196 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var226 = 0; timeStep$var226 < length$metric[sample$var196][0]; timeStep$var226 += 1)
						logProbability$sample256[sample$var196][server][timeStep$var226] = 0.0;
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
		if(!fixedFlag$sample33)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var32, int forEnd$var32, int threadID$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var32 = forStart$var32; var32 < forEnd$var32; var32 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var32]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var45, int forEnd$sample$var45, int threadID$sample$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var45 = forStart$sample$var45; sample$var45 < forEnd$sample$var45; sample$var45 += 1) {
						if(!fixedFlag$sample57)
							st[sample$var45][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample76) {
							int[] var67 = st[sample$var45];
							for(int timeStep$var66 = 1; timeStep$var66 < length$metric[sample$var45][0]; timeStep$var66 += 1)
								var67[timeStep$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var45][(timeStep$var66 - 1)]], noStates);
						}
					}
			}
		);
		if(!fixedFlag$sample134)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var119, int forEnd$var119, int threadID$var119, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var119 = forStart$var119; var119 < forEnd$var119; var119 += 1) {
							double[] var120 = current_metric_mean[var119];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var129, int forEnd$var129, int threadID$var129, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var129 = forStart$var129; var129 < forEnd$var129; var129 += 1)
											var120[var129] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample162)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var146, int forEnd$var146, int threadID$var146, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var146 = forStart$var146; var146 < forEnd$var146; var146 += 1) {
							double[] var147 = current_metric_var[var146];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var156, int forEnd$var156, int threadID$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var156 = forStart$var156; var156 < forEnd$var156; var156 += 1)
											var147[var156] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample190)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var173, int forEnd$var173, int threadID$var173, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var173 = forStart$var173; var173 < forEnd$var173; var173 += 1) {
							double[] var174 = current_metric_valid_bias[var173];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var183, int forEnd$var183, int threadID$var183, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var183 = forStart$var183; var183 < forEnd$var183; var183 += 1)
											var174[var183] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			int cv$length1 = metric_valid_g.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[][] cv$source2 = metric_valid[cv$index1];
				boolean[][] cv$target2 = metric_valid_g[cv$index1];
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
		int cv$length1 = metric_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			double[][] cv$source2 = metric[cv$index1];
			double[][] cv$target2 = metric_g[cv$index1];
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