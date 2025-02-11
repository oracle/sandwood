package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	private double[][] bias;
	private double[] cv$distributionAccumulator$var54;
	private double[][] cv$distributionAccumulator$var73;
	private double[][] cv$var25$countGlobal;
	private double[][] cv$var32$countGlobal;
	private double[] cv$var45$countGlobal;
	private double[] cv$var47$stateProbabilityGlobal;
	private double[][] cv$var55$stateProbabilityGlobal;
	private double[][] cv$var74$stateProbabilityGlobal;
	private double[][] distribution$sample61;
	private double[][][] distribution$sample81;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample103 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample34 = false;
	private boolean fixedFlag$sample51 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample61 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample34 = false;
	private boolean fixedProbFlag$sample51 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample61 = false;
	private boolean fixedProbFlag$sample81 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[][] logProbability$sample103;
	private double[] logProbability$sample61;
	private double[][] logProbability$sample81;
	private double logProbability$st;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var27;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var46;
	private double[] logProbability$var54;
	private double[][] logProbability$var73;
	private double[][] logProbability$var93;
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
		fixedProbFlag$sample34 = false;
		fixedProbFlag$sample103 = false;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final void set$events(int[][] cv$value) {
		events = cv$value;
		setFlag$events = true;
		fixedProbFlag$sample103 = false;
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
	public final boolean get$fixedFlag$sample103() {
		return fixedFlag$sample103;
	}

	@Override
	public final void set$fixedFlag$sample103(boolean cv$value) {
		fixedFlag$sample103 = cv$value;
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (cv$value && fixedProbFlag$sample26);
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
	}

	@Override
	public final boolean get$fixedFlag$sample34() {
		return fixedFlag$sample34;
	}

	@Override
	public final void set$fixedFlag$sample34(boolean cv$value) {
		fixedFlag$sample34 = cv$value;
		fixedProbFlag$sample34 = (cv$value && fixedProbFlag$sample34);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample51() {
		return fixedFlag$sample51;
	}

	@Override
	public final void set$fixedFlag$sample51(boolean cv$value) {
		fixedFlag$sample51 = cv$value;
		fixedProbFlag$sample51 = (cv$value && fixedProbFlag$sample51);
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (cv$value && fixedProbFlag$sample53);
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
	}

	@Override
	public final boolean get$fixedFlag$sample61() {
		return fixedFlag$sample61;
	}

	@Override
	public final void set$fixedFlag$sample61(boolean cv$value) {
		fixedFlag$sample61 = cv$value;
		fixedProbFlag$sample61 = (cv$value && fixedProbFlag$sample61);
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (cv$value && fixedProbFlag$sample81);
		fixedProbFlag$sample103 = (cv$value && fixedProbFlag$sample103);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample61 = false;
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
		fixedProbFlag$sample26 = false;
		fixedProbFlag$sample61 = false;
		fixedProbFlag$sample81 = false;
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
		fixedProbFlag$sample61 = false;
		fixedProbFlag$sample81 = false;
		fixedProbFlag$sample103 = false;
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
		fixedProbFlag$sample51 = false;
		fixedProbFlag$sample53 = false;
	}

	private final void logProbabilityDistribution$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = (events[i$var80][j$var88] - 1);
					if(fixedFlag$sample81) {
						int var31 = st[i$var80][j$var88];
						if(((0 <= var31) && (var31 < noStates))) {
							double[] var92 = bias[st[i$var80][j$var88]];
							cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample81$13 = 0; index$sample81$13 < noStates; index$sample81$13 += 1) {
							double cv$probabilitySample81Value14 = distribution$sample81[i$var80][(j$var88 - 1)][index$sample81$13];
							double[] var92 = bias[index$sample81$13];
							double cv$weightedProbability = (Math.log(cv$probabilitySample81Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample81Value14);
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var93[i$var80][(j$var88 - 1)] = cv$distributionAccumulator;
					logProbability$sample103[i$var80][(j$var88 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (((fixedFlag$sample103 && fixedFlag$sample34) && fixedFlag$sample61) && fixedFlag$sample81);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = logProbability$sample103[i$var80][(j$var88 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[i$var80][(j$var88 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample61() {
		if(!fixedProbFlag$sample61) {
			if(fixedFlag$sample61) {
				double cv$accumulator = 0.0;
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					int cv$sampleValue = st[i$var50][0];
					double[] var53 = m[initialState];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var54[i$var50] = cv$distributionAccumulator;
					logProbability$sample61[i$var50] = cv$distributionAccumulator;
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample61 = (fixedFlag$sample26 && fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = logProbability$sample61[i$var50];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[i$var50] = cv$rvAccumulator;
			}
			if(fixedFlag$sample61)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample81() {
		if(!fixedProbFlag$sample81) {
			if(fixedFlag$sample81) {
				double cv$accumulator = 0.0;
				for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
					for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = st[i$var60][j$var66];
						if((1 == j$var66)) {
							if(fixedFlag$sample61) {
								int var24 = st[i$var60][0];
								if(((0 <= var24) && (var24 < noStates))) {
									double[] var72 = m[st[i$var60][0]];
									cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample61$6 = 0; index$sample61$6 < noStates; index$sample61$6 += 1) {
									double cv$probabilitySample61Value7 = distribution$sample61[i$var60][index$sample61$6];
									double[] var72 = m[index$sample61$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample61Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample61Value7);
								}
							}
						}
						if((2 <= j$var66)) {
							int var24 = st[i$var60][(j$var66 - 1)];
							if(((0 <= var24) && (var24 < noStates))) {
								double[] var72 = m[st[i$var60][(j$var66 - 1)]];
								double cv$weightedProbability = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						logProbability$var73[i$var60][(j$var66 - 1)] = cv$distributionAccumulator;
						logProbability$sample81[i$var60][(j$var66 - 1)] = cv$distributionAccumulator;
					}
				}
				logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample81 = (fixedFlag$sample26 && fixedFlag$sample61);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = logProbability$sample81[i$var60][(j$var66 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[i$var60][(j$var66 - 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample81)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					int cv$sampleValue = (events[i$var80][j$var88] - 1);
					double[] var92 = bias[st[i$var80][j$var88]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var92.length))?Math.log(var92[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var93[i$var80][(j$var88 - 1)] = cv$distributionAccumulator;
					logProbability$sample103[i$var80][(j$var88 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = (((fixedFlag$sample103 && fixedFlag$sample34) && fixedFlag$sample61) && fixedFlag$sample81);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
					double cv$rvAccumulator = logProbability$sample103[i$var80][(j$var88 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var93[i$var80][(j$var88 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < noStates; var24 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(m[var24], v));
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			logProbability$var20 = logProbability$var25;
			logProbability$m = (logProbability$m + logProbability$var25);
			logProbability$$model = (logProbability$$model + logProbability$var25);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var25);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!fixedProbFlag$sample34) {
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < noStates; var31 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(bias[var31], v2));
			logProbability$var27 = cv$sampleAccumulator;
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$sampleAccumulator);
			logProbability$$model = (logProbability$$model + cv$sampleAccumulator);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + cv$sampleAccumulator);
			fixedProbFlag$sample34 = fixedFlag$sample34;
		} else {
			logProbability$var27 = logProbability$var32;
			logProbability$bias = (logProbability$bias + logProbability$var32);
			logProbability$$model = (logProbability$$model + logProbability$var32);
			if(fixedFlag$sample34)
				logProbability$$evidence = (logProbability$$evidence + logProbability$var32);
		}
	}

	private final void logProbabilityValue$sample51() {
		if(!fixedProbFlag$sample51) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(weights, v);
			logProbability$var44 = cv$distributionAccumulator;
			logProbability$weights = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample51 = fixedFlag$sample51;
		} else {
			logProbability$var44 = logProbability$weights;
			logProbability$$model = (logProbability$$model + logProbability$weights);
			if(fixedFlag$sample51)
				logProbability$$evidence = (logProbability$$evidence + logProbability$weights);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$distributionAccumulator = (((0.0 <= initialState) && (initialState < weights.length))?Math.log(weights[initialState]):Double.NEGATIVE_INFINITY);
			logProbability$var46 = cv$distributionAccumulator;
			logProbability$initialState = cv$distributionAccumulator;
			logProbability$$model = (logProbability$$model + cv$distributionAccumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$distributionAccumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample51);
		} else {
			logProbability$var46 = logProbability$initialState;
			logProbability$$model = (logProbability$$model + logProbability$initialState);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + logProbability$initialState);
		}
	}

	private final void logProbabilityValue$sample61() {
		if(!fixedProbFlag$sample61) {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				int cv$sampleValue = st[i$var50][0];
				double[] var53 = m[initialState];
				double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var53.length))?Math.log(var53[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				logProbability$var54[i$var50] = cv$distributionAccumulator;
				logProbability$sample61[i$var50] = cv$distributionAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample61 = ((fixedFlag$sample61 && fixedFlag$sample26) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				double cv$rvAccumulator = logProbability$sample61[i$var50];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var54[i$var50] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample61)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					int cv$sampleValue = st[i$var60][j$var66];
					double[] var72 = m[st[i$var60][(j$var66 - 1)]];
					double cv$distributionAccumulator = (((0.0 <= cv$sampleValue) && (cv$sampleValue < var72.length))?Math.log(var72[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					logProbability$var73[i$var60][(j$var66 - 1)] = cv$distributionAccumulator;
					logProbability$sample81[i$var60][(j$var66 - 1)] = cv$distributionAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample81 = ((fixedFlag$sample81 && fixedFlag$sample26) && fixedFlag$sample61);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					double cv$rvAccumulator = logProbability$sample81[i$var60][(j$var66 - 1)];
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var73[i$var60][(j$var66 - 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample81)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample26(int var24, int threadID$cv$var24, Rng RNG$) {
		double[] cv$countLocal = cv$var25$countGlobal[threadID$cv$var24];
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(((var24 == initialState) && fixedFlag$sample61)) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				cv$countLocal[st[i$var50][0]] = (cv$countLocal[st[i$var50][0]] + 1.0);
		}
		if(fixedFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				if((1 < length$eventsMeasured[i$var60])) {
					if(fixedFlag$sample61) {
						if((var24 == st[i$var60][0]))
							cv$countLocal[st[i$var60][1]] = (cv$countLocal[st[i$var60][1]] + 1.0);
					} else
						cv$countLocal[st[i$var60][1]] = (cv$countLocal[st[i$var60][1]] + distribution$sample61[i$var60][var24]);
				}
			}
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 2; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					if((var24 == st[i$var60][(j$var66 - 1)]))
						cv$countLocal[st[i$var60][j$var66]] = (cv$countLocal[st[i$var60][j$var66]] + 1.0);
				}
			}
		}
		if(((var24 == initialState) && !fixedFlag$sample61)) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample61[i$var50][cv$loopIndex]);
			}
		}
		if(!fixedFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				if((1 < length$eventsMeasured[i$var60])) {
					if(fixedFlag$sample61) {
						if((var24 == st[i$var60][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + distribution$sample81[i$var60][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample61[i$var60][var24];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[i$var60][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
					int index$j$59 = (j$var66 - 1);
					if((1 <= index$j$59)) {
						double cv$distributionProbability = distribution$sample81[i$var60][(index$j$59 - 1)][var24];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample81[i$var60][(j$var66 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, m[var24]);
	}

	private final void sample34(int var31, int threadID$cv$var31, Rng RNG$) {
		double[] cv$countLocal = cv$var32$countGlobal[threadID$cv$var31];
		for(int cv$loopIndex = 0; cv$loopIndex < noEvents; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1) {
				if(fixedFlag$sample81) {
					if((var31 == st[i$var80][j$var88]))
						cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + 1.0);
				} else
					cv$countLocal[(events[i$var80][j$var88] - 1)] = (cv$countLocal[(events[i$var80][j$var88] - 1)] + distribution$sample81[i$var80][(j$var88 - 1)][var31]);
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, bias[var31]);
	}

	private final void sample51() {
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var45$countGlobal[cv$loopIndex] = 0.0;
		cv$var45$countGlobal[initialState] = (cv$var45$countGlobal[initialState] + 1.0);
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var45$countGlobal, weights);
	}

	private final void sample53() {
		int cv$noStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			initialState = cv$valuePos;
			double cv$accumulatedProbabilities = ((cv$valuePos < weights.length)?Math.log(weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(fixedFlag$sample61) {
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					double[] cv$temp$1$var53 = m[cv$valuePos];
					cv$accumulatedProbabilities = ((((0.0 <= st[i$var50][0]) && (st[i$var50][0] < cv$temp$1$var53.length))?Math.log(cv$temp$1$var53[st[i$var50][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				for(int i$var50 = 0; i$var50 < samples; i$var50 += 1) {
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						cv$distributionAccumulator$var54[cv$i] = 0.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var54, 1.0, m[cv$valuePos]);
					double[] cv$sampleDistribution = distribution$sample61[i$var50];
					double cv$overlap = 0.0;
					for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
						double cv$normalisedDistValue = cv$distributionAccumulator$var54[cv$i];
						double cv$sampleDistValue = cv$sampleDistribution[cv$i];
						if((cv$sampleDistValue < cv$normalisedDistValue))
							cv$overlap = (cv$overlap + cv$sampleDistValue);
						else
							cv$overlap = (cv$overlap + cv$normalisedDistValue);
					}
					cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(cv$overlap));
				}
			}
			cv$var47$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum;
		double cv$lseMax = cv$var47$stateProbabilityGlobal[0];
		for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
			double cv$lseElementValue = cv$var47$stateProbabilityGlobal[cv$lseIndex];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
		}
		if((cv$lseMax == Double.NEGATIVE_INFINITY))
			cv$logSum = Double.NEGATIVE_INFINITY;
		else {
			double cv$lseSum = 0.0;
			for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
				cv$lseSum = (cv$lseSum + Math.exp((cv$var47$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var47$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$var47$stateProbabilityGlobal[cv$indexName] = Math.exp((cv$var47$stateProbabilityGlobal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$noStates; cv$indexName < cv$var47$stateProbabilityGlobal.length; cv$indexName += 1)
			cv$var47$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$var47$stateProbabilityGlobal);
	}

	private final void sample61(int i$var50, int threadID$cv$i$var50, Rng RNG$) {
		int cv$noStates = Math.max(0, noStates);
		double[] cv$stateProbabilityLocal = cv$var55$stateProbabilityGlobal[threadID$cv$i$var50];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double[] cv$temp$0$var53 = m[initialState];
			double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var53.length)?Math.log(cv$temp$0$var53[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((1 < length$eventsMeasured[i$var50])) {
				if(fixedFlag$sample81) {
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double[] cv$temp$1$var72 = m[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= st[i$var50][1]) && (st[i$var50][1] < cv$temp$1$var72.length))?Math.log(cv$temp$1$var72[st[i$var50][1]]):Double.NEGATIVE_INFINITY);
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
					double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$i$var50];
					for(int cv$i = 0; cv$i < noStates; cv$i += 1)
						cv$accumulatedConsumerDistributions[cv$i] = 0.0;
					double cv$reachedDistributionProbability = 0.0;
					if((cv$valuePos < noStates)) {
						cv$reachedDistributionProbability = 1.0;
						DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, m[cv$valuePos]);
					}
					double[] cv$sampleDistribution = distribution$sample81[i$var50][0];
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
		double[] cv$localProbability = distribution$sample61[i$var50];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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

	private final void sample81(int i$var60, int j$var66, int threadID$cv$i$var60, Rng RNG$) {
		int cv$noStates = 0;
		if((1 == j$var66)) {
			if(fixedFlag$sample61) {
				int var24 = st[i$var60][0];
				if(((0 <= var24) && (var24 < noStates)))
					cv$noStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$noStates = noStates;
			}
		}
		if(fixedFlag$sample81) {
			if((2 <= j$var66)) {
				int var24 = st[i$var60][(j$var66 - 1)];
				if(((0 <= var24) && (var24 < noStates)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		} else {
			if((0 < noStates)) {
				int index$j$14 = (j$var66 - 1);
				if(((1 <= index$j$14) && !(index$j$14 == j$var66)))
					cv$noStates = Math.max(cv$noStates, noStates);
			}
		}
		double[] cv$stateProbabilityLocal = cv$var74$stateProbabilityGlobal[threadID$cv$i$var60];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == j$var66)) {
				if(fixedFlag$sample61) {
					int var24 = st[i$var60][0];
					if(((0 <= var24) && (var24 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] cv$temp$0$var72 = m[st[i$var60][0]];
						double cv$accumulatedProbabilities = ((cv$valuePos < cv$temp$0$var72.length)?Math.log(cv$temp$0$var72[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$eventsMeasured[i$var60])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double[] cv$temp$4$var92 = bias[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var60][1]) && (events[i$var60][1] < (cv$temp$4$var92.length + 1)))?Math.log(cv$temp$4$var92[(events[i$var60][1] - 1)]):Double.NEGATIVE_INFINITY);
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
					for(int index$sample61$26 = 0; index$sample61$26 < noStates; index$sample61$26 += 1) {
						double cv$probabilitySample61Value27 = distribution$sample61[i$var60][index$sample61$26];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample61Value27);
						double[] cv$temp$1$var72 = m[index$sample61$26];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample61Value27) + ((cv$valuePos < cv$temp$1$var72.length)?Math.log(cv$temp$1$var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$eventsMeasured[i$var60])) {
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < noStates)) {
								double[] cv$temp$5$var92 = bias[cv$valuePos];
								cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var60][1]) && (events[i$var60][1] < (cv$temp$5$var92.length + 1)))?Math.log(cv$temp$5$var92[(events[i$var60][1] - 1)]):Double.NEGATIVE_INFINITY);
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
			int index$j$34 = (j$var66 - 1);
			if(((1 <= index$j$34) && !(index$j$34 == j$var66))) {
				for(int index$sample81$35 = 0; index$sample81$35 < noStates; index$sample81$35 += 1) {
					double cv$probabilitySample81Value36 = distribution$sample81[i$var60][(index$j$34 - 1)][index$sample81$35];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample81Value36);
					double[] cv$temp$3$var72 = m[index$sample81$35];
					double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample81Value36) + ((cv$valuePos < cv$temp$3$var72.length)?Math.log(cv$temp$3$var72[cv$valuePos]):Double.NEGATIVE_INFINITY));
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double[] cv$temp$7$var92 = bias[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((1.0 <= events[i$var60][j$var66]) && (events[i$var60][j$var66] < (cv$temp$7$var92.length + 1)))?Math.log(cv$temp$7$var92[(events[i$var60][j$var66] - 1)]):Double.NEGATIVE_INFINITY);
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
			int index$j$61_3 = (j$var66 + 1);
			if((index$j$61_3 < length$eventsMeasured[i$var60])) {
				double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var73[threadID$cv$i$var60];
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == j$var66)) {
						if(fixedFlag$sample61) {
							int index$var24$72_1 = st[i$var60][0];
							if(((0 <= index$var24$72_1) && (index$var24$72_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample61$68 = 0; index$sample61$68 < noStates; index$sample61$68 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample61[i$var60][index$sample61$68]);
						}
					}
					int index$j$76 = (j$var66 - 1);
					if((((1 <= index$j$76) && !(index$j$76 == j$var66)) && !(index$j$76 == index$j$61_3))) {
						for(int index$sample81$77 = 0; index$sample81$77 < noStates; index$sample81$77 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample81[i$var60][(index$j$76 - 1)][index$sample81$77]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, m[cv$valuePos]);
				}
				double[] cv$sampleDistribution = distribution$sample81[i$var60][(index$j$61_3 - 1)];
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
		double[] cv$localProbability = distribution$sample81[i$var60][(j$var66 - 1)];
		double cv$logSum;
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
			cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			if((0 < noStates))
				cv$max = noStates;
			int cv$threadCount = threadCount();
			cv$var25$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var25$countGlobal[cv$index] = new double[cv$max];
		}
		{
			int cv$max = 0;
			if((0 < noStates))
				cv$max = Math.max(0, noEvents);
			int cv$threadCount = threadCount();
			cv$var32$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var32$countGlobal[cv$index] = new double[cv$max];
		}
		cv$var45$countGlobal = new double[Math.max(0, noStates)];
		cv$distributionAccumulator$var54 = new double[noStates];
		cv$var47$stateProbabilityGlobal = new double[noStates];
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
		int cv$threadCount = threadCount();
		cv$var74$stateProbabilityGlobal = new double[cv$threadCount][];
		for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
			cv$var74$stateProbabilityGlobal[cv$index] = new double[noStates];
	}

	@Override
	public final void allocator() {
		v = new double[noStates];
		v2 = new double[noEvents];
		if(!setFlag$m) {
			m = new double[noStates][];
			for(int var24 = 0; var24 < noStates; var24 += 1)
				m[var24] = new double[noStates];
		}
		if(!setFlag$bias) {
			bias = new double[noStates][];
			for(int var31 = 0; var31 < noStates; var31 += 1)
				bias[var31] = new double[noEvents];
		}
		if(!setFlag$st) {
			st = new int[length$eventsMeasured.length][];
			for(int i$var38 = 0; i$var38 < length$eventsMeasured.length; i$var38 += 1)
				st[i$var38] = new int[length$eventsMeasured[i$var38]];
		}
		if(!setFlag$weights)
			weights = new double[noStates];
		if(!setFlag$events) {
			events = new int[length$eventsMeasured.length][];
			for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
				events[i$var80] = new int[length$eventsMeasured[i$var80]];
		}
		distribution$sample81 = new double[length$eventsMeasured.length][][];
		for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1) {
			double[][] subarray$0 = new double[(length$eventsMeasured[i$var60] - 1)][];
			distribution$sample81[i$var60] = subarray$0;
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
				subarray$0[(j$var66 - 1)] = new double[noStates];
		}
		distribution$sample61 = new double[length$eventsMeasured.length][];
		for(int i$var50 = 0; i$var50 < length$eventsMeasured.length; i$var50 += 1)
			distribution$sample61[i$var50] = new double[noStates];
		logProbability$var54 = new double[length$eventsMeasured.length];
		logProbability$sample61 = new double[length$eventsMeasured.length];
		logProbability$var73 = new double[length$eventsMeasured.length][];
		for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
			logProbability$var73[i$var60] = new double[(length$eventsMeasured[i$var60] - 1)];
		logProbability$sample81 = new double[length$eventsMeasured.length][];
		for(int i$var60 = 0; i$var60 < length$eventsMeasured.length; i$var60 += 1)
			logProbability$sample81[i$var60] = new double[(length$eventsMeasured[i$var60] - 1)];
		logProbability$var93 = new double[length$eventsMeasured.length][];
		for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
			logProbability$var93[i$var80] = new double[(length$eventsMeasured[i$var80] - 1)];
		logProbability$sample103 = new double[length$eventsMeasured.length][];
		for(int i$var80 = 0; i$var80 < length$eventsMeasured.length; i$var80 += 1)
			logProbability$sample103[i$var80] = new double[(length$eventsMeasured[i$var80] - 1)];
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var24]);
				}
			);

		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, bias[var31]);
				}
			);

		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
							st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
				}
			);

		if(!fixedFlag$sample81)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
							int[] var67 = st[i$var60];
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
								var67[j$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var60][(j$var66 - 1)]]);
						}
				}
			);

		if(!fixedFlag$sample103)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$index$i$var80, int forEnd$index$i$var80, int threadID$index$i$var80, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i$var80 = forStart$index$i$var80; index$i$var80 < forEnd$index$i$var80; index$i$var80 += 1) {
							int i$var80 = index$i$var80;
							int[] var89 = events[i$var80];
							parallelFor(RNG$1, 1, length$eventsMeasured[i$var80], 1,
								(int forStart$j$var88, int forEnd$j$var88, int threadID$j$var88, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var88 = forStart$j$var88; j$var88 < forEnd$j$var88; j$var88 += 1)
											var89[j$var88] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var80][j$var88]]) + 1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var24]);
				}
			);

		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, bias[var31]);
				}
			);

		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1) {
							double[] cv$distribution$sample61 = distribution$sample61[i$var50];
							double[] var53 = m[initialState];
							for(int index$var54 = 0; index$var54 < noStates; index$var54 += 1)
								cv$distribution$sample61[index$var54] = ((index$var54 < var53.length)?var53[index$var54]:0.0);
						}
				}
			);

		if(!fixedFlag$sample81)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1) {
								double[] cv$distribution$sample81 = distribution$sample81[i$var60][(j$var66 - 1)];
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$distribution$sample81[index$var73] = 0.0;
								if((1 == j$var66)) {
									if(fixedFlag$sample61) {
										int var24 = st[i$var60][0];
										if(((0 <= var24) && (var24 < noStates))) {
											double[] var72 = m[st[i$var60][0]];
											for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
												cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + ((index$var73 < var72.length)?var72[index$var73]:0.0));
										}
									} else {
										for(int index$sample61$3 = 0; index$sample61$3 < noStates; index$sample61$3 += 1) {
											double cv$probabilitySample61Value4 = distribution$sample61[i$var60][index$sample61$3];
											double[] var72 = m[index$sample61$3];
											for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
												cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (cv$probabilitySample61Value4 * ((index$var73 < var72.length)?var72[index$var73]:0.0)));
										}
									}
								}
								int index$j$11 = (j$var66 - 1);
								if((1 <= index$j$11)) {
									for(int index$sample81$12 = 0; index$sample81$12 < noStates; index$sample81$12 += 1) {
										double cv$probabilitySample81Value13 = distribution$sample81[i$var60][(index$j$11 - 1)][index$sample81$12];
										double[] var72 = m[index$sample81$12];
										for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
											cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] + (cv$probabilitySample81Value13 * ((index$var73 < var72.length)?var72[index$var73]:0.0)));
									}
								}
								double cv$var73$sum = 0.0;
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$var73$sum = (cv$var73$sum + cv$distribution$sample81[index$var73]);
								for(int index$var73 = 0; index$var73 < noStates; index$var73 += 1)
									cv$distribution$sample81[index$var73] = (cv$distribution$sample81[index$var73] / cv$var73$sum);
							}
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var24]);
				}
			);

		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, bias[var31]);
				}
			);

		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
							st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
				}
			);

		if(!fixedFlag$sample81)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
							int[] var67 = st[i$var60];
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
								var67[j$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var60][(j$var66 - 1)]]);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample26)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample26(var24, threadID$var24, RNG$1);
					}
				);

			if(!fixedFlag$sample34)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample34(var31, threadID$var31, RNG$1);
					}
				);

			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample61)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
								sample61(i$var50, threadID$i$var50, RNG$1);
					}
				);

			if(!fixedFlag$sample81)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
								for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
									sample81(i$var60, j$var66, threadID$i$var60, RNG$1);
							}
					}
				);

		} else {
			if(!fixedFlag$sample81)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
								for(int j$var66 = (length$eventsMeasured[i$var60] - 1); j$var66 >= 1; j$var66 -= 1)
									sample81(i$var60, j$var66, threadID$i$var60, RNG$1);
							}
					}
				);

			if(!fixedFlag$sample61)
				parallelFor(RNG$, 0, samples, 1,
					(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
								sample61(i$var50, threadID$i$var50, RNG$1);
					}
				);

			if(!fixedFlag$sample53)
				sample53();
			if(!fixedFlag$sample51)
				sample51();
			if(!fixedFlag$sample34)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
								sample34(var31, threadID$var31, RNG$1);
					}
				);

			if(!fixedFlag$sample26)
				parallelFor(RNG$, 0, noStates, 1,
					(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
								sample26(var24, threadID$var24, RNG$1);
					}
				);

		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var11, int forEnd$var11, int threadID$var11, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var11 = forStart$var11; var11 < forEnd$var11; var11 += 1)
						v[var11] = 0.1;
			}
		);
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var17, int forEnd$var17, int threadID$var17, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var17 = forStart$var17; var17 < forEnd$var17; var17 += 1)
						v2[var17] = 0.1;
			}
		);
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var20 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var27 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample34)
			logProbability$var32 = 0.0;
		logProbability$var44 = 0.0;
		if(!fixedProbFlag$sample51)
			logProbability$weights = 0.0;
		logProbability$var46 = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$initialState = 0.0;
		for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
			logProbability$var54[i$var50] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample61) {
			for(int i$var50 = 0; i$var50 < samples; i$var50 += 1)
				logProbability$sample61[i$var50] = 0.0;
		}
		for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
			for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
				logProbability$var73[i$var60][(j$var66 - 1)] = 0.0;
		}
		if(!fixedProbFlag$sample81) {
			for(int i$var60 = 0; i$var60 < samples; i$var60 += 1) {
				for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
					logProbability$sample81[i$var60][(j$var66 - 1)] = 0.0;
			}
		}
		for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
			for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
				logProbability$var93[i$var80][(j$var88 - 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i$var80 = 0; i$var80 < samples; i$var80 += 1) {
				for(int j$var88 = 1; j$var88 < length$eventsMeasured[i$var80]; j$var88 += 1)
					logProbability$sample103[i$var80][(j$var88 - 1)] = 0.0;
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
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		if(fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(fixedFlag$sample51)
			logProbabilityValue$sample51();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityDistribution$sample61();
		logProbabilityDistribution$sample81();
		logProbabilityDistribution$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample26();
		logProbabilityValue$sample34();
		logProbabilityValue$sample51();
		logProbabilityValue$sample53();
		logProbabilityValue$sample61();
		logProbabilityValue$sample81();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample26)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var24, int forEnd$var24, int threadID$var24, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var24 = forStart$var24; var24 < forEnd$var24; var24 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v, m[var24]);
				}
			);

		if(!fixedFlag$sample34)
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, v2, bias[var31]);
				}
			);

		if(!fixedFlag$sample51)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample53)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		if(!fixedFlag$sample61)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var50, int forEnd$i$var50, int threadID$i$var50, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var50 = forStart$i$var50; i$var50 < forEnd$i$var50; i$var50 += 1)
							st[i$var50][0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
				}
			);

		if(!fixedFlag$sample81)
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var60, int forEnd$i$var60, int threadID$i$var60, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var60 = forStart$i$var60; i$var60 < forEnd$i$var60; i$var60 += 1) {
							int[] var67 = st[i$var60];
							for(int j$var66 = 1; j$var66 < length$eventsMeasured[i$var60]; j$var66 += 1)
								var67[j$var66] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var60][(j$var66 - 1)]]);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n        \n        // Construct arrays describing the probability of a move from 1 state to another.\n        double[] v = new double[noStates] <~ 0.1;\n        double[] v2 = new double[noEvents] <~ 0.1;\n        double[][] m = dirichlet(v).sample(noStates);\n        \n        // Construct the bias for each webpage.\n        double[][] bias = dirichlet(v2).sample(noStates);\n\n        // Determine how many samples the model will need to produce.\n        int samples = eventsMeasured.length;\n        \n        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n        int[][] st = new int[samples][];\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            st[i] = new int[streamLength];\n        }\n\n        // Set the initial state by sampling from a categorical with learnt weightings.\n        double[] weights = dirichlet(v).sample();\n        int initialState = categorical(weights).sample();\n        for(int i:[0..samples)) {\n            st[i][0] = categorical(m[initialState]).sampleDistribution();\n        }\n\n        //Determine the remaining states based on the previous state.\n        for(int i:[0 .. samples)){\n            int streamLength = eventsMeasured[i].length;\n            for(int j:[1..streamLength)){\n                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n            }\n        }\n            \n        //Generate each event.\n        int[][] events = new int[samples][];\n        for(int i:[0 .. samples)) {\n            int streamLength = eventsMeasured[i].length;\n            events[i] = new int[streamLength];\n            for(int j:[1..streamLength)){\n                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n            }\n        }\n\n        //Tie the values of the flips to the values we have measured.\n        events.observe(eventsMeasured);\n}\n";
	}
}