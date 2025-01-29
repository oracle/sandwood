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
		fixedProbFlag$sample111 = (cv$value && fixedProbFlag$sample111);
		fixedProbFlag$sample173 = (cv$value && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	@Override
	public final void set$fixedFlag$sample126(boolean cv$value) {
		fixedFlag$sample126 = cv$value;
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		fixedProbFlag$sample161 = (cv$value && fixedProbFlag$sample161);
	}

	@Override
	public final boolean get$fixedFlag$sample161() {
		return fixedFlag$sample161;
	}

	@Override
	public final void set$fixedFlag$sample161(boolean cv$value) {
		fixedFlag$sample161 = cv$value;
		fixedProbFlag$sample161 = (cv$value && fixedProbFlag$sample161);
	}

	@Override
	public final boolean get$fixedFlag$sample173() {
		return fixedFlag$sample173;
	}

	@Override
	public final void set$fixedFlag$sample173(boolean cv$value) {
		fixedFlag$sample173 = cv$value;
		fixedProbFlag$sample173 = (cv$value && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
	}

	@Override
	public final boolean get$fixedFlag$sample50() {
		return fixedFlag$sample50;
	}

	@Override
	public final void set$fixedFlag$sample50(boolean cv$value) {
		fixedFlag$sample50 = cv$value;
		fixedProbFlag$sample50 = (cv$value && fixedProbFlag$sample50);
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
		fixedProbFlag$sample161 = (cv$value && fixedProbFlag$sample161);
		fixedProbFlag$sample173 = (cv$value && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		fixedFlag$sample63 = cv$value;
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
		fixedProbFlag$sample161 = (cv$value && fixedProbFlag$sample161);
		fixedProbFlag$sample173 = (cv$value && fixedProbFlag$sample173);
	}

	@Override
	public final boolean get$fixedFlag$sample96() {
		return fixedFlag$sample96;
	}

	@Override
	public final void set$fixedFlag$sample96(boolean cv$value) {
		fixedFlag$sample96 = cv$value;
		fixedProbFlag$sample96 = (cv$value && fixedProbFlag$sample96);
		fixedProbFlag$sample173 = (cv$value && fixedProbFlag$sample173);
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
						if((0 == timeStep$var140)) {
							if(fixedFlag$sample50) {
								int var114 = st[sample$var120][0];
								if(((0 <= var114) && (var114 < noStates))) {
									cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var120][0]]);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample50$4 = 0; index$sample50$4 < noStates; index$sample50$4 += 1) {
									double cv$probabilitySample50Value5 = distribution$sample50[sample$var120][index$sample50$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample50Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample50$4]));
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
						if((1 <= timeStep$var140)) {
							if(fixedFlag$sample63) {
								int var114 = st[sample$var120][timeStep$var140];
								if(((0 <= var114) && (var114 < noStates))) {
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
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
								for(int index$sample63$13 = 0; index$sample63$13 < noStates; index$sample63$13 += 1) {
									double cv$probabilitySample63Value14 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][index$sample63$13];
									double cv$weightedProbability = (Math.log(cv$probabilitySample63Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample63$13]));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$sample161[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample161[sample$var120][server][timeStep$var140];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$sampleValue;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
							if(((0 == timeStep$var140) && metric_valid_g[sample$var120][server][0])) {
								if(fixedFlag$sample50) {
									if((0 <= st[sample$var120][0])) {
										int var86 = st[sample$var120][0];
										if(((0 <= var86) && (var86 < noStates))) {
											double var154 = current_metric_var[server][st[sample$var120][0]];
											cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var120][0]]) / Math.sqrt(var154))) - (Math.log(var154) * 0.5));
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									for(int index$sample50$4 = 0; index$sample50$4 < noStates; index$sample50$4 += 1) {
										double cv$probabilitySample50Value5 = distribution$sample50[sample$var120][index$sample50$4];
										double var154 = current_metric_var[server][index$sample50$4];
										double cv$weightedProbability = ((Math.log(cv$probabilitySample50Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample50$4]) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
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
							if((1 <= timeStep$var140)) {
								if(fixedFlag$sample63) {
									if((0 <= st[sample$var120][timeStep$var140])) {
										int var86 = st[sample$var120][timeStep$var140];
										if(((0 <= var86) && (var86 < noStates))) {
											double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
											double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var120][timeStep$var140]]) / Math.sqrt(var154))) - (Math.log(var154) * 0.5));
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
								} else {
									for(int index$sample63$49 = 0; index$sample63$49 < noStates; index$sample63$49 += 1) {
										double cv$probabilitySample63Value50 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][index$sample63$49];
										double var154 = current_metric_var[server][index$sample63$49];
										double cv$weightedProbability = ((Math.log(cv$probabilitySample63Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample63$49]) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
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
							if((cv$probabilityReached == 0.0))
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							logProbability$sample173[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
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
							double cv$rvAccumulator = logProbability$sample173[sample$var120][server][timeStep$var140];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var32][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var43[sample$var32] = cv$distributionAccumulator;
					logProbability$sample50[sample$var32] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample50 = fixedFlag$sample24;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = logProbability$sample50[sample$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[sample$var32] = cv$rvAccumulator;
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample$var32][timeStep$var49];
						if((1 == timeStep$var49)) {
							if(fixedFlag$sample50) {
								int var26 = st[sample$var32][0];
								if(((0 <= var26) && (var26 < noStates))) {
									double[] var55 = m[st[sample$var32][0]];
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample50$6 = 0; index$sample50$6 < noStates; index$sample50$6 += 1) {
									double cv$probabilitySample50Value7 = distribution$sample50[sample$var32][index$sample50$6];
									double[] var55 = m[index$sample50$6];
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
						if((2 <= timeStep$var49)) {
							int var26 = st[sample$var32][(timeStep$var49 - 1)];
							if(((0 <= var26) && (var26 < noStates))) {
								double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
						logProbability$sample63[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample63 = (fixedFlag$sample30 && fixedFlag$sample50);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = logProbability$sample63[sample$var32][(timeStep$var49 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var96][var100], 1.0, 1.0));
			}
			logProbability$var92 = cv$sampleAccumulator;
			logProbability$var101 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample111)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample111 = fixedFlag$sample111;
		} else {
			logProbability$var92 = logProbability$var101;
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var101);
			logProbability$$model = (logProbability$$model + logProbability$var101);
			if(fixedFlag$sample111)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var101);
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!fixedProbFlag$sample126) {
			double cv$sampleAccumulator = 0.0;
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var110][var114], 1.0, 1.0));
			}
			logProbability$var106 = cv$sampleAccumulator;
			logProbability$var115 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample126 = fixedFlag$sample126;
		} else {
			logProbability$var106 = logProbability$var115;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var115);
			logProbability$$model = (logProbability$$model + logProbability$var115);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var115);
		}
	}

	private final void logProbabilityValue$sample161() {
		if(!fixedProbFlag$sample161) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$sample161[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample161[sample$var120][server][timeStep$var140];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$sampleValue;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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
							double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
							double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - current_metric_mean[server][st[sample$var120][timeStep$var140]]) / Math.sqrt(var154))) - (Math.log(var154) * 0.5));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							logProbability$sample173[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
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
							double cv$rvAccumulator = logProbability$sample173[sample$var120][server][timeStep$var140];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$rvAccumulator;
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			logProbability$var20 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			logProbability$var20 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$sampleAccumulator = 0.0;
			for(int var26 = 0; var26 < noStates; var26 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var26], v));
			logProbability$var22 = cv$sampleAccumulator;
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			logProbability$var22 = logProbability$var27;
			logProbability$m = (logProbability$m + logProbability$var27);
			logProbability$$model = (logProbability$$model + logProbability$var27);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var27);
		}
	}

	private final void logProbabilityValue$sample50() {
		if(!fixedProbFlag$sample50) {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				int cv$sampleValue = st[sample$var32][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var43[sample$var32] = cv$distributionAccumulator;
				logProbability$sample50[sample$var32] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample50)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample50 = (fixedFlag$sample50 && fixedFlag$sample24);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = logProbability$sample50[sample$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[sample$var32] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var32][timeStep$var49];
					double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
					logProbability$sample63[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample63[sample$var32][(timeStep$var49 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				for(int var86 = 0; var86 < noStates; var86 += 1) {
					double cv$sampleValue = current_metric_mean[var82][var86];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var78 = cv$sampleAccumulator;
			logProbability$var87 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample96)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample96 = fixedFlag$sample96;
		} else {
			logProbability$var78 = logProbability$var87;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var87);
			logProbability$$model = (logProbability$$model + logProbability$var87);
			if(fixedFlag$sample96)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var87);
		}
	}

	private final void sample111(int var96, int var100) {
		double cv$originalValue = current_metric_var[var96][var100];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				if((metric_valid_g[sample$var120][var96][0] && (0 < length$metric[sample$var120][0]))) {
					if(fixedFlag$sample50) {
						if((var100 == st[sample$var120][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var86 = st[sample$var120][0];
							if(((0 <= var86) && (var86 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][st[sample$var120][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
					} else {
						double cv$probabilitySample50Value7 = distribution$sample50[sample$var120][var100];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample50Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][var100]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample50Value7), 0.0);
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
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					if(metric_valid_g[sample$var120][var96][timeStep$var140]) {
						if(fixedFlag$sample63) {
							if((var100 == st[sample$var120][timeStep$var140])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var86 = st[sample$var120][timeStep$var140];
								if(((0 <= var86) && (var86 < noStates))) {
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][st[sample$var120][timeStep$var140]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
						} else {
							double cv$probabilitySample63Value19 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][var100];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample63Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][var100]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample63Value19), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_var[var96][var100] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			if((metric_valid_g[sample$var120][var96][0] && (0 < length$metric[sample$var120][0]))) {
				if(fixedFlag$sample50) {
					if((var100 == st[sample$var120][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var86 = st[sample$var120][0];
						if(((0 <= var86) && (var86 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][st[sample$var120][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
				} else {
					double cv$probabilitySample50Value7 = distribution$sample50[sample$var120][var100];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample50Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][var100]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample50Value7), 0.0);
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
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				if(metric_valid_g[sample$var120][var96][timeStep$var140]) {
					if(fixedFlag$sample63) {
						if((var100 == st[sample$var120][timeStep$var140])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var86 = st[sample$var120][timeStep$var140];
							if(((0 <= var86) && (var86 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][st[sample$var120][timeStep$var140]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
					} else {
						double cv$probabilitySample63Value19 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][var100];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample63Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][var100]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample63Value19), 0.0);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			current_metric_var[var96][var100] = cv$originalValue;
	}

	private final void sample126(int var110, int var114) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			if((0 < length$metric[sample$var120][0])) {
				if(fixedFlag$sample50) {
					if((var114 == st[sample$var120][0])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var120][var110][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample50Value7 = distribution$sample50[sample$var120][var114];
					cv$count = (cv$count + cv$probabilitySample50Value7);
					if(metric_valid_g[sample$var120][var110][0])
						cv$sum = (cv$sum + cv$probabilitySample50Value7);
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				if(fixedFlag$sample63) {
					if((var114 == st[sample$var120][timeStep$var140])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var120][var110][timeStep$var140])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample63Value19 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][var114];
					cv$count = (cv$count + cv$probabilitySample63Value19);
					if(metric_valid_g[sample$var120][var110][timeStep$var140])
						cv$sum = (cv$sum + cv$probabilitySample63Value19);
				}
			}
		}
		current_metric_valid_bias[var110][var114] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample24() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var21$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample50) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				cv$var21$countGlobal[st[sample$var32][0]] = (cv$var21$countGlobal[st[sample$var32][0]] + 1.0);
		} else {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var21$countGlobal[cv$loopIndex] = (cv$var21$countGlobal[cv$loopIndex] + distribution$sample50[sample$var32][cv$loopIndex]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var21$countGlobal, initialStateDistribution);
	}

	private final void sample30(int var26) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample63) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if((1 < length$metric[sample$var32][0])) {
					if(fixedFlag$sample50) {
						if((var26 == st[sample$var32][0]))
							cv$var27$countGlobal[st[sample$var32][1]] = (cv$var27$countGlobal[st[sample$var32][1]] + 1.0);
					} else
						cv$var27$countGlobal[st[sample$var32][1]] = (cv$var27$countGlobal[st[sample$var32][1]] + distribution$sample50[sample$var32][var26]);
				}
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 2; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					if((var26 == st[sample$var32][(timeStep$var49 - 1)]))
						cv$var27$countGlobal[st[sample$var32][timeStep$var49]] = (cv$var27$countGlobal[st[sample$var32][timeStep$var49]] + 1.0);
				}
			}
		} else {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if((1 < length$metric[sample$var32][0])) {
					if(fixedFlag$sample50) {
						if((var26 == st[sample$var32][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample63[sample$var32][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample50[sample$var32][var26];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + (distribution$sample63[sample$var32][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					int index$timeStep$52 = (timeStep$var49 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample63[sample$var32][(index$timeStep$52 - 1)][var26];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + (distribution$sample63[sample$var32][(timeStep$var49 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, m[var26]);
	}

	private final void sample50(int sample$var32) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample63 && (1 < length$metric[sample$var32][0]))) {
				double[] cv$temp$1$var55 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[sample$var32][1]) && (st[sample$var32][1] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[sample$var32][1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < length$metric[sample$var32][0])) {
				for(int server = 0; server < noServers; server += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][0], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
				for(int server = 0; server < noServers; server += 1)
					guard$sample50gaussian172$global[sample$var32][server][0] = false;
				for(int server = 0; server < noServers; server += 1) {
					if(!guard$sample50gaussian172$global[sample$var32][server][0]) {
						guard$sample50gaussian172$global[sample$var32][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if(metric_valid_g[sample$var32][server][0]) {
							double cv$temp$4$var154 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$4$var154))) - (Math.log(cv$temp$4$var154) * 0.5));
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
				}
				for(int server = 0; server < noServers; server += 1) {
					if(!guard$sample50gaussian172$global[sample$var32][server][0]) {
						guard$sample50gaussian172$global[sample$var32][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if(metric_valid_g[sample$var32][server][0]) {
							double cv$temp$12$var154 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$12$var154))) - (Math.log(cv$temp$12$var154) * 0.5));
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
				}
			}
			if((!fixedFlag$sample63 && (1 < length$metric[sample$var32][0]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var56[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var56, 1.0, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample63[sample$var32][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = cv$distributionAccumulator$var56[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			cv$var44$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample50[sample$var32];
		double cv$logSum;
		double cv$lseMax = cv$var44$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var44$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var44$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var44$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var44$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var44$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var44$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var44$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var44$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample63(int sample$var32, int timeStep$var49) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var49)) {
				if(fixedFlag$sample50) {
					int var26 = st[sample$var32][0];
					if(((0 <= var26) && (var26 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var55 = m[st[sample$var32][0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var55.length)?Math.log(cv$temp$0$var55[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample$var32][0])) {
							for(int server = 0; server < noServers; server += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][1], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
							for(int server = 0; server < noServers; server += 1)
								guard$sample63gaussian172$global[sample$var32][server][1] = false;
							for(int server = 0; server < noServers; server += 1) {
								if(!guard$sample63gaussian172$global[sample$var32][server][1]) {
									guard$sample63gaussian172$global[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$11$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$11$var154))) - (Math.log(cv$temp$11$var154) * 0.5));
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
							}
							for(int server = 0; server < noServers; server += 1) {
								if(!guard$sample63gaussian172$global[sample$var32][server][1]) {
									guard$sample63gaussian172$global[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$43$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$43$var154))) - (Math.log(cv$temp$43$var154) * 0.5));
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
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample50$5 = 0; index$sample50$5 < noStates; index$sample50$5 += 1) {
						double cv$probabilitySample50Value6 = distribution$sample50[sample$var32][index$sample50$5];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample50Value6);
						double[] cv$temp$1$var55 = m[index$sample50$5];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample50Value6) + ((cv$valuePos < cv$temp$1$var55.length)?Math.log(cv$temp$1$var55[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample$var32][0])) {
							for(int server = 0; server < noServers; server += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][1], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
							for(int server = 0; server < noServers; server += 1)
								guard$sample63gaussian172$global[sample$var32][server][1] = false;
							for(int server = 0; server < noServers; server += 1) {
								if(!guard$sample63gaussian172$global[sample$var32][server][1]) {
									guard$sample63gaussian172$global[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$19$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$19$var154))) - (Math.log(cv$temp$19$var154) * 0.5));
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
							}
							for(int server = 0; server < noServers; server += 1) {
								if(!guard$sample63gaussian172$global[sample$var32][server][1]) {
									guard$sample63gaussian172$global[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$51$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$51$var154))) - (Math.log(cv$temp$51$var154) * 0.5));
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
			int index$timeStep$13 = (timeStep$var49 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var49))) {
				for(int index$sample63$14 = 0; index$sample63$14 < noStates; index$sample63$14 += 1) {
					double cv$probabilitySample63Value15 = distribution$sample63[sample$var32][(index$timeStep$13 - 1)][index$sample63$14];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample63Value15);
					double[] cv$temp$3$var55 = m[index$sample63$14];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample63Value15) + ((cv$valuePos < cv$temp$3$var55.length)?Math.log(cv$temp$3$var55[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][timeStep$var49], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
					for(int server = 0; server < noServers; server += 1)
						guard$sample63gaussian172$global[sample$var32][server][timeStep$var49] = false;
					for(int server = 0; server < noServers; server += 1) {
						if(!guard$sample63gaussian172$global[sample$var32][server][timeStep$var49]) {
							guard$sample63gaussian172$global[sample$var32][server][timeStep$var49] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if(metric_valid_g[sample$var32][server][timeStep$var49]) {
								double cv$temp$35$var154 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$35$var154))) - (Math.log(cv$temp$35$var154) * 0.5));
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
					}
					for(int server = 0; server < noServers; server += 1) {
						if(!guard$sample63gaussian172$global[sample$var32][server][timeStep$var49]) {
							guard$sample63gaussian172$global[sample$var32][server][timeStep$var49] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if(metric_valid_g[sample$var32][server][timeStep$var49]) {
								double cv$temp$67$var154 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$67$var154))) - (Math.log(cv$temp$67$var154) * 0.5));
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
			int index$timeStep$248_3 = (timeStep$var49 + 1);
			if((index$timeStep$248_3 < length$metric[sample$var32][0])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var56[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == timeStep$var49)) {
					if(fixedFlag$sample50) {
						int index$var26$259_1 = st[sample$var32][0];
						if(((0 <= index$var26$259_1) && (index$var26$259_1 < noStates)))
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						for(int index$sample50$255 = 0; index$sample50$255 < noStates; index$sample50$255 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample50[sample$var32][index$sample50$255]);
					}
				}
				int index$timeStep$263 = (timeStep$var49 - 1);
				if((((1 <= index$timeStep$263) && !(index$timeStep$263 == timeStep$var49)) && !(index$timeStep$263 == index$timeStep$248_3))) {
					for(int index$sample63$264 = 0; index$sample63$264 < noStates; index$sample63$264 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample63[sample$var32][(index$timeStep$263 - 1)][index$sample63$264]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var56, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample63[sample$var32][(index$timeStep$248_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var56[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var57$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample63[sample$var32][(timeStep$var49 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var57$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var57$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var57$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var57$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var57$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var57$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var57$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var57$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var57$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample96(int var82, int var86) {
		double cv$originalValue = current_metric_mean[var82][var86];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				if((metric_valid_g[sample$var120][var82][0] && (0 < length$metric[sample$var120][0]))) {
					if(fixedFlag$sample50) {
						if((var86 == st[sample$var120][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var100 = st[sample$var120][0];
							if(((0 <= var100) && (var100 < noStates))) {
								double cv$temp$3$var154 = current_metric_var[var82][st[sample$var120][0]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var154))) - (Math.log(cv$temp$3$var154) * 0.5));
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
					} else {
						double cv$probabilitySample50Value7 = distribution$sample50[sample$var120][var86];
						double var154 = current_metric_var[var82][var86];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample50Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample50Value7), 0.0);
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
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
					if(metric_valid_g[sample$var120][var82][timeStep$var140]) {
						if(fixedFlag$sample63) {
							if((var86 == st[sample$var120][timeStep$var140])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var100 = st[sample$var120][timeStep$var140];
								if(((0 <= var100) && (var100 < noStates))) {
									double cv$temp$21$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$21$var154))) - (Math.log(cv$temp$21$var154) * 0.5));
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
						} else {
							double cv$probabilitySample63Value19 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][var86];
							double var154 = current_metric_var[var82][var86];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample63Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample63Value19), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		current_metric_mean[var82][var86] = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			if((metric_valid_g[sample$var120][var82][0] && (0 < length$metric[sample$var120][0]))) {
				if(fixedFlag$sample50) {
					if((var86 == st[sample$var120][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var100 = st[sample$var120][0];
						if(((0 <= var100) && (var100 < noStates))) {
							double cv$temp$3$var154 = current_metric_var[var82][st[sample$var120][0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var154))) - (Math.log(cv$temp$3$var154) * 0.5));
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
				} else {
					double cv$probabilitySample50Value7 = distribution$sample50[sample$var120][var86];
					double var154 = current_metric_var[var82][var86];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample50Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample50Value7), 0.0);
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
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				if(metric_valid_g[sample$var120][var82][timeStep$var140]) {
					if(fixedFlag$sample63) {
						if((var86 == st[sample$var120][timeStep$var140])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var100 = st[sample$var120][timeStep$var140];
							if(((0 <= var100) && (var100 < noStates))) {
								double cv$temp$21$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$21$var154))) - (Math.log(cv$temp$21$var154) * 0.5));
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
					} else {
						double cv$probabilitySample63Value19 = distribution$sample63[sample$var120][(timeStep$var140 - 1)][var86];
						double var154 = current_metric_var[var82][var86];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample63Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample63Value19), 0.0);
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
		if((((cv$accumulatedProbabilities - cv$originalProbability) <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN((cv$accumulatedProbabilities - cv$originalProbability))))
			current_metric_mean[var82][var86] = cv$originalValue;
	}

	@Override
	public final void allocateScratch() {
		cv$var21$countGlobal = new double[Math.max(0, noStates)];
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		cv$var27$countGlobal = new double[cv$max];
		cv$distributionAccumulator$var56 = new double[noStates];
		cv$var44$stateProbabilityGlobal = new double[noStates];
		{
			int cv$max_server = 0;
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, length$metric[sample$var120][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			guard$sample50gaussian172$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var140];
		}
		cv$var57$stateProbabilityGlobal = new double[noStates];
		int cv$max_server = 0;
		int cv$max_timeStep$var140 = 0;
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, length$metric[sample$var120][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		guard$sample63gaussian172$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var140];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$initialStateDistribution)
			initialStateDistribution = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var26 = 0; var26 < noStates; var26 += 1)
				m[var26] = new double[noStates];
		}
		if(!setFlag$st) {
			st = new int[length$metric.length][];
			for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
				st[sample$var32] = new int[length$metric[sample$var32][0]];
		}
		if(!setFlag$metric_g) {
			metric_g = new double[length$metric.length][][];
			for(int var67 = 0; var67 < length$metric.length; var67 += 1)
				metric_g[var67] = new double[length$metric[0].length][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_g[sample$var120][server] = new double[length$metric[sample$var120][0]];
			}
		}
		if(!setFlag$metric_valid_g) {
			metric_valid_g = new boolean[length$metric.length][][];
			for(int var73 = 0; var73 < length$metric.length; var73 += 1)
				metric_valid_g[var73] = new boolean[length$metric[0].length][];
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_valid_g[sample$var120][server] = new boolean[length$metric[sample$var120][0]];
			}
		}
		if(!setFlag$current_metric_mean) {
			current_metric_mean = new double[length$metric[0].length][];
			for(int var82 = 0; var82 < length$metric[0].length; var82 += 1)
				current_metric_mean[var82] = new double[noStates];
		}
		if(!setFlag$current_metric_var) {
			current_metric_var = new double[length$metric[0].length][];
			for(int var96 = 0; var96 < length$metric[0].length; var96 += 1)
				current_metric_var[var96] = new double[noStates];
		}
		if(!setFlag$current_metric_valid_bias) {
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var110 = 0; var110 < length$metric[0].length; var110 += 1)
				current_metric_valid_bias[var110] = new double[noStates];
		}
		distribution$sample50 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			distribution$sample50[sample$var32] = new double[noStates];
		distribution$sample63 = new double[length$metric.length][][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var32][0] - 1)][];
			distribution$sample63[sample$var32] = subarray$0;
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				subarray$0[(timeStep$var49 - 1)] = new double[noStates];
		}
		logProbability$var43 = new double[length$metric.length];
		logProbability$sample50 = new double[length$metric.length];
		logProbability$var56 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			logProbability$var56[sample$var32] = new double[(length$metric[sample$var32][0] - 1)];
		logProbability$sample63 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			logProbability$sample63[sample$var32] = new double[(length$metric[sample$var32][0] - 1)];
		logProbability$var145 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var145[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		logProbability$sample161 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample161[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		logProbability$var155 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var155[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		logProbability$sample173 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample173[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample50)
				st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample63) {
				int[] var50 = st[sample$var32];
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		if(!fixedFlag$sample96) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample111) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample126) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
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
					if((var135[server][timeStep$var140] && !fixedFlag$sample173))
						metric_inner[timeStep$var140] = ((Math.sqrt(current_metric_var[server][st[sample$var120][timeStep$var140]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var120][timeStep$var140]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample50) {
				double[] cv$distribution$sample50 = distribution$sample50[sample$var32];
				for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1)
					cv$distribution$sample50[index$var43] = ((index$var43 < initialStateDistribution.length)?initialStateDistribution[index$var43]:0.0);
			}
			if(!fixedFlag$sample63) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double[] cv$distribution$sample63 = distribution$sample63[sample$var32][(timeStep$var49 - 1)];
					for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
						cv$distribution$sample63[index$var56] = 0.0;
					if((1 == timeStep$var49)) {
						if(fixedFlag$sample50) {
							int var26 = st[sample$var32][0];
							if(((0 <= var26) && (var26 < noStates))) {
								double[] var55 = m[st[sample$var32][0]];
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + ((index$var56 < var55.length)?var55[index$var56]:0.0));
							}
						} else {
							for(int index$sample50$3 = 0; index$sample50$3 < noStates; index$sample50$3 += 1) {
								double cv$probabilitySample50Value4 = distribution$sample50[sample$var32][index$sample50$3];
								double[] var55 = m[index$sample50$3];
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (cv$probabilitySample50Value4 * ((index$var56 < var55.length)?var55[index$var56]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var49 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample63$12 = 0; index$sample63$12 < noStates; index$sample63$12 += 1) {
							double cv$probabilitySample63Value13 = distribution$sample63[sample$var32][(index$timeStep$11 - 1)][index$sample63$12];
							double[] var55 = m[index$sample63$12];
							for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
								cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] + (cv$probabilitySample63Value13 * ((index$var56 < var55.length)?var55[index$var56]:0.0)));
						}
					}
					double cv$var56$sum = 0.0;
					for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
						cv$var56$sum = (cv$var56$sum + cv$distribution$sample63[index$var56]);
					for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
						cv$distribution$sample63[index$var56] = (cv$distribution$sample63[index$var56] / cv$var56$sum);
				}
			}
		}
		if(!fixedFlag$sample96) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample111) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample126) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample50)
				st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample63) {
				int[] var50 = st[sample$var32];
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		if(!fixedFlag$sample96) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample111) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample126) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample24)
				sample24();
			if(!fixedFlag$sample30) {
				for(int var26 = 0; var26 < noStates; var26 += 1)
					sample30(var26);
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if(!fixedFlag$sample50)
					sample50(sample$var32);
				if(!fixedFlag$sample63) {
					for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
						sample63(sample$var32, timeStep$var49);
				}
			}
			if(!fixedFlag$sample96) {
				for(int var82 = 0; var82 < noServers; var82 += 1) {
					for(int var86 = 0; var86 < noStates; var86 += 1)
						sample96(var82, var86);
				}
			}
			if(!fixedFlag$sample111) {
				for(int var96 = 0; var96 < noServers; var96 += 1) {
					for(int var100 = 0; var100 < noStates; var100 += 1)
						sample111(var96, var100);
				}
			}
			if(!fixedFlag$sample126) {
				for(int var110 = 0; var110 < noServers; var110 += 1) {
					for(int var114 = 0; var114 < noStates; var114 += 1)
						sample126(var110, var114);
				}
			}
		} else {
			if(!fixedFlag$sample126) {
				for(int var110 = (noServers - 1); var110 >= 0; var110 -= 1) {
					for(int var114 = (noStates - 1); var114 >= 0; var114 -= 1)
						sample126(var110, var114);
				}
			}
			if(!fixedFlag$sample111) {
				for(int var96 = (noServers - 1); var96 >= 0; var96 -= 1) {
					for(int var100 = (noStates - 1); var100 >= 0; var100 -= 1)
						sample111(var96, var100);
				}
			}
			if(!fixedFlag$sample96) {
				for(int var82 = (noServers - 1); var82 >= 0; var82 -= 1) {
					for(int var86 = (noStates - 1); var86 >= 0; var86 -= 1)
						sample96(var82, var86);
				}
			}
			for(int sample$var32 = (noSamples - 1); sample$var32 >= 0; sample$var32 -= 1) {
				if(!fixedFlag$sample63) {
					for(int timeStep$var49 = (length$metric[sample$var32][0] - 1); timeStep$var49 >= 1; timeStep$var49 -= 1)
						sample63(sample$var32, timeStep$var49);
				}
				if(!fixedFlag$sample50)
					sample50(sample$var32);
			}
			if(!fixedFlag$sample30) {
				for(int var26 = (noStates - 1); var26 >= 0; var26 -= 1)
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
			logProbability$var43[sample$var32] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample50) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				logProbability$sample50[sample$var32] = 0.0;
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				logProbability$var56[sample$var32][(timeStep$var49 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample63) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					logProbability$sample63[sample$var32][(timeStep$var49 - 1)] = 0.0;
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
					logProbability$var145[sample$var120][server][timeStep$var140] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample161) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample161[sample$var120][server][timeStep$var140] = 0.0;
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var155[sample$var120][server][timeStep$var140] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample173) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample173[sample$var120][server][timeStep$var140] = 0.0;
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
		if(!fixedFlag$sample30) {
			for(int var26 = 0; var26 < noStates; var26 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var26]);
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			if(!fixedFlag$sample50)
				st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample63) {
				int[] var50 = st[sample$var32];
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var32][(timeStep$var49 - 1)]]);
			}
		}
		if(!fixedFlag$sample96) {
			for(int var82 = 0; var82 < noServers; var82 += 1) {
				double[] var83 = current_metric_mean[var82];
				for(int var86 = 0; var86 < noStates; var86 += 1)
					var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample111) {
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				double[] var97 = current_metric_var[var96];
				for(int var100 = 0; var100 < noStates; var100 += 1)
					var97[var100] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample126) {
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				double[] var111 = current_metric_valid_bias[var110];
				for(int var114 = 0; var114 < noStates; var114 += 1)
					var111[var114] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics4(\n               double[][][] metric,\n               boolean[][][] metric_valid, \n               int max_metric,\n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    //Calculate all the state transitions\n    int[][] st = new int[noSamples][];\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n    }\n    \n    // Calculate the number of servers\n    int noServers = metric[0].length;    \n    \n    // Allocate space for each generated metric.    \n    double[][][] metric_g = new double[noSamples][noServers][];\n    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n\n    // Calculate metric parameters\n    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n    \n    // Compute the values of each metric\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        for(int server = 0; server < noServers; server++) {\n            //Allocate space for the time series\n            double[] metric_inner = new double[streamLength];\n            metric_g[sample][server] = metric_inner;\n            \n            boolean[] metric_valid_inner = new boolean[streamLength];\n            metric_valid_g[sample][server] = metric_valid_inner;\n            \n            //Generate values.\n            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n                int currentState = st[sample][timeStep];\n                \n                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n                if(metric_valid_inner[timeStep])\n                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n            }\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n    metric_g.observe(metric);\n}";
	}
}