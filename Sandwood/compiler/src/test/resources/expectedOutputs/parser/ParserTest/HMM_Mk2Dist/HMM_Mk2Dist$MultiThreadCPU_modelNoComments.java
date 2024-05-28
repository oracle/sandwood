package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMM_Mk2Dist$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Mk2Dist$CoreInterface {
	private double[][] bias;
	private double[][] cv$distributionAccumulator$var128;
	private double[] cv$distributionAccumulator$var96;
	private double[][] cv$var129$stateProbabilityGlobal;
	private double[][] cv$var46$countGlobal;
	private double[][] cv$var60$countGlobal;
	private double[] cv$var80$countGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[][] cv$var97$stateProbabilityGlobal;
	private double[][] distribution$sample103;
	private double[][][] distribution$sample136;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample103 = false;
	private boolean fixedFlag$sample136 = false;
	private boolean fixedFlag$sample171 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample62 = false;
	private boolean fixedFlag$sample86 = false;
	private boolean fixedFlag$sample88 = false;
	private boolean fixedProbFlag$sample103 = false;
	private boolean fixedProbFlag$sample136 = false;
	private boolean fixedProbFlag$sample171 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample62 = false;
	private boolean fixedProbFlag$sample86 = false;
	private boolean fixedProbFlag$sample88 = false;
	private int initialState;
	private int[] length$eventsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$events;
	private double logProbability$initialState;
	private double logProbability$m;
	private double[] logProbability$sample103;
	private double[][] logProbability$sample136;
	private double[][] logProbability$sample171;
	private double logProbability$st;
	private double[][] logProbability$var128;
	private double[][] logProbability$var161;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var48;
	private double logProbability$var60;
	private double logProbability$var79;
	private double logProbability$var81;
	private double[] logProbability$var96;
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
		fixedProbFlag$sample62 = false;
		fixedProbFlag$sample171 = false;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final void set$events(int[][] cv$value) {
		events = cv$value;
		setFlag$events = true;
		fixedProbFlag$sample171 = false;
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
		fixedProbFlag$sample103 = (fixedFlag$sample103 && fixedProbFlag$sample103);
		fixedProbFlag$sample136 = (fixedFlag$sample103 && fixedProbFlag$sample136);
		fixedProbFlag$sample171 = (fixedFlag$sample103 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample136() {
		return fixedFlag$sample136;
	}

	@Override
	public final void set$fixedFlag$sample136(boolean cv$value) {
		fixedFlag$sample136 = cv$value;
		fixedProbFlag$sample136 = (fixedFlag$sample136 && fixedProbFlag$sample136);
		fixedProbFlag$sample171 = (fixedFlag$sample136 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample171() {
		return fixedFlag$sample171;
	}

	@Override
	public final void set$fixedFlag$sample171(boolean cv$value) {
		fixedFlag$sample171 = cv$value;
		fixedProbFlag$sample171 = (fixedFlag$sample171 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value) {
		fixedFlag$sample47 = cv$value;
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample103 = (fixedFlag$sample47 && fixedProbFlag$sample103);
		fixedProbFlag$sample136 = (fixedFlag$sample47 && fixedProbFlag$sample136);
	}

	@Override
	public final boolean get$fixedFlag$sample62() {
		return fixedFlag$sample62;
	}

	@Override
	public final void set$fixedFlag$sample62(boolean cv$value) {
		fixedFlag$sample62 = cv$value;
		fixedProbFlag$sample62 = (fixedFlag$sample62 && fixedProbFlag$sample62);
		fixedProbFlag$sample171 = (fixedFlag$sample62 && fixedProbFlag$sample171);
	}

	@Override
	public final boolean get$fixedFlag$sample86() {
		return fixedFlag$sample86;
	}

	@Override
	public final void set$fixedFlag$sample86(boolean cv$value) {
		fixedFlag$sample86 = cv$value;
		fixedProbFlag$sample86 = (fixedFlag$sample86 && fixedProbFlag$sample86);
		fixedProbFlag$sample88 = (fixedFlag$sample86 && fixedProbFlag$sample88);
	}

	@Override
	public final boolean get$fixedFlag$sample88() {
		return fixedFlag$sample88;
	}

	@Override
	public final void set$fixedFlag$sample88(boolean cv$value) {
		fixedFlag$sample88 = cv$value;
		fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedProbFlag$sample88);
		fixedProbFlag$sample103 = (fixedFlag$sample88 && fixedProbFlag$sample103);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value) {
		initialState = cv$value;
		fixedProbFlag$sample88 = false;
		fixedProbFlag$sample103 = false;
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
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample103 = false;
		fixedProbFlag$sample136 = false;
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
		fixedProbFlag$sample103 = false;
		fixedProbFlag$sample136 = false;
		fixedProbFlag$sample171 = false;
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
		fixedProbFlag$sample86 = false;
		fixedProbFlag$sample88 = false;
	}

	private final void logProbabilityDistribution$sample103() {
		if(!fixedProbFlag$sample103) {
			if(fixedFlag$sample103) {
				double cv$accumulator = 0.0;
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var92;
					{
						int cv$sampleValue = st[i$var92][0];
						{
							{
								double[] var95 = m[initialState];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var96[((i$var92 - 0) / 1)] = cv$sampleAccumulator;
					logProbability$sample103[((i$var92 - 0) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample103)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample103)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample103 = ((fixedFlag$sample103 && fixedFlag$sample47) && fixedFlag$sample88);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample103[((i$var92 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var96[((i$var92 - 0) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample103)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample136() {
		if(!fixedProbFlag$sample136) {
			if(fixedFlag$sample136) {
				double cv$accumulator = 0.0;
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int index$j$1 = j$var121;
						int index$i$2 = i$var109;
						{
							int cv$sampleValue = st[i$var109][j$var121];
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var109)) {
										if((0 == (j$var121 - 1))) {
											for(int var45 = 0; var45 < noStates; var45 += 1) {
												if((var45 == st[i$var109][(j$var121 - 1)])) {
													{
														double[] var127 = m[st[i$var109][(j$var121 - 1)]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										for(int index$sample103$6 = 0; index$sample103$6 < noStates; index$sample103$6 += 1) {
											int distributionTempVariable$var97$8 = index$sample103$6;
											double cv$probabilitySample103Value7 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$6]);
											int traceTempVariable$var126$9_1 = distributionTempVariable$var97$8;
											if((i$var92 == i$var109)) {
												if((0 == (j$var121 - 1))) {
													for(int var45 = 0; var45 < noStates; var45 += 1) {
														if((var45 == traceTempVariable$var126$9_1)) {
															{
																double[] var127 = m[traceTempVariable$var126$9_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample103Value7) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample103Value7);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							if((index$i$2 == i$var109)) {
								if((index$j$1 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == st[i$var109][(j$var121 - 1)])) {
											{
												double[] var127 = m[st[i$var109][(j$var121 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$i$13_1 = 0; index$i$13_1 < samples; index$i$13_1 += 1) {
									for(int index$j$13_2 = 1; index$j$13_2 < length$eventsMeasured[index$i$13_1]; index$j$13_2 += 1) {
										if((index$i$13_1 == i$var109)) {
											if((index$j$13_2 == (j$var121 - 1))) {
												for(int var45 = 0; var45 < noStates; var45 += 1) {
													if((var45 == st[i$var109][(j$var121 - 1)])) {
														{
															double[] var127 = m[st[i$var109][(j$var121 - 1)]];
															double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$i$14 = 0; index$i$14 < samples; index$i$14 += 1) {
									for(int index$j$15 = 1; index$j$15 < length$eventsMeasured[index$i$14]; index$j$15 += 1) {
										if(!((index$j$15 == index$j$1) && (index$i$14 == index$i$2))) {
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var129$18 = index$sample136$16;
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((index$i$14 - 0) / 1)][((index$j$15 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var126$19_1 = distributionTempVariable$var129$18;
												if((index$i$14 == i$var109)) {
													if((index$j$15 == (j$var121 - 1))) {
														for(int var45 = 0; var45 < noStates; var45 += 1) {
															if((var45 == traceTempVariable$var126$19_1)) {
																{
																	double[] var127 = m[traceTempVariable$var126$19_1];
																	double cv$weightedProbability = (Math.log(cv$probabilitySample136Value17) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$sampleAccumulator;
						logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$sampleProbability;
					}
				}
				if(fixedFlag$sample136)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample136)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample47) && fixedFlag$sample103);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			if(fixedFlag$sample136)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample171() {
		if(!fixedProbFlag$sample171) {
			double cv$accumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = (events[i$var142][j$var156] - 1);
						if(fixedFlag$sample103) {
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if((i$var92 == i$var142)) {
									if((0 == j$var156)) {
										for(int var59 = 0; var59 < noStates; var59 += 1) {
											if((var59 == st[i$var142][j$var156])) {
												{
													double[] var160 = bias[st[i$var142][j$var156]];
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if(true) {
									for(int index$sample103$4 = 0; index$sample103$4 < noStates; index$sample103$4 += 1) {
										int distributionTempVariable$var97$6 = index$sample103$4;
										double cv$probabilitySample103Value5 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$4]);
										int traceTempVariable$var159$7_1 = distributionTempVariable$var97$6;
										if((i$var92 == i$var142)) {
											if((0 == j$var156)) {
												for(int var59 = 0; var59 < noStates; var59 += 1) {
													if((var59 == traceTempVariable$var159$7_1)) {
														{
															double[] var160 = bias[traceTempVariable$var159$7_1];
															double cv$weightedProbability = (Math.log(cv$probabilitySample103Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample103Value5);
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
							for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if((i$var109 == i$var142)) {
										if((j$var121 == j$var156)) {
											for(int var59 = 0; var59 < noStates; var59 += 1) {
												if((var59 == st[i$var142][j$var156])) {
													{
														double[] var160 = bias[st[i$var142][j$var156]];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if(true) {
										for(int index$sample136$13 = 0; index$sample136$13 < noStates; index$sample136$13 += 1) {
											int distributionTempVariable$var129$15 = index$sample136$13;
											double cv$probabilitySample136Value14 = (1.0 * distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][index$sample136$13]);
											int traceTempVariable$var159$16_1 = distributionTempVariable$var129$15;
											if((i$var109 == i$var142)) {
												if((j$var121 == j$var156)) {
													for(int var59 = 0; var59 < noStates; var59 += 1) {
														if((var59 == traceTempVariable$var159$16_1)) {
															{
																double[] var160 = bias[traceTempVariable$var159$16_1];
																double cv$weightedProbability = (Math.log(cv$probabilitySample136Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var92;
				{
					int cv$sampleValue = st[i$var92][0];
					{
						{
							double[] var95 = m[initialState];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var95.length))?Math.log(var95[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var96[((i$var92 - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample103[((i$var92 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample103 = ((fixedFlag$sample103 && fixedFlag$sample47) && fixedFlag$sample88);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample103[((i$var92 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var96[((i$var92 - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample103)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample136() {
		if(!fixedProbFlag$sample136) {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$j$1 = j$var121;
					int index$i$2 = i$var109;
					{
						int cv$sampleValue = st[i$var109][j$var121];
						{
							{
								double[] var127 = m[st[i$var109][(j$var121 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var127.length))?Math.log(var127[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample136 = ((fixedFlag$sample136 && fixedFlag$sample47) && fixedFlag$sample103);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample136)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample171() {
		if(!fixedProbFlag$sample171) {
			double cv$accumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						int cv$sampleValue = (events[i$var142][j$var156] - 1);
						{
							{
								double[] var160 = bias[st[i$var142][j$var156]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var160.length))?Math.log(var160[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample171 = (((fixedFlag$sample171 && fixedFlag$sample62) && fixedFlag$sample103) && fixedFlag$sample136);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = cv$rvAccumulator;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < noStates; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var45];
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
			logProbability$var34 = cv$sampleAccumulator;
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var59 = 0; var59 < noStates; var59 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = bias[var59];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2));
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
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$var60 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample62 = fixedFlag$sample62;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var60;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var48 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample62)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample86() {
		if(!fixedProbFlag$sample86) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double[] cv$sampleValue = weights;
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
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$weights = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample86 = fixedFlag$sample86;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var79 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample86)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample88() {
		if(!fixedProbFlag$sample88) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = initialState;
				{
					{
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < weights.length))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var81 = cv$sampleAccumulator;
			logProbability$initialState = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample88 = (fixedFlag$sample88 && fixedFlag$sample86);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var81 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample88)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample103(int i$var92, int threadID$cv$i$var92, Rng RNG$) {
		int cv$noStates = 0;
		int index$i$1 = i$var92;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var97$stateProbabilityGlobal[threadID$cv$i$var92];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$2 = i$var92;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var95;
				{
					double[] var95 = m[initialState];
					cv$temp$0$var95 = var95;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var95.length))?Math.log(cv$temp$0$var95[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var126$3_1 = cv$currentValue;
						for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
							if((i$var92 == i$var109)) {
								for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
									if((0 == (j$var121 - 1))) {
										if(fixedFlag$sample136) {
											{
												int index$j$5 = j$var121;
												int index$i$6 = i$var109;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var45 = 0; var45 < noStates; var45 += 1) {
														if((var45 == traceTempVariable$var126$3_1)) {
															{
																{
																	double[] cv$temp$1$var127;
																	{
																		double[] var127 = m[traceTempVariable$var126$3_1];
																		cv$temp$1$var127 = var127;
																	}
																	if(((Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var109][j$var121]) && (st[i$var109][j$var121] < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[st[i$var109][j$var121]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var159$9_1 = cv$currentValue;
						for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
							if((i$var92 == i$var142)) {
								for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
									if((0 == j$var156)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var59 = 0; var59 < noStates; var59 += 1) {
													if((var59 == traceTempVariable$var159$9_1)) {
														{
															{
																double[] cv$temp$2$var160;
																{
																	double[] var160 = bias[traceTempVariable$var159$9_1];
																	cv$temp$2$var160 = var160;
																}
																if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$2$var160.length))?Math.log(cv$temp$2$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
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
					int traceTempVariable$var126$13_1 = cv$currentValue;
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						if((i$var92 == i$var109)) {
							for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
								if((0 == (j$var121 - 1))) {
									if(!fixedFlag$sample136) {
										{
											int index$j$15 = j$var121;
											int index$i$16 = i$var109;
											double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var128[threadID$cv$i$var92];
											for(int cv$i = 0; cv$i < noStates; cv$i += 1)
												cv$accumulatedConsumerDistributions[cv$i] = 0.0;
											double cv$reachedDistributionProbability = 0.0;
											for(int var45 = 0; var45 < noStates; var45 += 1) {
												if((var45 == traceTempVariable$var126$13_1)) {
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double[] cv$temp$3$var127;
														{
															double[] var127 = m[traceTempVariable$var126$13_1];
															cv$temp$3$var127 = var127;
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
														DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var127);
													}
												}
											}
											double[] cv$sampleDistribution = distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample103[((i$var92 - 0) / 1)];
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

	private final void sample136(int i$var109, int j$var121, int threadID$cv$i$var109, Rng RNG$) {
		int cv$noStates = 0;
		int index$j$1 = j$var121;
		int index$i$2 = i$var109;
		if(fixedFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				if((i$var92 == i$var109)) {
					if((0 == (j$var121 - 1))) {
						for(int var45 = 0; var45 < noStates; var45 += 1) {
							if((var45 == st[i$var109][(j$var121 - 1)]))
								cv$noStates = Math.max(cv$noStates, noStates);
						}
					}
				}
			}
		} else {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
				if(true) {
					for(int index$sample103$5 = 0; index$sample103$5 < noStates; index$sample103$5 += 1) {
						int distributionTempVariable$var97$7 = index$sample103$5;
						double cv$probabilitySample103Value6 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$5]);
						int traceTempVariable$var126$8_1 = distributionTempVariable$var97$7;
						if((i$var92 == i$var109)) {
							if((0 == (j$var121 - 1))) {
								for(int var45 = 0; var45 < noStates; var45 += 1) {
									if((var45 == traceTempVariable$var126$8_1))
										cv$noStates = Math.max(cv$noStates, noStates);
								}
							}
						}
					}
				}
			}
		}
		if((index$i$2 == i$var109)) {
			if((index$j$1 == (j$var121 - 1))) {
				for(int var45 = 0; var45 < noStates; var45 += 1) {
					if((var45 == st[i$var109][(j$var121 - 1)]))
						cv$noStates = Math.max(cv$noStates, noStates);
				}
			}
		}
		if(fixedFlag$sample136) {
			for(int index$i$12_1 = 0; index$i$12_1 < samples; index$i$12_1 += 1) {
				for(int index$j$12_2 = 1; index$j$12_2 < length$eventsMeasured[index$i$12_1]; index$j$12_2 += 1) {
					if((index$i$12_1 == i$var109)) {
						if((index$j$12_2 == (j$var121 - 1))) {
							for(int var45 = 0; var45 < noStates; var45 += 1) {
								if((var45 == st[i$var109][(j$var121 - 1)]))
									cv$noStates = Math.max(cv$noStates, noStates);
							}
						}
					}
				}
			}
		} else {
			for(int index$i$13 = 0; index$i$13 < samples; index$i$13 += 1) {
				for(int index$j$14 = 1; index$j$14 < length$eventsMeasured[index$i$13]; index$j$14 += 1) {
					if(!((index$j$14 == index$j$1) && (index$i$13 == index$i$2))) {
						for(int index$sample136$15 = 0; index$sample136$15 < noStates; index$sample136$15 += 1) {
							int distributionTempVariable$var129$17 = index$sample136$15;
							double cv$probabilitySample136Value16 = (1.0 * distribution$sample136[((index$i$13 - 0) / 1)][((index$j$14 - 1) / 1)][index$sample136$15]);
							int traceTempVariable$var126$18_1 = distributionTempVariable$var129$17;
							if((index$i$13 == i$var109)) {
								if((index$j$14 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == traceTempVariable$var126$18_1))
											cv$noStates = Math.max(cv$noStates, noStates);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var129$stateProbabilityGlobal[threadID$cv$i$var109];
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$j$22 = j$var121;
			int index$i$23 = i$var109;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample103) {
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					if((i$var92 == i$var109)) {
						if((0 == (j$var121 - 1))) {
							for(int var45 = 0; var45 < noStates; var45 += 1) {
								if((var45 == st[i$var109][(j$var121 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
									double[] cv$temp$0$var127;
									{
										double[] var127 = m[st[i$var109][(j$var121 - 1)]];
										cv$temp$0$var127 = var127;
									}
									double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var127.length))?Math.log(cv$temp$0$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var126$41_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$var159$45_1 = cv$currentValue;
											for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
												if((i$var109 == i$var142)) {
													for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
														if((j$var121 == j$var156)) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	for(int var59 = 0; var59 < noStates; var59 += 1) {
																		if((var59 == traceTempVariable$var159$45_1)) {
																			{
																				{
																					double[] cv$temp$4$var160;
																					{
																						double[] var160 = bias[traceTempVariable$var159$45_1];
																						cv$temp$4$var160 = var160;
																					}
																					if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$4$var160.length))?Math.log(cv$temp$4$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
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
				for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
					if(true) {
						for(int index$sample103$26 = 0; index$sample103$26 < noStates; index$sample103$26 += 1) {
							int distributionTempVariable$var97$28 = index$sample103$26;
							double cv$probabilitySample103Value27 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$26]);
							int traceTempVariable$var126$29_1 = distributionTempVariable$var97$28;
							if((i$var92 == i$var109)) {
								if((0 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == traceTempVariable$var126$29_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample103Value27);
											double[] cv$temp$1$var127;
											{
												double[] var127 = m[traceTempVariable$var126$29_1];
												cv$temp$1$var127 = var127;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample103Value27) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var127.length))?Math.log(cv$temp$1$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var126$42_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$var159$46_1 = cv$currentValue;
													for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
														if((i$var109 == i$var142)) {
															for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
																if((j$var121 == j$var156)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var59 = 0; var59 < noStates; var59 += 1) {
																				if((var59 == traceTempVariable$var159$46_1)) {
																					{
																						{
																							double[] cv$temp$5$var160;
																							{
																								double[] var160 = bias[traceTempVariable$var159$46_1];
																								cv$temp$5$var160 = var160;
																							}
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$5$var160.length))?Math.log(cv$temp$5$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
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
			int traceTempVariable$var126$32_1 = cv$currentValue;
			if((index$i$23 == i$var109)) {
				if((index$j$22 == (j$var121 - 1))) {
					for(int var45 = 0; var45 < noStates; var45 += 1) {
						if((var45 == traceTempVariable$var126$32_1)) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var127;
							{
								double[] var127 = m[traceTempVariable$var126$32_1];
								cv$temp$2$var127 = var127;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var127.length))?Math.log(cv$temp$2$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var126$43_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$var159$47_1 = cv$currentValue;
									for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
										if((i$var109 == i$var142)) {
											for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
												if((j$var121 == j$var156)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var59 = 0; var59 < noStates; var59 += 1) {
																if((var59 == traceTempVariable$var159$47_1)) {
																	{
																		{
																			double[] cv$temp$6$var160;
																			{
																				double[] var160 = bias[traceTempVariable$var159$47_1];
																				cv$temp$6$var160 = var160;
																			}
																			if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$6$var160.length))?Math.log(cv$temp$6$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
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
			for(int index$i$33 = 0; index$i$33 < samples; index$i$33 += 1) {
				for(int index$j$34 = 1; index$j$34 < length$eventsMeasured[index$i$33]; index$j$34 += 1) {
					if(!((index$j$34 == index$j$22) && (index$i$33 == index$i$23))) {
						for(int index$sample136$35 = 0; index$sample136$35 < noStates; index$sample136$35 += 1) {
							int distributionTempVariable$var129$37 = index$sample136$35;
							double cv$probabilitySample136Value36 = (1.0 * distribution$sample136[((index$i$33 - 0) / 1)][((index$j$34 - 1) / 1)][index$sample136$35]);
							int traceTempVariable$var126$38_1 = distributionTempVariable$var129$37;
							if((index$i$33 == i$var109)) {
								if((index$j$34 == (j$var121 - 1))) {
									for(int var45 = 0; var45 < noStates; var45 += 1) {
										if((var45 == traceTempVariable$var126$38_1)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample136Value36);
											double[] cv$temp$3$var127;
											{
												double[] var127 = m[traceTempVariable$var126$38_1];
												cv$temp$3$var127 = var127;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample136Value36) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var127.length))?Math.log(cv$temp$3$var127[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var126$44_1 = cv$currentValue;
												}
											}
											{
												{
													int traceTempVariable$var159$48_1 = cv$currentValue;
													for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
														if((i$var109 == i$var142)) {
															for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
																if((j$var121 == j$var156)) {
																	{
																		double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																		double cv$consumerDistributionProbabilityAccumulator = 1.0;
																		{
																			for(int var59 = 0; var59 < noStates; var59 += 1) {
																				if((var59 == traceTempVariable$var159$48_1)) {
																					{
																						{
																							double[] cv$temp$7$var160;
																							{
																								double[] var160 = bias[traceTempVariable$var159$48_1];
																								cv$temp$7$var160 = var160;
																							}
																							if(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= (events[i$var142][j$var156] - 1)) && ((events[i$var142][j$var156] - 1) < cv$temp$7$var160.length))?Math.log(cv$temp$7$var160[(events[i$var142][j$var156] - 1)]):Double.NEGATIVE_INFINITY)));
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
					int traceTempVariable$var126$61_1 = cv$currentValue;
					for(int index$i$61_2 = 0; index$i$61_2 < samples; index$i$61_2 += 1) {
						if((i$var109 == index$i$61_2)) {
							for(int index$j$61_3 = 1; index$j$61_3 < length$eventsMeasured[index$i$61_2]; index$j$61_3 += 1) {
								if((j$var121 == (index$j$61_3 - 1))) {
									{
										int index$j$63 = index$j$61_3;
										int index$i$64 = index$i$61_2;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var128[threadID$cv$i$var109];
										for(int cv$i = 0; cv$i < noStates; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var45 = 0; var45 < noStates; var45 += 1) {
											if((var45 == traceTempVariable$var126$61_1)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													if(fixedFlag$sample103) {
														for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
															if((i$var92 == i$var109)) {
																if((0 == (j$var121 - 1))) {
																	for(int index$var45$72_1 = 0; index$var45$72_1 < noStates; index$var45$72_1 += 1) {
																		if((index$var45$72_1 == st[i$var109][(j$var121 - 1)]))
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																}
															}
														}
													} else {
														for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
															if(true) {
																for(int index$sample103$68 = 0; index$sample103$68 < noStates; index$sample103$68 += 1) {
																	int distributionTempVariable$var97$70 = index$sample103$68;
																	double cv$probabilitySample103Value69 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$68]);
																	int traceTempVariable$var126$71_1 = distributionTempVariable$var97$70;
																	if((i$var92 == i$var109)) {
																		if((0 == (j$var121 - 1))) {
																			for(int index$var45$73_1 = 0; index$var45$73_1 < noStates; index$var45$73_1 += 1) {
																				if((index$var45$73_1 == traceTempVariable$var126$71_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample103Value69);
																			}
																		}
																	}
																}
															}
														}
													}
													int traceTempVariable$var126$74_1 = cv$currentValue;
													if((index$i$23 == i$var109)) {
														if((index$j$22 == (j$var121 - 1))) {
															for(int index$var45$81_1 = 0; index$var45$81_1 < noStates; index$var45$81_1 += 1) {
																if((index$var45$81_1 == traceTempVariable$var126$74_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
														}
													}
													for(int index$i$75 = 0; index$i$75 < samples; index$i$75 += 1) {
														for(int index$j$76 = 1; index$j$76 < length$eventsMeasured[index$i$75]; index$j$76 += 1) {
															if((!((index$j$76 == index$j$22) && (index$i$75 == index$i$23)) && !((index$j$76 == index$j$63) && (index$i$75 == index$i$64)))) {
																for(int index$sample136$77 = 0; index$sample136$77 < noStates; index$sample136$77 += 1) {
																	int distributionTempVariable$var129$79 = index$sample136$77;
																	double cv$probabilitySample136Value78 = (1.0 * distribution$sample136[((index$i$75 - 0) / 1)][((index$j$76 - 1) / 1)][index$sample136$77]);
																	int traceTempVariable$var126$80_1 = distributionTempVariable$var129$79;
																	if((index$i$75 == i$var109)) {
																		if((index$j$76 == (j$var121 - 1))) {
																			for(int index$var45$82_1 = 0; index$var45$82_1 < noStates; index$var45$82_1 += 1) {
																				if((index$var45$82_1 == traceTempVariable$var126$80_1))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample136Value78);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$8$var127;
													{
														double[] var127 = m[traceTempVariable$var126$61_1];
														cv$temp$8$var127 = var127;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var127);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample136[((index$i$61_2 - 0) / 1)][((index$j$61_3 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
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

	private final void sample47(int var45, int threadID$cv$var45, Rng RNG$) {
		double[] cv$targetLocal = m[var45];
		double[] cv$countLocal = cv$var46$countGlobal[threadID$cv$var45];
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var45 == initialState)) {
						for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
							if(fixedFlag$sample103) {
								{
									int index$i$3 = i$var92;
									{
										{
											{
												{
													cv$countLocal[st[i$var92][0]] = (cv$countLocal[st[i$var92][0]] + 1.0);
												}
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
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var109)) {
										if((0 == (j$var121 - 1))) {
											if((var45 == st[i$var109][(j$var121 - 1)])) {
												if(fixedFlag$sample136) {
													{
														int index$j$27 = j$var121;
														int index$i$28 = i$var109;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + 1.0);
																	}
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
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										for(int index$sample103$9 = 0; index$sample103$9 < noStates; index$sample103$9 += 1) {
											int distributionTempVariable$var97$11 = index$sample103$9;
											double cv$probabilitySample103Value10 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$9]);
											int traceTempVariable$var126$12_1 = distributionTempVariable$var97$11;
											if((i$var92 == i$var109)) {
												if((0 == (j$var121 - 1))) {
													if((var45 == traceTempVariable$var126$12_1)) {
														if(fixedFlag$sample136) {
															{
																int index$j$30 = j$var121;
																int index$i$31 = i$var109;
																{
																	{
																		{
																			{
																				cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + cv$probabilitySample103Value10);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(fixedFlag$sample136) {
								for(int index$i$17_1 = 0; index$i$17_1 < samples; index$i$17_1 += 1) {
									for(int index$j$17_2 = 1; index$j$17_2 < length$eventsMeasured[index$i$17_1]; index$j$17_2 += 1) {
										if((index$i$17_1 == i$var109)) {
											if((index$j$17_2 == (j$var121 - 1))) {
												if((var45 == st[i$var109][(j$var121 - 1)])) {
													if(fixedFlag$sample136) {
														{
															int index$j$33 = j$var121;
															int index$i$34 = i$var109;
															{
																{
																	{
																		{
																			cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + 1.0);
																		}
																	}
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
								for(int index$i$18 = 0; index$i$18 < samples; index$i$18 += 1) {
									for(int index$j$19 = 1; index$j$19 < length$eventsMeasured[index$i$18]; index$j$19 += 1) {
										if(true) {
											for(int index$sample136$20 = 0; index$sample136$20 < noStates; index$sample136$20 += 1) {
												int distributionTempVariable$var129$22 = index$sample136$20;
												double cv$probabilitySample136Value21 = (1.0 * distribution$sample136[((index$i$18 - 0) / 1)][((index$j$19 - 1) / 1)][index$sample136$20]);
												int traceTempVariable$var126$23_1 = distributionTempVariable$var129$22;
												if((index$i$18 == i$var109)) {
													if((index$j$19 == (j$var121 - 1))) {
														if((var45 == traceTempVariable$var126$23_1)) {
															if(fixedFlag$sample136) {
																{
																	int index$j$36 = j$var121;
																	int index$i$37 = i$var109;
																	{
																		{
																			{
																				{
																					cv$countLocal[st[i$var109][j$var121]] = (cv$countLocal[st[i$var109][j$var121]] + cv$probabilitySample136Value21);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				if((var45 == initialState)) {
					for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
						if(!fixedFlag$sample103) {
							{
								int index$i$44 = i$var92;
								{
									{
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample103[((i$var92 - 0) / 1)][cv$loopIndex] * cv$distributionProbability));
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
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						if(fixedFlag$sample103) {
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if((i$var92 == i$var109)) {
									if((0 == (j$var121 - 1))) {
										if((var45 == st[i$var109][(j$var121 - 1)])) {
											if(!fixedFlag$sample136) {
												{
													int index$j$67 = j$var121;
													int index$i$68 = i$var109;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
								if(true) {
									for(int index$sample103$49 = 0; index$sample103$49 < noStates; index$sample103$49 += 1) {
										int distributionTempVariable$var97$51 = index$sample103$49;
										double cv$probabilitySample103Value50 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$49]);
										int traceTempVariable$var126$52_1 = distributionTempVariable$var97$51;
										if((i$var92 == i$var109)) {
											if((0 == (j$var121 - 1))) {
												if((var45 == traceTempVariable$var126$52_1)) {
													if(!fixedFlag$sample136) {
														{
															int index$j$70 = j$var121;
															int index$i$71 = i$var109;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample103Value50);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
					for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
						if(fixedFlag$sample136) {
							for(int index$i$57_1 = 0; index$i$57_1 < samples; index$i$57_1 += 1) {
								for(int index$j$57_2 = 1; index$j$57_2 < length$eventsMeasured[index$i$57_1]; index$j$57_2 += 1) {
									if((index$i$57_1 == i$var109)) {
										if((index$j$57_2 == (j$var121 - 1))) {
											if((var45 == st[i$var109][(j$var121 - 1)])) {
												if(!fixedFlag$sample136) {
													{
														int index$j$73 = j$var121;
														int index$i$74 = i$var109;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
							for(int index$i$58 = 0; index$i$58 < samples; index$i$58 += 1) {
								for(int index$j$59 = 1; index$j$59 < length$eventsMeasured[index$i$58]; index$j$59 += 1) {
									if(true) {
										for(int index$sample136$60 = 0; index$sample136$60 < noStates; index$sample136$60 += 1) {
											int distributionTempVariable$var129$62 = index$sample136$60;
											double cv$probabilitySample136Value61 = (1.0 * distribution$sample136[((index$i$58 - 0) / 1)][((index$j$59 - 1) / 1)][index$sample136$60]);
											int traceTempVariable$var126$63_1 = distributionTempVariable$var129$62;
											if((index$i$58 == i$var109)) {
												if((index$j$59 == (j$var121 - 1))) {
													if((var45 == traceTempVariable$var126$63_1)) {
														if(!fixedFlag$sample136) {
															{
																int index$j$76 = j$var121;
																int index$i$77 = i$var109;
																{
																	{
																		double scopeVariable$reachedSourceProbability = 0.0;
																		{
																			scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																		}
																		double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample136Value61);
																		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																			cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																	}
																}
															}
														}
													}
												}
											}
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

	private final void sample62(int var59, int threadID$cv$var59, Rng RNG$) {
		double[] cv$targetLocal = bias[var59];
		double[] cv$countLocal = cv$var60$countGlobal[threadID$cv$var59];
		int cv$arrayLength = noEvents;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
						for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var142)) {
										if((0 == j$var156)) {
											if((var59 == st[i$var142][j$var156])) {
												{
													{
														{
															{
																{
																	cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + 1.0);
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
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										for(int index$sample103$5 = 0; index$sample103$5 < noStates; index$sample103$5 += 1) {
											int distributionTempVariable$var97$7 = index$sample103$5;
											double cv$probabilitySample103Value6 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$5]);
											int traceTempVariable$var159$8_1 = distributionTempVariable$var97$7;
											if((i$var92 == i$var142)) {
												if((0 == j$var156)) {
													if((var59 == traceTempVariable$var159$8_1)) {
														{
															{
																{
																	{
																		{
																			cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + cv$probabilitySample103Value6);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
						for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1) {
							if(fixedFlag$sample136) {
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
										if((i$var109 == i$var142)) {
											if((j$var121 == j$var156)) {
												if((var59 == st[i$var142][j$var156])) {
													{
														{
															{
																{
																	{
																		cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + 1.0);
																	}
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
								for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
									for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
										if(true) {
											for(int index$sample136$16 = 0; index$sample136$16 < noStates; index$sample136$16 += 1) {
												int distributionTempVariable$var129$18 = index$sample136$16;
												double cv$probabilitySample136Value17 = (1.0 * distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)][index$sample136$16]);
												int traceTempVariable$var159$19_1 = distributionTempVariable$var129$18;
												if((i$var109 == i$var142)) {
													if((j$var121 == j$var156)) {
														if((var59 == traceTempVariable$var159$19_1)) {
															{
																{
																	{
																		{
																			{
																				cv$countLocal[(events[i$var142][j$var156] - 1)] = (cv$countLocal[(events[i$var142][j$var156] - 1)] + cv$probabilitySample136Value17);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal);
	}

	private final void sample86() {
		double[] cv$targetLocal = weights;
		double[] cv$countLocal = cv$var80$countGlobal;
		int cv$arrayLength = noStates;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					{
						{
							{
								{
									{
										cv$countLocal[initialState] = (cv$countLocal[initialState] + 1.0);
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

	private final void sample88() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, noStates);
		}
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			initialState = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$weights;
				{
					cv$temp$0$weights = weights;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$weights.length))?Math.log(cv$temp$0$weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
							int traceTempVariable$initialState$1_2 = cv$currentValue;
							if(fixedFlag$sample103) {
								{
									int index$i$3 = i$var92;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double[] cv$temp$1$var95;
													{
														double[] var95 = m[traceTempVariable$initialState$1_2];
														cv$temp$1$var95 = var95;
													}
													if(((Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var92][0]) && (st[i$var92][0] < cv$temp$1$var95.length))?Math.log(cv$temp$1$var95[st[i$var92][0]]):Double.NEGATIVE_INFINITY)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
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
					for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
						int traceTempVariable$initialState$5_2 = cv$currentValue;
						if(!fixedFlag$sample103) {
							{
								int index$i$7 = i$var92;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var96;
								for(int cv$i = 0; cv$i < noStates; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								{
									{
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double[] cv$temp$2$var95;
										{
											double[] var95 = m[traceTempVariable$initialState$5_2];
											cv$temp$2$var95 = var95;
										}
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
										DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$2$var95);
									}
								}
								double[] cv$sampleDistribution = distribution$sample103[((i$var92 - 0) / 1)];
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
		initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var45 = 0; var45 < noStates; var45 += 1)
				cv$max = Math.max(cv$max, noStates);
			{
				int cv$threadCount = threadCount();
				cv$var46$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var46$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$max = 0;
			for(int var59 = 0; var59 < noStates; var59 += 1)
				cv$max = Math.max(cv$max, noEvents);
			{
				int cv$threadCount = threadCount();
				cv$var60$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var60$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$max = 0;
			cv$max = Math.max(cv$max, noStates);
			cv$var80$countGlobal = new double[cv$max];
		}
		{
			int cv$var47$max = noStates;
			cv$distributionAccumulator$var96 = new double[cv$var47$max];
		}
		{
			cv$var82$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$var47$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$distributionAccumulator$var128 = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var128[cv$index] = new double[cv$var47$max];
			}
		}
		{
			int cv$var47$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var97$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var97$stateProbabilityGlobal[cv$index] = new double[cv$var47$max];
			}
		}
		{
			int cv$var47$max = noStates;
			{
				int cv$threadCount = threadCount();
				cv$var129$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var129$stateProbabilityGlobal[cv$index] = new double[cv$var47$max];
			}
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[noStates];
		}
		{
			v2 = new double[noEvents];
		}
		if(!setFlag$m) {
			{
				m = new double[noStates][];
				for(int var45 = 0; var45 < noStates; var45 += 1)
					m[var45] = new double[noStates];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[noStates][];
				for(int var59 = 0; var59 < noStates; var59 += 1)
					bias[var59] = new double[noEvents];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var73 = 0; i$var73 < length$eventsMeasured.length; i$var73 += 1)
					st[i$var73] = new int[length$eventsMeasured[i$var73]];
			}
		}
		if(!setFlag$weights) {
			{
				weights = new double[noStates];
			}
		}
		if(!setFlag$events) {
			{
				events = new int[length$eventsMeasured.length][];
				for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
					events[i$var142] = new int[length$eventsMeasured[i$var142]];
			}
		}
		{
			distribution$sample103 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var92 = 0; i$var92 < length$eventsMeasured.length; i$var92 += 1)
				distribution$sample103[((i$var92 - 0) / 1)] = new double[noStates];
		}
		{
			distribution$sample136 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][][];
			for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1) {
				double[][] subarray$0 = new double[((((length$eventsMeasured[i$var109] - 1) - 1) / 1) + 1)][];
				distribution$sample136[((i$var109 - 0) / 1)] = subarray$0;
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
					subarray$0[((j$var121 - 1) / 1)] = new double[noStates];
			}
		}
		{
			logProbability$var96 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample103 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$var128 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1)
				logProbability$var128[((i$var109 - 0) / 1)] = new double[((((length$eventsMeasured[i$var109] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample136 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var109 = 0; i$var109 < length$eventsMeasured.length; i$var109 += 1)
				logProbability$sample136[((i$var109 - 0) / 1)] = new double[((((length$eventsMeasured[i$var109] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var161 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
				logProbability$var161[((i$var142 - 0) / 1)] = new double[((((length$eventsMeasured[i$var142] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample171 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var142 = 0; i$var142 < length$eventsMeasured.length; i$var142 += 1)
				logProbability$sample171[((i$var142 - 0) / 1)] = new double[((((length$eventsMeasured[i$var142] - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$index$i$var142, int forEnd$index$i$var142, int threadID$index$i$var142, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var142 = forStart$index$i$var142; index$i$var142 < forEnd$index$i$var142; index$i$var142 += 1) {
						int i$var142 = index$i$var142;
						int threadID$i$var142 = threadID$index$i$var142;
						int[] var157 = events[i$var142];
						parallelFor(RNG$1, 1, length$eventsMeasured[i$var142], 1,
							(int forStart$j$var156, int forEnd$j$var156, int threadID$j$var156, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var156 = forStart$j$var156; j$var156 < forEnd$j$var156; j$var156 += 1) {
										if(!fixedFlag$sample171)
											var157[j$var156] = (DistributionSampling.sampleCategorical(RNG$2, bias[st[i$var142][j$var156]]) + 1);
									}
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						double[] cv$distribution$sample103 = distribution$sample103[((i$var92 - 0) / 1)];
						double[] var95 = m[initialState];
						for(int index$var96 = 0; index$var96 < noStates; index$var96 += 1) {
							double cv$value = (((0.0 <= index$var96) && (index$var96 < var95.length))?var95[index$var96]:0.0);
							if(!fixedFlag$sample103)
								cv$distribution$sample103[index$var96] = cv$value;
						}
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							double[] cv$distribution$sample136 = distribution$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)];
							for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1) {
								if(!fixedFlag$sample136)
									cv$distribution$sample136[index$var128] = 0.0;
							}
							if(fixedFlag$sample103) {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if((i$var92 == i$var109)) {
										if((0 == (j$var121 - 1))) {
											for(int var45 = 0; var45 < noStates; var45 += 1) {
												if((var45 == st[i$var109][(j$var121 - 1)])) {
													{
														if(!fixedFlag$sample136) {
															double[] var127 = m[st[i$var109][(j$var121 - 1)]];
															for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
																cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (1.0 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int i$var92 = 0; i$var92 < samples; i$var92 += 1) {
									if(true) {
										for(int index$sample103$3 = 0; index$sample103$3 < noStates; index$sample103$3 += 1) {
											int distributionTempVariable$var97$5 = index$sample103$3;
											double cv$probabilitySample103Value4 = (1.0 * distribution$sample103[((i$var92 - 0) / 1)][index$sample103$3]);
											int traceTempVariable$var126$6_1 = distributionTempVariable$var97$5;
											if((i$var92 == i$var109)) {
												if((0 == (j$var121 - 1))) {
													for(int var45 = 0; var45 < noStates; var45 += 1) {
														if((var45 == traceTempVariable$var126$6_1)) {
															{
																if(!fixedFlag$sample136) {
																	double[] var127 = m[traceTempVariable$var126$6_1];
																	for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
																		cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (cv$probabilitySample103Value4 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
																}
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
								for(int index$i$9_1 = 0; index$i$9_1 < samples; index$i$9_1 += 1) {
									for(int index$j$9_2 = 1; index$j$9_2 < length$eventsMeasured[index$i$9_1]; index$j$9_2 += 1) {
										if((index$i$9_1 == i$var109)) {
											if((index$j$9_2 == (j$var121 - 1))) {
												for(int var45 = 0; var45 < noStates; var45 += 1) {
													if((var45 == st[i$var109][(j$var121 - 1)])) {
														{
															if(!fixedFlag$sample136) {
																double[] var127 = m[st[i$var109][(j$var121 - 1)]];
																for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
																	cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (1.0 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int index$i$10 = 0; index$i$10 < samples; index$i$10 += 1) {
									for(int index$j$11 = 1; index$j$11 < length$eventsMeasured[index$i$10]; index$j$11 += 1) {
										if(true) {
											for(int index$sample136$12 = 0; index$sample136$12 < noStates; index$sample136$12 += 1) {
												int distributionTempVariable$var129$14 = index$sample136$12;
												double cv$probabilitySample136Value13 = (1.0 * distribution$sample136[((index$i$10 - 0) / 1)][((index$j$11 - 1) / 1)][index$sample136$12]);
												int traceTempVariable$var126$15_1 = distributionTempVariable$var129$14;
												if((index$i$10 == i$var109)) {
													if((index$j$11 == (j$var121 - 1))) {
														for(int var45 = 0; var45 < noStates; var45 += 1) {
															if((var45 == traceTempVariable$var126$15_1)) {
																{
																	if(!fixedFlag$sample136) {
																		double[] var127 = m[traceTempVariable$var126$15_1];
																		for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1)
																			cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] + (cv$probabilitySample136Value13 * (((0.0 <= index$var128) && (index$var128 < var127.length))?var127[index$var128]:0.0)));
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							double cv$var128$sum = 0.0;
							for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1) {
								if(!fixedFlag$sample136)
									cv$var128$sum = (cv$var128$sum + cv$distribution$sample136[index$var128]);
							}
							for(int index$var128 = 0; index$var128 < noStates; index$var128 += 1) {
								if(!fixedFlag$sample136)
									cv$distribution$sample136[index$var128] = (cv$distribution$sample136[index$var128] / cv$var128$sum);
							}
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								sample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
							if(!fixedFlag$sample62)
								sample62(var59, threadID$var59, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample86)
				sample86();
			if(!fixedFlag$sample88)
				sample88();
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
							if(!fixedFlag$sample103)
								sample103(i$var92, threadID$i$var92, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
							for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
								if(!fixedFlag$sample136)
									sample136(i$var109, j$var121, threadID$i$var109, RNG$1);
							}
						}
				}
			);
		} else {
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
							for(int j$var121 = (length$eventsMeasured[i$var109] - ((((length$eventsMeasured[i$var109] - 1) - 1) % 1) + 1)); j$var121 >= ((1 - 1) + 1); j$var121 -= 1) {
								if(!fixedFlag$sample136)
									sample136(i$var109, j$var121, threadID$i$var109, RNG$1);
							}
						}
				}
			);
			parallelFor(RNG$, 0, samples, 1,
				(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
							if(!fixedFlag$sample103)
								sample103(i$var92, threadID$i$var92, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample88)
				sample88();
			if(!fixedFlag$sample86)
				sample86();
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
							if(!fixedFlag$sample62)
								sample62(var59, threadID$var59, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, noStates, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								sample47(var45, threadID$var45, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var18, int forEnd$var18, int threadID$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var18 = forStart$var18; var18 < forEnd$var18; var18 += 1)
						v[var18] = 0.1;
			}
		);
		parallelFor(RNG$, 0, noEvents, 1,
			(int forStart$var31, int forEnd$var31, int threadID$var31, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var31 = forStart$var31; var31 < forEnd$var31; var31 += 1)
						v2[var31] = 0.1;
			}
		);
		samples = length$eventsMeasured.length;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var34 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = 0.0;
		logProbability$var48 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample62)
			logProbability$var60 = 0.0;
		logProbability$var79 = 0.0;
		if(!fixedProbFlag$sample86)
			logProbability$weights = 0.0;
		logProbability$var81 = 0.0;
		if(!fixedProbFlag$sample88)
			logProbability$initialState = 0.0;
		for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
			logProbability$var96[((i$var92 - 0) / 1)] = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample103) {
			for(int i$var92 = 0; i$var92 < samples; i$var92 += 1)
				logProbability$sample103[((i$var92 - 0) / 1)] = 0.0;
		}
		for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
			for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
				logProbability$var128[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = 0.0;
		}
		if(!fixedProbFlag$sample136) {
			for(int i$var109 = 0; i$var109 < samples; i$var109 += 1) {
				for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1)
					logProbability$sample136[((i$var109 - 0) / 1)][((j$var121 - 1) / 1)] = 0.0;
			}
		}
		for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
			for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1)
				logProbability$var161[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = 0.0;
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample171) {
			for(int i$var142 = 0; i$var142 < samples; i$var142 += 1) {
				for(int j$var156 = 1; j$var156 < length$eventsMeasured[i$var142]; j$var156 += 1)
					logProbability$sample171[((i$var142 - 0) / 1)][((j$var156 - 1) / 1)] = 0.0;
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
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample62)
			logProbabilityValue$sample62();
		if(fixedFlag$sample86)
			logProbabilityValue$sample86();
		if(fixedFlag$sample88)
			logProbabilityValue$sample88();
		logProbabilityValue$sample171();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityDistribution$sample103();
		logProbabilityDistribution$sample136();
		logProbabilityDistribution$sample171();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample86();
		logProbabilityValue$sample88();
		logProbabilityValue$sample103();
		logProbabilityValue$sample136();
		logProbabilityValue$sample171();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						double[] var46 = m[var45];
						if(!fixedFlag$sample47)
							DistributionSampling.sampleDirichlet(RNG$1, v, var46);
					}
			}
		);
		parallelFor(RNG$, 0, noStates, 1,
			(int forStart$var59, int forEnd$var59, int threadID$var59, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var59 = forStart$var59; var59 < forEnd$var59; var59 += 1) {
						double[] var60 = bias[var59];
						if(!fixedFlag$sample62)
							DistributionSampling.sampleDirichlet(RNG$1, v2, var60);
					}
			}
		);
		if(!fixedFlag$sample86)
			DistributionSampling.sampleDirichlet(RNG$, v, weights);
		if(!fixedFlag$sample88)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var92, int forEnd$i$var92, int threadID$i$var92, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var92 = forStart$i$var92; i$var92 < forEnd$i$var92; i$var92 += 1) {
						int[] var93 = st[i$var92];
						if(!fixedFlag$sample103)
							var93[0] = DistributionSampling.sampleCategorical(RNG$1, m[initialState]);
					}
			}
		);
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$i$var109, int forEnd$i$var109, int threadID$i$var109, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var109 = forStart$i$var109; i$var109 < forEnd$i$var109; i$var109 += 1) {
						int[] var122 = st[i$var109];
						for(int j$var121 = 1; j$var121 < length$eventsMeasured[i$var109]; j$var121 += 1) {
							if(!fixedFlag$sample136)
								var122[j$var121] = DistributionSampling.sampleCategorical(RNG$1, m[st[i$var109][(j$var121 - 1)]]);
						}
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[][] cv$source1 = eventsMeasured;
		int[][] cv$target1 = events;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
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