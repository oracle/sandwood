package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Deterministic2$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var53;
	private double[][] cv$var29$countGlobal;
	private double[] cv$var54$stateProbabilityGlobal;
	private double[][] distribution$sample55;
	private boolean fixedFlag$sample29 = false;
	private boolean fixedFlag$sample55 = false;
	private boolean fixedFlag$sample75 = false;
	private boolean fixedProbFlag$sample29 = false;
	private boolean fixedProbFlag$sample55 = false;
	private boolean fixedProbFlag$sample75 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample55;
	private double[] logProbability$sample75;
	private double logProbability$var17;
	private double logProbability$var29;
	private double[] logProbability$var53;
	private double[] logProbability$var73;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$a() {
		return a;
	}

	@Override
	public final void set$a(int[] cv$value) {
		a = cv$value;
		setFlag$a = true;
		fixedProbFlag$sample55 = false;
		fixedProbFlag$sample75 = false;
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final boolean get$fixedFlag$sample29() {
		return fixedFlag$sample29;
	}

	@Override
	public final void set$fixedFlag$sample29(boolean cv$value) {
		fixedFlag$sample29 = cv$value;
		fixedProbFlag$sample29 = (fixedFlag$sample29 && fixedProbFlag$sample29);
		fixedProbFlag$sample55 = (fixedFlag$sample29 && fixedProbFlag$sample55);
	}

	@Override
	public final boolean get$fixedFlag$sample55() {
		return fixedFlag$sample55;
	}

	@Override
	public final void set$fixedFlag$sample55(boolean cv$value) {
		fixedFlag$sample55 = cv$value;
		fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedProbFlag$sample55);
		fixedProbFlag$sample75 = (fixedFlag$sample55 && fixedProbFlag$sample75);
	}

	@Override
	public final boolean get$fixedFlag$sample75() {
		return fixedFlag$sample75;
	}

	@Override
	public final void set$fixedFlag$sample75(boolean cv$value) {
		fixedFlag$sample75 = cv$value;
		fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedProbFlag$sample75);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample75 = false;
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
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$a() {
		return logProbability$a;
	}

	@Override
	public final double get$logProbability$b() {
		return logProbability$b;
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
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value) {
		m = cv$value;
		setFlag$m = true;
		fixedProbFlag$sample29 = false;
		fixedProbFlag$sample55 = false;
	}

	@Override
	public final int get$n() {
		return n;
	}

	@Override
	public final void set$n(int cv$value) {
		n = cv$value;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void logProbabilityDistribution$sample55() {
		if(!fixedProbFlag$sample55) {
			if(fixedFlag$sample55) {
				double cv$accumulator = 0.0;
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var46;
					{
						int cv$sampleValue = a[i$var46];
						int traceTempVariable$var49$3_1 = 0;
						for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
							if((0 == (index$i$3_2 - 1))) {
								int traceTempVariable$var51$3_3 = traceTempVariable$var49$3_1;
								if((index$i$3_2 == i$var46)) {
									for(int var28 = 0; var28 < states; var28 += 1) {
										if((var28 == b[i$var46])) {
											{
												double[] var52 = m[traceTempVariable$var51$3_3];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
							if((index$i$1 == (index$i$5_1 - 1))) {
								int traceTempVariable$var51$5_2 = a[(index$i$5_1 - 1)];
								if((index$i$5_1 == i$var46)) {
									for(int var28 = 0; var28 < states; var28 += 1) {
										if((var28 == b[i$var46])) {
											{
												double[] var52 = m[traceTempVariable$var51$5_2];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample55) {
							for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
								for(int index$i$6_2 = 1; index$i$6_2 < n; index$i$6_2 += 1) {
									if((index$i$6_1 == (index$i$6_2 - 1))) {
										int traceTempVariable$var51$6_3 = a[(index$i$6_2 - 1)];
										if((index$i$6_2 == i$var46)) {
											for(int var28 = 0; var28 < states; var28 += 1) {
												if((var28 == b[i$var46])) {
													{
														double[] var52 = m[traceTempVariable$var51$6_3];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
							for(int index$i$7 = 1; index$i$7 < n; index$i$7 += 1) {
								if(!(index$i$7 == index$i$1)) {
									for(int index$sample55$8 = 0; index$sample55$8 < states; index$sample55$8 += 1) {
										int distributionTempVariable$var54$10 = index$sample55$8;
										double cv$probabilitySample55Value9 = (1.0 * distribution$sample55[((index$i$7 - 1) / 1)][index$sample55$8]);
										for(int index$i$11_1 = 1; index$i$11_1 < n; index$i$11_1 += 1) {
											if((index$i$7 == (index$i$11_1 - 1))) {
												int traceTempVariable$var51$11_2 = a[(index$i$11_1 - 1)];
												if((index$i$11_1 == i$var46)) {
													for(int var28 = 0; var28 < states; var28 += 1) {
														if((var28 == b[i$var46])) {
															{
																double[] var52 = m[traceTempVariable$var51$11_2];
																double cv$weightedProbability = (Math.log(cv$probabilitySample55Value9) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value9);
															}
														}
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
					logProbability$var53[((i$var46 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
					boolean cv$guard$b = false;
					int index$i$15 = i$var46;
					{
						for(int index$i$16_1 = 1; index$i$16_1 < n; index$i$16_1 += 1) {
							if((i$var46 == (index$i$16_1 - 1))) {
								if(fixedFlag$sample55) {
									if(!cv$guard$b) {
										cv$guard$b = true;
										logProbability$b = (logProbability$b + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample55)
					logProbability$a = (logProbability$a + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample55)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var53[((i$var46 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$b = false;
				int index$i$17 = i$var46;
				{
					for(int index$i$18_1 = 1; index$i$18_1 < n; index$i$18_1 += 1) {
						if((i$var46 == (index$i$18_1 - 1))) {
							if(fixedFlag$sample55) {
								if(!cv$guard$b) {
									cv$guard$b = true;
									logProbability$b = (logProbability$b + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample55)
				logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					int traceTempVariable$var70$2_1 = 0;
					if((0 == (j + 1))) {
						{
							double var72 = (double)(1 / traceTempVariable$var70$2_1);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var72));
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
					if(fixedFlag$sample55) {
						for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
							if((i$var46 == (j + 1))) {
								{
									double var72 = (double)(1 / a[(j + 1)]);
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var72));
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
					} else {
						for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
							if(true) {
								for(int index$sample55$5 = 0; index$sample55$5 < states; index$sample55$5 += 1) {
									int distributionTempVariable$var54$7 = index$sample55$5;
									double cv$probabilitySample55Value6 = (1.0 * distribution$sample55[((i$var46 - 1) / 1)][index$sample55$5]);
									if((i$var46 == (j + 1))) {
										{
											double var72 = (double)(1 / a[(j + 1)]);
											double cv$weightedProbability = (Math.log(cv$probabilitySample55Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var72));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value6);
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
				logProbability$var73[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample75[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample55);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!fixedProbFlag$sample29) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < states; var28 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var28];
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
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$var29 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample29 = fixedFlag$sample29;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var29;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var17 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample29)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var46;
				{
					int cv$sampleValue = a[i$var46];
					{
						{
							double[] var52 = m[b[i$var46]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < states))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var53[((i$var46 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample55[((i$var46 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$b = false;
				int index$i$3 = i$var46;
				{
					for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
						if((i$var46 == (index$i$4_1 - 1))) {
							if(!cv$guard$b) {
								cv$guard$b = true;
								logProbability$b = (logProbability$b + cv$sampleProbability);
							}
						}
					}
				}
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample55 = (fixedFlag$sample55 && fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample55[((i$var46 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var53[((i$var46 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$b = false;
				int index$i$5 = i$var46;
				{
					for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
						if((i$var46 == (index$i$6_1 - 1))) {
							if(!cv$guard$b) {
								cv$guard$b = true;
								logProbability$b = (logProbability$b + cv$sampleValue);
							}
						}
					}
				}
			}
			logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample55)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var72 = (double)(1 / a[(j + 1)]);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var72));
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
				logProbability$var73[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample75[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample75 = (fixedFlag$sample75 && fixedFlag$sample55);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample75[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var73[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample29(int var28, int threadID$cv$var28, Rng RNG$) {
		double[] cv$targetLocal = m[var28];
		double[] cv$countLocal = cv$var29$countGlobal[threadID$cv$var28];
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
						int traceTempVariable$var49$2_1 = 0;
						for(int index$i$2_2 = 1; index$i$2_2 < n; index$i$2_2 += 1) {
							if((0 == (index$i$2_2 - 1))) {
								int traceTempVariable$var51$2_3 = traceTempVariable$var49$2_1;
								if((index$i$2_2 == i$var46)) {
									if((var28 == b[i$var46])) {
										if(fixedFlag$sample55) {
											{
												int index$i$14 = i$var46;
												{
													{
														{
															{
																cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
						if(fixedFlag$sample55) {
							for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
								for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
									if((index$i$5_1 == (index$i$5_2 - 1))) {
										int traceTempVariable$var51$5_3 = a[(index$i$5_2 - 1)];
										if((index$i$5_2 == i$var46)) {
											if((var28 == b[i$var46])) {
												if(fixedFlag$sample55) {
													{
														int index$i$16 = i$var46;
														{
															{
																{
																	{
																		cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + 1.0);
																	}
																}
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
							for(int index$i$6 = 1; index$i$6 < n; index$i$6 += 1) {
								if(true) {
									for(int index$sample55$7 = 0; index$sample55$7 < states; index$sample55$7 += 1) {
										int distributionTempVariable$var54$9 = index$sample55$7;
										double cv$probabilitySample55Value8 = (1.0 * distribution$sample55[((index$i$6 - 1) / 1)][index$sample55$7]);
										for(int index$i$10_1 = 1; index$i$10_1 < n; index$i$10_1 += 1) {
											if((index$i$6 == (index$i$10_1 - 1))) {
												int traceTempVariable$var51$10_2 = a[(index$i$10_1 - 1)];
												if((index$i$10_1 == i$var46)) {
													if((var28 == b[i$var46])) {
														if(fixedFlag$sample55) {
															{
																int index$i$18 = i$var46;
																{
																	{
																		{
																			{
																				cv$countLocal[a[i$var46]] = (cv$countLocal[a[i$var46]] + cv$probabilitySample55Value8);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
					int traceTempVariable$var49$23_1 = 0;
					for(int index$i$23_2 = 1; index$i$23_2 < n; index$i$23_2 += 1) {
						if((0 == (index$i$23_2 - 1))) {
							int traceTempVariable$var51$23_3 = traceTempVariable$var49$23_1;
							if((index$i$23_2 == i$var46)) {
								if((var28 == b[i$var46])) {
									if(!fixedFlag$sample55) {
										{
											int index$i$35 = i$var46;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
					if(fixedFlag$sample55) {
						for(int index$i$26_1 = 1; index$i$26_1 < n; index$i$26_1 += 1) {
							for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
								if((index$i$26_1 == (index$i$26_2 - 1))) {
									int traceTempVariable$var51$26_3 = a[(index$i$26_2 - 1)];
									if((index$i$26_2 == i$var46)) {
										if((var28 == b[i$var46])) {
											if(!fixedFlag$sample55) {
												{
													int index$i$37 = i$var46;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
						for(int index$i$27 = 1; index$i$27 < n; index$i$27 += 1) {
							if(true) {
								for(int index$sample55$28 = 0; index$sample55$28 < states; index$sample55$28 += 1) {
									int distributionTempVariable$var54$30 = index$sample55$28;
									double cv$probabilitySample55Value29 = (1.0 * distribution$sample55[((index$i$27 - 1) / 1)][index$sample55$28]);
									for(int index$i$31_1 = 1; index$i$31_1 < n; index$i$31_1 += 1) {
										if((index$i$27 == (index$i$31_1 - 1))) {
											int traceTempVariable$var51$31_2 = a[(index$i$31_1 - 1)];
											if((index$i$31_1 == i$var46)) {
												if((var28 == b[i$var46])) {
													if(!fixedFlag$sample55) {
														{
															int index$i$39 = i$var46;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample55Value29);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample55[((i$var46 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
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

	private final void sample55(int i$var46) {
		int cv$numNumStates = 0;
		int index$i$1 = i$var46;
		int traceTempVariable$var49$2_1 = 0;
		for(int index$i$2_2 = 1; index$i$2_2 < n; index$i$2_2 += 1) {
			if((0 == (index$i$2_2 - 1))) {
				int traceTempVariable$var51$2_3 = traceTempVariable$var49$2_1;
				if((index$i$2_2 == i$var46)) {
					for(int var28 = 0; var28 < states; var28 += 1) {
						if((var28 == b[i$var46]))
							cv$numNumStates = Math.max(cv$numNumStates, states);
					}
				}
			}
		}
		for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
			if((index$i$1 == (index$i$4_1 - 1))) {
				int traceTempVariable$var51$4_2 = a[(index$i$4_1 - 1)];
				if((index$i$4_1 == i$var46)) {
					for(int var28 = 0; var28 < states; var28 += 1) {
						if((var28 == b[i$var46]))
							cv$numNumStates = Math.max(cv$numNumStates, states);
					}
				}
			}
		}
		if(fixedFlag$sample55) {
			for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
				for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
					if((index$i$5_1 == (index$i$5_2 - 1))) {
						int traceTempVariable$var51$5_3 = a[(index$i$5_2 - 1)];
						if((index$i$5_2 == i$var46)) {
							for(int var28 = 0; var28 < states; var28 += 1) {
								if((var28 == b[i$var46]))
									cv$numNumStates = Math.max(cv$numNumStates, states);
							}
						}
					}
				}
			}
		} else {
			for(int index$i$6 = 1; index$i$6 < n; index$i$6 += 1) {
				if(!(index$i$6 == index$i$1)) {
					for(int index$sample55$7 = 0; index$sample55$7 < states; index$sample55$7 += 1) {
						int distributionTempVariable$var54$9 = index$sample55$7;
						double cv$probabilitySample55Value8 = (1.0 * distribution$sample55[((index$i$6 - 1) / 1)][index$sample55$7]);
						for(int index$i$10_1 = 1; index$i$10_1 < n; index$i$10_1 += 1) {
							if((index$i$6 == (index$i$10_1 - 1))) {
								int traceTempVariable$var51$10_2 = a[(index$i$10_1 - 1)];
								if((index$i$10_1 == i$var46)) {
									for(int var28 = 0; var28 < states; var28 += 1) {
										if((var28 == b[i$var46]))
											cv$numNumStates = Math.max(cv$numNumStates, states);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var54$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			int index$i$14 = i$var46;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int traceTempVariable$var49$15_1 = 0;
			for(int index$i$15_2 = 1; index$i$15_2 < n; index$i$15_2 += 1) {
				if((0 == (index$i$15_2 - 1))) {
					int traceTempVariable$var51$15_3 = traceTempVariable$var49$15_1;
					if((index$i$15_2 == i$var46)) {
						for(int var28 = 0; var28 < states; var28 += 1) {
							if((var28 == b[i$var46])) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$0$var52;
								{
									double[] var52 = m[traceTempVariable$var51$15_3];
									cv$temp$0$var52 = var52;
								}
								int cv$temp$1$$var835;
								{
									int $var835 = states;
									cv$temp$1$$var835 = $var835;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$$var835))?Math.log(cv$temp$0$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var49$25_1 = cv$currentValue;
										for(int index$i$25_2 = 1; index$i$25_2 < n; index$i$25_2 += 1) {
											if((i$var46 == (index$i$25_2 - 1))) {
												int traceTempVariable$var51$25_3 = traceTempVariable$var49$25_1;
											}
										}
									}
								}
								{
									{
										int traceTempVariable$var70$28_1 = cv$currentValue;
										for(int j = 0; j < n; j += 1) {
											if((i$var46 == (j + 1))) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$6$var72;
																	{
																		double var72 = (double)(1 / traceTempVariable$var70$28_1);
																		cv$temp$6$var72 = var72;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var72)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var72));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$6$var72)));
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
						}
					}
				}
			}
			int traceTempVariable$var49$17_1 = cv$currentValue;
			for(int index$i$17_2 = 1; index$i$17_2 < n; index$i$17_2 += 1) {
				if((index$i$14 == (index$i$17_2 - 1))) {
					int traceTempVariable$var51$17_3 = traceTempVariable$var49$17_1;
					if((index$i$17_2 == i$var46)) {
						for(int var28 = 0; var28 < states; var28 += 1) {
							if((var28 == b[i$var46])) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$2$var52;
								{
									double[] var52 = m[traceTempVariable$var51$17_3];
									cv$temp$2$var52 = var52;
								}
								int cv$temp$3$$var836;
								{
									int $var836 = states;
									cv$temp$3$$var836 = $var836;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$$var836))?Math.log(cv$temp$2$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var49$26_1 = cv$currentValue;
										for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
											if((i$var46 == (index$i$26_2 - 1))) {
												int traceTempVariable$var51$26_3 = traceTempVariable$var49$26_1;
											}
										}
									}
								}
								{
									{
										int traceTempVariable$var70$29_1 = cv$currentValue;
										for(int j = 0; j < n; j += 1) {
											if((i$var46 == (j + 1))) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$7$var72;
																	{
																		double var72 = (double)(1 / traceTempVariable$var70$29_1);
																		cv$temp$7$var72 = var72;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var72)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var72));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$7$var72)));
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
						}
					}
				}
			}
			for(int index$i$18 = 1; index$i$18 < n; index$i$18 += 1) {
				if(!(index$i$18 == index$i$14)) {
					for(int index$sample55$19 = 0; index$sample55$19 < states; index$sample55$19 += 1) {
						int distributionTempVariable$var54$21 = index$sample55$19;
						double cv$probabilitySample55Value20 = (1.0 * distribution$sample55[((index$i$18 - 1) / 1)][index$sample55$19]);
						int traceTempVariable$var49$22_1 = cv$currentValue;
						for(int index$i$22_2 = 1; index$i$22_2 < n; index$i$22_2 += 1) {
							if((index$i$18 == (index$i$22_2 - 1))) {
								int traceTempVariable$var51$22_3 = traceTempVariable$var49$22_1;
								if((index$i$22_2 == i$var46)) {
									for(int var28 = 0; var28 < states; var28 += 1) {
										if((var28 == b[i$var46])) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample55Value20);
											double[] cv$temp$4$var52;
											{
												double[] var52 = m[traceTempVariable$var51$22_3];
												cv$temp$4$var52 = var52;
											}
											int cv$temp$5$$var837;
											{
												int $var837 = states;
												cv$temp$5$$var837 = $var837;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample55Value20) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$5$$var837))?Math.log(cv$temp$4$var52[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var49$27_1 = distributionTempVariable$var54$21;
													for(int index$i$27_2 = 1; index$i$27_2 < n; index$i$27_2 += 1) {
														if((i$var46 == (index$i$27_2 - 1))) {
															int traceTempVariable$var51$27_3 = traceTempVariable$var49$27_1;
														}
													}
												}
											}
											{
												{
													int traceTempVariable$var70$30_1 = distributionTempVariable$var54$21;
													for(int j = 0; j < n; j += 1) {
														if((i$var46 == (j + 1))) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				double cv$temp$8$var72;
																				{
																					double var72 = (double)(1 / traceTempVariable$var70$30_1);
																					cv$temp$8$var72 = var72;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var72)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var72)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var72));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var72)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$8$var72)));
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
									}
								}
							}
						}
					}
				}
			}
			{
				{
					int traceTempVariable$var49$37_1 = cv$currentValue;
					for(int index$i$37_2 = 1; index$i$37_2 < n; index$i$37_2 += 1) {
						if((i$var46 == (index$i$37_2 - 1))) {
							int traceTempVariable$var51$37_3 = traceTempVariable$var49$37_1;
							for(int index$i$37_4 = 1; index$i$37_4 < n; index$i$37_4 += 1) {
								if((index$i$37_2 == index$i$37_4)) {
									{
										int index$i$39 = index$i$37_4;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var53;
										for(int cv$i = 0; cv$i < states; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var28 = 0; var28 < states; var28 += 1) {
											if((var28 == b[index$i$37_4])) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													int traceTempVariable$var49$41_1 = 0;
													for(int index$i$41_2 = 1; index$i$41_2 < n; index$i$41_2 += 1) {
														if((0 == (index$i$41_2 - 1))) {
															int traceTempVariable$var51$41_3 = traceTempVariable$var49$41_1;
															if((index$i$41_2 == i$var46)) {
																for(int index$var28$42_1 = 0; index$var28$42_1 < states; index$var28$42_1 += 1) {
																	if((index$var28$42_1 == b[i$var46]))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
													}
													int traceTempVariable$var49$43_1 = cv$currentValue;
													for(int index$i$43_2 = 1; index$i$43_2 < n; index$i$43_2 += 1) {
														if((index$i$14 == (index$i$43_2 - 1))) {
															int traceTempVariable$var51$43_3 = traceTempVariable$var49$43_1;
															if((index$i$43_2 == i$var46)) {
																for(int index$var28$49_1 = 0; index$var28$49_1 < states; index$var28$49_1 += 1) {
																	if((index$var28$49_1 == b[i$var46]))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
													}
													for(int index$i$44 = 1; index$i$44 < n; index$i$44 += 1) {
														if((!(index$i$44 == index$i$14) && !(index$i$44 == index$i$39))) {
															for(int index$sample55$45 = 0; index$sample55$45 < states; index$sample55$45 += 1) {
																int distributionTempVariable$var54$47 = index$sample55$45;
																double cv$probabilitySample55Value46 = (1.0 * distribution$sample55[((index$i$44 - 1) / 1)][index$sample55$45]);
																int traceTempVariable$var49$48_1 = cv$currentValue;
																for(int index$i$48_2 = 1; index$i$48_2 < n; index$i$48_2 += 1) {
																	if((index$i$44 == (index$i$48_2 - 1))) {
																		int traceTempVariable$var51$48_3 = traceTempVariable$var49$48_1;
																		if((index$i$48_2 == i$var46)) {
																			for(int index$var28$50_1 = 0; index$var28$50_1 < states; index$var28$50_1 += 1) {
																				if((index$var28$50_1 == b[i$var46]))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample55Value46);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$9$var52;
													{
														double[] var52 = m[traceTempVariable$var51$37_3];
														cv$temp$9$var52 = var52;
													}
													int cv$temp$10$$var887;
													{
														int $var887 = states;
														cv$temp$10$$var887 = $var887;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$9$var52, cv$temp$10$$var887);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample55[((index$i$37_4 - 1) / 1)];
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
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double[] cv$localProbability = distribution$sample55[((i$var46 - 1) / 1)];
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
				cv$var29$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var29$countGlobal[cv$index] = new double[5];
			}
		}
		{
			int cv$var30$max = 5;
			cv$distributionAccumulator$var53 = new double[cv$var30$max];
		}
		{
			int cv$var30$max = 5;
			cv$var54$stateProbabilityGlobal = new double[cv$var30$max];
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
				for(int var28 = 0; var28 < 5; var28 += 1)
					m[var28] = new double[5];
			}
		}
		if(!setFlag$a) {
			{
				a = new int[n];
			}
		}
		{
			b = new int[n];
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[n];
			}
		}
		{
			distribution$sample55 = new double[((((n - 1) - 1) / 1) + 1)][];
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				distribution$sample55[((i$var46 - 1) / 1)] = new double[5];
		}
		{
			logProbability$var53 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample55 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var73 = new double[((((n - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample75 = new double[((((n - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						double[] var29 = m[var28];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var29);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
		parallelFor(RNG$, 0, n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1) {
						if(!fixedFlag$sample75)
							flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / a[(j + 1)]));
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						double[] var29 = m[var28];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var29);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			double[] cv$distribution$sample55 = distribution$sample55[((i$var46 - 1) / 1)];
			for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
				if(!fixedFlag$sample55)
					cv$distribution$sample55[index$var53] = 0.0;
			}
			int traceTempVariable$var49$1_1 = 0;
			for(int index$i$1_2 = 1; index$i$1_2 < n; index$i$1_2 += 1) {
				if((0 == (index$i$1_2 - 1))) {
					int traceTempVariable$var51$1_3 = traceTempVariable$var49$1_1;
					if((index$i$1_2 == i$var46)) {
						for(int var28 = 0; var28 < states; var28 += 1) {
							if((var28 == b[i$var46])) {
								{
									if(!fixedFlag$sample55) {
										double[] var52 = m[traceTempVariable$var51$1_3];
										for(int index$var53 = 0; index$var53 < states; index$var53 += 1)
											cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (1.0 * (((0.0 <= index$var53) && (index$var53 < states))?var52[index$var53]:0.0)));
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample55) {
				for(int index$i$3_1 = 1; index$i$3_1 < n; index$i$3_1 += 1) {
					for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
						if((index$i$3_1 == (index$i$3_2 - 1))) {
							int traceTempVariable$var51$3_3 = a[(index$i$3_2 - 1)];
							if((index$i$3_2 == i$var46)) {
								for(int var28 = 0; var28 < states; var28 += 1) {
									if((var28 == b[i$var46])) {
										{
											if(!fixedFlag$sample55) {
												double[] var52 = m[traceTempVariable$var51$3_3];
												for(int index$var53 = 0; index$var53 < states; index$var53 += 1)
													cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (1.0 * (((0.0 <= index$var53) && (index$var53 < states))?var52[index$var53]:0.0)));
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				for(int index$i$4 = 1; index$i$4 < n; index$i$4 += 1) {
					if(true) {
						for(int index$sample55$5 = 0; index$sample55$5 < states; index$sample55$5 += 1) {
							int distributionTempVariable$var54$7 = index$sample55$5;
							double cv$probabilitySample55Value6 = (1.0 * distribution$sample55[((index$i$4 - 1) / 1)][index$sample55$5]);
							for(int index$i$8_1 = 1; index$i$8_1 < n; index$i$8_1 += 1) {
								if((index$i$4 == (index$i$8_1 - 1))) {
									int traceTempVariable$var51$8_2 = a[(index$i$8_1 - 1)];
									if((index$i$8_1 == i$var46)) {
										for(int var28 = 0; var28 < states; var28 += 1) {
											if((var28 == b[i$var46])) {
												{
													if(!fixedFlag$sample55) {
														double[] var52 = m[traceTempVariable$var51$8_2];
														for(int index$var53 = 0; index$var53 < states; index$var53 += 1)
															cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (cv$probabilitySample55Value6 * (((0.0 <= index$var53) && (index$var53 < states))?var52[index$var53]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var53$sum = 0.0;
			for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
				if(!fixedFlag$sample55)
					cv$var53$sum = (cv$var53$sum + cv$distribution$sample55[index$var53]);
			}
			for(int index$var53 = 0; index$var53 < states; index$var53 += 1) {
				if(!fixedFlag$sample55)
					cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] / cv$var53$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						double[] var29 = m[var28];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var29);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
							if(!fixedFlag$sample29)
								sample29(var28, threadID$var28, RNG$1);
						}
				}
			);
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
				if(!fixedFlag$sample55)
					sample55(i$var46);
			}
		} else {
			for(int i$var46 = (n - ((((n - 1) - 1) % 1) + 1)); i$var46 >= ((1 - 1) + 1); i$var46 -= 1) {
				if(!fixedFlag$sample55)
					sample55(i$var46);
			}
			parallelFor(RNG$, 0, states, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
							if(!fixedFlag$sample29)
								sample29(var28, threadID$var28, RNG$1);
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
			(int forStart$i$var14, int forEnd$i$var14, int threadID$i$var14, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var14 = forStart$i$var14; i$var14 < forEnd$i$var14; i$var14 += 1)
						v[i$var14] = 0.1;
			}
		);
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var17 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample29)
			logProbability$var29 = 0.0;
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
			logProbability$var53[((i$var46 - 1) / 1)] = 0.0;
		logProbability$b = 0.0;
		logProbability$a = 0.0;
		if(!fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < n; i$var46 += 1)
				logProbability$sample55[((i$var46 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var73[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample75) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample75[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityDistribution$sample55();
		logProbabilityDistribution$sample75();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 0, states, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						double[] var29 = m[var28];
						if(!fixedFlag$sample29)
							DistributionSampling.sampleDirichlet(RNG$1, v, states, var29);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(!fixedFlag$sample55)
				b[i$var46] = a[(i$var46 - 1)];
			if(!fixedFlag$sample55)
				a[i$var46] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var46]], states);
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
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < n; i$var46 += 1) {
			if(setFlag$a)
				b[i$var46] = a[(i$var46 - 1)];
		}
	}

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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}