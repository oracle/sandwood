package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Mk2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMM_Mk2$CoreInterface {
	private double[][] bias;
	private boolean[][] constrainedFlag$sample126;
	private boolean[] constrainedFlag$sample42;
	private boolean[] constrainedFlag$sample57;
	private boolean constrainedFlag$sample78 = true;
	private boolean constrainedFlag$sample80 = true;
	private boolean[] constrainedFlag$sample95;
	private double[] cv$var123$stateProbabilityGlobal;
	private double[] cv$var42$countGlobal;
	private double[] cv$var56$countGlobal;
	private double[] cv$var75$countGlobal;
	private double[] cv$var77$stateProbabilityGlobal;
	private double[] cv$var92$stateProbabilityGlobal;
	private int[][] events;
	private int[][] eventsMeasured;
	private boolean fixedFlag$sample126 = false;
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
	private double logProbability$var42;
	private double logProbability$var56;
	private double logProbability$weights;
	private double[][] m;
	private int noEvents;
	private int noStates;
	private int samples;
	private int[][] st;
	private boolean system$gibbsForward = true;
	private double[] v;
	private double[] v2;
	private double[] weights;

	public HMM_Mk2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[][] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[][] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample159 = false;
	}

	@Override
	public final int[][] get$events() {
		return events;
	}

	@Override
	public final int[][] get$eventsMeasured() {
		return eventsMeasured;
	}

	@Override
	public final void set$eventsMeasured(int[][] cv$value, boolean allocated$) {
		eventsMeasured = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample126() {
		return fixedFlag$sample126;
	}

	@Override
	public final void set$fixedFlag$sample126(boolean cv$value, boolean allocated$) {
		fixedFlag$sample126 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
				boolean[] cv$constrainedFlag$sample126$1 = constrainedFlag$sample126[index$constrainedFlag$sample126$1];
				for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
					cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = fixedFlag$sample126;
			}
		}
		fixedProbFlag$sample126 = (fixedFlag$sample126 && fixedProbFlag$sample126);
		fixedProbFlag$sample159 = (fixedFlag$sample126 && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample42() {
		return fixedFlag$sample42;
	}

	@Override
	public final void set$fixedFlag$sample42(boolean cv$value, boolean allocated$) {
		fixedFlag$sample42 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
				constrainedFlag$sample42[index$constrainedFlag$sample42$1] = fixedFlag$sample42;
		}
		fixedProbFlag$sample42 = (fixedFlag$sample42 && fixedProbFlag$sample42);
		fixedProbFlag$sample95 = (fixedFlag$sample42 && fixedProbFlag$sample95);
		fixedProbFlag$sample126 = (fixedFlag$sample42 && fixedProbFlag$sample126);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value, boolean allocated$) {
		fixedFlag$sample57 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
				constrainedFlag$sample57[index$constrainedFlag$sample57$1] = fixedFlag$sample57;
		}
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		fixedProbFlag$sample159 = (fixedFlag$sample57 && fixedProbFlag$sample159);
	}

	@Override
	public final boolean get$fixedFlag$sample78() {
		return fixedFlag$sample78;
	}

	@Override
	public final void set$fixedFlag$sample78(boolean cv$value, boolean allocated$) {
		fixedFlag$sample78 = cv$value;
		constrainedFlag$sample78 = (fixedFlag$sample78 || constrainedFlag$sample78);
		fixedProbFlag$sample78 = (fixedFlag$sample78 && fixedProbFlag$sample78);
		fixedProbFlag$sample80 = (fixedFlag$sample78 && fixedProbFlag$sample80);
	}

	@Override
	public final boolean get$fixedFlag$sample80() {
		return fixedFlag$sample80;
	}

	@Override
	public final void set$fixedFlag$sample80(boolean cv$value, boolean allocated$) {
		fixedFlag$sample80 = cv$value;
		constrainedFlag$sample80 = (fixedFlag$sample80 || constrainedFlag$sample80);
		fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedProbFlag$sample80);
		fixedProbFlag$sample95 = (fixedFlag$sample80 && fixedProbFlag$sample95);
	}

	@Override
	public final boolean get$fixedFlag$sample95() {
		return fixedFlag$sample95;
	}

	@Override
	public final void set$fixedFlag$sample95(boolean cv$value, boolean allocated$) {
		fixedFlag$sample95 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
				constrainedFlag$sample95[index$constrainedFlag$sample95$1] = fixedFlag$sample95;
		}
		fixedProbFlag$sample95 = (fixedFlag$sample95 && fixedProbFlag$sample95);
		fixedProbFlag$sample126 = (fixedFlag$sample95 && fixedProbFlag$sample126);
		fixedProbFlag$sample159 = (fixedFlag$sample95 && fixedProbFlag$sample159);
	}

	@Override
	public final int get$initialState() {
		return initialState;
	}

	@Override
	public final void set$initialState(int cv$value, boolean allocated$) {
		initialState = cv$value;
		fixedProbFlag$sample80 = false;
		fixedProbFlag$sample95 = false;
	}

	@Override
	public final int[] get$length$eventsMeasured() {
		return length$eventsMeasured;
	}

	@Override
	public final void set$length$eventsMeasured(int[] cv$value, boolean allocated$) {
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
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample42 = false;
		fixedProbFlag$sample95 = false;
		fixedProbFlag$sample126 = false;
	}

	@Override
	public final int get$noEvents() {
		return noEvents;
	}

	@Override
	public final void set$noEvents(int cv$value, boolean allocated$) {
		noEvents = cv$value;
	}

	@Override
	public final int get$noStates() {
		return noStates;
	}

	@Override
	public final void set$noStates(int cv$value, boolean allocated$) {
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
	public final void set$st(int[][] cv$value, boolean allocated$) {
		st = cv$value;
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
	public final void set$weights(double[] cv$value, boolean allocated$) {
		weights = cv$value;
		fixedProbFlag$sample78 = false;
		fixedProbFlag$sample80 = false;
	}

	private final void drawValueSample126(int i$var104, int j$var115) {
		int[] var116 = st[i$var104];
		var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
	}

	private final void drawValueSample42(int var41) {
		double[] var42 = m[var41];
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, var42);
	}

	private final void drawValueSample57(int var55) {
		double[] var56 = bias[var55];
		DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, var56);
	}

	private final void drawValueSample78() {
		DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
	}

	private final void drawValueSample80() {
		initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
	}

	private final void drawValueSample95(int i$var87) {
		int[] var88 = st[i$var87];
		var88[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
	}

	private final void inferSample126(int i$var104, int j$var115) {
		if(true) {
			constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var123$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var123 = cv$currentValue;
				{
					{
						{
							int[] var116 = st[i$var104];
							var116[j$var115] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var121 = m[st[i$var104][(j$var115 - 1)]];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$currentValue])) && (var121[cv$currentValue] <= 1.0))?Math.log(var121[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int index$i$2_2 = 0; index$i$2_2 < samples; index$i$2_2 += 1) {
									if((i$var104 == index$i$2_2)) {
										for(int index$j$2_3 = 1; index$j$2_3 < length$eventsMeasured[index$i$2_2]; index$j$2_3 += 1) {
											if((j$var115 == (index$j$2_3 - 1))) {
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((index$i$2_2 - 0) / 1)][((index$j$2_3 - 1) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] sc$var121$1 = m[traceTempVariable$var120$2_1];
																				if(((Math.log(1.0) + ((((((0.0 <= st[index$i$2_2][index$j$2_3]) && (st[index$i$2_2][index$j$2_3] < noStates)) && (0 < noStates)) && (0.0 <= sc$var121$1[st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[index$i$2_2][index$j$2_3]) && (st[index$i$2_2][index$j$2_3] < noStates)) && (0 < noStates)) && (0.0 <= sc$var121$1[st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[index$i$2_2][index$j$2_3]) && (st[index$i$2_2][index$j$2_3] < noStates)) && (0 < noStates)) && (0.0 <= sc$var121$1[st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[index$i$2_2][index$j$2_3]) && (st[index$i$2_2][index$j$2_3] < noStates)) && (0 < noStates)) && (0.0 <= sc$var121$1[st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[index$i$2_2][index$j$2_3]) && (st[index$i$2_2][index$j$2_3] < noStates)) && (0 < noStates)) && (0.0 <= sc$var121$1[st[index$i$2_2][index$j$2_3]])) && (sc$var121$1[st[index$i$2_2][index$j$2_3]] <= 1.0))?Math.log(sc$var121$1[st[index$i$2_2][index$j$2_3]]):Double.NEGATIVE_INFINITY)));
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
						}
					}
					{
						{
							{
								int traceTempVariable$var152$5_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
									if((i$var104 == i$var136)) {
										for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((j$var115 == j$var149)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] var153 = bias[traceTempVariable$var152$5_1];
																				if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]) {
				double cv$logSum = 0.0;
				{
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
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				int var123 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							int[] var116 = st[i$var104];
							var116[j$var115] = var123;
						}
					}
				}
			}
		}
	}

	private final void inferSample42(int var41) {
		if(true) {
			constrainedFlag$sample42[((var41 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var41];
			double[] cv$countLocal = cv$var42$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if((var41 == initialState)) {
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									boolean cv$sampleConstrained = (fixedFlag$sample95 || constrainedFlag$sample95[((i$var87 - 0) / 1)]);
									if(cv$sampleConstrained) {
										constrainedFlag$sample42[((var41 - 0) / 1)] = true;
										{
											{
												{
													{
														{
															cv$countLocal[st[i$var87][0]] = (cv$countLocal[st[i$var87][0]] + 1.0);
														}
													}
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
						{
							for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
									if((var41 == st[i$var104][(j$var115 - 1)])) {
										{
											{
												boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample42[((var41 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$countLocal[st[i$var104][j$var115]] = (cv$countLocal[st[i$var104][j$var115]] + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample42[((var41 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void inferSample57(int var55) {
		if(true) {
			constrainedFlag$sample57[((var55 - 0) / 1)] = false;
			double[] cv$targetLocal = bias[var55];
			double[] cv$countLocal = cv$var56$countGlobal;
			int cv$arrayLength = noEvents;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
								for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
									if((var55 == st[i$var136][j$var149])) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													constrainedFlag$sample57[((var55 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$countLocal[(events[i$var136][j$var149] - 1)] = (cv$countLocal[(events[i$var136][j$var149] - 1)] + 1.0);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if(constrainedFlag$sample57[((var55 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v2, cv$countLocal, cv$targetLocal, noEvents);
		}
	}

	private final void inferSample78() {
		if(true) {
			constrainedFlag$sample78 = false;
			double[] cv$targetLocal = weights;
			double[] cv$countLocal = cv$var75$countGlobal;
			int cv$arrayLength = noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									boolean cv$sampleConstrained = (fixedFlag$sample80 || constrainedFlag$sample80);
									if(cv$sampleConstrained) {
										constrainedFlag$sample78 = true;
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
						}
					}
				}
			}
			if(constrainedFlag$sample78)
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, noStates);
		}
	}

	private final void inferSample80() {
		if(true) {
			constrainedFlag$sample80 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var77$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				initialState = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= weights[cv$currentValue])) && (weights[cv$currentValue] <= 1.0))?Math.log(weights[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
									int traceTempVariable$initialState$1_2 = cv$currentValue;
									{
										{
											boolean cv$sampleConstrained = (fixedFlag$sample95 || constrainedFlag$sample95[((i$var87 - 0) / 1)]);
											if(cv$sampleConstrained) {
												constrainedFlag$sample80 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double[] var90 = m[traceTempVariable$initialState$1_2];
																	if(((Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[i$var87][0]) && (st[i$var87][0] < noStates)) && (0 < noStates)) && (0.0 <= var90[st[i$var87][0]])) && (var90[st[i$var87][0]] <= 1.0))?Math.log(var90[st[i$var87][0]]):Double.NEGATIVE_INFINITY)));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample80) {
				double cv$logSum = 0.0;
				{
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
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				initialState = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void inferSample95(int i$var87) {
		if(true) {
			constrainedFlag$sample95[((i$var87 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, noStates);
			}
			double[] cv$stateProbabilityLocal = cv$var92$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var92 = cv$currentValue;
				{
					{
						{
							int[] var88 = st[i$var87];
							var88[0] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var90 = m[initialState];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$currentValue])) && (var90[cv$currentValue] <= 1.0))?Math.log(var90[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var120$2_1 = cv$currentValue;
								for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
									if((i$var87 == i$var104)) {
										for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
											if((0 == (j$var115 - 1))) {
												{
													{
														boolean cv$sampleConstrained = (fixedFlag$sample126 || constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)]);
														if(cv$sampleConstrained) {
															constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] var121 = m[traceTempVariable$var120$2_1];
																				if(((Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[i$var104][j$var115]) && (st[i$var104][j$var115] < noStates)) && (0 < noStates)) && (0.0 <= var121[st[i$var104][j$var115]])) && (var121[st[i$var104][j$var115]] <= 1.0))?Math.log(var121[st[i$var104][j$var115]]):Double.NEGATIVE_INFINITY)));
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
						}
					}
					{
						{
							{
								int traceTempVariable$var152$5_1 = cv$currentValue;
								for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
									if((i$var87 == i$var136)) {
										for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
											if((0 == j$var149)) {
												{
													{
														boolean cv$sampleConstrained = true;
														if(cv$sampleConstrained) {
															constrainedFlag$sample95[((i$var87 - 0) / 1)] = true;
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			{
																				double[] var153 = bias[traceTempVariable$var152$5_1];
																				if(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (events[i$var136][j$var149] - 1)) && ((events[i$var136][j$var149] - 1) < noEvents)) && (0 < noEvents)) && (0.0 <= var153[(events[i$var136][j$var149] - 1)])) && (var153[(events[i$var136][j$var149] - 1)] <= 1.0))?Math.log(var153[(events[i$var136][j$var149] - 1)]):Double.NEGATIVE_INFINITY)));
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(constrainedFlag$sample95[((i$var87 - 0) / 1)]) {
				double cv$logSum = 0.0;
				{
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
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				int var92 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							int[] var88 = st[i$var87];
							var88[0] = var92;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!fixedProbFlag$sample126) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = st[i$var104][j$var115];
							{
								{
									double[] var121 = m[st[i$var104][(j$var115 - 1)]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample126)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample126 = ((fixedFlag$sample126 && fixedFlag$sample42) && fixedFlag$sample95);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
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
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						{
							int cv$sampleValue = (events[i$var136][j$var149] - 1);
							{
								{
									double[] var153 = bias[st[i$var136][j$var149]];
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noEvents)) && (0 < noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					cv$sampleReached = true;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = cv$sampleProbability;
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample159 = ((fixedFlag$sample57 && fixedFlag$sample95) && fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$sampleReached = true;
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				}
			}
			logProbability$events = (logProbability$events + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < noStates; var41 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var41];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample42 = fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < noStates; var41 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample42)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = bias[var55];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v2, noEvents));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var56 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = fixedFlag$sample57;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var55 = 0; var55 < noStates; var55 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var56;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = weights;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, noStates));
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
			logProbability$weights = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = fixedFlag$sample78;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$weights;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample78)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!fixedProbFlag$sample80) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = initialState;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= weights[cv$sampleValue])) && (weights[cv$sampleValue] <= 1.0))?Math.log(weights[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$initialState = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample80 = (fixedFlag$sample80 && fixedFlag$sample78);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialState;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample80)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = st[i$var87][0];
						{
							{
								double[] var90 = m[initialState];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < noStates)) && (0 < noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample95[((i$var87 - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample95 = ((fixedFlag$sample95 && fixedFlag$sample42) && fixedFlag$sample80);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample95[((i$var87 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample95)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var42$countGlobal = new double[noStates];
		}
		{
			cv$var56$countGlobal = new double[noEvents];
		}
		{
			cv$var75$countGlobal = new double[noStates];
		}
		{
			cv$var77$stateProbabilityGlobal = new double[noStates];
		}
		{
			int cv$var43$max = noStates;
			cv$var92$stateProbabilityGlobal = new double[cv$var43$max];
		}
		{
			int cv$var43$max = noStates;
			cv$var123$stateProbabilityGlobal = new double[cv$var43$max];
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
		if(!fixedFlag$sample42) {
			{
				m = new double[noStates][];
				for(int var41 = 0; var41 < noStates; var41 += 1)
					m[var41] = new double[noStates];
			}
		}
		if(!fixedFlag$sample57) {
			{
				bias = new double[noStates][];
				for(int var55 = 0; var55 < noStates; var55 += 1)
					bias[var55] = new double[noEvents];
			}
		}
		if((!fixedFlag$sample95 || !fixedFlag$sample126)) {
			{
				st = new int[length$eventsMeasured.length][];
				for(int i$var69 = 0; i$var69 < length$eventsMeasured.length; i$var69 += 1)
					st[i$var69] = new int[length$eventsMeasured[i$var69]];
			}
		}
		if(!fixedFlag$sample78) {
			{
				weights = new double[noStates];
			}
		}
		{
			events = new int[length$eventsMeasured.length][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				events[i$var136] = new int[length$eventsMeasured[i$var136]];
		}
		{
			constrainedFlag$sample95 = new boolean[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample126 = new boolean[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				constrainedFlag$sample126[((i$var104 - 0) / 1)] = new boolean[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		{
			constrainedFlag$sample42 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample57 = new boolean[((((noStates - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample95 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample126 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var104 = 0; i$var104 < length$eventsMeasured.length; i$var104 += 1)
				logProbability$sample126[((i$var104 - 0) / 1)] = new double[((((length$eventsMeasured[i$var104] - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample159 = new double[((((length$eventsMeasured.length - 1) - 0) / 1) + 1)][];
			for(int i$var136 = 0; i$var136 < length$eventsMeasured.length; i$var136 += 1)
				logProbability$sample159[((i$var136 - 0) / 1)] = new double[((((length$eventsMeasured[i$var136] - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var41 = 0; var41 < noStates; var41 += 1) {
			double[] var42 = m[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var42);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			double[] var56 = bias[var55];
			if(!fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, var56);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
			int[] var88 = st[i$var87];
			if(!fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			int[] var116 = st[i$var104];
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			int[] var150 = events[i$var136];
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
				var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var136][j$var149]], noEvents) + 1);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var41 = 0; var41 < noStates; var41 += 1) {
			double[] var42 = m[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var42);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			double[] var56 = bias[var55];
			if(!fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, var56);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
			int[] var88 = st[i$var87];
			if(!fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			int[] var116 = st[i$var104];
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var41 = 0; var41 < noStates; var41 += 1) {
			double[] var42 = m[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var42);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			double[] var56 = bias[var55];
			if(!fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, var56);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
			int[] var88 = st[i$var87];
			if(!fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			int[] var116 = st[i$var104];
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
		for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
			int[] var150 = events[i$var136];
			for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
				var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$, bias[st[i$var136][j$var149]], noEvents) + 1);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var41 = 0; var41 < noStates; var41 += 1) {
			double[] var42 = m[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var42);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			double[] var56 = bias[var55];
			if(!fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, var56);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
			int[] var88 = st[i$var87];
			if(!fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			int[] var116 = st[i$var104];
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var41 = 0; var41 < noStates; var41 += 1) {
			double[] var42 = m[var41];
			if(!fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(RNG$, v, noStates, var42);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			double[] var56 = bias[var55];
			if(!fixedFlag$sample57)
				DistributionSampling.sampleDirichlet(RNG$, v2, noEvents, var56);
		}
		if(!fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(RNG$, v, noStates, weights);
		if(!fixedFlag$sample80)
			initialState = DistributionSampling.sampleCategorical(RNG$, weights, noStates);
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
			int[] var88 = st[i$var87];
			if(!fixedFlag$sample95)
				var88[0] = DistributionSampling.sampleCategorical(RNG$, m[initialState], noStates);
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			int[] var116 = st[i$var104];
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!fixedFlag$sample126)
					var116[j$var115] = DistributionSampling.sampleCategorical(RNG$, m[st[i$var104][(j$var115 - 1)]], noStates);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var41 = 0; var41 < noStates; var41 += 1) {
				if(!fixedFlag$sample42)
					inferSample42(var41);
			}
			for(int var55 = 0; var55 < noStates; var55 += 1) {
				if(!fixedFlag$sample57)
					inferSample57(var55);
			}
			if(!fixedFlag$sample78)
				inferSample78();
			if(!fixedFlag$sample80)
				inferSample80();
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
				if(!fixedFlag$sample95)
					inferSample95(i$var87);
			}
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
					if(!fixedFlag$sample126)
						inferSample126(i$var104, j$var115);
				}
			}
		} else {
			for(int i$var104 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var104 >= ((0 - 1) + 1); i$var104 -= 1) {
				for(int j$var115 = (length$eventsMeasured[i$var104] - ((((length$eventsMeasured[i$var104] - 1) - 1) % 1) + 1)); j$var115 >= ((1 - 1) + 1); j$var115 -= 1) {
					if(!fixedFlag$sample126)
						inferSample126(i$var104, j$var115);
				}
			}
			for(int i$var87 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var87 >= ((0 - 1) + 1); i$var87 -= 1) {
				if(!fixedFlag$sample95)
					inferSample95(i$var87);
			}
			if(!fixedFlag$sample80)
				inferSample80();
			if(!fixedFlag$sample78)
				inferSample78();
			for(int var55 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var55 >= ((0 - 1) + 1); var55 -= 1) {
				if(!fixedFlag$sample57)
					inferSample57(var55);
			}
			for(int var41 = (noStates - ((((noStates - 1) - 0) % 1) + 1)); var41 >= ((0 - 1) + 1); var41 -= 1) {
				if(!fixedFlag$sample42)
					inferSample42(var41);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var41 = 0; var41 < noStates; var41 += 1) {
			if(!constrainedFlag$sample42[((var41 - 0) / 1)])
				drawValueSample42(var41);
		}
		for(int var55 = 0; var55 < noStates; var55 += 1) {
			if(!constrainedFlag$sample57[((var55 - 0) / 1)])
				drawValueSample57(var55);
		}
		if(!constrainedFlag$sample78)
			drawValueSample78();
		if(!constrainedFlag$sample80)
			drawValueSample80();
		for(int i$var87 = 0; i$var87 < samples; i$var87 += 1) {
			if(!constrainedFlag$sample95[((i$var87 - 0) / 1)])
				drawValueSample95(i$var87);
		}
		for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
			for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1) {
				if(!constrainedFlag$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)])
					drawValueSample126(i$var104, j$var115);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample42)
			logProbability$var42 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var56 = Double.NaN;
		if(!fixedProbFlag$sample78)
			logProbability$weights = Double.NaN;
		if(!fixedProbFlag$sample80)
			logProbability$initialState = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < samples; i$var87 += 1)
				logProbability$sample95[((i$var87 - 0) / 1)] = Double.NaN;
		}
		if(!fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < length$eventsMeasured[i$var104]; j$var115 += 1)
					logProbability$sample126[((i$var104 - 0) / 1)][((j$var115 - 1) / 1)] = Double.NaN;
			}
		}
		logProbability$events = 0.0;
		if(!fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < length$eventsMeasured[i$var136]; j$var149 += 1)
					logProbability$sample159[((i$var136 - 0) / 1)][((j$var149 - 1) / 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		for(int var14 = 0; var14 < noStates; var14 += 1)
			v[var14] = 0.1;
		for(int var27 = 0; var27 < noEvents; var27 += 1)
			v2[var27] = 0.1;
		samples = length$eventsMeasured.length;
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
			boolean[] cv$constrainedFlag$sample126$1 = constrainedFlag$sample126[index$constrainedFlag$sample126$1];
			for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
				cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(fixedFlag$sample80)
			logProbabilityValue$sample80();
		if(fixedFlag$sample95)
			logProbabilityValue$sample95();
		if(fixedFlag$sample126)
			logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logModelProbabilitiesDist() {
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
	public final void propagateObservedValues() {
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
		     + "model HMM_Mk2(int[][] eventsMeasured, int noStates, int noEvents) {\n"
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
		     + "            st[i][0] = categorical(m[initialState]).sample();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sample();\n"
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