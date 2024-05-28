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
		fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedProbFlag$sample117);
		fixedProbFlag$sample136 = (fixedFlag$sample117 && fixedProbFlag$sample136);
		fixedProbFlag$sample158 = (fixedFlag$sample117 && fixedProbFlag$sample158);
		fixedProbFlag$sample170 = (fixedFlag$sample117 && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample136() {
		return fixedFlag$sample136;
	}

	@Override
	public final void set$fixedFlag$sample136(boolean cv$value) {
		fixedFlag$sample136 = cv$value;
		fixedProbFlag$sample136 = (fixedFlag$sample136 && fixedProbFlag$sample136);
		fixedProbFlag$sample158 = (fixedFlag$sample136 && fixedProbFlag$sample158);
		fixedProbFlag$sample170 = (fixedFlag$sample136 && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample158() {
		return fixedFlag$sample158;
	}

	@Override
	public final void set$fixedFlag$sample158(boolean cv$value) {
		fixedFlag$sample158 = cv$value;
		fixedProbFlag$sample158 = (fixedFlag$sample158 && fixedProbFlag$sample158);
	}

	@Override
	public final boolean get$fixedFlag$sample170() {
		return fixedFlag$sample170;
	}

	@Override
	public final void set$fixedFlag$sample170(boolean cv$value) {
		fixedFlag$sample170 = cv$value;
		fixedProbFlag$sample170 = (fixedFlag$sample170 && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample30() {
		return fixedFlag$sample30;
	}

	@Override
	public final void set$fixedFlag$sample30(boolean cv$value) {
		fixedFlag$sample30 = cv$value;
		fixedProbFlag$sample30 = (fixedFlag$sample30 && fixedProbFlag$sample30);
		fixedProbFlag$sample117 = (fixedFlag$sample30 && fixedProbFlag$sample117);
	}

	@Override
	public final boolean get$fixedFlag$sample43() {
		return fixedFlag$sample43;
	}

	@Override
	public final void set$fixedFlag$sample43(boolean cv$value) {
		fixedFlag$sample43 = cv$value;
		fixedProbFlag$sample43 = (fixedFlag$sample43 && fixedProbFlag$sample43);
		fixedProbFlag$sample136 = (fixedFlag$sample43 && fixedProbFlag$sample136);
	}

	@Override
	public final boolean get$fixedFlag$sample63() {
		return fixedFlag$sample63;
	}

	@Override
	public final void set$fixedFlag$sample63(boolean cv$value) {
		fixedFlag$sample63 = cv$value;
		fixedProbFlag$sample63 = (fixedFlag$sample63 && fixedProbFlag$sample63);
		fixedProbFlag$sample170 = (fixedFlag$sample63 && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample79() {
		return fixedFlag$sample79;
	}

	@Override
	public final void set$fixedFlag$sample79(boolean cv$value) {
		fixedFlag$sample79 = cv$value;
		fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedProbFlag$sample79);
		fixedProbFlag$sample170 = (fixedFlag$sample79 && fixedProbFlag$sample170);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		fixedFlag$sample95 = cv$value;
		fixedProbFlag$sample95 = (fixedFlag$sample95 && fixedProbFlag$sample95);
		fixedProbFlag$sample158 = (fixedFlag$sample95 && fixedProbFlag$sample158);
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
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$sample$1 = sample;
					{
						int cv$sampleValue = st[sample][0];
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
					logProbability$var110[((sample - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample117[((sample - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample117)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample117)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedFlag$sample30);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample117[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var110[((sample - 0) / 1)] = cv$rvAccumulator;
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
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$timeStep$1 = timeStep$var122;
						int index$sample$2 = sample;
						{
							int cv$sampleValue = st[sample][timeStep$var122];
							if(fixedFlag$sample117) {
								for(int index$sample$4_1 = 0; index$sample$4_1 < noSamples; index$sample$4_1 += 1) {
									if((index$sample$4_1 == sample)) {
										if((0 == (timeStep$var122 - 1))) {
											for(int var39 = 0; var39 < noStates; var39 += 1) {
												if((var39 == st[sample][(timeStep$var122 - 1)])) {
													{
														double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
										for(int index$sample117$6 = 0; index$sample117$6 < noStates; index$sample117$6 += 1) {
											int distributionTempVariable$var111$8 = index$sample117$6;
											double cv$probabilitySample117Value7 = (1.0 * distribution$sample117[((index$sample$5 - 0) / 1)][index$sample117$6]);
											int traceTempVariable$var127$9_1 = distributionTempVariable$var111$8;
											if((index$sample$5 == sample)) {
												if((0 == (timeStep$var122 - 1))) {
													for(int var39 = 0; var39 < noStates; var39 += 1) {
														if((var39 == traceTempVariable$var127$9_1)) {
															{
																double[] var128 = m[traceTempVariable$var127$9_1];
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
												}
											}
										}
									}
								}
							}
							if((index$sample$2 == sample)) {
								if((index$timeStep$1 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == st[sample][(timeStep$var122 - 1)])) {
											{
												double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							if(fixedFlag$sample136) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var122 - 1))) {
												for(int var39 = 0; var39 < noStates; var39 += 1) {
													if((var39 == st[sample][(timeStep$var122 - 1)])) {
														{
															double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
										if(!((index$sample$14 == index$sample$2) && (index$timeStep$15 == index$timeStep$1))) {
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var130$18 = index$sample136$16;
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var127$19_1 = distributionTempVariable$var130$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var122 - 1))) {
														for(int var39 = 0; var39 < noStates; var39 += 1) {
															if((var39 == traceTempVariable$var127$19_1)) {
																{
																	double[] var128 = m[traceTempVariable$var127$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample136Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value17);
																}
															}
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
						logProbability$var129[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample136)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample136)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample43) && fixedFlag$sample117);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var129[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = cv$rvAccumulator;
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
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var145];
						if(fixedFlag$sample117) {
							for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
								if((index$sample$2_1 == sample)) {
									if((0 == timeStep$var145)) {
										for(int var90 = 0; var90 < noStates; var90 += 1) {
											if((var90 == st[sample][timeStep$var145])) {
												{
													double var148 = metric_valid_bias[st[sample][timeStep$var145]];
													double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
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
							for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
								if(true) {
									for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
										int distributionTempVariable$var111$6 = index$sample117$4;
										double cv$probabilitySample117Value5 = (1.0 * distribution$sample117[((index$sample$3 - 0) / 1)][index$sample117$4]);
										int traceTempVariable$currentState$7_1 = distributionTempVariable$var111$6;
										if((index$sample$3 == sample)) {
											if((0 == timeStep$var145)) {
												for(int var90 = 0; var90 < noStates; var90 += 1) {
													if((var90 == traceTempVariable$currentState$7_1)) {
														{
															double var148 = metric_valid_bias[traceTempVariable$currentState$7_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample117Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
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
											}
										}
									}
								}
							}
						}
						if(fixedFlag$sample136) {
							for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$10_1]; timeStep$var122 += 1) {
									if((index$sample$10_1 == sample)) {
										if((timeStep$var122 == timeStep$var145)) {
											for(int var90 = 0; var90 < noStates; var90 += 1) {
												if((var90 == st[sample][timeStep$var145])) {
													{
														double var148 = metric_valid_bias[st[sample][timeStep$var145]];
														double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
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
							for(int index$sample$11 = 0; index$sample$11 < noSamples; index$sample$11 += 1) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$11]; timeStep$var122 += 1) {
									if(true) {
										for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
											int distributionTempVariable$var130$15 = index$sample136$13;
											double cv$probabilitySample136Value14 = (1.0 * distribution$sample136[((index$sample$11 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$13]);
											int traceTempVariable$currentState$16_1 = distributionTempVariable$var130$15;
											if((index$sample$11 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													for(int var90 = 0; var90 < noStates; var90 += 1) {
														if((var90 == traceTempVariable$currentState$16_1)) {
															{
																double var148 = metric_valid_bias[traceTempVariable$currentState$16_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample136Value14) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
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
					logProbability$var149[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$metric_valid_g = false;
					boolean cv$guard$metric_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
					{
						for(int index$timeStep$24_1 = 0; index$timeStep$24_1 < length$metric[sample]; index$timeStep$24_1 += 1) {
							if((timeStep$var145 == index$timeStep$24_1)) {
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
							}
						}
					}
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
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var149[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$metric_valid_g = false;
					boolean cv$guard$metric_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
					{
						for(int index$timeStep$26_1 = 0; index$timeStep$26_1 < length$metric[sample]; index$timeStep$26_1 += 1) {
							if((timeStep$var145 == index$timeStep$26_1)) {
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
							}
						}
					}
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
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							if(metric_valid_g[sample][timeStep$var145]) {
								double cv$sampleValue = metric_g[sample][timeStep$var145];
								if(fixedFlag$sample117) {
									for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
										if((index$sample$2_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													for(int var58 = 0; var58 < noStates; var58 += 1) {
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var58 == st[sample][timeStep$var145])) {
																for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
																	if((index$sample$10_1 == sample)) {
																		if((0 == timeStep$var145)) {
																			if(metric_valid_g[sample][timeStep$var145]) {
																				for(int var74 = 0; var74 < noStates; var74 += 1) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						if((var74 == st[sample][timeStep$var145])) {
																							{
																								double var157 = metric_mean[st[sample][timeStep$var145]];
																								double var158 = metric_var[st[sample][timeStep$var145]];
																								double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
								} else {
									for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
										if(true) {
											for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
												int distributionTempVariable$var111$6 = index$sample117$4;
												double cv$probabilitySample117Value5 = (1.0 * distribution$sample117[((index$sample$3 - 0) / 1)][index$sample117$4]);
												int traceTempVariable$currentState$7_1 = distributionTempVariable$var111$6;
												if((index$sample$3 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$7_1)) {
																		int traceTempVariable$currentState$11_1 = distributionTempVariable$var111$6;
																		if((index$sample$3 == sample)) {
																			if((0 == timeStep$var145)) {
																				if(metric_valid_g[sample][timeStep$var145]) {
																					for(int var74 = 0; var74 < noStates; var74 += 1) {
																						if(metric_valid_g[sample][timeStep$var145]) {
																							if((var74 == traceTempVariable$currentState$11_1)) {
																								{
																									double var157 = metric_mean[traceTempVariable$currentState$11_1];
																									double var158 = metric_var[traceTempVariable$currentState$11_1];
																									double cv$weightedProbability = (Math.log(cv$probabilitySample117Value5) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
																					}
																				}
																			}
																		}
																		for(int index$sample$12 = 0; index$sample$12 < noSamples; index$sample$12 += 1) {
																			if(!(index$sample$12 == index$sample$3)) {
																				for(int index$sample117$13 = 0; index$sample117$13 < noStates; index$sample117$13 += 1) {
																					int distributionTempVariable$var111$15 = index$sample117$13;
																					double cv$probabilitySample117Value14 = (cv$probabilitySample117Value5 * distribution$sample117[((index$sample$12 - 0) / 1)][index$sample117$13]);
																					int traceTempVariable$currentState$16_1 = distributionTempVariable$var111$15;
																					if((index$sample$12 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$16_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$16_1];
																												double var158 = metric_var[traceTempVariable$currentState$16_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample117Value14) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value14);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample117) {
									for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
										if((index$sample$20_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													for(int var58 = 0; var58 < noStates; var58 += 1) {
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var58 == st[sample][timeStep$var145])) {
																if(fixedFlag$sample136) {
																	for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																		for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
																			if((index$sample$28_1 == sample)) {
																				if((timeStep$var122 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										double var157 = metric_mean[st[sample][timeStep$var145]];
																										double var158 = metric_var[st[sample][timeStep$var145]];
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
																	for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																		for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
																			if(true) {
																				for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
																					int distributionTempVariable$var130$33 = index$sample136$31;
																					double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
																					int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
																					if((index$sample$29 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$34_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$34_1];
																												double var158 = metric_var[traceTempVariable$currentState$34_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value32);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
									for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
										if(true) {
											for(int index$sample117$22 = 0; index$sample117$22 < noStates; index$sample117$22 += 1) {
												int distributionTempVariable$var111$24 = index$sample117$22;
												double cv$probabilitySample117Value23 = (1.0 * distribution$sample117[((index$sample$21 - 0) / 1)][index$sample117$22]);
												int traceTempVariable$currentState$25_1 = distributionTempVariable$var111$24;
												if((index$sample$21 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$25_1)) {
																		if(fixedFlag$sample136) {
																			for(int index$sample$35_1 = 0; index$sample$35_1 < noSamples; index$sample$35_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$35_1]; timeStep$var122 += 1) {
																					if((index$sample$35_1 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$25_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$25_1];
																												double var158 = metric_var[traceTempVariable$currentState$25_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value23);
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
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$36]; timeStep$var122 += 1) {
																					if(true) {
																						for(int index$sample136$38 = 0; index$sample136$38 < noStates; index$sample136$38 += 1) {
																							int distributionTempVariable$var130$40 = index$sample136$38;
																							double cv$probabilitySample136Value39 = (cv$probabilitySample117Value23 * distribution$sample136[((index$sample$36 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$38]);
																							int traceTempVariable$currentState$41_1 = distributionTempVariable$var130$40;
																							if((index$sample$36 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$41_1)) {
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$41_1];
																														double var158 = metric_var[traceTempVariable$currentState$41_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample136Value39) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value39);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample136) {
									for(int index$sample$46_1 = 0; index$sample$46_1 < noSamples; index$sample$46_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$46_1]; timeStep$var122 += 1) {
											if((index$sample$46_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														for(int var58 = 0; var58 < noStates; var58 += 1) {
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var58 == st[sample][timeStep$var145])) {
																	for(int index$sample$55_1 = 0; index$sample$55_1 < noSamples; index$sample$55_1 += 1) {
																		for(int index$timeStep$55_2 = 1; index$timeStep$55_2 < length$metric[index$sample$55_1]; index$timeStep$55_2 += 1) {
																			if((index$sample$55_1 == sample)) {
																				if((index$timeStep$55_2 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										double var157 = metric_mean[st[sample][timeStep$var145]];
																										double var158 = metric_var[st[sample][timeStep$var145]];
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
								} else {
									for(int index$sample$47 = 0; index$sample$47 < noSamples; index$sample$47 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$47]; timeStep$var122 += 1) {
											if(true) {
												for(int index$sample136$49 = 0; index$sample136$49 < noStates; index$sample136$49 += 1) {
													int distributionTempVariable$var130$51 = index$sample136$49;
													double cv$probabilitySample136Value50 = (1.0 * distribution$sample136[((index$sample$47 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$49]);
													int traceTempVariable$currentState$52_1 = distributionTempVariable$var130$51;
													if((index$sample$47 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																for(int var58 = 0; var58 < noStates; var58 += 1) {
																	if(metric_valid_g[sample][timeStep$var145]) {
																		if((var58 == traceTempVariable$currentState$52_1)) {
																			int traceTempVariable$currentState$56_1 = distributionTempVariable$var130$51;
																			if((index$sample$47 == sample)) {
																				if((timeStep$var122 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == traceTempVariable$currentState$56_1)) {
																									{
																										double var157 = metric_mean[traceTempVariable$currentState$56_1];
																										double var158 = metric_var[traceTempVariable$currentState$56_1];
																										double cv$weightedProbability = (Math.log(cv$probabilitySample136Value50) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
																						}
																					}
																				}
																			}
																			for(int index$sample$57 = 0; index$sample$57 < noSamples; index$sample$57 += 1) {
																				for(int index$timeStep$58 = 1; index$timeStep$58 < length$metric[index$sample$57]; index$timeStep$58 += 1) {
																					if(!((index$sample$57 == index$sample$47) && (index$timeStep$58 == timeStep$var122))) {
																						for(int index$sample136$59 = 0; index$sample136$59 < noStates; index$sample136$59 += 1) {
																							int distributionTempVariable$var130$61 = index$sample136$59;
																							double cv$probabilitySample136Value60 = (cv$probabilitySample136Value50 * distribution$sample136[((index$sample$57 - 0) / 1)][((index$timeStep$58 - 1) / 1)][index$sample136$59]);
																							int traceTempVariable$currentState$62_1 = distributionTempVariable$var130$61;
																							if((index$sample$57 == sample)) {
																								if((index$timeStep$58 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$62_1)) {
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$62_1];
																														double var158 = metric_var[traceTempVariable$currentState$62_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample136Value60) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value60);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
								if(fixedFlag$sample136) {
									for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$66_1]; timeStep$var122 += 1) {
											if((index$sample$66_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														for(int var58 = 0; var58 < noStates; var58 += 1) {
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var58 == st[sample][timeStep$var145])) {
																	if(fixedFlag$sample117) {
																		for(int index$sample$75_1 = 0; index$sample$75_1 < noSamples; index$sample$75_1 += 1) {
																			if((index$sample$75_1 == sample)) {
																				if((0 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										double var157 = metric_mean[st[sample][timeStep$var145]];
																										double var158 = metric_var[st[sample][timeStep$var145]];
																										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
																		for(int index$sample$76 = 0; index$sample$76 < noSamples; index$sample$76 += 1) {
																			if(true) {
																				for(int index$sample117$77 = 0; index$sample117$77 < noStates; index$sample117$77 += 1) {
																					int distributionTempVariable$var111$79 = index$sample117$77;
																					double cv$probabilitySample117Value78 = (1.0 * distribution$sample117[((index$sample$76 - 0) / 1)][index$sample117$77]);
																					int traceTempVariable$currentState$80_1 = distributionTempVariable$var111$79;
																					if((index$sample$76 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$80_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$80_1];
																												double var158 = metric_var[traceTempVariable$currentState$80_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample117Value78) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value78);
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
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
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$67]; timeStep$var122 += 1) {
											if(true) {
												for(int index$sample136$69 = 0; index$sample136$69 < noStates; index$sample136$69 += 1) {
													int distributionTempVariable$var130$71 = index$sample136$69;
													double cv$probabilitySample136Value70 = (1.0 * distribution$sample136[((index$sample$67 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$69]);
													int traceTempVariable$currentState$72_1 = distributionTempVariable$var130$71;
													if((index$sample$67 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																for(int var58 = 0; var58 < noStates; var58 += 1) {
																	if(metric_valid_g[sample][timeStep$var145]) {
																		if((var58 == traceTempVariable$currentState$72_1)) {
																			if(fixedFlag$sample117) {
																				for(int index$sample$81_1 = 0; index$sample$81_1 < noSamples; index$sample$81_1 += 1) {
																					if((index$sample$81_1 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$72_1)) {
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$72_1];
																												double var158 = metric_var[traceTempVariable$currentState$72_1];
																												double cv$weightedProbability = (Math.log(cv$probabilitySample136Value70) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																												if((cv$weightedProbability < cv$distributionAccumulator))
																													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																												else {
																													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																														cv$distributionAccumulator = cv$weightedProbability;
																													else
																														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																												}
																												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample136Value70);
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
																						for(int index$sample117$83 = 0; index$sample117$83 < noStates; index$sample117$83 += 1) {
																							int distributionTempVariable$var111$85 = index$sample117$83;
																							double cv$probabilitySample117Value84 = (cv$probabilitySample136Value70 * distribution$sample117[((index$sample$82 - 0) / 1)][index$sample117$83]);
																							int traceTempVariable$currentState$86_1 = distributionTempVariable$var111$85;
																							if((index$sample$82 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$86_1)) {
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$86_1];
																														double var158 = metric_var[traceTempVariable$currentState$86_1];
																														double cv$weightedProbability = (Math.log(cv$probabilitySample117Value84) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
																														if((cv$weightedProbability < cv$distributionAccumulator))
																															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																														else {
																															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																																cv$distributionAccumulator = cv$weightedProbability;
																															else
																																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																														}
																														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample117Value84);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
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
						logProbability$var159[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
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
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var159[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
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
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$sample$1 = sample;
				{
					int cv$sampleValue = st[sample][0];
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
				logProbability$var110[((sample - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample117[((sample - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample117)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample117 = (fixedFlag$sample117 && fixedFlag$sample30);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < noSamples; sample += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample117[((sample - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var110[((sample - 0) / 1)] = cv$rvAccumulator;
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
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$timeStep$1 = timeStep$var122;
					int index$sample$2 = sample;
					{
						int cv$sampleValue = st[sample][timeStep$var122];
						{
							{
								double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var128.length))?Math.log(var128[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var129[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = cv$sampleProbability;
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
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var129[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = cv$rvAccumulator;
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
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						boolean cv$sampleValue = metric_valid_g[sample][timeStep$var145];
						{
							{
								double var148 = metric_valid_bias[st[sample][timeStep$var145]];
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var148));
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
					logProbability$var149[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
					boolean cv$guard$metric_valid_g = false;
					boolean cv$guard$metric_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleProbability);
						}
					}
					{
						for(int index$timeStep$4_1 = 0; index$timeStep$4_1 < length$metric[sample]; index$timeStep$4_1 += 1) {
							if((timeStep$var145 == index$timeStep$4_1)) {
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
								}
							}
						}
					}
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
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var149[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$rvAccumulator;
					boolean cv$guard$metric_valid_g = false;
					boolean cv$guard$metric_g = false;
					{
						if(!cv$guard$metric_valid_g) {
							cv$guard$metric_valid_g = true;
							logProbability$metric_valid_g = (logProbability$metric_valid_g + cv$sampleValue);
						}
					}
					{
						for(int index$timeStep$6_1 = 0; index$timeStep$6_1 < length$metric[sample]; index$timeStep$6_1 += 1) {
							if((timeStep$var145 == index$timeStep$6_1)) {
								if(!cv$guard$metric_g) {
									cv$guard$metric_g = true;
									logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
								}
							}
						}
					}
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
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							if(metric_valid_g[sample][timeStep$var145]) {
								double cv$sampleValue = metric_g[sample][timeStep$var145];
								{
									{
										double var157 = metric_mean[st[sample][timeStep$var145]];
										double var158 = metric_var[st[sample][timeStep$var145]];
										double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var157) / Math.sqrt(var158))) - (0.5 * Math.log(var158))));
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						double cv$sampleProbability = cv$distributionAccumulator;
						cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
						cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
						logProbability$var159[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleAccumulator;
						logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$sampleProbability;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleProbability);
							}
						}
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
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
						logProbability$var159[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = cv$rvAccumulator;
						boolean cv$guard$metric_g = false;
						{
							if(!cv$guard$metric_g) {
								cv$guard$metric_g = true;
								logProbability$metric_g = (logProbability$metric_g + cv$sampleValue);
							}
						}
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
			logProbability$var26 = cv$sampleAccumulator;
			logProbability$initialStateDistribution = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample30 = fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialStateDistribution;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var26 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample30)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample43() {
		if(!fixedProbFlag$sample43) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var39 = 0; var39 < noStates; var39 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var39];
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
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$var40 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample43 = fixedFlag$sample43;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var40;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var28 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample43)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample63() {
		if(!fixedProbFlag$sample63) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var58 = 0; var58 < noStates; var58 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_mean[var58];
					{
						{
							double var45 = 0.0;
							double var46 = 100.0;
							double cv$weightedProbability = (Math.log(1.0) + (((var45 <= cv$sampleValue) && (cv$sampleValue <= var46))?(-Math.log((var46 - var45))):Double.NEGATIVE_INFINITY));
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
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$var59 = cv$sampleAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample63 = fixedFlag$sample63;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var59;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var47 = cv$rvAccumulator;
			logProbability$metric_mean = (logProbability$metric_mean + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample63)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var74 = 0; var74 < noStates; var74 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_var[var74];
					{
						{
							double var61 = 1.0;
							double var62 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$sampleValue, var61, var62));
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
			logProbability$var63 = cv$sampleAccumulator;
			logProbability$var75 = cv$sampleAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample79 = fixedFlag$sample79;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var75;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var63 = cv$rvAccumulator;
			logProbability$metric_var = (logProbability$metric_var + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample79)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var90 = 0; var90 < noStates; var90 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = metric_valid_bias[var90];
					{
						{
							double var77 = 1.0;
							double var78 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var77, var78));
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
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$var91 = cv$sampleAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample95 = fixedFlag$sample95;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var91;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			logProbability$metric_valid_bias = (logProbability$metric_valid_bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample117(int sample) {
		int cv$noStates = 0;
		int index$sample$1 = sample;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var111$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$sample$2 = sample;
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
						int traceTempVariable$var127$3_1 = cv$currentValue;
						for(int index$sample$3_2 = 0; index$sample$3_2 < noSamples; index$sample$3_2 += 1) {
							if((sample == index$sample$3_2)) {
								for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$3_2]; timeStep$var122 += 1) {
									if((0 == (timeStep$var122 - 1))) {
										if(fixedFlag$sample136) {
											{
												int index$timeStep$5 = timeStep$var122;
												int index$sample$6 = index$sample$3_2;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var39 = 0; var39 < noStates; var39 += 1) {
														if((var39 == traceTempVariable$var127$3_1)) {
															{
																{
																	double[] cv$temp$1$var128;
																	{
																		double[] var128 = m[traceTempVariable$var127$3_1];
																		cv$temp$1$var128 = var128;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[index$sample$3_2][timeStep$var122]) && (st[index$sample$3_2][timeStep$var122] < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[st[index$sample$3_2][timeStep$var122]]):Double.NEGATIVE_INFINITY)));
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
						for(int index$sample$9_2 = 0; index$sample$9_2 < noSamples; index$sample$9_2 += 1) {
							if((sample == index$sample$9_2)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$9_2]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var90 = 0; var90 < noStates; var90 += 1) {
													if((var90 == traceTempVariable$currentState$9_1)) {
														{
															{
																double cv$temp$2$var148;
																{
																	double var148 = metric_valid_bias[traceTempVariable$currentState$9_1];
																	cv$temp$2$var148 = var148;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$9_2][timeStep$var145], cv$temp$2$var148)));
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
				{
					{
						boolean[][] guard$sample117gaussian169 = guard$sample117gaussian169$global;
						for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
							if((sample == index$sample$13_1)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$13_1]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$13_1][timeStep$var145])
											guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int index$sample$14_1 = 0; index$sample$14_1 < noSamples; index$sample$14_1 += 1) {
							if((sample == index$sample$14_1)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$14_1]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$14_1][timeStep$var145])
											guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
									}
								}
							}
						}
						int traceTempVariable$currentState$15_1 = cv$currentValue;
						for(int index$sample$15_2 = 0; index$sample$15_2 < noSamples; index$sample$15_2 += 1) {
							if((sample == index$sample$15_2)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$15_2]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
											if(!guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
												guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
												{
													if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$15_1)) {
																		int traceTempVariable$currentState$20_1 = cv$currentValue;
																		if((index$sample$2 == index$sample$15_2)) {
																			if((0 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																					for(int var74 = 0; var74 < noStates; var74 += 1) {
																						if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																							if((var74 == traceTempVariable$currentState$20_1)) {
																								{
																									{
																										if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																											double cv$temp$3$var157;
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$20_1];
																												cv$temp$3$var157 = var157;
																											}
																											double cv$temp$4$var158;
																											{
																												double var158 = metric_var[traceTempVariable$currentState$20_1];
																												cv$temp$4$var158 = var158;
																											}
																											if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$3$var157) / Math.sqrt(cv$temp$4$var158))) - (0.5 * Math.log(cv$temp$4$var158)))));
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
																		for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
																			if(!(index$sample$21 == index$sample$2)) {
																				for(int index$sample117$22 = 0; index$sample117$22 < noStates; index$sample117$22 += 1) {
																					int distributionTempVariable$var111$24 = index$sample117$22;
																					double cv$probabilitySample117Value23 = (1.0 * distribution$sample117[((index$sample$21 - 0) / 1)][index$sample117$22]);
																					int traceTempVariable$currentState$25_1 = distributionTempVariable$var111$24;
																					if((index$sample$21 == index$sample$15_2)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$25_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																														double cv$temp$5$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$25_1];
																															cv$temp$5$var157 = var157;
																														}
																														double cv$temp$6$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$25_1];
																															cv$temp$6$var158 = var158;
																														}
																														if(((Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value23) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$5$var157) / Math.sqrt(cv$temp$6$var158))) - (0.5 * Math.log(cv$temp$6$var158)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value23);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															for(int var58 = 0; var58 < noStates; var58 += 1) {
																if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$15_1)) {
																		if(fixedFlag$sample136) {
																			for(int index$sample$29_1 = 0; index$sample$29_1 < noSamples; index$sample$29_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29_1]; timeStep$var122 += 1) {
																					if((index$sample$29_1 == index$sample$15_2)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$15_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																														double cv$temp$7$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$15_1];
																															cv$temp$7$var157 = var157;
																														}
																														double cv$temp$8$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$15_1];
																															cv$temp$8$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$7$var157) / Math.sqrt(cv$temp$8$var158))) - (0.5 * Math.log(cv$temp$8$var158)))));
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
																			for(int index$sample$30 = 0; index$sample$30 < noSamples; index$sample$30 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$30]; timeStep$var122 += 1) {
																					if(true) {
																						for(int index$sample136$32 = 0; index$sample136$32 < noStates; index$sample136$32 += 1) {
																							int distributionTempVariable$var130$34 = index$sample136$32;
																							double cv$probabilitySample136Value33 = (1.0 * distribution$sample136[((index$sample$30 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$32]);
																							int traceTempVariable$currentState$35_1 = distributionTempVariable$var130$34;
																							if((index$sample$30 == index$sample$15_2)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$35_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$15_2][timeStep$var145]) {
																																double cv$temp$9$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$35_1];
																																	cv$temp$9$var157 = var157;
																																}
																																double cv$temp$10$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$35_1];
																																	cv$temp$10$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value33) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$15_2][timeStep$var145] - cv$temp$9$var157) / Math.sqrt(cv$temp$10$var158))) - (0.5 * Math.log(cv$temp$10$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value33);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
						int traceTempVariable$currentState$16_1 = cv$currentValue;
						for(int index$sample$16_2 = 0; index$sample$16_2 < noSamples; index$sample$16_2 += 1) {
							if((sample == index$sample$16_2)) {
								for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$16_2]; timeStep$var145 += 1) {
									if((0 == timeStep$var145)) {
										if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
											if(!guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
												guard$sample117gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
												{
													if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															int traceTempVariable$currentState$38_1 = cv$currentValue;
															if((index$sample$2 == index$sample$16_2)) {
																if((0 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																		for(int var58 = 0; var58 < noStates; var58 += 1) {
																			if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																				if((var58 == traceTempVariable$currentState$38_1)) {
																					for(int var74 = 0; var74 < noStates; var74 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							if((var74 == traceTempVariable$currentState$38_1)) {
																								{
																									{
																										if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																											double cv$temp$11$var157;
																											{
																												double var157 = metric_mean[traceTempVariable$currentState$38_1];
																												cv$temp$11$var157 = var157;
																											}
																											double cv$temp$12$var158;
																											{
																												double var158 = metric_var[traceTempVariable$currentState$38_1];
																												cv$temp$12$var158 = var158;
																											}
																											if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$11$var157) / Math.sqrt(cv$temp$12$var158))) - (0.5 * Math.log(cv$temp$12$var158)))));
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
															for(int index$sample$39 = 0; index$sample$39 < noSamples; index$sample$39 += 1) {
																if(!(index$sample$39 == index$sample$2)) {
																	for(int index$sample117$40 = 0; index$sample117$40 < noStates; index$sample117$40 += 1) {
																		int distributionTempVariable$var111$42 = index$sample117$40;
																		double cv$probabilitySample117Value41 = (1.0 * distribution$sample117[((index$sample$39 - 0) / 1)][index$sample117$40]);
																		int traceTempVariable$currentState$43_1 = distributionTempVariable$var111$42;
																		if((index$sample$39 == index$sample$16_2)) {
																			if((0 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																					for(int var58 = 0; var58 < noStates; var58 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							if((var58 == traceTempVariable$currentState$43_1)) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$43_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																														double cv$temp$13$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$43_1];
																															cv$temp$13$var157 = var157;
																														}
																														double cv$temp$14$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$43_1];
																															cv$temp$14$var158 = var158;
																														}
																														if(((Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value41) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$13$var157) / Math.sqrt(cv$temp$14$var158))) - (0.5 * Math.log(cv$temp$14$var158)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value41);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
															if(fixedFlag$sample136) {
																for(int index$sample$48_1 = 0; index$sample$48_1 < noSamples; index$sample$48_1 += 1) {
																	for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$48_1]; timeStep$var122 += 1) {
																		if((index$sample$48_1 == index$sample$16_2)) {
																			if((timeStep$var122 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																					for(int var58 = 0; var58 < noStates; var58 += 1) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							if((var58 == traceTempVariable$currentState$16_1)) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$16_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																														double cv$temp$15$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$16_1];
																															cv$temp$15$var157 = var157;
																														}
																														double cv$temp$16$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$16_1];
																															cv$temp$16$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$15$var157) / Math.sqrt(cv$temp$16$var158))) - (0.5 * Math.log(cv$temp$16$var158)))));
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
															} else {
																for(int index$sample$49 = 0; index$sample$49 < noSamples; index$sample$49 += 1) {
																	for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$49]; timeStep$var122 += 1) {
																		if(true) {
																			for(int index$sample136$51 = 0; index$sample136$51 < noStates; index$sample136$51 += 1) {
																				int distributionTempVariable$var130$53 = index$sample136$51;
																				double cv$probabilitySample136Value52 = (1.0 * distribution$sample136[((index$sample$49 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$51]);
																				int traceTempVariable$currentState$54_1 = distributionTempVariable$var130$53;
																				if((index$sample$49 == index$sample$16_2)) {
																					if((timeStep$var122 == timeStep$var145)) {
																						if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$54_1)) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$54_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$16_2][timeStep$var145]) {
																																double cv$temp$17$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$54_1];
																																	cv$temp$17$var157 = var157;
																																}
																																double cv$temp$18$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$54_1];
																																	cv$temp$18$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value52) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$16_2][timeStep$var145] - cv$temp$17$var157) / Math.sqrt(cv$temp$18$var158))) - (0.5 * Math.log(cv$temp$18$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value52);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
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
					int traceTempVariable$var127$67_1 = cv$currentValue;
					for(int index$sample$67_2 = 0; index$sample$67_2 < noSamples; index$sample$67_2 += 1) {
						if((sample == index$sample$67_2)) {
							for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$67_2]; timeStep$var122 += 1) {
								if((0 == (timeStep$var122 - 1))) {
									if(!fixedFlag$sample136) {
										{
											int index$timeStep$69 = timeStep$var122;
											int index$sample$70 = index$sample$67_2;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var129;
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var39 = 0; var39 < noStates; var39 += 1) {
												if((var39 == traceTempVariable$var127$67_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$19$var128;
														{
															double[] var128 = m[traceTempVariable$var127$67_1];
															cv$temp$19$var128 = var128;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$19$var128);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample136[((index$sample$67_2 - 0) / 1)][((timeStep$var122 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample117[((sample - 0) / 1)];
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

	private final void sample136(int sample, int timeStep$var122) {
		int cv$noStates = 0;
		int index$timeStep$1 = timeStep$var122;
		int index$sample$2 = sample;
		if(fixedFlag$sample117) {
			for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
				if((index$sample$3_1 == sample)) {
					if((0 == (timeStep$var122 - 1))) {
						for(int var39 = 0; var39 < noStates; var39 += 1) {
							if((var39 == st[sample][(timeStep$var122 - 1)]))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
				if(true) {
					for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
						int distributionTempVariable$var111$7 = index$sample117$5;
						double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
						int traceTempVariable$var127$8_1 = distributionTempVariable$var111$7;
						if((index$sample$4 == sample)) {
							if((0 == (timeStep$var122 - 1))) {
								for(int var39 = 0; var39 < noStates; var39 += 1) {
									if((var39 == traceTempVariable$var127$8_1))
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		if((index$sample$2 == sample)) {
			if((index$timeStep$1 == (timeStep$var122 - 1))) {
				for(int var39 = 0; var39 < noStates; var39 += 1) {
					if((var39 == st[sample][(timeStep$var122 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample136) {
			for(int index$sample$12_1 = 0; index$sample$12_1 < noSamples; index$sample$12_1 += 1) {
				for(int index$timeStep$12_2 = 1; index$timeStep$12_2 < length$metric[index$sample$12_1]; index$timeStep$12_2 += 1) {
					if((index$sample$12_1 == sample)) {
						if((index$timeStep$12_2 == (timeStep$var122 - 1))) {
							for(int var39 = 0; var39 < noStates; var39 += 1) {
								if((var39 == st[sample][(timeStep$var122 - 1)]))
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$sample$13 = 0; index$sample$13 < noSamples; index$sample$13 += 1) {
				for(int index$timeStep$14 = 1; index$timeStep$14 < length$metric[index$sample$13]; index$timeStep$14 += 1) {
					if(!((index$sample$13 == index$sample$2) && (index$timeStep$14 == index$timeStep$1))) {
						for(int index$sample136$15 = 0; index$sample136$15 < noStates; index$sample136$15 += 1) {
							int distributionTempVariable$var130$17 = index$sample136$15;
							double cv$probabilitySample136Value16 = (1.0 * distribution$sample136[((index$sample$13 - 0) / 1)][((index$timeStep$14 - 1) / 1)][index$sample136$15]);
							int traceTempVariable$var127$18_1 = distributionTempVariable$var130$17;
							if((index$sample$13 == sample)) {
								if((index$timeStep$14 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == traceTempVariable$var127$18_1))
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var130$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$timeStep$22 = timeStep$var122;
			int index$sample$23 = sample;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample117) {
				for(int index$sample$24_1 = 0; index$sample$24_1 < noSamples; index$sample$24_1 += 1) {
					if((index$sample$24_1 == sample)) {
						if((0 == (timeStep$var122 - 1))) {
							for(int var39 = 0; var39 < noStates; var39 += 1) {
								if((var39 == st[sample][(timeStep$var122 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var128;
									{
										double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
										cv$temp$0$var128 = var128;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var128.length))?Math.log(cv$temp$0$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var127$41_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$currentState$45_1 = cv$currentValue;
											for(int index$sample$45_2 = 0; index$sample$45_2 < noSamples; index$sample$45_2 += 1) {
												if((sample == index$sample$45_2)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$45_2]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var90 = 0; var90 < noStates; var90 += 1) {
																		if((var90 == traceTempVariable$currentState$45_1)) {
																			{
																				{
																					double cv$temp$4$var148;
																					{
																						double var148 = metric_valid_bias[traceTempVariable$currentState$45_1];
																						cv$temp$4$var148 = var148;
																					}
																					if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$45_2][timeStep$var145], cv$temp$4$var148)));
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
									{
										{
											boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
											for(int index$sample$61_1 = 0; index$sample$61_1 < noSamples; index$sample$61_1 += 1) {
												if((sample == index$sample$61_1)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$61_1]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$61_1][timeStep$var145])
																guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
														}
													}
												}
											}
											for(int index$sample$65_1 = 0; index$sample$65_1 < noSamples; index$sample$65_1 += 1) {
												if((sample == index$sample$65_1)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$65_1]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$65_1][timeStep$var145])
																guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
														}
													}
												}
											}
											int traceTempVariable$currentState$69_1 = cv$currentValue;
											for(int index$sample$69_2 = 0; index$sample$69_2 < noSamples; index$sample$69_2 += 1) {
												if((sample == index$sample$69_2)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$69_2]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																	guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																	{
																		if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int var58 = 0; var58 < noStates; var58 += 1) {
																					if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																						if((var58 == traceTempVariable$currentState$69_1)) {
																							for(int index$sample$86_1 = 0; index$sample$86_1 < noSamples; index$sample$86_1 += 1) {
																								if((index$sample$86_1 == index$sample$69_2)) {
																									if((0 == timeStep$var145)) {
																										if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																											for(int var74 = 0; var74 < noStates; var74 += 1) {
																												if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																													if((var74 == traceTempVariable$currentState$69_1)) {
																														{
																															{
																																if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																	double cv$temp$8$var157;
																																	{
																																		double var157 = metric_mean[traceTempVariable$currentState$69_1];
																																		cv$temp$8$var157 = var157;
																																	}
																																	double cv$temp$9$var158;
																																	{
																																		double var158 = metric_var[traceTempVariable$currentState$69_1];
																																		cv$temp$9$var158 = var158;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))));
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
																				for(int var58 = 0; var58 < noStates; var58 += 1) {
																					if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																						if((var58 == traceTempVariable$currentState$69_1)) {
																							int traceTempVariable$currentState$89_1 = cv$currentValue;
																							if((index$sample$23 == index$sample$69_2)) {
																								if((index$timeStep$22 == timeStep$var145)) {
																									if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$89_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																double cv$temp$10$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$89_1];
																																	cv$temp$10$var157 = var157;
																																}
																																double cv$temp$11$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$89_1];
																																	cv$temp$11$var158 = var158;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))));
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
																							for(int index$sample$90 = 0; index$sample$90 < noSamples; index$sample$90 += 1) {
																								for(int index$timeStep$91 = 1; index$timeStep$91 < length$metric[index$sample$90]; index$timeStep$91 += 1) {
																									if(!((index$sample$90 == index$sample$23) && (index$timeStep$91 == index$timeStep$22))) {
																										for(int index$sample136$92 = 0; index$sample136$92 < noStates; index$sample136$92 += 1) {
																											int distributionTempVariable$var130$94 = index$sample136$92;
																											double cv$probabilitySample136Value93 = (1.0 * distribution$sample136[((index$sample$90 - 0) / 1)][((index$timeStep$91 - 1) / 1)][index$sample136$92]);
																											int traceTempVariable$currentState$95_1 = distributionTempVariable$var130$94;
																											if((index$sample$90 == index$sample$69_2)) {
																												if((index$timeStep$91 == timeStep$var145)) {
																													if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$95_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$69_2][timeStep$var145]) {
																																				double cv$temp$12$var157;
																																				{
																																					double var157 = metric_mean[traceTempVariable$currentState$95_1];
																																					cv$temp$12$var157 = var157;
																																				}
																																				double cv$temp$13$var158;
																																				{
																																					double var158 = metric_var[traceTempVariable$currentState$95_1];
																																					cv$temp$13$var158 = var158;
																																				}
																																				if(((Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value93) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$69_2][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value93);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
											int traceTempVariable$currentState$73_1 = cv$currentValue;
											for(int index$sample$73_2 = 0; index$sample$73_2 < noSamples; index$sample$73_2 += 1) {
												if((sample == index$sample$73_2)) {
													for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$73_2]; timeStep$var145 += 1) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																	guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																	{
																		if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				for(int index$sample$157_1 = 0; index$sample$157_1 < noSamples; index$sample$157_1 += 1) {
																					if((index$sample$157_1 == index$sample$73_2)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																										if((var58 == traceTempVariable$currentState$73_1)) {
																											for(int var74 = 0; var74 < noStates; var74 += 1) {
																												if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																													if((var74 == traceTempVariable$currentState$73_1)) {
																														{
																															{
																																if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																	double cv$temp$40$var157;
																																	{
																																		double var157 = metric_mean[traceTempVariable$currentState$73_1];
																																		cv$temp$40$var157 = var157;
																																	}
																																	double cv$temp$41$var158;
																																	{
																																		double var158 = metric_var[traceTempVariable$currentState$73_1];
																																		cv$temp$41$var158 = var158;
																																	}
																																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))) < cv$accumulatedConsumerProbabilities))
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																	else {
																																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158))));
																																		else
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$40$var157) / Math.sqrt(cv$temp$41$var158))) - (0.5 * Math.log(cv$temp$41$var158)))));
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
																				int traceTempVariable$currentState$160_1 = cv$currentValue;
																				if((index$sample$23 == index$sample$73_2)) {
																					if((index$timeStep$22 == timeStep$var145)) {
																						if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$160_1)) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$160_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																double cv$temp$42$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$160_1];
																																	cv$temp$42$var157 = var157;
																																}
																																double cv$temp$43$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$160_1];
																																	cv$temp$43$var158 = var158;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$42$var157) / Math.sqrt(cv$temp$43$var158))) - (0.5 * Math.log(cv$temp$43$var158)))));
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
																				for(int index$sample$161 = 0; index$sample$161 < noSamples; index$sample$161 += 1) {
																					for(int index$timeStep$162 = 1; index$timeStep$162 < length$metric[index$sample$161]; index$timeStep$162 += 1) {
																						if(!((index$sample$161 == index$sample$23) && (index$timeStep$162 == index$timeStep$22))) {
																							for(int index$sample136$163 = 0; index$sample136$163 < noStates; index$sample136$163 += 1) {
																								int distributionTempVariable$var130$165 = index$sample136$163;
																								double cv$probabilitySample136Value164 = (1.0 * distribution$sample136[((index$sample$161 - 0) / 1)][((index$timeStep$162 - 1) / 1)][index$sample136$163]);
																								int traceTempVariable$currentState$166_1 = distributionTempVariable$var130$165;
																								if((index$sample$161 == index$sample$73_2)) {
																									if((index$timeStep$162 == timeStep$var145)) {
																										if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																											for(int var58 = 0; var58 < noStates; var58 += 1) {
																												if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																													if((var58 == traceTempVariable$currentState$166_1)) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$166_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$73_2][timeStep$var145]) {
																																				double cv$temp$44$var157;
																																				{
																																					double var157 = metric_mean[traceTempVariable$currentState$166_1];
																																					cv$temp$44$var157 = var157;
																																				}
																																				double cv$temp$45$var158;
																																				{
																																					double var158 = metric_var[traceTempVariable$currentState$166_1];
																																					cv$temp$45$var158 = var158;
																																				}
																																				if(((Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value164) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$73_2][timeStep$var145] - cv$temp$44$var157) / Math.sqrt(cv$temp$45$var158))) - (0.5 * Math.log(cv$temp$45$var158)))));
																																				}
																																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value164);
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
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
						for(int index$sample117$26 = 0; index$sample117$26 < noStates; index$sample117$26 += 1) {
							int distributionTempVariable$var111$28 = index$sample117$26;
							double cv$probabilitySample117Value27 = (1.0 * distribution$sample117[((index$sample$25 - 0) / 1)][index$sample117$26]);
							int traceTempVariable$var127$29_1 = distributionTempVariable$var111$28;
							if((index$sample$25 == sample)) {
								if((0 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == traceTempVariable$var127$29_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample117Value27);
											double[] cv$temp$1$var128;
											{
												double[] var128 = m[traceTempVariable$var127$29_1];
												cv$temp$1$var128 = var128;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample117Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var128.length))?Math.log(cv$temp$1$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var127$42_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$46_1 = cv$currentValue;
													for(int index$sample$46_2 = 0; index$sample$46_2 < noSamples; index$sample$46_2 += 1) {
														if((sample == index$sample$46_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$46_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var90 = 0; var90 < noStates; var90 += 1) {
																				if((var90 == traceTempVariable$currentState$46_1)) {
																					{
																						{
																							double cv$temp$5$var148;
																							{
																								double var148 = metric_valid_bias[traceTempVariable$currentState$46_1];
																								cv$temp$5$var148 = var148;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$46_2][timeStep$var145], cv$temp$5$var148)));
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
											{
												{
													boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
													for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
														if((sample == index$sample$62_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$62_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$62_1][timeStep$var145])
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int index$sample$66_1 = 0; index$sample$66_1 < noSamples; index$sample$66_1 += 1) {
														if((sample == index$sample$66_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$66_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$66_1][timeStep$var145])
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$70_1 = cv$currentValue;
													for(int index$sample$70_2 = 0; index$sample$70_2 < noSamples; index$sample$70_2 += 1) {
														if((sample == index$sample$70_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$70_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			{
																				if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$70_1)) {
																									int traceTempVariable$currentState$99_1 = distributionTempVariable$var111$28;
																									if((index$sample$25 == index$sample$70_2)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$99_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																		double cv$temp$14$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$99_1];
																																			cv$temp$14$var157 = var157;
																																		}
																																		double cv$temp$15$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$99_1];
																																			cv$temp$15$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))));
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
																									for(int index$sample$100 = 0; index$sample$100 < noSamples; index$sample$100 += 1) {
																										if(!(index$sample$100 == index$sample$25)) {
																											for(int index$sample117$101 = 0; index$sample117$101 < noStates; index$sample117$101 += 1) {
																												int distributionTempVariable$var111$103 = index$sample117$101;
																												double cv$probabilitySample117Value102 = (1.0 * distribution$sample117[((index$sample$100 - 0) / 1)][index$sample117$101]);
																												int traceTempVariable$currentState$104_1 = distributionTempVariable$var111$103;
																												if((index$sample$100 == index$sample$70_2)) {
																													if((0 == timeStep$var145)) {
																														if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																															for(int var74 = 0; var74 < noStates; var74 += 1) {
																																if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																	if((var74 == traceTempVariable$currentState$104_1)) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																					double cv$temp$16$var157;
																																					{
																																						double var157 = metric_mean[traceTempVariable$currentState$104_1];
																																						cv$temp$16$var157 = var157;
																																					}
																																					double cv$temp$17$var158;
																																					{
																																						double var158 = metric_var[traceTempVariable$currentState$104_1];
																																						cv$temp$17$var158 = var158;
																																					}
																																					if(((Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value102) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value102);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$70_1)) {
																									int traceTempVariable$currentState$108_1 = cv$currentValue;
																									if((index$sample$23 == index$sample$70_2)) {
																										if((index$timeStep$22 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$108_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																		double cv$temp$18$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$108_1];
																																			cv$temp$18$var157 = var157;
																																		}
																																		double cv$temp$19$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$108_1];
																																			cv$temp$19$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))));
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
																									for(int index$sample$109 = 0; index$sample$109 < noSamples; index$sample$109 += 1) {
																										for(int index$timeStep$110 = 1; index$timeStep$110 < length$metric[index$sample$109]; index$timeStep$110 += 1) {
																											if(!((index$sample$109 == index$sample$23) && (index$timeStep$110 == index$timeStep$22))) {
																												for(int index$sample136$111 = 0; index$sample136$111 < noStates; index$sample136$111 += 1) {
																													int distributionTempVariable$var130$113 = index$sample136$111;
																													double cv$probabilitySample136Value112 = (1.0 * distribution$sample136[((index$sample$109 - 0) / 1)][((index$timeStep$110 - 1) / 1)][index$sample136$111]);
																													int traceTempVariable$currentState$114_1 = distributionTempVariable$var130$113;
																													if((index$sample$109 == index$sample$70_2)) {
																														if((index$timeStep$110 == timeStep$var145)) {
																															if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$114_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$70_2][timeStep$var145]) {
																																						double cv$temp$20$var157;
																																						{
																																							double var157 = metric_mean[traceTempVariable$currentState$114_1];
																																							cv$temp$20$var157 = var157;
																																						}
																																						double cv$temp$21$var158;
																																						{
																																							double var158 = metric_var[traceTempVariable$currentState$114_1];
																																							cv$temp$21$var158 = var158;
																																						}
																																						if(((Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value112) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$70_2][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value112);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
													int traceTempVariable$currentState$74_1 = cv$currentValue;
													for(int index$sample$74_2 = 0; index$sample$74_2 < noSamples; index$sample$74_2 += 1) {
														if((sample == index$sample$74_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$74_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			{
																				if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						int traceTempVariable$currentState$171_1 = distributionTempVariable$var111$28;
																						if((index$sample$25 == index$sample$74_2)) {
																							if((0 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$171_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$171_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																		double cv$temp$46$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$171_1];
																																			cv$temp$46$var157 = var157;
																																		}
																																		double cv$temp$47$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$171_1];
																																			cv$temp$47$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$46$var157) / Math.sqrt(cv$temp$47$var158))) - (0.5 * Math.log(cv$temp$47$var158)))));
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
																						for(int index$sample$172 = 0; index$sample$172 < noSamples; index$sample$172 += 1) {
																							if(!(index$sample$172 == index$sample$25)) {
																								for(int index$sample117$173 = 0; index$sample117$173 < noStates; index$sample117$173 += 1) {
																									int distributionTempVariable$var111$175 = index$sample117$173;
																									double cv$probabilitySample117Value174 = (1.0 * distribution$sample117[((index$sample$172 - 0) / 1)][index$sample117$173]);
																									int traceTempVariable$currentState$176_1 = distributionTempVariable$var111$175;
																									if((index$sample$172 == index$sample$74_2)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$176_1)) {
																															for(int var74 = 0; var74 < noStates; var74 += 1) {
																																if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																	if((var74 == traceTempVariable$currentState$176_1)) {
																																		{
																																			{
																																				if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																					double cv$temp$48$var157;
																																					{
																																						double var157 = metric_mean[traceTempVariable$currentState$176_1];
																																						cv$temp$48$var157 = var157;
																																					}
																																					double cv$temp$49$var158;
																																					{
																																						double var158 = metric_var[traceTempVariable$currentState$176_1];
																																						cv$temp$49$var158 = var158;
																																					}
																																					if(((Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))) < cv$accumulatedConsumerProbabilities))
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																					else {
																																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158))));
																																						else
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value174) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$48$var157) / Math.sqrt(cv$temp$49$var158))) - (0.5 * Math.log(cv$temp$49$var158)))));
																																					}
																																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value174);
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
																						if((index$sample$23 == index$sample$74_2)) {
																							if((index$timeStep$22 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$181_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$181_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																		double cv$temp$50$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$181_1];
																																			cv$temp$50$var157 = var157;
																																		}
																																		double cv$temp$51$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$181_1];
																																			cv$temp$51$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$50$var157) / Math.sqrt(cv$temp$51$var158))) - (0.5 * Math.log(cv$temp$51$var158)))));
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
																						for(int index$sample$182 = 0; index$sample$182 < noSamples; index$sample$182 += 1) {
																							for(int index$timeStep$183 = 1; index$timeStep$183 < length$metric[index$sample$182]; index$timeStep$183 += 1) {
																								if(!((index$sample$182 == index$sample$23) && (index$timeStep$183 == index$timeStep$22))) {
																									for(int index$sample136$184 = 0; index$sample136$184 < noStates; index$sample136$184 += 1) {
																										int distributionTempVariable$var130$186 = index$sample136$184;
																										double cv$probabilitySample136Value185 = (1.0 * distribution$sample136[((index$sample$182 - 0) / 1)][((index$timeStep$183 - 1) / 1)][index$sample136$184]);
																										int traceTempVariable$currentState$187_1 = distributionTempVariable$var130$186;
																										if((index$sample$182 == index$sample$74_2)) {
																											if((index$timeStep$183 == timeStep$var145)) {
																												if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																													for(int var58 = 0; var58 < noStates; var58 += 1) {
																														if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																															if((var58 == traceTempVariable$currentState$187_1)) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$187_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$74_2][timeStep$var145]) {
																																						double cv$temp$52$var157;
																																						{
																																							double var157 = metric_mean[traceTempVariable$currentState$187_1];
																																							cv$temp$52$var157 = var157;
																																						}
																																						double cv$temp$53$var158;
																																						{
																																							double var158 = metric_var[traceTempVariable$currentState$187_1];
																																							cv$temp$53$var158 = var158;
																																						}
																																						if(((Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value185) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$74_2][timeStep$var145] - cv$temp$52$var157) / Math.sqrt(cv$temp$53$var158))) - (0.5 * Math.log(cv$temp$53$var158)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value185);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
			int traceTempVariable$var127$32_1 = cv$currentValue;
			if((index$sample$23 == sample)) {
				if((index$timeStep$22 == (timeStep$var122 - 1))) {
					for(int var39 = 0; var39 < noStates; var39 += 1) {
						if((var39 == traceTempVariable$var127$32_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var128;
							{
								double[] var128 = m[traceTempVariable$var127$32_1];
								cv$temp$2$var128 = var128;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var128.length))?Math.log(cv$temp$2$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var127$43_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$currentState$47_1 = cv$currentValue;
									for(int index$sample$47_2 = 0; index$sample$47_2 < noSamples; index$sample$47_2 += 1) {
										if((sample == index$sample$47_2)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$47_2]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var90 = 0; var90 < noStates; var90 += 1) {
																if((var90 == traceTempVariable$currentState$47_1)) {
																	{
																		{
																			double cv$temp$6$var148;
																			{
																				double var148 = metric_valid_bias[traceTempVariable$currentState$47_1];
																				cv$temp$6$var148 = var148;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$47_2][timeStep$var145], cv$temp$6$var148)));
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
							{
								{
									boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
									for(int index$sample$63_1 = 0; index$sample$63_1 < noSamples; index$sample$63_1 += 1) {
										if((sample == index$sample$63_1)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$63_1]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$63_1][timeStep$var145])
														guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
												}
											}
										}
									}
									for(int index$sample$67_1 = 0; index$sample$67_1 < noSamples; index$sample$67_1 += 1) {
										if((sample == index$sample$67_1)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$67_1]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$67_1][timeStep$var145])
														guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
												}
											}
										}
									}
									int traceTempVariable$currentState$71_1 = cv$currentValue;
									for(int index$sample$71_2 = 0; index$sample$71_2 < noSamples; index$sample$71_2 += 1) {
										if((sample == index$sample$71_2)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$71_2]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
														if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
															guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
															{
																if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int var58 = 0; var58 < noStates; var58 += 1) {
																			if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																				if((var58 == traceTempVariable$currentState$71_1)) {
																					if(fixedFlag$sample117) {
																						for(int index$sample$118_1 = 0; index$sample$118_1 < noSamples; index$sample$118_1 += 1) {
																							if((index$sample$118_1 == index$sample$71_2)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$71_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																																double cv$temp$22$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$71_1];
																																	cv$temp$22$var157 = var157;
																																}
																																double cv$temp$23$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$71_1];
																																	cv$temp$23$var158 = var158;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))));
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
																					} else {
																						for(int index$sample$119 = 0; index$sample$119 < noSamples; index$sample$119 += 1) {
																							if(true) {
																								for(int index$sample117$120 = 0; index$sample117$120 < noStates; index$sample117$120 += 1) {
																									int distributionTempVariable$var111$122 = index$sample117$120;
																									double cv$probabilitySample117Value121 = (1.0 * distribution$sample117[((index$sample$119 - 0) / 1)][index$sample117$120]);
																									int traceTempVariable$currentState$123_1 = distributionTempVariable$var111$122;
																									if((index$sample$119 == index$sample$71_2)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$123_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																																		double cv$temp$24$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$123_1];
																																			cv$temp$24$var157 = var157;
																																		}
																																		double cv$temp$25$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$123_1];
																																			cv$temp$25$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value121) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value121);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																		for(int var58 = 0; var58 < noStates; var58 += 1) {
																			if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																				if((var58 == traceTempVariable$currentState$71_1)) {
																					int traceTempVariable$currentState$127_1 = cv$currentValue;
																					if((index$sample$23 == index$sample$71_2)) {
																						if((index$timeStep$22 == timeStep$var145)) {
																							if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$127_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																														double cv$temp$26$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$127_1];
																															cv$temp$26$var157 = var157;
																														}
																														double cv$temp$27$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$127_1];
																															cv$temp$27$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))));
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
																					for(int index$sample$128 = 0; index$sample$128 < noSamples; index$sample$128 += 1) {
																						for(int index$timeStep$129 = 1; index$timeStep$129 < length$metric[index$sample$128]; index$timeStep$129 += 1) {
																							if(!((index$sample$128 == index$sample$23) && (index$timeStep$129 == index$timeStep$22))) {
																								for(int index$sample136$130 = 0; index$sample136$130 < noStates; index$sample136$130 += 1) {
																									int distributionTempVariable$var130$132 = index$sample136$130;
																									double cv$probabilitySample136Value131 = (1.0 * distribution$sample136[((index$sample$128 - 0) / 1)][((index$timeStep$129 - 1) / 1)][index$sample136$130]);
																									int traceTempVariable$currentState$133_1 = distributionTempVariable$var130$132;
																									if((index$sample$128 == index$sample$71_2)) {
																										if((index$timeStep$129 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$133_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$71_2][timeStep$var145]) {
																																		double cv$temp$28$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$133_1];
																																			cv$temp$28$var157 = var157;
																																		}
																																		double cv$temp$29$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$133_1];
																																			cv$temp$29$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value131) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$71_2][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value131);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
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
									int traceTempVariable$currentState$75_1 = cv$currentValue;
									for(int index$sample$75_2 = 0; index$sample$75_2 < noSamples; index$sample$75_2 += 1) {
										if((sample == index$sample$75_2)) {
											for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$75_2]; timeStep$var145 += 1) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
														if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
															guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
															{
																if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		if(fixedFlag$sample117) {
																			for(int index$sample$192_1 = 0; index$sample$192_1 < noSamples; index$sample$192_1 += 1) {
																				if((index$sample$192_1 == index$sample$75_2)) {
																					if((0 == timeStep$var145)) {
																						if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$75_1)) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$75_1)) {
																													{
																														{
																															if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																																double cv$temp$54$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$75_1];
																																	cv$temp$54$var157 = var157;
																																}
																																double cv$temp$55$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$75_1];
																																	cv$temp$55$var158 = var158;
																																}
																																if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$54$var157) / Math.sqrt(cv$temp$55$var158))) - (0.5 * Math.log(cv$temp$55$var158)))));
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
																		} else {
																			for(int index$sample$193 = 0; index$sample$193 < noSamples; index$sample$193 += 1) {
																				if(true) {
																					for(int index$sample117$194 = 0; index$sample117$194 < noStates; index$sample117$194 += 1) {
																						int distributionTempVariable$var111$196 = index$sample117$194;
																						double cv$probabilitySample117Value195 = (1.0 * distribution$sample117[((index$sample$193 - 0) / 1)][index$sample117$194]);
																						int traceTempVariable$currentState$197_1 = distributionTempVariable$var111$196;
																						if((index$sample$193 == index$sample$75_2)) {
																							if((0 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$197_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$197_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																																		double cv$temp$56$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$197_1];
																																			cv$temp$56$var157 = var157;
																																		}
																																		double cv$temp$57$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$197_1];
																																			cv$temp$57$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value195) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$56$var157) / Math.sqrt(cv$temp$57$var158))) - (0.5 * Math.log(cv$temp$57$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value195);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
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
																		if((index$sample$23 == index$sample$75_2)) {
																			if((index$timeStep$22 == timeStep$var145)) {
																				if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																					for(int var58 = 0; var58 < noStates; var58 += 1) {
																						if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																							if((var58 == traceTempVariable$currentState$202_1)) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$202_1)) {
																											{
																												{
																													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																														double cv$temp$58$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$202_1];
																															cv$temp$58$var157 = var157;
																														}
																														double cv$temp$59$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$202_1];
																															cv$temp$59$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$58$var157) / Math.sqrt(cv$temp$59$var158))) - (0.5 * Math.log(cv$temp$59$var158)))));
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
																		for(int index$sample$203 = 0; index$sample$203 < noSamples; index$sample$203 += 1) {
																			for(int index$timeStep$204 = 1; index$timeStep$204 < length$metric[index$sample$203]; index$timeStep$204 += 1) {
																				if(!((index$sample$203 == index$sample$23) && (index$timeStep$204 == index$timeStep$22))) {
																					for(int index$sample136$205 = 0; index$sample136$205 < noStates; index$sample136$205 += 1) {
																						int distributionTempVariable$var130$207 = index$sample136$205;
																						double cv$probabilitySample136Value206 = (1.0 * distribution$sample136[((index$sample$203 - 0) / 1)][((index$timeStep$204 - 1) / 1)][index$sample136$205]);
																						int traceTempVariable$currentState$208_1 = distributionTempVariable$var130$207;
																						if((index$sample$203 == index$sample$75_2)) {
																							if((index$timeStep$204 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$208_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$208_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$75_2][timeStep$var145]) {
																																		double cv$temp$60$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$208_1];
																																			cv$temp$60$var157 = var157;
																																		}
																																		double cv$temp$61$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$208_1];
																																			cv$temp$61$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value206) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$75_2][timeStep$var145] - cv$temp$60$var157) / Math.sqrt(cv$temp$61$var158))) - (0.5 * Math.log(cv$temp$61$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value206);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
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
				for(int index$timeStep$34 = 1; index$timeStep$34 < length$metric[index$sample$33]; index$timeStep$34 += 1) {
					if(!((index$sample$33 == index$sample$23) && (index$timeStep$34 == index$timeStep$22))) {
						for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
							int distributionTempVariable$var130$37 = index$sample136$35;
							double cv$probabilitySample136Value36 = (1.0 * distribution$sample136[((index$sample$33 - 0) / 1)][((index$timeStep$34 - 1) / 1)][index$sample136$35]);
							int traceTempVariable$var127$38_1 = distributionTempVariable$var130$37;
							if((index$sample$33 == sample)) {
								if((index$timeStep$34 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == traceTempVariable$var127$38_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
											double[] cv$temp$3$var128;
											{
												double[] var128 = m[traceTempVariable$var127$38_1];
												cv$temp$3$var128 = var128;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var128.length))?Math.log(cv$temp$3$var128[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var127$44_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$currentState$48_1 = cv$currentValue;
													for(int index$sample$48_2 = 0; index$sample$48_2 < noSamples; index$sample$48_2 += 1) {
														if((sample == index$sample$48_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$48_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var90 = 0; var90 < noStates; var90 += 1) {
																				if((var90 == traceTempVariable$currentState$48_1)) {
																					{
																						{
																							double cv$temp$7$var148;
																							{
																								double var148 = metric_valid_bias[traceTempVariable$currentState$48_1];
																								cv$temp$7$var148 = var148;
																							}
																							if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(metric_valid_g[index$sample$48_2][timeStep$var145], cv$temp$7$var148)));
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
											{
												{
													boolean[][] guard$sample136gaussian169 = guard$sample136gaussian169$global;
													for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
														if((sample == index$sample$64_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$64_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$64_1][timeStep$var145])
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													for(int index$sample$68_1 = 0; index$sample$68_1 < noSamples; index$sample$68_1 += 1) {
														if((sample == index$sample$68_1)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$68_1]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$68_1][timeStep$var145])
																		guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = false;
																}
															}
														}
													}
													int traceTempVariable$currentState$72_1 = cv$currentValue;
													for(int index$sample$72_2 = 0; index$sample$72_2 < noSamples; index$sample$72_2 += 1) {
														if((sample == index$sample$72_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$72_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			{
																				if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$72_1)) {
																									if(fixedFlag$sample117) {
																										for(int index$sample$137_1 = 0; index$sample$137_1 < noSamples; index$sample$137_1 += 1) {
																											if((index$sample$137_1 == index$sample$72_2)) {
																												if((0 == timeStep$var145)) {
																													if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$72_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																				double cv$temp$30$var157;
																																				{
																																					double var157 = metric_mean[traceTempVariable$currentState$72_1];
																																					cv$temp$30$var157 = var157;
																																				}
																																				double cv$temp$31$var158;
																																				{
																																					double var158 = metric_var[traceTempVariable$currentState$72_1];
																																					cv$temp$31$var158 = var158;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$30$var157) / Math.sqrt(cv$temp$31$var158))) - (0.5 * Math.log(cv$temp$31$var158)))));
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
																									} else {
																										for(int index$sample$138 = 0; index$sample$138 < noSamples; index$sample$138 += 1) {
																											if(true) {
																												for(int index$sample117$139 = 0; index$sample117$139 < noStates; index$sample117$139 += 1) {
																													int distributionTempVariable$var111$141 = index$sample117$139;
																													double cv$probabilitySample117Value140 = (1.0 * distribution$sample117[((index$sample$138 - 0) / 1)][index$sample117$139]);
																													int traceTempVariable$currentState$142_1 = distributionTempVariable$var111$141;
																													if((index$sample$138 == index$sample$72_2)) {
																														if((0 == timeStep$var145)) {
																															if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$142_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																						double cv$temp$32$var157;
																																						{
																																							double var157 = metric_mean[traceTempVariable$currentState$142_1];
																																							cv$temp$32$var157 = var157;
																																						}
																																						double cv$temp$33$var158;
																																						{
																																							double var158 = metric_var[traceTempVariable$currentState$142_1];
																																							cv$temp$33$var158 = var158;
																																						}
																																						if(((Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value140) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$32$var157) / Math.sqrt(cv$temp$33$var158))) - (0.5 * Math.log(cv$temp$33$var158)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value140);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																								if((var58 == traceTempVariable$currentState$72_1)) {
																									int traceTempVariable$currentState$146_1 = cv$currentValue;
																									if((index$sample$23 == index$sample$72_2)) {
																										if((index$timeStep$22 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$146_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		double cv$temp$34$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$146_1];
																																			cv$temp$34$var157 = var157;
																																		}
																																		double cv$temp$35$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$146_1];
																																			cv$temp$35$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$34$var157) / Math.sqrt(cv$temp$35$var158))) - (0.5 * Math.log(cv$temp$35$var158)))));
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
																									int traceTempVariable$currentState$147_1 = distributionTempVariable$var130$37;
																									if((index$sample$33 == index$sample$72_2)) {
																										if((index$timeStep$34 == timeStep$var145)) {
																											if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$147_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		double cv$temp$36$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$147_1];
																																			cv$temp$36$var157 = var157;
																																		}
																																		double cv$temp$37$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$147_1];
																																			cv$temp$37$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$36$var157) / Math.sqrt(cv$temp$37$var158))) - (0.5 * Math.log(cv$temp$37$var158)))));
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
																									for(int index$sample$148 = 0; index$sample$148 < noSamples; index$sample$148 += 1) {
																										for(int index$timeStep$149 = 1; index$timeStep$149 < length$metric[index$sample$148]; index$timeStep$149 += 1) {
																											if((!((index$sample$148 == index$sample$23) && (index$timeStep$149 == index$timeStep$22)) && !((index$sample$148 == index$sample$33) && (index$timeStep$149 == index$timeStep$34)))) {
																												for(int index$sample136$150 = 0; index$sample136$150 < noStates; index$sample136$150 += 1) {
																													int distributionTempVariable$var130$152 = index$sample136$150;
																													double cv$probabilitySample136Value151 = (1.0 * distribution$sample136[((index$sample$148 - 0) / 1)][((index$timeStep$149 - 1) / 1)][index$sample136$150]);
																													int traceTempVariable$currentState$153_1 = distributionTempVariable$var130$152;
																													if((index$sample$148 == index$sample$72_2)) {
																														if((index$timeStep$149 == timeStep$var145)) {
																															if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$153_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$72_2][timeStep$var145]) {
																																						double cv$temp$38$var157;
																																						{
																																							double var157 = metric_mean[traceTempVariable$currentState$153_1];
																																							cv$temp$38$var157 = var157;
																																						}
																																						double cv$temp$39$var158;
																																						{
																																							double var158 = metric_var[traceTempVariable$currentState$153_1];
																																							cv$temp$39$var158 = var158;
																																						}
																																						if(((Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value151) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$72_2][timeStep$var145] - cv$temp$38$var157) / Math.sqrt(cv$temp$39$var158))) - (0.5 * Math.log(cv$temp$39$var158)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value151);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
													int traceTempVariable$currentState$76_1 = cv$currentValue;
													for(int index$sample$76_2 = 0; index$sample$76_2 < noSamples; index$sample$76_2 += 1) {
														if((sample == index$sample$76_2)) {
															for(int timeStep$var145 = 0; timeStep$var145 < length$metric[index$sample$76_2]; timeStep$var145 += 1) {
																if((timeStep$var122 == timeStep$var145)) {
																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																		if(!guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)]) {
																			guard$sample136gaussian169[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = true;
																			{
																				if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																					double cv$consumerDistributionProbabilityAccumulator = 1.0;
																					{
																						if(fixedFlag$sample117) {
																							for(int index$sample$213_1 = 0; index$sample$213_1 < noSamples; index$sample$213_1 += 1) {
																								if((index$sample$213_1 == index$sample$76_2)) {
																									if((0 == timeStep$var145)) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																											for(int var58 = 0; var58 < noStates; var58 += 1) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																													if((var58 == traceTempVariable$currentState$76_1)) {
																														for(int var74 = 0; var74 < noStates; var74 += 1) {
																															if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																if((var74 == traceTempVariable$currentState$76_1)) {
																																	{
																																		{
																																			if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																				double cv$temp$62$var157;
																																				{
																																					double var157 = metric_mean[traceTempVariable$currentState$76_1];
																																					cv$temp$62$var157 = var157;
																																				}
																																				double cv$temp$63$var158;
																																				{
																																					double var158 = metric_var[traceTempVariable$currentState$76_1];
																																					cv$temp$63$var158 = var158;
																																				}
																																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))) < cv$accumulatedConsumerProbabilities))
																																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																				else {
																																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158))));
																																					else
																																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$62$var157) / Math.sqrt(cv$temp$63$var158))) - (0.5 * Math.log(cv$temp$63$var158)))));
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
																						} else {
																							for(int index$sample$214 = 0; index$sample$214 < noSamples; index$sample$214 += 1) {
																								if(true) {
																									for(int index$sample117$215 = 0; index$sample117$215 < noStates; index$sample117$215 += 1) {
																										int distributionTempVariable$var111$217 = index$sample117$215;
																										double cv$probabilitySample117Value216 = (1.0 * distribution$sample117[((index$sample$214 - 0) / 1)][index$sample117$215]);
																										int traceTempVariable$currentState$218_1 = distributionTempVariable$var111$217;
																										if((index$sample$214 == index$sample$76_2)) {
																											if((0 == timeStep$var145)) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																													for(int var58 = 0; var58 < noStates; var58 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																															if((var58 == traceTempVariable$currentState$218_1)) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$218_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																						double cv$temp$64$var157;
																																						{
																																							double var157 = metric_mean[traceTempVariable$currentState$218_1];
																																							cv$temp$64$var157 = var157;
																																						}
																																						double cv$temp$65$var158;
																																						{
																																							double var158 = metric_var[traceTempVariable$currentState$218_1];
																																							cv$temp$65$var158 = var158;
																																						}
																																						if(((Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value216) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$64$var157) / Math.sqrt(cv$temp$65$var158))) - (0.5 * Math.log(cv$temp$65$var158)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value216);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
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
																						if((index$sample$23 == index$sample$76_2)) {
																							if((index$timeStep$22 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$223_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$223_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		double cv$temp$66$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$223_1];
																																			cv$temp$66$var157 = var157;
																																		}
																																		double cv$temp$67$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$223_1];
																																			cv$temp$67$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$66$var157) / Math.sqrt(cv$temp$67$var158))) - (0.5 * Math.log(cv$temp$67$var158)))));
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
																						int traceTempVariable$currentState$224_1 = distributionTempVariable$var130$37;
																						if((index$sample$33 == index$sample$76_2)) {
																							if((index$timeStep$34 == timeStep$var145)) {
																								if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																									for(int var58 = 0; var58 < noStates; var58 += 1) {
																										if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																											if((var58 == traceTempVariable$currentState$224_1)) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$224_1)) {
																															{
																																{
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		double cv$temp$68$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$224_1];
																																			cv$temp$68$var157 = var157;
																																		}
																																		double cv$temp$69$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$224_1];
																																			cv$temp$69$var158 = var158;
																																		}
																																		if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$68$var157) / Math.sqrt(cv$temp$69$var158))) - (0.5 * Math.log(cv$temp$69$var158)))));
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
																						for(int index$sample$225 = 0; index$sample$225 < noSamples; index$sample$225 += 1) {
																							for(int index$timeStep$226 = 1; index$timeStep$226 < length$metric[index$sample$225]; index$timeStep$226 += 1) {
																								if((!((index$sample$225 == index$sample$23) && (index$timeStep$226 == index$timeStep$22)) && !((index$sample$225 == index$sample$33) && (index$timeStep$226 == index$timeStep$34)))) {
																									for(int index$sample136$227 = 0; index$sample136$227 < noStates; index$sample136$227 += 1) {
																										int distributionTempVariable$var130$229 = index$sample136$227;
																										double cv$probabilitySample136Value228 = (1.0 * distribution$sample136[((index$sample$225 - 0) / 1)][((index$timeStep$226 - 1) / 1)][index$sample136$227]);
																										int traceTempVariable$currentState$230_1 = distributionTempVariable$var130$229;
																										if((index$sample$225 == index$sample$76_2)) {
																											if((index$timeStep$226 == timeStep$var145)) {
																												if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																													for(int var58 = 0; var58 < noStates; var58 += 1) {
																														if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																															if((var58 == traceTempVariable$currentState$230_1)) {
																																for(int var74 = 0; var74 < noStates; var74 += 1) {
																																	if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																		if((var74 == traceTempVariable$currentState$230_1)) {
																																			{
																																				{
																																					if(metric_valid_g[index$sample$76_2][timeStep$var145]) {
																																						double cv$temp$70$var157;
																																						{
																																							double var157 = metric_mean[traceTempVariable$currentState$230_1];
																																							cv$temp$70$var157 = var157;
																																						}
																																						double cv$temp$71$var158;
																																						{
																																							double var158 = metric_var[traceTempVariable$currentState$230_1];
																																							cv$temp$71$var158 = var158;
																																						}
																																						if(((Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))) < cv$accumulatedConsumerProbabilities))
																																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																						else {
																																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158))));
																																							else
																																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value228) + (DistributionSampling.logProbabilityGaussian(((metric_g[index$sample$76_2][timeStep$var145] - cv$temp$70$var157) / Math.sqrt(cv$temp$71$var158))) - (0.5 * Math.log(cv$temp$71$var158)))));
																																						}
																																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value228);
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
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
					int traceTempVariable$var127$269_1 = cv$currentValue;
					for(int index$sample$269_2 = 0; index$sample$269_2 < noSamples; index$sample$269_2 += 1) {
						if((sample == index$sample$269_2)) {
							for(int index$timeStep$269_3 = 1; index$timeStep$269_3 < length$metric[index$sample$269_2]; index$timeStep$269_3 += 1) {
								if((timeStep$var122 == (index$timeStep$269_3 - 1))) {
									{
										int index$timeStep$271 = index$timeStep$269_3;
										int index$sample$272 = index$sample$269_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var129;
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var39 = 0; var39 < noStates; var39 += 1) {
											if((var39 == traceTempVariable$var127$269_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample117) {
														for(int index$sample$274_1 = 0; index$sample$274_1 < noSamples; index$sample$274_1 += 1) {
															if((index$sample$274_1 == sample)) {
																if((0 == (timeStep$var122 - 1))) {
																	for(int index$var39$280_1 = 0; index$var39$280_1 < noStates; index$var39$280_1 += 1) {
																		if((index$var39$280_1 == st[sample][(timeStep$var122 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int index$sample$275 = 0; index$sample$275 < noSamples; index$sample$275 += 1) {
															if(true) {
																for(int index$sample117$276 = 0; index$sample117$276 < noStates; index$sample117$276 += 1) {
																	int distributionTempVariable$var111$278 = index$sample117$276;
																	double cv$probabilitySample117Value277 = (1.0 * distribution$sample117[((index$sample$275 - 0) / 1)][index$sample117$276]);
																	int traceTempVariable$var127$279_1 = distributionTempVariable$var111$278;
																	if((index$sample$275 == sample)) {
																		if((0 == (timeStep$var122 - 1))) {
																			for(int index$var39$281_1 = 0; index$var39$281_1 < noStates; index$var39$281_1 += 1) {
																				if((index$var39$281_1 == traceTempVariable$var127$279_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample117Value277);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var127$282_1 = cv$currentValue;
													if((index$sample$23 == sample)) {
														if((index$timeStep$22 == (timeStep$var122 - 1))) {
															for(int index$var39$289_1 = 0; index$var39$289_1 < noStates; index$var39$289_1 += 1) {
																if((index$var39$289_1 == traceTempVariable$var127$282_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$sample$283 = 0; index$sample$283 < noSamples; index$sample$283 += 1) {
														for(int index$timeStep$284 = 1; index$timeStep$284 < length$metric[index$sample$283]; index$timeStep$284 += 1) {
															if((!((index$sample$283 == index$sample$23) && (index$timeStep$284 == index$timeStep$22)) && !((index$sample$283 == index$sample$272) && (index$timeStep$284 == index$timeStep$271)))) {
																for(int index$sample136$285 = 0; index$sample136$285 < noStates; index$sample136$285 += 1) {
																	int distributionTempVariable$var130$287 = index$sample136$285;
																	double cv$probabilitySample136Value286 = (1.0 * distribution$sample136[((index$sample$283 - 0) / 1)][((index$timeStep$284 - 1) / 1)][index$sample136$285]);
																	int traceTempVariable$var127$288_1 = distributionTempVariable$var130$287;
																	if((index$sample$283 == sample)) {
																		if((index$timeStep$284 == (timeStep$var122 - 1))) {
																			for(int index$var39$290_1 = 0; index$var39$290_1 < noStates; index$var39$290_1 += 1) {
																				if((index$var39$290_1 == traceTempVariable$var127$288_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample136Value286);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$72$var128;
													{
														double[] var128 = m[traceTempVariable$var127$269_1];
														cv$temp$72$var128 = var128;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$72$var128);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample136[((index$sample$269_2 - 0) / 1)][((index$timeStep$269_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)];
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

	private final void sample158(int sample, int timeStep$var145) {
		boolean cv$varObserved = false;
		{
			cv$varObserved = true;
		}
		if(!cv$varObserved) {
			int cv$noStates = 0;
			if(fixedFlag$sample117) {
				for(int index$sample$2_1 = 0; index$sample$2_1 < noSamples; index$sample$2_1 += 1) {
					if((index$sample$2_1 == sample)) {
						if((0 == timeStep$var145)) {
							for(int var90 = 0; var90 < noStates; var90 += 1) {
								if((var90 == st[sample][timeStep$var145]))
									cv$noStates = Math.max(cv$noStates, 2);
							}
						}
					}
				}
			} else {
				for(int index$sample$3 = 0; index$sample$3 < noSamples; index$sample$3 += 1) {
					if(true) {
						for(int index$sample117$4 = 0; index$sample117$4 < noStates; index$sample117$4 += 1) {
							int distributionTempVariable$var111$6 = index$sample117$4;
							double cv$probabilitySample117Value5 = (1.0 * distribution$sample117[((index$sample$3 - 0) / 1)][index$sample117$4]);
							int traceTempVariable$currentState$7_1 = distributionTempVariable$var111$6;
							if((index$sample$3 == sample)) {
								if((0 == timeStep$var145)) {
									for(int var90 = 0; var90 < noStates; var90 += 1) {
										if((var90 == traceTempVariable$currentState$7_1))
											cv$noStates = Math.max(cv$noStates, 2);
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample136) {
				for(int index$sample$10_1 = 0; index$sample$10_1 < noSamples; index$sample$10_1 += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$10_1]; timeStep$var122 += 1) {
						if((index$sample$10_1 == sample)) {
							if((timeStep$var122 == timeStep$var145)) {
								for(int var90 = 0; var90 < noStates; var90 += 1) {
									if((var90 == st[sample][timeStep$var145]))
										cv$noStates = Math.max(cv$noStates, 2);
								}
							}
						}
					}
				}
			} else {
				for(int index$sample$11 = 0; index$sample$11 < noSamples; index$sample$11 += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$11]; timeStep$var122 += 1) {
						if(true) {
							for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
								int distributionTempVariable$var130$15 = index$sample136$13;
								double cv$probabilitySample136Value14 = (1.0 * distribution$sample136[((index$sample$11 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$13]);
								int traceTempVariable$currentState$16_1 = distributionTempVariable$var130$15;
								if((index$sample$11 == sample)) {
									if((timeStep$var122 == timeStep$var145)) {
										for(int var90 = 0; var90 < noStates; var90 += 1) {
											if((var90 == traceTempVariable$currentState$16_1))
												cv$noStates = Math.max(cv$noStates, 2);
										}
									}
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var150$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				boolean cv$currentValue;
				cv$currentValue = (cv$valuePos == 1);
				boolean var150 = cv$currentValue;
				boolean[] metric_valid_1d = metric_valid_g[sample];
				metric_valid_1d[timeStep$var145] = cv$currentValue;
				{
					for(int index$timeStep$19_1 = 0; index$timeStep$19_1 < length$metric[sample]; index$timeStep$19_1 += 1) {
						if((timeStep$var145 == index$timeStep$19_1)) {
							{
								double[] metric_1d = metric_g[sample];
							}
						}
					}
				}
				if(fixedFlag$sample117) {
					for(int index$sample$20_1 = 0; index$sample$20_1 < noSamples; index$sample$20_1 += 1) {
						if((index$sample$20_1 == sample)) {
							if((0 == timeStep$var145)) {
								for(int var90 = 0; var90 < noStates; var90 += 1) {
									if((var90 == st[sample][timeStep$var145])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
										double cv$temp$0$var148;
										{
											double var148 = metric_valid_bias[st[sample][timeStep$var145]];
											cv$temp$0$var148 = var148;
										}
										double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$0$var148));
										{
											{
												for(int index$timeStep$37_1 = 0; index$timeStep$37_1 < length$metric[sample]; index$timeStep$37_1 += 1) {
													if((timeStep$var145 == index$timeStep$37_1)) {
														if(metric_valid_g[sample][index$timeStep$37_1]) {
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
				} else {
					for(int index$sample$21 = 0; index$sample$21 < noSamples; index$sample$21 += 1) {
						if(true) {
							for(int index$sample117$22 = 0; index$sample117$22 < noStates; index$sample117$22 += 1) {
								int distributionTempVariable$var111$24 = index$sample117$22;
								double cv$probabilitySample117Value23 = (1.0 * distribution$sample117[((index$sample$21 - 0) / 1)][index$sample117$22]);
								int traceTempVariable$currentState$25_1 = distributionTempVariable$var111$24;
								if((index$sample$21 == sample)) {
									if((0 == timeStep$var145)) {
										for(int var90 = 0; var90 < noStates; var90 += 1) {
											if((var90 == traceTempVariable$currentState$25_1)) {
												cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample117Value23);
												double cv$temp$1$var148;
												{
													double var148 = metric_valid_bias[traceTempVariable$currentState$25_1];
													cv$temp$1$var148 = var148;
												}
												double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample117Value23) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$1$var148));
												{
													{
														for(int index$timeStep$38_1 = 0; index$timeStep$38_1 < length$metric[sample]; index$timeStep$38_1 += 1) {
															if((timeStep$var145 == index$timeStep$38_1)) {
																if(metric_valid_g[sample][index$timeStep$38_1]) {
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
				if(fixedFlag$sample136) {
					for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
							if((index$sample$28_1 == sample)) {
								if((timeStep$var122 == timeStep$var145)) {
									for(int var90 = 0; var90 < noStates; var90 += 1) {
										if((var90 == st[sample][timeStep$var145])) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
											double cv$temp$2$var148;
											{
												double var148 = metric_valid_bias[st[sample][timeStep$var145]];
												cv$temp$2$var148 = var148;
											}
											double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$2$var148));
											{
												{
													for(int index$timeStep$39_1 = 0; index$timeStep$39_1 < length$metric[sample]; index$timeStep$39_1 += 1) {
														if((timeStep$var145 == index$timeStep$39_1)) {
															if(metric_valid_g[sample][index$timeStep$39_1]) {
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
				} else {
					for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
							if(true) {
								for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
									int distributionTempVariable$var130$33 = index$sample136$31;
									double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
									int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
									if((index$sample$29 == sample)) {
										if((timeStep$var122 == timeStep$var145)) {
											for(int var90 = 0; var90 < noStates; var90 += 1) {
												if((var90 == traceTempVariable$currentState$34_1)) {
													cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value32);
													double cv$temp$3$var148;
													{
														double var148 = metric_valid_bias[traceTempVariable$currentState$34_1];
														cv$temp$3$var148 = var148;
													}
													double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value32) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$3$var148));
													{
														{
															for(int index$timeStep$40_1 = 0; index$timeStep$40_1 < length$metric[sample]; index$timeStep$40_1 += 1) {
																if((timeStep$var145 == index$timeStep$40_1)) {
																	if(metric_valid_g[sample][index$timeStep$40_1]) {
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
			boolean var150 = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal) == 1);
			boolean[] metric_valid_1d = metric_valid_g[sample];
			metric_valid_1d[timeStep$var145] = var150;
			{
				for(int index$timeStep$41_1 = 0; index$timeStep$41_1 < length$metric[sample]; index$timeStep$41_1 += 1) {
					if((timeStep$var145 == index$timeStep$41_1)) {
						{
							double[] metric_1d = metric_g[sample];
						}
					}
				}
			}
		}
	}

	private final void sample30() {
		double[] cv$targetLocal = initialStateDistribution;
		double[] cv$countLocal = cv$var27$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						if(fixedFlag$sample117) {
							{
								int index$sample$3 = sample;
								{
									{
										{
											{
												cv$countLocal[st[sample][0]] = (cv$countLocal[st[sample][0]] + 1.0);
											}
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
				for(int sample = 0; sample < noSamples; sample += 1) {
					if(!fixedFlag$sample117) {
						{
							int index$sample$7 = sample;
							{
								{
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample117[((sample - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal);
	}

	private final void sample43(int var39) {
		double[] cv$targetLocal = m[var39];
		double[] cv$countLocal = cv$var40$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
							if(fixedFlag$sample117) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == (timeStep$var122 - 1))) {
											if((var39 == st[sample][(timeStep$var122 - 1)])) {
												if(fixedFlag$sample136) {
													{
														int index$timeStep$23 = timeStep$var122;
														int index$sample$24 = sample;
														{
															{
																{
																	{
																		cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + 1.0);
																	}
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
										for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
											int distributionTempVariable$var111$7 = index$sample117$5;
											double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
											int traceTempVariable$var127$8_1 = distributionTempVariable$var111$7;
											if((index$sample$4 == sample)) {
												if((0 == (timeStep$var122 - 1))) {
													if((var39 == traceTempVariable$var127$8_1)) {
														if(fixedFlag$sample136) {
															{
																int index$timeStep$26 = timeStep$var122;
																int index$sample$27 = sample;
																{
																	{
																		{
																			{
																				cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + cv$probabilitySample117Value6);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
							if(fixedFlag$sample136) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int index$timeStep$13_2 = 1; index$timeStep$13_2 < length$metric[index$sample$13_1]; index$timeStep$13_2 += 1) {
										if((index$sample$13_1 == sample)) {
											if((index$timeStep$13_2 == (timeStep$var122 - 1))) {
												if((var39 == st[sample][(timeStep$var122 - 1)])) {
													if(fixedFlag$sample136) {
														{
															int index$timeStep$29 = timeStep$var122;
															int index$sample$30 = sample;
															{
																{
																	{
																		{
																			cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + 1.0);
																		}
																	}
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
									for(int index$timeStep$15 = 1; index$timeStep$15 < length$metric[index$sample$14]; index$timeStep$15 += 1) {
										if(true) {
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var130$18 = index$sample136$16;
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((index$timeStep$15 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var127$19_1 = distributionTempVariable$var130$18;
												if((index$sample$14 == sample)) {
													if((index$timeStep$15 == (timeStep$var122 - 1))) {
														if((var39 == traceTempVariable$var127$19_1)) {
															if(fixedFlag$sample136) {
																{
																	int index$timeStep$32 = timeStep$var122;
																	int index$sample$33 = sample;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[sample][timeStep$var122]] = (cv$countLocal[st[sample][timeStep$var122]] + cv$probabilitySample136Value17);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						if(fixedFlag$sample117) {
							for(int index$sample$40_1 = 0; index$sample$40_1 < noSamples; index$sample$40_1 += 1) {
								if((index$sample$40_1 == sample)) {
									if((0 == (timeStep$var122 - 1))) {
										if((var39 == st[sample][(timeStep$var122 - 1)])) {
											if(!fixedFlag$sample136) {
												{
													int index$timeStep$60 = timeStep$var122;
													int index$sample$61 = sample;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
									for(int index$sample117$42 = 0; index$sample117$42 < noStates; index$sample117$42 += 1) {
										int distributionTempVariable$var111$44 = index$sample117$42;
										double cv$probabilitySample117Value43 = (1.0 * distribution$sample117[((index$sample$41 - 0) / 1)][index$sample117$42]);
										int traceTempVariable$var127$45_1 = distributionTempVariable$var111$44;
										if((index$sample$41 == sample)) {
											if((0 == (timeStep$var122 - 1))) {
												if((var39 == traceTempVariable$var127$45_1)) {
													if(!fixedFlag$sample136) {
														{
															int index$timeStep$63 = timeStep$var122;
															int index$sample$64 = sample;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample117Value43);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int sample = 0; sample < noSamples; sample += 1) {
					for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
						if(fixedFlag$sample136) {
							for(int index$sample$50_1 = 0; index$sample$50_1 < noSamples; index$sample$50_1 += 1) {
								for(int index$timeStep$50_2 = 1; index$timeStep$50_2 < length$metric[index$sample$50_1]; index$timeStep$50_2 += 1) {
									if((index$sample$50_1 == sample)) {
										if((index$timeStep$50_2 == (timeStep$var122 - 1))) {
											if((var39 == st[sample][(timeStep$var122 - 1)])) {
												if(!fixedFlag$sample136) {
													{
														int index$timeStep$66 = timeStep$var122;
														int index$sample$67 = sample;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$timeStep$52 = 1; index$timeStep$52 < length$metric[index$sample$51]; index$timeStep$52 += 1) {
									if(true) {
										for(int index$sample136$53 = 0; index$sample136$53 < noStates; index$sample136$53 += 1) {
											int distributionTempVariable$var130$55 = index$sample136$53;
											double cv$probabilitySample136Value54 = (1.0 * distribution$sample136[((index$sample$51 - 0) / 1)][((index$timeStep$52 - 1) / 1)][index$sample136$53]);
											int traceTempVariable$var127$56_1 = distributionTempVariable$var130$55;
											if((index$sample$51 == sample)) {
												if((index$timeStep$52 == (timeStep$var122 - 1))) {
													if((var39 == traceTempVariable$var127$56_1)) {
														if(!fixedFlag$sample136) {
															{
																int index$timeStep$69 = timeStep$var122;
																int index$sample$70 = sample;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample136Value54);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample63(int var58) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = metric_mean[var58];
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
					double var59 = cv$proposedValue;
					metric_mean[var58] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var45;
				{
					cv$temp$0$var45 = 0.0;
				}
				double cv$temp$1$var46;
				{
					cv$temp$1$var46 = 100.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var45 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var46))?(-Math.log((cv$temp$1$var46 - cv$temp$0$var45))):Double.NEGATIVE_INFINITY));
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample117) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													double traceTempVariable$var157$9_1 = cv$currentValue;
													if(metric_valid_g[sample][timeStep$var145]) {
														if((var58 == st[sample][timeStep$var145])) {
															{
																if(metric_valid_g[sample][timeStep$var145]) {
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int index$sample$26_1 = 0; index$sample$26_1 < noSamples; index$sample$26_1 += 1) {
																			if((index$sample$26_1 == sample)) {
																				if((0 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var74 = 0; var74 < noStates; var74 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var74 == st[sample][timeStep$var145])) {
																									{
																										{
																											if(metric_valid_g[sample][timeStep$var145]) {
																												double cv$temp$2$var157;
																												{
																													double var157 = traceTempVariable$var157$9_1;
																													cv$temp$2$var157 = var157;
																												}
																												double cv$temp$3$var158;
																												{
																													double var158 = metric_var[st[sample][timeStep$var145]];
																													cv$temp$3$var158 = var158;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))));
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
																		if(fixedFlag$sample136) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
																					if((index$sample$28_1 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$4$var157;
																														{
																															double var157 = traceTempVariable$var157$9_1;
																															cv$temp$4$var157 = var157;
																														}
																														double cv$temp$5$var158;
																														{
																															double var158 = metric_var[st[sample][timeStep$var145]];
																															cv$temp$5$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))));
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
																			for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
																					if(true) {
																						for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
																							int distributionTempVariable$var130$33 = index$sample136$31;
																							double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
																							if((index$sample$29 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$34_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$6$var157;
																																{
																																	double var157 = traceTempVariable$var157$9_1;
																																	cv$temp$6$var157 = var157;
																																}
																																double cv$temp$7$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$34_1];
																																	cv$temp$7$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value32);
																															}
																														}
																													}
																												}
																											}
																										}
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
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
												int distributionTempVariable$var111$7 = index$sample117$5;
												double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
												int traceTempVariable$currentState$8_1 = distributionTempVariable$var111$7;
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															double traceTempVariable$var157$10_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var58 == traceTempVariable$currentState$8_1)) {
																	{
																		if(metric_valid_g[sample][timeStep$var145]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				int traceTempVariable$currentState$37_1 = distributionTempVariable$var111$7;
																				if((index$sample$4 == sample)) {
																					if((0 == timeStep$var145)) {
																						if(metric_valid_g[sample][timeStep$var145]) {
																							for(int var74 = 0; var74 < noStates; var74 += 1) {
																								if(metric_valid_g[sample][timeStep$var145]) {
																									if((var74 == traceTempVariable$currentState$37_1)) {
																										{
																											{
																												if(metric_valid_g[sample][timeStep$var145]) {
																													double cv$temp$8$var157;
																													{
																														double var157 = traceTempVariable$var157$10_1;
																														cv$temp$8$var157 = var157;
																													}
																													double cv$temp$9$var158;
																													{
																														double var158 = metric_var[traceTempVariable$currentState$37_1];
																														cv$temp$9$var158 = var158;
																													}
																													if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																					if(!(index$sample$38 == index$sample$4)) {
																						for(int index$sample117$39 = 0; index$sample117$39 < noStates; index$sample117$39 += 1) {
																							int distributionTempVariable$var111$41 = index$sample117$39;
																							double cv$probabilitySample117Value40 = (cv$probabilitySample117Value6 * distribution$sample117[((index$sample$38 - 0) / 1)][index$sample117$39]);
																							int traceTempVariable$currentState$42_1 = distributionTempVariable$var111$41;
																							if((index$sample$38 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$42_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$10$var157;
																																{
																																	double var157 = traceTempVariable$var157$10_1;
																																	cv$temp$10$var157 = var157;
																																}
																																double cv$temp$11$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$42_1];
																																	cv$temp$11$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value40);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(fixedFlag$sample136) {
																					for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$45_1]; timeStep$var122 += 1) {
																							if((index$sample$45_1 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$8_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$12$var157;
																																{
																																	double var157 = traceTempVariable$var157$10_1;
																																	cv$temp$12$var157 = var157;
																																}
																																double cv$temp$13$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$8_1];
																																	cv$temp$13$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																															}
																														}
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
																					for(int index$sample$46 = 0; index$sample$46 < noSamples; index$sample$46 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$46]; timeStep$var122 += 1) {
																							if(true) {
																								for(int index$sample136$48 = 0; index$sample136$48 < noStates; index$sample136$48 += 1) {
																									int distributionTempVariable$var130$50 = index$sample136$48;
																									double cv$probabilitySample136Value49 = (cv$probabilitySample117Value6 * distribution$sample136[((index$sample$46 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$48]);
																									int traceTempVariable$currentState$51_1 = distributionTempVariable$var130$50;
																									if((index$sample$46 == sample)) {
																										if((timeStep$var122 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$51_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$14$var157;
																																		{
																																			double var157 = traceTempVariable$var157$10_1;
																																			cv$temp$14$var157 = var157;
																																		}
																																		double cv$temp$15$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$51_1];
																																			cv$temp$15$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value49);
																																	}
																																}
																															}
																														}
																													}
																												}
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
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample136) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$13_1]; timeStep$var122 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														double traceTempVariable$var157$20_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var58 == st[sample][timeStep$var145])) {
																{
																	if(metric_valid_g[sample][timeStep$var145]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample117) {
																				for(int index$sample$54_1 = 0; index$sample$54_1 < noSamples; index$sample$54_1 += 1) {
																					if((index$sample$54_1 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$16$var157;
																														{
																															double var157 = traceTempVariable$var157$20_1;
																															cv$temp$16$var157 = var157;
																														}
																														double cv$temp$17$var158;
																														{
																															double var158 = metric_var[st[sample][timeStep$var145]];
																															cv$temp$17$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))));
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
																			} else {
																				for(int index$sample$55 = 0; index$sample$55 < noSamples; index$sample$55 += 1) {
																					if(true) {
																						for(int index$sample117$56 = 0; index$sample117$56 < noStates; index$sample117$56 += 1) {
																							int distributionTempVariable$var111$58 = index$sample117$56;
																							double cv$probabilitySample117Value57 = (1.0 * distribution$sample117[((index$sample$55 - 0) / 1)][index$sample117$56]);
																							int traceTempVariable$currentState$59_1 = distributionTempVariable$var111$58;
																							if((index$sample$55 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$59_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$18$var157;
																																{
																																	double var157 = traceTempVariable$var157$20_1;
																																	cv$temp$18$var157 = var157;
																																}
																																double cv$temp$19$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$59_1];
																																	cv$temp$19$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value57);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																				for(int index$timeStep$62_2 = 1; index$timeStep$62_2 < length$metric[index$sample$62_1]; index$timeStep$62_2 += 1) {
																					if((index$sample$62_1 == sample)) {
																						if((index$timeStep$62_2 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$20$var157;
																														{
																															double var157 = traceTempVariable$var157$20_1;
																															cv$temp$20$var157 = var157;
																														}
																														double cv$temp$21$var158;
																														{
																															double var158 = metric_var[st[sample][timeStep$var145]];
																															cv$temp$21$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))));
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
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$14]; timeStep$var122 += 1) {
											if(true) {
												for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
													int distributionTempVariable$var130$18 = index$sample136$16;
													double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$16]);
													int traceTempVariable$currentState$19_1 = distributionTempVariable$var130$18;
													if((index$sample$14 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																double traceTempVariable$var157$21_1 = cv$currentValue;
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var58 == traceTempVariable$currentState$19_1)) {
																		{
																			if(metric_valid_g[sample][timeStep$var145]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample117) {
																						for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																							if((index$sample$64_1 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var74 = 0; var74 < noStates; var74 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var74 == traceTempVariable$currentState$19_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$22$var157;
																																{
																																	double var157 = traceTempVariable$var157$21_1;
																																	cv$temp$22$var157 = var157;
																																}
																																double cv$temp$23$var158;
																																{
																																	double var158 = metric_var[traceTempVariable$currentState$19_1];
																																	cv$temp$23$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																															}
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
																						for(int index$sample$65 = 0; index$sample$65 < noSamples; index$sample$65 += 1) {
																							if(true) {
																								for(int index$sample117$66 = 0; index$sample117$66 < noStates; index$sample117$66 += 1) {
																									int distributionTempVariable$var111$68 = index$sample117$66;
																									double cv$probabilitySample117Value67 = (cv$probabilitySample136Value17 * distribution$sample117[((index$sample$65 - 0) / 1)][index$sample117$66]);
																									int traceTempVariable$currentState$69_1 = distributionTempVariable$var111$68;
																									if((index$sample$65 == sample)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$69_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$24$var157;
																																		{
																																			double var157 = traceTempVariable$var157$21_1;
																																			cv$temp$24$var157 = var157;
																																		}
																																		double cv$temp$25$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$69_1];
																																			cv$temp$25$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value67);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					int traceTempVariable$currentState$72_1 = distributionTempVariable$var130$18;
																					if((index$sample$14 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var74 = 0; var74 < noStates; var74 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var74 == traceTempVariable$currentState$72_1)) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$26$var157;
																														{
																															double var157 = traceTempVariable$var157$21_1;
																															cv$temp$26$var157 = var157;
																														}
																														double cv$temp$27$var158;
																														{
																															double var158 = metric_var[traceTempVariable$currentState$72_1];
																															cv$temp$27$var158 = var158;
																														}
																														if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$73 = 0; index$sample$73 < noSamples; index$sample$73 += 1) {
																						for(int index$timeStep$74 = 1; index$timeStep$74 < length$metric[index$sample$73]; index$timeStep$74 += 1) {
																							if(!((index$sample$73 == index$sample$14) && (index$timeStep$74 == timeStep$var122))) {
																								for(int index$sample136$75 = 0; index$sample136$75 < noStates; index$sample136$75 += 1) {
																									int distributionTempVariable$var130$77 = index$sample136$75;
																									double cv$probabilitySample136Value76 = (cv$probabilitySample136Value17 * distribution$sample136[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample136$75]);
																									int traceTempVariable$currentState$78_1 = distributionTempVariable$var130$77;
																									if((index$sample$73 == sample)) {
																										if((index$timeStep$74 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var74 = 0; var74 < noStates; var74 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var74 == traceTempVariable$currentState$78_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$28$var157;
																																		{
																																			double var157 = traceTempVariable$var157$21_1;
																																			cv$temp$28$var157 = var157;
																																		}
																																		double cv$temp$29$var158;
																																		{
																																			double var158 = metric_var[traceTempVariable$currentState$78_1];
																																			cv$temp$29$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value76);
																																	}
																																}
																															}
																														}
																													}
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
			double var59 = cv$originalValue;
			metric_mean[var58] = var59;
		}
	}

	private final void sample79(int var74) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = metric_var[var74];
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
					double var75 = cv$proposedValue;
					metric_var[var74] = cv$currentValue;
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var61;
				{
					cv$temp$0$var61 = 1.0;
				}
				double cv$temp$1$var62;
				{
					cv$temp$1$var62 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityInverseGamma(cv$currentValue, cv$temp$0$var61, cv$temp$1$var62));
				{
					{
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample117) {
									for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
										if((index$sample$3_1 == sample)) {
											if((0 == timeStep$var145)) {
												if(metric_valid_g[sample][timeStep$var145]) {
													double traceTempVariable$var158$9_1 = cv$currentValue;
													if(metric_valid_g[sample][timeStep$var145]) {
														if((var74 == st[sample][timeStep$var145])) {
															{
																if(metric_valid_g[sample][timeStep$var145]) {
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		for(int index$sample$26_1 = 0; index$sample$26_1 < noSamples; index$sample$26_1 += 1) {
																			if((index$sample$26_1 == sample)) {
																				if((0 == timeStep$var145)) {
																					if(metric_valid_g[sample][timeStep$var145]) {
																						for(int var58 = 0; var58 < noStates; var58 += 1) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								if((var58 == st[sample][timeStep$var145])) {
																									{
																										{
																											if(metric_valid_g[sample][timeStep$var145]) {
																												double cv$temp$2$var157;
																												{
																													double var157 = metric_mean[st[sample][timeStep$var145]];
																													cv$temp$2$var157 = var157;
																												}
																												double cv$temp$3$var158;
																												{
																													double var158 = traceTempVariable$var158$9_1;
																													cv$temp$3$var158 = var158;
																												}
																												if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) < cv$accumulatedConsumerProbabilities))
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																												else {
																													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158))));
																													else
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$2$var157) / Math.sqrt(cv$temp$3$var158))) - (0.5 * Math.log(cv$temp$3$var158)))));
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
																		if(fixedFlag$sample136) {
																			for(int index$sample$28_1 = 0; index$sample$28_1 < noSamples; index$sample$28_1 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$28_1]; timeStep$var122 += 1) {
																					if((index$sample$28_1 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$4$var157;
																														{
																															double var157 = metric_mean[st[sample][timeStep$var145]];
																															cv$temp$4$var157 = var157;
																														}
																														double cv$temp$5$var158;
																														{
																															double var158 = traceTempVariable$var158$9_1;
																															cv$temp$5$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$4$var157) / Math.sqrt(cv$temp$5$var158))) - (0.5 * Math.log(cv$temp$5$var158)))));
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
																			for(int index$sample$29 = 0; index$sample$29 < noSamples; index$sample$29 += 1) {
																				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$29]; timeStep$var122 += 1) {
																					if(true) {
																						for(int index$sample136$31 = 0; index$sample136$31 < noStates; index$sample136$31 += 1) {
																							int distributionTempVariable$var130$33 = index$sample136$31;
																							double cv$probabilitySample136Value32 = (1.0 * distribution$sample136[((index$sample$29 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$31]);
																							int traceTempVariable$currentState$34_1 = distributionTempVariable$var130$33;
																							if((index$sample$29 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$34_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$6$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$34_1];
																																	cv$temp$6$var157 = var157;
																																}
																																double cv$temp$7$var158;
																																{
																																	double var158 = traceTempVariable$var158$9_1;
																																	cv$temp$7$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value32) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$6$var157) / Math.sqrt(cv$temp$7$var158))) - (0.5 * Math.log(cv$temp$7$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value32);
																															}
																														}
																													}
																												}
																											}
																										}
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
									for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
										if(true) {
											for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
												int distributionTempVariable$var111$7 = index$sample117$5;
												double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
												int traceTempVariable$currentState$8_1 = distributionTempVariable$var111$7;
												if((index$sample$4 == sample)) {
													if((0 == timeStep$var145)) {
														if(metric_valid_g[sample][timeStep$var145]) {
															double traceTempVariable$var158$10_1 = cv$currentValue;
															if(metric_valid_g[sample][timeStep$var145]) {
																if((var74 == traceTempVariable$currentState$8_1)) {
																	{
																		if(metric_valid_g[sample][timeStep$var145]) {
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				int traceTempVariable$currentState$37_1 = distributionTempVariable$var111$7;
																				if((index$sample$4 == sample)) {
																					if((0 == timeStep$var145)) {
																						if(metric_valid_g[sample][timeStep$var145]) {
																							for(int var58 = 0; var58 < noStates; var58 += 1) {
																								if(metric_valid_g[sample][timeStep$var145]) {
																									if((var58 == traceTempVariable$currentState$37_1)) {
																										{
																											{
																												if(metric_valid_g[sample][timeStep$var145]) {
																													double cv$temp$8$var157;
																													{
																														double var157 = metric_mean[traceTempVariable$currentState$37_1];
																														cv$temp$8$var157 = var157;
																													}
																													double cv$temp$9$var158;
																													{
																														double var158 = traceTempVariable$var158$10_1;
																														cv$temp$9$var158 = var158;
																													}
																													if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) < cv$accumulatedConsumerProbabilities))
																														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																													else {
																														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																															cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158))));
																														else
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$8$var157) / Math.sqrt(cv$temp$9$var158))) - (0.5 * Math.log(cv$temp$9$var158)))));
																													}
																													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				for(int index$sample$38 = 0; index$sample$38 < noSamples; index$sample$38 += 1) {
																					if(!(index$sample$38 == index$sample$4)) {
																						for(int index$sample117$39 = 0; index$sample117$39 < noStates; index$sample117$39 += 1) {
																							int distributionTempVariable$var111$41 = index$sample117$39;
																							double cv$probabilitySample117Value40 = (cv$probabilitySample117Value6 * distribution$sample117[((index$sample$38 - 0) / 1)][index$sample117$39]);
																							int traceTempVariable$currentState$42_1 = distributionTempVariable$var111$41;
																							if((index$sample$38 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$42_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$10$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$42_1];
																																	cv$temp$10$var157 = var157;
																																}
																																double cv$temp$11$var158;
																																{
																																	double var158 = traceTempVariable$var158$10_1;
																																	cv$temp$11$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value40) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$10$var157) / Math.sqrt(cv$temp$11$var158))) - (0.5 * Math.log(cv$temp$11$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value40);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																				if(fixedFlag$sample136) {
																					for(int index$sample$45_1 = 0; index$sample$45_1 < noSamples; index$sample$45_1 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$45_1]; timeStep$var122 += 1) {
																							if((index$sample$45_1 == sample)) {
																								if((timeStep$var122 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$8_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$12$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$8_1];
																																	cv$temp$12$var157 = var157;
																																}
																																double cv$temp$13$var158;
																																{
																																	double var158 = traceTempVariable$var158$10_1;
																																	cv$temp$13$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value6) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$12$var157) / Math.sqrt(cv$temp$13$var158))) - (0.5 * Math.log(cv$temp$13$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value6);
																															}
																														}
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
																					for(int index$sample$46 = 0; index$sample$46 < noSamples; index$sample$46 += 1) {
																						for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$46]; timeStep$var122 += 1) {
																							if(true) {
																								for(int index$sample136$48 = 0; index$sample136$48 < noStates; index$sample136$48 += 1) {
																									int distributionTempVariable$var130$50 = index$sample136$48;
																									double cv$probabilitySample136Value49 = (cv$probabilitySample117Value6 * distribution$sample136[((index$sample$46 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$48]);
																									int traceTempVariable$currentState$51_1 = distributionTempVariable$var130$50;
																									if((index$sample$46 == sample)) {
																										if((timeStep$var122 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$51_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$14$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$51_1];
																																			cv$temp$14$var157 = var157;
																																		}
																																		double cv$temp$15$var158;
																																		{
																																			double var158 = traceTempVariable$var158$10_1;
																																			cv$temp$15$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value49) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$14$var157) / Math.sqrt(cv$temp$15$var158))) - (0.5 * Math.log(cv$temp$15$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value49);
																																	}
																																}
																															}
																														}
																													}
																												}
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
						for(int sample = 0; sample < noSamples; sample += 1) {
							for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
								if(fixedFlag$sample136) {
									for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$13_1]; timeStep$var122 += 1) {
											if((index$sample$13_1 == sample)) {
												if((timeStep$var122 == timeStep$var145)) {
													if(metric_valid_g[sample][timeStep$var145]) {
														double traceTempVariable$var158$20_1 = cv$currentValue;
														if(metric_valid_g[sample][timeStep$var145]) {
															if((var74 == st[sample][timeStep$var145])) {
																{
																	if(metric_valid_g[sample][timeStep$var145]) {
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			if(fixedFlag$sample117) {
																				for(int index$sample$54_1 = 0; index$sample$54_1 < noSamples; index$sample$54_1 += 1) {
																					if((index$sample$54_1 == sample)) {
																						if((0 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$16$var157;
																														{
																															double var157 = metric_mean[st[sample][timeStep$var145]];
																															cv$temp$16$var157 = var157;
																														}
																														double cv$temp$17$var158;
																														{
																															double var158 = traceTempVariable$var158$20_1;
																															cv$temp$17$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$16$var157) / Math.sqrt(cv$temp$17$var158))) - (0.5 * Math.log(cv$temp$17$var158)))));
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
																			} else {
																				for(int index$sample$55 = 0; index$sample$55 < noSamples; index$sample$55 += 1) {
																					if(true) {
																						for(int index$sample117$56 = 0; index$sample117$56 < noStates; index$sample117$56 += 1) {
																							int distributionTempVariable$var111$58 = index$sample117$56;
																							double cv$probabilitySample117Value57 = (1.0 * distribution$sample117[((index$sample$55 - 0) / 1)][index$sample117$56]);
																							int traceTempVariable$currentState$59_1 = distributionTempVariable$var111$58;
																							if((index$sample$55 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$59_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$18$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$59_1];
																																	cv$temp$18$var157 = var157;
																																}
																																double cv$temp$19$var158;
																																{
																																	double var158 = traceTempVariable$var158$20_1;
																																	cv$temp$19$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value57) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$18$var157) / Math.sqrt(cv$temp$19$var158))) - (0.5 * Math.log(cv$temp$19$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value57);
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																			for(int index$sample$62_1 = 0; index$sample$62_1 < noSamples; index$sample$62_1 += 1) {
																				for(int index$timeStep$62_2 = 1; index$timeStep$62_2 < length$metric[index$sample$62_1]; index$timeStep$62_2 += 1) {
																					if((index$sample$62_1 == sample)) {
																						if((index$timeStep$62_2 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == st[sample][timeStep$var145])) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$20$var157;
																														{
																															double var157 = metric_mean[st[sample][timeStep$var145]];
																															cv$temp$20$var157 = var157;
																														}
																														double cv$temp$21$var158;
																														{
																															double var158 = traceTempVariable$var158$20_1;
																															cv$temp$21$var158 = var158;
																														}
																														if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$20$var157) / Math.sqrt(cv$temp$21$var158))) - (0.5 * Math.log(cv$temp$21$var158)))));
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
									for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
										for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$14]; timeStep$var122 += 1) {
											if(true) {
												for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
													int distributionTempVariable$var130$18 = index$sample136$16;
													double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$16]);
													int traceTempVariable$currentState$19_1 = distributionTempVariable$var130$18;
													if((index$sample$14 == sample)) {
														if((timeStep$var122 == timeStep$var145)) {
															if(metric_valid_g[sample][timeStep$var145]) {
																double traceTempVariable$var158$21_1 = cv$currentValue;
																if(metric_valid_g[sample][timeStep$var145]) {
																	if((var74 == traceTempVariable$currentState$19_1)) {
																		{
																			if(metric_valid_g[sample][timeStep$var145]) {
																				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																				double cv$consumerDistributionProbabilityAccumulator = 1.0;
																				{
																					if(fixedFlag$sample117) {
																						for(int index$sample$64_1 = 0; index$sample$64_1 < noSamples; index$sample$64_1 += 1) {
																							if((index$sample$64_1 == sample)) {
																								if((0 == timeStep$var145)) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										for(int var58 = 0; var58 < noStates; var58 += 1) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												if((var58 == traceTempVariable$currentState$19_1)) {
																													{
																														{
																															if(metric_valid_g[sample][timeStep$var145]) {
																																double cv$temp$22$var157;
																																{
																																	double var157 = metric_mean[traceTempVariable$currentState$19_1];
																																	cv$temp$22$var157 = var157;
																																}
																																double cv$temp$23$var158;
																																{
																																	double var158 = traceTempVariable$var158$21_1;
																																	cv$temp$23$var158 = var158;
																																}
																																if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) < cv$accumulatedConsumerProbabilities))
																																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																else {
																																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																		cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158))));
																																	else
																																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$22$var157) / Math.sqrt(cv$temp$23$var158))) - (0.5 * Math.log(cv$temp$23$var158)))));
																																}
																																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																															}
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
																						for(int index$sample$65 = 0; index$sample$65 < noSamples; index$sample$65 += 1) {
																							if(true) {
																								for(int index$sample117$66 = 0; index$sample117$66 < noStates; index$sample117$66 += 1) {
																									int distributionTempVariable$var111$68 = index$sample117$66;
																									double cv$probabilitySample117Value67 = (cv$probabilitySample136Value17 * distribution$sample117[((index$sample$65 - 0) / 1)][index$sample117$66]);
																									int traceTempVariable$currentState$69_1 = distributionTempVariable$var111$68;
																									if((index$sample$65 == sample)) {
																										if((0 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$69_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$24$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$69_1];
																																			cv$temp$24$var157 = var157;
																																		}
																																		double cv$temp$25$var158;
																																		{
																																			double var158 = traceTempVariable$var158$21_1;
																																			cv$temp$25$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))))) + 1)) + (Math.log(cv$probabilitySample117Value67) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$24$var157) / Math.sqrt(cv$temp$25$var158))) - (0.5 * Math.log(cv$temp$25$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample117Value67);
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					int traceTempVariable$currentState$72_1 = distributionTempVariable$var130$18;
																					if((index$sample$14 == sample)) {
																						if((timeStep$var122 == timeStep$var145)) {
																							if(metric_valid_g[sample][timeStep$var145]) {
																								for(int var58 = 0; var58 < noStates; var58 += 1) {
																									if(metric_valid_g[sample][timeStep$var145]) {
																										if((var58 == traceTempVariable$currentState$72_1)) {
																											{
																												{
																													if(metric_valid_g[sample][timeStep$var145]) {
																														double cv$temp$26$var157;
																														{
																															double var157 = metric_mean[traceTempVariable$currentState$72_1];
																															cv$temp$26$var157 = var157;
																														}
																														double cv$temp$27$var158;
																														{
																															double var158 = traceTempVariable$var158$21_1;
																															cv$temp$27$var158 = var158;
																														}
																														if(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) < cv$accumulatedConsumerProbabilities))
																															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																														else {
																															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158))));
																															else
																																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value17) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$26$var157) / Math.sqrt(cv$temp$27$var158))) - (0.5 * Math.log(cv$temp$27$var158)))));
																														}
																														cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value17);
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																					for(int index$sample$73 = 0; index$sample$73 < noSamples; index$sample$73 += 1) {
																						for(int index$timeStep$74 = 1; index$timeStep$74 < length$metric[index$sample$73]; index$timeStep$74 += 1) {
																							if(!((index$sample$73 == index$sample$14) && (index$timeStep$74 == timeStep$var122))) {
																								for(int index$sample136$75 = 0; index$sample136$75 < noStates; index$sample136$75 += 1) {
																									int distributionTempVariable$var130$77 = index$sample136$75;
																									double cv$probabilitySample136Value76 = (cv$probabilitySample136Value17 * distribution$sample136[((index$sample$73 - 0) / 1)][((index$timeStep$74 - 1) / 1)][index$sample136$75]);
																									int traceTempVariable$currentState$78_1 = distributionTempVariable$var130$77;
																									if((index$sample$73 == sample)) {
																										if((index$timeStep$74 == timeStep$var145)) {
																											if(metric_valid_g[sample][timeStep$var145]) {
																												for(int var58 = 0; var58 < noStates; var58 += 1) {
																													if(metric_valid_g[sample][timeStep$var145]) {
																														if((var58 == traceTempVariable$currentState$78_1)) {
																															{
																																{
																																	if(metric_valid_g[sample][timeStep$var145]) {
																																		double cv$temp$28$var157;
																																		{
																																			double var157 = metric_mean[traceTempVariable$currentState$78_1];
																																			cv$temp$28$var157 = var157;
																																		}
																																		double cv$temp$29$var158;
																																		{
																																			double var158 = traceTempVariable$var158$21_1;
																																			cv$temp$29$var158 = var158;
																																		}
																																		if(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) < cv$accumulatedConsumerProbabilities))
																																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																																		else {
																																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																																				cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158))));
																																			else
																																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))))) + 1)) + (Math.log(cv$probabilitySample136Value76) + (DistributionSampling.logProbabilityGaussian(((metric_g[sample][timeStep$var145] - cv$temp$28$var157) / Math.sqrt(cv$temp$29$var158))) - (0.5 * Math.log(cv$temp$29$var158)))));
																																		}
																																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample136Value76);
																																	}
																																}
																															}
																														}
																													}
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
			double var75 = cv$originalValue;
			metric_var[var74] = var75;
		}
	}

	private final void sample95(int var90) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
							if(fixedFlag$sample117) {
								for(int index$sample$3_1 = 0; index$sample$3_1 < noSamples; index$sample$3_1 += 1) {
									if((index$sample$3_1 == sample)) {
										if((0 == timeStep$var145)) {
											if((var90 == st[sample][timeStep$var145])) {
												{
													{
														{
															{
																{
																	cv$count = (cv$count + 1.0);
																	if(metric_valid_g[sample][timeStep$var145])
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
							} else {
								for(int index$sample$4 = 0; index$sample$4 < noSamples; index$sample$4 += 1) {
									if(true) {
										for(int index$sample117$5 = 0; index$sample117$5 < noStates; index$sample117$5 += 1) {
											int distributionTempVariable$var111$7 = index$sample117$5;
											double cv$probabilitySample117Value6 = (1.0 * distribution$sample117[((index$sample$4 - 0) / 1)][index$sample117$5]);
											int traceTempVariable$currentState$8_1 = distributionTempVariable$var111$7;
											if((index$sample$4 == sample)) {
												if((0 == timeStep$var145)) {
													if((var90 == traceTempVariable$currentState$8_1)) {
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + cv$probabilitySample117Value6);
																			if(metric_valid_g[sample][timeStep$var145])
																				cv$sum = (cv$sum + cv$probabilitySample117Value6);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int sample = 0; sample < noSamples; sample += 1) {
						for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
							if(fixedFlag$sample136) {
								for(int index$sample$13_1 = 0; index$sample$13_1 < noSamples; index$sample$13_1 += 1) {
									for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$13_1]; timeStep$var122 += 1) {
										if((index$sample$13_1 == sample)) {
											if((timeStep$var122 == timeStep$var145)) {
												if((var90 == st[sample][timeStep$var145])) {
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + 1.0);
																		if(metric_valid_g[sample][timeStep$var145])
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
								for(int index$sample$14 = 0; index$sample$14 < noSamples; index$sample$14 += 1) {
									for(int timeStep$var122 = 1; timeStep$var122 < length$metric[index$sample$14]; timeStep$var122 += 1) {
										if(true) {
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var130$18 = index$sample136$16;
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$sample$14 - 0) / 1)][((timeStep$var122 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$currentState$19_1 = distributionTempVariable$var130$18;
												if((index$sample$14 == sample)) {
													if((timeStep$var122 == timeStep$var145)) {
														if((var90 == traceTempVariable$currentState$19_1)) {
															{
																{
																	{
																		{
																			{
																				cv$count = (cv$count + cv$probabilitySample136Value17);
																				if(metric_valid_g[sample][timeStep$var145])
																					cv$sum = (cv$sum + cv$probabilitySample136Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var91 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		metric_valid_bias[var90] = var91;
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var27$countGlobal = new double[cv$max];
		}
		{
			int cv$max = 0;
			for(int var39 = 0; var39 < noStates; var39 += 1)
				cv$max = Math.max(cv$max, noStates);
			cv$var40$countGlobal = new double[cv$max];
		}
		{
			int cv$var41$max = noStates;
			cv$distributionAccumulator$var129 = new double[cv$var41$max];
		}
		{
			cv$var111$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			guard$sample117gaussian169$global = new boolean[cv$max_sample][cv$max_timeStep$var145];
		}
		{
			int cv$var41$max = noStates;
			cv$var130$stateProbabilityGlobal = new double[cv$var41$max];
		}
		{
			int cv$max_sample = 0;
			int cv$max_timeStep$var145 = 0;
			for(int sample = 0; sample < length$metric.length; sample += 1)
				cv$max_timeStep$var145 = Math.max(cv$max_timeStep$var145, ((length$metric[sample] - 0) / 1));
			cv$max_sample = Math.max(cv$max_sample, ((length$metric.length - 0) / 1));
			guard$sample136gaussian169$global = new boolean[cv$max_sample][cv$max_timeStep$var145];
		}
		{
			cv$var150$stateProbabilityGlobal = new double[2];
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
				for(int var39 = 0; var39 < noStates; var39 += 1)
					m[var39] = new double[noStates];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					st[sample] = new int[length$metric[sample]];
			}
		}
		if(!setFlag$metric_g) {
			{
				metric_g = new double[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					metric_g[sample] = new double[length$metric[sample]];
			}
		}
		if(!setFlag$metric_valid_g) {
			{
				metric_valid_g = new boolean[length$metric.length][];
				for(int sample = 0; sample < length$metric.length; sample += 1)
					metric_valid_g[sample] = new boolean[length$metric[sample]];
			}
		}
		if(!setFlag$metric_mean) {
			{
				metric_mean = new double[noStates];
			}
		}
		if(!setFlag$metric_var) {
			{
				metric_var = new double[noStates];
			}
		}
		if(!setFlag$metric_valid_bias) {
			{
				metric_valid_bias = new double[noStates];
			}
		}
		{
			distribution$sample117 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				distribution$sample117[((sample - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample136 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][][];
			for(int sample = 0; sample < length$metric.length; sample += 1) {
				double[][] subarray$0 = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)][];
				distribution$sample136[((sample - 0) / 1)] = subarray$0;
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					subarray$0[((timeStep$var122 - 1) / 1)] = new double[noStates];
			}
		}
		{
			logProbability$var110 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample117 = new double[((((length$metric.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var129 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var129[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample136 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample136[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var149 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var149[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample158 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample158[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var159 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$var159[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample170 = new double[((((length$metric.length - 1) - 0) / 1) + 1)][];
			for(int sample = 0; sample < length$metric.length; sample += 1)
				logProbability$sample170[((sample - 0) / 1)] = new double[((((length$metric[sample] - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var108 = st[sample];
			if(!fixedFlag$sample117)
				var108[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var123 = st[sample];
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				if(!fixedFlag$sample136)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if(metric_valid_g[sample][timeStep$var145]) {
					if(!(fixedFlag$sample158 && fixedFlag$sample170))
						metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			double[] cv$distribution$sample117 = distribution$sample117[((sample - 0) / 1)];
			for(int index$var110 = 0; index$var110 < noStates; index$var110 += 1) {
				double cv$value = (((0.0 <= index$var110) && (index$var110 < initialStateDistribution.length))?initialStateDistribution[index$var110]:0.0);
				if(!fixedFlag$sample117)
					cv$distribution$sample117[index$var110] = cv$value;
			}
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				double[] cv$distribution$sample136 = distribution$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)];
				for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1) {
					if(!fixedFlag$sample136)
						cv$distribution$sample136[index$var129] = 0.0;
				}
				if(fixedFlag$sample117) {
					for(int index$sample$1_1 = 0; index$sample$1_1 < noSamples; index$sample$1_1 += 1) {
						if((index$sample$1_1 == sample)) {
							if((0 == (timeStep$var122 - 1))) {
								for(int var39 = 0; var39 < noStates; var39 += 1) {
									if((var39 == st[sample][(timeStep$var122 - 1)])) {
										{
											if(!fixedFlag$sample136) {
												double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
												for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
													cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (1.0 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
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
							for(int index$sample117$3 = 0; index$sample117$3 < noStates; index$sample117$3 += 1) {
								int distributionTempVariable$var111$5 = index$sample117$3;
								double cv$probabilitySample117Value4 = (1.0 * distribution$sample117[((index$sample$2 - 0) / 1)][index$sample117$3]);
								int traceTempVariable$var127$6_1 = distributionTempVariable$var111$5;
								if((index$sample$2 == sample)) {
									if((0 == (timeStep$var122 - 1))) {
										for(int var39 = 0; var39 < noStates; var39 += 1) {
											if((var39 == traceTempVariable$var127$6_1)) {
												{
													if(!fixedFlag$sample136) {
														double[] var128 = m[traceTempVariable$var127$6_1];
														for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
															cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample117Value4 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample136) {
					for(int index$sample$9_1 = 0; index$sample$9_1 < noSamples; index$sample$9_1 += 1) {
						for(int index$timeStep$9_2 = 1; index$timeStep$9_2 < length$metric[index$sample$9_1]; index$timeStep$9_2 += 1) {
							if((index$sample$9_1 == sample)) {
								if((index$timeStep$9_2 == (timeStep$var122 - 1))) {
									for(int var39 = 0; var39 < noStates; var39 += 1) {
										if((var39 == st[sample][(timeStep$var122 - 1)])) {
											{
												if(!fixedFlag$sample136) {
													double[] var128 = m[st[sample][(timeStep$var122 - 1)]];
													for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
														cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (1.0 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
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
						for(int index$timeStep$11 = 1; index$timeStep$11 < length$metric[index$sample$10]; index$timeStep$11 += 1) {
							if(true) {
								for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
									int distributionTempVariable$var130$14 = index$sample136$12;
									double cv$probabilitySample136Value13 = (1.0 * distribution$sample136[((index$sample$10 - 0) / 1)][((index$timeStep$11 - 1) / 1)][index$sample136$12]);
									int traceTempVariable$var127$15_1 = distributionTempVariable$var130$14;
									if((index$sample$10 == sample)) {
										if((index$timeStep$11 == (timeStep$var122 - 1))) {
											for(int var39 = 0; var39 < noStates; var39 += 1) {
												if((var39 == traceTempVariable$var127$15_1)) {
													{
														if(!fixedFlag$sample136) {
															double[] var128 = m[traceTempVariable$var127$15_1];
															for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1)
																cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] + (cv$probabilitySample136Value13 * (((0.0 <= index$var129) && (index$var129 < var128.length))?var128[index$var129]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				double cv$var129$sum = 0.0;
				for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1) {
					if(!fixedFlag$sample136)
						cv$var129$sum = (cv$var129$sum + cv$distribution$sample136[index$var129]);
				}
				for(int index$var129 = 0; index$var129 < noStates; index$var129 += 1) {
					if(!fixedFlag$sample136)
						cv$distribution$sample136[index$var129] = (cv$distribution$sample136[index$var129] / cv$var129$sum);
				}
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if(metric_valid_g[sample][timeStep$var145]) {
					if(!(fixedFlag$sample158 && fixedFlag$sample170))
						metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample30)
			DistributionSampling.sampleDirichlet(RNG$, v, initialStateDistribution);
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var108 = st[sample];
			if(!fixedFlag$sample117)
				var108[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var123 = st[sample];
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				if(!fixedFlag$sample136)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
			boolean[] metric_valid_1d = metric_valid_g[sample];
			double[] metric_1d = metric_g[sample];
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
				if(!fixedFlag$sample158)
					metric_valid_1d[timeStep$var145] = DistributionSampling.sampleBernoulli(RNG$, metric_valid_bias[st[sample][timeStep$var145]]);
				if(metric_valid_g[sample][timeStep$var145]) {
					if(!(fixedFlag$sample158 && fixedFlag$sample170))
						metric_1d[timeStep$var145] = ((Math.sqrt(metric_var[st[sample][timeStep$var145]]) * DistributionSampling.sampleGaussian(RNG$)) + metric_mean[st[sample][timeStep$var145]]);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample30)
				sample30();
			for(int var39 = 0; var39 < noStates; var39 += 1) {
				if(!fixedFlag$sample43)
					sample43(var39);
			}
			for(int var58 = 0; var58 < noStates; var58 += 1) {
				if(!fixedFlag$sample63)
					sample63(var58);
			}
			for(int var74 = 0; var74 < noStates; var74 += 1) {
				if(!fixedFlag$sample79)
					sample79(var74);
			}
			for(int var90 = 0; var90 < noStates; var90 += 1) {
				if(!fixedFlag$sample95)
					sample95(var90);
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(!fixedFlag$sample117)
					sample117(sample);
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
					if(!fixedFlag$sample136)
						sample136(sample, timeStep$var122);
				}
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1) {
					if(!fixedFlag$sample158)
						sample158(sample, timeStep$var145);
				}
			}
		} else {
			for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
				for(int timeStep$var145 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var145 >= ((0 - 1) + 1); timeStep$var145 -= 1) {
					if(!fixedFlag$sample158)
						sample158(sample, timeStep$var145);
				}
				for(int timeStep$var122 = (length$metric[sample] - ((((length$metric[sample] - 1) - 1) % 1) + 1)); timeStep$var122 >= ((1 - 1) + 1); timeStep$var122 -= 1) {
					if(!fixedFlag$sample136)
						sample136(sample, timeStep$var122);
				}
				if(!fixedFlag$sample117)
					sample117(sample);
			}
			for(int var90 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var90 >= ((0 - 1) + 1); var90 -= 1) {
				if(!fixedFlag$sample95)
					sample95(var90);
			}
			for(int var74 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var74 >= ((0 - 1) + 1); var74 -= 1) {
				if(!fixedFlag$sample79)
					sample79(var74);
			}
			for(int var58 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var58 >= ((0 - 1) + 1); var58 -= 1) {
				if(!fixedFlag$sample63)
					sample63(var58);
			}
			for(int var39 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var39 >= ((0 - 1) + 1); var39 -= 1) {
				if(!fixedFlag$sample43)
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
			logProbability$var110[((sample - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample117) {
			for(int sample = 0; sample < noSamples; sample += 1)
				logProbability$sample117[((sample - 0) / 1)] = 0.0;
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
				logProbability$var129[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample136) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1)
					logProbability$sample136[((sample - 0) / 1)][((timeStep$var122 - 1) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
				logProbability$var149[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = 0.0;
		}
		logProbability$metric_g = 0.0;
		logProbability$metric_valid_1d = 0.0;
		logProbability$metric_valid_g = 0.0;
		if(!fixedProbFlag$sample158) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample158[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = 0.0;
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
				logProbability$var159[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = 0.0;
		}
		logProbability$metric_1d = 0.0;
		if(!fixedProbFlag$sample170) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var145 = 0; timeStep$var145 < length$metric[sample]; timeStep$var145 += 1)
					logProbability$sample170[((sample - 0) / 1)][((timeStep$var145 - 0) / 1)] = 0.0;
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
		for(int var39 = 0; var39 < noStates; var39 += 1) {
			double[] var40 = m[var39];
			if(!fixedFlag$sample43)
				DistributionSampling.sampleDirichlet(RNG$, v, var40);
		}
		for(int var58 = 0; var58 < noStates; var58 += 1) {
			if(!fixedFlag$sample63)
				metric_mean[var58] = (0.0 + ((100.0 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		for(int var74 = 0; var74 < noStates; var74 += 1) {
			if(!fixedFlag$sample79)
				metric_var[var74] = DistributionSampling.sampleInverseGamma(RNG$, 1.0, 1.0);
		}
		for(int var90 = 0; var90 < noStates; var90 += 1) {
			if(!fixedFlag$sample95)
				metric_valid_bias[var90] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			int[] var108 = st[sample];
			if(!fixedFlag$sample117)
				var108[0] = DistributionSampling.sampleCategorical(RNG$, initialStateDistribution);
			int[] var123 = st[sample];
			for(int timeStep$var122 = 1; timeStep$var122 < length$metric[sample]; timeStep$var122 += 1) {
				if(!fixedFlag$sample136)
					var123[timeStep$var122] = DistributionSampling.sampleCategorical(RNG$, m[st[sample][(timeStep$var122 - 1)]]);
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		{
			boolean[][] cv$source1 = metric_valid;
			boolean[][] cv$target1 = metric_valid_g;
			int cv$length1 = cv$target1.length;
			for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
				boolean[] cv$source2 = cv$source1[cv$index1];
				boolean[] cv$target2 = cv$target1[cv$index1];
				int cv$length2 = cv$target2.length;
				for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
					cv$target2[cv$index2] = cv$source2[cv$index2];
			}
		}
		for(int sample = (noSamples - ((((noSamples - 1) - 0) % 1) + 1)); sample >= ((0 - 1) + 1); sample -= 1) {
			for(int timeStep$var145 = (length$metric[sample] - ((((length$metric[sample] - 1) - 0) % 1) + 1)); timeStep$var145 >= ((0 - 1) + 1); timeStep$var145 -= 1)
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