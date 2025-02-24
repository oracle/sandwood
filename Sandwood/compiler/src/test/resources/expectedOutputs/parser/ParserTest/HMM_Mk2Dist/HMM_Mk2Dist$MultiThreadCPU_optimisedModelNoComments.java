package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	private double[][] bias;
	private double[][] cv$distributionAccumulator$var122;
	private double[] cv$distributionAccumulator$var91;
	private double[][] cv$var123$stateProbabilityGlobal;
	private double[][] cv$var42$countGlobal;
	private double[][] cv$var56$countGlobal;
	private double[] cv$var75$countGlobal;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[][] cv$var92$stateProbabilityGlobal;
	private double[][][] distribution$sample126;
	private double[][] distribution$sample95;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample126 = false;
	private boolean fixedFlag$sample159 = false;
	private boolean fixedFlag$sample42 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample78 = false;
	private boolean fixedFlag$sample80 = false;
	private boolean fixedFlag$sample95 = false;
	private boolean fixedProbFlag$sample126 = false;
	private boolean fixedProbFlag$sample159 = false;
	private boolean fixedProbFlag$sample42 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean fixedProbFlag$sample80 = false;
	private boolean fixedProbFlag$sample95 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[][] logProbability$sample126;
	private double[][] logProbability$sample159;
	private double[] logProbability$sample95;
	private double logProbability$st;
	private double[][] logProbability$var122;
	private double[][] logProbability$var154;
	private double logProbability$var30;
	private double logProbability$var42;
	private double logProbability$var44;
	private double logProbability$var56;
	private double logProbability$var74;
	private double logProbability$var76;
	private double[] logProbability$var91;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private boolean setFlag$bias = false;
	private boolean setFlag$events = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private boolean setFlag$weights = false;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2Dist$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[][] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample159 = false;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final void set$events(int[][] cv$value) {
		events = cv$value;
		setFlag$events = true;
		fixedProbFlag$sample159 = false;
	}

	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	@Override
	public final void set$eventsMeasured(int[][] cv$value) {
		eventsMeasured = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	@Override
	public final void set$fixedFlag$sample126(boolean cv$value) {
		fixedFlag$sample126 = cv$value;
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample159() {
		return fixedFlag$sample159;
	}

	@Override
	public final void set$fixedFlag$sample159(boolean cv$value) {
		fixedFlag$sample159 = cv$value;
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value) {
		fixedFlag$sample42 = cv$value;
		fixedProbFlag$sample42 = (cv$value && fixedProbFlag$sample42);
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (cv$value && fixedProbFlag$sample57);
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value) {
		fixedFlag$sample78 = cv$value;
		fixedProbFlag$sample78 = (cv$value && fixedProbFlag$sample78);
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value) {
		fixedFlag$sample80 = cv$value;
		fixedProbFlag$sample80 = (cv$value && fixedProbFlag$sample80);
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value) {
		fixedFlag$sample95 = cv$value;
		fixedProbFlag$sample95 = (cv$value && fixedProbFlag$sample95);
		fixedProbFlag$sample126 = (cv$value && fixedProbFlag$sample126);
		fixedProbFlag$sample159 = (cv$value && fixedProbFlag$sample159);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
		fixedProbFlag$sample80 = false;
		fixedProbFlag$sample95 = false;
	}

	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	@Override
	public final void set$length$eventsMeasured(int[] cv$value) {
		length$eventsMeasured = cv$value;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$events() {
		return logProbability$events;
	}

	@Override
	public final double get$logProbability$initialState() {
		return logProbability$initialState;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample126 = false;
	}

	@Override
	public final int get$noEvents() {
		return noEvents;
	}

	@Override
	public final void set$noEvents(int cv$value) {
		noEvents = cv$value;
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
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[][] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[][] cv$value) {
		st = cv$value;
		setFlag$st = true;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample126 = false;
		fixedProbFlag$sample159 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	@Override
	public final double[] get$v2() {
		return v2;
	}

	@Override
	public final double[] get$weights() {
		return weights;
	}

	@Override
	public final void set$weights(double[] cv$value) {
		weights = cv$value;
		setFlag$weights = true;
		fixedProbFlag$sample78 = false;
		fixedProbFlag$sample80 = false;
	}

	private final void logProbabilityDistribution$sample126() {
		if(!fixedProbFlag$sample126) {
			if(fixedFlag$sample126) {
				double cv$accumulator = 0.0;
				for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[i$var104][j$var115];
						if((1 == j$var115)) {
							if(fixedFlag$sample95) {
								int var41 = st[i$var104][0];
								if(((0 <= var41) && (var41 < noStates))) {
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[i$var104][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample95$6 = 0; index$sample95$6 < noStates; index$sample95$6 += 1) {
									double cv$probabilitySample95Value7 = distribution$sample95[i$var104][index$sample95$6];
									int var41 = st[i$var104][0];
									if(((0 <= var41) && (var41 < noStates))) {
										double cv$weightedProbability = (Math.log(cv$probabilitySample95Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[i$var104][0]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
										if((cv$weightedProbability < cv$distributionAccumulator))
											cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
										else {
											if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
												cv$distributionAccumulator = cv$weightedProbability;
											else
												cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
										}
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value7);
									}
								}
							}
						}
						if((2 <= j$var115)) {
							int var41 = st[i$var104][(j$var115 - 1)];
							if(((0 <= var41) && (var41 < noStates))) {
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[i$var104][(j$var115 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$var122[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
						logProbability$sample126[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample126 = (fixedFlag$sample42 && fixedFlag$sample95);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = logProbability$sample126[i$var104][(j$var115 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var122[i$var104][(j$var115 - 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample126)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample159() {
		if(!fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = (events[i$var136][j$var149] - 1);
					if(fixedFlag$sample126) {
						int var55 = st[i$var136][j$var149];
						if(((0 <= var55) && (var55 < noStates))) {
							cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(bias[st[i$var136][j$var149]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample126$13 = 0; index$sample126$13 < noStates; index$sample126$13 += 1) {
							double cv$probabilitySample126Value14 = distribution$sample126[i$var136][(j$var149 - 1)][index$sample126$13];
							int var55 = st[i$var136][j$var149];
							if(((0 <= var55) && (var55 < noStates))) {
								double cv$weightedProbability = (Math.log(cv$probabilitySample126Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(bias[st[i$var136][j$var149]][cv$sampleValue]):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value14);
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var154[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
					logProbability$sample159[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample159 = (((fixedFlag$sample159 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = logProbability$sample159[i$var136][(j$var149 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var154[i$var136][(j$var149 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample95() {
		if(!fixedProbFlag$sample95) {
			if(fixedFlag$sample95) {
				double cv$accumulator = 0.0;
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					int cv$sampleValue = st[i$var87][0];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[initialState][cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var91[i$var87] = cv$distributionAccumulator;
					logProbability$sample95[i$var87] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample95 = (fixedFlag$sample42 && fixedFlag$sample80);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = logProbability$sample95[i$var87];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var91[i$var87] = cv$rvAccumulator;
			}
			if(fixedFlag$sample95)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!fixedProbFlag$sample126) {
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					int cv$sampleValue = st[i$var104][j$var115];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[st[i$var104][(j$var115 - 1)]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var122[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
					logProbability$sample126[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = logProbability$sample126[i$var104][(j$var115 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var122[i$var104][(j$var115 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample159() {
		if(!fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					int cv$sampleValue = (events[i$var136][j$var149] - 1);
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents))?Math.log(bias[st[i$var136][j$var149]][cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var154[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
					logProbability$sample159[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample159 = (((fixedFlag$sample159 && fixedFlag$sample57) && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = logProbability$sample159[i$var136][(j$var149 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var154[i$var136][(j$var149 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < noStates; var41 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var41], v, noStates));
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			logProbability$var30 = logProbability$var42;
			logProbability$m = (logProbability$m + logProbability$var42);
			logProbability$$model = (logProbability$$model + logProbability$var42);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var42);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$sampleAccumulator = 0.0;
			for(int var55 = 0; var55 < noStates; var55 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(bias[var55], v2, noEvents));
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$var56 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			logProbability$var44 = logProbability$var56;
			logProbability$bias = (logProbability$bias + logProbability$var56);
			logProbability$$model = (logProbability$$model + logProbability$var56);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var56);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(weights, v, noStates);
			logProbability$var74 = cv$distributionAccumulator;
			logProbability$weights = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample78 = fixedFlag$sample78;
		} else {
			logProbability$var74 = logProbability$weights;
			logProbability$$model = (logProbability$$model + logProbability$weights);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + logProbability$weights);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
			double cv$distributionAccumulator = (((0.0 <= initialState) && (initialState < noStates))?Math.log(weights[initialState]):Double.NEGATIVE_INFINITY);
			logProbability$var76 = cv$distributionAccumulator;
			logProbability$initialState = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedFlag$sample78);
		} else {
			logProbability$var76 = logProbability$initialState;
			logProbability$$model = (logProbability$$model + logProbability$initialState);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialState);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				int cv$sampleValue = st[i$var87][0];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates))?Math.log(m[initialState][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var91[i$var87] = cv$distributionAccumulator;
				logProbability$sample95[i$var87] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = logProbability$sample95[i$var87];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var91[i$var87] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		int cv$numNumStates = 0;
		if((1 == j$var115)) {
			if(fixedFlag$sample95) {
				int var41 = st[i$var104][0];
				if(((0 <= var41) && (var41 < noStates)))
					cv$numNumStates = Math.max(0, noStates);
			} else {
				if((0 < noStates)) {
					int var41 = st[i$var104][0];
					if(((0 <= var41) && (var41 < noStates)))
						cv$numNumStates = noStates;
				}
			}
		}
		if(fixedFlag$sample126) {
			if((2 <= j$var115)) {
				int var41 = st[i$var104][(j$var115 - 1)];
				if(((0 <= var41) && (var41 < noStates)))
					cv$numNumStates = Math.max(cv$numNumStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$j$14 = (j$var115 - 1);
				if(((1 <= index$j$14) && !(index$j$14 == j$var115))) {
					int var41 = st[i$var104][(j$var115 - 1)];
					if(((0 <= var41) && (var41 < noStates)))
						cv$numNumStates = Math.max(cv$numNumStates, noStates);
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == j$var115)) {
				if(fixedFlag$sample95) {
					int var41 = st[i$var104][0];
					if(((0 <= var41) && (var41 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[st[i$var104][0]][cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$eventsMeasured[i$var104])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var55 = st[i$var104][1];
							if(((0 <= var55) && (var55 < noStates))) {
								cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var104][1]) && (events[i$var104][1] < (noEvents + 1)))?Math.log(bias[cv$valuePos][(events[i$var104][1] - 1)]):Double.NEGATIVE_INFINITY);
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample95$26 = 0; index$sample95$26 < noStates; index$sample95$26 += 1) {
						double cv$probabilitySample95Value27 = distribution$sample95[i$var104][index$sample95$26];
						int var41 = st[i$var104][0];
						if(((0 <= var41) && (var41 < noStates))) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample95Value27);
							double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample95Value27) + ((cv$valuePos < noStates)?Math.log(m[st[i$var104][0]][cv$valuePos]):Double.NEGATIVE_INFINITY));
							if((1 < length$eventsMeasured[i$var104])) {
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var55 = st[i$var104][1];
								if(((0 <= var55) && (var55 < noStates))) {
									cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var104][1]) && (events[i$var104][1] < (noEvents + 1)))?Math.log(bias[cv$valuePos][(events[i$var104][1] - 1)]):Double.NEGATIVE_INFINITY);
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
				}
			}
			int index$j$34 = (j$var115 - 1);
			if(((1 <= index$j$34) && !(index$j$34 == j$var115))) {
				for(int index$sample126$35 = 0; index$sample126$35 < noStates; index$sample126$35 += 1) {
					double cv$probabilitySample126Value36 = distribution$sample126[i$var104][(index$j$34 - 1)][index$sample126$35];
					int var41 = st[i$var104][(j$var115 - 1)];
					if(((0 <= var41) && (var41 < noStates))) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample126Value36);
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample126Value36) + ((cv$valuePos < noStates)?Math.log(m[cv$valuePos][cv$valuePos]):Double.NEGATIVE_INFINITY));
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						int var55 = st[i$var104][j$var115];
						if(((0 <= var55) && (var55 < noStates))) {
							cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var104][j$var115]) && (events[i$var104][j$var115] < (noEvents + 1)))?Math.log(bias[index$sample126$35][(events[i$var104][j$var115] - 1)]):Double.NEGATIVE_INFINITY);
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
			int index$j$61_3 = (j$var115 + 1);
			if((index$j$61_3 < length$eventsMeasured[i$var104])) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var104];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				int var41 = st[i$var104][(index$j$61_3 - 1)];
				if(((0 <= var41) && (var41 < noStates))) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == j$var115)) {
						if(fixedFlag$sample95) {
							int index$var41$72_1 = st[i$var104][0];
							if(((0 <= index$var41$72_1) && (index$var41$72_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample95$68 = 0; index$sample95$68 < noStates; index$sample95$68 += 1) {
								int index$var41$73_1 = st[i$var104][0];
								if(((0 <= index$var41$73_1) && (index$var41$73_1 < noStates)))
									scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample95[i$var104][index$sample95$68]);
							}
						}
					}
					int index$j$76 = (j$var115 - 1);
					if((((1 <= index$j$76) && !(index$j$76 == j$var115)) && !(index$j$76 == index$j$61_3))) {
						for(int index$sample126$77 = 0; index$sample126$77 < noStates; index$sample126$77 += 1) {
							int index$var41$82_1 = st[i$var104][(j$var115 - 1)];
							if(((0 <= index$var41$82_1) && (index$var41$82_1 < noStates)))
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample126[i$var104][(index$j$76 - 1)][index$sample126$77]);
						}
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample126[i$var104][(index$j$61_3 - 1)];
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
		double[] cv$localProbability = distribution$sample126[i$var104][(j$var115 - 1)];
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

	private final void sample42(int var41, int threadID$cv$var41, Rng RNG$) {
		double[] cv$countLocal = cv$var42$countGlobal[threadID$cv$var41];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(((var41 == initialState) && fixedFlag$sample95)) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				cv$countLocal[st[i$var87][0]] = (cv$countLocal[st[i$var87][0]] + 1.0);
		}
		if(fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				if(((var41 == st[i$var104][0]) && (1 < length$eventsMeasured[i$var104]))) {
					if(fixedFlag$sample95)
						cv$countLocal[st[i$var104][1]] = (cv$countLocal[st[i$var104][1]] + 1.0);
					else {
						for(int index$sample95$9 = 0; index$sample95$9 < noStates; index$sample95$9 += 1)
							cv$countLocal[st[i$var104][1]] = (cv$countLocal[st[i$var104][1]] + distribution$sample95[i$var104][index$sample95$9]);
					}
				}
			}
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 2; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					if((var41 == st[i$var104][(j$var115 - 1)]))
						cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
				}
			}
		}
		if(((var41 == initialState) && !fixedFlag$sample95)) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample95[i$var87][cv$loopIndex]);
			}
		}
		if(!fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				if(((var41 == st[i$var104][0]) && (1 < length$eventsMeasured[i$var104]))) {
					if(fixedFlag$sample95) {
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample126[i$var104][0][cv$loopIndex]);
					} else {
						for(int index$sample95$49 = 0; index$sample95$49 < noStates; index$sample95$49 += 1) {
							double cv$distributionProbability = distribution$sample95[i$var104][index$sample95$49];
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[i$var104][0][cv$loopIndex] * cv$distributionProbability));
						}
					}
				}
			}
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					if((var41 == st[i$var104][(j$var115 - 1)])) {
						int index$j$59 = (j$var115 - 1);
						if((1 <= index$j$59)) {
							for(int index$sample126$60 = 0; index$sample126$60 < noStates; index$sample126$60 += 1) {
								double cv$distributionProbability = distribution$sample126[i$var104][(index$j$59 - 1)][index$sample126$60];
								for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
									cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample126[i$var104][(j$var115 - 1)][cv$loopIndex] * cv$distributionProbability));
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var41], noStates);
	}

	private final void sample57(int var55, int threadID$cv$var55, Rng RNG$) {
		double[] cv$countLocal = cv$var56$countGlobal[threadID$cv$var55];
		for(int cv$loopIndex = 0; cv$loopIndex < noEvents; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
				if((var55 == st[i$var136][j$var149])) {
					if(fixedFlag$sample126)
						cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
					else {
						for(int index$sample126$16 = 0; index$sample126$16 < noStates; index$sample126$16 += 1)
							cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + distribution$sample126[i$var136][(j$var149 - 1)][index$sample126$16]);
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, bias[var55], noEvents);
	}

	private final void sample78() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var75$countGlobal[cv$loopIndex] = 0.0;
		cv$var75$countGlobal[initialState] = (cv$var75$countGlobal[initialState] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var75$countGlobal, weights, noStates);
	}

	private final void sample80() {
		int cv$numNumStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			initialState = cv$valuePos;
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample95) {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
					cv$accumulatedProbabilities = ((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates))?Math.log(m[cv$valuePos][st[i$var87][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			} else {
				for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						cv$distributionAccumulator$var91[cv$i] = 0.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var91, 1.0, m[cv$valuePos], noStates);
					double[] cv$sampleDistribution = distribution$sample95[i$var87];
					double cv$overlap = 0.0;
					for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
						double cv$normalisedDistValue = cv$distributionAccumulator$var91[cv$i];
						double cv$sampleDistValue = cv$sampleDistribution[cv$i];
						if((cv$sampleDistValue < cv$normalisedDistValue))
							cv$overlap = (cv$overlap + cv$sampleDistValue);
						else
							cv$overlap = (cv$overlap + cv$normalisedDistValue);
					}
					cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(cv$overlap));
				}
			}
			cv$var77$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var77$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var77$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var77$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var77$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$var77$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var77$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$var77$stateProbabilityGlobal, cv$numNumStates);
	}

	private final void sample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		int cv$numNumStates = Math.max(0, noStates);
		double[] cv$stateProbabilityLocal = cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = ((cv$valuePos < noStates)?Math.log(m[initialState][cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((1 < length$eventsMeasured[i$var87])) {
				if(fixedFlag$sample126) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					int var41 = st[i$var87][0];
					if(((0 <= var41) && (var41 < noStates))) {
						cv$accumulatedConsumerProbabilities = (((0.0 <= st[i$var87][1]) && (st[i$var87][1] < noStates))?Math.log(m[cv$valuePos][st[i$var87][1]]):Double.NEGATIVE_INFINITY);
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
					double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var122[threadID$cv$i$var87];
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						cv$accumulatedConsumerDistributions[cv$i] = 0.0;
					double cv$reachedDistributionProbability = 0.0;
					int var41 = st[i$var87][0];
					if(((0 <= var41) && (var41 < noStates))) {
						cv$reachedDistributionProbability = 1.0;
						DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos], noStates);
					}
					double[] cv$sampleDistribution = distribution$sample126[i$var87][0];
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
			}
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample95[i$var87];
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
		{
			int cv$threadCount = threadCount();
			cv$var42$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var42$countGlobal[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$var56$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var56$countGlobal[cv$index] = new double[noEvents];
		}
		cv$var75$countGlobal = new double[noStates];
		cv$distributionAccumulator$var91 = new double[noStates];
		cv$var77$stateProbabilityGlobal = new double[noStates];
		{
			int cv$threadCount = threadCount();
			cv$distributionAccumulator$var122 = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$distributionAccumulator$var122[cv$index] = new double[noStates];
		}
		{
			int cv$threadCount = threadCount();
			cv$var92$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var92$stateProbabilityGlobal[cv$index] = new double[noStates];
		}
		int cv$threadCount = threadCount();
		cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var123$stateProbabilityGlobal[cv$index] = new double[noStates];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		v2 = new double[noEvents];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var41 = 0; var41 < noStates; var41 += 1)
				m[var41] = new double[noStates];
		}
		if(!setFlag$bias) {
			bias = new double[noStates][];
			for(int var55 = 0; var55 < noStates; var55 += 1)
				bias[var55] = new double[noEvents];
		}
		if(!setFlag$st) {
			st = new int[length$eventsMeasured.length][];
			for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
				st[i$var69] = new int[length$eventsMeasured[i$var69]];
		}
		if(!setFlag$weights)
			weights = new double[noStates];
		if(!setFlag$events) {
			events = new int[length$eventsMeasured.length][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				events[i$var136] = new int[length$eventsMeasured[i$var136]];
		}
		distribution$sample126 = new double[length$eventsMeasured.length][][];
		for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1) {
			double[][] subarray$0 = new double[(length$eventsMeasured[i$var104] - 1)][];
			distribution$sample126[i$var104] = subarray$0;
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
				subarray$0[(j$var115 - 1)] = new double[noStates];
		}
		distribution$sample95 = new double[length$eventsMeasured.length][];
		for(int i$var87 = 0; i$var87 < length$eventsMeasured.length; i$var87 += 1)
			distribution$sample95[i$var87] = new double[noStates];
		logProbability$var91 = new double[length$eventsMeasured.length];
		logProbability$sample95 = new double[length$eventsMeasured.length];
		logProbability$var122 = new double[length$eventsMeasured.length][];
		for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
			logProbability$var122[i$var104] = new double[(length$eventsMeasured[i$var104] - 1)];
		logProbability$sample126 = new double[length$eventsMeasured.length][];
		for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
			logProbability$sample126[i$var104] = new double[(length$eventsMeasured[i$var104] - 1)];
		logProbability$var154 = new double[length$eventsMeasured.length][];
		for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
			logProbability$var154[i$var136] = new double[(length$eventsMeasured[i$var136] - 1)];
		logProbability$sample159 = new double[length$eventsMeasured.length][];
		for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
			logProbability$sample159[i$var136] = new double[(length$eventsMeasured[i$var136] - 1)];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		if(!fixedFlag$sample57)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		if(!fixedFlag$sample126)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

		if(!fixedFlag$sample159)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
							int i$var136 = index$i$var136;
							int threadID$i$var136 = threadID$index$i$var136;
							int[] var150 = events[i$var136];
							parallelFor(RNG$1, 1, length$eventsMeasured[i$var136], 1,
								(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
											var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var136][j$var149]], noEvents) + 1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		if(!fixedFlag$sample57)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							double[] cv$distribution$sample95 = distribution$sample95[i$var87];
							double[] var90 = m[initialState];
							for(int index$var91 = 0; index$var91 < noStates; index$var91 += 1)
								cv$distribution$sample95[index$var91] = var90[index$var91];
						}
				}
			);

		if(!fixedFlag$sample126)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
								double[] cv$distribution$sample126 = distribution$sample126[i$var104][(j$var115 - 1)];
								for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
									cv$distribution$sample126[index$var122] = 0.0;
								if((1 == j$var115)) {
									if(fixedFlag$sample95) {
										int var41 = st[i$var104][0];
										if(((0 <= var41) && (var41 < noStates))) {
											double[] var121 = m[st[i$var104][0]];
											for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
												cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + var121[index$var122]);
										}
									} else {
										for(int index$sample95$3 = 0; index$sample95$3 < noStates; index$sample95$3 += 1) {
											double cv$probabilitySample95Value4 = distribution$sample95[i$var104][index$sample95$3];
											int var41 = st[i$var104][0];
											if(((0 <= var41) && (var41 < noStates))) {
												double[] var121 = m[st[i$var104][0]];
												for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
													cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample95Value4 * var121[index$var122]));
											}
										}
									}
								}
								int index$j$11 = (j$var115 - 1);
								if((1 <= index$j$11)) {
									for(int index$sample126$12 = 0; index$sample126$12 < noStates; index$sample126$12 += 1) {
										double cv$probabilitySample126Value13 = distribution$sample126[i$var104][(index$j$11 - 1)][index$sample126$12];
										int var41 = st[i$var104][(j$var115 - 1)];
										if(((0 <= var41) && (var41 < noStates))) {
											double[] var121 = m[st[i$var104][(j$var115 - 1)]];
											for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
												cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample126Value13 * var121[index$var122]));
										}
									}
								}
								double cv$var122$sum = 0.0;
								for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
									cv$var122$sum = (cv$var122$sum + cv$distribution$sample126[index$var122]);
								for(int index$var122 = 0; index$var122 < noStates; index$var122 += 1)
									cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] / cv$var122$sum);
							}
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		if(!fixedFlag$sample57)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		if(!fixedFlag$sample126)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample42)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								sample42(var41, threadID$var41, RNG$1);
					}
				);

			if(!fixedFlag$sample57)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								sample57(var55, threadID$var55, RNG$1);
					}
				);

			if(!fixedFlag$sample78)
				sample78();
			if(!fixedFlag$sample80)
				sample80();
			if(!fixedFlag$sample95)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
								sample95(i$var87, threadID$i$var87, RNG$1);
					}
				);

			if(!fixedFlag$sample126)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
									sample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
					}
				);

		} else {
			if(!fixedFlag$sample126)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
								for(int j$var115 = (length$eventsMeasured[i$var104] - 1); j$var115 >= 1; j$var115 -= 1)
									sample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
					}
				);

			if(!fixedFlag$sample95)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
								sample95(i$var87, threadID$i$var87, RNG$1);
					}
				);

			if(!fixedFlag$sample80)
				sample80();
			if(!fixedFlag$sample78)
				sample78();
			if(!fixedFlag$sample57)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								sample57(var55, threadID$var55, RNG$1);
					}
				);

			if(!fixedFlag$sample42)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								sample42(var41, threadID$var41, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var14, int forEnd$var14, int threadID$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var14 = forStart$var14; var14 < forEnd$var14; var14 += 1)
						v[var14] = 0.1;
			}
		);
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
						v2[var27] = 0.1;
			}
		);
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var30 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = 0.0;
		logProbability$var44 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = 0.0;
		logProbability$var74 = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$weights = 0.0;
		logProbability$var76 = 0.0;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = 0.0;
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
			logProbability$var91[i$var87] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				logProbability$sample95[i$var87] = 0.0;
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
				logProbability$var122[i$var104][(j$var115 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					logProbability$sample126[i$var104][(j$var115 - 1)] = 0.0;
			}
		}
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
				logProbability$var154[i$var136][(j$var149 - 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					logProbability$sample159[i$var136][(j$var149 - 1)] = 0.0;
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
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityDistribution$sample95();
		logProbabilityDistribution$sample126();
		logProbabilityDistribution$sample159();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample42)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, noStates, m[var41]);
				}
			);

		if(!fixedFlag$sample57)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, noEvents, bias[var55]);
				}
			);

		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		if(!fixedFlag$sample95)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState], noStates);
				}
			);

		if(!fixedFlag$sample126)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = st[i$var104];
							for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var104][(j$var115 - 1)]], noStates);
						}
				}
			);

		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int cv$length1 = events.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = eventsMeasured[cv$index1];
			int[] cv$target2 = events[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sampleDistribution();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}