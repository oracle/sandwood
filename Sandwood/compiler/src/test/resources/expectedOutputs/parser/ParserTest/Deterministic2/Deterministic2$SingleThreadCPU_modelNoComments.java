package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Deterministic2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Deterministic2$CoreInterface {
	private int[] a;
	private int[] b;
	private double[] cv$distributionAccumulator$var33;
	private double[] cv$var17$countGlobal;
	private double[] cv$var34$stateProbabilityGlobal;
	private double[][] distribution$sample36;
	private boolean fixedFlag$sample18 = false;
	private boolean fixedFlag$sample36 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample18 = false;
	private boolean fixedProbFlag$sample36 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$a;
	private double logProbability$b;
	private double logProbability$flips;
	private double logProbability$m;
	private double[] logProbability$sample36;
	private double[] logProbability$sample49;
	private double logProbability$var12;
	private double logProbability$var17;
	private double[] logProbability$var33;
	private double[] logProbability$var46;
	private double[][] m;
	private int n;
	private boolean setFlag$a = false;
	private boolean setFlag$flips = false;
	private boolean setFlag$m = false;
	private int states;
	private boolean system$gibbsForward = true;
	private double[] v;

	public Deterministic2$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample36 = false;
		fixedProbFlag$sample49 = false;
	}

	@Override
	public final int[] get$b() {
		return b;
	}

	@Override
	public final boolean get$fixedFlag$sample18() {
		return fixedFlag$sample18;
	}

	@Override
	public final void set$fixedFlag$sample18(boolean cv$value) {
		fixedFlag$sample18 = cv$value;
		fixedProbFlag$sample18 = (fixedFlag$sample18 && fixedProbFlag$sample18);
		fixedProbFlag$sample36 = (fixedFlag$sample18 && fixedProbFlag$sample36);
	}

	@Override
	public final boolean get$fixedFlag$sample36() {
		return fixedFlag$sample36;
	}

	@Override
	public final void set$fixedFlag$sample36(boolean cv$value) {
		fixedFlag$sample36 = cv$value;
		fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedProbFlag$sample36);
		fixedProbFlag$sample49 = (fixedFlag$sample36 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample49 = false;
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
		fixedProbFlag$sample18 = false;
		fixedProbFlag$sample36 = false;
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

	private final void logProbabilityDistribution$sample36() {
		if(!fixedProbFlag$sample36) {
			if(fixedFlag$sample36) {
				double cv$accumulator = 0.0;
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i$var26;
					{
						int cv$sampleValue = a[i$var26];
						int traceTempVariable$var29$3_1 = 0;
						for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
							if((0 == (index$i$3_2 - 1))) {
								int traceTempVariable$var31$3_3 = traceTempVariable$var29$3_1;
								if((index$i$3_2 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == traceTempVariable$var31$3_3)) {
											{
												double[] var32 = m[traceTempVariable$var31$3_3];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
								int traceTempVariable$var31$5_2 = a[(index$i$5_1 - 1)];
								if((index$i$5_1 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == traceTempVariable$var31$5_2)) {
											{
												double[] var32 = m[traceTempVariable$var31$5_2];
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						if(fixedFlag$sample36) {
							for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
								for(int index$i$6_2 = 1; index$i$6_2 < n; index$i$6_2 += 1) {
									if((index$i$6_1 == (index$i$6_2 - 1))) {
										int traceTempVariable$var31$6_3 = a[(index$i$6_2 - 1)];
										if((index$i$6_2 == i$var26)) {
											for(int var16 = 0; var16 < states; var16 += 1) {
												if((var16 == traceTempVariable$var31$6_3)) {
													{
														double[] var32 = m[traceTempVariable$var31$6_3];
														double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
									for(int index$sample36$8 = 0; index$sample36$8 < states; index$sample36$8 += 1) {
										int distributionTempVariable$var34$10 = index$sample36$8;
										double cv$probabilitySample36Value9 = (1.0 * distribution$sample36[((index$i$7 - 1) / 1)][index$sample36$8]);
										int traceTempVariable$var29$11_1 = distributionTempVariable$var34$10;
										for(int index$i$11_2 = 1; index$i$11_2 < n; index$i$11_2 += 1) {
											if((index$i$7 == (index$i$11_2 - 1))) {
												int traceTempVariable$var31$11_3 = traceTempVariable$var29$11_1;
												if((index$i$11_2 == i$var26)) {
													for(int var16 = 0; var16 < states; var16 += 1) {
														if((var16 == traceTempVariable$var31$11_3)) {
															{
																double[] var32 = m[traceTempVariable$var31$11_3];
																double cv$weightedProbability = (Math.log(cv$probabilitySample36Value9) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample36Value9);
															}
														}
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
					logProbability$var33[((i$var26 - 1) / 1)] = cv$sampleAccumulator;
					logProbability$sample36[((i$var26 - 1) / 1)] = cv$sampleProbability;
					boolean cv$guard$b = false;
					int index$i$15 = i$var26;
					{
						for(int index$i$16_1 = 1; index$i$16_1 < n; index$i$16_1 += 1) {
							if((i$var26 == (index$i$16_1 - 1))) {
								if(fixedFlag$sample36) {
									if(!cv$guard$b) {
										cv$guard$b = true;
										logProbability$b = (logProbability$b + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				if(fixedFlag$sample36)
					logProbability$a = (logProbability$a + cv$accumulator);
				logProbability$$model = (logProbability$$model + cv$accumulator);
				if(fixedFlag$sample36)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample18);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample36[((i$var26 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var33[((i$var26 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$b = false;
				int index$i$17 = i$var26;
				{
					for(int index$i$18_1 = 1; index$i$18_1 < n; index$i$18_1 += 1) {
						if((i$var26 == (index$i$18_1 - 1))) {
							if(fixedFlag$sample36) {
								if(!cv$guard$b) {
									cv$guard$b = true;
									logProbability$b = (logProbability$b + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample36)
				logProbability$a = (logProbability$a + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					int traceTempVariable$var43$2_1 = 0;
					if((0 == (j + 1))) {
						{
							double var45 = (double)(1 / traceTempVariable$var43$2_1);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
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
					if(fixedFlag$sample36) {
						for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
							if((i$var26 == (j + 1))) {
								{
									double var45 = (double)(1 / a[(j + 1)]);
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
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
						for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
							if(true) {
								for(int index$sample36$5 = 0; index$sample36$5 < states; index$sample36$5 += 1) {
									int distributionTempVariable$var34$7 = index$sample36$5;
									double cv$probabilitySample36Value6 = (1.0 * distribution$sample36[((i$var26 - 1) / 1)][index$sample36$5]);
									int traceTempVariable$var43$8_1 = distributionTempVariable$var34$7;
									if((i$var26 == (j + 1))) {
										{
											double var45 = (double)(1 / traceTempVariable$var43$8_1);
											double cv$weightedProbability = (Math.log(cv$probabilitySample36Value6) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
											if((cv$weightedProbability < cv$distributionAccumulator))
												cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
											else {
												if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
													cv$distributionAccumulator = cv$weightedProbability;
												else
													cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
											}
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample36Value6);
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
				logProbability$var46[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample49[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample49[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample18() {
		if(!fixedProbFlag$sample18) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var16 = 0; var16 < states; var16 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double[] cv$sampleValue = m[var16];
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
			logProbability$var12 = cv$sampleAccumulator;
			logProbability$var17 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample18 = fixedFlag$sample18;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var17;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var12 = cv$rvAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample18)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample36() {
		if(!fixedProbFlag$sample36) {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i$var26;
				{
					int cv$sampleValue = a[i$var26];
					{
						{
							double[] var32 = m[b[i$var26]];
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var32.length))?Math.log(var32[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$var33[((i$var26 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample36[((i$var26 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$b = false;
				int index$i$3 = i$var26;
				{
					for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
						if((i$var26 == (index$i$4_1 - 1))) {
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
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample36 = (fixedFlag$sample36 && fixedFlag$sample18);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample36[((i$var26 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var33[((i$var26 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$b = false;
				int index$i$5 = i$var26;
				{
					for(int index$i$6_1 = 1; index$i$6_1 < n; index$i$6_1 += 1) {
						if((i$var26 == (index$i$6_1 - 1))) {
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
			if(fixedFlag$sample36)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[j];
					{
						{
							double var45 = (double)(1 / a[(j + 1)]);
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var45));
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
				logProbability$var46[((j - 0) / 1)] = cv$sampleAccumulator;
				logProbability$sample49[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample36);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < n; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample49[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var46[((j - 0) / 1)] = cv$rvAccumulator;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample18(int var16) {
		double[] cv$targetLocal = m[var16];
		double[] cv$countLocal = cv$var17$countGlobal;
		int cv$arrayLength = states;
		for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		{
			{
				{
					for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
						int traceTempVariable$var29$2_1 = 0;
						for(int index$i$2_2 = 1; index$i$2_2 < n; index$i$2_2 += 1) {
							if((0 == (index$i$2_2 - 1))) {
								int traceTempVariable$var31$2_3 = traceTempVariable$var29$2_1;
								if((index$i$2_2 == i$var26)) {
									if((var16 == traceTempVariable$var31$2_3)) {
										if(fixedFlag$sample36) {
											{
												int index$i$14 = i$var26;
												{
													{
														{
															{
																cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + 1.0);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
						if(fixedFlag$sample36) {
							for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
								for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
									if((index$i$5_1 == (index$i$5_2 - 1))) {
										int traceTempVariable$var31$5_3 = a[(index$i$5_2 - 1)];
										if((index$i$5_2 == i$var26)) {
											if((var16 == traceTempVariable$var31$5_3)) {
												if(fixedFlag$sample36) {
													{
														int index$i$16 = i$var26;
														{
															{
																{
																	{
																		cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + 1.0);
																	}
																}
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
									for(int index$sample36$7 = 0; index$sample36$7 < states; index$sample36$7 += 1) {
										int distributionTempVariable$var34$9 = index$sample36$7;
										double cv$probabilitySample36Value8 = (1.0 * distribution$sample36[((index$i$6 - 1) / 1)][index$sample36$7]);
										int traceTempVariable$var29$10_1 = distributionTempVariable$var34$9;
										for(int index$i$10_2 = 1; index$i$10_2 < n; index$i$10_2 += 1) {
											if((index$i$6 == (index$i$10_2 - 1))) {
												int traceTempVariable$var31$10_3 = traceTempVariable$var29$10_1;
												if((index$i$10_2 == i$var26)) {
													if((var16 == traceTempVariable$var31$10_3)) {
														if(fixedFlag$sample36) {
															{
																int index$i$18 = i$var26;
																{
																	{
																		{
																			{
																				cv$countLocal[a[i$var26]] = (cv$countLocal[a[i$var26]] + cv$probabilitySample36Value8);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
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
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					int traceTempVariable$var29$23_1 = 0;
					for(int index$i$23_2 = 1; index$i$23_2 < n; index$i$23_2 += 1) {
						if((0 == (index$i$23_2 - 1))) {
							int traceTempVariable$var31$23_3 = traceTempVariable$var29$23_1;
							if((index$i$23_2 == i$var26)) {
								if((var16 == traceTempVariable$var31$23_3)) {
									if(!fixedFlag$sample36) {
										{
											int index$i$35 = i$var26;
											{
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													{
														scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
														cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[((i$var26 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
												}
											}
										}
									}
								}
							}
						}
					}
				}
				for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
					if(fixedFlag$sample36) {
						for(int index$i$26_1 = 1; index$i$26_1 < n; index$i$26_1 += 1) {
							for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
								if((index$i$26_1 == (index$i$26_2 - 1))) {
									int traceTempVariable$var31$26_3 = a[(index$i$26_2 - 1)];
									if((index$i$26_2 == i$var26)) {
										if((var16 == traceTempVariable$var31$26_3)) {
											if(!fixedFlag$sample36) {
												{
													int index$i$37 = i$var26;
													{
														{
															double scopeVariable$reachedSourceProbability = 0.0;
															{
																scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
															}
															double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
															for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[((i$var26 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
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
								for(int index$sample36$28 = 0; index$sample36$28 < states; index$sample36$28 += 1) {
									int distributionTempVariable$var34$30 = index$sample36$28;
									double cv$probabilitySample36Value29 = (1.0 * distribution$sample36[((index$i$27 - 1) / 1)][index$sample36$28]);
									int traceTempVariable$var29$31_1 = distributionTempVariable$var34$30;
									for(int index$i$31_2 = 1; index$i$31_2 < n; index$i$31_2 += 1) {
										if((index$i$27 == (index$i$31_2 - 1))) {
											int traceTempVariable$var31$31_3 = traceTempVariable$var29$31_1;
											if((index$i$31_2 == i$var26)) {
												if((var16 == traceTempVariable$var31$31_3)) {
													if(!fixedFlag$sample36) {
														{
															int index$i$39 = i$var26;
															{
																{
																	double scopeVariable$reachedSourceProbability = 0.0;
																	{
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																	}
																	double cv$distributionProbability = (scopeVariable$reachedSourceProbability * cv$probabilitySample36Value29);
																	for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
																		cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (distribution$sample36[((i$var26 - 1) / 1)][cv$loopIndex] * cv$distributionProbability));
																}
															}
														}
													}
												}
											}
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

	private final void sample36(int i$var26) {
		int cv$noStates = 0;
		int index$i$1 = i$var26;
		int traceTempVariable$var29$2_1 = 0;
		for(int index$i$2_2 = 1; index$i$2_2 < n; index$i$2_2 += 1) {
			if((0 == (index$i$2_2 - 1))) {
				int traceTempVariable$var31$2_3 = traceTempVariable$var29$2_1;
				if((index$i$2_2 == i$var26)) {
					for(int var16 = 0; var16 < states; var16 += 1) {
						if((var16 == traceTempVariable$var31$2_3))
							cv$noStates = Math.max(cv$noStates, states);
					}
				}
			}
		}
		for(int index$i$4_1 = 1; index$i$4_1 < n; index$i$4_1 += 1) {
			if((index$i$1 == (index$i$4_1 - 1))) {
				int traceTempVariable$var31$4_2 = a[(index$i$4_1 - 1)];
				if((index$i$4_1 == i$var26)) {
					for(int var16 = 0; var16 < states; var16 += 1) {
						if((var16 == traceTempVariable$var31$4_2))
							cv$noStates = Math.max(cv$noStates, states);
					}
				}
			}
		}
		if(fixedFlag$sample36) {
			for(int index$i$5_1 = 1; index$i$5_1 < n; index$i$5_1 += 1) {
				for(int index$i$5_2 = 1; index$i$5_2 < n; index$i$5_2 += 1) {
					if((index$i$5_1 == (index$i$5_2 - 1))) {
						int traceTempVariable$var31$5_3 = a[(index$i$5_2 - 1)];
						if((index$i$5_2 == i$var26)) {
							for(int var16 = 0; var16 < states; var16 += 1) {
								if((var16 == traceTempVariable$var31$5_3))
									cv$noStates = Math.max(cv$noStates, states);
							}
						}
					}
				}
			}
		} else {
			for(int index$i$6 = 1; index$i$6 < n; index$i$6 += 1) {
				if(!(index$i$6 == index$i$1)) {
					for(int index$sample36$7 = 0; index$sample36$7 < states; index$sample36$7 += 1) {
						int distributionTempVariable$var34$9 = index$sample36$7;
						double cv$probabilitySample36Value8 = (1.0 * distribution$sample36[((index$i$6 - 1) / 1)][index$sample36$7]);
						int traceTempVariable$var29$10_1 = distributionTempVariable$var34$9;
						for(int index$i$10_2 = 1; index$i$10_2 < n; index$i$10_2 += 1) {
							if((index$i$6 == (index$i$10_2 - 1))) {
								int traceTempVariable$var31$10_3 = traceTempVariable$var29$10_1;
								if((index$i$10_2 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == traceTempVariable$var31$10_3))
											cv$noStates = Math.max(cv$noStates, states);
									}
								}
							}
						}
					}
				}
			}
		}
		double[] cv$stateProbabilityLocal = cv$var34$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			int index$i$14 = i$var26;
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			int cv$currentValue;
			cv$currentValue = cv$valuePos;
			int traceTempVariable$var29$15_1 = 0;
			for(int index$i$15_2 = 1; index$i$15_2 < n; index$i$15_2 += 1) {
				if((0 == (index$i$15_2 - 1))) {
					int traceTempVariable$var31$15_3 = traceTempVariable$var29$15_1;
					if((index$i$15_2 == i$var26)) {
						for(int var16 = 0; var16 < states; var16 += 1) {
							if((var16 == traceTempVariable$var31$15_3)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$0$var32;
								{
									double[] var32 = m[traceTempVariable$var31$15_3];
									cv$temp$0$var32 = var32;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var32.length))?Math.log(cv$temp$0$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var29$25_1 = cv$currentValue;
										for(int index$i$25_2 = 1; index$i$25_2 < n; index$i$25_2 += 1) {
											if((i$var26 == (index$i$25_2 - 1))) {
												int traceTempVariable$var31$25_3 = traceTempVariable$var29$25_1;
											}
										}
									}
								}
								{
									{
										int traceTempVariable$var43$28_1 = cv$currentValue;
										for(int j = 0; j < n; j += 1) {
											if((i$var26 == (j + 1))) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$3$var45;
																	{
																		double var45 = (double)(1 / traceTempVariable$var43$28_1);
																		cv$temp$3$var45 = var45;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$3$var45)));
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
			int traceTempVariable$var29$17_1 = cv$currentValue;
			for(int index$i$17_2 = 1; index$i$17_2 < n; index$i$17_2 += 1) {
				if((index$i$14 == (index$i$17_2 - 1))) {
					int traceTempVariable$var31$17_3 = traceTempVariable$var29$17_1;
					if((index$i$17_2 == i$var26)) {
						for(int var16 = 0; var16 < states; var16 += 1) {
							if((var16 == traceTempVariable$var31$17_3)) {
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								double[] cv$temp$1$var32;
								{
									double[] var32 = m[traceTempVariable$var31$17_3];
									cv$temp$1$var32 = var32;
								}
								double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var32.length))?Math.log(cv$temp$1$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
								{
									{
										int traceTempVariable$var29$26_1 = cv$currentValue;
										for(int index$i$26_2 = 1; index$i$26_2 < n; index$i$26_2 += 1) {
											if((i$var26 == (index$i$26_2 - 1))) {
												int traceTempVariable$var31$26_3 = traceTempVariable$var29$26_1;
											}
										}
									}
								}
								{
									{
										int traceTempVariable$var43$29_1 = cv$currentValue;
										for(int j = 0; j < n; j += 1) {
											if((i$var26 == (j + 1))) {
												{
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double cv$temp$4$var45;
																	{
																		double var45 = (double)(1 / traceTempVariable$var43$29_1);
																		cv$temp$4$var45 = var45;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$4$var45)));
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
					for(int index$sample36$19 = 0; index$sample36$19 < states; index$sample36$19 += 1) {
						int distributionTempVariable$var34$21 = index$sample36$19;
						double cv$probabilitySample36Value20 = (1.0 * distribution$sample36[((index$i$18 - 1) / 1)][index$sample36$19]);
						int traceTempVariable$var29$22_1 = distributionTempVariable$var34$21;
						for(int index$i$22_2 = 1; index$i$22_2 < n; index$i$22_2 += 1) {
							if((index$i$18 == (index$i$22_2 - 1))) {
								int traceTempVariable$var31$22_3 = traceTempVariable$var29$22_1;
								if((index$i$22_2 == i$var26)) {
									for(int var16 = 0; var16 < states; var16 += 1) {
										if((var16 == traceTempVariable$var31$22_3)) {
											cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample36Value20);
											double[] cv$temp$2$var32;
											{
												double[] var32 = m[traceTempVariable$var31$22_3];
												cv$temp$2$var32 = var32;
											}
											double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample36Value20) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var32.length))?Math.log(cv$temp$2$var32[cv$currentValue]):Double.NEGATIVE_INFINITY));
											{
												{
													int traceTempVariable$var29$27_1 = cv$currentValue;
													for(int index$i$27_2 = 1; index$i$27_2 < n; index$i$27_2 += 1) {
														if((i$var26 == (index$i$27_2 - 1))) {
															int traceTempVariable$var31$27_3 = traceTempVariable$var29$27_1;
														}
													}
												}
											}
											{
												{
													int traceTempVariable$var43$30_1 = cv$currentValue;
													for(int j = 0; j < n; j += 1) {
														if((i$var26 == (j + 1))) {
															{
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				double cv$temp$5$var45;
																				{
																					double var45 = (double)(1 / traceTempVariable$var43$30_1);
																					cv$temp$5$var45 = var45;
																				}
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[j], cv$temp$5$var45)));
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
					int traceTempVariable$var29$37_1 = cv$currentValue;
					for(int index$i$37_2 = 1; index$i$37_2 < n; index$i$37_2 += 1) {
						if((i$var26 == (index$i$37_2 - 1))) {
							int traceTempVariable$var31$37_3 = traceTempVariable$var29$37_1;
							for(int index$i$37_4 = 1; index$i$37_4 < n; index$i$37_4 += 1) {
								if((index$i$37_2 == index$i$37_4)) {
									{
										int index$i$39 = index$i$37_4;
										double[] cv$accumulatedConsumerDistributions = cv$distributionAccumulator$var33;
										for(int cv$i = 0; cv$i < states; cv$i += 1)
											cv$accumulatedConsumerDistributions[cv$i] = 0.0;
										double cv$reachedDistributionProbability = 0.0;
										for(int var16 = 0; var16 < states; var16 += 1) {
											if((var16 == traceTempVariable$var31$37_3)) {
												{
													double scopeVariable$reachedSourceProbability = 0.0;
													int traceTempVariable$var29$41_1 = 0;
													for(int index$i$41_2 = 1; index$i$41_2 < n; index$i$41_2 += 1) {
														if((0 == (index$i$41_2 - 1))) {
															int traceTempVariable$var31$41_3 = traceTempVariable$var29$41_1;
															if((index$i$41_2 == i$var26)) {
																for(int index$var16$42_1 = 0; index$var16$42_1 < states; index$var16$42_1 += 1) {
																	if((index$var16$42_1 == traceTempVariable$var31$41_3))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
													}
													int traceTempVariable$var29$43_1 = cv$currentValue;
													for(int index$i$43_2 = 1; index$i$43_2 < n; index$i$43_2 += 1) {
														if((index$i$14 == (index$i$43_2 - 1))) {
															int traceTempVariable$var31$43_3 = traceTempVariable$var29$43_1;
															if((index$i$43_2 == i$var26)) {
																for(int index$var16$49_1 = 0; index$var16$49_1 < states; index$var16$49_1 += 1) {
																	if((index$var16$49_1 == traceTempVariable$var31$43_3))
																		scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + 1.0);
																}
															}
														}
													}
													for(int index$i$44 = 1; index$i$44 < n; index$i$44 += 1) {
														if((!(index$i$44 == index$i$14) && !(index$i$44 == index$i$39))) {
															for(int index$sample36$45 = 0; index$sample36$45 < states; index$sample36$45 += 1) {
																int distributionTempVariable$var34$47 = index$sample36$45;
																double cv$probabilitySample36Value46 = (1.0 * distribution$sample36[((index$i$44 - 1) / 1)][index$sample36$45]);
																int traceTempVariable$var29$48_1 = distributionTempVariable$var34$47;
																for(int index$i$48_2 = 1; index$i$48_2 < n; index$i$48_2 += 1) {
																	if((index$i$44 == (index$i$48_2 - 1))) {
																		int traceTempVariable$var31$48_3 = traceTempVariable$var29$48_1;
																		if((index$i$48_2 == i$var26)) {
																			for(int index$var16$50_1 = 0; index$var16$50_1 < states; index$var16$50_1 += 1) {
																				if((index$var16$50_1 == traceTempVariable$var31$48_3))
																					scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + cv$probabilitySample36Value46);
																			}
																		}
																	}
																}
															}
														}
													}
													double[] cv$temp$6$var32;
													{
														double[] var32 = m[traceTempVariable$var31$37_3];
														cv$temp$6$var32 = var32;
													}
													double cv$distributionProbability = (scopeVariable$reachedSourceProbability * 1.0);
													cv$reachedDistributionProbability = (cv$reachedDistributionProbability + cv$distributionProbability);
													DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, cv$distributionProbability, cv$temp$6$var32);
												}
											}
										}
										double[] cv$sampleDistribution = distribution$sample36[((index$i$37_4 - 1) / 1)];
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
		double[] cv$localProbability = distribution$sample36[((i$var26 - 1) / 1)];
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
			for(int var16 = 0; var16 < 5; var16 += 1)
				cv$max = Math.max(cv$max, 5);
			cv$var17$countGlobal = new double[cv$max];
		}
		{
			int cv$var18$max = 5;
			cv$distributionAccumulator$var33 = new double[cv$var18$max];
		}
		{
			int cv$var18$max = 5;
			cv$var34$stateProbabilityGlobal = new double[cv$var18$max];
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
				for(int var16 = 0; var16 < 5; var16 += 1)
					m[var16] = new double[5];
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
			distribution$sample36 = new double[((((n - 1) - 1) / 1) + 1)][];
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				distribution$sample36[((i$var26 - 1) / 1)] = new double[5];
		}
		{
			logProbability$var33 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample36 = new double[((((n - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$var46 = new double[((((n - 1) - 0) / 1) + 1)];
		}
		{
			logProbability$sample49 = new double[((((n - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		for(int var16 = 0; var16 < states; var16 += 1) {
			double[] var17 = m[var16];
			if(!fixedFlag$sample18)
				DistributionSampling.sampleDirichlet(RNG$, v, var17);
		}
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(!fixedFlag$sample36)
				b[i$var26] = a[(i$var26 - 1)];
			if(!fixedFlag$sample36)
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
		}
		for(int j = 0; j < n; j += 1) {
			if(!fixedFlag$sample49)
				flips[j] = DistributionSampling.sampleBernoulli(RNG$, (1 / a[(j + 1)]));
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		for(int var16 = 0; var16 < states; var16 += 1) {
			double[] var17 = m[var16];
			if(!fixedFlag$sample18)
				DistributionSampling.sampleDirichlet(RNG$, v, var17);
		}
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			double[] cv$distribution$sample36 = distribution$sample36[((i$var26 - 1) / 1)];
			for(int index$var33 = 0; index$var33 < states; index$var33 += 1) {
				if(!fixedFlag$sample36)
					cv$distribution$sample36[index$var33] = 0.0;
			}
			int traceTempVariable$var29$1_1 = 0;
			for(int index$i$1_2 = 1; index$i$1_2 < n; index$i$1_2 += 1) {
				if((0 == (index$i$1_2 - 1))) {
					int traceTempVariable$var31$1_3 = traceTempVariable$var29$1_1;
					if((index$i$1_2 == i$var26)) {
						for(int var16 = 0; var16 < states; var16 += 1) {
							if((var16 == traceTempVariable$var31$1_3)) {
								{
									if(!fixedFlag$sample36) {
										double[] var32 = m[traceTempVariable$var31$1_3];
										for(int index$var33 = 0; index$var33 < states; index$var33 += 1)
											cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] + (1.0 * (((0.0 <= index$var33) && (index$var33 < var32.length))?var32[index$var33]:0.0)));
									}
								}
							}
						}
					}
				}
			}
			if(fixedFlag$sample36) {
				for(int index$i$3_1 = 1; index$i$3_1 < n; index$i$3_1 += 1) {
					for(int index$i$3_2 = 1; index$i$3_2 < n; index$i$3_2 += 1) {
						if((index$i$3_1 == (index$i$3_2 - 1))) {
							int traceTempVariable$var31$3_3 = a[(index$i$3_2 - 1)];
							if((index$i$3_2 == i$var26)) {
								for(int var16 = 0; var16 < states; var16 += 1) {
									if((var16 == traceTempVariable$var31$3_3)) {
										{
											if(!fixedFlag$sample36) {
												double[] var32 = m[traceTempVariable$var31$3_3];
												for(int index$var33 = 0; index$var33 < states; index$var33 += 1)
													cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] + (1.0 * (((0.0 <= index$var33) && (index$var33 < var32.length))?var32[index$var33]:0.0)));
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
						for(int index$sample36$5 = 0; index$sample36$5 < states; index$sample36$5 += 1) {
							int distributionTempVariable$var34$7 = index$sample36$5;
							double cv$probabilitySample36Value6 = (1.0 * distribution$sample36[((index$i$4 - 1) / 1)][index$sample36$5]);
							int traceTempVariable$var29$8_1 = distributionTempVariable$var34$7;
							for(int index$i$8_2 = 1; index$i$8_2 < n; index$i$8_2 += 1) {
								if((index$i$4 == (index$i$8_2 - 1))) {
									int traceTempVariable$var31$8_3 = traceTempVariable$var29$8_1;
									if((index$i$8_2 == i$var26)) {
										for(int var16 = 0; var16 < states; var16 += 1) {
											if((var16 == traceTempVariable$var31$8_3)) {
												{
													if(!fixedFlag$sample36) {
														double[] var32 = m[traceTempVariable$var31$8_3];
														for(int index$var33 = 0; index$var33 < states; index$var33 += 1)
															cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] + (cv$probabilitySample36Value6 * (((0.0 <= index$var33) && (index$var33 < var32.length))?var32[index$var33]:0.0)));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			double cv$var33$sum = 0.0;
			for(int index$var33 = 0; index$var33 < states; index$var33 += 1) {
				if(!fixedFlag$sample36)
					cv$var33$sum = (cv$var33$sum + cv$distribution$sample36[index$var33]);
			}
			for(int index$var33 = 0; index$var33 < states; index$var33 += 1) {
				if(!fixedFlag$sample36)
					cv$distribution$sample36[index$var33] = (cv$distribution$sample36[index$var33] / cv$var33$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var16 = 0; var16 < states; var16 += 1) {
			double[] var17 = m[var16];
			if(!fixedFlag$sample18)
				DistributionSampling.sampleDirichlet(RNG$, v, var17);
		}
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(!fixedFlag$sample36)
				b[i$var26] = a[(i$var26 - 1)];
			if(!fixedFlag$sample36)
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var16 = 0; var16 < states; var16 += 1) {
				if(!fixedFlag$sample18)
					sample18(var16);
			}
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
				if(!fixedFlag$sample36)
					sample36(i$var26);
			}
		} else {
			for(int i$var26 = (n - ((((n - 1) - 1) % 1) + 1)); i$var26 >= ((1 - 1) + 1); i$var26 -= 1) {
				if(!fixedFlag$sample36)
					sample36(i$var26);
			}
			for(int var16 = (states - ((((states - 1) - 0) % 1) + 1)); var16 >= ((0 - 1) + 1); var16 -= 1) {
				if(!fixedFlag$sample18)
					sample18(var16);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		states = 5;
		for(int i$var9 = 0; i$var9 < 5; i$var9 += 1)
			v[i$var9] = 0.1;
		a[0] = 0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var12 = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample18)
			logProbability$var17 = 0.0;
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
			logProbability$var33[((i$var26 - 1) / 1)] = 0.0;
		logProbability$a = 0.0;
		logProbability$b = 0.0;
		if(!fixedProbFlag$sample36) {
			for(int i$var26 = 1; i$var26 < n; i$var26 += 1)
				logProbability$sample36[((i$var26 - 1) / 1)] = 0.0;
		}
		for(int j = 0; j < n; j += 1)
			logProbability$var46[((j - 0) / 1)] = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample49) {
			for(int j = 0; j < n; j += 1)
				logProbability$sample49[((j - 0) / 1)] = 0.0;
		}
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample18)
			logProbabilityValue$sample18();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityDistribution$sample36();
		logProbabilityDistribution$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample18();
		logProbabilityValue$sample36();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		for(int var16 = 0; var16 < states; var16 += 1) {
			double[] var17 = m[var16];
			if(!fixedFlag$sample18)
				DistributionSampling.sampleDirichlet(RNG$, v, var17);
		}
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(!fixedFlag$sample36)
				b[i$var26] = a[(i$var26 - 1)];
			if(!fixedFlag$sample36)
				a[i$var26] = DistributionSampling.sampleCategorical(RNG$, m[b[i$var26]]);
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
		for(int i$var26 = 1; i$var26 < n; i$var26 += 1) {
			if(setFlag$a)
				b[i$var26] = a[(i$var26 - 1)];
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\n/**\n * A model for the fairness work.\n */\npublic model Deterministic2(int n, boolean[] flipsMeasured) {\n    int states = 5;\n\n    double[] v = new double[states];\n    for(int i:[0..states))\n        v[i] = 0.1;\n    \n    double[][] m = dirichlet(v).sample(states);\n\n    int[] a = new int[n];\n    int[] b = new int[n];\n    a[0] = 0;\n    for(int i=1; i<n; i++) {\n        b[i] = a[i-1];\n        a[i] = categorical(m[b[i]]).sampleDistribution();\n    }\n    \n    boolean[] flips = new boolean[n];\n            \n    for(int j:[0..n))\n        flips[j] = bernoulli(1/a[j+1]).sample();\n        flips.observe(flipsMeasured);\n}";
	}
}