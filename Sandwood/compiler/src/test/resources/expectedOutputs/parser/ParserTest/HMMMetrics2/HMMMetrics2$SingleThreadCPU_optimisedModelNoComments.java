package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMMetrics2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMMetrics2$CoreInterface {
	private double[] cv$distributionAccumulator$var129;
	private double[] cv$var111$stateProbabilityGlobal;
	private double[] cv$var130$stateProbabilityGlobal;
	private double[] cv$var150$stateProbabilityGlobal;
	private double[] cv$var27$countGlobal;
	private double[] cv$var40$countGlobal;
	private double[][] distribution$sample117;
	private double[][][] distribution$sample136;
	private boolean fixedFlag$sample117 = false;
	private boolean fixedFlag$sample136 = false;
	private boolean fixedFlag$sample158 = false;
	private boolean fixedFlag$sample170 = false;
	private boolean fixedFlag$sample30 = false;
	private boolean fixedFlag$sample43 = false;
	private boolean fixedFlag$sample63 = false;
	private boolean fixedFlag$sample79 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample117 = false;
	private boolean fixedProbFlag$sample136 = false;
	private boolean fixedProbFlag$sample158 = false;
	private boolean fixedProbFlag$sample170 = false;
	private boolean fixedProbFlag$sample30 = false;
	private boolean fixedProbFlag$sample43 = false;
	private boolean fixedProbFlag$sample63 = false;
	private boolean fixedProbFlag$sample79 = false;
	private boolean fixedProbFlag$sample95 = false;
	private boolean[][] guard$sample117gaussian169$global;
	private boolean[][] guard$sample136gaussian169$global;
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
	private double[] logProbability$sample117;
	private double[][] logProbability$sample136;
	private double[][] logProbability$sample158;
	private double[][] logProbability$sample170;
	private double logProbability$st;
	private double[] logProbability$var110;
	private double[][] logProbability$var129;
	private double[][] logProbability$var149;
	private double[][] logProbability$var159;
	private double logProbability$var26;
	private double logProbability$var28;
	private double logProbability$var40;
	private double logProbability$var47;
	private double logProbability$var59;
	private double logProbability$var63;
	private double logProbability$var75;
	private double logProbability$var79;
	private double logProbability$var91;
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
	public final boolean get$fixedFlag$sample117() {
		return fixedFlag$sample117;
	}

	@Override
	public final void set$fixedFlag$sample117(boolean cv$value) {
		fixedFlag$sample117 = cv$value;
		fixedProbFlag$sample117 = (cv$value && fixedProbFlag$sample117);
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample136() {
		return fixedFlag$sample136;
	}

	@Override
	public final void set$fixedFlag$sample136(boolean cv$value) {
		fixedFlag$sample136 = cv$value;
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample158() {
		return fixedFlag$sample158;
	}

	@Override
	public final void set$fixedFlag$sample158(boolean cv$value) {
		fixedFlag$sample158 = cv$value;
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
	}

	@Override
	public final boolean get$fixedFlag$sample170() {
		return fixedFlag$sample170;
	}

	@Override
	public final void set$fixedFlag$sample170(boolean cv$value) {
		fixedFlag$sample170 = cv$value;
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (cv$value && fixedProbFlag$sample30);
		fixedProbFlag$sample117 = (cv$value && fixedProbFlag$sample117);
	}

	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		fixedFlag$sample43 = cv$value;
		fixedProbFlag$sample43 = (cv$value && fixedProbFlag$sample43);
		fixedProbFlag$sample136 = (cv$value && fixedProbFlag$sample136);
	}

	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		fixedFlag$sample63 = cv$value;
		fixedProbFlag$sample63 = (cv$value && fixedProbFlag$sample63);
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (cv$value && fixedProbFlag$sample79);
		fixedProbFlag$sample170 = (cv$value && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		fixedFlag$sample95 = cv$value;
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		fixedProbFlag$sample158 = (cv$value && fixedProbFlag$sample158);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		setFlag$initialStateDistribution = true;
		fixedProbFlag$sample30 = false;
		fixedProbFlag$sample117 = false;
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
		fixedProbFlag$sample43 = false;
		fixedProbFlag$sample136 = false;
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
		fixedProbFlag$sample63 = false;
		fixedProbFlag$sample170 = false;
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
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample158 = false;
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
		fixedProbFlag$sample79 = false;
		fixedProbFlag$sample170 = false;
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
		fixedProbFlag$sample117 = false;
		fixedProbFlag$sample136 = false;
		fixedProbFlag$sample158 = false;
		fixedProbFlag$sample170 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample117() {
		if(!fixedProbFlag$sample117) {
			if(fixedFlag$sample117) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					int cv$sampleValue = st[sample][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var110[sample] = cv$distributionAccumulator;
					logProbability$sample117[sample] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample117 = fixedFlag$sample30;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = logProbability$sample117[sample];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var110[sample] = cv$rvAccumulator;
			}
			if(fixedFlag$sample117)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample136() {
		if(!fixedProbFlag$sample136) {
			if(fixedFlag$sample136) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample][timeStep$var122];
						if((1 == timeStep$var122)) {
							if(fixedFlag$sample117) {
								int var39 = st[sample][0];
								if(((0 <= var39) && (var39 < noStates))) {
									double[] var128 = m[st[sample][0]];
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample117$6 = 0; index$sample117$6 < noStates; index$sample117$6 += 1) {
									double cv$probabilitySample117Value7 = distribution$sample117[sample][index$sample117$6];
									double[] var128 = m[index$sample117$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample117Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value7);
								}
							}
						}
						if((2 <= timeStep$var122)) {
							int var39 = st[sample][(timeStep$var122 - 1)];
							if(((0 <= var39) && (var39 < noStates))) {
								double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$var129[sample][(timeStep$var122 - 1)] = cv$distributionAccumulator;
						logProbability$sample136[sample][(timeStep$var122 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample136 = (fixedFlag$sample43 && fixedFlag$sample117);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					double cv$rvAccumulator = logProbability$sample136[sample][(timeStep$var122 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var129[sample][(timeStep$var122 - 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample136)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample158() {
		if(!fixedProbFlag$sample158) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					boolean cv$sampleValue = metric_valid_g[sample][timeStep$var145];
					if((0 == timeStep$var145)) {
						if(fixedFlag$sample117) {
							int var90 = st[sample][0];
							if(((0 <= var90) && (var90 < noStates))) {
								cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][0]]);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
								double cv$probabilitySample117Value5 = distribution$sample117[sample][index$sample117$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample117Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample117$4]));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value5);
							}
						}
					}
					if((1 <= timeStep$var145)) {
						if(fixedFlag$sample136) {
							int var90 = st[sample][timeStep$var145];
							if(((0 <= var90) && (var90 < noStates))) {
								double cv$weightedProbability = DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[st[sample][timeStep$var145]]);
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
							for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
								double cv$probabilitySample136Value14 = distribution$sample136[sample][(timeStep$var145 - 1)][index$sample136$13];
								double cv$weightedProbability = (Math.log(cv$probabilitySample136Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, metric_valid_bias[index$sample136$13]));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value14);
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var149[sample][timeStep$var145] = cv$distributionAccumulator;
					logProbability$sample158[sample][timeStep$var145] = cv$distributionAccumulator;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
					logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample158 = (((fixedFlag$sample158 && fixedFlag$sample95) && fixedFlag$sample117) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$sampleValue = logProbability$sample158[sample][timeStep$var145];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var149[sample][timeStep$var145] = cv$sampleValue;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
					logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample170() {
		if(!fixedProbFlag$sample170) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						double cv$sampleValue = metric_g[sample][timeStep$var145];
						if(((0 == timeStep$var145) && metric_valid_g[sample][0])) {
							if(fixedFlag$sample117) {
								if((0 <= st[sample][0])) {
									int var58 = st[sample][0];
									if(((0 <= var58) && (var58 < noStates))) {
										double var158 = metric_var[st[sample][0]];
										cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var158))) - (Math.log(var158) * 0.5));
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
									double cv$probabilitySample117Value5 = distribution$sample117[sample][index$sample117$4];
									double var158 = metric_var[index$sample117$4];
									double cv$weightedProbability = ((Math.log(cv$probabilitySample117Value5) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample117$4]) / Math.sqrt(var158)))) - (Math.log(var158) * 0.5));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value5);
								}
							}
						}
						if((1 <= timeStep$var145)) {
							if(fixedFlag$sample136) {
								if((0 <= st[sample][timeStep$var145])) {
									int var58 = st[sample][timeStep$var145];
									if(((0 <= var58) && (var58 < noStates))) {
										double var158 = metric_var[st[sample][timeStep$var145]];
										double cv$weightedProbability = (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(var158))) - (Math.log(var158) * 0.5));
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
								for(int index$sample136$49 = 0; index$sample136$49 < noStates; index$sample136$49 += 1) {
									double cv$probabilitySample136Value50 = distribution$sample136[sample][(timeStep$var145 - 1)][index$sample136$49];
									double var158 = metric_var[index$sample136$49];
									double cv$weightedProbability = ((Math.log(cv$probabilitySample136Value50) + DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample136$49]) / Math.sqrt(var158)))) - (Math.log(var158) * 0.5));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value50);
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var159[sample][timeStep$var145] = cv$distributionAccumulator;
						logProbability$sample170[sample][timeStep$var145] = cv$distributionAccumulator;
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample170 = ((((fixedFlag$sample170 && fixedFlag$sample63) && fixedFlag$sample79) && fixedFlag$sample117) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$sampleValue = logProbability$sample170[sample][timeStep$var145];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var159[sample][timeStep$var145] = cv$sampleValue;
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample117() {
		if(!fixedProbFlag$sample117) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				int cv$sampleValue = st[sample][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < initialStateDistribution.length))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var110[sample] = cv$distributionAccumulator;
				logProbability$sample117[sample] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedFlag$sample30);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = logProbability$sample117[sample];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var110[sample] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample136() {
		if(!fixedProbFlag$sample136) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					int cv$sampleValue = st[sample][timeStep$var122];
					double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var129[sample][(timeStep$var122 - 1)] = cv$distributionAccumulator;
					logProbability$sample136[sample][(timeStep$var122 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample43) && fixedFlag$sample117);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					double cv$rvAccumulator = logProbability$sample136[sample][(timeStep$var122 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var129[sample][(timeStep$var122 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample158() {
		if(!fixedProbFlag$sample158) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$distributionAccumulator = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var145], metric_valid_bias[st[sample][timeStep$var145]]);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var149[sample][timeStep$var145] = cv$distributionAccumulator;
					logProbability$sample158[sample][timeStep$var145] = cv$distributionAccumulator;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$distributionAccumulator);
					logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample158 = (((fixedFlag$sample158 && fixedFlag$sample95) && fixedFlag$sample117) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					double cv$sampleValue = logProbability$sample158[sample][timeStep$var145];
					cv$accumulator = (cv$accumulator + cv$sampleValue);
					logProbability$var149[sample][timeStep$var145] = cv$sampleValue;
					logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
					logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample170() {
		if(!fixedProbFlag$sample170) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double var158 = metric_var[st[sample][timeStep$var145]];
						double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(var158))) - (Math.log(var158) * 0.5));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$var159[sample][timeStep$var145] = cv$distributionAccumulator;
						logProbability$sample170[sample][timeStep$var145] = cv$distributionAccumulator;
						logProbability$metric_g = (logProbability$metric_g + cv$distributionAccumulator);
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample170 = ((((fixedFlag$sample170 && fixedFlag$sample63) && fixedFlag$sample79) && fixedFlag$sample117) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						double cv$sampleValue = logProbability$sample170[sample][timeStep$var145];
						cv$accumulator = (cv$accumulator + cv$sampleValue);
						logProbability$var159[sample][timeStep$var145] = cv$sampleValue;
						logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
					}
				}
			}
			logProbability$metric_1d = (logProbability$metric_1d + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!fixedProbFlag$sample30) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v);
			logProbability$var26 = cv$distributionAccumulator;
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			logProbability$var26 = logProbability$initialStateDistribution;
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample43() {
		if(!fixedProbFlag$sample43) {
			double cv$sampleAccumulator = 0.0;
			for(int var39 = 0; var39 < noStates; var39 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var39], v));
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample43 = fixedFlag$sample43;
		} else {
			logProbability$var28 = logProbability$var40;
			logProbability$m = (logProbability$m + logProbability$var40);
			logProbability$$model = (logProbability$$model + logProbability$var40);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var40);
		}
	}

	private final void logProbabilityValue$sample63() {
		if(!fixedProbFlag$sample63) {
			double cv$sampleAccumulator = 0.0;
			for(int var58 = 0; var58 < noStates; var58 += 1) {
				double cv$sampleValue = metric_mean[var58];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$var59 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample63 = fixedFlag$sample63;
		} else {
			logProbability$var47 = logProbability$var59;
			logProbability$metric_mean = (logProbability$metric_mean + logProbability$var59);
			logProbability$$model = (logProbability$$model + logProbability$var59);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var59);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(metric_var[var74], 1.0, 1.0));
			logProbability$var63 = cv$sampleAccumulator;
			logProbability$var75 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample79 = fixedFlag$sample79;
		} else {
			logProbability$var63 = logProbability$var75;
			logProbability$metric_var = (logProbability$metric_var + logProbability$var75);
			logProbability$$model = (logProbability$$model + logProbability$var75);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var75);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$sampleAccumulator = 0.0;
			for(int var90 = 0; var90 < noStates; var90 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(metric_valid_bias[var90], 1.0, 1.0));
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$var91 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			logProbability$var79 = logProbability$var91;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + logProbability$var91);
			logProbability$$model = (logProbability$$model + logProbability$var91);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var91);
		}
	}

	private final void sample117(int sample) {
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < initialStateDistribution.length)?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample136 && (1 < length$metric[sample]))) {
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] cv$temp$1$var128 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((0.0 <= st[sample][1]) && (st[sample][1] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[sample][1]]):Double.NEGATIVE_INFINITY);
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
			if((0 < length$metric[sample])) {
				{
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][0], metric_valid_bias[cv$valuePos]);
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
				if(metric_valid_g[sample][0]) {
					guard$sample117gaussian169$global[sample][0] = false;
					if(!guard$sample117gaussian169$global[sample][0]) {
						guard$sample117gaussian169$global[sample][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$4$var158 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$4$var158))) - (Math.log(cv$temp$4$var158) * 0.5));
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
					if(!guard$sample117gaussian169$global[sample][0]) {
						guard$sample117gaussian169$global[sample][0] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double cv$temp$12$var158 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$12$var158))) - (Math.log(cv$temp$12$var158) * 0.5));
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
			if((!fixedFlag$sample136 && (1 < length$metric[sample]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var129[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var129, 1.0, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample136[sample][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var129[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var111$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample117[sample];
		double cv$logSum;
		double cv$lseMax = cv$var111$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var111$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var111$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var111$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var111$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample136(int sample, int timeStep$var122) {
		int cv$noStates = 0;
		if((1 == timeStep$var122)) {
			if(fixedFlag$sample117) {
				int var39 = st[sample][0];
				if(((0 <= var39) && (var39 < noStates)))
					cv$noStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample136) {
			if((2 <= timeStep$var122)) {
				int var39 = st[sample][(timeStep$var122 - 1)];
				if(((0 <= var39) && (var39 < noStates)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$timeStep$14 = (timeStep$var122 - 1);
				if(((1 <= index$timeStep$14) && !(index$timeStep$14 == timeStep$var122)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var122)) {
				if(fixedFlag$sample117) {
					int var39 = st[sample][0];
					if(((0 <= var39) && (var39 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var128 = m[st[sample][0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var128.length)?Math.log(cv$temp$0$var128[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample])) {
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]);
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
							if(metric_valid_g[sample][1]) {
								guard$sample136gaussian169$global[sample][1] = false;
								if(!guard$sample136gaussian169$global[sample][1]) {
									guard$sample136gaussian169$global[sample][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$11$var158 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$11$var158))) - (Math.log(cv$temp$11$var158) * 0.5));
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
								if(!guard$sample136gaussian169$global[sample][1]) {
									guard$sample136gaussian169$global[sample][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$43$var158 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$43$var158))) - (Math.log(cv$temp$43$var158) * 0.5));
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
					for(int index$sample117$26 = 0; index$sample117$26 < noStates; index$sample117$26 += 1) {
						double cv$probabilitySample117Value27 = distribution$sample117[sample][index$sample117$26];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample117Value27);
						double[] cv$temp$1$var128 = m[index$sample117$26];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample117Value27) + ((cv$valuePos < cv$temp$1$var128.length)?Math.log(cv$temp$1$var128[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample])) {
							{
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][1], metric_valid_bias[cv$valuePos]);
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
							if(metric_valid_g[sample][1]) {
								guard$sample136gaussian169$global[sample][1] = false;
								if(!guard$sample136gaussian169$global[sample][1]) {
									guard$sample136gaussian169$global[sample][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$19$var158 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$19$var158))) - (Math.log(cv$temp$19$var158) * 0.5));
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
								if(!guard$sample136gaussian169$global[sample][1]) {
									guard$sample136gaussian169$global[sample][1] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double cv$temp$51$var158 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$51$var158))) - (Math.log(cv$temp$51$var158) * 0.5));
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
			int index$timeStep$34 = (timeStep$var122 - 1);
			if(((1 <= index$timeStep$34) && !(index$timeStep$34 == timeStep$var122))) {
				for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
					double cv$probabilitySample136Value36 = distribution$sample136[sample][(index$timeStep$34 - 1)][index$sample136$35];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
					double[] cv$temp$3$var128 = m[index$sample136$35];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + ((cv$valuePos < cv$temp$3$var128.length)?Math.log(cv$temp$3$var128[cv$valuePos]):Double.NEGATIVE_INFINITY));
					{
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							cv$accumulatedConsumerProbabilities = DistributionSampling.logProbabilityBernoulli(metric_valid_g[sample][timeStep$var122], metric_valid_bias[cv$valuePos]);
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
					if(metric_valid_g[sample][timeStep$var122]) {
						guard$sample136gaussian169$global[sample][timeStep$var122] = false;
						if(!guard$sample136gaussian169$global[sample][timeStep$var122]) {
							guard$sample136gaussian169$global[sample][timeStep$var122] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$35$var158 = metric_var[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var122] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$35$var158))) - (Math.log(cv$temp$35$var158) * 0.5));
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
						if(!guard$sample136gaussian169$global[sample][timeStep$var122]) {
							guard$sample136gaussian169$global[sample][timeStep$var122] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double cv$temp$67$var158 = metric_var[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var122] - metric_mean[cv$valuePos]) / Math.sqrt(cv$temp$67$var158))) - (Math.log(cv$temp$67$var158) * 0.5));
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
			int index$timeStep$269_3 = (timeStep$var122 + 1);
			if((index$timeStep$269_3 < length$metric[sample])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var129[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var122)) {
						if(fixedFlag$sample117) {
							int index$var39$280_1 = st[sample][0];
							if(((0 <= index$var39$280_1) && (index$var39$280_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample117$276 = 0; index$sample117$276 < noStates; index$sample117$276 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample117[sample][index$sample117$276]);
						}
					}
					int index$timeStep$284 = (timeStep$var122 - 1);
					if((((1 <= index$timeStep$284) && !(index$timeStep$284 == timeStep$var122)) && !(index$timeStep$284 == index$timeStep$269_3))) {
						for(int index$sample136$285 = 0; index$sample136$285 < noStates; index$sample136$285 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample136[sample][(index$timeStep$284 - 1)][index$sample136$285]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var129, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample136[sample][(index$timeStep$269_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var129[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var130$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		double[] cv$localProbability = distribution$sample136[sample][(timeStep$var122 - 1)];
		double cv$logSum;
		double cv$lseMax = cv$var130$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var130$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var130$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$var130$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var130$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	private final void sample158(int sample, int timeStep$var145) {}

	private final void sample30() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var27$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample117) {
			for(int sample = 0; sample < noSamples; sample += 1)
				cv$var27$countGlobal[st[sample][0]] = (cv$var27$countGlobal[st[sample][0]] + 1.0);
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var27$countGlobal[cv$loopIndex] = (cv$var27$countGlobal[cv$loopIndex] + distribution$sample117[sample][cv$loopIndex]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var27$countGlobal, initialStateDistribution);
	}

	private final void sample43(int var39) {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var40$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample136) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample117) {
						if((var39 == st[sample][0]))
							cv$var40$countGlobal[st[sample][1]] = (cv$var40$countGlobal[st[sample][1]] + 1.0);
					} else
						cv$var40$countGlobal[st[sample][1]] = (cv$var40$countGlobal[st[sample][1]] + distribution$sample117[sample][var39]);
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 2; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					if((var39 == st[sample][(timeStep$var122 - 1)]))
						cv$var40$countGlobal[st[sample][timeStep$var122]] = (cv$var40$countGlobal[st[sample][timeStep$var122]] + 1.0);
				}
			}
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample117) {
						if((var39 == st[sample][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var40$countGlobal[cv$loopIndex] = (cv$var40$countGlobal[cv$loopIndex] + distribution$sample136[sample][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample117[sample][var39];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var40$countGlobal[cv$loopIndex] = (cv$var40$countGlobal[cv$loopIndex] + (distribution$sample136[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					int index$timeStep$52 = (timeStep$var122 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample136[sample][(index$timeStep$52 - 1)][var39];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var40$countGlobal[cv$loopIndex] = (cv$var40$countGlobal[cv$loopIndex] + (distribution$sample136[sample][(timeStep$var122 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var40$countGlobal, m[var39]);
	}

	private final void sample63(int var58) {
		double cv$originalValue = metric_mean[var58];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample117) {
						if((var58 == st[sample][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var74 = st[sample][0];
							if(((0 <= var74) && (var74 < noStates))) {
								double cv$temp$3$var158 = metric_var[st[sample][0]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$3$var158))) - (Math.log(cv$temp$3$var158) * 0.5));
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
						double cv$probabilitySample117Value6 = distribution$sample117[sample][var58];
						double cv$temp$9$var158 = metric_var[var58];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$originalValue) / Math.sqrt(cv$temp$9$var158)))) - (Math.log(cv$temp$9$var158) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
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
				for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						if(fixedFlag$sample136) {
							if((var58 == st[sample][timeStep$var145])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var74 = st[sample][timeStep$var145];
								if(((0 <= var74) && (var74 < noStates))) {
									double cv$temp$21$var158 = metric_var[st[sample][timeStep$var145]];
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$originalValue) / Math.sqrt(cv$temp$21$var158))) - (Math.log(cv$temp$21$var158) * 0.5));
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
							double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var58];
							double cv$temp$27$var158 = metric_var[var58];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$originalValue) / Math.sqrt(cv$temp$27$var158)))) - (Math.log(cv$temp$27$var158) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
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
		metric_mean[var58] = cv$proposedValue;
		double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample117) {
					if((var58 == st[sample][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var74 = st[sample][0];
						if(((0 <= var74) && (var74 < noStates))) {
							double cv$temp$3$var158 = metric_var[st[sample][0]];
							cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$3$var158))) - (Math.log(cv$temp$3$var158) * 0.5));
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
					double cv$probabilitySample117Value6 = distribution$sample117[sample][var58];
					double cv$temp$9$var158 = metric_var[var58];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - cv$proposedValue) / Math.sqrt(cv$temp$9$var158)))) - (Math.log(cv$temp$9$var158) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
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
			for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(metric_valid_g[sample][timeStep$var145]) {
					if(fixedFlag$sample136) {
						if((var58 == st[sample][timeStep$var145])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var74 = st[sample][timeStep$var145];
							if(((0 <= var74) && (var74 < noStates))) {
								double cv$temp$21$var158 = metric_var[st[sample][timeStep$var145]];
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$proposedValue) / Math.sqrt(cv$temp$21$var158))) - (Math.log(cv$temp$21$var158) * 0.5));
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
						double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var58];
						double cv$temp$27$var158 = metric_var[var58];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$proposedValue) / Math.sqrt(cv$temp$27$var158)))) - (Math.log(cv$temp$27$var158) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
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
			metric_mean[var58] = cv$originalValue;
	}

	private final void sample79(int var74) {
		double cv$originalValue = metric_var[var74];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
					if(fixedFlag$sample117) {
						if((var74 == st[sample][0])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var58 = st[sample][0];
							if(((0 <= var58) && (var58 < noStates))) {
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
						double cv$probabilitySample117Value6 = distribution$sample117[sample][var74];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var74]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
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
				for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(metric_valid_g[sample][timeStep$var145]) {
						if(fixedFlag$sample136) {
							if((var74 == st[sample][timeStep$var145])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var58 = st[sample][timeStep$var145];
								if(((0 <= var58) && (var58 < noStates))) {
									cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5));
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
							double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var74];
							double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[var74]) / Math.sqrt(cv$originalValue)))) - (Math.log(cv$originalValue) * 0.5));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
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
		metric_var[var74] = cv$proposedValue;
		double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((metric_valid_g[sample][0] && (0 < length$metric[sample]))) {
				if(fixedFlag$sample117) {
					if((var74 == st[sample][0])) {
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var58 = st[sample][0];
						if(((0 <= var58) && (var58 < noStates))) {
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
					double cv$probabilitySample117Value6 = distribution$sample117[sample][var74];
					double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample117Value6) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][0] - metric_mean[var74]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
					double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample117Value6), 0.0);
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
			for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(metric_valid_g[sample][timeStep$var145]) {
					if(fixedFlag$sample136) {
						if((var74 == st[sample][timeStep$var145])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var58 = st[sample][timeStep$var145];
							if(((0 <= var58) && (var58 < noStates))) {
								cv$accumulatedConsumerProbabilities = (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[st[sample][timeStep$var145]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5));
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
						double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var74];
						double cv$accumulatedConsumerProbabilities = ((Math.log(cv$probabilitySample136Value17) + DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - metric_mean[var74]) / Math.sqrt(cv$proposedValue)))) - (Math.log(cv$proposedValue) * 0.5));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample136Value17), 0.0);
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
			metric_var[var74] = cv$originalValue;
	}

	private final void sample95(int var90) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((0 < length$metric[sample])) {
				if(fixedFlag$sample117) {
					if((var90 == st[sample][0])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample117Value6 = distribution$sample117[sample][var90];
					cv$count = (cv$count + cv$probabilitySample117Value6);
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample117Value6);
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 1; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(fixedFlag$sample136) {
					if((var90 == st[sample][timeStep$var145])) {
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][timeStep$var145])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample136Value17 = distribution$sample136[sample][(timeStep$var145 - 1)][var90];
					cv$count = (cv$count + cv$probabilitySample136Value17);
					if(metric_valid_g[sample][timeStep$var145])
						cv$sum = (cv$sum + cv$probabilitySample136Value17);
				}
			}
		}
		metric_valid_bias[var90] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {
		cv$var27$countGlobal = new double[Math.max(0, noStates)];
		int cv$max = 0;
		if((0 < noStates))
			cv$max = noStates;
		cv$var40$countGlobal = new double[cv$max];
		cv$distributionAccumulator$var129 = new double[noStates];
		cv$var111$stateProbabilityGlobal = new double[noStates];
		{
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, length$metric[sample]);
			guard$sample117gaussian169$global = new boolean[length$metric.length][cv$max_timeStep$var145];
		}
		cv$var130$stateProbabilityGlobal = new double[noStates];
		int cv$max_timeStep$var145 = 0;
		for(int sample = 0; sample < length$metric.length; sample += 1)
			cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, length$metric[sample]);
		guard$sample136gaussian169$global = new boolean[length$metric.length][cv$max_timeStep$var145];
		cv$var150$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!setFlag$initialStateDistribution)
			initialStateDistribution = new double[noStates];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var39 = 0; var39 < noStates; var39 += 1)
				m[var39] = new double[noStates];
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
		distribution$sample117 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			distribution$sample117[sample] = new double[noStates];
		distribution$sample136 = new double[length$metric.length][][];
		for(int sample = 0; sample < length$metric.length; sample += 1) {
			double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
			distribution$sample136[sample] = subarray$0;
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
				subarray$0[(timeStep$var122 - 1)] = new double[noStates];
		}
		logProbability$var110 = new double[length$metric.length];
		logProbability$sample117 = new double[length$metric.length];
		logProbability$var129 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var129[sample] = new double[(length$metric[sample] - 1)];
		logProbability$sample136 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample136[sample] = new double[(length$metric[sample] - 1)];
		logProbability$var149 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var149[sample] = new double[length$metric[sample]];
		logProbability$sample158 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample158[sample] = new double[length$metric[sample]];
		logProbability$var159 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$var159[sample] = new double[length$metric[sample]];
		logProbability$sample170 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample170[sample] = new double[length$metric[sample]];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample43) {
			for(int var39 = 0; var39 < noStates; var39 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var39]);
		}
		if(!fixedFlag$sample63) {
			for(int var58 = 0; var58 < noStates; var58 += 1)
				metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample95) {
			for(int var90 = 0; var90 < noStates; var90 += 1)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample117)
				st[sample][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample136) {
				int[] var123 = st[sample];
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if((metric_valid_g[sample][timeStep$var145] && (!fixedFlag$sample158 || !fixedFlag$sample170)))
					metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample43) {
			for(int var39 = 0; var39 < noStates; var39 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var39]);
		}
		if(!fixedFlag$sample63) {
			for(int var58 = 0; var58 < noStates; var58 += 1)
				metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample95) {
			for(int var90 = 0; var90 < noStates; var90 += 1)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample117) {
				double[] cv$distribution$sample117 = distribution$sample117[sample];
				for(int index$var110 = 0; index$var110 < noStates; index$var110 += 1)
					cv$distribution$sample117[index$var110] = ((index$var110 < initialStateDistribution.length)?initialStateDistribution[index$var110]:0.0);
			}
			if(!fixedFlag$sample136) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					double[] cv$distribution$sample136 = distribution$sample136[sample][(timeStep$var122 - 1)];
					for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
						cv$distribution$sample136[index$var129] = 0.0;
					if((1 == timeStep$var122)) {
						if(fixedFlag$sample117) {
							int var39 = st[sample][0];
							if(((0 <= var39) && (var39 < noStates))) {
								double[] var128 = m[st[sample][0]];
								for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
									cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + ((index$var129 < var128.length)?var128[index$var129]:0.0));
							}
						} else {
							for(int index$sample117$3 = 0; index$sample117$3 < noStates; index$sample117$3 += 1) {
								double cv$probabilitySample117Value4 = distribution$sample117[sample][index$sample117$3];
								double[] var128 = m[index$sample117$3];
								for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
									cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample117Value4 * ((index$var129 < var128.length)?var128[index$var129]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var122 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
							double cv$probabilitySample136Value13 = distribution$sample136[sample][(index$timeStep$11 - 1)][index$sample136$12];
							double[] var128 = m[index$sample136$12];
							for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
								cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample136Value13 * ((index$var129 < var128.length)?var128[index$var129]:0.0)));
						}
					}
					double cv$var129$sum = 0.0;
					for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
						cv$var129$sum = (cv$var129$sum + cv$distribution$sample136[index$var129]);
					for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
						cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] / cv$var129$sum);
				}
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if((metric_valid_g[sample][timeStep$var145] && (!fixedFlag$sample158 || !fixedFlag$sample170)))
					metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample43) {
			for(int var39 = 0; var39 < noStates; var39 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var39]);
		}
		if(!fixedFlag$sample63) {
			for(int var58 = 0; var58 < noStates; var58 += 1)
				metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample95) {
			for(int var90 = 0; var90 < noStates; var90 += 1)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample117)
				st[sample][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample136) {
				int[] var123 = st[sample];
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if((metric_valid_g[sample][timeStep$var145] && (!fixedFlag$sample158 || !fixedFlag$sample170)))
					metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample30)
				sample30();
			if(!fixedFlag$sample43) {
				for(int var39 = 0; var39 < noStates; var39 += 1)
					sample43(var39);
			}
			if(!fixedFlag$sample63) {
				for(int var58 = 0; var58 < noStates; var58 += 1)
					sample63(var58);
			}
			if(!fixedFlag$sample79) {
				for(int var74 = 0; var74 < noStates; var74 += 1)
					sample79(var74);
			}
			if(!fixedFlag$sample95) {
				for(int var90 = 0; var90 < noStates; var90 += 1)
					sample95(var90);
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(!fixedFlag$sample117)
					sample117(sample);
				if(!fixedFlag$sample136) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
						sample136(sample, timeStep$var122);
				}
				if(!fixedFlag$sample158) {
					for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
						sample158(sample, timeStep$var145);
				}
			}
		} else {
			for(int sample = (noSamples - 1); sample >= 0; sample -= 1) {
				if(!fixedFlag$sample158) {
					for(int timeStep$var145 = (length$metric[sample] - 1); timeStep$var145 >= 0; timeStep$var145 -= 1)
						sample158(sample, timeStep$var145);
				}
				if(!fixedFlag$sample136) {
					for(int timeStep$var122 = (length$metric[sample] - 1); timeStep$var122 >= 1; timeStep$var122 -= 1)
						sample136(sample, timeStep$var122);
				}
				if(!fixedFlag$sample117)
					sample117(sample);
			}
			if(!fixedFlag$sample95) {
				for(int var90 = (noStates - 1); var90 >= 0; var90 -= 1)
					sample95(var90);
			}
			if(!fixedFlag$sample79) {
				for(int var74 = (noStates - 1); var74 >= 0; var74 -= 1)
					sample79(var74);
			}
			if(!fixedFlag$sample63) {
				for(int var58 = (noStates - 1); var58 >= 0; var58 -= 1)
					sample63(var58);
			}
			if(!fixedFlag$sample43) {
				for(int var39 = (noStates - 1); var39 >= 0; var39 -= 1)
					sample43(var39);
			}
			if(!fixedFlag$sample30)
				sample30();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		noSamples = length$metric.length;
		for(int var23 = 0; var23 < noStates; var23 += 1)
			v[var23] = 0.1;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample30)
			logProbability$initialStateDistribution = 0.0;
		logProbability$var28 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample43)
			logProbability$var40 = 0.0;
		logProbability$var47 = 0.0;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample63)
			logProbability$var59 = 0.0;
		logProbability$var63 = 0.0;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample79)
			logProbability$var75 = 0.0;
		logProbability$var79 = 0.0;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample95)
			logProbability$var91 = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1)
			logProbability$var110[sample] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample117) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample117[sample] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
				logProbability$var129[sample][(timeStep$var122 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample136) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					logProbability$sample136[sample][(timeStep$var122 - 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
				logProbability$var149[sample][timeStep$var145] = 0.0;
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample158) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample158[sample][timeStep$var145] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
				logProbability$var159[sample][timeStep$var145] = 0.0;
		}
		logProbability$metric_1d = 0.0;
		if(!fixedProbFlag$sample170) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample170[sample][timeStep$var145] = 0.0;
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
		if(fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(fixedFlag$sample43)
			logProbabilityValue$sample43();
		if(fixedFlag$sample63)
			logProbabilityValue$sample63();
		if(fixedFlag$sample79)
			logProbabilityValue$sample79();
		if(fixedFlag$sample95)
			logProbabilityValue$sample95();
		logProbabilityValue$sample158();
		logProbabilityValue$sample170();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample43();
		logProbabilityValue$sample63();
		logProbabilityValue$sample79();
		logProbabilityValue$sample95();
		logProbabilityDistribution$sample117();
		logProbabilityDistribution$sample136();
		logProbabilityDistribution$sample158();
		logProbabilityDistribution$sample170();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample43();
		logProbabilityValue$sample63();
		logProbabilityValue$sample79();
		logProbabilityValue$sample95();
		logProbabilityValue$sample117();
		logProbabilityValue$sample136();
		logProbabilityValue$sample158();
		logProbabilityValue$sample170();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		if(!fixedFlag$sample43) {
			for(int var39 = 0; var39 < noStates; var39 += 1)
				DistributionSampling.sampleDirichlet(RNG$, v, m[var39]);
		}
		if(!fixedFlag$sample63) {
			for(int var58 = 0; var58 < noStates; var58 += 1)
				metric_mean[var58] = (DistributionSampling.sampleUniform(RNG$) * 100.0);
		}
		if(!fixedFlag$sample79) {
			for(int var74 = 0; var74 < noStates; var74 += 1)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample95) {
			for(int var90 = 0; var90 < noStates; var90 += 1)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			if(!fixedFlag$sample117)
				st[sample][0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			if(!fixedFlag$sample136) {
				int[] var123 = st[sample];
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
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
			for(int timeStep$var145 = (length$metric[sample] - 1); timeStep$var145 >= 0; timeStep$var145 -= 1)
				metric_g[sample][timeStep$var145] = metric[sample][timeStep$var145];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMMetrics2(\n               double[][] metric,\n               boolean[][] metric_valid, \n               int noStates) {\n    \n    int noSamples = metric.length;\n\n    // Construct arrays describing the probability of a move from 1 state to another.\n    double[] v = new double[noStates] <~ 0.1;\n    double[] initialStateDistribution = dirichlet(v).sample();\n    double[][] m = dirichlet(v).sample(noStates);\n\n    //Allocate space for states\n    int[][] st = new int[noSamples][];\n\n    //Allocate space for generated metrics \n    double[][] metric_g = new double[noSamples][];\n    boolean[][] metric_valid_g = new boolean[noSamples][];\n    \n    //Calculate priors for the metric\n    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n    \n    // Compute the values of each metric value\n    for(int sample = 0; sample < noSamples; sample++) {\n        //Calculate all the state transitions\n        int streamLength = metric[sample].length;\n        \n        // Allocate space for the state.\n        st[sample] = new int[streamLength];\n        \n        // Set the initial state by sampling from a categorical with learnt weightings.\n        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n        \n        // Calculate the remaining weightings\n        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n        \n        //Calculate metric values\n        double[] metric_1d = new double[streamLength];\n        metric_g[sample] = metric_1d;\n\n        boolean[] metric_valid_1d = new boolean[streamLength];\n        metric_valid_g[sample] = metric_valid_1d;\n\n        //Generate values.\n        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n            int currentState = st[sample][timeStep];\n            \n            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n            if(metric_valid_1d[timeStep])\n                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n            metric_1d[timeStep].observe(metric[sample][timeStep]);\n        }\n    }\n\n    //Tie the values to the measured values.\n    metric_valid_g.observe(metric_valid);\n}";
	}
}