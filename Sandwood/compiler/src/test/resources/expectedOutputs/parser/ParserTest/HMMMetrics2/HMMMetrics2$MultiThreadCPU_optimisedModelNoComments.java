package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMMetrics2$CoreInterface {
	private boolean[] constrainedFlag$sample104;
	private boolean[][] constrainedFlag$sample123;
	private boolean constrainedFlag$sample19 = true;
	private boolean[] constrainedFlag$sample32;
	private boolean[] constrainedFlag$sample52;
	private boolean[] constrainedFlag$sample68;
	private boolean[] constrainedFlag$sample84;
	private double[][] cv$distributionAccumulator$var120;
	private double[][] cv$var102$stateProbabilityGlobal;
	private double[][] cv$var121$stateProbabilityGlobal;
	private double[] cv$var19$countGlobal;
	private double[][] cv$var32$countGlobal;
	private double[][] distribution$sample104;
	private double[][][] distribution$sample123;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample123 = false;
	private boolean fixedFlag$sample157 = false;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedFlag$sample68 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample123 = false;
	private boolean fixedProbFlag$sample145 = false;
	private boolean fixedProbFlag$sample157 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean fixedProbFlag$sample68 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean[][][] guard$sample104gaussian156$global;
	private boolean[][][] guard$sample123gaussian156$global;
	private double[] initialStateDistribution;
	private int[] length$metric;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$initialStateDistribution;
	private double logProbability$m;
	private double logProbability$metric_g;
	private double logProbability$metric_mean;
	private double logProbability$metric_valid_1d;
	private double logProbability$metric_valid_bias;
	private double logProbability$metric_valid_g;
	private double logProbability$metric_var;
	private double[] logProbability$sample104;
	private double[][] logProbability$sample123;
	private double[][] logProbability$sample145;
	private double[][] logProbability$sample157;
	private double logProbability$st;
	private double logProbability$var151;
	private double logProbability$var32;
	private double logProbability$var51;
	private double logProbability$var67;
	private double logProbability$var83;
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
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[][] var151;

	public HMMMetrics2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$distribution$sample104() {
		return distribution$sample104;
	}

	@Override
	public final void set$distribution$sample104(double[][] cv$value) {
		distribution$sample104 = cv$value;
	}

	@Override
	public final double[][][] get$distribution$sample123() {
		return distribution$sample123;
	}

	@Override
	public final void set$distribution$sample123(double[][][] cv$value) {
		distribution$sample123 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		fixedFlag$sample104 = cv$value;
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample123() {
		return fixedFlag$sample123;
	}

	@Override
	public final void set$fixedFlag$sample123(boolean cv$value) {
		fixedFlag$sample123 = cv$value;
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
		fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample157() {
		return fixedFlag$sample157;
	}

	@Override
	public final void set$fixedFlag$sample157(boolean cv$value) {
		fixedFlag$sample157 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (cv$value && fixedProbFlag$sample19);
		fixedProbFlag$sample104 = (cv$value && fixedProbFlag$sample104);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value) {
		fixedFlag$sample32 = cv$value;
		fixedProbFlag$sample32 = (cv$value && fixedProbFlag$sample32);
		fixedProbFlag$sample123 = (cv$value && fixedProbFlag$sample123);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (cv$value && fixedProbFlag$sample52);
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample68() {
		return fixedFlag$sample68;
	}

	@Override
	public final void set$fixedFlag$sample68(boolean cv$value) {
		fixedFlag$sample68 = cv$value;
		fixedProbFlag$sample68 = (cv$value && fixedProbFlag$sample68);
		fixedProbFlag$sample157 = (cv$value && fixedProbFlag$sample157);
	}

	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		fixedFlag$sample84 = cv$value;
		fixedProbFlag$sample84 = (cv$value && fixedProbFlag$sample84);
		fixedProbFlag$sample145 = (cv$value && fixedProbFlag$sample145);
	}

	@Override
	public final double[] get$initialStateDistribution() {
		return initialStateDistribution;
	}

	@Override
	public final void set$initialStateDistribution(double[] cv$value) {
		initialStateDistribution = cv$value;
		fixedProbFlag$sample19 = false;
		fixedProbFlag$sample104 = false;
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
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample123 = false;
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
	public final double[] get$metric_mean() {
		return metric_mean;
	}

	@Override
	public final void set$metric_mean(double[] cv$value) {
		metric_mean = cv$value;
		fixedProbFlag$sample52 = false;
		fixedProbFlag$sample157 = false;
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
		fixedProbFlag$sample84 = false;
		fixedProbFlag$sample145 = false;
	}

	@Override
	public final boolean[][] get$metric_valid_g() {
		return metric_valid_g;
	}

	@Override
	public final double[] get$metric_var() {
		return metric_var;
	}

	@Override
	public final void set$metric_var(double[] cv$value) {
		metric_var = cv$value;
		fixedProbFlag$sample68 = false;
		fixedProbFlag$sample157 = false;
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
		fixedProbFlag$sample104 = false;
		fixedProbFlag$sample123 = false;
		fixedProbFlag$sample145 = false;
		fixedProbFlag$sample157 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample104() {
		if(!fixedProbFlag$sample104) {
			if(fixedFlag$sample104) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					int cv$sampleValue = st[sample][0];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample104[sample] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample104 = fixedFlag$sample19;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample104[sample]);
			if(fixedFlag$sample104)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample123() {
		if(!fixedProbFlag$sample123) {
			if(fixedFlag$sample123) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[sample][timeStep$var113];
						if((1 == timeStep$var113)) {
							if(fixedFlag$sample104) {
								int var31 = st[sample][0];
								if(((0 <= var31) && (var31 < noStates))) {
									double[] var119 = m[st[sample][0]];
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample104$6 = 0; index$sample104$6 < noStates; index$sample104$6 += 1) {
									double cv$probabilitySample104Value7 = distribution$sample104[sample][index$sample104$6];
									double[] var119 = m[index$sample104$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value7);
								}
							}
						}
						if((2 <= timeStep$var113)) {
							int var31 = st[sample][(timeStep$var113 - 1)];
							if(((0 <= var31) && (var31 < noStates))) {
								double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$sample123[sample][(timeStep$var113 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample123 = (fixedFlag$sample32 && fixedFlag$sample104);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample123[sample][(timeStep$var113 - 1)]);
			}
			if(fixedFlag$sample123)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample145() {
		if(!fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					boolean cv$sampleValue = metric_valid_g[sample][timeStep$var136];
					if((0 == timeStep$var136)) {
						if(fixedFlag$sample104) {
							int var82 = st[sample][0];
							if(((0 <= var82) && (var82 < noStates))) {
								double var139 = metric_valid_bias[st[sample][0]];
								cv$distributionAccumulator = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
								double cv$probabilitySample104Value5 = distribution$sample104[sample][index$sample104$4];
								double var139 = metric_valid_bias[index$sample104$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
							}
						}
					}
					if((1 <= timeStep$var136)) {
						if(fixedFlag$sample123) {
							int var82 = st[sample][timeStep$var136];
							if(((0 <= var82) && (var82 < noStates))) {
								double var139 = metric_valid_bias[st[sample][timeStep$var136]];
								double cv$weightedProbability = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
							for(int index$sample123$13 = 0; index$sample123$13 < noStates; index$sample123$13 += 1) {
								double cv$probabilitySample123Value14 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$13];
								double var139 = metric_valid_bias[index$sample123$13];
								double cv$weightedProbability = (Math.log(cv$probabilitySample123Value14) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value14);
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample145[sample][timeStep$var136] = cv$distributionAccumulator;
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample145[sample][timeStep$var136]);
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						double cv$sampleValue = var151[sample][timeStep$var136];
						if((0 == timeStep$var136)) {
							if(fixedFlag$sample104) {
								if((0 <= st[sample][0])) {
									int var50 = st[sample][0];
									if(((0 <= var50) && (var50 < noStates))) {
										double var149 = metric_var[st[sample][0]];
										cv$distributionAccumulator = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][0]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								for(int index$sample104$4 = 0; index$sample104$4 < noStates; index$sample104$4 += 1) {
									double cv$probabilitySample104Value5 = distribution$sample104[sample][index$sample104$4];
									double var149 = metric_var[index$sample104$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample104$4]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
								}
							}
						}
						if((1 <= timeStep$var136)) {
							if(fixedFlag$sample123) {
								if((0 <= st[sample][timeStep$var136])) {
									int var50 = st[sample][timeStep$var136];
									if(((0 <= var50) && (var50 < noStates))) {
										double var149 = metric_var[st[sample][timeStep$var136]];
										double cv$weightedProbability = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
								for(int index$sample123$49 = 0; index$sample123$49 < noStates; index$sample123$49 += 1) {
									double cv$probabilitySample123Value50 = distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$49];
									double var149 = metric_var[index$sample123$49];
									double cv$weightedProbability = (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - metric_mean[index$sample123$49]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value50);
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$sample157[sample][timeStep$var136] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = (((fixedFlag$sample52 && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136])
						cv$accumulator = (cv$accumulator + logProbability$sample157[sample][timeStep$var136]);
				}
			}
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				int cv$sampleValue = st[sample][0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$sampleValue])) && (initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$sample104[sample] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1)
				cv$accumulator = (cv$accumulator + logProbability$sample104[sample]);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample104)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					int cv$sampleValue = st[sample][timeStep$var113];
					double[] var119 = m[st[sample][(timeStep$var113 - 1)]];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample123[sample][(timeStep$var113 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample123 = ((fixedFlag$sample123 && fixedFlag$sample32) && fixedFlag$sample104);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample123[sample][(timeStep$var113 - 1)]);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample123)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample145() {
		if(!fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					double var139 = metric_valid_bias[st[sample][timeStep$var136]];
					double cv$distributionAccumulator = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$sample145[sample][timeStep$var136] = cv$distributionAccumulator;
				}
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample145 = ((fixedFlag$sample84 && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					cv$accumulator = (cv$accumulator + logProbability$sample145[sample][timeStep$var136]);
			}
			logProbability$metric_valid_1d = (logProbability$metric_valid_1d + cv$accumulator);
			logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						double var149 = metric_var[st[sample][timeStep$var136]];
						double cv$distributionAccumulator = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						logProbability$sample157[sample][timeStep$var136] = cv$distributionAccumulator;
					}
				}
			}
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample157 = (((fixedFlag$sample52 && fixedFlag$sample68) && fixedFlag$sample104) && fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136])
						cv$accumulator = (cv$accumulator + logProbability$sample157[sample][timeStep$var136]);
				}
			}
			logProbability$var151 = (logProbability$var151 + cv$accumulator);
			logProbability$metric_g = (logProbability$metric_g + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(initialStateDistribution, v, noStates);
			logProbability$initialStateDistribution = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			logProbability$$model = (logProbability$$model + logProbability$initialStateDistribution);
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noStates; var31 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var31], v, noStates));
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			logProbability$m = (logProbability$m + logProbability$var32);
			logProbability$$model = (logProbability$$model + logProbability$var32);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < noStates; var50 += 1) {
				double cv$sampleValue = metric_mean[var50];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample52 = fixedFlag$sample52;
		} else {
			logProbability$metric_mean = (logProbability$metric_mean + logProbability$var51);
			logProbability$$model = (logProbability$$model + logProbability$var51);
			if(fixedFlag$sample52)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var51);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!fixedProbFlag$sample68) {
			double cv$sampleAccumulator = 0.0;
			for(int var66 = 0; var66 < noStates; var66 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(metric_var[var66], 1.0, 1.0));
			logProbability$var67 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample68 = fixedFlag$sample68;
		} else {
			logProbability$metric_var = (logProbability$metric_var + logProbability$var67);
			logProbability$$model = (logProbability$$model + logProbability$var67);
			if(fixedFlag$sample68)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var67);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!fixedProbFlag$sample84) {
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < noStates; var82 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(metric_valid_bias[var82], 1.0, 1.0));
			logProbability$var83 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample84 = fixedFlag$sample84;
		} else {
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + logProbability$var83);
			logProbability$$model = (logProbability$$model + logProbability$var83);
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var83);
		}
	}

	private final void sample104(int sample, int threadID$cv$sample, Rng RNG$) {
		constrainedFlag$sample104[sample] = false;
		int cv$numStates = Math.max(0, noStates);
		double[] cv$stateProbabilityLocal = cv$var102$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample123 && (1 < length$metric[sample]))) {
				constrainedFlag$sample104[sample] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] var119 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[sample][1]) && (st[sample][1] < noStates)) && (0.0 <= var119[st[sample][1]])) && (var119[st[sample][1]] <= 1.0))?Math.log(var119[st[sample][1]]):Double.NEGATIVE_INFINITY);
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
					constrainedFlag$sample104[sample] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var139 = metric_valid_bias[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][0]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
					boolean[][] guard$sample104gaussian156 = guard$sample104gaussian156$global[threadID$cv$sample];
					guard$sample104gaussian156[sample][0] = false;
					if(!guard$sample104gaussian156[sample][0]) {
						guard$sample104gaussian156[sample][0] = true;
						constrainedFlag$sample104[sample] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					if(!guard$sample104gaussian156[sample][0]) {
						guard$sample104gaussian156[sample][0] = true;
						constrainedFlag$sample104[sample] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
			if((!fixedFlag$sample123 && (1 < length$metric[sample]))) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120[threadID$cv$sample];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample123[sample][0];
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
		if(constrainedFlag$sample104[sample]) {
			double[] cv$localProbability = distribution$sample104[sample];
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample123(int sample, int timeStep$var113, int threadID$cv$sample, Rng RNG$) {
		constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = false;
		int cv$numStates = 0;
		if((1 == timeStep$var113)) {
			if(fixedFlag$sample104) {
				int var31 = st[sample][0];
				if(((0 <= var31) && (var31 < noStates)))
					cv$numStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$numStates = noStates;
			}
		}
		if((0 < noStates)) {
			int index$timeStep$13 = (timeStep$var113 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var113)))
				cv$numStates = noStates;
		}
		double[] cv$stateProbabilityLocal = cv$var121$stateProbabilityGlobal[threadID$cv$sample];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var113)) {
				if(fixedFlag$sample104) {
					int var31 = st[sample][0];
					if(((0 <= var31) && (var31 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var119 = m[st[sample][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var119[cv$valuePos])) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample])) {
							{
								constrainedFlag$sample123[sample][0] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									double var139 = metric_valid_bias[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
								boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
								guard$sample123gaussian156[sample][1] = false;
								if(!guard$sample123gaussian156[sample][1]) {
									guard$sample123gaussian156[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var149 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
								if(!guard$sample123gaussian156[sample][1]) {
									guard$sample123gaussian156[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var149 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					for(int index$sample104$22 = 0; index$sample104$22 < noStates; index$sample104$22 += 1) {
						double cv$probabilitySample104Value23 = distribution$sample104[sample][index$sample104$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value23);
						double[] var119 = m[index$sample104$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value23) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample])) {
							constrainedFlag$sample123[sample][0] = true;
							double var139 = metric_valid_bias[cv$valuePos];
							cv$accumulatedProbabilities = ((((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							if(metric_valid_g[sample][1]) {
								boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
								guard$sample123gaussian156[sample][1] = false;
								if(!guard$sample123gaussian156[sample][1]) {
									guard$sample123gaussian156[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double var149 = metric_var[cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
								if(!guard$sample123gaussian156[sample][1]) {
									guard$sample123gaussian156[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double var149 = metric_var[cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$30 = (timeStep$var113 - 1);
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var113))) {
				for(int index$sample123$31 = 0; index$sample123$31 < noStates; index$sample123$31 += 1) {
					double cv$probabilitySample123Value32 = distribution$sample123[sample][(index$timeStep$30 - 1)][index$sample123$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value32);
					double[] var119 = m[index$sample123$31];
					constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
					double var139 = metric_valid_bias[index$sample123$31];
					double cv$accumulatedProbabilities = (((((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][timeStep$var113]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample123Value32)) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
					if(metric_valid_g[sample][timeStep$var113]) {
						boolean[][] guard$sample123gaussian156 = guard$sample123gaussian156$global[threadID$cv$sample];
						guard$sample123gaussian156[sample][timeStep$var113] = false;
						if(!guard$sample123gaussian156[sample][timeStep$var113]) {
							guard$sample123gaussian156[sample][timeStep$var113] = true;
							constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var113] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!guard$sample123gaussian156[sample][timeStep$var113]) {
							guard$sample123gaussian156[sample][timeStep$var113] = true;
							constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var113] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			int index$timeStep$265_3 = (timeStep$var113 + 1);
			if((index$timeStep$265_3 < length$metric[sample])) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var120[threadID$cv$sample];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var113)) {
						if(fixedFlag$sample104) {
							int index$var31$276_1 = st[sample][0];
							if(((0 <= index$var31$276_1) && (index$var31$276_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample104$272 = 0; index$sample104$272 < noStates; index$sample104$272 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample104[sample][index$sample104$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var113 - 1);
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var113)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						for(int index$sample123$281 = 0; index$sample123$281 < noStates; index$sample123$281 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample123[sample][(index$timeStep$280 - 1)][index$sample123$281]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample123[sample][(index$timeStep$265_3 - 1)];
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
		if(constrainedFlag$sample123[sample][(timeStep$var113 - 1)]) {
			double[] cv$localProbability = distribution$sample123[sample][(timeStep$var113 - 1)];
			double cv$logSum;
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample19() {
		constrainedFlag$sample19 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var19$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				constrainedFlag$sample19 = true;
				cv$var19$countGlobal[st[sample][0]] = (cv$var19$countGlobal[st[sample][0]] + 1.0);
			}
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var19$countGlobal[cv$loopIndex] = (cv$var19$countGlobal[cv$loopIndex] + distribution$sample104[sample][cv$loopIndex]);
			}
		}
		if(constrainedFlag$sample19)
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var19$countGlobal, initialStateDistribution, noStates);
	}

	private final void sample32(int var31, int threadID$cv$var31, Rng RNG$) {
		constrainedFlag$sample32[var31] = false;
		double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample123) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample104) {
						if((var31 == st[sample][0])) {
							constrainedFlag$sample32[var31] = true;
							cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + 1.0);
						}
					} else {
						constrainedFlag$sample32[var31] = true;
						cv$countLocal[st[sample][1]] = (cv$countLocal[st[sample][1]] + distribution$sample104[sample][var31]);
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 2; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					if((var31 == st[sample][(timeStep$var113 - 1)])) {
						constrainedFlag$sample32[var31] = true;
						cv$countLocal[st[sample][timeStep$var113]] = (cv$countLocal[st[sample][timeStep$var113]] + 1.0);
					}
				}
			}
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample104) {
						if((var31 == st[sample][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample123[sample][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample104[sample][var31];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					int index$timeStep$52 = (timeStep$var113 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample123[sample][(index$timeStep$52 - 1)][var31];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample123[sample][(timeStep$var113 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(constrainedFlag$sample32[var31])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var31], noStates);
	}

	private final void sample52(int var50, int threadID$cv$var50, Rng RNG$) {
		constrainedFlag$sample52[var50] = false;
		double cv$originalValue = metric_mean[var50];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var50 == st[sample][0])) {
							constrainedFlag$sample52[var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = st[sample][0];
							if(((0 <= var66) && (var66 < noStates))) {
								double var149 = metric_var[st[sample][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var50];
						constrainedFlag$sample52[var50] = true;
						double var149 = metric_var[var50];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var50 == st[sample][timeStep$var136])) {
								constrainedFlag$sample52[var50] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < noStates))) {
									double var149 = metric_var[st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							constrainedFlag$sample52[var50] = true;
							double var149 = metric_var[var50];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
		if(constrainedFlag$sample52[var50]) {
			metric_mean[var50] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var50 == st[sample][0])) {
							constrainedFlag$sample52[var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = st[sample][0];
							if(((0 <= var66) && (var66 < noStates))) {
								double var149 = metric_var[st[sample][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var50];
						constrainedFlag$sample52[var50] = true;
						double var149 = metric_var[var50];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var50 == st[sample][timeStep$var136])) {
								constrainedFlag$sample52[var50] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < noStates))) {
									double var149 = metric_var[st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							constrainedFlag$sample52[var50] = true;
							double var149 = metric_var[var50];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				metric_mean[var50] = cv$originalValue;
		}
	}

	private final void sample68(int var66, int threadID$cv$var66, Rng RNG$) {
		constrainedFlag$sample68[var66] = false;
		double cv$originalValue = metric_var[var66];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var66 == st[sample][0])) {
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = st[sample][0];
							if(((0 <= var50) && (var50 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var66];
						constrainedFlag$sample68[var66] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var66 == st[sample][timeStep$var136])) {
								constrainedFlag$sample68[var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
		if(constrainedFlag$sample68[var66]) {
			metric_var[var66] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var66 == st[sample][0])) {
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = st[sample][0];
							if(((0 <= var50) && (var50 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var66];
						constrainedFlag$sample68[var66] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var66 == st[sample][timeStep$var136])) {
								constrainedFlag$sample68[var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				metric_var[var66] = cv$originalValue;
		}
	}

	private final void sample84(int var82, int threadID$cv$var82, Rng RNG$) {
		constrainedFlag$sample84[var82] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((0 < length$metric[sample])) {
				if(fixedFlag$sample104) {
					if((var82 == st[sample][0])) {
						constrainedFlag$sample84[var82] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample104Value6 = distribution$sample104[sample][var82];
					constrainedFlag$sample84[var82] = true;
					cv$count = (cv$count + cv$probabilitySample104Value6);
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample104Value6);
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				if(fixedFlag$sample123) {
					if((var82 == st[sample][timeStep$var136])) {
						constrainedFlag$sample84[var82] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][timeStep$var136])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample123Value17 = distribution$sample123[sample][(timeStep$var136 - 1)][var82];
					constrainedFlag$sample84[var82] = true;
					cv$count = (cv$count + cv$probabilitySample123Value17);
					if(metric_valid_g[sample][timeStep$var136])
						cv$sum = (cv$sum + cv$probabilitySample123Value17);
				}
			}
		}
		if(constrainedFlag$sample84[var82])
			metric_valid_bias[var82] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	@Override
	public final void allocateScratch() {
		cv$var19$countGlobal = new double[noStates];
		{
			int cv$threadCount = threadCount();
			cv$var32$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var32$countGlobal[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$distributionAccumulator$var120 = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$distributionAccumulator$var120[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$var102$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var102$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		{
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, length$metric[sample]);
			int cv$max_sample = length$metric.length;
			int cv$threadCount = threadCount();
			guard$sample104gaussian156$global = new boolean[cv$threadCount][][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample104gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
		}
		{
			int cv$threadCount = threadCount();
			cv$var121$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var121$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		int cv$max_timeStep$var136 = 0;
		for(int sample = 0; sample < length$metric.length; sample += 1)
			cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, length$metric[sample]);
		int cv$max_sample = length$metric.length;
		int cv$threadCount = threadCount();
		guard$sample123gaussian156$global = new boolean[cv$threadCount][][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			guard$sample123gaussian156$global[cv$index] = new boolean[cv$max_sample][cv$max_timeStep$var136];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		if(!fixedFlag$sample19)
			initialStateDistribution = new double[noStates];
		if(!fixedFlag$sample32) {
			m = new double[noStates][];
			for(int var31 = 0; var31 < noStates; var31 += 1)
				m[var31] = new double[noStates];
		}
		if((!fixedFlag$sample104 || !fixedFlag$sample123)) {
			st = new int[length$metric.length][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				st[sample] = new int[length$metric[sample]];
		}
		metric_g = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			metric_g[sample] = new double[length$metric[sample]];
		metric_valid_g = new boolean[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			metric_valid_g[sample] = new boolean[length$metric[sample]];
		if(!fixedFlag$sample52)
			metric_mean = new double[noStates];
		if(!fixedFlag$sample68)
			metric_var = new double[noStates];
		if(!fixedFlag$sample84)
			metric_valid_bias = new double[noStates];
		var151 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			var151[sample] = new double[length$metric[sample]];
		distribution$sample104 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			distribution$sample104[sample] = new double[noStates];
		distribution$sample123 = new double[length$metric.length][][];
		for(int sample = 0; sample < length$metric.length; sample += 1) {
			double[][] subarray$0 = new double[(length$metric[sample] - 1)][];
			distribution$sample123[sample] = subarray$0;
			for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
				subarray$0[(timeStep$var113 - 1)] = new double[noStates];
		}
		constrainedFlag$sample32 = new boolean[noStates];
		constrainedFlag$sample123 = new boolean[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			constrainedFlag$sample123[sample] = new boolean[(length$metric[sample] - 1)];
		constrainedFlag$sample104 = new boolean[length$metric.length];
		constrainedFlag$sample84 = new boolean[noStates];
		constrainedFlag$sample68 = new boolean[noStates];
		constrainedFlag$sample52 = new boolean[noStates];
		logProbability$sample104 = new double[length$metric.length];
		logProbability$sample123 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample123[sample] = new double[(length$metric[sample] - 1)];
		logProbability$sample145 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample145[sample] = new double[length$metric[sample]];
		logProbability$sample157 = new double[length$metric.length][];
		for(int sample = 0; sample < length$metric.length; sample += 1)
			logProbability$sample157[sample] = new double[length$metric[sample]];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		if(!fixedFlag$sample52)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		if(!fixedFlag$sample68)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int threadID$sample = threadID$index$sample;
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var136, int forEnd$timeStep$var136, int threadID$timeStep$var136, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int timeStep$var136 = forStart$timeStep$var136; timeStep$var136 < forEnd$timeStep$var136; timeStep$var136 += 1) {
										metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var136]]);
										if(metric_valid_1d[timeStep$var136]) {
											if(!fixedFlag$sample157)
												var151[sample][timeStep$var136] = ((Math.sqrt(metric_var[st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var136]]);
											metric_1d[timeStep$var136] = var151[sample][timeStep$var136];
										}
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		if(!fixedFlag$sample52)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		if(!fixedFlag$sample68)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample104) {
							double[] cv$distribution$sample104 = distribution$sample104[sample];
							for(int index$var101 = 0; index$var101 < noStates; index$var101 += 1)
								cv$distribution$sample104[index$var101] = (((0.0 <= initialStateDistribution[index$var101]) && (initialStateDistribution[index$var101] <= 1.0))?initialStateDistribution[index$var101]:0.0);
						}
						if(!fixedFlag$sample123) {
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
								double[] cv$distribution$sample123 = distribution$sample123[sample][(timeStep$var113 - 1)];
								for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
									cv$distribution$sample123[index$var120] = 0.0;
								if((1 == timeStep$var113)) {
									if(fixedFlag$sample104) {
										int var31 = st[sample][0];
										if(((0 <= var31) && (var31 < noStates))) {
											double[] var119 = m[st[sample][0]];
											for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
												cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0));
										}
									} else {
										for(int index$sample104$3 = 0; index$sample104$3 < noStates; index$sample104$3 += 1) {
											double cv$probabilitySample104Value4 = distribution$sample104[sample][index$sample104$3];
											double[] var119 = m[index$sample104$3];
											for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
												cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
										}
									}
								}
								int index$timeStep$11 = (timeStep$var113 - 1);
								if((1 <= index$timeStep$11)) {
									for(int index$sample123$12 = 0; index$sample123$12 < noStates; index$sample123$12 += 1) {
										double cv$probabilitySample123Value13 = distribution$sample123[sample][(index$timeStep$11 - 1)][index$sample123$12];
										double[] var119 = m[index$sample123$12];
										for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
											cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
									}
								}
								double cv$var120$sum = 0.0;
								for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
									cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
								for(int index$var120 = 0; index$var120 < noStates; index$var120 += 1)
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
							}
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		if(!fixedFlag$sample52)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		if(!fixedFlag$sample68)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$index$sample, int forEnd$index$sample, int threadID$index$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$sample = forStart$index$sample; index$sample < forEnd$index$sample; index$sample += 1) {
						int sample = index$sample;
						int threadID$sample = threadID$index$sample;
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
						boolean[] metric_valid_1d = metric_valid_g[sample];
						double[] metric_1d = metric_g[sample];
						parallelFor(RNG$1, 0, length$metric[sample], 1,
							(int forStart$timeStep$var136, int forEnd$timeStep$var136, int threadID$timeStep$var136, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int timeStep$var136 = forStart$timeStep$var136; timeStep$var136 < forEnd$timeStep$var136; timeStep$var136 += 1) {
										metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(RNG$2, metric_valid_bias[st[sample][timeStep$var136]]);
										if(metric_valid_1d[timeStep$var136]) {
											if(!fixedFlag$sample157)
												var151[sample][timeStep$var136] = ((Math.sqrt(metric_var[st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(RNG$2)) + metric_mean[st[sample][timeStep$var136]]);
											metric_1d[timeStep$var136] = var151[sample][timeStep$var136];
										}
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		if(!fixedFlag$sample52)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		if(!fixedFlag$sample68)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, initialStateDistribution);
		if(!fixedFlag$sample32)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var31]);
				}
			);

		if(!fixedFlag$sample52)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
							metric_mean[var50] = (DistributionSampling.sampleUniform(RNG$1) * 100.0);
				}
			);

		if(!fixedFlag$sample68)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
							metric_var[var66] = DistributionSampling.sampleInverseGamma(RNG$1, 1.0, 1.0);
				}
			);

		if(!fixedFlag$sample84)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
							metric_valid_bias[var82] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		parallelFor(RNG$, 0, noSamples, 1,
			(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
						if(!fixedFlag$sample104)
							st[sample][0] = DistributionSampling.sampleCategorical(RNG$1, initialStateDistribution, noStates);
						if(!fixedFlag$sample123) {
							int[] var114 = st[sample];
							for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
								var114[timeStep$var113] = DistributionSampling.sampleCategorical(RNG$1, m[st[sample][(timeStep$var113 - 1)]], noStates);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample19)
				sample19();
			if(!fixedFlag$sample32)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample32(var31, threadID$var31, RNG$1);
					}
				);

			if(!fixedFlag$sample52)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
								sample52(var50, threadID$var50, RNG$1);
					}
				);

			if(!fixedFlag$sample68)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
								sample68(var66, threadID$var66, RNG$1);
					}
				);

			if(!fixedFlag$sample84)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
								sample84(var82, threadID$var82, RNG$1);
					}
				);

			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!fixedFlag$sample104)
								sample104(sample, threadID$sample, RNG$1);
							if(!fixedFlag$sample123) {
								for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
									sample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, noSamples, 1,
				(int forStart$sample, int forEnd$sample, int threadID$sample, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int sample = forStart$sample; sample < forEnd$sample; sample += 1) {
							if(!fixedFlag$sample123) {
								for(int timeStep$var113 = (length$metric[sample] - 1); timeStep$var113 >= 1; timeStep$var113 -= 1)
									sample123(sample, timeStep$var113, threadID$sample, RNG$1);
							}
							if(!fixedFlag$sample104)
								sample104(sample, threadID$sample, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample84)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var82, int forEnd$var82, int threadID$var82, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var82 = forStart$var82; var82 < forEnd$var82; var82 += 1)
								sample84(var82, threadID$var82, RNG$1);
					}
				);

			if(!fixedFlag$sample68)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var66, int forEnd$var66, int threadID$var66, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var66 = forStart$var66; var66 < forEnd$var66; var66 += 1)
								sample68(var66, threadID$var66, RNG$1);
					}
				);

			if(!fixedFlag$sample52)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var50, int forEnd$var50, int threadID$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var50 = forStart$var50; var50 < forEnd$var50; var50 += 1)
								sample52(var50, threadID$var50, RNG$1);
					}
				);

			if(!fixedFlag$sample32)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample32(var31, threadID$var31, RNG$1);
					}
				);

			if(!fixedFlag$sample19)
				sample19();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		if(!fixedProbFlag$sample19)
			logProbability$initialStateDistribution = Double.NaN;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample32)
			logProbability$var32 = Double.NaN;
		logProbability$metric_mean = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var51 = Double.NaN;
		logProbability$metric_var = 0.0;
		if(!fixedProbFlag$sample68)
			logProbability$var67 = Double.NaN;
		logProbability$metric_valid_bias = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$var83 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample104[sample] = Double.NaN;
		}
		if(!fixedProbFlag$sample123) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1)
					logProbability$sample123[sample][(timeStep$var113 - 1)] = Double.NaN;
			}
		}
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample145) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					logProbability$sample145[sample][timeStep$var136] = Double.NaN;
			}
		}
		logProbability$var151 = 0.0;
		logProbability$metric_g = 0.0;
		if(!fixedProbFlag$sample157) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1)
					logProbability$sample157[sample][timeStep$var136] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		noSamples = length$metric.length;
		for(int var15 = 0; var15 < noStates; var15 += 1)
			v[var15] = 0.1;
		for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
			constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
		for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
			boolean[] cv$constrainedFlag$sample123$1 = constrainedFlag$sample123[index$constrainedFlag$sample123$1];
			for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
				cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
		}
		for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
			constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
		for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
			constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample145();
		logProbabilityDistribution$sample157();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityValue$sample104();
		logProbabilityValue$sample123();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void propogateObservedValues() {
		fixedFlag$sample157 = false;
		int cv$length1 = metric_valid_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = metric_valid[cv$index1];
			boolean[] cv$target2 = metric_valid_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int sample = (noSamples - 1); sample >= 0; sample -= 1) {
			for(int timeStep$var136 = (length$metric[sample] - 1); timeStep$var136 >= 0; timeStep$var136 -= 1) {
				metric_g[sample][timeStep$var136] = metric[sample][timeStep$var136];
				if(metric_valid_g[sample][timeStep$var136])
					var151[sample][timeStep$var136] = metric_g[sample][timeStep$var136];
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
		     + "model HMMMetrics2(\n"
		     + "               double[][] metric,\n"
		     + "               boolean[][] metric_valid, \n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "\n"
		     + "    //Allocate space for states\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "\n"
		     + "    //Allocate space for generated metrics \n"
		     + "    double[][] metric_g = new double[noSamples][];\n"
		     + "    boolean[][] metric_valid_g = new boolean[noSamples][];\n"
		     + "    \n"
		     + "    //Calculate priors for the metric\n"
		     + "    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n"
		     + "    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n"
		     + "    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric value\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        //Calculate all the state transitions\n"
		     + "        int streamLength = metric[sample].length;\n"
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
		     + "        \n"
		     + "        //Calculate metric values\n"
		     + "        double[] metric_1d = new double[streamLength];\n"
		     + "        metric_g[sample] = metric_1d;\n"
		     + "\n"
		     + "        boolean[] metric_valid_1d = new boolean[streamLength];\n"
		     + "        metric_valid_g[sample] = metric_valid_1d;\n"
		     + "\n"
		     + "        //Generate values.\n"
		     + "        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "            int currentState = st[sample][timeStep];\n"
		     + "            \n"
		     + "            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n"
		     + "            if(metric_valid_1d[timeStep])\n"
		     + "                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n"
		     + "            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n"
		     + "            metric_1d[timeStep].observe(metric[sample][timeStep]);\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "}";
	}
}