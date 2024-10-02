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
		fixedProbFlag$sample150 = (cv$value && fixedProbFlag$sample150);
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample178() {
		return fixedFlag$sample178;
	}

	@Override
	public final void set$fixedFlag$sample178(boolean cv$value) {
		fixedFlag$sample178 = cv$value;
		fixedProbFlag$sample178 = (cv$value && fixedProbFlag$sample178);
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample206() {
		return fixedFlag$sample206;
	}

	@Override
	public final void set$fixedFlag$sample206(boolean cv$value) {
		fixedFlag$sample206 = cv$value;
		fixedProbFlag$sample206 = (cv$value && fixedProbFlag$sample206);
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
	}

	@Override
	public final boolean get$fixedFlag$sample260() {
		return fixedFlag$sample260;
	}

	@Override
	public final void set$fixedFlag$sample260(boolean cv$value) {
		fixedFlag$sample260 = cv$value;
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
	}

	@Override
	public final boolean get$fixedFlag$sample275() {
		return fixedFlag$sample275;
	}

	@Override
	public final void set$fixedFlag$sample275(boolean cv$value) {
		fixedFlag$sample275 = cv$value;
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (cv$value && fixedProbFlag$sample31);
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample44() {
		return fixedFlag$sample44;
	}

	@Override
	public final void set$fixedFlag$sample44(boolean cv$value) {
		fixedFlag$sample44 = cv$value;
		fixedProbFlag$sample44 = (cv$value && fixedProbFlag$sample44);
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (cv$value && fixedProbFlag$sample71);
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
	}

	@Override
	public final boolean get$fixedFlag$sample90() {
		return fixedFlag$sample90;
	}

	@Override
	public final void set$fixedFlag$sample90(boolean cv$value) {
		fixedFlag$sample90 = cv$value;
		fixedProbFlag$sample90 = (cv$value && fixedProbFlag$sample90);
		fixedProbFlag$sample260 = (cv$value && fixedProbFlag$sample260);
		fixedProbFlag$sample275 = (cv$value && fixedProbFlag$sample275);
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						boolean cv$sampleValue = metric_valid_g[sample$var207][server][timeStep$var239];
						if((0 == timeStep$var239)) {
							if(fixedFlag$sample71) {
								int var194 = st[sample$var207][0];
								if(((0 <= var194) && (var194 < noStates))) {
									cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var207][0]]);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
									double cv$probabilitySample71Value5 = distribution$sample71[sample$var207][index$sample71$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample71Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample71$4]));
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
						if((1 <= timeStep$var239)) {
							if(fixedFlag$sample90) {
								int var194 = st[sample$var207][timeStep$var239];
								if(((0 <= var194) && (var194 < noStates))) {
									double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
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
								for(int index$sample90$13 = 0; index$sample90$13 < noStates; index$sample90$13 += 1) {
									double cv$probabilitySample90Value14 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][index$sample90$13];
									double cv$weightedProbability = (Math.log(cv$probabilitySample90Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, current_metric_valid_bias[server][index$sample90$13]));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						logProbability$sample260[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample260[sample$var207][server][timeStep$var239];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$sampleValue;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
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
							double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							double cv$probabilityReached = 0.0;
							double cv$sampleValue = metric_g[sample$var207][server][timeStep$var239];
							if(((0 == timeStep$var239) && metric_valid_g[sample$var207][server][0])) {
								if(fixedFlag$sample71) {
									if((0 <= st[sample$var207][0])) {
										int var140 = st[sample$var207][0];
										if(((0 <= var140) && (var140 < noStates))) {
											double var256 = current_metric_var[server][st[sample$var207][0]];
											cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var207][0]]) / Math.sqrt(var256))) - (Math.log(var256) * 0.5));
											cv$probabilityReached = 1.0;
										}
									}
								} else {
									for(int index$sample71$4 = 0; index$sample71$4 < noStates; index$sample71$4 += 1) {
										double cv$probabilitySample71Value5 = distribution$sample71[sample$var207][index$sample71$4];
										double var256 = current_metric_var[server][index$sample71$4];
										double cv$weightedProbability = ((Math.log(cv$probabilitySample71Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample71$4]) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
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
							if((1 <= timeStep$var239)) {
								if(fixedFlag$sample90) {
									if((0 <= st[sample$var207][timeStep$var239])) {
										int var140 = st[sample$var207][timeStep$var239];
										if(((0 <= var140) && (var140 < noStates))) {
											double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
											double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][st[sample$var207][timeStep$var239]]) / Math.sqrt(var256))) - (Math.log(var256) * 0.5));
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
									for(int index$sample90$49 = 0; index$sample90$49 < noStates; index$sample90$49 += 1) {
										double cv$probabilitySample90Value50 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][index$sample90$49];
										double var256 = current_metric_var[server][index$sample90$49];
										double cv$weightedProbability = ((Math.log(cv$probabilitySample90Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - current_metric_mean[server][index$sample90$49]) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
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
							if((cv$probabilityReached == 0.0))
								cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
							else
								cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
							logProbability$sample275[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
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
							double cv$rvAccumulator = logProbability$sample275[sample$var207][server][timeStep$var239];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var53][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var64[sample$var53] = cv$distributionAccumulator;
					logProbability$sample71[sample$var53] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample71 = fixedFlag$sample31;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				double cv$rvAccumulator = logProbability$sample71[sample$var53];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var64[sample$var53] = cv$rvAccumulator;
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample$var53][timeStep$var76];
						if((1 == timeStep$var76)) {
							if(fixedFlag$sample71) {
								int var40 = st[sample$var53][0];
								if(((0 <= var40) && (var40 < noStates))) {
									double[] var82 = m[st[sample$var53][0]];
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample71$6 = 0; index$sample71$6 < noStates; index$sample71$6 += 1) {
									double cv$probabilitySample71Value7 = distribution$sample71[sample$var53][index$sample71$6];
									double[] var82 = m[index$sample71$6];
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
						if((2 <= timeStep$var76)) {
							int var40 = st[sample$var53][(timeStep$var76 - 1)];
							if(((0 <= var40) && (var40 < noStates))) {
								double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
						logProbability$sample90[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample90 = (fixedFlag$sample44 && fixedFlag$sample71);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					double cv$rvAccumulator = logProbability$sample90[sample$var53][(timeStep$var76 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$rvAccumulator;
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
			double cv$sampleAccumulator = 0.0;
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				for(int var140 = 0; var140 < noStates; var140 += 1) {
					double cv$sampleValue = current_metric_mean[var130][var140];
					cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY));
				}
			}
			logProbability$var119 = cv$sampleAccumulator;
			logProbability$var141 = cv$sampleAccumulator;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample150)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample150 = fixedFlag$sample150;
		} else {
			logProbability$var119 = logProbability$var141;
			logProbability$current_metric_mean = (logProbability$current_metric_mean + logProbability$var141);
			logProbability$$model = (logProbability$$model + logProbability$var141);
			if(fixedFlag$sample150)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var141);
		}
	}

	private final void logProbabilityValue$sample178() {
		if(!fixedProbFlag$sample178) {
			double cv$sampleAccumulator = 0.0;
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				for(int var167 = 0; var167 < noStates; var167 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(current_metric_var[var157][var167], 1.0, 1.0));
			}
			logProbability$var146 = cv$sampleAccumulator;
			logProbability$var168 = cv$sampleAccumulator;
			logProbability$current_metric_var = (logProbability$current_metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample178)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample178 = fixedFlag$sample178;
		} else {
			logProbability$var146 = logProbability$var168;
			logProbability$current_metric_var = (logProbability$current_metric_var + logProbability$var168);
			logProbability$$model = (logProbability$$model + logProbability$var168);
			if(fixedFlag$sample178)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var168);
		}
	}

	private final void logProbabilityValue$sample206() {
		if(!fixedProbFlag$sample206) {
			double cv$sampleAccumulator = 0.0;
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				for(int var194 = 0; var194 < noStates; var194 += 1)
					cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(current_metric_valid_bias[var184][var194], 1.0, 1.0));
			}
			logProbability$var173 = cv$sampleAccumulator;
			logProbability$var195 = cv$sampleAccumulator;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample206)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample206 = fixedFlag$sample206;
		} else {
			logProbability$var173 = logProbability$var195;
			logProbability$current_metric_valid_bias = (logProbability$current_metric_valid_bias + logProbability$var195);
			logProbability$$model = (logProbability$$model + logProbability$var195);
			if(fixedFlag$sample206)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var195);
		}
	}

	private final void logProbabilityValue$sample260() {
		if(!fixedProbFlag$sample260) {
			double cv$accumulator = 0.0;
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
						double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var207][server][timeStep$var239], current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						logProbability$sample260[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample260[sample$var207][server][timeStep$var239];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var244[sample$var207][server][timeStep$var239] = cv$sampleValue;
						logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
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
							double var256 = current_metric_var[server][st[sample$var207][timeStep$var239]];
							double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][server][timeStep$var239] - current_metric_mean[server][st[sample$var207][timeStep$var239]]) / Math.sqrt(var256))) - (Math.log(var256) * 0.5));
							cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
							logProbability$sample275[sample$var207][server][timeStep$var239] = cv$distributionAccumulator;
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
							double cv$rvAccumulator = logProbability$sample275[sample$var207][server][timeStep$var239];
							cv$accumulator = (cv$accumulator + cv$rvAccumulator);
							logProbability$var257[sample$var207][server][timeStep$var239] = cv$rvAccumulator;
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			logProbability$var27 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			logProbability$var27 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample44() {
		if(!fixedProbFlag$sample44) {
			double cv$sampleAccumulator = 0.0;
			for(int var40 = 0; var40 < noStates; var40 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var40], v));
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$var41 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample44 = fixedFlag$sample44;
		} else {
			logProbability$var29 = logProbability$var41;
			logProbability$m = (logProbability$m + logProbability$var41);
			logProbability$$model = (logProbability$$model + logProbability$var41);
			if(fixedFlag$sample44)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var41);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				int cv$sampleValue = st[sample$var53][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var64[sample$var53] = cv$distributionAccumulator;
				logProbability$sample71[sample$var53] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				double cv$rvAccumulator = logProbability$sample71[sample$var53];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var64[sample$var53] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample$var53][timeStep$var76];
					double[] var82 = m[st[sample$var53][(timeStep$var76 - 1)]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var82.length))?Math.log(var82[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
					logProbability$sample90[sample$var53][(timeStep$var76 - 1)] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample90[sample$var53][(timeStep$var76 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var83[sample$var53][(timeStep$var76 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample90)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample150(int var130, int var140) {
		double cv$originalValue = current_metric_mean[var130][var140];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				if((metric_valid_g[sample$var207][var130][0] && (0 < length$metric[sample$var207][0]))) {
					if(fixedFlag$sample71) {
						if((var140 == st[sample$var207][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var167 = st[sample$var207][0];
							if(((0 <= var167) && (var167 < noStates))) {
								double cv$temp$3$var256 = current_metric_var[var130][st[sample$var207][0]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var256))) - (Math.log(cv$temp$3$var256) * 0.5));
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
						double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var140];
						double var256 = current_metric_var[var130][var140];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$originalValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
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
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					if(metric_valid_g[sample$var207][var130][timeStep$var239]) {
						if(fixedFlag$sample90) {
							if((var140 == st[sample$var207][timeStep$var239])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var167 = st[sample$var207][timeStep$var239];
								if(((0 <= var167) && (var167 < noStates))) {
									double cv$temp$21$var256 = current_metric_var[var130][st[sample$var207][timeStep$var239]];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$originalValue) / Math.sqrt(cv$temp$21$var256))) - (Math.log(cv$temp$21$var256) * 0.5));
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
							double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var140];
							double var256 = current_metric_var[var130][var140];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$originalValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
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
		current_metric_mean[var130][var140] = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= (double)max_metric))?(-Math.log(max_metric)):Double.NEGATIVE_INFINITY);
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			if((metric_valid_g[sample$var207][var130][0] && (0 < length$metric[sample$var207][0]))) {
				if(fixedFlag$sample71) {
					if((var140 == st[sample$var207][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var167 = st[sample$var207][0];
						if(((0 <= var167) && (var167 < noStates))) {
							double cv$temp$3$var256 = current_metric_var[var130][st[sample$var207][0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var256))) - (Math.log(cv$temp$3$var256) * 0.5));
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
					double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var140];
					double var256 = current_metric_var[var130][var140];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][0] - cv$proposedValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
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
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
				if(metric_valid_g[sample$var207][var130][timeStep$var239]) {
					if(fixedFlag$sample90) {
						if((var140 == st[sample$var207][timeStep$var239])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var167 = st[sample$var207][timeStep$var239];
							if(((0 <= var167) && (var167 < noStates))) {
								double cv$temp$21$var256 = current_metric_var[var130][st[sample$var207][timeStep$var239]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$proposedValue) / Math.sqrt(cv$temp$21$var256))) - (Math.log(cv$temp$21$var256) * 0.5));
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
						double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var140];
						double var256 = current_metric_var[var130][var140];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var130][timeStep$var239] - cv$proposedValue) / Math.sqrt(var256)))) - (Math.log(var256) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
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
			current_metric_mean[var130][var140] = cv$originalValue;
	}

	private final void sample178(int var157, int var167) {
		double cv$originalValue = current_metric_var[var157][var167];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				if((metric_valid_g[sample$var207][var157][0] && (0 < length$metric[sample$var207][0]))) {
					if(fixedFlag$sample71) {
						if((var167 == st[sample$var207][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var140 = st[sample$var207][0];
							if(((0 <= var140) && (var140 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][st[sample$var207][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
						double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var167];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][var167]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
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
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
					if(metric_valid_g[sample$var207][var157][timeStep$var239]) {
						if(fixedFlag$sample90) {
							if((var167 == st[sample$var207][timeStep$var239])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var140 = st[sample$var207][timeStep$var239];
								if(((0 <= var140) && (var140 < noStates))) {
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][st[sample$var207][timeStep$var239]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
							double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var167];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][var167]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
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
		current_metric_var[var157][var167] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			if((metric_valid_g[sample$var207][var157][0] && (0 < length$metric[sample$var207][0]))) {
				if(fixedFlag$sample71) {
					if((var167 == st[sample$var207][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var140 = st[sample$var207][0];
						if(((0 <= var140) && (var140 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][st[sample$var207][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
					double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var167];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][0] - current_metric_mean[var157][var167]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample71Value7), 0.0);
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
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
				if(metric_valid_g[sample$var207][var157][timeStep$var239]) {
					if(fixedFlag$sample90) {
						if((var167 == st[sample$var207][timeStep$var239])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var140 = st[sample$var207][timeStep$var239];
							if(((0 <= var140) && (var140 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][st[sample$var207][timeStep$var239]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
						double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var167];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample90Value19) + DistributionSampling.logProbabilityGaussian(((metric_g[sample$var207][var157][timeStep$var239] - current_metric_mean[var157][var167]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample90Value19), 0.0);
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
			current_metric_var[var157][var167] = cv$originalValue;
	}

	private final void sample206(int var184, int var194) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			if((0 < length$metric[sample$var207][0])) {
				if(fixedFlag$sample71) {
					if((var194 == st[sample$var207][0])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var207][var184][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample71Value7 = distribution$sample71[sample$var207][var194];
					cv$count = (cv$count + cv$probabilitySample71Value7);
					if(metric_valid_g[sample$var207][var184][0])
						cv$sum = (cv$sum + cv$probabilitySample71Value7);
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int timeStep$var239 = 1; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1) {
				if(fixedFlag$sample90) {
					if((var194 == st[sample$var207][timeStep$var239])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample$var207][var184][timeStep$var239])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample90Value19 = distribution$sample90[sample$var207][(timeStep$var239 - 1)][var194];
					cv$count = (cv$count + cv$probabilitySample90Value19);
					if(metric_valid_g[sample$var207][var184][timeStep$var239])
						cv$sum = (cv$sum + cv$probabilitySample90Value19);
				}
			}
		}
		current_metric_valid_bias[var184][var194] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample260(int sample$var207, int server, int timeStep$var239) {}

	private final void sample31() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var28$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample71) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
				cv$var28$countGlobal[st[sample$var53][0]] = (cv$var28$countGlobal[st[sample$var53][0]] + 1.0);
		} else {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var28$countGlobal[cv$loopIndex] = (cv$var28$countGlobal[cv$loopIndex] + distribution$sample71[sample$var53][cv$loopIndex]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var28$countGlobal, initialStateDistribution);
	}

	private final void sample44(int var40) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var41$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample90) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				if((1 < length$metric[sample$var53][0])) {
					if(fixedFlag$sample71) {
						if((var40 == st[sample$var53][0]))
							cv$var41$countGlobal[st[sample$var53][1]] = (cv$var41$countGlobal[st[sample$var53][1]] + 1.0);
					} else
						cv$var41$countGlobal[st[sample$var53][1]] = (cv$var41$countGlobal[st[sample$var53][1]] + distribution$sample71[sample$var53][var40]);
				}
			}
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 2; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					if((var40 == st[sample$var53][(timeStep$var76 - 1)]))
						cv$var41$countGlobal[st[sample$var53][timeStep$var76]] = (cv$var41$countGlobal[st[sample$var53][timeStep$var76]] + 1.0);
				}
			}
		} else {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				if((1 < length$metric[sample$var53][0])) {
					if(fixedFlag$sample71) {
						if((var40 == st[sample$var53][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var41$countGlobal[cv$loopIndex] = (cv$var41$countGlobal[cv$loopIndex] + distribution$sample90[sample$var53][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample71[sample$var53][var40];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var41$countGlobal[cv$loopIndex] = (cv$var41$countGlobal[cv$loopIndex] + (distribution$sample90[sample$var53][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					int index$timeStep$52 = (timeStep$var76 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample90[sample$var53][(index$timeStep$52 - 1)][var40];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var41$countGlobal[cv$loopIndex] = (cv$var41$countGlobal[cv$loopIndex] + (distribution$sample90[sample$var53][(timeStep$var76 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var41$countGlobal, m[var40]);
	}

	private final void sample71(int sample$var53) {
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample90 && (1 < length$metric[sample$var53][0]))) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] cv$temp$1$var82 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample$var53][1]) && (st[sample$var53][1] < cv$temp$1$var82.length))?Math.log(cv$temp$1$var82[st[sample$var53][1]]):Double.NEGATIVE_INFINITY);
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
			if((0 < length$metric[sample$var53][0])) {
				for(int server = 0; server < noServers; server += 1) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][0], current_metric_valid_bias[server][cv$valuePos]);
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
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var53][server][0])
						guard$sample71gaussian274$global[sample$var53][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if(metric_valid_g[sample$var53][server][0])
						guard$sample71gaussian274$global[sample$var53][server][0] = false;
				}
				for(int server = 0; server < noServers; server += 1) {
					if((metric_valid_g[sample$var53][server][0] && !guard$sample71gaussian274$global[sample$var53][server][0])) {
						guard$sample71gaussian274$global[sample$var53][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$4$var256 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$4$var256))) - (Math.log(cv$temp$4$var256) * 0.5));
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
					if((metric_valid_g[sample$var53][server][0] && !guard$sample71gaussian274$global[sample$var53][server][0])) {
						guard$sample71gaussian274$global[sample$var53][server][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$12$var256 = current_metric_var[server][cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][0] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$12$var256))) - (Math.log(cv$temp$12$var256) * 0.5));
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
			if((!fixedFlag$sample90 && (1 < length$metric[sample$var53][0]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var83[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var83, 1.0, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample90[sample$var53][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var83[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var65$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample71[sample$var53];
		double cv$logSum;
		double cv$lseMax = cv$var65$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var65$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var65$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var65$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var65$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample90(int sample$var53, int timeStep$var76) {
		int cv$noStates = 0;
		if((1 == timeStep$var76)) {
			if(fixedFlag$sample71) {
				int var40 = st[sample$var53][0];
				if(((0 <= var40) && (var40 < noStates)))
					cv$noStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample90) {
			if((2 <= timeStep$var76)) {
				int var40 = st[sample$var53][(timeStep$var76 - 1)];
				if(((0 <= var40) && (var40 < noStates)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var76 - 1);
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var76)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var76)) {
				if(fixedFlag$sample71) {
					int var40 = st[sample$var53][0];
					if(((0 <= var40) && (var40 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var82 = m[st[sample$var53][0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var82.length)?Math.log(cv$temp$0$var82[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample$var53][0])) {
							for(int server = 0; server < noServers; server += 1) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][1], current_metric_valid_bias[server][cv$valuePos]);
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
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var53][server][1])
									guard$sample90gaussian274$global[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var53][server][1])
									guard$sample90gaussian274$global[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274$global[sample$var53][server][1])) {
									guard$sample90gaussian274$global[sample$var53][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$11$var256 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$11$var256))) - (Math.log(cv$temp$11$var256) * 0.5));
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
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274$global[sample$var53][server][1])) {
									guard$sample90gaussian274$global[sample$var53][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$43$var256 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$43$var256))) - (Math.log(cv$temp$43$var256) * 0.5));
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
					for(int index$sample71$26 = 0; index$sample71$26 < noStates; index$sample71$26 += 1) {
						double cv$probabilitySample71Value27 = distribution$sample71[sample$var53][index$sample71$26];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value27);
						double[] cv$temp$1$var82 = m[index$sample71$26];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value27) + ((cv$valuePos < cv$temp$1$var82.length)?Math.log(cv$temp$1$var82[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample$var53][0])) {
							for(int server = 0; server < noServers; server += 1) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][1], current_metric_valid_bias[server][cv$valuePos]);
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
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var53][server][1])
									guard$sample90gaussian274$global[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if(metric_valid_g[sample$var53][server][1])
									guard$sample90gaussian274$global[sample$var53][server][1] = false;
							}
							for(int server = 0; server < noServers; server += 1) {
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274$global[sample$var53][server][1])) {
									guard$sample90gaussian274$global[sample$var53][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$19$var256 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$19$var256))) - (Math.log(cv$temp$19$var256) * 0.5));
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
								if((metric_valid_g[sample$var53][server][1] && !guard$sample90gaussian274$global[sample$var53][server][1])) {
									guard$sample90gaussian274$global[sample$var53][server][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$51$var256 = current_metric_var[server][cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][1] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$51$var256))) - (Math.log(cv$temp$51$var256) * 0.5));
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
			int index$timeStep$34 = (timeStep$var76 - 1);
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var76))) {
				for(int index$sample90$35 = 0; index$sample90$35 < noStates; index$sample90$35 += 1) {
					double cv$probabilitySample90Value36 = distribution$sample90[sample$var53][(index$timeStep$34 - 1)][index$sample90$35];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample90Value36);
					double[] cv$temp$3$var82 = m[index$sample90$35];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample90Value36) + ((cv$valuePos < cv$temp$3$var82.length)?Math.log(cv$temp$3$var82[cv$valuePos]):Double.NEGATIVE_INFINITY));
					for(int server = 0; server < noServers; server += 1) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample$var53][server][timeStep$var76], current_metric_valid_bias[server][cv$valuePos]);
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
					for(int server = 0; server < noServers; server += 1) {
						if(metric_valid_g[sample$var53][server][timeStep$var76])
							guard$sample90gaussian274$global[sample$var53][server][timeStep$var76] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						if(metric_valid_g[sample$var53][server][timeStep$var76])
							guard$sample90gaussian274$global[sample$var53][server][timeStep$var76] = false;
					}
					for(int server = 0; server < noServers; server += 1) {
						if((metric_valid_g[sample$var53][server][timeStep$var76] && !guard$sample90gaussian274$global[sample$var53][server][timeStep$var76])) {
							guard$sample90gaussian274$global[sample$var53][server][timeStep$var76] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$35$var256 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][timeStep$var76] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$35$var256))) - (Math.log(cv$temp$35$var256) * 0.5));
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
						if((metric_valid_g[sample$var53][server][timeStep$var76] && !guard$sample90gaussian274$global[sample$var53][server][timeStep$var76])) {
							guard$sample90gaussian274$global[sample$var53][server][timeStep$var76] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$67$var256 = current_metric_var[server][cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample$var53][server][timeStep$var76] - current_metric_mean[server][cv$valuePos]) / Math.sqrt(cv$temp$67$var256))) - (Math.log(cv$temp$67$var256) * 0.5));
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
			int index$timeStep$269_3 = (timeStep$var76 + 1);
			if((index$timeStep$269_3 < length$metric[sample$var53][0])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var83[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var76)) {
						if(fixedFlag$sample71) {
							int index$var40$280_1 = st[sample$var53][0];
							if(((0 <= index$var40$280_1) && (index$var40$280_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample71$276 = 0; index$sample71$276 < noStates; index$sample71$276 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample71[sample$var53][index$sample71$276]);
						}
					}
					int index$timeStep$284 = (timeStep$var76 - 1);
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var76)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						for(int index$sample90$285 = 0; index$sample90$285 < noStates; index$sample90$285 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample90[sample$var53][(index$timeStep$284 - 1)][index$sample90$285]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var83, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample90[sample$var53][(index$timeStep$269_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var83[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var84$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample90[sample$var53][(timeStep$var76 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var84$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var84$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var84$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var84$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var84$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	@Override
	public final void allocateScratch() {
		cv$var28$countGlobal = new double[Math.max(0, noStates)];
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		cv$var41$countGlobal = new double[cv$max];
		cv$distributionAccumulator$var83 = new double[noStates];
		cv$var65$stateProbabilityGlobal = new double[noStates];
		{
			int cv$max_server = 0;
			int cv$max_timeStep$var239 = 0;
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				if((0 < length$metric[0].length))
					cv$max_timeStep$var239 = Math.max(cv$max_timeStep$var239, length$metric[sample$var207][0]);
				cv$max_server = Math.max(cv$max_server, length$metric[0].length);
			}
			guard$sample71gaussian274$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var239];
		}
		cv$var84$stateProbabilityGlobal = new double[noStates];
		int cv$max_server = 0;
		int cv$max_timeStep$var239 = 0;
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			if((0 < length$metric[0].length))
				cv$max_timeStep$var239 = Math.max(cv$max_timeStep$var239, length$metric[sample$var207][0]);
			cv$max_server = Math.max(cv$max_server, length$metric[0].length);
		}
		guard$sample90gaussian274$global = new boolean[length$metric.length][cv$max_server][cv$max_timeStep$var239];
		cv$var245$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$initialStateDistribution)
			initialStateDistribution = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var40 = 0; var40 < noStates; var40 += 1)
				m[var40] = new double[noStates];
		}
		if(!setFlag$st) {
			st = new int[length$metric.length][];
			for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
				st[sample$var53] = new int[length$metric[sample$var53][0]];
		}
		if(!setFlag$metric_g) {
			metric_g = new double[length$metric.length][][];
			for(int var101 = 0; var101 < length$metric.length; var101 += 1)
				metric_g[var101] = new double[length$metric[0].length][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_g[sample$var207][server] = new double[length$metric[sample$var207][0]];
			}
		}
		if(!setFlag$metric_valid_g) {
			metric_valid_g = new boolean[length$metric.length][][];
			for(int var114 = 0; var114 < length$metric.length; var114 += 1)
				metric_valid_g[var114] = new boolean[length$metric[0].length][];
			for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
				for(int server = 0; server < length$metric[0].length; server += 1)
					metric_valid_g[sample$var207][server] = new boolean[length$metric[sample$var207][0]];
			}
		}
		if(!setFlag$current_metric_mean) {
			current_metric_mean = new double[length$metric[0].length][];
			for(int var130 = 0; var130 < length$metric[0].length; var130 += 1)
				current_metric_mean[var130] = new double[noStates];
		}
		if(!setFlag$current_metric_var) {
			current_metric_var = new double[length$metric[0].length][];
			for(int var157 = 0; var157 < length$metric[0].length; var157 += 1)
				current_metric_var[var157] = new double[noStates];
		}
		if(!setFlag$current_metric_valid_bias) {
			current_metric_valid_bias = new double[length$metric[0].length][];
			for(int var184 = 0; var184 < length$metric[0].length; var184 += 1)
				current_metric_valid_bias[var184] = new double[noStates];
		}
		distribution$sample71 = new double[length$metric.length][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
			distribution$sample71[sample$var53] = new double[noStates];
		distribution$sample90 = new double[length$metric.length][][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1) {
			double[][] subarray$0 = new double[(length$metric[sample$var53][0] - 1)][];
			distribution$sample90[sample$var53] = subarray$0;
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
				subarray$0[(timeStep$var76 - 1)] = new double[noStates];
		}
		logProbability$var64 = new double[length$metric.length];
		logProbability$sample71 = new double[length$metric.length];
		logProbability$var83 = new double[length$metric.length][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
			logProbability$var83[sample$var53] = new double[(length$metric[sample$var53][0] - 1)];
		logProbability$sample90 = new double[length$metric.length][];
		for(int sample$var53 = 0; sample$var53 < length$metric.length; sample$var53 += 1)
			logProbability$sample90[sample$var53] = new double[(length$metric[sample$var53][0] - 1)];
		logProbability$var244 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var244[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		logProbability$sample260 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample260[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		logProbability$var257 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$var257[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		logProbability$sample275 = new double[length$metric.length][][];
		for(int sample$var207 = 0; sample$var207 < length$metric.length; sample$var207 += 1) {
			double[][] subarray$0 = new double[length$metric[0].length][];
			logProbability$sample275[sample$var207] = subarray$0;
			for(int server = 0; server < length$metric[0].length; server += 1)
				subarray$0[server] = new double[length$metric[sample$var207][0]];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample44) {
			for(int var40 = 0; var40 < noStates; var40 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var40]);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			if(!fixedFlag$sample71)
				st[sample$var53][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample90) {
				int[] var77 = st[sample$var53];
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var53][(timeStep$var76 - 1)]]);
			}
		}
		if(!fixedFlag$sample150) {
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				double[] var131 = current_metric_mean[var130];
				for(int var140 = 0; var140 < noStates; var140 += 1)
					var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample178) {
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				double[] var158 = current_metric_var[var157];
				for(int var167 = 0; var167 < noStates; var167 += 1)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample206) {
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				double[] var185 = current_metric_valid_bias[var184];
				for(int var194 = 0; var194 < noStates; var194 += 1)
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
					if((var228[server][timeStep$var239] && (!fixedFlag$sample260 || !fixedFlag$sample275)))
						metric_inner[timeStep$var239] = ((Math.sqrt(current_metric_var[server][st[sample$var207][timeStep$var239]]) * DistributionSampling.sampleGaussian(RNG$)) + current_metric_mean[server][st[sample$var207][timeStep$var239]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample44) {
			for(int var40 = 0; var40 < noStates; var40 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var40]);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			if(!fixedFlag$sample71) {
				double[] cv$distribution$sample71 = distribution$sample71[sample$var53];
				for(int index$var64 = 0; index$var64 < noStates; index$var64 += 1)
					cv$distribution$sample71[index$var64] = ((index$var64 < initialStateDistribution.length)?initialStateDistribution[index$var64]:0.0);
			}
			if(!fixedFlag$sample90) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1) {
					double[] cv$distribution$sample90 = distribution$sample90[sample$var53][(timeStep$var76 - 1)];
					for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
						cv$distribution$sample90[index$var83] = 0.0;
					if((1 == timeStep$var76)) {
						if(fixedFlag$sample71) {
							int var40 = st[sample$var53][0];
							if(((0 <= var40) && (var40 < noStates))) {
								double[] var82 = m[st[sample$var53][0]];
								for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
									cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + ((index$var83 < var82.length)?var82[index$var83]:0.0));
							}
						} else {
							for(int index$sample71$3 = 0; index$sample71$3 < noStates; index$sample71$3 += 1) {
								double cv$probabilitySample71Value4 = distribution$sample71[sample$var53][index$sample71$3];
								double[] var82 = m[index$sample71$3];
								for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
									cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (cv$probabilitySample71Value4 * ((index$var83 < var82.length)?var82[index$var83]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var76 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample90$12 = 0; index$sample90$12 < noStates; index$sample90$12 += 1) {
							double cv$probabilitySample90Value13 = distribution$sample90[sample$var53][(index$timeStep$11 - 1)][index$sample90$12];
							double[] var82 = m[index$sample90$12];
							for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
								cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] + (cv$probabilitySample90Value13 * ((index$var83 < var82.length)?var82[index$var83]:0.0)));
						}
					}
					double cv$var83$sum = 0.0;
					for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
						cv$var83$sum = (cv$var83$sum + cv$distribution$sample90[index$var83]);
					for(int index$var83 = 0; index$var83 < noStates; index$var83 += 1)
						cv$distribution$sample90[index$var83] = (cv$distribution$sample90[index$var83] / cv$var83$sum);
				}
			}
		}
		if(!fixedFlag$sample150) {
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				double[] var131 = current_metric_mean[var130];
				for(int var140 = 0; var140 < noStates; var140 += 1)
					var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample178) {
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				double[] var158 = current_metric_var[var157];
				for(int var167 = 0; var167 < noStates; var167 += 1)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample206) {
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				double[] var185 = current_metric_valid_bias[var184];
				for(int var194 = 0; var194 < noStates; var194 += 1)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample260) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						metric_valid_inner[timeStep$var239] = DistributionSampling.sampleBernoulli(RNG$, current_metric_valid_bias[server][st[sample$var207][timeStep$var239]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample44) {
			for(int var40 = 0; var40 < noStates; var40 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var40]);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			if(!fixedFlag$sample71)
				st[sample$var53][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample90) {
				int[] var77 = st[sample$var53];
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var53][(timeStep$var76 - 1)]]);
			}
		}
		if(!fixedFlag$sample150) {
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				double[] var131 = current_metric_mean[var130];
				for(int var140 = 0; var140 < noStates; var140 += 1)
					var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample178) {
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				double[] var158 = current_metric_var[var157];
				for(int var167 = 0; var167 < noStates; var167 += 1)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample206) {
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				double[] var185 = current_metric_valid_bias[var184];
				for(int var194 = 0; var194 < noStates; var194 += 1)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample260) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					boolean[] metric_valid_inner = metric_valid_g[sample$var207][server];
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
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
			if(!fixedFlag$sample44) {
				for(int var40 = 0; var40 < noStates; var40 += 1)
					sample44(var40);
			}
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				if(!fixedFlag$sample71)
					sample71(sample$var53);
				if(!fixedFlag$sample90) {
					for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
						sample90(sample$var53, timeStep$var76);
				}
			}
			if(!fixedFlag$sample150) {
				for(int var130 = 0; var130 < noServers; var130 += 1) {
					for(int var140 = 0; var140 < noStates; var140 += 1)
						sample150(var130, var140);
				}
			}
			if(!fixedFlag$sample178) {
				for(int var157 = 0; var157 < noServers; var157 += 1) {
					for(int var167 = 0; var167 < noStates; var167 += 1)
						sample178(var157, var167);
				}
			}
			if(!fixedFlag$sample206) {
				for(int var184 = 0; var184 < noServers; var184 += 1) {
					for(int var194 = 0; var194 < noStates; var194 += 1)
						sample206(var184, var194);
				}
			}
			if(!fixedFlag$sample260) {
				for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
					for(int server = 0; server < noServers; server += 1) {
						for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
							sample260(sample$var207, server, timeStep$var239);
					}
				}
			}
		} else {
			if(!fixedFlag$sample260) {
				for(int sample$var207 = (noSamples - 1); sample$var207 >= 0; sample$var207 -= 1) {
					for(int server = (noServers - 1); server >= 0; server -= 1) {
						for(int timeStep$var239 = (length$metric[sample$var207][0] - 1); timeStep$var239 >= 0; timeStep$var239 -= 1)
							sample260(sample$var207, server, timeStep$var239);
					}
				}
			}
			if(!fixedFlag$sample206) {
				for(int var184 = (noServers - 1); var184 >= 0; var184 -= 1) {
					for(int var194 = (noStates - 1); var194 >= 0; var194 -= 1)
						sample206(var184, var194);
				}
			}
			if(!fixedFlag$sample178) {
				for(int var157 = (noServers - 1); var157 >= 0; var157 -= 1) {
					for(int var167 = (noStates - 1); var167 >= 0; var167 -= 1)
						sample178(var157, var167);
				}
			}
			if(!fixedFlag$sample150) {
				for(int var130 = (noServers - 1); var130 >= 0; var130 -= 1) {
					for(int var140 = (noStates - 1); var140 >= 0; var140 -= 1)
						sample150(var130, var140);
				}
			}
			for(int sample$var53 = (noSamples - 1); sample$var53 >= 0; sample$var53 -= 1) {
				if(!fixedFlag$sample90) {
					for(int timeStep$var76 = (length$metric[sample$var53][0] - 1); timeStep$var76 >= 1; timeStep$var76 -= 1)
						sample90(sample$var53, timeStep$var76);
				}
				if(!fixedFlag$sample71)
					sample71(sample$var53);
			}
			if(!fixedFlag$sample44) {
				for(int var40 = (noStates - 1); var40 >= 0; var40 -= 1)
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
			logProbability$var64[sample$var53] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1)
				logProbability$sample71[sample$var53] = 0.0;
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
				logProbability$var83[sample$var53][(timeStep$var76 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample90) {
			for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					logProbability$sample90[sample$var53][(timeStep$var76 - 1)] = 0.0;
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
					logProbability$var244[sample$var207][server][timeStep$var239] = 0.0;
			}
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_inner = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample260) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						logProbability$sample260[sample$var207][server][timeStep$var239] = 0.0;
				}
			}
		}
		for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
			for(int server = 0; server < noServers; server += 1) {
				for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
					logProbability$var257[sample$var207][server][timeStep$var239] = 0.0;
			}
		}
		if(!fixedProbFlag$sample275) {
			for(int sample$var207 = 0; sample$var207 < noSamples; sample$var207 += 1) {
				for(int server = 0; server < noServers; server += 1) {
					for(int timeStep$var239 = 0; timeStep$var239 < length$metric[sample$var207][0]; timeStep$var239 += 1)
						logProbability$sample275[sample$var207][server][timeStep$var239] = 0.0;
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
		if(!fixedFlag$sample44) {
			for(int var40 = 0; var40 < noStates; var40 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var40]);
		}
		for(int sample$var53 = 0; sample$var53 < noSamples; sample$var53 += 1) {
			if(!fixedFlag$sample71)
				st[sample$var53][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample90) {
				int[] var77 = st[sample$var53];
				for(int timeStep$var76 = 1; timeStep$var76 < length$metric[sample$var53][0]; timeStep$var76 += 1)
					var77[timeStep$var76] = DistributionSampling.sampleCategorical(RNG$, m[st[sample$var53][(timeStep$var76 - 1)]]);
			}
		}
		if(!fixedFlag$sample150) {
			for(int var130 = 0; var130 < noServers; var130 += 1) {
				double[] var131 = current_metric_mean[var130];
				for(int var140 = 0; var140 < noStates; var140 += 1)
					var131[var140] = ((double)max_metric * DistributionSampling.sampleUniform(RNG$));
			}
		}
		if(!fixedFlag$sample178) {
			for(int var157 = 0; var157 < noServers; var157 += 1) {
				double[] var158 = current_metric_var[var157];
				for(int var167 = 0; var167 < noStates; var167 += 1)
					var158[var167] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
			}
		}
		if(!fixedFlag$sample206) {
			for(int var184 = 0; var184 < noServers; var184 += 1) {
				double[] var185 = current_metric_valid_bias[var184];
				for(int var194 = 0; var194 < noStates; var194 += 1)
					var185[var194] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
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