package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart8$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart8$CoreInterface {
	private double[] bias;
	private double[] cv$distributionAccumulator$var69;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
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
	private boolean setFlag$bias = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private boolean setFlag$st = false;
	private int[] st;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMMTestPart8$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		setFlag$bias = true;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample87 = false;
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
		setFlag$m = true;
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
		setFlag$st = true;
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
							for(int index$i$6_1 = 1; index$i$6_1 < samples; index$i$6_1 += 1) {
								if((index$i$6_1 == (i$var64 - 1))) {
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
							for(int index$i$7 = 1; index$i$7 < samples; index$i$7 += 1) {
								if(!(index$i$7 == index$i$1)) {
									for(int index$sample71$8 = 0; index$sample71$8 < states; index$sample71$8 += 1) {
										int distributionTempVariable$var70$10 = index$sample71$8;
										double cv$probabilitySample71Value9 = (1.0 * distribution$sample71[((index$i$7 - 1) / 1)][index$sample71$8]);
										if((index$i$7 == (i$var64 - 1))) {
											for(int var27 = 0; var27 < states; var27 += 1) {
												if((var27 == st[(i$var64 - 1)])) {
													{
														double[] var68 = m[st[(i$var64 - 1)]];
														double cv$weightedProbability = (Math.log(cv$probabilitySample71Value9) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value9);
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
					if((0 == j)) {
						for(int var43 = 0; var43 < states; var43 += 1) {
							if((var43 == st[j])) {
								{
									double var84 = bias[st[j]];
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
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
						for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
							if((i$var64 == j)) {
								for(int var43 = 0; var43 < states; var43 += 1) {
									if((var43 == st[j])) {
										{
											double var84 = bias[st[j]];
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
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
								for(int index$sample71$6 = 0; index$sample71$6 < states; index$sample71$6 += 1) {
									int distributionTempVariable$var70$8 = index$sample71$6;
									double cv$probabilitySample71Value7 = (1.0 * distribution$sample71[((i$var64 - 1) / 1)][index$sample71$6]);
									if((i$var64 == j)) {
										for(int var43 = 0; var43 < states; var43 += 1) {
											if((var43 == st[j])) {
												{
													double var84 = bias[st[j]];
													double cv$weightedProbability = (Math.log(cv$probabilitySample71Value7) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
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
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var84));
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

	private final void sample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] cv$targetLocal = m[var27];
		double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var27 == 0)) {
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
			{
				{
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if((0 == (i$var64 - 1))) {
							if((var27 == st[(i$var64 - 1)])) {
								if(fixedFlag$sample71) {
									{
										int index$i$17 = i$var64;
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
					for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
						if(fixedFlag$sample71) {
							for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
								if((index$i$8_1 == (i$var64 - 1))) {
									if((var27 == st[(i$var64 - 1)])) {
										if(fixedFlag$sample71) {
											{
												int index$i$19 = i$var64;
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
							for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
								if(true) {
									for(int index$sample71$10 = 0; index$sample71$10 < states; index$sample71$10 += 1) {
										int distributionTempVariable$var70$12 = index$sample71$10;
										double cv$probabilitySample71Value11 = (1.0 * distribution$sample71[((index$i$9 - 1) / 1)][index$sample71$10]);
										if((index$i$9 == (i$var64 - 1))) {
											if((var27 == st[(i$var64 - 1)])) {
												if(fixedFlag$sample71) {
													{
														int index$i$21 = i$var64;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var64]] = (cv$countLocal[st[i$var64]] + cv$probabilitySample71Value11);
																	}
																}
															}
														}
													}
												}
											}
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
					if((0 == (i$var64 - 1))) {
						if((var27 == st[(i$var64 - 1)])) {
							if(!fixedFlag$sample71) {
								{
									int index$i$38 = i$var64;
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
				for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
					if(fixedFlag$sample71) {
						for(int index$i$29_1 = 1; index$i$29_1 < samples; index$i$29_1 += 1) {
							if((index$i$29_1 == (i$var64 - 1))) {
								if((var27 == st[(i$var64 - 1)])) {
									if(!fixedFlag$sample71) {
										{
											int index$i$40 = i$var64;
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
						for(int index$i$30 = 1; index$i$30 < samples; index$i$30 += 1) {
							if(true) {
								for(int index$sample71$31 = 0; index$sample71$31 < states; index$sample71$31 += 1) {
									int distributionTempVariable$var70$33 = index$sample71$31;
									double cv$probabilitySample71Value32 = (1.0 * distribution$sample71[((index$i$30 - 1) / 1)][index$sample71$31]);
									if((index$i$30 == (i$var64 - 1))) {
										if((var27 == st[(i$var64 - 1)])) {
											if(!fixedFlag$sample71) {
												{
													int index$i$42 = i$var64;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample71Value32);
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

	private final void sample45(int var43, int threadID$cv$var43, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int j = 0; j < samples; j += 1) {
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
									for(int index$sample71$7 = 0; index$sample71$7 < states; index$sample71$7 += 1) {
										int distributionTempVariable$var70$9 = index$sample71$7;
										double cv$probabilitySample71Value8 = (1.0 * distribution$sample71[((i$var64 - 1) / 1)][index$sample71$7]);
										if((i$var64 == j)) {
											if((var43 == st[j])) {
												{
													{
														{
															{
																{
																	cv$count = (cv$count + cv$probabilitySample71Value8);
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample71Value8);
																}
															}
														}
													}
												}
											}
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
		bias[var43] = var44;
	}

	private final void sample53() {
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
			int var52 = cv$currentValue;
			st[0] = cv$currentValue;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var50;
				{
					double[] var50 = m[0];
					cv$temp$0$var50 = var50;
				}
				int cv$temp$1$$var694;
				{
					int $var694 = states;
					cv$temp$1$$var694 = $var694;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var694))?Math.log(cv$temp$0$var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
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
															int cv$temp$3$$var705;
															{
																int $var705 = states;
																cv$temp$3$$var705 = $var705;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var705))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var705))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var705))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var705))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var64]) && (st[i$var64] < cv$temp$3$$var705))?Math.log(cv$temp$2$var68[st[i$var64]]):Double.NEGATIVE_INFINITY)));
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
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var84)));
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
												int cv$temp$6$$var725;
												{
													int $var725 = states;
													cv$temp$6$$var725 = $var725;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$5$var68, cv$temp$6$$var725);
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
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		int var52 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates);
		st[0] = var52;
	}

	private final void sample71(int i$var64) {
		int cv$numNumStates = 0;
		int index$i$1 = i$var64;
		if((0 == (i$var64 - 1))) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if((var27 == st[(i$var64 - 1)]))
					cv$numNumStates = Math.max(cv$numNumStates, states);
			}
		}
		if((index$i$1 == (i$var64 - 1))) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if((var27 == st[(i$var64 - 1)]))
					cv$numNumStates = Math.max(cv$numNumStates, states);
			}
		}
		if(fixedFlag$sample71) {
			for(int index$i$5_1 = 1; index$i$5_1 < samples; index$i$5_1 += 1) {
				if((index$i$5_1 == (i$var64 - 1))) {
					for(int var27 = 0; var27 < states; var27 += 1) {
						if((var27 == st[(i$var64 - 1)]))
							cv$numNumStates = Math.max(cv$numNumStates, states);
					}
				}
			}
		} else {
			for(int index$i$6 = 1; index$i$6 < samples; index$i$6 += 1) {
				if(!(index$i$6 == index$i$1)) {
					for(int index$sample71$7 = 0; index$sample71$7 < states; index$sample71$7 += 1) {
						int distributionTempVariable$var70$9 = index$sample71$7;
						double cv$probabilitySample71Value8 = (1.0 * distribution$sample71[((index$i$6 - 1) / 1)][index$sample71$7]);
						if((index$i$6 == (i$var64 - 1))) {
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
			int index$i$14 = i$var64;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if((0 == (i$var64 - 1))) {
				for(int var27 = 0; var27 < states; var27 += 1) {
					if((var27 == st[(i$var64 - 1)])) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$0$var68;
						{
							double[] var68 = m[st[(i$var64 - 1)]];
							cv$temp$0$var68 = var68;
						}
						int cv$temp$1$$var772;
						{
							int $var772 = states;
							cv$temp$1$$var772 = $var772;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var772))?Math.log(cv$temp$0$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var67$25_1 = cv$currentValue;
							}
						}
						{
							{
								int traceTempVariable$var83$28_1 = cv$currentValue;
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
																double cv$temp$6$var84;
																{
																	double var84 = bias[traceTempVariable$var83$28_1];
																	cv$temp$6$var84 = var84;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var84)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var84));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var84)));
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
			int traceTempVariable$var67$17_1 = cv$currentValue;
			if((index$i$14 == (i$var64 - 1))) {
				for(int var27 = 0; var27 < states; var27 += 1) {
					if((var27 == st[(i$var64 - 1)])) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var68;
						{
							double[] var68 = m[traceTempVariable$var67$17_1];
							cv$temp$2$var68 = var68;
						}
						int cv$temp$3$$var773;
						{
							int $var773 = states;
							cv$temp$3$$var773 = $var773;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var773))?Math.log(cv$temp$2$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var67$26_1 = cv$currentValue;
							}
						}
						{
							{
								int traceTempVariable$var83$29_1 = cv$currentValue;
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
																double cv$temp$7$var84;
																{
																	double var84 = bias[traceTempVariable$var83$29_1];
																	cv$temp$7$var84 = var84;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var84)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var84));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var84)));
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
			for(int index$i$18 = 1; index$i$18 < samples; index$i$18 += 1) {
				if(!(index$i$18 == index$i$14)) {
					for(int index$sample71$19 = 0; index$sample71$19 < states; index$sample71$19 += 1) {
						int distributionTempVariable$var70$21 = index$sample71$19;
						double cv$probabilitySample71Value20 = (1.0 * distribution$sample71[((index$i$18 - 1) / 1)][index$sample71$19]);
						int traceTempVariable$var67$22_1 = cv$currentValue;
						if((index$i$18 == (i$var64 - 1))) {
							for(int var27 = 0; var27 < states; var27 += 1) {
								if((var27 == st[(i$var64 - 1)])) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value20);
									double[] cv$temp$4$var68;
									{
										double[] var68 = m[traceTempVariable$var67$22_1];
										cv$temp$4$var68 = var68;
									}
									int cv$temp$5$$var774;
									{
										int $var774 = states;
										cv$temp$5$$var774 = $var774;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample71Value20) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var774))?Math.log(cv$temp$4$var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var67$27_1 = distributionTempVariable$var70$21;
										}
									}
									{
										{
											int traceTempVariable$var83$30_1 = distributionTempVariable$var70$21;
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
																				double var84 = bias[traceTempVariable$var83$30_1];
																				cv$temp$8$var84 = var84;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var84)));
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
					int traceTempVariable$var67$40_1 = cv$currentValue;
					for(int index$i$40_2 = 1; index$i$40_2 < samples; index$i$40_2 += 1) {
						if((i$var64 == (index$i$40_2 - 1))) {
							{
								int index$i$42 = index$i$40_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var69;
								for(int cv$i = 0; cv$i < states; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(index$i$40_2 - 1)])) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if((0 == (i$var64 - 1))) {
												for(int index$var27$45_1 = 0; index$var27$45_1 < states; index$var27$45_1 += 1) {
													if((index$var27$45_1 == st[(i$var64 - 1)]))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											int traceTempVariable$var67$46_1 = cv$currentValue;
											if((index$i$14 == (i$var64 - 1))) {
												for(int index$var27$52_1 = 0; index$var27$52_1 < states; index$var27$52_1 += 1) {
													if((index$var27$52_1 == st[(i$var64 - 1)]))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$47 = 1; index$i$47 < samples; index$i$47 += 1) {
												if((!(index$i$47 == index$i$14) && !(index$i$47 == index$i$42))) {
													for(int index$sample71$48 = 0; index$sample71$48 < states; index$sample71$48 += 1) {
														int distributionTempVariable$var70$50 = index$sample71$48;
														double cv$probabilitySample71Value49 = (1.0 * distribution$sample71[((index$i$47 - 1) / 1)][index$sample71$48]);
														int traceTempVariable$var67$51_1 = cv$currentValue;
														if((index$i$47 == (i$var64 - 1))) {
															for(int index$var27$53_1 = 0; index$var27$53_1 < states; index$var27$53_1 += 1) {
																if((index$var27$53_1 == st[(i$var64 - 1)]))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample71Value49);
															}
														}
													}
												}
											}
											double[] cv$temp$9$var68;
											{
												double[] var68 = m[traceTempVariable$var67$40_1];
												cv$temp$9$var68 = var68;
											}
											int cv$temp$10$$var812;
											{
												int $var812 = states;
												cv$temp$10$$var812 = $var812;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$9$var68, cv$temp$10$$var812);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample71[((index$i$40_2 - 1) / 1)];
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

	@Override
	public final void allocateScratch() {
		{
			{
				int cv$threadCount = threadCount();
				cv$var28$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[5];
			}
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
		if(!setFlag$m) {
			{
				m = new double[5][];
				for(int var27 = 0; var27 < 5; var27 += 1)
					m[var27] = new double[5];
			}
		}
		if(!setFlag$bias) {
			{
				bias = new double[5];
			}
		}
		if(!setFlag$st) {
			{
				st = new int[length$flipsMeasured];
			}
		}
		{
			flips = new boolean[length$flipsMeasured];
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
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			double[] cv$distribution$sample71 = distribution$sample71[((i$var64 - 1) / 1)];
			for(int index$var69 = 0; index$var69 < states; index$var69 += 1) {
				if(!fixedFlag$sample71)
					cv$distribution$sample71[index$var69] = 0.0;
			}
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
			if(fixedFlag$sample71) {
				for(int index$i$3_1 = 1; index$i$3_1 < samples; index$i$3_1 += 1) {
					if((index$i$3_1 == (i$var64 - 1))) {
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
				for(int index$i$4 = 1; index$i$4 < samples; index$i$4 += 1) {
					if(true) {
						for(int index$sample71$5 = 0; index$sample71$5 < states; index$sample71$5 += 1) {
							int distributionTempVariable$var70$7 = index$sample71$5;
							double cv$probabilitySample71Value6 = (1.0 * distribution$sample71[((index$i$4 - 1) / 1)][index$sample71$5]);
							if((index$i$4 == (i$var64 - 1))) {
								for(int var27 = 0; var27 < states; var27 += 1) {
									if((var27 == st[(i$var64 - 1)])) {
										{
											if(!fixedFlag$sample71) {
												double[] var68 = m[st[(i$var64 - 1)]];
												for(int index$var69 = 0; index$var69 < states; index$var69 += 1)
													cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value6 * (((0.0 <= index$var69) && (index$var69 < states))?var68[index$var69]:0.0)));
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
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
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
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
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
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
							if(!fixedFlag$sample45)
								sample45(var43, threadID$var43, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								sample28(var27, threadID$var27, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 5;
		parallelFor(RNG$, 0, 5, 1,
			(int forStart$i$var13, int forEnd$i$var13, int threadID$i$var13, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var13 = forStart$i$var13; i$var13 < forEnd$i$var13; i$var13 += 1)
						v[i$var13] = 0.1;
			}
		);
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
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
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
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var28);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!fixedFlag$sample45)
							bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0], states);
		for(int i$var64 = 1; i$var64 < samples; i$var64 += 1) {
			if(!fixedFlag$sample71)
				st[i$var64] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var64 - 1)]], states);
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
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
		     + "model HMMTestPart8(boolean[] flipsMeasured) {\n"
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
		     + "        st[0] = categorical(m[0]).sample();\n"
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