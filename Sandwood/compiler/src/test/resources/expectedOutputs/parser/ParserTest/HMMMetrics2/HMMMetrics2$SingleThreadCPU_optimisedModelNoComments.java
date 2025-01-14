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
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample113() {
		return fixedFlag$sample113;
	}

	@Override
	public final void set$fixedFlag$sample113(boolean cv$value) {
		fixedFlag$sample113 = cv$value;
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample23() {
		return fixedFlag$sample23;
	}

	@Override
	public final void set$fixedFlag$sample23(boolean cv$value) {
		fixedFlag$sample23 = cv$value;
		fixedProbFlag$sample23 = (cv$value && fixedProbFlag$sample23);
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (cv$value && fixedProbFlag$sample29);
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample60() {
		return fixedFlag$sample60;
	}

	@Override
	public final void set$fixedFlag$sample60(boolean cv$value) {
		fixedFlag$sample60 = cv$value;
		fixedProbFlag$sample60 = (cv$value && fixedProbFlag$sample60);
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		fixedFlag$sample75 = cv$value;
		fixedProbFlag$sample75 = (cv$value && fixedProbFlag$sample75);
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (cv$value && fixedProbFlag$sample88);
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
		fixedProbFlag$sample113 = (cv$value && fixedProbFlag$sample113);
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

	private final void logProbabilityDistribution$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					boolean cv$sampleValue = metric_valid_g[sample][timeStep$var91];
					if((0 == timeStep$var91)) {
						if(fixedFlag$sample75) {
							int var55 = st[sample][0];
							if(((0 <= var55) && (var55 < noStates))) {
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][0]]);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample75$4 = 0; index$sample75$4 < noStates; index$sample75$4 += 1) {
								double cv$probabilitySample75Value5 = distribution$sample75[sample][index$sample75$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample75Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample75$4]));
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
					if((1 <= timeStep$var91)) {
						if(fixedFlag$sample88) {
							int var55 = st[sample][timeStep$var91];
							if(((0 <= var55) && (var55 < noStates))) {
								double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][timeStep$var91]]);
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
							for(int index$sample88$13 = 0; index$sample88$13 < noStates; index$sample88$13 += 1) {
								double cv$probabilitySample88Value14 = distribution$sample88[sample][(timeStep$var91 - 1)][index$sample88$13];
								double cv$weightedProbability = (Math.log(cv$probabilitySample88Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample88$13]));
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
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var95[sample][timeStep$var91] = cv$distributionAccumulator;
					logProbability$sample104[sample][timeStep$var91] = cv$distributionAccumulator;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
					double cv$sampleValue = logProbability$sample104[sample][timeStep$var91];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var95[sample][timeStep$var91] = cv$sampleValue;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						double cv$sampleValue = metric_g[sample][timeStep$var91];
						if(((0 == timeStep$var91) && metric_valid_g[sample][0])) {
							if(fixedFlag$sample75) {
								if((0 <= st[sample][0])) {
									int var37 = st[sample][0];
									if(((0 <= var37) && (var37 < noStates))) {
										double var101 = metric_var[st[sample][0]];
										cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var101))) - (Math.log(var101) * 0.5));
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								for(int index$sample75$4 = 0; index$sample75$4 < noStates; index$sample75$4 += 1) {
									double cv$probabilitySample75Value5 = distribution$sample75[sample][index$sample75$4];
									double var101 = metric_var[index$sample75$4];
									double cv$weightedProbability = ((Math.log(cv$probabilitySample75Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample75$4]) / Math.sqrt(var101)))) - (Math.log(var101) * 0.5));
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
						if((1 <= timeStep$var91)) {
							if(fixedFlag$sample88) {
								if((0 <= st[sample][timeStep$var91])) {
									int var37 = st[sample][timeStep$var91];
									if(((0 <= var37) && (var37 < noStates))) {
										double var101 = metric_var[st[sample][timeStep$var91]];
										double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(var101))) - (Math.log(var101) * 0.5));
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
								for(int index$sample88$49 = 0; index$sample88$49 < noStates; index$sample88$49 += 1) {
									double cv$probabilitySample88Value50 = distribution$sample88[sample][(timeStep$var91 - 1)][index$sample88$49];
									double var101 = metric_var[index$sample88$49];
									double cv$weightedProbability = ((Math.log(cv$probabilitySample88Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample88$49]) / Math.sqrt(var101)))) - (Math.log(var101) * 0.5));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var102[sample][timeStep$var91] = cv$distributionAccumulator;
						logProbability$sample113[sample][timeStep$var91] = cv$distributionAccumulator;
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample113[sample][timeStep$var91];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var102[sample][timeStep$var91] = cv$sampleValue;
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
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
					int cv$sampleValue = st[sample][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var68[sample] = cv$distributionAccumulator;
					logProbability$sample75[sample] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample75 = fixedFlag$sample23;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = logProbability$sample75[sample];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[sample] = cv$rvAccumulator;
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
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample][timeStep$var74];
						if((1 == timeStep$var74)) {
							if(fixedFlag$sample75) {
								int var25 = st[sample][0];
								if(((0 <= var25) && (var25 < noStates))) {
									double[] var80 = m[st[sample][0]];
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample75$6 = 0; index$sample75$6 < noStates; index$sample75$6 += 1) {
									double cv$probabilitySample75Value7 = distribution$sample75[sample][index$sample75$6];
									double[] var80 = m[index$sample75$6];
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
						if((2 <= timeStep$var74)) {
							int var25 = st[sample][(timeStep$var74 - 1)];
							if(((0 <= var25) && (var25 < noStates))) {
								double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$var81[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
						logProbability$sample88[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample88 = (fixedFlag$sample29 && fixedFlag$sample75);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double cv$rvAccumulator = logProbability$sample88[sample][(timeStep$var74 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[sample][(timeStep$var74 - 1)] = cv$rvAccumulator;
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
					double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var91], metric_valid_bias[st[sample][timeStep$var91]]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var95[sample][timeStep$var91] = cv$distributionAccumulator;
					logProbability$sample104[sample][timeStep$var91] = cv$distributionAccumulator;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
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
					double cv$sampleValue = logProbability$sample104[sample][timeStep$var91];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var95[sample][timeStep$var91] = cv$sampleValue;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
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
						double var101 = metric_var[st[sample][timeStep$var91]];
						double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(var101))) - (Math.log(var101) * 0.5));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var102[sample][timeStep$var91] = cv$distributionAccumulator;
						logProbability$sample113[sample][timeStep$var91] = cv$distributionAccumulator;
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
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
						double cv$sampleValue = logProbability$sample113[sample][timeStep$var91];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var102[sample][timeStep$var91] = cv$sampleValue;
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
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
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			logProbability$var19 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample23 = fixedFlag$sample23;
		} else {
			logProbability$var19 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample23)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var25 = 0; var25 < noStates; var25 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var25], v));
			logProbability$var21 = cv$sampleAccumulator;
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			logProbability$var21 = logProbability$var26;
			logProbability$m = (logProbability$m + logProbability$var26);
			logProbability$$model = (logProbability$$model + logProbability$var26);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var26);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int var37 = 0; var37 < noStates; var37 += 1) {
				double cv$sampleValue = metric_mean[var37];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			logProbability$var33 = cv$sampleAccumulator;
			logProbability$var38 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			logProbability$var33 = logProbability$var38;
			logProbability$metric_mean = (logProbability$metric_mean + logProbability$var38);
			logProbability$$model = (logProbability$$model + logProbability$var38);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var38);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noStates; var46 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(metric_var[var46], 1.0, 1.0));
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			logProbability$var42 = logProbability$var47;
			logProbability$metric_var = (logProbability$metric_var + logProbability$var47);
			logProbability$$model = (logProbability$$model + logProbability$var47);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var47);
		}
	}

	private final void logProbabilityValue$sample60() {
		if(!fixedProbFlag$sample60) {
			double cv$sampleAccumulator = 0.0;
			for(int var55 = 0; var55 < noStates; var55 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(metric_valid_bias[var55], 1.0, 1.0));
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$var56 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample60)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample60 = fixedFlag$sample60;
		} else {
			logProbability$var51 = logProbability$var56;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + logProbability$var56);
			logProbability$$model = (logProbability$$model + logProbability$var56);
			if(fixedFlag$sample60)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				int cv$sampleValue = st[sample][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var68[sample] = cv$distributionAccumulator;
				logProbability$sample75[sample] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = logProbability$sample75[sample];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var68[sample] = cv$rvAccumulator;
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
					int cv$sampleValue = st[sample][timeStep$var74];
					double[] var80 = m[st[sample][(timeStep$var74 - 1)]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var81[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
					logProbability$sample88[sample][(timeStep$var74 - 1)] = cv$distributionAccumulator;
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
					double cv$rvAccumulator = logProbability$sample88[sample][(timeStep$var74 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var81[sample][(timeStep$var74 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample23() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var20$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample75) {
			for(int sample = 0; sample < noSamples; sample += 1)
				cv$var20$countGlobal[st[sample][0]] = (cv$var20$countGlobal[st[sample][0]] + 1.0);
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var20$countGlobal[cv$loopIndex] = (cv$var20$countGlobal[cv$loopIndex] + distribution$sample75[sample][cv$loopIndex]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var20$countGlobal, initialStateDistribution);
	}

	private final void sample29(int var25) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var26$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample88) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample75) {
						if((var25 == st[sample][0]))
							cv$var26$countGlobal[st[sample][1]] = (cv$var26$countGlobal[st[sample][1]] + 1.0);
					} else
						cv$var26$countGlobal[st[sample][1]] = (cv$var26$countGlobal[st[sample][1]] + distribution$sample75[sample][var25]);
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 2; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					if((var25 == st[sample][(timeStep$var74 - 1)]))
						cv$var26$countGlobal[st[sample][timeStep$var74]] = (cv$var26$countGlobal[st[sample][timeStep$var74]] + 1.0);
				}
			}
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample75) {
						if((var25 == st[sample][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var26$countGlobal[cv$loopIndex] = (cv$var26$countGlobal[cv$loopIndex] + distribution$sample88[sample][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample75[sample][var25];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var26$countGlobal[cv$loopIndex] = (cv$var26$countGlobal[cv$loopIndex] + (distribution$sample88[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					int index$timeStep$52 = (timeStep$var74 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample88[sample][(index$timeStep$52 - 1)][var25];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var26$countGlobal[cv$loopIndex] = (cv$var26$countGlobal[cv$loopIndex] + (distribution$sample88[sample][(timeStep$var74 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var26$countGlobal, m[var25]);
	}

	private final void sample42(int var37) {
		double cv$originalValue = metric_mean[var37];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample75) {
						if((var37 == st[sample][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var46 = st[sample][0];
							if(((0 <= var46) && (var46 < noStates))) {
								double cv$temp$3$var101 = metric_var[st[sample][0]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var101))) - (Math.log(cv$temp$3$var101) * 0.5));
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
						double cv$probabilitySample75Value6 = distribution$sample75[sample][var37];
						double cv$temp$9$var101 = metric_var[var37];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var101)))) - (Math.log(cv$temp$9$var101) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						if(fixedFlag$sample88) {
							if((var37 == st[sample][timeStep$var91])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var46 = st[sample][timeStep$var91];
								if(((0 <= var46) && (var46 < noStates))) {
									double cv$temp$21$var101 = metric_var[st[sample][timeStep$var91]];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$originalValue) / Math.sqrt(cv$temp$21$var101))) - (Math.log(cv$temp$21$var101) * 0.5));
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
							double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var37];
							double cv$temp$27$var101 = metric_var[var37];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$originalValue) / Math.sqrt(cv$temp$27$var101)))) - (Math.log(cv$temp$27$var101) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
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
		metric_mean[var37] = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample75) {
					if((var37 == st[sample][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var46 = st[sample][0];
						if(((0 <= var46) && (var46 < noStates))) {
							double cv$temp$3$var101 = metric_var[st[sample][0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var101))) - (Math.log(cv$temp$3$var101) * 0.5));
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
					double cv$probabilitySample75Value6 = distribution$sample75[sample][var37];
					double cv$temp$9$var101 = metric_var[var37];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var101)))) - (Math.log(cv$temp$9$var101) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
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
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(metric_valid_g[sample][timeStep$var91]) {
					if(fixedFlag$sample88) {
						if((var37 == st[sample][timeStep$var91])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var46 = st[sample][timeStep$var91];
							if(((0 <= var46) && (var46 < noStates))) {
								double cv$temp$21$var101 = metric_var[st[sample][timeStep$var91]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$proposedValue) / Math.sqrt(cv$temp$21$var101))) - (Math.log(cv$temp$21$var101) * 0.5));
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
						double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var37];
						double cv$temp$27$var101 = metric_var[var37];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - cv$proposedValue) / Math.sqrt(cv$temp$27$var101)))) - (Math.log(cv$temp$27$var101) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
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
			metric_mean[var37] = cv$originalValue;
	}

	private final void sample51(int var46) {
		double cv$originalValue = metric_var[var46];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample75) {
						if((var46 == st[sample][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var37 = st[sample][0];
							if(((0 <= var37) && (var37 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
						double cv$probabilitySample75Value6 = distribution$sample75[sample][var46];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var46]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
					if(metric_valid_g[sample][timeStep$var91]) {
						if(fixedFlag$sample88) {
							if((var46 == st[sample][timeStep$var91])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var37 = st[sample][timeStep$var91];
								if(((0 <= var37) && (var37 < noStates))) {
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
							double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var46];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[var46]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
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
		metric_var[var46] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample75) {
					if((var46 == st[sample][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var37 = st[sample][0];
						if(((0 <= var37) && (var37 < noStates))) {
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
					double cv$probabilitySample75Value6 = distribution$sample75[sample][var46];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample75Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var46]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample75Value6), 0.0);
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
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(metric_valid_g[sample][timeStep$var91]) {
					if(fixedFlag$sample88) {
						if((var46 == st[sample][timeStep$var91])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var37 = st[sample][timeStep$var91];
							if(((0 <= var37) && (var37 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[st[sample][timeStep$var91]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
						double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var46];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample88Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var91] - metric_mean[var46]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample88Value17), 0.0);
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
			metric_var[var46] = cv$originalValue;
	}

	private final void sample60(int var55) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((0 < length$metric[sample])) {
				if(fixedFlag$sample75) {
					if((var55 == st[sample][0])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample75Value6 = distribution$sample75[sample][var55];
					cv$count = (cv$count + cv$probabilitySample75Value6);
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample75Value6);
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 1; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(fixedFlag$sample88) {
					if((var55 == st[sample][timeStep$var91])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][timeStep$var91])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample88Value17 = distribution$sample88[sample][(timeStep$var91 - 1)][var55];
					cv$count = (cv$count + cv$probabilitySample88Value17);
					if(metric_valid_g[sample][timeStep$var91])
						cv$sum = (cv$sum + cv$probabilitySample88Value17);
				}
			}
		}
		metric_valid_bias[var55] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void sample75(int sample) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample88 && (1 < length$metric[sample]))) {
				double[] cv$temp$1$var80 = m[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= st[sample][1]) && (st[sample][1] < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[st[sample][1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < length$metric[sample])) {
				cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][0], metric_valid_bias[cv$valuePos]) + cv$accumulatedProbabilities);
				guard$sample75gaussian112$global[sample][0] = false;
				if(!guard$sample75gaussian112$global[sample][0]) {
					guard$sample75gaussian112$global[sample][0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(metric_valid_g[sample][0]) {
						double cv$temp$4$var101 = metric_var[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$4$var101))) - (Math.log(cv$temp$4$var101) * 0.5));
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
				if(!guard$sample75gaussian112$global[sample][0]) {
					guard$sample75gaussian112$global[sample][0] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if(metric_valid_g[sample][0]) {
						double cv$temp$12$var101 = metric_var[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$12$var101))) - (Math.log(cv$temp$12$var101) * 0.5));
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
			if((!fixedFlag$sample88 && (1 < length$metric[sample]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var81[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var81, 1.0, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample88[sample][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = cv$distributionAccumulator$var81[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			cv$var69$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample75[sample];
		double cv$logSum;
		double cv$lseMax = cv$var69$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var69$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var69$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var69$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var69$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var69$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var69$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var69$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var69$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample88(int sample, int timeStep$var74) {
		for(int cv$valuePos = 0; cv$valuePos < noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var74)) {
				if(fixedFlag$sample75) {
					int var25 = st[sample][0];
					if(((0 <= var25) && (var25 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var80 = m[st[sample][0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var80.length)?Math.log(cv$temp$0$var80[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample])) {
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]) + cv$accumulatedProbabilities);
							guard$sample88gaussian112$global[sample][1] = false;
							if(!guard$sample88gaussian112$global[sample][1]) {
								guard$sample88gaussian112$global[sample][1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if(metric_valid_g[sample][1]) {
									double cv$temp$11$var101 = metric_var[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$11$var101))) - (Math.log(cv$temp$11$var101) * 0.5));
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
							if(!guard$sample88gaussian112$global[sample][1]) {
								guard$sample88gaussian112$global[sample][1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if(metric_valid_g[sample][1]) {
									double cv$temp$43$var101 = metric_var[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$43$var101))) - (Math.log(cv$temp$43$var101) * 0.5));
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample75$5 = 0; index$sample75$5 < noStates; index$sample75$5 += 1) {
						double cv$probabilitySample75Value6 = distribution$sample75[sample][index$sample75$5];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value6);
						double[] cv$temp$1$var80 = m[index$sample75$5];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample75Value6) + ((cv$valuePos < cv$temp$1$var80.length)?Math.log(cv$temp$1$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample])) {
							cv$accumulatedProbabilities = (DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]) + cv$accumulatedProbabilities);
							guard$sample88gaussian112$global[sample][1] = false;
							if(!guard$sample88gaussian112$global[sample][1]) {
								guard$sample88gaussian112$global[sample][1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if(metric_valid_g[sample][1]) {
									double cv$temp$19$var101 = metric_var[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$19$var101))) - (Math.log(cv$temp$19$var101) * 0.5));
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
							if(!guard$sample88gaussian112$global[sample][1]) {
								guard$sample88gaussian112$global[sample][1] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if(metric_valid_g[sample][1]) {
									double cv$temp$51$var101 = metric_var[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$51$var101))) - (Math.log(cv$temp$51$var101) * 0.5));
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
			}
			int index$timeStep$13 = (timeStep$var74 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var74))) {
				for(int index$sample88$14 = 0; index$sample88$14 < noStates; index$sample88$14 += 1) {
					double cv$probabilitySample88Value15 = distribution$sample88[sample][(index$timeStep$13 - 1)][index$sample88$14];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample88Value15);
					double[] cv$temp$3$var80 = m[index$sample88$14];
					double cv$accumulatedProbabilities = ((DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var74], metric_valid_bias[cv$valuePos]) + Math.log(cv$probabilitySample88Value15)) + ((cv$valuePos < cv$temp$3$var80.length)?Math.log(cv$temp$3$var80[cv$valuePos]):Double.NEGATIVE_INFINITY));
					guard$sample88gaussian112$global[sample][timeStep$var74] = false;
					if(!guard$sample88gaussian112$global[sample][timeStep$var74]) {
						guard$sample88gaussian112$global[sample][timeStep$var74] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if(metric_valid_g[sample][timeStep$var74]) {
							double cv$temp$35$var101 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var74] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$35$var101))) - (Math.log(cv$temp$35$var101) * 0.5));
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
					if(!guard$sample88gaussian112$global[sample][timeStep$var74]) {
						guard$sample88gaussian112$global[sample][timeStep$var74] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if(metric_valid_g[sample][timeStep$var74]) {
							double cv$temp$67$var101 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var74] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$67$var101))) - (Math.log(cv$temp$67$var101) * 0.5));
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
			int index$timeStep$248_3 = (timeStep$var74 + 1);
			if((index$timeStep$248_3 < length$metric[sample])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var81[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == timeStep$var74)) {
					if(fixedFlag$sample75) {
						int index$var25$259_1 = st[sample][0];
						if(((0 <= index$var25$259_1) && (index$var25$259_1 < noStates)))
							scopeVariable$reachedSourceProbability = 1.0;
					} else {
						for(int index$sample75$255 = 0; index$sample75$255 < noStates; index$sample75$255 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample75[sample][index$sample75$255]);
					}
				}
				int index$timeStep$263 = (timeStep$var74 - 1);
				if((((1 <= index$timeStep$263) && !(index$timeStep$263 == timeStep$var74)) && !(index$timeStep$263 == index$timeStep$248_3))) {
					for(int index$sample88$264 = 0; index$sample88$264 < noStates; index$sample88$264 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample88[sample][(index$timeStep$263 - 1)][index$sample88$264]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var81, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				double[] cv$sampleDistribution = distribution$sample88[sample][(index$timeStep$248_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var81[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			cv$var82$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample88[sample][(timeStep$var74 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var82$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$var82$stateProbabilityGlobal.length; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var82$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$var82$stateProbabilityGlobal.length; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var82$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$var82$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$var82$stateProbabilityGlobal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$var82$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var82$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var20$countGlobal = new double[Math.max(0, noStates)];
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		cv$var26$countGlobal = new double[cv$max];
		cv$distributionAccumulator$var81 = new double[noStates];
		cv$var69$stateProbabilityGlobal = new double[noStates];
		{
			int cv$max_timeStep$var91 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, length$metric[sample]);
			guard$sample75gaussian112$global = new boolean[length$metric.length][cv$max_timeStep$var91];
		}
		cv$var82$stateProbabilityGlobal = new double[noStates];
		int cv$max_timeStep$var91 = 0;
		for(int sample = 0; sample < length$metric.length; sample += 1)
			cv$max_timeStep$var91 = Math.max(cv$max_timeStep$var91, length$metric[sample]);
		guard$sample88gaussian112$global = new boolean[length$metric.length][cv$max_timeStep$var91];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$initialStateDistribution)
			initialStateDistribution = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var25 = 0; var25 < noStates; var25 += 1)
				m[var25] = new double[noStates];
		}
		if(!setFlag$st) {
			st = new int[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				st[sample] = new int[length$metric[sample]];
		}
		if(!setFlag$metric_g) {
			metric_g = new double[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_g[sample] = new double[length$metric[sample]];
		}
		if(!setFlag$metric_valid_g) {
			metric_valid_g = new boolean[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				metric_valid_g[sample] = new boolean[length$metric[sample]];
		}
		if(!setFlag$metric_mean)
			metric_mean = new double[noStates];
		if(!setFlag$metric_var)
			metric_var = new double[noStates];
		if(!setFlag$metric_valid_bias)
			metric_valid_bias = new double[noStates];
		distribution$sample88 = new double[length$metric.length][][];
		for(int sample = 0; sample < length$metric.length; sample += 1) {
			double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
			distribution$sample88[sample] = subarray$0;
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
				subarray$0[(timeStep$var74 - 1)] = new double[noStates];
		}
		distribution$sample75 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			distribution$sample75[sample] = new double[noStates];
		logProbability$var68 = new double[length$metric.length];
		logProbability$sample75 = new double[length$metric.length];
		logProbability$var81 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var81[sample] = new double[(length$metric[sample] - 1)];
		logProbability$sample88 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample88[sample] = new double[(length$metric[sample] - 1)];
		logProbability$var95 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var95[sample] = new double[length$metric[sample]];
		logProbability$sample104 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample104[sample] = new double[length$metric[sample]];
		logProbability$var102 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var102[sample] = new double[length$metric[sample]];
		logProbability$sample113 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample113[sample] = new double[length$metric[sample]];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample29) {
			for(int var25 = 0; var25 < noStates; var25 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var25]);
		}
		if(!fixedFlag$sample42) {
			for(int var37 = 0; var37 < noStates; var37 += 1)
				metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample51) {
			for(int var46 = 0; var46 < noStates; var46 += 1)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample60) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample75)
				st[sample][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample88) {
				int[] var75 = st[sample];
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var74 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1) {
				if(!fixedFlag$sample104)
					metric_valid_1d[timeStep$var91] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var91]]);
				if((metric_valid_g[sample][timeStep$var91] && !fixedFlag$sample113))
					metric_1d[timeStep$var91] = ((Math.sqrt(metric_var[st[sample][timeStep$var91]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var91]]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample29) {
			for(int var25 = 0; var25 < noStates; var25 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var25]);
		}
		if(!fixedFlag$sample42) {
			for(int var37 = 0; var37 < noStates; var37 += 1)
				metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample51) {
			for(int var46 = 0; var46 < noStates; var46 += 1)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample60) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample75) {
				double[] cv$distribution$sample75 = distribution$sample75[sample];
				for(int index$var68 = 0; index$var68 < noStates; index$var68 += 1)
					cv$distribution$sample75[index$var68] = ((index$var68 < initialStateDistribution.length)?initialStateDistribution[index$var68]:0.0);
			}
			if(!fixedFlag$sample88) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1) {
					double[] cv$distribution$sample88 = distribution$sample88[sample][(timeStep$var74 - 1)];
					for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
						cv$distribution$sample88[index$var81] = 0.0;
					if((1 == timeStep$var74)) {
						if(fixedFlag$sample75) {
							int var25 = st[sample][0];
							if(((0 <= var25) && (var25 < noStates))) {
								double[] var80 = m[st[sample][0]];
								for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
									cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + ((index$var81 < var80.length)?var80[index$var81]:0.0));
							}
						} else {
							for(int index$sample75$3 = 0; index$sample75$3 < noStates; index$sample75$3 += 1) {
								double cv$probabilitySample75Value4 = distribution$sample75[sample][index$sample75$3];
								double[] var80 = m[index$sample75$3];
								for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
									cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (cv$probabilitySample75Value4 * ((index$var81 < var80.length)?var80[index$var81]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var74 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample88$12 = 0; index$sample88$12 < noStates; index$sample88$12 += 1) {
							double cv$probabilitySample88Value13 = distribution$sample88[sample][(index$timeStep$11 - 1)][index$sample88$12];
							double[] var80 = m[index$sample88$12];
							for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
								cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] + (cv$probabilitySample88Value13 * ((index$var81 < var80.length)?var80[index$var81]:0.0)));
						}
					}
					double cv$var81$sum = 0.0;
					for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
						cv$var81$sum = (cv$var81$sum + cv$distribution$sample88[index$var81]);
					for(int index$var81 = 0; index$var81 < noStates; index$var81 += 1)
						cv$distribution$sample88[index$var81] = (cv$distribution$sample88[index$var81] / cv$var81$sum);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample23)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample29) {
			for(int var25 = 0; var25 < noStates; var25 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var25]);
		}
		if(!fixedFlag$sample42) {
			for(int var37 = 0; var37 < noStates; var37 += 1)
				metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample51) {
			for(int var46 = 0; var46 < noStates; var46 += 1)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample60) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample75)
				st[sample][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample88) {
				int[] var75 = st[sample];
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var74 - 1)]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample23)
				sample23();
			if(!fixedFlag$sample29) {
				for(int var25 = 0; var25 < noStates; var25 += 1)
					sample29(var25);
			}
			if(!fixedFlag$sample42) {
				for(int var37 = 0; var37 < noStates; var37 += 1)
					sample42(var37);
			}
			if(!fixedFlag$sample51) {
				for(int var46 = 0; var46 < noStates; var46 += 1)
					sample51(var46);
			}
			if(!fixedFlag$sample60) {
				for(int var55 = 0; var55 < noStates; var55 += 1)
					sample60(var55);
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(!fixedFlag$sample75)
					sample75(sample);
				if(!fixedFlag$sample88) {
					for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
						sample88(sample, timeStep$var74);
				}
			}
		} else {
			for(int sample = (noSamples - 1); sample >= 0; sample -= 1) {
				if(!fixedFlag$sample88) {
					for(int timeStep$var74 = (length$metric[sample] - 1); timeStep$var74 >= 1; timeStep$var74 -= 1)
						sample88(sample, timeStep$var74);
				}
				if(!fixedFlag$sample75)
					sample75(sample);
			}
			if(!fixedFlag$sample60) {
				for(int var55 = (noStates - 1); var55 >= 0; var55 -= 1)
					sample60(var55);
			}
			if(!fixedFlag$sample51) {
				for(int var46 = (noStates - 1); var46 >= 0; var46 -= 1)
					sample51(var46);
			}
			if(!fixedFlag$sample42) {
				for(int var37 = (noStates - 1); var37 >= 0; var37 -= 1)
					sample42(var37);
			}
			if(!fixedFlag$sample29) {
				for(int var25 = (noStates - 1); var25 >= 0; var25 -= 1)
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
			logProbability$var68[sample] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample75[sample] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
				logProbability$var81[sample][(timeStep$var74 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample88) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					logProbability$sample88[sample][(timeStep$var74 - 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var95[sample][timeStep$var91] = 0.0;
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample104[sample][timeStep$var91] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
				logProbability$var102[sample][timeStep$var91] = 0.0;
		}
		logProbability$metric_1d = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample113) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var91 = 0; timeStep$var91 < length$metric[sample]; timeStep$var91 += 1)
					logProbability$sample113[sample][timeStep$var91] = 0.0;
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
		if(!fixedFlag$sample29) {
			for(int var25 = 0; var25 < noStates; var25 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var25]);
		}
		if(!fixedFlag$sample42) {
			for(int var37 = 0; var37 < noStates; var37 += 1)
				metric_mean[var37] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample51) {
			for(int var46 = 0; var46 < noStates; var46 += 1)
				metric_var[var46] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample60) {
			for(int var55 = 0; var55 < noStates; var55 += 1)
				metric_valid_bias[var55] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample75)
				st[sample][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample88) {
				int[] var75 = st[sample];
				for(int timeStep$var74 = 1; timeStep$var74 < length$metric[sample]; timeStep$var74 += 1)
					var75[timeStep$var74] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var74 - 1)]]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = metric_valid_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = metric_valid[cv$index1];
			boolean[] cv$target2 = metric_valid_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int sample = (noSamples - 1); sample >= 0; sample -= 1) {
			for(int timeStep$var91 = (length$metric[sample] - 1); timeStep$var91 >= 0; timeStep$var91 -= 1)
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