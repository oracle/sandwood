package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class HMMTestPart7$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements HMMTestPart7$CoreInterface {
	private double[] bias;
	private double[] cv$distributionAccumulator$var42;
	private double[] cv$var16$countGlobal;
	private double[] cv$var33$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private double[] distribution$sample35;
	private double[][] distribution$sample45;
	private boolean fixedFlag$sample17 = false;
	private boolean fixedFlag$sample26 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedFlag$sample54 = false;
	private boolean fixedProbFlag$sample17 = false;
	private boolean fixedProbFlag$sample26 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample54 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample45;
	private double[] logProbability$sample54;
	private double logProbability$st;
	private double logProbability$var11;
	private double logProbability$var16;
	private double logProbability$var20;
	private double logProbability$var25;
	private double logProbability$var32;
	private double logProbability$var33;
	private double[] logProbability$var42;
	private double[] logProbability$var51;
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
		setFlag$bias = true;
	}

	@Override
	public final boolean get$fixedFlag$sample17() {
		return fixedFlag$sample17;
	}

	@Override
	public final void set$fixedFlag$sample17(boolean cv$value) {
		fixedFlag$sample17 = cv$value;
		fixedProbFlag$sample17 = (fixedFlag$sample17 && fixedProbFlag$sample17);
		fixedProbFlag$sample35 = (fixedFlag$sample17 && fixedProbFlag$sample35);
		fixedProbFlag$sample45 = (fixedFlag$sample17 && fixedProbFlag$sample45);
	}

	@Override
	public final boolean get$fixedFlag$sample26() {
		return fixedFlag$sample26;
	}

	@Override
	public final void set$fixedFlag$sample26(boolean cv$value) {
		fixedFlag$sample26 = cv$value;
		fixedProbFlag$sample26 = (fixedFlag$sample26 && fixedProbFlag$sample26);
		fixedProbFlag$sample54 = (fixedFlag$sample26 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample45 = (fixedFlag$sample35 && fixedProbFlag$sample45);
		fixedProbFlag$sample54 = (fixedFlag$sample35 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		fixedFlag$sample45 = cv$value;
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample54 = (fixedFlag$sample45 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean get$fixedFlag$sample54() {
		return fixedFlag$sample54;
	}

	@Override
	public final void set$fixedFlag$sample54(boolean cv$value) {
		fixedFlag$sample54 = cv$value;
		fixedProbFlag$sample54 = (fixedFlag$sample54 && fixedProbFlag$sample54);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample35() {
		if(!fixedProbFlag$sample35) {
			if(fixedFlag$sample35) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = st[0];
					{
						{
							double[] var31 = m[0];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var31.length))?Math.log(var31[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var32 = cv$sampleAccumulator;
				logProbability$var33 = cv$sampleProbability;
				if(fixedFlag$sample35)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample35)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample17);
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			if(fixedFlag$sample35)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample45() {
		if(!fixedProbFlag$sample45) {
			if(fixedFlag$sample45) {
				double cv$accumulator = 0.0;
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var37;
					{
						int cv$sampleValue = st[i$var37];
						if(fixedFlag$sample35) {
							if((0 == (i$var37 - 1))) {
								for(int var15 = 0; var15 < states; var15 += 1) {
									if((var15 == st[(i$var37 - 1)])) {
										{
											double[] var41 = m[st[(i$var37 - 1)]];
											double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								for(int index$sample35$4 = 0; index$sample35$4 < states; index$sample35$4 += 1) {
									int distributionTempVariable$var33$6 = index$sample35$4;
									double cv$probabilitySample35Value5 = (1.0 * distribution$sample35[index$sample35$4]);
									int traceTempVariable$var40$7_1 = distributionTempVariable$var33$6;
									if((0 == (i$var37 - 1))) {
										for(int var15 = 0; var15 < states; var15 += 1) {
											if((var15 == traceTempVariable$var40$7_1)) {
												{
													double[] var41 = m[traceTempVariable$var40$7_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample35Value5) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value5);
												}
											}
										}
									}
								}
							}
						}
						int traceTempVariable$var40$10_1 = DistributionSampling.sampleCategorical(RNG$, m[st[(index$i$1 - 1)]]);
						if((index$i$1 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$10_1)) {
									{
										double[] var41 = m[traceTempVariable$var40$10_1];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample45) {
							for(int index$i$11_1 = 1; index$i$11_1 < samples; index$i$11_1 += 1) {
								if((index$i$11_1 == (i$var37 - 1))) {
									for(int var15 = 0; var15 < states; var15 += 1) {
										if((var15 == st[(i$var37 - 1)])) {
											{
												double[] var41 = m[st[(i$var37 - 1)]];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample45$13 = 0; index$sample45$13 < states; index$sample45$13 += 1) {
										int distributionTempVariable$var43$15 = index$sample45$13;
										double cv$probabilitySample45Value14 = (1.0 * distribution$sample45[((index$i$12 - 1) / 1)][index$sample45$13]);
										int traceTempVariable$var40$16_1 = distributionTempVariable$var43$15;
										if((index$i$12 == (i$var37 - 1))) {
											for(int var15 = 0; var15 < states; var15 += 1) {
												if((var15 == traceTempVariable$var40$16_1)) {
													{
														double[] var41 = m[traceTempVariable$var40$16_1];
														double cv$weightedProbability = (Math.log(cv$probabilitySample45Value14) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value14);
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
					logProbability$var42[((i$var37 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample45[((i$var37 - 1) / 1)] = cv$sampleProbability;
				}
				if(fixedFlag$sample45)
					logProbability$st = (logProbability$st + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample45)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample45 = ((fixedFlag$sample45 && fixedFlag$sample17) && fixedFlag$sample35);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45[((i$var37 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var42[((i$var37 - 1) / 1)] = cv$rvAccumulator;
			}
			if(fixedFlag$sample45)
				logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					if(fixedFlag$sample35) {
						if((0 == j)) {
							for(int var24 = 0; var24 < states; var24 += 1) {
								if((var24 == st[j])) {
									{
										double var50 = bias[st[j]];
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
							for(int index$sample35$3 = 0; index$sample35$3 < states; index$sample35$3 += 1) {
								int distributionTempVariable$var33$5 = index$sample35$3;
								double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
								int traceTempVariable$var49$6_1 = distributionTempVariable$var33$5;
								if((0 == j)) {
									for(int var24 = 0; var24 < states; var24 += 1) {
										if((var24 == traceTempVariable$var49$6_1)) {
											{
												double var50 = bias[traceTempVariable$var49$6_1];
												double cv$weightedProbability = (Math.log(cv$probabilitySample35Value4) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
												if((cv$weightedProbability < cv$distributionAccumulator))
													cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
												else {
													if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
														cv$distributionAccumulator = cv$weightedProbability;
													else
														cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
												}
												cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample35Value4);
											}
										}
									}
								}
							}
						}
					}
					if(fixedFlag$sample45) {
						for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
							if((i$var37 == j)) {
								for(int var24 = 0; var24 < states; var24 += 1) {
									if((var24 == st[j])) {
										{
											double var50 = bias[st[j]];
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
						for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
							if(true) {
								for(int index$sample45$11 = 0; index$sample45$11 < states; index$sample45$11 += 1) {
									int distributionTempVariable$var43$13 = index$sample45$11;
									double cv$probabilitySample45Value12 = (1.0 * distribution$sample45[((i$var37 - 1) / 1)][index$sample45$11]);
									int traceTempVariable$var49$14_1 = distributionTempVariable$var43$13;
									if((i$var37 == j)) {
										for(int var24 = 0; var24 < states; var24 += 1) {
											if((var24 == traceTempVariable$var49$14_1)) {
												{
													double var50 = bias[traceTempVariable$var49$14_1];
													double cv$weightedProbability = (Math.log(cv$probabilitySample45Value12) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
													if((cv$weightedProbability < cv$distributionAccumulator))
														cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
													else {
														if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
															cv$distributionAccumulator = cv$weightedProbability;
														else
															cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
													}
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample45Value12);
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
				logProbability$var51[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample54[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = (((fixedFlag$sample54 && fixedFlag$sample26) && fixedFlag$sample35) && fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample54[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample17() {
		if(!fixedProbFlag$sample17) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var15 = 0; var15 < states; var15 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var15];
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
			logProbability$var11 = cv$sampleAccumulator;
			logProbability$var16 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample17 = fixedFlag$sample17;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var16;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var11 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample17)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample26() {
		if(!fixedProbFlag$sample26) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var24 = 0; var24 < states; var24 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = bias[var24];
					{
						{
							double var18 = 1.0;
							double var19 = 1.0;
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var18, var19));
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
			logProbability$var20 = cv$sampleAccumulator;
			logProbability$var25 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample26 = fixedFlag$sample26;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var25;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var20 = cv$rvAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample26)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				int cv$sampleValue = st[0];
				{
					{
						double[] var31 = m[0];
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var31.length))?Math.log(var31[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var32 = cv$sampleAccumulator;
			logProbability$var33 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedFlag$sample17);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var33;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var32 = cv$rvAccumulator;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var37;
				{
					int cv$sampleValue = st[i$var37];
					{
						{
							double[] var41 = m[st[(i$var37 - 1)]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var41.length))?Math.log(var41[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var42[((i$var37 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample45[((i$var37 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = ((fixedFlag$sample45 && fixedFlag$sample17) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45[((i$var37 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var42[((i$var37 - 1) / 1)] = cv$rvAccumulator;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample54() {
		if(!fixedProbFlag$sample54) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var50 = bias[st[j]];
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var50));
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
				logProbability$var51[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample54[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample54 = (((fixedFlag$sample54 && fixedFlag$sample26) && fixedFlag$sample35) && fixedFlag$sample45);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample54[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var51[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample17(int var15) {
		double[] cv$targetLocal = m[var15];
		double[] cv$countLocal = cv$var16$countGlobal;
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					if((var15 == 0)) {
						if(fixedFlag$sample35) {
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
					for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
						if(fixedFlag$sample35) {
							if((0 == (i$var37 - 1))) {
								if((var15 == st[(i$var37 - 1)])) {
									if(fixedFlag$sample45) {
										{
											int index$i$22 = i$var37;
											{
												{
													{
														{
															cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + 1.0);
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
								for(int index$sample35$6 = 0; index$sample35$6 < states; index$sample35$6 += 1) {
									int distributionTempVariable$var33$8 = index$sample35$6;
									double cv$probabilitySample35Value7 = (1.0 * distribution$sample35[index$sample35$6]);
									int traceTempVariable$var40$9_1 = distributionTempVariable$var33$8;
									if((0 == (i$var37 - 1))) {
										if((var15 == traceTempVariable$var40$9_1)) {
											if(fixedFlag$sample45) {
												{
													int index$i$24 = i$var37;
													{
														{
															{
																{
																	cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + cv$probabilitySample35Value7);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
						if(fixedFlag$sample45) {
							for(int index$i$13_1 = 1; index$i$13_1 < samples; index$i$13_1 += 1) {
								if((index$i$13_1 == (i$var37 - 1))) {
									if((var15 == st[(i$var37 - 1)])) {
										if(fixedFlag$sample45) {
											{
												int index$i$26 = i$var37;
												{
													{
														{
															{
																cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + 1.0);
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
									for(int index$sample45$15 = 0; index$sample45$15 < states; index$sample45$15 += 1) {
										int distributionTempVariable$var43$17 = index$sample45$15;
										double cv$probabilitySample45Value16 = (1.0 * distribution$sample45[((index$i$14 - 1) / 1)][index$sample45$15]);
										int traceTempVariable$var40$18_1 = distributionTempVariable$var43$17;
										if((index$i$14 == (i$var37 - 1))) {
											if((var15 == traceTempVariable$var40$18_1)) {
												if(fixedFlag$sample45) {
													{
														int index$i$28 = i$var37;
														{
															{
																{
																	{
																		cv$countLocal[st[i$var37]] = (cv$countLocal[st[i$var37]] + cv$probabilitySample45Value16);
																	}
																}
															}
														}
													}
												}
											}
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
				if((var15 == 0)) {
					if(!fixedFlag$sample35) {
						{
							{
								{
									double scopeVariable$reachedSourceProbability = 0.0;
									{
										scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
									}
									double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
									for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
										cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample35[cv$loopIndex] * cv$distributionProbability));
								}
							}
						}
					}
				}
			}
		}
		{
			{
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
					if(fixedFlag$sample35) {
						if((0 == (i$var37 - 1))) {
							if((var15 == st[(i$var37 - 1)])) {
								if(!fixedFlag$sample45) {
									{
										int index$i$53 = i$var37;
										{
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
													cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
											}
										}
									}
								}
							}
						}
					} else {
						if(true) {
							for(int index$sample35$37 = 0; index$sample35$37 < states; index$sample35$37 += 1) {
								int distributionTempVariable$var33$39 = index$sample35$37;
								double cv$probabilitySample35Value38 = (1.0 * distribution$sample35[index$sample35$37]);
								int traceTempVariable$var40$40_1 = distributionTempVariable$var33$39;
								if((0 == (i$var37 - 1))) {
									if((var15 == traceTempVariable$var40$40_1)) {
										if(!fixedFlag$sample45) {
											{
												int index$i$55 = i$var37;
												{
													{
														double scopeVariable$reachedSourceProbability = 0.0;
														{
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
														}
														double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample35Value38);
														for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
															cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
					if(fixedFlag$sample45) {
						for(int index$i$44_1 = 1; index$i$44_1 < samples; index$i$44_1 += 1) {
							if((index$i$44_1 == (i$var37 - 1))) {
								if((var15 == st[(i$var37 - 1)])) {
									if(!fixedFlag$sample45) {
										{
											int index$i$57 = i$var37;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample45$46 = 0; index$sample45$46 < states; index$sample45$46 += 1) {
									int distributionTempVariable$var43$48 = index$sample45$46;
									double cv$probabilitySample45Value47 = (1.0 * distribution$sample45[((index$i$45 - 1) / 1)][index$sample45$46]);
									int traceTempVariable$var40$49_1 = distributionTempVariable$var43$48;
									if((index$i$45 == (i$var37 - 1))) {
										if((var15 == traceTempVariable$var40$49_1)) {
											if(!fixedFlag$sample45) {
												{
													int index$i$59 = i$var37;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample45Value47);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample45[((i$var37 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
														}
													}
												}
											}
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

	private final void sample26(int var24) {
		double cv$sum = 0.0;
		double cv$count = 0.0;
		{
			{
				{
					for(int j = 0; j < samples; j += 1) {
						if(fixedFlag$sample35) {
							if((0 == j)) {
								if((var24 == st[j])) {
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
								for(int index$sample35$3 = 0; index$sample35$3 < states; index$sample35$3 += 1) {
									int distributionTempVariable$var33$5 = index$sample35$3;
									double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
									int traceTempVariable$var49$6_1 = distributionTempVariable$var33$5;
									if((0 == j)) {
										if((var24 == traceTempVariable$var49$6_1)) {
											{
												{
													{
														{
															{
																cv$count = (cv$count + cv$probabilitySample35Value4);
																if(flips[j])
																	cv$sum = (cv$sum + cv$probabilitySample35Value4);
															}
														}
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
						if(fixedFlag$sample45) {
							for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
								if((i$var37 == j)) {
									if((var24 == st[j])) {
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
							for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
								if(true) {
									for(int index$sample45$12 = 0; index$sample45$12 < states; index$sample45$12 += 1) {
										int distributionTempVariable$var43$14 = index$sample45$12;
										double cv$probabilitySample45Value13 = (1.0 * distribution$sample45[((i$var37 - 1) / 1)][index$sample45$12]);
										int traceTempVariable$var49$15_1 = distributionTempVariable$var43$14;
										if((i$var37 == j)) {
											if((var24 == traceTempVariable$var49$15_1)) {
												{
													{
														{
															{
																{
																	cv$count = (cv$count + cv$probabilitySample45Value13);
																	if(flips[j])
																		cv$sum = (cv$sum + cv$probabilitySample45Value13);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		double var25 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		bias[var24] = var25;
	}

	private final void sample35() {
		double[] cv$stateProbabilityLocal = cv$var33$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$var31;
				{
					double[] var31 = m[0];
					cv$temp$0$var31 = var31;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var31.length))?Math.log(cv$temp$0$var31[cv$currentValue]):Double.NEGATIVE_INFINITY));
				{
					{
						int traceTempVariable$var40$1_1 = cv$currentValue;
						for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
							if((0 == (i$var37 - 1))) {
								if(fixedFlag$sample45) {
									{
										int index$i$3 = i$var37;
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											for(int var15 = 0; var15 < states; var15 += 1) {
												if((var15 == traceTempVariable$var40$1_1)) {
													{
														{
															double[] cv$temp$1$var41;
															{
																double[] var41 = m[traceTempVariable$var40$1_1];
																cv$temp$1$var41 = var41;
															}
															if(((Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= st[i$var37]) && (st[i$var37] < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[st[i$var37]]):Double.NEGATIVE_INFINITY)));
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
						int traceTempVariable$var49$6_1 = cv$currentValue;
						for(int j = 0; j < samples; j += 1) {
							if((0 == j)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										for(int var24 = 0; var24 < states; var24 += 1) {
											if((var24 == traceTempVariable$var49$6_1)) {
												{
													{
														double cv$temp$2$var50;
														{
															double var50 = bias[traceTempVariable$var49$6_1];
															cv$temp$2$var50 = var50;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$2$var50)));
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
					int traceTempVariable$var40$10_1 = cv$currentValue;
					for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
						if((0 == (i$var37 - 1))) {
							if(!fixedFlag$sample45) {
								{
									int index$i$12 = i$var37;
									double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var42;
									for(int cv$i = 0; cv$i < states; cv$i += 1)
										cv$accumulatedConsumerDistributions[cv$i] = 0.0;
									double cv$reachedDistributionProbability = 0.0;
									for(int var15 = 0; var15 < states; var15 += 1) {
										if((var15 == traceTempVariable$var40$10_1)) {
											{
												double scopeVariable$reachedSourceProbability = 0.0;
												{
													scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
												double[] cv$temp$3$var41;
												{
													double[] var41 = m[traceTempVariable$var40$10_1];
													cv$temp$3$var41 = var41;
												}
												double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
												cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
												DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$3$var41);
											}
										}
									}
									double[] cv$sampleDistribution = distribution$sample45[((i$var37 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample35;
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	private final void sample45(int i$var37) {
		double[] cv$stateProbabilityLocal = cv$var43$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < states; cv$valuePos += 1) {
			int index$i$1 = i$var37;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			if(fixedFlag$sample35) {
				if((0 == (i$var37 - 1))) {
					for(int var15 = 0; var15 < states; var15 += 1) {
						if((var15 == st[(i$var37 - 1)])) {
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$0$var41;
							{
								double[] var41 = m[st[(i$var37 - 1)]];
								cv$temp$0$var41 = var41;
							}
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var41.length))?Math.log(cv$temp$0$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
							{
								{
									int traceTempVariable$var40$17_1 = cv$currentValue;
								}
							}
							{
								{
									int traceTempVariable$var49$21_1 = cv$currentValue;
									for(int j = 0; j < samples; j += 1) {
										if((i$var37 == j)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													for(int var24 = 0; var24 < states; var24 += 1) {
														if((var24 == traceTempVariable$var49$21_1)) {
															{
																{
																	double cv$temp$4$var50;
																	{
																		double var50 = bias[traceTempVariable$var49$21_1];
																		cv$temp$4$var50 = var50;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var50)));
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
					for(int index$sample35$3 = 0; index$sample35$3 < states; index$sample35$3 += 1) {
						int distributionTempVariable$var33$5 = index$sample35$3;
						double cv$probabilitySample35Value4 = (1.0 * distribution$sample35[index$sample35$3]);
						int traceTempVariable$var40$6_1 = distributionTempVariable$var33$5;
						if((0 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$6_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample35Value4);
									double[] cv$temp$1$var41;
									{
										double[] var41 = m[traceTempVariable$var40$6_1];
										cv$temp$1$var41 = var41;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample35Value4) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var41.length))?Math.log(cv$temp$1$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var40$18_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$var49$22_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var37 == j)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var24 = 0; var24 < states; var24 += 1) {
																if((var24 == traceTempVariable$var49$22_1)) {
																	{
																		{
																			double cv$temp$5$var50;
																			{
																				double var50 = bias[traceTempVariable$var49$22_1];
																				cv$temp$5$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var50)));
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
			int traceTempVariable$var40$9_1 = cv$currentValue;
			if((index$i$1 == (i$var37 - 1))) {
				for(int var15 = 0; var15 < states; var15 += 1) {
					if((var15 == traceTempVariable$var40$9_1)) {
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$2$var41;
						{
							double[] var41 = m[traceTempVariable$var40$9_1];
							cv$temp$2$var41 = var41;
						}
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var41.length))?Math.log(cv$temp$2$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
						{
							{
								int traceTempVariable$var40$19_1 = cv$currentValue;
							}
						}
						{
							{
								int traceTempVariable$var49$23_1 = cv$currentValue;
								for(int j = 0; j < samples; j += 1) {
									if((i$var37 == j)) {
										{
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												for(int var24 = 0; var24 < states; var24 += 1) {
													if((var24 == traceTempVariable$var49$23_1)) {
														{
															{
																double cv$temp$6$var50;
																{
																	double var50 = bias[traceTempVariable$var49$23_1];
																	cv$temp$6$var50 = var50;
																}
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var50)));
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
			for(int index$i$10 = 1; index$i$10 < samples; index$i$10 += 1) {
				if(!(index$i$10 == index$i$1)) {
					for(int index$sample45$11 = 0; index$sample45$11 < states; index$sample45$11 += 1) {
						int distributionTempVariable$var43$13 = index$sample45$11;
						double cv$probabilitySample45Value12 = (1.0 * distribution$sample45[((index$i$10 - 1) / 1)][index$sample45$11]);
						int traceTempVariable$var40$14_1 = distributionTempVariable$var43$13;
						if((index$i$10 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$14_1)) {
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample45Value12);
									double[] cv$temp$3$var41;
									{
										double[] var41 = m[traceTempVariable$var40$14_1];
										cv$temp$3$var41 = var41;
									}
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample45Value12) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var41.length))?Math.log(cv$temp$3$var41[cv$currentValue]):Double.NEGATIVE_INFINITY));
									{
										{
											int traceTempVariable$var40$20_1 = cv$currentValue;
										}
									}
									{
										{
											int traceTempVariable$var49$24_1 = cv$currentValue;
											for(int j = 0; j < samples; j += 1) {
												if((i$var37 == j)) {
													{
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															for(int var24 = 0; var24 < states; var24 += 1) {
																if((var24 == traceTempVariable$var49$24_1)) {
																	{
																		{
																			double cv$temp$7$var50;
																			{
																				double var50 = bias[traceTempVariable$var49$24_1];
																				cv$temp$7$var50 = var50;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var50)));
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
					int traceTempVariable$var40$37_1 = cv$currentValue;
					for(int index$i$37_2 = 1; index$i$37_2 < samples; index$i$37_2 += 1) {
						if((i$var37 == (index$i$37_2 - 1))) {
							{
								int index$i$39 = index$i$37_2;
								double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var42;
								for(int cv$i = 0; cv$i < states; cv$i += 1)
									cv$accumulatedConsumerDistributions[cv$i] = 0.0;
								double cv$reachedDistributionProbability = 0.0;
								for(int var15 = 0; var15 < states; var15 += 1) {
									if((var15 == traceTempVariable$var40$37_1)) {
										{
											double scopeVariable$reachedSourceProbability = 0.0;
											if(fixedFlag$sample35) {
												if((0 == (i$var37 - 1))) {
													for(int index$var15$46_1 = 0; index$var15$46_1 < states; index$var15$46_1 += 1) {
														if((index$var15$46_1 == st[(i$var37 - 1)]))
															scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
												}
											} else {
												if(true) {
													for(int index$sample35$42 = 0; index$sample35$42 < states; index$sample35$42 += 1) {
														int distributionTempVariable$var33$44 = index$sample35$42;
														double cv$probabilitySample35Value43 = (1.0 * distribution$sample35[index$sample35$42]);
														int traceTempVariable$var40$45_1 = distributionTempVariable$var33$44;
														if((0 == (i$var37 - 1))) {
															for(int index$var15$47_1 = 0; index$var15$47_1 < states; index$var15$47_1 += 1) {
																if((index$var15$47_1 == traceTempVariable$var40$45_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample35Value43);
															}
														}
													}
												}
											}
											int traceTempVariable$var40$48_1 = cv$currentValue;
											if((index$i$1 == (i$var37 - 1))) {
												for(int index$var15$54_1 = 0; index$var15$54_1 < states; index$var15$54_1 += 1) {
													if((index$var15$54_1 == traceTempVariable$var40$48_1))
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
												}
											}
											for(int index$i$49 = 1; index$i$49 < samples; index$i$49 += 1) {
												if((!(index$i$49 == index$i$1) && !(index$i$49 == index$i$39))) {
													for(int index$sample45$50 = 0; index$sample45$50 < states; index$sample45$50 += 1) {
														int distributionTempVariable$var43$52 = index$sample45$50;
														double cv$probabilitySample45Value51 = (1.0 * distribution$sample45[((index$i$49 - 1) / 1)][index$sample45$50]);
														int traceTempVariable$var40$53_1 = distributionTempVariable$var43$52;
														if((index$i$49 == (i$var37 - 1))) {
															for(int index$var15$55_1 = 0; index$var15$55_1 < states; index$var15$55_1 += 1) {
																if((index$var15$55_1 == traceTempVariable$var40$53_1))
																	scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample45Value51);
															}
														}
													}
												}
											}
											double[] cv$temp$8$var41;
											{
												double[] var41 = m[traceTempVariable$var40$37_1];
												cv$temp$8$var41 = var41;
											}
											double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
											cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
											DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$8$var41);
										}
									}
								}
								double[] cv$sampleDistribution = distribution$sample45[((index$i$37_2 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample45[((i$var37 - 1) / 1)];
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$stateProbabilityLocal.length; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$stateProbabilityLocal.length);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
	}

	@Override
	public final void allocateScratch() {
		{
			int cv$max = 0;
			for(int var15 = 0; var15 < 5; var15 += 1)
				cv$max = Math.max(cv$max, 5);
			cv$var16$countGlobal = new double[cv$max];
		}
		{
			int cv$var17$max = 5;
			cv$distributionAccumulator$var42 = new double[cv$var17$max];
		}
		{
			int cv$var17$max = 5;
			cv$var33$stateProbabilityGlobal = new double[cv$var17$max];
		}
		{
			int cv$var17$max = 5;
			cv$var43$stateProbabilityGlobal = new double[cv$var17$max];
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
				for(int var15 = 0; var15 < 5; var15 += 1)
					m[var15] = new double[5];
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
			distribution$sample35 = new double[5];
		}
		{
			distribution$sample45 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)][];
			for(int i$var37 = 1; i$var37 < length$flipsMeasured; i$var37 += 1)
				distribution$sample45[((i$var37 - 1) / 1)] = new double[5];
		}
		{
			logProbability$var42 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample45 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var51 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample54 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var15 = 0; var15 < states; var15 += 1) {
			double[] var16 = m[var15];
			if(!fixedFlag$sample17)
				DistributionSampling.sampleDirichlet(RNG$, v, var16);
		}
		for(int var24 = 0; var24 < states; var24 += 1) {
			if(!fixedFlag$sample26)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if(!fixedFlag$sample45)
				st[i$var37] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
		}
		for(int j = 0; j < samples; j += 1) {
			if(!fixedFlag$sample54)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var15 = 0; var15 < states; var15 += 1) {
			double[] var16 = m[var15];
			if(!fixedFlag$sample17)
				DistributionSampling.sampleDirichlet(RNG$, v, var16);
		}
		for(int var24 = 0; var24 < states; var24 += 1) {
			if(!fixedFlag$sample26)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		double[] cv$distribution$sample35 = distribution$sample35;
		double[] var31 = m[0];
		for(int index$var32 = 0; index$var32 < states; index$var32 += 1) {
			double cv$value = (((0.0 <= index$var32) && (index$var32 < var31.length))?var31[index$var32]:0.0);
			if(!fixedFlag$sample35)
				cv$distribution$sample35[index$var32] = cv$value;
		}
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			double[] cv$distribution$sample45 = distribution$sample45[((i$var37 - 1) / 1)];
			for(int index$var42 = 0; index$var42 < states; index$var42 += 1) {
				if(!fixedFlag$sample45)
					cv$distribution$sample45[index$var42] = 0.0;
			}
			if(fixedFlag$sample35) {
				if((0 == (i$var37 - 1))) {
					for(int var15 = 0; var15 < states; var15 += 1) {
						if((var15 == st[(i$var37 - 1)])) {
							{
								if(!fixedFlag$sample45) {
									double[] var41 = m[st[(i$var37 - 1)]];
									for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
										cv$distribution$sample45[index$var42] = (cv$distribution$sample45[index$var42] + (1.0 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
								}
							}
						}
					}
				}
			} else {
				if(true) {
					for(int index$sample35$2 = 0; index$sample35$2 < states; index$sample35$2 += 1) {
						int distributionTempVariable$var33$4 = index$sample35$2;
						double cv$probabilitySample35Value3 = (1.0 * distribution$sample35[index$sample35$2]);
						int traceTempVariable$var40$5_1 = distributionTempVariable$var33$4;
						if((0 == (i$var37 - 1))) {
							for(int var15 = 0; var15 < states; var15 += 1) {
								if((var15 == traceTempVariable$var40$5_1)) {
									{
										if(!fixedFlag$sample45) {
											double[] var41 = m[traceTempVariable$var40$5_1];
											for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
												cv$distribution$sample45[index$var42] = (cv$distribution$sample45[index$var42] + (cv$probabilitySample35Value3 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
										}
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample45) {
				for(int index$i$8_1 = 1; index$i$8_1 < samples; index$i$8_1 += 1) {
					if((index$i$8_1 == (i$var37 - 1))) {
						for(int var15 = 0; var15 < states; var15 += 1) {
							if((var15 == st[(i$var37 - 1)])) {
								{
									if(!fixedFlag$sample45) {
										double[] var41 = m[st[(i$var37 - 1)]];
										for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
											cv$distribution$sample45[index$var42] = (cv$distribution$sample45[index$var42] + (1.0 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$9 = 1; index$i$9 < samples; index$i$9 += 1) {
					if(true) {
						for(int index$sample45$10 = 0; index$sample45$10 < states; index$sample45$10 += 1) {
							int distributionTempVariable$var43$12 = index$sample45$10;
							double cv$probabilitySample45Value11 = (1.0 * distribution$sample45[((index$i$9 - 1) / 1)][index$sample45$10]);
							int traceTempVariable$var40$13_1 = distributionTempVariable$var43$12;
							if((index$i$9 == (i$var37 - 1))) {
								for(int var15 = 0; var15 < states; var15 += 1) {
									if((var15 == traceTempVariable$var40$13_1)) {
										{
											if(!fixedFlag$sample45) {
												double[] var41 = m[traceTempVariable$var40$13_1];
												for(int index$var42 = 0; index$var42 < states; index$var42 += 1)
													cv$distribution$sample45[index$var42] = (cv$distribution$sample45[index$var42] + (cv$probabilitySample45Value11 * (((0.0 <= index$var42) && (index$var42 < var41.length))?var41[index$var42]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var42$sum = 0.0;
			for(int index$var42 = 0; index$var42 < states; index$var42 += 1) {
				if(!fixedFlag$sample45)
					cv$var42$sum = (cv$var42$sum + cv$distribution$sample45[index$var42]);
			}
			for(int index$var42 = 0; index$var42 < states; index$var42 += 1) {
				if(!fixedFlag$sample45)
					cv$distribution$sample45[index$var42] = (cv$distribution$sample45[index$var42] / cv$var42$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var15 = 0; var15 < states; var15 += 1) {
			double[] var16 = m[var15];
			if(!fixedFlag$sample17)
				DistributionSampling.sampleDirichlet(RNG$, v, var16);
		}
		for(int var24 = 0; var24 < states; var24 += 1) {
			if(!fixedFlag$sample26)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if(!fixedFlag$sample45)
				st[i$var37] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var15 = 0; var15 < states; var15 += 1) {
				if(!fixedFlag$sample17)
					sample17(var15);
			}
			for(int var24 = 0; var24 < states; var24 += 1) {
				if(!fixedFlag$sample26)
					sample26(var24);
			}
			if(!fixedFlag$sample35)
				sample35();
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
				if(!fixedFlag$sample45)
					sample45(i$var37);
			}
		} else {
			for(int i$var37 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var37 >= ((1 - 1) + 1); i$var37 -= 1) {
				if(!fixedFlag$sample45)
					sample45(i$var37);
			}
			if(!fixedFlag$sample35)
				sample35();
			for(int var24 = (states - ((((states - 1) - 0) % 1) + 1)); var24 >= ((0 - 1) + 1); var24 -= 1) {
				if(!fixedFlag$sample26)
					sample26(var24);
			}
			for(int var15 = (states - ((((states - 1) - 0) % 1) + 1)); var15 >= ((0 - 1) + 1); var15 -= 1) {
				if(!fixedFlag$sample17)
					sample17(var15);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 5;
		for(int i$var8 = 0; i$var8 < 5; i$var8 += 1)
			v[i$var8] = 0.1;
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var11 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample17)
			logProbability$var16 = 0.0;
		logProbability$var20 = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample26)
			logProbability$var25 = 0.0;
		logProbability$var32 = 0.0;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$var33 = 0.0;
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
			logProbability$var42[((i$var37 - 1) / 1)] = 0.0;
		if(!fixedProbFlag$sample45) {
			for(int i$var37 = 1; i$var37 < samples; i$var37 += 1)
				logProbability$sample45[((i$var37 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < samples; j += 1)
			logProbability$var51[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample54) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample54[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample17)
			logProbabilityValue$sample17();
		if(fixedFlag$sample26)
			logProbabilityValue$sample26();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample26();
		logProbabilityDistribution$sample35();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample54();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample17();
		logProbabilityValue$sample26();
		logProbabilityValue$sample35();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var15 = 0; var15 < states; var15 += 1) {
			double[] var16 = m[var15];
			if(!fixedFlag$sample17)
				DistributionSampling.sampleDirichlet(RNG$, v, var16);
		}
		for(int var24 = 0; var24 < states; var24 += 1) {
			if(!fixedFlag$sample26)
				bias[var24] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample35)
			st[0] = DistributionSampling.sampleCategorical(RNG$, m[0]);
		for(int i$var37 = 1; i$var37 < samples; i$var37 += 1) {
			if(!fixedFlag$sample45)
				st[i$var37] = DistributionSampling.sampleCategorical(RNG$, m[st[(i$var37 - 1)]]);
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
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel HMMTestPart7(boolean[] flipsMeasured) {\n        int states = 5;\n\n        double[] v = new double[states];\n        for(int i:[0..states))\n            v[i] = 0.1;\n        \n        double[][] m = dirichlet(v).sample(states);\n        double[] bias = beta(1.0, 1.0).sample(states);\n\n        int samples = flipsMeasured.length;\n        int[] st = new int[samples];\n\n        st[0] = categorical(m[0]).sampleDistribution();\n\n        for(int i:[1..samples))\n            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n            \n        boolean[] flips = new boolean[samples];\n            \n        for(int j:[0..samples))\n            flips[j] = bernoulli(bias[st[j]]).sample();\n\n        flips.observe(flipsMeasured);\n}\n";
	}
}