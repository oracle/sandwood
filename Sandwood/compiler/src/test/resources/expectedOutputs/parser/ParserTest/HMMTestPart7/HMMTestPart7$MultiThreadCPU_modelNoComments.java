package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMMTestPart7$CoreInterface {
	private double[] bias;
	private double[] cv$distributionAccumulator$var71;
	private double[][] cv$var30$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[] cv$var72$stateProbabilityGlobal;
	private double[] distribution$sample57;
	private double[][] distribution$sample75;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample48 = false;
	private boolean fixedFlag$sample57 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedFlag$sample91 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample48 = false;
	private boolean fixedProbFlag$sample57 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean fixedProbFlag$sample91 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample75;
	private double[] logProbability$sample91;
	private double logProbability$st;
	private double logProbability$var18;
	private double logProbability$var30;
	private double logProbability$var34;
	private double logProbability$var46;
	private double logProbability$var53;
	private double logProbability$var54;
	private double[] logProbability$var71;
	private double[] logProbability$var87;
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

	public HMMTestPart7$MultiThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample48 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		fixedFlag$sample31 = cv$value;
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		fixedProbFlag$sample57 = (fixedFlag$sample31 && fixedProbFlag$sample57);
		fixedProbFlag$sample75 = (fixedFlag$sample31 && fixedProbFlag$sample75);
	}

	@Override
	public final boolean get$fixedFlag$sample48() {
		return fixedFlag$sample48;
	}

	@Override
	public final void set$fixedFlag$sample48(boolean cv$value) {
		fixedFlag$sample48 = cv$value;
		fixedProbFlag$sample48 = (fixedFlag$sample48 && fixedProbFlag$sample48);
		fixedProbFlag$sample91 = (fixedFlag$sample48 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample57() {
		return fixedFlag$sample57;
	}

	@Override
	public final void set$fixedFlag$sample57(boolean cv$value) {
		fixedFlag$sample57 = cv$value;
		fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedProbFlag$sample57);
		fixedProbFlag$sample75 = (fixedFlag$sample57 && fixedProbFlag$sample75);
		fixedProbFlag$sample91 = (fixedFlag$sample57 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		fixedFlag$sample75 = cv$value;
		fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedProbFlag$sample75);
		fixedProbFlag$sample91 = (fixedFlag$sample75 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean get$fixedFlag$sample91() {
		return fixedFlag$sample91;
	}

	@Override
	public final void set$fixedFlag$sample91(boolean cv$value) {
		fixedFlag$sample91 = cv$value;
		fixedProbFlag$sample91 = (fixedFlag$sample91 && fixedProbFlag$sample91);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample91 = false;
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
		fixedProbFlag$sample31 = false;
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample75 = false;
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
		fixedProbFlag$sample57 = false;
		fixedProbFlag$sample75 = false;
		fixedProbFlag$sample91 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample57() {
		if(!fixedProbFlag$sample57) {
			if(fixedFlag$sample57) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[0];
					{
						{
							double[] var52 = m[0];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var53 = cv$sampleAccumulator;
				logProbability$var54 = cv$sampleProbability;
				if(fixedFlag$sample57)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample57)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var53 = cv$rvAccumulator;
			if(fixedFlag$sample57)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!fixedProbFlag$sample75) {
			if(fixedFlag$sample75) {
				double cv$accumulator = 0.0;
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var66;
					{
						int cv$sampleValue = st[i$var66];
						if(fixedFlag$sample57) {
							if((0 == (i$var66 - 1))) {
								for(int var29 = 0; var29 < states; var29 += 1) {
									if((var29 == st[(i$var66 - 1)])) {
										{
											double[] var70 = m[st[(i$var66 - 1)]];
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$sample57$4 = 0; index$sample57$4 < states; index$sample57$4 += 1) {
									int distributionTempVariable$var54$6 = index$sample57$4;
									double cv$probabilitySample57Value5 = (1.0 * distribution$sample57[index$sample57$4]);
									int traceTempVariable$var69$7_1 = distributionTempVariable$var54$6;
									if((0 == (i$var66 - 1))) {
										for(int var29 = 0; var29 < states; var29 += 1) {
											if((var29 == traceTempVariable$var69$7_1)) {
												{
													double[] var70 = m[traceTempVariable$var69$7_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample57Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value5);
												}
											}
										}
									}
								}
							}
						}
						if((index$i$1 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == st[(i$var66 - 1)])) {
									{
										double[] var70 = m[st[(i$var66 - 1)]];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample75) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var66 - 1))) {
									for(int var29 = 0; var29 < states; var29 += 1) {
										if((var29 == st[(i$var66 - 1)])) {
											{
												double[] var70 = m[st[(i$var66 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample75$13 = 0; index$sample75$13 < states; index$sample75$13 += 1) {
										int distributionTempVariable$var72$15 = index$sample75$13;
										double cv$probabilitySample75Value14 = (1.0 * distribution$sample75[((index$i$12 - 1) / 1)][index$sample75$13]);
										int traceTempVariable$var69$16_1 = distributionTempVariable$var72$15;
										if((index$i$12 == (i$var66 - 1))) {
											for(int var29 = 0; var29 < states; var29 += 1) {
												if((var29 == traceTempVariable$var69$16_1)) {
													{
														double[] var70 = m[traceTempVariable$var69$16_1];
														double cv$weightedProbability = (Math.log(cv$probabilitySample75Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value14);
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
					logProbability$var71[((i$var66 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample75[((i$var66 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample75)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample75)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((i$var66 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[((i$var66 - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample75)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample91() {
		if(!fixedProbFlag$sample91) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					if(fixedFlag$sample57) {
						if((0 == j)) {
							for(int var45 = 0; var45 < states; var45 += 1) {
								if((var45 == st[j])) {
									{
										double var86 = bias[st[j]];
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
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
							for(int index$sample57$3 = 0; index$sample57$3 < states; index$sample57$3 += 1) {
								int distributionTempVariable$var54$5 = index$sample57$3;
								double cv$probabilitySample57Value4 = (1.0 * distribution$sample57[index$sample57$3]);
								int traceTempVariable$var85$6_1 = distributionTempVariable$var54$5;
								if((0 == j)) {
									for(int var45 = 0; var45 < states; var45 += 1) {
										if((var45 == traceTempVariable$var85$6_1)) {
											{
												double var86 = bias[traceTempVariable$var85$6_1];
												double cv$weightedProbability = (Math.log(cv$probabilitySample57Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
												if((cv$weightedProbability < cv$distributionAccumulator))
													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
												else {
													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
														cv$distributionAccumulator = cv$weightedProbability;
													else
														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
												}
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample57Value4);
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample75) {
						for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
							if((i$var66 == j)) {
								for(int var45 = 0; var45 < states; var45 += 1) {
									if((var45 == st[j])) {
										{
											double var86 = bias[st[j]];
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
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
						for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
							if(true) {
								for(int index$sample75$11 = 0; index$sample75$11 < states; index$sample75$11 += 1) {
									int distributionTempVariable$var72$13 = index$sample75$11;
									double cv$probabilitySample75Value12 = (1.0 * distribution$sample75[((i$var66 - 1) / 1)][index$sample75$11]);
									int traceTempVariable$var85$14_1 = distributionTempVariable$var72$13;
									if((i$var66 == j)) {
										for(int var45 = 0; var45 < states; var45 += 1) {
											if((var45 == traceTempVariable$var85$14_1)) {
												{
													double var86 = bias[traceTempVariable$var85$14_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample75Value12) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample75Value12);
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
				logProbability$var87[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample91[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample91[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var87[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample31() {
		if(!fixedProbFlag$sample31) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var29 = 0; var29 < states; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var29];
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
			logProbability$var18 = cv$sampleAccumulator;
			logProbability$var30 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var18 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample48() {
		if(!fixedProbFlag$sample48) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < states; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var45];
					{
						{
							double var32 = 1.0;
							double var33 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var32, var33));
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
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample48 = fixedFlag$sample48;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample48)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!fixedProbFlag$sample57) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double[] var52 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var52.length))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var53 = cv$sampleAccumulator;
			logProbability$var54 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample57 = (fixedFlag$sample57 && fixedFlag$sample31);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var54;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var53 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample57)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var66;
				{
					int cv$sampleValue = st[i$var66];
					{
						{
							double[] var70 = m[st[(i$var66 - 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var70.length))?Math.log(var70[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var71[((i$var66 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample75[((i$var66 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = ((fixedFlag$sample75 && fixedFlag$sample31) && fixedFlag$sample57);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((i$var66 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var71[((i$var66 - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample75)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample91() {
		if(!fixedProbFlag$sample91) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var86 = bias[st[j]];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var86));
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
				logProbability$var87[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample91[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample91 = (((fixedFlag$sample91 && fixedFlag$sample48) && fixedFlag$sample57) && fixedFlag$sample75);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample91[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var87[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample31(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] cv$targetLocal = m[var29];
		double[] cv$countLocal = cv$var30$countGlobal[threadID$cv$var29];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var29 == 0)) {
						if(fixedFlag$sample57) {
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
					for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
						if(fixedFlag$sample57) {
							if((0 == (i$var66 - 1))) {
								if((var29 == st[(i$var66 - 1)])) {
									if(fixedFlag$sample75) {
										{
											int index$i$22 = i$var66;
											{
												{
													{
														{
															cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + 1.0);
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
								for(int index$sample57$6 = 0; index$sample57$6 < states; index$sample57$6 += 1) {
									int distributionTempVariable$var54$8 = index$sample57$6;
									double cv$probabilitySample57Value7 = (1.0 * distribution$sample57[index$sample57$6]);
									int traceTempVariable$var69$9_1 = distributionTempVariable$var54$8;
									if((0 == (i$var66 - 1))) {
										if((var29 == traceTempVariable$var69$9_1)) {
											if(fixedFlag$sample75) {
												{
													int index$i$24 = i$var66;
													{
														{
															{
																{
																	cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + cv$probabilitySample57Value7);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
						if(fixedFlag$sample75) {
							for(int index$i$13_1 = 1; index$i$13_1 < samples; index$i$13_1 += 1) {
								if((index$i$13_1 == (i$var66 - 1))) {
									if((var29 == st[(i$var66 - 1)])) {
										if(fixedFlag$sample75) {
											{
												int index$i$26 = i$var66;
												{
													{
														{
															{
																cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + 1.0);
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
									for(int index$sample75$15 = 0; index$sample75$15 < states; index$sample75$15 += 1) {
										int distributionTempVariable$var72$17 = index$sample75$15;
										double cv$probabilitySample75Value16 = (1.0 * distribution$sample75[((index$i$14 - 1) / 1)][index$sample75$15]);
										int traceTempVariable$var69$18_1 = distributionTempVariable$var72$17;
										if((index$i$14 == (i$var66 - 1))) {
											if((var29 == traceTempVariable$var69$18_1)) {
												if(fixedFlag$sample75) {
													{
														int index$i$28 = i$var66;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var66]] = (cv$countLocal[st[i$var66]] + cv$probabilitySample75Value16);
																	}
																}
															}
														}
													}
												}
											}
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
				if((var29 == 0)) {
					if(!fixedFlag$sample57) {
						{
							{
								{
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample57[cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		{
			{
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					if(fixedFlag$sample57) {
						if((0 == (i$var66 - 1))) {
							if((var29 == st[(i$var66 - 1)])) {
								if(!fixedFlag$sample75) {
									{
										int index$i$53 = i$var66;
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample57$37 = 0; index$sample57$37 < states; index$sample57$37 += 1) {
								int distributionTempVariable$var54$39 = index$sample57$37;
								double cv$probabilitySample57Value38 = (1.0 * distribution$sample57[index$sample57$37]);
								int traceTempVariable$var69$40_1 = distributionTempVariable$var54$39;
								if((0 == (i$var66 - 1))) {
									if((var29 == traceTempVariable$var69$40_1)) {
										if(!fixedFlag$sample75) {
											{
												int index$i$55 = i$var66;
												{
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample57Value38);
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
					if(fixedFlag$sample75) {
						for(int index$i$44_1 = 1; index$i$44_1 < samples; index$i$44_1 += 1) {
							if((index$i$44_1 == (i$var66 - 1))) {
								if((var29 == st[(i$var66 - 1)])) {
									if(!fixedFlag$sample75) {
										{
											int index$i$57 = i$var66;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample75$46 = 0; index$sample75$46 < states; index$sample75$46 += 1) {
									int distributionTempVariable$var72$48 = index$sample75$46;
									double cv$probabilitySample75Value47 = (1.0 * distribution$sample75[((index$i$45 - 1) / 1)][index$sample75$46]);
									int traceTempVariable$var69$49_1 = distributionTempVariable$var72$48;
									if((index$i$45 == (i$var66 - 1))) {
										if((var29 == traceTempVariable$var69$49_1)) {
											if(!fixedFlag$sample75) {
												{
													int index$i$59 = i$var66;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample75Value47);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample75[((i$var66 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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

	private final void sample48(int var45, int threadID$cv$var45, Rng RNG$) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int j = 0; j < samples; j += 1) {
						if(fixedFlag$sample57) {
							if((0 == j)) {
								if((var45 == st[j])) {
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
								for(int index$sample57$3 = 0; index$sample57$3 < states; index$sample57$3 += 1) {
									int distributionTempVariable$var54$5 = index$sample57$3;
									double cv$probabilitySample57Value4 = (1.0 * distribution$sample57[index$sample57$3]);
									int traceTempVariable$var85$6_1 = distributionTempVariable$var54$5;
									if((0 == j)) {
										if((var45 == traceTempVariable$var85$6_1)) {
											{
												{
													{
														{
															{
																cv$count = (cv$count + cv$probabilitySample57Value4);
																if(flips[j])
																	cv$sum = (cv$sum + cv$probabilitySample57Value4);
															}
														}
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
						if(fixedFlag$sample75) {
							for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
								if((i$var66 == j)) {
									if((var45 == st[j])) {
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
							for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
								if(true) {
									for(int index$sample75$12 = 0; index$sample75$12 < states; index$sample75$12 += 1) {
										int distributionTempVariable$var72$14 = index$sample75$12;
										double cv$probabilitySample75Value13 = (1.0 * distribution$sample75[((i$var66 - 1) / 1)][index$sample75$12]);
										int traceTempVariable$var85$15_1 = distributionTempVariable$var72$14;
										if((i$var66 == j)) {
											if((var45 == traceTempVariable$var85$15_1)) {
												{
													{
														{
															{
																{
																	cv$count = (cv$count + cv$probabilitySample75Value13);
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample75Value13);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var45] = var46;
	}

	private final void sample57() {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, states);
		}
		double[] cv$stateProbabilityLocal = cv$var54$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var52;
				{
					double[] var52 = m[0];
					cv$temp$0$var52 = var52;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var52.length))?Math.log(cv$temp$0$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var69$1_1 = cv$currentValue;
						for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
							if((0 == (i$var66 - 1))) {
								if(fixedFlag$sample75) {
									{
										int index$i$3 = i$var66;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var29 = 0; var29 < states; var29 += 1) {
												if((var29 == traceTempVariable$var69$1_1)) {
													{
														{
															double[] cv$temp$1$var70;
															{
																double[] var70 = m[traceTempVariable$var69$1_1];
																cv$temp$1$var70 = var70;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var66]) && (st[i$var66] < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[st[i$var66]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var85$6_1 = cv$currentValue;
						for(int j = 0; j < samples; j += 1) {
							if((0 == j)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										for(int var45 = 0; var45 < states; var45 += 1) {
											if((var45 == traceTempVariable$var85$6_1)) {
												{
													{
														double cv$temp$2$var86;
														{
															double var86 = bias[traceTempVariable$var85$6_1];
															cv$temp$2$var86 = var86;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var86)));
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
					int traceTempVariable$var69$10_1 = cv$currentValue;
					for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
						if((0 == (i$var66 - 1))) {
							if(!fixedFlag$sample75) {
								{
									int index$i$12 = i$var66;
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var71;
									for(int cv$i = 0; cv$i < states; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									double cv$reachedDistributionProbability = 0.0;
									for(int var29 = 0; var29 < states; var29 += 1) {
										if((var29 == traceTempVariable$var69$10_1)) {
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$3$var70;
												{
													double[] var70 = m[traceTempVariable$var69$10_1];
													cv$temp$3$var70 = var70;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var70);
											}
										}
									}
									double[] cv$sampleDistribution = distribution$sample75[((i$var66 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample57;
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

	private final void sample75(int i$var66) {
		int cv$noStates = 0;
		int index$i$1 = i$var66;
		if(fixedFlag$sample57) {
			if((0 == (i$var66 - 1))) {
				for(int var29 = 0; var29 < states; var29 += 1) {
					if((var29 == st[(i$var66 - 1)]))
						cv$noStates = Math.max(cv$noStates, states);
				}
			}
		} else {
			if(true) {
				for(int index$sample57$3 = 0; index$sample57$3 < states; index$sample57$3 += 1) {
					int distributionTempVariable$var54$5 = index$sample57$3;
					double cv$probabilitySample57Value4 = (1.0 * distribution$sample57[index$sample57$3]);
					int traceTempVariable$var69$6_1 = distributionTempVariable$var54$5;
					if((0 == (i$var66 - 1))) {
						for(int var29 = 0; var29 < states; var29 += 1) {
							if((var29 == traceTempVariable$var69$6_1))
								cv$noStates = Math.max(cv$noStates, states);
						}
					}
				}
			}
		}
		if((index$i$1 == (i$var66 - 1))) {
			for(int var29 = 0; var29 < states; var29 += 1) {
				if((var29 == st[(i$var66 - 1)]))
					cv$noStates = Math.max(cv$noStates, states);
			}
		}
		if(fixedFlag$sample75) {
			for(int index$i$10_1 = 1; index$i$10_1 < samples; index$i$10_1 += 1) {
				if((index$i$10_1 == (i$var66 - 1))) {
					for(int var29 = 0; var29 < states; var29 += 1) {
						if((var29 == st[(i$var66 - 1)]))
							cv$noStates = Math.max(cv$noStates, states);
					}
				}
			}
		} else {
			for(int index$i$11 = 1; index$i$11 < samples; index$i$11 += 1) {
				if(!(index$i$11 == index$i$1)) {
					for(int index$sample75$12 = 0; index$sample75$12 < states; index$sample75$12 += 1) {
						int distributionTempVariable$var72$14 = index$sample75$12;
						double cv$probabilitySample75Value13 = (1.0 * distribution$sample75[((index$i$11 - 1) / 1)][index$sample75$12]);
						int traceTempVariable$var69$15_1 = distributionTempVariable$var72$14;
						if((index$i$11 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == traceTempVariable$var69$15_1))
									cv$noStates = Math.max(cv$noStates, states);
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var72$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$19 = i$var66;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample57) {
				if((0 == (i$var66 - 1))) {
					for(int var29 = 0; var29 < states; var29 += 1) {
						if((var29 == st[(i$var66 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var70;
							{
								double[] var70 = m[st[(i$var66 - 1)]];
								cv$temp$0$var70 = var70;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var70.length))?Math.log(cv$temp$0$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var69$35_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$var85$39_1 = cv$currentValue;
									for(int j = 0; j < samples; j += 1) {
										if((i$var66 == j)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var45 = 0; var45 < states; var45 += 1) {
														if((var45 == traceTempVariable$var85$39_1)) {
															{
																{
																	double cv$temp$4$var86;
																	{
																		double var86 = bias[traceTempVariable$var85$39_1];
																		cv$temp$4$var86 = var86;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var86)));
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
					for(int index$sample57$21 = 0; index$sample57$21 < states; index$sample57$21 += 1) {
						int distributionTempVariable$var54$23 = index$sample57$21;
						double cv$probabilitySample57Value22 = (1.0 * distribution$sample57[index$sample57$21]);
						int traceTempVariable$var69$24_1 = distributionTempVariable$var54$23;
						if((0 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == traceTempVariable$var69$24_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample57Value22);
									double[] cv$temp$1$var70;
									{
										double[] var70 = m[traceTempVariable$var69$24_1];
										cv$temp$1$var70 = var70;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample57Value22) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var70.length))?Math.log(cv$temp$1$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var69$36_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$var85$40_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var66 == j)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var45 = 0; var45 < states; var45 += 1) {
																if((var45 == traceTempVariable$var85$40_1)) {
																	{
																		{
																			double cv$temp$5$var86;
																			{
																				double var86 = bias[traceTempVariable$var85$40_1];
																				cv$temp$5$var86 = var86;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var86)));
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
			int traceTempVariable$var69$27_1 = cv$currentValue;
			if((index$i$19 == (i$var66 - 1))) {
				for(int var29 = 0; var29 < states; var29 += 1) {
					if((var29 == traceTempVariable$var69$27_1)) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var70;
						{
							double[] var70 = m[traceTempVariable$var69$27_1];
							cv$temp$2$var70 = var70;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var70.length))?Math.log(cv$temp$2$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var69$37_1 = cv$currentValue;
							}
						}
						{
							{
								int traceTempVariable$var85$41_1 = cv$currentValue;
								for(int j = 0; j < samples; j += 1) {
									if((i$var66 == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var45 = 0; var45 < states; var45 += 1) {
													if((var45 == traceTempVariable$var85$41_1)) {
														{
															{
																double cv$temp$6$var86;
																{
																	double var86 = bias[traceTempVariable$var85$41_1];
																	cv$temp$6$var86 = var86;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var86)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var86));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var86)));
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
					for(int index$sample75$29 = 0; index$sample75$29 < states; index$sample75$29 += 1) {
						int distributionTempVariable$var72$31 = index$sample75$29;
						double cv$probabilitySample75Value30 = (1.0 * distribution$sample75[((index$i$28 - 1) / 1)][index$sample75$29]);
						int traceTempVariable$var69$32_1 = distributionTempVariable$var72$31;
						if((index$i$28 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == traceTempVariable$var69$32_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample75Value30);
									double[] cv$temp$3$var70;
									{
										double[] var70 = m[traceTempVariable$var69$32_1];
										cv$temp$3$var70 = var70;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample75Value30) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var70.length))?Math.log(cv$temp$3$var70[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var69$38_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$var85$42_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var66 == j)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var45 = 0; var45 < states; var45 += 1) {
																if((var45 == traceTempVariable$var85$42_1)) {
																	{
																		{
																			double cv$temp$7$var86;
																			{
																				double var86 = bias[traceTempVariable$var85$42_1];
																				cv$temp$7$var86 = var86;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var86)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var86)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var86));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var86)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var86)));
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
					int traceTempVariable$var69$55_1 = cv$currentValue;
					for(int index$i$55_2 = 1; index$i$55_2 < samples; index$i$55_2 += 1) {
						if((i$var66 == (index$i$55_2 - 1))) {
							{
								int index$i$57 = index$i$55_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var71;
								for(int cv$i = 0; cv$i < states; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var29 = 0; var29 < states; var29 += 1) {
									if((var29 == traceTempVariable$var69$55_1)) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if(fixedFlag$sample57) {
												if((0 == (i$var66 - 1))) {
													for(int index$var29$64_1 = 0; index$var29$64_1 < states; index$var29$64_1 += 1) {
														if((index$var29$64_1 == st[(i$var66 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													for(int index$sample57$60 = 0; index$sample57$60 < states; index$sample57$60 += 1) {
														int distributionTempVariable$var54$62 = index$sample57$60;
														double cv$probabilitySample57Value61 = (1.0 * distribution$sample57[index$sample57$60]);
														int traceTempVariable$var69$63_1 = distributionTempVariable$var54$62;
														if((0 == (i$var66 - 1))) {
															for(int index$var29$65_1 = 0; index$var29$65_1 < states; index$var29$65_1 += 1) {
																if((index$var29$65_1 == traceTempVariable$var69$63_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample57Value61);
															}
														}
													}
												}
											}
											int traceTempVariable$var69$66_1 = cv$currentValue;
											if((index$i$19 == (i$var66 - 1))) {
												for(int index$var29$72_1 = 0; index$var29$72_1 < states; index$var29$72_1 += 1) {
													if((index$var29$72_1 == traceTempVariable$var69$66_1))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$67 = 1; index$i$67 < samples; index$i$67 += 1) {
												if((!(index$i$67 == index$i$19) && !(index$i$67 == index$i$57))) {
													for(int index$sample75$68 = 0; index$sample75$68 < states; index$sample75$68 += 1) {
														int distributionTempVariable$var72$70 = index$sample75$68;
														double cv$probabilitySample75Value69 = (1.0 * distribution$sample75[((index$i$67 - 1) / 1)][index$sample75$68]);
														int traceTempVariable$var69$71_1 = distributionTempVariable$var72$70;
														if((index$i$67 == (i$var66 - 1))) {
															for(int index$var29$73_1 = 0; index$var29$73_1 < states; index$var29$73_1 += 1) {
																if((index$var29$73_1 == traceTempVariable$var69$71_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample75Value69);
															}
														}
													}
												}
											}
											double[] cv$temp$8$var70;
											{
												double[] var70 = m[traceTempVariable$var69$55_1];
												cv$temp$8$var70 = var70;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var70);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample75[((index$i$55_2 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample75[((i$var66 - 1) / 1)];
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

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var29 = 0; var29 < 5; var29 += 1)
				cv$max = Math.max(cv$max, 5);
			{
				int cv$threadCount = threadCount();
				cv$var30$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var30$countGlobal[cv$index] = new double[cv$max];
			}
		}
		{
			int cv$var31$max = 5;
			cv$distributionAccumulator$var71 = new double[cv$var31$max];
		}
		{
			int cv$var31$max = 5;
			cv$var54$stateProbabilityGlobal = new double[cv$var31$max];
		}
		{
			int cv$var31$max = 5;
			cv$var72$stateProbabilityGlobal = new double[cv$var31$max];
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
				for(int var29 = 0; var29 < 5; var29 += 1)
					m[var29] = new double[5];
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
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
		{
			distribution$sample57 = new double[5];
		}
		{
			distribution$sample75 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var66 = 1; i$var66 < length$flipsMeasured; i$var66 += 1)
				distribution$sample75[((i$var66 - 1) / 1)] = new double[5];
		}
		{
			logProbability$var71 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample75 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var87 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample91 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			if(!fixedFlag$sample75)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample91)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		double[] cv$distribution$sample57 = distribution$sample57;
		double[] var52 = m[0];
		for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
			double cv$value = (((0.0 <= index$var53) && (index$var53 < var52.length))?var52[index$var53]:0.0);
			if(!fixedFlag$sample57)
				cv$distribution$sample57[index$var53] = cv$value;
		}
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			double[] cv$distribution$sample75 = distribution$sample75[((i$var66 - 1) / 1)];
			for(int index$var71 = 0; index$var71 < states; index$var71 += 1) {
				if(!fixedFlag$sample75)
					cv$distribution$sample75[index$var71] = 0.0;
			}
			if(fixedFlag$sample57) {
				if((0 == (i$var66 - 1))) {
					for(int var29 = 0; var29 < states; var29 += 1) {
						if((var29 == st[(i$var66 - 1)])) {
							{
								if(!fixedFlag$sample75) {
									double[] var70 = m[st[(i$var66 - 1)]];
									for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
										cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (1.0 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample57$2 = 0; index$sample57$2 < states; index$sample57$2 += 1) {
						int distributionTempVariable$var54$4 = index$sample57$2;
						double cv$probabilitySample57Value3 = (1.0 * distribution$sample57[index$sample57$2]);
						int traceTempVariable$var69$5_1 = distributionTempVariable$var54$4;
						if((0 == (i$var66 - 1))) {
							for(int var29 = 0; var29 < states; var29 += 1) {
								if((var29 == traceTempVariable$var69$5_1)) {
									{
										if(!fixedFlag$sample75) {
											double[] var70 = m[traceTempVariable$var69$5_1];
											for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
												cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample57Value3 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample75) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var66 - 1))) {
						for(int var29 = 0; var29 < states; var29 += 1) {
							if((var29 == st[(i$var66 - 1)])) {
								{
									if(!fixedFlag$sample75) {
										double[] var70 = m[st[(i$var66 - 1)]];
										for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
											cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (1.0 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample75$10 = 0; index$sample75$10 < states; index$sample75$10 += 1) {
							int distributionTempVariable$var72$12 = index$sample75$10;
							double cv$probabilitySample75Value11 = (1.0 * distribution$sample75[((index$i$9 - 1) / 1)][index$sample75$10]);
							int traceTempVariable$var69$13_1 = distributionTempVariable$var72$12;
							if((index$i$9 == (i$var66 - 1))) {
								for(int var29 = 0; var29 < states; var29 += 1) {
									if((var29 == traceTempVariable$var69$13_1)) {
										{
											if(!fixedFlag$sample75) {
												double[] var70 = m[traceTempVariable$var69$13_1];
												for(int index$var71 = 0; index$var71 < states; index$var71 += 1)
													cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] + (cv$probabilitySample75Value11 * (((0.0 <= index$var71) && (index$var71 < var70.length))?var70[index$var71]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var71$sum = 0.0;
			for(int index$var71 = 0; index$var71 < states; index$var71 += 1) {
				if(!fixedFlag$sample75)
					cv$var71$sum = (cv$var71$sum + cv$distribution$sample75[index$var71]);
			}
			for(int index$var71 = 0; index$var71 < states; index$var71 += 1) {
				if(!fixedFlag$sample75)
					cv$distribution$sample75[index$var71] = (cv$distribution$sample75[index$var71] / cv$var71$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			if(!fixedFlag$sample75)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample31)
								sample31(var29, threadID$var29, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample48)
								sample48(var45, threadID$var45, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample57)
				sample57();
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
				if(!fixedFlag$sample75)
					sample75(i$var66);
			}
		} else {
			for(int i$var66 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var66 >= ((1 - 1) + 1); i$var66 -= 1) {
				if(!fixedFlag$sample75)
					sample75(i$var66);
			}
			if(!fixedFlag$sample57)
				sample57();
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample48)
								sample48(var45, threadID$var45, RNG$1);
						}
				}
			);
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!fixedFlag$sample31)
								sample31(var29, threadID$var29, RNG$1);
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
			(int forStart$i$var15, int forEnd$i$var15, int threadID$i$var15, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var15 = forStart$i$var15; i$var15 < forEnd$i$var15; i$var15 += 1)
						v[i$var15] = 0.1;
			}
		);
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var18 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample31)
			logProbability$var30 = 0.0;
		logProbability$var34 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample48)
			logProbability$var46 = 0.0;
		logProbability$var53 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample57)
			logProbability$var54 = 0.0;
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
			logProbability$var71[((i$var66 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int i$var66 = 1; i$var66 < samples; i$var66 += 1)
				logProbability$sample75[((i$var66 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var87[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample91) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample91[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample31)
			logProbabilityValue$sample31();
		if(fixedFlag$sample48)
			logProbabilityValue$sample48();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityDistribution$sample57();
		logProbabilityDistribution$sample75();
		logProbabilityDistribution$sample91();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample31();
		logProbabilityValue$sample48();
		logProbabilityValue$sample57();
		logProbabilityValue$sample75();
		logProbabilityValue$sample91();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = m[var29];
						if(!fixedFlag$sample31)
							DistributionSampling.sampleDirichlet(RNG$1, v, var30);
					}
			}
		);
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample48)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample57)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var66 = 1; i$var66 < samples; i$var66 += 1) {
			if(!fixedFlag$sample75)
				st[i$var66] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var66 - 1)]]);
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