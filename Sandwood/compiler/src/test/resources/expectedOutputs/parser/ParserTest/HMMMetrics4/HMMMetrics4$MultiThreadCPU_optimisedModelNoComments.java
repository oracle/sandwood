package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics4$CoreInterface {
	private double[][] current_metric_mean;
	private double[][] current_metric_valid_bias;
	private double[][] current_metric_var;
	private double[][] cv$distributionAccumulator$var56;
	private double[] cv$var21$countGlobal;
	private double[][] cv$var27$countGlobal;
	private double[][] cv$var44$stateProbabilityGlobal;
	private double[][] cv$var57$stateProbabilityGlobal;
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
	private boolean[][][][] guard$sample48gaussian163$global;
	private boolean[][][][] guard$sample61gaussian163$global;
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
		fixedProbFlag$sample106 = (cv$value && fixedProbFlag$sample106);
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample120() {
		return fixedFlag$sample120;
	}

	@Override
	public final void set$fixedFlag$sample120(boolean cv$value) {
		fixedFlag$sample120 = cv$value;
		fixedProbFlag$sample120 = (cv$value && fixedProbFlag$sample120);
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
	}

	@Override
	public final boolean get$fixedFlag$sample152() {
		return fixedFlag$sample152;
	}

	@Override
	public final void set$fixedFlag$sample152(boolean cv$value) {
		fixedFlag$sample152 = cv$value;
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
	}

	@Override
	public final boolean get$fixedFlag$sample164() {
		return fixedFlag$sample164;
	}

	@Override
	public final void set$fixedFlag$sample164(boolean cv$value) {
		fixedFlag$sample164 = cv$value;
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (cv$value && fixedProbFlag$sample24);
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (cv$value && fixedProbFlag$sample48);
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		fixedProbFlag$sample152 = (cv$value && fixedProbFlag$sample152);
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
	}

	@Override
	public final boolean get$fixedFlag$sample92() {
		return fixedFlag$sample92;
	}

	@Override
	public final void set$fixedFlag$sample92(boolean cv$value) {
		fixedFlag$sample92 = cv$value;
		fixedProbFlag$sample92 = (cv$value && fixedProbFlag$sample92);
		fixedProbFlag$sample164 = (cv$value && fixedProbFlag$sample164);
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						boolean cv$sampleValue = metric_valid_g[sample$var120][server][timeStep$var140];
						if((0 == timeStep$var140)) {
							if(fixedFlag$sample48) {
								int var114 = st[sample$var120][0];
								if(((0 <= var114) && (var114 < noStates))) {
									cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var120][0]]);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample48$4 = 0; index$sample48$4 < noStates; index$sample48$4 += 1) {
									double cv$probabilitySample48Value5 = distribution$sample48[sample$var120][index$sample48$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample48Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample48$4]));
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
						if((1 <= timeStep$var140)) {
							if(fixedFlag$sample61) {
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
								for(int index$sample61$13 = 0; index$sample61$13 < noStates; index$sample61$13 += 1) {
									double cv$probabilitySample61Value14 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][index$sample61$13];
									double cv$weightedProbability = (Math.log(cv$probabilitySample61Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample61$13]));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$sample152[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample152[sample$var120][server][timeStep$var140];
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

	private final void logProbabilityDistribution$sample164() {
		if(!fixedProbFlag$sample164) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							double cv$sampleValue = metric_g[sample$var120][server][timeStep$var140];
							if(((0 == timeStep$var140) && metric_valid_g[sample$var120][server][0])) {
								if(fixedFlag$sample48) {
									if((0 <= st[sample$var120][0])) {
										int var86 = st[sample$var120][0];
										if(((0 <= var86) && (var86 < noStates))) {
											double var154 = current_metric_var[server][st[sample$var120][0]];
											cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var120][0]]) / Math.sqrt(var154))) - (Math.log(var154) * 0.5));
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									for(int index$sample48$4 = 0; index$sample48$4 < noStates; index$sample48$4 += 1) {
										double cv$probabilitySample48Value5 = distribution$sample48[sample$var120][index$sample48$4];
										double var154 = current_metric_var[server][index$sample48$4];
										double cv$weightedProbability = ((Math.log(cv$probabilitySample48Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample48$4]) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
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
							if((1 <= timeStep$var140)) {
								if(fixedFlag$sample61) {
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
									for(int index$sample61$49 = 0; index$sample61$49 < noStates; index$sample61$49 += 1) {
										double cv$probabilitySample61Value50 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][index$sample61$49];
										double var154 = current_metric_var[server][index$sample61$49];
										double cv$weightedProbability = ((Math.log(cv$probabilitySample61Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample61$49]) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
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
							if((cv$probabilityReached == 0.0))
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							logProbability$sample164[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
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
							double cv$sampleValue = logProbability$sample164[sample$var120][server][timeStep$var140];
							cv$accumulator = (cv$accumulator + cv$sampleValue);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$sampleValue;
							logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
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
					int cv$sampleValue = st[sample$var32][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var43[sample$var32] = cv$distributionAccumulator;
					logProbability$sample48[sample$var32] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample48 = fixedFlag$sample24;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = logProbability$sample48[sample$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[sample$var32] = cv$rvAccumulator;
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample$var32][timeStep$var49];
						if((1 == timeStep$var49)) {
							if(fixedFlag$sample48) {
								int var26 = st[sample$var32][0];
								if(((0 <= var26) && (var26 < noStates))) {
									double[] var55 = m[st[sample$var32][0]];
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample48$6 = 0; index$sample48$6 < noStates; index$sample48$6 += 1) {
									double cv$probabilitySample48Value7 = distribution$sample48[sample$var32][index$sample48$6];
									double[] var55 = m[index$sample48$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample48Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						logProbability$sample61[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample61 = (fixedFlag$sample30 && fixedFlag$sample48);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					double cv$rvAccumulator = logProbability$sample61[sample$var32][(timeStep$var49 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var96 = 0; var96 < noServers; var96 += 1) {
				for(int var100 = 0; var100 < noStates; var100 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var96][var100], 1.0, 1.0));
			}
			logProbability$var92 = cv$sampleAccumulator;
			logProbability$var101 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample106)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample106 = fixedFlag$sample106;
		} else {
			logProbability$var92 = logProbability$var101;
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var101);
			logProbability$$model = (logProbability$$model + logProbability$var101);
			if(fixedFlag$sample106)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var101);
		}
	}

	private final void logProbabilityValue$sample120() {
		if(!fixedProbFlag$sample120) {
			double cv$sampleAccumulator = 0.0;
			for(int var110 = 0; var110 < noServers; var110 += 1) {
				for(int var114 = 0; var114 < noStates; var114 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var110][var114], 1.0, 1.0));
			}
			logProbability$var106 = cv$sampleAccumulator;
			logProbability$var115 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample120)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample120 = fixedFlag$sample120;
		} else {
			logProbability$var106 = logProbability$var115;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var115);
			logProbability$$model = (logProbability$$model + logProbability$var115);
			if(fixedFlag$sample120)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var115);
		}
	}

	private final void logProbabilityValue$sample152() {
		if(!fixedProbFlag$sample152) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var120][server][timeStep$var140], current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var145[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$sample152[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample152[sample$var120][server][timeStep$var140];
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

	private final void logProbabilityValue$sample164() {
		if(!fixedProbFlag$sample164) {
			double cv$accumulator = 0.0;
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
						if(metric_valid_g[sample$var120][server][timeStep$var140]) {
							double var154 = current_metric_var[server][st[sample$var120][timeStep$var140]];
							double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][server][timeStep$var140] - current_metric_mean[server][st[sample$var120][timeStep$var140]]) / Math.sqrt(var154))) - (Math.log(var154) * 0.5));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							logProbability$sample164[sample$var120][server][timeStep$var140] = cv$distributionAccumulator;
							logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
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
							double cv$sampleValue = logProbability$sample164[sample$var120][server][timeStep$var140];
							cv$accumulator = (cv$accumulator + cv$sampleValue);
							logProbability$var155[sample$var120][server][timeStep$var140] = cv$sampleValue;
							logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
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

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				int cv$sampleValue = st[sample$var32][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var43[sample$var32] = cv$distributionAccumulator;
				logProbability$sample48[sample$var32] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedFlag$sample24);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				double cv$rvAccumulator = logProbability$sample48[sample$var32];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var43[sample$var32] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var32][timeStep$var49];
					double[] var55 = m[st[sample$var32][(timeStep$var49 - 1)]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var55.length))?Math.log(var55[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
					logProbability$sample61[sample$var32][(timeStep$var49 - 1)] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample61[sample$var32][(timeStep$var49 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var56[sample$var32][(timeStep$var49 - 1)] = cv$rvAccumulator;
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
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample92 = fixedFlag$sample92;
		} else {
			logProbability$var78 = logProbability$var87;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var87);
			logProbability$$model = (logProbability$$model + logProbability$var87);
			if(fixedFlag$sample92)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var87);
		}
	}

	private final void sample106(int var96, int var100, int threadID$cv$var100, Rng RNG$) {
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
					if(fixedFlag$sample48) {
						if((var100 == st[sample$var120][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var86 = st[sample$var120][0];
							if(((0 <= var86) && (var86 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][st[sample$var120][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
								double cv$temp$30$var152 = current_metric_mean[var96][st[sample$var120][0]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$originalValue))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$originalValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								}
								double cv$temp$58$var152 = current_metric_mean[var96][st[sample$var120][0]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$originalValue))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$originalValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								}
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var100];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][var100]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						{
							double var152 = current_metric_mean[var96][var100];
							if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							}
						}
						double var152 = current_metric_mean[var96][var100];
						if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							else
								cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						}
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
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
						if(fixedFlag$sample61) {
							if((var100 == st[sample$var120][timeStep$var140])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var86 = st[sample$var120][timeStep$var140];
								if(((0 <= var86) && (var86 < noStates))) {
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][st[sample$var120][timeStep$var140]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
									double cv$temp$48$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
									if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$originalValue))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$originalValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
									}
									double cv$temp$76$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
									if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$originalValue))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$originalValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
									}
									cv$consumerDistributionProbabilityAccumulator = -2.0;
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
							double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var100];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][var100]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							{
								double var152 = current_metric_mean[var96][var100];
								if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								}
							}
							double var152 = current_metric_mean[var96][var100];
							if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - ((Math.log(cv$originalValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$originalValue) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							}
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
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
				if(fixedFlag$sample48) {
					if((var100 == st[sample$var120][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var86 = st[sample$var120][0];
						if(((0 <= var86) && (var86 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][st[sample$var120][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
							double cv$temp$30$var152 = current_metric_mean[var96][st[sample$var120][0]];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$proposedValue))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$proposedValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$30$var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							}
							double cv$temp$58$var152 = current_metric_mean[var96][st[sample$var120][0]];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$proposedValue))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$proposedValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - cv$temp$58$var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							}
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var100];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - current_metric_mean[var96][var100]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					{
						double var152 = current_metric_mean[var96][var100];
						if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							else
								cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						}
					}
					double var152 = current_metric_mean[var96][var100];
					if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						else
							cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][0] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					}
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
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
					if(fixedFlag$sample61) {
						if((var100 == st[sample$var120][timeStep$var140])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var86 = st[sample$var120][timeStep$var140];
							if(((0 <= var86) && (var86 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][st[sample$var120][timeStep$var140]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
								double cv$temp$48$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$proposedValue))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$proposedValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$48$var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
								}
								double cv$temp$76$var152 = current_metric_mean[var96][st[sample$var120][timeStep$var140]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$proposedValue))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$proposedValue))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - cv$temp$76$var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
								}
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var100];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - current_metric_mean[var96][var100]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						{
							double var152 = current_metric_mean[var96][var100];
							if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							}
						}
						double var152 = current_metric_mean[var96][var100];
						if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - ((Math.log(cv$proposedValue) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
							else
								cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$proposedValue) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var96][timeStep$var140] - var152) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						}
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
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

	private final void sample120(int var110, int var114, int threadID$cv$var114, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			if((0 < length$metric[sample$var120][0])) {
				if(fixedFlag$sample48) {
					if((var114 == st[sample$var120][0])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var120][var110][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var114];
					cv$count = (cv$count + cv$probabilitySample48Value7);
					if(metric_valid_g[sample$var120][var110][0])
						cv$sum = (cv$sum + cv$probabilitySample48Value7);
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int timeStep$var140 = 1; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1) {
				if(fixedFlag$sample61) {
					if((var114 == st[sample$var120][timeStep$var140])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var120][var110][timeStep$var140])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var114];
					cv$count = (cv$count + cv$probabilitySample61Value19);
					if(metric_valid_g[sample$var120][var110][timeStep$var140])
						cv$sum = (cv$sum + cv$probabilitySample61Value19);
				}
			}
		}
		current_metric_valid_bias[var110][var114] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample24() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var21$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample48) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				cv$var21$countGlobal[st[sample$var32][0]] = (cv$var21$countGlobal[st[sample$var32][0]] + 1.0);
		} else {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var21$countGlobal[cv$loopIndex] = (cv$var21$countGlobal[cv$loopIndex] + distribution$sample48[sample$var32][cv$loopIndex]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var21$countGlobal, initialStateDistribution);
	}

	private final void sample30(int var26, int threadID$cv$var26, Rng RNG$) {
		double[] cv$countLocal = cv$var27$countGlobal[threadID$cv$var26];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample61) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if((1 < length$metric[sample$var32][0])) {
					if(fixedFlag$sample48) {
						if((var26 == st[sample$var32][0]))
							cv$countLocal[st[sample$var32][1]] = (cv$countLocal[st[sample$var32][1]] + 1.0);
					} else
						cv$countLocal[st[sample$var32][1]] = (cv$countLocal[st[sample$var32][1]] + distribution$sample48[sample$var32][var26]);
				}
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 2; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					if((var26 == st[sample$var32][(timeStep$var49 - 1)]))
						cv$countLocal[st[sample$var32][timeStep$var49]] = (cv$countLocal[st[sample$var32][timeStep$var49]] + 1.0);
				}
			}
		} else {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				if((1 < length$metric[sample$var32][0])) {
					if(fixedFlag$sample48) {
						if((var26 == st[sample$var32][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample61[sample$var32][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample48[sample$var32][var26];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[sample$var32][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
					int index$timeStep$52 = (timeStep$var49 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample61[sample$var32][(index$timeStep$52 - 1)][var26];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample61[sample$var32][(timeStep$var49 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var26]);
	}

	private final void sample48(int sample$var32, int threadID$cv$sample$var32, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var44$stateProbabilityGlobal[threadID$cv$sample$var32];
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample61 && (1 < length$metric[sample$var32][0]))) {
				double[] cv$temp$1$var55 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[sample$var32][1]) && (st[sample$var32][1] < cv$temp$1$var55.length))?Math.log(cv$temp$1$var55[st[sample$var32][1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < length$metric[sample$var32][0])) {
				for(int server = 0; server < noServers; server += 1)
					cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][0], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
				boolean[][][] guard$sample48gaussian163 = guard$sample48gaussian163$global[threadID$cv$sample$var32];
				for(int server = 0; server < noServers; server += 1)
					guard$sample48gaussian163[sample$var32][server][0] = false;
				for(int server = 0; server < noServers; server += 1) {
					if(!guard$sample48gaussian163[sample$var32][server][0]) {
						guard$sample48gaussian163[sample$var32][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if(metric_valid_g[sample$var32][server][0]) {
							double cv$temp$4$var154 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$4$var154))) - (Math.log(cv$temp$4$var154) * 0.5));
							double cv$temp$19$var152 = current_metric_mean[server][cv$valuePos];
							double cv$temp$20$var154 = current_metric_var[server][cv$valuePos];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$19$var152) / Math.sqrt(cv$temp$20$var154))) - (Math.log(cv$temp$20$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$19$var152) / Math.sqrt(cv$temp$20$var154))) - ((Math.log(cv$temp$20$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$19$var152) / Math.sqrt(cv$temp$20$var154))) - (Math.log(cv$temp$20$var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$20$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$19$var152) / Math.sqrt(cv$temp$20$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$19$var152) / Math.sqrt(cv$temp$20$var154)))) - (Math.log(cv$temp$20$var154) * 0.5));
							}
							double cv$temp$35$var152 = current_metric_mean[server][cv$valuePos];
							double cv$temp$36$var154 = current_metric_var[server][cv$valuePos];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$35$var152) / Math.sqrt(cv$temp$36$var154))) - (Math.log(cv$temp$36$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$35$var152) / Math.sqrt(cv$temp$36$var154))) - ((Math.log(cv$temp$36$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$35$var152) / Math.sqrt(cv$temp$36$var154))) - (Math.log(cv$temp$36$var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$36$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$35$var152) / Math.sqrt(cv$temp$36$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$35$var152) / Math.sqrt(cv$temp$36$var154)))) - (Math.log(cv$temp$36$var154) * 0.5));
							}
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					if(!guard$sample48gaussian163[sample$var32][server][0]) {
						guard$sample48gaussian163[sample$var32][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if(metric_valid_g[sample$var32][server][0]) {
							double cv$temp$12$var154 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$12$var154))) - (Math.log(cv$temp$12$var154) * 0.5));
							double cv$temp$27$var152 = current_metric_mean[server][cv$valuePos];
							double cv$temp$28$var154 = current_metric_var[server][cv$valuePos];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$27$var152) / Math.sqrt(cv$temp$28$var154))) - (Math.log(cv$temp$28$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$27$var152) / Math.sqrt(cv$temp$28$var154))) - ((Math.log(cv$temp$28$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$27$var152) / Math.sqrt(cv$temp$28$var154))) - (Math.log(cv$temp$28$var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$28$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$27$var152) / Math.sqrt(cv$temp$28$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$27$var152) / Math.sqrt(cv$temp$28$var154)))) - (Math.log(cv$temp$28$var154) * 0.5));
							}
							double cv$temp$43$var152 = current_metric_mean[server][cv$valuePos];
							double cv$temp$44$var154 = current_metric_var[server][cv$valuePos];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$43$var152) / Math.sqrt(cv$temp$44$var154))) - (Math.log(cv$temp$44$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$43$var152) / Math.sqrt(cv$temp$44$var154))) - ((Math.log(cv$temp$44$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$43$var152) / Math.sqrt(cv$temp$44$var154))) - (Math.log(cv$temp$44$var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$44$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$43$var152) / Math.sqrt(cv$temp$44$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][0] - cv$temp$43$var152) / Math.sqrt(cv$temp$44$var154)))) - (Math.log(cv$temp$44$var154) * 0.5));
							}
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
			if((!fixedFlag$sample61 && (1 < length$metric[sample$var32][0]))) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56[threadID$cv$sample$var32];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample61[sample$var32][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = cv$accumulatedConsumerDistributions[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample48[sample$var32];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample61(int sample$var32, int timeStep$var49, int threadID$cv$sample$var32, Rng RNG$) {
		double[] cv$stateProbabilityLocal = cv$var57$stateProbabilityGlobal[threadID$cv$sample$var32];
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var49)) {
				if(fixedFlag$sample48) {
					int var26 = st[sample$var32][0];
					if(((0 <= var26) && (var26 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var55 = m[st[sample$var32][0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var55.length)?Math.log(cv$temp$0$var55[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample$var32][0])) {
							for(int server = 0; server < noServers; server += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][1], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
							boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global[threadID$cv$sample$var32];
							for(int server = 0; server < noServers; server += 1)
								guard$sample61gaussian163[sample$var32][server][1] = false;
							for(int server = 0; server < noServers; server += 1) {
								if(!guard$sample61gaussian163[sample$var32][server][1]) {
									guard$sample61gaussian163[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$11$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$11$var154))) - (Math.log(cv$temp$11$var154) * 0.5));
										double cv$temp$74$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$75$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$74$var152) / Math.sqrt(cv$temp$75$var154))) - (Math.log(cv$temp$75$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$74$var152) / Math.sqrt(cv$temp$75$var154))) - ((Math.log(cv$temp$75$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$74$var152) / Math.sqrt(cv$temp$75$var154))) - (Math.log(cv$temp$75$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$75$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$74$var152) / Math.sqrt(cv$temp$75$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$74$var152) / Math.sqrt(cv$temp$75$var154)))) - (Math.log(cv$temp$75$var154) * 0.5));
										}
										double cv$temp$138$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$139$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$138$var152) / Math.sqrt(cv$temp$139$var154))) - (Math.log(cv$temp$139$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$138$var152) / Math.sqrt(cv$temp$139$var154))) - ((Math.log(cv$temp$139$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$138$var152) / Math.sqrt(cv$temp$139$var154))) - (Math.log(cv$temp$139$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$139$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$138$var152) / Math.sqrt(cv$temp$139$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$138$var152) / Math.sqrt(cv$temp$139$var154)))) - (Math.log(cv$temp$139$var154) * 0.5));
										}
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
								if(!guard$sample61gaussian163[sample$var32][server][1]) {
									guard$sample61gaussian163[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$43$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$43$var154))) - (Math.log(cv$temp$43$var154) * 0.5));
										double cv$temp$106$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$107$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$106$var152) / Math.sqrt(cv$temp$107$var154))) - (Math.log(cv$temp$107$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$106$var152) / Math.sqrt(cv$temp$107$var154))) - ((Math.log(cv$temp$107$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$106$var152) / Math.sqrt(cv$temp$107$var154))) - (Math.log(cv$temp$107$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$107$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$106$var152) / Math.sqrt(cv$temp$107$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$106$var152) / Math.sqrt(cv$temp$107$var154)))) - (Math.log(cv$temp$107$var154) * 0.5));
										}
										double cv$temp$170$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$171$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$170$var152) / Math.sqrt(cv$temp$171$var154))) - (Math.log(cv$temp$171$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$170$var152) / Math.sqrt(cv$temp$171$var154))) - ((Math.log(cv$temp$171$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$170$var152) / Math.sqrt(cv$temp$171$var154))) - (Math.log(cv$temp$171$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$171$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$170$var152) / Math.sqrt(cv$temp$171$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$170$var152) / Math.sqrt(cv$temp$171$var154)))) - (Math.log(cv$temp$171$var154) * 0.5));
										}
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					for(int index$sample48$5 = 0; index$sample48$5 < noStates; index$sample48$5 += 1) {
						double cv$probabilitySample48Value6 = distribution$sample48[sample$var32][index$sample48$5];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample48Value6);
						double[] cv$temp$1$var55 = m[index$sample48$5];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample48Value6) + ((cv$valuePos < cv$temp$1$var55.length)?Math.log(cv$temp$1$var55[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample$var32][0])) {
							for(int server = 0; server < noServers; server += 1)
								cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][1], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
							boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global[threadID$cv$sample$var32];
							for(int server = 0; server < noServers; server += 1)
								guard$sample61gaussian163[sample$var32][server][1] = false;
							for(int server = 0; server < noServers; server += 1) {
								if(!guard$sample61gaussian163[sample$var32][server][1]) {
									guard$sample61gaussian163[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$19$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$19$var154))) - (Math.log(cv$temp$19$var154) * 0.5));
										double cv$temp$82$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$83$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$82$var152) / Math.sqrt(cv$temp$83$var154))) - (Math.log(cv$temp$83$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$82$var152) / Math.sqrt(cv$temp$83$var154))) - ((Math.log(cv$temp$83$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$82$var152) / Math.sqrt(cv$temp$83$var154))) - (Math.log(cv$temp$83$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$83$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$82$var152) / Math.sqrt(cv$temp$83$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$82$var152) / Math.sqrt(cv$temp$83$var154)))) - (Math.log(cv$temp$83$var154) * 0.5));
										}
										double cv$temp$146$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$147$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$146$var152) / Math.sqrt(cv$temp$147$var154))) - (Math.log(cv$temp$147$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$146$var152) / Math.sqrt(cv$temp$147$var154))) - ((Math.log(cv$temp$147$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$146$var152) / Math.sqrt(cv$temp$147$var154))) - (Math.log(cv$temp$147$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$147$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$146$var152) / Math.sqrt(cv$temp$147$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$146$var152) / Math.sqrt(cv$temp$147$var154)))) - (Math.log(cv$temp$147$var154) * 0.5));
										}
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
								if(!guard$sample61gaussian163[sample$var32][server][1]) {
									guard$sample61gaussian163[sample$var32][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if(metric_valid_g[sample$var32][server][1]) {
										double cv$temp$51$var154 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$51$var154))) - (Math.log(cv$temp$51$var154) * 0.5));
										double cv$temp$114$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$115$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$114$var152) / Math.sqrt(cv$temp$115$var154))) - (Math.log(cv$temp$115$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$114$var152) / Math.sqrt(cv$temp$115$var154))) - ((Math.log(cv$temp$115$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$114$var152) / Math.sqrt(cv$temp$115$var154))) - (Math.log(cv$temp$115$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$115$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$114$var152) / Math.sqrt(cv$temp$115$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$114$var152) / Math.sqrt(cv$temp$115$var154)))) - (Math.log(cv$temp$115$var154) * 0.5));
										}
										double cv$temp$178$var152 = current_metric_mean[server][cv$valuePos];
										double cv$temp$179$var154 = current_metric_var[server][cv$valuePos];
										if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$178$var152) / Math.sqrt(cv$temp$179$var154))) - (Math.log(cv$temp$179$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$178$var152) / Math.sqrt(cv$temp$179$var154))) - ((Math.log(cv$temp$179$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
										else {
											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
												cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$178$var152) / Math.sqrt(cv$temp$179$var154))) - (Math.log(cv$temp$179$var154) * 0.5));
											else
												cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$179$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$178$var152) / Math.sqrt(cv$temp$179$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][1] - cv$temp$178$var152) / Math.sqrt(cv$temp$179$var154)))) - (Math.log(cv$temp$179$var154) * 0.5));
										}
										cv$consumerDistributionProbabilityAccumulator = -2.0;
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
				for(int index$sample61$14 = 0; index$sample61$14 < noStates; index$sample61$14 += 1) {
					double cv$probabilitySample61Value15 = distribution$sample61[sample$var32][(index$timeStep$13 - 1)][index$sample61$14];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample61Value15);
					double[] cv$temp$3$var55 = m[index$sample61$14];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample61Value15) + ((cv$valuePos < cv$temp$3$var55.length)?Math.log(cv$temp$3$var55[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1)
						cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var32][server][timeStep$var49], current_metric_valid_bias[server][cv$valuePos]) + cv$accumulatedProbabilities);
					boolean[][][] guard$sample61gaussian163 = guard$sample61gaussian163$global[threadID$cv$sample$var32];
					for(int server = 0; server < noServers; server += 1)
						guard$sample61gaussian163[sample$var32][server][timeStep$var49] = false;
					for(int server = 0; server < noServers; server += 1) {
						if(!guard$sample61gaussian163[sample$var32][server][timeStep$var49]) {
							guard$sample61gaussian163[sample$var32][server][timeStep$var49] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if(metric_valid_g[sample$var32][server][timeStep$var49]) {
								double cv$temp$35$var154 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$35$var154))) - (Math.log(cv$temp$35$var154) * 0.5));
								double cv$temp$98$var152 = current_metric_mean[server][cv$valuePos];
								double cv$temp$99$var154 = current_metric_var[server][cv$valuePos];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$98$var152) / Math.sqrt(cv$temp$99$var154))) - (Math.log(cv$temp$99$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$98$var152) / Math.sqrt(cv$temp$99$var154))) - ((Math.log(cv$temp$99$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$98$var152) / Math.sqrt(cv$temp$99$var154))) - (Math.log(cv$temp$99$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$99$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$98$var152) / Math.sqrt(cv$temp$99$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$98$var152) / Math.sqrt(cv$temp$99$var154)))) - (Math.log(cv$temp$99$var154) * 0.5));
								}
								double cv$temp$162$var152 = current_metric_mean[server][cv$valuePos];
								double cv$temp$163$var154 = current_metric_var[server][cv$valuePos];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$162$var152) / Math.sqrt(cv$temp$163$var154))) - (Math.log(cv$temp$163$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$162$var152) / Math.sqrt(cv$temp$163$var154))) - ((Math.log(cv$temp$163$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$162$var152) / Math.sqrt(cv$temp$163$var154))) - (Math.log(cv$temp$163$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$163$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$162$var152) / Math.sqrt(cv$temp$163$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$162$var152) / Math.sqrt(cv$temp$163$var154)))) - (Math.log(cv$temp$163$var154) * 0.5));
								}
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						if(!guard$sample61gaussian163[sample$var32][server][timeStep$var49]) {
							guard$sample61gaussian163[sample$var32][server][timeStep$var49] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if(metric_valid_g[sample$var32][server][timeStep$var49]) {
								double cv$temp$67$var154 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$67$var154))) - (Math.log(cv$temp$67$var154) * 0.5));
								double cv$temp$130$var152 = current_metric_mean[server][cv$valuePos];
								double cv$temp$131$var154 = current_metric_var[server][cv$valuePos];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$130$var152) / Math.sqrt(cv$temp$131$var154))) - (Math.log(cv$temp$131$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$130$var152) / Math.sqrt(cv$temp$131$var154))) - ((Math.log(cv$temp$131$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$130$var152) / Math.sqrt(cv$temp$131$var154))) - (Math.log(cv$temp$131$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$131$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$130$var152) / Math.sqrt(cv$temp$131$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$130$var152) / Math.sqrt(cv$temp$131$var154)))) - (Math.log(cv$temp$131$var154) * 0.5));
								}
								double cv$temp$194$var152 = current_metric_mean[server][cv$valuePos];
								double cv$temp$195$var154 = current_metric_var[server][cv$valuePos];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$194$var152) / Math.sqrt(cv$temp$195$var154))) - (Math.log(cv$temp$195$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$194$var152) / Math.sqrt(cv$temp$195$var154))) - ((Math.log(cv$temp$195$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$194$var152) / Math.sqrt(cv$temp$195$var154))) - (Math.log(cv$temp$195$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$195$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$194$var152) / Math.sqrt(cv$temp$195$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var32][server][timeStep$var49] - cv$temp$194$var152) / Math.sqrt(cv$temp$195$var154)))) - (Math.log(cv$temp$195$var154) * 0.5));
								}
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
			int index$timeStep$312_3 = (timeStep$var49 + 1);
			if((index$timeStep$312_3 < length$metric[sample$var32][0])) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var56[threadID$cv$sample$var32];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == timeStep$var49)) {
					if(fixedFlag$sample48) {
						int index$var26$323_1 = st[sample$var32][0];
						if(((0 <= index$var26$323_1) && (index$var26$323_1 < noStates)))
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						for(int index$sample48$319 = 0; index$sample48$319 < noStates; index$sample48$319 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample48[sample$var32][index$sample48$319]);
					}
				}
				int index$timeStep$327 = (timeStep$var49 - 1);
				if((((1 <= index$timeStep$327) && !(index$timeStep$327 == timeStep$var49)) && !(index$timeStep$327 == index$timeStep$312_3))) {
					for(int index$sample61$328 = 0; index$sample61$328 < noStates; index$sample61$328 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample61[sample$var32][(index$timeStep$327 - 1)][index$sample61$328]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample61[sample$var32][(index$timeStep$312_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample61[sample$var32][(timeStep$var49 - 1)];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample92(int var82, int var86, int threadID$cv$var86, Rng RNG$) {
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
					if(fixedFlag$sample48) {
						if((var86 == st[sample$var120][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var100 = st[sample$var120][0];
							if(((0 <= var100) && (var100 < noStates))) {
								double cv$temp$3$var154 = current_metric_var[var82][st[sample$var120][0]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var154))) - (Math.log(cv$temp$3$var154) * 0.5));
								double cv$temp$31$var154 = current_metric_var[var82][st[sample$var120][0]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$31$var154))) - (Math.log(cv$temp$31$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$31$var154))) - ((Math.log(cv$temp$31$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$31$var154))) - (Math.log(cv$temp$31$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$31$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$31$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$31$var154)))) - (Math.log(cv$temp$31$var154) * 0.5));
								}
								double cv$temp$59$var154 = current_metric_var[var82][st[sample$var120][0]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$59$var154))) - (Math.log(cv$temp$59$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$59$var154))) - ((Math.log(cv$temp$59$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$59$var154))) - (Math.log(cv$temp$59$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$59$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$59$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(cv$temp$59$var154)))) - (Math.log(cv$temp$59$var154) * 0.5));
								}
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var86];
						double cv$accumulatedConsumerProbabilities;
						{
							double var154 = current_metric_var[var82][var86];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						}
						{
							double var154 = current_metric_var[var82][var86];
							if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							}
						}
						double var154 = current_metric_var[var82][var86];
						if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							else
								cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						}
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
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
						if(fixedFlag$sample61) {
							if((var86 == st[sample$var120][timeStep$var140])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var100 = st[sample$var120][timeStep$var140];
								if(((0 <= var100) && (var100 < noStates))) {
									double cv$temp$21$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$21$var154))) - (Math.log(cv$temp$21$var154) * 0.5));
									double cv$temp$49$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
									if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$49$var154))) - (Math.log(cv$temp$49$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$49$var154))) - ((Math.log(cv$temp$49$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$49$var154))) - (Math.log(cv$temp$49$var154) * 0.5));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$49$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$49$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$49$var154)))) - (Math.log(cv$temp$49$var154) * 0.5));
									}
									double cv$temp$77$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
									if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$77$var154))) - (Math.log(cv$temp$77$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$77$var154))) - ((Math.log(cv$temp$77$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
									else {
										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
											cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$77$var154))) - (Math.log(cv$temp$77$var154) * 0.5));
										else
											cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$77$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$77$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(cv$temp$77$var154)))) - (Math.log(cv$temp$77$var154) * 0.5));
									}
									cv$consumerDistributionProbabilityAccumulator = -2.0;
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
							double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var86];
							double cv$accumulatedConsumerProbabilities;
							{
								double var154 = current_metric_var[var82][var86];
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							}
							{
								double var154 = current_metric_var[var82][var86];
								if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
								}
							}
							double var154 = current_metric_var[var82][var86];
							if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$originalValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							}
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
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
				if(fixedFlag$sample48) {
					if((var86 == st[sample$var120][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var100 = st[sample$var120][0];
						if(((0 <= var100) && (var100 < noStates))) {
							double cv$temp$3$var154 = current_metric_var[var82][st[sample$var120][0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var154))) - (Math.log(cv$temp$3$var154) * 0.5));
							double cv$temp$31$var154 = current_metric_var[var82][st[sample$var120][0]];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$31$var154))) - (Math.log(cv$temp$31$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$31$var154))) - ((Math.log(cv$temp$31$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$31$var154))) - (Math.log(cv$temp$31$var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$31$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$31$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$31$var154)))) - (Math.log(cv$temp$31$var154) * 0.5));
							}
							double cv$temp$59$var154 = current_metric_var[var82][st[sample$var120][0]];
							if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$59$var154))) - (Math.log(cv$temp$59$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$59$var154))) - ((Math.log(cv$temp$59$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$59$var154))) - (Math.log(cv$temp$59$var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$59$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$59$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(cv$temp$59$var154)))) - (Math.log(cv$temp$59$var154) * 0.5));
							}
							cv$consumerDistributionProbabilityAccumulator = -2.0;
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
					double cv$probabilitySample48Value7 = distribution$sample48[sample$var120][var86];
					double cv$accumulatedConsumerProbabilities;
					{
						double var154 = current_metric_var[var82][var86];
						cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
					}
					{
						double var154 = current_metric_var[var82][var86];
						if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							else
								cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						}
					}
					double var154 = current_metric_var[var82][var86];
					if((((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
					else {
						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						else
							cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample48Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample48Value7)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][0] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
					}
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample48Value7 + cv$probabilitySample48Value7) + cv$probabilitySample48Value7)), 0.0);
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
					if(fixedFlag$sample61) {
						if((var86 == st[sample$var120][timeStep$var140])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var100 = st[sample$var120][timeStep$var140];
							if(((0 <= var100) && (var100 < noStates))) {
								double cv$temp$21$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$21$var154))) - (Math.log(cv$temp$21$var154) * 0.5));
								double cv$temp$49$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$49$var154))) - (Math.log(cv$temp$49$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$49$var154))) - ((Math.log(cv$temp$49$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$49$var154))) - (Math.log(cv$temp$49$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$49$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$49$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$49$var154)))) - (Math.log(cv$temp$49$var154) * 0.5));
								}
								double cv$temp$77$var154 = current_metric_var[var82][st[sample$var120][timeStep$var140]];
								if(((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$77$var154))) - (Math.log(cv$temp$77$var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$77$var154))) - ((Math.log(cv$temp$77$var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
								else {
									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$77$var154))) - (Math.log(cv$temp$77$var154) * 0.5));
									else
										cv$accumulatedConsumerProbabilities = ((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(cv$temp$77$var154) * 0.5)) - DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$77$var154))))) + 1)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(cv$temp$77$var154)))) - (Math.log(cv$temp$77$var154) * 0.5));
								}
								cv$consumerDistributionProbabilityAccumulator = -2.0;
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
						double cv$probabilitySample61Value19 = distribution$sample61[sample$var120][(timeStep$var140 - 1)][var86];
						double cv$accumulatedConsumerProbabilities;
						{
							double var154 = current_metric_var[var82][var86];
							cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						}
						{
							double var154 = current_metric_var[var82][var86];
							if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
							else {
								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
									cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
								else
									cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							}
						}
						double var154 = current_metric_var[var82][var86];
						if((((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5)) < cv$accumulatedConsumerProbabilities))
							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - ((Math.log(var154) * 0.5) + cv$accumulatedConsumerProbabilities))) + 1)) + cv$accumulatedConsumerProbabilities);
						else {
							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
								cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
							else
								cv$accumulatedConsumerProbabilities = (((Math.log((Math.exp(((cv$accumulatedConsumerProbabilities + (Math.log(var154) * 0.5)) - (Math.log(cv$probabilitySample61Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))))) + 1)) + Math.log(cv$probabilitySample61Value19)) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var120][var82][timeStep$var140] - cv$proposedValue) / Math.sqrt(var154)))) - (Math.log(var154) * 0.5));
						}
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - ((cv$probabilitySample61Value19 + cv$probabilitySample61Value19) + cv$probabilitySample61Value19)), 0.0);
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
		{
			int cv$max = 0;
			if((0 < noStates))
				cv$max = noStates;
			int cv$threadCount = threadCount();
			cv$var27$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var27$countGlobal[cv$index] = new double[cv$max];
		}
		{
			int cv$threadCount = threadCount();
			cv$distributionAccumulator$var56 = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$distributionAccumulator$var56[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$var44$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var44$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		{
			int cv$max_server = 0;
			int cv$max_timeStep$var140 = 0;
			for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, length$metric[sample$var120][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			int cv$max_sample$var120 = length$metric.length;
			int cv$threadCount = threadCount();
			guard$sample48gaussian163$global = new boolean[cv$threadCount][][][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample48gaussian163$global[cv$index] = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
		}
		{
			int cv$threadCount = threadCount();
			cv$var57$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var57$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		int cv$max_server = 0;
		int cv$max_timeStep$var140 = 0;
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var140 = Math.max(cv$max_timeStep$var140, length$metric[sample$var120][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		int cv$max_sample$var120 = length$metric.length;
		int cv$threadCount = threadCount();
		guard$sample61gaussian163$global = new boolean[cv$threadCount][][][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample61gaussian163$global[cv$index] = new boolean[cv$max_sample$var120][cv$max_server][cv$max_timeStep$var140];
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
		distribution$sample61 = new double[length$metric.length][][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var32][0] - 1)][];
			distribution$sample61[sample$var32] = subarray$0;
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				subarray$0[(timeStep$var49 - 1)] = new double[noStates];
		}
		distribution$sample48 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			distribution$sample48[sample$var32] = new double[noStates];
		logProbability$var43 = new double[length$metric.length];
		logProbability$sample48 = new double[length$metric.length];
		logProbability$var56 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			logProbability$var56[sample$var32] = new double[(length$metric[sample$var32][0] - 1)];
		logProbability$sample61 = new double[length$metric.length][];
		for(int sample$var32 = 0; sample$var32 < length$metric.length; sample$var32 += 1)
			logProbability$sample61[sample$var32] = new double[(length$metric[sample$var32][0] - 1)];
		logProbability$var145 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var145[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		logProbability$sample152 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample152[sample$var120] = subarray$0;
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
		logProbability$sample164 = new double[length$metric.length][][];
		for(int sample$var120 = 0; sample$var120 < length$metric.length; sample$var120 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample164[sample$var120] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var120][0]];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var26]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var32, int forEnd$sample$var32, int threadID$sample$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var32 = forStart$sample$var32; sample$var32 < forEnd$sample$var32; sample$var32 += 1) {
						if(!fixedFlag$sample48)
							st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						if(!fixedFlag$sample61) {
							int[] var50 = st[sample$var32];
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
								var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var32][(timeStep$var49 - 1)]]);
						}
					}
			}
		);
		if(!fixedFlag$sample92)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							double[] var83 = current_metric_mean[var82];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var86, int forEnd$var86, int threadID$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var86 = forStart$var86; var86 < forEnd$var86; var86 += 1)
											var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample106)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1) {
							double[] var97 = current_metric_var[var96];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var100, int forEnd$var100, int threadID$var100, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var100 = forStart$var100; var100 < forEnd$var100; var100 += 1)
											var97[var100] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample120)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var110, int forEnd$var110, int threadID$var110, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var110 = forStart$var110; var110 < forEnd$var110; var110 += 1) {
							double[] var111 = current_metric_valid_bias[var110];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var114, int forEnd$var114, int threadID$var114, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var114 = forStart$var114; var114 < forEnd$var114; var114 += 1)
											var111[var114] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample$var120, int forEnd$index$sample$var120, int threadID$index$sample$var120, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample$var120 = forStart$index$sample$var120; index$sample$var120 < forEnd$index$sample$var120; index$sample$var120 += 1) {
						int sample$var120 = index$sample$var120;
						parallelFor(RNG$1, 0, noServers, 1,
							(int forStart$index$server, int forEnd$index$server, int threadID$index$server, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int index$server = forStart$index$server; index$server < forEnd$index$server; index$server += 1) {
										int server = index$server;
										double[] metric_inner = metric_g[sample$var120][server];
										parallelFor(RNG$2, 0, length$metric[sample$var120][0], 1,
											(int forStart$timeStep$var140, int forEnd$timeStep$var140, int threadID$timeStep$var140, org.sandwood.random.internal.Rng RNG$3) -> { 
												for(int timeStep$var140 = forStart$timeStep$var140; timeStep$var140 < forEnd$timeStep$var140; timeStep$var140 += 1) {
														if(!fixedFlag$sample152)
															metric_valid_g[sample$var120][server][timeStep$var140] = DistributionSampling.sampleBernoulli(RNG$3, current_metric_valid_bias[server][st[sample$var120][timeStep$var140]]);
														if((metric_valid_g[sample$var120][server][timeStep$var140] && !fixedFlag$sample164))
															metric_inner[timeStep$var140] = ((Math.sqrt(current_metric_var[server][st[sample$var120][timeStep$var140]]) * DistributionSampling.sampleGaussian(RNG$3)) + current_metric_mean[server][st[sample$var120][timeStep$var140]]);
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
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var26]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var32, int forEnd$sample$var32, int threadID$sample$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var32 = forStart$sample$var32; sample$var32 < forEnd$sample$var32; sample$var32 += 1) {
						if(!fixedFlag$sample48) {
							double[] cv$distribution$sample48 = distribution$sample48[sample$var32];
							for(int index$var43 = 0; index$var43 < noStates; index$var43 += 1)
								cv$distribution$sample48[index$var43] = ((index$var43 < initialStateDistribution.length)?initialStateDistribution[index$var43]:0.0);
						}
						if(!fixedFlag$sample61) {
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1) {
								double[] cv$distribution$sample61 = distribution$sample61[sample$var32][(timeStep$var49 - 1)];
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									cv$distribution$sample61[index$var56] = 0.0;
								if((1 == timeStep$var49)) {
									if(fixedFlag$sample48) {
										int var26 = st[sample$var32][0];
										if(((0 <= var26) && (var26 < noStates))) {
											double[] var55 = m[st[sample$var32][0]];
											for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
												cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + ((index$var56 < var55.length)?var55[index$var56]:0.0));
										}
									} else {
										for(int index$sample48$3 = 0; index$sample48$3 < noStates; index$sample48$3 += 1) {
											double cv$probabilitySample48Value4 = distribution$sample48[sample$var32][index$sample48$3];
											double[] var55 = m[index$sample48$3];
											for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
												cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (cv$probabilitySample48Value4 * ((index$var56 < var55.length)?var55[index$var56]:0.0)));
										}
									}
								}
								int index$timeStep$11 = (timeStep$var49 - 1);
								if((1 <= index$timeStep$11)) {
									for(int index$sample61$12 = 0; index$sample61$12 < noStates; index$sample61$12 += 1) {
										double cv$probabilitySample61Value13 = distribution$sample61[sample$var32][(index$timeStep$11 - 1)][index$sample61$12];
										double[] var55 = m[index$sample61$12];
										for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
											cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] + (cv$probabilitySample61Value13 * ((index$var56 < var55.length)?var55[index$var56]:0.0)));
									}
								}
								double cv$var56$sum = 0.0;
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									cv$var56$sum = (cv$var56$sum + cv$distribution$sample61[index$var56]);
								for(int index$var56 = 0; index$var56 < noStates; index$var56 += 1)
									cv$distribution$sample61[index$var56] = (cv$distribution$sample61[index$var56] / cv$var56$sum);
							}
						}
					}
			}
		);
		if(!fixedFlag$sample92)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							double[] var83 = current_metric_mean[var82];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var86, int forEnd$var86, int threadID$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var86 = forStart$var86; var86 < forEnd$var86; var86 += 1)
											var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample106)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1) {
							double[] var97 = current_metric_var[var96];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var100, int forEnd$var100, int threadID$var100, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var100 = forStart$var100; var100 < forEnd$var100; var100 += 1)
											var97[var100] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample120)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var110, int forEnd$var110, int threadID$var110, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var110 = forStart$var110; var110 < forEnd$var110; var110 += 1) {
							double[] var111 = current_metric_valid_bias[var110];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var114, int forEnd$var114, int threadID$var114, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var114 = forStart$var114; var114 < forEnd$var114; var114 += 1)
											var111[var114] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample24)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var26]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var32, int forEnd$sample$var32, int threadID$sample$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var32 = forStart$sample$var32; sample$var32 < forEnd$sample$var32; sample$var32 += 1) {
						if(!fixedFlag$sample48)
							st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						if(!fixedFlag$sample61) {
							int[] var50 = st[sample$var32];
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
								var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var32][(timeStep$var49 - 1)]]);
						}
					}
			}
		);
		if(!fixedFlag$sample92)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							double[] var83 = current_metric_mean[var82];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var86, int forEnd$var86, int threadID$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var86 = forStart$var86; var86 < forEnd$var86; var86 += 1)
											var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample106)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1) {
							double[] var97 = current_metric_var[var96];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var100, int forEnd$var100, int threadID$var100, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var100 = forStart$var100; var100 < forEnd$var100; var100 += 1)
											var97[var100] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample120)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var110, int forEnd$var110, int threadID$var110, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var110 = forStart$var110; var110 < forEnd$var110; var110 += 1) {
							double[] var111 = current_metric_valid_bias[var110];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var114, int forEnd$var114, int threadID$var114, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var114 = forStart$var114; var114 < forEnd$var114; var114 += 1)
											var111[var114] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample24)
				sample24();
			if(!fixedFlag$sample30)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample30(var26, threadID$var26, RNG$1);
					}
				);

			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var32, int forEnd$sample$var32, int threadID$sample$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample$var32 = forStart$sample$var32; sample$var32 < forEnd$sample$var32; sample$var32 += 1) {
							if(!fixedFlag$sample48)
								sample48(sample$var32, threadID$sample$var32, RNG$1);
							if(!fixedFlag$sample61) {
								for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
									sample61(sample$var32, timeStep$var49, threadID$sample$var32, RNG$1);
							}
						}
				}
			);
			if(!fixedFlag$sample92)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var82, int forEnd$index$var82, int threadID$index$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var82 = forStart$index$var82; index$var82 < forEnd$index$var82; index$var82 += 1) {
								int var82 = index$var82;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var86, int forEnd$var86, int threadID$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var86 = forStart$var86; var86 < forEnd$var86; var86 += 1)
												sample92(var82, var86, threadID$var86, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample106)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var96, int forEnd$index$var96, int threadID$index$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var96 = forStart$index$var96; index$var96 < forEnd$index$var96; index$var96 += 1) {
								int var96 = index$var96;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var100, int forEnd$var100, int threadID$var100, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var100 = forStart$var100; var100 < forEnd$var100; var100 += 1)
												sample106(var96, var100, threadID$var100, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample120)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var110, int forEnd$index$var110, int threadID$index$var110, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var110 = forStart$index$var110; index$var110 < forEnd$index$var110; index$var110 += 1) {
								int var110 = index$var110;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var114, int forEnd$var114, int threadID$var114, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var114 = forStart$var114; var114 < forEnd$var114; var114 += 1)
												sample120(var110, var114, threadID$var114, RNG$2);
									}
								);
							}
					}
				);

		} else {
			if(!fixedFlag$sample120)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var110, int forEnd$index$var110, int threadID$index$var110, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var110 = forStart$index$var110; index$var110 < forEnd$index$var110; index$var110 += 1) {
								int var110 = index$var110;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var114, int forEnd$var114, int threadID$var114, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var114 = forStart$var114; var114 < forEnd$var114; var114 += 1)
												sample120(var110, var114, threadID$var114, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample106)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var96, int forEnd$index$var96, int threadID$index$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var96 = forStart$index$var96; index$var96 < forEnd$index$var96; index$var96 += 1) {
								int var96 = index$var96;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var100, int forEnd$var100, int threadID$var100, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var100 = forStart$var100; var100 < forEnd$var100; var100 += 1)
												sample106(var96, var100, threadID$var100, RNG$2);
									}
								);
							}
					}
				);

			if(!fixedFlag$sample92)
				parallelFor(RNG$, 0, noServers, 1,
					(int forStart$index$var82, int forEnd$index$var82, int threadID$index$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int index$var82 = forStart$index$var82; index$var82 < forEnd$index$var82; index$var82 += 1) {
								int var82 = index$var82;
								parallelFor(RNG$1, 0, noStates, 1,
									(int forStart$var86, int forEnd$var86, int threadID$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
										for(int var86 = forStart$var86; var86 < forEnd$var86; var86 += 1)
												sample92(var82, var86, threadID$var86, RNG$2);
									}
								);
							}
					}
				);

			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample$var32, int forEnd$sample$var32, int threadID$sample$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample$var32 = forStart$sample$var32; sample$var32 < forEnd$sample$var32; sample$var32 += 1) {
							if(!fixedFlag$sample61) {
								for(int timeStep$var49 = (length$metric[sample$var32][0] - 1); timeStep$var49 >= 1; timeStep$var49 -= 1)
									sample61(sample$var32, timeStep$var49, threadID$sample$var32, RNG$1);
							}
							if(!fixedFlag$sample48)
								sample48(sample$var32, threadID$sample$var32, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample30)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
								sample30(var26, threadID$var26, RNG$1);
					}
				);

			if(!fixedFlag$sample24)
				sample24();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
						v[var17] = 0.1;
			}
		);
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
		if(!fixedProbFlag$sample48) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1)
				logProbability$sample48[sample$var32] = 0.0;
		}
		for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
			for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
				logProbability$var56[sample$var32][(timeStep$var49 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample61) {
			for(int sample$var32 = 0; sample$var32 < noSamples; sample$var32 += 1) {
				for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
					logProbability$sample61[sample$var32][(timeStep$var49 - 1)] = 0.0;
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
					logProbability$var145[sample$var120][server][timeStep$var140] = 0.0;
			}
		}
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample152) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample152[sample$var120][server][timeStep$var140] = 0.0;
				}
			}
		}
		for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
					logProbability$var155[sample$var120][server][timeStep$var140] = 0.0;
			}
		}
		logProbability$var131 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample164) {
			for(int sample$var120 = 0; sample$var120 < noSamples; sample$var120 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var140 = 0; timeStep$var140 < length$metric[sample$var120][0]; timeStep$var140 += 1)
						logProbability$sample164[sample$var120][server][timeStep$var140] = 0.0;
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
		if(!fixedFlag$sample30)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var26, int forEnd$var26, int threadID$var26, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var26 = forStart$var26; var26 < forEnd$var26; var26 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var26]);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample$var32, int forEnd$sample$var32, int threadID$sample$var32, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample$var32 = forStart$sample$var32; sample$var32 < forEnd$sample$var32; sample$var32 += 1) {
						if(!fixedFlag$sample48)
							st[sample$var32][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution);
						if(!fixedFlag$sample61) {
							int[] var50 = st[sample$var32];
							for(int timeStep$var49 = 1; timeStep$var49 < length$metric[sample$var32][0]; timeStep$var49 += 1)
								var50[timeStep$var49] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample$var32][(timeStep$var49 - 1)]]);
						}
					}
			}
		);
		if(!fixedFlag$sample92)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1) {
							double[] var83 = current_metric_mean[var82];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var86, int forEnd$var86, int threadID$var86, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var86 = forStart$var86; var86 < forEnd$var86; var86 += 1)
											var83[var86] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$2));
								}
							);
						}
				}
			);

		if(!fixedFlag$sample106)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var96, int forEnd$var96, int threadID$var96, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var96 = forStart$var96; var96 < forEnd$var96; var96 += 1) {
							double[] var97 = current_metric_var[var96];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var100, int forEnd$var100, int threadID$var100, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var100 = forStart$var100; var100 < forEnd$var100; var100 += 1)
											var97[var100] = DistributionSampling.sampleInverseGamma(RNG$2, 1.0, 1.0);
								}
							);
						}
				}
			);

		if(!fixedFlag$sample120)
			parallelFor(RNG$, 0, noServers, 1,
				(int forStart$var110, int forEnd$var110, int threadID$var110, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var110 = forStart$var110; var110 < forEnd$var110; var110 += 1) {
							double[] var111 = current_metric_valid_bias[var110];
							parallelFor(RNG$1, 0, noStates, 1,
								(int forStart$var114, int forEnd$var114, int threadID$var114, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int var114 = forStart$var114; var114 < forEnd$var114; var114 += 1)
											var111[var114] = DistributionSampling.sampleBeta(RNG$2, 1.0, 1.0);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics4(\n               double[][][] metric,\n               boolean[][][] metric_valid, \n               int max_metric,\n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n    \n    //Calculate all the state transitions\n    int[][] st = new int[noSamples][];\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n    }\n    \n    // Calculate the number of servers\n    int noServers = metric[0].length;    \n    \n    // Allocate space for each generated metric.    \n    double[][][] metric_g = new double[noSamples][noServers][];\n    boolean[][][] metric_valid_g = new boolean[noSamples][noServers][];\n\n    // Calculate metric parameters\n    double[][] current_metric_mean = uniform(0.0, (double) max_metric).sample(noServers, noStates);\n    double[][] current_metric_var = inverseGamma(1.0, 1.0).sample(noServers, noStates);\n    double[][] current_metric_valid_bias = beta(1.0, 1.0).sample(noServers, noStates);\n    \n    // Compute the values of each metric\n    for(int sample = 0; sample < noSamples; sample++) {\n        int streamLength = metric[sample][0].length;\n        for(int server = 0; server < noServers; server++) {\n            //Allocate space for the time series\n            double[] metric_inner = new double[streamLength];\n            metric_g[sample][server] = metric_inner;\n            \n            boolean[] metric_valid_inner = new boolean[streamLength];\n            metric_valid_g[sample][server] = metric_valid_inner;\n            \n            //Generate values.\n            for(int timeStep = 0; timeStep < streamLength; timeStep++){\n                int currentState = st[sample][timeStep];\n                \n                metric_valid_inner[timeStep] = bernoulli(current_metric_valid_bias[server][currentState]).sample();\n                if(metric_valid_inner[timeStep])\n                    metric_inner[timeStep] = gaussian(current_metric_mean[server][currentState], current_metric_var[server][currentState]).sample();\n            }\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n    metric_g.observe(metric);\n}";
	}
}