package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart7$CoreInterface {
	private double[] bias;
	private double[] cv$distributionAccumulator$var69;
	private double[] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private double[] distribution$sample53;
	private double[][] distribution$sample71;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var16;
	private double logProbability$var28;
	private double logProbability$var32;
	private double logProbability$var44;
	private double logProbability$var51;
	private double logProbability$var52;
	private double[] logProbability$var69;
	private double[] logProbability$var85;
	private double[][] m;
	private int samples;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart7$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[] get$distribution$sample53() {
		return distribution$sample53;
	}

	@Override
	public final void set$distribution$sample53(double[] cv$value) {
		distribution$sample53 = cv$value;
	}

	@Override
	public final double[][] get$distribution$sample71() {
		return distribution$sample71;
	}

	@Override
	public final void set$distribution$sample71(double[][] cv$value) {
		distribution$sample71 = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample53 = (fixedFlag$sample28 && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (fixedFlag$sample28 && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample87 = (fixedFlag$sample45 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value) {
		fixedFlag$sample53 = cv$value;
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (fixedFlag$sample53 && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (fixedFlag$sample53 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value) {
		fixedFlag$sample71 = cv$value;
		fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (fixedFlag$sample71 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$flips() {
		return logProbability$flips;
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
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value) {
		st = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample53() {
		if(!fixedProbFlag$sample53) {
			if(fixedFlag$sample53) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[0];
					{
						{
							double[] var50 = m[0];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var51 = cv$sampleAccumulator;
				logProbability$var52 = cv$sampleProbability;
				if(fixedFlag$sample53)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample53)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			if(fixedFlag$sample53)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample71() {
		if(!fixedProbFlag$sample71) {
			if(fixedFlag$sample71) {
				double cv$accumulator = 0.0;
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var64;
					{
						int cv$sampleValue = st[i$var64];
						if(fixedFlag$sample53) {
							if((0 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										{
											double[] var68 = m[st[(i$var64 - 1)]];
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						} else {
							if(true) {
								for(int index$sample53$4 = 0; index$sample53$4 < states; index$sample53$4 += 1) {
									int distributionTempVariable$var52$6 = index$sample53$4;
									double cv$probabilitySample53Value5 = (1.0 * distribution$sample53[index$sample53$4]);
									if((0 == (i$var64 - 1))) {
										for(int var27 = 0; var27 < states; var27 += 1) {
											if((var27 == st[(i$var64 - 1)])) {
												{
													double[] var68 = m[st[(i$var64 - 1)]];
													double cv$weightedProbability = (Math.log(cv$probabilitySample53Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value5);
												}
											}
										}
									}
								}
							}
						}
						if((index$i$1 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									{
										double[] var68 = m[st[(i$var64 - 1)]];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample71) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var64 - 1))) {
									for(int var27 = 0; var27 < states; var27 += 1) {
										if((var27 == st[(i$var64 - 1)])) {
											{
												double[] var68 = m[st[(i$var64 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						} else {
							for(int index$i$12 = 1; index$i$12 < samples; index$i$12 += 1) {
								if(!(index$i$12 == index$i$1)) {
									for(int index$sample71$13 = 0; index$sample71$13 < states; index$sample71$13 += 1) {
										int distributionTempVariable$var70$15 = index$sample71$13;
										double cv$probabilitySample71Value14 = (1.0 * distribution$sample71[((index$i$12 - 1) / 1)][index$sample71$13]);
										if((index$i$12 == (i$var64 - 1))) {
											for(int var27 = 0; var27 < states; var27 += 1) {
												if((var27 == st[(i$var64 - 1)])) {
													{
														double[] var68 = m[st[(i$var64 - 1)]];
														double cv$weightedProbability = (Math.log(cv$probabilitySample71Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value14);
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
					logProbability$var69[((i$var64 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample71[((i$var64 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample71)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample71)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((i$var64 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((i$var64 - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample71)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					if(fixedFlag$sample53) {
						if((0 == j)) {
							for(int var43 = 0; var43 < states; var43 += 1) {
								if((var43 == st[j])) {
									{
										double var84 = bias[st[j]];
										double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var84:(1.0 - var84))));
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
					} else {
						if(true) {
							for(int index$sample53$3 = 0; index$sample53$3 < states; index$sample53$3 += 1) {
								int distributionTempVariable$var52$5 = index$sample53$3;
								double cv$probabilitySample53Value4 = (1.0 * distribution$sample53[index$sample53$3]);
								if((0 == j)) {
									for(int var43 = 0; var43 < states; var43 += 1) {
										if((var43 == st[j])) {
											{
												double var84 = bias[st[j]];
												double cv$weightedProbability = (Math.log(cv$probabilitySample53Value4) + Math.log((cv$sampleValue?var84:(1.0 - var84))));
												if((cv$weightedProbability < cv$distributionAccumulator))
													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
												else {
													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
														cv$distributionAccumulator = cv$weightedProbability;
													else
														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
												}
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample53Value4);
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample71) {
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if((i$var64 == j)) {
								for(int var43 = 0; var43 < states; var43 += 1) {
									if((var43 == st[j])) {
										{
											double var84 = bias[st[j]];
											double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var84:(1.0 - var84))));
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
					} else {
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if(true) {
								for(int index$sample71$11 = 0; index$sample71$11 < states; index$sample71$11 += 1) {
									int distributionTempVariable$var70$13 = index$sample71$11;
									double cv$probabilitySample71Value12 = (1.0 * distribution$sample71[((i$var64 - 1) / 1)][index$sample71$11]);
									if((i$var64 == j)) {
										for(int var43 = 0; var43 < states; var43 += 1) {
											if((var43 == st[j])) {
												{
													double var84 = bias[st[j]];
													double cv$weightedProbability = (Math.log(cv$probabilitySample71Value12) + Math.log((cv$sampleValue?var84:(1.0 - var84))));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value12);
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
				logProbability$var85[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var27];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
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
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var16 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var43];
					{
						{
							double var30 = 1.0;
							double var31 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double[] var50 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var51 = cv$sampleAccumulator;
			logProbability$var52 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var51 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var64;
				{
					int cv$sampleValue = st[i$var64];
					{
						{
							double[] var68 = m[st[(i$var64 - 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var69[((i$var64 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample71[((i$var64 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((i$var64 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var69[((i$var64 - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var84 = bias[st[j]];
							double cv$weightedProbability = (Math.log(1.0) + Math.log((cv$sampleValue?var84:(1.0 - var84))));
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
				logProbability$var85[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var85[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample28(int var27) {
		if(true) {
			double[] cv$targetLocal = m[var27];
			double[] cv$countLocal = cv$var28$countGlobal;
			int cv$arrayLength = states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						if((var27 == 0)) {
							if(fixedFlag$sample53) {
								{
									{
										{
											{
												{
													cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
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
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if(fixedFlag$sample53) {
								if((0 == (i$var64 - 1))) {
									if((var27 == st[(i$var64 - 1)])) {
										if(fixedFlag$sample71) {
											{
												int index$i$22 = i$var64;
												{
													{
														{
															{
																cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								if(true) {
									for(int index$sample53$6 = 0; index$sample53$6 < states; index$sample53$6 += 1) {
										int distributionTempVariable$var52$8 = index$sample53$6;
										double cv$probabilitySample53Value7 = (1.0 * distribution$sample53[index$sample53$6]);
										if((0 == (i$var64 - 1))) {
											if((var27 == st[(i$var64 - 1)])) {
												if(fixedFlag$sample71) {
													{
														int index$i$24 = i$var64;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + cv$probabilitySample53Value7);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if(fixedFlag$sample71) {
								for(int index$i$13_1 = 1; index$i$13_1 < samples; index$i$13_1 += 1) {
									if((index$i$13_1 == (i$var64 - 1))) {
										if((var27 == st[(i$var64 - 1)])) {
											if(fixedFlag$sample71) {
												{
													int index$i$26 = i$var64;
													{
														{
															{
																{
																	cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + 1.0);
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
								for(int index$i$14 = 1; index$i$14 < samples; index$i$14 += 1) {
									if(true) {
										for(int index$sample71$15 = 0; index$sample71$15 < states; index$sample71$15 += 1) {
											int distributionTempVariable$var70$17 = index$sample71$15;
											double cv$probabilitySample71Value16 = (1.0 * distribution$sample71[((index$i$14 - 1) / 1)][index$sample71$15]);
											if((index$i$14 == (i$var64 - 1))) {
												if((var27 == st[(i$var64 - 1)])) {
													if(fixedFlag$sample71) {
														{
															int index$i$28 = i$var64;
															{
																{
																	{
																		{
																			cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + cv$probabilitySample71Value16);
																		}
																	}
																}
															}
														}
													}
												}
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
					if((var27 == 0)) {
						if(!fixedFlag$sample53) {
							{
								{
									{
										double scopeVariable$reachedSourceProbability = 0.0;
										{
											scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
										}
										double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
										for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
											cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample53[cv$loopIndex] * cv$distributionProbability));
									}
								}
							}
						}
					}
				}
			}
			{
				{
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if(fixedFlag$sample53) {
							if((0 == (i$var64 - 1))) {
								if((var27 == st[(i$var64 - 1)])) {
									if(!fixedFlag$sample71) {
										{
											int index$i$53 = i$var64;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample53$37 = 0; index$sample53$37 < states; index$sample53$37 += 1) {
									int distributionTempVariable$var52$39 = index$sample53$37;
									double cv$probabilitySample53Value38 = (1.0 * distribution$sample53[index$sample53$37]);
									if((0 == (i$var64 - 1))) {
										if((var27 == st[(i$var64 - 1)])) {
											if(!fixedFlag$sample71) {
												{
													int index$i$55 = i$var64;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample53Value38);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if(fixedFlag$sample71) {
							for(int index$i$44_1 = 1; index$i$44_1 < samples; index$i$44_1 += 1) {
								if((index$i$44_1 == (i$var64 - 1))) {
									if((var27 == st[(i$var64 - 1)])) {
										if(!fixedFlag$sample71) {
											{
												int index$i$57 = i$var64;
												{
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						} else {
							for(int index$i$45 = 1; index$i$45 < samples; index$i$45 += 1) {
								if(true) {
									for(int index$sample71$46 = 0; index$sample71$46 < states; index$sample71$46 += 1) {
										int distributionTempVariable$var70$48 = index$sample71$46;
										double cv$probabilitySample71Value47 = (1.0 * distribution$sample71[((index$i$45 - 1) / 1)][index$sample71$46]);
										if((index$i$45 == (i$var64 - 1))) {
											if((var27 == st[(i$var64 - 1)])) {
												if(!fixedFlag$sample71) {
													{
														int index$i$59 = i$var64;
														{
															{
																double scopeVariable$reachedSourceProbability = 0.0;
																{
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
																double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample71Value47);
																for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																	cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample71[((i$var64 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
		}
	}

	private final void sample45(int var43) {
		if(true) {
			double cv$sum = 0.0;
			double cv$count = 0.0;
			{
				{
					{
						for(int j = 0; j < samples; j += 1) {
							if(fixedFlag$sample53) {
								if((0 == j)) {
									if((var43 == st[j])) {
										{
											{
												{
													{
														{
															cv$count = (cv$count + 1.0);
															if(flips[j])
																cv$sum = (cv$sum + 1.0);
														}
													}
												}
											}
										}
									}
								}
							} else {
								if(true) {
									for(int index$sample53$3 = 0; index$sample53$3 < states; index$sample53$3 += 1) {
										int distributionTempVariable$var52$5 = index$sample53$3;
										double cv$probabilitySample53Value4 = (1.0 * distribution$sample53[index$sample53$3]);
										if((0 == j)) {
											if((var43 == st[j])) {
												{
													{
														{
															{
																{
																	cv$count = (cv$count + cv$probabilitySample53Value4);
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample53Value4);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						for(int j = 0; j < samples; j += 1) {
							if(fixedFlag$sample71) {
								for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
									if((i$var64 == j)) {
										if((var43 == st[j])) {
											{
												{
													{
														{
															{
																cv$count = (cv$count + 1.0);
																if(flips[j])
																	cv$sum = (cv$sum + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							} else {
								for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
									if(true) {
										for(int index$sample71$12 = 0; index$sample71$12 < states; index$sample71$12 += 1) {
											int distributionTempVariable$var70$14 = index$sample71$12;
											double cv$probabilitySample71Value13 = (1.0 * distribution$sample71[((i$var64 - 1) / 1)][index$sample71$12]);
											if((i$var64 == j)) {
												if((var43 == st[j])) {
													{
														{
															{
																{
																	{
																		cv$count = (cv$count + cv$probabilitySample71Value13);
																		if(flips[j])
																			cv$sum = (cv$sum + cv$probabilitySample71Value13);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
			{
				{
					bias[var43] = var44;
				}
			}
		}
	}

	private final void sample53() {
		if(true) {
			int cv$numNumStates = 0;
			{
				cv$numNumStates = Math.max(cv$numNumStates, states);
			}
			double[] cv$stateProbabilityLocal = cv$var52$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] cv$temp$0$var50;
					{
						double[] var50 = m[0];
						cv$temp$0$var50 = var50;
					}
					int cv$temp$1$$var292;
					{
						int $var292 = states;
						cv$temp$1$$var292 = $var292;
					}
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var292))?Math.log(cv$temp$0$var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							int traceTempVariable$var67$1_1 = cv$currentValue;
							for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
								if((0 == (i$var64 - 1))) {
									if(fixedFlag$sample71) {
										{
											int index$i$3 = i$var64;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var27 = 0; var27 < states; var27 += 1) {
													if((var27 == st[(i$var64 - 1)])) {
														{
															{
																double[] cv$temp$2$var68;
																{
																	double[] var68 = m[traceTempVariable$var67$1_1];
																	cv$temp$2$var68 = var68;
																}
																int cv$temp$3$$var303;
																{
																	int $var303 = states;
																	cv$temp$3$$var303 = $var303;
																}
																if(((Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var303))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var303))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var303))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var303))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var303))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							int traceTempVariable$var83$6_1 = cv$currentValue;
							for(int j = 0; j < samples; j += 1) {
								if((0 == j)) {
									{
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var43 = 0; var43 < states; var43 += 1) {
												if((var43 == st[j])) {
													{
														{
															double cv$temp$4$var84;
															{
																double var84 = bias[traceTempVariable$var83$6_1];
																cv$temp$4$var84 = var84;
															}
															if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var84:(1.0 - cv$temp$4$var84)))) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var84:(1.0 - cv$temp$4$var84)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var84:(1.0 - cv$temp$4$var84))));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var84:(1.0 - cv$temp$4$var84)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$4$var84:(1.0 - cv$temp$4$var84)))));
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
						int traceTempVariable$var67$10_1 = cv$currentValue;
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if((0 == (i$var64 - 1))) {
								if(!fixedFlag$sample71) {
									{
										int index$i$12 = i$var64;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var69;
										for(int cv$i = 0; cv$i < states; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var27 = 0; var27 < states; var27 += 1) {
											if((var27 == st[(i$var64 - 1)])) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double[] cv$temp$5$var68;
													{
														double[] var68 = m[traceTempVariable$var67$10_1];
														cv$temp$5$var68 = var68;
													}
													int cv$temp$6$$var323;
													{
														int $var323 = states;
														cv$temp$6$$var323 = $var323;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$5$var68, cv$temp$6$$var323);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample71[((i$var64 - 1) / 1)];
										double cv$overlap = 0.0;
										for(int cv$i = 0; cv$i < states; cv$i += 1) {
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			double[] cv$localProbability = distribution$sample53;
			double cv$logSum = 0.0;
			{
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
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
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
	}

	private final void sample71(int i$var64) {
		if(true) {
			int cv$numNumStates = 0;
			int index$i$1 = i$var64;
			if(fixedFlag$sample53) {
				if((0 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)]))
							cv$numNumStates = Math.max(cv$numNumStates, states);
					}
				}
			} else {
				if(true) {
					for(int index$sample53$3 = 0; index$sample53$3 < states; index$sample53$3 += 1) {
						int distributionTempVariable$var52$5 = index$sample53$3;
						double cv$probabilitySample53Value4 = (1.0 * distribution$sample53[index$sample53$3]);
						if((0 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)]))
									cv$numNumStates = Math.max(cv$numNumStates, states);
							}
						}
					}
				}
			}
			if((index$i$1 == (i$var64 - 1))) {
				for(int var27 = 0; var27 < states; var27 += 1) {
					if((var27 == st[(i$var64 - 1)]))
						cv$numNumStates = Math.max(cv$numNumStates, states);
				}
			}
			if(fixedFlag$sample71) {
				for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
					if((index$i$10_1 == (i$var64 - 1))) {
						for(int var27 = 0; var27 < states; var27 += 1) {
							if((var27 == st[(i$var64 - 1)]))
								cv$numNumStates = Math.max(cv$numNumStates, states);
						}
					}
				}
			} else {
				for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
					if(!(index$i$11 == index$i$1)) {
						for(int index$sample71$12 = 0; index$sample71$12 < states; index$sample71$12 += 1) {
							int distributionTempVariable$var70$14 = index$sample71$12;
							double cv$probabilitySample71Value13 = (1.0 * distribution$sample71[((index$i$11 - 1) / 1)][index$sample71$12]);
							if((index$i$11 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)]))
										cv$numNumStates = Math.max(cv$numNumStates, states);
								}
							}
						}
					}
				}
			}
			double[] cv$stateProbabilityLocal = cv$var70$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
				int index$i$19 = i$var64;
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				if(fixedFlag$sample53) {
					if((0 == (i$var64 - 1))) {
						for(int var27 = 0; var27 < states; var27 += 1) {
							if((var27 == st[(i$var64 - 1)])) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$0$var68;
								{
									double[] var68 = m[st[(i$var64 - 1)]];
									cv$temp$0$var68 = var68;
								}
								int cv$temp$1$$var382;
								{
									int $var382 = states;
									cv$temp$1$$var382 = $var382;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var382))?Math.log(cv$temp$0$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var67$35_1 = cv$currentValue;
									}
								}
								{
									{
										int traceTempVariable$var83$39_1 = cv$currentValue;
										for(int j = 0; j < samples; j += 1) {
											if((i$var64 == j)) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														for(int var43 = 0; var43 < states; var43 += 1) {
															if((var43 == st[j])) {
																{
																	{
																		double cv$temp$8$var84;
																		{
																			double var84 = bias[traceTempVariable$var83$39_1];
																			cv$temp$8$var84 = var84;
																		}
																		if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var84:(1.0 - cv$temp$8$var84)))) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var84:(1.0 - cv$temp$8$var84)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var84:(1.0 - cv$temp$8$var84))));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var84:(1.0 - cv$temp$8$var84)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$8$var84:(1.0 - cv$temp$8$var84)))));
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
				} else {
					if(true) {
						for(int index$sample53$21 = 0; index$sample53$21 < states; index$sample53$21 += 1) {
							int distributionTempVariable$var52$23 = index$sample53$21;
							double cv$probabilitySample53Value22 = (1.0 * distribution$sample53[index$sample53$21]);
							if((0 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample53Value22);
										double[] cv$temp$2$var68;
										{
											double[] var68 = m[st[(i$var64 - 1)]];
											cv$temp$2$var68 = var68;
										}
										int cv$temp$3$$var383;
										{
											int $var383 = states;
											cv$temp$3$$var383 = $var383;
										}
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample53Value22) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var383))?Math.log(cv$temp$2$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												int traceTempVariable$var67$36_1 = cv$currentValue;
											}
										}
										{
											{
												int traceTempVariable$var83$40_1 = cv$currentValue;
												for(int j = 0; j < samples; j += 1) {
													if((i$var64 == j)) {
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var43 = 0; var43 < states; var43 += 1) {
																	if((var43 == st[j])) {
																		{
																			{
																				double cv$temp$9$var84;
																				{
																					double var84 = bias[traceTempVariable$var83$40_1];
																					cv$temp$9$var84 = var84;
																				}
																				if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$9$var84:(1.0 - cv$temp$9$var84)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$9$var84:(1.0 - cv$temp$9$var84)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$9$var84:(1.0 - cv$temp$9$var84))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$9$var84:(1.0 - cv$temp$9$var84)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$9$var84:(1.0 - cv$temp$9$var84)))));
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
				int traceTempVariable$var67$27_1 = cv$currentValue;
				if((index$i$19 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$4$var68;
							{
								double[] var68 = m[traceTempVariable$var67$27_1];
								cv$temp$4$var68 = var68;
							}
							int cv$temp$5$$var384;
							{
								int $var384 = states;
								cv$temp$5$$var384 = $var384;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var384))?Math.log(cv$temp$4$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var67$37_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$var83$41_1 = cv$currentValue;
									for(int j = 0; j < samples; j += 1) {
										if((i$var64 == j)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var43 = 0; var43 < states; var43 += 1) {
														if((var43 == st[j])) {
															{
																{
																	double cv$temp$10$var84;
																	{
																		double var84 = bias[traceTempVariable$var83$41_1];
																		cv$temp$10$var84 = var84;
																	}
																	if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$10$var84:(1.0 - cv$temp$10$var84)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$10$var84:(1.0 - cv$temp$10$var84)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$10$var84:(1.0 - cv$temp$10$var84))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$10$var84:(1.0 - cv$temp$10$var84)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$10$var84:(1.0 - cv$temp$10$var84)))));
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
				for(int index$i$28 = 1; index$i$28 < samples; index$i$28 += 1) {
					if(!(index$i$28 == index$i$19)) {
						for(int index$sample71$29 = 0; index$sample71$29 < states; index$sample71$29 += 1) {
							int distributionTempVariable$var70$31 = index$sample71$29;
							double cv$probabilitySample71Value30 = (1.0 * distribution$sample71[((index$i$28 - 1) / 1)][index$sample71$29]);
							int traceTempVariable$var67$32_1 = cv$currentValue;
							if((index$i$28 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value30);
										double[] cv$temp$6$var68;
										{
											double[] var68 = m[traceTempVariable$var67$32_1];
											cv$temp$6$var68 = var68;
										}
										int cv$temp$7$$var385;
										{
											int $var385 = states;
											cv$temp$7$$var385 = $var385;
										}
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value30) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$7$$var385))?Math.log(cv$temp$6$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
										{
											{
												int traceTempVariable$var67$38_1 = distributionTempVariable$var70$31;
											}
										}
										{
											{
												int traceTempVariable$var83$42_1 = distributionTempVariable$var70$31;
												for(int j = 0; j < samples; j += 1) {
													if((i$var64 == j)) {
														{
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																for(int var43 = 0; var43 < states; var43 += 1) {
																	if((var43 == st[j])) {
																		{
																			{
																				double cv$temp$11$var84;
																				{
																					double var84 = bias[traceTempVariable$var83$42_1];
																					cv$temp$11$var84 = var84;
																				}
																				if(((Math.log(1.0) + Math.log((flips[j]?cv$temp$11$var84:(1.0 - cv$temp$11$var84)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + Math.log((flips[j]?cv$temp$11$var84:(1.0 - cv$temp$11$var84)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + Math.log((flips[j]?cv$temp$11$var84:(1.0 - cv$temp$11$var84))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + Math.log((flips[j]?cv$temp$11$var84:(1.0 - cv$temp$11$var84)))))) + 1)) + (Math.log(1.0) + Math.log((flips[j]?cv$temp$11$var84:(1.0 - cv$temp$11$var84)))));
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
				{
					{
						int traceTempVariable$var67$55_1 = cv$currentValue;
						for(int index$i$55_2 = 1; index$i$55_2 < samples; index$i$55_2 += 1) {
							if((i$var64 == (index$i$55_2 - 1))) {
								{
									int index$i$57 = index$i$55_2;
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var69;
									for(int cv$i = 0; cv$i < states; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									double cv$reachedDistributionProbability = 0.0;
									for(int var27 = 0; var27 < states; var27 += 1) {
										if((var27 == st[(index$i$55_2 - 1)])) {
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												if(fixedFlag$sample53) {
													if((0 == (i$var64 - 1))) {
														for(int index$var27$64_1 = 0; index$var27$64_1 < states; index$var27$64_1 += 1) {
															if((index$var27$64_1 == st[(i$var64 - 1)]))
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
													}
												} else {
													if(true) {
														for(int index$sample53$60 = 0; index$sample53$60 < states; index$sample53$60 += 1) {
															int distributionTempVariable$var52$62 = index$sample53$60;
															double cv$probabilitySample53Value61 = (1.0 * distribution$sample53[index$sample53$60]);
															if((0 == (i$var64 - 1))) {
																for(int index$var27$65_1 = 0; index$var27$65_1 < states; index$var27$65_1 += 1) {
																	if((index$var27$65_1 == st[(i$var64 - 1)]))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample53Value61);
																}
															}
														}
													}
												}
												int traceTempVariable$var67$66_1 = cv$currentValue;
												if((index$i$19 == (i$var64 - 1))) {
													for(int index$var27$72_1 = 0; index$var27$72_1 < states; index$var27$72_1 += 1) {
														if((index$var27$72_1 == st[(i$var64 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
												for(int index$i$67 = 1; index$i$67 < samples; index$i$67 += 1) {
													if((!(index$i$67 == index$i$19) && !(index$i$67 == index$i$57))) {
														for(int index$sample71$68 = 0; index$sample71$68 < states; index$sample71$68 += 1) {
															int distributionTempVariable$var70$70 = index$sample71$68;
															double cv$probabilitySample71Value69 = (1.0 * distribution$sample71[((index$i$67 - 1) / 1)][index$sample71$68]);
															int traceTempVariable$var67$71_1 = cv$currentValue;
															if((index$i$67 == (i$var64 - 1))) {
																for(int index$var27$73_1 = 0; index$var27$73_1 < states; index$var27$73_1 += 1) {
																	if((index$var27$73_1 == st[(i$var64 - 1)]))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample71Value69);
																}
															}
														}
													}
												}
												double[] cv$temp$12$var68;
												{
													double[] var68 = m[traceTempVariable$var67$55_1];
													cv$temp$12$var68 = var68;
												}
												int cv$temp$13$$var434;
												{
													int $var434 = states;
													cv$temp$13$$var434 = $var434;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$12$var68, cv$temp$13$$var434);
											}
										}
									}
									double[] cv$sampleDistribution = distribution$sample71[((index$i$55_2 - 1) / 1)];
									double cv$overlap = 0.0;
									for(int cv$i = 0; cv$i < states; cv$i += 1) {
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
			double[] cv$localProbability = distribution$sample71[((i$var64 - 1) / 1)];
			double cv$logSum = 0.0;
			{
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
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
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
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var28$countGlobal = new double[5];
		}
		{
			int cv$var29$max = 5;
			cv$distributionAccumulator$var69 = new double[cv$var29$max];
		}
		{
			int cv$var29$max = 5;
			cv$var52$stateProbabilityGlobal = new double[cv$var29$max];
		}
		{
			int cv$var29$max = 5;
			cv$var70$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[5];
		}
		if(!fixedFlag$sample28) {
			{
				m = new double[5][];
				for(int var27 = 0; var27 < 5; var27 += 1)
					m[var27] = new double[5];
			}
		}
		if(!fixedFlag$sample45) {
			{
				bias = new double[5];
			}
		}
		if((!fixedFlag$sample53 || !fixedFlag$sample71)) {
			{
				st = new int[length$flipsMeasured];
			}
		}
		{
			flips = new boolean[length$flipsMeasured];
		}
		{
			distribution$sample53 = new double[5];
		}
		{
			distribution$sample71 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var64 = 1; i$var64 < length$flipsMeasured; i$var64 += 1)
				distribution$sample71[((i$var64 - 1) / 1)] = new double[5];
		}
		{
			logProbability$var69 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample71 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var85 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample87 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		double[] cv$distribution$sample53 = distribution$sample53;
		double[] var50 = m[0];
		for(int index$var51 = 0; index$var51 < states; index$var51 += 1) {
			double cv$value = (((0.0 <= index$var51) && (index$var51 < states))?var50[index$var51]:0.0);
			if(!fixedFlag$sample53)
				cv$distribution$sample53[index$var51] = cv$value;
		}
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			double[] cv$distribution$sample71 = distribution$sample71[((i$var64 - 1) / 1)];
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					cv$distribution$sample71[index$var69] = 0.0;
			}
			if(fixedFlag$sample53) {
				if((0 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)])) {
							{
								if(!fixedFlag$sample71) {
									double[] var68 = m[st[(i$var64 - 1)]];
									for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
										cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample53$2 = 0; index$sample53$2 < states; index$sample53$2 += 1) {
						int distributionTempVariable$var52$4 = index$sample53$2;
						double cv$probabilitySample53Value3 = (1.0 * distribution$sample53[index$sample53$2]);
						if((0 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									{
										if(!fixedFlag$sample71) {
											double[] var68 = m[st[(i$var64 - 1)]];
											for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
												cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample53Value3 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample71) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var64 - 1))) {
						for(int var27 = 0; var27 < states; var27 += 1) {
							if((var27 == st[(i$var64 - 1)])) {
								{
									if(!fixedFlag$sample71) {
										double[] var68 = m[st[(i$var64 - 1)]];
										for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
											cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (1.0 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample71$10 = 0; index$sample71$10 < states; index$sample71$10 += 1) {
							int distributionTempVariable$var70$12 = index$sample71$10;
							double cv$probabilitySample71Value11 = (1.0 * distribution$sample71[((index$i$9 - 1) / 1)][index$sample71$10]);
							if((index$i$9 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										{
											if(!fixedFlag$sample71) {
												double[] var68 = m[st[(i$var64 - 1)]];
												for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
													cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value11 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var69$sum = 0.0;
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
			}
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if(!fixedFlag$sample28)
					sample28(var27);
			}
			for(int var43 = 0; var43 < states; var43 += 1) {
				if(!fixedFlag$sample45)
					sample45(var43);
			}
			if(!fixedFlag$sample53)
				sample53();
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
				if(!fixedFlag$sample71)
					sample71(i$var64);
			}
		} else {
			for(int i$var64 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var64 >= ((1 - 1) + 1); i$var64 -= 1) {
				if(!fixedFlag$sample71)
					sample71(i$var64);
			}
			if(!fixedFlag$sample53)
				sample53();
			for(int var43 = (states - ((((states - 1) - 0) % 1) + 1)); var43 >= ((0 - 1) + 1); var43 -= 1) {
				if(!fixedFlag$sample45)
					sample45(var43);
			}
			for(int var27 = (states - ((((states - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!fixedFlag$sample28)
					sample28(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 5;
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			v[i$var13] = 0.1;
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var16 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = 0.0;
		logProbability$var51 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = 0.0;
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
			logProbability$var69[((i$var64 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample71) {
			for(int i$var64 = 1; i$var64 < samples; i$var64 += 1)
				logProbability$sample71[((i$var64 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var85[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample87[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityDistribution$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
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
		     + "model HMMTestPart7(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sampleDistribution();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}